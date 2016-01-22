package org.tiogasolutions.apis.easypost.pub;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.ZonedDateTime;
import java.util.Map;


@JsonIgnoreProperties("object")
public class PostageLabel {

  private final String id;
  private final SystemMode mode;
  private final ZonedDateTime createdAt;
  private final ZonedDateTime updatedAt;
  private final Map<String,String> verifications;

  private final ZonedDateTime labelDate;

  private final String dateAdvance;
  private final String integratedForm;
  private final String labelResolution;
  private final String labelSize;
  private final String labelType;
  private final String labelUrl;
  private final String labelFileType;
  private final String labelPdfSize;
  private final String labelPdfType;
  private final String labelPdfUrl;
  private final String labelPdfFileType;
  private final String labelEpl2Size;
  private final String labelEpl2Type;
  private final String labelEpl2Url;
  private final String labelEpl2FileType;
  private final String labelZplSize;
  private final String labelZplType;
  private final String labelZplUrl;
  private final String labelZplFileType;

  public PostageLabel(
      @JsonProperty("id") String id,
      @JsonProperty("mode") SystemMode mode,
      @JsonProperty("created_at") ZonedDateTime createdAt,
      @JsonProperty("updated_at") ZonedDateTime updatedAt,
      @JsonProperty("verifications") Map<String, String> verifications,
      @JsonProperty("date_advance") String dateAdvance,
      @JsonProperty("integrated_form") String integratedForm,
      @JsonProperty("label_date") ZonedDateTime labelDate,
      @JsonProperty("label_resolution") String labelResolution,
      @JsonProperty("label_size") String labelSize,
      @JsonProperty("label_type") String labelType,
      @JsonProperty("label_url") String labelUrl,
      @JsonProperty("label_file_type") String labelFileType,
      @JsonProperty("label_pdf_size") String labelPdfSize,
      @JsonProperty("label_pdf_type") String labelPdfType,
      @JsonProperty("label_pdf_url") String labelPdfUrl,
      @JsonProperty("label_pdf_file_type") String labelPdfFileType,
      @JsonProperty("label_epl2_size") String labelEpl2Size,
      @JsonProperty("label_epl2_type") String labelEpl2Type,
      @JsonProperty("label_epl2_url") String labelEpl2Url,
      @JsonProperty("label_epl2_file_type") String labelEpl2FileType,
      @JsonProperty("label_zpl_size") String labelZplSize,
      @JsonProperty("label_zpl_type") String labelZplType,
      @JsonProperty("label_zpl_url") String labelZplUrl,
      @JsonProperty("label_zpl_file_type") String labelZplFileType) {

    this.id = id;
    this.mode = mode;
    this.createdAt = createdAt;
    this.updatedAt = updatedAt;
    this.verifications = verifications;

    this.labelDate = labelDate;
    this.dateAdvance = dateAdvance;
    this.integratedForm = integratedForm;
    this.labelResolution = labelResolution;
    this.labelSize = labelSize;
    this.labelType = labelType;
    this.labelUrl = labelUrl;
    this.labelFileType = labelFileType;
    this.labelPdfSize = labelPdfSize;
    this.labelPdfType = labelPdfType;
    this.labelPdfUrl = labelPdfUrl;
    this.labelPdfFileType = labelPdfFileType;
    this.labelEpl2Size = labelEpl2Size;
    this.labelEpl2Type = labelEpl2Type;
    this.labelEpl2Url = labelEpl2Url;
    this.labelEpl2FileType = labelEpl2FileType;
    this.labelZplSize = labelZplSize;
    this.labelZplType = labelZplType;
    this.labelZplUrl = labelZplUrl;
    this.labelZplFileType = labelZplFileType;
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

  public String getDateAdvance() {
    return dateAdvance;
  }

  public String getIntegratedForm() {
    return integratedForm;
  }

  public String getLabelResolution() {
    return labelResolution;
  }

  public String getLabelSize() {
    return labelSize;
  }

  public String getLabelType() {
    return labelType;
  }

  public String getLabelUrl() {
    return labelUrl;
  }

  public String getLabelFileType() {
    return labelFileType;
  }

  public String getLabelPdfSize() {
    return labelPdfSize;
  }

  public String getLabelPdfType() {
    return labelPdfType;
  }

  public String getLabelPdfUrl() {
    return labelPdfUrl;
  }

  public String getLabelPdfFileType() {
    return labelPdfFileType;
  }

  public String getLabelEpl2Size() {
    return labelEpl2Size;
  }

  public String getLabelEpl2Type() {
    return labelEpl2Type;
  }

  public String getLabelEpl2Url() {
    return labelEpl2Url;
  }

  public String getLabelEpl2FileType() {
    return labelEpl2FileType;
  }

  public String getLabelZplSize() {
    return labelZplSize;
  }

  public String getLabelZplType() {
    return labelZplType;
  }

  public String getLabelZplUrl() {
    return labelZplUrl;
  }

  public String getLabelZplFileType() {
    return labelZplFileType;
  }
}
