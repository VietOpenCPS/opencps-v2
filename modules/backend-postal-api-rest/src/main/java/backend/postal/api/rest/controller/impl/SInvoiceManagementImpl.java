package backend.postal.api.rest.controller.impl;

import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Company;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.Base64;
import com.liferay.portal.kernel.util.GetterUtil;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;

import org.opencps.api.sinvoice.builder.RequestBodyBuilderImpl;
import org.opencps.api.sinvoice.model.InvokeResultModel;
import org.opencps.api.sinvoice.model.SearchInputModel;
import org.opencps.communication.model.ServerConfig;
import org.opencps.communication.service.ServerConfigLocalServiceUtil;

import backend.postal.api.rest.controller.SInvoiceManagement;

/**
 * @author trungnt
 *
 */
public class SInvoiceManagementImpl implements SInvoiceManagement {

	private static final Log _log = LogFactoryUtil.getLog(SInvoiceManagementImpl.class);

	private InvokeResultModel doInvoke(HttpServletRequest request, HttpHeaders header, Company company, Locale locale,
			User user, ServiceContext serviceContext, String code, JSONObject requestBody, String cmd) {

		long groupId = GetterUtil.getLong(header.getHeaderString("groupId"));

		InvokeResultModel invokeResultModel = new InvokeResultModel();

		ServerConfig serverConfig = ServerConfigLocalServiceUtil.getByCode(groupId, code);

		if (serverConfig == null) {
			invokeResultModel.setStatus(HttpURLConnection.HTTP_INTERNAL_ERROR);
			invokeResultModel.setErrorCode("NULL_SERVECE_CONFIG");
			invokeResultModel.setDescription("Not found server config with code = " + code);
			return invokeResultModel;
		}

		try {
			JSONObject config = JSONFactoryUtil.createJSONObject(serverConfig.getConfigs());
			String userName = config.getString("userName");
			String password = config.getString("password");
			JSONObject endpointConfig = config.getJSONObject(cmd);
			String endpoint = endpointConfig.getString("endpoint");
			String method = endpointConfig.getString("method");
			String accept = endpointConfig.getString("accept");
			String contentType = endpointConfig.getString("contentType");

			JSONObject inputSchema = endpointConfig.getJSONObject("inputSchema");

			System.out.println(inputSchema);

			if ("createInvoice".equals(cmd)) {
				// fix tam, do chua mapping dc cac truong trong dossier voi sinvoice
				requestBody = inputSchema;
			}

			// TODO repalace inputSchema = requestData
			return invokeData(endpoint, userName, password, method, accept, contentType, requestBody);

		} catch (Exception e) {
			_log.error(e);
			invokeResultModel = new InvokeResultModel();
			invokeResultModel.setStatus(HttpURLConnection.HTTP_INTERNAL_ERROR);
			invokeResultModel.setErrorCode("NOT_ACCEPT_IP_ADDRESS_OR_CONNECTED_ERROR");
			invokeResultModel.setDescription("Not accept ip address or connected error");
			return invokeResultModel;
		}
	}

	@Override
	public Response getCreateInvoice(HttpServletRequest request, HttpHeaders header, Company company, Locale locale,
			User user, ServiceContext serviceContext, String code, String dossier, String paymentConfig) {

		RequestBodyBuilderImpl builder = new RequestBodyBuilderImpl();

		// TODO maping requestBody with dossier, paymentConfig

		JSONObject requestBody = builder.generalInvoiceInfo(null).sellerInfo(null).buyerInfo(null).build().getData();

		InvokeResultModel invokeResultModel = doInvoke(request, header, company, locale, user, serviceContext, code,
				requestBody, "createInvoice");

		if (invokeResultModel != null) {

			return Response.status(invokeResultModel.getStatus())
					.entity(JSONFactoryUtil.looseSerialize(invokeResultModel)).build();
		}
		invokeResultModel = new InvokeResultModel();
		invokeResultModel.setStatus(HttpURLConnection.HTTP_INTERNAL_ERROR);
		invokeResultModel.setErrorCode("NOT_GET_RESPONSE_DATA");
		invokeResultModel.setDescription("Not get response data");
		return Response.status(HttpURLConnection.HTTP_INTERNAL_ERROR)
				.entity(JSONFactoryUtil.looseSerialize(invokeResultModel)).build();

	}

