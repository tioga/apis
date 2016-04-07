package org.tiogasolutions.apis.easypost.pub;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.ZonedDateTime;
import java.util.Collections;
import java.util.Map;

@JsonIgnoreProperties("object")
public class EpParcel {

	private final String id;
  private final EpSystemMode mode;
  private final ZonedDateTime createdAt;
  private final ZonedDateTime updatedAt;
  private final Map<String,String> verifications;

	private final String predefinedPackage;

	private final double weight;
	private final double length;
	private final double width;
	private final double height;

  @JsonCreator
  public EpParcel(@JsonProperty("id") String id,
                  @JsonProperty("mode") EpSystemMode mode,
                  @JsonProperty("created_at") ZonedDateTime createdAt,
                  @JsonProperty("updated_at") ZonedDateTime updatedAt,

                  @JsonProperty("predefined_package") String predefinedPackage,
                  @JsonProperty("weight") double weight,
                  @JsonProperty("length") double length,
                  @JsonProperty("width") double width,
                  @JsonProperty("height") double height,
                  @JsonProperty("verifications") Map<String,String> verifications) {

    this.id = id;
    this.mode = mode;
    this.createdAt = createdAt;
    this.updatedAt = updatedAt;
    this.verifications = (verifications != null) ? verifications : Collections.emptyMap();

    this.predefinedPackage = predefinedPackage;
    this.weight = weight;
    this.length = length;
    this.width = width;
    this.height = height;
  }

  public String getId() {
    return id;
  }

  public EpSystemMode getMode() {
    return mode;
  }

  @JsonProperty("created_at")
  public ZonedDateTime getCreatedAt() {
    return createdAt;
  }

  @JsonProperty("updated_at")
  public ZonedDateTime getUpdatedAt() {
    return updatedAt;
  }

  public Map<String, String> getVerifications() {
    return verifications;
  }

  @JsonProperty("predefined_package")
  public String getPredefinedPackage() {
    return predefinedPackage;
  }

  public double getWeight() {
    return weight;
  }

  public double getLength() {
    return length;
  }

  public double getWidth() {
    return width;
  }

  public double getHeight() {
    return height;
  }
}
