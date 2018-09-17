package org.opencps.api.controller.impl;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;

import org.opencps.api.controller.NotificationTemplateManagement;
import org.opencps.api.controller.util.NotificationTemplateUtils;
import org.opencps.api.error.model.ErrorMsg;
import org.opencps.api.notificationtemplate.model.DataSearchModel;
import org.opencps.api.notificationtemplate.model.NotificationtemplateInputModel;
import org.opencps.api.notificationtemplate.model.NotificationtemplateModel;
import org.opencps.api.notificationtemplate.model.NotificationtemplateResults;
import org.opencps.communication.action.NotificationTemplateInterface;
import org.opencps.communication.action.impl.NotificationTemplateActions;
import org.opencps.communication.model.Notificationtemplate;

import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.model.Company;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.search.Document;
import com.liferay.portal.kernel.search.Sort;
import com.liferay.portal.kernel.search.SortFactoryUtil;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.Validator;

import backend.auth.api.exception.BusinessExceptionImpl;

public class NotificationTemplateImpl implements NotificationTemplateManagement {

//	private static final Log _log = LogFactoryUtil.getLog(NotificationTemplateImpl.class);

	@SuppressWarnings("unchecked")
	@Override
	public Response getNotificationtemplates(HttpServletRequest request, HttpHeaders header, Company company,
			Locale locale, User user, ServiceContext serviceContext, DataSearchModel query) {
		NotificationTemplateInterface actions = new NotificationTemplateActions();
		NotificationtemplateResults result = new NotificationtemplateResults();

		try {

			if (query.getEnd() == 0) {

				query.setStart(-1);

				query.setEnd(-1);

			}

			long groupId = GetterUtil.getLong(header.getHeaderString("groupId"));

			LinkedHashMap<String, Object> params = new LinkedHashMap<String, Object>();

			params.put("groupId", String.valueOf(groupId));
			params.put("keywords", query.getKeywords());

			Sort[] sorts = new Sort[] { SortFactoryUtil.create("notificationType_sortable", Sort.STRING_TYPE,
					Boolean.valueOf(query.getOrder())) };

			JSONObject jsonData = actions.getNotificationTemplates(user.getUserId(), company.getCompanyId(), groupId,
					params, sorts, query.getStart(), query.getEnd(), serviceContext);

			result.setTotal(jsonData.getLong("total"));
			result.getNotificationtemplateModel().addAll(
					NotificationTemplateUtils.mapperNotificationtemplateList((List<Document>) jsonData.get("data")));

			return Response.status(200).entity(result).build();

		} catch (Exception e) {
			return BusinessExceptionImpl.processException(e);
		}
	}

	@Override
	public Response read(HttpServletRequest request, HttpHeaders header, Company company, Locale locale, User user,
			ServiceContext serviceContext, String type) {

		NotificationTemplateInterface actions = new NotificationTemplateActions();
		NotificationtemplateModel notificationtemplateModel;

		long groupId = GetterUtil.getLong(header.getHeaderString("groupId"));

		Notificationtemplate notificationtemplate = actions.read(user.getUserId(), groupId, type, serviceContext);

		if (Validator.isNotNull(notificationtemplate)) {

			notificationtemplateModel = NotificationTemplateUtils.mapperNotificationtemplateModel(notificationtemplate);

			return Response.status(200).entity(notificationtemplateModel).build();

		} else {

			ErrorMsg error = new ErrorMsg();

			error.setMessage("not found!");
			error.setCode(404);
			error.setDescription("not found!");

			return Response.status(404).entity(error).build();

		}
	}

	@Override
	public Response update(HttpServletRequest request, HttpHeaders header, Company company, Locale locale, User user,
			ServiceContext serviceContext, String type, NotificationtemplateInputModel input) {
		NotificationTemplateInterface actions = new NotificationTemplateActions();
		NotificationtemplateModel notificationtemplateModel = new NotificationtemplateModel();

		try {

			long groupId = GetterUtil.getLong(header.getHeaderString("groupId"));

			Notificationtemplate notificationtemplate = actions.update(user.getUserId(), groupId, type,
					input.getEmailBody(), input.getEmailSubject(), input.getSendEmail(), input.getTextMessage(),
					input.getTextSMS(), input.getExpireDuration(), input.getUserUrlPattern(),
					input.getGuestUrlPattern(), input.getInterval(), input.getGrouping(), serviceContext);

			notificationtemplateModel = NotificationTemplateUtils.mapperNotificationtemplateModel(notificationtemplate);

			return Response.status(200).entity(notificationtemplateModel).build();

		} catch (Exception e) {
			return BusinessExceptionImpl.processException(e);
		}
	}

	@Override
	public Response delete(HttpServletRequest request, HttpHeaders header, Company company, Locale locale, User user,
			ServiceContext serviceContext, String type) {
		NotificationTemplateInterface actions = new NotificationTemplateActions();

		try {

			long groupId = GetterUtil.getLong(header.getHeaderString("groupId"));

			boolean flag = actions.delete(user.getUserId(), groupId, type, serviceContext);

			if (flag) {

				return Response.status(200).build();

			} else {

				ErrorMsg error = new ErrorMsg();

				error.setMessage("not found!");
				error.setCode(404);
				error.setDescription("not found!");

				return Response.status(404).entity(error).build();

			}

		} catch (Exception e) {
			return BusinessExceptionImpl.processException(e);
		}
	}

	@Override
	public Response create(HttpServletRequest request, HttpHeaders header, Company company, Locale locale, User user,
			ServiceContext serviceContext, NotificationtemplateInputModel input) {
		NotificationTemplateInterface actions = new NotificationTemplateActions();
		NotificationtemplateModel notificationtemplateModel = new NotificationtemplateModel();

		try {

			long groupId = GetterUtil.getLong(header.getHeaderString("groupId"));

			Notificationtemplate notificationtemplate = actions.create(user.getUserId(), groupId, input.getNotificationType(),
					input.getEmailBody(), input.getEmailSubject(), input.getSendEmail(), input.getTextMessage(),
					input.getTextSMS(), input.getExpireDuration(), input.getUserUrlPattern(),
					input.getGuestUrlPattern(), input.getInterval(), input.getGrouping(), serviceContext);

			notificationtemplateModel = NotificationTemplateUtils.mapperNotificationtemplateModel(notificationtemplate);

			return Response.status(200).entity(notificationtemplateModel).build();

		} catch (Exception e) {
			return BusinessExceptionImpl.processException(e);
		}
	}

}
