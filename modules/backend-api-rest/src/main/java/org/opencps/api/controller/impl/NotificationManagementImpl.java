package org.opencps.api.controller.impl;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;

import org.apache.commons.httpclient.util.HttpURLConnection;
import org.opencps.api.controller.NotificationManagement;
import org.opencps.api.notification.model.NotificationSearchModel;

import backend.auth.api.exception.BusinessExceptionImpl;

import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Company;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.model.UserNotificationEvent;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.UserNotificationEventLocalServiceUtil;
import com.liferay.portal.kernel.util.GetterUtil;

public class NotificationManagementImpl implements NotificationManagement{

	private static Log _log = LogFactoryUtil.getLog(NotificationManagementImpl.class);

	@Override
	public Response getNotificationList(HttpServletRequest request, HttpHeaders header, Company company, Locale locale,
			User user, ServiceContext serviceContext, NotificationSearchModel query) {
		JSONObject result = JSONFactoryUtil.createJSONObject();
		JSONArray data = JSONFactoryUtil.createJSONArray();
		JSONObject record = JSONFactoryUtil.createJSONObject();
		DateFormat dateFormatDateTime = new SimpleDateFormat("MM-dd-yyyy HH:mm:ss");

//		ThemeDisplay themeDisplay =
//			(ThemeDisplay) actionRequest.getAttribute(WebKeys.THEME_DISPLAY);
//		Locale locale = themeDisplay.getLocale();
//		TimeZone timeZone = themeDisplay.getTimeZone();
//
//		Integer delta = ParamUtil.getInteger(actionRequest, "delta", 0);
//		Integer cur = ParamUtil.getInteger(actionRequest, "cur", 0);
//		String bbb =
//			ParamUtil.getString(actionRequest, "bbb", StringPool.BLANK);

		// System.out.println(">>>>>>>>>>>>>>>>>>>bbb>>>>>>>>>>" + bbb);
		long userId = user.getUserId();
//		long groupId = GetterUtil.getLong(header.getHeaderString("groupId"));

		try {

			// Mask as read all when click BELL
			markAsReadAll(userId);

			List<UserNotificationEvent> events = UserNotificationEventLocalServiceUtil
					.getArchivedUserNotificationEvents(userId, false, true, -1, -1);

			for (UserNotificationEvent event : events) {

				record = JSONFactoryUtil.createJSONObject(event.getPayload());
				record.put("notificationDate",
					dateFormatDateTime.format(event.getTimestamp()));
//				record.put(
//					"notificationDate_show",
//					Time.getRelativeTimeDescription(
//						event.getTimestamp(), locale, timeZone,
//						dateFormatDateTime));
				record.put("eventId", event.getUserNotificationEventId());

				data.put(record);
			}

			int userNotificationEventsCount = UserNotificationEventLocalServiceUtil.
					getArchivedUserNotificationEventsCount(userId, false, false);

			result.put("total", userNotificationEventsCount);
			result.put("data", data);
//			writeJSON(actionRequest, actionResponse, result);
		} catch (Exception e) {
			return BusinessExceptionImpl.processException(e);
		}
		return Response.status(HttpURLConnection.HTTP_OK).entity(result).build();
	}

	private void markAsReadAll(long userId) throws IOException {

		try {
			List<UserNotificationEvent> events = UserNotificationEventLocalServiceUtil
					.getArchivedUserNotificationEvents(userId, false, false, -1, -1);

			for (UserNotificationEvent event : events) {
				updateArchived(event.getUserNotificationEventId());
			}

			_log.info(">>>>>>>markAsReadAll is OK>>>>>>>>");
		} catch (Exception e) {
			_log.error(e);
			_log.info(">>>>>>>markAsReadAll is ERR>>>>>>>>");
		}

	}

	public void markAsRead(long userNotificationEventId) throws IOException {

			JSONObject result = JSONFactoryUtil.createJSONObject();

			try {

				updateArchived(userNotificationEventId);
				result.put("success", true);
			}
			catch (Exception e) {
				_log.error(e);
				result.put("success", false);
			}

		}

	private void updateArchived(long userNotificationEventId)
			throws Exception {

			UserNotificationEvent userNotificationEvent =
				UserNotificationEventLocalServiceUtil.fetchUserNotificationEvent(
					userNotificationEventId);
			if (userNotificationEvent == null) {
				return;
			}

			userNotificationEvent.setArchived(true);

			UserNotificationEventLocalServiceUtil.updateUserNotificationEvent(
				userNotificationEvent);
		}

	@Override
	public Response countTotalNotifications(HttpServletRequest request, HttpHeaders header, Company company, Locale locale,
			User user, ServiceContext serviceContext, NotificationSearchModel query) {

		JSONObject result = JSONFactoryUtil.createJSONObject();
		long userId = user.getUserId();

		try {

			int userNotificationEventsCount = UserNotificationEventLocalServiceUtil
					.getArchivedUserNotificationEventsCount(userId, false, false);

			result.put("total", userNotificationEventsCount);
		} catch (Exception e) {
			_log.error(e);
			result.put("total", 0);
			return Response.status(HttpURLConnection.HTTP_INTERNAL_ERROR).entity(result).build();
		}

		return Response.status(HttpURLConnection.HTTP_OK).entity(result).build();

	}

	@Override
	public Response deleteNotification(HttpServletRequest request, HttpHeaders header, Company company, Locale locale,
			User user, ServiceContext serviceContext, String id) {

		JSONObject result = JSONFactoryUtil.createJSONObject();
		long userNotificationEventId = GetterUtil.getLong(id);

		try {

			UserNotificationEventLocalServiceUtil.deleteUserNotificationEvent(userNotificationEventId);
			result.put("success", true);
		} catch (Exception e) {
			_log.error(e);
			result.put("success", false);
			return Response.status(HttpURLConnection.HTTP_INTERNAL_ERROR).entity(result).build();
		}
		return Response.status(HttpURLConnection.HTTP_OK).entity(result).build();
	}

}
