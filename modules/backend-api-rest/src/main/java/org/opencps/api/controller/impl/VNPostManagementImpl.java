package org.opencps.api.controller.impl;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;

import org.apache.commons.httpclient.util.HttpURLConnection;
import org.opencps.api.controller.VNPostManagement;
import org.opencps.api.controller.exception.ErrorMsg;
import org.opencps.api.dossiervnpost.model.DossierVnpostModel;
import org.opencps.auth.api.BackendAuth;
import org.opencps.auth.api.BackendAuthImpl;
import org.opencps.auth.api.exception.UnauthenticationException;
import org.opencps.auth.api.exception.UnauthorizationException;
import org.opencps.dossiermgt.action.VNPostAction;
import org.opencps.dossiermgt.action.impl.VNPostActionImpl;

import com.liferay.portal.kernel.model.Company;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.GetterUtil;

public class VNPostManagementImpl implements VNPostManagement {

	@Override
	public Response create(HttpServletRequest request, HttpHeaders header, Company company, Locale locale, User user,
			ServiceContext serviceContext, DossierVnpostModel input) {
		BackendAuth auth = new BackendAuthImpl();
		VNPostAction action = new VNPostActionImpl();
		long groupId = GetterUtil.getLong(header.getHeaderString("groupId"));
		try {
			if (!auth.isAuth(serviceContext)) {
				throw new UnauthenticationException();
			}
			String message = action.postOrder(input.getFullName(), input.getPhoneNumber(), input.getDeliverAddress(),
					input.getCityCode(), input.getCityName(), input.getDistrictCode(), input.getDistrictName(),
					input.getWardCode(), input.getWardName(), input.getDossierId(), groupId);
			return Response.status(200).entity(message).build();
		} catch (Exception e) {
			return processException(e);
		}
	}

	private Response processException(Exception e) {
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

				error.setMessage("No Content.");
				error.setCode(HttpURLConnection.HTTP_FORBIDDEN);
				error.setDescription("No Content.");

				return Response.status(HttpURLConnection.HTTP_FORBIDDEN).entity(error).build();

			}
		}
	}

}
