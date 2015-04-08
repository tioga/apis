package org.tiogasolutions.apis.useragentstring;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.tiogasolutions.dev.common.IoUtils;
import org.tiogasolutions.dev.common.StringUtils;
import org.tiogasolutions.dev.jackson.TiogaJacksonObjectMapper;

import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;

public class UserAgentStringUtils {

  private static final ObjectMapper objectMapper = new TiogaJacksonObjectMapper();

  public static UserAgentModel lookupUserAgent(String userAgentString) throws IOException {

    String encodedUserAgent = StringUtils.encodeUrl(userAgentString);
    String url = "http://www.useragentstring.com/?getJSON=all&uas=" + encodedUserAgent;

    URLConnection connection = new URL(url).openConnection();
    String json = IoUtils.toString(connection.getInputStream());

    return objectMapper.readValue(json, UserAgentModel.class);
  }

/*
  public static UserAgentModel lookupUserAgent(String userAgentString) {

    ClientBuilder clientBuilder = ClientBuilder.newBuilder();
    Client client = clientBuilder.build();

    String encodedUserAgent = StringUtils.encodeUrl(userAgentString);
    String uri = "http://www.useragentstring.com/?getJSON=all&uas=" + encodedUserAgent;
    Entity entity = Entity.entity("{}", MediaType.APPLICATION_JSON_TYPE);

    Response jerseyResponse = client.target(uri)
        .request(MediaType.APPLICATION_JSON_TYPE)
        .post(entity);

    int status = jerseyResponse.getStatus();

    if (status / 100 != 2) {
      String msg = String.format("Unexpected response (%s) from Cosmic Push Service.", status);
      throw ApiException.internalServerError(msg);
    }

    try {
      String json = jerseyResponse.readEntity(String.class);
      return objectMapper.readValue(json, UserAgentModel.class);

    } catch (IOException e) {
      throw ApiException.internalServerError("IOException translating response from JSON", e);
    }
  }
*/

  private UserAgentStringUtils() {
  }
}
