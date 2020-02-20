
package org.opencps.api.controller.impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.Normalizer;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.HttpMethod;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;

import org.opencps.api.constants.ConstantUtils;
import org.opencps.api.constants.SMSManagementConstants;
import org.opencps.api.controller.SMSManagement;
import org.opencps.api.controller.util.MessageUtil;
import org.opencps.api.sms.model.IPacificSearchSMS;
import org.opencps.communication.constants.SendSMSTerm;
import org.opencps.communication.model.ServerConfig;
import org.opencps.communication.service.ServerConfigLocalServiceUtil;
import org.opencps.communication.sms.utils.ViettelSMSUtils;
import org.opencps.datamgt.util.DueDateUtils;
import org.opencps.dossiermgt.model.Dossier;
import org.opencps.dossiermgt.model.PaymentConfig;
import org.opencps.dossiermgt.model.PaymentFile;
import org.opencps.dossiermgt.scheduler.InvokeREST;
import org.opencps.dossiermgt.service.DossierLocalServiceUtil;
import org.opencps.dossiermgt.service.PaymentConfigLocalServiceUtil;
import org.opencps.dossiermgt.service.PaymentFileLocalServiceUtil;

import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Company;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.servlet.HttpMethods;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;

import backend.auth.api.exception.BusinessExceptionImpl;
import ws.bulkSms.impl.Result;

public class SMSManagementImpl implements SMSManagement {

	@Override
	public Response sendSMS(
		HttpServletRequest request, HttpHeaders header, Company company,
		Locale locale, User user, ServiceContext serviceContext, String body,
		String toTelNo) {

		try {
			long groupId =
				GetterUtil.getLong(header.getHeaderString(Field.GROUP_ID));
			Result result = ViettelSMSUtils.sendSMS(
				groupId, body, StringPool.BLANK, toTelNo);

			return Response.status(HttpURLConnection.HTTP_OK).entity(result.getMessage()).build();
		}
		catch (Exception e) {
			return BusinessExceptionImpl.processException(e);
		}
	}

	@Override
	public Response getInetSMS(
		HttpServletRequest request, HttpHeaders header, Company company,
		Locale locale, User user, ServiceContext serviceContext,
		IPacificSearchSMS input) {

		// Bgtvt mshs mtc -> 8085
		// ====GET=====input.getCommandCode()=======BGTVT
		// ====GET=====input.getInfo()==============Bgtvt mshs mtc
		// ============input.getPassword()==========Gt.sms@201620182019
		// ============input.getServiceNumber()=====8085
		// ============input.getUser()==============gtsms2019
		// ============input.getUserId()============84978266524

		_log.info("===============" + input.getPassword());
		_log.info("===============" + input.getServiceNumber());
		_log.info("===============" + input.getUser());
		
		JSONObject formDataKey = JSONFactoryUtil.createJSONObject();
//		formDataKey.put("bac", 1);
//		formDataKey.put("baB", "shkdshd");
		buildDeliverableSearchDataForm(formDataKey.toJSONString());

		return Response.status(HttpURLConnection.HTTP_OK).entity(_buiderResponseSMS(input)).build();
	}

	@Override
	public Response getZaloUIdByTelNo(
		HttpServletRequest request, HttpHeaders header, Company company,
		Locale locale, User user, ServiceContext serviceContext,
		String toTelNo) {

		try {
			JSONObject zaloConfig = _getZaloInfo();
			String zaloAccessToken =
				zaloConfig.getString(SendSMSTerm.OAID_TOKEN_ACCESS);
			JSONObject resultApi = JSONFactoryUtil.createJSONObject(
				_getZaloUidByTelNo(zaloAccessToken, toTelNo));
			String uid = "not found";
			if (resultApi.has(ConstantUtils.DATA)) {

				uid = resultApi.getJSONObject(ConstantUtils.DATA).getString(ConstantUtils.USER_ID);
			}
			return Response.status(HttpURLConnection.HTTP_OK).entity(uid).build();
		}
		catch (Exception e) {
			_log.debug(e);
			return Response.status(HttpURLConnection.HTTP_INTERNAL_ERROR).entity(StringPool.BLANK).build();
		}

	}

