
package org.opencps.api.controller.impl;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;

import javax.activation.DataHandler;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.FormParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;

import org.apache.cxf.jaxrs.ext.multipart.Attachment;
import org.opencps.api.controller.DeliverablesManagement;
import org.opencps.api.controller.util.DeliverableUtils;
import org.opencps.api.controller.util.OneGateUtils;
import org.opencps.api.deliverable.model.DeliverableInputModel;
import org.opencps.api.deliverable.model.DeliverableModel;
import org.opencps.api.deliverable.model.DeliverableSearchModel;
import org.opencps.api.deliverable.model.DeliverableUpdateModel;
import org.opencps.api.dossier.model.DossierDetailModel;
import org.opencps.auth.api.BackendAuth;
import org.opencps.auth.api.BackendAuthImpl;
import org.opencps.auth.api.exception.UnauthenticationException;
import org.opencps.dossiermgt.action.DeliverableActions;
import org.opencps.dossiermgt.action.DeliverableLogActions;
import org.opencps.dossiermgt.action.impl.DeliverableActionsImpl;
import org.opencps.dossiermgt.action.impl.DeliverableLogActionsImpl;
import org.opencps.dossiermgt.action.util.ConstantUtils;
import org.opencps.dossiermgt.action.util.ReadFilePropertiesUtils;
import org.opencps.dossiermgt.constants.DeliverableTerm;
import org.opencps.dossiermgt.constants.DossierTerm;
import org.opencps.dossiermgt.model.Deliverable;
import org.opencps.dossiermgt.model.DeliverableLog;
import org.opencps.dossiermgt.model.Dossier;
import org.opencps.dossiermgt.model.DossierFile;
import org.opencps.dossiermgt.service.DeliverableLocalServiceUtil;
import org.opencps.dossiermgt.service.DossierFileLocalServiceUtil;
import org.opencps.dossiermgt.service.DossierLocalServiceUtil;

import com.liferay.document.library.kernel.service.DLAppLocalServiceUtil;
import com.liferay.document.library.kernel.util.DLUtil;
import com.liferay.petra.string.StringPool;
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
import com.liferay.portal.kernel.search.Indexer;
import com.liferay.portal.kernel.search.IndexerRegistryUtil;
import com.liferay.portal.kernel.search.SearchException;
import com.liferay.portal.kernel.search.Sort;
import com.liferay.portal.kernel.search.SortFactoryUtil;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.Validator;

import backend.auth.api.exception.BusinessExceptionImpl;
import io.swagger.annotations.ApiParam;

public class DeliverablesManagementImpl implements DeliverablesManagement {

	private static Log _log =
		LogFactoryUtil.getLog(DeliverablesManagementImpl.class);

