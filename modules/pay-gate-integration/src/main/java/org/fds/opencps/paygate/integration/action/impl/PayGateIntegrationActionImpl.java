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
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.FileUtil;
import com.liferay.portal.kernel.util.Validator;

import java.io.BufferedReader;
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
import org.opencps.communication.model.ServerConfig;
import org.opencps.communication.service.ServerConfigLocalServiceUtil;
import org.opencps.dossiermgt.action.PaymentFileActions;
import org.opencps.dossiermgt.action.impl.PaymentFileActionsImpl;
import org.opencps.dossiermgt.action.util.ConstantUtils;
import org.opencps.dossiermgt.action.util.OpenCPSConfigUtil;
import org.opencps.dossiermgt.constants.VTPayTerm;
import org.opencps.dossiermgt.model.Dossier;
import org.opencps.dossiermgt.model.PaymentConfig;
import org.opencps.dossiermgt.model.PaymentFile;
import org.opencps.dossiermgt.service.DossierLocalServiceUtil;
import org.opencps.dossiermgt.service.PaymentConfigLocalServiceUtil;
import org.opencps.dossiermgt.service.PaymentFileLocalServiceUtil;

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

	private void invokeReceiveResult(User user, ServiceContext serviceContext, JSONObject searchResult, String billcode,
			String cust_msisdn, long trans_amount) {

		String merchant_code = searchResult.getString(PayGateTerm.MERCHANT_CODE);
		String order_id = searchResult.getString(PayGateTerm.ORDER_ID);
		int payment_status = searchResult.getInt(PayGateTerm.PAYMENT_STATUS);
		String version = searchResult.getString(PayGateTerm.VERSION);
		String check_sum = searchResult.getString(PayGateTerm.CHECK_SUM);
		String error_code = searchResult.getString(PayGateTerm.ERROR_CODE);
		String vt_transaction_id = searchResult.getString(PayGateTerm.VT_TRANSACTION_ID);
		receiveResult(user, serviceContext, billcode, cust_msisdn, error_code, merchant_code, order_id, payment_status,
				trans_amount, vt_transaction_id, check_sum);
	}

	public JSONObject callPostAPI(String httpMethod, String accept, String urlPath, HashMap<String, String> properties,
			Map<String, Object> params, String username, String password) {

		JSONObject response = JSONFactoryUtil.createJSONObject();

		HttpURLConnection conn = null;

		BufferedReader br = null;

		try {

			response.put("url___", urlPath);
			URL url = new URL(urlPath);

			conn = (HttpURLConnection) url.openConnection();
			conn.setConnectTimeout(OpenCPSConfigUtil.getRestConnectionTimeout());
			conn.setReadTimeout(OpenCPSConfigUtil.getRestReadTimeout());
			conn.setRequestMethod(httpMethod);
			conn.setDoInput(true);
			conn.setDoOutput(true);
			response.put("url___2", accept);
			conn.setRequestProperty(ConstantUtils.VALUE_ACCEPT, accept);

			if (Validator.isNotNull(username) && Validator.isNotNull(password)) {
				String authString = username + StringPool.COLON + password;

				String authStringEnc = new String(Base64.getEncoder().encodeToString(authString.getBytes()));

				conn.setRequestProperty(ConstantUtils.VALUE_AUTHORIZATION, ConstantUtils.VALUE_BASIC + authStringEnc);
			}
			response.put("url___3", 1);
			if (!properties.isEmpty()) {
				for (Map.Entry m : properties.entrySet()) {
					conn.setRequestProperty(m.getKey().toString(), m.getValue().toString());
				}
			}

			StringBuilder postData = new StringBuilder();
			response.put("url___4", accept);
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
			response.put("url___5", sb);
			JSONFactoryUtil.createJSONObject(sb.toString());
			//response = JSONFactoryUtil.createJSONObject(sb.toString());

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
		System.out.println("=============call to doconfirm===================");
		_log.info("=============call to doconfirm===================");
		String mcUrl = VTPayTerm.getMcUrlByBillCode(billcode) + StringPool.SLASH + PayGateTerm.ENDPOINT_CONFIRM;
		HashMap<String, String> properties = new HashMap<String, String>();
		Map<String, Object> params = new HashMap<>();

		properties.put(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_FORM_URLENCODED);
		params.put(PayGateTerm.BILLCODE, billcode);
		params.put(PayGateTerm.MERCHANT_CODE, merchant_code);
		params.put(PayGateTerm.ORDER_ID, order_id);
		params.put(PayGateTerm.CHECK_SUM, check_sum);
		JSONObject schema = JSONFactoryUtil.createJSONObject();
		schema.put("lollll", 2);
		return schema;
//		return callPostAPI(HttpMethod.POST, MediaType.APPLICATION_JSON, mcUrl, properties, params, StringPool.BLANK,
//				StringPool.BLANK);
		
	}

	@Override
	public JSONObject receiveResult(User user, ServiceContext serviceContext, String billcode, String cust_msisdn,
			String error_code, String merchant_code, String order_id, int payment_status, long trans_amount,
			String vt_transaction_id, String check_sum) {
		System.out.println("=============call to rec===================");
		_log.info("=============call to receiveResult===================");
		String mcUrl = VTPayTerm.getMcUrlByBillCode(billcode) + StringPool.SLASH + PayGateTerm.ENDPOINT_RECEIVER;
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
		System.out.println("=============call to search===================");

		String mcUrl = VTPayTerm.getMcUrlByBillCode(billcode) + StringPool.SLASH + PayGateTerm.ENDPOINT_SEARCH;
		HashMap<String, String> properties = new HashMap<String, String>();
		Map<String, Object> params = new HashMap<>();

		params.put(PayGateTerm.BILLCODE, billcode);
		params.put(PayGateTerm.CUST_MSISDN, cust_msisdn);
		params.put(PayGateTerm.TRANS_AMOUNT, trans_amount);
		params.put(PayGateTerm.ORDER_ID, order_id);
		return callPostAPI(HttpMethod.GET, MediaType.APPLICATION_JSON, mcUrl, properties, params, StringPool.BLANK,
				StringPool.BLANK);
	}

	@Override
	public JSONObject mcDoConfirm(User user, ServiceContext serviceContext, String billcode, String merchant_code,
			String order_id, String check_sum) {

		try {

			System.out.println("=============mcDO confirm========");
			_log.info("=============mcDO confir");
			long dossierId = VTPayTerm.getDossierIdNoByOrderId(order_id);
			Dossier dossier = DossierLocalServiceUtil.fetchDossier(dossierId);

			_log.info(dossier);

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
			if (!paymentFile.getInvoiceNo().equals(invoiceNo)) {
				return confirmResponseData(billcode, order_id, merchant_code, check_sum, paymentFile.getPaymentAmount(),
						PayGateTerm.ERROR_CODE_01);
			}

			String _tmp_check_sum = access_code + paymentFile.getInvoiceNo() + merchant_code + order_id
					+ paymentFile.getPaymentAmount();

			try {
				SecretKeySpec signingKey = new SecretKeySpec(hash_key.getBytes(), PayGateTerm.HMAC_SHA1);
				Mac mac = Mac.getInstance(PayGateTerm.HMAC_SHA1);
				mac.init(signingKey);
				String _tmp_check_sum_encode = toHexString(
						mac.doFinal(_tmp_check_sum.getBytes(StandardCharsets.UTF_8)));
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

			System.out.println("=============call to mc rec===================");

			long dossierId = VTPayTerm.getDossierIdNoByOrderId(order_id);
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

			if (PayGateTerm.ERROR_CODE_00.equals(error_code)) {
				JSONObject action = JSONFactoryUtil.createJSONObject();

				if (dossier.isOnline()) {
					// TODO: call api doaction to DVC
					action = config.getJSONObject(PayGateTerm.ACTION_IS_ONLINE);
					String endPoint = action.getString(PayGateTerm.URL) + PayGateTerm.ENDPOINT_DVCRECEIVER;
					HashMap<String, String> properties = new HashMap<String, String>();
					properties.put(Field.GROUP_ID, action.getString(Field.GROUP_ID));

					Map<String, Object> params = new HashMap<String, Object>();
					params.put(PayGateTerm.ACTION_CODE, action.get(PayGateTerm.ACTION_CODE));
					params.put(PayGateTerm.URL, action.getString(PayGateTerm.URL));
					params.put(Field.GROUP_ID, action.getString(Field.GROUP_ID));
					params.put(PayGateTerm.ORDER_ID, order_id);
					JSONObject resPostDossier = callPostAPI(HttpMethod.POST, MediaType.APPLICATION_JSON, endPoint,
							properties, params, action.getString(PayGateTerm.USERNAME),
							action.getString(PayGateTerm.PWD));
					System.out.println("=====resPostDossier=========" + resPostDossier);

				} else {
					// TODO: call api doaction to MC

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

					System.out.println("=====resPostDossier=========" + resPostDossier);
				}
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
		System.out.println("=============call to MCsearch===================");

		try {

			long dossierId = VTPayTerm.getDossierIdNoByOrderId(order_id);
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
			params.put(schema.getJSONObject(PayGateTerm.MERCHANT_CODE).getString(PayGateTerm.KEY),
					schema.getJSONObject(PayGateTerm.MERCHANT_CODE).getString(PayGateTerm.VALUE));
			params.put(schema.getJSONObject(PayGateTerm.ORDER_ID).getString(PayGateTerm.KEY), order_id);
			params.put(PayGateTerm.CHECK_SUM, check_sum_encoded);

			JSONObject searchResult = callPostAPI(HttpMethod.POST, MediaType.APPLICATION_JSON, url_search, properties,
					params, StringPool.BLANK, StringPool.BLANK);

			invokeReceiveResult(user, serviceContext, searchResult, billcode, cust_msisdn, trans_amount);
			return searchResult;

		} catch (Exception e) {
			e.printStackTrace();
			return JSONFactoryUtil.createJSONObject();
		}
	}

	@Override
	public JSONObject dvcReceiveResult(User user, ServiceContext serviceContext, String url, long groupId,
			String actionCode, String order_id, String username, String pwd) {

		System.out.println("=============call to DVC result===================");

		try {

			Dossier dossier = DossierLocalServiceUtil.getByDossierNo(groupId, order_id);
			PaymentConfig paymentConfig = PaymentConfigLocalServiceUtil
					.getPaymentConfigByGovAgencyCode(dossier.getGroupId(), dossier.getGovAgencyCode());
			PaymentFile paymentFile = PaymentFileLocalServiceUtil.getByDossierId(dossier.getGroupId(),
					dossier.getDossierId());

			PaymentFileActions actions = new PaymentFileActionsImpl();

			// Change payment Status = 5
			actions.updateFileConfirm(paymentFile.getGroupId(), paymentFile.getDossierId(),
					paymentFile.getReferenceUid(), StringPool.BLANK, "Keypay",
					JSONFactoryUtil.createJSONObject().toJSONString(), serviceContext);

			HashMap<String, String> properties = new HashMap<String, String>();
			properties.put(Field.GROUP_ID, String.valueOf(groupId));

			String endPoint = PayGateTerm.buildPathDoAction(url, dossier.getDossierId());

			Map<String, Object> params = new HashMap<String, Object>();
			params.put(PayGateTerm.ACTION_CODE, actionCode);
			JSONObject resPostDossier = callPostAPI(HttpMethod.POST, MediaType.APPLICATION_JSON, endPoint, properties,
					params, username, pwd);

			System.out.println("=====resPostDossier=========" + resPostDossier);
			return resPostDossier;

		} catch (Exception e) {
			e.printStackTrace();
			return JSONFactoryUtil.createJSONObject();
		}
	}
}
