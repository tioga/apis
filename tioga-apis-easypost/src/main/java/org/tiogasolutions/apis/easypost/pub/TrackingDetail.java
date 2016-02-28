package org.tiogasolutions.apis.easypost.pub;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

@JsonIgnoreProperties({"object"})
public class TrackingDetail {

	private final String status;
	private final String message;
	private final Date datetime;
	private final TrackingLocation trackingLocation;
	private final String source;

	public TrackingDetail(@JsonProperty("status") String status,
												@JsonProperty("message") String message,
												@JsonProperty("datetime") Date datetime,
												@JsonProperty("tracking_location") TrackingLocation trackingLocation,
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

	public TrackingLocation getTrackingLocation() {
		return trackingLocation;
	}
}
