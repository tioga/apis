package org.tiogasolutions.apis.easypost.carrier;

public enum UspsPredefinedPackages implements PredefinedPackage {

  uspsFlatRateMapping("USPS Flat Rate Mapping"),
  card("Card"),
  letter("Letter"),
  flat("Flat"),
  parcel("Parcel"),
  largeParcel("LargeParcel"),
  irregularParcel("IrregularParcel"),
  flatRateEnvelope("FlatRateEnvelope"),
  flatRateLegalEnvelope("FlatRateLegalEnvelope"),
  flatRatePaddedEnvelope("FlatRatePaddedEnvelope"),
  flatRateGiftCardEnvelope("FlatRateGiftCardEnvelope"),
  flatRateWindowEnvelope("FlatRateWindowEnvelope"),
  flatRateCardboardEnvelope("FlatRateCardboardEnvelope"),
  smallFlatRateEnvelope("SmallFlatRateEnvelope"),
  smallFlatRateBox("SmallFlatRateBox"),
  mediumFlatRateBox("MediumFlatRate"),
  largeFlatRateBox("LargeFlatRateBox"),
  regionalRateBoxA("RegionalRateBoxA"),
  regionalRateBoxB("RegionalRateBoxB"),
  regionalRateBoxC("RegionalRateBoxC"),
  largeFlatRateBoardGameBox("LargeFlatRateBoardGameBox");

  private final String code;

  UspsPredefinedPackages(String code) {
    this.code = code;
  }

  @Override
  public String getCode() {
    return code;
  }
}
