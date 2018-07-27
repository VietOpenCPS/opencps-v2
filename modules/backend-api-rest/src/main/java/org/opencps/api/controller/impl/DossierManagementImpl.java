package org.opencps.api.controller.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.UUID;

import javax.activation.DataHandler;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;

import org.apache.commons.httpclient.util.HttpURLConnection;
import org.apache.cxf.jaxrs.ext.multipart.Attachment;
import org.opencps.api.controller.DossierManagement;
import org.opencps.api.controller.exception.ErrorMsg;
import org.opencps.api.controller.util.DossierFileUtils;
import org.opencps.api.controller.util.DossierMarkUtils;
import org.opencps.api.controller.util.DossierUtils;
import org.opencps.api.dossier.model.DoActionModel;
import org.opencps.api.dossier.model.DossierDetailModel;
import org.opencps.api.dossier.model.DossierInputModel;
import org.opencps.api.dossier.model.DossierResultsModel;
import org.opencps.api.dossier.model.DossierSearchModel;
import org.opencps.api.dossierfile.model.DossierFileModel;
import org.opencps.api.dossiermark.model.DossierMarkInputModel;
import org.opencps.api.dossiermark.model.DossierMarkModel;
import org.opencps.api.dossiermark.model.DossierMarkResultDetailModel;
import org.opencps.api.dossiermark.model.DossierMarkResultsModel;
import org.opencps.api.processsequence.model.ActionModel;
import org.opencps.api.processsequence.model.DossierSequenceModel;
import org.opencps.api.processsequence.model.DossierSequenceResultModel;
import org.opencps.api.processsequence.model.ProcessSequenceOutputModel;
import org.opencps.api.reassign.model.ReAssign;
import org.opencps.api.reassign.model.ToUsers;
import org.opencps.auth.api.BackendAuth;
import org.opencps.auth.api.BackendAuthImpl;
import org.opencps.auth.api.exception.NotFoundException;
import org.opencps.auth.api.exception.UnauthenticationException;
import org.opencps.auth.api.exception.UnauthorizationException;
import org.opencps.auth.api.keys.ActionKeys;
import org.opencps.auth.utils.APIDateTimeUtils;
import org.opencps.datamgt.model.DictCollection;
import org.opencps.datamgt.model.DictItem;
import org.opencps.datamgt.service.DictCollectionLocalServiceUtil;
import org.opencps.datamgt.service.DictItemLocalServiceUtil;
import org.opencps.datamgt.utils.DateTimeUtils;
import org.opencps.dossiermgt.action.DossierActions;
import org.opencps.dossiermgt.action.DossierFileActions;
import org.opencps.dossiermgt.action.DossierMarkActions;
import org.opencps.dossiermgt.action.DossierUserActions;
import org.opencps.dossiermgt.action.impl.DossierActionsImpl;
import org.opencps.dossiermgt.action.impl.DossierFileActionsImpl;
import org.opencps.dossiermgt.action.impl.DossierMarkActionsImpl;
import org.opencps.dossiermgt.action.impl.DossierPermission;
import org.opencps.dossiermgt.action.impl.DossierUserActionsImpl;
import org.opencps.dossiermgt.action.util.DossierNumberGenerator;
import org.opencps.dossiermgt.action.util.DossierOverDueUtils;
import org.opencps.dossiermgt.action.util.SpecialCharacterUtils;
import org.opencps.dossiermgt.constants.DossierActionTerm;
import org.opencps.dossiermgt.constants.DossierTerm;
import org.opencps.dossiermgt.model.ActionConfig;
import org.opencps.dossiermgt.model.Dossier;
import org.opencps.dossiermgt.model.DossierAction;
import org.opencps.dossiermgt.model.DossierActionUser;
import org.opencps.dossiermgt.model.DossierFile;
import org.opencps.dossiermgt.model.DossierMark;
import org.opencps.dossiermgt.model.DossierPart;
import org.opencps.dossiermgt.model.DossierTemplate;
import org.opencps.dossiermgt.model.DossierUser;
import org.opencps.dossiermgt.model.ProcessAction;
import org.opencps.dossiermgt.model.ProcessOption;
import org.opencps.dossiermgt.model.ProcessSequence;
import org.opencps.dossiermgt.model.ProcessStep;
import org.opencps.dossiermgt.model.ServiceConfig;
import org.opencps.dossiermgt.model.ServiceInfo;
import org.opencps.dossiermgt.model.ServiceProcess;
import org.opencps.dossiermgt.model.ServiceProcessRole;
import org.opencps.dossiermgt.model.StepConfig;
import org.opencps.dossiermgt.service.ActionConfigLocalServiceUtil;
import org.opencps.dossiermgt.service.DossierActionLocalServiceUtil;
import org.opencps.dossiermgt.service.DossierActionUserLocalServiceUtil;
import org.opencps.dossiermgt.service.DossierFileLocalServiceUtil;
import org.opencps.dossiermgt.service.DossierLocalServiceUtil;
import org.opencps.dossiermgt.service.DossierPartLocalServiceUtil;
import org.opencps.dossiermgt.service.DossierRequestUDLocalServiceUtil;
import org.opencps.dossiermgt.service.DossierTemplateLocalServiceUtil;
import org.opencps.dossiermgt.service.DossierUserLocalServiceUtil;
import org.opencps.dossiermgt.service.ProcessOptionLocalServiceUtil;
import org.opencps.dossiermgt.service.ProcessSequenceLocalServiceUtil;
import org.opencps.dossiermgt.service.ProcessStepLocalServiceUtil;
import org.opencps.dossiermgt.service.ServiceConfigLocalServiceUtil;
import org.opencps.dossiermgt.service.ServiceInfoLocalServiceUtil;
import org.opencps.dossiermgt.service.ServiceProcessLocalServiceUtil;
import org.opencps.dossiermgt.service.ServiceProcessRoleLocalServiceUtil;
import org.opencps.dossiermgt.service.StepConfigLocalServiceUtil;
import org.opencps.dossiermgt.service.persistence.DossierActionUserPK;
import org.opencps.usermgt.model.Applicant;
import org.opencps.usermgt.service.ApplicantLocalServiceUtil;

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
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.uuid.PortalUUIDUtil;

public class DossierManagementImpl implements DossierManagement {

	public static final String RT_CANCELLING = "cancelling";
	public static final String RT_CORRECTING = "correcting";
	public static final String RT_SUBMITTING = "submitting";

	@SuppressWarnings("unchecked")
	@Override
	public Response getDossiers(HttpServletRequest request, HttpHeaders header, Company company, Locale locale,
			User user, ServiceContext serviceContext, DossierSearchModel query) {

		long groupId = GetterUtil.getLong(header.getHeaderString("groupId"));
		BackendAuth auth = new BackendAuthImpl();
		DossierPermission dossierPermission = new DossierPermission();
		DossierActions actions = new DossierActionsImpl();

		try {
			boolean isCitizen = false;

//			if (!query.getSecetKey().contentEquals("OPENCPSV2")) {
//
//				if (!auth.isAuth(serviceContext)) {
//					throw new UnauthenticationException();
//				}
//
//				isCitizen = dossierPermission.isCitizen(user.getUserId());
//
//				dossierPermission.hasGetDossiers(groupId, user.getUserId(), query.getSecetKey());
//			} else {
//				groupId = 55217;
//			}

			if (Validator.isNull(query.getEnd()) || query.getEnd() == 0) {

				query.setStart(-1);

				query.setEnd(-1);

			}

			LinkedHashMap<String, Object> params = new LinkedHashMap<String, Object>();
			params.put(Field.GROUP_ID, String.valueOf(groupId));
			// LamTV_Process search LIKE
			String keywordSearch = query.getKeyword();
			String keySearch = StringPool.BLANK;
			if (Validator.isNotNull(keywordSearch)) {
				keySearch = SpecialCharacterUtils.splitSpecial(keywordSearch);
			}
			params.put(Field.KEYWORD_SEARCH, keySearch);

			String status = query.getStatus();
			String substatus = query.getSubstatus();
			String agency = query.getAgency();
			String service = query.getService();
			String template = query.getTemplate();
			int year = query.getYear();
			int month = query.getMonth();
			String owner = query.getOwner();
			// If user is citizen then default owner true
			if (isCitizen) {
				owner = String.valueOf(true);
			}
			String follow = query.getFollow();
			String step = query.getStep();
			String submitting = query.getSubmitting();
			String top = query.getTop();
			String state = query.getState();
			String dossierIdNo = query.getDossierNo();
			String dossierNoSearch = StringPool.BLANK;
			if (Validator.isNotNull(dossierIdNo)) {
				dossierNoSearch = SpecialCharacterUtils.splitSpecial(dossierIdNo);
			}
			String soChungChi = query.getSoChungChi();
			String certNo = StringPool.BLANK;
			if (Validator.isNotNull(soChungChi)) {
				certNo = SpecialCharacterUtils.splitSpecial(soChungChi);
			}

			String fromReceiveDate = APIDateTimeUtils.convertNormalDateToLuceneDate(query.getFromReceiveDate());

			String toReceiveDate = APIDateTimeUtils.convertNormalDateToLuceneDate(query.getToReceiveDate());

			String fromCertDate = APIDateTimeUtils.convertNormalDateToLuceneDate(query.getTuNgayKyCc());

			String toCertDate = APIDateTimeUtils.convertNormalDateToLuceneDate(query.getDenNgayKyCc());

			String dossierIdCTN = query.getDossierIdCTN();
			String fromSubmitDate = APIDateTimeUtils.convertNormalDateToLuceneDate(query.getFromSubmitDate());
			String toSubmitDate = APIDateTimeUtils.convertNormalDateToLuceneDate(query.getToSubmitDate());
			//LamTV:Get info case abnormal
			Long statusRegNo = null;
			if (Validator.isNotNull(query.getStatusReg())) {
				statusRegNo = Long.valueOf(query.getStatusReg());
			}

			Long notStatusRegNo = null;
			if (Validator.isNotNull(query.getNotStatusReg())) {
				notStatusRegNo = Long.valueOf(query.getNotStatusReg());
			}
			
			String online = query.getOnline();
			
			params.put(DossierTerm.ONLINE, online);
			params.put(DossierTerm.STATUS, status);
			params.put(DossierTerm.SUBSTATUS, substatus);
			params.put(DossierTerm.AGENCY, agency);
			params.put(DossierTerm.SERVICE, service);
			params.put(DossierTerm.TEMPLATE, template);
			if (year != 0) {
				params.put(DossierTerm.YEAR, year);				
			}
			if (month != 0) {
				params.put(DossierTerm.MONTH, month);				
			}
			params.put(DossierTerm.STEP, step);
			params.put(DossierTerm.OWNER, owner);
			params.put(DossierTerm.SUBMITTING, submitting);
			params.put(DossierTerm.FOLLOW, follow);
			params.put(DossierTerm.TOP, top);
			params.put(DossierTerm.USER_ID, user.getUserId());
			params.put("secetKey", query.getSecetKey());
			params.put(DossierTerm.STATE, state);
			params.put(DossierTerm.DOSSIER_NO, dossierNoSearch);
			params.put(DossierTerm.CERT_NO, certNo);
			params.put(DossierTerm.FROM_RECEIVEDATE, fromReceiveDate);
			params.put(DossierTerm.TO_RECEIVEDATE, toReceiveDate);
			params.put(DossierTerm.FROM_CERT_DATE, fromCertDate);
			params.put(DossierTerm.TO_CERT_DATE, toCertDate);
			params.put(DossierTerm.DOSSIER_ID_CTN, dossierIdCTN);
			params.put(DossierTerm.FROM_SUBMIT_DATE, fromSubmitDate);
			params.put(DossierTerm.TO_SUBMIT_DATE, toSubmitDate);
			params.put(DossierTerm.STATUS_REG, statusRegNo);
			params.put(DossierTerm.NOT_STATUS_REG, notStatusRegNo);

			Sort[] sorts = null;
//			Sort[] sorts = new Sort[] { SortFactoryUtil.create(query.getSort() + "_sortable", Sort.STRING_TYPE,
//					GetterUtil.getBoolean(query.getOrder())) };
			if (!GetterUtil.getBoolean(query.getOrder())) {
				sorts = new Sort[] {
						SortFactoryUtil.create(DossierTerm.CREATE_DATE + "_sortable", Sort.STRING_TYPE, true) };
			} else {
				sorts = new Sort[] { SortFactoryUtil.create(query.getSort() + "_sortable", Sort.STRING_TYPE,
						GetterUtil.getBoolean(query.getOrder())) };
			}

			if (Validator.isNotNull(top)) {
				switch (top) {
				case "receive":
					sorts = new Sort[] { SortFactoryUtil.create(DossierTerm.RECEIVE_DATE_TIMESTAMP + "_sortable",
							Sort.LONG_TYPE, false) };
					break;
				case "overdue":
					sorts = new Sort[] { SortFactoryUtil.create(DossierTerm.DUE_DATE_TIMESTAMP + "_sortable",
							Sort.LONG_TYPE, false) };
					break;
				case "release":
					sorts = new Sort[] { SortFactoryUtil.create(DossierTerm.RELEASE_DATE_TIMESTAMP + "_sortable",
							Sort.LONG_TYPE, false) };
					break;
				case "cancelling":
					sorts = new Sort[] { SortFactoryUtil.create(DossierTerm.CANCELLING_DATE_TIMESTAMP + "_sortable",
							Sort.LONG_TYPE, false) };
					break;
				case "corecting":
					sorts = new Sort[] { SortFactoryUtil.create(DossierTerm.CORRECTING_DATE_TIMESTAMP + "_sortable",
							Sort.LONG_TYPE, false) };
					break;
				default:
					break;
				}

			}

			DossierResultsModel results = new DossierResultsModel();

			JSONObject jsonData = actions.getDossiers(user.getUserId(), company.getCompanyId(), groupId, params, sorts,
						query.getStart(), query.getEnd(), serviceContext);

			results.setTotal(jsonData.getInt("total"));

			results.getData().addAll(DossierUtils.mappingForGetList((List<Document>) jsonData.get("data")));

			return Response.status(200).entity(results).build();

		} catch (Exception e) {
			_log.info(e);
			return processException(e);
		}

	}