	private String _buiderResponseSMS(IPacificSearchSMS iPacific) {

		// contentType;messageType;info;[mobile]
		// contentType: Loại nội dung trả về.
		// 0:Text
		// 2:Logo
		// 8:wappush
		// MessageType:Bản tin có hợp lệ không->Có tính tiền được không
		// 71:Valid->Tính tiền
		// 72:Invalid->Hoàn cước cho khách hàng
		// info:Nội dung trả về.
		// mobile:Số điện thoại khách hàng nhận tin nhắn
		// "0;72;HE THONG XIN THONG BAO\n BAN KHONG THE NHAN TIN NHAN;|0;0;Nhan
		// den ban b;0362219930"

		String result = MessageUtil.getMessage(SMSManagementConstants.SYSTEM_NOT_CONFIG_MESSAGE);
		JSONObject epacifConfig = JSONFactoryUtil.createJSONObject();
		try {

			ServerConfig sc = ServerConfigLocalServiceUtil.getByCode(
				SendSMSTerm.SERVER_CONFIG_SERVERNO_EPACIFIC);
			epacifConfig = JSONFactoryUtil.createJSONObject(sc.getConfigs());
			Dossier dossier = null;

			if (epacifConfig.has(SendSMSTerm.EPACIFIC_GROUPID) &&
				epacifConfig.has(SendSMSTerm.EPACIFIC_MINE) &&
				epacifConfig.has(SendSMSTerm.EPACIFIC_USER) &&
				epacifConfig.has(SendSMSTerm.EPACIFIC_SECRET)) {

				String[] strArr = iPacific.getInfo().split(" ");
				String dossierNo = strArr[1];
				String secretCode = strArr[2];

				dossier = DossierLocalServiceUtil.getByDossierNo(
					epacifConfig.getLong(SendSMSTerm.EPACIFIC_GROUPID),
					dossierNo);

				if (!epacifConfig.getString(
					SendSMSTerm.EPACIFIC_MINE).equalsIgnoreCase(
						iPacific.getCommandCode())) {

					result = epacifConfig.getString(
						SendSMSTerm.EPACIFIC_MINE_ERROR_MES);

				}
				else if (!epacifConfig.getString(
					SendSMSTerm.EPACIFIC_USER).equalsIgnoreCase(
						iPacific.getUser())) {

					result = epacifConfig.getString(
						SendSMSTerm.EPACIFIC_USER_ERROR_MES);

				}
				else if (!epacifConfig.getString(
					SendSMSTerm.EPACIFIC_SECRET).equalsIgnoreCase(
						iPacific.getPassword())) {

					result = epacifConfig.getString(
						SendSMSTerm.EPACIFIC_SECRET_ERROR_MES);

				}
				else if (Validator.isNull(dossier)) {

					result = epacifConfig.getString(
						SendSMSTerm.EPACIFIC_D_NOT_FOUND_MES);

				}
				else if (!dossier.getPassword().equalsIgnoreCase(secretCode)) {

					result = epacifConfig.getString(
						SendSMSTerm.EPACIFIC_D_SECRET_ERROR_MES);

				}
				else {

					result = epacifConfig.getString(
						SendSMSTerm.EPACIFIC_SUCCESS_MES);
					result = result.replaceAll(
						epacifConfig.getString(
							SendSMSTerm.EPACIFIC_DOSSIER_NO_REPLACE),
						dossierNo);
					result = result.replaceAll(
						epacifConfig.getString(
							SendSMSTerm.EPACIFIC_DOSSIER_STATUS_REPLACE),
						_removeAccent(dossier.getDossierStatusText()));
				}
			}
			else {

				result = MessageUtil.getMessage(SMSManagementConstants.SYSTEM_NOT_CONFIG_MESSAGE);
			}
		}
		catch (ArrayIndexOutOfBoundsException e) {
			_log.debug(e);
			result =
				epacifConfig.getString(SendSMSTerm.EPACIFIC_SYNTAX_ERROR_MES);
		}
		catch (Exception e) {
			_log.debug(e);
			result = MessageUtil.getMessage(SMSManagementConstants.SYSTEM_NOT_CONFIG_MESSAGE);
		}

		_log.info(result);
		return result;
	}

