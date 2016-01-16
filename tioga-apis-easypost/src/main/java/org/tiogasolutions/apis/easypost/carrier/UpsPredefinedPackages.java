package org.tiogasolutions.apis.easypost.carrier;

public enum UpsPredefinedPackages implements PredefinedPackage {

  upsLetter("UPSLetter"),
  upsExpressBox("UPSExpressBox"),
  ups25kgBox("UPS25kgBox"),
  ups10kgBox("UPS10kgBox"),
  tube("Tube"),
  pak("Pak"),
  pallet("Pallet"),
  smallExpressBox("SmallExpressBox"),
  mediumExpressBox("MediumExpressBox"),
  largeExpressBox("LargeExpressBox");

  private final String code;

  UpsPredefinedPackages(String code) {
    this.code = code;
  }

  @Override
  public String getCode() {
    return code;
  }
}
