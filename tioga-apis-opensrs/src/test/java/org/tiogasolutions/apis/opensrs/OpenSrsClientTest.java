package org.tiogasolutions.apis.opensrs;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.tiogasolutions.apis.opensrs.pub.DnLookupResponse;
import org.tiogasolutions.apis.opensrs.pub.DnStatus;

@Test
public class OpenSrsClientTest {

    public void testAvailableDomain_HC() throws Exception {
        OpenSrsClient client = OpenSrsClient.liveHttps(new HttpClientDelegate(10));

        DnLookupResponse response = client.lookup("shamingstick.com", false);
        Assert.assertNotNull(response);
        Assert.assertEquals(response.getDomainName(), "shamingstick.com");
        Assert.assertEquals(response.getStatus(), DnStatus.available);
    }

    public void testTakenDomain_HC() throws Exception {
        OpenSrsClient client = OpenSrsClient.liveHttps(new HttpClientDelegate(10));

        DnLookupResponse response = client.lookup("google.com", false);
        Assert.assertNotNull(response);
        Assert.assertEquals(response.getDomainName(), "google.com");
        Assert.assertEquals(response.getStatus(), DnStatus.taken);
    }

    public void testAvailableDomain_SRC() throws Exception {
        OpenSrsClient client = OpenSrsClient.liveHttps(new SimpleRestClientDelegate());

        DnLookupResponse response = client.lookup("shamingstick.com", false);
        Assert.assertNotNull(response);
        Assert.assertEquals(response.getDomainName(), "shamingstick.com");
        Assert.assertEquals(response.getStatus(), DnStatus.available);
    }

    public void testTakenDomain_SRC() throws Exception {
        OpenSrsClient client = OpenSrsClient.liveHttps(new SimpleRestClientDelegate());

        DnLookupResponse response = client.lookup("google.com", false);
        Assert.assertNotNull(response);
        Assert.assertEquals(response.getDomainName(), "google.com");
        Assert.assertEquals(response.getStatus(), DnStatus.taken);
    }
}