	@SuppressWarnings("unchecked")
	@Override
	public Response getDeliverables(
		HttpServletRequest request, HttpHeaders header, Company company,
		Locale locale, User user, ServiceContext serviceContext,
		DeliverableSearchModel search) {

		BackendAuth auth = new BackendAuthImpl();

		try {
			// Check user is login
			if (!auth.isAuth(serviceContext)) {
				throw new UnauthenticationException();
			}

			if (search.getEnd() == 0) {
				search.setStart(-1);
				search.setEnd(-1);
			}

			long groupId =
				GetterUtil.getLong(header.getHeaderString(Field.GROUP_ID));

			// Default sort by modifiedDate
			Sort[] sorts = new Sort[] {
				SortFactoryUtil.create(
					Field.MODIFIED_DATE + ReadFilePropertiesUtils.get(ConstantUtils.SORT_PATTERN), Sort.STRING_TYPE, true)
			};

			if (Validator.isNotNull(search.getSort()) &&
				Validator.isNotNull(search.getOrder())) {
				sorts = new Sort[] {
					SortFactoryUtil.create(
						search.getSort() + ReadFilePropertiesUtils.get(ConstantUtils.SORT_PATTERN), Sort.STRING_TYPE,
						GetterUtil.getBoolean(search.getOrder()))
				};
			}

			//
			LinkedHashMap<String, Object> params =
				new LinkedHashMap<String, Object>();

			params.put(Field.GROUP_ID, String.valueOf(groupId));
			params.put(DeliverableTerm.DELIVERABLE_STATE, search.getState());
			params.put(DeliverableTerm.GOV_AGENCY_CODE, search.getAgency());
			params.put(Field.KEYWORD_SEARCH, search.getKeyword());
			params.put(DeliverableTerm.DELIVERABLE_TYPE, search.getType());
			params.put(DeliverableTerm.APPLICANT_ID_NO, search.getApplicant());
			params.put(DossierTerm.USER_ID, user.getUserId());

			DeliverableActions actions = new DeliverableActionsImpl();
			JSONObject results = JSONFactoryUtil.createJSONObject();

			// get JSON data deliverable
			JSONObject jsonData = actions.getListDeliverable(
				user.getUserId(), serviceContext.getCompanyId(), params, sorts,
				search.getStart(), search.getEnd(), serviceContext);
			results.put(ConstantUtils.TOTAL, jsonData.getInt(ConstantUtils.TOTAL));
			List<Document> docList = (List<Document>) jsonData.get(ConstantUtils.DATA);

			JSONArray formDataArr = JSONFactoryUtil.createJSONArray();
			for (Document doc : docList) {
				String formData = doc.get(DeliverableTerm.FORM_DATA);
				JSONObject formJson =
					JSONFactoryUtil.createJSONObject(formData);
				formJson.put(
					"ten_chung_chi", doc.get(DeliverableTerm.DELIVERABLE_NAME));
				formJson.put(
					"deliverableCode",
					doc.get(DeliverableTerm.DELIVERABLE_CODE));
				// _log.info("formData: "+formData);
				formDataArr.put(formJson);
			}
			results.put(ConstantUtils.DATA, formDataArr);

			return Response.status(200).entity(
				JSONFactoryUtil.looseSerialize(results)).build();
		}
		catch (Exception e) {
			return BusinessExceptionImpl.processException(e);
		}

	}

	@Override
	public Response insertDeliverables(
		HttpServletRequest request, HttpHeaders header, Company company,
		Locale locale, User user, ServiceContext serviceContext,
		DeliverableInputModel input) {

		BackendAuth auth = new BackendAuthImpl();
		long groupId = GetterUtil.getLong(header.getHeaderString(Field.GROUP_ID));

		try {
			if (!auth.isAuth(serviceContext)) {
				throw new UnauthenticationException();
			}

			DeliverableActions action = new DeliverableActionsImpl();
			//
			String deliverableType = input.getDeliverableType();
			String deliverableCode = input.getDeliverableCode();
			String govAgencyName = input.getGovAgencyName();
			String govAgencyCode = input.getGovAgencyCode();
			String applicantIdNo = input.getApplicantIdNo();
			String applicantName = input.getApplicantName();
			String subject = input.getSubject();
			String issueDate = input.getIssueDate();
			String expireDate = input.getExpireDate();
			String revalidate = input.getRevalidate();
			String deliverableState = input.getDeliverableState();
			//
			Deliverable deliverable = action.addDeliverable(
				groupId, deliverableType, deliverableCode, govAgencyCode,
				govAgencyName, applicantIdNo, applicantName, subject, issueDate,
				expireDate, revalidate, deliverableState, serviceContext);

			DeliverableInputModel result =
				DeliverableUtils.mappingToDeliverablesModel(deliverable);

			return Response.status(200).entity(
				JSONFactoryUtil.looseSerialize(result)).build();
		}
		catch (Exception e) {
			return BusinessExceptionImpl.processException(e);
		}

	}

	@Override
	public Response getDeliverablesDetail(
		HttpServletRequest request, HttpHeaders header, Company company,
		Locale locale, User user, ServiceContext serviceContext, Long id) {

		BackendAuth auth = new BackendAuthImpl();

		try {
			if (!auth.isAuth(serviceContext)) {
				throw new UnauthenticationException();
			}

			DeliverableActions actions = new DeliverableActionsImpl();
			DeliverableModel results;

			Deliverable deliverableInfo = actions.getDetailById(id);

			if (Validator.isNotNull(deliverableInfo)) {
				results = DeliverableUtils.mappingToDeliverableDetailModel(
					deliverableInfo);
			}
			else {
				throw new Exception();
			}

			return Response.status(200).entity(results).build();

		}
		catch (Exception e) {
			return BusinessExceptionImpl.processException(e);
		}
	}

