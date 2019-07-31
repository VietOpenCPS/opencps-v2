package backend.api.rest.application.v21.impl;

import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.messaging.Message;
import com.liferay.portal.kernel.messaging.MessageBusException;
import com.liferay.portal.kernel.messaging.MessageBusUtil;
import com.liferay.portal.kernel.model.Company;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.service.GroupLocalServiceUtil;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.Validator;

import java.io.File;
import java.net.URLEncoder;
import java.util.Locale;

import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

import org.opencps.dossiermgt.model.DocumentType;
import org.opencps.dossiermgt.service.DocumentTypeLocalServiceUtil;
import org.opencps.rest.application.api.StatisticReportApi;

public class StatisticReportApiImpl implements StatisticReportApi {

	@Context
	private User user;

	@Context
	private Company company;

	@Context
	private HttpHeaders header;

	@Context
	HttpServletResponse response;
	
	ServiceContext serviceContext = new ServiceContext();

	private static Log _log = LogFactoryUtil.getLog(StatisticReportApiImpl.class);

	@Override
	public Object statisticReportPrint(User user, Company company, Locale locale, HttpHeaders httpHeaders,
			ServiceContext serviceContext, String code, String body) {
		_log.info("====START PRINT REPORT==== ");

		File file = null;

		long userId = user.getUserId();
		long groupId = GetterUtil.getLong(header.getHeaderString(Field.GROUP_ID));

		Group group = GroupLocalServiceUtil.fetchGroup(groupId);
		String siteName = group.getNameCurrentValue();
		serviceContext.setUserId(userId);

		DocumentType docType = DocumentTypeLocalServiceUtil.getByTypeCode(groupId, code);
		String documentScript;
		String reportType = "pdf";
		try {
			JSONObject bodyObj = JSONFactoryUtil.createJSONObject(body);
			if (bodyObj.has("reportType")) {
				reportType = bodyObj.getString("reportType");
			}
		} catch (JSONException e) {
			_log.debug(e);
			_log.error("Report Type JSONException");
		}
		
		if (docType != null) {

			documentScript = docType.getDocumentScript();

			Message message = new Message();
			message.put("formReport", documentScript);
			message.put("reportType", reportType);

			JSONObject resultObject = doGetFormData(code, body, siteName);
			
			message.put("formData", resultObject);
			
			try {
				String previewResponse = (String) MessageBusUtil
						.sendSynchronousMessage("jasper/engine/preview/destination", message, 10000);

				if (Validator.isNotNull(previewResponse)) {
					file = new File(previewResponse);

					ResponseBuilder responseBuilder = Response.ok((Object) file);
					String rootFileName = docType.getDocumentName();
					try {
						rootFileName = URLEncoder.encode(docType.getDocumentName(), "UTF-8");
					}
					catch (Exception e) {
						_log.debug(e);
					}
					if ("excel".equals(reportType)) {
						responseBuilder.header("Content-Disposition",
								"attachment; filename=\"" + rootFileName + ".xls\"");
//						responseBuilder.header("Content-Type", "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");			
						responseBuilder.header("Content-Type", "application/vnd.ms-excel");	
//						responseBuilder.header("Content-Transfer-Encoding", "binary");	
					}
					else if ("word".equals(reportType)) {
						responseBuilder.header("Content-Disposition",
								"attachment; filename=\"" + rootFileName + ".doc\"");
						responseBuilder.header("Content-Type", "application/msword");	
//						responseBuilder.header("Content-Type", "application/octet-stream");
//						responseBuilder.header("Content-Transfer-Encoding", "binary");
					}
					else {
						responseBuilder.header("Content-Disposition",
								"attachment; filename=\"" + rootFileName + ".pdf\"");
						responseBuilder.header("Content-Type", "application/pdf");						
					}
					return responseBuilder.build();
				}
				_log.info("====END PRINT REPORT==== ");
			} catch (MessageBusException e) {
				_log.debug(e);
				_log.error("====PRINT REPORT ERROR==== ");
				return Response.status(HttpServletResponse.SC_CONFLICT).build();
			}

		}

		return Response.status(HttpServletResponse.SC_NOT_FOUND).build();
	}

