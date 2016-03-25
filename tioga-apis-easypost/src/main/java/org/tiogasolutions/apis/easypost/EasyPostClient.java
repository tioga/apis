package org.tiogasolutions.apis.easypost;

import org.tiogasolutions.apis.easypost.carrier.PredefinedPackage;
import org.tiogasolutions.apis.easypost.pub.*;
import org.tiogasolutions.apis.easypost.requests.*;
import org.tiogasolutions.dev.common.json.JsonTranslator;
import org.tiogasolutions.dev.jackson.TiogaJacksonTranslator;
import org.tiogasolutions.lib.jaxrs.client.BasicAuthorization;
import org.tiogasolutions.lib.jaxrs.client.SimpleRestClient;

import javax.ws.rs.core.Form;

public class EasyPostClient {

  private final SimpleRestClient client;

  public EasyPostClient(String apiKey) {
    JsonTranslator translator = new TiogaJacksonTranslator();
    this.client = new SimpleRestClient(translator, "https://api.easypost.com/v2", new BasicAuthorization(apiKey, ""));
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

  
  public Shipment createShipment(CreateParcelRequest newParcel, CreateAddressRequest fromAddress, CreateAddressRequest toAddress, LabelFormat labelFormat) {
    CreateShipmentRequest newShipment = new CreateShipmentRequest(newParcel, fromAddress, toAddress, labelFormat);
    return createShipment(newShipment);
  }
  public Shipment createShipmentFrom(Parcel parcel, Address fromAddress, Address toAddress, LabelFormat labelFormat) {
    CreateShipmentRequest newShipment = new CreateShipmentRequest(parcel, fromAddress, toAddress, labelFormat);
    return createShipment(newShipment);
  }
  public Shipment createShipment(CreateShipmentRequest newShipment) {
    Form form = newShipment.toForm();
    return client.post(Shipment.class, "/shipments", form);
  }

  public BuyRateResponse buyPostage(BuyRateRequest buyRateRequest) {
    Form form = buyRateRequest.toForm();

    String url = String.format("/shipments/%s/buy", buyRateRequest.getShipmentId());
    return client.post(BuyRateResponse.class, url, form);
  }

  public Shipment getShipment(Shipment shipment) {
    return getShipment(shipment.getId());
  }

  public Shipment getShipment(String id) {
    String url = String.format("/shipments/%s", id);
    return client.get(Shipment.class, url);
  }

  public Parcel getParcel(Parcel parcel) {
    return getParcel(parcel.getId());
  }

  public Parcel getParcel(String id) {
    String url = String.format("/parcels/%s", id);
    return client.get(Parcel.class, url);
  }

  public Address getAddress(Address address) {
    return getAddress(address.getId());
  }

  public Address getAddress(String id) {
    String url = String.format("/addresses/%s", id);
    return client.get(Address.class, url);
  }

//  public PostageLabel getPostageLabel(Shipment shipment, PostageLabel postageLabel) {
//    return getPostageLabel(shipment.getId(), postageLabel.getId());
//  }
//
//  public PostageLabel getPostageLabel(String shipmentId, String labelId) {
//    String url = String.format("/shipments/%s/label", shipmentId, labelId);
//    return client.get(PostageLabel.class, url, "file_format=PDF");
//  }
}












