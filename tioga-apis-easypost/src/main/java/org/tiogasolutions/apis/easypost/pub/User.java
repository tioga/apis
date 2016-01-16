package org.tiogasolutions.apis.easypost.pub;

import java.util.List;
import java.util.Map;
import java.util.HashMap;

public class User {
    public String id;
    String name;
    String email;
    String phoneNumber;
    String balance;
    Number rechargeAmount;
    Number secondaryRechargeAmount;
    Number rechargeThreshold;
    List<User> children;
}
