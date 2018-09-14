package org.opencps.api.controller.impl;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;

import org.opencps.api.controller.DeliverablesLogManagement;

import com.liferay.portal.kernel.model.Company;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.ServiceContext;

public class DeliverablesLogManagementImpl implements DeliverablesLogManagement {

//	@Override
//	public Response getDeliverables(Long id) {
//		try {
//			DeliverableLogActions action = new DeliverableLogActionsImpl();
//			DeliverableLog deliverable = action.getDeliverableLog(id);
//			return Response.status(200).entity(deliverable).build();
//		} catch (Exception e) {
//			return Response.status(HttpURLConnection.HTTP_INTERNAL_ERROR).entity(e).build();
//		}
//	}

	@Override
	public Response getDeliverableLog(HttpServletRequest request, HttpHeaders header, Company company, Locale locale,
			User user, ServiceContext serviceContext, Long id) {
		// TODO Auto-generated method stub
		return null;
	}

}
