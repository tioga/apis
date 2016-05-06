package org.tiogasolutions.apis.opensrs;

import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.tiogasolutions.apis.opensrs.pub.DnLookupResponse;
import org.tiogasolutions.apis.opensrs.pub.DnStatus;
import org.tiogasolutions.dev.common.EnvUtils;

@Test(enabled = false)
public class OpenSrsClientTest {

    @BeforeClass
    public void beforeClass() {
        String apiLogin = EnvUtils.findProperty("OPEN_SRS_API_LOGIN");
        if (apiLogin == null) {
        throw new SkipException("Open SRS API authentication was not found.");
        }
    }

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