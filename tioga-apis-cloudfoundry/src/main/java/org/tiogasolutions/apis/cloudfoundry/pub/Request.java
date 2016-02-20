package org.tiogasolutions.apis.cloudfoundry.pub;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Request {

  private final String state;
  private final String name;
  private final int instances;
  private final int memory;
  private final String buildpack;
  private final String environmentJson;
  private final int healthCheckTimeout;
  private final String route;
  private final String organizationGuid;
  private final String developerGuids;


  public Request(@JsonProperty("state") String state,
                 @JsonProperty("name") String name,
                 @JsonProperty("instances") int instances,
                 @JsonProperty("memory") int memory,
                 @JsonProperty("buildpack") String buildpack,
                 @JsonProperty("environment_json") String environmentJson,
                 @JsonProperty("health_check_timeout") int healthCheckTimeout,
                 @JsonProperty("route") String route,
                 @JsonProperty("organization_guid") String organizationGuid,
                 @JsonProperty("developer_guids") String developerGuids) {

    this.state = state;
    this.name = name;
    this.instances = instances;
    this.memory = memory;
    this.buildpack = buildpack;
    this.environmentJson = environmentJson;
    this.healthCheckTimeout = healthCheckTimeout;
    this.route = route;
    this.organizationGuid = organizationGuid;
    this.developerGuids = developerGuids;
  }

  public String getOrganizationGuid() {
    return organizationGuid;
  }

  public String getRoute() {
    return route;
  }

  public String getState() {
    return state;
  }

  public String getName() {
    return name;
  }

  public int getInstances() {
    return instances;
  }

  public int getMemory() {
    return memory;
  }

  public String getBuildpack() {
    return buildpack;
  }

  public String getEnvironmentJson() {
    return environmentJson;
  }

  public int getHealthCheckTimeout() {
    return healthCheckTimeout;
  }

  public String getDeveloperGuids() {
    return developerGuids;
  }
}
