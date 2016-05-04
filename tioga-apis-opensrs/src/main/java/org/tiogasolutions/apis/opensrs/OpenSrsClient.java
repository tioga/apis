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
import org.tiogasolutions.dev.common.EnvUtils;

import java.math.BigInteger;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class OpenSrsClient {

    private static final Logger log = LoggerFactory.getLogger(OpenSrsClient.class);

    private static String OPEN_SRS_API_KEY = "OPEN_SRS_API_KEY";
    private static String OPEN_SRS_API_LOGIN = "OPEN_SRS_API_LOGIN";

    private final String privateKey;
    private final String host;
    private final int port;
    private final String userName;

    private final HttpClient httpClient;

    private OpenSrsClient(int maxConnections, String host, int port, String userName, String privateKey) {
        this.host = host;
        this.port = port;
        this.userName = userName;
        this.privateKey = privateKey;

        HttpClientParams params = new HttpClientParams();
        params.setConnectionManagerTimeout(3*60*1000);
        params.setSoTimeout(2*60*1000);

        MultiThreadedHttpConnectionManager manager = new MultiThreadedHttpConnectionManager();
        manager.setMaxTotalConnections(maxConnections);
        manager.setMaxConnectionsPerHost(maxConnections);

        httpClient = new HttpClient(params, manager);
    }

    protected String md5Sum(String str) throws NoSuchAlgorithmException {
        MessageDigest md5 = MessageDigest.getInstance("MD5");
        return String.format("%032x", new BigInteger(1, md5.digest(str.getBytes())));
    }

    public DnLookupResponse lookup(String domainName, boolean noCache) throws Exception {
        String xml = "<?xml version='1.0' encoding='UTF-8' standalone='no' ?>" +
                "<?xml version='1.0' encoding='UTF-8' standalone='no' ?>" +
                "<!DOCTYPE OPS_envelope SYSTEM 'ops.dtd'>" +
                "<OPS_envelope>" +
                "   <header>" +
                "       <version>0.9</version>" +
                "       <msg_id>2.21765911726198</msg_id>" +
                "       <msg_type>standard</msg_type>" +
                "   </header>" +
                "   <body>" +
                "       <data_block>" +
                "           <dt_assoc>" +
                "               <item key='attributes'>" +
                "                   <dt_assoc>" +
                "                       <item key='domain'>" + domainName + "</item>" +
                "                       <item key='no_cache'>" + (noCache ? 1 : 0) + "</item>" +
                "                       <item key='pre-reg'>0</item>" +
                "                   </dt_assoc>" +
                "               </item>" +
                "               <item key='object'>DOMAIN</item>" +
                "               <item key='action'>LOOKUP</item>" +
                "               <item key='protocol'>XCP</item>" +
                "           </dt_assoc>" +
                "       </data_block>" +
                "   </body>" +
                "</OPS_envelope>";

        String portStr = String.valueOf(port);
        Protocol.registerProtocol("https", new Protocol("https", (ProtocolSocketFactory) new MySSLSocketFactory(), port));

        String uri = "https://" + host + ":" + portStr + "/";
        PostMethod postRequest = new PostMethod(uri);
        postRequest.addRequestHeader("Content-Length", String.valueOf(xml.length()));
        postRequest.addRequestHeader("Content-Type", "text/xml");
        postRequest.addRequestHeader("X-Username", userName);

        String signature = md5Sum(md5Sum(xml + privateKey) + privateKey);
        postRequest.addRequestHeader("X-Signature", signature);

        postRequest.setRequestEntity(new StringRequestEntity(xml, "application/xml", "utf-8"));

        try {
            httpClient.executeMethod(postRequest);
        } catch (SocketTimeoutException ex) {
            log.info("Read timed out, retrying: {}", ex.getMessage());
            return lookup(domainName, noCache);
        } catch (UnknownHostException ex) {
            log.info("Connection timed out, retrying: {}", ex.getMessage());
            return lookup(domainName, noCache);

        } catch (Exception ex) {
            throw new Exception("Sending post got exception ", ex);
        }

        String xmlResponse = postRequest.getResponseBodyAsString();
        DnLookupResponse response = DnLookupResponse.fromXmlResponse(domainName, xmlResponse);

        if (response.getResponseCode() == 310) {
            log.info("Client connection limit exceeded, retrying.");
            return lookup(domainName, noCache);
        } else {
            return response;
        }
    }

    public static OpenSrsClient liveHttps(int maxConnections) {
        String username = EnvUtils.requireProperty(OpenSrsClient.OPEN_SRS_API_LOGIN);
        String privateKey = EnvUtils.requireProperty(OpenSrsClient.OPEN_SRS_API_KEY);
        return liveHttps(maxConnections, username, privateKey);
    }

    public static OpenSrsClient liveHttps(int maxConnections, String username, String privateKey) {
        return new OpenSrsClient(maxConnections, "rr-n1-tor.opensrs.net", 55443, username, privateKey);
    }
}