	private static final String MAU_KHONG_DAU = "\\p{InCombiningDiacriticalMarks}+";
	private static final String DD = "Đ";
	private static final String D = "D";
	private static final String dd = "đ";
	private static final String d = "d";
	
	private String _removeAccent(String textConvert) {

		String temp = Normalizer.normalize(textConvert, Normalizer.Form.NFD);
		Pattern pattern = Pattern.compile(MAU_KHONG_DAU);
		return pattern.matcher(temp).replaceAll(StringPool.BLANK).replaceAll(
			DD, D).replaceAll(dd, d);
	}

	private static final String VN_FORMAT_H = "dd-MM-yyyy-HH-mm";
	
	@Override
	public Response calculateDueDate(
		HttpServletRequest request, HttpHeaders header, Company company,
		Locale locale, User user, ServiceContext serviceContext,
		String startDate, double durationCount, int durationUnit,
		long groupId) {

		try {

			Date startDateS =
				new SimpleDateFormat(VN_FORMAT_H).parse(startDate);
			String dueDate2 =
				new SimpleDateFormat(VN_FORMAT_H).format(startDateS);
			_log.info(startDateS);
			_log.info(dueDate2);
			DueDateUtils dueDateUtils = new DueDateUtils(
				startDateS, durationCount, durationUnit, groupId);
			String dueDate = new SimpleDateFormat(VN_FORMAT_H).format(
				dueDateUtils.getDueDate());
			return Response.status(HttpURLConnection.HTTP_OK).entity(dueDate).build();
		}
		catch (Exception e) {
			_log.debug(e);
		}

		return Response.status(HttpURLConnection.HTTP_INTERNAL_ERROR).entity(StringPool.BLANK).build();
	}

	private String _getZaloUidByTelNo(String token, String toTelNo) {

		try {

			HashMap<String, String> properties = new HashMap<String, String>();

			JSONObject data = JSONFactoryUtil.createJSONObject();

			data.put(ConstantUtils.USER_ID, toTelNo);

//			String endPoint = "/v2.0/oa/getprofile?access_token=" + token +
//				"&data=" + data.toJSONString();
			String endPoint = String.format(MessageUtil.getMessage(SMSManagementConstants.SMS_ACCESS_TOKEN_URL), token, data.toJSONString());
			
			_log.info("end point=========" + endPoint);

			JSONObject resPostDossier = _callAPI(
				HttpMethods.GET, ConstantUtils.CONTENT_TYPE_JSON, MessageUtil.getMessage(SMSManagementConstants.SMS_ZALO_API_URL),
				endPoint, StringPool.BLANK, StringPool.BLANK, properties);
			String uid = resPostDossier.getString(SMSManagementConstants.SMS_MESSAGE);

			if (Validator.isNotNull(uid)) {

				return uid;
			}
		}
		catch (Exception e) {
			_log.debug(e);
		}
		return StringPool.BLANK;
	}

	private static JSONObject _getZaloInfo() {

		JSONObject zaloInfoConfig = JSONFactoryUtil.createJSONObject();

		try {

			ServerConfig sc = ServerConfigLocalServiceUtil.getByCode(
				SendSMSTerm.SERVER_CONFIG_SERVERNO_ZALO);

			zaloInfoConfig = JSONFactoryUtil.createJSONObject(sc.getConfigs());
		}
		catch (Exception e) {
			_log.debug(e);
		}

		return zaloInfoConfig;
	}