	// 5
	@Override
	public Response deleteDeliverables(
		HttpServletRequest request, HttpHeaders header, Company company,
		Locale locale, User user, ServiceContext serviceContext, Long id) {

		BackendAuth auth = new BackendAuthImpl();

		try {
			if (!auth.isAuth(serviceContext)) {
				throw new UnauthenticationException();
			}

			DeliverableActions actions = new DeliverableActionsImpl();
			DeliverableModel results;

			Deliverable deliverableInfo = actions.deleteById(id);
			if (Validator.isNotNull(deliverableInfo)) {
				results = DeliverableUtils.mappingToDeliverableDetailModel(
					deliverableInfo);
			}
			else {
				throw new Exception();
			}

			return Response.status(200).entity(results).build();

		}
		catch (Exception e) {
			return BusinessExceptionImpl.processException(e);
		}

	}

	@SuppressWarnings("unchecked")
	@Override
	public Response getFormData(
		HttpServletRequest request, HttpHeaders header, Company company,
		Locale locale, User user, ServiceContext serviceContext, Long id) {

		BackendAuth auth = new BackendAuthImpl();

		try {

			// Check user is login
			if (!auth.isAuth(serviceContext)) {
				throw new UnauthenticationException();
			}

			long groupId =
				GetterUtil.getLong(header.getHeaderString(Field.GROUP_ID));

			//
			LinkedHashMap<String, Object> params =
				new LinkedHashMap<String, Object>();

			params.put(Field.GROUP_ID, String.valueOf(groupId));
			params.put(DeliverableTerm.DELIVERABLE_ID, id);

			DeliverableActions actions = new DeliverableActionsImpl();

			// get JSON data deliverable
			JSONObject jsonData = actions.getFormDataById(
				serviceContext.getCompanyId(), params, null, -1, -1,
				serviceContext);

			JSONObject results = JSONFactoryUtil.createJSONObject(
				DeliverableUtils.mappingToDeliverableFormDataModel(
					(List<Document>) jsonData.get(ConstantUtils.DATA)));

			return Response.status(200).entity(
				JSONFactoryUtil.looseSerialize(results)).build();
		}
		catch (Exception e) {
			return BusinessExceptionImpl.processException(e);
		}

	}

	@Override
	public Response updateFormData(
		HttpServletRequest request, HttpHeaders header, Company company,
		Locale locale, User user, ServiceContext serviceContext, Long id,
		String formdata) {

		BackendAuth auth = new BackendAuthImpl();

		long groupId = GetterUtil.getLong(header.getHeaderString(Field.GROUP_ID));

		try {
			if (!auth.isAuth(serviceContext)) {
				throw new UnauthenticationException();
			}

			DeliverableActions actions = new DeliverableActionsImpl();

			Deliverable deliverable =
				actions.updateFormData(groupId, id, formdata, serviceContext);

			String formData = deliverable.getFormData();

			JSONObject result = JSONFactoryUtil.createJSONObject(formData);

			return Response.status(200).entity(
				JSONFactoryUtil.looseSerialize(result)).build();
		}
		catch (Exception e) {
			return BusinessExceptionImpl.processException(e);
		}
	}

	// 8
	@Override
	public Response getFormScript(
		HttpServletRequest request, HttpHeaders header, Company company,
		Locale locale, User user, ServiceContext serviceContext, Long id) {

		BackendAuth auth = new BackendAuthImpl();

		try {
			if (!auth.isAuth(serviceContext)) {
				throw new UnauthenticationException();
			}

			DeliverableActions actions = new DeliverableActionsImpl();
			String results;

			Deliverable deliverableInfo = actions.getDetailById(id);

			if (Validator.isNotNull(deliverableInfo)) {
				results = deliverableInfo.getFormScript();
			}
			else {
				throw new Exception();
			}

			return Response.status(200).entity(
				JSONFactoryUtil.looseSerialize(results)).build();

		}
		catch (Exception e) {
			return BusinessExceptionImpl.processException(e);
		}

	}

