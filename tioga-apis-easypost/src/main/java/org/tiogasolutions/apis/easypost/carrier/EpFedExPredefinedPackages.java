package org.tiogasolutions.apis.easypost.carrier;

public enum EpFedExPredefinedPackages implements EpPredefinedPackage {

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

  EpFedExPredefinedPackages(String code) {
    this.code = code;
  }

  @Override
  public String getCode() {
    return code;
  }
}