	private JSONObject doGetFormData(String code, String body, String siteName) {
		_log.info("====START GET FORM DATA==== ");
		JSONObject result = JSONFactoryUtil.createJSONObject();

		switch (code) {
		case "REPORT_01":
			result = getFormDataReport01(body, siteName);
			break;
		case "REPORT_02":
			result = getFormDataReportTongHop(body, siteName);
			break;
		case "REPORT_03":
			result = getFormDataReportDetail(body, siteName);
			break;
		case "REPORT_04":
			result = getFormDataReportTongHop(body, siteName);
			break;
		case "REPORT_05":
			result = getFormDataReportDetail(body, siteName);
			break;
		case "REPORT_06":
			result = getFormDataReportTongHop(body, siteName);
			break;
		case "REPORT_07":
			// result = getFormDataReport07(body);
			break;
		case "REPORT_08":
			// result = getFormDataReport08(body);
			break;
		case "REPORT_09":
			result = getFormDataReportDetail(body, siteName);
			break;
		case "REPORT_10":
			result = getFormDataReportDetail(body, siteName);
			break;
		default:
			result = JSONFactoryUtil.createJSONObject();
		}
		_log.info("====END GET FORM DATA==== ");
		return result;
	}

	

	private JSONObject getFormDataReportTongHop(String body, String siteName) {
		// TODO Auto-generated method stub
		return null;
	}

	private JSONObject getFormDataReportDetail(String body, String siteName) {
		_log.info("====START GET DATA REPORT DETAIL==== ");
		JSONObject result = JSONFactoryUtil.createJSONObject();
		try {
			JSONObject resultBody = JSONFactoryUtil.createJSONObject(body);

			result.put("year", resultBody.getInt("year"));
			result.put("month", resultBody.getInt("month"));
			result.put("govAgencyName", siteName);

			result.put("fromDate", resultBody.getString("fromDate"));
			result.put("toDate", resultBody.getString("toDate"));
			result.put("actionUser", "");

			JSONArray dossierData = resultBody.getJSONArray("data");
			JSONObject currentObject = null;

			JSONObject domainRaw = JSONFactoryUtil.createJSONObject();
			JSONObject dossierRaw = JSONFactoryUtil.createJSONObject();

			for (int i = 0; i < dossierData.length(); i++) {
				currentObject = dossierData.getJSONObject(i);

				JSONObject domainRawItem = JSONFactoryUtil.createJSONObject();

				domainRawItem.put("domainName", currentObject.getString("domainName"));
				domainRawItem.put("services", JSONFactoryUtil.createJSONArray());

				if (Validator.isNull(domainRaw.getJSONObject(currentObject.getString("domainName")))) {
					domainRaw.put(currentObject.getString("domainName"), domainRawItem);
				}

				if (Validator.isNotNull(dossierRaw.getJSONObject(currentObject.getString("serviceCode")))) {
					if (dossierRaw.getJSONObject(currentObject.getString("serviceCode")).getString("serviceCode")
							.equalsIgnoreCase(currentObject.getString("serviceCode"))) {
						dossierRaw.getJSONObject(currentObject.getString("serviceCode")).getJSONArray("dossiers")
								.put(currentObject);
					}
				} else {
					JSONObject dossierRawItem = JSONFactoryUtil.createJSONObject();

					dossierRawItem.put("serviceCode", currentObject.getString("serviceCode"));
					dossierRawItem.put("serviceName", currentObject.getString("serviceName"));
					dossierRawItem.put("domainName", currentObject.getString("domainName"));
					dossierRawItem.put("dossiers", JSONFactoryUtil.createJSONArray());

					dossierRaw.put(currentObject.getString("serviceCode"), dossierRawItem);
					dossierRaw.getJSONObject(currentObject.getString("serviceCode")).getJSONArray("dossiers")
							.put(currentObject);
				}

			}

			JSONArray keys = dossierRaw.names();

			for (int i = 0; i < keys.length(); ++i) {

				String key = keys.getString(i);

				JSONObject keyObject = dossierRaw.getJSONObject(key);
				if (Validator.isNotNull(keyObject)) {
					if (Validator.isNotNull(domainRaw.getString(keyObject.getString("domainName")))
							&& domainRaw.getJSONObject(keyObject.getString("domainName")).getString("domainName")
									.equalsIgnoreCase(keyObject.getString("domainName"))) {
						domainRaw.getJSONObject(keyObject.getString("domainName")).getJSONArray("services")
								.put(keyObject);
					}
				}

			}

			JSONArray domains = JSONFactoryUtil.createJSONArray();

			keys = domainRaw.names();

			for (int i = 0; i < keys.length(); ++i) {

				String key = keys.getString(i);

				JSONObject keyObject = domainRaw.getJSONObject(key);

				if (Validator.isNotNull(key)) {

					JSONObject domainRawItem = JSONFactoryUtil.createJSONObject();

					domainRawItem.put("domainName", key);
					domainRawItem.put("services", keyObject.getJSONArray("services"));

					domains.put(domainRawItem);
				}

			}
			_log.info("====END GET DATA REPORT DETAIL==== ");
			result.put("domains", domains);
		} catch (JSONException e) {
			_log.error(e);
		}
		
		return result;
	}

