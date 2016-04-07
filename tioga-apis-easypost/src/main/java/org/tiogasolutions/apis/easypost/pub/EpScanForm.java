package org.tiogasolutions.apis.easypost.pub;

import java.util.List;

public class EpScanForm {
	public String id;
  String status;
	EpAddress fromAddress;
	List<String> trackingCodes;

	String formUrl;
}
