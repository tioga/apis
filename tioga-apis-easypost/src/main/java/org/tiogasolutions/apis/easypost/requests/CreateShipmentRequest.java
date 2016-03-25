package org.tiogasolutions.apis.easypost.requests;

import org.tiogasolutions.apis.easypost.pub.Address;
import org.tiogasolutions.apis.easypost.pub.LabelFormat;
import org.tiogasolutions.apis.easypost.pub.Parcel;

import javax.ws.rs.core.Form;

public class CreateShipmentRequest {

  private final String toAddresssId;
  private final String fromAddressId;
  private final String parcelId;

  private final CreateAddressRequest toAddress;
  private final CreateAddressRequest fromAddress;
  private final CreateParcelRequest parcel;

  private final LabelFormat labelFormat;

  public CreateShipmentRequest(Parcel parcel, Address fromAddress, Address toAddress, LabelFormat labelFormat) {
    this.parcelId = parcel.getId();
    this.toAddresssId = toAddress.getId();
    this.fromAddressId = fromAddress.getId();

    this.parcel = null;
    this.fromAddress = null;
    this.toAddress = null;

    this.labelFormat = labelFormat;
  }

  public CreateShipmentRequest(CreateParcelRequest parcel, CreateAddressRequest toAddresss, CreateAddressRequest fromAddress, LabelFormat labelFormat) {
    this.parcel = parcel;
    this.fromAddress = fromAddress;
    this.toAddress = toAddresss;

    this.toAddresssId = null;
    this.fromAddressId = null;
    this.parcelId = null;

    this.labelFormat = labelFormat;
  }

  public CreateAddressRequest getToAddress() {
    return toAddress;
  }

  public CreateAddressRequest getFromAddress() {
    return fromAddress;
  }

  public CreateParcelRequest getParcel() {
    return parcel;
  }

  public String getToAddresssId() {
    return toAddresssId;
  }

  public String getFromAddressId() {
    return fromAddressId;
  }

  public String getParcelId() {
    return parcelId;
  }

  public LabelFormat getLabelFormat() {
    return labelFormat;
  }

  public Form toForm() {
    Form form = new Form();
    form.param("shipment[options][label_format]", labelFormat.name());

    if (parcelId != null) {
      form.param("shipment[to_address][id]", toAddresssId);
      form.param("shipment[from_address][id]", fromAddressId);
      form.param("shipment[parcel][id]", parcelId);

    } else {
      form.param("shipment[to_address][name]", toAddress.getName());
      form.param("shipment[to_address][company]", toAddress.getCompany());
      form.param("shipment[to_address][street1]", toAddress.getStreet1());
      form.param("shipment[to_address][street2]", toAddress.getStreet2());
      form.param("shipment[to_address][city]", toAddress.getCity());
      form.param("shipment[to_address][state]", toAddress.getState());
      form.param("shipment[to_address][zip]", toAddress.getZip());
      form.param("shipment[to_address][country]", toAddress.getCountry());
      form.param("shipment[to_address][phone]", toAddress.getPhone());
      form.param("shipment[to_address][email]", toAddress.getEmail());
      form.param("shipment[to_address][residential]", String.valueOf(toAddress.isResidential()));

      form.param("shipment[from_address][name]", fromAddress.getName());
      form.param("shipment[from_address][company]", fromAddress.getCompany());
      form.param("shipment[from_address][street1]", fromAddress.getStreet1());
      form.param("shipment[from_address][street2]", fromAddress.getStreet2());
      form.param("shipment[from_address][city]", fromAddress.getCity());
      form.param("shipment[from_address][state]", fromAddress.getState());
      form.param("shipment[from_address][zip]", fromAddress.getZip());
      form.param("shipment[from_address][country]", fromAddress.getCountry());
      form.param("shipment[from_address][phone]", fromAddress.getPhone());
      form.param("shipment[from_address][email]", fromAddress.getEmail());
      form.param("shipment[from_address][residential]", String.valueOf(fromAddress.isResidential()));

      if (parcel.getPredefinedPackage() != null) {
        form.param("shipment[parcel][weight]", String.valueOf(parcel.getWeight()));
        form.param("shipment[parcel][predefined_package]", parcel.getPredefinedPackage());

      } else {
        form.param("shipment[parcel][weight]", String.valueOf(parcel.getWeight()));
        form.param("shipment[parcel][length]", String.valueOf(parcel.getLength()));
        form.param("shipment[parcel][width]",  String.valueOf(parcel.getWidth()));
        form.param("shipment[parcel][height]", String.valueOf(parcel.getHeight()));
      }
    }

    return form;
  }
}
