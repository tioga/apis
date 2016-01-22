package org.tiogasolutions.apis.easypost.requests;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.tiogasolutions.apis.easypost.pub.Fee;
import org.tiogasolutions.apis.easypost.pub.PostageLabel;
import org.tiogasolutions.apis.easypost.pub.Rate;
import org.tiogasolutions.apis.easypost.pub.Tracker;

import javax.ws.rs.core.Form;
import java.util.List;

public class BuyRateResponse {

  private final PostageLabel postageLabel;
  private final String insurance;
  private final String trackingCode;
  private final List<String> forms;
  private final List<String> messages;
  private final Rate selectedRate;
  private final List<Fee> fees;
  private final Tracker tracker;

  public BuyRateResponse(@JsonProperty("postage_label") PostageLabel postageLabel,
                         @JsonProperty("insurance") String insurance,
                         @JsonProperty("tracking_code") String trackingCode,
                         @JsonProperty("forms") List<String> forms,
                         @JsonProperty("messages") List<String> messages,
                         @JsonProperty("selected_rate") Rate selectedRate,
                         @JsonProperty("fees") List<Fee> fees,
                         @JsonProperty("tracker") Tracker tracker) {

    this.postageLabel = postageLabel;
    this.insurance = insurance;
    this.trackingCode = trackingCode;
    this.forms = forms;
    this.messages = messages;
    this.selectedRate = selectedRate;
    this.fees = fees;
    this.tracker = tracker;
  }

  public PostageLabel getPostageLabel() {
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

  public Rate getSelectedRate() {
    return selectedRate;
  }

  public List<Fee> getFees() {
    return fees;
  }

  public Tracker getTracker() {
    return tracker;
  }
}
