package backend.api.rest.application.v21.impl;

import java.io.File;

import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

import org.opencps.auth.api.BackendAuth;
import org.opencps.auth.api.BackendAuthImpl;
import org.opencps.dossiermgt.model.DocumentType;
import org.opencps.dossiermgt.service.DocumentTypeLocalServiceUtil;

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
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;

import io.swagger.api.StatisticReportApi;

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
	public Object statisticReportPrint(String code, String body) {
		File file = null;

		BackendAuth auth = new BackendAuthImpl();

		long userId = user.getUserId();
		long groupId = GetterUtil.getLong(header.getHeaderString(Field.GROUP_ID));

		Group group = GroupLocalServiceUtil.fetchGroup(groupId);
		
		String siteName = group.getNameCurrentValue();
		
		serviceContext.setUserId(userId);

//		if (auth.isAuth(serviceContext)) {
//			return Response.status(HttpServletResponse.SC_FORBIDDEN).build();
//		}

		DocumentType docType = DocumentTypeLocalServiceUtil.getByTypeCode(groupId, code);
		String documentScript = StringPool.BLANK;

		if (docType != null) {

			documentScript = docType.getDocumentScript();

			Message message = new Message();
			message.put("formReport", documentScript);
			
			JSONObject resultObject = doGetFormData(code, body, siteName);
			
			message.put("formData", resultObject);

			try {
				String previewResponse = (String) MessageBusUtil
						.sendSynchronousMessage("jasper/engine/preview/destination", message, 10000);

				if (Validator.isNotNull(previewResponse)) {
					file = new File(previewResponse);

					ResponseBuilder responseBuilder = Response.ok((Object) file);

					responseBuilder.header("Content-Disposition",
							"attachment; filename=\"" + docType.getDocumentName()+ ".pdf\"");
					responseBuilder.header("Content-Type", "application/pdf");

					return responseBuilder.build();
				}

			} catch (MessageBusException e) {
				return Response.status(HttpServletResponse.SC_CONFLICT).build();
			}

		}

		return Response.status(HttpServletResponse.SC_NOT_FOUND).build();
	}

	private JSONObject doGetFormData(String code, String body, String siteName) {
		JSONObject result = JSONFactoryUtil.createJSONObject();
		
		switch(code)
        {
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
//            	result = getFormDataReport07(body);
                break;
            case "REPORT_08":
//            	result = getFormDataReport08(body);
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
		
		return result;
	}

	

	private JSONObject getFormDataReportTongHop(String body, String siteName) {
		// TODO Auto-generated method stub
		return null;
	}

	private JSONObject getFormDataReportDetail(String body, String siteName) {
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
					if (dossierRaw.getJSONObject(currentObject.getString("serviceCode")).getString("serviceCode").equalsIgnoreCase(currentObject.getString("serviceCode"))) {
						dossierRaw.getJSONObject(currentObject.getString("serviceCode")).getJSONArray("dossiers").put(currentObject);
					}
				} else {
					JSONObject dossierRawItem = JSONFactoryUtil.createJSONObject();
					
					dossierRawItem.put("serviceCode", currentObject.getString("serviceCode"));
					dossierRawItem.put("serviceName", currentObject.getString("serviceName"));
					dossierRawItem.put("domainName", currentObject.getString("domainName"));
					dossierRawItem.put("dossiers", JSONFactoryUtil.createJSONArray());
					
					dossierRaw.put(currentObject.getString("serviceCode"), dossierRawItem);
					dossierRaw.getJSONObject(currentObject.getString("serviceCode")).getJSONArray("dossiers").put(currentObject);
				}
				
			}
			
			JSONArray keys = dossierRaw.names();

			for (int i = 0; i < keys.length (); ++i) {

			   String key = keys.getString (i);
			   
			   JSONObject keyObject = dossierRaw.getJSONObject(key);
			   if (Validator.isNotNull(keyObject)) {
				   if (Validator.isNotNull(domainRaw.getString(keyObject.getString("domainName"))) &&
						   domainRaw.getJSONObject(keyObject.getString("domainName")).getString("domainName").equalsIgnoreCase(keyObject.getString("domainName"))) {
					   domainRaw.getJSONObject(keyObject.getString("domainName")).getJSONArray("services").put(keyObject);
				   }
			   }
			   
			}
			
			JSONArray domains = JSONFactoryUtil.createJSONArray();
			
			keys = domainRaw.names();

			for (int i = 0; i < keys.length (); ++i) {

			   String key = keys.getString (i);
			   
			   JSONObject keyObject = domainRaw.getJSONObject(key);
			   
			   if (Validator.isNotNull(key)) {
				   
				   JSONObject domainRawItem = JSONFactoryUtil.createJSONObject();
			   
				   domainRawItem.put("domainName", key);
				   domainRawItem.put("services", keyObject.getJSONArray("services"));
				   
				   domains.put(domainRawItem);
			   }
			   
			}
			
			result.put("domains", domains);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return result;
	}

	private JSONObject getFormDataReport01(String body, String siteName) {
		JSONObject result = JSONFactoryUtil.createJSONObject();
		try {
			JSONObject resultBody = JSONFactoryUtil.createJSONObject(body);
			
			result.put("year", resultBody.getInt("year"));
			result.put("month", resultBody.getInt("month"));
			result.put("govAgencyName", siteName);
			
			JSONArray statistics = resultBody.getJSONArray("data");
			JSONArray statisticsData = JSONFactoryUtil.createJSONArray();
			
			
			if (statistics.length() > 0) {
				result.put("nodata", 1);
			}
			
			JSONObject currentObject = null;
			for (int i = 0; i < statistics.length(); i++) {
				currentObject = statistics.getJSONObject(i);
				if (Validator.isNull(currentObject.getString("domainName")) && Validator.isNotNull(currentObject.getString("govAgencyName"))) {
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
					result.put("total_17", currentObject.getInt("ontimePercentage"));
					result.put("total_18", currentObject.getInt("doneCount"));
					result.put("total_19", currentObject.getInt("releasingCount"));
					result.put("total_20", currentObject.getInt("unresolvedCount"));
					result.put("total_21", currentObject.getInt("processingCount"));
					result.put("total_22", currentObject.getInt("undueCount"));
					result.put("total_23", currentObject.getInt("overdueCount"));
					result.put("total_24", currentObject.getInt("waitingCount"));
	
				} else if (Validator.isNotNull(currentObject.getString("domainName")) && Validator.isNotNull(currentObject.getString("govAgencyName"))) {
					statisticsData.put(currentObject);
				}
			}
			

			result.put("statistics", statisticsData);
			
//			if (Validator.isNotNull(statisticsTotal) && statisticsTotal.length() > 0) {
//				
//				JSONObject currentTotal = statisticsTotal.getJSONObject(0);
//				result.put("total_3", currentTotal.getInt("totalCount"));
//				result.put("total_4", currentTotal.getInt("deniedCount"));
//				result.put("total_5", currentTotal.getInt("cancelledCount"));
//				result.put("total_6", currentTotal.getInt("processCount"));
//				result.put("total_7", currentTotal.getInt("remainingCount"));
//				result.put("total_8", currentTotal.getInt("receivedCount"));
//				result.put("total_9", currentTotal.getInt("onegateCount"));
//				result.put("total_10", currentTotal.getInt("onlineCount"));
//				result.put("total_11", currentTotal.getInt("releaseCount"));
//				result.put("total_12", currentTotal.getInt("betimesCount"));
//				result.put("total_13", currentTotal.getInt("ontimeCount"));
//				result.put("total_14", currentTotal.getInt("overtimeCount"));
//				result.put("total_15", currentTotal.getInt("overtimeInside"));
//				result.put("total_16", currentTotal.getInt("overtimeOutside"));
//				result.put("total_17", currentTotal.getInt("ontimePercentage"));
//				result.put("total_18", currentTotal.getInt("doneCount"));
//				result.put("total_19", currentTotal.getInt("releasingCount"));
//				result.put("total_20", currentTotal.getInt("unresolvedCount"));
//				result.put("total_21", currentTotal.getInt("processingCount"));
//				result.put("total_22", currentTotal.getInt("undueCount"));
//				result.put("total_23", currentTotal.getInt("overdueCount"));
//				result.put("total_24", currentTotal.getInt("waitingCount"));
//
//			}
			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

}
