package org.opencps.api.controller.impl;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;

import org.apache.commons.httpclient.util.HttpURLConnection;
import org.opencps.api.controller.RegistrationFormManagement;
import org.opencps.api.controller.exception.ErrorMsg;
import org.opencps.api.controller.util.DeliverableUtils;
import org.opencps.api.controller.util.RegistrationFormUtils;
import org.opencps.api.deliverable.model.DeliverableResultModel;
import org.opencps.api.registrationform.model.RegistrationFormDetailModel;
import org.opencps.api.registrationform.model.RegistrationFormInfoModel;
import org.opencps.api.registrationform.model.RegistrationFormResultsModel;
import org.opencps.auth.api.BackendAuth;
import org.opencps.auth.api.BackendAuthImpl;
import org.opencps.auth.api.exception.UnauthenticationException;
import org.opencps.auth.api.exception.UnauthorizationException;
import org.opencps.dossiermgt.action.RegistrationFormActions;
import org.opencps.dossiermgt.action.impl.RegistrationFormActionsImpl;
import org.opencps.dossiermgt.constants.DeliverableTerm;
import org.opencps.dossiermgt.constants.RegistrationFormTerm;
import org.opencps.dossiermgt.model.Registration;
import org.opencps.dossiermgt.model.RegistrationForm;
import org.opencps.dossiermgt.service.RegistrationFormLocalServiceUtil;
import org.opencps.dossiermgt.service.RegistrationLocalServiceUtil;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Company;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.search.Document;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.search.Sort;
import com.liferay.portal.kernel.search.SortFactoryUtil;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.Validator;

public class RegistrationFormManagementImpl implements RegistrationFormManagement {
	private static Log _log = LogFactoryUtil.getLog(RegistrationFormManagementImpl.class);

	@Override
	public Response deleteFormbyRegId(HttpServletRequest request, HttpHeaders header, Company company, Locale locale,
			User user, ServiceContext serviceContext, long id, String referenceUid) {
		BackendAuth auth = new BackendAuthImpl();

		try {
			if (!auth.isAuth(serviceContext)) {
				throw new UnauthenticationException();
			}
//			long groupId = GetterUtil.getLong(header.getHeaderString("groupId"));
			RegistrationFormActions action = new RegistrationFormActionsImpl();

			action.deleteRegistrationForm(referenceUid);

			return Response.status(HttpURLConnection.HTTP_NO_CONTENT).build();

		} catch (Exception e) {
			return processException(e);
		}

	}

	@Override
	public Response getformdatabyRegidRefid(HttpServletRequest request, HttpHeaders header, Company company,
			Locale locale, User user, ServiceContext serviceContext, long registrationId, String referenceUid)
			throws PortalException {
		BackendAuth auth = new BackendAuthImpl();
		try {
			if (!auth.isAuth(serviceContext)) {
				throw new UnauthenticationException();
			}
			long groupId = GetterUtil.getLong(header.getHeaderString("groupId"));
			RegistrationForm registrationForm = RegistrationFormLocalServiceUtil.findFormbyRegidRefid(groupId,
					registrationId, referenceUid);

			return Response.status(200).entity(registrationForm.getFormData()).build();

		} catch (Exception e) {
			return processException(e);
		}
	}

	@Override
	public Response updateRegFormFormData(HttpServletRequest request, HttpHeaders header, Company company,
			Locale locale, User user, ServiceContext serviceContext, long registrationId, String referenceUid,
			String formData) throws PortalException {
		BackendAuth auth = new BackendAuthImpl();
		try {
			if (!auth.isAuth(serviceContext)) {
				throw new UnauthenticationException();
			}
			long groupId = GetterUtil.getLong(header.getHeaderString("groupId"));
			RegistrationFormActions action = new RegistrationFormActionsImpl();

			RegistrationForm registrationForm = action.updateRegFormFormData(groupId, registrationId, referenceUid,
					formData, serviceContext);

			RegistrationFormDetailModel result = RegistrationFormUtils
					.mappingToRegistrationFormDetailModel(registrationForm);

			return Response.status(200).entity(result).build();

		} catch (Exception e) {
			return processException(e);
		}
	}

	@Override
	public Response getformScriptbyRegidRefid(HttpServletRequest request, HttpHeaders header, Company company,
			Locale locale, User user, ServiceContext serviceContext, long registrationId, String referenceUid)
			throws PortalException {
		BackendAuth auth = new BackendAuthImpl();
		try {
			if (!auth.isAuth(serviceContext)) {
				throw new UnauthenticationException();
			}
			long groupId = GetterUtil.getLong(header.getHeaderString("groupId"));
			RegistrationForm registrationForm = RegistrationFormLocalServiceUtil.findFormbyRegidRefid(groupId,
					registrationId, referenceUid);

			return Response.status(200).entity(registrationForm.getFormScript()).build();

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
			    _log.error(e);
			    
				error.setMessage("No Content.");
				error.setCode(HttpURLConnection.HTTP_FORBIDDEN);
				error.setDescription("No Content.");

				return Response.status(HttpURLConnection.HTTP_FORBIDDEN).entity(error).build();

			}
		}
	}

	@Override
	public Response registrationSyncsForm(HttpServletRequest request, HttpHeaders header, Company company,
			Locale locale, User user, ServiceContext serviceContext, String referenceUid, String registrationUUID,
			String formNo, String formName, String formData, String formScript, String formReport, Boolean removed) {
		BackendAuth auth = new BackendAuthImpl();
		try {
			if (!auth.isAuth(serviceContext)) {
				throw new UnauthenticationException();
			}
			long groupId = GetterUtil.getLong(header.getHeaderString("groupId"));
			// TODO sync registration Form
			RegistrationFormLocalServiceUtil.registrationFormSync(groupId, registrationUUID, referenceUid, formNo,
					formName, formData, formScript, formReport, removed, serviceContext);
			return Response.status(200).build();

		} catch (Exception e) {
			return processException(e);
		}
	}


}
