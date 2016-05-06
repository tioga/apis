package org.tiogasolutions.apis.opensrs;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.tiogasolutions.apis.opensrs.pub.DnLookupResponse;
import org.tiogasolutions.dev.common.EnvUtils;

public class OpenSrsClient {

    private static final Logger log = LoggerFactory.getLogger(OpenSrsClient.class);

    protected static String OPEN_SRS_API_KEY = "OPEN_SRS_API_KEY";
    protected static String OPEN_SRS_API_LOGIN = "OPEN_SRS_API_LOGIN";

    protected final String privateKey;
    protected final String username;

    private final OpenSrsDelegate delegate;

    public OpenSrsClient(OpenSrsDelegate delegate, String username, String privateKey) {
        this.username = username;
        this.privateKey = privateKey;

        this.delegate = delegate.init(username, privateKey);
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

        DnLookupResponse response = delegate.lookup(xml, domainName, noCache);

        if (response.getResponseCode() == 310) {
            log.info("Client connection limit exceeded, retrying.");
            return delegate.lookup(xml, domainName, noCache);
        } else {
            return response;
        }
    }

    public static OpenSrsClient liveHttps(OpenSrsDelegate delegate) {
        String username = EnvUtils.requireProperty(OpenSrsClient.OPEN_SRS_API_LOGIN);
        String privateKey = EnvUtils.requireProperty(OpenSrsClient.OPEN_SRS_API_KEY);
        return liveHttps(delegate, username, privateKey);
    }

    public static OpenSrsClient liveHttps(OpenSrsDelegate delegate, String username, String privateKey) {
        return new OpenSrsClient(delegate, username, privateKey);
    }
}
