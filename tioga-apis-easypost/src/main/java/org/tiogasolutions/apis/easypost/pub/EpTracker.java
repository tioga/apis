package org.tiogasolutions.apis.easypost.pub;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.Date;
import java.util.Map;

@JsonIgnoreProperties("object")
public class EpTracker {

	private final String id;
	private final String mode;
  private final ZonedDateTime createdAt;
  private final ZonedDateTime updatedAt;
  private final Map<String,String> verifications;

  private final String trackingCode;
	private final String status;
	private final String shipmentId;
	private final String carrier;
	private final List<EpTrackingDetail> trackingDetails;
	private final double weight;
	private final Date estDeliveryDate;
	private final String signedBy;
	private final EpCarrierDetail carrierDetail;
  private final List<EpFee> fees;

  public EpTracker(@JsonProperty("id") String id,
                   @JsonProperty("mode") String mode,
                   @JsonProperty("created_at") ZonedDateTime createdAt,
                   @JsonProperty("updated_at") ZonedDateTime updatedAt,
                   @JsonProperty("verifications") Map<String, String> verifications,
                   @JsonProperty("tracking_code") String trackingCode,
                   @JsonProperty("status") String status,
                   @JsonProperty("shipment_id") String shipmentId,
                   @JsonProperty("carrier") String carrier,
                   @JsonProperty("tracking_details") List<EpTrackingDetail> trackingDetails,
                   @JsonProperty("weight") double weight,
                   @JsonProperty("est_delivery_date") Date estDeliveryDate,
                   @JsonProperty("signed_by") String signedBy,
                   @JsonProperty("carrier_detail") EpCarrierDetail carrierDetail,
                   @JsonProperty("fees") List<EpFee> fees) {

    this.id = id;
    this.mode = mode;
    this.createdAt = createdAt;
    this.updatedAt = updatedAt;
    this.verifications = verifications;

    this.trackingCode = trackingCode;
    this.status = status;
    this.shipmentId = shipmentId;
    this.carrier = carrier;
    this.trackingDetails = trackingDetails;
    this.weight = weight;
    this.estDeliveryDate = estDeliveryDate;
    this.signedBy = signedBy;
    this.carrierDetail = carrierDetail;
    this.fees = fees;
  }

  public String getId() {
    return id;
  }

  public String getMode() {
    return mode;
  }

  public String getTrackingCode() {
    return trackingCode;
  }

  public String getStatus() {
    return status;
  }

  public String getShipmentId() {
    return shipmentId;
  }

  public String getCarrier() {
    return carrier;
  }

  public List<EpTrackingDetail> getTrackingDetails() {
    return trackingDetails;
  }

  public double getWeight() {
    return weight;
  }

  public Date getEstDeliveryDate() {
    return estDeliveryDate;
  }

  public String getSignedBy() {
    return signedBy;
  }

  public EpCarrierDetail getCarrierDetail() {
    return carrierDetail;
  }

  public ZonedDateTime getCreatedAt() {
    return createdAt;
  }

  public ZonedDateTime getUpdatedAt() {
    return updatedAt;
  }

  public Map<String, String> getVerifications() {
    return verifications;
  }

  public List<EpFee> getFees() {
    return fees;
  }
}
