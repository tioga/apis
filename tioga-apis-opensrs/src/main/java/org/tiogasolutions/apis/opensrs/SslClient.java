package org.tiogasolutions.apis.opensrs;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.StringRequestEntity;
import org.apache.commons.httpclient.params.HttpClientParams;
import org.apache.commons.httpclient.params.HttpConnectionParams;
import org.apache.commons.httpclient.protocol.Protocol;
import org.apache.commons.httpclient.protocol.ProtocolSocketFactory;
import org.apache.commons.httpclient.protocol.SecureProtocolSocketFactory;
import org.tiogasolutions.dev.common.EnvUtils;

import javax.net.SocketFactory;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.io.IOException;
import java.math.BigInteger;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class SslClient {

  private String privateKey;
  private String host;
  private int port;
  private String userName;

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
    public Socket createSocket(String host, int port, InetAddress clientHost, int clientPort, HttpConnectionParams params) throws IOException{
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

  public SslClient(String host, int port, String userName, String privateKey) {
    this.host = host;
    this.port = port;
    this.userName = userName;
    this.privateKey = privateKey;
  }

  protected static String md5Sum(String str) throws NoSuchAlgorithmException {
    MessageDigest md5 = MessageDigest.getInstance("MD5");
    return String.format("%032x", new BigInteger(1, md5.digest(str.getBytes())));
  }

  public static String getSignature(String xml, String privateKey) throws NoSuchAlgorithmException {
    return md5Sum(md5Sum(xml + privateKey) + privateKey);
  }

  public String sendRequest(String xml) throws Exception {

    HttpClientParams params = new HttpClientParams();
    params.setConnectionManagerTimeout(60000);
    params.setSoTimeout(60000);

    HttpClient client = new HttpClient(params);

    String portStr = String.valueOf(port);
    Protocol.registerProtocol("https", new Protocol("https", (ProtocolSocketFactory)new MySSLSocketFactory(), port));
    String signature = getSignature(xml, privateKey);
    String uri = "https://" + host + ":" + portStr + "/";
    PostMethod postRequest = new PostMethod(uri);
    postRequest.addRequestHeader("Content-Length", String.valueOf(xml.length()));
    postRequest.addRequestHeader("Content-Type", "text/xml");
    postRequest.addRequestHeader("X-Signature", signature);
    postRequest.addRequestHeader("X-Username", userName);

    postRequest.setRequestEntity(new StringRequestEntity(xml, "application/xml", "utf-8"));

    System.out.println("Sending https request....." + postRequest.toString());

    try {
      client.executeMethod(postRequest);

    } catch (Exception ex) {
      throw new Exception("Sending post got exception ", ex);
    }

    return postRequest.getResponseBodyAsString();
  }
}