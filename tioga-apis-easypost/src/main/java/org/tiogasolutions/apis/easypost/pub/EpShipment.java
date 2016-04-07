package org.tiogasolutions.apis.easypost.pub;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.Map;

@SuppressWarnings("unused")
@JsonIgnoreProperties({"object", "batch_id", "fees"})
public class EpShipment {

	private final String id;
	private final EpSystemMode mode;
	private final ZonedDateTime createdAt;
	private final ZonedDateTime updatedAt;
	private final Map<String,String> verifications;

	private final String reference;
	private final Boolean isReturn;
	private final EpAddress toAddress;
	private final EpAddress buyerAddress;
	private final EpAddress fromAddress;
	private final EpAddress returnAddress;
	private final EpParcel parcel;
	private final EpCustomsInfo customsInfo;
	private final EpRate selectedRate;
	private final List<EpRate> rates;
	private final EpPostageLabel postageLabel;
	private final EpScanForm scanForm;
	private final List<EpForm> forms;
	private final EpTracker tracker;
	private final String insurance;
	private final String trackingCode;
	private final String status;
	private final String refundStatus;
	private final String batchStatus;
	private final String batchMessage;
	private final String uspsZone;
	private final Map<String, String> options;
	private final List<EpShipmentMessage> messages;
  private final String batchId;
  private final List<EpFee> fees;

	public EpShipment(@JsonProperty("id") String id,
					  @JsonProperty("mode") EpSystemMode mode,
					  @JsonProperty("created_at") ZonedDateTime createdAt,
					  @JsonProperty("updated_at") ZonedDateTime updatedAt,
					  @JsonProperty("verifications") Map<String, String> verifications,
					  @JsonProperty("reference") String reference,
					  @JsonProperty("is_return") Boolean isReturn,
					  @JsonProperty("to_address") EpAddress toAddress,
					  @JsonProperty("buyer_address") EpAddress buyerAddress,
					  @JsonProperty("from_address") EpAddress fromAddress,
					  @JsonProperty("return_address") EpAddress returnAddress,
					  @JsonProperty("parcel") EpParcel parcel,
					  @JsonProperty("customs_info") EpCustomsInfo customsInfo,
					  @JsonProperty("selected_rate") EpRate selectedRate,
					  @JsonProperty("rates") List<EpRate> rates,
					  @JsonProperty("postage_label") EpPostageLabel postageLabel,
					  @JsonProperty("scan_form") EpScanForm scanForm,
					  @JsonProperty("forms") List<EpForm> forms,
					  @JsonProperty("tracker") EpTracker tracker,
					  @JsonProperty("insurance") String insurance,
					  @JsonProperty("tracking_code") String trackingCode,
					  @JsonProperty("status") String status,
					  @JsonProperty("refund_status") String refundStatus,
					  @JsonProperty("batch_status") String batchStatus,
					  @JsonProperty("batch_message") String batchMessage,
					  @JsonProperty("usps_zone") String uspsZone,
					  @JsonProperty("options") Map<String, String> options,
					  @JsonProperty("messages") List<EpShipmentMessage> messages,
					  @JsonProperty("batch_id") String batchId,
					  @JsonProperty("fees") List<EpFee> fees) {

		this.id = id;
		this.mode = mode;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
		this.verifications = verifications;
		this.reference = reference;
		this.isReturn = isReturn;
		this.toAddress = toAddress;
		this.buyerAddress = buyerAddress;
		this.fromAddress = fromAddress;
		this.returnAddress = returnAddress;
		this.parcel = parcel;
		this.customsInfo = customsInfo;
		this.selectedRate = selectedRate;
		this.rates = rates;
		this.postageLabel = postageLabel;
		this.scanForm = scanForm;
		this.forms = forms;
		this.tracker = tracker;
		this.insurance = insurance;
		this.trackingCode = trackingCode;
		this.status = status;
		this.refundStatus = refundStatus;
		this.batchStatus = batchStatus;
		this.batchMessage = batchMessage;
		this.uspsZone = uspsZone;
		this.options = options;
		this.messages = messages;
    this.batchId = batchId;
    this.fees = fees;
	}

	public String getId() {
		return id;
	}

	public EpSystemMode getMode() {
		return mode;
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

	public String getReference() {
		return reference;
	}

	public Boolean getReturn() {
		return isReturn;
	}

	public EpAddress getToAddress() {
		return toAddress;
	}

	public EpAddress getBuyerAddress() {
		return buyerAddress;
	}

	public EpAddress getFromAddress() {
		return fromAddress;
	}

	public EpAddress getReturnAddress() {
		return returnAddress;
	}

	public EpParcel getParcel() {
		return parcel;
	}

	public EpCustomsInfo getCustomsInfo() {
		return customsInfo;
	}

	public EpRate getSelectedRate() {
		return selectedRate;
	}

	public List<EpRate> getRates() {
		return rates;
	}

	public EpPostageLabel getPostageLabel() {
		return postageLabel;
	}

	public EpScanForm getScanForm() {
		return scanForm;
	}

	public List<EpForm> getForms() {
		return forms;
	}

	public EpTracker getTracker() {
		return tracker;
	}

	public String getInsurance() {
		return insurance;
	}

	public String getTrackingCode() {
		return trackingCode;
	}

	public String getStatus() {
		return status;
	}

	public String getRefundStatus() {
		return refundStatus;
	}

	public String getBatchStatus() {
		return batchStatus;
	}

	public String getBatchMessage() {
		return batchMessage;
	}

	public String getUspsZone() {
		return uspsZone;
	}

	public Map<String, String> getOptions() {
		return options;
	}

	public List<EpShipmentMessage> getMessages() {
		return messages;
	}

  public String getBatchId() {
    return batchId;
  }

  public List<EpFee> getFees() {
    return fees;
  }
}
