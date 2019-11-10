
package org.opencps.api.controller.impl;

import java.io.File;
import java.util.Date;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

import org.opencps.api.controller.JasperUtilsManagerment;
import org.opencps.auth.api.BackendAuth;
import org.opencps.auth.api.BackendAuthImpl;
import org.opencps.auth.api.exception.UnauthenticationException;
import org.opencps.usermgt.service.EmployeeLocalServiceUtil;

import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.messaging.Message;
import com.liferay.portal.kernel.messaging.MessageBusException;
import com.liferay.portal.kernel.messaging.MessageBusUtil;
import com.liferay.portal.kernel.model.Company;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.Validator;

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
				jsonData.put("userName", employee.getFullName());
			}
			else {
				jsonData.put("userName", user.getFullName());
			}
			jsonData.put("url", serviceContext.getPortalURL());
			_log.info("jsonData: " + jsonData);
			Message message = new Message();
			message.put("formReport", scriptStr);
			message.put("formData", jsonData.toJSONString());

			Date dateEnd = new Date();
			_log.info(
				"TIME Part 1: " + (dateEnd.getTime() - dateStart.getTime()) +
					" ms");
			try {
				Date dateStart1 = new Date();
				String previewResponse =
					(String) MessageBusUtil.sendSynchronousMessage(
						"jasper/engine/preview/destination", message, 10000);

				if (Validator.isNotNull(previewResponse)) {
				}

				File file = new File(previewResponse);

				ResponseBuilder responseBuilder = Response.ok((Object) file);

				responseBuilder.header(
					ReadFilePropertiesUtils.get(ConstantUtils.TYPE_DISPOSITON),
					ReadFilePropertiesUtils.get(ConstantUtils.VALUE_PATTERN_FILENAME) + file.getName() + "\"");
				responseBuilder.header(ConstantUtils.CONTENT_TYPE, "application/pdf");

				Date dateEnd1 = new Date();
				_log.info(
					"TIME Part 2: " +
						(dateEnd1.getTime() - dateStart1.getTime()) + " ms");
				return responseBuilder.build();

			}
			catch (MessageBusException e) {
				_log.info(e);
				throw new Exception("Preview rendering not avariable");
			}

		}
		catch (Exception e) {
			return BusinessExceptionImpl.processException(e);

		}

	}

}
