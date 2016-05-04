package org.tiogasolutions.apis.opensrs;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.tiogasolutions.apis.opensrs.pub.DnLookupResponse;
import org.tiogasolutions.apis.opensrs.pub.DnStatus;

@Test
public class OpenSrsClientTest {

    public void testAvailableDomain() throws Exception {
        OpenSrsClient client = OpenSrsClient.liveHttps(5);

        DnLookupResponse response = client.lookup("shamingstick.com", false);
        Assert.assertNotNull(response);
        Assert.assertEquals(response.getDomainName(), "shamingstick.com");
        Assert.assertEquals(response.getStatus(), DnStatus.available);
    }

    public void testTakenDomain() throws Exception {
        OpenSrsClient client = OpenSrsClient.liveHttps(5);

        DnLookupResponse response = client.lookup("google.com", false);
        Assert.assertNotNull(response);
        Assert.assertEquals(response.getDomainName(), "google.com");
        Assert.assertEquals(response.getStatus(), DnStatus.taken);
    }
}