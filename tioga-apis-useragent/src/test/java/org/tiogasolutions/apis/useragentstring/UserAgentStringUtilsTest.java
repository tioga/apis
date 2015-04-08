package org.tiogasolutions.apis.useragentstring;

import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class UserAgentStringUtilsTest {

  @Test
  public void testLookupUserAgent() throws Exception {
    String uas = "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/36.0.1985.125 Safari/537.36";
    UserAgentModel uam = UserAgentStringUtils.lookupUserAgent(uas);
    
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