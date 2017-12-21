package org.opencps.api.controller.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;

import org.apache.commons.httpclient.util.HttpURLConnection;
import org.opencps.api.controller.RegistrationLogManagement;
import org.opencps.api.controller.exception.ErrorMsg;
import org.opencps.api.controller.util.RegistrationFormUtils;
import org.opencps.api.controller.util.RegistrationLogUtils;
import org.opencps.api.controller.util.RegistrationUtils;
import org.opencps.api.registration.model.RegistrationDetailModel;
import org.opencps.api.registration.model.RegistrationDetailResultModel;
import org.opencps.api.registration.model.RegistrationInputModel;
import org.opencps.api.registration.model.RegistrationResultsModel;
import org.opencps.api.registrationform.model.RegistrationFormDetailModel;
import org.opencps.api.registrationform.model.RegistrationFormInputModel;
import org.opencps.api.registrationform.model.RegistrationFormModel;
import org.opencps.api.registrationform.model.RegistrationFormResultsModel;
import org.opencps.api.registrationlog.model.RegistrationLogResultsModel;
import org.opencps.auth.api.BackendAuth;
import org.opencps.auth.api.BackendAuthImpl;
import org.opencps.auth.api.exception.UnauthenticationException;
import org.opencps.auth.api.exception.UnauthorizationException;
import org.opencps.dossiermgt.action.RegistrationActions;
import org.opencps.dossiermgt.action.RegistrationFormActions;
import org.opencps.dossiermgt.action.RegistrationLogActions;
import org.opencps.dossiermgt.action.impl.RegistrationActionsImpl;
import org.opencps.dossiermgt.action.impl.RegistrationFormActionsImpl;
import org.opencps.dossiermgt.action.impl.RegistrationLogActionsImpl;
import org.opencps.dossiermgt.model.Registration;
import org.opencps.dossiermgt.model.RegistrationForm;
import org.opencps.dossiermgt.model.RegistrationLog;
import org.opencps.dossiermgt.service.RegistrationLocalServiceUtil;
import org.opencps.dossiermgt.service.RegistrationLogLocalServiceUtil;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Company;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.GetterUtil;

public class RegistrationLogManagementImpl implements RegistrationLogManagement {
	Log _log = LogFactoryUtil.getLog(RegistrationLogManagementImpl.class);

	@Override
	public Response getRegistrationLogsbyRegId(HttpServletRequest request, HttpHeaders header, Company company, Locale locale, User user,
			ServiceContext serviceContext, long registrationId) {
		BackendAuth auth = new BackendAuthImpl();
		long groupId = GetterUtil.getLong(header.getHeaderString("groupId"));
		try {

			if (!auth.isAuth(serviceContext)) {
				throw new UnauthenticationException();
			}
			
			RegistrationLogActions action = new RegistrationLogActionsImpl();

			RegistrationLogResultsModel results = new RegistrationLogResultsModel();

			List<RegistrationLog> lstRegistrationLog = action.getRegistrationLogbyId(groupId, registrationId);

			results.setTotal(lstRegistrationLog.size());
			results.getData().addAll(RegistrationLogUtils.mappingToRegistrationLoggData(lstRegistrationLog));

			return Response.status(200).entity(results).build();

		} catch (Exception e) {
			_log.error(e);
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
