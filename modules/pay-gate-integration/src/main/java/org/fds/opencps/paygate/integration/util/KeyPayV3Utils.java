/**
 * 
 */
package org.fds.opencps.paygate.integration.util;

import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Formatter;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import javax.ws.rs.HttpMethod;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;

/**
 * @author moon
 *
 */
public class KeyPayV3Utils {

	private static Log _log = LogFactoryUtil.getLog(KeyPayV3Utils.class.getName());

	public static JSONObject postAPI(String endpoint, JSONObject data) {

		HttpURLConnection conn = null;
		JSONObject result = JSONFactoryUtil.createJSONObject();
		try {
			URL url = new URL(endpoint);

			conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod(HttpMethod.POST);
			conn.setDoInput(true);
			conn.setDoOutput(true);

			conn.setRequestProperty(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON);
			conn.setRequestProperty(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON);

			conn.setInstanceFollowRedirects(true);
			HttpURLConnection.setFollowRedirects(true);
			conn.setReadTimeout(60 * 1000);

			byte[] postData = data.toJSONString().getBytes("UTF-8");
			int postDataLength = postData.length;
			conn.setRequestProperty(HttpHeaders.CONTENT_LENGTH, Integer.toString(postDataLength));
			try (DataOutputStream wr = new DataOutputStream(conn.getOutputStream())) {
				wr.write(postData);
			}

			conn.connect();

			try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader((conn.getInputStream())))) {

				String output = StringPool.BLANK;

				StringBuilder sb = new StringBuilder();

				while ((output = bufferedReader.readLine()) != null) {
					sb.append(output);
				}

				result = JSONFactoryUtil.createJSONObject(sb.toString());
				_log.info("result " + result);

			}
		} catch (Exception e) {
			_log.error(e);
		} finally {
			if (conn != null) {
				conn.disconnect();
			}
		}
		return result;
	}

	public static String toHexString(byte[] bytes) {
		Formatter formatter = new Formatter();
		String result = StringPool.BLANK;
		try {
			for (byte b : bytes) {
				formatter.format(PayGateTerm.HEX_FORMAT, b);
			}

			result = formatter.toString();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			formatter.close();
		}

		return result;
	}

	public static String encodeTransactionId(long dossierId) {

		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		return sdf.format(new Date()) + StringPool.DASH + dossierId;
	}
	public static String decodeTransactionId(String transactionId) {

		return transactionId.split(StringPool.DASH)[1];
	}
	public static String createChecksum(String message, String secret) {
		try {
			Mac sha256HMAC = Mac.getInstance("HmacSHA256");
			SecretKeySpec secretKey = new SecretKeySpec(secret.getBytes(), "HmacSHA256");
			sha256HMAC.init(secretKey);
			byte[] hash = sha256HMAC.doFinal(message.getBytes());
			return toHexString(hash);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public static String genCreateChecksum(String client_id, String command, String transaction_id,
			String CLIENT_KEY_1) {
		String chuoi_du_lieu = client_id + "|" + command + "|" + transaction_id;
		return createChecksum(chuoi_du_lieu, CLIENT_KEY_1);
	}

	public static String genCreateChecksumReceived(String client_id, String command, String transaction_id,
			String CLIENT_KEY_2) {
		String chuoi_du_lieu = client_id + "|" + command + "|" + transaction_id;
		return createChecksum(chuoi_du_lieu, CLIENT_KEY_2);
	}

	public static String genRefundChecksum(String amount, String client_id, String command, String reason,
			String transaction_id, String CLIENT_KEY_1) {
		String chuoi_du_lieu = amount + "|" + client_id + "|" + command + "|" + reason + "|" + transaction_id;
		return createChecksum(chuoi_du_lieu, CLIENT_KEY_1);
	}

	public static String genRefundChecksumReceived(String amount, String client_id, String command, String reason,
			String transaction_id, String CLIENT_KEY_2) {
		String chuoi_du_lieu = amount + "|" + client_id + "|" + command + "|" + reason + "|" + transaction_id;
		return createChecksum(chuoi_du_lieu, CLIENT_KEY_2);
	}

	public static String genDetailRefundChecksum(String client_id, String command, String transaction_refund_code,
			String CLIENT_KEY_1) {
		String chuoi_du_lieu = client_id + "|" + command + "|" + transaction_refund_code;
		return createChecksum(chuoi_du_lieu, CLIENT_KEY_1);
	}

	public static String genDetailRefundChecksumReceived(String client_id, String command,
			String transaction_refund_code, String CLIENT_KEY_2) {
		String chuoi_du_lieu = client_id + "|" + command + "|" + transaction_refund_code;
		return createChecksum(chuoi_du_lieu, CLIENT_KEY_2);
	}

	public static String genCallbackChecksumReceived(String addition_fee, String client_id, String command,
			String trans_amount, String transaction_id, String version, String CLIENT_KEY_1) {
		String chuoi_du_lieu = addition_fee + "|" + client_id + "|" + command + "|" + trans_amount + "|"
				+ transaction_id + "|" + version;
		_log.info("Chuoi du liệu " + chuoi_du_lieu);
		return createChecksum(chuoi_du_lieu, CLIENT_KEY_1);
	}
}
