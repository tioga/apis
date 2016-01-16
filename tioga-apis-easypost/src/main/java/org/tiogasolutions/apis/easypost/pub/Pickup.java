package org.tiogasolutions.apis.easypost.pub;

import java.util.HashMap;
import java.util.List;
import java.util.Date;
import java.util.Map;

public class Pickup {
    public String id;
    String mode;
    String status;
    String reference;
    Date minDatetime;
    Date maxDatetime;
    Boolean isAccountAddress;
    String instructions;
    List<ShipmentMessage> messages;
    String confirmation;
    Address address;
    List<CarrierAccount> carrierAccounts;
    List<PickupRate> pickupRates;
}
