package org.tiogasolutions.apis.cloudfoundry.pub;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.ZonedDateTime;
import java.util.HashMap;
import java.util.Map;

public class Metadata implements Comparable<Metadata> {

  private final String guid;
  private final String exitDescription;
  private final ZonedDateTime createdAt;
  private final Request request;

  private final Map<String,Object> properties = new HashMap<>();

  public Metadata(@JsonProperty("guid") String guid,
                  @JsonProperty("exit_description") String exitDescription,
                  @JsonProperty("created_at") ZonedDateTime createdAt,
                  @JsonProperty("request") Request request) {

    this.guid = guid;
    this.request = request;
    this.createdAt = createdAt;
    this.exitDescription = exitDescription;
  }

  @JsonAnyGetter
  public Object getProperty(String propertyName) {
    return properties.get(propertyName);
  }

  @JsonAnySetter
  public void setProperty(String propertyName, Object value) {
    properties.put(propertyName, value);
  }

  public String getGuid() {
    return guid;
  }

  public String getExitDescription() {
    return exitDescription;
  }

  public ZonedDateTime getCreatedAt() {
    return createdAt;
  }

  public Request getRequest() {
    return request;
  }

  @Override
  public int compareTo(Metadata that) {
    int diff = this.getCreatedAt().compareTo(that.getCreatedAt());
    if (diff != 0) return diff;

    return this.getGuid().compareTo(that.getGuid());
  }
}
