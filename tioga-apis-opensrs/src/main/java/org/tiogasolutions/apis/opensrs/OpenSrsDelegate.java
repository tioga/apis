package org.tiogasolutions.apis.opensrs;

import org.tiogasolutions.apis.opensrs.pub.DnLookupResponse;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public abstract class OpenSrsDelegate {

    public abstract DnLookupResponse lookup(String xml, String domainName, boolean noCache) throws Exception;

    protected final int port = 55443;
    protected String host = "rr-n1-tor.opensrs.net";

    protected String username;
    protected String privateKey;

    public OpenSrsDelegate() {
    }

    public OpenSrsDelegate init(String username, String privateKey) {
        this.username = username;
        this.privateKey = privateKey;

        return this;
    }

    protected String md5Sum(String str) throws NoSuchAlgorithmException {
        MessageDigest md5 = MessageDigest.getInstance("MD5");
        return String.format("%032x", new BigInteger(1, md5.digest(str.getBytes())));
    }
}