	@Override
	public Response getInvoiceFile(HttpServletRequest request, HttpHeaders header, Company company, Locale locale,
			User user, ServiceContext serviceContext, String code, String supplierTaxCode, String invoiceNo,
			String templateCode, String transactionUuid, String fileType, Boolean paid) {

		JSONObject requestBody = JSONFactoryUtil.createJSONObject();

		requestBody.put("supplierTaxCode", supplierTaxCode);
		requestBody.put("invoiceNo", invoiceNo);
		requestBody.put("transactionUuid", transactionUuid);
		requestBody.put("fileType", fileType);
		requestBody.put("templateCode", templateCode);
		requestBody.put("paid", paid);

		InvokeResultModel invokeResultModel = doInvoke(request, header, company, locale, user, serviceContext, code,
				requestBody, "getInvoiceFile");

		if (invokeResultModel != null) {

			return Response.status(invokeResultModel.getStatus())
					.entity(JSONFactoryUtil.looseSerialize(invokeResultModel)).build();
		}
		invokeResultModel = new InvokeResultModel();
		invokeResultModel.setStatus(HttpURLConnection.HTTP_INTERNAL_ERROR);
		invokeResultModel.setErrorCode("NOT_GET_RESPONSE_DATA");
		invokeResultModel.setDescription("Not get response data");

		return Response.status(HttpURLConnection.HTTP_INTERNAL_ERROR)
				.entity(JSONFactoryUtil.looseSerialize(invokeResultModel)).build();
	}

	private InvokeResultModel invokeData(String endpoint, String userName, String password, String method,
			String accept, String contentType, JSONObject inputData) throws IOException {

		HttpURLConnection conn = null;

		InvokeResultModel resultModel = null;

		try {

			StringBuffer params = new StringBuffer();
			Iterator<String> keys = inputData.keys();
			while (keys.hasNext()) {
				String key = keys.next();
				Object value = inputData.get(key);
				params.append(key + "=");
				params.append(URLEncoder.encode(value.toString(), "UTF-8"));
				params.append("&");
			}

			if ("GET".equals(method)) {
				// endpoint = endpoint + "?" + URLEncoder.encode(params.toString(), "UTF-8");
				endpoint = endpoint + "?" + params.toString();
			}

			System.out.println("endpoint: " + endpoint);

			URL url = new URL(endpoint);

			conn = (HttpURLConnection) url.openConnection();
			conn.setRequestProperty("Authorization", "Basic " + Base64.encode((userName + ":" + password).getBytes()));
			conn.setRequestMethod(method);
			conn.setDoInput(true);
			conn.setDoOutput(true);
			conn.setRequestProperty("Accept", accept);
			conn.setRequestProperty("Content-Type", contentType);

			if ("POST".equals(method)) {
				if ("application/json".equals(contentType)) {
					// body json
					try (OutputStream os = conn.getOutputStream()) {
						byte[] input = inputData.toJSONString().getBytes("utf-8");
						os.write(input, 0, input.length);
						os.flush();
					}

				} else if ("application/x-www-form-urlencoded".equals(contentType)) {

					try (OutputStream os = conn.getOutputStream()) {

						byte[] input = params.toString().getBytes(StandardCharsets.UTF_8);
						os.write(input, 0, input.length);
						os.flush();
					}
				}
			}

			conn.setReadTimeout(60 * 1000);
			conn.connect();

			try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader((conn.getInputStream())))) {

				String output = StringPool.BLANK;

				StringBuilder sb = new StringBuilder();

				while ((output = bufferedReader.readLine()) != null) {
					sb.append(output);
				}

				System.out.println("response: " + sb.toString());

				int responseCode = conn.getResponseCode();

				resultModel = new InvokeResultModel();

				resultModel.setStatus(responseCode);

				JSONObject resultData = JSONFactoryUtil.createJSONObject(sb.toString());

				if (resultData.has("errorCode")) {
					resultModel.setErrorCode(resultData.getString("errorCode"));
				}

				if (resultData.has("description")) {
					resultModel.setDescription(resultData.getString("description"));
				}

				if (resultData.has("result")) {
					JSONObject result = resultData.getJSONObject("result");

					if (result != null) {
						if (result.has("supplierTaxCode")) {
							resultModel.setSupplierTaxCode(result.getString("supplierTaxCode"));
						}

						if (result.has("invoiceNo")) {
							resultModel.setInvoiceNo(result.getString("invoiceNo"));
						}

						if (result.has("transactionID")) {
							resultModel.setTransactionID(result.getString("transactionID"));
						}

						if (result.has("reservationCode")) {
							resultModel.setReservationCode(result.getString("reservationCode"));
						}
					}
				}

				if (resultData.has("fileName")) {
					resultModel.setFileName(resultData.getString("fileName"));
				}

				if (resultData.has("fileToBytes")) {
					resultModel.setFileToBytes(resultData.getString("fileToBytes"));
				}

				if (resultData.has("invoices")) {
					JSONArray invoices = resultData.getJSONArray("invoices");
					resultModel.setInvoices(invoices);
				}

			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
			_log.error(e);
		} finally {
			if (conn != null) {
				conn.disconnect();
			}
		}