	// 9
	@Override
	public Response getPreview(
		HttpServletRequest request, HttpHeaders header, Company company,
		Locale locale, User user, ServiceContext serviceContext, Long id) {

		BackendAuth auth = new BackendAuthImpl();

		try {
			if (!auth.isAuth(serviceContext)) {
				throw new UnauthenticationException();
			}

			DeliverableActions actions = new DeliverableActionsImpl();
			String results;

			Deliverable deliverableInfo = actions.getDetailById(id);

			if (Validator.isNotNull(deliverableInfo)) {
				results = deliverableInfo.getFormReport();
			}
			else {
				throw new Exception();
			}

			return Response.status(200).entity(
				JSONFactoryUtil.looseSerialize(results)).build();

		}
		catch (Exception e) {
			return BusinessExceptionImpl.processException(e);
		}
	}

	// 11
	@Override
	public Response getDeliverableLog(
		HttpServletRequest request, HttpHeaders header, Company company,
		Locale locale, User user, ServiceContext serviceContext, Long id) {

		BackendAuth auth = new BackendAuthImpl();

		try {
			if (!auth.isAuth(serviceContext)) {
				throw new UnauthenticationException();
			}

			DeliverableLogActions action = new DeliverableLogActionsImpl();

			DeliverableLog log = action.getDeliverableLog(id);

			return Response.status(200).entity(
				JSONFactoryUtil.looseSerialize(log)).build();
		}
		catch (Exception e) {
			return BusinessExceptionImpl.processException(e);
		}
	}

	@Override
	public Response updateDeliverables(
		HttpServletRequest request, HttpHeaders header, Company company,
		Locale locale, User user, ServiceContext serviceContext, Long id,
		DeliverableUpdateModel input) {

		BackendAuth auth = new BackendAuthImpl();

		long groupId = GetterUtil.getLong(header.getHeaderString(Field.GROUP_ID));

		try {
			if (!auth.isAuth(serviceContext)) {
				throw new UnauthenticationException();
			}

			DeliverableActions action = new DeliverableActionsImpl();
			//
			String subject = input.getSubject();
			String issueDate = input.getIssueDate();
			String expireDate = input.getExpireDate();
			String revalidate = input.getRevalidate();
			String deliverableState = input.getDeliverableState();
			String deliverableAction = input.getDeliverableAction();
			//
			Deliverable deliverable = action.updateDeliverable(
				groupId, id, subject, issueDate, expireDate, revalidate,
				deliverableState, deliverableAction, serviceContext);

			DeliverableUpdateModel result =
				DeliverableUtils.mappingToDeliverablesUpdateModel(deliverable);

			return Response.status(200).entity(
				JSONFactoryUtil.looseSerialize(result)).build();
		}
		catch (Exception e) {
			return BusinessExceptionImpl.processException(e);
		}

	}

