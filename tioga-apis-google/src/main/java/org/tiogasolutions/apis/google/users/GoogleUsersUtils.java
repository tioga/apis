package org.tiogasolutions.apis.google.users;

import java.io.IOException;
import javax.ws.rs.client.*;
import javax.ws.rs.core.*;
import org.tiogasolutions.dev.common.StringUtils;
import org.tiogasolutions.dev.common.exceptions.ApiException;
import org.tiogasolutions.dev.jackson.TiogaJacksonObjectMapper;

public class GoogleUsersUtils {

  private GoogleUsersUtils() {
  }

  public static GoogleAuthentication getAuthResponse(String code, String clientId, String clientSecret) throws IOException {

    Client client = ClientBuilder.newBuilder().build();

    Form form = new Form();
    form.param("code", code);
    form.param("client_id", clientId);
    form.param("client_secret", clientSecret);
    form.param("grant_type", "authorization_code");
    form.param("redirect_uri", "postmessage");

    UriBuilder uriBuilder = UriBuilder.fromUri("https://accounts.google.com/o/oauth2/token");
    Response jerseyResponse = client.target(uriBuilder)
        .request(MediaType.APPLICATION_JSON_TYPE)
        .post(Entity.entity(form,MediaType.APPLICATION_FORM_URLENCODED_TYPE));

    int status = jerseyResponse.getStatus();
    String json = jerseyResponse.readEntity(String.class);

    TiogaJacksonObjectMapper objectMapper = new TiogaJacksonObjectMapper();
    GoogleAuthentication googleAuth = objectMapper.readValue(json, GoogleAuthentication.class);

    // If there was an error in the token info, abort.
    if (StringUtils.isNotBlank(googleAuth.getError())) {
      String msg = String.format("Authentication Error: %s", googleAuth.getError());
      throw ApiException.internalServerError(msg);
    }

    return googleAuth;
  }

  public static UserInfo getUserInfo(GoogleAuthentication authentication) throws IOException {
    return getUserInfo(authentication.getAccessToken());
  }

  public static UserInfo getUserInfo(String accessToken) throws IOException {
    Client client = ClientBuilder.newBuilder().build();
    UriBuilder uriBuilder = UriBuilder.fromUri("https://www.googleapis.com/oauth2/v1/userinfo");
    uriBuilder.queryParam("alt", "json");
    uriBuilder.queryParam("access_token", accessToken);

    Response response = client.target(uriBuilder).request(MediaType.APPLICATION_JSON_TYPE).get();
    String json = response.readEntity(String.class);
    return new TiogaJacksonObjectMapper().readValue(json, UserInfo.class);
  }
}
