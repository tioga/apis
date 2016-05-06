package org.tiogasolutions.apis.opensrs;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.MultiThreadedHttpConnectionManager;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.StringRequestEntity;
import org.apache.commons.httpclient.params.HttpClientParams;
import org.apache.commons.httpclient.protocol.Protocol;
import org.apache.commons.httpclient.protocol.ProtocolSocketFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.tiogasolutions.apis.opensrs.pub.DnLookupResponse;
import org.tiogasolutions.apis.opensrs.support.MySSLSocketFactory;

import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

public class HttpClientDelegate extends OpenSrsDelegate {

    private static final Logger log = LoggerFactory.getLogger(HttpClientDelegate.class);

    private final HttpClient httpClient;

    public HttpClientDelegate(int maxConnections) {
        HttpClientParams params = new HttpClientParams();
        params.setConnectionManagerTimeout(3*60*1000);
        params.setSoTimeout(2*60*1000);

        MultiThreadedHttpConnectionManager manager = new MultiThreadedHttpConnectionManager();
        manager.setMaxTotalConnections(maxConnections);
        manager.setMaxConnectionsPerHost(maxConnections);

        httpClient = new HttpClient(params, manager);
    }

    public DnLookupResponse lookup(String xml, String domainName, boolean noCache) throws Exception {

        String portStr = String.valueOf(port);
        Protocol.registerProtocol("https", new Protocol("https", (ProtocolSocketFactory) new MySSLSocketFactory(), port));

        String uri = "https://" + host + ":" + portStr + "/";
        PostMethod postRequest = new PostMethod(uri);
        postRequest.addRequestHeader("Content-Length", String.valueOf(xml.length()));
        postRequest.addRequestHeader("Content-Type", "text/xml");
        postRequest.addRequestHeader("X-Username", username);

        String signature = md5Sum(md5Sum(xml + privateKey) + privateKey);
        postRequest.addRequestHeader("X-Signature", signature);

        postRequest.setRequestEntity(new StringRequestEntity(xml, "application/xml", "utf-8"));

        try {
            httpClient.executeMethod(postRequest);
        } catch (SocketTimeoutException ex) {
            log.info("Read timed out, retrying: {}", ex.getMessage());
            return lookup(xml, domainName, noCache);
        } catch (UnknownHostException ex) {
            log.info("Connection timed out, retrying: {}", ex.getMessage());
            return lookup(xml, domainName, noCache);
        } catch (Exception ex) {
            throw new Exception("Sending post got exception ", ex);
        }

        String xmlResponse = postRequest.getResponseBodyAsString();
        return DnLookupResponse.fromXmlResponse(domainName, xmlResponse);
    }
}