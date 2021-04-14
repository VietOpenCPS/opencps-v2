
package org.opencps.api.controller.impl;

import java.net.HttpURLConnection;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
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
import org.opencps.api.constants.ConstantUtils;
import org.opencps.api.controller.DeliverablesManagement;
import org.opencps.api.controller.util.DeliverableUtils;
import org.opencps.api.controller.util.MessageUtil;
import org.opencps.api.controller.util.OneGateUtils;
import org.opencps.api.controller.util.OpenCPSUtils;
import org.opencps.api.deliverable.model.DeliverableInputModel;
import org.opencps.api.deliverable.model.DeliverableModel;
import org.opencps.api.deliverable.model.DeliverableSearchModel;
import org.opencps.api.deliverable.model.DeliverableUpdateModel;
import org.opencps.api.dossier.model.DossierDetailModel;
import org.opencps.auth.api.BackendAuth;
import org.opencps.auth.api.BackendAuthImpl;
import org.opencps.auth.api.exception.UnauthenticationException;
import org.opencps.auth.utils.APIDateTimeUtils;
import org.opencps.dossiermgt.action.DeliverableActions;
import org.opencps.dossiermgt.action.DeliverableLogActions;
import org.opencps.dossiermgt.action.impl.DeliverableActionsImpl;
import org.opencps.dossiermgt.action.impl.DeliverableLogActionsImpl;
import org.opencps.dossiermgt.action.util.DeliverableNumberGenerator;
import org.opencps.dossiermgt.constants.DeliverableTerm;
import org.opencps.dossiermgt.constants.DossierTerm;
import org.opencps.dossiermgt.model.*;
import org.opencps.dossiermgt.service.DeliverableLocalServiceUtil;
import org.opencps.dossiermgt.service.DeliverableTypeLocalServiceUtil;
import org.opencps.dossiermgt.service.DossierFileLocalServiceUtil;
import org.opencps.dossiermgt.service.DossierLocalServiceUtil;

import com.liferay.document.library.kernel.service.DLAppLocalServiceUtil;
import com.liferay.document.library.kernel.util.DLUtil;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
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
import org.opencps.usermgt.model.Employee;
import org.opencps.usermgt.service.EmployeeLocalServiceUtil;

public class DeliverablesManagementImpl implements DeliverablesManagement {

	private static Log _log =
		LogFactoryUtil.getLog(DeliverablesManagementImpl.class);
	
	private static final String API_LIST_DELIVERABLE = "API_LIST_DELIVERABLE";
	private static final String API_VIEW_DELIVERABLE = "API_VIEW_DELIVERABLE";

