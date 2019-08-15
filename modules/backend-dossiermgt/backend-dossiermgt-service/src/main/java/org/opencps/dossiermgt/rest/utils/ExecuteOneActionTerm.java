
package org.opencps.dossiermgt.rest.utils;

import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.HttpMethod;

import org.opencps.dossiermgt.model.Dossier;
import org.opencps.dossiermgt.model.PaymentConfig;
import org.opencps.dossiermgt.model.PaymentFile;
import org.opencps.dossiermgt.scheduler.InvokeREST;
import org.opencps.dossiermgt.service.PaymentConfigLocalServiceUtil;
import org.opencps.dossiermgt.service.PaymentFileLocalServiceUtil;

import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.service.ServiceContext;

public class ExecuteOneActionTerm {

	public static final String ACTION_CODE = "actionCode";
	public static final String ACTION_USER = "actionUser";
	public static final String ACTION_NOTE = "actionNote";
	public static final String PAYLOAD = "payload";
	public static final String ASSIGN_USERS = "assignUsers";

	public static String invokeSInvoice(
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
		summarizeInfo.put("totalAmountWithTaxInWords", oldPaymentFile.getPaymentAmount());
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

		String serverConfigSInvoice = "SERVER_SINVOICE";
		String SINVOICEUrl =
			"postal/invoice/sinvoice/" + serverConfigSInvoice + "/create";
		InvokeREST callRest = new InvokeREST();
		String baseUrl = "http://localhost:8080/o/rest/v2/";
		HashMap<String, String> properties = new HashMap<String, String>();
		Map<String, Object> params = new HashMap<>();
		params.put("paymentConfig", paymentConfig.toString());

		JSONObject resultObj = callRest.callPostAPI(
			groupId, HttpMethod.POST, "application/json", baseUrl, SINVOICEUrl,
			sysPaymentConfig.getString("auth-username"),
			sysPaymentConfig.getString("auth-password"), properties, params,
			context);

		oldPaymentFile.setEinvoice(resultObj.toString());
		PaymentFileLocalServiceUtil.updatePaymentFile(oldPaymentFile);

		return resultObj.toString();
	}
}
