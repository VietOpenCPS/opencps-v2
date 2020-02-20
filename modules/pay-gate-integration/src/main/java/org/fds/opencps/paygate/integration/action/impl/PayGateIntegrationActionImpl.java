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
import org.fds.opencps.paygate.integration.util.PayGateTerm;
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
		List<ServerConfig> serverConfigs = ServerConfigLocalServiceUtil.getByProtocol(PayGateTerm.VTP_PROTOCOL);

		if (paymentFile != null && serverConfigs != null && !serverConfigs.isEmpty()) {
			ServerConfig serverConfig = serverConfigs.get(0);
			try {
				JSONObject schema = JSONFactoryUtil.createJSONObject(serverConfig.getConfigs());
				JSONObject data = JSONFactoryUtil.createJSONObject();
				data.put(schema.getJSONObject(PayGateTerm.PRIORITY).getString(PayGateTerm.KEY),
						schema.getJSONObject(PayGateTerm.PRIORITY).getString(PayGateTerm.VALUE));
				data.put(schema.getJSONObject(PayGateTerm.VERSION).getString(PayGateTerm.KEY),
						schema.getJSONObject(PayGateTerm.VERSION).getString(PayGateTerm.VALUE));
				data.put(schema.getJSONObject(PayGateTerm.TYPE).getString(PayGateTerm.KEY),
						schema.getJSONObject(PayGateTerm.TYPE).getString(PayGateTerm.VALUE));
				data.put(schema.getJSONObject(PayGateTerm.BILLCODE).getString(PayGateTerm.KEY), paymentFile.getInvoiceNo());
				data.put(schema.getJSONObject(PayGateTerm.ORDER_ID).getString(PayGateTerm.KEY), paymentFile.getPaymentFileId());
				data.put(schema.getJSONObject(PayGateTerm.AMOUNT).getString(PayGateTerm.KEY), paymentFile.getPaymentAmount());
				data.put(schema.getJSONObject(PayGateTerm.MERCHANT_CODE).getString(PayGateTerm.KEY),
						schema.getJSONObject(PayGateTerm.MERCHANT_CODE).getString(PayGateTerm.VALUE));

				QRCodeWriter qrCodeWriter = new QRCodeWriter();

				BitMatrix matrix = qrCodeWriter.encode(data.toJSONString(), BarcodeFormat.QR_CODE, 200, 200);

				file = FileUtil.createTempFile(PayGateTerm.DOT_PNG);

				OutputStream os = new FileOutputStream(file);

				MatrixToImageWriter.writeToStream(matrix, PayGateTerm.PNG_UPPER, os);

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
	
		List<ServerConfig> serverConfigs = ServerConfigLocalServiceUtil.getByProtocol(PayGateTerm.VTP_PROTOCOL);

		if (serverConfigs == null || serverConfigs.isEmpty()) {
			return confirmResponseData(billcode, order_id, merchant_code, check_sum, 0, PayGateTerm.ERROR_CODE_03);
		}

		ServerConfig serverConfig = serverConfigs.get(0);

		JSONObject config = null;

		try {
			config = JSONFactoryUtil.createJSONObject(serverConfig.getConfigs());
		} catch (JSONException e) {
			_log.error(e);
			return confirmResponseData(billcode, order_id, merchant_code, check_sum, 0, PayGateTerm.ERROR_CODE_03);
		}

		if (config.length() == 0) {
			return confirmResponseData(billcode, order_id, merchant_code, check_sum, 0, PayGateTerm.ERROR_CODE_03);
		}

		String conf_merchant_code = config.getJSONObject(PayGateTerm.MERCHANT_CODE) != null
				? config.getJSONObject(PayGateTerm.MERCHANT_CODE).getString(PayGateTerm.VALUE)
				: StringPool.BLANK;

		String access_code = config.getString(PayGateTerm.ACCESS_CODE);

		String hash_key = config.getString(PayGateTerm.HASH_KEY);

		if (Validator.isNull(access_code)) {
			return confirmResponseData(billcode, order_id, merchant_code, check_sum, 0, PayGateTerm.ERROR_CODE_03);
		}

		if (Validator.isNull(hash_key)) {
			return confirmResponseData(billcode, order_id, merchant_code, check_sum, 0, PayGateTerm.ERROR_CODE_03);
		}

		if (!conf_merchant_code.equals(merchant_code)) {
			return confirmResponseData(billcode, order_id, merchant_code, check_sum, 0, PayGateTerm.ERROR_CODE_01);
		}

		PaymentFile paymentFile = PaymentFileLocalServiceUtil.fetchPaymentFile(GetterUtil.getLong(order_id));

		if (paymentFile == null) {
			return confirmResponseData(billcode, order_id, merchant_code, check_sum, 0, PayGateTerm.ERROR_CODE_01);
		}

		if (!paymentFile.getInvoiceNo().equals(billcode)) {
			return confirmResponseData(billcode, order_id, merchant_code, check_sum, paymentFile.getPaymentAmount(),
					"01");
		}

		String _tmp_check_sum = access_code + paymentFile.getInvoiceNo() + merchant_code + order_id
				+ paymentFile.getPaymentAmount();

		try {
			SecretKeySpec signingKey = new SecretKeySpec(hash_key.getBytes(), PayGateTerm.HMAC_SHA1);
			Mac mac = Mac.getInstance(PayGateTerm.HMAC_SHA1);
			mac.init(signingKey);
			String _tmp_check_sum_encode = toHexString(mac.doFinal(_tmp_check_sum.getBytes(StandardCharsets.UTF_8)));
			if (!_tmp_check_sum_encode.equals(check_sum)) {
				return confirmResponseData(billcode, order_id, merchant_code, check_sum, paymentFile.getPaymentAmount(),
						PayGateTerm.ERROR_CODE_02);
			}
		} catch (Exception e) {
			_log.error(e);
			return confirmResponseData(billcode, order_id, merchant_code, check_sum, paymentFile.getPaymentAmount(),
					PayGateTerm.ERROR_CODE_03);
		}

		return confirmResponseData(billcode, order_id, merchant_code, check_sum, paymentFile.getPaymentAmount(), PayGateTerm.ERROR_CODE_00);
	}

	private String toHexString(byte[] bytes) {
		Formatter formatter = new Formatter();
		String result = StringPool.BLANK;
		try {
			for (byte b : bytes) {
				formatter.format(PayGateTerm.HEX_FORMAT, b);
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
		result.put(PayGateTerm.BILLCODE, billcode);
		result.put(PayGateTerm.ORDER_ID, order_id);
		result.put(PayGateTerm.MERCHANT_CODE, merchant_code);
		result.put(PayGateTerm.CHECK_SUM, check_sum);
		result.put(PayGateTerm.TRANS_AMOUNT, trans_amount);
		result.put(PayGateTerm.ERROR_CODE, error_code);
		return result;
	}

	@Override
	public JSONObject receiveResult(User user, ServiceContext serviceContext, String billcode, String cust_msisdn,
			String error_code, String merchant_code, String order_id, int payment_status, long trans_amount,
			String vt_transaction_id, String check_sum) {
		
		return null;
	}
}