		return resultModel;
	}

	@Override
	public Response getInvoiceFile(HttpServletRequest request, HttpHeaders header, Company company, Locale locale,
			User user, ServiceContext serviceContext, String code, String supplierTaxCode, String invoiceNo,
			String templateCode, String strIssueDate, String buyerIdNo, String reservationCode, String fileType) {

		JSONObject requestBody = JSONFactoryUtil.createJSONObject();

		requestBody.put("supplierTaxCode", supplierTaxCode);
		requestBody.put("invoiceNo", invoiceNo);
		requestBody.put("buyerIdNo", buyerIdNo);
		requestBody.put("fileType", fileType);
		requestBody.put("templateCode", templateCode);
		requestBody.put("reservationCode", reservationCode);
		requestBody.put("strIssueDate", strIssueDate);

		InvokeResultModel invokeResultModel = doInvoke(request, header, company, locale, user, serviceContext, code,
				requestBody, "getInvoiceFilePortal");

		if (invokeResultModel != null) {

			return Response.status(invokeResultModel.getStatus())
					.entity(JSONFactoryUtil.looseSerialize(invokeResultModel)).build();
		}
		invokeResultModel = new InvokeResultModel();
		invokeResultModel.setStatus(HttpURLConnection.HTTP_INTERNAL_ERROR);
		invokeResultModel.setErrorCode("NOT_GET_RESPONSE_DATA");
		invokeResultModel.setDescription("Not get response data");

		return Response.status(HttpURLConnection.HTTP_INTERNAL_ERROR)
				.entity(JSONFactoryUtil.looseSerialize(invokeResultModel)).build();
	}

	@Override
	public Response createExchangeInvoiceFile(HttpServletRequest request, HttpHeaders header, Company company,
			Locale locale, User user, ServiceContext serviceContext, String code, String supplierTaxCode,
			String invoiceNo, String templateCode, String strIssueDate, String exchangeUser) {

		JSONObject requestBody = JSONFactoryUtil.createJSONObject();

		requestBody.put("supplierTaxCode", supplierTaxCode);
		requestBody.put("invoiceNo", invoiceNo);
		requestBody.put("exchangeUser", exchangeUser);
		requestBody.put("templateCode", templateCode);
		requestBody.put("strIssueDate", strIssueDate);

		InvokeResultModel invokeResultModel = doInvoke(request, header, company, locale, user, serviceContext, code,
				requestBody, "createExchangeInvoiceFile");

		if (invokeResultModel != null) {

			return Response.status(invokeResultModel.getStatus())
					.entity(JSONFactoryUtil.looseSerialize(invokeResultModel)).build();
		}
		invokeResultModel = new InvokeResultModel();
		invokeResultModel.setStatus(HttpURLConnection.HTTP_INTERNAL_ERROR);
		invokeResultModel.setErrorCode("NOT_GET_RESPONSE_DATA");
		invokeResultModel.setDescription("Not get response data");

		return Response.status(HttpURLConnection.HTTP_INTERNAL_ERROR)
				.entity(JSONFactoryUtil.looseSerialize(invokeResultModel)).build();
	}

	@Override
	public Response cancelTransactionInvoice(HttpServletRequest request, HttpHeaders header, Company company,
			Locale locale, User user, ServiceContext serviceContext, String code, String supplierTaxCode,
			String invoiceNo, String templateCode, String strIssueDate, String additionalReferenceDesc,
			String additionalReferenceDate) {

		JSONObject requestBody = JSONFactoryUtil.createJSONObject();

		requestBody.put("supplierTaxCode", supplierTaxCode);
		requestBody.put("invoiceNo", invoiceNo);
		requestBody.put("additionalReferenceDesc", additionalReferenceDesc);
		requestBody.put("additionalReferenceDate", additionalReferenceDate);
		requestBody.put("templateCode", templateCode);
		requestBody.put("strIssueDate", strIssueDate);

		InvokeResultModel invokeResultModel = doInvoke(request, header, company, locale, user, serviceContext, code,
				requestBody, "cancelTransactionInvoice");

		if (invokeResultModel != null) {

			return Response.status(invokeResultModel.getStatus())
					.entity(JSONFactoryUtil.looseSerialize(invokeResultModel)).build();
		}
		invokeResultModel = new InvokeResultModel();
		invokeResultModel.setStatus(HttpURLConnection.HTTP_INTERNAL_ERROR);
		invokeResultModel.setErrorCode("NOT_GET_RESPONSE_DATA");
		invokeResultModel.setDescription("Not get response data");

		return Response.status(HttpURLConnection.HTTP_INTERNAL_ERROR)
				.entity(JSONFactoryUtil.looseSerialize(invokeResultModel)).build();
	}

	@Override
	public Response updatePaymentStatus(HttpServletRequest request, HttpHeaders header, Company company, Locale locale,
			User user, ServiceContext serviceContext, String code, String supplierTaxCode, String invoiceNo,
			String templateCode, String strIssueDate, String buyerEmailAddress, String paymentType,
			String paymentTypeName, boolean cusGetInvoiceRight) {

		JSONObject requestBody = JSONFactoryUtil.createJSONObject();

		requestBody.put("supplierTaxCode", supplierTaxCode);
		requestBody.put("invoiceNo", invoiceNo);
		requestBody.put("templateCode", templateCode);
		requestBody.put("buyerEmailAddress", buyerEmailAddress);
		requestBody.put("paymentType", paymentType);
		requestBody.put("paymentTypeName", paymentTypeName);
		requestBody.put("strIssueDate", strIssueDate);
		requestBody.put("cusGetInvoiceRight", cusGetInvoiceRight);

		InvokeResultModel invokeResultModel = doInvoke(request, header, company, locale, user, serviceContext, code,
				requestBody, "updatePaymentStatus");

		if (invokeResultModel != null) {

			return Response.status(invokeResultModel.getStatus())
					.entity(JSONFactoryUtil.looseSerialize(invokeResultModel)).build();
		}
		invokeResultModel = new InvokeResultModel();
		invokeResultModel.setStatus(HttpURLConnection.HTTP_INTERNAL_ERROR);
		invokeResultModel.setErrorCode("NOT_GET_RESPONSE_DATA");
		invokeResultModel.setDescription("Not get response data");

		return Response.status(HttpURLConnection.HTTP_INTERNAL_ERROR)
				.entity(JSONFactoryUtil.looseSerialize(invokeResultModel)).build();
	}

	@Override
	public Response cancelPaymentStatus(HttpServletRequest request, HttpHeaders header, Company company, Locale locale,
			User user, ServiceContext serviceContext, String code, String supplierTaxCode, String invoiceNo,
			String strIssueDate) {
		JSONObject requestBody = JSONFactoryUtil.createJSONObject();

		requestBody.put("supplierTaxCode", supplierTaxCode);
		requestBody.put("invoiceNo", invoiceNo);

		requestBody.put("strIssueDate", strIssueDate);

		InvokeResultModel invokeResultModel = doInvoke(request, header, company, locale, user, serviceContext, code,
				requestBody, "cancelPaymentStatus");

		if (invokeResultModel != null) {

			return Response.status(invokeResultModel.getStatus())
					.entity(JSONFactoryUtil.looseSerialize(invokeResultModel)).build();
		}
		invokeResultModel = new InvokeResultModel();
		invokeResultModel.setStatus(HttpURLConnection.HTTP_INTERNAL_ERROR);
		invokeResultModel.setErrorCode("NOT_GET_RESPONSE_DATA");
		invokeResultModel.setDescription("Not get response data");

		return Response.status(HttpURLConnection.HTTP_INTERNAL_ERROR)
				.entity(JSONFactoryUtil.looseSerialize(invokeResultModel)).build();
	}

	@Override
	public Response search(HttpServletRequest request, HttpHeaders header, Company company, Locale locale, User user,
			ServiceContext serviceContext, String code, SearchInputModel body) {
		InvokeResultModel invokeResultModel = null;
		try {
			invokeResultModel = doInvoke(request, header, company, locale, user, serviceContext, code,
					JSONFactoryUtil.createJSONObject(JSONFactoryUtil.looseSerialize(body)), "getInvoices");

			if (invokeResultModel != null) {

				return Response.status(invokeResultModel.getStatus())
						.entity(JSONFactoryUtil.looseSerialize(invokeResultModel)).build();
			}
			invokeResultModel = new InvokeResultModel();
			invokeResultModel.setStatus(HttpURLConnection.HTTP_INTERNAL_ERROR);
			invokeResultModel.setErrorCode("NOT_GET_RESPONSE_DATA");
			invokeResultModel.setDescription("Not get response data");

			return Response.status(HttpURLConnection.HTTP_INTERNAL_ERROR)
					.entity(JSONFactoryUtil.looseSerialize(invokeResultModel)).build();
		} catch (Exception e) {
			_log.error(e);
			invokeResultModel = new InvokeResultModel();
			invokeResultModel.setStatus(HttpURLConnection.HTTP_INTERNAL_ERROR);
			invokeResultModel.setErrorCode("NOT_GET_RESPONSE_DATA");
			invokeResultModel.setDescription("Not get response data");

			return Response.status(HttpURLConnection.HTTP_INTERNAL_ERROR)
					.entity(JSONFactoryUtil.looseSerialize(invokeResultModel)).build();
		}

	}

}
