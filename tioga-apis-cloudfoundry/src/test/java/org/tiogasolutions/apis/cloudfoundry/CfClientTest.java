package org.tiogasolutions.apis.cloudfoundry;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.tiogasolutions.apis.cloudfoundry.pub.*;

import java.util.List;
import java.util.SortedSet;

@Test
public class CfClientTest {

  private CfClient cfClient;

  @BeforeClass
  public void beforeClass() {
    cfClient = new CfClient();
    cfClient.setIgnoringCertificates(true);

    LoginResponse response = cfClient.login("me@jacobparr.com", "go2Pivotal");
    Assert.assertNotNull(response);
  }

  public void testRefresh() throws Exception {
    String oldAccessToken = cfClient.getApiAuthorization().getAccessToken();

    cfClient.refresh();

    String newAccessToken = cfClient.getApiAuthorization().getAccessToken();
    Assert.assertNotEquals(oldAccessToken, newAccessToken);
  }

  public void testGetEvents() throws Exception {
    GetEventsResponse response = cfClient.getApplicationEvents(0);
    Assert.assertNotNull(response);

    SortedSet<Event> events = response.getAppEvents("jacob-parr");
    for (Event event : events) {
      String date = event.getTimestamp().toLocalDateTime().toString().replace("T", " ");
      System.out.printf("%-20s %s\n", date, event.getType());
      if (event.getMetadata().getExitDescription() != null) {
        String desc = event.getMetadata().getExitDescription();
        desc = desc.replace("\n\n", "\n");
        System.out.printf("* %s\n\n", desc);
      }
    }
  }

  public void testGetApplications() throws Exception {
    GetAppsResponse response = cfClient.getApplications();
    Assert.assertNotNull(response);

    List<AppResource> resources = response.getAppResources();
    for (AppResource appResource : resources) {
      App app = appResource.getApp();
      System.out.printf("%s: %s\n", app.getName(), appResource.getMetadata().getGuid());
    }
  }
}
