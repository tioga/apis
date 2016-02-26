package org.tiogasolutions.apis.cloudfoundry.pub;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.HashMap;
import java.util.Map;

public class Request {

  private final String state;
  private final Map<String,Object> properties = new HashMap<>();

  public Request(@JsonProperty("state") String state) {
    this.state = state;
  }

  @JsonAnyGetter
  public Object getProperty(String propertyName) {
    return properties.get(propertyName);
  }

  @JsonAnySetter
  public void setProperty(String propertyName, Object value) {
    properties.put(propertyName, value);
  }

  public String getState() {
    return state;
  }
}
