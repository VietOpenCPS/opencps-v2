package org.fds.opencps.paygate.integration.action.impl;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.FileUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.Validator;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Formatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import javax.ws.rs.HttpMethod;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;

import org.fds.opencps.paygate.integration.action.PayGateIntegrationAction;
import org.fds.opencps.paygate.integration.util.PayGateTerm;
import org.fds.opencps.paygate.integration.util.PayGateUtil;
import org.opencps.communication.model.ServerConfig;
import org.opencps.communication.service.ServerConfigLocalServiceUtil;
import org.opencps.dossiermgt.action.PaymentFileActions;
import org.opencps.dossiermgt.action.impl.PaymentFileActionsImpl;
import org.opencps.dossiermgt.action.util.ConstantUtils;
import org.opencps.dossiermgt.action.util.OpenCPSConfigUtil;
import org.opencps.dossiermgt.constants.KeyPayTerm;
import org.opencps.dossiermgt.constants.PaymentFileTerm;
import org.opencps.dossiermgt.constants.VTPayTerm;
import org.opencps.dossiermgt.model.Dossier;
import org.opencps.dossiermgt.model.DossierAction;
import org.opencps.dossiermgt.model.PaymentConfig;
import org.opencps.dossiermgt.model.PaymentFile;
import org.opencps.dossiermgt.model.ProcessAction;
import org.opencps.dossiermgt.model.ServiceInfoMapping;
import org.opencps.dossiermgt.service.DossierActionLocalServiceUtil;
import org.opencps.dossiermgt.service.DossierLocalServiceUtil;
import org.opencps.dossiermgt.service.PaymentConfigLocalServiceUtil;
import org.opencps.dossiermgt.service.PaymentFileLocalServiceUtil;
import org.opencps.dossiermgt.service.ProcessActionLocalServiceUtil;
import org.opencps.dossiermgt.service.ServiceInfoMappingLocalServiceUtil;

/**
 * @author trungnt
 *
 */
public class PayGateIntegrationActionImpl implements PayGateIntegrationAction {

	private Log _log = LogFactoryUtil.getLog(PayGateIntegrationActionImpl.class.getName());

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

	private JSONObject receiverResponseData(String order_id, String merchant_code, String check_sum,
			String error_code) {
		JSONObject result = JSONFactoryUtil.createJSONObject();
		result.put(PayGateTerm.ERROR_CODE, error_code);
		result.put(PayGateTerm.MERCHANT_CODE, merchant_code);
		result.put(PayGateTerm.ORDER_ID, order_id);
		result.put(PayGateTerm.RETURN_URL, StringPool.BLANK);
		result.put(PayGateTerm.RETURN_BILL_CODE, StringPool.BLANK);
		result.put(PayGateTerm.RETURN_OTHER_INFO, StringPool.BLANK);
		result.put(PayGateTerm.CHECK_SUM, check_sum);
		return result;
	}

	//	private void invokeReceiveResult(User user, ServiceContext serviceContext, JSONObject searchResult, String billcode,
	//			String cust_msisdn, long trans_amount) {
	//
	//		String merchant_code = searchResult.getString(PayGateTerm.MERCHANT_CODE);
	//		String order_id = searchResult.getString(PayGateTerm.ORDER_ID);
	//		int payment_status = searchResult.getInt(PayGateTerm.PAYMENT_STATUS);
	//		String version = searchResult.getString(PayGateTerm.VERSION);
	//		String check_sum = searchResult.getString(PayGateTerm.CHECK_SUM);
	//		String error_code = searchResult.getString(PayGateTerm.ERROR_CODE);
	//		String vt_transaction_id = searchResult.getString(PayGateTerm.VT_TRANSACTION_ID);
	//		mcReceiveResult(user, serviceContext, billcode, cust_msisdn, error_code, merchant_code, order_id,
	//				payment_status, trans_amount, vt_transaction_id, check_sum);
	//	}

