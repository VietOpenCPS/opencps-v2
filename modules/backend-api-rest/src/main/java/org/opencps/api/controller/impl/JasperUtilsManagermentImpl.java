
package org.opencps.api.controller.impl;

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
import com.liferay.portal.kernel.util.Validator;

import java.io.File;
import java.util.Date;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

import org.opencps.api.constants.ConstantUtils;
import org.opencps.api.controller.JasperUtilsManagerment;
import org.opencps.api.controller.util.MessageUtil;
import org.opencps.auth.api.BackendAuth;
import org.opencps.auth.api.BackendAuthImpl;
import org.opencps.usermgt.service.EmployeeLocalServiceUtil;

import backend.auth.api.exception.BusinessExceptionImpl;

public class JasperUtilsManagermentImpl implements JasperUtilsManagerment {

	private static Log _log =
		LogFactoryUtil.getLog(JasperUtilsManagermentImpl.class);

	@Override
	public Response getPreview(
		HttpServletRequest request, HttpHeaders header, Company company,
		Locale locale, User user, ServiceContext serviceContext,
		String jsonDataStr, String scriptStr) {

		BackendAuth auth = new BackendAuthImpl();
		long groupId = GetterUtil.getLong(header.getHeaderString(Field.GROUP_ID));

		_log.info("jsonDataStr: " + jsonDataStr);
		_log.info("documentScriptStr: " + scriptStr);
		Date dateStart = new Date();
		try {

			if (!auth.isAuth(serviceContext)) {
				// throw new UnauthenticationException();
			}
			JSONObject jsonData = JSONFactoryUtil.createJSONObject(jsonDataStr);
			org.opencps.usermgt.model.Employee employee =
				EmployeeLocalServiceUtil.fetchByF_mappingUserId(
					groupId, user.getUserId());
			if (employee != null) {
				jsonData.put(Field.USER_NAME, employee.getFullName());
			}
			else {
				jsonData.put(Field.USER_NAME, user.getFullName());
			}
			jsonData.put(ConstantUtils.API_JSON_URL, serviceContext.getPortalURL());
			_log.info("jsonData: " + jsonData);
			Message message = new Message();
			message.put(ConstantUtils.API_JSON_FORM_REPORT, scriptStr);
			message.put(ConstantUtils.API_JSON_FORM_DATA, jsonData.toJSONString());

			Date dateEnd = new Date();
			_log.info(
				"TIME Part 1: " + (dateEnd.getTime() - dateStart.getTime()) +
					" ms");
			try {
				Date dateStart1 = new Date();
				String previewResponse =
					(String) MessageBusUtil.sendSynchronousMessage(
						ConstantUtils.DOSSIERDOCUMENT_JASPER_ENGINE_PREVIEW, message, 10000);

				if (Validator.isNotNull(previewResponse)) {
				}

				File file = new File(previewResponse);

				ResponseBuilder responseBuilder = Response.ok((Object) file);
				String attachmentFilename = String.format(MessageUtil.getMessage(ConstantUtils.ATTACHMENT_FILENAME), file.getName());
				responseBuilder.header(
					ConstantUtils.CONTENT_DISPOSITION,
					attachmentFilename);
				responseBuilder.header(HttpHeaders.CONTENT_TYPE, ConstantUtils.MEDIA_TYPE_PDF);

				Date dateEnd1 = new Date();
				_log.info(
					"TIME Part 2: " +
						(dateEnd1.getTime() - dateStart1.getTime()) + " ms");
				return responseBuilder.build();

			}
			catch (MessageBusException e) {
				_log.info(e);
				throw new Exception(MessageUtil.getMessage(ConstantUtils.DOSSIERDOCUMENT_MESSAGE_PREVIEW_NOT_AVAILABLE));
			}

		}
		catch (Exception e) {
			return BusinessExceptionImpl.processException(e);

		}

	}

}
