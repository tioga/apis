package org.tiogasolutions.apis.opensrs;

public enum OpenSrsEnv {

  test("horizon.opensrs.net"),
  live("rr-n1-tor.opensrs.net");

  private final String host;

  OpenSrsEnv(String host) {
    this.host = host;
  }

  public String getHost() {
    return host;
  }
}
