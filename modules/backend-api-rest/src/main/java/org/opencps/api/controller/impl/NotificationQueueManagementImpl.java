package org.opencps.api.controller.impl;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;

import org.opencps.api.controller.NotificationQueueManagement;
import org.opencps.api.controller.util.NotificationTemplateUtils;
import org.opencps.api.controller.exception.ErrorMsg;
import org.opencps.api.notificationtemplate.model.DataSearchModel;
import org.opencps.api.notificationtemplate.model.NotificationQueueResults;
import org.opencps.api.notificationtemplate.model.NotificationtemplateResults;
import org.opencps.communication.action.NotificationTemplateInterface;
import org.opencps.communication.action.impl.NotificationTemplateActions;

import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Company;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.search.Document;
import com.liferay.portal.kernel.search.Sort;
import com.liferay.portal.kernel.search.SortFactoryUtil;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.GetterUtil;

public class NotificationQueueManagementImpl implements NotificationQueueManagement {

	private static final Log _log = LogFactoryUtil.getLog(NotificationQueueManagementImpl.class);

	@Override
	public Response getNotificationQueues(HttpServletRequest request, HttpHeaders header, Company company,
			Locale locale, User user, ServiceContext serviceContext, DataSearchModel query) {
		NotificationTemplateInterface actions = new NotificationTemplateActions();
		NotificationQueueResults result = new NotificationQueueResults();

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
					Boolean.getBoolean(query.getOrder())) };

			JSONObject jsonData = actions.getNotificationTemplates(user.getUserId(), company.getCompanyId(), groupId,
					params, sorts, query.getStart(), query.getEnd(), serviceContext);

			result.setTotal(jsonData.getLong("total"));
			result.getNotificationQueueModel().addAll(
					NotificationTemplateUtils.mapperNotificationQueueList((List<Document>) jsonData.get("data")));

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
