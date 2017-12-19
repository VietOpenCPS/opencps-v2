package org.opencps.api.controller.impl;

import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;

import org.apache.commons.httpclient.util.HttpURLConnection;
import org.opencps.api.controller.RegistrationManagement;
import org.opencps.api.controller.exception.ErrorMsg;
import org.opencps.api.controller.util.RegistrationFormUtils;
import org.opencps.api.controller.util.RegistrationUtils;
import org.opencps.api.registration.model.RegistrationDetailModel;
import org.opencps.api.registration.model.RegistrationDetailResultModel;
import org.opencps.api.registration.model.RegistrationInputModel;
import org.opencps.api.registration.model.RegistrationResultsModel;
import org.opencps.api.registrationform.model.RegistrationFormDetailModel;
import org.opencps.api.registrationform.model.RegistrationFormInputModel;
import org.opencps.api.registrationform.model.RegistrationFormModel;
import org.opencps.api.registrationform.model.RegistrationFormResultsModel;
import org.opencps.auth.api.BackendAuth;
import org.opencps.auth.api.BackendAuthImpl;
import org.opencps.auth.api.exception.UnauthenticationException;
import org.opencps.auth.api.exception.UnauthorizationException;
import org.opencps.dossiermgt.action.RegistrationActions;
import org.opencps.dossiermgt.action.RegistrationFormActions;
import org.opencps.dossiermgt.action.impl.RegistrationActionsImpl;
import org.opencps.dossiermgt.action.impl.RegistrationFormActionsImpl;
import org.opencps.dossiermgt.model.Registration;
import org.opencps.dossiermgt.model.RegistrationForm;
import org.opencps.dossiermgt.service.RegistrationLocalServiceUtil;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Company;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.GetterUtil;

public class RegistrationManagementImpl implements RegistrationManagement {
	Log _log = LogFactoryUtil.getLog(RegistrationManagementImpl.class);

	@Override
	public Response getList(HttpServletRequest request, HttpHeaders header, Company company, Locale locale, User user,
			ServiceContext serviceContext) {
		BackendAuth auth = new BackendAuthImpl();
		int start = -1, end = -1;
		try {

			if (!auth.isAuth(serviceContext)) {
				throw new UnauthenticationException();
			}

			RegistrationResultsModel results = new RegistrationResultsModel();

			List<Registration> lstRegistrationModel = RegistrationLocalServiceUtil.getRegistrations(start, end);

			results.setTotal(RegistrationLocalServiceUtil.getRegistrationsCount());
			results.getData().addAll(RegistrationUtils.mappingToRegistrationResultsModel(lstRegistrationModel));

			return Response.status(200).entity(results).build();

		} catch (Exception e) {
			_log.error(e);
			return processException(e);
		}
	}

	@Override
	public Response add(HttpServletRequest request, HttpHeaders header, Company company, Locale locale, User user,
			ServiceContext serviceContext, RegistrationInputModel input) {
		RegistrationDetailModel result = null;
		try {
			RegistrationActions action = new RegistrationActionsImpl();
			long groupId = GetterUtil.getLong(header.getHeaderString("groupId"));

			Registration registration = action.insert(groupId, input.getApplicantName(), input.getApplicantIdType(),
					input.getApplicantIdNo(), input.getApplicantIdDate(), input.getAddress(), input.getCityCode(),
					input.getCityName(), input.getDistrictCode(), input.getDistrictName(), input.getWardCode(),
					input.getWardName(), input.getContactName(), input.getContactTelNo(), input.getContactEmail(),
					input.getGovAgencyCode(), input.getGovAgencyName(), input.getRegistrationState(),
					input.getRegistrationClass(), serviceContext);

			result = RegistrationUtils.mappingToRegistrationDetailModel(registration);
			return Response.status(200).entity(result).build();
		} catch (Exception e) {
			_log.error(e);
			return processException(e);
		}

	}