	@SuppressWarnings("unchecked")
	@Override
	public Response getDossiersTest(HttpServletRequest request, HttpHeaders header, Company company, Locale locale,
			User user, ServiceContext serviceContext, DossierSearchModel query) {

		long groupId = GetterUtil.getLong(header.getHeaderString("groupId"));
		BackendAuth auth = new BackendAuthImpl();
		DossierPermission dossierPermission = new DossierPermission();
		DossierActions actions = new DossierActionsImpl();

		try {

			// _log.info("1");
			if (!auth.isAuth(serviceContext)) {
				throw new UnauthenticationException();
			}

			// _log.info("2");
			boolean isCitizen = dossierPermission.isCitizen(user.getUserId());

			// _log.info("3");
			dossierPermission.hasGetDossiers(groupId, user.getUserId(), query.getSecetKey());

			// _log.info("31" + query.getEnd());

			if (query.getEnd() == 0) {

				query.setStart(-1);

				query.setEnd(-1);

			}

			LinkedHashMap<String, Object> params = new LinkedHashMap<String, Object>();

			params.put(Field.GROUP_ID, String.valueOf(groupId));
			// LamTV_Process search LIKE
			String keywordSearch = query.getKeyword();
			String keySearch = StringPool.BLANK;
			if (Validator.isNotNull(keywordSearch)) {
				keySearch = SpecialCharacterUtils.splitSpecial(keywordSearch);
			}
			params.put(Field.KEYWORD_SEARCH, keySearch);

			String status = query.getStatus();
			String substatus = query.getSubstatus();
			String agency = query.getAgency();
			String service = query.getService();
			String template = query.getTemplate();
			int year = query.getYear();
			int month = query.getMonth();
			String owner = query.getOwner();
			// If user is citizen then default owner true
			if (isCitizen) {
				owner = String.valueOf(true);
			}
			if (Boolean.valueOf(query.getSpecialKey())){
				_log.info("TESSSSST");
				owner = String.valueOf(false);
			}
			String follow = query.getFollow();
			String step = query.getStep();
			String submitting = query.getSubmitting();
			String top = query.getTop();
			String state = query.getState();
			String dossierIdNo = query.getDossierNo();
			String dossierNoSearch = StringPool.BLANK;
			if (Validator.isNotNull(dossierIdNo)) {
				dossierNoSearch = SpecialCharacterUtils.splitSpecial(dossierIdNo);
			}
			String soChungChi = query.getSoChungChi();
			String certNo = StringPool.BLANK;
			if (Validator.isNotNull(soChungChi)) {
				certNo = SpecialCharacterUtils.splitSpecial(soChungChi);
			}

			String fromReceiveDate = APIDateTimeUtils.convertNormalDateToLuceneDate(query.getFromReceiveDate());

			String toReceiveDate = APIDateTimeUtils.convertNormalDateToLuceneDate(query.getToReceiveDate());

			String fromCertDate = APIDateTimeUtils.convertNormalDateToLuceneDate(query.getTuNgayKyCc());

			String toCertDate = APIDateTimeUtils.convertNormalDateToLuceneDate(query.getDenNgayKyCc());

			String dossierIdCTN = query.getDossierIdCTN();
			String fromSubmitDate = APIDateTimeUtils.convertNormalDateToLuceneDate(query.getFromSubmitDate());
			String toSubmitDate = APIDateTimeUtils.convertNormalDateToLuceneDate(query.getToSubmitDate());

			params.put(DossierTerm.STATUS, status);
			params.put(DossierTerm.SUBSTATUS, substatus);
			params.put(DossierTerm.AGENCY, agency);
			params.put(DossierTerm.SERVICE, service);
			params.put(DossierTerm.TEMPLATE, template);
			params.put(DossierTerm.YEAR, year);
			params.put(DossierTerm.MONTH, month);
			params.put(DossierTerm.STEP, step);
			params.put(DossierTerm.OWNER, owner);
			params.put(DossierTerm.SUBMITTING, submitting);
			params.put(DossierTerm.FOLLOW, follow);
			params.put(DossierTerm.TOP, top);
			params.put(DossierTerm.USER_ID, user.getUserId());
			params.put("secetKey", query.getSecetKey());
			params.put(DossierTerm.STATE, state);
			params.put(DossierTerm.DOSSIER_NO, dossierNoSearch);
			params.put(DossierTerm.CERT_NO, certNo);
			params.put(DossierTerm.FROM_RECEIVEDATE, fromReceiveDate);
			params.put(DossierTerm.TO_RECEIVEDATE, toReceiveDate);
			params.put(DossierTerm.FROM_CERT_DATE, fromCertDate);
			params.put(DossierTerm.TO_CERT_DATE, toCertDate);
			params.put(DossierTerm.DOSSIER_ID_CTN, dossierIdCTN);
			params.put(DossierTerm.FROM_SUBMIT_DATE, fromSubmitDate);
			params.put(DossierTerm.TO_SUBMIT_DATE, toSubmitDate);

			// _log.info("4");
			Sort[] sorts = new Sort[] { SortFactoryUtil.create(query.getSort() + "_sortable", Sort.STRING_TYPE,
					GetterUtil.getBoolean(query.getOrder())) };

			if (Validator.isNotNull(top)) {
				switch (top) {
				case "receive":
					sorts = new Sort[] { SortFactoryUtil.create(DossierTerm.RECEIVE_DATE_TIMESTAMP + "_sortable",
							Sort.LONG_TYPE, false) };
					break;
				case "overdue":
					sorts = new Sort[] { SortFactoryUtil.create(DossierTerm.DUE_DATE_TIMESTAMP + "_sortable",
							Sort.LONG_TYPE, false) };
					break;
				case "release":
					sorts = new Sort[] { SortFactoryUtil.create(DossierTerm.RELEASE_DATE_TIMESTAMP + "_sortable",
							Sort.LONG_TYPE, false) };
					break;
				case "cancelling":
					sorts = new Sort[] { SortFactoryUtil.create(DossierTerm.CANCELLING_DATE_TIMESTAMP + "_sortable",
							Sort.LONG_TYPE, false) };
					break;
				case "corecting":
					sorts = new Sort[] { SortFactoryUtil.create(DossierTerm.CORRECTING_DATE_TIMESTAMP + "_sortable",
							Sort.LONG_TYPE, false) };
					break;
				default:
					break;
				}

			}
			// _log.info("5");
			JSONObject jsonData = actions.getDossiersTest(user.getUserId(), company.getCompanyId(), groupId, params,
					sorts, query.getStart(), query.getEnd(), serviceContext);

			// _log.info("6");
			DossierResultsModel results = new DossierResultsModel();

			if (jsonData != null && jsonData.length() > 0) {
				results.setTotal(jsonData.getInt("total"));
//				_log.info("7");
//				results.getData().addAll(DossierUtils.mappingForGetList((List<Document>) jsonData.get("data")));

				List<Document> docs = (List<Document>) jsonData.get("data");
				if (docs != null && docs.size() > 0) {
					if (Validator.isNotNull(status) || Validator.isNotNull(substatus)) {
						results.getData().addAll(DossierUtils.mappingForGetList(docs));
					} else {
						// Process paging
						if (query.getEnd() == -1) {
							results.getData().addAll(DossierUtils.mappingForGetList(docs));
						} else {
							_log.info("669999");
							results.getData().addAll(
									DossierUtils.mappingForGetListPaging(docs, query.getStart(), query.getEnd()));
						}
					}
				}
			} else {
				results.setTotal(0);
			}

			// _log.info("8");
			return Response.status(200).entity(results).build();

		} catch (Exception e) {
			_log.info(e);
			return processException(e);
		}

	}

