package org.opencps.api.controller.impl;

import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;

import org.opencps.api.controller.RegistrationTemplatesManagement;
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
import org.opencps.dossiermgt.action.RegistrationTemplatesActions;
import org.opencps.dossiermgt.action.impl.RegistrationTemplatesActionsImpl;
import org.opencps.dossiermgt.model.RegistrationTemplates;
import org.opencps.dossiermgt.service.RegistrationTemplatesLocalServiceUtil;

import backend.auth.api.exception.BusinessExceptionImpl;

import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Company;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.Validator;

public class RegistrationTemplatesManagementImpl implements RegistrationTemplatesManagement {
	
	Log _log = LogFactoryUtil.getLog(RegistrationTemplatesManagementImpl.class);

	@SuppressWarnings("unchecked")
	@Override
	public Response getRegistrationTemplates(HttpServletRequest request, HttpHeaders header, Company company,
			Locale locale, User user, ServiceContext serviceContext, String formNo, String govAgencyCode) {

		BackendAuth auth = new BackendAuthImpl();
		int start = 0, end = 0;
		long groupId = GetterUtil.getLong(header.getHeaderString("groupId"));

		try {

			if (!auth.isAuth(serviceContext)) {
				throw new UnauthenticationException();
			}

			RegistrationTemplatesResultsModel results = new RegistrationTemplatesResultsModel();

			RegistrationTemplatesActions action = new RegistrationTemplatesActionsImpl();

			JSONObject registrationTemplateJsonObject;

			if (Validator.isNull(formNo) && Validator.isNull(govAgencyCode)) {
				registrationTemplateJsonObject = action.getRegistrationTemplates(groupId, start, end);
			} else {
				registrationTemplateJsonObject = action.getRegistrationTemplates(groupId, formNo, govAgencyCode);
			}

			List<RegistrationTemplates> lstRegistrationTemplate = (List<RegistrationTemplates>) registrationTemplateJsonObject
					.get("lstRegistrationTemplate");

			results.setTotal(registrationTemplateJsonObject.getInt("total"));
			results.getData().addAll(
					RegistrationTemplatesUtils.mappingToRegistrationTemplatesResultsModel(lstRegistrationTemplate));

			return Response.status(200).entity(results).build();

		} catch (Exception e) {
			return BusinessExceptionImpl.processException(e);
		}
	}

