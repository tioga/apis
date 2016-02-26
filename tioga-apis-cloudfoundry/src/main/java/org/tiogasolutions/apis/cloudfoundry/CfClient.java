package org.tiogasolutions.apis.cloudfoundry;

import org.tiogasolutions.apis.cloudfoundry.pub.Event;
import org.tiogasolutions.apis.cloudfoundry.pub.GetAppsResponse;
import org.tiogasolutions.apis.cloudfoundry.pub.GetEventsResponse;
import org.tiogasolutions.apis.cloudfoundry.pub.LoginResponse;
import org.tiogasolutions.dev.jackson.TiogaJacksonTranslator;
import org.tiogasolutions.lib.jaxrs.client.BasicAuthorization;
import org.tiogasolutions.lib.jaxrs.client.BearerAuthorization;
import org.tiogasolutions.lib.jaxrs.client.SimpleRestClient;

import javax.ws.rs.core.Form;
import java.util.SortedSet;

public class CfClient {

  private TiogaJacksonTranslator translator = new TiogaJacksonTranslator();

  private SimpleRestClient loginClient = new SimpleRestClient(translator, "https://login.run.pivotal.io", new BasicAuthorization("cf",""));
  private SimpleRestClient apiClient = new SimpleRestClient(translator, "https://api.run.pivotal.io");

  private String refreshToken;

  public CfClient() {
  }

  public BasicAuthorization getLoginAuthorization() {
    return (BasicAuthorization)loginClient.getAuthorization();
  }

  public BearerAuthorization getApiAuthorization() {
    return (BearerAuthorization)apiClient.getAuthorization();
  }

  public void setIgnoringCertificates(boolean ignoringCertificates) {
    apiClient.setIgnoringCertificates(ignoringCertificates);
    loginClient.setIgnoringCertificates(ignoringCertificates);
  }

  public LoginResponse login(String emailAddress, String password) {
    Form form = new Form();
    form.param("grant_type", "password");
    form.param("username", emailAddress);
    form.param("password", password);

    LoginResponse response = loginClient.post(LoginResponse.class, "/oauth/token", form);

    this.refreshToken = response.getRefreshToken();
    String accessToken = response.getAccessToken();
    apiClient.setAuthorization(new BearerAuthorization(accessToken));

    return response;
  }

  public LoginResponse refresh() {
    Form form = new Form();
    form.param("grant_type", "refresh_token");
    form.param("refresh_token", refreshToken);

    LoginResponse response = loginClient.post(LoginResponse.class, "/oauth/token", form);

    this.refreshToken = response.getRefreshToken();
    String accessToken = response.getAccessToken();
    apiClient.setAuthorization(new BearerAuthorization(accessToken));

    return response;
  }

  public SortedSet<Event> getApplicatinEvents(String appName, int max) {
    GetEventsResponse response = getApplicationEvents(max);
    return response.getAppEvents(appName);
  }

  public GetEventsResponse getApplicationEvents(int max) {
    if (max < 1) max = 100;
    max = Math.min(100, max);
    String resultsPerPage = "results-per-page="+max;
    String orderDirection = "order-direction=desc";
    return apiClient.get(GetEventsResponse.class, "/v2/events", resultsPerPage, orderDirection);
  }

  public GetAppsResponse getApplications() {
    return apiClient.get(GetAppsResponse.class, "/v2/apps");
  }
}
