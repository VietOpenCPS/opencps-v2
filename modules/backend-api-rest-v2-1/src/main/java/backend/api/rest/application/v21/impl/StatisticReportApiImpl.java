package backend.api.rest.application.v21.impl;

import java.io.File;

import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

import org.opencps.auth.api.BackendAuth;
import org.opencps.auth.api.BackendAuthImpl;
import org.opencps.dossiermgt.model.DocumentType;
import org.opencps.dossiermgt.service.DocumentTypeLocalServiceUtil;

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

		if (!auth.hasResource(serviceContext, StringPool.BLANK, StringPool.BLANK)) {
			return Response.status(HttpServletResponse.SC_FORBIDDEN).build();
		}

		DocumentType docType = DocumentTypeLocalServiceUtil.getByTypeCode(groupId, code);
		String documentScript = StringPool.BLANK;

		if (docType != null) {

			documentScript = docType.getDocumentScript();

			Message message = new Message();
			message.put("formReport", documentScript);
			message.put("formData", doGetFormData(code, body));

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

	private JSONObject doGetFormData(String code, Object body) {
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

	private JSONObject getFormDataReport10(Object body) {
		// TODO Auto-generated method stub
		return null;
	}

	private JSONObject getFormDataReport09(Object body) {
		// TODO Auto-generated method stub
		return null;
	}

	private JSONObject getFormDataReport08(Object body) {
		// TODO Auto-generated method stub
		return null;
	}

	private JSONObject getFormDataReport07(Object body) {
		// TODO Auto-generated method stub
		return null;
	}

	private JSONObject getFormDataReport06(Object body) {
		// TODO Auto-generated method stub
		return null;
	}

	private JSONObject getFormDataReport05(Object body) {
		// TODO Auto-generated method stub
		return null;
	}

	private JSONObject getFormDataReport04(Object body) {
		// TODO Auto-generated method stub
		return null;
	}

	private JSONObject getFormDataReport03(Object body) {
		// TODO Auto-generated method stub
		return null;
	}

	private JSONObject getFormDataReport02(Object body) {
		// TODO Auto-generated method stub
		return null;
	}

	private JSONObject getFormDataReport01(Object body) {
		System.out.println("StatisticReportApiImpl.getFormDataReport01()" + body);
		JSONObject result = (JSONObject) body;
		System.out.println("StatisticReportApiImpl.getFormDataReport01(result)" + result);
		
		return result;
	}

}
