package org.opencps.dossiermgt.action.keypay.util;

import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class HashFunction {
	private Log _log = LogFactoryUtil.getLog(HashFunction.class.getName());

	static final char[] HEX_TABLE = new char[] { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D',
			'E', 'F' };

	static final char[] HEX_TABLE_SHORT = new char[] { '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D',
			'E', 'F' };
	// HASH MD5
	public String hashAllFields(Map fields, String SECURE_SECRET) {
		// create a list and sort it
		List fieldNames = new ArrayList(fields.keySet());
		Collections.sort(fieldNames);

		// create a buffer for the md5 input and add the secure secret first
		StringBuffer buf = new StringBuffer();
		buf.append(SECURE_SECRET);

		// iterate through the list and add the remaining field values
		Iterator itr = fieldNames.iterator();

		while (itr.hasNext()) {
			String fieldName = (String) itr.next();
			String fieldValue = (String) fields.get(fieldName);
			if ((fieldValue != null) && (fieldValue.length() > 0)) {

				buf.append(fieldValue);
			}
		}

		MessageDigest md5 = null;
		byte[] ba = null;
		// create the md5 hash and UTF-8 encode it
		try {
//			md5 = MessageDigest.getInstance("SHA-256");
			md5 = MessageDigest.getInstance("MD5");
			ba = md5.digest(buf.toString().getBytes("UTF-8"));
		} catch (Exception e) {
			_log.debug(e);
			//_log.error(e);
		} // wont happen
			// return buf.toString();
		if (ba != null)
			return hex(ba);
		else
			return StringPool.BLANK;
	}
	
	// HASH SSA
//	public String hashAllFields(Map fields, String SECURE_SECRET) {
//		// create a list and sort it
//		List fieldNames = new ArrayList(fields.keySet());
//		Collections.sort(fieldNames);
//
//		// create a buffer for the md5 input and add the secure secret first
//		StringBuffer buf = new StringBuffer();
//		buf.append(SECURE_SECRET);
//
//		// iterate through the list and add the remaining field values
//		Iterator itr = fieldNames.iterator();
//
//		while (itr.hasNext()) {
//			String fieldName = (String) itr.next();
//			String fieldValue = (String) fields.get(fieldName);
//			if ((fieldValue != null) && (fieldValue.length() > 0)) {
//
//				buf.append(fieldValue);
//			}
//		}
//
//		StringBuffer sb = new StringBuffer();
//		try {
//			MessageDigest md = MessageDigest.getInstance("SHA-256");
//			byte[] ba = md.digest(buf.toString().getBytes("UTF-8"));
//
//			// convert the byte to hex format
//
//			for (int i = 0; i < ba.length; i++) {
//				sb.append(Integer.toString((ba[i] & 0xff) + 0x100, 16)
//						.substring(1));
//			}
//		} catch (Exception e) {
//			_log.error(e);
//		}
//
//		return sb.toString();
//	}

	/**
	 * Returns Hex output of byte array
	 */
	public static String hex(byte[] input) {
		// create a StringBuffer 2x the size of the hash array
		StringBuffer sb = new StringBuffer(input.length * 2);

		// retrieve the byte array data, convert it to hex
		// and add it to the StringBuffer
		for (int i = 0; i < input.length; i++) {
			sb.append(HEX_TABLE[(input[i] >> 4) & 0xf]);
			sb.append(HEX_TABLE[input[i] & 0xf]);
		}
		return sb.toString();
	}
	
	public static String hexShort(byte[] input) {
		// create a StringBuffer 2x the size of the hash array
		StringBuffer sb = new StringBuffer(3);

		// retrieve the byte array data, convert it to hex
		// and add it to the StringBuffer
		for (int i = 0; i < 3; i++) {
			sb.append(HEX_TABLE[(input[i] >> 4) & 0xf]);
			sb.append(HEX_TABLE[input[i] & 0xf]);
		}
		
		return sb.toString();
	}
}