	@Override
	public Response addRegistrationTemplate(HttpServletRequest request, HttpHeaders header, Company company,
			Locale locale, User user, ServiceContext serviceContext, RegistrationTemplateInputModel input) {

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
			return BusinessExceptionImpl.processException(e);
		}
	}

	@Override
	public Response updateRegistrationTemplate(HttpServletRequest request, HttpHeaders header, Company company,
			Locale locale, User user, ServiceContext serviceContext, RegistrationTemplateInputModel input,
			long registrationTemplateId) {

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
			return BusinessExceptionImpl.processException(e);
		}
	}

	@Override
	public Response removeRegistrationTemplate(HttpServletRequest request, HttpHeaders header, Company company,
			Locale locale, User user, ServiceContext serviceContext, long registrationTemplateId) {

		BackendAuth auth = new BackendAuthImpl();

		long groupId = GetterUtil.getLong(header.getHeaderString("groupId"));

		try {

			if (!auth.isAuth(serviceContext)) {
				throw new UnauthenticationException();
			}

			RegistrationTemplatesActions action = new RegistrationTemplatesActionsImpl();

			RegistrationTemplates registrationTemplate = action.removeRegistrationTemplate(groupId,
					registrationTemplateId);

			RegistrationTemplateDetailModel result = RegistrationTemplatesUtils
					.mappingToRegistrationTemplateModel(registrationTemplate);

			return Response.status(200).entity(result).build();

		} catch (Exception e) {
			return BusinessExceptionImpl.processException(e);
		}
	}

	@Override
	public Response getFormScriptByRegistrationTemplateId(HttpServletRequest request, HttpHeaders header,
			Company company, Locale locale, User user, ServiceContext serviceContext, long registrationTemplateId) {
		// Get FormScript of RegistrationTemplates
		BackendAuth auth = new BackendAuthImpl();

		RegistrationTemplateFormScriptInputUpdateModel result = new RegistrationTemplateFormScriptInputUpdateModel();
		long groupId = GetterUtil.getLong(header.getHeaderString("groupId"));
		try {

			if (!auth.isAuth(serviceContext)) {
				throw new UnauthenticationException();
			}

			RegistrationTemplates registrationTemplate = RegistrationTemplatesLocalServiceUtil
					.getRegTempbyRegId(groupId, registrationTemplateId);

			result.setFormScript(registrationTemplate.getFormScript());

			return Response.status(200).entity(result).build();

		} catch (Exception e) {
			return BusinessExceptionImpl.processException(e);
		}
	}

	public Response updateRegistrationTemplateFormScript(HttpServletRequest request, HttpHeaders header,
			Company company, Locale locale, User user, ServiceContext serviceContext, long registrationTemplateId,
			String formScript) {

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
			return BusinessExceptionImpl.processException(e);
		}
	}

	@Override
	public Response getFormReportByRegistrationTemplateId(HttpServletRequest request, HttpHeaders header,
			Company company, Locale locale, User user, ServiceContext serviceContext, long registrationTemplateId) {
		// Get FormReport of RegistrationTemplates
		BackendAuth auth = new BackendAuthImpl();

		RegistrationTemplateFormReportInputUpdateModel result = new RegistrationTemplateFormReportInputUpdateModel();

		try {

			if (!auth.isAuth(serviceContext)) {
				throw new UnauthenticationException();
			}
			long groupId = GetterUtil.getLong(header.getHeaderString("groupId"));
			RegistrationTemplates registrationTemplate = RegistrationTemplatesLocalServiceUtil
					.getRegTempbyRegId(groupId, registrationTemplateId);

			result.setFormReport(registrationTemplate.getFormReport());

			return Response.status(200).entity(result).build();

		} catch (Exception e) {
			return BusinessExceptionImpl.processException(e);
		}
	}

	public Response updateRegistrationTemplateFormReport(HttpServletRequest request, HttpHeaders header,
			Company company, Locale locale, User user, ServiceContext serviceContext, long registrationTemplateId,
			String formReport) {
		//  Update FormReport of RegistrationTemplates
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
			return BusinessExceptionImpl.processException(e);
		}
	}

	@Override
	public Response getSampleDataByRegistrationTemplateId(HttpServletRequest request, HttpHeaders header,
			Company company, Locale locale, User user, ServiceContext serviceContext, long registrationTemplateId) {
		// Get SampleData of RegistrationTemplates
		BackendAuth auth = new BackendAuthImpl();

		RegistrationTemplateSampleDataInputUpdateModel result = new RegistrationTemplateSampleDataInputUpdateModel();

		try {

			if (!auth.isAuth(serviceContext)) {
				throw new UnauthenticationException();
			}
			long groupId = GetterUtil.getLong(header.getHeaderString("groupId"));
			RegistrationTemplates registrationTemplate = RegistrationTemplatesLocalServiceUtil
					.getRegTempbyRegId(groupId, registrationTemplateId);

			result.setSampleData(registrationTemplate.getSampleData());

			return Response.status(200).entity(result).build();

		} catch (Exception e) {
			return BusinessExceptionImpl.processException(e);
		}
	}

	public Response updateRegistrationTemplateSampleData(HttpServletRequest request, HttpHeaders header,
			Company company, Locale locale, User user, ServiceContext serviceContext, long registrationTemplatesId,
			String sampleData) {
		// Update FormReport of RegistrationTemplates
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
			return BusinessExceptionImpl.processException(e);
		}
	}

	@Override
	public Response getRegistrationTemplatebyId(HttpServletRequest request, HttpHeaders header, Company company,
			Locale locale, User user, ServiceContext serviceContext, String id) {
		// Get RegistrationTemplates by Id
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
			return BusinessExceptionImpl.processException(e);
		}
	}

}
