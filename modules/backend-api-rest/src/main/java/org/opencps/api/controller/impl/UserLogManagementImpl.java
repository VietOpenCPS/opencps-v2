package org.opencps.api.controller.impl;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;

import org.apache.commons.httpclient.util.HttpURLConnection;
import org.opencps.api.controller.UserLogManagement;
import org.opencps.api.controller.util.DeliverableUtils;
import org.opencps.api.deliverable.model.DeliverableInputModel;
import org.opencps.api.userlog.model.UserLogInputModel;
import org.opencps.api.userlog.model.UserLogSearchModel;
import org.opencps.auth.api.BackendAuth;
import org.opencps.auth.api.BackendAuthImpl;
import org.opencps.auth.api.exception.UnauthenticationException;
import org.opencps.dossiermgt.action.DeliverableActions;
import org.opencps.dossiermgt.action.impl.DeliverableActionsImpl;
import org.opencps.dossiermgt.model.Deliverable;

import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.model.Company;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.GetterUtil;

public class UserLogManagementImpl implements UserLogManagement{

	@Override
	public Response getUserLogs(HttpServletRequest request, HttpHeaders header, Company company, Locale locale,
			User user, ServiceContext serviceContext, UserLogSearchModel query) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Response addUserLog(HttpServletRequest request, HttpHeaders header, Company company, Locale locale,
			User user, ServiceContext serviceContext, UserLogInputModel input) {
		// TODO Add Deliverable Type
		BackendAuth auth = new BackendAuthImpl();

		long groupId = GetterUtil.getLong(header.getHeaderString("groupId"));

		try {
			if (!auth.isAuth(serviceContext)) {
				throw new UnauthenticationException();
			}

			DeliverableActions action = new DeliverableActionsImpl();
			//
//			String deliverableType = input.getDeliverableType();
//			String deliverableCode = input.getDeliverableCode();
//			String govAgencyCode = input.getGovAgencyCode();
//			String applicantIdNo = input.getApplicantIdNo();
//			String applicantName = input.getApplicantName();
//			String subject = input.getSubject();
//			String issueDate = input.getIssueDate();
//			String expireDate = input.getExpireDate();
//			String revalidate = input.getRevalidate();
//			String deliverableState = input.getDeliverableState();
			//
//			Deliverable deliverable = action.addDeliverable(groupId, deliverableType, deliverableCode, 
//					govAgencyCode, applicantIdNo, applicantName, subject, issueDate, expireDate,
//					revalidate, deliverableState, serviceContext);

			DeliverableInputModel result = DeliverableUtils.mappingToDeliverablesModel(null);

			return Response.status(200).entity(JSONFactoryUtil.looseSerialize(result)).build();
		} catch (Exception e) {
			return Response.status(HttpURLConnection.HTTP_INTERNAL_ERROR).entity(e).build();
		}
	}

}
