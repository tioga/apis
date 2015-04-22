package org.tiogasolutions.apis.bitly;

import org.tiogasolutions.dev.common.StringUtils;
import org.tiogasolutions.dev.common.exceptions.ApiException;
import org.tiogasolutions.dev.common.json.JsonTranslator;
import org.tiogasolutions.dev.common.net.HttpStatusCode;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.net.URI;

public class BitlyApis {

  private final String accessToken;
  private final String version;
  private final JsonTranslator translator;
  private final String urlTemplate;
  Client client = ClientBuilder.newBuilder().build();

  public BitlyApis(JsonTranslator translator, String accessToken) {
    this("api-ssl.bitly.com", translator, "3", accessToken);
  }

  public BitlyApis(String apiEndPoint, JsonTranslator translator, String version, String accessToken) {
    this.accessToken = accessToken;
    this.version = version;
    this.translator = translator;
    this.urlTemplate = "https://"+apiEndPoint+"/v%s/%s";
  }

  public String parseAndShorten(String content) {
    if (content == null) {
      return null;
    }
    content = parseAndShorten(content, "http://");
    return parseAndShorten(content, "https://");
  }

  protected String parseAndShorten(String content, String prefix) {
    if (content == null) {
      return null;
    }

    int pos = content.indexOf(prefix);
    return parseAndShorten(content, prefix, pos);
  }

  protected String parseAndShorten(String content, String prefix, int posA) {
    if (posA < 0) return content;

    int posB = posA + 1;

    for (; posB < content.length(); posB++) {
      char chr = content.charAt(posB);
      if (Character.isWhitespace(chr)) {
        break;
      }
    }

    int fromIndex;
    String left = content.substring(0, posA);
    String right = content.substring(posB);
    String longUrl = content.substring(posA, posB);

    try {
      String shortUrl = shortenUnencodedUrl(longUrl);
      content = left+shortUrl+right;
      fromIndex = left.length() + shortUrl.length();

    } catch (Exception e) {
      content = left+longUrl+right;
      fromIndex = left.length() + longUrl.length();
    }

    int pos = content.indexOf(prefix, fromIndex);
    return parseAndShorten(content, prefix, pos);
  }

  public String shortenUnencodedUrl(String longUrl) throws IOException {
    longUrl = StringUtils.encodeUrl(longUrl);
    return shortenEncodedUrl(longUrl);
  }

  public String shortenEncodedUrl(String longUrl) throws IOException {
    String prettyUrl = StringUtils.decodeUrl(longUrl);

    String path = String.format(urlTemplate, version, "shorten");
    URI uri = URI.create(path);

    try {
      Response response = client.target(uri)
        .queryParam("format", "txt")
        .queryParam("longUrl", longUrl)
        .queryParam("access_token", accessToken)
        .request(MediaType.APPLICATION_JSON_TYPE)
        .get();

      int status = response.getStatus();
      String text = response.readEntity(String.class).trim();

      if (status == 200) {
        return text;

      } else {
        String msg = String.format("%s: Unable to process request to shortenEncoded %s (%s)", status, prettyUrl, text);
        throw ApiException.fromCode(status, msg);
      }

    } catch (ApiException ex) {
      throw ex;

    } catch (Exception ex) {
      String msg = String.format("Unexpected exception shortening %s", prettyUrl);
      throw new IOException(msg, ex);
    }
  }

  public String getAccessToken() {
    return accessToken;
  }

  public String getVersion() {
    return version;
  }

  public JsonTranslator getTranslator() {
    return translator;
  }
}
