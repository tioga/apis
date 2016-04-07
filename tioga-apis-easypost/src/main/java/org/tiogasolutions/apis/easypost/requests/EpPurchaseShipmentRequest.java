package org.tiogasolutions.apis.easypost.requests;

import org.tiogasolutions.apis.easypost.pub.EpRate;

import javax.ws.rs.core.Form;

public class EpPurchaseShipmentRequest {

  private final String rateId;
  private final String shipmentId;

  public EpPurchaseShipmentRequest(String rateId, String shipmentId) {
    this.rateId = rateId;
    this.shipmentId = shipmentId;
  }

  public EpPurchaseShipmentRequest(EpRate rate) {
    this.rateId = rate.getId();
    this.shipmentId = rate.getShipmentId();
  }

  public String getRateId() {
    return rateId;
  }

  public String getShipmentId() {
    return shipmentId;
  }

  public Form toForm() {
    Form form = new Form();
    form.param("rate[id]", rateId);
    return form;
  }
}