	private JSONObject _callAPI(
		String httpMethod, String accept, String pathBase, String endPoint,
		String username, String password, HashMap<String, String> properties) {

		JSONObject response = JSONFactoryUtil.createJSONObject();

		try {
			String urlPath;
			if (pathBase.endsWith(StringPool.SLASH) && endPoint.startsWith(StringPool.SLASH)) {
				String endPoint2 = endPoint.substring(1);
				urlPath = pathBase + endPoint2;
			}
			else if ((!pathBase.endsWith(StringPool.SLASH) && endPoint.startsWith(StringPool.SLASH)) ||
				(pathBase.endsWith(StringPool.SLASH) && !endPoint.startsWith(StringPool.SLASH))) {
				urlPath = pathBase + endPoint;
			}
			else {
				urlPath = pathBase + StringPool.SLASH + endPoint;
			}
			URL url = new URL(urlPath);

			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setConnectTimeout(RESTFulConfiguration.TIME_OUT);

			conn.setRequestMethod(httpMethod);
			conn.setRequestProperty(HttpHeaders.ACCEPT, accept);
			conn.setDoInput(true);
			conn.setDoOutput(true);
			conn.setRequestProperty(Field.GROUP_ID, StringPool.BLANK);

			if (Validator.isNotNull(username) &&
				Validator.isNotNull(password)) {

				String authString = username + StringPool.COLON + password;

				String authStringEnc = new String(
					java.util.Base64.getEncoder().encodeToString(
						authString.getBytes()));
				String basicAuth = String.format(MessageUtil.getMessage(ConstantUtils.HTTP_HEADER_BASICAUTH), authStringEnc);
				conn.setRequestProperty(
					HttpHeaders.AUTHORIZATION, basicAuth);
			}

			if (!properties.isEmpty()) {
				for (Map.Entry m : properties.entrySet()) {
					conn.setRequestProperty(
						m.getKey().toString(), m.getValue().toString());
				}
			}

			BufferedReader br = new BufferedReader(
				new InputStreamReader((conn.getInputStream())));

			String output;

			StringBuilder sb = new StringBuilder();

			while ((output = br.readLine()) != null) {
				sb.append(output);
			}

			response.put(RESTFulConfiguration.STATUS, conn.getResponseCode());
			response.put(RESTFulConfiguration.MESSAGE, sb.toString());

			conn.disconnect();

		}
		catch (MalformedURLException e) {
			_log.debug(e);
		}
		catch (IOException e1) {
			_log.debug(e1);
		}

		return response;
	}

	@Override
	public Response postInvoiceTest(
		HttpServletRequest request, HttpHeaders header, Company company,
		Locale locale, User user, ServiceContext serviceContext,
		long dossierId) {

		try {
			long groupId =
				GetterUtil.getLong(header.getHeaderString(Field.GROUP_ID));
			System.out.println(
				"================groupId====================" + groupId +
					" ===" + dossierId);
			Dossier dossier = DossierLocalServiceUtil.fetchDossier(dossierId);

			System.out.println(Validator.isNull(dossier));

			String res = _invokeSInvoice(groupId, dossier, serviceContext);

			return Response.status(200).entity(res).build();
		}
		catch (Exception e) {
			e.printStackTrace();
			return BusinessExceptionImpl.processException(e);
		}
	}

