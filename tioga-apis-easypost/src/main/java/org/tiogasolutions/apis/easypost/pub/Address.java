package org.tiogasolutions.apis.easypost.pub;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.ZonedDateTime;
import java.util.Collections;
import java.util.Map;

@JsonIgnoreProperties("object")
public class Address {

  private final String id;
  private final SystemMode mode;
  private final ZonedDateTime createdAt;
  private final ZonedDateTime updatedAt;
  private final Map<String,String> verifications;

  private final String name;
  private final String company;
  private final String street1;
  private final String street2;
  private final String zip;
  private final String city;
  private final String state;
  private final String country;
  private final String phone;
  private final String email;
  private final String message;
  private final String carrierFacility;
  private final String federalTaxId;
  private final boolean residential;

  @JsonCreator
  public Address(@JsonProperty("id") String id,
                 @JsonProperty("mode") SystemMode mode,
                 @JsonProperty("created_at") ZonedDateTime createdAt,
                 @JsonProperty("updated_at") ZonedDateTime updatedAt,

                 @JsonProperty("name") String name,
                 @JsonProperty("company") String company,
                 @JsonProperty("street1") String street1,
                 @JsonProperty("street2") String street2,
                 @JsonProperty("city") String city,
                 @JsonProperty("state") String state,
                 @JsonProperty("zip") String zip,
                 @JsonProperty("country") String country,
                 @JsonProperty("phone") String phone,
                 @JsonProperty("email") String email,
                 @JsonProperty("message") String message,
                 @JsonProperty("carrier_facility") String carrierFacility,
                 @JsonProperty("federal_tax_id") String federalTaxId,
                 @JsonProperty("residential") boolean residential,
                 @JsonProperty("verifications") Map<String,String> verifications) {

    this.id = id;
    this.mode = mode;
    this.createdAt = createdAt;
    this.updatedAt = updatedAt;
    this.verifications = (verifications != null) ? verifications : Collections.emptyMap();

    this.name = name;
    this.company = company;
    this.street1 = street1;
    this.street2 = street2;
    this.zip = zip;
    this.city = city;
    this.state = state;
    this.country = country;
    this.phone = phone;
    this.email = email;
    this.message = message;
    this.carrierFacility = carrierFacility;
    this.federalTaxId = federalTaxId;
    this.residential = residential;
  }

  public String getId() {
    return id;
  }

  public SystemMode getMode() {
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

  public String getName() {
    return name;
  }

  public String getCompany() {
    return company;
  }

  public String getStreet1() {
    return street1;
  }

  public String getStreet2() {
    return street2;
  }

  public String getZip() {
    return zip;
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

  public String getPhone() {
    return phone;
  }

  public String getEmail() {
    return email;
  }

  public String getMessage() {
    return message;
  }

  @JsonProperty("carrier_facility")
  public String getCarrierFacility() {
    return carrierFacility;
  }

  @JsonProperty("federal_tax_id")
  public String getFederalTaxId() {
    return federalTaxId;
  }

  public boolean isResidential() {
    return residential;
  }
}
