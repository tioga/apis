package org.tiogasolutions.apis.easypost.requests;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.tiogasolutions.apis.easypost.pub.EpFee;
import org.tiogasolutions.apis.easypost.pub.EpPostageLabel;
import org.tiogasolutions.apis.easypost.pub.EpRate;
import org.tiogasolutions.apis.easypost.pub.EpTracker;

import java.util.List;

public class EpPurchaseShippingResponse {

  private final EpPostageLabel postageLabel;
  private final String insurance;
  private final String trackingCode;
  private final List<String> forms;
  private final List<String> messages;
  private final EpRate selectedRate;
  private final List<EpFee> fees;
  private final EpTracker tracker;

  public EpPurchaseShippingResponse(@JsonProperty("postage_label") EpPostageLabel postageLabel,
                                    @JsonProperty("insurance") String insurance,
                                    @JsonProperty("tracking_code") String trackingCode,
                                    @JsonProperty("forms") List<String> forms,
                                    @JsonProperty("messages") List<String> messages,
                                    @JsonProperty("selected_rate") EpRate selectedRate,
                                    @JsonProperty("fees") List<EpFee> fees,
                                    @JsonProperty("tracker") EpTracker tracker) {

    this.postageLabel = postageLabel;
    this.insurance = insurance;
    this.trackingCode = trackingCode;
    this.forms = forms;
    this.messages = messages;
    this.selectedRate = selectedRate;
    this.fees = fees;
    this.tracker = tracker;
  }

  public EpPostageLabel getPostageLabel() {
    return postageLabel;
  }

  public String getInsurance() {
    return insurance;
  }

  public String getTrackingCode() {
    return trackingCode;
  }

  public List<String> getForms() {
    return forms;
  }

  public List<String> getMessages() {
    return messages;
  }

  public EpRate getSelectedRate() {
    return selectedRate;
  }

  public List<EpFee> getFees() {
    return fees;
  }

  public EpTracker getTracker() {
    return tracker;
  }
}
