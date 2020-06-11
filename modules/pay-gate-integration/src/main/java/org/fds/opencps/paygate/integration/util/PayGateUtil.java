package org.fds.opencps.paygate.integration.util;

import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

import java.util.Base64;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

/**
 * @author trungnt
 *
 */
public class PayGateUtil {

	public static String generateChecksum(String client_id, String command, String transaction_id, String haskey) {
		String checksum = client_id + "|" + command + "|" + transaction_id;
		return hmacSHA256Encode(checksum, haskey);

	}

	public static String generateChecksum(String addition_fee, String client_id, String trans_amount, String command,
			String transaction_id, String version, String haskey) {
		String checksum = addition_fee + "|" + client_id + "|" + command + "|" + trans_amount + "|" + transaction_id
				+ "|" + version;
		return hmacSHA256Encode(checksum, haskey);

	}

	public static String hmacSHA256Encode(String key, String value) {
		String encryptValue = StringPool.BLANK;
		try {

			Mac sha256_HMAC = Mac.getInstance("HmacSHA256");
			SecretKeySpec secret_key = new SecretKeySpec(key.getBytes(), "HmacSHA256");
			sha256_HMAC.init(secret_key);

			encryptValue = Base64.getEncoder().encodeToString(sha256_HMAC.doFinal(value.getBytes()));

		} catch (Exception e) {
			_log.error(e);
		}

		return encryptValue;
	}

	public static JSONObject createResponseMessage(int return_code, String return_msg) {
		JSONObject result = JSONFactoryUtil.createJSONObject();
		result.put("return_code", return_code);
		result.put("return_msg", return_msg);
		return result;

	}

	private static Log _log = LogFactoryUtil.getLog(PayGateUtil.class);
}
