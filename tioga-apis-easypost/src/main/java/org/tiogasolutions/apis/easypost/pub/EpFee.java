package org.tiogasolutions.apis.easypost.pub;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;

@JsonIgnoreProperties("object")
public class EpFee {

  private final EpFeeType type;
  private final BigDecimal amount;
  private final boolean charged;
  private final boolean refunded;

  public EpFee(@JsonProperty("type") EpFeeType type,
               @JsonProperty("amount") BigDecimal amount,
               @JsonProperty("charged") boolean charged,
               @JsonProperty("refunded") boolean refunded) {

    this.type = type;
    this.amount = amount;
    this.charged = charged;
    this.refunded = refunded;
  }

  public EpFeeType getType() {
    return type;
  }

  public BigDecimal getAmount() {
    return amount;
  }

  public boolean isCharged() {
    return charged;
  }

  public boolean isRefunded() {
    return refunded;
  }
}