	@Override
	public Response getDetail(HttpServletRequest request, HttpHeaders header, Company company, Locale locale, User user,
			ServiceContext serviceContext, Long id) {
		BackendAuth auth = new BackendAuthImpl();
		try {
			if (!auth.isAuth(serviceContext)) {
				throw new UnauthenticationException();
			}
			RegistrationActions action = new RegistrationActionsImpl();

			Registration detail = action.getDetail(id);

			RegistrationDetailResultModel result = RegistrationUtils.mappingToRegistrationDetailResultModel(detail);
			return Response.status(200).entity(result).build();
		} catch (Exception e) {
			_log.error(e);
			return processException(e);
		}
	}

	@Override
	public Response update(HttpServletRequest request, HttpHeaders header, Company company, Locale locale, User user,
			ServiceContext serviceContext, RegistrationInputModel input, long registrationId) {
		
		try {
			long groupId = GetterUtil.getLong(header.getHeaderString("groupId"));
			RegistrationActions action = new RegistrationActionsImpl();

			Registration registration = action.updateRegistration(groupId, registrationId, input.getApplicantName(),
					input.getApplicantIdType(), input.getApplicantIdNo(), input.getApplicantIdDate(),
					input.getAddress(), input.getCityCode(), input.getCityName(), input.getDistrictCode(),
					input.getDistrictName(), input.getWardCode(), input.getWardName(), input.getContactName(),
					input.getContactTelNo(), input.getContactEmail(), input.getGovAgencyCode(),
					input.getGovAgencyName(), input.getRegistrationState(), input.getRegistrationClass(),
					serviceContext);

			RegistrationDetailModel result = RegistrationUtils.mappingToRegistrationDetailModel(registration);
			
			return Response.status(200).entity(result).build();
		} catch (Exception e) {
			_log.error(e);
			return processException(e);
		}
	}

	@Override
	public Response delete(Long id) {
		try {
			RegistrationActions action = new RegistrationActionsImpl();
			action.delete(id);
			return Response.status(200).entity("Success").build();
		} catch (Exception e) {
			_log.error(e);
			return processException(e);
		}

	}

	@Override
	public Response getFormsbyRegId(long id) throws PortalException {

		try {

			RegistrationFormActions action = new RegistrationFormActionsImpl();

			RegistrationFormResultsModel result = new RegistrationFormResultsModel();

			List<RegistrationForm> lstRegistrationForm = action.getFormbyRegId(id);
			int total = lstRegistrationForm.size();

			List<RegistrationFormModel> lstRegistrationFormModel = RegistrationFormUtils
					.mappingToRegistrationFormResultsModel(lstRegistrationForm);

			result.setTotal(total);
			result.getData().addAll(lstRegistrationFormModel);

			return Response.status(200).entity(result).build();

		} catch (Exception e) {
			_log.error(e);
			return processException(e);
		}
	}

	@Override
	public Response addRegistrationForm(HttpServletRequest request, HttpHeaders header, Company company, Locale locale,
			User user, ServiceContext serviceContext, RegistrationFormInputModel input) {
		BackendAuth auth = new BackendAuthImpl();
		RegistrationFormDetailModel result = null;
		try {

			if (!auth.isAuth(serviceContext)) {
				throw new UnauthenticationException();
			}

			RegistrationFormActions action = new RegistrationFormActionsImpl();
			long groupId = GetterUtil.getLong(header.getHeaderString("groupId"));

			RegistrationForm registrationForm = action.insert(groupId, input.getRegistrationId(),
					input.getReferenceUid(), input.getFormNo(), input.getFormName(), input.getFormData(),
					input.getFormScript(), input.getFormReport(), input.getFileEntryId(), input.isIsNew(),
					input.isRemoved(), serviceContext);

			result = RegistrationFormUtils.mappingToRegistrationFormDetailModel(registrationForm);
			return Response.status(200).entity(result).build();
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