	//LamTV: Process dossierTodo
	@SuppressWarnings("unchecked")
	@Override
	public Response getDossierProcessList(HttpServletRequest request, HttpHeaders header, Company company, Locale locale,
			User user, ServiceContext serviceContext, DossierSearchModel query) {

		long groupId = GetterUtil.getLong(header.getHeaderString("groupId"));
		BackendAuth auth = new BackendAuthImpl();
		DossierPermission dossierPermission = new DossierPermission();
		DossierActions actions = new DossierActionsImpl();

		try {

			if (!auth.isAuth(serviceContext)) {
				throw new UnauthenticationException();
			}
			boolean isCitizen = dossierPermission.isCitizen(user.getUserId());
			dossierPermission.hasGetDossiers(groupId, user.getUserId(), query.getSecetKey());

			if (query.getEnd() == 0) {
				query.setStart(-1);
				query.setEnd(-1);
			}

			LinkedHashMap<String, Object> params = new LinkedHashMap<String, Object>();
			params.put(Field.GROUP_ID, String.valueOf(groupId));
			// LamTV_Process search LIKE
			String keywordSearch = query.getKeyword();
			String keySearch = StringPool.BLANK;
			if (Validator.isNotNull(keywordSearch)) {
				keySearch = SpecialCharacterUtils.splitSpecial(keywordSearch);
			}
			params.put(Field.KEYWORD_SEARCH, keySearch);

			String status = query.getStatus();
			String substatus = query.getSubstatus();
			String step = query.getStep();
			_log.info("step: "+step);
			StringBuilder strStatusStep = null;
			StringBuilder strSubStatusStep = null;
			if (Validator.isNotNull(step)) {
				strStatusStep = new StringBuilder();
				strSubStatusStep = new StringBuilder();
				String[] stepArr = step.split(StringPool.COMMA);
				if (stepArr != null && stepArr.length > 0) {
					String statusStep = StringPool.BLANK;
					String subStatusStep = StringPool.BLANK;
					for (int i = 0; i < stepArr.length; i++) {
						StepConfig stepConfig = StepConfigLocalServiceUtil.getByCode(groupId, stepArr[i]);
						if (stepConfig != null) {
							statusStep = stepConfig.getDossierStatus();
							subStatusStep = stepConfig.getDossierSubStatus();
							if (i == 0) {
								strStatusStep.append(statusStep);
								if (Validator.isNotNull(subStatusStep)) {
									strSubStatusStep.append(subStatusStep);
								} else {
									strSubStatusStep.append("empty");
								}
							} else {
								strStatusStep.append(StringPool.COMMA);
								strStatusStep.append(statusStep);
								strSubStatusStep.append(StringPool.COMMA);
								if (Validator.isNotNull(subStatusStep)) {
									strSubStatusStep.append(subStatusStep);
								} else {
									strSubStatusStep.append("empty");
								}
							}
						}
					}
				}
 			}
//			_log.info("strStatusStep: "+strStatusStep.toString());
//			_log.info("strSubStatusStep: "+strSubStatusStep.toString());
//			if (stepList != null && stepList.size() > 0) {
//				_log.info("length: "+stepList.size());
//				for (StepConfig step: stepList) {
//					params.put(DossierTerm.STATUS, step.getDossierStatus());
//					params.put(DossierTerm.SUBSTATUS, step.getDossierSubStatus());
//					_log.info("START");
//					long count = actions.countTodoTest(user.getUserId(), company.getCompanyId(), groupId, params,
//							null, serviceContext);
//					_log.info("START");
//					JSONObject statistic = JSONFactoryUtil.createJSONObject();
//					statistic.put("stepCode", step.getStepCode());
//					statistic.put("stepName", step.getStepName());
//					statistic.put("dossierStatus", step.getDossierStatus());
//					statistic.put("dossierSubStatus", step.getDossierSubStatus());
//					statistic.put("totalCount", count);
//					total += count;
//					statistics.put(statistic);
//				}
//			}
			String agency = query.getAgency();
			String service = query.getService();
			String template = query.getTemplate();
			int year = query.getYear();
			int month = query.getMonth();
			String owner = query.getOwner();
			// If user is citizen then default owner true
			if (isCitizen) {
				owner = String.valueOf(true);
			}
			if (Boolean.valueOf(query.getSpecialKey())){
				owner = String.valueOf(false);
			}
			String follow = query.getFollow();
			String submitting = query.getSubmitting();
			String top = query.getTop();
			String state = query.getState();
			String dossierIdNo = query.getDossierNo();
			String dossierNoSearch = StringPool.BLANK;
			if (Validator.isNotNull(dossierIdNo)) {
				dossierNoSearch = SpecialCharacterUtils.splitSpecial(dossierIdNo);
			}
			String soChungChi = query.getSoChungChi();
			String certNo = StringPool.BLANK;
			if (Validator.isNotNull(soChungChi)) {
				certNo = SpecialCharacterUtils.splitSpecial(soChungChi);
			}

			String fromReceiveDate = APIDateTimeUtils.convertNormalDateToLuceneDate(query.getFromReceiveDate());
			String toReceiveDate = APIDateTimeUtils.convertNormalDateToLuceneDate(query.getToReceiveDate());
			String fromCertDate = APIDateTimeUtils.convertNormalDateToLuceneDate(query.getTuNgayKyCc());
			String toCertDate = APIDateTimeUtils.convertNormalDateToLuceneDate(query.getDenNgayKyCc());
			String fromSubmitDate = APIDateTimeUtils.convertNormalDateToLuceneDate(query.getFromSubmitDate());
			String toSubmitDate = APIDateTimeUtils.convertNormalDateToLuceneDate(query.getToSubmitDate());
			String dossierIdCTN = query.getDossierIdCTN();

			params.put(DossierTerm.STATUS, status);
			params.put(DossierTerm.SUBSTATUS, substatus);
			params.put(DossierTerm.AGENCY, agency);
			params.put(DossierTerm.SERVICE, service);
			params.put(DossierTerm.TEMPLATE, template);
			params.put(DossierTerm.YEAR, year);
			params.put(DossierTerm.MONTH, month);
//			params.put(DossierTerm.STEP, step);
			params.put(DossierTerm.OWNER, owner);
			params.put(DossierTerm.SUBMITTING, submitting);
			params.put(DossierTerm.FOLLOW, follow);
			params.put(DossierTerm.TOP, top);
			params.put(DossierTerm.USER_ID, user.getUserId());
			params.put(DossierTerm.SECET_KEY, query.getSecetKey());
			params.put(DossierTerm.STATE, state);
			params.put(DossierTerm.DOSSIER_NO, dossierNoSearch);
			params.put(DossierTerm.CERT_NO, certNo);
			params.put(DossierTerm.FROM_RECEIVEDATE, fromReceiveDate);
			params.put(DossierTerm.TO_RECEIVEDATE, toReceiveDate);
			params.put(DossierTerm.FROM_CERT_DATE, fromCertDate);
			params.put(DossierTerm.TO_CERT_DATE, toCertDate);
			params.put(DossierTerm.DOSSIER_ID_CTN, dossierIdCTN);
			params.put(DossierTerm.FROM_SUBMIT_DATE, fromSubmitDate);
			params.put(DossierTerm.TO_SUBMIT_DATE, toSubmitDate);
			//Process follow StepCode
			params.put(DossierTerm.DOSSIER_STATUS_STEP, strStatusStep.toString());
			params.put(DossierTerm.DOSSIER_SUBSTATUS_STEP, strSubStatusStep.toString());
			//TODO
			String permission = user.getUserId() + ":write";
			params.put(DossierTerm.MAPPING_PERMISSION, permission);
			// Add param original
//			params.put(DossierTerm.ORIGINALLITY, ConstantUtils.ORIGINAL_TODO);

			Sort[] sorts = new Sort[] { SortFactoryUtil.create(query.getSort() + "_sortable", Sort.STRING_TYPE,
					GetterUtil.getBoolean(query.getOrder())) };

			if (Validator.isNotNull(top)) {
				switch (top) {
				case "receive":
					sorts = new Sort[] { SortFactoryUtil.create(DossierTerm.RECEIVE_DATE_TIMESTAMP + "_sortable",
							Sort.LONG_TYPE, false) };
					break;
				case "overdue":
					sorts = new Sort[] { SortFactoryUtil.create(DossierTerm.DUE_DATE_TIMESTAMP + "_sortable",
							Sort.LONG_TYPE, false) };
					break;
				case "release":
					sorts = new Sort[] { SortFactoryUtil.create(DossierTerm.RELEASE_DATE_TIMESTAMP + "_sortable",
							Sort.LONG_TYPE, false) };
					break;
				case "cancelling":
					sorts = new Sort[] { SortFactoryUtil.create(DossierTerm.CANCELLING_DATE_TIMESTAMP + "_sortable",
							Sort.LONG_TYPE, false) };
					break;
				case "corecting":
					sorts = new Sort[] { SortFactoryUtil.create(DossierTerm.CORRECTING_DATE_TIMESTAMP + "_sortable",
							Sort.LONG_TYPE, false) };
					break;
				default:
					break;
				}

			}

			JSONObject jsonData = actions.getDossierProcessList(user.getUserId(), company.getCompanyId(), groupId, params,
					sorts, query.getStart(), query.getEnd(), serviceContext);

			// _log.info("6");
			DossierResultsModel results = new DossierResultsModel();

			if (jsonData != null && jsonData.getInt("total") > 0) {
				results.setTotal(jsonData.getInt("total"));
				results.getData().addAll(DossierUtils.mappingForGetList((List<Document>) jsonData.get("data")));
//				_log.info("7");
//				results.getData().addAll(DossierUtils.mappingForGetList((List<Document>) jsonData.get("data")));

//				List<Document> docs = (List<Document>) jsonData.get("data");
//				if (docs != null && docs.size() > 0) {
//					if (Validator.isNotNull(status) || Validator.isNotNull(substatus)) {
//						results.getData().addAll(DossierUtils.mappingForGetList(docs));
//					} else {
//						// Process paging
//						if (query.getEnd() == -1) {
//							results.getData().addAll(DossierUtils.mappingForGetList(docs));
//						} else {
//							_log.info("669999");
//							results.getData().addAll(
//									DossierUtils.mappingForGetListPaging(docs, query.getStart(), query.getEnd()));
//						}
//					}
//				}
			} else {
				results.setTotal(0);
			}

			// _log.info("8");
			return Response.status(200).entity(results).build();

		} catch (Exception e) {
			_log.info(e);
			return processException(e);
		}

	}

