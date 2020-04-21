package org.opencps.dossiermgt.action.keypay.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Logger;

import org.opencps.dossiermgt.constants.KeyPayTerm;

public class KeypayHashUtil {
    private Logger log = Logger.getLogger(KeypayHashUtil.class.getName());

  public String getHash(String value, String algorithm)
          throws NoSuchAlgorithmException, UnsupportedEncodingException {
      log.info("String input secure : " + value);
      final StringBuilder sbHash = new StringBuilder();
//      final MessageDigest m = MessageDigest.getInstance(KeyPayTerm.SHA_256);
      final MessageDigest m = MessageDigest.getInstance(algorithm);
      m.update(value.getBytes(KeyPayTerm.VALUE_UTF_8));

      final byte data[] = m.digest();

      for (byte element : data) {
          sbHash.append(Character.forDigit((element >> 4) & 0xf, 16));
          sbHash.append(Character.forDigit(element & 0xf, 16));
      }        
      return sbHash.toString();
  }
}
