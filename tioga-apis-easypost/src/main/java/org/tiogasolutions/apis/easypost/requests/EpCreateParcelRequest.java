package org.tiogasolutions.apis.easypost.requests;

import org.tiogasolutions.apis.easypost.carrier.EpPredefinedPackage;

import javax.ws.rs.core.Form;

public class EpCreateParcelRequest {

  private final double weight;
  private final Double length;
  private final Double width;
  private final Double height;
  private final String predefinedPackage;

  public EpCreateParcelRequest(double weight, EpPredefinedPackage predefinedPackage) {
    this.weight = weight;
    this.predefinedPackage = predefinedPackage.getCode();

    this.length = null;
    this.width = null;
    this.height = null;
  }

  public EpCreateParcelRequest(double weight, double length, double width, double height) {
    this.weight = weight;
    this.length = length;
    this.width = width;
    this.height = height;

    this.predefinedPackage = null;
  }

  public double getWeight() {
    return weight;
  }

  public Double getLength() {
    return length;
  }

  public Double getWidth() {
    return width;
  }

  public Double getHeight() {
    return height;
  }

  public String getPredefinedPackage() {
    return predefinedPackage;
  }

  public Form toForm() {
    Form form = new Form();

    if (predefinedPackage != null) {
      form.param("parcel[weight]", String.valueOf(weight));
      form.param("parcel[predefined_package]", predefinedPackage);

    } else {
      form.param("parcel[weight]", String.valueOf(weight));
      form.param("parcel[length]", String.valueOf(length));
      form.param("parcel[width]", String.valueOf(width));
      form.param("parcel[height]", String.valueOf(height));
    }

    return form;
  }
}
