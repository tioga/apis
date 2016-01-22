package org.tiogasolutions.apis.easypost.pub;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

public class TrackingDetail {
	private final String status;
	private final String message;
	private final Date datetime;
	private final TrackingLocation trackingLocation;

	public TrackingDetail(@JsonProperty("status") String status,
												@JsonProperty("message") String message,
												@JsonProperty("datetime") Date datetime,
												@JsonProperty("trackingLocation") TrackingLocation trackingLocation) {

		this.status = status;
		this.message = message;
		this.datetime = datetime;
		this.trackingLocation = trackingLocation;
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
