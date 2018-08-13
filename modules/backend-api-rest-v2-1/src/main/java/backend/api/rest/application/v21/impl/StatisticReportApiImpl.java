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
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.search.Field;
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
			
			JSONObject resultObject = doGetFormData(code, body);
			
//			System.out.println("StatisticReportApiImpl.statisticReportPrint()" + resultObject);
//			System.out.println("StatisticReportApiImpl.statisticReportPrint()" + resultObject.toJSONString());
			
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

	private JSONObject doGetFormData(String code, String body) {
		JSONObject result = JSONFactoryUtil.createJSONObject();
		
		switch(code)
        {
            case "REPORT_01":
                result = getFormDataReport01(body);
                break;
            case "REPORT_02":
            	result = getFormDataReport02(body);
                break;
            case "REPORT_03":
            	result = getFormDataReport03(body);
                break;
            case "REPORT_04":
            	result = getFormDataReport04(body);
                break;
            case "REPORT_05":
            	result = getFormDataReport05(body);
                break;
            case "REPORT_06":
            	result = getFormDataReport06(body);
                break;
            case "REPORT_07":
            	result = getFormDataReport07(body);
                break;
            case "REPORT_08":
            	result = getFormDataReport08(body);
                break;
            case "REPORT_09":
            	result = getFormDataReport09(body);
                break;
            case "REPORT_10":
            	result = getFormDataReport10(body);
                break;
            default:
            	result = JSONFactoryUtil.createJSONObject();
        }
		
		return result;
	}

	private JSONObject getFormDataReport10(String body) {
		JSONObject result = JSONFactoryUtil.createJSONObject();
		try {
			JSONObject resultBody = JSONFactoryUtil.createJSONObject(body);
			
			result.put("year", resultBody.getInt("year"));
			result.put("month", resultBody.getInt("month"));
			result.put("govAgencyName", resultBody.getString("govAgencyName"));
			
			result.put("fromDate", resultBody.getString("fromDate"));
			result.put("toDate", resultBody.getString("toDate"));
			result.put("actionUser", "");
			  
			JSONArray dossierData = resultBody.getJSONArray("data");
			JSONObject currentObject = null;
			
			JSONObject domainRaw = JSONFactoryUtil.createJSONObject();
			JSONArray domainsservicesRaw = JSONFactoryUtil.createJSONArray();
			
			
			for (int i = 0; i < dossierData.length(); i++) {
				currentObject = dossierData.getJSONObject(i);
				
				if (!domainRaw.has(currentObject.getString("domainName"))) {
					domainsservicesRaw = JSONFactoryUtil.createJSONArray();
					JSONObject servicesRaw = JSONFactoryUtil.createJSONObject();
					servicesRaw.put("serviceName", currentObject.getString("serviceName"));
					servicesRaw.put("serviceCode", currentObject.getString("serviceCode"));
					
					domainsservicesRaw.put(servicesRaw);
					
					domainRaw.put(currentObject.getString("domainName"), domainsservicesRaw);
				} else {
					JSONObject servicesRaw = JSONFactoryUtil.createJSONObject();
					servicesRaw.put("serviceName", currentObject.getString("serviceName"));
					servicesRaw.put("serviceCode", currentObject.getString("serviceCode"));
					
					domainsservicesRaw.put(servicesRaw);
					
					domainRaw.put(currentObject.getString("domainName"), domainsservicesRaw);
				}
				
			}
			
			JSONArray domains = JSONFactoryUtil.createJSONArray();
			
			JSONArray keys = domainRaw.names();

			for (int i = 0; i < keys.length (); ++i) {

			   String key = keys.getString (i);
			   JSONArray value = domainRaw.getJSONArray(key);
			   if (Validator.isNotNull(key)) {
				   
				   for (int y = 0; y < value.length(); y++) {
					   JSONObject currentService = value.getJSONObject(y);
					   JSONArray dossierDataJasper = JSONFactoryUtil.createJSONArray();
					   
					   for (int k = 0; k < dossierData.length(); k++) {
						   JSONObject currentObjectDossier = dossierData.getJSONObject(k);
							
						   if (currentObjectDossier.getString("serviceCode").equalsIgnoreCase(currentService.getString("serviceCode"))) {
							   dossierDataJasper.put(currentObjectDossier);
						   }
						   
					   }
					   currentService.put("dossiers", dossierDataJasper);
				   }
				   
				   JSONObject domainRawItem = JSONFactoryUtil.createJSONObject();
			   
			   
				   domainRawItem.put("domainName", key);
				   domainRawItem.put("services", value);
				   
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

	private JSONObject getFormDataReport09(String body) {
		JSONObject result = JSONFactoryUtil.createJSONObject();
		try {
			JSONObject resultBody = JSONFactoryUtil.createJSONObject(body);
			
			result.put("year", resultBody.getInt("year"));
			result.put("month", resultBody.getInt("month"));
			result.put("govAgencyName", resultBody.getString("govAgencyName"));
			
			result.put("fromDate", resultBody.getString("fromDate"));
			result.put("toDate", resultBody.getString("toDate"));
			result.put("actionUser", "");
			  
			JSONArray dossierData = resultBody.getJSONArray("data");
			JSONObject currentObject = null;
			
			JSONObject domainRaw = JSONFactoryUtil.createJSONObject();
			JSONArray domainsservicesRaw = JSONFactoryUtil.createJSONArray();
			
			
			for (int i = 0; i < dossierData.length(); i++) {
				currentObject = dossierData.getJSONObject(i);
				
				if (!domainRaw.has(currentObject.getString("domainName"))) {
					domainsservicesRaw = JSONFactoryUtil.createJSONArray();
					JSONObject servicesRaw = JSONFactoryUtil.createJSONObject();
					servicesRaw.put("serviceName", currentObject.getString("serviceName"));
					servicesRaw.put("serviceCode", currentObject.getString("serviceCode"));
					
					domainsservicesRaw.put(servicesRaw);
					
					domainRaw.put(currentObject.getString("domainName"), domainsservicesRaw);
				} else {
					JSONObject servicesRaw = JSONFactoryUtil.createJSONObject();
					servicesRaw.put("serviceName", currentObject.getString("serviceName"));
					servicesRaw.put("serviceCode", currentObject.getString("serviceCode"));
					
					domainsservicesRaw.put(servicesRaw);
					
					domainRaw.put(currentObject.getString("domainName"), domainsservicesRaw);
				}
				
			}
			
			JSONArray domains = JSONFactoryUtil.createJSONArray();
			
			JSONArray keys = domainRaw.names();

			for (int i = 0; i < keys.length (); ++i) {

			   String key = keys.getString (i);
			   JSONArray value = domainRaw.getJSONArray(key);
			   if (Validator.isNotNull(key)) {
				   
				   for (int y = 0; y < value.length(); y++) {
					   JSONObject currentService = value.getJSONObject(y);
					   JSONArray dossierDataJasper = JSONFactoryUtil.createJSONArray();
					   
					   for (int k = 0; k < dossierData.length(); k++) {
						   JSONObject currentObjectDossier = dossierData.getJSONObject(k);
							
						   if (currentObjectDossier.getString("serviceCode").equalsIgnoreCase(currentService.getString("serviceCode"))) {
							   dossierDataJasper.put(currentObjectDossier);
						   }
						   
					   }
					   currentService.put("dossiers", dossierDataJasper);
				   }
				   
				   JSONObject domainRawItem = JSONFactoryUtil.createJSONObject();
			   
			   
				   domainRawItem.put("domainName", key);
				   domainRawItem.put("services", value);
				   
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

	private JSONObject getFormDataReport08(String body) {
		// TODO Auto-generated method stub
		return null;
	}

	private JSONObject getFormDataReport07(String body) {
		// TODO Auto-generated method stub
		return null;
	}

	private JSONObject getFormDataReport06(String body) {
		JSONObject result = JSONFactoryUtil.createJSONObject();
		try {
			JSONObject resultBody = JSONFactoryUtil.createJSONObject(body);
			
			result.put("year", resultBody.getInt("year"));
			result.put("month", resultBody.getInt("month"));
			result.put("govAgencyName", resultBody.getString("govAgencyName"));
			
			result.put("fromDate", resultBody.getString("fromDate"));
			result.put("toDate", resultBody.getString("toDate"));
			result.put("actionUser", "");
			  
			JSONArray dossierData = resultBody.getJSONArray("data");
			JSONObject currentObject = null;
			
			JSONObject domainRaw = JSONFactoryUtil.createJSONObject();
			JSONArray domainsservicesRaw = JSONFactoryUtil.createJSONArray();
			
			for (int i = 0; i < dossierData.length(); i++) {
				currentObject = dossierData.getJSONObject(i);
				
				if (!domainRaw.has(currentObject.getString("domainName"))) {
					domainsservicesRaw = JSONFactoryUtil.createJSONArray();
					JSONObject servicesRaw = JSONFactoryUtil.createJSONObject();
					servicesRaw.put("serviceName", currentObject.getString("serviceName"));
					servicesRaw.put("serviceCode", currentObject.getString("serviceCode"));
					servicesRaw.put("tiep_nhan", 0);
					servicesRaw.put("dang_giai_quyet", 0);
					servicesRaw.put("da_giai_quyet", 0);
					servicesRaw.put("da_tra_ket_qua", 0);
					servicesRaw.put("tu_choi", 0);
					
					domainsservicesRaw.put(servicesRaw);
					
					domainRaw.put(currentObject.getString("domainName"), domainsservicesRaw);
				} else {
					JSONObject servicesRaw = JSONFactoryUtil.createJSONObject();
					servicesRaw.put("serviceName", currentObject.getString("serviceName"));
					servicesRaw.put("serviceCode", currentObject.getString("serviceCode"));
					servicesRaw.put("tiep_nhan", 0);
					servicesRaw.put("dang_giai_quyet", 0);
					servicesRaw.put("da_giai_quyet", 0);
					servicesRaw.put("da_tra_ket_qua", 0);
					servicesRaw.put("tu_choi", 0);
					
					domainsservicesRaw.put(servicesRaw);
					
					domainRaw.put(currentObject.getString("domainName"), domainsservicesRaw);
				}
				
			}
			
			JSONArray domains = JSONFactoryUtil.createJSONArray();
			
			JSONArray keys = domainRaw.names();

			for (int i = 0; i < keys.length (); ++i) {

			   String key = keys.getString (i);
			   JSONArray value = domainRaw.getJSONArray(key);
			   if (Validator.isNotNull(key)) {
			   
				   JSONObject domainRawItem = JSONFactoryUtil.createJSONObject();
			   
				   domainRawItem.put("domainName", key);
				   domainRawItem.put("services", value);
				   
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

	private JSONObject getFormDataReport05(String body) {
		JSONObject result = JSONFactoryUtil.createJSONObject();
		try {
			JSONObject resultBody = JSONFactoryUtil.createJSONObject(body);
			
			result.put("year", resultBody.getInt("year"));
			result.put("month", resultBody.getInt("month"));
			result.put("govAgencyName", resultBody.getString("govAgencyName"));
			
			result.put("fromDate", resultBody.getString("fromDate"));
			result.put("toDate", resultBody.getString("toDate"));
			result.put("actionUser", "");
			  
			JSONArray dossierData = resultBody.getJSONArray("data");
			JSONObject currentObject = null;
			
			JSONObject domainRaw = JSONFactoryUtil.createJSONObject();
			JSONArray domainsservicesRaw = JSONFactoryUtil.createJSONArray();
			
			
			for (int i = 0; i < dossierData.length(); i++) {
				currentObject = dossierData.getJSONObject(i);
				
				if (!domainRaw.has(currentObject.getString("domainName"))) {
					domainsservicesRaw = JSONFactoryUtil.createJSONArray();
					JSONObject servicesRaw = JSONFactoryUtil.createJSONObject();
					servicesRaw.put("serviceName", currentObject.getString("serviceName"));
					servicesRaw.put("serviceCode", currentObject.getString("serviceCode"));
					
					domainsservicesRaw.put(servicesRaw);
					
					domainRaw.put(currentObject.getString("domainName"), domainsservicesRaw);
				} else {
					JSONObject servicesRaw = JSONFactoryUtil.createJSONObject();
					servicesRaw.put("serviceName", currentObject.getString("serviceName"));
					servicesRaw.put("serviceCode", currentObject.getString("serviceCode"));
					
					domainsservicesRaw.put(servicesRaw);
					
					domainRaw.put(currentObject.getString("domainName"), domainsservicesRaw);
				}
				
			}
			
			JSONArray domains = JSONFactoryUtil.createJSONArray();
			
			JSONArray keys = domainRaw.names();

			for (int i = 0; i < keys.length (); ++i) {

			   String key = keys.getString (i);
			   JSONArray value = domainRaw.getJSONArray(key);
			   if (Validator.isNotNull(key)) {
				   
				   for (int y = 0; y < value.length(); y++) {
					   JSONObject currentService = value.getJSONObject(y);
					   JSONArray dossierDataJasper = JSONFactoryUtil.createJSONArray();
					   
					   for (int k = 0; k < dossierData.length(); k++) {
						   JSONObject currentObjectDossier = dossierData.getJSONObject(k);
							
						   if (currentObjectDossier.getString("serviceCode").equalsIgnoreCase(currentService.getString("serviceCode"))) {
							   dossierDataJasper.put(currentObjectDossier);
						   }
						   
					   }
					   currentService.put("dossiers", dossierDataJasper);
				   }
				   
				   JSONObject domainRawItem = JSONFactoryUtil.createJSONObject();
			   
			   
				   domainRawItem.put("domainName", key);
				   domainRawItem.put("services", value);
				   
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

	private JSONObject getFormDataReport04(String body) {
		JSONObject result = JSONFactoryUtil.createJSONObject();
		try {
			JSONObject resultBody = JSONFactoryUtil.createJSONObject(body);
			
			result.put("year", resultBody.getInt("year"));
			result.put("month", resultBody.getInt("month"));
			result.put("govAgencyName", resultBody.getString("govAgencyName"));
			
			result.put("fromDate", resultBody.getString("fromDate"));
			result.put("toDate", resultBody.getString("toDate"));
			result.put("actionUser", "");
			  
			JSONArray dossierData = resultBody.getJSONArray("data");
			JSONObject currentObject = null;
			
			JSONObject domainRaw = JSONFactoryUtil.createJSONObject();
			JSONArray domainsservicesRaw = JSONFactoryUtil.createJSONArray();
			
			for (int i = 0; i < dossierData.length(); i++) {
				currentObject = dossierData.getJSONObject(i);
				
				if (!domainRaw.has(currentObject.getString("domainName"))) {
					domainsservicesRaw = JSONFactoryUtil.createJSONArray();
					JSONObject servicesRaw = JSONFactoryUtil.createJSONObject();
					servicesRaw.put("serviceName", currentObject.getString("serviceName"));
					servicesRaw.put("serviceCode", currentObject.getString("serviceCode"));
					servicesRaw.put("da_tra", 0);
					servicesRaw.put("tu_choi", 0);
					
					domainsservicesRaw.put(servicesRaw);
					
					domainRaw.put(currentObject.getString("domainName"), domainsservicesRaw);
				} else {
					JSONObject servicesRaw = JSONFactoryUtil.createJSONObject();
					servicesRaw.put("serviceName", currentObject.getString("serviceName"));
					servicesRaw.put("serviceCode", currentObject.getString("serviceCode"));
					servicesRaw.put("da_tra", 0);
					servicesRaw.put("tu_choi", 0);
					
					domainsservicesRaw.put(servicesRaw);
					
					domainRaw.put(currentObject.getString("domainName"), domainsservicesRaw);
				}
				
			}
			
			JSONArray domains = JSONFactoryUtil.createJSONArray();
			
			JSONArray keys = domainRaw.names();

			for (int i = 0; i < keys.length (); ++i) {

			   String key = keys.getString (i);
			   JSONArray value = domainRaw.getJSONArray(key);
			   if (Validator.isNotNull(key)) {
			   
				   JSONObject domainRawItem = JSONFactoryUtil.createJSONObject();
			   
				   domainRawItem.put("domainName", key);
				   domainRawItem.put("services", value);
				   
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

	private JSONObject getFormDataReport03(String body) {
		JSONObject result = JSONFactoryUtil.createJSONObject();
		try {
			JSONObject resultBody = JSONFactoryUtil.createJSONObject(body);
			
			result.put("year", resultBody.getInt("year"));
			result.put("month", resultBody.getInt("month"));
			result.put("govAgencyName", resultBody.getString("govAgencyName"));
			
			result.put("fromDate", resultBody.getString("fromDate"));
			result.put("toDate", resultBody.getString("toDate"));
			result.put("actionUser", "");
			  
			JSONArray dossierData = resultBody.getJSONArray("data");
			JSONObject currentObject = null;
			
			JSONObject domainRaw = JSONFactoryUtil.createJSONObject();
			JSONArray domainsservicesRaw = JSONFactoryUtil.createJSONArray();
			
			
			for (int i = 0; i < dossierData.length(); i++) {
				currentObject = dossierData.getJSONObject(i);
				
				if (!domainRaw.has(currentObject.getString("domainName"))) {
					domainsservicesRaw = JSONFactoryUtil.createJSONArray();
					JSONObject servicesRaw = JSONFactoryUtil.createJSONObject();
					servicesRaw.put("serviceName", currentObject.getString("serviceName"));
					servicesRaw.put("serviceCode", currentObject.getString("serviceCode"));
					
					domainsservicesRaw.put(servicesRaw);
					
					domainRaw.put(currentObject.getString("domainName"), domainsservicesRaw);
				} else {
					JSONObject servicesRaw = JSONFactoryUtil.createJSONObject();
					servicesRaw.put("serviceName", currentObject.getString("serviceName"));
					servicesRaw.put("serviceCode", currentObject.getString("serviceCode"));
					
					domainsservicesRaw.put(servicesRaw);
					
					domainRaw.put(currentObject.getString("domainName"), domainsservicesRaw);
				}
				
			}
			
			JSONArray domains = JSONFactoryUtil.createJSONArray();
			
			JSONArray keys = domainRaw.names();

			for (int i = 0; i < keys.length (); ++i) {

			   String key = keys.getString (i);
			   JSONArray value = domainRaw.getJSONArray(key);
			   if (Validator.isNotNull(key)) {
				   for (int y = 0; y < value.length(); y++) {
					   JSONObject currentService = value.getJSONObject(y);
					   JSONArray dossierDataJasper = JSONFactoryUtil.createJSONArray();
					   
					   for (int k = 0; k < dossierData.length(); k++) {
						   JSONObject currentObjectDossier = dossierData.getJSONObject(k);
							
						   if (currentObjectDossier.getString("serviceCode").equalsIgnoreCase(currentService.getString("serviceCode"))) {
							   dossierDataJasper.put(currentObjectDossier);
						   }
						   
					   }
					   currentService.put("dossiers", dossierDataJasper);
				   }
				   
				   JSONObject domainRawItem = JSONFactoryUtil.createJSONObject();
			   
				   domainRawItem.put("domainName", key);
				   domainRawItem.put("services", value);
				   
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

	private JSONObject getFormDataReport02(String body) {
		JSONObject result = JSONFactoryUtil.createJSONObject();
		try {
			JSONObject resultBody = JSONFactoryUtil.createJSONObject(body);
			
			result.put("year", resultBody.getInt("year"));
			result.put("month", resultBody.getInt("month"));
			result.put("govAgencyName", resultBody.getString("govAgencyName"));
			
			result.put("fromDate", resultBody.getString("fromDate"));
			result.put("toDate", resultBody.getString("toDate"));
			result.put("actionUser", "");
			  
			JSONArray dossierData = resultBody.getJSONArray("data");
			JSONObject currentObject = null;
			
			JSONObject domainRaw = JSONFactoryUtil.createJSONObject();
			JSONArray domainsservicesRaw = JSONFactoryUtil.createJSONArray();
			
			for (int i = 0; i < dossierData.length(); i++) {
				currentObject = dossierData.getJSONObject(i);
				
				if (!domainRaw.has(currentObject.getString("domainName"))) {
					domainsservicesRaw = JSONFactoryUtil.createJSONArray();
					JSONObject servicesRaw = JSONFactoryUtil.createJSONObject();
					servicesRaw.put("serviceName", currentObject.getString("serviceName"));
					servicesRaw.put("serviceCode", currentObject.getString("serviceCode"));
					servicesRaw.put("tong_so", 0);
					servicesRaw.put("ky_truoc", 0);
					servicesRaw.put("trong_ky", 0);
					
					domainsservicesRaw.put(servicesRaw);
					
					domainRaw.put(currentObject.getString("domainName"), domainsservicesRaw);
				} else {
					JSONObject servicesRaw = JSONFactoryUtil.createJSONObject();
					servicesRaw.put("serviceName", currentObject.getString("serviceName"));
					servicesRaw.put("serviceCode", currentObject.getString("serviceCode"));
					servicesRaw.put("tong_so", 0);
					servicesRaw.put("ky_truoc", 0);
					servicesRaw.put("trong_ky", 0);
					
					domainsservicesRaw.put(servicesRaw);
					
					domainRaw.put(currentObject.getString("domainName"), domainsservicesRaw);
				}
				
			}
			
			JSONArray domains = JSONFactoryUtil.createJSONArray();
			
			JSONArray keys = domainRaw.names();

			for (int i = 0; i < keys.length (); ++i) {

			   String key = keys.getString (i);
			   JSONArray value = domainRaw.getJSONArray(key);
			   
			   JSONObject domainRawItem = JSONFactoryUtil.createJSONObject();
			   if (Validator.isNotNull(key)) {
				   domainRawItem.put("domainName", key);
				   domainRawItem.put("services", value);
				   
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

	private JSONObject getFormDataReport01(String body) {
		JSONObject result = JSONFactoryUtil.createJSONObject();
		try {
			JSONObject resultBody = JSONFactoryUtil.createJSONObject(body);
			
			result.put("year", resultBody.getInt("year"));
			result.put("month", resultBody.getInt("month"));
			result.put("govAgencyName", resultBody.getString("govAgencyName"));
			
			JSONArray statistics = resultBody.getJSONArray("data");
			
			result.put("statistics", statistics);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

}
