package org.tiogasolutions.apis.easypost.pub;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ShipmentMessage {

  private final String carrier;
  private final String type;
  private final String message;

  public ShipmentMessage(@JsonProperty("carrier") String carrier,
                         @JsonProperty("type") String type,
                         @JsonProperty("message") String message) {

    this.carrier = carrier;
    this.type = type;
    this.message = message;
  }

  public String getCarrier() {
    return carrier;
  }

  public String getType() {
    return type;
  }

  public String getMessage() {
    return message;
  }
}
