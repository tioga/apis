package org.tiogasolutions.apis.easypost.pub;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.Map;

@SuppressWarnings("unused")
@JsonIgnoreProperties({"object", "batch_id", "fees"})
public class Shipment {

	private final String id;
	private final SystemMode mode;
	private final ZonedDateTime createdAt;
	private final ZonedDateTime updatedAt;
	private final Map<String,String> verifications;

	private final String reference;
	private final Boolean isReturn;
	private final Address toAddress;
	private final Address buyerAddress;
	private final Address fromAddress;
	private final Address returnAddress;
	private final Parcel parcel;
	private final CustomsInfo customsInfo;
	private final Rate selectedRate;
	private final List<Rate> rates;
	private final PostageLabel postageLabel;
	private final ScanForm scanForm;
	private final List<Form> forms;
	private final Tracker tracker;
	private final String insurance;
	private final String trackingCode;
	private final String status;
	private final String refundStatus;
	private final String batchStatus;
	private final String batchMessage;
	private final String uspsZone;
	private final Map<String, String> options;
	private final List<ShipmentMessage> messages;
  private final String batchId;
  private final List<Fee> fees;

	public Shipment(@JsonProperty("id") String id,
									@JsonProperty("mode") SystemMode mode,
									@JsonProperty("created_at") ZonedDateTime createdAt,
									@JsonProperty("updated_at") ZonedDateTime updatedAt,
									@JsonProperty("verifications") Map<String, String> verifications,
									@JsonProperty("reference") String reference,
									@JsonProperty("is_return") Boolean isReturn,
									@JsonProperty("to_address") Address toAddress,
									@JsonProperty("buyer_address") Address buyerAddress,
									@JsonProperty("from_address") Address fromAddress,
									@JsonProperty("return_address") Address returnAddress,
									@JsonProperty("parcel") Parcel parcel,
									@JsonProperty("customs_info") CustomsInfo customsInfo,
									@JsonProperty("selected_rate") Rate selectedRate,
									@JsonProperty("rates") List<Rate> rates,
									@JsonProperty("postage_label") PostageLabel postageLabel,
									@JsonProperty("scan_form") ScanForm scanForm,
									@JsonProperty("forms") List<Form> forms,
									@JsonProperty("tracker") Tracker tracker,
									@JsonProperty("insurance") String insurance,
									@JsonProperty("tracking_code") String trackingCode,
									@JsonProperty("status") String status,
									@JsonProperty("refund_status") String refundStatus,
									@JsonProperty("batch_status") String batchStatus,
									@JsonProperty("batch_message") String batchMessage,
									@JsonProperty("usps_zone") String uspsZone,
									@JsonProperty("options") Map<String, String> options,
									@JsonProperty("messages") List<ShipmentMessage> messages,
                  @JsonProperty("batch_id") String batchId,
                  @JsonProperty("fees") List<Fee> fees) {

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

	public SystemMode getMode() {
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

	public Address getToAddress() {
		return toAddress;
	}

	public Address getBuyerAddress() {
		return buyerAddress;
	}

	public Address getFromAddress() {
		return fromAddress;
	}

	public Address getReturnAddress() {
		return returnAddress;
	}

	public Parcel getParcel() {
		return parcel;
	}

	public CustomsInfo getCustomsInfo() {
		return customsInfo;
	}

	public Rate getSelectedRate() {
		return selectedRate;
	}

	public List<Rate> getRates() {
		return rates;
	}

	public PostageLabel getPostageLabel() {
		return postageLabel;
	}

	public ScanForm getScanForm() {
		return scanForm;
	}

	public List<Form> getForms() {
		return forms;
	}

	public Tracker getTracker() {
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

	public List<ShipmentMessage> getMessages() {
		return messages;
	}

  public String getBatchId() {
    return batchId;
  }

  public List<Fee> getFees() {
    return fees;
  }
}
