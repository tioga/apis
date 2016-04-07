package org.tiogasolutions.apis.easypost;

import org.tiogasolutions.apis.easypost.requests.EpCreateAddressRequest;

public class EpRequestFactory {

  public static EpCreateAddressRequest createBusinessAddress() {
    return new EpCreateAddressRequest(
        "Jacob Parr", "Sierra Telephone",
        "40033 Sierra Way", null,
        "Oakhurst", "CA", "93644", null,
        "559-658-1234", "jacobp.work@example.com",
        false);
  }

  public static EpCreateAddressRequest createResidentialAddress() {
    return new EpCreateAddressRequest(
        "Jacob Parr", null,
        "40565 Goldside Dr", null,
        "Oakhurst", "CA", "93644", null,
        "559-658-1234", "jacobp.home@example.com",
        true);
  }
}