	private String _invokeSInvoice(
		long groupId, Dossier dossier, ServiceContext context)
		throws JSONException {

		PaymentFile oldPaymentFile = PaymentFileLocalServiceUtil.getByDossierId(
			groupId, dossier.getDossierId());
		PaymentConfig orgPaymentConfig =
			PaymentConfigLocalServiceUtil.getPaymentConfigByGovAgencyCode(
				groupId, dossier.getGovAgencyCode());

		JSONObject sysPaymentConfig = JSONFactoryUtil.createJSONObject(
			orgPaymentConfig.getEpaymentConfig());

		JSONObject paymentConfig = JSONFactoryUtil.createJSONObject();
		JSONObject generalInvoiceInfo = JSONFactoryUtil.createJSONObject();
		generalInvoiceInfo.put(
			"invoiceType",
			sysPaymentConfig.get("generalInvoiceInfo-invoiceType"));
		generalInvoiceInfo.put(
			"templateCode",
			sysPaymentConfig.get("generalInvoiceInfo-templateCode"));
		generalInvoiceInfo.put(
			"invoiceSeries",
			sysPaymentConfig.get("generalInvoiceInfo-invoiceSeries"));
		generalInvoiceInfo.put(
			"currencyCode",
			sysPaymentConfig.get("generalInvoiceInfo-currencyCode"));
		generalInvoiceInfo.put(
			"invoiceNote",
			sysPaymentConfig.get("generalInvoiceInfo-invoiceNote"));
		generalInvoiceInfo.put(
			"adjustmentType",
			sysPaymentConfig.get("generalInvoiceInfo-adjustmentType"));
		generalInvoiceInfo.put(
			"paymentStatus",
			sysPaymentConfig.get("generalInvoiceInfo-paymentStatus"));
		generalInvoiceInfo.put(
			"paymentType", oldPaymentFile.getPaymentMethod());
		generalInvoiceInfo.put(
			"paymentTypeName", oldPaymentFile.getPaymentMethod());
		generalInvoiceInfo.put(
			"cusGetInvoiceRight",
			sysPaymentConfig.get("generalInvoiceInfo-cusGetInvoiceRight"));
		generalInvoiceInfo.put(
			"userName", sysPaymentConfig.get("generalInvoiceInfo-userName"));
		paymentConfig.put("generalInvoiceInfo", generalInvoiceInfo);

		JSONObject buyerInfo = JSONFactoryUtil.createJSONObject();
		buyerInfo.put("buyerName", dossier.getApplicantName());
		buyerInfo.put("buyerLegalName", dossier.getApplicantName());
		buyerInfo.put("buyerTaxCode", dossier.getApplicantIdNo());
		buyerInfo.put("buyerAddressLine", dossier.getAddress());
		buyerInfo.put("buyerPostalCode", dossier.getPostalCityCode());
		buyerInfo.put("buyerDistrictName", dossier.getDistrictName());
		buyerInfo.put("buyerCityName", dossier.getCityName());
		buyerInfo.put(
			"buyerCountryCode",
			sysPaymentConfig.get("buyerInfo-buyerCountryCode"));
		buyerInfo.put("buyerPhoneNumber", dossier.getContactTelNo());
		buyerInfo.put("buyerFaxNumber", "");
		buyerInfo.put("buyerEmail", dossier.getContactEmail());
		buyerInfo.put("buyerBankName", "");
		buyerInfo.put("buyerBankAccount", "");
		buyerInfo.put("buyerIdNo", dossier.getApplicantIdNo());
		buyerInfo.put("buyerIdType", "3");
		buyerInfo.put("buyerCode", dossier.getApplicantIdNo());
		buyerInfo.put("buyerBirthDay", "");
		paymentConfig.put("buyerInfo", buyerInfo);

		// TODO thong tin nhan vien ke toan ke toan tai buoc nay
		JSONObject sellerInfo = JSONFactoryUtil.createJSONObject();
		sellerInfo.put(
			"sellerCode", sysPaymentConfig.get("sellerInfo-sellerCode"));
		sellerInfo.put(
			"sellerLegalName",
			sysPaymentConfig.get("sellerInfo-sellerLegalName"));
		sellerInfo.put(
			"sellerTaxCode", sysPaymentConfig.get("sellerInfo-sellerTaxCode"));
		sellerInfo.put(
			"sellerAddressLine",
			sysPaymentConfig.get("sellerInfo-sellerAddressLine"));
		sellerInfo.put(
			"sellerPhoneNumber",
			sysPaymentConfig.get("sellerInfo-sellerPhoneNumber"));
		sellerInfo.put(
			"sellerEmail", sysPaymentConfig.get("sellerInfo-sellerEmail"));
		sellerInfo.put(
			"sellerBankName",
			sysPaymentConfig.get("sellerInfo-sellerBankName"));
		sellerInfo.put(
			"sellerBankAccount",
			sysPaymentConfig.get("sellerInfo-sellerBankAccount"));
		paymentConfig.put("sellerInfo", sellerInfo);

		JSONArray extAttribute = JSONFactoryUtil.createJSONArray();
		paymentConfig.put("extAttribute", extAttribute);

		JSONArray payments = JSONFactoryUtil.createJSONArray();
		JSONObject paymentItem = JSONFactoryUtil.createJSONObject();
		paymentItem.put("paymentMethodName", oldPaymentFile.getPaymentMethod());
		payments.put(paymentItem);
		paymentConfig.put("payments", payments);

		JSONObject deliveryInfo = JSONFactoryUtil.createJSONObject();
		paymentConfig.put("deliveryInfo", deliveryInfo);

		JSONArray itemInfo = JSONFactoryUtil.createJSONArray();
		JSONObject itemInfoItem = JSONFactoryUtil.createJSONObject();
		itemInfoItem.put("lineNumber", 1);
		itemInfoItem.put("itemCode", dossier.getServiceCode());
		itemInfoItem.put("itemName", oldPaymentFile.getPaymentFee());
		itemInfoItem.put("unitName", "");
		itemInfoItem.put("unitPrice", oldPaymentFile.getPaymentAmount());
		itemInfoItem.put("quantity", 1);
		itemInfoItem.put(
			"itemTotalAmountWithoutTax", oldPaymentFile.getPaymentAmount());
		itemInfoItem.put(
			"itemTotalAmountWithTax", oldPaymentFile.getPaymentAmount());
		itemInfoItem.put(
			"itemTotalAmountAfterDiscount", oldPaymentFile.getPaymentAmount());
		itemInfoItem.put("taxPercentage", 0);
		itemInfoItem.put("taxAmount", 0);
		itemInfoItem.put("discount", 0);
		itemInfoItem.put("itemDiscount", 0);
		itemInfoItem.put("itemNote", "");
		itemInfoItem.put("batchNo", "");
		itemInfoItem.put("expDate", "");
		itemInfo.put(itemInfoItem);
		paymentConfig.put("itemInfo", itemInfo);

		JSONArray discountItemInfo = JSONFactoryUtil.createJSONArray();
		paymentConfig.put("discountItemInfo", discountItemInfo);

		JSONObject summarizeInfo = JSONFactoryUtil.createJSONObject();
		summarizeInfo.put(
			"sumOfTotalLineAmountWithoutTax",
			oldPaymentFile.getPaymentAmount());
		summarizeInfo.put(
			"totalAmountWithoutTax", oldPaymentFile.getPaymentAmount());
		summarizeInfo.put("totalTaxAmount", oldPaymentFile.getPaymentAmount());
		summarizeInfo.put(
			"totalAmountWithTax", oldPaymentFile.getPaymentAmount());
		summarizeInfo.put(
			"totalAmountAfterDiscount", oldPaymentFile.getPaymentAmount());
		summarizeInfo.put(
			"totalAmountWithTaxInWords", oldPaymentFile.getPaymentAmount());
		summarizeInfo.put("discountAmount", oldPaymentFile.getPaymentAmount());
		paymentConfig.put("summarizeInfo", summarizeInfo);

		JSONArray taxBreakdowns = JSONFactoryUtil.createJSONArray();
		JSONObject taxBreakdown = JSONFactoryUtil.createJSONObject();
		taxBreakdown.put("taxPercentage", -2);
		taxBreakdown.put("taxableAmount", 15000);
		taxBreakdown.put("taxAmount", 0);
		taxBreakdowns.put(taxBreakdown);
		paymentConfig.put("taxBreakdowns", taxBreakdowns);

		JSONArray metadata = JSONFactoryUtil.createJSONArray();
		paymentConfig.put("metadata", metadata);

		JSONArray customFields = JSONFactoryUtil.createJSONArray();
		paymentConfig.put("customFields", customFields);

		JSONArray meterReading = JSONFactoryUtil.createJSONArray();
		paymentConfig.put("meterReading", meterReading);

		JSONObject invoiceFile = JSONFactoryUtil.createJSONObject();
		invoiceFile.put(
			"fileContent", sysPaymentConfig.get("invoiceFile-fileContent"));
		invoiceFile.put(
			"fileType", sysPaymentConfig.get("invoiceFile-fileType"));
		paymentConfig.put("invoiceFile", invoiceFile);

		String SINVOICEUrl = sysPaymentConfig.getString("server-sInvoiceUrl");
		String baseUrl = sysPaymentConfig.getString("server-baseUrl");

		InvokeREST callRest = new InvokeREST();
		HashMap<String, String> properties = new HashMap<String, String>();
		Map<String, Object> params = new HashMap<>();
		params.put("paymentConfig", paymentConfig.toString());

		JSONObject resultObj = callRest.callPostAPI(
			groupId, HttpMethod.POST, "application/json", baseUrl, SINVOICEUrl,
			sysPaymentConfig.getString("auth-username"),
			sysPaymentConfig.getString("auth-password"), properties, params,
			context);

		JSONObject eInvoice = JSONFactoryUtil.createJSONObject(
			resultObj.getString(RESTFulConfiguration.MESSAGE));

		eInvoice.put("sInvoiceUrl", sysPaymentConfig.get("server-sInvoiceClientUrl"));
		eInvoice.put("invoiceTemplateNo", sysPaymentConfig.get("generalInvoiceInfo-templateCode"));

		oldPaymentFile.setEinvoice(eInvoice.toString());
		PaymentFileLocalServiceUtil.updatePaymentFile(oldPaymentFile);

		return resultObj.toString();
	}
	
