package org.tiogasolutions.apis.easypost;

import org.tiogasolutions.apis.easypost.requests.CreateAddressRequest;

public class RequestFactory {

  public static CreateAddressRequest createBusinessAddress() {
    return new CreateAddressRequest(
        "Jacob Parr", "Sierra Telephone",
        "40033 Sierra Way", null,
        "Oakhurst", "CA", "93644", null,
        "559-658-1234", "jacobp.work@example.com",
        false);
  }

  public static CreateAddressRequest createResidentialAddress() {
    return new CreateAddressRequest(
        "Jacob Parr", null,
        "40565 Goldside Dr", null,
        "Oakhurst", "CA", "93644", null,
        "559-658-1234", "jacobp.home@example.com",
        true);
  }
}
