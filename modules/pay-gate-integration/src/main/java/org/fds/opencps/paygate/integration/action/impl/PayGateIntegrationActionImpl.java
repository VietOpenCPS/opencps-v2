package org.fds.opencps.paygate.integration.action.impl;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.json.JSONException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.FileUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.Validator;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.util.Formatter;
import java.util.List;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import org.fds.opencps.paygate.integration.action.PayGateIntegrationAction;
import org.opencps.communication.model.ServerConfig;
import org.opencps.communication.service.ServerConfigLocalServiceUtil;
import org.opencps.dossiermgt.model.PaymentFile;
import org.opencps.dossiermgt.service.PaymentFileLocalServiceUtil;

/**
 * @author trungnt
 *
 */
public class PayGateIntegrationActionImpl implements PayGateIntegrationAction {
	public File genneralQRCode(User user, long groupId, long dossierId, ServiceContext serviceContext) {
		File file = null;
		PaymentFile paymentFile = PaymentFileLocalServiceUtil.getByDossierId(groupId, dossierId);
		List<ServerConfig> serverConfigs = ServerConfigLocalServiceUtil.getByProtocol("VTP_QRCODE");

		if (paymentFile != null && serverConfigs != null && !serverConfigs.isEmpty()) {
			ServerConfig serverConfig = serverConfigs.get(0);
			try {
				JSONObject schema = JSONFactoryUtil.createJSONObject(serverConfig.getConfigs());
				JSONObject data = JSONFactoryUtil.createJSONObject();
				data.put(schema.getJSONObject("priority").getString("key"),
						schema.getJSONObject("priority").getString("value"));
				data.put(schema.getJSONObject("version").getString("key"),
						schema.getJSONObject("version").getString("value"));
				data.put(schema.getJSONObject("type").getString("key"),
						schema.getJSONObject("type").getString("value"));
				data.put(schema.getJSONObject("billcode").getString("key"), paymentFile.getInvoiceNo());
				data.put(schema.getJSONObject("order_id").getString("key"), paymentFile.getPaymentFileId());
				data.put(schema.getJSONObject("amount").getString("key"), paymentFile.getPaymentAmount());
				data.put(schema.getJSONObject("merchant_code").getString("key"),
						schema.getJSONObject("merchant_code").getString("value"));

				QRCodeWriter qrCodeWriter = new QRCodeWriter();

				BitMatrix matrix = qrCodeWriter.encode(data.toJSONString(), BarcodeFormat.QR_CODE, 200, 200);

				file = FileUtil.createTempFile(".png");

				OutputStream os = new FileOutputStream(file);

				MatrixToImageWriter.writeToStream(matrix, "PNG", os);

				os.close();

			} catch (Exception e) {
				_log.error(e);
			}

		}
		return file;
	}

	private Log _log = LogFactoryUtil.getLog(PayGateIntegrationActionImpl.class.getName());

	@Override
	public JSONObject doConfirm(User user, ServiceContext serviceContext, String billcode, String merchant_code,
			String order_id, String check_sum) {
	
		List<ServerConfig> serverConfigs = ServerConfigLocalServiceUtil.getByProtocol("VTP_QRCODE");

		if (serverConfigs == null || serverConfigs.isEmpty()) {
			return confirmResponseData(billcode, order_id, merchant_code, check_sum, 0, "03");
		}

		ServerConfig serverConfig = serverConfigs.get(0);

		JSONObject config = null;

		try {
			config = JSONFactoryUtil.createJSONObject(serverConfig.getConfigs());
		} catch (JSONException e) {
			_log.error(e);
			return confirmResponseData(billcode, order_id, merchant_code, check_sum, 0, "03");
		}

		if (config.length() == 0) {
			return confirmResponseData(billcode, order_id, merchant_code, check_sum, 0, "03");
		}

		String conf_merchant_code = config.getJSONObject("merchant_code") != null
				? config.getJSONObject("merchant_code").getString("value")
				: StringPool.BLANK;

		String access_code = config.getString("access_code");

		String hash_key = config.getString("hash_key");

		if (Validator.isNull(access_code)) {
			return confirmResponseData(billcode, order_id, merchant_code, check_sum, 0, "03");
		}

		if (Validator.isNull(hash_key)) {
			return confirmResponseData(billcode, order_id, merchant_code, check_sum, 0, "03");
		}

		if (!conf_merchant_code.equals(merchant_code)) {
			return confirmResponseData(billcode, order_id, merchant_code, check_sum, 0, "01");
		}

		PaymentFile paymentFile = PaymentFileLocalServiceUtil.fetchPaymentFile(GetterUtil.getLong(order_id));

		if (paymentFile == null) {
			return confirmResponseData(billcode, order_id, merchant_code, check_sum, 0, "01");
		}

		if (!paymentFile.getInvoiceNo().equals(billcode)) {
			return confirmResponseData(billcode, order_id, merchant_code, check_sum, paymentFile.getPaymentAmount(),
					"01");
		}

		String _tmp_check_sum = access_code + paymentFile.getInvoiceNo() + merchant_code + order_id
				+ paymentFile.getPaymentAmount();

		try {
			SecretKeySpec signingKey = new SecretKeySpec(hash_key.getBytes(), "HmacSHA1");
			Mac mac = Mac.getInstance("HmacSHA1");
			mac.init(signingKey);
			String _tmp_check_sum_encode = toHexString(mac.doFinal(_tmp_check_sum.getBytes(StandardCharsets.UTF_8)));
			if (!_tmp_check_sum_encode.equals(check_sum)) {
				return confirmResponseData(billcode, order_id, merchant_code, check_sum, paymentFile.getPaymentAmount(),
						"02");
			}
		} catch (Exception e) {
			_log.error(e);
			return confirmResponseData(billcode, order_id, merchant_code, check_sum, paymentFile.getPaymentAmount(),
					"03");
		}

		return confirmResponseData(billcode, order_id, merchant_code, check_sum, paymentFile.getPaymentAmount(), "00");
	}

	private String toHexString(byte[] bytes) {
		Formatter formatter = new Formatter();
		String result = StringPool.BLANK;
		try {
			for (byte b : bytes) {
				formatter.format("%02x", b);
			}

			result = formatter.toString();

		} catch (Exception e) {
			_log.error(e);
		} finally {
			formatter.close();
		}

		return result;
	}

	private JSONObject confirmResponseData(String billcode, String order_id, String merchant_code, String check_sum,
			long trans_amount, String error_code) {
		JSONObject result = JSONFactoryUtil.createJSONObject();
		result.put("billcode", billcode);
		result.put("order_id", order_id);
		result.put("merchant_code", merchant_code);
		result.put("check_sum", check_sum);
		result.put("trans_amount", trans_amount);
		result.put("error_code", error_code);
		return result;
	}
}
