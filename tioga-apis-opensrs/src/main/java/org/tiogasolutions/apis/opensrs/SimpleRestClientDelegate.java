package org.tiogasolutions.apis.opensrs;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.tiogasolutions.apis.opensrs.pub.DnLookupResponse;
import org.tiogasolutions.dev.common.IoUtils;
import org.tiogasolutions.dev.common.exceptions.ApiException;
import org.tiogasolutions.dev.common.json.JsonTranslator;
import org.tiogasolutions.dev.jackson.TiogaJacksonTranslator;
import org.tiogasolutions.lib.jaxrs.client.SimpleRestClient;

import javax.ws.rs.core.MediaType;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;

public class SimpleRestClientDelegate extends OpenSrsDelegate {

    private static final Logger log = LoggerFactory.getLogger(SimpleRestClientDelegate.class);

    private final SimpleRestClient restClient;

    public SimpleRestClientDelegate() {
        System.setProperty("sun.net.http.allowRestrictedHeaders", "true");

        JsonTranslator translator = new TiogaJacksonTranslator();
        String rootUrl = String.format("https://%s:%s", host, port);
        restClient = new SimpleRestClient(translator, rootUrl);
    }

    @Override
    public DnLookupResponse lookup(String xml, String domainName, boolean noCache) throws Exception {

        Map<String,Object> headers = new HashMap<>();
        headers.put("Content-Length", String.valueOf(xml.length()));
        headers.put("Content-Type", "text/xml");
        headers.put("X-Username", username);

        String signature = md5Sum(md5Sum(xml + privateKey) + privateKey);
        headers.put("X-Signature", signature);

        try {
            String xmlResponse = restClient.post(String.class, MediaType.TEXT_XML_TYPE, "/", xml, headers, MediaType.APPLICATION_XML);
            return DnLookupResponse.fromXmlResponse(domainName, xmlResponse);

        } catch (ApiException e) {
            if (e.getStatusCode() == -1) {
                String ipAddress = "unknown";

                try { ipAddress = IoUtils.toString(URI.create("https://api.ipify.org").toURL().openStream());
                } catch (Exception ignored) {/* ignored*/}

                String msg = String.format("Our upstream service provider is temporarily unavailable (%s).", ipAddress.trim());
                throw ApiException.serviceUnavailable(msg);
            }
            throw e;
        }
/*
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
*/
    }
}
