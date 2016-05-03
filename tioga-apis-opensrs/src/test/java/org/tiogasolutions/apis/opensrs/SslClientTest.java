package org.tiogasolutions.apis.opensrs;

import org.testng.annotations.Test;
import org.tiogasolutions.dev.common.EnvUtils;

@Test
public class SslClientTest {

    public void testAvailability() throws Exception {

        String privateKey = EnvUtils.requireProperty(OpenSrsApi.OPEN_SRS_API_KEY);
        String userName = EnvUtils.requireProperty(OpenSrsApi.OPEN_SRS_API_LOGIN);

        int port = OpenSrsPort.secure.getPort();
        String host = OpenSrsEnv.live.getHost();

        String xml = "<?xml version='1.0' encoding='UTF-8' standalone='no' ?>" +
                "<?xml version='1.0' encoding='UTF-8' standalone='no' ?>"+
                "<!DOCTYPE OPS_envelope SYSTEM 'ops.dtd'>"+
                "<OPS_envelope>"+
                "<header>"+
                "<version>0.9</version>"+
                "<msg_id>2.21765911726198</msg_id>"+
                "<msg_type>standard</msg_type>"+
                "</header>"+
                "<body>"+
                "<data_block>"+
                "<dt_assoc>"+
                "<item key='attributes'>"+
                "<dt_assoc>"+
                "<item key='domain'>shamingstick.com</item>"+
                "<item key='pre-reg'>0</item>"+
                "</dt_assoc>"+
                "</item>"+
                "<item key='object'>DOMAIN</item>"+
                "<item key='action'>LOOKUP</item>"+
                "<item key='protocol'>XCP</item>"+
                "</dt_assoc>"+
                "</data_block>"+
                "</body>"+
                "</OPS_envelope>";

        SslClient sslclient = new SslClient(host, port, userName, privateKey);

        String response = sslclient.sendRequest(xml);
        System.out.println("\nResponse is:\n" + response);
    }
}