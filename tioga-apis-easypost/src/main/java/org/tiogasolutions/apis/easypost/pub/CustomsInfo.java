package org.tiogasolutions.apis.easypost.pub;

import java.util.List;
import java.util.HashMap;
import java.util.Map;

public class CustomsInfo {
	public String id;
	String contentsType;
	String contentsExplanation;
	boolean customsCertify;
	String customsSigner;
	String nonDeliveryOption;
	String restrictionType;
	String restrictionComments;
	List<CustomsItem> customsItems;
}
