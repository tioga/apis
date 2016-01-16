package org.tiogasolutions.apis.easypost.carrier;

public enum FedExPredefinedPackages implements PredefinedPackage {

  fedExEnvelope("FedExEnvelope"),
  fedExBox("FedExBox"),
  fedExPak("FedExPak"),
  fedExTube("FedExTube"),
  fedEx10kgBox("FedEx10kgBox"),
  fedEx25kgBox("FedEx25kg"),
  fedExSmallBox("FedExSmallBox"),
  fedExMediumBox("FedExMediumBox"),
  fedExLargeBox("FedExLargeBox"),
  fedExExtraLargeBox("FedExExtraLargeBox");

  private final String code;

  FedExPredefinedPackages(String code) {
    this.code = code;
  }

  @Override
  public String getCode() {
    return code;
  }
}
