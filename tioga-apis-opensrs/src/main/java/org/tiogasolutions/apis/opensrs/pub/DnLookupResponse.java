package org.tiogasolutions.apis.opensrs.pub;

public class DnLookupResponse {

    private final String domainName;
    private final DnStatus status;
    private final int responseCode;

    public DnLookupResponse(String domainName, int responseCode, DnStatus status) {
        this.status = status;
        this.domainName = domainName;
        this.responseCode = responseCode;
    }

    public int getResponseCode() {
        return responseCode;
    }

    public String getDomainName() {
        return domainName;
    }

    public DnStatus getStatus() {
        return status;
    }

    public static DnLookupResponse fromXmlResponse(String domainName, String xmlResponse) {
        String status = getTag(xmlResponse, "<item key=\"status\">", "</item>");
        DnStatus dnStatus = (status == null) ? null : DnStatus.valueOf(status);

        String responseCode = getTag(xmlResponse, "<item key=\"response_code\">", "</item>");
        if (responseCode == null) responseCode = "-1";

        return new DnLookupResponse(domainName, Integer.valueOf(responseCode), dnStatus);
    }

    private static String getTag(String xmlResponse, String start, String end) {
        int posA = xmlResponse.indexOf(start);
        if (posA < 0) return null;
        posA += start.length();

        int posB = xmlResponse.indexOf(end, posA);
        if (posB < 0) {
            String msg = String.format("The key %s was not found in the XML Response\n\n%s", end, xmlResponse);
            throw new RuntimeException(msg);
        }

        return xmlResponse.substring(posA, posB);
    }
}
