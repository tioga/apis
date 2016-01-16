package org.tiogasolutions.apis.easypost.pub;

import java.util.List;

public class Batch {
	public String id;
	String mode;
	String state;
	public BatchStatus status;
  Number numShipments;
	List<Shipment> shipments;
	String labelUrl;
	ScanForm scanForm;
  String reference;
}
