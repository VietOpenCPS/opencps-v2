package org.opencps.api.controller.impl;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;

import org.apache.commons.httpclient.util.HttpURLConnection;
import org.opencps.api.controller.DigitalSignatureManagement;
import org.opencps.api.controller.exception.ErrorMsg;
import org.opencps.api.digitalsignature.model.DigitalSignatureInputModel;
import org.opencps.auth.api.BackendAuth;
import org.opencps.auth.api.BackendAuthImpl;
import org.opencps.auth.api.exception.UnauthenticationException;
import org.opencps.auth.api.exception.UnauthorizationException;
import org.opencps.dossiermgt.action.DigitalSignatureActions;
import org.opencps.dossiermgt.action.impl.DigitalSignatureActionsImpl;

import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.model.Company;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.StringPool;

public class DigitalSignatureManagementImpl implements DigitalSignatureManagement{

	@Override
	public Response getByTokens(HttpServletRequest request, HttpHeaders header, Company company, Locale locale,
			User user, ServiceContext serviceContext, long id, DigitalSignatureInputModel input) {
		// TODO Add Deliverable Type
		BackendAuth auth = new BackendAuthImpl();

//		long groupId = GetterUtil.getLong(header.getHeaderString("groupId"));

		try {
			if (!auth.isAuth(serviceContext)) {
				throw new UnauthenticationException();
			}

			DigitalSignatureActions action = new DigitalSignatureActionsImpl();
			//

			String strId = input.getStrIdArr();

			String[] idSplit = strId.split(StringPool.SEMICOLON);
			
			JSONObject results = action.createHashSignature(user, id, idSplit);
//			Deliverable deliverable = action.addDeliverable(groupId, deliverableType, deliverableCode, 
//					govAgencyCode, applicantIdNo, applicantName, subject, issueDate, expireDate,
//					revalidate, deliverableState, serviceContext);
//
//			DeliverableInputModel result = DeliverableUtils.mappingToDeliverablesModel(deliverable);

			return Response.status(200).entity(JSONFactoryUtil.looseSerialize(results)).build();

		} catch (Exception e) {
			ErrorMsg error = new ErrorMsg();

			if (e instanceof UnauthenticationException) {
				error.setMessage("Non-Authoritative Information.");
				error.setCode(HttpURLConnection.HTTP_NOT_AUTHORITATIVE);
				error.setDescription("Non-Authoritative Information.");

				return Response.status(HttpURLConnection.HTTP_NOT_AUTHORITATIVE).entity(error).build();
			} else {
				if (e instanceof UnauthorizationException) {
					error.setMessage("Unauthorized.");
					error.setCode(HttpURLConnection.HTTP_NOT_AUTHORITATIVE);
					error.setDescription("Unauthorized.");

					return Response.status(HttpURLConnection.HTTP_UNAUTHORIZED).entity(error).build();

				} else {

					error.setMessage("Internal Server Error");
					error.setCode(HttpURLConnection.HTTP_FORBIDDEN);
					error.setDescription(e.getMessage());

					return Response.status(HttpURLConnection.HTTP_INTERNAL_ERROR).entity(error).build();

				}
			}
		}
	}

	@Override
	public Response completeSignature(HttpServletRequest request, HttpHeaders header, Company company, Locale locale,
			User user, ServiceContext serviceContext, long id, DigitalSignatureInputModel input) {
		// TODO Add Deliverable Type
		BackendAuth auth = new BackendAuthImpl();

//				long groupId = GetterUtil.getLong(header.getHeaderString("groupId"));

		try {
			if (!auth.isAuth(serviceContext)) {
				throw new UnauthenticationException();
			}

			DigitalSignatureActions action = new DigitalSignatureActionsImpl();

			String sign = input.getSign();
			String signFieldName = input.getSignFieldName();
			String fileName = input.getFileName();
			JSONObject results = action.completeSignature(user, id, sign, signFieldName, fileName);
//					Deliverable deliverable = action.addDeliverable(groupId, deliverableType, deliverableCode, 
//							govAgencyCode, applicantIdNo, applicantName, subject, issueDate, expireDate,
//							revalidate, deliverableState, serviceContext);
//
//					DeliverableInputModel result = DeliverableUtils.mappingToDeliverablesModel(deliverable);

			return Response.status(200).entity(JSONFactoryUtil.looseSerialize(results)).build();

		} catch (Exception e) {
			ErrorMsg error = new ErrorMsg();

			if (e instanceof UnauthenticationException) {
				error.setMessage("Non-Authoritative Information.");
				error.setCode(HttpURLConnection.HTTP_NOT_AUTHORITATIVE);
				error.setDescription("Non-Authoritative Information.");

				return Response.status(HttpURLConnection.HTTP_NOT_AUTHORITATIVE).entity(error).build();
			} else {
				if (e instanceof UnauthorizationException) {
					error.setMessage("Unauthorized.");
					error.setCode(HttpURLConnection.HTTP_NOT_AUTHORITATIVE);
					error.setDescription("Unauthorized.");

					return Response.status(HttpURLConnection.HTTP_UNAUTHORIZED).entity(error).build();

				} else {

					error.setMessage("Internal Server Error");
					error.setCode(HttpURLConnection.HTTP_FORBIDDEN);
					error.setDescription(e.getMessage());

					return Response.status(HttpURLConnection.HTTP_INTERNAL_ERROR).entity(error).build();

				}
			}
		}
	}

}
