package org.opencps.api.controller.impl;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
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
import org.opencps.dossiermgt.constants.RegistrationTerm;
import org.opencps.dossiermgt.model.Registration;
import org.opencps.dossiermgt.model.RegistrationForm;
import org.opencps.dossiermgt.service.RegistrationLocalServiceUtil;

import com.liferay.portal.kernel.exception.PortalException;
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

public class RegistrationManagementImpl implements RegistrationManagement {
	Log _log = LogFactoryUtil.getLog(RegistrationManagementImpl.class);

	@GET
	@Path("/registrations")
	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON, MediaType.APPLICATION_FORM_URLENCODED })
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON, MediaType.APPLICATION_FORM_URLENCODED })
	public Response getList(@Context ServiceContext serviceContext, @DefaultValue("") @QueryParam("stage") String stage,
			@DefaultValue("") @QueryParam("agency") String agency, @DefaultValue("") @QueryParam("owner") String owner,
			@DefaultValue("") @QueryParam("registrationClass") String registrationClass,
			@DefaultValue("") @QueryParam("submitting") String submitting,
			@DefaultValue("") @QueryParam("keyword") String keyword, @DefaultValue("") @QueryParam("sort") String sort,
			@Context HttpHeaders header) {

		BackendAuth auth = new BackendAuthImpl();
		RegistrationActions actions = new RegistrationActionsImpl();
		try {

			if (!auth.isAuth(serviceContext)) {
				throw new UnauthenticationException();
			}
			long groupId = GetterUtil.getLong(header.getHeaderString("groupId"));

			LinkedHashMap<String, Object> params = new LinkedHashMap<String, Object>();

			params.put(Field.GROUP_ID, String.valueOf(groupId));
			params.put(Field.KEYWORD_SEARCH, keyword);
			params.put(RegistrationTerm.REGISTRATIONSTATE, stage);
			params.put(RegistrationTerm.GOV_AGENCY_CODE, agency);
			params.put(RegistrationTerm.OWNER, owner);
			params.put(RegistrationTerm.REGISTRATION_CLASS, registrationClass);
			params.put(RegistrationTerm.SUBMITTING, submitting);

			Sort[] sorts = new Sort[] { SortFactoryUtil.create(sort + "_sortable", Sort.STRING_TYPE, false) };

			JSONObject jsonData = actions.getRegistrations(serviceContext.getUserId(), serviceContext.getCompanyId(),
					groupId, params, sorts, -1, -1, serviceContext);

			RegistrationResultsModel results = new RegistrationResultsModel();
			// long userId = serviceContext.getUserId();
			// List<Registration> lstRegistrationModel =
			// RegistrationLocalServiceUtil.getRegistrations(start, end);
			//
			results.setTotal(jsonData.getInt("total"));
			//results.getData().addAll(RegistrationUtils
			//		.mappingToRegistrationResultModel((List<Document>) jsonData.get("data"), serviceContext));
			
			results.getData().addAll(RegistrationUtils
					.mappingRegistrationToRegistrationResultModel((List<Registration>) jsonData.get("data"), serviceContext));

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
	public Response delete(long id) {
		try {
			RegistrationActions action = new RegistrationActionsImpl();

			Registration registration = action.delete(id);

			RegistrationDetailModel result = RegistrationUtils.mappingToRegistrationDetailModel(registration);

			return Response.status(200).entity(result).build();
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
			User user, ServiceContext serviceContext, RegistrationFormInputModel input, long registrationId,
			String formNo) {
		BackendAuth auth = new BackendAuthImpl();
		RegistrationFormDetailModel result = null;
		try {

			if (!auth.isAuth(serviceContext)) {
				throw new UnauthenticationException();
			}

			RegistrationFormActions action = new RegistrationFormActionsImpl();
			long groupId = GetterUtil.getLong(header.getHeaderString("groupId"));

			long fileEntryId = getfileEntryId(input.getFormData(), input.getFormScript(), input.getFormReport());

			RegistrationForm registrationForm = action.insert(groupId, registrationId, input.getReferenceUid(), formNo,
					input.getFormName(), input.getFormData(), input.getFormScript(), input.getFormReport(), fileEntryId,
					input.isIsNew(), input.isRemoved(), serviceContext);

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

	public long getfileEntryId(String formdata, String formScript, String formReport) {

		long fileEntryId = 0;

		return fileEntryId;
	}

	@Override
	public Response registrationSyncs(HttpServletRequest request, HttpHeaders header, Company company, Locale locale,
			User user, ServiceContext serviceContext, RegistrationInputModel input, boolean submitting, String uuid) {
		BackendAuth auth = new BackendAuthImpl();
		try {

			if (!auth.isAuth(serviceContext)) {
				throw new UnauthenticationException();
			}

			long groupId = GetterUtil.getLong(header.getHeaderString("groupId"));

			RegistrationLocalServiceUtil.registrationSync(groupId, uuid, input.getApplicantName(),
					input.getApplicantIdType(), input.getApplicantIdNo(), input.getApplicantIdDate(),
					input.getAddress(), input.getCityCode(), input.getCityName(), input.getDistrictCode(),
					input.getDistrictName(), input.getWardCode(), input.getWardName(), input.getContactName(),
					input.getContactTelNo(), input.getContactEmail(), input.getGovAgencyCode(),
					input.getGovAgencyName(), input.getRegistrationState(), input.getRegistrationClass(),
					serviceContext);

			return Response.status(200).build();
		} catch (Exception e) {
			_log.error(e);
			return processException(e);
		}
	}
}
