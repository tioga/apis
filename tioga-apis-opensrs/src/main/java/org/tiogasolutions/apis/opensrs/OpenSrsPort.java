package org.tiogasolutions.apis.opensrs;

public enum OpenSrsPort {

  secure(55443),
  standard(55000);

  private final int port;

  OpenSrsPort(int port) {
    this.port = port;
  }

  public int getPort() {
    return port;
  }
}
