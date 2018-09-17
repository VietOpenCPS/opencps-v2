package org.opencps.api.controller.impl;

import com.liferay.document.library.kernel.service.DLAppLocalServiceUtil;
import com.liferay.document.library.kernel.service.DLFileEntryLocalServiceUtil;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Company;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.search.Document;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.search.Sort;
import com.liferay.portal.kernel.search.SortFactoryUtil;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.Validator;

import java.io.File;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.UUID;

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
import javax.ws.rs.core.Response.ResponseBuilder;

import org.apache.commons.httpclient.util.HttpURLConnection;
import org.opencps.api.controller.RegistrationManagement;
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
import org.opencps.datamgt.model.DictCollection;
import org.opencps.datamgt.model.DictItem;
import org.opencps.datamgt.service.DictCollectionLocalServiceUtil;
import org.opencps.datamgt.service.DictItemLocalServiceUtil;
import org.opencps.dossiermgt.action.RegistrationActions;
import org.opencps.dossiermgt.action.RegistrationFormActions;
import org.opencps.dossiermgt.action.impl.RegistrationActionsImpl;
import org.opencps.dossiermgt.action.impl.RegistrationFormActionsImpl;
import org.opencps.dossiermgt.constants.RegistrationFormTerm;
import org.opencps.dossiermgt.constants.RegistrationTerm;
import org.opencps.dossiermgt.model.Registration;
import org.opencps.dossiermgt.model.RegistrationForm;
import org.opencps.dossiermgt.model.RegistrationTemplates;
import org.opencps.dossiermgt.service.RegistrationFormLocalServiceUtil;
import org.opencps.dossiermgt.service.RegistrationLocalServiceUtil;
import org.opencps.dossiermgt.service.RegistrationTemplatesLocalServiceUtil;

import backend.auth.api.exception.BusinessExceptionImpl;

public class RegistrationManagementImpl implements RegistrationManagement {
	private static Log _log = LogFactoryUtil.getLog(RegistrationManagementImpl.class);

	private static String ADMINISTRATIVE_REGION = "ADMINISTRATIVE_REGION";

	@SuppressWarnings("unchecked")
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

			Sort[] sorts = new Sort[] { SortFactoryUtil.create(sort + "_sortable", Sort.STRING_TYPE, true) };

			JSONObject jsonData = actions.getRegistrations(serviceContext.getUserId(), serviceContext.getCompanyId(),
					groupId, params, sorts, -1, -1, serviceContext);

			RegistrationResultsModel results = new RegistrationResultsModel();
			//
			results.setTotal(jsonData.getInt("total"));

			results.getData()
					.addAll(RegistrationUtils.mappingToRegistrationResultModel((List<Document>) jsonData.get("data")));

