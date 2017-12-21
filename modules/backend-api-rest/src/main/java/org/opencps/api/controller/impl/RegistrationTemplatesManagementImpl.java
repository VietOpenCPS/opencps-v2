package org.opencps.api.controller.impl;

import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;

import org.apache.commons.httpclient.util.HttpURLConnection;
import org.opencps.api.controller.RegistrationTemplatesManagement;
import org.opencps.api.controller.exception.ErrorMsg;
import org.opencps.api.controller.util.RegistrationTemplatesUtils;
import org.opencps.api.registrationtemplate.model.RegistrationTemplateDetailModel;
import org.opencps.api.registrationtemplate.model.RegistrationTemplateFormReportInputUpdateModel;
import org.opencps.api.registrationtemplate.model.RegistrationTemplateFormScriptInputUpdateModel;
import org.opencps.api.registrationtemplate.model.RegistrationTemplateInputModel;
import org.opencps.api.registrationtemplate.model.RegistrationTemplateSampleDataInputUpdateModel;
import org.opencps.api.registrationtemplate.model.RegistrationTemplatesResultsModel;
import org.opencps.auth.api.BackendAuth;
import org.opencps.auth.api.BackendAuthImpl;
import org.opencps.auth.api.exception.UnauthenticationException;
import org.opencps.auth.api.exception.UnauthorizationException;
import org.opencps.dossiermgt.action.RegistrationTemplatesActions;
import org.opencps.dossiermgt.action.impl.RegistrationTemplatesActionsImpl;
import org.opencps.dossiermgt.model.RegistrationTemplates;
import org.opencps.dossiermgt.service.RegistrationTemplatesLocalServiceUtil;

import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.model.Company;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.GetterUtil;

public class RegistrationTemplatesManagementImpl implements RegistrationTemplatesManagement {

	@Override
	public Response getRegistrationTemplates(HttpServletRequest request, HttpHeaders header, Company company,
			Locale locale, User user, ServiceContext serviceContext) {
		// TODO Get All RegistrationTemplates
		BackendAuth auth = new BackendAuthImpl();
		int start = 0, end = 0;
		long groupId = GetterUtil.getLong(header.getHeaderString("groupId"));

		try {

			if (!auth.isAuth(serviceContext)) {
				throw new UnauthenticationException();
			}

			RegistrationTemplatesResultsModel results = new RegistrationTemplatesResultsModel();

			RegistrationTemplatesActions action = new RegistrationTemplatesActionsImpl();

			JSONObject registrationTemplateJsonObject = action.getRegistrationTemplates(groupId, start, end);

			List<RegistrationTemplates> lstRegistrationTemplate = (List<RegistrationTemplates>) registrationTemplateJsonObject
					.get("lstRegistrationTemplate");

			results.setTotal(registrationTemplateJsonObject.getInt("total"));
			results.getData().addAll(
					RegistrationTemplatesUtils.mappingToRegistrationTemplatesResultsModel(lstRegistrationTemplate));

			return Response.status(200).entity(results).build();

		} catch (Exception e) {
			return processException(e);
		}
	}

	@Override
	public Response addRegistrationTemplate(HttpServletRequest request, HttpHeaders header, Company company,
			Locale locale, User user, ServiceContext serviceContext, RegistrationTemplateInputModel input) {
		// TODO Add RegistrationTemplates
		BackendAuth auth = new BackendAuthImpl();

		long groupId = GetterUtil.getLong(header.getHeaderString("groupId"));

		try {

			if (!auth.isAuth(serviceContext)) {
				throw new UnauthenticationException();
			}
			RegistrationTemplatesActions action = new RegistrationTemplatesActionsImpl();

			RegistrationTemplates registrationTemplate = action.addRegistrationTemplate(groupId,
					input.getGovAgencyCode(), input.getGovAgencyName(), input.getFormNo(), input.getFormName(),
					input.isMultiple(), input.getFormScript(), input.getFormReport(), input.getSampleData(),
					serviceContext);

			RegistrationTemplateDetailModel result = RegistrationTemplatesUtils
					.mappingToRegistrationTemplateModel(registrationTemplate);

			return Response.status(200).entity(result).build();

		} catch (Exception e) {
			return processException(e);
		}
	}