	// 18
	@SuppressWarnings("unchecked")
	@Override
	public Response getDataFormByTypeCode(
		HttpServletRequest request, HttpHeaders header, Company company,
		Locale locale, User user, ServiceContext serviceContext,
		String agencyNo, String typeCode, String keyword, String start,
		String end, String applicantIdNo, String deliverableState) {

		BackendAuth auth = new BackendAuthImpl();

		try {
			// Check user is login
			if (!auth.isAuth(serviceContext)) {
				throw new UnauthenticationException();
			}

			int startSearch = -1;
			int endSearch = -1;
			if (Validator.isNotNull(end) && Integer.getInteger(end) > 0) {
				startSearch = Integer.parseInt(start);
				endSearch = Integer.parseInt(end);
			}

			long groupId =
				GetterUtil.getLong(header.getHeaderString(Field.GROUP_ID));
			JSONObject keyJson = JSONFactoryUtil.createJSONObject(keyword);

			String pattern = String.valueOf(keyJson.get("query"));
			String paramValues = String.valueOf(keyJson.get("values"));
			String paramTypes = String.valueOf(keyJson.get("type"));

			LinkedHashMap<String, Object> params =
				new LinkedHashMap<String, Object>();
			params.put(Field.GROUP_ID, String.valueOf(groupId));
			params.put(DeliverableTerm.GOV_AGENCY_CODE, agencyNo);
			params.put(DeliverableTerm.DELIVERABLE_TYPE, typeCode);
			params.put(DeliverableTerm.APPLICANT_ID_NO, applicantIdNo);
			params.put(DeliverableTerm.DELIVERABLE_STATE, deliverableState);
			params.put("pattern", pattern);
			params.put("paramValues", paramValues);
			params.put("paramTypes", paramTypes);

			DeliverableActions actions = new DeliverableActionsImpl();
			// DeliverableResultModel results = new DeliverableResultModel();
			JSONObject results = JSONFactoryUtil.createJSONObject();

			Sort[] sorts = new Sort[] {
				SortFactoryUtil.create(
					Field.MODIFIED_DATE + ReadFilePropertiesUtils.get(ConstantUtils.SORT_PATTERN), Sort.STRING_TYPE, true)
			};
			// get JSON data deliverable
			JSONObject jsonData = actions.getFormDataByTypecode(
				serviceContext.getCompanyId(), params, sorts, startSearch,
				endSearch, serviceContext);

			results.put(ConstantUtils.TOTAL, jsonData.getInt(ConstantUtils.TOTAL));
			List<Document> docList = (List<Document>) jsonData.get(ConstantUtils.DATA);

			JSONArray formDataArr = JSONFactoryUtil.createJSONArray();
			for (Document doc : docList) {
				String formData = doc.get(DeliverableTerm.FORM_DATA);
				JSONObject formJson =
					JSONFactoryUtil.createJSONObject(formData);
				formJson.put(
					"ten_chung_chi", doc.get(DeliverableTerm.DELIVERABLE_NAME));
				formJson.put(
					"deliverableCode",
					doc.get(DeliverableTerm.DELIVERABLE_CODE));
				// _log.info("formData: "+formData);
				formDataArr.put(formJson);
			}
			results.put(ConstantUtils.DATA, formDataArr);

			return Response.status(200).entity(
				JSONFactoryUtil.looseSerialize(results)).build();

		}
		catch (Exception e) {
			return BusinessExceptionImpl.processException(e);
		}

	}

	@Override
	public Response getDeliverableAction(
		HttpServletRequest request, HttpHeaders header, Company company,
		Locale locale, User user, ServiceContext serviceContext, Long id,
		String deliverableAction) {
		return null;
	}

