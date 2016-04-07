package org.tiogasolutions.apis.easypost.pub;

import java.util.List;

public class EpCustomsInfo {
	public String id;
	String contentsType;
	String contentsExplanation;
	boolean customsCertify;
	String customsSigner;
	String nonDeliveryOption;
	String restrictionType;
	String restrictionComments;
	List<EpCustomsItem> customsItems;
}
