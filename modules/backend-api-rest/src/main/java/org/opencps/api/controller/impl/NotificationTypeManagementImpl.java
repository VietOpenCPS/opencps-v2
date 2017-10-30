package org.opencps.api.controller.impl;

import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;

import org.opencps.api.controller.NotificationTypeManagement;
import org.opencps.api.controller.util.NotificationTemplateUtils;
import org.opencps.api.error.model.ErrorMsg;
import org.opencps.api.notificationtemplate.model.DataSearchModel;
import org.opencps.api.notificationtemplate.model.NotificationTypeResults;
import org.opencps.communication.action.NotificationTemplateInterface;
import org.opencps.communication.action.impl.NotificationTemplateActions;

import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Company;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.ServiceContext;

public class NotificationTypeManagementImpl implements NotificationTypeManagement {

	private static final Log _log = LogFactoryUtil.getLog(NotificationTypeManagementImpl.class);

	@Override
	public Response getNotificationTypes(HttpServletRequest request, HttpHeaders header, Company company, Locale locale,
			User user, ServiceContext serviceContext, DataSearchModel query) {
		NotificationTemplateInterface actions = new NotificationTemplateActions();
		NotificationTypeResults result = new NotificationTypeResults();

		try {

			JSONObject jsonData = actions.getNotificationTypes();

			result.setTotal(jsonData.getLong("total"));
			result.getNotificationTypeModel().addAll(
					NotificationTemplateUtils.mapperNotificationTypeList((Map<String, String>) jsonData.get("data")));

			return Response.status(200).entity(result).build();

		} catch (Exception e) {
			_log.error("/ @GET: " + e);
			ErrorMsg error = new ErrorMsg();

			error.setMessage("not found!");
			error.setCode(404);
			error.setDescription("not found!");

			return Response.status(404).entity(error).build();
		}
	}

}