	@Override
	public Response addDossier(HttpServletRequest request, HttpHeaders header, Company company, Locale locale,
			User user, ServiceContext serviceContext, DossierInputModel input) {

		long groupId = GetterUtil.getLong(header.getHeaderString("groupId"));
		BackendAuth auth = new BackendAuthImpl();
		DossierPermission dossierPermission = new DossierPermission();

		DossierActions actions = new DossierActionsImpl();

		try {

			if (!auth.isAuth(serviceContext)) {
				throw new UnauthenticationException();
			}

			// if (!auth.hasResource(serviceContext,
			// DossierTemplate.class.getName(), ActionKeys.ADD_ENTRY)) {
			// throw new UnauthorizationException();
			// }

			dossierPermission.hasCreateDossier(groupId, user.getUserId(), input.getServiceCode(),
					input.getGovAgencyCode(), input.getDossierTemplateNo());

			int counter = DossierNumberGenerator.counterDossier(user.getUserId(), groupId);
			String referenceUid = input.getReferenceUid();

			ProcessOption option = getProcessOption(input.getServiceCode(), input.getGovAgencyCode(),
					input.getDossierTemplateNo(), groupId);

			// Create dossierNote
			ServiceProcess process = null;
			boolean online = GetterUtil.getBoolean(input.getOnline());
			int originality = GetterUtil.getInteger(input.getOriginality());
			
			if (option != null) {
				long serviceProcessId = option.getServiceProcessId();
				process = ServiceProcessLocalServiceUtil.getServiceProcess(serviceProcessId);
//				if (process.getServerNo().trim().length() != 0) {
//					online = false;
//				}
			}

			if (process == null) {
				throw new NotFoundException("Cant find process");
			}

			if (Validator.isNull(referenceUid) || referenceUid.trim().length() == 0)
				referenceUid = DossierNumberGenerator.generateReferenceUID(groupId);

			String serviceName = getServiceName(input.getServiceCode(), groupId);

			String govAgencyName = getDictItemName(groupId, GOVERNMENT_AGENCY, input.getGovAgencyCode());

			String cityName = getDictItemName(groupId, ADMINISTRATIVE_REGION, input.getCityCode());
			String districtName = getDictItemName(groupId, ADMINISTRATIVE_REGION, input.getDistrictCode());
			String wardName = getDictItemName(groupId, ADMINISTRATIVE_REGION, input.getWardCode());

			String password = StringPool.BLANK;
			if (Validator.isNotNull(process.getGeneratePassword()) && process.getGeneratePassword()) {
				password = DossierNumberGenerator.generatePassword(DEFAULT_PATTERN_PASSWORD, LENGHT_DOSSIER_PASSWORD);
			}

			List<Dossier> oldDossiers = DossierLocalServiceUtil.getByNotO_DS_SC_GC(groupId, 
					0, StringPool.BLANK, input.getServiceCode(), input.getGovAgencyCode());
			Dossier dossier = null;
			
			if (originality == DossierTerm.ORIGINALITY_DVCTT
					|| originality == DossierTerm.ORIGINALITY_LIENTHONG) {
				online = true;
			}
			
			if (oldDossiers.size() > 0 && oldDossiers.get(0).getOriginality() == Integer.valueOf(input.getOriginality())) {
				dossier = oldDossiers.get(0);
				dossier.setApplicantName(input.getApplicantName());
				dossier.setApplicantNote(input.getApplicantNote());
				dossier.setApplicantIdNo(input.getApplicantIdNo());
				dossier.setAddress(input.getAddress());
				dossier.setContactEmail(input.getContactEmail());
				dossier.setContactName(input.getContactName());
				dossier.setContactTelNo(input.getContactTelNo());	
				dossier.setDossierNo(input.getDossierNo());
				dossier.setSubmitDate(new Date());
				dossier.setOnline(online);
			}
			else {
				dossier = actions.initDossier(groupId, 0l, referenceUid, counter, input.getServiceCode(), serviceName,
						input.getGovAgencyCode(), govAgencyName, input.getApplicantName(), input.getApplicantIdType(),
						input.getApplicantIdNo(), input.getApplicantIdDate(), input.getAddress(), input.getCityCode(),
						cityName, input.getDistrictCode(), districtName, input.getWardCode(), wardName,
						input.getContactName(), input.getContactTelNo(), input.getContactEmail(),
						input.getDossierTemplateNo(), password, 0, StringPool.BLANK, StringPool.BLANK, StringPool.BLANK,
						StringPool.BLANK, online, process.getDirectNotification(), input.getApplicantNote(),
						Integer.valueOf(input.getOriginality()), serviceContext);
			}

			if (originality != DossierTerm.ORIGINALITY_LIENTHONG) {
				Applicant applicant = ApplicantLocalServiceUtil.fetchByMappingID(serviceContext.getUserId());
				if (applicant != null) {
					dossier = DossierLocalServiceUtil.updateApplicantInfo(
							dossier.getDossierId(), 
							applicant.getApplicantIdDate(),
							applicant.getApplicantIdNo(),
							applicant.getApplicantIdType(),
							applicant.getApplicantName(),
							applicant.getAddress(),
							applicant.getCityCode(),
							applicant.getCityName(),
							applicant.getDistrictCode(),
							applicant.getDistrictName(),
							applicant.getWardCode(),
							applicant.getWardName(),
							applicant.getContactEmail(),
							applicant.getContactTelNo());
				}
			}
			if (Validator.isNull(dossier)) {
				throw new NotFoundException("Cant add DOSSIER");
			}

			//Create dossier user
			List<DossierUser> lstDus = DossierUserLocalServiceUtil.findByDID(dossier.getDossierId());
			if (lstDus.size() == 0) {
				DossierUserActions duActions = new DossierUserActionsImpl();
				duActions.initDossierUser(groupId, dossier);				
			}
			
			if (originality == DossierTerm.ORIGINALITY_MOTCUA
					|| originality == DossierTerm.ORIGINALITY_LIENTHONG) {
				//Update submit date
				Date now = new Date();
				dossier.setSubmitDate(now);
				int durationCount = 0;
				int durationUnit = 0;
				if (process != null ) {
					durationCount = process.getDurationCount();
					durationUnit = process.getDurationUnit();
					int durationDays = 0;

					if (durationUnit == 0) {
						durationDays = durationCount;
					} else {
						durationDays = Math.round(durationCount / 8);
					}
					Date dueDate = null;
					if (durationDays > 0) {
						dueDate = DossierOverDueUtils.calculateEndDate(now, durationDays);
					}

					dossier.setDueDate(dueDate);
					dossier.setReceiveDate(now);
					dossier.setDurationCount(durationCount);
					dossier.setDurationUnit(durationUnit);
				}
				
				if (Validator.isNotNull(input.getDossierNo())) {
					dossier.setDossierNo(input.getDossierNo());
				}
			}
			if (originality == DossierTerm.ORIGINALITY_DVCTT) {
				long userId = serviceContext.getUserId();
				DossierUserLocalServiceUtil.addDossierUser(groupId, dossier.getDossierId(), userId, 1, true);
			}
			
			DossierLocalServiceUtil.updateDossier(dossier);

			//Add to dossier user based on service process role
			List<ServiceProcessRole> lstProcessRoles = ServiceProcessRoleLocalServiceUtil.findByS_P_ID(process.getServiceProcessId());
			DossierUtils.createDossierUsers(groupId, dossier, process, lstProcessRoles);
			
			DossierDetailModel result = DossierUtils.mappingForGetDetail(dossier, user.getUserId());

			return Response.status(200).entity(result).build();

		} catch (Exception e) {
			_log.info(e);
			return processException(e);
		}

	}

	@Override
	public Response getDetailDossier(HttpServletRequest request, HttpHeaders header, Company company, Locale locale,
			User user, ServiceContext serviceContext, String id) {

		long groupId = GetterUtil.getLong(header.getHeaderString("groupId"));
		String password = GetterUtil.getString(header.getHeaderString("password"));
		DossierPermission dossierPermission = new DossierPermission();
		BackendAuth auth = new BackendAuthImpl();

		try {

			if (Validator.isNotNull(password)) {

				Dossier dossier = DossierUtils.getDossier(id, groupId);

				dossierPermission.checkPassword(dossier, password);

				DossierDetailModel result = DossierUtils.mappingForGetDetail(dossier, user.getUserId());

				return Response.status(200).entity(result).build();

			} else {
				if (!auth.isAuth(serviceContext)) {
					throw new UnauthenticationException();
				}

				Dossier dossier = DossierUtils.getDossier(id, groupId);

//				ProcessOption option = getProcessOption(dossier.getServiceCode(), dossier.getGovAgencyCode(),
//						dossier.getDossierTemplateNo(), groupId);
				// dossierPermission.hasGetDetailDossier(groupId,
				// user.getUserId(), dossier, option.getServiceProcessId());

				DossierDetailModel result = DossierUtils.mappingForGetDetail(dossier, user.getUserId());

				return Response.status(200).entity(result).build();

			}

		} catch (Exception e) {
//			_log.info(e);
			return processException(e);
		}

	}

	@Override
	public Response updateDossier(HttpServletRequest request, HttpHeaders header, Company company, Locale locale,
			User user, ServiceContext serviceContext, long id, DossierInputModel input) {
		long groupId = GetterUtil.getLong(header.getHeaderString("groupId"));
		BackendAuth auth = new BackendAuthImpl();

		DossierActions actions = new DossierActionsImpl();
		DossierPermission dossierPermission = new DossierPermission();

		try {
			if (!auth.isAuth(serviceContext)) {
				throw new UnauthenticationException();
			}

			// if (!auth.hasResource(serviceContext,
			// DossierTemplate.class.getName(), ActionKeys.ADD_ENTRY)) {
			// throw new UnauthorizationException();
			// }

			dossierPermission.hasCreateDossier(groupId, user.getUserId(), input.getServiceCode(),
					input.getGovAgencyCode(), input.getDossierTemplateNo());

//			int counter = 0;
//			String referenceUid = StringPool.BLANK;
			//
			// ProcessOption option = getProcessOption(input.getServiceCode(),
			// input.getGovAgencyCode(),
			// input.getDossierTemplateNo(), groupId);
			//
			// ServiceProcess process =
			// ServiceProcessLocalServiceUtil.getServiceProcess(option.getServiceProcessId());

//			if (referenceUid.trim().length() == 0)
//				referenceUid = DossierNumberGenerator.generateReferenceUID(groupId);

			// String serviceName = getServiceName(input.getServiceCode(),
			// groupId);

			// String govAgencyName = getDictItemName(groupId,
			// GOVERNMENT_AGENCY, input.getGovAgencyCode());

			String cityName = StringPool.BLANK;
			String districtName = StringPool.BLANK;
			String wardName = StringPool.BLANK;
			String postalCityName = StringPool.BLANK;

			if (Validator.isNotNull(input.getCityCode()))
				cityName = getDictItemName(groupId, ADMINISTRATIVE_REGION, input.getCityCode());
			if (Validator.isNotNull(input.getDistrictCode()))
				districtName = getDictItemName(groupId, ADMINISTRATIVE_REGION, input.getDistrictCode());
			if (Validator.isNotNull(input.getWardCode()))
				wardName = getDictItemName(groupId, ADMINISTRATIVE_REGION, input.getWardCode());

			if (Validator.isNotNull(input.getPostalCityCode())) {
				postalCityName = getDictItemName(groupId, ADMINISTRATIVE_REGION, input.getPostalCityCode());
			}

//			boolean online = true;
//
//			String password = StringPool.BLANK;

//			Dossier dossier = actions.initDossier(groupId, id, referenceUid, counter, input.getServiceCode(),
//					StringPool.BLANK, StringPool.BLANK, StringPool.BLANK, input.getApplicantName(),
//					input.getApplicantIdType(), input.getApplicantIdNo(), input.getApplicantIdDate(),
//					input.getAddress(), input.getCityCode(), cityName, input.getDistrictCode(), districtName,
//					input.getWardCode(), wardName, input.getContactName(), input.getContactTelNo(),
//					input.getContactEmail(), input.getDossierTemplateNo(), password, input.getViaPostal(),
//					input.getPostalAddress(), input.getPostalCityCode(), postalCityName, input.getPostalTelNo(), online,
//					true, input.getApplicantNote(), Integer.valueOf(input.getOriginality()), serviceContext);
			//
			Dossier dossier = actions.initUpdateDossier(groupId, id, input.getApplicantName(),
					input.getApplicantIdType(), input.getApplicantIdNo(), input.getApplicantIdDate(),
					input.getAddress(), input.getCityCode(), cityName, input.getDistrictCode(), districtName,
					input.getWardCode(), wardName, input.getContactName(), input.getContactTelNo(),
					input.getContactEmail(), input.getDossierTemplateNo(), input.getViaPostal(),
					input.getPostalAddress(), input.getPostalCityCode(), postalCityName, input.getPostalTelNo(),
					input.getApplicantNote(), serviceContext);

			DossierDetailModel result = DossierUtils.mappingForGetDetail(dossier, user.getUserId());

			return Response.status(200).entity(result).build();

		} catch (Exception e) {
			_log.info(e);
			return processException(e);
		}
	}

	@Override
	public Response removeDossier(HttpServletRequest request, HttpHeaders header, Company company, Locale locale,
			User user, ServiceContext serviceContext, String id) {
		long groupId = GetterUtil.getLong(header.getHeaderString("groupId"));
		BackendAuth auth = new BackendAuthImpl();
		DossierActions actions = new DossierActionsImpl();
//		DossierPermission dossierPermission = new DossierPermission();

		try {
			if (!auth.isAuth(serviceContext)) {
				throw new UnauthenticationException();
			}

			Dossier dossier = DossierUtils.getDossier(id, groupId);

			// dossierPermission.allowSubmitting(user.getUserId(),
			// dossier.getDossierId());

			Dossier removeDossier = actions.removeDossier(groupId, dossier.getDossierId(), dossier.getReferenceUid());

			DossierDetailModel result = DossierUtils.mappingForGetDetail(removeDossier, user.getUserId());

			return Response.status(200).entity(result).build();

		} catch (Exception e) {
			_log.info(e);
			return processException(e);
		}
	}

	@Override
	public Response cancellingDossier(HttpServletRequest request, HttpHeaders header, Company company, Locale locale,
			User user, ServiceContext serviceContext, String id) {

		long groupId = GetterUtil.getLong(header.getHeaderString("groupId"));
		BackendAuth auth = new BackendAuthImpl();
		DossierActions actions = new DossierActionsImpl();

//		DossierPermission dossierPermission = new DossierPermission();

		try {
			if (!auth.isAuth(serviceContext)) {
				throw new UnauthenticationException();
			}

			Dossier dossier = DossierUtils.getDossier(id, groupId);

			// dossierPermission.allowSubmitting(user.getUserId(),
			// dossier.getDossierId());

			Dossier cancellingDossier = actions.cancelDossier(groupId, dossier.getDossierId(),
					dossier.getReferenceUid(), serviceContext);

			DossierDetailModel result = DossierUtils.mappingForGetDetail(cancellingDossier, user.getUserId());

			return Response.status(200).entity(result).build();

		} catch (Exception e) {
			_log.info(e);
			return processException(e);
		}
	}