	@SuppressWarnings("unchecked")
	@Override
	public Response getDeliverables(
		HttpServletRequest request, HttpHeaders header, Company company,
		Locale locale, User user, ServiceContext serviceContext,
		DeliverableSearchModel search) {

		// TODO
		BackendAuth auth = new BackendAuthImpl();
		long groupId =
				GetterUtil.getLong(header.getHeaderString(Field.GROUP_ID));
		JSONObject bodyResponse = JSONFactoryUtil.createJSONObject();

		try {

			// Check user is login
			if (!auth.isAuth(serviceContext)) {
				throw new UnauthenticationException();
			}

			if (search.getEnd() == 0) {
				search.setStart(QueryUtil.ALL_POS);
				search.setEnd(QueryUtil.ALL_POS);
			}			

			// Default sort by modifiedDate
			String dateSort = String.format(MessageUtil.getMessage(ConstantUtils.QUERY_SORT), Field.MODIFIED_DATE);
			
			Sort[] sorts = new Sort[] {
				SortFactoryUtil.create(
					dateSort, Sort.STRING_TYPE, true)
			};

			if (Validator.isNotNull(search.getSort()) &&
				Validator.isNotNull(search.getOrder())) {
				String querySort = String.format(MessageUtil.getMessage(ConstantUtils.QUERY_SORT), search.getSort());
				
				sorts = new Sort[] {
					SortFactoryUtil.create(
						querySort, Sort.STRING_TYPE,
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
			// String owner = search.getOwner();
			// if (Validator.isNotNull(owner)) {
			// params.put(DossierTerm.OWNER, search.getOwner());
			// } else {
			// params.put(DossierTerm.OWNER, String.valueOf(true));
			// }
			params.put(DossierTerm.USER_ID, user.getUserId());

			DeliverableActions actions = new DeliverableActionsImpl();
			JSONObject results = JSONFactoryUtil.createJSONObject();
			// DeliverableResultModel results = new DeliverableResultModel();

			// get JSON data deliverable
			JSONObject jsonData = actions.getListDeliverable(
				user.getUserId(), serviceContext.getCompanyId(), params, sorts,
				search.getStart(), search.getEnd(), serviceContext);
			// JSONObject result = action.getListDeliverable(state, agency,
			// type, applicant);
			// results.setTotal(jsonData.getInt(ConstantUtils.TOTAL));
			results.put(ConstantUtils.TOTAL, jsonData.getInt(ConstantUtils.TOTAL));
			// results.getData()
			// .addAll(DeliverableUtils.mappingToDeliverableResultModel((List<Document>)
			// jsonData.get(ConstantUtils.DATA)));
			List<Document> docList = (List<Document>) jsonData.get(ConstantUtils.DATA);
			

			JSONArray formDataArr = JSONFactoryUtil.createJSONArray();
			for (Document doc : docList) {
				String formData = doc.get(DeliverableTerm.FORM_DATA);
				JSONObject formJson =
					JSONFactoryUtil.createJSONObject(formData);
				formJson.put(
					DeliverableTerm.TEN_CHUNG_CHI, doc.get(DeliverableTerm.DELIVERABLE_NAME));
				formJson.put(
					DeliverableTerm.DELIVERABLE_CODE,
					doc.get(DeliverableTerm.DELIVERABLE_CODE));
				// _log.info("formData: "+formData);
				formDataArr.put(formJson);
			}
			results.put(ConstantUtils.DATA, formDataArr);
			
			bodyResponse.put("status", HttpURLConnection.HTTP_OK);
			bodyResponse.put("total", results.getInt(ConstantUtils.TOTAL));
			// ghi log vao syncTracking
			OpenCPSUtils.addSyncTracking(API_LIST_DELIVERABLE, user.getUserId(),
					groupId, StringPool.NULL,StringPool.NULL, StringPool.NULL, 1,
					JSONFactoryUtil.looseSerialize(search), bodyResponse.toJSONString());
						
			return Response.status(HttpURLConnection.HTTP_OK).entity(
				JSONFactoryUtil.looseSerialize(results)).build();
			// return Response.status(HttpURLConnection.HTTP_OK).entity(results).build();
		}
		catch (Exception e) {
			
			bodyResponse.put("status", HttpURLConnection.HTTP_INTERNAL_ERROR);
			// ghi log vao syncTracking
			OpenCPSUtils.addSyncTracking(API_LIST_DELIVERABLE, user.getUserId(),
					groupId, StringPool.NULL,StringPool.NULL, StringPool.NULL, 0,
					JSONFactoryUtil.looseSerialize(search), bodyResponse.toJSONString());
			return BusinessExceptionImpl.processException(e);
		}

	}

	@Override
	public Response insertDeliverables(
		HttpServletRequest request, HttpHeaders header, Company company,
		Locale locale, User user, ServiceContext serviceContext,
		DeliverableInputModel input) {

		// TODO Add Deliverable Type
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

			return Response.status(HttpURLConnection.HTTP_OK).entity(
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

		// TODO Add Deliverable Type
		BackendAuth auth = new BackendAuthImpl();

		long groupId = GetterUtil.getLong(header.getHeaderString(Field.GROUP_ID));

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

			// ghi log vao syncTracking
			OpenCPSUtils.addSyncTracking(API_VIEW_DELIVERABLE, user.getUserId(),
					deliverableInfo.getGroupId(), StringPool.NULL,StringPool.NULL, StringPool.NULL, 1,
					String.valueOf(id), JSONFactoryUtil.looseSerialize(results));
			
			return Response.status(HttpURLConnection.HTTP_OK).entity(results).build();

		}
		catch (Exception e) {
			// ghi log vao syncTracking
			OpenCPSUtils.addSyncTracking(API_VIEW_DELIVERABLE, user.getUserId(),
					groupId, StringPool.NULL,StringPool.NULL, StringPool.NULL, 0,
					String.valueOf(id),  StringPool.NULL);
			return BusinessExceptionImpl.processException(e);
		}
	}

	// 5
	@Override
	public Response deleteDeliverables(
		HttpServletRequest request, HttpHeaders header, Company company,
		Locale locale, User user, ServiceContext serviceContext, Long id) {

		// TODO Add Deliverable Type
		BackendAuth auth = new BackendAuthImpl();

		// long groupId = GetterUtil.getLong(header.getHeaderString(Field.GROUP_ID));

		try {
			if (!auth.isAuth(serviceContext)) {
				throw new UnauthenticationException();
			}

			// _log.info("groupId: "+groupId +"*keyword*: "+ id);
			DeliverableActions actions = new DeliverableActionsImpl();
			DeliverableModel results;

			Deliverable deliverableInfo = actions.deleteById(id);
			// _log.info("deliverableInfo: "+ deliverableInfo);
			if (Validator.isNotNull(deliverableInfo)) {
				results = DeliverableUtils.mappingToDeliverableDetailModel(
					deliverableInfo);
			}
			else {
				throw new Exception();
			}

			return Response.status(HttpURLConnection.HTTP_OK).entity(results).build();

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

		// TODO
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
				serviceContext.getCompanyId(), params, null, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
				serviceContext);

			JSONObject results = JSONFactoryUtil.createJSONObject(
				DeliverableUtils.mappingToDeliverableFormDataModel(
					(List<Document>) jsonData.get(ConstantUtils.DATA)));

			return Response.status(HttpURLConnection.HTTP_OK).entity(
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

			return Response.status(HttpURLConnection.HTTP_OK).entity(
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

		// TODO Add Deliverable Type
		BackendAuth auth = new BackendAuthImpl();

		// long groupId = GetterUtil.getLong(header.getHeaderString(Field.GROUP_ID));

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

			return Response.status(HttpURLConnection.HTTP_OK).entity(
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

		// TODO Add Deliverable Type
		BackendAuth auth = new BackendAuthImpl();

		// long groupId = GetterUtil.getLong(header.getHeaderString(Field.GROUP_ID));

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

			return Response.status(HttpURLConnection.HTTP_OK).entity(
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

		// TODO Add Deliverable Type
		BackendAuth auth = new BackendAuthImpl();

		// long groupId = GetterUtil.getLong(header.getHeaderString(Field.GROUP_ID));

		try {
			if (!auth.isAuth(serviceContext)) {
				throw new UnauthenticationException();
			}

			DeliverableLogActions action = new DeliverableLogActionsImpl();

			DeliverableLog log = action.getDeliverableLog(id);

			return Response.status(HttpURLConnection.HTTP_OK).entity(
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

		// TODO Add Deliverable Type
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

			return Response.status(HttpURLConnection.HTTP_OK).entity(
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
			if (Validator.isNotNull(end) && !ConstantUtils.QUERY_ZERO.equals(end)) {
				startSearch = Integer.parseInt(start);
				endSearch = Integer.parseInt(end);
			}

			long groupId =
				GetterUtil.getLong(header.getHeaderString(Field.GROUP_ID));
			_log.info("groupId: " + groupId + "*keyword*: " + keyword);
			_log.info("agencyNo: " + agencyNo + "*typeCode*: " + typeCode);
			JSONObject keyJson = JSONFactoryUtil.createJSONObject(keyword);

			String pattern = String.valueOf(keyJson.get(ConstantUtils.DELIVERABLE_DATAFORM_QUERY_KEY));
			String paramValues = String.valueOf(keyJson.get(ConstantUtils.DELIVERABLE_DATAFORM_VALUES_KEY));
			String paramTypes = String.valueOf(keyJson.get(ConstantUtils.DELIVERABLE_DATAFORM_TYPE_KEY));

			LinkedHashMap<String, Object> params =
				new LinkedHashMap<String, Object>();
			params.put(Field.GROUP_ID, String.valueOf(groupId));
			params.put(DeliverableTerm.GOV_AGENCY_CODE, agencyNo);
			params.put(DeliverableTerm.DELIVERABLE_TYPE, typeCode);
			params.put(DeliverableTerm.APPLICANT_ID_NO, applicantIdNo);
			params.put(DeliverableTerm.DELIVERABLE_STATE, deliverableState);
			params.put(ConstantUtils.DELIVERABLE_DATAFORM_PATTERN_KEY, pattern);
			params.put(ConstantUtils.DELIVERABLE_DATAFORM_PARAMVALUES_KEY, paramValues);
			params.put(ConstantUtils.DELIVERABLE_DATAFORM_PARAMTYPES_KEY, paramTypes);

			DeliverableActions actions = new DeliverableActionsImpl();
			// DeliverableResultModel results = new DeliverableResultModel();
			JSONObject results = JSONFactoryUtil.createJSONObject();
			String dateSort = String.format(MessageUtil.getMessage(ConstantUtils.QUERY_SORT), Field.MODIFIED_DATE);
			
			Sort[] sorts = new Sort[] {
				SortFactoryUtil.create(
					dateSort, Sort.STRING_TYPE, true)
			};
			// get JSON data deliverable
			JSONObject jsonData = actions.getFormDataByTypecode(
				serviceContext.getCompanyId(), params, sorts, startSearch,
				endSearch, serviceContext);

			// _log.info("total: "+jsonData.getInt(ConstantUtils.TOTAL));
			// results.setTotal(jsonData.getInt(ConstantUtils.TOTAL));
			// results.getData()
			// .addAll(DeliverableUtils.mappingToDeliverableResultModel((List<Document>)
			// jsonData.get(ConstantUtils.DATA)));

			// TODO
			results.put(ConstantUtils.TOTAL, jsonData.getInt(ConstantUtils.TOTAL));
			List<Document> docList = (List<Document>) jsonData.get(ConstantUtils.DATA);

			JSONArray formDataArr = JSONFactoryUtil.createJSONArray();
			for (Document doc : docList) {
				String formData = doc.get(DeliverableTerm.FORM_DATA);
				JSONObject formJson =
					JSONFactoryUtil.createJSONObject(formData);
				formJson.put(
					DeliverableTerm.TEN_CHUNG_CHI, doc.get(DeliverableTerm.DELIVERABLE_NAME));
				formJson.put(
					DeliverableTerm.DELIVERABLE_CODE,
					doc.get(DeliverableTerm.DELIVERABLE_CODE));
				// _log.info("formData: "+formData);
				formDataArr.put(formJson);
			}
			results.put(ConstantUtils.DATA, formDataArr);

			return Response.status(HttpURLConnection.HTTP_OK).entity(
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

		// TODO Auto-generated method stub
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

			_log.info("deliverableCode: " + deliverableCode);
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

			return Response.status(HttpURLConnection.HTTP_OK).entity(dossierInfo).build();

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
		// long userId = user.getUserId();
		DeliverableActions actions = new DeliverableActionsImpl();
		Indexer<Deliverable> indexer =
			IndexerRegistryUtil.nullSafeGetIndexer(Deliverable.class);

		LinkedHashMap<String, Object> params =
			new LinkedHashMap<String, Object>();
		params.put(Field.GROUP_ID, String.valueOf(groupId));

		// JSONObject jsonData = actions.getDossiers(user.getUserId(),
		// company.getCompanyId(), groupId, params, null,
		// -1, -1, serviceContext);

		// get JSON data deliverable
		JSONObject jsonData = actions.getListDeliverable(
			user.getUserId(), serviceContext.getCompanyId(), params, null, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, serviceContext);

		long total = jsonData.getLong(ConstantUtils.TOTAL);
		// JSONArray dossierArr = JSONFactoryUtil.createJSONArray();

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

		return Response.status(HttpURLConnection.HTTP_OK).entity(ConstantUtils.API_JSON_EMPTY).build();
	}

	@Override
	public Response importDeliverables(
		HttpServletRequest request, HttpHeaders header, Company company,
		Locale locale, User user, ServiceContext serviceContext,
		Attachment file) {

		_log.info("================POST===========================" + file);
		JSONObject result = JSONFactoryUtil.createJSONObject();

		BackendAuth auth = new BackendAuthImpl();
		backend.auth.api.BackendAuth auth2 =
			new backend.auth.api.BackendAuthImpl();

		try {

			// Check user is login
			if (!auth.isAuth(serviceContext)) {
				throw new UnauthenticationException();
			}
			if (!auth2.isAdmin(serviceContext, ConstantUtils.ROLE_ADMIN_LOWER)) {
				// return Response.status(
				// HttpURLConnection.HTTP_UNAUTHORIZED).entity(
				// "User not permission process!").build();
			}
			long groupId =
				GetterUtil.getLong(header.getHeaderString(Field.GROUP_ID));
			long userId = user.getUserId();
			long companyId = user.getCompanyId();
			String userName = user.getFullName();

			JSONArray deliverables = 
					DeliverableUtils.readZipDeliverabe(file, userId, groupId, companyId, userName, serviceContext);
			int size = 0;
			
			 result.put(ConstantUtils.TOTAL, deliverables.length());
			 result.put(ConstantUtils.API_JSON_COUNT, size);

			return Response.status(HttpURLConnection.HTTP_OK).entity(
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

		// TODO Add Deliverable Type
		BackendAuth auth = new BackendAuthImpl();

		// long groupId = GetterUtil.getLong(header.getHeaderString(Field.GROUP_ID));

		try {
			if (!auth.isAuth(serviceContext)) {
				throw new UnauthenticationException();
			}

			JSONObject results = JSONFactoryUtil.createJSONObject();
			results.put(ConstantUtils.API_JSON_SUCCESS, true);
			return Response.status(HttpURLConnection.HTTP_OK).entity(
				JSONFactoryUtil.looseSerialize(results)).build();

		}
		catch (Exception e) {
			return BusinessExceptionImpl.processException(e);
		}

	}

	@Override
	public Response importDeliverables2(
		HttpServletRequest request, HttpHeaders header, Company company,
		Locale locale, User user, ServiceContext serviceContext,
		Attachment file, String deliverableType) {

		try {

//			System.out.println(
//				"================POST===========================" +
//					deliverableType + " " + file);
			JSONObject result = JSONFactoryUtil.createJSONObject();

			if (Validator.isNull(deliverableType) || Validator.isNull(file)) {
				return Response.status(204).entity(
					JSONFactoryUtil.looseSerialize(result)).build();
			}

			BackendAuth auth = new BackendAuthImpl();
			backend.auth.api.BackendAuth auth2 =
				new backend.auth.api.BackendAuthImpl();

			// Check user is login
			if (!auth.isAuth(serviceContext)) {
				throw new UnauthenticationException();
			}
			if (!auth2.isAdmin(serviceContext, ConstantUtils.ROLE_ADMIN_LOWER)) {
				// return Response.status(
				// HttpURLConnection.HTTP_UNAUTHORIZED).entity(
				// "User not permission process!").build();
			}
			long groupId =
				GetterUtil.getLong(header.getHeaderString(Field.GROUP_ID));
			long userId = user.getUserId();
			long companyId = user.getCompanyId();
			String userName = user.getFullName();

			DataHandler dataHandle = file.getDataHandler();
			JSONArray deliverables = DeliverableUtils.readExcelDeliverable(dataHandle.getInputStream());

			int size = 0;
			for (int i = 0; i < deliverables.length(); i++) {

				JSONObject deliverable = deliverables.getJSONObject(i);

				if (Validator.isNotNull(deliverable.get(DeliverableTerm.DELIVERABLE_CODE))) {

					Deliverable deliverableObj =
						DeliverableLocalServiceUtil.getByF_GID_DCODE(
							groupId, deliverable.getString(DeliverableTerm.DELIVERABLE_CODE));

					deliverable.put(
						DeliverableTerm.DELIVERABLE_ID, Validator.isNotNull(deliverableObj)
							? deliverableObj.getDeliverableId() : 0);
					deliverable.put(Field.GROUP_ID, groupId);
					deliverable.put(Field.USER_ID, userId);
					deliverable.put(Field.COMPANY_ID, companyId);
					deliverable.put(Field.USER_NAME, userName);
					deliverable.put(DeliverableTerm.DELIVERABLE_TYPE, deliverableType);
					deliverable.put(DeliverableTerm.FILE_ATTACH, false);
					DeliverableLocalServiceUtil.adminProcessData(
							deliverable);
//					System.out.println("add================" + deliverable);
					size += 1;
				}
			}

			result.put(ConstantUtils.TOTAL, size);

			return Response.status(HttpURLConnection.HTTP_OK).entity(
				JSONFactoryUtil.looseSerialize(result)).build();
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

		result.put(DeliverableTerm.URL, StringPool.BLANK);
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
			backend.auth.api.BackendAuth auth2 =
				new backend.auth.api.BackendAuthImpl();

			// Check user is login
			if (!auth.isAuth(serviceContext)) {
				throw new UnauthenticationException();
			}
			if (!auth2.isAdmin(serviceContext, ConstantUtils.ROLE_ADMIN_LOWER)) {
				// return Response.status(
				// HttpURLConnection.HTTP_UNAUTHORIZED).entity(
				// "User not permission process!").build();
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

				result.put(ConstantUtils.DELIVERABLE_DL_FILENAME, fileEntry.getFileName());
				result.put(ConstantUtils.DELIVERABLE_DL_FILETYPE, fileEntry.getMimeType());
				result.put(
					DeliverableTerm.URL,
					DLUtil.getPreviewURL(
						fileEntry, fileEntry.getFileVersion(),
						serviceContext.getThemeDisplay(), StringPool.BLANK));

			}

			return Response.status(HttpURLConnection.HTTP_OK).entity(
				JSONFactoryUtil.looseSerialize(result)).build();
		}
		catch (Exception e) {
			return BusinessExceptionImpl.processException(e);
		}
	}
//	private static String CHE_DO_MOT_LAN = "Một lần";
//	private static String CHE_DO = "chedo";
	@Override
	public Response importDeliverables3(HttpServletRequest request, HttpHeaders header, Company company, Locale locale, User user, ServiceContext serviceContext, Attachment file, String deliverableTypeCode) {
		try {

			JSONObject result = JSONFactoryUtil.createJSONObject();

			if (Validator.isNull(deliverableTypeCode) || Validator.isNull(file)) {
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
			long userId = user.getUserId();
			long companyId = user.getCompanyId();
			String userName = user.getFullName();

			DataHandler dataHandle = file.getDataHandler();
			JSONArray deliverables = DeliverableUtils.readExcelDeliverableV3(dataHandle.getInputStream());

			int size = 0;
			if(Validator.isNotNull(deliverables)) {
				_log.debug("Deliverables: " + JSONFactoryUtil.looseSerialize(deliverables));
				for (int i = 0; i < deliverables.length(); i++) {
					JSONObject deliverable = deliverables.getJSONObject(i);
					if (Validator.isNotNull(deliverable)) {
						JSONObject formData = deliverable.getJSONObject(DeliverableTerm.FORM_DATA);
						String gioitinh = formData.getString(DeliverableTerm.GIOI_TINH_TEXT);
						if (gioitinh.contains(DeliverableTerm.GIOI_TINH_NAM)) {
							_log.debug("NAM ......");
							formData.put(DeliverableTerm.GIOI_TINH, 0);
						} else if (gioitinh.contains(DeliverableTerm.GIOI_TINH_NU)) {
							_log.debug("NU ......");
							formData.put(DeliverableTerm.GIOI_TINH, 1);
						} else {
							_log.debug("KO ......");
							formData.put(DeliverableTerm.GIOI_TINH, 2);
						}

						DeliverableType delType = DeliverableTypeLocalServiceUtil.getByCode(groupId, deliverableTypeCode);
						String ngaysinh = formData.getString(DeliverableTerm.NGAY_SINH);
						if (Validator.isNotNull(ngaysinh)) {
							Date ngaysinhParse = APIDateTimeUtils.convertStringToDate(ngaysinh, APIDateTimeUtils._NORMAL_DATE);
							if (Validator.isNotNull(ngaysinhParse)) {
								System.out.println("Ngay sinh : " + ngaysinhParse);

								DateFormat dateFormat = new SimpleDateFormat("dd/MM/YYYY");
								String strDate = dateFormat.format(ngaysinhParse);

								System.out.println("Converted String: " + strDate);
								formData.put(DeliverableTerm.NGAY_SINH, strDate);
							}
						}

						deliverable.put(Field.GROUP_ID, groupId);
						deliverable.put(Field.USER_ID, userId);
						deliverable.put(Field.COMPANY_ID, companyId);
						deliverable.put(Field.USER_NAME, userName);
						deliverable.put(DeliverableTerm.DELIVERABLE_TYPE, delType.getTypeCode());
						deliverable.put(DeliverableTerm.FILE_ATTACH, false);
						deliverable.put(DeliverableTerm.FORM_DATA, formData.toString());

						Employee employee = EmployeeLocalServiceUtil.fetchByF_mappingUserId(groupId, userId);
						if (Validator.isNotNull(employee)) {
							String scope = employee.getScope();
							if (scope.split(",").length > 1) {
								String[] govAgencyCode = scope.split(",");
								scope = govAgencyCode[0];
							}
							deliverable.put(DeliverableTerm.GOV_AGENCY_CODE, scope);
						}
						DeliverableLocalServiceUtil.adminProcessData(deliverable);
						size += 1;
					}
				}
			}
			result.put(ConstantUtils.TOTAL, size);

			return Response.status(HttpURLConnection.HTTP_OK).entity(
					JSONFactoryUtil.looseSerialize(result)).build();
		}
		catch (Exception e) {
			return BusinessExceptionImpl.processException(e);
		}
	}

	@Override
	public Response genDeliverable(HttpServletRequest request, HttpHeaders header, Company company,
								   Locale locale, User user, ServiceContext serviceContext, String typeCode,
								   String govAgencyCode) {

		long groupId = GetterUtil.getLong(header.getHeaderString(Field.GROUP_ID));
		_log.info("groupId: " + groupId);
		BackendAuth auth = new BackendAuthImpl();

		try {

			if (!auth.isAuth(serviceContext)) {
				throw new UnauthenticationException();
			}
			_log.debug("typeCode: " + typeCode);
			_log.debug("govAgencyCode: " + govAgencyCode);
			String deliverableCode = DeliverableNumberGenerator.genDeliverableNumberByGovType(groupId,typeCode,govAgencyCode);
			return Response.status(HttpURLConnection.HTTP_OK).entity(deliverableCode).build();

		}
		catch (Exception e) {
			return BusinessExceptionImpl.processException(e);
		}
	}
}
