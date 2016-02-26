package org.tiogasolutions.apis.bitly;

import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.tiogasolutions.dev.common.EnvUtils;
import org.tiogasolutions.dev.common.exceptions.ApiException;

import static org.testng.Assert.assertEquals;

@Test
public class BitlyApisTest {

  private BitlyApis bitlyApis;

  @BeforeClass
  public void beforeClass() {
    try {
      String accessToken = EnvUtils.requireProperty("tioga.test.bitly.access.token", SkipException.class);
      bitlyApis = new BitlyApis(accessToken);

    } catch (Exception ex) {
      throw new SkipException("Authentication required for test.", ex);
    }
  }

  public void testShortenJunk() throws Exception {
    try {
      bitlyApis.shortenUnencodedUrl("http://");
      Assert.fail("Expected exception");
    } catch (ApiException ex) {
      assertEquals(ex.getMessage(), "500: Unable to process request to shortenEncoded http:// (INVALID_URI)");
    }
  }

  public void testShortenUnencoded() throws Exception {
    String shortUrl = bitlyApis.shortenUnencodedUrl("https://www.google.com/search?q=testing+123&oq=testing+123");
    assertEquals(shortUrl, "http://bit.ly/1A3UVdc");
  }

  public void testShortenEncoded() throws Exception {
    String shortUrl = bitlyApis.shortenEncodedUrl("https%3A%2F%2Fwww.google.com%2Fsearch%3Fq%3Dtesting%2B123%26oq%3Dtesting%2B123");
    assertEquals(shortUrl, "http://bit.ly/1A3UVdc");
  }

  public void parseAndShorten() throws Exception {
    assertEquals(bitlyApis.shortenUnencodedUrl("http://example.com/where?x=kitty+hawk"),     "http://bit.ly/1zjZ1bN");
    assertEquals(bitlyApis.shortenUnencodedUrl("https://example.com/where?y=mickey.mouse"),  "http://bit.ly/1zjZ7A9");
    assertEquals(bitlyApis.shortenUnencodedUrl("https://www.example.com/where?a1&b=2&c=3"),  "http://bit.ly/1zjZhYl");

    String result = bitlyApis.parseAndShorten(TEST_DATA);
    assertEquals(result, EXPECTED_DATA);
  }

  private static final String TEST_DATA =
    "This is a test for http://example.com/where?x=kitty+hawk\n" +
      "and https://example.com/where?y=mickey.mouse but before \n" +
      "we get into that, you really http:// need to check out \n" +
      "https://www.example.com/where?a1&b=2&c=3";

  private static final String EXPECTED_DATA =
    "This is a test for http://bit.ly/1zjZ1bN\n" +
      "and http://bit.ly/1zjZ7A9 but before \n" +
      "we get into that, you really http:// need to check out \n" +
      "http://bit.ly/1zjZhYl";
}