	@Override
	public Response updateRegistrationTemplate(HttpServletRequest request, HttpHeaders header, Company company,
			Locale locale, User user, ServiceContext serviceContext, RegistrationTemplateInputModel input,
			long registrationTemplateId) {
		// TODO Update RegistrationTemplates
		BackendAuth auth = new BackendAuthImpl();

		long groupId = GetterUtil.getLong(header.getHeaderString("groupId"));

		try {

			if (!auth.isAuth(serviceContext)) {
				throw new UnauthenticationException();
			}

			RegistrationTemplatesActions action = new RegistrationTemplatesActionsImpl();

			RegistrationTemplates registrationTemplate = action.updateRegistrationTemplates(groupId,
					registrationTemplateId, input.getGovAgencyCode(), input.getGovAgencyName(), input.getFormNo(),
					input.getFormName(), input.isMultiple(), input.getFormScript(), input.getFormReport(),
					input.getSampleData(), serviceContext);

			RegistrationTemplateDetailModel result = RegistrationTemplatesUtils
					.mappingToRegistrationTemplateModel(registrationTemplate);

			return Response.status(200).entity(result).build();

		} catch (Exception e) {
			return processException(e);
		}
	}

	@Override
	public Response removeRegistrationTemplate(HttpServletRequest request, HttpHeaders header, Company company,
			Locale locale, User user, ServiceContext serviceContext, String registrationTemplateId) {
		// TODO Remove RegistrationTemplates
		BackendAuth auth = new BackendAuthImpl();

		long groupId = GetterUtil.getLong(header.getHeaderString("groupId"));

		try {

			if (!auth.isAuth(serviceContext)) {
				throw new UnauthenticationException();
			}

			RegistrationTemplatesActions action = new RegistrationTemplatesActionsImpl();

			RegistrationTemplates registrationTemplate = action.removeRegistrationTemplate(groupId,
					registrationTemplateId);

			return Response.status(200).entity("OK!").build();

		} catch (Exception e) {
			return processException(e);
		}
	}

	@Override
	public Response getFormScriptByRegistrationTemplateId(HttpServletRequest request, HttpHeaders header,
			Company company, Locale locale, User user, ServiceContext serviceContext, long registrationTemplateId) {
		// TODO Get FormScript of RegistrationTemplates
		BackendAuth auth = new BackendAuthImpl();
		
		RegistrationTemplateFormScriptInputUpdateModel result = new RegistrationTemplateFormScriptInputUpdateModel();

		try {

			if (!auth.isAuth(serviceContext)) {
				throw new UnauthenticationException();
			}

			RegistrationTemplates registrationTemplate = RegistrationTemplatesLocalServiceUtil
					.getRegistrationTemplates(registrationTemplateId);
			
			result.setFormScript(registrationTemplate.getFormScript());

			return Response.status(200).entity(result).build();

		} catch (Exception e) {
			return processException(e);
		}
	}

	public Response updateRegistrationTemplateFormScript(HttpServletRequest request, HttpHeaders header,
			Company company, Locale locale, User user, ServiceContext serviceContext, long registrationTemplateId,
			String formScript) {
		// TODO Update FormScript of RegistrationTemplates
		BackendAuth auth = new BackendAuthImpl();

		long groupId = GetterUtil.getLong(header.getHeaderString("groupId"));

		try {

			if (!auth.isAuth(serviceContext)) {
				throw new UnauthenticationException();
			}

			RegistrationTemplatesActions action = new RegistrationTemplatesActionsImpl();

			RegistrationTemplates registrationTemplate = action.updateFormScript(groupId, registrationTemplateId,
					formScript, serviceContext);

			RegistrationTemplateDetailModel result = RegistrationTemplatesUtils
					.mappingToRegistrationTemplateModel(registrationTemplate);

			return Response.status(200).entity(result).build();

		} catch (Exception e) {
			return processException(e);
		}
	}

