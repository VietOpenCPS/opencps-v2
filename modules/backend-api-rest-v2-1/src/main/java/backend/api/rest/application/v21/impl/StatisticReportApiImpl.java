package backend.api.rest.application.v21.impl;

import java.io.File;

import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;

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
	public File statisticReportPrint(String code, String q) {
		File file = null;

		BackendAuth auth = new BackendAuthImpl();

		long userId = user.getUserId();
		long groupId = GetterUtil.getLong(header.getHeaderString(Field.GROUP_ID));

		serviceContext.setUserId(userId);
		
		if (auth.hasResource(serviceContext, StringPool.BLANK, StringPool.BLANK)) {
			response.setStatus(HttpServletResponse.SC_FORBIDDEN);
			return file;
		}

		DocumentType docType = DocumentTypeLocalServiceUtil.getByTypeCode(groupId, code);
		String documentScript = StringPool.BLANK;

		if (docType != null) {
			
			JSONObject formDataJSON = JSONFactoryUtil.createJSONObject();
			documentScript = docType.getDocumentScript();
			
			Message message = new Message();
			message.put("formReport", documentScript);
			message.put("formData", formDataJSON.toJSONString());

			try {
				String previewResponse = (String) MessageBusUtil.sendSynchronousMessage("jasper/engine/preview/destination",
						message, 10000);

				if (Validator.isNotNull(previewResponse)) {
					file = new File(previewResponse);
					
					response.addHeader("Content-Disposition",
							"attachment; filename=\"" + file.getName() + "\"");
					response.addHeader("Content-Type", "application/pdf");
					
				}

			} catch (MessageBusException e) {
				response.setStatus(HttpServletResponse.SC_CONFLICT);
			}
			
		}

		return file;
	}

}
