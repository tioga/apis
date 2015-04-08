package org.tiogasolutions.apis.opensrs;

public class OpenSrsApi {

  public static String OPEN_SRS_API_KEY = "OPEN_SRS_API_KEY";
  public static String OPEN_SRS_API_LOGIN = "OPEN_SRS_API_LOGIN";

  private final OpenSrsPort port;
  private final OpenSrsEnv environment;

  public OpenSrsApi(OpenSrsEnv environment, OpenSrsPort port) {
    this.port = port;
    this.environment = environment;
  }

  public OpenSrsPort getPort() {
    return port;
  }

  public OpenSrsEnv getEnvironment() {
    return environment;
  }
}
