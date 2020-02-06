package org.opencps.api.controller.impl;

import java.net.HttpURLConnection;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;

import org.opencps.api.constants.ConstantUtils;
import org.opencps.api.controller.NotificationQueueManagement;
import org.opencps.api.controller.util.MessageUtil;
import org.opencps.api.controller.util.NotificationTemplateUtils;
import org.opencps.api.error.model.ErrorMsg;
import org.opencps.api.notificationtemplate.model.DataSearchModel;
import org.opencps.api.notificationtemplate.model.NotificationQueueResults;
import org.opencps.api.notificationtemplate.model.NotificationQueueShortModel;
import org.opencps.communication.action.NotificationQueueInterface;
import org.opencps.communication.action.impl.NotificationQueueActions;
import org.opencps.communication.model.NotificationQueue;

import backend.auth.api.exception.BusinessExceptionImpl;

import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.model.Company;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.Validator;

public class NotificationQueueManagementImpl implements NotificationQueueManagement {

//	private static final Log _log = LogFactoryUtil.getLog(NotificationQueueManagementImpl.class);

	@SuppressWarnings("unchecked")
	@Override
	public Response getNotificationQueues(HttpServletRequest request, HttpHeaders header, Company company,
			Locale locale, User user, ServiceContext serviceContext, DataSearchModel query) {
		NotificationQueueInterface actions = new NotificationQueueActions();
		NotificationQueueResults result = new NotificationQueueResults();

		try {

			JSONObject jsonData = actions.getNotificationQueues(serviceContext);

			result.setTotal(jsonData.getLong(ConstantUtils.TOTAL));
			result.getNotificationQueueShortModel().addAll(NotificationTemplateUtils
					.mapperNotificationQueueList((List<NotificationQueue>) jsonData.get(ConstantUtils.DATA)));

			return Response.status(HttpURLConnection.HTTP_OK).entity(result).build();

		} catch (Exception e) {
			return BusinessExceptionImpl.processException(e);
		}
	}

	@Override
	public Response getNotificationQueuesByID(HttpServletRequest request, HttpHeaders header, Company company,
			Locale locale, User user, ServiceContext serviceContext, long id) {
		NotificationQueueInterface actions = new NotificationQueueActions();
		NotificationQueueShortModel notificationQueueModel;

//		long groupId = GetterUtil.getLong(header.getHeaderString("groupId"));

		NotificationQueue notificationQueue = actions.read(id, serviceContext);

		if (Validator.isNotNull(notificationQueue)) {

			notificationQueueModel = NotificationTemplateUtils.mapperNotificationQueueModel(notificationQueue);

			return Response.status(HttpURLConnection.HTTP_OK).entity(notificationQueueModel).build();

		} else {

			ErrorMsg error = new ErrorMsg();

			error.setMessage(MessageUtil.getMessage(ConstantUtils.API_MESSAGE_NOTFOUND));
			error.setCode(HttpURLConnection.HTTP_NOT_FOUND);
			error.setDescription(MessageUtil.getMessage(ConstantUtils.API_MESSAGE_NOTFOUND));

			return Response.status(HttpURLConnection.HTTP_NOT_FOUND).entity(error).build();

		}
	}

}
