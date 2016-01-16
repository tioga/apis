package org.tiogasolutions.apis.easypost.pub;

import java.util.List;
import java.util.HashMap;
import java.util.Map;
import java.util.Date;

public class Tracker {
	public String id;
	String mode;
	String trackingCode;
	String status;
	String shipmentId;
	String carrier;
	List<TrackingDetail> trackingDetails;
	float weight;
	Date estDeliveryDate;
	String signedBy;
	CarrierDetail carrierDetail;
}
