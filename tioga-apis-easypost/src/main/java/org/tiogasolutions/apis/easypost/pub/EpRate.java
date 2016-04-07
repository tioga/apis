package org.tiogasolutions.apis.easypost.pub;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.ZonedDateTime;
import java.util.Map;

@JsonIgnoreProperties({"object", "batch_id", "fees"})
public class EpRate {

  private final String id;
  private final EpSystemMode mode;
  private final ZonedDateTime createdAt;
  private final ZonedDateTime updatedAt;
  private final Map<String,String> verifications;

  private final String carrier;
  private final String service;
  private final String serviceCode;
  private final double rate;
  private final String currency;
  private final double listRate;
  private final String listCurrency;
  private final double retailRate;
  private final String retailCurrency;
  private final int deliveryDays;
  private final String deliveryDate;
  private final boolean deliveryDateGuaranteed;
  private final int estDeliveryDays;
  private final String shipmentId;
  private final String carrierAccountId;

  public EpRate(@JsonProperty("id") String id,
                @JsonProperty("mode") EpSystemMode mode,
                @JsonProperty("created_at") ZonedDateTime createdAt,
                @JsonProperty("updated_at") ZonedDateTime updatedAt,
                @JsonProperty("verifications") Map<String, String> verifications,

                @JsonProperty("carrier") String carrier,
                @JsonProperty("service") String service,
                @JsonProperty("service_code") String serviceCode,
                @JsonProperty("rate") double rate,
                @JsonProperty("currency") String currency,
                @JsonProperty("list_rate") double listRate,
                @JsonProperty("list_currency") String listCurrency,
                @JsonProperty("retail_rate") double retailRate,
                @JsonProperty("retail_currency") String retailCurrency,
                @JsonProperty("delivery_days") int deliveryDays,
                @JsonProperty("delivery_date") String deliveryDate,
                @JsonProperty("delivery_date_guaranteed") boolean deliveryDateGuaranteed,
                @JsonProperty("est_delivery_days") int estDeliveryDays,
                @JsonProperty("shipment_id") String shipmentId,
                @JsonProperty("carrier_account_id") String carrierAccountId) {

    this.id = id;
    this.mode = mode;
    this.createdAt = createdAt;
    this.updatedAt = updatedAt;
    this.verifications = verifications;
    this.carrier = carrier;
    this.service = service;
    this.serviceCode = serviceCode;
    this.rate = rate;
    this.currency = currency;
    this.listRate = listRate;
    this.listCurrency = listCurrency;
    this.retailRate = retailRate;
    this.retailCurrency = retailCurrency;
    this.deliveryDays = deliveryDays;
    this.deliveryDate = deliveryDate;
    this.deliveryDateGuaranteed = deliveryDateGuaranteed;
    this.estDeliveryDays = estDeliveryDays;
    this.shipmentId = shipmentId;
    this.carrierAccountId = carrierAccountId;
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

  public String getCarrier() {
    return carrier;
  }

  public String getService() {
    return service;
  }

  public String getServiceCode() {
    return serviceCode;
  }

  public double getRate() {
    return rate;
  }

  public String getCurrency() {
    return currency;
  }

  public double getListRate() {
    return listRate;
  }

  public String getListCurrency() {
    return listCurrency;
  }

  public double getRetailRate() {
    return retailRate;
  }

  public String getRetailCurrency() {
    return retailCurrency;
  }

  public int getDeliveryDays() {
    return deliveryDays;
  }

  public String getDeliveryDate() {
    return deliveryDate;
  }

  public boolean isDeliveryDateGuaranteed() {
    return deliveryDateGuaranteed;
  }

  public int getEstDeliveryDays() {
    return estDeliveryDays;
  }

  public String getShipmentId() {
    return shipmentId;
  }

  public String getCarrierAccountId() {
    return carrierAccountId;
  }

  @Override
  public String toString() {
    return String.format("%s-%s", carrier, service);
  }
}
