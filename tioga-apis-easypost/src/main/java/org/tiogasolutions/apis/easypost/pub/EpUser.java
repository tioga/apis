package org.tiogasolutions.apis.easypost.pub;

import java.util.List;

public class EpUser {
    public String id;
    String name;
    String email;
    String phoneNumber;
    String balance;
    Number rechargeAmount;
    Number secondaryRechargeAmount;
    Number rechargeThreshold;
    List<EpUser> children;
}
