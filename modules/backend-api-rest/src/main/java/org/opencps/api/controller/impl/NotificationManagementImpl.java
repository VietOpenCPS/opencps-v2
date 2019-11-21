package org.opencps.api.controller.impl;

import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Company;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.model.UserNotificationEvent;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.service.UserNotificationEventLocalServiceUtil;
import com.liferay.portal.kernel.util.GetterUtil;

import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;

import org.apache.commons.httpclient.util.HttpURLConnection;
import org.opencps.api.controller.NotificationManagement;
import org.opencps.api.notification.model.NotificationSearchModel;
import org.opencps.dossiermgt.action.util.ConstantUtils;
import org.opencps.dossiermgt.action.util.ReadFilePropertiesUtils;
import org.opencps.dossiermgt.constants.DossierActionTerm;

import backend.auth.api.exception.BusinessExceptionImpl;

public class NotificationManagementImpl implements NotificationManagement{

	private static Log _log = LogFactoryUtil.getLog(NotificationManagementImpl.class);

	@Override
	public Response getNotificationList(HttpServletRequest request, HttpHeaders header, Company company, Locale locale,
			User user, ServiceContext serviceContext, NotificationSearchModel query, Boolean archived) {
		JSONObject result = JSONFactoryUtil.createJSONObject();
		JSONArray data = JSONFactoryUtil.createJSONArray();
		//JSONObject record = JSONFactoryUtil.createJSONObject();
		long userId = user.getUserId();
		Boolean archivedParam = archived != null ? archived : true;
		
		try {

			// Mask as read all when click BELL
//			markAsReadAll(userId);
			int end = query.getEnd();
			int start = query.getStart();
			if (end == 0) {
				start = QueryUtil.ALL_POS;
				end = QueryUtil.ALL_POS;
			}
			List<UserNotificationEvent> events = UserNotificationEventLocalServiceUtil
					.getArchivedUserNotificationEvents(userId, false, archivedParam, start, end);

			for (UserNotificationEvent event : events) {

				JSONObject record = JSONFactoryUtil.createJSONObject(event.getPayload());
				record.put(DossierActionTerm.PAYLOAD, event.getPayload());
				record.put(Field.USER_ID, event.getUserId());
				User finduser = UserLocalServiceUtil.fetchUser(event.getUserId());
				record.put(Field.USER_NAME, finduser.getFullName());
				
				data.put(record);
			}

			int userNotificationEventsCount = UserNotificationEventLocalServiceUtil.
					getArchivedUserNotificationEventsCount(userId, false, archivedParam);

			result.put(ConstantUtils.TOTAL, userNotificationEventsCount);
			result.put(ConstantUtils.DATA, data);
//			writeJSON(actionRequest, actionResponse, result);
		} catch (Exception e) {
			return BusinessExceptionImpl.processException(e);
		}
		return Response.status(HttpURLConnection.HTTP_OK).entity(result.toJSONString()).build();
	}

	@Override
	public Response countTotalNotifications(HttpServletRequest request, HttpHeaders header, Company company, Locale locale,
			User user, ServiceContext serviceContext, NotificationSearchModel query, Boolean archived) {

		JSONObject result = JSONFactoryUtil.createJSONObject();
		long userId = user.getUserId();
		Boolean archivedParam = archived != null ? archived : true;
		
		try {

			int userNotificationEventsCount = UserNotificationEventLocalServiceUtil
					.getArchivedUserNotificationEventsCount(userId, false, archivedParam);

			result.put(ConstantUtils.TOTAL, userNotificationEventsCount);
		} catch (Exception e) {
			_log.debug(e);
			result.put(ConstantUtils.TOTAL, 0);
			return Response.status(HttpURLConnection.HTTP_INTERNAL_ERROR).entity(result.toJSONString()).build();
		}

		return Response.status(HttpURLConnection.HTTP_OK).entity(result.toJSONString()).build();

	}

	@Override
	public Response deleteNotification(HttpServletRequest request, HttpHeaders header, Company company, Locale locale,
			User user, ServiceContext serviceContext, String id) {

		JSONObject result = JSONFactoryUtil.createJSONObject();
		long userNotificationEventId = GetterUtil.getLong(id);

		try {

			UserNotificationEventLocalServiceUtil.deleteUserNotificationEvent(userNotificationEventId);
			result.put(ReadFilePropertiesUtils.get(ConstantUtils.MSG_SUCCESS), true);
		} catch (Exception e) {
			_log.debug(e);
			result.put(ReadFilePropertiesUtils.get(ConstantUtils.MSG_SUCCESS), false);
			return Response.status(HttpURLConnection.HTTP_INTERNAL_ERROR).entity(result).build();
		}
		return Response.status(HttpURLConnection.HTTP_OK).entity(result).build();
	}

}
