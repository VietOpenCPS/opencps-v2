package org.opencps.api.controller.util;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.MessageDigest;
import java.security.spec.KeySpec;

public class PasswordEncrypt {

	/** Password encryption secret key */
	private static final String ENCRYPTION_SECRET_KEY = "DT-2020";

	/** Password encryption algorithm */
	private static final String ENCRYPTION_ALGORITHM = "AES";

	/** Secret key spec used to generate secret key */
	private static SecretKeySpec secretKeySpec;

	private static final String DIGEST_5 = "MD5";
	
	private static String asHexStr(byte buf[]) {
		StringBuffer strbuf = new StringBuffer(buf.length * 2);
		int i;

		for (i = 0; i < buf.length; i++) {
			if (((int) buf[i] & 0xff) < 0x10)
				strbuf.append("0");
			strbuf.append(Long.toString((int) buf[i] & 0xff, 16));
		}
		return strbuf.toString();
	}

	/**
	 * Encrypt a string
	 *
	 * @param message
	 * @return
	 * @throws Exception
	 */
	public static String encrypt(String message) throws Exception {

		// Convert input message to byte array
		byte[] messages = message.getBytes();

		// init mySecretKeySpec if it is not initialized
		if (secretKeySpec == null) {
			secretKeySpec = getSecretKeySpec(ENCRYPTION_SECRET_KEY);
		}
		// create cipher object
		Cipher cipher = Cipher.getInstance(secretKeySpec.getAlgorithm());
		cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec);

		// Do encryption
		byte[] encrypted = cipher.doFinal(messages);

		// Return hexString of encrypted message
		return asHexStr(encrypted);
	}

	/**
	 * Get secret key specs
	 *
	 * @param encryptKey
	 * @return
	 * @throws Exception
	 */
	private static SecretKeySpec getSecretKeySpec(String encryptKey) throws Exception {
		// 8-byte Salt - SHOULD NOT BE DISCLOSED
		// alternative approach is to have the salt passed from the
		// calling class (pass-the-salt)?
		byte[] salt = { (byte) 0xA9, (byte) 0x87, (byte) 0xC8, (byte) 0x32, (byte) 0x56, (byte) 0xA5, (byte) 0xE3,
				(byte) 0xB2 };

		// Iteration count
		int iterationCount = 1024;

		KeySpec keySpec = new PBEKeySpec(encryptKey.toCharArray(), salt, iterationCount);

		SecretKey secretKey = SecretKeyFactory.getInstance("PBEWithMD5AndDES").generateSecret(keySpec);

		MessageDigest md = MessageDigest.getInstance(DIGEST_5);
		md.update(secretKey.getEncoded());
		md.update(salt);
		for (int i = 1; i < iterationCount; i++)
			md.update(md.digest());

		byte[] keyBytes = md.digest();
		SecretKeySpec skeyspec = new SecretKeySpec(keyBytes, ENCRYPTION_ALGORITHM);

		return skeyspec;
	}

}