			return Response.status(200).entity(results).build();

		} catch (Exception e) {
			return BusinessExceptionImpl.processException(e);
		}
	}

	@Override
	public Response add(HttpServletRequest request, HttpHeaders header, Company company, Locale locale,
			User user, ServiceContext serviceContext, RegistrationInputModel input) {
		_log.info("START");
		RegistrationDetailModel result = null;
		
		long companyId = company.getCompanyId();
		_log.info("companyId: "+companyId);
		try {
			long groupId = GetterUtil.getLong(header.getHeaderString("groupId"));
			_log.info("groupId: "+groupId);
			String cityName = "";
			String districtName = "";
			String wardName = "";

			if (Validator.isNotNull(input.getCityCode())) {
				cityName = getDictItemName(groupId, ADMINISTRATIVE_REGION, input.getCityCode());

			}
			if (Validator.isNotNull(input.getDistrictCode())) {
				districtName = getDictItemName(groupId, ADMINISTRATIVE_REGION, input.getDistrictCode());

			}
			if (Validator.isNotNull(input.getWardCode())) {
				wardName = getDictItemName(groupId, ADMINISTRATIVE_REGION, input.getWardCode());

			}
			_log.info("cityName: "+cityName);
			_log.info("districtName: "+districtName);
			_log.info("wardName: "+wardName);

			RegistrationActions action = new RegistrationActionsImpl();

			Registration registration = action.insert(groupId, companyId, input.getApplicantName(), input.getApplicantIdType(),
					input.getApplicantIdNo(), input.getApplicantIdDate(), input.getAddress(), input.getCityCode(),
					cityName, input.getDistrictCode(), districtName, input.getWardCode(), wardName,
					input.getContactName(), input.getContactTelNo(), input.getContactEmail(), input.getGovAgencyCode(),
					input.getGovAgencyName(), input.getRegistrationState(), input.getRegistrationClass(),
					input.getRepresentativeEnterprise(), serviceContext);

			result = RegistrationUtils.mappingToRegistrationDetailModel(registration);
			return Response.status(200).entity(result).build();
		} catch (Exception e) {
			return BusinessExceptionImpl.processException(e);
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
			return BusinessExceptionImpl.processException(e);
		}
	}

	@Override
	public Response update(HttpServletRequest request, HttpHeaders header, Company company, Locale locale, User user,
			ServiceContext serviceContext, RegistrationInputModel input, long registrationId) {

		try {
			long groupId = GetterUtil.getLong(header.getHeaderString("groupId"));
			RegistrationActions action = new RegistrationActionsImpl();
			String cityName = "";
			String districtName = "";
			String wardName = "";

			if (Validator.isNotNull(input.getCityCode())) {
				cityName = getDictItemName(groupId, ADMINISTRATIVE_REGION, input.getCityCode());

			}
			if (Validator.isNotNull(input.getDistrictCode())) {
				districtName = getDictItemName(groupId, ADMINISTRATIVE_REGION, input.getDistrictCode());

			}
			if (Validator.isNotNull(input.getWardCode())) {
				wardName = getDictItemName(groupId, ADMINISTRATIVE_REGION, input.getWardCode());

			}
			Registration registration = action.updateRegistration(groupId, registrationId, input.getApplicantName(),
					input.getApplicantIdType(), input.getApplicantIdNo(), input.getApplicantIdDate(),
					input.getAddress(), input.getCityCode(), cityName, input.getDistrictCode(), districtName,
					input.getWardCode(), wardName, input.getContactName(), input.getContactTelNo(),
					input.getContactEmail(), input.getGovAgencyCode(), input.getGovAgencyName(),
					input.getRegistrationState(), input.getRegistrationClass(), input.getRepresentativeEnterprise(),
					serviceContext);

			RegistrationDetailResultModel result = RegistrationUtils.mappingToRegistrationDetailResultModel(registration);

			return Response.status(200).entity(result).build();
		} catch (Exception e) {
			return BusinessExceptionImpl.processException(e);
		}
	}

	@Override
	public Response delete(HttpHeaders header, long id) {
		try {
			long groupId = GetterUtil.getLong(header.getHeaderString("groupId"));

			RegistrationActions action = new RegistrationActionsImpl();

			Registration registration = action.delete(id);
			RegistrationFormLocalServiceUtil.deleteRegistrationForms(groupId, id);
			RegistrationDetailModel result = RegistrationUtils.mappingToRegistrationDetailModel(registration);

			return Response.status(200).entity(result).build();
		} catch (Exception e) {
			return BusinessExceptionImpl.processException(e);
		}

	}

	@Override
	public Response getFormsbyRegId(HttpHeaders header, long id) throws PortalException {

		try {
			long groupId = GetterUtil.getLong(header.getHeaderString("groupId"));

			RegistrationFormActions action = new RegistrationFormActionsImpl();

			RegistrationFormResultsModel result = new RegistrationFormResultsModel();

			List<RegistrationForm> lstRegistrationForm = action.getFormbyRegId(groupId, id);
			int total = lstRegistrationForm.size();
			for (RegistrationForm registrationForm : lstRegistrationForm) {
				_log.info("registrationFormXXXXXXX: "+registrationForm.getRemoved());
			}

			List<RegistrationFormModel> lstRegistrationFormModel = RegistrationFormUtils
					.mappingToRegistrationFormResultsModel(lstRegistrationForm);

			result.setTotal(total);
			result.getData().addAll(lstRegistrationFormModel);

			return Response.status(200).entity(result).build();

		} catch (Exception e) {
			return BusinessExceptionImpl.processException(e);
		}
	}

	@Override
	public Response addRegistrationForm(HttpServletRequest request, HttpHeaders header, Company company, Locale locale,
			User user, ServiceContext serviceContext, RegistrationFormInputModel input, long registrationId,
			String formNo) {
		BackendAuth auth = new BackendAuthImpl();
		RegistrationFormDetailModel result = null;
		long companyId = company.getCompanyId();
		try {

			if (!auth.isAuth(serviceContext)) {
				throw new UnauthenticationException();
			}

			long groupId = GetterUtil.getLong(header.getHeaderString("groupId"));

			// long fileEntryId = getfileEntryId(input.getFormData(),
			// input.getFormScript(), input.getFormReport());
			// TODO: lam lai
			String govAgencyCode = "";
			RegistrationTemplates registrationTemplate = RegistrationTemplatesLocalServiceUtil
					.getRegTempbyFormNoGovCode(groupId, formNo, govAgencyCode);

			String referenceUid = UUID.randomUUID().toString();

			RegistrationForm registrationForm = RegistrationFormLocalServiceUtil.addRegistrationForm(groupId, companyId,
					registrationId, referenceUid, registrationTemplate.getFormNo(), registrationTemplate.getFormName(),
					registrationTemplate.getSampleData(), registrationTemplate.getFormScript(),
					registrationTemplate.getFormReport(), 0, true, false, serviceContext);

			result = RegistrationFormUtils.mappingToRegistrationFormDetailModel(registrationForm);
			return Response.status(200).entity(result).build();
		} catch (Exception e) {
			return BusinessExceptionImpl.processException(e);
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
					input.getRepresentativeEnterprise(), serviceContext);

			return Response.status(200).build();
		} catch (Exception e) {
			return BusinessExceptionImpl.processException(e);
		}
	}

	@Override
	public Response previewFile(HttpServletRequest request, HttpHeaders header, Company company, Locale locale,
			User user, ServiceContext serviceContext, long registrationId, String referenceUid) {
		BackendAuth auth = new BackendAuthImpl();
		try {

			if (!auth.isAuth(serviceContext)) {
				throw new UnauthenticationException();
			}

			long groupId = GetterUtil.getLong(header.getHeaderString("groupId"));

			RegistrationForm registrationForm = RegistrationFormLocalServiceUtil.findFormbyRegidRefid(groupId,
					registrationId, referenceUid);

			if (registrationForm != null && registrationForm.getFileEntryId() > 0) {
				FileEntry fileEntry = DLAppLocalServiceUtil.getFileEntry(registrationForm.getFileEntryId());

				File file = DLFileEntryLocalServiceUtil.getFile(fileEntry.getFileEntryId(), fileEntry.getVersion(),
						true);

				ResponseBuilder responseBuilder = Response.ok((Object) file);

				responseBuilder.header("Content-Disposition",
						"attachment; filename=\"" + fileEntry.getFileName() + "\"");
				responseBuilder.header("Content-Type", fileEntry.getMimeType());

				return responseBuilder.build();
			} else {
				return Response.status(HttpURLConnection.HTTP_NO_CONTENT).build();
			}
		} catch (Exception e) {
			return BusinessExceptionImpl.processException(e);
		}
	}

	@Override
	public Response submitting(HttpServletRequest request, HttpHeaders header, Company company, Locale locale,
			User user, ServiceContext serviceContext, long registrationId) {

		BackendAuth auth = new BackendAuthImpl();
		try {

			if (!auth.isAuth(serviceContext)) {
				throw new UnauthenticationException();
			}

			Registration registration = RegistrationLocalServiceUtil.updateSubmitting(registrationId, true);

			return Response.status(200).entity(registration).build();
		} catch (Exception e) {
			return BusinessExceptionImpl.processException(e);
		}
	}

	protected String getDictItemName(long groupId, String collectionCode, String itemCode) {

		DictCollection dc = DictCollectionLocalServiceUtil.fetchByF_dictCollectionCode(collectionCode, groupId);

		if (Validator.isNotNull(dc)) {
			DictItem it = DictItemLocalServiceUtil.fetchByF_dictItemCode(itemCode, dc.getPrimaryKey(), groupId);

			return it.getItemName();

		} else {
			return StringPool.BLANK;
		}

	}

	//18
	@SuppressWarnings("unchecked")
	@Override
	public Response getDataFormByFormNo(HttpServletRequest request, HttpHeaders header, Company company, Locale locale,
			User user, ServiceContext serviceContext, String applicantNo, String agencyNo, String formNo,
			String keyword) {

		BackendAuth auth = new BackendAuthImpl();

		try {

			// Check user is login
			if (!auth.isAuth(serviceContext)) {
				throw new UnauthenticationException();
			}

			long groupId = GetterUtil.getLong(header.getHeaderString("groupId"));

			Registration regInfo = null;
			long registrationId = 0;
			//TODO
			agencyNo = "";
//			if (Validator.isNotNull(applicantNo) && Validator.isNotNull(agencyNo)) {
				_log.info("groupId "+groupId);
				_log.info("applicantNo "+applicantNo);
				regInfo = RegistrationLocalServiceUtil.getByApplicantAndAgency(groupId, applicantNo, agencyNo);
//				_log.info("id reg "+regInfo.getRegistrationId());
//			}
			if (regInfo != null) {
				registrationId = regInfo.getRegistrationId();
			}
//			if (search.getEnd() == 0) {
//				search.setStart(-1);
//				search.setEnd(-1);
//			}

//			String _properties = search.getProperties();
////			String _properties = "ten_doanh_nghiep";
//			String[] splitProperties = null;
//			if (Validator.isNotNull(_properties)) {
//				splitProperties = _properties.split(";");
//			}
			JSONObject keyJson = JSONFactoryUtil.createJSONObject(keyword);
			
			String pattern = String.valueOf(keyJson.get("query"));
			String paramValues = String.valueOf(keyJson.get("values"));
			String paramTypes = String.valueOf(keyJson.get("type"));
			_log.info("keyword "+keyword);
			_log.info("pattern "+pattern);
			_log.info("paramValues "+paramValues);
			_log.info("paramTypes "+paramTypes);

			LinkedHashMap<String, Object> params = new LinkedHashMap<String, Object>();
			params.put(Field.GROUP_ID, String.valueOf(groupId));
			params.put(RegistrationFormTerm.REGISTRATION_ID, registrationId);
			params.put(RegistrationFormTerm.FORM_NO, formNo);
			params.put("pattern", pattern);
			params.put("paramValues", paramValues);
			params.put("paramTypes", paramTypes);
			
			RegistrationActions actions = new RegistrationActionsImpl();
			
//			JSONArray jsonDataList = actions.getFormDataByFormNo(groupId, registrationId,
//					formNo, splitProperties);

			JSONObject results = JSONFactoryUtil.createJSONObject();
			
			Sort[] sorts = new Sort[] {
					SortFactoryUtil.create(Field.MODIFIED_DATE + "_sortable", Sort.STRING_TYPE, true) };
			// get JSON data deliverable
			JSONObject jsonData = actions.getFormDataByFormNo(serviceContext.getUserId(), serviceContext.getCompanyId(), params, sorts,
					-1, -1, serviceContext);

			results.put("total", jsonData.getInt("total"));
			List<Document> docList =(List<Document>) jsonData.get("data");

			JSONArray formDataArr = JSONFactoryUtil.createJSONArray();
			for (Document doc : docList) {
				String formData = doc.get(RegistrationFormTerm.FORM_DATA);
				String registrationFormId = doc.get(RegistrationFormTerm.REGISTRATION_FORM_ID);
				JSONObject formDataJson = null;
				if (Validator.isNotNull(formData)) {
					formDataJson = JSONFactoryUtil.createJSONObject(formData);
					formDataJson.put("registrationFormId", registrationFormId);
				}
				if (formDataJson != null) {
					formDataArr.put(formDataJson);
				}
			}
			results.put("data", formDataArr);
			
			return Response.status(200).entity(JSONFactoryUtil.looseSerialize(results)).build();

		} catch (Exception e) {
			return BusinessExceptionImpl.processException(e);
		}

	}

	@Override
	public Response getFormDataByReferenceUid(HttpServletRequest request, HttpHeaders header, Company company,
			Locale locale, User user, ServiceContext serviceContext, long registrationId) {
		BackendAuth auth = new BackendAuthImpl();
		try {

			if (!auth.isAuth(serviceContext)) {
				throw new UnauthenticationException();
			}

			Registration reg = RegistrationLocalServiceUtil.fetchRegistration(registrationId);

			JSONObject results = JSONFactoryUtil.createJSONObject();
			JSONArray JsonArr = null;
			if (reg != null) {
				JsonArr = RegistrationUtils.mappingFormData(reg);
			}
			
			//
			results.put("total", JsonArr != null ? JsonArr.length() : 0);

			results.put("data", JsonArr);

			return Response.status(200).entity(JSONFactoryUtil.looseSerialize(results)).build();

		} catch (Exception e) {
			return BusinessExceptionImpl.processException(e);
		}
	}

//	public static void main(String []args) {
//		String jsonString = "{'name':'Hamidul Islam','country':'India'}";
//		try {
//			JSONObject jSONObject = JSONFactoryUtil.createJSONObject(jsonString);
//			jSONObject.put("test", "123456");
//	        jSONObject.put("test1", "1111");
//	        String formData = String.valueOf(jSONObject);
//	        if (Validator.isNotNull(formData)) {
//	        	try {
//					JSONObject formDataJson = JSONFactoryUtil.createJSONObject(formData);
//					formDataJson.put("test2", "33333");
//					System.out.println(formDataJson.toString());
//				} catch (JSONException e) {
//					e.printStackTrace();
//				}
//	        	
//			}
//		} catch (JSONException e1) {
//			e1.printStackTrace();
//		}
//	}
}