	@SuppressWarnings("rawtypes")
	public JSONObject callPostAPI(String httpMethod, String accept, String urlPath, HashMap<String, String> properties,
			Map<String, Object> params, String username, String password) {

		JSONObject response = JSONFactoryUtil.createJSONObject();

		HttpURLConnection conn = null;

		BufferedReader br = null;

		try {

			URL url = new URL(urlPath);

			conn = (HttpURLConnection) url.openConnection();
			conn.setConnectTimeout(OpenCPSConfigUtil.getRestConnectionTimeout());
			conn.setReadTimeout(OpenCPSConfigUtil.getRestReadTimeout());
			conn.setRequestMethod(httpMethod);
			conn.setDoInput(true);
			conn.setDoOutput(true);

			conn.setRequestProperty(ConstantUtils.VALUE_ACCEPT, accept);

			if (Validator.isNotNull(username) && Validator.isNotNull(password)) {
				String authString = username + StringPool.COLON + password;

				String authStringEnc = new String(Base64.getEncoder().encodeToString(authString.getBytes()));

				conn.setRequestProperty(ConstantUtils.VALUE_AUTHORIZATION, ConstantUtils.VALUE_BASIC + authStringEnc);
			}

			if (!properties.isEmpty()) {
				for (Map.Entry m : properties.entrySet()) {
					conn.setRequestProperty(m.getKey().toString(), m.getValue().toString());
				}
			}

			StringBuilder postData = new StringBuilder();

			for (Map.Entry<String, Object> param : params.entrySet()) {
				if (postData.length() != 0)
					postData.append(StringPool.AMPERSAND.charAt(0));
				postData.append(java.net.URLEncoder.encode(param.getKey(), ConstantUtils.UTF_8));
				postData.append(StringPool.EQUAL.charAt(0));
				postData.append(java.net.URLEncoder.encode(String.valueOf(param.getValue()), ConstantUtils.UTF_8));
			}

			byte[] postDataBytes = postData.toString().getBytes(ConstantUtils.UTF_8);

			conn.setRequestProperty(ConstantUtils.CONTENT_LENGTH, String.valueOf(postDataBytes.length));

			conn.getOutputStream().write(postDataBytes);

			br = new BufferedReader(new InputStreamReader((conn.getInputStream())));

			String output;

			StringBuilder sb = new StringBuilder();

			while ((output = br.readLine()) != null) {
				sb.append(output);
			}

			response = JSONFactoryUtil.createJSONObject(sb.toString());

			conn.disconnect();

		} catch (MalformedURLException e) {
			_log.error("Can't invoke api " + urlPath);
		} catch (IOException e) {
			_log.error("Can't invoke api " + urlPath);
		} catch (JSONException e) {
			_log.error("Can't invoke api " + urlPath);
			e.printStackTrace();
		} finally {
			if (conn != null) {
				conn.disconnect();
			}
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					_log.error(e);
				}
			}

		}

		return response;
	}

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
				data.put(schema.getJSONObject(PayGateTerm.BILLCODE).getString(PayGateTerm.KEY),
						paymentFile.getInvoiceNo());
				data.put(schema.getJSONObject(PayGateTerm.ORDER_ID).getString(PayGateTerm.KEY),
						paymentFile.getPaymentFileId());
				data.put(schema.getJSONObject(PayGateTerm.AMOUNT).getString(PayGateTerm.KEY),
						paymentFile.getPaymentAmount());
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

	@Override
	public JSONObject doConfirm(User user, ServiceContext serviceContext, String billcode, String merchant_code,
			String order_id, String check_sum) {
		_log.info("=============call to doconfirm===================");
		_log.info("=============call to doconfirm===================");
		String mcUrl = PayGateTerm.getMcUrlByBillCode(billcode) + StringPool.SLASH + PayGateTerm.ENDPOINT_CONFIRM;
		HashMap<String, String> properties = new HashMap<String, String>();
		Map<String, Object> params = new HashMap<>();

		properties.put(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_FORM_URLENCODED);
		params.put(PayGateTerm.BILLCODE, billcode);
		params.put(PayGateTerm.MERCHANT_CODE, merchant_code);
		params.put(PayGateTerm.ORDER_ID, order_id);
		params.put(PayGateTerm.CHECK_SUM, check_sum);
		return callPostAPI(HttpMethod.POST, MediaType.APPLICATION_JSON, mcUrl, properties, params, StringPool.BLANK,
				StringPool.BLANK);

	}

	@Override
	public JSONObject receiveResult(User user, ServiceContext serviceContext, String billcode, String cust_msisdn,
			String error_code, String merchant_code, String order_id, int payment_status, long trans_amount,
			String vt_transaction_id, String check_sum) {
		_log.info("=============call to rec===================");
		_log.info("=============call to receiveResult===================" + billcode);
		String mcUrl = PayGateTerm.getMcUrlByBillCode(billcode) + StringPool.SLASH + PayGateTerm.ENDPOINT_RECEIVER;
		HashMap<String, String> properties = new HashMap<String, String>();
		Map<String, Object> params = new HashMap<>();

		properties.put(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_FORM_URLENCODED);
		params.put(PayGateTerm.BILLCODE, billcode);
		params.put(PayGateTerm.CUST_MSISDN, cust_msisdn);
		params.put(PayGateTerm.ERROR_CODE, error_code);
		params.put(PayGateTerm.MERCHANT_CODE, merchant_code);
		params.put(PayGateTerm.PAYMENT_STATUS, payment_status);
		params.put(PayGateTerm.TRANS_AMOUNT, trans_amount);
		params.put(PayGateTerm.VT_TRANSACTION_ID, vt_transaction_id);
		params.put(PayGateTerm.ORDER_ID, order_id);
		params.put(PayGateTerm.CHECK_SUM, check_sum);
		return callPostAPI(HttpMethod.POST, MediaType.APPLICATION_JSON, mcUrl, properties, params, StringPool.BLANK,
				StringPool.BLANK);
	}

	@Override
	public JSONObject searchResult(User user, ServiceContext serviceContext, String order_id, String billcode,
			String cust_msisdn, long trans_amount) {
		_log.info("=============call to search===================");

		String mcUrl = PayGateTerm.getMcUrlByBillCode(billcode) + StringPool.SLASH + PayGateTerm.ENDPOINT_SEARCH;
		HashMap<String, String> properties = new HashMap<String, String>();
		Map<String, Object> params = new HashMap<>();

		params.put(PayGateTerm.BILLCODE, billcode);
		params.put(PayGateTerm.CUST_MSISDN, cust_msisdn);
		params.put(PayGateTerm.TRANS_AMOUNT, trans_amount);
		params.put(PayGateTerm.ORDER_ID, order_id);
		String q = null;
		String mcUrl2 = mcUrl;
		for (Map.Entry<String, Object> param : params.entrySet()) {
			if (q == null) {
				q = StringPool.QUESTION;
			} else {
				q = StringPool.AMPERSAND;
			}
			mcUrl2 += q + param.getKey() + StringPool.EQUAL + param.getValue();
		}
		_log.info("=========search mc=====" + mcUrl2);
		return callPostAPI(HttpMethod.POST, MediaType.APPLICATION_JSON, mcUrl, properties, params, StringPool.BLANK,
				StringPool.BLANK);
	}

	@Override
	public JSONObject mcDoConfirm(User user, ServiceContext serviceContext, String billcode, String merchant_code,
			String order_id, String check_sum) {

		try {

			_log.info("=============mcDO confirm========");
			long dossierId = VTPayTerm.getDossierIdByOrderId(order_id);
			Dossier dossier = DossierLocalServiceUtil.fetchDossier(dossierId);

			_log.debug(dossier);

			PaymentConfig paymentConfig = PaymentConfigLocalServiceUtil
					.getPaymentConfigByGovAgencyCode(dossier.getGroupId(), dossier.getGovAgencyCode());

			PaymentFile paymentFile = PaymentFileLocalServiceUtil.getByDossierId(dossier.getGroupId(),
					dossier.getDossierId());

			JSONObject config = JSONFactoryUtil.createJSONObject(paymentConfig.getEpaymentConfig())
					.getJSONObject(VTPayTerm.VTP_CONFIG);

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

			String invoiceNo = VTPayTerm.getInvoiceNoByBillCode(billcode);
			if (paymentFile.getInvoiceNo().indexOf(invoiceNo) < 0) {
				return confirmResponseData(billcode, order_id, merchant_code, check_sum, paymentFile.getPaymentAmount(),
						PayGateTerm.ERROR_CODE_01);
			}

			String _tmp_check_sum = access_code + billcode + merchant_code + order_id + paymentFile.getPaymentAmount();

			try {
				SecretKeySpec signingKey = new SecretKeySpec(hash_key.getBytes(), PayGateTerm.HMAC_SHA1);
				Mac mac = Mac.getInstance(PayGateTerm.HMAC_SHA1);
				mac.init(signingKey);
				String _tmp_check_sum_encode = toHexString(
						mac.doFinal(_tmp_check_sum.getBytes(StandardCharsets.UTF_8)));
				_log.info("checksum==========" + _tmp_check_sum_encode);
				if (!_tmp_check_sum_encode.equals(check_sum)) {
					return confirmResponseData(billcode, order_id, merchant_code, check_sum,
							paymentFile.getPaymentAmount(), PayGateTerm.ERROR_CODE_02);
				}
			} catch (Exception e) {
				_log.error(e);
				return confirmResponseData(billcode, order_id, merchant_code, check_sum, paymentFile.getPaymentAmount(),
						PayGateTerm.ERROR_CODE_03);
			}

			return confirmResponseData(billcode, order_id, merchant_code, check_sum, paymentFile.getPaymentAmount(),
					PayGateTerm.ERROR_CODE_00);
		} catch (Exception e) {
			e.printStackTrace();
			return confirmResponseData(billcode, order_id, merchant_code, check_sum, 0, PayGateTerm.ERROR_CODE_03);
		}
	}

	@Override
	public JSONObject mcReceiveResult(User user, ServiceContext serviceContext, String billcode, String cust_msisdn,
			String error_code, String merchant_code, String order_id, int payment_status, long trans_amount,
			String vt_transaction_id, String check_sum) {

		try {

			_log.info("=============call to mc rec===================" + billcode + cust_msisdn + error_code
					+ merchant_code + order_id + payment_status + trans_amount + vt_transaction_id + check_sum);

			long dossierId = VTPayTerm.getDossierIdByOrderId(order_id);
			Dossier dossier = DossierLocalServiceUtil.fetchDossier(dossierId);
			PaymentConfig paymentConfig = PaymentConfigLocalServiceUtil
					.getPaymentConfigByGovAgencyCode(dossier.getGroupId(), dossier.getGovAgencyCode());
			PaymentFile paymentFile = PaymentFileLocalServiceUtil.getByDossierId(dossier.getGroupId(),
					dossier.getDossierId());

			JSONObject config = JSONFactoryUtil.createJSONObject(paymentConfig.getEpaymentConfig())
					.getJSONObject(VTPayTerm.VTP_CONFIG);

			String access_code = config.getString(PayGateTerm.ACCESS_CODE);
			String hash_key = config.getString(PayGateTerm.HASH_KEY);
			String _tmp_check_sum = access_code + error_code + merchant_code + order_id;
			SecretKeySpec signingKey = new SecretKeySpec(hash_key.getBytes(), PayGateTerm.HMAC_SHA1);
			Mac mac = Mac.getInstance(PayGateTerm.HMAC_SHA1);
			mac.init(signingKey);
			String check_sum_encoded = toHexString(mac.doFinal(_tmp_check_sum.getBytes(StandardCharsets.UTF_8)));

			_log.info("payment_status====" + payment_status);
			_log.info("error_code====" + error_code);
			_log.info("paymentFile.getPaymentMethod()====" + paymentFile.getPaymentMethod());
			if (payment_status == 1 && PayGateTerm.ERROR_CODE_00.equals(error_code)
					&& !PaymentFileTerm.PAYMENT_METHOD_VIETTEL_PAY.equals(paymentFile.getPaymentMethod())) {

				JSONObject action = JSONFactoryUtil.createJSONObject();

				if (dossier.isOnline()) {
					// TODO: call api doaction to DVC
					action = config.getJSONObject(PayGateTerm.ACTION_IS_ONLINE);
					String endPoint = action.getString(PayGateTerm.URL) + StringPool.SLASH
							+ PayGateTerm.ENDPOINT_DVCRECEIVER;
					HashMap<String, String> properties = new HashMap<String, String>();
					properties.put(Field.GROUP_ID, action.getString(Field.GROUP_ID));

					Map<String, Object> params = new HashMap<String, Object>();
					params.put(PayGateTerm.ACTION_CODE, action.get(PayGateTerm.ACTION_CODE));
					params.put(PayGateTerm.URL, action.getString(PayGateTerm.URL));
					params.put(Field.GROUP_ID, action.getString(Field.GROUP_ID));
					params.put(PayGateTerm.ORDER_ID, order_id);
					params.put(PayGateTerm.USERNAME, action.getString(PayGateTerm.USERNAME));
					params.put(PayGateTerm.PWD, action.getString(PayGateTerm.PWD));

					JSONObject resPostDossier = callPostAPI(HttpMethod.POST, MediaType.APPLICATION_JSON, endPoint,
							properties, params, action.getString(PayGateTerm.USERNAME),
							action.getString(PayGateTerm.PWD));
					_log.info("=====resPostDossier=========" + resPostDossier);

				} else {
					// TODO: call api doaction to MC

					PaymentFileActions actions = new PaymentFileActionsImpl();

					// Change payment Status = 5
					actions.updateFileConfirm(paymentFile.getGroupId(), paymentFile.getDossierId(),
							paymentFile.getReferenceUid(), StringPool.BLANK, PaymentFileTerm.PAYMENT_METHOD_VIETTEL_PAY,
							JSONFactoryUtil.createJSONObject().toJSONString(), serviceContext);

					action = config.getJSONObject(PayGateTerm.ACTION_IS_NOT_ONLINE);
					HashMap<String, String> properties = new HashMap<String, String>();
					properties.put(Field.GROUP_ID, String.valueOf(dossier.getGroupId()));

					String endPoint = PayGateTerm.buildPathDoAction(action.getString(PayGateTerm.URL),
							dossier.getDossierId());

					Map<String, Object> params = new HashMap<String, Object>();
					params.put(PayGateTerm.ACTION_CODE, action.get(PayGateTerm.ACTION_CODE));
					JSONObject resPostDossier = callPostAPI(HttpMethod.POST, MediaType.APPLICATION_JSON, endPoint,
							properties, params, action.getString(PayGateTerm.USERNAME),
							action.getString(PayGateTerm.PWD));

					_log.info("=====resPostDossier=========" + resPostDossier);
				}
			}

			String conf_merchant_code = config.getJSONObject(PayGateTerm.MERCHANT_CODE) != null
					? config.getJSONObject(PayGateTerm.MERCHANT_CODE).getString(PayGateTerm.VALUE)
					: StringPool.BLANK;
			if (!conf_merchant_code.equals(merchant_code)) {
				return receiverResponseData(order_id, merchant_code, check_sum_encoded, PayGateTerm.ERROR_CODE_01);
			}

			String invoiceNo = VTPayTerm.getInvoiceNoByBillCode(billcode);
			if (paymentFile.getInvoiceNo().indexOf(invoiceNo) < 0) {
				return receiverResponseData(order_id, merchant_code, check_sum_encoded, PayGateTerm.ERROR_CODE_01);
			}

			return receiverResponseData(order_id, merchant_code, check_sum_encoded, PayGateTerm.ERROR_CODE_00);
		} catch (Exception e) {

			e.printStackTrace();
			return receiverResponseData(order_id, merchant_code, check_sum, PayGateTerm.ERROR_CODE_03);
		}

	}

	@Override
	public JSONObject mcSearchResult(User user, ServiceContext serviceContext, String order_id, String billcode,
			String cust_msisdn, long trans_amount) {
		_log.info(
				"=============call to MCsearch===================" + order_id + billcode + cust_msisdn + trans_amount);

		try {

			long dossierId = VTPayTerm.getDossierIdByOrderId(order_id);
			Dossier dossier = DossierLocalServiceUtil.fetchDossier(dossierId);
			PaymentConfig paymentConfig = PaymentConfigLocalServiceUtil
					.getPaymentConfigByGovAgencyCode(dossier.getGroupId(), dossier.getGovAgencyCode());

			JSONObject schema = JSONFactoryUtil.createJSONObject(paymentConfig.getEpaymentConfig())
					.getJSONObject(VTPayTerm.VTP_CONFIG);

			String cmd = schema.getJSONObject(PayGateTerm.MERCHANT_CODE).getString(PayGateTerm.VALUE);
			String merchant_code = schema.getJSONObject(PayGateTerm.MERCHANT_CODE).getString(PayGateTerm.VALUE);
			String version = schema.getJSONObject(PayGateTerm.VERSION_SEARCH).getString(PayGateTerm.VALUE);
			String access_code = schema.getString(PayGateTerm.ACCESS_CODE);
			String hash_key = schema.getString(PayGateTerm.HASH_KEY);
			String url_search = schema.getString(PayGateTerm.URL_SEARCH);

			String _tmp_check_sum = access_code + cmd + merchant_code + order_id + version;
			SecretKeySpec signingKey = new SecretKeySpec(hash_key.getBytes(), PayGateTerm.HMAC_SHA1);
			Mac mac = Mac.getInstance(PayGateTerm.HMAC_SHA1);
			mac.init(signingKey);
			String check_sum_encoded = toHexString(mac.doFinal(_tmp_check_sum.getBytes(StandardCharsets.UTF_8)));

			HashMap<String, String> properties = new HashMap<String, String>();
			Map<String, Object> params = new HashMap<>();

			properties.put(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_FORM_URLENCODED);
			params.put(schema.getJSONObject(PayGateTerm.CMD_SEARCH).getString(PayGateTerm.KEY),
					schema.getJSONObject(PayGateTerm.CMD_SEARCH).getString(PayGateTerm.VALUE));
			params.put(schema.getJSONObject(PayGateTerm.VERSION_SEARCH).getString(PayGateTerm.KEY),
					schema.getJSONObject(PayGateTerm.VERSION_SEARCH).getString(PayGateTerm.VALUE));
			params.put(PayGateTerm.MERCHANT_CODE,
					schema.getJSONObject(PayGateTerm.MERCHANT_CODE).getString(PayGateTerm.VALUE));
			params.put(PayGateTerm.ORDER_ID, order_id);
			params.put(PayGateTerm.CHECK_SUM, check_sum_encoded);

			_log.info(_tmp_check_sum);
			_log.info(params);
			JSONObject searchResult = callPostAPI(HttpMethod.POST, MediaType.APPLICATION_JSON, url_search, properties,
					params, StringPool.BLANK, StringPool.BLANK);

			_log.info("searchResult===" + searchResult);
			// auto update payment status and call next action
			// invokeReceiveResult(user, serviceContext, searchResult, billcode,
			// cust_msisdn, trans_amount);
			return searchResult;

		} catch (Exception e) {
			e.printStackTrace();
			return JSONFactoryUtil.createJSONObject();
		}
	}

	@Override
	public JSONObject dvcReceiveResult(User user, ServiceContext serviceContext, String url, long groupId,
			String actionCode, String order_id, String username, String pwd) {

		_log.info("=============call to DVC result===================" + username + "  " + pwd);

		try {

			String dossierNo = VTPayTerm.getDossierNoByOrderId(order_id);
			Dossier dossier = DossierLocalServiceUtil.getByDossierNo(groupId, dossierNo);
			_log.info(groupId + "dossierId=====" + dossier);
			//			PaymentConfig paymentConfig = PaymentConfigLocalServiceUtil
			//					.getPaymentConfigByGovAgencyCode(dossier.getGroupId(), dossier.getGovAgencyCode());
			PaymentFile paymentFile = PaymentFileLocalServiceUtil.getByDossierId(dossier.getGroupId(),
					dossier.getDossierId());

			PaymentFileActions actions = new PaymentFileActionsImpl();

			// Change payment Status = 5
			paymentFile = actions.updateFileConfirm(paymentFile.getGroupId(), paymentFile.getDossierId(),
					paymentFile.getReferenceUid(), StringPool.BLANK, PaymentFileTerm.PAYMENT_METHOD_VIETTEL_PAY,
					JSONFactoryUtil.createJSONObject().toJSONString(), serviceContext);

			HashMap<String, String> properties = new HashMap<String, String>();
			properties.put(Field.GROUP_ID, String.valueOf(groupId));

			String endPoint = PayGateTerm.buildPathDoAction(url, dossier.getDossierId());

			Map<String, Object> params = new HashMap<String, Object>();
			params.put(PayGateTerm.ACTION_CODE, actionCode);

			JSONObject payment = JSONFactoryUtil.createJSONObject();
			payment.put(PaymentFileTerm.PAYMENT_REQUEST, 3);
			payment.put(PaymentFileTerm.ADVANCE_AMOUNT, paymentFile.getAdvanceAmount());
			payment.put(PaymentFileTerm.FEE_AMOUNT, paymentFile.getFeeAmount());
			payment.put(PaymentFileTerm.PAYMENT_NOTE, paymentFile.getPaymentNote());
			payment.put(PaymentFileTerm.SERVICE_AMOUNT, paymentFile.getServiceAmount());
			payment.put(PaymentFileTerm.SHIP_AMOUNT, paymentFile.getShipAmount());
			payment.put(PaymentFileTerm.PAYMENT_METHOD, PaymentFileTerm.PAYMENT_METHOD_VIETTEL_PAY);
			params.put(PayGateTerm.PAYMENT, payment.toString());

			long dossierActionId = dossier.getDossierActionId();
			DossierAction dossierAction = DossierActionLocalServiceUtil.fetchDossierAction(dossierActionId);
			long serviceProcessId = dossierAction.getServiceProcessId();
			String stepCode = dossierAction.getStepCode();
			if (stepCode != null) {

				List<ProcessAction> processActionList = ProcessActionLocalServiceUtil
						.getProcessActionByG_SPID_PRESC(groupId, serviceProcessId, stepCode);

				for (ProcessAction processAction : processActionList) {

					_log.info(processAction.getActionCode());
					_log.info(processAction.getRequestPayment());
					if (processAction.getActionCode().equals(actionCode)) {

						payment = JSONFactoryUtil.createJSONObject();
						payment.put(PaymentFileTerm.PAYMENT_REQUEST, processAction.getRequestPayment());
						payment.put(PaymentFileTerm.ADVANCE_AMOUNT, paymentFile.getAdvanceAmount());
						payment.put(PaymentFileTerm.FEE_AMOUNT, paymentFile.getFeeAmount());
						payment.put(PaymentFileTerm.PAYMENT_NOTE, paymentFile.getPaymentNote());
						payment.put(PaymentFileTerm.SERVICE_AMOUNT, paymentFile.getServiceAmount());
						payment.put(PaymentFileTerm.PAYMENT_METHOD, PaymentFileTerm.PAYMENT_METHOD_VIETTEL_PAY);
						params.put(PayGateTerm.PAYMENT, payment.toString());
					}
				}
			}

			_log.info("params============" + params);
			JSONObject resPostDossier = callPostAPI(HttpMethod.POST, MediaType.APPLICATION_JSON, endPoint, properties,
					params, username, pwd);

			_log.info("=====resPostDossier=========" + resPostDossier);
			return resPostDossier;

		} catch (Exception e) {
			e.printStackTrace();
			return JSONFactoryUtil.createJSONObject();
		}
	}

	//Add by TrungNT
	@Override
	public String kpCreateTransaction(User user, long groupId, long dossierId, ServiceContext serviceContext) {
		String result = StringPool.BLANK;


		Dossier dossier = DossierLocalServiceUtil.fetchDossier(dossierId);
		PaymentFile paymentFile = PaymentFileLocalServiceUtil.getByDossierId(groupId, dossierId);

		if (dossier != null && paymentFile != null) {

			HttpURLConnection conn = null;
			try {

				JSONObject schema = JSONFactoryUtil.createJSONObject(paymentFile.getEpaymentProfile()).getJSONObject(KeyPayTerm.KP_DVCQG_CONFIG);
				if (schema == null) {
					return result;
				}

				//addition_fee, client_id, trans_amount, command, transaction_id, version, haskey

				String client_id = schema.getString(PayGateTerm.CLIENT_ID);
				String addition_fee = String.valueOf(paymentFile.getShipAmount());
				String trans_amount = String.valueOf(paymentFile.getPaymentAmount());
				String command = schema.getString(PayGateTerm.COMMAND);
				String version = schema.getString(PayGateTerm.VERSION);
				String hash_key_1 = schema.getString(PayGateTerm.HASH_KEY_1);

				//TODO validate

				JSONObject data = JSONFactoryUtil.createJSONObject();
				String transactionId = PayGateUtil.decodeTransactionId(paymentFile.getPaymentFileId());
				data.put(PayGateTerm.CLIENT_ID, schema.getString(PayGateTerm.CLIENT_ID));
				data.put(PayGateTerm.TRANSACTION_ID, transactionId);
				data.put(PayGateTerm.TRANS_AMOUNT, trans_amount);
				data.put(PayGateTerm.COMMAND, schema.getString(PayGateTerm.COMMAND));//default PAY
				data.put(PayGateTerm.VERSION, schema.getString(PayGateTerm.VERSION));// default "3.0"
				data.put(PayGateTerm.DESCRIPTION, paymentFile.getPaymentNote());//?
				data.put(PayGateTerm.LOCALE, schema.getString(PayGateTerm.LOCALE));//default vn
				data.put(PayGateTerm.COUNTRY_CODE, schema.getString(PayGateTerm.COUNTRY_CODE)); //default vi
				data.put(PayGateTerm.CURRENCY_CODE, schema.getString(PayGateTerm.CURRENCY_CODE)); //default VND
				data.put(PayGateTerm.ENVIRONMENT, schema.getString(PayGateTerm.ENVIRONMENT)); //default 1-web, 2-app

				JSONObject bill_info = JSONFactoryUtil.createJSONObject();
				bill_info.put(PayGateTerm.MADICHVU, schema.getString(PayGateTerm.MADICHVU)); //thu phi le phi = 2

				// moi dich vu cong co 1 thong tin ngan hang thu huong khac nhau
				JSONObject banksInfo = schema.getJSONObject(PayGateTerm.BANKINFO);
				JSONObject bankInfo = JSONFactoryUtil.createJSONObject();
				if (banksInfo.has(dossier.getServiceCode())) {
					bankInfo = banksInfo.getJSONObject(dossier.getServiceCode());
				} else {
					bankInfo = banksInfo.getJSONObject(PayGateTerm.DEFAULT);
				}
				bill_info.put(PayGateTerm.TKTHUHUONG, bankInfo.getString(PayGateTerm.TKTHUHUONG));
				bill_info.put(PayGateTerm.MANHTHUHUONG, bankInfo.getString(PayGateTerm.MANHTHUHUONG));
				bill_info.put(PayGateTerm.TENTKTHUHUONG, bankInfo.getString(PayGateTerm.TENTKTHUHUONG));

				JSONArray philephi = JSONFactoryUtil.createJSONArray();
				//????????????????????????
				JSONObject philephiJ = JSONFactoryUtil.createJSONObject();
				philephiJ.put(PayGateTerm.LOAIPHILEPHI, 2);
				philephiJ.put(PayGateTerm.MAPHILEPHI, 2);
				philephiJ.put(PayGateTerm.TENPHILEPHI, bankInfo.getString(PayGateTerm.TENPHILEPHI));
				philephiJ.put(PayGateTerm.SOTIEN, paymentFile.getFeeAmount());
				philephi.put(philephiJ);
				bill_info.put(PayGateTerm.PHILEPHI, philephi);

				// TODO: 
//				MaDonVi,TenDonVi,MaCoQuanQD,TenCoQuanQD
//
//				MaDonVi: 000.00.00.G17 TenDonVi: Bộ Xây dựng
//
//				MaCQ và Ten CQ trùng luôn MaDonVi và TenDonVi
				// bill_info.put(PayGateTerm.MADONVI, dossier.getGovAgencyCode());
				// bill_info.put(PayGateTerm.TENDONVI, dossier.getGovAgencyName());
				bill_info.put(PayGateTerm.MADONVI, schema.getString(PayGateTerm.MADONVI));
				bill_info.put(PayGateTerm.TENDONVI, schema.getString(PayGateTerm.TENDONVI));

				bill_info.put(PayGateTerm.MAHOSO, dossier.getDossierNo());
				ServiceInfoMapping serviceInfoMapping = ServiceInfoMappingLocalServiceUtil
						.fetchDVCQGServiceCode(groupId, dossier.getServiceCode());
				String serviceCodeDVCQG = serviceInfoMapping != null ? serviceInfoMapping.getServiceCodeDVCQG()
						: StringPool.BLANK;
				String serviceNameDVCQG = serviceInfoMapping != null ? serviceInfoMapping.getServiceNameDVCQG()
						: StringPool.BLANK;
				//TODO validate
				bill_info.put(PayGateTerm.MATTHC, serviceCodeDVCQG);// lay mapping
				// bill_info.put(PayGateTerm.TENTTHC, serviceNameDVCQG);
				bill_info.put(PayGateTerm.TENTTHC, dossier.getServiceName());
				// TODO: fix
				bill_info.put(PayGateTerm.MADVC, serviceCodeDVCQG + schema.getString(PayGateTerm.MADVCAPPEND));// chua xd
				bill_info.put(PayGateTerm.TENDVC, dossier.getServiceName());// chua xd
				bill_info.put(PayGateTerm.NOIDUNGTHANHTOAN, paymentFile.getPaymentNote());
				bill_info.put(PayGateTerm.MALOAIHINHTHUPHAT, "");//ko bb;

				bill_info.put(PayGateTerm.HOTENNGUOINOP, dossier.getApplicantName());

				bill_info.put(PayGateTerm.SOCMNDNGUOINOP, dossier.getApplicantIdNo());
				bill_info.put(PayGateTerm.DIACHINGUOINOP, dossier.getAddress());
				bill_info.put(PayGateTerm.HUYENNGUOINOP, "");//ko bb
				bill_info.put(PayGateTerm.TINHNGUOINOP, "");//ko bb

				//lay trong dictItem
//				ServiceInfo serviceInfo = ServiceInfoLocalServiceUtil.getByCode(groupId, dossier.getServiceCode());
//				String macoquanqd = serviceInfo != null ? serviceInfo.getAdministrationCode() : StringPool.BLANK;
//				String tencoquanqd = serviceInfo != null ? serviceInfo.getAdministrationName() : StringPool.BLANK;
//				bill_info.put(PayGateTerm.MACOQUANQD, macoquanqd);//ko xd
//				bill_info.put(PayGateTerm.TENCOQUANQD, tencoquanqd);
				// TODO: fix MACOQUANQD TENCOQUANQD
				bill_info.put(PayGateTerm.MACOQUANQD, schema.getString(PayGateTerm.MACOQUANQD));//ko xd
				bill_info.put(PayGateTerm.TENCOQUANQD, schema.getString(PayGateTerm.TENCOQUANQD));

				bill_info.put(PayGateTerm.KHOBAC, "");//ko bb
				bill_info.put(PayGateTerm.NGAYQD, "");//ko bb
				bill_info.put(PayGateTerm.SOQD, "");//ko bb

				bill_info.put(PayGateTerm.THOIGIANVIPHAM, "");
				bill_info.put(PayGateTerm.DIADIEMVIPHAM, "");
				bill_info.put(PayGateTerm.TENNGUOIVIPHAM, "");
				bill_info.put(PayGateTerm.TAIKHOANTHUNSNN, "");

				JSONArray dskhoannop = JSONFactoryUtil.createJSONArray();
				JSONObject dskhoannop_obj = JSONFactoryUtil.createJSONObject();

				dskhoannop_obj.put(PayGateTerm.NOIDUNG, paymentFile.getPaymentNote());
				dskhoannop_obj.put(PayGateTerm.SOTIEN, String.valueOf(paymentFile.getPaymentAmount()));
				dskhoannop.put(dskhoannop_obj);

				bill_info.put(PayGateTerm.DSKHOANNOP, dskhoannop);

				data.put(PayGateTerm.BILL_INFO, bill_info);

				JSONObject config = JSONFactoryUtil.createJSONObject(paymentFile.getEpaymentProfile())
						.getJSONObject(KeyPayTerm.KP_DVCQG_CONFIG);
				if (dossier.isOnline()) {
					String returnUrl = config.getJSONObject(PayGateTerm.ACTION_IS_ONLINE).getString(PayGateTerm.URL_DOMAIN);
					data.put(PayGateTerm.RETURN_URL, returnUrl);
				} else {
					String returnUrl = config.getJSONObject(PayGateTerm.ACTION_IS_NOT_ONLINE).getString(PayGateTerm.URL_DOMAIN);
					data.put(PayGateTerm.RETURN_URL, returnUrl);
				}

				data.put(PayGateTerm.ADDITION_FEE, addition_fee);

				String check_sum = PayGateUtil.generateChecksum(addition_fee, client_id, trans_amount, command,
						transactionId, version, hash_key_1);

				_log.info("keypay check_sum " + check_sum);

				data.put(PayGateTerm.CHECK_SUM, check_sum);

				String endpoint = schema.getString(PayGateTerm.KEYPAY_DVCQG_CREATE_TRANSACTION_ENDPOINT);

				_log.info("keypay endpoint " + endpoint);

				_log.info("keypay data " + data);

				URL url = new URL(endpoint);

				conn = (HttpURLConnection) url.openConnection();
				conn.setRequestMethod("POST");
				conn.setDoInput(true);
				conn.setDoOutput(true);

				conn.setRequestProperty("Accept", "application/json");
				conn.setRequestProperty("Content-Type", "application/json");

				conn.setInstanceFollowRedirects(true);
				HttpURLConnection.setFollowRedirects(true);
				conn.setReadTimeout(60 * 1000);

				byte[] postData = data.toJSONString().getBytes("UTF-8");
				int postDataLength = postData.length;
				conn.setRequestProperty("Content-Length", Integer.toString(postDataLength));
				try (DataOutputStream wr = new DataOutputStream(conn.getOutputStream())) {
					wr.write(postData);
				}

				conn.connect();

				try (BufferedReader bufferedReader = new BufferedReader(
						new InputStreamReader((conn.getInputStream())))) {

					String output = StringPool.BLANK;

					StringBuilder sb = new StringBuilder();

					while ((output = bufferedReader.readLine()) != null) {
						sb.append(output);
					}

					_log.info("response: " + sb.toString());

					JSONObject  response = JSONFactoryUtil.createJSONObject(sb.toString());
					
					if(response.has(PayGateTerm.ERROR_RES_KEY) && PayGateTerm.ERROR_RES_SUCCESS.equals(response.getString(PayGateTerm.ERROR_RES_KEY))) {
						JSONObject epaymentProfile = JSONFactoryUtil.createJSONObject(paymentFile.getEpaymentProfile());
						schema.put(PayGateTerm.TRANSACTION_ID, transactionId);
						epaymentProfile.put(KeyPayTerm.KP_DVCQG_CONFIG, schema);
						PaymentFileLocalServiceUtil.updateEProfile(dossier.getDossierId(), paymentFile.getReferenceUid(),
								epaymentProfile.toJSONString(), serviceContext);
					}
					result = sb.toString();
					_log.info("result " + result);

				}

			} catch (Exception e) {
				_log.error(e);
			} finally {
				if (conn != null) {
					conn.disconnect();
				}
			}

		}

		return result;
	}

	@Override
	public JSONObject kpViewDetailTransaction(User user, long groupId, long dossierId, ServiceContext serviceContext) {
		JSONObject result = JSONFactoryUtil.createJSONObject();
		Dossier dossier = DossierLocalServiceUtil.fetchDossier(dossierId);
		PaymentFile paymentFile = PaymentFileLocalServiceUtil.getByDossierId(groupId, dossierId);

		if (dossier != null && paymentFile != null) {
			HttpURLConnection conn = null;
			try {

				JSONObject schema = JSONFactoryUtil.createJSONObject(paymentFile.getEpaymentProfile()).getJSONObject(KeyPayTerm.KP_DVCQG_CONFIG);
				if (schema == null) {
					return result;
				}

				//addition_fee, client_id, trans_amount, command, transaction_id, version, haskey

				String client_id = schema.getString(PayGateTerm.CLIENT_ID);
				String command = schema.getString(PayGateTerm.COMMAND);
				String hash_key_1 = schema.getString(PayGateTerm.HASH_KEY_1);

				//TODO validate

				JSONObject data = JSONFactoryUtil.createJSONObject();

				String transactionId = schema.getString(PayGateTerm.TRANSACTION_ID);
				data.put(PayGateTerm.CLIENT_ID, schema.getString(PayGateTerm.CLIENT_ID));
				data.put(PayGateTerm.TRANSACTION_ID, transactionId);
				data.put(PayGateTerm.COMMAND, schema.getString(PayGateTerm.COMMAND));//default PAY

				String check_sum = PayGateUtil.generateChecksum(client_id, command, transactionId, hash_key_1);

				_log.info("keypay check_sum " + check_sum);

				data.put(PayGateTerm.CHECK_SUM, check_sum);

				String endpoint = schema.getString(PayGateTerm.KEYPAY_DVCQG_DETAIL_TRANSACTION_ENDPOINT);

				_log.info("keypay endpoint " + endpoint);

				_log.info("keypay data " + data);

				URL url = new URL(endpoint);

				conn = (HttpURLConnection) url.openConnection();
				conn.setRequestMethod("POST");
				conn.setDoInput(true);
				conn.setDoOutput(true);

				conn.setRequestProperty("Accept", "application/json");
				conn.setRequestProperty("Content-Type", "application/json");

				conn.setInstanceFollowRedirects(true);
				HttpURLConnection.setFollowRedirects(true);
				conn.setReadTimeout(60 * 1000);

				byte[] postData = data.toJSONString().getBytes("UTF-8");
				int postDataLength = postData.length;
				conn.setRequestProperty("Content-Length", Integer.toString(postDataLength));
				try (DataOutputStream wr = new DataOutputStream(conn.getOutputStream())) {
					wr.write(postData);
				}

				conn.connect();

				try (BufferedReader bufferedReader = new BufferedReader(
						new InputStreamReader((conn.getInputStream())))) {

					String output = StringPool.BLANK;

					StringBuilder sb = new StringBuilder();

					while ((output = bufferedReader.readLine()) != null) {
						sb.append(output);
					}

					_log.info("response: " + sb.toString());

					result = JSONFactoryUtil.createJSONObject(sb.toString());

				}

			} catch (Exception e) {
				_log.error(e);
			} finally {
				if (conn != null) {
					conn.disconnect();
				}
			}

		}
		return result;
	}

	@Override
	public JSONObject kpCallBack(User user, ServiceContext serviceContext, String body) {

		try {
			_log.info("=======body========" + body);
			JSONObject data = JSONFactoryUtil.createJSONObject(body);

			if (data != null && data.length() > 0) {
				//String client_id = data.getString(PayGateTerm.CLIENT_ID);
				String transaction_id = data.getString(PayGateTerm.TRANSACTION_ID);
				///String transaction_code = data.getString(PayGateTerm.TRANSACTION_CODE);
				//String trans_amount = data.getString(PayGateTerm.TRANS_AMOUNT);
				//String payment_fee = data.getString(PayGateTerm.PAYMENT_FEE);
				//String command = data.getString(PayGateTerm.COMMAND);
				//String addition_fee = data.getString(PayGateTerm.ADDITION_FEE);
				//String version = data.getString(PayGateTerm.VERSION);
				//String description = data.getString(PayGateTerm.DESCRIPTION);
				//String locale = data.getString(PayGateTerm.LOCALE);
				//String country_code = data.getString(PayGateTerm.COUNTRY_CODE);
				//String currency_code = data.getString(PayGateTerm.CURRENCY_CODE);
				//String environment = data.getString(PayGateTerm.ENVIRONMENT);

				String check_sum = data.getString(PayGateTerm.CHECK_SUM);

				long paymentFileId = 0;

				if (Validator.isNotNull(transaction_id)) {
					paymentFileId = PayGateUtil.getPaymentFileIdByTrans(transaction_id);
				}

				if (paymentFileId <= 0) {
					return PayGateUtil.createResponseMessage(-1, "error: paymentfile_id = 0");
				}

				PaymentFile paymentFile = PaymentFileLocalServiceUtil.fetchPaymentFile(paymentFileId);

				if (paymentFile == null) {
					return PayGateUtil.createResponseMessage(-1, "error: paymentfile null");
				}

				Dossier dossier = DossierLocalServiceUtil.fetchDossier(paymentFile.getDossierId());

				if (dossier == null) {
					return PayGateUtil.createResponseMessage(-1, "error: dossier null");
				}

				JSONObject schema = JSONFactoryUtil.createJSONObject(paymentFile.getEpaymentProfile()).getJSONObject(KeyPayTerm.KP_DVCQG_CONFIG);
				if (schema == null) {
					return PayGateUtil.createResponseMessage(-1, "error: paymentfile_config null");
				}

				String client_id_config = schema.getString(PayGateTerm.CLIENT_ID);
				String command_config = schema.getString(PayGateTerm.COMMAND);
				String hash_key_2 = schema.getString(PayGateTerm.HASH_KEY_2);
				String transactionId_tmp = schema.getString(PayGateTerm.TRANSACTION_ID);
				String version_config = schema.getString(PayGateTerm.VERSION);

				String addition_fee = String.valueOf(paymentFile.getShipAmount());
				String trans_amount = String.valueOf(paymentFile.getPaymentAmount());
				String check_sum_tmp = PayGateUtil.generateChecksum(addition_fee,
						client_id_config, trans_amount, command_config,
						transactionId_tmp, version_config, hash_key_2);
				if (!check_sum.equals(check_sum_tmp)) {
					return PayGateUtil.createResponseMessage(-1, "error: check_sum invalid");
				}

				int status = data.getInt(PayGateTerm.STATUS);

				if (status == 0) {

					boolean doAction = doAction(user, paymentFile.getGroupId(), dossier, paymentFile, serviceContext);

					if (doAction) {
						return PayGateUtil.createResponseMessage(0, "success");
					} else {
						return PayGateUtil.createResponseMessage(-1, "error");
					}

					//
				} else {
					return PayGateUtil.createResponseMessage(0, "success");
				}

			} else {
				return PayGateUtil.createResponseMessage(-1, "error: data empty of not found server config");
			}

		} catch (Exception e) {
			_log.error(e);
			return PayGateUtil.createResponseMessage(-1, "error: system exception");
		}
	}

	private boolean doAction(User user, long groupId, Dossier dossier, PaymentFile paymentFile,
			ServiceContext serviceContext) {

		try {

			JSONObject config = JSONFactoryUtil.createJSONObject(paymentFile.getEpaymentProfile())
					.getJSONObject(KeyPayTerm.KP_DVCQG_CONFIG);

			PaymentFileActions actions = new PaymentFileActionsImpl();

			JSONObject action = JSONFactoryUtil.createJSONObject();

			if (dossier.isOnline()) {
				// TODO: call api doaction to DVC
				action = config.getJSONObject(PayGateTerm.ACTION_IS_ONLINE);

				String actionCode = action.getString(PayGateTerm.ACTION_CODE);

				String url = action.getString(PayGateTerm.URL);

				String username = action.getString(PayGateTerm.USERNAME);

				String pwd = action.getString(PayGateTerm.PWD);

				// Change payment Status = 5
				paymentFile = actions.updateFileConfirm(groupId, dossier.getDossierId(), paymentFile.getReferenceUid(),
						StringPool.BLANK, PaymentFileTerm.PAYMENT_METHOD_KEYPAY_DVCQG,
						JSONFactoryUtil.createJSONObject().toJSONString(), serviceContext);

				HashMap<String, String> properties = new HashMap<String, String>();

				properties.put(Field.GROUP_ID, String.valueOf(groupId));

				String endPoint = PayGateTerm.buildPathDoAction(url, dossier.getDossierId());

				Map<String, Object> params = new HashMap<String, Object>();

				params.put(PayGateTerm.ACTION_CODE, actionCode);

				JSONObject payment = JSONFactoryUtil.createJSONObject();
				payment.put(PaymentFileTerm.PAYMENT_REQUEST, 3);
				payment.put(PaymentFileTerm.ADVANCE_AMOUNT, paymentFile.getAdvanceAmount());
				payment.put(PaymentFileTerm.FEE_AMOUNT, paymentFile.getFeeAmount());
				payment.put(PaymentFileTerm.PAYMENT_NOTE, paymentFile.getPaymentNote());
				payment.put(PaymentFileTerm.SERVICE_AMOUNT, paymentFile.getServiceAmount());
				payment.put(PaymentFileTerm.SHIP_AMOUNT, paymentFile.getShipAmount());
				payment.put(PaymentFileTerm.PAYMENT_METHOD, PaymentFileTerm.PAYMENT_METHOD_KEYPAY_DVCQG);
				params.put(PayGateTerm.PAYMENT, payment.toString());

				long dossierActionId = dossier.getDossierActionId();

				DossierAction dossierAction = DossierActionLocalServiceUtil.fetchDossierAction(dossierActionId);

				long serviceProcessId = dossierAction.getServiceProcessId();

				String stepCode = dossierAction.getStepCode();

				if (stepCode != null) {

					List<ProcessAction> processActionList = ProcessActionLocalServiceUtil
							.getProcessActionByG_SPID_PRESC(groupId, serviceProcessId, stepCode);

					for (ProcessAction processAction : processActionList) {

						_log.info(processAction.getActionCode());
						_log.info(processAction.getRequestPayment());
						if (processAction.getActionCode().equals(actionCode)) {

							payment = JSONFactoryUtil.createJSONObject();
							payment.put(PaymentFileTerm.PAYMENT_REQUEST, processAction.getRequestPayment());
							payment.put(PaymentFileTerm.ADVANCE_AMOUNT, paymentFile.getAdvanceAmount());
							payment.put(PaymentFileTerm.FEE_AMOUNT, paymentFile.getFeeAmount());
							payment.put(PaymentFileTerm.PAYMENT_NOTE, paymentFile.getPaymentNote());
							payment.put(PaymentFileTerm.SERVICE_AMOUNT, paymentFile.getServiceAmount());
							payment.put(PaymentFileTerm.PAYMENT_METHOD, PaymentFileTerm.PAYMENT_METHOD_KEYPAY_DVCQG);
							params.put(PayGateTerm.PAYMENT, payment.toString());
						}
					}
				}

				_log.info("params============" + params);
				JSONObject resPostDossier = callPostAPI(HttpMethod.POST, MediaType.APPLICATION_JSON, endPoint,
						properties, params, username, pwd);

				_log.info("=====resPostDossier=========" + resPostDossier);

				return true;
			} else {
				paymentFile = actions.updateFileConfirm(groupId, dossier.getDossierId(), paymentFile.getReferenceUid(),
						StringPool.BLANK, PaymentFileTerm.PAYMENT_METHOD_KEYPAY_DVCQG,
						JSONFactoryUtil.createJSONObject().toJSONString(), serviceContext);

				action = config.getJSONObject(PayGateTerm.ACTION_IS_NOT_ONLINE);

				HashMap<String, String> properties = new HashMap<String, String>();

				properties.put(Field.GROUP_ID, String.valueOf(dossier.getGroupId()));

				String endPoint = PayGateTerm.buildPathDoAction(action.getString(PayGateTerm.URL),
						dossier.getDossierId());

				Map<String, Object> params = new HashMap<String, Object>();

				params.put(PayGateTerm.ACTION_CODE, action.get(PayGateTerm.ACTION_CODE));

				JSONObject resPostDossier = callPostAPI(HttpMethod.POST, MediaType.APPLICATION_JSON, endPoint,
						properties, params, action.getString(PayGateTerm.USERNAME), action.getString(PayGateTerm.PWD));

				_log.info("=====resPostDossier=========" + resPostDossier);

				return true;
			}

		} catch (Exception e) {
			_log.error(e);
			return false;
		}
	}

}
