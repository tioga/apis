package org.tiogasolutions.apis.easypost;

import org.tiogasolutions.apis.easypost.carrier.EpPredefinedPackage;
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

  
  public EpAddress createBusinessAddress(
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
  public EpAddress createResidentialAddress(
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
  public EpAddress createAddress(
      String name, String company,
      String street1, String street2,
      String city, String state, String zip, String country,
      String phone, String email,
      boolean residential) {
    
    EpCreateAddressRequest createAddressRequest = new EpCreateAddressRequest(
        name, company,
        street1, street2,
        city, state, zip, country,
        phone, email,
        residential);
    
    return createAddress(createAddressRequest);
  }
  public EpAddress createAddress(EpCreateAddressRequest createAddressRequest) {
    Form form = createAddressRequest.toForm();
    return client.post(EpAddress.class, "/addresses", form);
  }

  
  public EpParcel createParcel(double weight, EpPredefinedPackage predefinedPackage) {
    EpCreateParcelRequest createParcelRequest = new EpCreateParcelRequest(weight, predefinedPackage);
    return createParcel(createParcelRequest);
  }
  public EpParcel createParcel(double weight, double length, double width, double height) {
    EpCreateParcelRequest createParcelRequest = new EpCreateParcelRequest(weight, length, width, height);
    return createParcel(createParcelRequest);
  }
  public EpParcel createParcel(EpCreateParcelRequest createParcelRequest) {
    Form form = createParcelRequest.toForm();
    return client.post(EpParcel.class, "/parcels", form);
  }

  
  public EpShipment createShipment(EpCreateParcelRequest newParcel, EpCreateAddressRequest fromAddress, EpCreateAddressRequest toAddress, EpLabelFormat labelFormat) {
    EpCreateShipmentRequest newShipment = new EpCreateShipmentRequest(newParcel, fromAddress, toAddress, labelFormat);
    return createShipment(newShipment);
  }
  public EpShipment createShipmentFrom(EpParcel parcel, EpAddress fromAddress, EpAddress toAddress, EpLabelFormat labelFormat) {
    EpCreateShipmentRequest newShipment = new EpCreateShipmentRequest(parcel, fromAddress, toAddress, labelFormat);
    return createShipment(newShipment);
  }
  public EpShipment createShipment(EpCreateShipmentRequest newShipment) {
    Form form = newShipment.toForm();
    return client.post(EpShipment.class, "/shipments", form);
  }

  public EpPurchaseShippingResponse purchaseShipment(EpPurchaseShipmentRequest purchaseRequest) {
    Form form = purchaseRequest.toForm();

    String url = String.format("/shipments/%s/buy", purchaseRequest.getShipmentId());
    return client.post(EpPurchaseShippingResponse.class, url, form);
  }

  public EpShipment getShipment(EpShipment shipment) {
    return getShipment(shipment.getId());
  }

  public EpShipment getShipment(String id) {
    String url = String.format("/shipments/%s", id);
    return client.get(EpShipment.class, url);
  }

  public EpParcel getParcel(EpParcel parcel) {
    return getParcel(parcel.getId());
  }

  public EpParcel getParcel(String id) {
    String url = String.format("/parcels/%s", id);
    return client.get(EpParcel.class, url);
  }

  public EpAddress getAddress(EpAddress address) {
    return getAddress(address.getId());
  }

  public EpAddress getAddress(String id) {
    String url = String.format("/addresses/%s", id);
    return client.get(EpAddress.class, url);
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












