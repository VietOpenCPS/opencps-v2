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
import org.opencps.api.controller.util.RegistrationFormUtils;
import org.opencps.api.registrationform.model.RegistrationFormDetailModel;
import org.opencps.api.registrationform.model.RegistrationFormInfoModel;
import org.opencps.auth.api.BackendAuth;
import org.opencps.auth.api.BackendAuthImpl;
import org.opencps.auth.api.exception.UnauthenticationException;
import org.opencps.auth.api.exception.UnauthorizationException;
import org.opencps.dossiermgt.action.RegistrationActions;
import org.opencps.dossiermgt.action.RegistrationFormActions;
import org.opencps.dossiermgt.action.impl.RegistrationActionsImpl;
import org.opencps.dossiermgt.action.impl.RegistrationFormActionsImpl;
import org.opencps.dossiermgt.constants.RegistrationFormTerm;
import org.opencps.dossiermgt.constants.RegistrationTerm;
import org.opencps.dossiermgt.model.Registration;
import org.opencps.dossiermgt.model.RegistrationForm;
import org.opencps.dossiermgt.service.RegistrationFormLocalServiceUtil;
import org.opencps.dossiermgt.service.RegistrationLocalServiceUtil;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Company;
import com.liferay.portal.kernel.model.User;
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
			long groupId = GetterUtil.getLong(header.getHeaderString("groupId"));
			RegistrationFormActions action = new RegistrationFormActionsImpl();

			action.deleteRegistrationForm(groupId, id, referenceUid);

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

	//18
	@Override
	public Response getDataFormByFormNo(HttpServletRequest request, HttpHeaders header, Company company, Locale locale,
			User user, ServiceContext serviceContext, String applicantNo, String agencyNo, String formNo,
			RegistrationFormInfoModel search) {

		BackendAuth auth = new BackendAuthImpl();

		try {

			// Check user is login
			if (!auth.isAuth(serviceContext)) {
				throw new UnauthenticationException();
			}

			long groupId = GetterUtil.getLong(header.getHeaderString("groupId"));

			Registration regInfo = null;
			long registrationId = 0;
			if (Validator.isNotNull(applicantNo) && Validator.isNotNull(agencyNo)) {
				regInfo = RegistrationLocalServiceUtil.getByApplicantAndAgency(groupId, applicantNo, agencyNo);
			}
			if (regInfo != null) {
				registrationId = regInfo.getRegistrationId();
			}
//			if (search.getEnd() == 0) {
//				search.setStart(-1);
//				search.setEnd(-1);
//			}

			String _properties = search.getProperties();
//			String _properties = "ten_doanh_nghiep";
			String[] splitProperties = null;
			if (Validator.isNotNull(_properties)) {
				splitProperties = _properties.split(";");
			}
			
			RegistrationFormActions actions = new RegistrationFormActionsImpl();
			
			List<JSONObject> jsonDataList = actions.getFormDataByFormNo(groupId, registrationId,
					formNo, splitProperties);
//			final String PROPERTIES = "_properties";
//			LinkedHashMap<String, Object> params = new LinkedHashMap<String, Object>();
//			params.put(PROPERTIES, splitProperties);
//			params.put(Field.GROUP_ID, String.valueOf(groupId));
//			params.put(Field.KEYWORD_SEARCH, search.getKeyword());
//			params.put(RegistrationTerm.APPLICATION_ID_NO, applicantNo);
//			params.put(RegistrationTerm.GOV_AGENCY_CODE, agencyNo);
//			params.put(RegistrationFormTerm.FORM_NO, formNo);

			// Default sort by modifiedDate
//			Sort[] sorts = new Sort[] {
//					SortFactoryUtil.create(Field.MODIFIED_DATE + "_sortable", Sort.STRING_TYPE, true) };

//			if (Validator.isNotNull(search.getSort()) && Validator.isNotNull(search.getOrder())) {
//				sorts = new Sort[] { SortFactoryUtil.create(search.getSort() + "_sortable", Sort.STRING_TYPE,
//						GetterUtil.getBoolean(search.getOrder())) };
//			}

			JSONObject results = JSONFactoryUtil.createJSONObject();
			results.put("total", jsonDataList.size());
			results.put("data", jsonDataList);
//			JSONObject jsonData = null;
//			if (flag) {
				// get JSON data by dossierId
//				jsonData = actions.getFormDataByFormNo(serviceContext.getCompanyId(), params,
//						sorts, search.getStart(), search.getEnd(), serviceContext);
//			}

			// Parse JSONObejct to PaymentFileResultModel Object
//			results.setTotal(jsonData.getInt("total"));
//			results.getData().addAll((List<String>) jsonData.get("data"));

			return Response.status(200).entity(results).build();

		} catch (Exception e) {
			ErrorMsg error = new ErrorMsg();

			error.setMessage("not found!");
			error.setCode(404);
			error.setDescription("not found!");

			return Response.status(404).entity(error).build();
		}

	}

}
