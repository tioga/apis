package org.tiogasolutions.apis.opensrs.support;

import org.apache.commons.httpclient.params.HttpConnectionParams;
import org.apache.commons.httpclient.protocol.SecureProtocolSocketFactory;

import javax.net.SocketFactory;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class MySSLSocketFactory implements SecureProtocolSocketFactory {

    private TrustManager[] getTrustManager() {
        return new TrustManager[]{
                new X509TrustManager() {
                    public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                        return null;
                    }

                    public void checkClientTrusted(
                            java.security.cert.X509Certificate[] certs, String authType) {
                    }

                    public void checkServerTrusted(
                            java.security.cert.X509Certificate[] certs, String authType) {
                    }
                }
        };
    }

    public Socket createSocket(String host, int port) throws IOException {
        return createSocket(host, port, null, 0, null);
    }

    public Socket createSocket(Socket socket, String host, int port, boolean flag) throws IOException {
        return createSocket(host, port, null, 0, null);
    }

    public Socket createSocket(String host, int port, InetAddress clientHost, int clientPort) throws IOException {
        return createSocket(host, port, clientHost, clientPort, null);
    }

    @Override
    public Socket createSocket(String host, int port, InetAddress clientHost, int clientPort, HttpConnectionParams params) throws IOException {
        TrustManager[] trustAllCerts = getTrustManager();
        try {
            SSLContext sc = SSLContext.getInstance("SSL");
            sc.init(null, trustAllCerts, new java.security.SecureRandom());
            HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
            SocketFactory socketFactory = HttpsURLConnection.getDefaultSSLSocketFactory();

            // return socketFactory.createSocket(host, port);
            return socketFactory.createSocket(host, port, clientHost, clientPort);

        } catch (Exception ex) {
            throw new UnknownHostException("Problems to connect " + host + ex.toString());
        }
    }
}
