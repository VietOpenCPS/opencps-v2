package backend.api.rest.application.v21.impl;

import com.liferay.petra.string.StringPool;
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

import org.opencps.dossiermgt.action.util.ConstantUtils;
import org.opencps.dossiermgt.action.util.ReadFilePropertiesUtils;
import org.opencps.dossiermgt.model.DocumentType;
import org.opencps.dossiermgt.service.DocumentTypeLocalServiceUtil;
import org.opencps.rest.application.api.StatisticReportApi;

import backend.api.rest.application.utils.ConstantTerm;

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
		String reportType = ConstantTerm.PDF_TYPE;
		try {
			JSONObject bodyObj = JSONFactoryUtil.createJSONObject(body);
			if (bodyObj.has(ConstantTerm.JSON_REPORT_TYPE)) {
				reportType = bodyObj.getString(ConstantTerm.JSON_REPORT_TYPE);
			}
		} catch (JSONException e) {
			_log.debug(e);
			_log.error("Report Type JSONException");
		}
		
		if (docType != null) {

			documentScript = docType.getDocumentScript();

			Message message = new Message();
			message.put(ConstantTerm.JSON_FORM_REPORT, documentScript);
			message.put(ConstantTerm.JSON_REPORT_TYPE, reportType);

			JSONObject resultObject = doGetFormData(code, body, siteName);
			
			message.put(ConstantTerm.JSON_FORM_DATA, resultObject);
			
			try {
				String previewResponse = (String) MessageBusUtil
						.sendSynchronousMessage(ConstantTerm.JASPER_ENGINE_PREVIEW_DESTINATION, message, 10000);

				if (Validator.isNotNull(previewResponse)) {
					file = new File(previewResponse);

					ResponseBuilder responseBuilder = Response.ok((Object) file);
					String rootFileName = docType.getDocumentName();
					try {
						rootFileName = URLEncoder.encode(docType.getDocumentName(), ConstantTerm.UTF_8);
					}
					catch (Exception e) {
						_log.debug(e);
					}
					if ("excel".equals(reportType)) {
						responseBuilder.header(ReadFilePropertiesUtils.get(ConstantUtils.TYPE_DISPOSITON),
								ReadFilePropertiesUtils.get(ConstantUtils.VALUE_PATTERN_FILENAME) + rootFileName + backend.api.rest.application.utils.ReadFilePropertiesUtils.get(ConstantTerm.EXTENSION_XLS));
//						responseBuilder.header(ConstantUtils.CONTENT_TYPE, "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");			
						responseBuilder.header(ConstantUtils.CONTENT_TYPE, backend.api.rest.application.utils.ReadFilePropertiesUtils.get(ConstantTerm.APPLICATION_EXCEL_CONTENT_TYPE));	
//						responseBuilder.header("Content-Transfer-Encoding", "binary");	
					}
					else if ("word".equals(reportType)) {
						responseBuilder.header(ReadFilePropertiesUtils.get(ConstantUtils.TYPE_DISPOSITON),
								ReadFilePropertiesUtils.get(ConstantUtils.VALUE_PATTERN_FILENAME) + rootFileName + backend.api.rest.application.utils.ReadFilePropertiesUtils.get(ConstantTerm.EXTENSION_DOC));
						responseBuilder.header(ConstantUtils.CONTENT_TYPE, backend.api.rest.application.utils.ReadFilePropertiesUtils.get(ConstantTerm.APPLICATION_WORD_CONTENT_TYPE));	
//						responseBuilder.header(ConstantUtils.CONTENT_TYPE, "application/octet-stream");
//						responseBuilder.header("Content-Transfer-Encoding", "binary");
					}
					else {
						responseBuilder.header(ReadFilePropertiesUtils.get(ConstantUtils.TYPE_DISPOSITON),
								ReadFilePropertiesUtils.get(ConstantUtils.VALUE_PATTERN_FILENAME) + rootFileName + backend.api.rest.application.utils.ReadFilePropertiesUtils.get(ConstantTerm.EXTENSION_PDF));
						responseBuilder.header(ConstantUtils.CONTENT_TYPE, backend.api.rest.application.utils.ReadFilePropertiesUtils.get(ConstantTerm.APPLICATION_PDF_CONTENT_TYPE));						
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
		case ConstantTerm.REPORT_01:
			result = getFormDataReport01(body, siteName);
			break;
		case ConstantTerm.REPORT_02:
			result = getFormDataReportTongHop(body, siteName);
			break;
		case ConstantTerm.REPORT_03:
			result = getFormDataReportDetail(body, siteName);
			break;
		case ConstantTerm.REPORT_04:
			result = getFormDataReportTongHop(body, siteName);
			break;
		case ConstantTerm.REPORT_05:
			result = getFormDataReportDetail(body, siteName);
			break;
		case ConstantTerm.REPORT_06:
			result = getFormDataReportTongHop(body, siteName);
			break;
		case ConstantTerm.REPORT_07:
			// result = getFormDataReport07(body);
			break;
		case ConstantTerm.REPORT_08:
			// result = getFormDataReport08(body);
			break;
		case ConstantTerm.REPORT_09:
			result = getFormDataReportDetail(body, siteName);
			break;
		case ConstantTerm.REPORT_10:
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

			result.put(ConstantTerm.JSON_YEAR, resultBody.getInt(ConstantTerm.JSON_YEAR));
			result.put(ConstantTerm.JSON_MONTH, resultBody.getInt(ConstantTerm.JSON_MONTH));
			result.put(ConstantTerm.JSON_GOV_AGENCY_NAME, siteName);

			result.put(ConstantTerm.JSON_FROMDATE, resultBody.getString(ConstantTerm.JSON_FROMDATE));
			result.put(ConstantTerm.JSON_TODATE, resultBody.getString(ConstantTerm.JSON_TODATE));
			result.put(ConstantTerm.JSON_ACTION_USER, StringPool.BLANK);

			JSONArray dossierData = resultBody.getJSONArray(ConstantUtils.DATA);
			JSONObject currentObject = null;

			JSONObject domainRaw = JSONFactoryUtil.createJSONObject();
			JSONObject dossierRaw = JSONFactoryUtil.createJSONObject();

			for (int i = 0; i < dossierData.length(); i++) {
				currentObject = dossierData.getJSONObject(i);

				JSONObject domainRawItem = JSONFactoryUtil.createJSONObject();

				domainRawItem.put(ConstantTerm.JSON_DOMAIN_NAME, currentObject.getString(ConstantTerm.JSON_DOMAIN_NAME));
				domainRawItem.put(ConstantTerm.JSON_SERVICES, JSONFactoryUtil.createJSONArray());

				if (Validator.isNull(domainRaw.getJSONObject(currentObject.getString(ConstantTerm.JSON_DOMAIN_NAME)))) {
					domainRaw.put(currentObject.getString(ConstantTerm.JSON_DOMAIN_NAME), domainRawItem);
				}

				if (Validator.isNotNull(dossierRaw.getJSONObject(currentObject.getString(ConstantTerm.JSON_SERVICE_CODE)))) {
					if (dossierRaw.getJSONObject(currentObject.getString(ConstantTerm.JSON_SERVICE_CODE)).getString(ConstantTerm.JSON_SERVICE_CODE)
							.equalsIgnoreCase(currentObject.getString(ConstantTerm.JSON_SERVICE_CODE))) {
						dossierRaw.getJSONObject(currentObject.getString(ConstantTerm.JSON_SERVICE_CODE)).getJSONArray(ConstantTerm.JSON_DOSSIERS)
								.put(currentObject);
					}
				} else {
					JSONObject dossierRawItem = JSONFactoryUtil.createJSONObject();

					dossierRawItem.put(ConstantTerm.JSON_SERVICE_CODE, currentObject.getString(ConstantTerm.JSON_SERVICE_CODE));
					dossierRawItem.put(ConstantTerm.JSON_SERVICE_NAME, currentObject.getString(ConstantTerm.JSON_SERVICE_NAME));
					dossierRawItem.put(ConstantTerm.JSON_DOMAIN_NAME, currentObject.getString(ConstantTerm.JSON_DOMAIN_NAME));
					dossierRawItem.put(ConstantTerm.JSON_DOSSIERS, JSONFactoryUtil.createJSONArray());

					dossierRaw.put(currentObject.getString(ConstantTerm.JSON_SERVICE_CODE), dossierRawItem);
					dossierRaw.getJSONObject(currentObject.getString(ConstantTerm.JSON_SERVICE_CODE)).getJSONArray(ConstantTerm.JSON_DOSSIERS)
							.put(currentObject);
				}

			}

			JSONArray keys = dossierRaw.names();

			for (int i = 0; i < keys.length(); ++i) {

				String key = keys.getString(i);

				JSONObject keyObject = dossierRaw.getJSONObject(key);
				if (Validator.isNotNull(keyObject)) {
					if (Validator.isNotNull(domainRaw.getString(keyObject.getString(ConstantTerm.JSON_DOMAIN_NAME)))
							&& domainRaw.getJSONObject(keyObject.getString(ConstantTerm.JSON_DOMAIN_NAME)).getString(ConstantTerm.JSON_DOMAIN_NAME)
									.equalsIgnoreCase(keyObject.getString(ConstantTerm.JSON_DOMAIN_NAME))) {
						domainRaw.getJSONObject(keyObject.getString(ConstantTerm.JSON_DOMAIN_NAME)).getJSONArray(ConstantTerm.JSON_SERVICES)
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

					domainRawItem.put(ConstantTerm.JSON_DOMAIN_NAME, key);
					domainRawItem.put(ConstantTerm.JSON_SERVICES, keyObject.getJSONArray(ConstantTerm.JSON_SERVICES));

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
			int year = resultBody.getInt(ConstantTerm.JSON_YEAR);
			int month = resultBody.getInt(ConstantTerm.JSON_MONTH);
			String fromStatisticDate = resultBody.getString(ConstantTerm.JSON_FROMSTATISTICDATE);
			String toStatisticDate = resultBody.getString(ConstantTerm.JSON_TOSTATISTICDATE);
			if (year > 0 || month > 0) {
				result.put(ConstantTerm.JSON_YEAR, year);
				result.put(ConstantTerm.JSON_MONTH, month);
			} else if (Validator.isNotNull(fromStatisticDate) || Validator.isNotNull(toStatisticDate)){
				result.put(ConstantTerm.JSON_FROMSTATISTICDATE, fromStatisticDate);
				result.put(ConstantTerm.JSON_TOSTATISTICDATE, toStatisticDate);
			}

			result.put(ConstantTerm.JSON_GOV_AGENCY_NAME, siteName);
			//Process statistic all agency
			int flagAgency = resultBody.getInt(ConstantTerm.JSON_FLAG_AGENCY);
			result.put(ConstantTerm.JSON_FLAG_AGENCY, flagAgency);
			JSONArray statistics = resultBody.getJSONArray(ConstantUtils.DATA);
			JSONArray statisticsData = JSONFactoryUtil.createJSONArray();
			
			if (statistics.length() > 0) {
				result.put(ConstantTerm.JSON_NODATA, 1);
			}

			JSONObject currentObject = null;
			if (flagAgency > 0) {
				for (int i = 0; i < statistics.length(); i++) {
					currentObject = statistics.getJSONObject(i);
					if (Validator.isNull(currentObject.getString(ConstantTerm.JSON_DOMAIN_NAME)) && Validator.isNull(currentObject.getString(ConstantTerm.JSON_GOV_AGENCY_NAME))) {
						result.put(ConstantTerm.JSON_TOTAL_3, currentObject.getInt(ConstantTerm.JSON_TOTAL_COUNT));
						result.put(ConstantTerm.JSON_TOTAL_4, currentObject.getInt(ConstantTerm.JSON_DENIED_COUNT));
						result.put(ConstantTerm.JSON_TOTAL_5, currentObject.getInt(ConstantTerm.JSON_CANCELLED_COUNT));
						result.put(ConstantTerm.JSON_TOTAL_6, currentObject.getInt(ConstantTerm.JSON_PROCESS_COUNT));
						result.put(ConstantTerm.JSON_TOTAL_7, currentObject.getInt(ConstantTerm.JSON_REMAINING_COUNT));
						result.put(ConstantTerm.JSON_TOTAL_8, currentObject.getInt(ConstantTerm.JSON_RECEIVED_COUNT));
						result.put(ConstantTerm.JSON_TOTAL_9, currentObject.getInt(ConstantTerm.JSON_ONEGATE_COUNT));
						result.put(ConstantTerm.JSON_TOTAL_10, currentObject.getInt(ConstantTerm.JSON_ONLINE_COUNT));
						result.put(ConstantTerm.JSON_TOTAL_11, currentObject.getInt(ConstantTerm.JSON_RELEASE_COUNT));
						result.put(ConstantTerm.JSON_TOTAL_12, currentObject.getInt(ConstantTerm.JSON_BETIMES_COUNT));
						result.put(ConstantTerm.JSON_TOTAL_13, currentObject.getInt(ConstantTerm.JSON_ONTIME_COUNT));
						result.put(ConstantTerm.JSON_TOTAL_14, currentObject.getInt(ConstantTerm.JSON_OVERTIME_COUNT));
						result.put(ConstantTerm.JSON_TOTAL_15, currentObject.getInt(ConstantTerm.JSON_OVERTIME_INSIDE));
						result.put(ConstantTerm.JSON_TOTAL_16, currentObject.getInt(ConstantTerm.JSON_OVERTIME_OUTSIDE));
						result.put(ConstantTerm.JSON_TOTAL_17, currentObject.getInt(ConstantTerm.JSON_DONE_COUNT));
						result.put(ConstantTerm.JSON_TOTAL_18, currentObject.getInt(ConstantTerm.JSON_RELEASING_COUNT));
						result.put(ConstantTerm.JSON_TOTAL_19, currentObject.getInt(ConstantTerm.JSON_UNRESOLVED_COUNT));
						result.put(ConstantTerm.JSON_TOTAL_20, currentObject.getInt(ConstantTerm.JSON_PROCESSING_COUNT));
						result.put(ConstantTerm.JSON_TOTAL_21, currentObject.getInt(ConstantTerm.JSON_UNDUE_COUNT));
						result.put(ConstantTerm.JSON_TOTAL_22, currentObject.getInt(ConstantTerm.JSON_OVERDUE_COUNT));
						result.put(ConstantTerm.JSON_TOTAL_23, currentObject.getInt(ConstantTerm.JSON_OUTSIDE_COUNT));
						result.put(ConstantTerm.JSON_TOTAL_24, currentObject.getInt(ConstantTerm.JSON_WAITING_COUNT));
						result.put(ConstantTerm.JSON_TOTAL_25, currentObject.getInt(ConstantTerm.JSON_ONTIME_PERCENTAGE));
		
					} else if (Validator.isNull(currentObject.getString(ConstantTerm.JSON_DOMAIN_NAME)) && Validator.isNotNull(currentObject.getString(ConstantTerm.JSON_GOV_AGENCY_NAME))) {
						statisticsData.put(currentObject);
					}
				}
			} else {
				for (int i = 0; i < statistics.length(); i++) {
					currentObject = statistics.getJSONObject(i);
					if (Validator.isNull(currentObject.getString(ConstantTerm.JSON_DOMAIN_NAME)) && Validator.isNotNull(currentObject.getString(ConstantTerm.JSON_GOV_AGENCY_NAME))) {
//						result.put(ConstantTerm.JSON_TOTAL_3, currentObject.getInt(ConstantTerm.JSON_TOTAL_COUNT));
//						result.put(ConstantTerm.JSON_TOTAL_4, currentObject.getInt(ConstantTerm.JSON_DENIED_COUNT));
//						result.put(ConstantTerm.JSON_TOTAL_5, currentObject.getInt(ConstantTerm.JSON_CANCELLED_COUNT));
//						result.put(ConstantTerm.JSON_TOTAL_6, currentObject.getInt(ConstantTerm.JSON_PROCESS_COUNT));
//						result.put(ConstantTerm.JSON_TOTAL_7, currentObject.getInt(ConstantTerm.JSON_REMAINING_COUNT));
//						result.put(ConstantTerm.JSON_TOTAL_8, currentObject.getInt(ConstantTerm.JSON_RECEIVED_COUNT));
//						result.put(ConstantTerm.JSON_TOTAL_9, currentObject.getInt(ConstantTerm.JSON_ONEGATE_COUNT));
//						result.put(ConstantTerm.JSON_TOTAL_10, currentObject.getInt(ConstantTerm.JSON_ONLINE_COUNT));
//						result.put(ConstantTerm.JSON_TOTAL_11, currentObject.getInt(ConstantTerm.JSON_RELEASE_COUNT));
//						result.put(ConstantTerm.JSON_TOTAL_12, currentObject.getInt(ConstantTerm.JSON_BETIMES_COUNT));
//						result.put(ConstantTerm.JSON_TOTAL_13, currentObject.getInt(ConstantTerm.JSON_ONTIME_COUNT));
//						result.put(ConstantTerm.JSON_TOTAL_14, currentObject.getInt(ConstantTerm.JSON_OVERTIME_COUNT));
//						result.put(ConstantTerm.JSON_TOTAL_15, currentObject.getInt(ConstantTerm.JSON_OVERTIME_INSIDE));
//						result.put(ConstantTerm.JSON_TOTAL_16, currentObject.getInt(ConstantTerm.JSON_OVERTIME_OUTSIDE));
//						result.put(ConstantTerm.JSON_TOTAL_17, currentObject.getInt(ConstantTerm.JSON_ONTIME_PERCENTAGE));
//						result.put(ConstantTerm.JSON_TOTAL_18, currentObject.getInt(ConstantTerm.JSON_DONE_COUNT));
//						result.put(ConstantTerm.JSON_TOTAL_19, currentObject.getInt(ConstantTerm.JSON_RELEASING_COUNT));
//						result.put(ConstantTerm.JSON_TOTAL_20, currentObject.getInt(ConstantTerm.JSON_UNRESOLVED_COUNT));
//						result.put(ConstantTerm.JSON_TOTAL_21, currentObject.getInt(ConstantTerm.JSON_PROCESSING_COUNT));
//						result.put(ConstantTerm.JSON_TOTAL_22, currentObject.getInt(ConstantTerm.JSON_UNDUE_COUNT));
//						result.put(ConstantTerm.JSON_TOTAL_23, currentObject.getInt(ConstantTerm.JSON_OVERDUE_COUNT));
//						result.put(ConstantTerm.JSON_TOTAL_24, currentObject.getInt(ConstantTerm.JSON_WAITING_COUNT));
						result.put(ConstantTerm.JSON_TOTAL_3, currentObject.getInt(ConstantTerm.JSON_TOTAL_COUNT));
						result.put(ConstantTerm.JSON_TOTAL_4, currentObject.getInt(ConstantTerm.JSON_DENIED_COUNT));
						result.put(ConstantTerm.JSON_TOTAL_5, currentObject.getInt(ConstantTerm.JSON_CANCELLED_COUNT));
						result.put(ConstantTerm.JSON_TOTAL_6, currentObject.getInt(ConstantTerm.JSON_PROCESS_COUNT));
						result.put(ConstantTerm.JSON_TOTAL_7, currentObject.getInt(ConstantTerm.JSON_REMAINING_COUNT));
						result.put(ConstantTerm.JSON_TOTAL_8, currentObject.getInt(ConstantTerm.JSON_RECEIVED_COUNT));
						result.put(ConstantTerm.JSON_TOTAL_9, currentObject.getInt(ConstantTerm.JSON_ONEGATE_COUNT));
						result.put(ConstantTerm.JSON_TOTAL_10, currentObject.getInt(ConstantTerm.JSON_ONLINE_COUNT));
						result.put(ConstantTerm.JSON_TOTAL_11, currentObject.getInt(ConstantTerm.JSON_RELEASE_COUNT));
						result.put(ConstantTerm.JSON_TOTAL_12, currentObject.getInt(ConstantTerm.JSON_BETIMES_COUNT));
						result.put(ConstantTerm.JSON_TOTAL_13, currentObject.getInt(ConstantTerm.JSON_ONTIME_COUNT));
						result.put(ConstantTerm.JSON_TOTAL_14, currentObject.getInt(ConstantTerm.JSON_OVERTIME_COUNT));
						result.put(ConstantTerm.JSON_TOTAL_15, currentObject.getInt(ConstantTerm.JSON_OVERTIME_INSIDE));
						result.put(ConstantTerm.JSON_TOTAL_16, currentObject.getInt(ConstantTerm.JSON_OVERTIME_OUTSIDE));
						result.put(ConstantTerm.JSON_TOTAL_17, currentObject.getInt(ConstantTerm.JSON_DONE_COUNT));
						result.put(ConstantTerm.JSON_TOTAL_18, currentObject.getInt(ConstantTerm.JSON_RELEASING_COUNT));
						result.put(ConstantTerm.JSON_TOTAL_19, currentObject.getInt(ConstantTerm.JSON_UNRESOLVED_COUNT));
						result.put(ConstantTerm.JSON_TOTAL_20, currentObject.getInt(ConstantTerm.JSON_PROCESSING_COUNT));
						result.put(ConstantTerm.JSON_TOTAL_21, currentObject.getInt(ConstantTerm.JSON_UNDUE_COUNT));
						result.put(ConstantTerm.JSON_TOTAL_22, currentObject.getInt(ConstantTerm.JSON_OVERDUE_COUNT));
						result.put(ConstantTerm.JSON_TOTAL_23, currentObject.getInt(ConstantTerm.JSON_OUTSIDE_COUNT));
						result.put(ConstantTerm.JSON_TOTAL_24, currentObject.getInt(ConstantTerm.JSON_WAITING_COUNT));
						result.put(ConstantTerm.JSON_TOTAL_25, currentObject.getInt(ConstantTerm.JSON_ONTIME_PERCENTAGE));
		
					} else if (Validator.isNotNull(currentObject.getString(ConstantTerm.JSON_DOMAIN_NAME)) && Validator.isNotNull(currentObject.getString(ConstantTerm.JSON_GOV_AGENCY_NAME))) {
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
