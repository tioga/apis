package org.tiogasolutions.apis.cloudfoundry.pub;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Map;

public class AppResource {

  private final App app;
  private final Metadata metadata;

  public AppResource(@JsonProperty("entity") App app,
                     @JsonProperty("metadata") Metadata metadata) {

    this.app = app;
    this.metadata = metadata;
  }

  public Metadata getMetadata() {
    return metadata;
  }

  public App getApp() {
    return app;
  }
}
