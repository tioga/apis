package org.tiogasolutions.apis.easypost.pub;

import java.util.List;

public class EpBatch {
	public String id;
	String mode;
	String state;
	public EpBatchStatus status;
  Number numShipments;
	List<EpShipment> shipments;
	String labelUrl;
	EpScanForm scanForm;
  String reference;
}
