package org.tiogasolutions.apis.easypost.pub;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties({"object"})
public class TrackingLocation {
    private final String city;
    private final String state;
    private final String country;
    private final String zip;

    public TrackingLocation(@JsonProperty("city") String city,
                            @JsonProperty("state") String state,
                            @JsonProperty("country") String country,
                            @JsonProperty("zip") String zip) {
        this.city = city;
        this.state = state;
        this.country = country;
        this.zip = zip;
    }

  public String getCity() {
    return city;
  }

  public String getState() {
    return state;
  }

  public String getCountry() {
    return country;
  }

  public String getZip() {
    return zip;
  }
}
