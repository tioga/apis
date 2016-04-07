package org.tiogasolutions.apis.easypost.requests;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.ws.rs.core.Form;
import java.time.ZonedDateTime;
import java.util.Collections;
import java.util.Map;

public class EpCreateAddressRequest {

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
  private final boolean residential;

  public EpCreateAddressRequest(String name,
                                String company,
                                String street1,
                                String street2,
                                String city,
                                String state,
                                String zip,
                                String country,
                                String phone,
                                String email,
                                boolean residential) {

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
    this.residential = residential;
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

  public boolean isResidential() {
    return residential;
  }

  public Form toForm() {
    Form form = new Form();
    form.param("address[name]", name);
    form.param("address[company]", company);
    form.param("address[street1]", street1);
    form.param("address[street2]", street2);
    form.param("address[city]", city);
    form.param("address[state]", state);
    form.param("address[zip]", zip);
    form.param("address[country]", country);
    form.param("address[phone]", phone);
    form.param("address[email]", email);
    form.param("address[residential]", String.valueOf(residential));
    return form;
  }
}
