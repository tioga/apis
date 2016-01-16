package org.tiogasolutions.apis.easypost;

import org.tiogasolutions.apis.easypost.carrier.PredefinedPackage;
import org.tiogasolutions.apis.easypost.pub.*;
import org.tiogasolutions.apis.easypost.requests.CreateAddressRequest;
import org.tiogasolutions.apis.easypost.requests.CreateParcelRequest;
import org.tiogasolutions.apis.easypost.requests.CreateShipmentRequest;
import org.tiogasolutions.dev.common.json.JsonTranslator;
import org.tiogasolutions.dev.jackson.TiogaJacksonTranslator;
import org.tiogasolutions.lib.jaxrs.client.SimpleRestClient;

import javax.ws.rs.core.Form;

public class EasyPostClient {

  private final SimpleRestClient client;

  public EasyPostClient(String apiKey) {
    JsonTranslator translator = new TiogaJacksonTranslator();
    this.client = new SimpleRestClient(translator, "https://api.easypost.com/v2", apiKey, "");
  }

  
  public Address createBusinessAddress(
      String name, String company,
      String street1, String street2,
      String city, String state, String zip,
      String phone, String email) {

    return createAddress(
        name, company,
        street1, street2, city, state, zip, null,
        phone, email,
        false);
  }
  public Address createResidentialAddress(
      String name,
      String street1, String street2,
      String city, String state, String zip,
      String phone, String email) {

    return createAddress(
        name, null,
        street1, street2, city, state, zip, null,
        phone, email,
        true);
  }
  public Address createAddress(
      String name, String company,
      String street1, String street2,
      String city, String state, String zip, String country,
      String phone, String email,
      boolean residential) {
    
    CreateAddressRequest createAddressRequest = new CreateAddressRequest(
        name, company,
        street1, street2,
        city, state, zip, country,
        phone, email,
        residential);
    
    return createAddress(createAddressRequest);
  }
  public Address createAddress(CreateAddressRequest createAddressRequest) {
    Form form = createAddressRequest.toForm();
    return client.post(Address.class, "/addresses", form);
  }

  
  public Parcel createParcel(double weight, PredefinedPackage predefinedPackage) {
    CreateParcelRequest createParcelRequest = new CreateParcelRequest(weight, predefinedPackage);
    return createParcel(createParcelRequest);
  }
  public Parcel createParcel(double weight, double length, double width, double height) {
    CreateParcelRequest createParcelRequest = new CreateParcelRequest(weight, length, width, height);
    return createParcel(createParcelRequest);
  }
  public Parcel createParcel(CreateParcelRequest createParcelRequest) {
    Form form = createParcelRequest.toForm();
    return client.post(Parcel.class, "/parcels", form);
  }

  
  public Shipment createShipment(CreateParcelRequest newParcel, CreateAddressRequest toAddress, CreateAddressRequest fromAddress) {
    CreateShipmentRequest newShipment = new CreateShipmentRequest(newParcel, toAddress, fromAddress);
    return createShipment(newShipment);
  }
  public Shipment createShipmentFrom(Parcel parcel, Address toAddress, Address fromAddress) {
    CreateShipmentRequest newShipment = new CreateShipmentRequest(parcel, toAddress, fromAddress);
    return createShipment(newShipment);
  }
  public Shipment createShipment(CreateShipmentRequest newShipment) {
    Form form = newShipment.toForm();
    return client.post(Shipment.class, "/shipments", form);
  }

  public PostageLabel buyPostage(Rate rate) {
    Form form = new Form();
    form.param("rate[id]", rate.getId());

    String url = String.format("/shipments/%s/buy", rate.getShipmentId());
    return client.post(PostageLabel.class, url, form);
  }
}