	@Override
	public Response correctingDossier(HttpServletRequest request, HttpHeaders header, Company company, Locale locale,
			User user, ServiceContext serviceContext, String id) {

		long groupId = GetterUtil.getLong(header.getHeaderString("groupId"));
		BackendAuth auth = new BackendAuthImpl();
		DossierActions actions = new DossierActionsImpl();

//		DossierPermission dossierPermission = new DossierPermission();

		try {
			if (!auth.isAuth(serviceContext)) {
				throw new UnauthenticationException();
			}

			Dossier dossier = DossierUtils.getDossier(id, groupId);

			// dossierPermission.allowSubmitting(user.getUserId(),
			// dossier.getDossierId());

			Dossier correctingDossier = actions.correctDossier(groupId, dossier.getDossierId(),
					dossier.getReferenceUid(), serviceContext);

			DossierDetailModel result = DossierUtils.mappingForGetDetail(correctingDossier, user.getUserId());

			return Response.status(200).entity(result).build();

		} catch (Exception e) {
			_log.info(e);
			return processException(e);
		}

	}

	@Override
	public Response submittingDossier(HttpServletRequest request, HttpHeaders header, Company company, Locale locale,
			User user, ServiceContext serviceContext, String id) {

		long groupId = GetterUtil.getLong(header.getHeaderString("groupId"));
		BackendAuth auth = new BackendAuthImpl();

		DossierActions actions = new DossierActionsImpl();

		DossierPermission dossierPermission = new DossierPermission();

		try {
			if (!auth.isAuth(serviceContext)) {
				throw new UnauthenticationException();
			}

			Dossier dossier = DossierUtils.getDossier(id, groupId);

			dossierPermission.allowSubmitting(user.getUserId(), dossier.getDossierId());

			Dossier submittedDossier = actions.submitDossier(groupId, dossier.getDossierId(), dossier.getReferenceUid(),
					serviceContext);

			DossierDetailModel result = DossierUtils.mappingForGetDetail(submittedDossier, user.getUserId());

			return Response.status(200).entity(result).build();

		} catch (Exception e) {
			_log.info(e);
			return processException(e);
		}

	}

	@Override
	public Response resetDossier(HttpServletRequest request, HttpHeaders header, Company company, Locale locale,
			User user, ServiceContext serviceContext, String id) {

		// RESET submitting
		long groupId = GetterUtil.getLong(header.getHeaderString("groupId"));
//		DossierPermission dossierPermission = new DossierPermission();
//		BackendAuth auth = new BackendAuthImpl();
		DossierActions actions = new DossierActionsImpl();
		try {
			// isSyncAction equal 1 that is the action was processed by
			// DossierPushScheduler
			Dossier dossier = DossierUtils.getDossier(id, groupId);

			Dossier dossierResetted = actions.resetDossier(groupId, dossier.getDossierId(), dossier.getReferenceUid(),
					serviceContext);

			return Response.status(200).entity(DossierUtils.mappingForGetDetail(dossierResetted, user.getUserId()))
					.build();

		} catch (Exception e) {
			_log.info(e);
			return processException(e);
		}

	}

	//LamTV: Process DoAction
	@Override
	public Response doAction(HttpServletRequest request, HttpHeaders header, Company company, Locale locale, User user,
			ServiceContext serviceContext, String id, DoActionModel input) {

		long groupId = GetterUtil.getLong(header.getHeaderString("groupId"));
		long userId = user.getUserId();
//		DossierPermission dossierPermission = new DossierPermission();
		BackendAuth auth = new BackendAuthImpl();
		DossierActions actions = new DossierActionsImpl();
		DossierAction dossierResult = null;
		
		try {
			if (!auth.isAuth(serviceContext)) {
				throw new UnauthenticationException();
			}

			Dossier dossier = DossierUtils.getDossier(id, groupId);

			_log.info("LamTV-input: "+JSONFactoryUtil.looseSerialize(input));
			_log.info("LamTV-Call in groupId: "+groupId + "|dossierId: "+id +" |userId: "+userId);

			if (dossier != null) {
				_log.info("Dossier: " + dossier + ", action code: " + input.getActionCode());
				String actionCode = input.getActionCode();
				if (Validator.isNotNull(actionCode)) {
					ActionConfig actConfig = ActionConfigLocalServiceUtil.getByCode(groupId, actionCode);
					_log.info("Action config: " + actConfig);
					String serviceCode = dossier.getServiceCode();
					String govAgencyCode = dossier.getGovAgencyCode();
					String dossierTempNo = dossier.getDossierTemplateNo();
					if (actConfig != null) {
						boolean insideProcess = actConfig.getInsideProcess();
						if (insideProcess) {
							
							ProcessOption option = DossierUtils.getProcessOption(serviceCode, govAgencyCode,
									dossierTempNo, groupId);
							_log.info("Option: " + option);
							if (option != null) {
								long serviceProcessId = option.getServiceProcessId();
								ProcessAction proAction = DossierUtils.getProcessAction(groupId, dossier, actionCode,
										serviceProcessId);
								_log.info("Process action: " + proAction);
								if (proAction != null) {
									dossierResult = actions.doAction(groupId, userId, dossier, option, proAction, actionCode,
											input.getActionUser(), input.getActionNote(), input.getPayload(),
											input.getAssignUsers(), input.getPayment(), actConfig.getSyncType(), serviceContext);
								} else {
									//TODO: Error
								}
							}
						} else {
							dossierResult = actions.doAction(groupId, userId, dossier, null, null,
									actionCode, input.getActionUser(),
									input.getActionNote(), input.getPayload(), input.getAssignUsers(),
									input.getPayment(),
									actConfig.getSyncType(),
									serviceContext);
						}
					} else {
						ProcessOption option = DossierUtils.getProcessOption(serviceCode, govAgencyCode,
								dossierTempNo, groupId);
						if (option != null) {
							long serviceProcessId = option.getServiceProcessId();
							ProcessAction proAction = DossierUtils.getProcessAction(groupId, dossier, actionCode,
									serviceProcessId);
							if (proAction != null) {
								dossierResult = actions.doAction(groupId, userId, dossier, option, proAction, actionCode,
										input.getActionUser(), input.getActionNote(), input.getPayload(),
										input.getAssignUsers(), 
										input.getPayment(),
										0, serviceContext);
							} else {
								//TODO: Error
							}
						
					}
				}
			}
			}
			
//			DossierAction dossierAction = actions.doAction(groupId, dossier, option, proAction,
//					actionCode, input.getActionUser(),
//					input.getActionNote(), input.getPayload(), input.getAssignUsers(), serviceContext);
			
			
//			DossierAction dossierAction = actions.doAction(groupId, dossier.getDossierId(),
//					dossier.getReferenceUid(), input.getActionCode(), 0l, input.getActionUser(),
//					input.getActionNote(), input.getAssignUserId(), 0l, subUsers, serviceContext);

//				return Response.status(200).entity(JSONFactoryUtil.looseSerializeDeep(dossierAction)).build();
			if (dossierResult != null) {
				return Response.status(200).entity(JSONFactoryUtil.looseSerializeDeep(dossierResult)).build();				
			}
			else {
				return Response.status(405).entity("{ \"error\": \"Lỗi xảy ra không thể thực hiện hành động!\" }").build();
			}
//				ProcessOption option = getProcessOption(dossier.getServiceCode(), dossier.getGovAgencyCode(),
//						dossier.getDossierTemplateNo(), groupId);

//				ProcessAction action = getProcessAction(dossier,
//						input.getActionCode(), option.getServiceProcessId());

//				 dossierPermission.hasPermitDoAction(groupId,
//				 user.getUserId(), dossier, option.getServiceProcessId(),
//				 action);

//				DossierAction dossierAction = actions.doAction(groupId, dossier.getDossierId(),
//						dossier.getReferenceUid(), input.getActionCode(), action.getProcessActionId(),
//						input.getActionUser(), input.getActionNote(), input.getAssignUserId(), user.getUserId(),
//						subUsers, serviceContext);


		} catch (Exception e) {
			_log.info(e);
			return processException(e);
		}
	}

	protected ProcessOption getProcessOption(String serviceInfoCode, String govAgencyCode, String dossierTemplateNo,
			long groupId) throws PortalException {

		ServiceConfig config = ServiceConfigLocalServiceUtil.getBySICodeAndGAC(groupId, serviceInfoCode, govAgencyCode);

		return ProcessOptionLocalServiceUtil.getByDTPLNoAndServiceCF(groupId, dossierTemplateNo,
				config.getServiceConfigId());
	}

	protected String getDictItemName(long groupId, String collectionCode, String itemCode) {

		DictCollection dc = DictCollectionLocalServiceUtil.fetchByF_dictCollectionCode(collectionCode, groupId);

		if (Validator.isNotNull(dc)) {
			DictItem it = DictItemLocalServiceUtil.fetchByF_dictItemCode(itemCode, dc.getPrimaryKey(), groupId);
			if(Validator.isNotNull(it)){
				return it.getItemName();
			}else{
				return StringPool.BLANK;
			}
		} else {
			return StringPool.BLANK;
		}

	}

	protected String getServiceName(String serviceCode, long groupId) throws PortalException {

		try {
			ServiceInfo service = ServiceInfoLocalServiceUtil.getByCode(groupId, serviceCode);

			return service.getServiceName();
		} catch (Exception e) {
			throw new NotFoundException("NotFoundExceptionWithServiceCode");
		}

	}

	protected String getDossierTemplateName(String dossierTemplateCode, long groupId) throws PortalException {
		try {
			DossierTemplate template = DossierTemplateLocalServiceUtil.getByTemplateNo(groupId, dossierTemplateCode);

			return template.getTemplateName();
		} catch (Exception e) {
			throw new NotFoundException("NotFoundExceptionWithTemplateCode");
		}

	}

	public static final String GOVERNMENT_AGENCY = "GOVERNMENT_AGENCY";
	public static final String ADMINISTRATIVE_REGION = "ADMINISTRATIVE_REGION";
	public static final int LENGHT_DOSSIER_PASSWORD = 15;
	public static final String DEFAULT_PATTERN_PASSWORD = "0123456789khoa";

	@Override
	public Response getContactsDossier(HttpHeaders header, ServiceContext serviceContext, Long dossierId,
			String referenceUid) {
		DossierActions action = new DossierActionsImpl();
		BackendAuth auth = new BackendAuthImpl();
		try {

			if (!auth.isAuth(serviceContext)) {
				throw new UnauthenticationException();
			}

			long groupId = GetterUtil.getLong(header.getHeaderString("groupId"));
			JSONObject result = action.getContacts(groupId, dossierId, referenceUid);
			return Response.status(200).entity(result).build();
		} catch (PortalException e) {
			_log.info(e);
			return processException(e);
		}
	}

	@Override
	public Response cloneDossier(HttpServletRequest request, HttpHeaders header, Company company, Locale locale,
			User user, ServiceContext serviceContext, long dossierId, String referenceUid) {
		long groupId = GetterUtil.getLong(header.getHeaderString("groupId"));

		BackendAuth auth = new BackendAuthImpl();

		try {
			if (!auth.isAuth(serviceContext)) {
				throw new UnauthenticationException();
			}

			DossierActions actions = new DossierActionsImpl();

			Dossier dossier = actions.cloneDossier(groupId, dossierId, serviceContext);

			DossierDetailModel result = DossierUtils.mappingForGetDetail(dossier, user.getUserId());

			return Response.status(200).entity(result).build();

		} catch (Exception e) {
			_log.info(e);
			return processException(e);
		}

	}

	@Override
	public Response updateDossierNo(HttpServletRequest request, HttpHeaders header, Company company, Locale locale,
			User user, ServiceContext serviceContext, String id) {

		long groupId = GetterUtil.getLong(header.getHeaderString("groupId"));

		String dossierno = GetterUtil.getString(header.getHeaderString("dossierno"));

		BackendAuth auth = new BackendAuthImpl();

		try {

			if (!auth.isAuth(serviceContext)) {
				throw new UnauthenticationException();
			}

			_log.info("===================== dossierId " + id);

			_log.info("===================== groupId " + groupId);

			_log.info("===================== dossierno " + dossierno);

			Dossier dossier = DossierUtils.getDossier(id, groupId);

			if (dossier != null && Validator.isNull(dossier.getDossierNo())) {
				dossier.setDossierNo(dossierno);

				DossierLocalServiceUtil.updateDossier(dossier);
			}

			return Response.status(200).entity("OK").build();

		} catch (Exception e) {

			_log.error("Can't syncDossierNo with id = " + id, e);
			_log.info(e);
			return processException(e);

		}

	}

