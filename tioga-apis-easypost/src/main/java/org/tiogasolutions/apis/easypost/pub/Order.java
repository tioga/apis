package org.tiogasolutions.apis.easypost.pub;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Order {
  public String id;
  String mode;
  String reference;
  Boolean isReturn;
  Address toAddress;
  Address buyerAddress;
  Address fromAddress;
  Address returnAddress;
  CustomsInfo customsInfo;
  List<Shipment> shipments;
  List<Rate> rates;
  List<Container> containers;
  List<Item> items;
  Map<String, String> options;
  List<String> messages;
}
