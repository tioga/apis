package org.tiogasolutions.apis.easypost.pub;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

@JsonIgnoreProperties({"object"})
public class EpTrackingDetail {

	private final String status;
	private final String message;
	private final Date datetime;
	private final EpTrackingLocation trackingLocation;
	private final String source;

	public EpTrackingDetail(@JsonProperty("status") String status,
							@JsonProperty("message") String message,
							@JsonProperty("datetime") Date datetime,
							@JsonProperty("tracking_location") EpTrackingLocation trackingLocation,
							@JsonProperty("source") String source) {

		this.status = status;
		this.message = message;
		this.datetime = datetime;
		this.trackingLocation = trackingLocation;
		this.source = source;
	}

	public String getSource() {
		return source;
	}

	public String getStatus() {
		return status;
	}

	public String getMessage() {
		return message;
	}

	public Date getDatetime() {
		return datetime;
	}

	public EpTrackingLocation getTrackingLocation() {
		return trackingLocation;
	}
}