	Log _log = LogFactoryUtil.getLog(DossierManagementImpl.class);

	// Get dossier by certificate Number
	@SuppressWarnings("unchecked")
	@Override
	public Response getDossierByCertificateNumber(HttpServletRequest request, HttpHeaders header, Company company,
			Locale locale, User user, ServiceContext serviceContext, String certificateNumber) {
		_log.info("START*********1");
		long groupId = GetterUtil.getLong(header.getHeaderString("groupId"));
		_log.info("groupId: " + groupId);
		BackendAuth auth = new BackendAuthImpl();
		DossierActions actions = new DossierActionsImpl();

		try {

			if (!auth.isAuth(serviceContext)) {
				throw new UnauthenticationException();
			}

			LinkedHashMap<String, Object> params = new LinkedHashMap<String, Object>();

			_log.info("certificateNumber: " + certificateNumber);
			params.put(Field.GROUP_ID, String.valueOf(groupId));
			params.put(DossierTerm.DOSSIER_ID + "CTN", certificateNumber);

			Sort[] sorts = new Sort[] { SortFactoryUtil.create("modifiedDate" + "_sortable", Sort.STRING_TYPE, false) };

			JSONObject jsonData = actions.getDossiers(user.getUserId(), company.getCompanyId(), groupId, params, sorts,
					-1, -1, serviceContext);

			JSONObject results = JSONFactoryUtil.createJSONObject();

			Document data = ((List<Document>) jsonData.get("data")).get(0);
			results.put("dossierId", data.get(DossierTerm.DOSSIER_ID));

			return Response.status(200).entity(JSONFactoryUtil.looseSerialize(results)).build();

		} catch (Exception e) {
			_log.info(e);
			return processException(e);
		}

	}

	@Override
	public Response addDossierMark(HttpServletRequest request, HttpHeaders header, Company company, Locale locale,
			User user, ServiceContext serviceContext, long dossierId, String dossierPartNo,
			DossierMarkInputModel input) {

		long groupId = GetterUtil.getLong(header.getHeaderString("groupId"));

		BackendAuth auth = new BackendAuthImpl();

		try {
			if (!auth.isAuth(serviceContext)) {
				throw new UnauthenticationException();
			}

			DossierMarkActions actions = new DossierMarkActionsImpl();

			DossierMark dossierMark = actions.addDossierMark(groupId, dossierId, dossierPartNo, input.getFileMark(),
					input.getFileCheck(), input.getFileComment(), serviceContext);

			DossierMarkResultDetailModel result = DossierMarkUtils.mappingDossierMarkDetailModel(dossierMark);

			return Response.status(200).entity(result).build();

		} catch (Exception e) {
			_log.error(e);
			return processException(e);
		}
	}

	@Override
	public Response getDossierMarks(HttpServletRequest request, HttpHeaders header, Company company, Locale locale,
			User user, ServiceContext serviceContext, long dossierId) {

		long groupId = GetterUtil.getLong(header.getHeaderString("groupId"));

		BackendAuth auth = new BackendAuthImpl();

		try {
			if (!auth.isAuth(serviceContext)) {
				throw new UnauthenticationException();
			}

			DossierMarkActions actions = new DossierMarkActionsImpl();

			DossierMarkResultsModel result = new DossierMarkResultsModel();

			List<DossierMark> lstDossierMark = actions.getDossierMarks(groupId, dossierId);

			List<DossierMarkModel> outputs = DossierMarkUtils.mappingDossierMarks(lstDossierMark);

			result.setTotal(lstDossierMark.size());
			result.getData().addAll(outputs);

			return Response.status(200).entity(result).build();

		} catch (Exception e) {
			return processException(e);
		}
	}

	@Override
	public Response getDossierMarkbyDossierId(HttpServletRequest request, HttpHeaders header, Company company,
			Locale locale, User user, ServiceContext serviceContext, long dossierId, String dossierPartNo) {

		long groupId = GetterUtil.getLong(header.getHeaderString("groupId"));

		BackendAuth auth = new BackendAuthImpl();

		try {
			if (!auth.isAuth(serviceContext)) {
				throw new UnauthenticationException();
			}

			DossierMarkActions actions = new DossierMarkActionsImpl();

			DossierMark dossierMark = actions.getDossierMarkbyDossierId(groupId, dossierId, dossierPartNo);

			DossierMarkResultDetailModel result = DossierMarkUtils.mappingDossierMarkDetailModel(dossierMark);

			return Response.status(200).entity(result).build();

		} catch (Exception e) {
			return processException(e);
		}
	}

	@Override
	public Response cancellingRequestDossier(HttpServletRequest request, HttpHeaders header, Company company,
			Locale locale, User user, ServiceContext serviceContext, String id, String body) {

		long groupId = GetterUtil.getLong(header.getHeaderString("groupId"));
		BackendAuth auth = new BackendAuthImpl();
		DossierActions actions = new DossierActionsImpl();

//		DossierPermission dossierPermission = new DossierPermission();

		try {
			if (!auth.isAuth(serviceContext)) {
				throw new UnauthenticationException();
			}

			// 1. Update cancellingDate in dossier

			Dossier dossier = DossierUtils.getDossier(id, groupId);

			serviceContext.setScopeGroupId(groupId);

			Dossier cancellingDossier = actions.cancelDossier(groupId, dossier.getDossierId(),
					dossier.getReferenceUid(), serviceContext);

			// 2. update requestDossier

			String referenceUid = PortalUUIDUtil.generate();
			int status = 3;

			DossierRequestUDLocalServiceUtil.updateDossierRequest(0, dossier.getDossierId(), referenceUid,
					RT_CANCELLING, body, 1, status, serviceContext);

			DossierDetailModel result = DossierUtils.mappingForGetDetail(cancellingDossier, user.getUserId());

			return Response.status(200).entity(result).build();

		} catch (Exception e) {
			_log.info(e);
			return processException(e);
		}

	}

	@Override
	public Response correctingRequestDossier(HttpServletRequest request, HttpHeaders header, Company company,
			Locale locale, User user, ServiceContext serviceContext, String id, String body) {
		long groupId = GetterUtil.getLong(header.getHeaderString("groupId"));

		BackendAuth auth = new BackendAuthImpl();
		DossierActions actions = new DossierActionsImpl();

//		DossierPermission dossierPermission = new DossierPermission();

		try {
			if (!auth.isAuth(serviceContext)) {
				throw new UnauthenticationException();
			}

			// 1. Update cancellingDate in dossier

			Dossier dossier = DossierUtils.getDossier(id, groupId);

			serviceContext.setScopeGroupId(groupId);

			Dossier correctingDossier = actions.correctDossier(groupId, dossier.getDossierId(),
					dossier.getReferenceUid(), serviceContext);

			// 2. update requestDossier

			String referenceUid = PortalUUIDUtil.generate();
			int status = 3;

			DossierRequestUDLocalServiceUtil.updateDossierRequest(0, dossier.getDossierId(), referenceUid,
					RT_CORRECTING, body, 1, status, serviceContext);

			DossierDetailModel result = DossierUtils.mappingForGetDetail(correctingDossier, user.getUserId());

			return Response.status(200).entity(result).build();

		} catch (Exception e) {
			_log.info(e);
			return processException(e);
		}

	}

	@Override
	public Response submittingDossierPOST(HttpServletRequest request, HttpHeaders header, Company company,
			Locale locale, User user, ServiceContext serviceContext, String id, String body) {
		long groupId = GetterUtil.getLong(header.getHeaderString("groupId"));

		BackendAuth auth = new BackendAuthImpl();
		DossierActions actions = new DossierActionsImpl();

//		DossierPermission dossierPermission = new DossierPermission();

		try {
			if (!auth.isAuth(serviceContext)) {
				throw new UnauthenticationException();
			}

			// 1. Update cancellingDate in dossier

			Dossier dossier = DossierUtils.getDossier(id, groupId);

			serviceContext.setScopeGroupId(groupId);

			Dossier endorsementDossier = actions.submitPostDossier(groupId, dossier.getDossierId(),
					dossier.getReferenceUid(), serviceContext);

			// 2. update requestDossier

			String referenceUid = PortalUUIDUtil.generate();
			int status = 3;

			DossierRequestUDLocalServiceUtil.updateDossierRequest(0, dossier.getDossierId(), referenceUid,
					RT_SUBMITTING, body, 1, status, serviceContext);

			DossierDetailModel result = DossierUtils.mappingForGetDetail(endorsementDossier, user.getUserId());

			return Response.status(200).entity(result).build();

		} catch (Exception e) {
			_log.info(e);
			return processException(e);
		}

	}

	@Override
	public Response getProcessStepByDossierId(HttpServletRequest request, HttpHeaders header, Company company,
			Locale locale, User user, ServiceContext serviceContext, String id) {
		BackendAuth auth = new BackendAuthImpl();

		try {
			if (!auth.isAuth(serviceContext)) {
				throw new UnauthenticationException();
			}

			long groupId = GetterUtil.getLong(header.getHeaderString("groupId"));
			long dossierId = GetterUtil.getLong(id);

			Dossier dossier = DossierLocalServiceUtil.getDossier(dossierId);
			long dossierActionId = 0;
			DossierAction dossierAction = null;
			long serviceProcessId = 0;
			String actionCode = StringPool.BLANK;
			if (dossier != null) {
				dossierActionId = dossier.getDossierActionId();
				if (dossierActionId > 0) {
					dossierAction = DossierActionLocalServiceUtil.fetchDossierAction(dossierActionId);
				}
			}
			if (dossierAction != null) {
				serviceProcessId = dossierAction.getServiceProcessId();
				actionCode = dossierAction.getActionCode();
			}

			ProcessStep proStep = ProcessStepLocalServiceUtil.fetchBySC_GID(actionCode, groupId, serviceProcessId);
			String restrictDossier = StringPool.BLANK;
			if (proStep != null) {
				restrictDossier = proStep.getRestrictDossier();
			}

			return Response.status(200).entity(restrictDossier).build();

		} catch (Exception e) {
			_log.info(e);
			return processException(e);
		}
	}