	@Override
	public Response getDossierIdByCode(
		HttpServletRequest request, HttpHeaders header, Company company,
		Locale locale, User user, ServiceContext serviceContext, Long id,
		String deliverableCode) {

		_log.info("START*********1");
		long groupId = GetterUtil.getLong(header.getHeaderString(Field.GROUP_ID));
		_log.info("groupId: " + groupId);
		BackendAuth auth = new BackendAuthImpl();

		try {

			if (!auth.isAuth(serviceContext)) {
				throw new UnauthenticationException();
			}

			DossierDetailModel dossierInfo = null;
			DossierFile dossierFile =
				DossierFileLocalServiceUtil.getByDeliverableCode(
					deliverableCode);
			Dossier dossier = null;
			if (dossierFile != null) {
				dossier = DossierLocalServiceUtil.fetchDossier(
					dossierFile.getDossierId());
			}
			if (dossier != null) {
				dossierInfo = OneGateUtils.mappingForGetDetail(dossier);
			}

			return Response.status(200).entity(dossierInfo).build();

		}
		catch (Exception e) {
			return BusinessExceptionImpl.processException(e);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public Response resolveConflictDeliverables(
		HttpServletRequest request, HttpHeaders header, Company company,
		Locale locale, User user, ServiceContext serviceContext) {

		long groupId = GetterUtil.getLong(header.getHeaderString(Field.GROUP_ID));
		DeliverableActions actions = new DeliverableActionsImpl();
		Indexer<Deliverable> indexer =
			IndexerRegistryUtil.nullSafeGetIndexer(Deliverable.class);

		LinkedHashMap<String, Object> params =
			new LinkedHashMap<String, Object>();
		params.put(Field.GROUP_ID, String.valueOf(groupId));

		// get JSON data deliverable
		JSONObject jsonData = actions.getListDeliverable(
			user.getUserId(), serviceContext.getCompanyId(), params, null, -1,
			-1, serviceContext);

		long total = jsonData.getLong(ConstantUtils.TOTAL);

		if (total > 0) {
			List<Document> lstDocuments = (List<Document>) jsonData.get(ConstantUtils.DATA);
			for (Document document : lstDocuments) {
				long deliverableId = GetterUtil.getLong(
					document.get(DeliverableTerm.DELIVERABLE_ID));
				long companyId =
					GetterUtil.getLong(document.get(Field.COMPANY_ID));
				String uid = document.get(Field.UID);
				Deliverable oldDeliverable =
					DeliverableLocalServiceUtil.fetchDeliverable(deliverableId);
				if (oldDeliverable == null) {
					try {
						indexer.delete(companyId, uid);
					}
					catch (SearchException e) {
						_log.error(e);
					}
				}
			}
		}

		return Response.status(200).entity(ReadFilePropertiesUtils.get(ConstantUtils.STATUS_DONE)).build();
	}

	@Override
	public Response importDeliverables(
		HttpServletRequest request, HttpHeaders header, Company company,
		Locale locale, User user, ServiceContext serviceContext,
		Attachment file) {

		System.out.println("================POST===========================");
		JSONObject result = JSONFactoryUtil.createJSONObject();

		BackendAuth auth = new BackendAuthImpl();

		try {

			// Check user is login
			if (!auth.isAuth(serviceContext)) {
				throw new UnauthenticationException();
			}

			return Response.status(200).entity(
				JSONFactoryUtil.looseSerialize(result)).build();
		}
		catch (Exception e) {
			return BusinessExceptionImpl.processException(e);
		}
	}

	@Override
	public Response getFormScript2(
		HttpServletRequest request, HttpHeaders header, Company company,
		Locale locale, User user, ServiceContext serviceContext, Long id) {

		BackendAuth auth = new BackendAuthImpl();

		try {
			if (!auth.isAuth(serviceContext)) {
				throw new UnauthenticationException();
			}

			JSONObject results = JSONFactoryUtil.createJSONObject();
			results.put("success", true);
			return Response.status(200).entity(
				JSONFactoryUtil.looseSerialize(results)).build();

		}
		catch (Exception e) {
			return BusinessExceptionImpl.processException(e);
		}

	}

	public Response getDeliverableUrl(
		@Context HttpServletRequest request, @Context HttpHeaders header,
		@Context Company company, @Context Locale locale, @Context User user,
		@Context ServiceContext serviceContext,
		@ApiParam(value = "deliverableCode of Deliverable", required = true) @FormParam("deliverableCode") String deliverableCode,
		@ApiParam(value = "id of DossierFile", required = true) @FormParam("dossierFileId") Long dossierFileId) {

		JSONObject result = JSONFactoryUtil.createJSONObject();

		result.put("url", StringPool.BLANK);
		try {

			_log.info(
				"================GET===========================" +
					deliverableCode + " " + deliverableCode + " " +
					header.getHeaderString(Field.GROUP_ID));

			if (Validator.isNull(deliverableCode)) {
				return Response.status(204).entity(
					JSONFactoryUtil.looseSerialize(result)).build();
			}

			BackendAuth auth = new BackendAuthImpl();

			// Check user is login
			if (!auth.isAuth(serviceContext)) {
				throw new UnauthenticationException();
			}
			long groupId =
				GetterUtil.getLong(header.getHeaderString(Field.GROUP_ID));

			Deliverable deliverable =
				DeliverableLocalServiceUtil.getByF_GID_DCODE(
					groupId, deliverableCode);

			if (Validator.isNotNull(deliverable) &&
				deliverable.getFileEntryId() > 0) {

				FileEntry fileEntry = DLAppLocalServiceUtil.getFileEntry(
					deliverable.getFileEntryId());

				result.put("fileName", fileEntry.getFileName());
				result.put("fileType", fileEntry.getMimeType());
				result.put(
					"url",
					DLUtil.getPreviewURL(
						fileEntry, fileEntry.getFileVersion(),
						serviceContext.getThemeDisplay(), StringPool.BLANK));

			}

			return Response.status(200).entity(
				JSONFactoryUtil.looseSerialize(result)).build();
		}
		catch (Exception e) {
			return BusinessExceptionImpl.processException(e);
		}
	}

}
