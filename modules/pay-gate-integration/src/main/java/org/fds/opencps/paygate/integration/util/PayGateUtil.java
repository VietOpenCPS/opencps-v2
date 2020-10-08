package org.fds.opencps.paygate.integration.util;

import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Date;
import java.util.Formatter;
import java.util.UUID;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

/**
 * @author trungnt
 *
 */
public class PayGateUtil {
	
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

	public static String generateChecksum(String client_id, String command, String transaction_id, String haskey) {
		String checksum = client_id + "|" + command + "|" + transaction_id;
		return hmacSHA256Hex(haskey, checksum);

	}

	public static String generateChecksum(String addition_fee, String client_id, String trans_amount, String command,
			String transaction_id, String version, String haskey) {
		String checksum = addition_fee + "|" + client_id + "|" + command + "|" + trans_amount + "|" + transaction_id
				+ "|" + version;
		return hmacSHA256Hex(haskey, checksum);

	}

	public static String generateGetBillChecksum(String loaiBanTin, String phienBan, String maDoiTac, String maThamChieu,
			String thoiGianGD, String hash_key) {
		String checksum = loaiBanTin + "|" + phienBan + "|" + maDoiTac + "|" + maThamChieu + "|" + thoiGianGD
				+ "|" + hash_key;
		return sha256(checksum);

	}

	private static String bytesToHex(byte[] hash) {
	    StringBuffer hexString = new StringBuffer();
	    for (int i = 0; i < hash.length; i++) {
	    String hex = Integer.toHexString(0xff & hash[i]);
	    if(hex.length() == 1) hexString.append('0');
	        hexString.append(hex);
	    }
	    return hexString.toString();
	}
	public static String sha256 (String originalString) {
		MessageDigest digest = null;
		try {
			digest = MessageDigest.getInstance("SHA-256");
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		final byte[] hashbytes = digest.digest(
		  originalString.getBytes(StandardCharsets.UTF_8));
		String sha3_256hex = bytesToHex(hashbytes);
		System.out.println("======originalString=====" + originalString);
		System.out.println("======originalString=====" + sha3_256hex);
		return sha3_256hex;
	}
	public static String hmacSHA256Hex(String key, String value) {
		String encryptValue = StringPool.BLANK;
		try {

			Mac sha256_HMAC = Mac.getInstance("HmacSHA256");
			SecretKeySpec secret_key = new SecretKeySpec(key.getBytes(), "HmacSHA256");
			sha256_HMAC.init(secret_key);

			encryptValue = toHexString(sha256_HMAC.doFinal(value.getBytes()));

		} catch (Exception e) {
			_log.error(e);
		}

		return encryptValue;
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

	public static JSONObject createResponseToPaygov(String errorCode, String errorMessage) {
		JSONObject result = JSONFactoryUtil.createJSONObject();
		result.put("error_code", errorCode);
		result.put("error_message", errorMessage);
		return result;
	}

	public static String decodeTransactionId (long paymentFileId) {
		return (paymentFileId + StringPool.UNDERLINE + UUID.randomUUID().toString()).substring(0, 20);
	}

	public static long getPaymentFileIdByTrans (String transactionId) {
		if (null == transactionId) {
			return 0l;
		} else {
			return Long.parseLong(transactionId.split(StringPool.UNDERLINE)[0]);
		}
	}

	public static String generateChecksum(String loaiBantin, String phienBan, String maDoitac, String maThamchieu,
			String sotien, String loaiHinhthanhtoan, String maKenhthanhtoan, String maThietbi, String ngonNgu, 
			String maTiente, String maNganhang, String thongtinGD, String thoigianGD, String ip, String haskey) {
		
		String checksum = loaiBantin + "|" + phienBan + "|" + maDoitac + "|" + maThamchieu + "|" + sotien
				+ "|" + loaiHinhthanhtoan + "|" + maKenhthanhtoan + "|" + maThietbi + "|" + ngonNgu 
				+ "|" + maTiente + "|" + maNganhang + "|" + thongtinGD + "|" + thoigianGD + "|" + ip + "|" + haskey;
		return sha256(checksum);

	}
	
	public static JSONObject createResponseMessage(String errCode, String errMsg, String partnerCode, 
			String refferenceCode, String transactionTime, String verificationCode) {
		JSONObject result = JSONFactoryUtil.createJSONObject();
		result.put("MaLoi", errCode);
		result.put("MoTaLoi", errMsg);
		result.put("MaDoiTac", partnerCode);
		result.put("MaThamChieu", refferenceCode);
		result.put("ThoiGianGD", transactionTime);
		result.put("MaXacThuc", verificationCode);
		return result;

	}
	
	public static String generateChecksum(String loaiBantin, String maLoi, String maDoitac, String maThamchieu,
			int sotien, String maTiente, String maGD, String maNganhang, String thongtinGD, String thoigianGD, String haskey) {
		
		String checksum = loaiBantin + "|" + maLoi + "|"  + maDoitac + "|" + maThamchieu + "|" + sotien
				+ "|" + maTiente + "|" + maGD + "|" + maNganhang + "|" + thoigianGD + "|" + thongtinGD + "|" + haskey;
		return sha256(checksum);
	}
	
	public static String generateChecksum(String loaiBantin, String phienBan, String maDoitac,
			String maThamchieu, String thoigianGD, String hashkey) {
		
		String checksum = loaiBantin + "|" +  phienBan + "|" + maDoitac + "|" + maThamchieu + "|" + thoigianGD + "|" + hashkey;
		return sha256(checksum);
	}
	
	public static String convertDate(Date date, String dateTemplate) {
		Format f = new SimpleDateFormat(dateTemplate);
		String convertDate = StringPool.BLANK;
		try {
			 convertDate = f.format(date);
		} catch (Exception e) {
			e.printStackTrace();
		}		
		return convertDate;
	}
	
	public static String hmacVTP(String data, String key) throws java.security.SignatureException {//key do viettel cung cap
		String result = StringPool.BLANK;
		String HMAC_SHA1_ALGORITHM = "HmacSHA1";
		try {
			SecretKeySpec signingKey = new SecretKeySpec(key.getBytes(), HMAC_SHA1_ALGORITHM);
			Mac mac = Mac.getInstance(HMAC_SHA1_ALGORITHM);
			mac.init(signingKey);
			byte[] rawHmac = mac.doFinal(data.getBytes());
			result = new String(Base64.getEncoder().encodeToString((rawHmac)));
		} catch (Exception e) {
			_log.debug(e);
		}
		return result;
	}
	
	public static final String createOrderIdFull (long dossierId, String dossierNo) {
		return dossierId + "___" + dossierNo;
	}
	private static Log _log = LogFactoryUtil.getLog(PayGateUtil.class);
}