	public static String buildDeliverableSearchDataForm (String formDataKey) {

		String result = StringPool.BLANK;
		String searchFormat = MessageUtil.getMessage(SMSManagementConstants.SMS_SEARCH_DELIVERABLE);
		try {

			if (Validator.isNull(formDataKey)) {

				return result;
			}

			JSONObject formDataKeyObject = JSONFactoryUtil.createJSONObject(formDataKey);

			for (Iterator<String> iii = formDataKeyObject.keys(); iii.hasNext();) {
				
				String key = iii.next();
				String andQuery = String.format(searchFormat, key, formDataKeyObject.get(key));
				result += andQuery;
			}
		}
		catch (Exception e) {
			_log.info(e);
		}
//		System.out.println("========result==========" + result);
//		
//		String sdd = "KQ01-BGTVT-285799@1, KQ01-BGTVT-285800@2, KQ01-BGTVT-285800@3,KQ01-BGTVT-285799@3";
//		String d = 
//						StringUtil.split(sdd, "BGTVT-285800" + "@")[1].substring(0, 1);
//		System.out.println(d);
		
		return result;
	}

	static Log _log = LogFactoryUtil.getLog(SMSManagementImpl.class.getName());
}

class RESTFulConfiguration {

	public static final String STATUS = "status";
	public static final String MESSAGE = "message";

	public static final String SUBMIT = "submit";
	public static final String TIMER = "timer";

	public static final int TIME_OUT = 3000;

}
