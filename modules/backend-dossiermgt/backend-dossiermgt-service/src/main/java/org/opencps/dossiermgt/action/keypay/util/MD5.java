package org.opencps.dossiermgt.action.keypay.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Logger;

public class MD5 {
    private Logger log = Logger.getLogger(MD5.class.getName());
//    private Logger logError = Logger.getLogger("ErrorLog");

    public String getMD5Hash(String value)
            throws NoSuchAlgorithmException, UnsupportedEncodingException {
        log.info("String input secure : " + value);
        final StringBuilder sbMd5Hash = new StringBuilder();
        final MessageDigest m = MessageDigest.getInstance("SHA-256");
        m.update(value.getBytes("UTF-8"));

        final byte data[] = m.digest();

        for (byte element : data) {
            sbMd5Hash.append(Character.forDigit((element >> 4) & 0xf, 16));
            sbMd5Hash.append(Character.forDigit(element & 0xf, 16));
        }        
        return sbMd5Hash.toString();
    }
}