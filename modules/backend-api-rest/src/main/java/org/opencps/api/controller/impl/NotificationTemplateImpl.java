package org.opencps.api.controller.impl;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.Response;

import org.apache.cxf.headers.Header;
import org.opencps.api.controller.NotificationTemplate;
import org.opencps.api.notificationtemplate.model.NotificationTemplatesInputModel;

public class NotificationTemplateImpl implements NotificationTemplate{

	@Override
	public Response getNotificationTemplate(HttpServletRequest request, Header header) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Response getNotificationTemplate(HttpServletRequest request, Header header, String type) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Response updateNotificationTemplates(HttpServletRequest request, Header header, String type,
			NotificationTemplatesInputModel input) {
		// TODO Auto-generated method stub
		return null;
	}

}