	@Override
	public Response submitDossier(HttpServletRequest request, HttpHeaders header, Company company, Locale locale,
			User user, ServiceContext serviceContext, String id, String body) {
		long groupId = GetterUtil.getLong(header.getHeaderString("groupId"));

		BackendAuth auth = new BackendAuthImpl();

		DossierActions actions = new DossierActionsImpl();

		//DossierPermission dossierPermission = new DossierPermission();

		try {
			if (!auth.isAuth(serviceContext)) {
				throw new UnauthenticationException();
			}

			// 1. Update cancellingDate in dossier

			Dossier dossier = DossierUtils.getDossier(id, groupId);

			serviceContext.setScopeGroupId(groupId);

			Dossier cancellingDossier = actions.submitPostDossier(groupId, dossier.getDossierId(),
					dossier.getReferenceUid(), serviceContext);

			// 2. update requestDossier

			String referenceUid = PortalUUIDUtil.generate();
			int status = 0;

			DossierRequestUDLocalServiceUtil.updateDossierRequest(0, dossier.getDossierId(), referenceUid,
					RT_SUBMITTING, body, 1, status, serviceContext);

			DossierDetailModel result = DossierUtils.mappingForGetDetail(cancellingDossier, user.getUserId());

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
				error.setDescription(e.getMessage());

				return Response.status(HttpURLConnection.HTTP_FORBIDDEN).entity(error).build();

			}
		}
	}

	public Response getDossierPenddingByDossierId(HttpServletRequest request, HttpHeaders header, Company company,
			Locale locale, User user, ServiceContext serviceContext, String referenceUid) {
		BackendAuth auth = new BackendAuthImpl();

		try {
			if (!auth.isAuth(serviceContext)) {
				throw new UnauthenticationException();
			}

			//TODO: Fix port process
			long groupId = 55301;

			Dossier dossier = DossierLocalServiceUtil.getByRef(groupId, referenceUid);
			long dossierActionId = 0;
			DossierAction dossierAction = null;
			boolean pendding = false;
			if (dossier != null) {
				dossierActionId = dossier.getDossierActionId();
				if (dossierActionId > 0) {
					dossierAction = DossierActionLocalServiceUtil.fetchDossierAction(dossierActionId);
				}
			}
			if (dossierAction != null) {
				pendding = dossierAction.getPending();
			}

			return Response.status(200).entity(pendding).build();

		} catch (Exception e) {
			_log.info(e);
			return processException(e);
		}
	}

	@SuppressWarnings({ "unchecked"})
	@Override
	public Response getDossiersPendingList(HttpServletRequest request, HttpHeaders header, Company company,
			Locale locale, User user, ServiceContext serviceContext, DossierSearchModel query) {

		long groupId = GetterUtil.getLong(header.getHeaderString("groupId"));
		BackendAuth auth = new BackendAuthImpl();
		DossierPermission dossierPermission = new DossierPermission();
		DossierActions actions = new DossierActionsImpl();

		try {
			boolean isCitizen = false;

			if (!query.getSecetKey().contentEquals("OPENCPSV2")) {

				if (!auth.isAuth(serviceContext)) {
					throw new UnauthenticationException();
				}

				isCitizen = dossierPermission.isCitizen(user.getUserId());

				dossierPermission.hasGetDossiers(groupId, user.getUserId(), query.getSecetKey());
			}

			if (query.getEnd() == 0) {

				query.setStart(-1);

				query.setEnd(-1);

			}

			LinkedHashMap<String, Object> params = new LinkedHashMap<String, Object>();
			DossierResultsModel results = new DossierResultsModel();

			params.put(Field.GROUP_ID, String.valueOf(groupId));
			params.put(Field.KEYWORD_SEARCH, query.getKeyword());

			String owner = query.getOwner();
			// If user is citizen then default owner true
			if (isCitizen) {
				owner = String.valueOf(true);
			}
			// Get info input params
			String submitting = query.getSubmitting();
			String pendding = query.getPendding();
			String applicantIdNo = query.getApplicantIdNo();

			params.put(DossierTerm.OWNER, owner);
			params.put(DossierTerm.SUBMITTING, submitting);
			params.put("pendding", pendding);
			params.put(DossierTerm.APPLICANT_ID_NO, applicantIdNo);

			Sort[] sorts = new Sort[] { SortFactoryUtil.create(query.getSort() + "_sortable", Sort.STRING_TYPE,
					GetterUtil.getBoolean(query.getOrder())) };

			// _log.info("START 1");
			// _log.info("START Applicant: "+query.getApplicantIdNo());

			if (Boolean.parseBoolean(pendding)) {
				long groupIdCXL = 55301;
				JSONObject jsonDataPending = null;

				List<DossierAction> dActionList = DossierActionLocalServiceUtil.getDossiersPending(groupIdCXL,
						pendding);

				if (dActionList != null && dActionList.size() > 0) {
					LinkedHashMap<String, Object> paramPending = new LinkedHashMap<String, Object>();
					_log.info("dActionList: " + dActionList.size());
					int length = dActionList.size();
					StringBuilder sb = new StringBuilder();
					for (int i = 0; i < length; i++) {
						DossierAction dAct = dActionList.get(i);
						long dActId = dAct.getDossierActionId();
						if (i == 0) {
							sb.append(dActId);
						} else {
							sb.append(StringPool.COMMA);
							sb.append(dActId);

						}
					}
					_log.info("DOSSIER_ACTION_ID_PENDING: " + sb.toString());

					paramPending.put(Field.GROUP_ID, String.valueOf(groupIdCXL));
					paramPending.put(DossierTerm.OWNER, String.valueOf(false));
					paramPending.put(DossierTerm.APPLICANT_ID_NO, query.getApplicantIdNo());
					paramPending.put(DossierTerm.DOSSIER_ACTION_ID_PENDING, sb.toString());

					jsonDataPending = actions.getDossiers(user.getUserId(), company.getCompanyId(), groupIdCXL,
							paramPending, sorts, -1, -1, serviceContext);
					_log.info("jsonDataPending: " + jsonDataPending);
				}
				if (jsonDataPending != null) {
					List<Document> docs = (List<Document>) jsonDataPending.get("data");
					if (docs != null && docs.size() > 0) {
						StringBuilder sb1 = new StringBuilder();
						int length = docs.size();
						for (int i = 0; i < length; i++) {
							Document doc = docs.get(i);
							String referenceUid = doc.get(DossierTerm.REFERENCE_UID);
							if (i == 0) {
								sb1.append(referenceUid);
							} else {
								sb1.append(StringPool.COMMA);
								sb1.append(referenceUid);
							}
						}
						_log.info("REFERENCE_UID: " + sb1.toString());
						params.put(DossierTerm.REFERENCE_UID, sb1.toString());
					}
				}

			}

			JSONObject jsonData = actions.getDossiers(user.getUserId(), company.getCompanyId(), groupId, params, sorts,
					-1, -1, serviceContext);

			if (jsonData == null) {
				jsonData = JSONFactoryUtil.createJSONObject();
			}

			results.setTotal(jsonData.getInt("total"));

			results.getData().addAll(DossierUtils.mappingForGetList((List<Document>) jsonData.get("data")));

			return Response.status(200).entity(results).build();

		} catch (Exception e) {
			_log.info(e);
			return processException(e);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public Response getDossiersInfoList(HttpServletRequest request, HttpHeaders header, Company company, Locale locale,
			User user, ServiceContext serviceContext, DossierSearchModel query) {

		long groupId = GetterUtil.getLong(header.getHeaderString("groupId"));
		BackendAuth auth = new BackendAuthImpl();
		DossierPermission dossierPermission = new DossierPermission();
		DossierActions actions = new DossierActionsImpl();

		try {
			boolean isCitizen = false;

			if (!query.getSecetKey().contentEquals("OPENCPSV2")) {

				if (!auth.isAuth(serviceContext)) {
					throw new UnauthenticationException();
				}

				isCitizen = dossierPermission.isCitizen(user.getUserId());

				dossierPermission.hasGetDossiers(groupId, user.getUserId(), query.getSecetKey());
			}

			if (query.getEnd() == 0) {

				query.setStart(-1);

				query.setEnd(-1);

			}

			LinkedHashMap<String, Object> params = new LinkedHashMap<String, Object>();
			DossierResultsModel results = new DossierResultsModel();

			params.put(Field.GROUP_ID, String.valueOf(groupId));
			params.put(Field.KEYWORD_SEARCH, query.getKeyword());

			String owner = query.getOwner();
			// If user is citizen then default owner true
			if (isCitizen) {
				owner = String.valueOf(true);
			}

			// Test API new
			String dossierArr = query.getDossierArr();
			String status = query.getStatus();
			// Get info input params
			// String submitting = query.getSubmitting();
			// String pendding = query.getPendding();
			String applicantIdNo = query.getApplicantIdNo();

			params.put(DossierTerm.OWNER, owner);
			// params.put(DossierTerm.SUBMITTING, submitting);
			// params.put("pendding", pendding);
			params.put(DossierTerm.APPLICANT_ID_NO, applicantIdNo);
			params.put(DossierTerm.STATUS, status);
			params.put("dossierArr", dossierArr);

			Sort[] sorts = new Sort[] { SortFactoryUtil.create(query.getSort() + "_sortable", Sort.STRING_TYPE,
					GetterUtil.getBoolean(query.getOrder())) };

			// if (Boolean.parseBoolean(pendding)) {
			// long groupIdCXL = 55301;
			// JSONObject jsonDataPending = null;
			//
			// List<DossierAction> dActionList =
			// DossierActionLocalServiceUtil.getDossiersPending(groupIdCXL,
			// pendding);
			//
			// if (dActionList != null && dActionList.size() > 0) {
			// LinkedHashMap<String, Object> paramPending = new
			// LinkedHashMap<String, Object>();
			// _log.info("dActionList: "+dActionList.size());
			// int length = dActionList.size();
			// StringBuilder sb = new StringBuilder();
			// for (int i = 0; i < length; i ++) {
			// DossierAction dAct = dActionList.get(i);
			// long dActId = dAct.getDossierActionId();
			// if (i == 0) {
			// sb.append(dActId);
			// } else {
			// sb.append(StringPool.COMMA);
			// sb.append(dActId);
			//
			// }
			// }
			// _log.info("DOSSIER_ACTION_ID_PENDING: "+sb.toString());
			//
			// paramPending.put(Field.GROUP_ID, String.valueOf(groupIdCXL));
			// paramPending.put(DossierTerm.OWNER, String.valueOf(false));
			// paramPending.put(DossierTerm.APPLICANT_ID_NO,
			// query.getApplicantIdNo());
			// paramPending.put(DossierTerm.DOSSIER_ACTION_ID_PENDING,
			// sb.toString());
			//
			// jsonDataPending = actions.getDossiers(user.getUserId(),
			// company.getCompanyId(), groupIdCXL, paramPending, sorts,
			// -1, -1, serviceContext);
			// _log.info("jsonDataPending: "+jsonDataPending);
			// }
			// if (jsonDataPending != null) {
			// List<Document> docs = (List<Document>)
			// jsonDataPending.get("data");
			// if (docs != null && docs.size() > 0) {
			// StringBuilder sb1 = new StringBuilder();
			// int length = docs.size();
			// for (int i = 0; i < length; i ++) {
			// Document doc = docs.get(i);
			// String referenceUid = doc.get(DossierTerm.REFERENCE_UID);
			// if (i == 0) {
			// sb1.append(referenceUid);
			// } else {
			// sb1.append(StringPool.COMMA);
			// sb1.append(referenceUid);
			// }
			// }
			// _log.info("REFERENCE_UID: "+sb1.toString());
			// params.put(DossierTerm.REFERENCE_UID, sb1.toString());
			// }
			// }
			//
			// }

			JSONObject jsonData = actions.getDossiers(user.getUserId(), company.getCompanyId(), groupId, params, sorts,
					-1, -1, serviceContext);

			if (jsonData == null) {
				jsonData = JSONFactoryUtil.createJSONObject();
			}

			results.setTotal(jsonData.getInt("total"));

			results.getData().addAll(DossierUtils.mappingForGetList((List<Document>) jsonData.get("data")));

			return Response.status(200).entity(results).build();

		} catch (Exception e) {
			_log.info(e);
			return processException(e);
		}
	}

	@Override
	public Response getReassignUsers(HttpServletRequest request, HttpHeaders header, Company company, Locale locale,
			User user, long dossierId, ServiceContext serviceContext) {
		ReAssign reAssign = new ReAssign();

		List<ToUsers> lstUsers = new ArrayList<>();
		
		Dossier dossier = DossierLocalServiceUtil.fetchDossier(dossierId);
		if (dossier != null) {
			DossierAction dossierAction = DossierActionLocalServiceUtil.fetchDossierAction(dossier.getDossierActionId());
			if (dossierAction != null) {
				String stepCode = dossierAction.getStepCode();
				List<org.opencps.dossiermgt.model.DossierActionUser> lstDossierActionUsers = DossierActionUserLocalServiceUtil.getByDossierAndStepCode(dossierId, stepCode);
				for (org.opencps.dossiermgt.model.DossierActionUser dau : lstDossierActionUsers) {
					ToUsers toUsers = new ToUsers();
					toUsers.setAssigned(dau.getAssigned());
					toUsers.setUserId(dau.getUserId());
					User u = UserLocalServiceUtil.fetchUser(toUsers.getUserId());
					toUsers.setModerator(dau.getModerator() == 1 ? true : false);
					toUsers.setUserName(u.getFullName());
					lstUsers.add(toUsers);
				}
			}
		}
		
		reAssign.getToUsers().addAll(lstUsers);
		
		return Response.status(200).entity(reAssign).build();
	}

	@Override
	public Response updateReassignUsers(HttpServletRequest request, HttpHeaders header, Company company, Locale locale,
			User user, ServiceContext serviceContext, long dossierId, String toUsers) {

		long groupId = GetterUtil.getLong(header.getHeaderString("groupId"));
//		_log.info("groupId: "+groupId);
//		_log.info("toUsers: "+toUsers);
		DossierActionUser oldDau = null;
		
		try {
			Dossier dossier = DossierLocalServiceUtil.fetchDossier(dossierId);
			ReAssign reAssign = new ReAssign();
			if (dossier != null) {
				DossierAction dossierAction = DossierActionLocalServiceUtil
						.fetchDossierAction(dossier.getDossierActionId());
				if (dossierAction != null) {
					String stepCode = dossierAction.getStepCode();
					long dossierActionId = dossier.getDossierActionId();
					DossierActionUserLocalServiceUtil.deleteByDossierAndStepCode(dossierId, stepCode);
					//
					if (Validator.isNotNull(toUsers)) {
						JSONArray userArr = JSONFactoryUtil.createJSONArray(toUsers);
						if (userArr != null && userArr.length() > 0) {
							long userId = 0;
							int assigned = 0;
							int moderator = 0;
							for (int i = 0; i < userArr.length(); i++) {
								JSONObject jsonUser = userArr.getJSONObject(i);
								if (jsonUser != null) {
									userId = jsonUser.getLong("userId");
									assigned = jsonUser.getInt("assigned");
									if (assigned > 0) {
										moderator = 1;
									}
									DossierActionUserPK pk = new DossierActionUserPK();
									pk.setDossierActionId(dossierActionId);
									pk.setUserId(userId);
									
									oldDau = DossierActionUserLocalServiceUtil.fetchDossierActionUser(pk);
									
									if (oldDau == null) {
										DossierActionUserLocalServiceUtil.addDossierActionUser(userId, groupId,
												dossierActionId, dossierId, stepCode, moderator, assigned, true);										
									}
									else {
										oldDau.setModerator(moderator);
										DossierActionUserLocalServiceUtil.updateDossierActionUser(oldDau);
									}
									
									if (moderator == 1) {
										DossierUser du = DossierUserLocalServiceUtil.findByDID_UD(dossierId, userId);
										if (du != null) {
											if (du.getUserId() == userId && du.getModerator() == 0 && moderator == 1) {
												du.setModerator(moderator);
												DossierUserLocalServiceUtil.updateDossierUser(dossierId, du.getUserId(), moderator, true);
											}
										}
										else {
											DossierUserLocalServiceUtil.addDossierUser(groupId, dossierId, userId, moderator, true);
										}
									}
									else {
										DossierUser du = DossierUserLocalServiceUtil.findByDID_UD(dossierId, userId);
										if (du != null) {
										}
										else {
											DossierUserLocalServiceUtil.addDossierUser(groupId, dossierId, userId, moderator, true);
										}										
									}
								}
							}
							List<org.opencps.dossiermgt.model.DossierActionUser> lstDossierActionUsers = DossierActionUserLocalServiceUtil
									.getByDossierAndStepCode(dossierId, stepCode);
							List<ToUsers> lstUsers = new ArrayList<>();
							for (org.opencps.dossiermgt.model.DossierActionUser dau : lstDossierActionUsers) {
								ToUsers tu = new ToUsers();
								tu.setAssigned(dau.getAssigned());
								tu.setUserId(dau.getUserId());
								User u = UserLocalServiceUtil.fetchUser(tu.getUserId());
								tu.setModerator(dau.getModerator() == 1 ? true : false);
								tu.setUserName(u.getFullName());
								lstUsers.add(tu);
							}
							reAssign.getToUsers().addAll(lstUsers);
						}
					}
				}
			}
			return Response.status(200).entity(reAssign).build();
		} catch (Exception e) {
			_log.error(e);
			return processException(e);
		}

	}

	@Override
	public Response rollback(HttpServletRequest request, HttpHeaders header, Company company, Locale locale, User user,
			ServiceContext serviceContext, String id) {
		long groupId = GetterUtil.getLong(header.getHeaderString("groupId"));
		Dossier dossier = null;
		try {
			long dossierId = Integer.parseInt(id);
			dossier = DossierLocalServiceUtil.fetchDossier(dossierId);
		}
		catch (NumberFormatException nfe) {
			
		}
		if (dossier == null) {
			dossier = DossierLocalServiceUtil.getByRef(groupId, id);
		}
		if (dossier != null) {
			DossierAction dossierAction = DossierActionLocalServiceUtil.fetchDossierAction(dossier.getDossierActionId());
			if (dossierAction != null && dossierAction.isRollbackable()) {
				DossierActionLocalServiceUtil.updateState(dossierAction.getDossierActionId(), DossierActionTerm.STATE_ROLLBACK);
			
				DossierAction previousAction = DossierActionLocalServiceUtil.fetchDossierAction(dossierAction.getPreviousActionId());
				if (previousAction != null) {
					DossierActionLocalServiceUtil.updateState(previousAction.getDossierActionId(), DossierActionTerm.STATE_WAITING_PROCESSING);
					try {
						DossierActionLocalServiceUtil.updateNextActionId(previousAction.getDossierActionId(), 0);
					} catch (PortalException e) {
//						e.printStackTrace();
						return processException(e);
					}
				}
			}
			return Response.status(200).entity(null).build();			
		}
		else {
			return Response.status(404).entity(null).build();			
		}
	}

	@Override
	public Response getDossierSequences(HttpServletRequest request, HttpHeaders header, Company company, Locale locale,
			User user, ServiceContext serviceContext, String id) {
		BackendAuth auth = new BackendAuthImpl();
		long groupId = GetterUtil.getLong(header.getHeaderString("groupId"));
		DossierSequenceResultModel result = new DossierSequenceResultModel();
		
		try {

			if (!auth.isAuth(serviceContext)) {
				throw new UnauthenticationException();
			}
//			if (!auth.hasResource(serviceContext, ProcessSequence.class.getName(), ActionKeys.ADD_ENTRY)) {
//				throw new UnauthorizationException("UnauthorizationException");
//			}

			Dossier dossier = null;
			long dossierId = GetterUtil.getLong(id);
			dossier = DossierLocalServiceUtil.getDossier(dossierId);
			if (dossier == null) {
				dossier = DossierLocalServiceUtil.getByRef(groupId, id);
			}
			if (dossier == null) {
				throw new Exception("Không tìm thấy hồ sơ");
			}
			String serviceCode = dossier.getServiceCode();
			ServiceProcess serviceProcess = ServiceProcessLocalServiceUtil.getServiceByCode(groupId, serviceCode, dossier.getGovAgencyCode(), dossier.getDossierTemplateNo());
			if (serviceProcess == null) {
				throw new Exception("Không tìm thấy hồ sơ");
			}
			
			List<ProcessSequence> lstSequences = ProcessSequenceLocalServiceUtil.getByServiceProcess(groupId, serviceProcess.getServiceProcessId());
			result.setProcessNo(serviceProcess.getProcessNo());
			result.setDurationUnit(serviceProcess.getDurationUnit());
			result.setDurationCount(serviceProcess.getDurationCount());
			result.setTotal(lstSequences.size());
			
			List<DossierSequenceModel> lstDsms = new ArrayList<>();
			for (ProcessSequence ps : lstSequences) {
				DossierSequenceModel dsm = new DossierSequenceModel();
				dsm.setSequenceNo(ps.getSequenceNo());
				dsm.setSequenceName(ps.getSequenceName());
				dsm.setSequenceRole(ps.getSequenceRole());
				dsm.setDurationCount(ps.getDurationCount());
				
				List<DossierAction> lstDossierActions = DossierActionLocalServiceUtil.findDossierActionByDID_FSN(dossier.getDossierId(), dsm.getSequenceNo());
				List<ActionModel> lstActionModels = new ArrayList<>();
				
				for (DossierAction da : lstDossierActions) {
					ActionModel am = new ActionModel();
					am.setUserId(da.getUserId());
					am.setFromStepCode(da.getFromStepCode());
					am.setFromStepName(da.getFromStepName());
					am.setSequenceNo(da.getSequenceNo());
					am.setDossierId(da.getDossierId());
					am.setServiceProcessId(da.getServiceProcessId());
					am.setPreviousActionId(da.getPreviousActionId());
					am.setActionCode(da.getActionCode());
					am.setActionName(da.getActionName());
					am.setActionNote(da.getActionNote());
					am.setActionUser(da.getActionUser());
					am.setActionOverdue(da.getActionOverdue());
					am.setPayload(da.getPayload());
					am.setPending(da.getPending());
					am.setRoolbackable(da.getRollbackable());
					am.setCreateDate(DateTimeUtils.convertDateToString(da.getCreateDate(), DateTimeUtils._TIMESTAMP));
					am.setModifiedDate(DateTimeUtils.convertDateToString(da.getModifiedDate(), DateTimeUtils._TIMESTAMP));
					am.setDueDate(DateTimeUtils.convertDateToString(da.getDueDate(), DateTimeUtils._TIMESTAMP));
					am.setNextActionId(da.getNextActionId());
					am.setState(da.getState());
					am.setStepCode(da.getStepCode());
					am.setStepName(da.getStepName());
					am.setUserId(da.getUserId());
					
					lstActionModels.add(am);
				}
				
				dsm.getActions().addAll(lstActionModels);
				
				lstDsms.add(dsm);
			}
						
			result.getData().addAll(lstDsms);
			
			return Response.status(200).entity(result).build();
		} catch (Exception e) {
			ErrorMsg error = new ErrorMsg();

			error.setMessage("Content not found!");
			error.setCode(404);
			error.setDescription(e.getMessage());

			return Response.status(404).entity(error).build();
		}	

	}

	@Override
	public Response addDossierFileByEForm(HttpServletRequest request, HttpHeaders header, Company company,
			Locale locale, User user, ServiceContext serviceContext, Attachment file, String id, String partNo, String formData) {
		BackendAuth auth = new BackendAuthImpl();

		long groupId = GetterUtil.getLong(header.getHeaderString("groupId"));
		_log.info("In dossier file create");
		
		try {

			if (!auth.isAuth(serviceContext)) {
				throw new UnauthenticationException();
			}

			long dossierId = GetterUtil.getLong(id);

			Dossier dossier = null;

			if (dossierId != 0) {
				dossier = DossierLocalServiceUtil.fetchDossier(dossierId);
				if (Validator.isNull(dossier)) {
					dossier = DossierLocalServiceUtil.getByRef(groupId, id);
				}
			} else {
				dossier = DossierLocalServiceUtil.getByRef(groupId, id);
			}

			String referenceUid = UUID.randomUUID().toString();

			DataHandler dataHandler = file.getDataHandler();
			
			DossierFileActions action = new DossierFileActionsImpl();
			DossierPart dossierPart = DossierPartLocalServiceUtil.fetchByTemplatePartNo(groupId, dossier.getDossierTemplateNo(), partNo);
			
			
			_log.info("__Start add file at:" + new Date());

			DossierFile dossierFile = action.addDossierFile(groupId, dossier.getDossierId(), referenceUid,
					dossier.getDossierTemplateNo(), partNo, dossierPart.getFileTemplateNo(), dossierPart.getPartName(), dataHandler.getName(), 0,
					dataHandler.getInputStream(), StringPool.BLANK, "true", serviceContext);
			
			_log.info("__End add file at:" + new Date());
			
			if(Validator.isNotNull(formData)) {
				dossierFile.setFormData(formData);
			}
					
			_log.info("__Start update dossier file at:" + new Date());

			DossierFileLocalServiceUtil.updateDossierFile(dossierFile);

			dossierFile = action.updateDossierFileFormData(groupId, dossier.getDossierId(), referenceUid, formData,
					serviceContext);
			
			_log.info("__End update dossier file at:" + new Date());

			DossierFileModel result = DossierFileUtils.mappingToDossierFileModel(dossierFile);
			
			_log.info("__End bind to dossierFile" + new Date());

			return Response.status(200).entity(result).build();

		} catch (Exception e) {
			return processException(e);
		}
	}

}