	private JSONObject getFormDataReport01(String body, String siteName) {
		_log.info("====START GET DATA REPORT 01==== ");
		JSONObject result = JSONFactoryUtil.createJSONObject();
		try {
			JSONObject resultBody = JSONFactoryUtil.createJSONObject(body);
			//
			int year = resultBody.getInt("year");
			int month = resultBody.getInt("month");
			String fromStatisticDate = resultBody.getString("fromStatisticDate");
			String toStatisticDate = resultBody.getString("toStatisticDate");
			if (year > 0 || month > 0) {
				result.put("year", year);
				result.put("month", month);
			} else if (Validator.isNotNull(fromStatisticDate) || Validator.isNotNull(toStatisticDate)){
				result.put("fromStatisticDate", fromStatisticDate);
				result.put("toStatisticDate", toStatisticDate);
			}

			result.put("govAgencyName", siteName);
			//Process statistic all agency
			int flagAgency = resultBody.getInt("flagAgency");
			result.put("flagAgency", flagAgency);
			JSONArray statistics = resultBody.getJSONArray("data");
			JSONArray statisticsData = JSONFactoryUtil.createJSONArray();
			
			if (statistics.length() > 0) {
				result.put("nodata", 1);
			}

			JSONObject currentObject = null;
			if (flagAgency > 0) {
				for (int i = 0; i < statistics.length(); i++) {
					currentObject = statistics.getJSONObject(i);
					if (Validator.isNull(currentObject.getString("domainName")) && Validator.isNull(currentObject.getString("govAgencyName"))) {
						result.put("total_3", currentObject.getInt("totalCount"));
						result.put("total_4", currentObject.getInt("deniedCount"));
						result.put("total_5", currentObject.getInt("cancelledCount"));
						result.put("total_6", currentObject.getInt("processCount"));
						result.put("total_7", currentObject.getInt("remainingCount"));
						result.put("total_8", currentObject.getInt("receivedCount"));
						result.put("total_9", currentObject.getInt("onegateCount"));
						result.put("total_10", currentObject.getInt("onlineCount"));
						result.put("total_11", currentObject.getInt("releaseCount"));
						result.put("total_12", currentObject.getInt("betimesCount"));
						result.put("total_13", currentObject.getInt("ontimeCount"));
						result.put("total_14", currentObject.getInt("overtimeCount"));
						result.put("total_15", currentObject.getInt("overtimeInside"));
						result.put("total_16", currentObject.getInt("overtimeOutside"));
						result.put("total_17", currentObject.getInt("doneCount"));
						result.put("total_18", currentObject.getInt("releasingCount"));
						result.put("total_19", currentObject.getInt("unresolvedCount"));
						result.put("total_20", currentObject.getInt("processingCount"));
						result.put("total_21", currentObject.getInt("undueCount"));
						result.put("total_22", currentObject.getInt("overdueCount"));
						result.put("total_23", currentObject.getInt("outsideCount"));
						result.put("total_24", currentObject.getInt("waitingCount"));
						result.put("total_25", currentObject.getInt("ontimePercentage"));
		
					} else if (Validator.isNull(currentObject.getString("domainName")) && Validator.isNotNull(currentObject.getString("govAgencyName"))) {
						statisticsData.put(currentObject);
					}
				}
			} else {
				for (int i = 0; i < statistics.length(); i++) {
					currentObject = statistics.getJSONObject(i);
					if (Validator.isNull(currentObject.getString("domainName")) && Validator.isNotNull(currentObject.getString("govAgencyName"))) {
//						result.put("total_3", currentObject.getInt("totalCount"));
//						result.put("total_4", currentObject.getInt("deniedCount"));
//						result.put("total_5", currentObject.getInt("cancelledCount"));
//						result.put("total_6", currentObject.getInt("processCount"));
//						result.put("total_7", currentObject.getInt("remainingCount"));
//						result.put("total_8", currentObject.getInt("receivedCount"));
//						result.put("total_9", currentObject.getInt("onegateCount"));
//						result.put("total_10", currentObject.getInt("onlineCount"));
//						result.put("total_11", currentObject.getInt("releaseCount"));
//						result.put("total_12", currentObject.getInt("betimesCount"));
//						result.put("total_13", currentObject.getInt("ontimeCount"));
//						result.put("total_14", currentObject.getInt("overtimeCount"));
//						result.put("total_15", currentObject.getInt("overtimeInside"));
//						result.put("total_16", currentObject.getInt("overtimeOutside"));
//						result.put("total_17", currentObject.getInt("ontimePercentage"));
//						result.put("total_18", currentObject.getInt("doneCount"));
//						result.put("total_19", currentObject.getInt("releasingCount"));
//						result.put("total_20", currentObject.getInt("unresolvedCount"));
//						result.put("total_21", currentObject.getInt("processingCount"));
//						result.put("total_22", currentObject.getInt("undueCount"));
//						result.put("total_23", currentObject.getInt("overdueCount"));
//						result.put("total_24", currentObject.getInt("waitingCount"));
						result.put("total_3", currentObject.getInt("totalCount"));
						result.put("total_4", currentObject.getInt("deniedCount"));
						result.put("total_5", currentObject.getInt("cancelledCount"));
						result.put("total_6", currentObject.getInt("processCount"));
						result.put("total_7", currentObject.getInt("remainingCount"));
						result.put("total_8", currentObject.getInt("receivedCount"));
						result.put("total_9", currentObject.getInt("onegateCount"));
						result.put("total_10", currentObject.getInt("onlineCount"));
						result.put("total_11", currentObject.getInt("releaseCount"));
						result.put("total_12", currentObject.getInt("betimesCount"));
						result.put("total_13", currentObject.getInt("ontimeCount"));
						result.put("total_14", currentObject.getInt("overtimeCount"));
						result.put("total_15", currentObject.getInt("overtimeInside"));
						result.put("total_16", currentObject.getInt("overtimeOutside"));
						result.put("total_17", currentObject.getInt("doneCount"));
						result.put("total_18", currentObject.getInt("releasingCount"));
						result.put("total_19", currentObject.getInt("unresolvedCount"));
						result.put("total_20", currentObject.getInt("processingCount"));
						result.put("total_21", currentObject.getInt("undueCount"));
						result.put("total_22", currentObject.getInt("overdueCount"));
						result.put("total_23", currentObject.getInt("outsideCount"));
						result.put("total_24", currentObject.getInt("waitingCount"));
						result.put("total_25", currentObject.getInt("ontimePercentage"));
		
					} else if (Validator.isNotNull(currentObject.getString("domainName")) && Validator.isNotNull(currentObject.getString("govAgencyName"))) {
						statisticsData.put(currentObject);
					}
				}
			}
			_log.info("====END GET DATA REPORT 01==== ");
			result.put("statistics", statisticsData);

		} catch (JSONException e) {
			_log.error("JSONException: "+e);
		}
		return result;
	}

}
