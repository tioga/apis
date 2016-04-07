package org.tiogasolutions.apis.easypost.pub;

import java.util.List;
import java.util.Date;

public class EpPickup {
    public String id;
    String mode;
    String status;
    String reference;
    Date minDatetime;
    Date maxDatetime;
    Boolean isAccountAddress;
    String instructions;
    List<EpShipmentMessage> messages;
    String confirmation;
    EpAddress address;
    List<EpCarrierAccount> carrierAccounts;
    List<EpPickupRate> pickupRates;
}
