package org.tiogasolutions.apis.useragentstring;

import org.testng.SkipException;
import org.testng.annotations.Test;

import java.net.ConnectException;
import java.net.UnknownHostException;

import static org.testng.Assert.assertEquals;

public class UserAgentStringUtilsTest {

  @Test
  public void testLookupUserAgent() throws Exception {
    UserAgentModel uam = null;
    try {
      String uas = "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/36.0.1985.125 Safari/537.36";
      uam = UserAgentStringUtils.lookupUserAgent(uas);
    } catch (UnknownHostException | ConnectException e) {
      throw new SkipException("Offline", e);
    }

    assertEquals(uam.getAgentType(), "Browser");
    assertEquals(uam.getAgentName(), "Chrome");
    assertEquals(uam.getAgentVersion(), "36.0.1985.125");
    assertEquals(uam.getAgentLanguage(), null);
    assertEquals(uam.getAgentLanguageTag(), null);
    assertEquals(uam.getOsType(), "Windows");
    assertEquals(uam.getOsName(), "Windows 7");
    assertEquals(uam.getOsProducer(), null);
    assertEquals(uam.getOsProducerURL(), null);
    assertEquals(uam.getOsVersionName(), null);
    assertEquals(uam.getOsVersionNumber(), null);
    assertEquals(uam.getLinuxDistribution(), null);
  }
}