	@Override
	public Response getFormReportByRegistrationTemplateId(HttpServletRequest request, HttpHeaders header,
			Company company, Locale locale, User user, ServiceContext serviceContext, long registrationTemplateId) {
		// TODO Get FormReport of RegistrationTemplates
		BackendAuth auth = new BackendAuthImpl();
		
		RegistrationTemplateFormReportInputUpdateModel result = new RegistrationTemplateFormReportInputUpdateModel();

		try {

			if (!auth.isAuth(serviceContext)) {
				throw new UnauthenticationException();
			}

			RegistrationTemplates registrationTemplate = RegistrationTemplatesLocalServiceUtil
					.getRegistrationTemplates(registrationTemplateId);
			
			result.setFormReport(registrationTemplate.getFormReport());

			return Response.status(200).entity(result).build();

		} catch (Exception e) {
			return processException(e);
		}
	}

	public Response updateRegistrationTemplateFormReport(HttpServletRequest request, HttpHeaders header,
			Company company, Locale locale, User user, ServiceContext serviceContext, long registrationTemplateId,
			String formReport) {
		// TODO Update FormReport of RegistrationTemplates
		BackendAuth auth = new BackendAuthImpl();

		long groupId = GetterUtil.getLong(header.getHeaderString("groupId"));

		try {

			if (!auth.isAuth(serviceContext)) {
				throw new UnauthenticationException();
			}

			RegistrationTemplatesActions action = new RegistrationTemplatesActionsImpl();

			RegistrationTemplates registrationTemplate = action.updateFormReport(groupId, registrationTemplateId,
					formReport, serviceContext);

			RegistrationTemplateDetailModel result = RegistrationTemplatesUtils
					.mappingToRegistrationTemplateModel(registrationTemplate);

			return Response.status(200).entity(result).build();

		} catch (Exception e) {
			return processException(e);
		}
	}

	@Override
	public Response getSampleDataByRegistrationTemplateId(HttpServletRequest request, HttpHeaders header,
			Company company, Locale locale, User user, ServiceContext serviceContext, long registrationTemplateId) {
		// TODO Get SampleData of RegistrationTemplates
		BackendAuth auth = new BackendAuthImpl();
		
		RegistrationTemplateSampleDataInputUpdateModel result = new RegistrationTemplateSampleDataInputUpdateModel();

		try {

			if (!auth.isAuth(serviceContext)) {
				throw new UnauthenticationException();
			}

			RegistrationTemplates registrationTemplate = RegistrationTemplatesLocalServiceUtil
					.getRegistrationTemplates(registrationTemplateId);
			
			result.setSampleData(registrationTemplate.getSampleData());

			return Response.status(200).entity(result).build();

		} catch (Exception e) {
			return processException(e);
		}
	}

	public Response updateRegistrationTemplateSampleData(HttpServletRequest request, HttpHeaders header,
			Company company, Locale locale, User user, ServiceContext serviceContext, long registrationTemplatesId,
			String sampleData) {
		// TODO Update FormReport of RegistrationTemplates
		BackendAuth auth = new BackendAuthImpl();

		long groupId = GetterUtil.getLong(header.getHeaderString("groupId"));

		try {

			if (!auth.isAuth(serviceContext)) {
				throw new UnauthenticationException();
			}

			RegistrationTemplatesActions action = new RegistrationTemplatesActionsImpl();

			RegistrationTemplates registrationTemplate = action.updateSampledata(groupId, registrationTemplatesId,
					sampleData, serviceContext);

			RegistrationTemplateDetailModel result = RegistrationTemplatesUtils
					.mappingToRegistrationTemplateModel(registrationTemplate);

			return Response.status(200).entity(result).build();

		} catch (Exception e) {
			return processException(e);
		}
	}

	@Override
	public Response getRegistrationTemplatebyId(HttpServletRequest request, HttpHeaders header, Company company,
			Locale locale, User user, ServiceContext serviceContext, String id) {
		// TODO Get RegistrationTemplates by Id
		BackendAuth auth = new BackendAuthImpl();
		long groupId = GetterUtil.getLong(header.getHeaderString("groupId"));
		try {

			if (!auth.isAuth(serviceContext)) {
				throw new UnauthenticationException();
			}

			RegistrationTemplates registrationTemplates = RegistrationTemplatesLocalServiceUtil
					.getRegistrationTemplatebyId(groupId, id);

			RegistrationTemplateDetailModel result = RegistrationTemplatesUtils
					.mappingToRegistrationTemplateModel(registrationTemplates);

			return Response.status(200).entity(result).build();

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
