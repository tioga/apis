package org.tiogasolutions.apis.easypost.pub;

import java.util.List;
import java.util.Map;

public class EpOrder {
  public String id;
  String mode;
  String reference;
  Boolean isReturn;
  EpAddress toAddress;
  EpAddress buyerAddress;
  EpAddress fromAddress;
  EpAddress returnAddress;
  EpCustomsInfo customsInfo;
  List<EpShipment> shipments;
  List<EpRate> rates;
  List<EpContainer> containers;
  List<EpItem> items;
  Map<String, String> options;
  List<String> messages;
}
