package org.opencps.api.controller.impl;

import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.messaging.Message;
import com.liferay.portal.kernel.messaging.MessageBusUtil;
import com.liferay.portal.kernel.model.Company;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.model.Role;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.search.Document;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.search.Indexer;
import com.liferay.portal.kernel.search.IndexerRegistryUtil;
import com.liferay.portal.kernel.search.SearchException;
import com.liferay.portal.kernel.search.Sort;
import com.liferay.portal.kernel.search.SortFactoryUtil;
import com.liferay.portal.kernel.service.GroupLocalServiceUtil;
import com.liferay.portal.kernel.service.RoleLocalServiceUtil;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.ListUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.uuid.PortalUUIDUtil;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.UUID;

import javax.activation.DataHandler;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.util.HttpURLConnection;
import org.apache.cxf.jaxrs.ext.multipart.Attachment;
import org.opencps.api.controller.DossierManagement;
import org.opencps.api.controller.util.DossierFileUtils;
import org.opencps.api.controller.util.DossierMarkUtils;
import org.opencps.api.controller.util.DossierUtils;
import org.opencps.api.dossier.model.DoActionModel;
import org.opencps.api.dossier.model.DossierActionDetailModel;
import org.opencps.api.dossier.model.DossierDataModel;
import org.opencps.api.dossier.model.DossierDetailModel;
import org.opencps.api.dossier.model.DossierInputModel;
import org.opencps.api.dossier.model.DossierMultipleInputModel;
import org.opencps.api.dossier.model.DossierPublishModel;
import org.opencps.api.dossier.model.DossierResultPublishModel;
import org.opencps.api.dossier.model.DossierResultsModel;
import org.opencps.api.dossier.model.DossierSearchModel;
import org.opencps.api.dossierfile.model.DossierFileModel;
import org.opencps.api.dossiermark.model.DossierMarkInputModel;
import org.opencps.api.dossiermark.model.DossierMarkModel;
import org.opencps.api.dossiermark.model.DossierMarkResultDetailModel;
import org.opencps.api.dossiermark.model.DossierMarkResultsModel;
import org.opencps.api.reassign.model.ReAssign;
import org.opencps.api.reassign.model.ToUsers;
import org.opencps.api.v21.dossiersync.model.DossierSyncV21DataModel;
import org.opencps.api.v21.dossiersync.model.DossierSyncV21ResultsModel;
import org.opencps.auth.api.BackendAuth;
import org.opencps.auth.api.BackendAuthImpl;
import org.opencps.auth.api.exception.NotFoundException;
import org.opencps.auth.api.exception.UnauthenticationException;
import org.opencps.auth.utils.APIDateTimeUtils;
import org.opencps.datamgt.model.DictCollection;
import org.opencps.datamgt.model.DictItem;
import org.opencps.datamgt.service.DictCollectionLocalServiceUtil;
import org.opencps.datamgt.service.DictItemLocalServiceUtil;
import org.opencps.datamgt.util.HolidayUtils;
import org.opencps.dossiermgt.action.DossierActions;
import org.opencps.dossiermgt.action.DossierFileActions;
import org.opencps.dossiermgt.action.DossierMarkActions;
import org.opencps.dossiermgt.action.DossierSyncActions;
import org.opencps.dossiermgt.action.FileUploadUtils;
import org.opencps.dossiermgt.action.impl.DossierActionUserImpl;
import org.opencps.dossiermgt.action.impl.DossierActionsImpl;
import org.opencps.dossiermgt.action.impl.DossierFileActionsImpl;
import org.opencps.dossiermgt.action.impl.DossierMarkActionsImpl;
import org.opencps.dossiermgt.action.impl.DossierPermission;
import org.opencps.dossiermgt.action.impl.DossierSyncActionsImpl;
import org.opencps.dossiermgt.action.util.AutoFillFormData;
import org.opencps.dossiermgt.action.util.DossierActionUtils;
import org.opencps.dossiermgt.action.util.DossierMgtUtils;
import org.opencps.dossiermgt.action.util.DossierNumberGenerator;
import org.opencps.dossiermgt.action.util.SpecialCharacterUtils;
import org.opencps.dossiermgt.constants.ActionConfigTerm;
import org.opencps.dossiermgt.constants.DossierActionTerm;
import org.opencps.dossiermgt.constants.DossierActionUserTerm;
import org.opencps.dossiermgt.constants.DossierDocumentTerm;
import org.opencps.dossiermgt.constants.DossierFileTerm;
import org.opencps.dossiermgt.constants.DossierSyncTerm;
import org.opencps.dossiermgt.constants.DossierTerm;
import org.opencps.dossiermgt.constants.PaymentFileTerm;
import org.opencps.dossiermgt.constants.ProcessActionTerm;
import org.opencps.dossiermgt.constants.ProcessStepRoleTerm;
import org.opencps.dossiermgt.constants.PublishQueueTerm;
import org.opencps.dossiermgt.constants.ServiceProcessTerm;
import org.opencps.dossiermgt.model.ActionConfig;
import org.opencps.dossiermgt.model.Dossier;
import org.opencps.dossiermgt.model.DossierAction;
import org.opencps.dossiermgt.model.DossierActionUser;
import org.opencps.dossiermgt.model.DossierDocument;
import org.opencps.dossiermgt.model.DossierFile;
import org.opencps.dossiermgt.model.DossierLog;
import org.opencps.dossiermgt.model.DossierMark;
import org.opencps.dossiermgt.model.DossierPart;
import org.opencps.dossiermgt.model.DossierSync;
import org.opencps.dossiermgt.model.DossierTemplate;
import org.opencps.dossiermgt.model.DossierUser;
import org.opencps.dossiermgt.model.ProcessAction;
import org.opencps.dossiermgt.model.ProcessOption;
import org.opencps.dossiermgt.model.ProcessSequence;
import org.opencps.dossiermgt.model.ProcessStep;
import org.opencps.dossiermgt.model.ProcessStepRole;
import org.opencps.dossiermgt.model.PublishQueue;
import org.opencps.dossiermgt.model.ServiceConfig;
import org.opencps.dossiermgt.model.ServiceInfo;
import org.opencps.dossiermgt.model.ServiceProcess;
import org.opencps.dossiermgt.model.StepConfig;
import org.opencps.dossiermgt.service.ActionConfigLocalServiceUtil;
import org.opencps.dossiermgt.service.CPSDossierBusinessLocalServiceUtil;
import org.opencps.dossiermgt.service.DossierActionLocalServiceUtil;
import org.opencps.dossiermgt.service.DossierActionUserLocalServiceUtil;
import org.opencps.dossiermgt.service.DossierDocumentLocalServiceUtil;
import org.opencps.dossiermgt.service.DossierFileLocalServiceUtil;
import org.opencps.dossiermgt.service.DossierLocalServiceUtil;
import org.opencps.dossiermgt.service.DossierLogLocalServiceUtil;
import org.opencps.dossiermgt.service.DossierPartLocalServiceUtil;
import org.opencps.dossiermgt.service.DossierRequestUDLocalServiceUtil;
import org.opencps.dossiermgt.service.DossierSyncLocalServiceUtil;
import org.opencps.dossiermgt.service.DossierTemplateLocalServiceUtil;
import org.opencps.dossiermgt.service.DossierUserLocalServiceUtil;
import org.opencps.dossiermgt.service.ProcessActionLocalServiceUtil;
import org.opencps.dossiermgt.service.ProcessOptionLocalServiceUtil;
import org.opencps.dossiermgt.service.ProcessSequenceLocalServiceUtil;
import org.opencps.dossiermgt.service.ProcessStepLocalServiceUtil;
import org.opencps.dossiermgt.service.ProcessStepRoleLocalService;
import org.opencps.dossiermgt.service.ProcessStepRoleLocalServiceUtil;
import org.opencps.dossiermgt.service.PublishQueueLocalServiceUtil;
import org.opencps.dossiermgt.service.ServiceConfigLocalServiceUtil;
import org.opencps.dossiermgt.service.ServiceInfoLocalServiceUtil;
import org.opencps.dossiermgt.service.ServiceProcessLocalServiceUtil;
import org.opencps.dossiermgt.service.StepConfigLocalServiceUtil;
import org.opencps.dossiermgt.service.persistence.DossierActionUserPK;
import org.opencps.usermgt.model.Applicant;
import org.opencps.usermgt.model.Employee;
import org.opencps.usermgt.service.ApplicantLocalServiceUtil;
import org.opencps.usermgt.service.EmployeeLocalServiceUtil;

import backend.auth.api.exception.BusinessExceptionImpl;
import backend.auth.api.exception.ErrorMsgModel;
import uk.org.okapibarcode.backend.Code128;
import uk.org.okapibarcode.backend.HumanReadableLocation;
import uk.org.okapibarcode.backend.QrCode;
import uk.org.okapibarcode.backend.Symbol;
import uk.org.okapibarcode.output.Java2DRenderer;

public class DossierManagementImpl implements DossierManagement {

	public static final String RT_CANCELLING = "cancelling";
	public static final String RT_CORRECTING = "correcting";
	public static final String RT_SUBMITTING = "submitting";

	@SuppressWarnings("unchecked")
	@Override
	public Response getDossiers(HttpServletRequest request, HttpHeaders header, Company company, Locale locale,
			User user, ServiceContext serviceContext, DossierSearchModel query) {

		long groupId = GetterUtil.getLong(header.getHeaderString("groupId"));
		long userId = user.getUserId();
		String emailLogin = user.getEmailAddress();
		DossierActions actions = new DossierActionsImpl();

		try {
			//boolean isCitizen = false;
			if (Validator.isNull(query.getEnd()) || query.getEnd() == 0) {
				query.setStart(-1);
				query.setEnd(-1);
//				query.setStart(0);
//				query.setEnd(15);
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
			if ("all".equals(agency)) {
				agency = StringPool.BLANK;
			}
			String serviceCode = query.getService();
			String service = StringPool.BLANK;
			if (Validator.isNotNull(serviceCode)) {
				service = SpecialCharacterUtils.splitSpecial(serviceCode);
			}
			String templateNo = query.getTemplate();
			String template = StringPool.BLANK;
			if (Validator.isNotNull(templateNo)) {
				template = SpecialCharacterUtils.splitSpecial(templateNo);
			}
			//Integer originality = GetterUtil.getInteger(query.getOriginality());
			//String originality = query.getOriginality();
//			if (originality == -1) {
//				owner = String.valueOf(false);
//			} else {
				// If user is citizen then default owner true
//				if (isCitizen) {
//					owner = String.valueOf(true);
//				}
//			}

			String step = query.getStep();
			String submitting = query.getSubmitting();
			//Process Top using statistic
			int year = query.getYear();
			int month = query.getMonth();
			String fromStatisticDate = APIDateTimeUtils.convertNormalDateToLuceneDate(query.getFromStatisticDate());
			String toStatisticDate = APIDateTimeUtils.convertNormalDateToLuceneDate(query.getToStatisticDate());
			String top = query.getTop();
			if (Validator.isNotNull(top) && DossierTerm.STATISTIC.equals(top.toLowerCase())) {
				if ((year > 0 || month > 0) || (Validator.isNotNull(fromStatisticDate) || Validator.isNotNull(toStatisticDate))) {
//					if (Validator.isNotNull(fromStatisticDate) || Validator.isNotNull(toStatisticDate)) {
//						
//					}
				} else {
					Calendar baseDateCal = Calendar.getInstance();
					baseDateCal.setTime(new Date());
					if (month == 0) {
						month = baseDateCal.get(Calendar.MONTH) + 1;
					}
					if (year == 0) {
						year = baseDateCal.get(Calendar.YEAR);
					}
				}
			}

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
			//Process Statistic
			String fromReleaseDate = APIDateTimeUtils.convertNormalDateToLuceneDate(query.getFromReleaseDate());
			String toReleaseDate = APIDateTimeUtils.convertNormalDateToLuceneDate(query.getToReleaseDate());

			String fromFinishDate = APIDateTimeUtils.convertNormalDateToLuceneDate(query.getFromFinishDate());
			String toFinishDate = APIDateTimeUtils.convertNormalDateToLuceneDate(query.getToFinishDate());

			//_log.info("fromFinishDate: "+fromFinishDate);
			//_log.info("toFinishDate: "+toFinishDate);

			String fromReceiveNotDoneDate = APIDateTimeUtils
					.convertNormalDateToLuceneDate(query.getFromReceiveNotDoneDate());
			String toReceiveNotDoneDate = APIDateTimeUtils
					.convertNormalDateToLuceneDate(query.getToReceiveNotDoneDate());

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
			String domain = query.getDomain();
			String domainName = query.getDomainName();
			String applicantName = query.getApplicantName();
			String applicantIdNo = query.getApplicantIdNo();
			String serviceName = query.getServiceName();
			Integer originDossierId = query.getOriginDossierId();
			String owner = query.getOwner();
			String follow = query.getFollow();
			String applicantFollowIdNo = null;
			if (Boolean.valueOf(follow)) {
				Applicant applicant = ApplicantLocalServiceUtil.fetchByMappingID(userId);
				if (applicant != null) {
					applicantFollowIdNo = applicant.getApplicantIdNo();
				}
			}

			String permission = query.getPermission();
			if (Validator.isNotNull(permission)) {
				String permissionUserId = StringPool.BLANK;
				if (permission.contains(StringPool.COMMA)) {
					String[] permissionArr = permission.split(StringPool.COMMA);
					if (permissionArr != null && permissionArr.length > 0) {
						List<String> permissionList = new ArrayList<>();
						for (String permissionDetail : permissionArr) {
							if (Validator.isNotNull(permissionDetail)) {
								permissionList.add(user.getUserId() + StringPool.UNDERLINE + permissionDetail.toLowerCase());
							}
						}
						//
						permissionUserId = StringUtil.merge(permissionList, StringPool.COMMA);
					}
				} else {
					permissionUserId = user.getUserId() + StringPool.UNDERLINE + permission.toLowerCase();
				}

				params.put(DossierTerm.MAPPING_PERMISSION, permissionUserId);
			}

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
			params.put(DossierTerm.DAY, query.getDay());
			params.put(DossierTerm.STEP, step);
			params.put(DossierTerm.OWNER, owner);
			params.put(DossierTerm.APPLICANT_FOLLOW_ID_NO,
					Validator.isNotNull(applicantFollowIdNo) ? applicantFollowIdNo : StringPool.BLANK);
			params.put(DossierTerm.SUBMITTING, submitting);

			params.put(DossierTerm.FOLLOW, follow);
			params.put(DossierTerm.TOP, top);
			
			backend.auth.api.BackendAuth auth2 = new backend.auth.api.BackendAuthImpl();
			if (!auth2.isAdmin(serviceContext, "admin")) {
				params.put(DossierTerm.USER_ID, user.getUserId());
			}
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
			if (Validator.isNotNull(domain)) {
				params.put(DossierTerm.DOMAIN_CODE, domain);
			}
			params.put(DossierTerm.DOMAIN_NAME, domainName);
			params.put(DossierTerm.APPLICANT_NAME, applicantName);
			params.put(DossierTerm.APPLICANT_ID_NO, applicantIdNo);
			params.put(DossierTerm.SERVICE_NAME, serviceName);
			//Check guest search
			params.put(DossierTerm.EMAIL_USER_LOGIN, emailLogin);
			params.put(DossierTerm.ORIGINALLITY, query.getOriginality());
			//
			params.put(DossierTerm.FROM_RELEASE_DATE, fromReleaseDate);
			params.put(DossierTerm.TO_RELEASE_DATE, toReleaseDate);
			params.put(DossierTerm.FROM_FINISH_DATE, fromFinishDate);
			params.put(DossierTerm.TO_FINISH_DATE, toFinishDate);
			params.put(DossierTerm.FROM_RECEIVE_NOTDONE_DATE, fromReceiveNotDoneDate);
			params.put(DossierTerm.TO_RECEIVE_NOTDONE_DATE, toReceiveNotDoneDate);
			params.put(PaymentFileTerm.PAYMENT_STATUS, query.getPaymentStatus());
			params.put(DossierTerm.FROM_STATISTIC_DATE, fromStatisticDate);
			params.put(DossierTerm.TO_STATISTIC_DATE, toStatisticDate);
			params.put(DossierTerm.ORIGIN, query.getOrigin());
			params.put(DossierTerm.TIME, query.getTime());
			params.put(DossierTerm.REGISTER, query.getRegister());
			
			params.put(DossierTerm.GROUP_DOSSIER_ID, query.getGroupDossierId());
			
			//Search theo tu tuong moi
			//params.put(DossierTerm.ORIGINALLITY_TEST, strOriginality);
			if (Validator.isNotNull(originDossierId))
				params.put(DossierTerm.ORIGIN_DOSSIER_ID, originDossierId);
			
			Sort[] sorts = null;
			if (Validator.isNull(query.getSort())) {
				sorts = new Sort[] { SortFactoryUtil.create(DossierTerm.CREATE_DATE + "_sortable", Sort.STRING_TYPE,
						GetterUtil.getBoolean(query.getOrder())) };
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

			results.getData().addAll(
					DossierUtils.mappingForGetList((List<Document>) jsonData.get("data"), userId, query.getAssigned()));

			return Response.status(200).entity(results).build();

		} catch (Exception e) {
			return BusinessExceptionImpl.processException(e);
		}

	}

	//LamTV: Process dossierTodo
	@SuppressWarnings({ "unchecked"})
	@Override
	public Response getDossierProcessList(HttpServletRequest request, HttpHeaders header, Company company, Locale locale,
			User user, ServiceContext serviceContext, DossierSearchModel query) {

		long groupId = GetterUtil.getLong(header.getHeaderString("groupId"));
		long userId = user.getUserId();
//		_log.info("userId: "+userId);
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
//			_log.info("step: "+step);
			StringBuilder strStatusStep = null;
			StringBuilder strSubStatusStep = null;
			if (Validator.isNotNull(step)) {
				strStatusStep = new StringBuilder();
				strSubStatusStep = new StringBuilder();
				String[] stepArr = step.split(StringPool.COMMA);
				if (stepArr != null && stepArr.length > 0) {
					String statusStep;
					String subStatusStep;
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

			Integer assigned = query.getAssigned();
			StringBuilder strAssignedUserId = null;
			if (assigned != null && Validator.isNotNull(step)) {
				String[] stepArr = step.split(StringPool.COMMA);
				strAssignedUserId = new StringBuilder();
				if (stepArr != null && stepArr.length > 0) {
					for (int i = 0; i < stepArr.length; i++) {
						if (i == 0) {
							StringBuilder sbParams = new StringBuilder();
							if (stepArr[i].contains("x")) {
								for (int j = 0; j < 9; j++) {
									if (j == 0) {
										sbParams.append(userId + "_" + stepArr[i].replace("x", String.valueOf(j)) + "_" + assigned);
									} else {
										sbParams.append(StringPool.COMMA);
										sbParams.append(userId + "_" + stepArr[i].replace("x", String.valueOf(j)) + "_" + assigned);
									}
								}
							} else {
								sbParams.append(userId + "_" + stepArr[i] + "_" + assigned);
							}
							//
							strAssignedUserId.append(sbParams.toString());
						} else {
							strAssignedUserId.append(StringPool.COMMA);
							//
							StringBuilder sbParams = new StringBuilder();
							if (stepArr[i].contains("x")) {
								for (int j = 0; j < 9; j++) {
									if (j == 0) {
										sbParams.append(userId + "_" + stepArr[i].replace("x", String.valueOf(j)) + "_" + assigned);
									} else {
										sbParams.append(StringPool.COMMA);
										sbParams.append(userId + "_" + stepArr[i].replace("x", String.valueOf(j)) + "_" + assigned);
									}
								}
							} else {
								sbParams.append(userId + "_" + stepArr[i] + "_" + assigned);
							}
							//
							strAssignedUserId.append(sbParams.toString());
						}
					}
				}
				params.put(DossierTerm.ASSIGNED_USER_ID, strAssignedUserId.toString());
			}

			String agency = query.getAgency();
			String serviceCode = query.getService();
			String service = StringPool.BLANK;
			if (Validator.isNotNull(serviceCode)) {
				service = SpecialCharacterUtils.splitSpecial(serviceCode);
			}
			String templateNo = query.getTemplate();
			String template = StringPool.BLANK;
			if (Validator.isNotNull(templateNo)) {
				template = SpecialCharacterUtils.splitSpecial(templateNo);
			}
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
			//Process Top using statistic
			int year = query.getYear();
			int month = query.getMonth();
			String top = query.getTop();
			if (Validator.isNotNull(top) && DossierTerm.STATISTIC.equals(top.toLowerCase())) {
				Calendar baseDateCal = Calendar.getInstance();
				baseDateCal.setTime(new Date());
				if (month == 0) {
					month = baseDateCal.get(Calendar.MONTH) + 1;
				}
				if (year == 0) {
					year = baseDateCal.get(Calendar.YEAR);
				}
			}
//			_log.info("month: "+month);
//			_log.info("year: "+year);

			String state = query.getState();
			String dossierIdNo = query.getDossierNo();
//			_log.info("dossierIdNo: "+dossierIdNo);
			String dossierNoSearch = StringPool.BLANK;
			if (Validator.isNotNull(dossierIdNo)) {
				dossierNoSearch = SpecialCharacterUtils.splitSpecial(dossierIdNo);
			}
//			_log.info("dossierNoSearch: "+dossierNoSearch);
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
			String domain = query.getDomain();
			String domainName = query.getDomainName();
			String applicantName = query.getApplicantName();
			String applicantIdNo = query.getApplicantIdNo();
			String serviceName = query.getServiceName();
			
			params.put(DossierTerm.STATUS, status);
			params.put(DossierTerm.SUBSTATUS, substatus);
			params.put(DossierTerm.AGENCY, agency);
			params.put(DossierTerm.SERVICE, service);
			params.put(DossierTerm.TEMPLATE, template);
			params.put(DossierTerm.OWNER, owner);
			params.put(DossierTerm.SUBMITTING, submitting);
			params.put(DossierTerm.FOLLOW, follow);
			params.put(DossierTerm.TOP, top);
			params.put(DossierTerm.YEAR, year);
			params.put(DossierTerm.MONTH, month);
			params.put(DossierTerm.DAY, query.getDay());
			//backend.auth.api.BackendAuth auth2 = new backend.auth.api.BackendAuthImpl();
			//Check role follow dossier
			boolean isAdmin = false;
			List<Role> roles = RoleLocalServiceUtil.getUserRoles(user.getUserId());
			if (roles != null && roles.size() > 0) {
				for (Role role : roles) {
					// LamTV_Fix sonarqube
					if ("Administrator".equals(role.getName())) {
						isAdmin = true;
						break;
					}
					if ("Administrator_data".equals(role.getName())) {
						isAdmin = true;
						break;
					}
				}
			}
			if (isAdmin) {
			}
			else {
				params.put(DossierTerm.USER_ID, user.getUserId());
			}

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
			if (Validator.isNotNull(domain)) {
				params.put(DossierTerm.DOMAIN_CODE, domain);
			}
			params.put(DossierTerm.DOMAIN_NAME, domainName);
			params.put(DossierTerm.APPLICANT_NAME, applicantName);
			params.put(DossierTerm.APPLICANT_ID_NO, applicantIdNo);
			params.put(DossierTerm.SERVICE_NAME, serviceName);
			params.put(PaymentFileTerm.PAYMENT_STATUS, query.getPaymentStatus());
			//Process follow StepCode
			if (Validator.isNotNull(strStatusStep)) {
				params.put(DossierTerm.DOSSIER_STATUS_STEP, strStatusStep.toString());
			} else {
				params.put(DossierTerm.DOSSIER_STATUS_STEP, StringPool.BLANK);
			}
			if (Validator.isNotNull(strSubStatusStep)) {
				params.put(DossierTerm.DOSSIER_SUBSTATUS_STEP, strSubStatusStep.toString());
			} else {
				params.put(DossierTerm.DOSSIER_SUBSTATUS_STEP, StringPool.BLANK);
			}
			//if (auth2.isAdmin(serviceContext, "admin")) {
			if (isAdmin) {
			}
			else {
				String permission = user.getUserId() + StringPool.UNDERLINE + "write";
				params.put(DossierTerm.MAPPING_PERMISSION, permission);
			}

			// Add param original
			params.put(DossierTerm.ORIGINALLITY, query.getOriginality());
			params.put(DossierTerm.GROUP_DOSSIER_ID, query.getGroupDossierId());
			params.put(DossierTerm.REGISTER, query.getRegister());
			
			Sort[] sorts = null;
			if (Validator.isNull(query.getSort())) {
				sorts = new Sort[] { SortFactoryUtil.create(DossierTerm.CREATE_DATE + "_sortable", Sort.STRING_TYPE,
						GetterUtil.getBoolean(query.getOrder())) };
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

			JSONObject jsonData = actions.getDossierProcessList(user.getUserId(), company.getCompanyId(), groupId, params,
					sorts, query.getStart(), query.getEnd(), serviceContext);

			DossierResultsModel results = new DossierResultsModel();
			if (jsonData != null && jsonData.getInt("total") > 0) {
				results.setTotal(jsonData.getInt("total"));
				results.getData().addAll(DossierUtils.mappingForGetList((List<Document>) jsonData.get("data"), userId,
						query.getAssigned()));
			} else {
				results.setTotal(0);
			}

			return Response.status(200).entity(results).build();

		} catch (Exception e) {
			return BusinessExceptionImpl.processException(e);
		}

	}

	@Override
	public Response addDossier(HttpServletRequest request, HttpHeaders header, Company company, Locale locale,
			User user, ServiceContext serviceContext, DossierInputModel input) {

		long groupId = GetterUtil.getLong(header.getHeaderString("groupId"));
		try {
			Dossier dossier = CPSDossierBusinessLocalServiceUtil.addDossier(groupId, company, user, serviceContext,
					DossierUtils.convertFormModelToInputModel(input));
			DossierDetailModel result = DossierUtils.mappingForGetDetail(dossier, user.getUserId());
			return Response.status(HttpStatus.SC_OK).entity(result).build();
		} catch (Exception e) {
			return BusinessExceptionImpl.processException(e);
		}
	}

	@Override
	public Response getDetailDossier(HttpServletRequest request, HttpHeaders header, Company company, Locale locale,
			User user, ServiceContext serviceContext, String id, String secretKey) {

		long groupId = GetterUtil.getLong(header.getHeaderString("groupId"));
		String secretCode = GetterUtil.getString(header.getHeaderString("secretCode"));
//		_log.info("secretCode: "+secretCode);
//		_log.info("secretKey: "+secretKey);
		DossierPermission dossierPermission = new DossierPermission();
		BackendAuth auth = new BackendAuthImpl();

		try {

			if (Validator.isNotNull(secretKey)) {
				try {
					Dossier dossier = DossierUtils.getDossier(id, groupId);

					List<Role> userRoles = user.getRoles();
					boolean isAdmin = false;
					for (Role r : userRoles) {
						if (r.getName().startsWith("Administrator")) {
							isAdmin = true;
							break;
						}
					}

					if (!isAdmin) {
						dossierPermission.checkPassword(dossier, secretKey);						
					}
					
					DossierDetailModel result = DossierUtils.mappingForGetDetail(dossier, user.getUserId());

					return Response.status(200).entity(result).build();
				} catch (Exception e) {
					_log.debug(e);
					return Response.status(HttpURLConnection.HTTP_NOT_AUTHORITATIVE).entity("secretKey not sucess")
							.build();
				}

			} else if (Validator.isNotNull(secretCode)) {
				try {
					Dossier dossier = DossierUtils.getDossier(id, groupId);

					dossierPermission.checkPassword(dossier, secretCode);

					DossierDetailModel result = DossierUtils.mappingForGetDetail(dossier, user.getUserId());

					return Response.status(200).entity(result).build();
				} catch (Exception e) {
					_log.debug(e);
					return Response.status(HttpURLConnection.HTTP_NOT_AUTHORITATIVE).entity("secretCode not sucess")
							.build();
				}
			}
			else {
//				_log.info("START");
				if (!auth.isAuth(serviceContext)) {
					throw new UnauthenticationException();
				}

				Dossier dossier = DossierUtils.getDossier(id, groupId);
//				_log.info("dossier: "+dossier);

//				ProcessOption option = getProcessOption(dossier.getServiceCode(), dossier.getGovAgencyCode(),
//						dossier.getDossierTemplateNo(), groupId);
				// dossierPermission.hasGetDetailDossier(groupId,
				// user.getUserId(), dossier, option.getServiceProcessId());

				DossierDetailModel result = DossierUtils.mappingForGetDetail(dossier, user.getUserId());

				return Response.status(200).entity(result).build();

			}

		} catch (Exception e) {
			_log.error(e);
			return BusinessExceptionImpl.processException(e);
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
				postalCityName = getDictItemName(groupId, VNPOST_CITY_CODE, input.getPostalCityCode());
			}

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
					input.getApplicantNote(), input.isSameAsApplicant(), input.getDelegateName(),
					input.getDelegateIdNo(), input.getDelegateTelNo(), input.getDelegateEmail(),
					input.getDelegateAddress(), input.getDelegateCityCode(), input.getDelegateDistrictCode(),
					input.getDelegateWardCode(), input.getSampleCount(), input.getDossierName(), input.getBriefNote(),
					serviceContext);

			DossierDetailModel result = DossierUtils.mappingForGetDetail(dossier, user.getUserId());

			return Response.status(200).entity(result).build();

		} catch (Exception e) {
			return BusinessExceptionImpl.processException(e);
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
			if (dossier != null) {
				int originality = dossier.getOriginality();
				Dossier removeDossier = null;
				if (originality > 0) {
					dossier.setOriginality(-originality);
					removeDossier = DossierLocalServiceUtil.updateDossier(dossier);
					if (removeDossier != null && originality > DossierTerm.ORIGINALITY_DVCTT) {
						DossierMgtUtils.processSyncDeleteDossier(removeDossier, originality);
					}
				} else {
					removeDossier = actions.removeDossier(groupId, dossier.getDossierId(), dossier.getReferenceUid());
				}

				DossierDetailModel result = null;
				if (removeDossier != null) {
					result = DossierUtils.mappingForGetDetail(removeDossier, user.getUserId());
				}
				return Response.status(200).entity(result).build();
			} else {
				return Response.status(HttpServletResponse.SC_FORBIDDEN).entity("No find dossier is delete").build();
			}
		} catch (Exception e) {
			return BusinessExceptionImpl.processException(e);
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
			return BusinessExceptionImpl.processException(e);
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
			return BusinessExceptionImpl.processException(e);
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
			return BusinessExceptionImpl.processException(e);
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
			return BusinessExceptionImpl.processException(e);
		}

	}

	//LamTV: Process DoAction
	@Override
	public Response doAction(HttpServletRequest request, HttpHeaders header, Company company, Locale locale, User user,
			ServiceContext serviceContext, String id, DoActionModel input, Long dueDate) {

		long groupId = GetterUtil.getLong(header.getHeaderString("groupId"));
		long userId = user.getUserId();
//		DossierPermission dossierPermission = new DossierPermission();
		BackendAuth auth = new BackendAuthImpl();
		DossierActions actions = new DossierActionsImpl();
		DossierAction dossierResult = null;
		ErrorMsgModel errorModel = new ErrorMsgModel();
		String actionUser = input.getActionUser();
		Employee employee = EmployeeLocalServiceUtil.fetchByF_mappingUserId(groupId, user.getUserId());
		if (employee != null) {
			actionUser = employee.getFullName();
		} else {
			if (Validator.isNull(actionUser)) {
				actionUser = user.getFullName();
			}
		}
		
		try {
			if (!auth.isAuth(serviceContext)) {
				throw new UnauthenticationException();
			}

			Dossier dossier = DossierUtils.getDossier(id, groupId);

			_log.debug("LamTV-input: "+JSONFactoryUtil.looseSerialize(input));
			_log.debug("LamTV-Call in groupId: "+groupId + "|dossierId: "+id +" |userId: "+userId);

			if (dossier != null) {
				_log.debug("Dossier: " + dossier + ", action code: " + input.getActionCode());
				if (Validator.isNotNull(dueDate)) {
					DossierLocalServiceUtil.updateDueDate(groupId, dossier.getDossierId(), dossier.getReferenceUid(), new Date(dueDate), serviceContext);
				}
				String actionCode = input.getActionCode();
				if (Validator.isNotNull(actionCode)) {
					ActionConfig actConfig = ActionConfigLocalServiceUtil.getByCode(groupId, actionCode);
					_log.debug("Action config: " + actConfig);
					String serviceCode = dossier.getServiceCode();
					String govAgencyCode = dossier.getGovAgencyCode();
					String dossierTempNo = dossier.getDossierTemplateNo();
					if (actConfig != null) {
						boolean insideProcess = actConfig.getInsideProcess();
						ProcessOption option = DossierUtils.getProcessOption(serviceCode, govAgencyCode,
								dossierTempNo, groupId);
						if (insideProcess) {
							if (dossier.getDossierActionId() == 0) {
								if (option != null) {
									long serviceProcessId = option.getServiceProcessId();
									ProcessAction proAction = DossierUtils.getProcessAction(groupId, dossier, actionCode,
											serviceProcessId);
									if (proAction != null) {
										_log.debug("DO ACTION: " + proAction.getActionCode());
										dossierResult = actions.doAction(groupId, userId, dossier, option, proAction,
												actionCode, actionUser, input.getActionNote(),
												input.getPayload(), input.getAssignUsers(), input.getPayment(),
												actConfig.getSyncType(), serviceContext, errorModel);
									} else {
										//TODO: Error
									}
								}								
							}
							else {
								DossierAction dossierAction = DossierActionLocalServiceUtil.fetchDossierAction(dossier.getDossierActionId());
								if (dossierAction != null) {
									long serviceProcessId = dossierAction.getServiceProcessId();
									DossierTemplate dossierTemplate = DossierTemplateLocalServiceUtil.getByTemplateNo(groupId, dossier.getDossierTemplateNo());
									
									ProcessOption oldOption = ProcessOptionLocalServiceUtil.fetchBySP_DT(serviceProcessId, dossierTemplate.getDossierTemplateId());
									
									ProcessAction proAction = DossierUtils.getProcessAction(groupId, dossier, actionCode,
											serviceProcessId);
									if (proAction != null) {
										_log.debug("DO ACTION: " + proAction.getActionCode());
										dossierResult = actions.doAction(groupId, userId, dossier, oldOption, proAction,
												actionCode, actionUser, input.getActionNote(),
												input.getPayload(), input.getAssignUsers(), input.getPayment(),
												actConfig.getSyncType(), serviceContext, errorModel);
									} else {
										//TODO: Error
									}									
								}
							}
//							if (dossierResult != null) {
//								String actionCodeResult = dossierResult.getActionCode();
//								_log.info("actionCodeResult: "+actionCodeResult);
//								if (Validator.isNotNull(actionCodeResult)) {
//									ActionConfig actConfigResult = ActionConfigLocalServiceUtil.getByCode(groupId, actionCodeResult);
//									int dateOption = actConfigResult.getDateOption();
//									_log.info("dateOption: "+dateOption);
//									_log.info("dossierResult.getDossierActionId(): "+dossierResult.getDossierActionId());
//									if (dateOption == DossierTerm.DATE_OPTION_CAL_WAITING) {
//										DossierAction dActEnd = DossierActionLocalServiceUtil
//												.fetchDossierAction(dossierResult.getDossierActionId());
//										if (dActEnd != null) {
//											_log.info("dActEnd.getPreviousActionId(): "+dActEnd.getPreviousActionId());
//											DossierAction dActStart = DossierActionLocalServiceUtil
//													.fetchDossierAction(dActEnd.getPreviousActionId());
//											if (dActStart != null) {
//												long createEnd = dActEnd.getCreateDate().getTime();
//												long createStart = dActStart.getCreateDate().getTime();
//												_log.info("createStart: "+createStart);
//												_log.info("createEnd: "+createEnd);
//												if (createEnd > createStart) {
//													long extendDateTimeStamp = ExtendDueDateUtils.getTimeWaitingByHoliday(createStart, createEnd, groupId);
//													_log.info("extendDateTimeStamp: "+extendDateTimeStamp);
//													if (extendDateTimeStamp > 0) {
//														dossier = DossierLocalServiceUtil.fetchDossier(dossierResult.getDossierId());
//														long hoursCount = (long) (extendDateTimeStamp / (1000 * 60 * 60));
//														_log.info("hoursCount: "+hoursCount);
//														//_log.info("dossier.getExtendDate(): "+dossier.getExtendDate());
//														List<Holiday> holidayList = HolidayLocalServiceUtil
//																.getHolidayByGroupIdAndType(groupId, 0);
//														List<Holiday> extendWorkDayList = HolidayLocalServiceUtil
//																.getHolidayByGroupIdAndType(groupId, 1);
//
//														Date dueDateExtend = HolidayUtils.getEndDate(groupId,
//																dossier.getDueDate(), hoursCount, holidayList,
//																extendWorkDayList);
//														_log.info("dueDateExtend: "+dueDateExtend);
//														if (dueDateExtend != null) {
//															dossier.setDueDate(dueDateExtend);
//															//dossier.setCorrecttingDate(null);
//															DossierLocalServiceUtil.updateDossier(dossier);
//														}
//													}
//												}
//											}
//										}
//									} else if (dateOption == DossierTerm.DATE_OPTION_CHANGE_DUE_DATE) {
//										dossier = DossierLocalServiceUtil.fetchDossier(dossierResult.getDossierId());
//										if (dossier.getDueDate() != null) {
//											//dossier.setCorrecttingDate(dossier.getDueDate());
//											//dossier.setDueDate(null);
//											dossier.setLockState(DossierTerm.PAUSE_STATE);
//											DossierLocalServiceUtil.updateDossier(dossier);
//										}
//									} 
//									else if (dateOption == DossierTerm.DATE_OPTION_RESET_DUE_DATE) {
//										dossier = DossierLocalServiceUtil.fetchDossier(dossierResult.getDossierId());
//										if (dossier.getDueDate() != null) {
//											ServiceProcess serviceProcess = ServiceProcessLocalServiceUtil
//													.fetchServiceProcess(dossierResult.getServiceProcessId());
//											if (serviceProcess != null) {
//												Date newDueDate = HolidayUtils.getDueDate(new Date(),
//														serviceProcess.getDurationCount(),
//														serviceProcess.getDurationUnit(), groupId);
//												if (newDueDate != null) {
//													dossier.setDueDate(newDueDate);
//													DossierLocalServiceUtil.updateDossier(dossier);
//												}
//											}
//
//										}
//									}
//								}
//							}
						} else {
							dossierResult = actions.doAction(groupId, userId, dossier, option, null, actionCode,
									actionUser, input.getActionNote(), input.getPayload(),
									input.getAssignUsers(), input.getPayment(), actConfig.getSyncType(),
									serviceContext, errorModel);
						}
						//Process send email or sms
//						if (dossierResult != null) {
//							String notificationType = actConfig.getNotificationType();
							//
//							if (Validator.isNotNull(notificationType)) {
//								long notificationQueueId = CounterLocalServiceUtil.increment(NotificationQueue.class.getName());
//								
//								NotificationQueue queue = NotificationQueueLocalServiceUtil.createNotificationQueue(notificationQueueId);
								//Process add notification queue
//								Date now = new Date();
//	
//								Calendar cal = Calendar.getInstance();
//								cal.set(Calendar.HOUR, cal.get(Calendar.HOUR) + 1);
//								
//								queue.setCreateDate(now);
//								queue.setModifiedDate(now);
//								queue.setGroupId(groupId);
//								queue.setCompanyId(company.getCompanyId());
//								
//								queue.setNotificationType(notificationType);
//								queue.setClassName(Dossier.class.getName());
//								queue.setClassPK(String.valueOf(dossier.getPrimaryKey()));
//								queue.setToUsername(dossier.getUserName());
//								queue.setToUserId(dossier.getUserId());
								//queue.setToEmail(dossier.getContactEmail());
								//queue.setToTelNo(dossier.getContactTelNo());
//								if (notificationType.contains("APLC")) {
//									if (dossier.getOriginality() == 3) {
//										queue.setToEmail(dossier.getDelegateEmail());
//										queue.setToTelNo(dossier.getDelegateTelNo());
//									} else {
//										queue.setToEmail(dossier.getContactEmail());
//										queue.setToTelNo(dossier.getContactTelNo());
//									}
//								} else if (notificationType.contains("EPLC")) {
//									Employee employee = EmployeeLocalServiceUtil.fetchByF_mappingUserId(groupId,
//											dossier.getUserId());
//									if (employee != null) {
//										queue.setToEmail(employee.getEmail());
//										queue.setToTelNo(employee.getTelNo());
//									}
//								} else {
//									queue.setToEmail(dossier.getContactEmail());
//									queue.setToTelNo(dossier.getContactTelNo());
//								}
//								
//								JSONObject payload = JSONFactoryUtil.createJSONObject();
//								try {
	//								_log.info("START PAYLOAD: ");
//									payload.put(
//										"Dossier", JSONFactoryUtil.createJSONObject(
//											JSONFactoryUtil.looseSerialize(dossier)));
//								}
//								catch (JSONException parse) {
//									_log.error(parse);
//								}
	//							_log.info("payloadTest: "+payload.toJSONString());
//								queue.setPayload(payload.toJSONString());
//								queue.setExpireDate(cal.getTime());
//	
//								NotificationQueueLocalServiceUtil.addNotificationQueue(queue);
//							}
//						}
					} else {
						ProcessOption option = DossierUtils.getProcessOption(serviceCode, govAgencyCode, dossierTempNo,
								groupId);
						if (option != null) {
							long serviceProcessId = option.getServiceProcessId();
							ProcessAction proAction = DossierUtils.getProcessAction(groupId, dossier, actionCode,
									serviceProcessId);
							if (proAction != null) {
								dossierResult = actions.doAction(groupId, userId, dossier, option, proAction,
										actionCode, actionUser, input.getActionNote(), input.getPayload(),
										input.getAssignUsers(), input.getPayment(), 0, serviceContext, errorModel);
							} else {
								// TODO: Error
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
				long dossierActionId = dossierResult.getDossierActionId();
				DossierDocument doc = DossierDocumentLocalServiceUtil.getByActiocId(groupId, dossierActionId);
				long dossierDocumentId = 0;
				if (doc != null) {
					dossierDocumentId = doc.getDossierDocumentId();
				}
				
				DossierActionDetailModel dAction = DossierUtils.mappingDossierAction(dossierResult, dossierDocumentId);
//				String strDossierResult = JSONFactoryUtil.looseSerializeDeep(dossierResult);
//				JSONObject jsonData = null;
//				if (Validator.isNotNull(strDossierResult)) {
//					jsonData = JSONFactoryUtil.createJSONObject(strDossierResult);
//					jsonData.put(DossierActionTerm.DOSSIER_DOCUMENT_ID, dossierDocumentId);
//				}
				return Response.status(200).entity(dAction).build();
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
			_log.debug(e);
			return BusinessExceptionImpl.processException(e);
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

	protected String getDictItemName(long groupId, DictCollection dc, String itemCode) {

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

	protected String getServiceName(String serviceCode, String templateNo, long groupId) throws PortalException {

		try {
//			ServiceInfo service = ServiceInfoLocalServiceUtil.getByCode(groupId, serviceCode);
//			if (service != null) {
//				List<ServiceConfig> configList = ServiceConfigLocalServiceUtil.getByServiceInfo(groupId,
//						service.getServiceInfoId());
//				if (configList != null && configList.size() > 0) {
//					for (ServiceConfig config : configList) {
//						ProcessOption option = ProcessOptionLocalServiceUtil.getByDTPLNoAndServiceCF(groupId,
//								templateNo, config.getServiceConfigId());
//						if (option != null) {
//							return option.getOptionName();
//						}
//					}
//				}
//			}
			ServiceInfo service = ServiceInfoLocalServiceUtil.getByCode(groupId, serviceCode);
			if (service != null) {
				return service.getServiceName();
			}
		} catch (Exception e) {
			_log.debug(e);
			throw new NotFoundException("NotFoundExceptionWithServiceCode");
		}

		return StringPool.BLANK;
	}

	protected String getDossierTemplateName(String dossierTemplateCode, long groupId) throws PortalException {
		try {
			DossierTemplate template = DossierTemplateLocalServiceUtil.getByTemplateNo(groupId, dossierTemplateCode);

			return template.getTemplateName();
		} catch (Exception e) {
			_log.error(e);
			throw new NotFoundException("NotFoundExceptionWithTemplateCode");
		}

	}

	public static final String GOVERNMENT_AGENCY = "GOVERNMENT_AGENCY";
	public static final String ADMINISTRATIVE_REGION = "ADMINISTRATIVE_REGION";
	public static final String VNPOST_CITY_CODE = "VNPOST_CITY_CODE";
	public static final String REGISTER_BOOK = "REGISTER_BOOK";
	
//	public static final int LENGHT_DOSSIER_PASSWORD = 4;
//	public static final String DEFAULT_PATTERN_PASSWORD = "0123";

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
			return BusinessExceptionImpl.processException(e);
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
			return BusinessExceptionImpl.processException(e);
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
			return BusinessExceptionImpl.processException(e);

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
			return BusinessExceptionImpl.processException(e);
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
					input.getFileCheck(), input.getFileComment(), input.getRecordCount(), serviceContext);

			DossierMarkResultDetailModel result = DossierMarkUtils.mappingDossierMarkDetailModel(dossierMark);

			return Response.status(200).entity(result).build();

		} catch (Exception e) {
			return BusinessExceptionImpl.processException(e);
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
			return BusinessExceptionImpl.processException(e);
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
			return BusinessExceptionImpl.processException(e);
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
			return BusinessExceptionImpl.processException(e);
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
			return BusinessExceptionImpl.processException(e);
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
			return BusinessExceptionImpl.processException(e);
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
			return BusinessExceptionImpl.processException(e);
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
			return BusinessExceptionImpl.processException(e);
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
			return BusinessExceptionImpl.processException(e);
		}
	}

	@SuppressWarnings({ "unchecked"})
	@Override
	public Response getDossiersPendingList(HttpServletRequest request, HttpHeaders header, Company company,
			Locale locale, User user, ServiceContext serviceContext, DossierSearchModel query) {

		long groupId = GetterUtil.getLong(header.getHeaderString("groupId"));
		long userId = user.getUserId();
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

			results.getData().addAll(
					DossierUtils.mappingForGetList((List<Document>) jsonData.get("data"), userId, query.getAssigned()));

			return Response.status(200).entity(results).build();

		} catch (Exception e) {
			return BusinessExceptionImpl.processException(e);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public Response getDossiersInfoList(HttpServletRequest request, HttpHeaders header, Company company, Locale locale,
			User user, ServiceContext serviceContext, DossierSearchModel query) {

		long groupId = GetterUtil.getLong(header.getHeaderString("groupId"));
		long userId = user.getUserId();
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

			results.getData().addAll(
					DossierUtils.mappingForGetList((List<Document>) jsonData.get("data"), userId, query.getAssigned()));

			return Response.status(200).entity(results).build();

		} catch (Exception e) {
			return BusinessExceptionImpl.processException(e);
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
					//
					Employee employee = EmployeeLocalServiceUtil.fetchByF_mappingUserId(dossier.getGroupId(), u.getUserId());
					if (employee != null) {
						toUsers.setUserName(employee.getFullName());
					} else {
						toUsers.setUserName(u.getFullName());
					}
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
								Employee employee = EmployeeLocalServiceUtil.fetchByF_mappingUserId(dossier.getGroupId(), u.getUserId());
								if (employee != null) {
									tu.setUserName(employee.getFullName());
								} else {
									tu.setUserName(u.getFullName());
								}
								lstUsers.add(tu);
							}
							reAssign.getToUsers().addAll(lstUsers);
						}
					}
				}
			}
			return Response.status(200).entity(reAssign).build();
		} catch (Exception e) {
			return BusinessExceptionImpl.processException(e);
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
		List<Role> userRoles = user.getRoles();
		boolean isAdmin = false;
		for (Role r : userRoles) {
			if (r.getName().startsWith("Administrator")) {
				isAdmin = true;
				break;
			}
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
						DossierLocalServiceUtil.rollback(dossier, previousAction);
					} catch (PortalException e) {
						return BusinessExceptionImpl.processException(e);
					}
				}
				
				DossierSync ds = DossierSyncLocalServiceUtil.getByDID_DAD(groupId, dossier.getDossierId(), dossierAction.getDossierActionId());
				if (ds != null && ((ds.getSyncType() == DossierSyncTerm.SYNCTYPE_INFORM && dossier.getOriginality() == DossierTerm.ORIGINALITY_LIENTHONG)
						|| (ds.getSyncType() == DossierSyncTerm.SYNCTYPE_REQUEST && dossier.getOriginality() == DossierTerm.ORIGINALITY_DVCTT))) {
					DossierMgtUtils.processSyncRollbackDossier(dossier);					
				}
			}
			else if (dossierAction != null && isAdmin) {
				DossierActionLocalServiceUtil.updateState(dossierAction.getDossierActionId(), DossierActionTerm.STATE_ROLLBACK);
				
				DossierAction previousAction = DossierActionLocalServiceUtil.fetchDossierAction(dossierAction.getPreviousActionId());
				if (previousAction != null) {
					DossierActionLocalServiceUtil.updateState(previousAction.getDossierActionId(), DossierActionTerm.STATE_WAITING_PROCESSING);
					try {
						DossierActionLocalServiceUtil.updateNextActionId(previousAction.getDossierActionId(), 0);
						DossierLocalServiceUtil.rollback(dossier, previousAction);
					} catch (PortalException e) {
						return BusinessExceptionImpl.processException(e);
					}
				}	
				
				DossierSync ds = DossierSyncLocalServiceUtil.getByDID_DAD(groupId, dossier.getDossierId(), dossierAction.getDossierActionId());
				if (ds != null && ((ds.getSyncType() == DossierSyncTerm.SYNCTYPE_INFORM && dossier.getOriginality() == DossierTerm.ORIGINALITY_LIENTHONG)
						|| (ds.getSyncType() == DossierSyncTerm.SYNCTYPE_REQUEST && dossier.getOriginality() == DossierTerm.ORIGINALITY_DVCTT))) {
					DossierMgtUtils.processSyncRollbackDossier(dossier);					
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
		//BackendAuth auth = new BackendAuthImpl();
		long groupId = GetterUtil.getLong(header.getHeaderString("groupId"));
		
		try {

//			if (!auth.isAuth(serviceContext)) {
//				throw new UnauthenticationException();
//			}
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
			
			JSONObject result = getDossierProcessSequencesJSON(groupId, dossier, serviceProcess);
			
			return Response.status(200).entity(result.toJSONString()).build();
		} catch (Exception e) {
			return BusinessExceptionImpl.processException(e);
		}	

	}

	private JSONObject getDossierProcessSequencesJSON(long groupId, Dossier dossier, ServiceProcess serviceProcess) {
		JSONObject result = JSONFactoryUtil.createJSONObject();
		List<ProcessSequence> lstSequences = ProcessSequenceLocalServiceUtil.getByServiceProcess(groupId, serviceProcess.getServiceProcessId());

		result.put(ServiceProcessTerm.PROCESS_NO, serviceProcess.getProcessNo());
		result.put(ServiceProcessTerm.DURATION_UNIT, serviceProcess.getDurationUnit());
		result.put(ServiceProcessTerm.DURATION_COUNT, serviceProcess.getDurationCount());
		result.put("total", lstSequences.size());
		JSONArray sequenceArr = JSONFactoryUtil.createJSONArray();
		DossierAction lastDA = DossierActionLocalServiceUtil.fetchDossierAction(dossier.getDossierActionId());
		List<DossierActionUser> lstDus = DossierActionUserLocalServiceUtil.getListUser(dossier.getDossierActionId());
		List<DossierLog> lstLogs = new ArrayList<>();
		
		try {
			lstLogs = DossierLogLocalServiceUtil.getByDossierAndType(dossier.getDossierId(), "PROCESS_TYPE", QueryUtil.ALL_POS, QueryUtil.ALL_POS);
		} catch (PortalException e) {
			_log.debug(e);
		}
		Map<Long, JSONArray> mapFiles = new HashMap<>();
		
		for (DossierLog log : lstLogs) {
			JSONObject payload;
			try {
				payload = JSONFactoryUtil.createJSONObject(log.getPayload());
				if (payload.has("dossierActionId")) {
					mapFiles.put(payload.getLong("dossierActionId"), payload.getJSONArray("files"));
				}
			} catch (JSONException e) {
				_log.debug(e);
			}
		}
		
		List<DossierAction> dossierActionListCheck = DossierActionLocalServiceUtil.findByG_DID(groupId, dossier.getDossierId());
		if (dossierActionListCheck != null && dossierActionListCheck.size() == 1 && "400".equals(dossierActionListCheck.get(0).getStepCode())) {
		} else {
		for (ProcessSequence ps : lstSequences) {		
			JSONObject sequenceObj = JSONFactoryUtil.createJSONObject();
			sequenceObj.put("sequenceNo", ps.getSequenceNo());
			sequenceObj.put("sequenceName", ps.getSequenceName());
			sequenceObj.put("sequenceRole", ps.getSequenceRole());
			sequenceObj.put("durationCount", ps.getDurationCount());
			
			if (lastDA != null && lastDA.getSequenceNo().equals(ps.getSequenceNo())) {
				sequenceObj.put("statusText", "Đang thực hiện: " + lastDA.getStepName());
			}
			List<DossierAction> lstDossierActions = DossierActionLocalServiceUtil.findDossierActionByG_DID_FSN(groupId, dossier.getDossierId(), ps.getSequenceNo());
			List<DossierAction> lstPrevDossierActions = DossierActionLocalServiceUtil.findDossierActionByG_DID_SN(groupId, dossier.getDossierId(), ps.getSequenceNo());
				
			DossierAction lastAction = lstPrevDossierActions.size() > 0 ? lstPrevDossierActions.get(lstPrevDossierActions.size() - 1) : null;
			if (lastAction != null) {
				sequenceObj.put("startDate", lastAction.getCreateDate().getTime());
			}			
			
			if (lstDossierActions.size() > 0) {
				DossierAction lastDASequence = lstDossierActions.get(lstDossierActions.size() - 1);
				if (lastDASequence.getActionOverdue() != 0) {
					String preText = (lastDASequence.getActionOverdue() > 0 ? "Còn " : "Quá ");
					sequenceObj.put("overdueText", preText + lastDASequence.getActionOverdue() + " ngày");
				}
			}
			JSONArray assignUserArr = JSONFactoryUtil.createJSONArray();
			List<Long> lstUsers = new ArrayList<>();
			
			if (lastDA.getSequenceNo().equals(ps.getSequenceNo())) {
				for (DossierActionUser dau : lstDus) {
					User u = UserLocalServiceUtil.fetchUser(dau.getUserId());
					if (u != null) {
						if (!lstUsers.contains(dau.getUserId()) && dau.getModerator() == DossierActionUserTerm.ASSIGNED_TH) {
							JSONObject assignUserObj = JSONFactoryUtil.createJSONObject();
							lstUsers.add(dau.getUserId());
							assignUserObj.put("userId", dau.getUserId());
							//TODO: Not update user
							Employee emp = EmployeeLocalServiceUtil.fetchByF_mappingUserId(groupId, u.getUserId());
							if (emp != null) {
								assignUserObj.put("userName", emp.getFullName());
							} else {
								assignUserObj.put("userName", u.getFullName());
							}
							
							assignUserArr.put(assignUserObj);
						}
					}
				}
			}
			for (DossierAction da : lstDossierActions) {
				if (!lstUsers.contains(da.getUserId())) {
					JSONObject assignUserObj = JSONFactoryUtil.createJSONObject();
					lstUsers.add(da.getUserId());
					assignUserObj.put("userId", da.getUserId());
					//TODO: Not update user
					Employee emp = EmployeeLocalServiceUtil.fetchByF_mappingUserId(groupId, da.getUserId());
					if (emp != null) {
						assignUserObj.put("userName", emp.getFullName());
					} else {
						assignUserObj.put("userName", da.getUserName());
					}
					
					assignUserArr.put(assignUserObj);					
				}
			}

			sequenceObj.put("assignUsers", assignUserArr);
			
			JSONArray actionsArr = JSONFactoryUtil.createJSONArray();
			for (DossierAction da : lstDossierActions) {
				JSONObject actionObj = JSONFactoryUtil.createJSONObject();
				
				actionObj.put(DossierActionTerm.USER_ID, da.getUserId());
				actionObj.put("fromStepCode", da.getFromStepCode());
				actionObj.put("fromStepName", da.getFromStepName());
				actionObj.put("sequenceNo", da.getSequenceNo());
				actionObj.put("dossierId", da.getDossierId());
				actionObj.put("serviceProcessId", da.getServiceProcessId());
				actionObj.put("previousActionId", da.getPreviousActionId());
				actionObj.put("actionCode", da.getActionCode());
				actionObj.put("actionName", da.getActionName());
				actionObj.put("actionNote", da.getActionNote());
				actionObj.put("actionUser", da.getActionUser());
				actionObj.put("actionOverdue", da.getActionOverdue());
				actionObj.put("payload", da.getPayload());
				actionObj.put("pending", da.getPending());
				actionObj.put("rollbackable", da.getRollbackable());
				actionObj.put("createDate", da.getCreateDate() != null ? da.getCreateDate().getTime() : 0l);
				actionObj.put("modifiedDate", da.getModifiedDate() != null ? da.getModifiedDate().getTime() : 0l);
				actionObj.put("dueDate", da.getDueDate() != null ? da.getDueDate().getTime() : 0l);
				actionObj.put("nextActionId", da.getNextActionId());
				actionObj.put("state", da.getState());
				actionObj.put("stepCode", da.getStepCode());
				actionObj.put("stepName", da.getStepName());
				actionObj.put("userId", da.getUserId());				
				if (mapFiles.containsKey(da.getPreviousActionId())) {
					actionObj.put("files", mapFiles.get(da.getPreviousActionId()));
				}
				_log.info("Action obj: " + actionObj.toJSONString());
				actionsArr.put(actionObj);
			}			
			
			sequenceObj.put("actions", actionsArr);
			
			sequenceArr.put(sequenceObj);
			
		}
		}

		result.put("data", sequenceArr);
		return result;
	}
	
//	private DossierSequenceResultModel getDossierProcessSequences(long groupId, Dossier dossier, ServiceProcess serviceProcess) {
//		DossierSequenceResultModel result = new DossierSequenceResultModel();
//		List<ProcessSequence> lstSequences = ProcessSequenceLocalServiceUtil.getByServiceProcess(groupId, serviceProcess.getServiceProcessId());
//		result.setProcessNo(serviceProcess.getProcessNo());
//		result.setDurationUnit(serviceProcess.getDurationUnit());
//		result.setDurationCount(serviceProcess.getDurationCount());
//		result.setTotal(lstSequences.size());
//		
//		List<DossierSequenceModel> lstDsms = new ArrayList<>();
//		for (ProcessSequence ps : lstSequences) {
//			DossierSequenceModel dsm = new DossierSequenceModel();
//			dsm.setSequenceNo(ps.getSequenceNo());
//			dsm.setSequenceName(ps.getSequenceName());
//			dsm.setSequenceRole(ps.getSequenceRole());
//			dsm.setDurationCount(ps.getDurationCount());
//			
//			List<DossierAction> lstDossierActions = DossierActionLocalServiceUtil.findDossierActionByDID_FSN(dossier.getDossierId(), dsm.getSequenceNo());
//			List<ActionModel> lstActionModels = new ArrayList<>();
//			
//			for (DossierAction da : lstDossierActions) {
//				ActionModel am = new ActionModel();
//				am.setUserId(da.getUserId());
//				am.setFromStepCode(da.getFromStepCode());
//				am.setFromStepName(da.getFromStepName());
//				am.setSequenceNo(da.getSequenceNo());
//				am.setDossierId(da.getDossierId());
//				am.setServiceProcessId(da.getServiceProcessId());
//				am.setPreviousActionId(da.getPreviousActionId());
//				am.setActionCode(da.getActionCode());
//				am.setActionName(da.getActionName());
//				am.setActionNote(da.getActionNote());
//				am.setActionUser(da.getActionUser());
//				am.setActionOverdue(da.getActionOverdue());
//				am.setPayload(da.getPayload());
//				am.setPending(da.getPending());
//				am.setRoolbackable(da.getRollbackable());
//				am.setCreateDate(DateTimeUtils.convertDateToString(da.getCreateDate(), DateTimeUtils._TIMESTAMP));
//				am.setModifiedDate(DateTimeUtils.convertDateToString(da.getModifiedDate(), DateTimeUtils._TIMESTAMP));
//				am.setDueDate(DateTimeUtils.convertDateToString(da.getDueDate(), DateTimeUtils._TIMESTAMP));
//				am.setNextActionId(da.getNextActionId());
//				am.setState(da.getState());
//				am.setStepCode(da.getStepCode());
//				am.setStepName(da.getStepName());
//				am.setUserId(da.getUserId());
//				
//				lstActionModels.add(am);
//			}
//			
//			dsm.getActions().addAll(lstActionModels);
//			
//			lstDsms.add(dsm);
//		}
//					
//		result.getData().addAll(lstDsms);		
//		return result;
//	}
	
	@Override
	public Response addDossierFileByEForm(HttpServletRequest request, HttpHeaders header, Company company,
			Locale locale, User user, ServiceContext serviceContext, Attachment file, String id, String partNo, String removed, String eForm,
			String formData) {
		BackendAuth auth = new BackendAuthImpl();

		long groupId = GetterUtil.getLong(header.getHeaderString("groupId"));
		_log.info("In dossier file create by eform");
		
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
			DossierFile dossierFile = null;
			DossierFileActions action = new DossierFileActionsImpl();
			DossierPart dossierPart = DossierPartLocalServiceUtil.fetchByTemplatePartNo(groupId, dossier.getDossierTemplateNo(), partNo);
			_log.info("__file:" + file);
			DataHandler dataHandler = (file != null) ? file.getDataHandler() : null;
			
			if (dataHandler != null && dataHandler.getInputStream() != null) {
				_log.info("__Start add file at:" + new Date());
	
				dossierFile = DossierFileLocalServiceUtil.getByGID_DID_PART_EFORM(groupId, dossier.getDossierId(),
						partNo, true, false);
				if (dossierFile == null) {
					dossierFile = action.addDossierFileEForm(groupId, dossier.getDossierId(), referenceUid,
							dossier.getDossierTemplateNo(), partNo, dossierPart.getFileTemplateNo(), dossierPart.getPartName(), dataHandler.getName(), 0,
							dataHandler.getInputStream(), StringPool.BLANK, "true", serviceContext);
				} else {
					try {
						FileEntry fileEntry = FileUploadUtils.uploadDossierFile(user.getUserId(), groupId,
								dataHandler.getInputStream(), dossierPart.getPartName(), StringPool.BLANK, 0,
								serviceContext);

						if (fileEntry != null) {
							dossierFile.setFileEntryId(fileEntry.getFileEntryId());
						}
					} catch (Exception e) {
						_log.debug(e);
					}
				}
				
				_log.info("__End add file at:" + new Date());
			}
			else {
				dossierFile = DossierFileLocalServiceUtil.getByGID_DID_PART_EFORM(groupId, dossier.getDossierId(),
						partNo, true, false);
				if (dossierFile == null) {
					_log.info("dossierFile NULL");
					dossierFile = action.addDossierFileEForm(groupId, dossier.getDossierId(), referenceUid,
							dossier.getDossierTemplateNo(), partNo, dossierPart.getFileTemplateNo(), dossierPart.getPartName(), dossierPart.getPartName(), 0,
							null, StringPool.BLANK, "true", serviceContext);
				}
			}
			
			if(Validator.isNotNull(formData)) {
				dossierFile.setFormData(formData);
			}
			if(Validator.isNotNull(removed)) {
				dossierFile.setRemoved(Boolean.parseBoolean(removed));
			}
			if(Validator.isNotNull(eForm)) {
				dossierFile.setEForm(Boolean.parseBoolean(eForm));
			}

			_log.info("__Start update dossier file at:" + new Date());

			DossierFileLocalServiceUtil.updateDossierFile(dossierFile);

			dossierFile = action.updateDossierFileFormData(groupId, dossier.getDossierId(), dossierFile.getReferenceUid(), formData,
					serviceContext);
			
			_log.info("__End update dossier file at:" + new Date());

			DossierFileModel result = DossierFileUtils.mappingToDossierFileModel(dossierFile);
			
			_log.info("__End bind to dossierFile" + new Date());

			return Response.status(200).entity(result).build();

		} catch (Exception e) {
			return BusinessExceptionImpl.processException(e);
		}
	}

	@Override
	public Response getSampleDataByEForm(HttpServletRequest request, HttpHeaders header, Company company, Locale locale,
			User user, ServiceContext serviceContext, String id, String partNo) {
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

			DossierPart dossierPart = DossierPartLocalServiceUtil.fetchByTemplatePartNo(groupId, dossier.getDossierTemplateNo(), partNo);
//			_log.info("Dossier part sample data: " + dossierPart.getSampleData());
			String formData = AutoFillFormData.sampleDataBinding(dossierPart.getSampleData(), dossier.getDossierId(), serviceContext);
			
			return Response.status(200).entity(formData).build();

		} catch (Exception e) {
			return BusinessExceptionImpl.processException(e);
		}
	}

	@Override
	public Response removeDossierLucene(HttpServletRequest request, HttpHeaders header, Company company, Locale locale,
			User user, ServiceContext serviceContext, String id) {
//		Indexer<Dossier> indexer = IndexerRegistryUtil
//				.nullSafeGetIndexer(Dossier.class);
		return null;
	}

	@Override
	public Response removeConflictDossier(HttpServletRequest request, HttpHeaders header, Company company,
			Locale locale, User user, ServiceContext serviceContext) {
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Response getConflictDossier(HttpServletRequest request, HttpHeaders header, Company company, Locale locale,
			User user, ServiceContext serviceContext) {
		long groupId = GetterUtil.getLong(header.getHeaderString("groupId"));
//		long userId = user.getUserId();
		DossierActions actions = new DossierActionsImpl();
//        String authorizationHeader = header.getHeaderString(HttpHeaders.AUTHORIZATION);
//        if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
//            throw new NotAuthorizedException("Authorization header must be provided");
//        }
//
//        String token = authorizationHeader.substring("Bearer".length()).trim();
//        KeyGenerator keyGenerator = new OpenCPSKeyGenerator();
        try {

            // Validate the token
//            Key key = keyGenerator.generateKey();
//            Jwts.parser().setSigningKey(key).parseClaimsJws(token);
 
		
			LinkedHashMap<String, Object> params = new LinkedHashMap<String, Object>();
			params.put(Field.GROUP_ID, String.valueOf(groupId));
	
//			DossierResultsModel results = new DossierResultsModel();
	
			JSONObject jsonData = actions.getDossiers(user.getUserId(), company.getCompanyId(), groupId, params, null,
						-1, -1, serviceContext);
			
//			List<Dossier> lstInDbs = DossierLocalServiceUtil.getDossiers(QueryUtil.ALL_POS, QueryUtil.ALL_POS);
			
			long total = jsonData.getLong("total");
			JSONArray dossierArr = JSONFactoryUtil.createJSONArray();
			
			if (total > 0) {
				List<Document> lstDocuments = (List<Document>) jsonData.get("data");	
				for (Document document : lstDocuments) {
					long dossierId = GetterUtil.getLong(document.get(DossierTerm.DOSSIER_ID));
					Dossier oldDossier = DossierLocalServiceUtil.fetchDossier(dossierId);
					if (oldDossier == null) {
						JSONObject dossierObj = JSONFactoryUtil.createJSONObject();
						dossierObj.put(DossierTerm.DOSSIER_ID, dossierId);
						dossierArr.put(dossierObj);
					}
				}
			}
			else {
				
			}
			
			return Response.status(200).entity(dossierArr.toJSONString()).build();
        } catch (Exception e) {
        	return BusinessExceptionImpl.processException(e);
        }
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public Response resolveConflictDossier(HttpServletRequest request, HttpHeaders header, Company company,
			Locale locale, User user, ServiceContext serviceContext) {
		long groupId = GetterUtil.getLong(header.getHeaderString("groupId"));
//		long userId = user.getUserId();
		DossierActions actions = new DossierActionsImpl();
		Indexer<Dossier> indexer = IndexerRegistryUtil
				.nullSafeGetIndexer(Dossier.class);
		
		LinkedHashMap<String, Object> params = new LinkedHashMap<String, Object>();
		params.put(Field.GROUP_ID, String.valueOf(groupId));

//		DossierResultsModel results = new DossierResultsModel();

		JSONObject jsonData = actions.getDossiers(user.getUserId(), company.getCompanyId(), groupId, params, null,
					-1, -1, serviceContext);
		
//		List<Dossier> lstInDbs = DossierLocalServiceUtil.getDossiers(QueryUtil.ALL_POS, QueryUtil.ALL_POS);
		
		long total = jsonData.getLong("total");
//		JSONArray dossierArr = JSONFactoryUtil.createJSONArray();
		
		if (total > 0) {
			List<Document> lstDocuments = (List<Document>) jsonData.get("data");	
			for (Document document : lstDocuments) {
				long dossierId = GetterUtil.getLong(document.get(DossierTerm.DOSSIER_ID));
				long companyId = GetterUtil.getLong(document.get(Field.COMPANY_ID));
				String uid = document.get(Field.UID);
				Dossier oldDossier = DossierLocalServiceUtil.fetchDossier(dossierId);
				if (oldDossier == null) {
					try {
						indexer.delete(companyId, uid);
					} catch (SearchException e) {
						_log.error(e);
					}
				}
			}
		}

		params.put(DossierTerm.ORIGINALLITY, String.valueOf(9));
		JSONObject jsonDataGroup = actions.getDossiers(user.getUserId(), company.getCompanyId(), groupId, params, null,
				-1, -1, serviceContext);
	
		long totalGroup = jsonDataGroup.getLong("total");
	//	JSONArray dossierArr = JSONFactoryUtil.createJSONArray();
		
		if (totalGroup > 0) {
			List<Document> lstDocuments = (List<Document>) jsonDataGroup.get("data");	
			for (Document document : lstDocuments) {
				long dossierId = GetterUtil.getLong(document.get(DossierTerm.DOSSIER_ID));
				long companyId = GetterUtil.getLong(document.get(Field.COMPANY_ID));
				String uid = document.get(Field.UID);
				Dossier oldDossier = DossierLocalServiceUtil.fetchDossier(dossierId);
				if (oldDossier == null) {
					try {
						indexer.delete(companyId, uid);
					} catch (SearchException e) {
						_log.error(e);
					}
				}
			}
		}
		
		return Response.status(200).entity("{}").build();
	}

	@Override
	public Response addDossierPublish(HttpServletRequest request, HttpHeaders header, Company company, Locale locale,
			User user, ServiceContext serviceContext, DossierPublishModel input) {

		long groupId = GetterUtil.getLong(header.getHeaderString("groupId"));

		try {
			Dossier dossier = CPSDossierBusinessLocalServiceUtil.addDossierPublish(groupId, company, user, serviceContext, DossierUtils.convertFormModelToPublishModel(input));
			
			return Response.status(200).entity(JSONFactoryUtil.looseSerializeDeep(dossier)).build();				
		} catch (Exception e) {
			return BusinessExceptionImpl.processException(e);
		}
	}

	@Override
	public Response getDossierBarcode(HttpServletRequest request, HttpHeaders header, Company company, Locale locale,
			User user, ServiceContext serviceContext, String id) {
		long groupId = GetterUtil.getLong(header.getHeaderString("groupId"));
		
		try {
			long dossierId = GetterUtil.getLong(id);
			
			Dossier dossier = DossierLocalServiceUtil.fetchDossier(dossierId);
			
			if (dossier == null) {
				dossier = DossierLocalServiceUtil.getByRef(groupId, id);
			}
			Code128 barcode = new Code128();
			barcode.setFontName("Monospaced");
			barcode.setFontSize(16);
			barcode.setModuleWidth(2);
			barcode.setBarHeight(50);
			barcode.setHumanReadableLocation(HumanReadableLocation.BOTTOM);
			barcode.setContent(dossier.getDossierNo());

			int width = barcode.getWidth();
			int height = barcode.getHeight();

			BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_BYTE_GRAY);
			Graphics2D g2d = image.createGraphics();
			Java2DRenderer renderer = new Java2DRenderer(g2d, 1, Color.WHITE, Color.BLACK);
			renderer.render(barcode);
			File destDir = new File("barcode");
			if (!destDir.exists()) {
				destDir.mkdir();
			}
			File file = new File("barcode/" + dossier.getDossierId() + ".png");
			if (!file.exists()) {
				file.createNewFile();				
			}

			if (file.exists()) {
				ImageIO.write(image, "png", file);
	//			String fileType = Files.probeContentType(file.toPath());
				ResponseBuilder responseBuilder = Response.ok((Object) file);

				responseBuilder.header("Content-Disposition",
						"attachment; filename=\"" + file.getName() + "\"");
				responseBuilder.header("Content-Type", "image/png");

				return responseBuilder.build();
			} else {
				return Response.status(HttpURLConnection.HTTP_NO_CONTENT).build();
			}

		} catch (Exception e) {
			return BusinessExceptionImpl.processException(e);
		}
	}

	@Override
	public Response getDossierQRcode(HttpServletRequest request, HttpHeaders header, Company company, Locale locale,
			User user, ServiceContext serviceContext, String id) {
		long groupId = GetterUtil.getLong(header.getHeaderString("groupId"));
		
		try {
			long dossierId = GetterUtil.getLong(id);
			
			Dossier dossier = DossierLocalServiceUtil.fetchDossier(dossierId);
			
			if (dossier == null) {
				dossier = DossierLocalServiceUtil.getByRef(groupId, id);
			}
			QrCode qrcode = new QrCode();
			qrcode.setHumanReadableLocation(HumanReadableLocation.BOTTOM);
			qrcode.setDataType(Symbol.DataType.HIBC);
			qrcode.setPreferredVersion(40);
			qrcode.setContent(dossier.getDossierNo());

			int width = qrcode.getWidth();
			int height = qrcode.getHeight();

			BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_BYTE_GRAY);
			Graphics2D g2d = image.createGraphics();
			Java2DRenderer renderer = new Java2DRenderer(g2d, 1, Color.WHITE, Color.BLACK);
			renderer.render(qrcode);
			File destDir = new File("barcode");
			if (!destDir.exists()) {
				destDir.mkdir();
			}
			File file = new File("barcode/" + dossier.getDossierId() + ".png");
			if (!file.exists()) {
				file.createNewFile();				
			}

			if (file.exists()) {
				ImageIO.write(image, "png", file);
	//			String fileType = Files.probeContentType(file.toPath());
				ResponseBuilder responseBuilder = Response.ok((Object) file);

				responseBuilder.header("Content-Disposition",
						"attachment; filename=\"" + file.getName() + "\"");
				responseBuilder.header("Content-Type", "image/png");

				return responseBuilder.build();
			} else {
				return Response.status(HttpURLConnection.HTTP_NO_CONTENT).build();
			}

		} catch (Exception e) {
			return BusinessExceptionImpl.processException(e);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public Response getDossierSyncsByDossier(HttpServletRequest request, HttpHeaders header, Company company,
			Locale locale, User user, ServiceContext serviceContext, String id, Integer info, Integer start, Integer end) {
		BackendAuth auth = new BackendAuthImpl();
		DossierSyncActions actions = new DossierSyncActionsImpl();
		
		try {

			if (!auth.isAuth(serviceContext)) {
				throw new UnauthenticationException();
			}
			if (start == null || start == 0) {
				start = -1;
				end = -1;
			}

			long groupId = GetterUtil.getLong(header.getHeaderString("groupId"));
			JSONObject jsonData = null;
			
			if (info == null) {
				jsonData = actions.getDossierSyncByDossiers(groupId, id, start, end, serviceContext);
			}
			else {
				jsonData = actions.getDossierSyncByDossierAndInfo(groupId, id, info, start, end, serviceContext);				
			}
			DossierSyncV21ResultsModel results = new DossierSyncV21ResultsModel();
			
			results.setTotal(jsonData.getInt("total"));
			if (jsonData != null && jsonData.getInt("total") > 0) {
				List<DossierSyncV21DataModel> lstDatas = new ArrayList<>();
				List<DossierSync> lstSyncs = (List<DossierSync>)jsonData.get("data");
				for (DossierSync ds : lstSyncs) {
					DossierSyncV21DataModel model = new DossierSyncV21DataModel();
					model.setActionCode(ds.getActionCode());
					model.setActionName(ds.getActionName());
					model.setActionNote(ds.getActionNote());
					model.setActionUser(ds.getActionUser());
					model.setCreateDate(ds.getCreateDate().getTime());
					model.setDossierId(ds.getDossierId());
					model.setDossierRefUid(ds.getDossierRefUid());
					model.setDossierSyncId(ds.getDossierSyncId());
					model.setInfoType(ds.getInfoType());
					model.setPayload(ds.getPayload());
					model.setSyncRefUid(ds.getSyncRefUid());
					model.setSyncType(ds.getSyncType());
					model.setUserId(ds.getUserId());
					
					lstDatas.add(model);
				}
				results.getData().addAll(lstDatas);
			}

			return Response.status(200).entity(results).build();

		} catch (Exception e) {
			return BusinessExceptionImpl.processException(e);
		}	
	}

	@SuppressWarnings("unchecked")
	@Override
	public Response getDossierRelaseList(HttpServletRequest request, HttpHeaders header, Company company, Locale locale,
			User user, ServiceContext serviceContext, DossierSearchModel query) {
		long groupId = GetterUtil.getLong(header.getHeaderString("groupId"));
		DossierActions actions = new DossierActionsImpl();
		long userId = user.getUserId();
		String emailLogin = user.getEmailAddress();
		_log.info("userId: "+userId);
		_log.info("emailLogin: "+emailLogin);

		try {
			if (Validator.isNull(query.getEnd()) || query.getEnd() == 0) {
				query.setStart(-1);
				query.setEnd(-1);
			}

			LinkedHashMap<String, Object> params = new LinkedHashMap<String, Object>();
			params.put(Field.GROUP_ID, String.valueOf(groupId));

			String step = query.getStep();
//			_log.info("step: "+step);
			StringBuilder strStatusStep = null;
			StringBuilder strSubStatusStep = null;
			if (Validator.isNotNull(step)) {
				strStatusStep = new StringBuilder();
				strSubStatusStep = new StringBuilder();
				String[] stepArr = step.split(StringPool.COMMA);
				if (stepArr != null && stepArr.length > 0) {
					String statusStep;
					String subStatusStep;
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

			String fromReleaseDate = APIDateTimeUtils.convertNormalDateToLuceneDate(query.getFromReleaseDate());
			String toReleaseDate = APIDateTimeUtils.convertNormalDateToLuceneDate(query.getToReleaseDate());

			params.put(DossierTerm.EMAIL_USER_LOGIN, emailLogin);
			params.put(DossierTerm.FROM_RELEASE_DATE, fromReleaseDate);
			params.put(DossierTerm.TO_RELEASE_DATE, toReleaseDate);
			//Process follow StepCode
			if (Validator.isNotNull(strStatusStep)) {
				params.put(DossierTerm.DOSSIER_STATUS_STEP, strStatusStep != null ? strStatusStep.toString() : StringPool.BLANK);
			} else {
				params.put(DossierTerm.DOSSIER_STATUS_STEP, StringPool.BLANK);
			}
			if (Validator.isNotNull(strSubStatusStep)) {
				params.put(DossierTerm.DOSSIER_SUBSTATUS_STEP, strSubStatusStep != null ? strSubStatusStep.toString() : StringPool.BLANK);
			} else {
				params.put(DossierTerm.DOSSIER_SUBSTATUS_STEP, StringPool.BLANK);
			}

			Sort[] sorts = null;
			if (Validator.isNull(query.getSort())) {
				sorts = new Sort[] { SortFactoryUtil.create(DossierTerm.CREATE_DATE + "_sortable", Sort.STRING_TYPE,
						GetterUtil.getBoolean(query.getOrder())) };
			} else {
				sorts = new Sort[] { SortFactoryUtil.create(query.getSort() + "_sortable", Sort.STRING_TYPE,
						GetterUtil.getBoolean(query.getOrder())) };
			}

			DossierResultPublishModel results = new DossierResultPublishModel();

			JSONObject jsonData = actions.getDossiers(user.getUserId(), company.getCompanyId(), groupId, params, sorts,
						query.getStart(), query.getEnd(), serviceContext);

			results.setTotal(jsonData.getInt("total"));

			results.getData().addAll(DossierUtils.mappingForGetPublishList((List<Document>) jsonData.get("data")));

			return Response.status(200).entity(results).build();

		} catch (Exception e) {
			return BusinessExceptionImpl.processException(e);
		}
	}

	private String sequenceOf(List<ProcessStep> lstSteps, String stepCode) {
		for (ProcessStep ps : lstSteps) {
			if (ps.getStepCode().equals(stepCode)) {
				return ps.getSequenceNo();
			}
		}
		
		return "";
	}
	
	@Override
	public Response getMermaidGraphDetailDossier(HttpServletRequest request, HttpHeaders header, Company company,
			Locale locale, User user, ServiceContext serviceContext, String id) {
		long groupId = GetterUtil.getLong(header.getHeaderString("groupId"));
		BackendAuth auth = new BackendAuthImpl();

		try {

			Dossier dossier = DossierUtils.getDossier(id, groupId);

			if (!auth.isAuth(serviceContext)) {
				throw new UnauthenticationException();
			}

			if (dossier != null && dossier.getDossierActionId() != 0) {
				DossierAction lastAction = DossierActionLocalServiceUtil.fetchDossierAction(dossier.getDossierActionId());
				ServiceProcess serviceProcess = ServiceProcessLocalServiceUtil.getServiceProcess(lastAction.getServiceProcessId());
				if (serviceProcess != null) {
					StringBuilder result = new StringBuilder();
					result.append("graph TD\n");
					List<ProcessSequence> lstSequences = ProcessSequenceLocalServiceUtil.getByServiceProcess(groupId, serviceProcess.getServiceProcessId());
					List<ProcessStep> lstSteps = ProcessStepLocalServiceUtil.getProcessStepbyServiceProcessId(serviceProcess.getServiceProcessId());
					
					for (ProcessSequence ps : lstSequences) {
						result.append(ps.getSequenceNo());
						result.append("(\"[");
						result.append(ps.getSequenceRole());
						result.append("] ");
						result.append(ps.getSequenceName());
						result.append("\")\n");
						for (ProcessSequence psOther : lstSequences) {
							List<String> arcs = new ArrayList<>();
							
							if (!psOther.getSequenceNo().equals(ps.getSequenceNo())) {
								List<ProcessAction> lstActions = ProcessActionLocalServiceUtil.getProcessActionbyServiceProcessId(serviceProcess.getServiceProcessId());
								for (ProcessAction pa : lstActions) {
									if (sequenceOf(lstSteps, pa.getPreStepCode()).equals(ps.getSequenceNo())
											&& sequenceOf(lstSteps, pa.getPostStepCode()).equals(psOther.getSequenceNo())
											&& !arcs.contains(pa.getActionName())) {
										result.append(ps.getSequenceNo());
										result.append("-->|\"");
										result.append(pa.getActionName());
										result.append("\"|");
										result.append(psOther.getSequenceNo());
										result.append("\n");
										if (lastAction.getStepCode().equals(pa.getPreStepCode())) {
											result.append("style " + ps.getSequenceNo() + " fill:#f9f,stroke:#333,stroke-width:4px");
											result.append("\n");
										}
										arcs.add(pa.getActionName());
									}
								}								
							}
						}
					}
					
					return Response.status(200).entity(result.toString()).build();				
				}
				else {
					return Response.status(200).entity(StringPool.BLANK).build();
				}				
			}
			else {
				return Response.status(200).entity(StringPool.BLANK).build();
			}
		} catch (Exception e) {
			_log.error(e);
			return BusinessExceptionImpl.processException(e);
		}		
	}

	@Override
	public Response getAssignUsers(HttpServletRequest request, HttpHeaders header, Company company, Locale locale,
			User user, ServiceContext serviceContext, String id) {
		long groupId = GetterUtil.getLong(header.getHeaderString("groupId"));
		BackendAuth auth = new BackendAuthImpl();
		DossierActions actions = new DossierActionsImpl();
		
		try {

			Dossier dossier = DossierUtils.getDossier(id, groupId);

			if (!auth.isAuth(serviceContext)) {
				throw new UnauthenticationException();
			}

			if (dossier != null && dossier.getDossierActionId() != 0) {
				JSONObject result = JSONFactoryUtil.createJSONObject();
				DossierAction da = DossierActionLocalServiceUtil.fetchDossierAction(dossier.getDossierActionId());
				ProcessStep ps = ProcessStepLocalServiceUtil.fetchBySC_GID(da.getStepCode(), groupId, da.getServiceProcessId());
				
				List<User> lstUsers = actions.getAssignUsersByStep(dossier, ps);
				result.put("total", lstUsers.size());
				JSONArray userArr = JSONFactoryUtil.createJSONArray();
				
				for (User u : lstUsers) {
					JSONObject userObj = JSONFactoryUtil.createJSONObject();
					userObj.put("userId", u.getUserId());
					//userObj.put("userName", u.getFullName());
					Employee employee = EmployeeLocalServiceUtil.fetchByF_mappingUserId(dossier.getGroupId(), u.getUserId());
					if (employee != null) {
						userObj.put("userName", employee.getFullName());
					} else {
						userObj.put("userName", u.getFullName());
					}
					DossierActionUserPK pk = new DossierActionUserPK();
					pk.setDossierActionId(dossier.getDossierActionId());
					pk.setUserId(u.getUserId());
					DossierActionUser dau = DossierActionUserLocalServiceUtil.fetchDossierActionUser(pk);
					if (dau != null) {
						userObj.put("assigned", dau.getAssigned());
					}
					else {
						userObj.put("assigned", 0);
					}
					
					userArr.put(userObj);
				}
				
				result.put("data", userArr);
				
				return Response.status(200).entity(result.toJSONString()).build();
			}
			else {
				return Response.status(200).entity(StringPool.BLANK).build();
			}
		} catch (Exception e) {
			_log.error(e);
			return BusinessExceptionImpl.processException(e);
		}			
	}

	@Override
	public Response assignUsers(HttpServletRequest request, HttpHeaders header, Company company, Locale locale,
			User user, ServiceContext serviceContext, String id, String assignUsers, Integer delegacy) {
		long groupId = GetterUtil.getLong(header.getHeaderString("groupId"));
		BackendAuth auth = new BackendAuthImpl();
		org.opencps.dossiermgt.action.DossierActionUser actions = new DossierActionUserImpl();
		
		try {

			Dossier dossier = DossierUtils.getDossier(id, groupId);

			if (!auth.isAuth(serviceContext)) {
				throw new UnauthenticationException();
			}
			if (dossier != null && dossier.getDossierActionId() != 0) {
				JSONArray assignUserArr = JSONFactoryUtil.createJSONArray(assignUsers);
				DossierAction da = DossierActionLocalServiceUtil.fetchDossierAction(dossier.getDossierActionId());
				
				actions.assignDossierActionUser(dossier, 1, da, user.getUserId(), groupId, 0, assignUserArr, delegacy);
				//Reindex dossier
				Indexer<Dossier> indexer = IndexerRegistryUtil
						.nullSafeGetIndexer(Dossier.class);
				indexer.reindex(dossier);
				
				return Response.status(200).entity(StringPool.BLANK).build();
			}
			else {
				return Response.status(200).entity(StringPool.BLANK).build();
			}
		} catch (Exception e) {
			_log.error(e);
			return BusinessExceptionImpl.processException(e);
		}					
	}

	@Override
	public Response refreshDossier(HttpServletRequest request, HttpHeaders header, Company company, Locale locale,
			User user, ServiceContext serviceContext, String id) {
		long groupId = GetterUtil.getLong(header.getHeaderString("groupId"));
		BackendAuth auth = new BackendAuthImpl();
		try {

			Dossier dossier = DossierUtils.getDossier(id, groupId);

			if (!auth.isAuth(serviceContext)) {
				throw new UnauthenticationException();
			}
			if (dossier != null && dossier.getDossierActionId() != 0) {
				publishEvent(dossier);
				return Response.status(200).entity(StringPool.BLANK).build();
			}
			else {
				return Response.status(200).entity(StringPool.BLANK).build();
			}
		} catch (Exception e) {
			_log.error(e);
			return BusinessExceptionImpl.processException(e);
		}					
	}
	
	private void publishEvent(Dossier dossier) {
		Message message = new Message();
		JSONObject msgData = JSONFactoryUtil.createJSONObject();

		message.put("msgToEngine", msgData);
		message.put("dossier", DossierMgtUtils.convertDossierToJSON(dossier));
		
		MessageBusUtil.sendMessage(DossierTerm.PUBLISH_DOSSIER_DESTINATION, message);	
		
		Message lgspMessage = new Message();
		JSONObject lgspMsgData = msgData;

		lgspMessage.put("msgToEngine", lgspMsgData);
		lgspMessage.put("dossier", DossierMgtUtils.convertDossierToJSON(dossier));
		
		MessageBusUtil.sendMessage(DossierTerm.LGSP_DOSSIER_DESTINATION, lgspMessage);	
	}

	@Override
	public Response fixDossierSync(HttpServletRequest request, HttpHeaders header, Company company, Locale locale,
			User user, ServiceContext serviceContext, String id) {
		long groupId = GetterUtil.getLong(header.getHeaderString("groupId"));
		BackendAuth auth = new BackendAuthImpl();
		try {

			Dossier dossier = DossierUtils.getDossier(id, groupId);

			if (!auth.isAuth(serviceContext)) {
				throw new UnauthenticationException();
			}
			if (dossier != null && dossier.getDossierActionId() != 0) {
				List<DossierAction> lstActions = DossierActionLocalServiceUtil.findByG_DID(groupId, dossier.getDossierId());
				ServiceProcess serviceProcess = null;
				
				for (DossierAction da : lstActions) {
					ActionConfig ac = ActionConfigLocalServiceUtil.getByCode(groupId, da.getActionCode());
					if (serviceProcess == null) {
						serviceProcess = ServiceProcessLocalServiceUtil.fetchServiceProcess(da.getServiceProcessId());
					}
					ProcessAction proAction = ProcessActionLocalServiceUtil.fetchByF_GID_SID_AC_PRE_POST(groupId, serviceProcess != null ? serviceProcess.getServiceProcessId() : 0l, da.getActionCode(), da.getFromStepCode(), da.getStepCode());
//					ProcessAction proAction = ProcessActionLocalServiceUtil.fetchBySPID_AC(serviceProcess != null ? serviceProcess.getServiceProcessId() : 0l, da.getActionCode());
					
					if (ac != null) {
						if (ac.getSyncType() > 0) {
							String dossierRefUid = dossier.getReferenceUid();
							String syncRefUid = UUID.randomUUID().toString();

							int state = DossierActionUtils.getSyncState(ac.getSyncType(), dossier);
							
							JSONObject payloadObject = JSONFactoryUtil.createJSONObject(da.getPayload());
							
							//Update payload
							
							JSONArray dossierFilesArr = JSONFactoryUtil.createJSONArray();
							if (ac.getSyncType() == DossierSyncTerm.SYNCTYPE_REQUEST) {
								if (dossier.getOriginDossierId() == 0) {
									List<DossierFile> lstFiles = DossierFileLocalServiceUtil.findByDID(dossier.getDossierId());
									if (lstFiles.size() > 0) {
										for (DossierFile df : lstFiles) {
											JSONObject dossierFileObj = JSONFactoryUtil.createJSONObject();
											dossierFileObj.put(DossierFileTerm.REFERENCE_UID, df.getReferenceUid());
											dossierFilesArr.put(dossierFileObj);
										}
									}					
								}
								else {
									ServiceConfig serviceConfig = ServiceConfigLocalServiceUtil.getBySICodeAndGAC(groupId, dossier.getServiceCode(), dossier.getGovAgencyCode());
									List<ProcessOption> lstOptions = ProcessOptionLocalServiceUtil.getByServiceProcessId(serviceConfig.getServiceConfigId());
									
									if (serviceConfig != null) {
										if (lstOptions.size() > 0) {
											ProcessOption processOption = lstOptions.get(0);
											
											DossierTemplate dossierTemplate = DossierTemplateLocalServiceUtil.fetchDossierTemplate(processOption.getDossierTemplateId());
											List<DossierPart> lstParts = DossierPartLocalServiceUtil.getByTemplateNo(groupId, dossierTemplate.getTemplateNo());
											
											List<DossierFile> lstFiles = DossierFileLocalServiceUtil.findByDID(dossier.getOriginDossierId());
											if (lstFiles.size() > 0) {
												for (DossierFile df : lstFiles) {
													boolean flagHslt = false;
													for (DossierPart dp : lstParts) {
														if (dp.getPartNo().equals(df.getDossierPartNo())) {
															flagHslt = true;
															break;
														}
													}
													if (flagHslt) {
														JSONObject dossierFileObj = JSONFactoryUtil.createJSONObject();
														dossierFileObj.put(DossierFileTerm.REFERENCE_UID, df.getReferenceUid());
														dossierFilesArr.put(dossierFileObj);										
													}
												}
											}										
										}
									}
									
								}
							}
							else {
								//Sync result files
								
							}
							
							payloadObject.put("dossierFiles", dossierFilesArr);
							
							if (Validator.isNotNull(proAction.getReturnDossierFiles())) {
								List<DossierFile> lsDossierFile = DossierFileLocalServiceUtil.findByDID(dossier.getDossierId());
								dossierFilesArr = JSONFactoryUtil.createJSONArray();

								// check return file
								List<String> returnDossierFileTemplateNos = ListUtil
										.toList(StringUtil.split(proAction.getReturnDossierFiles()));

								for (DossierFile dossierFile : lsDossierFile) {
									if (returnDossierFileTemplateNos.contains(dossierFile.getFileTemplateNo())) {
										JSONObject dossierFileObj = JSONFactoryUtil.createJSONObject();
										dossierFileObj.put(DossierFileTerm.REFERENCE_UID, dossierFile.getReferenceUid());
										dossierFilesArr.put(dossierFileObj);

									}

								}
								payloadObject.put("dossierFiles", dossierFilesArr);				
							}
							
							List<DossierDocument> lstDossierDocuments = DossierDocumentLocalServiceUtil.getDossierDocumentList(dossier.getDossierId(), QueryUtil.ALL_POS, QueryUtil.ALL_POS);
							JSONArray dossierDocumentArr = JSONFactoryUtil.createJSONArray();

							for (DossierDocument dossierDocument : lstDossierDocuments) {
								JSONObject dossierDocumentObj = JSONFactoryUtil.createJSONObject();
								dossierDocumentObj.put(DossierDocumentTerm.REFERENCE_UID, dossierDocument.getReferenceUid());
								dossierDocumentArr.put(dossierDocumentObj);
							}
							payloadObject.put("dossierFiles", dossierFilesArr);				
							payloadObject.put("dossierDocuments", dossierDocumentArr);
							
							//Put dossier note
							payloadObject.put(DossierTerm.DOSSIER_NOTE, dossier.getDossierNote());
							
							DossierSyncLocalServiceUtil.updateDossierSync(groupId, da.getUserId(), dossier.getDossierId(), dossierRefUid, syncRefUid,
									da.getDossierActionId(), da.getActionCode(), da.getActionName(), da.getActionUser(), da.getActionNote(),
									ac.getSyncType(), ac.getInfoType(), payloadObject.toJSONString(), serviceProcess.getServerNo(), state);
							
							if (state == DossierSyncTerm.STATE_NOT_SYNC
									&& ac != null && ac.getEventType() == ActionConfigTerm.EVENT_TYPE_SENT) {
								publishEvent(dossier);
							}
							
						}
					}
				}
				return Response.status(200).entity(StringPool.BLANK).build();
			}
			else {
				return Response.status(200).entity(StringPool.BLANK).build();
			}
		} catch (Exception e) {
			_log.error(e);
			return BusinessExceptionImpl.processException(e);
		}							
	}

	@Override
	public Response resyncDossier(HttpServletRequest request, HttpHeaders header, Company company, Locale locale,
			User user, ServiceContext serviceContext, String id) {
		long groupId = GetterUtil.getLong(header.getHeaderString("groupId"));
		BackendAuth auth = new BackendAuthImpl();
		try {

			Dossier dossier = DossierUtils.getDossier(id, groupId);

			if (!auth.isAuth(serviceContext)) {
				throw new UnauthenticationException();
			}
			
			List<Role> userRoles = user.getRoles();
			boolean isAdmin = false;
			for (Role r : userRoles) {
				if (r.getName().startsWith("Administrator")) {
					isAdmin = true;
					break;
				}
			}
			
			if (!isAdmin) {
				throw new UnauthenticationException();
			}

			if (dossier != null && dossier.getDossierActionId() != 0) {	
				List<DossierSync> lstSyncs = DossierSyncLocalServiceUtil.findByG_DID_ST(groupId, dossier.getDossierId(), DossierSyncTerm.STATE_ACK_ERROR);
				for (DossierSync ds : lstSyncs) {
					ds.setState(DossierSyncTerm.STATE_WAITING_SYNC);
					ds.setRetry(0);
					
					DossierSyncLocalServiceUtil.updateDossierSync(ds);
				}
			}
			
			return Response.status(200).entity(StringPool.BLANK).build();
		} catch (Exception e) {
			_log.error(e);
			return BusinessExceptionImpl.processException(e);
		}				
	}

	@Override
	public Response forceResyncDossier(HttpServletRequest request, HttpHeaders header, Company company, Locale locale,
			User user, ServiceContext serviceContext, String id) {
		long groupId = GetterUtil.getLong(header.getHeaderString("groupId"));
		BackendAuth auth = new BackendAuthImpl();
		try {

			Dossier dossier = DossierUtils.getDossier(id, groupId);

			if (!auth.isAuth(serviceContext)) {
				throw new UnauthenticationException();
			}
			
			List<Role> userRoles = user.getRoles();
			boolean isAdmin = false;
			for (Role r : userRoles) {
				if (r.getName().startsWith("Administrator")) {
					isAdmin = true;
					break;
				}
			}
			
			if (!isAdmin) {
				throw new UnauthenticationException();
			}

			if (dossier != null && dossier.getDossierActionId() != 0) {	
				List<DossierSync> lstSyncs = DossierSyncLocalServiceUtil.findByG_DID(groupId, dossier.getDossierId());
				
				for (DossierSync ds : lstSyncs) {
					ds.setState(DossierSyncTerm.STATE_WAITING_SYNC);
					ds.setRetry(0);
					
					DossierSyncLocalServiceUtil.updateDossierSync(ds);
				}
			}
			
			return Response.status(200).entity(StringPool.BLANK).build();
		} catch (Exception e) {
			_log.error(e);
			return BusinessExceptionImpl.processException(e);
		}				
	}

	@Override
	public Response restoreDossier(HttpServletRequest request, HttpHeaders header, Company company, Locale locale,
			User user, ServiceContext serviceContext, String id) {
		long groupId = GetterUtil.getLong(header.getHeaderString("groupId"));
		BackendAuth auth = new BackendAuthImpl();

		try {
			if (!auth.isAuth(serviceContext)) {
				throw new UnauthenticationException();
			}

			List<Role> userRoles = user.getRoles();
			boolean isAdmin = false;
			for (Role r : userRoles) {
				if (r.getName().startsWith("Administrator")) {
					isAdmin = true;
					break;
				}
			}
			
			if (!isAdmin) {
				throw new UnauthenticationException();
			}

			Dossier dossier = DossierUtils.getDossier(id, groupId);
			if (dossier != null) {
				int originality = dossier.getOriginality();
				Dossier removeDossier = null;
				if (originality < 0) {
					dossier.setOriginality(-originality);
					removeDossier = DossierLocalServiceUtil.updateDossier(dossier);
				}

				DossierDetailModel result = null;
				if (removeDossier != null) {
					result = DossierUtils.mappingForGetDetail(removeDossier, user.getUserId());
				}
				return Response.status(200).entity(result).build();
			} else {
				return Response.status(HttpServletResponse.SC_FORBIDDEN).entity("No find dossier to restore").build();
			}
		} catch (Exception e) {
			return BusinessExceptionImpl.processException(e);
		}
	}

	@Override
	public Response republishPublishQueue(HttpServletRequest request, HttpHeaders header, Company company,
			Locale locale, User user, ServiceContext serviceContext) {
		long groupId = GetterUtil.getLong(header.getHeaderString("groupId"));
		BackendAuth auth = new BackendAuthImpl();

		try {
			if (!auth.isAuth(serviceContext)) {
				throw new UnauthenticationException();
			}
			
			List<Role> userRoles = user.getRoles();
			boolean isAdmin = false;
			for (Role r : userRoles) {
				if (r.getName().startsWith("Administrator")) {
					isAdmin = true;
					break;
				}
			}
			
			if (!isAdmin) {
				throw new UnauthenticationException();
			}

			List<PublishQueue> lstPqs = PublishQueueLocalServiceUtil.getByStatus(PublishQueueTerm.STATE_ACK_ERROR, QueryUtil.ALL_POS, QueryUtil.ALL_POS);
			for (PublishQueue pq : lstPqs) {
				if (groupId == 0) {
					pq.setStatus(PublishQueueTerm.STATE_WAITING_SYNC);
					pq.setRetry(0);
					PublishQueueLocalServiceUtil.updatePublishQueue(pq);					
				}
				else if (pq.getGroupId() == groupId) {
					pq.setStatus(PublishQueueTerm.STATE_WAITING_SYNC);
					pq.setRetry(0);
					PublishQueueLocalServiceUtil.updatePublishQueue(pq);					
				}
			}
			
			return Response.status(200).entity(StringPool.BLANK).build();			
		} catch (Exception e) {
			return BusinessExceptionImpl.processException(e);
		}
	}

	@Override
	public Response getDossiersByErrorStep(HttpServletRequest request, HttpHeaders header, Company company,
			Locale locale, User user, ServiceContext serviceContext) {
		long groupId = GetterUtil.getLong(header.getHeaderString("groupId"));
		BackendAuth auth = new BackendAuthImpl();

		try {
			if (!auth.isAuth(serviceContext)) {
				throw new UnauthenticationException();
			}
			
			List<Role> userRoles = user.getRoles();
			boolean isAdmin = false;
			for (Role r : userRoles) {
				if (r.getName().startsWith("Administrator")) {
					isAdmin = true;
					break;
				}
			}
			
			if (!isAdmin) {
				throw new UnauthenticationException();
			}

			List<Dossier> lstDossiers = DossierLocalServiceUtil.findDossierByGroup(groupId);
			List<Dossier> errorDossiers = new ArrayList<>();
			
			for (Dossier d : lstDossiers) {
				if (d.getDossierActionId() != 0) {
					DossierAction lastDa = DossierActionLocalServiceUtil.fetchDossierAction(d.getDossierActionId());
					if (lastDa != null) {
						String stepCode = lastDa.getStepCode();
						ProcessStep step = ProcessStepLocalServiceUtil.fetchBySC_GID(stepCode, groupId, lastDa.getServiceProcessId());
						if (step == null) {
							errorDossiers.add(d);
						}
					}
				}
			}
			
			DossierResultsModel results = new DossierResultsModel();
			results.setTotal(errorDossiers.size());
			
			for (Dossier d : errorDossiers) {
				DossierDataModel model = new DossierDataModel();
				model.setDossierId((int)d.getDossierId());
				model.setAddress(d.getAddress());
				model.setReferenceUid(d.getReferenceUid());
				model.setServiceCode(d.getServiceCode());
				model.setServiceName(d.getServiceName());
				
				results.getData().add(model);
			}
			return Response.status(200).entity(results).build();			
		} catch (Exception e) {
			return BusinessExceptionImpl.processException(e);
		}
	}

	@Override
	public Response checkStep(HttpServletRequest request, HttpHeaders header, Company company, Locale locale, User user,
			ServiceContext serviceContext, String id) {
		long groupId = GetterUtil.getLong(header.getHeaderString("groupId"));
		BackendAuth auth = new BackendAuthImpl();

		try {
			if (!auth.isAuth(serviceContext)) {
				throw new UnauthenticationException();
			}
			
			List<Role> userRoles = user.getRoles();
			boolean isAdmin = false;
			for (Role r : userRoles) {
				if (r.getName().startsWith("Administrator")) {
					isAdmin = true;
					break;
				}
			}
			
			if (!isAdmin) {
				throw new UnauthenticationException();
			}

			Dossier dossier = DossierUtils.getDossier(id, groupId);
			if (dossier != null) {
				if (dossier.getDossierActionId() != 0) {
					DossierAction lastDa = DossierActionLocalServiceUtil.fetchDossierAction(dossier.getDossierActionId());
					if (lastDa != null) {
						String stepCode = lastDa.getStepCode();
						ProcessStep step = ProcessStepLocalServiceUtil.fetchBySC_GID(stepCode, groupId, lastDa.getServiceProcessId());
						do {
							if (step == null) {
								DossierActionLocalServiceUtil.updateState(lastDa.getDossierActionId(), DossierActionTerm.STATE_ROLLBACK);
								
								DossierAction previousAction = DossierActionLocalServiceUtil.fetchDossierAction(lastDa.getPreviousActionId());
								if (previousAction != null) {
									DossierActionLocalServiceUtil.updateState(previousAction.getDossierActionId(), DossierActionTerm.STATE_WAITING_PROCESSING);
									try {
										DossierActionLocalServiceUtil.updateNextActionId(previousAction.getDossierActionId(), 0);
										dossier.setDossierActionId(previousAction.getDossierActionId());
										_log.info("Dossier action id: " + dossier.getDossierActionId());
									} catch (PortalException e) {
										return BusinessExceptionImpl.processException(e);
									}
								}	
								else {
									lastDa = DossierActionLocalServiceUtil.fetchDossierAction(dossier.getDossierActionId());
									stepCode = lastDa.getStepCode();
									step = ProcessStepLocalServiceUtil.fetchBySC_GID(stepCode, groupId, lastDa.getServiceProcessId());
									break;
								}
							}
							else {
								break;
							}
							
							lastDa = DossierActionLocalServiceUtil.fetchDossierAction(dossier.getDossierActionId());
							stepCode = lastDa.getStepCode();
							step = ProcessStepLocalServiceUtil.fetchBySC_GID(stepCode, groupId, lastDa.getServiceProcessId());
						}
						while (step == null);
						
						_log.info("Dossier step: " + step);
						if (step != null) {
							JSONObject jsonDataStatusText = getStatusText(dossier.getGroupId(), "DOSSIER_STATUS", step.getDossierStatus(), step.getDossierSubStatus());

							dossier.setDossierStatus(step.getDossierStatus());
							dossier.setDossierStatusText(jsonDataStatusText != null ? jsonDataStatusText.getString(step.getDossierStatus()) : StringPool.BLANK);
							dossier.setDossierSubStatus(step.getDossierSubStatus());
							dossier.setDossierSubStatusText(jsonDataStatusText != null ? jsonDataStatusText.getString(step.getDossierSubStatus()) : StringPool.BLANK);
							
							DossierLocalServiceUtil.updateDossier(dossier);							
						}
					}
				}
				
				return Response.status(200).entity(StringPool.BLANK).build();
			} else {
				return Response.status(HttpServletResponse.SC_FORBIDDEN).entity("No find dossier to check step").build();
			}		
		} catch (Exception e) {
			return BusinessExceptionImpl.processException(e);
		}		
	}	
	
	private JSONObject getStatusText(long groupId, String collectionCode, String curStatus, String curSubStatus) {

		JSONObject jsonData = null;
		DictCollection dc = DictCollectionLocalServiceUtil.fetchByF_dictCollectionCode(collectionCode, groupId);

		if (Validator.isNotNull(dc) && Validator.isNotNull(curStatus)) {
			jsonData = JSONFactoryUtil.createJSONObject();
			DictItem it = DictItemLocalServiceUtil.fetchByF_dictItemCode(curStatus, dc.getPrimaryKey(), groupId);
			if (Validator.isNotNull(it)) {
				jsonData.put(curStatus, it.getItemName());
				if (Validator.isNotNull(curSubStatus)) {
					DictItem dItem = DictItemLocalServiceUtil.fetchByF_dictItemCode(curSubStatus, dc.getPrimaryKey(),
							groupId);
					if (Validator.isNotNull(dItem)) {
						jsonData.put(curSubStatus, dItem.getItemName());
					}
				}
			}
		}

		return jsonData;
	}

	@Override
	public Response gotoStep(HttpServletRequest request, HttpHeaders header, Company company, Locale locale, User user,
			ServiceContext serviceContext, String id, String stepCode) {
		long groupId = GetterUtil.getLong(header.getHeaderString("groupId"));
		BackendAuth auth = new BackendAuthImpl();

		try {
			if (!auth.isAuth(serviceContext)) {
				throw new UnauthenticationException();
			}
			
			List<Role> userRoles = user.getRoles();
			boolean isAdmin = false;
			for (Role r : userRoles) {
				if (r.getName().startsWith("Administrator")) {
					isAdmin = true;
					break;
				}
			}
			
			if (!isAdmin) {
				throw new UnauthenticationException();
			}

			Dossier dossier = DossierUtils.getDossier(id, groupId);
			if (dossier != null) {
				Date now = new Date();
				if (dossier.getDossierActionId() != 0) {
					DossierAction lastda = DossierActionLocalServiceUtil.fetchDossierAction(dossier.getDossierActionId());
					if (lastda != null) {
						long serviceProcessId = lastda.getServiceProcessId();
						ProcessStep step = ProcessStepLocalServiceUtil.fetchBySC_GID(stepCode, groupId, serviceProcessId);
						if (step != null) {
							JSONObject jsonDataStatusText = getStatusText(dossier.getGroupId(), "DOSSIER_STATUS", step.getDossierStatus(), step.getDossierSubStatus());

							dossier.setDossierStatus(step.getDossierStatus());
							dossier.setDossierStatusText(jsonDataStatusText != null ? jsonDataStatusText.getString(step.getDossierStatus()) : StringPool.BLANK);
							dossier.setDossierSubStatus(step.getDossierSubStatus());
							dossier.setDossierSubStatusText(jsonDataStatusText != null ? jsonDataStatusText.getString(step.getDossierSubStatus()) : StringPool.BLANK);
							
							String curStatus = step.getDossierStatus();
							boolean stateProcessed = false;
							
							if (DossierTerm.DOSSIER_STATUS_RELEASING.equals(curStatus)
									|| DossierTerm.DOSSIER_STATUS_DENIED.equals(curStatus)
									|| DossierTerm.DOSSIER_STATUS_UNRESOLVED.equals(curStatus)
									|| DossierTerm.DOSSIER_STATUS_CANCELLED.equals(curStatus)
									|| DossierTerm.DOSSIER_STATUS_DONE.equals(curStatus)) {
								if (Validator.isNull(dossier.getReleaseDate())) {
									dossier.setReleaseDate(now);
									stateProcessed = true;
								}
							}
							if (DossierTerm.DOSSIER_STATUS_DENIED.equals(curStatus)
									|| DossierTerm.DOSSIER_STATUS_UNRESOLVED.equals(curStatus)
									|| DossierTerm.DOSSIER_STATUS_CANCELLED.equals(curStatus)
									|| DossierTerm.DOSSIER_STATUS_DONE.equals(curStatus)) {
								if (Validator.isNull(dossier.getFinishDate())) {
									if (Validator.isNull(dossier.getReleaseDate())) {
										dossier.setReleaseDate(now);
									}
									dossier.setFinishDate(now);			
								}
							}

							DossierLocalServiceUtil.updateDossier(dossier);							
							
							DossierAction dossierAction = DossierActionLocalServiceUtil.updateDossierAction(groupId, 0, dossier.getDossierId(),
									serviceProcessId, dossier.getDossierActionId(), 
									lastda.getStepCode(), lastda.getStepName(), lastda.getSequenceNo(),
									"9999", user.getFullName(), "Chuyển dịch đặc biệt", StringPool.BLANK, 0,
									stepCode, step.getStepName(), 
									step.getSequenceNo(),
									null, 0l, StringPool.BLANK, step.getStepInstruction(), 
									DossierActionTerm.STATE_WAITING_PROCESSING, DossierActionTerm.EVENT_STATUS_NOT_CREATED,
									serviceContext);
							
							lastda.setNextActionId(dossierAction.getDossierActionId());
							DossierActionLocalServiceUtil.updateDossierAction(lastda);
							DossierActionLocalServiceUtil.updateState(lastda.getDossierActionId(), DossierActionTerm.STATE_ALREADY_PROCESSED);					
							
							if (stateProcessed) {
								DossierActionLocalServiceUtil.updateState(dossierAction.getDossierActionId(), DossierActionTerm.STATE_ALREADY_PROCESSED);													
							}
							DossierDetailModel model = new DossierDetailModel();
							model.setDossierId((int)dossier.getDossierId());
							model.setReferenceUid(dossier.getReferenceUid());
							
							if (dossier.getDossierActionId() != 0) {
								publishEvent(dossier);
							}
							
							DossierMgtUtils.processSyncGotoDossier(dossier, stepCode);
							
							return Response.status(HttpServletResponse.SC_OK).entity(model).build();
						}
						else {
							return Response.status(HttpServletResponse.SC_BAD_REQUEST).entity("Not found step!").build();							
						}
					}
				}
				return Response.status(HttpServletResponse.SC_BAD_REQUEST).entity("Dossier is not in process!").build();
			} else {
				return Response.status(HttpServletResponse.SC_FORBIDDEN).entity("No find dossier to check step").build();
			}		
		} catch (Exception e) {
			return BusinessExceptionImpl.processException(e);
		}			
	}

	@Override
	public Response reindexDossier(HttpServletRequest request, HttpHeaders header, Company company, Locale locale,
			User user, ServiceContext serviceContext, String id) {
		long groupId = GetterUtil.getLong(header.getHeaderString("groupId"));
		BackendAuth auth = new BackendAuthImpl();

		try {
			if (!auth.isAuth(serviceContext)) {
				throw new UnauthenticationException();
			}
			List<Role> userRoles = user.getRoles();
			boolean isAdmin = false;
			for (Role r : userRoles) {
				if (r.getName().startsWith("Administrator")) {
					isAdmin = true;
					break;
				}
			}
			
			if (!isAdmin) {
				throw new UnauthenticationException();
			}
			Dossier dossier = DossierUtils.getDossier(id, groupId);
			Indexer<Dossier> indexer = IndexerRegistryUtil
					.nullSafeGetIndexer(Dossier.class);
			indexer.reindex(dossier);
			
			DossierDetailModel result = DossierUtils.mappingForGetDetail(dossier, user.getUserId());

			return Response.status(200).entity(result).build();

		} catch (Exception e) {
			return BusinessExceptionImpl.processException(e);
		}		
	}

	@Override
	public Response fixDueDateDossier(HttpServletRequest request, HttpHeaders header, Company company, Locale locale,
			User user, ServiceContext serviceContext, String id, String strReceiveDate) {

		long groupId = GetterUtil.getLong(header.getHeaderString("groupId"));
		BackendAuth auth = new BackendAuthImpl();
		long VALUE_CONVERT_HOUR_TIMESTAMP = 1000 * 60 * 60;

		try {
			if (!auth.isAuth(serviceContext)) {
				throw new UnauthenticationException();
			}
			List<Role> userRoles = user.getRoles();
			boolean isAdmin = false;
			for (Role r : userRoles) {
				if (r.getName().startsWith("Administrator")) {
					isAdmin = true;
					break;
				}
			}
			
			if (!isAdmin) {
				throw new UnauthenticationException();
			}

			long dossierId = GetterUtil.getLong(id);
			Dossier dossier = null;
			if (dossierId > 0) {
				dossier = DossierLocalServiceUtil.fetchDossier(dossierId);
			} else {
				dossier = DossierLocalServiceUtil.getByDossierNo(groupId, id);
			}
			if (dossier != null) {
				Date receiveDate = dossier.getReceiveDate();
				Date dueDate = null;
				if (receiveDate != null) {
					String processNo = StringPool.BLANK;
					ServiceProcess process = null;
					//Process DueDate of dossier
					if (Validator.isNotNull(processNo)) {
						process = ServiceProcessLocalServiceUtil.getByG_PNO(groupId, processNo);
						if (process != null) {
							dueDate = HolidayUtils.getDueDate(receiveDate, process.getDurationCount(),
									process.getDurationUnit(), groupId);
							if (dueDate != null) {
								dossier.setDueDate(dueDate);
								//
								DossierLocalServiceUtil.updateDossier(dossier);
							}
						}
						//Process dueDate of dossierAction
						long dossierActionId = dossier.getDossierActionId();
						if (dossierActionId > 0) {
							DossierAction dAction = DossierActionLocalServiceUtil.fetchDossierAction(dossierActionId);
							if (dAction != null && process != null) {
								String stepCode = dAction.getStepCode();
								if (Validator.isNotNull(stepCode)) {
									ProcessStep step = ProcessStepLocalServiceUtil.fetchBySC_GID(stepCode, groupId,
											process.getServiceProcessId());
									if (step != null) {
										double durationCountStep = step.getDurationCount();
										int durationUnit = process.getDurationUnit();
										long durationHour = 0;
										boolean checkDueDate = true;
										if (durationCountStep > 0) {
											if (durationUnit == 0) {
												durationHour = (long) durationCountStep * 8;
											} else {
												durationHour = (long) durationCountStep;
											}
											
											long createDateTimeStamp = dAction.getCreateDate().getTime();
											long dueDateActionTimeStamp = dAction.getDueDate() != null ? dAction.getDueDate().getTime() : 0;
											if (dueDateActionTimeStamp > 0) {
												checkDueDate = (dueDateActionTimeStamp - createDateTimeStamp
														- durationHour * VALUE_CONVERT_HOUR_TIMESTAMP) < 0 ? true
																: false;
											}
										} else {
											checkDueDate = false;
										}
										if (checkDueDate) {
											Date dueDateAction = HolidayUtils.getDueDate(dAction.getCreateDate(), step.getDurationCount(),
													process.getDurationUnit(), groupId);
											if (dueDateAction != null) {
												dAction.setDueDate(dueDateAction);
												//
												DossierActionLocalServiceUtil.updateDossierAction(dAction);
											}
										}
									}
								}
							}
						}
					} else {
						long dossierActionId = dossier.getDossierActionId();
						if (dossierActionId > 0) {
							DossierAction dAction = DossierActionLocalServiceUtil.fetchDossierAction(dossierActionId);
							if (dAction != null) {
								process = ServiceProcessLocalServiceUtil.fetchServiceProcess(dAction.getServiceProcessId());
								if (process != null) {
									dueDate = HolidayUtils.getDueDate(receiveDate, process.getDurationCount(),
											process.getDurationUnit(), groupId);
									if (dueDate != null) {
										dossier.setDueDate(dueDate);
										//
										DossierLocalServiceUtil.updateDossier(dossier);
									}
								}
								//Update DueDate Action
								String stepCode = dAction.getStepCode();
								if (Validator.isNotNull(stepCode) && process != null) {
									ProcessStep step = ProcessStepLocalServiceUtil.fetchBySC_GID(stepCode, groupId,
											process.getServiceProcessId());
									if (step != null) {
										double durationCountStep = step.getDurationCount();
										int durationUnit = process.getDurationUnit();
										long durationHour = 0;
										boolean checkDueDate = true;
										if (durationCountStep > 0) {
											if (durationUnit == 0) {
												durationHour = (long) durationCountStep * 8;
											} else {
												durationHour = (long) durationCountStep;
											}
											
											long createDateTimeStamp = dAction.getCreateDate().getTime();
											long dueDateActionTimeStamp = dAction.getDueDate() != null ? dAction.getDueDate().getTime() : 0;
											if (dueDateActionTimeStamp > 0) {
												checkDueDate = (dueDateActionTimeStamp - createDateTimeStamp
														- durationHour * VALUE_CONVERT_HOUR_TIMESTAMP) < 0 ? true
																: false;
											}
										} else {
											checkDueDate = false;
										}
										if (checkDueDate) {
											Date dueDateAction = HolidayUtils.getDueDate(dAction.getCreateDate(), step.getDurationCount(),
													process.getDurationUnit(), groupId);
											if (dueDateAction != null) {
												dAction.setDueDate(dueDateAction);
												//
												DossierActionLocalServiceUtil.updateDossierAction(dAction);
											}
										}
									}
								}
							}
						}
					}
				} else {
					receiveDate = APIDateTimeUtils.convertStringToDate(strReceiveDate, APIDateTimeUtils._NORMAL_PARTTERN);
					if (receiveDate != null) {
						String processNo = StringPool.BLANK;
						ServiceProcess process = null;
						//Process DueDate of dossier
						if (Validator.isNotNull(processNo)) {
							process = ServiceProcessLocalServiceUtil.getByG_PNO(groupId, processNo);
							if (process != null) {
								dueDate = HolidayUtils.getDueDate(receiveDate, process.getDurationCount(),
										process.getDurationUnit(), groupId);
								if (dueDate != null) {
									dossier.setDueDate(dueDate);
									//
									DossierLocalServiceUtil.updateDossier(dossier);
								}
							}
							//Process dueDate of dossierAction
							long dossierActionId = dossier.getDossierActionId();
							if (dossierActionId > 0) {
								DossierAction dAction = DossierActionLocalServiceUtil.fetchDossierAction(dossierActionId);
								if (dAction != null) {
									String stepCode = dAction.getStepCode();
									if (Validator.isNotNull(stepCode) && process != null) {
										ProcessStep step = ProcessStepLocalServiceUtil.fetchBySC_GID(stepCode, groupId,
												process.getServiceProcessId());
										if (step != null) {
											double durationCountStep = step.getDurationCount();
											int durationUnit = process.getDurationUnit();
											long durationHour = 0;
											boolean checkDueDate = true;
											if (durationCountStep > 0) {
												if (durationUnit == 0) {
													durationHour = (long) durationCountStep * 8;
												} else {
													durationHour = (long) durationCountStep;
												}
												
												long createDateTimeStamp = dAction.getCreateDate().getTime();
												long dueDateActionTimeStamp = dAction.getDueDate() != null ? dAction.getDueDate().getTime() : 0;
												if (dueDateActionTimeStamp > 0) {
													checkDueDate = (dueDateActionTimeStamp - createDateTimeStamp
															- durationHour * VALUE_CONVERT_HOUR_TIMESTAMP) < 0 ? true
																	: false;
												}
											} else {
												checkDueDate = false;
											}
											if (checkDueDate) {
												Date dueDateAction = HolidayUtils.getDueDate(dAction.getCreateDate(), step.getDurationCount(),
														process.getDurationUnit(), groupId);
												if (dueDateAction != null) {
													dAction.setDueDate(dueDateAction);
													//
													DossierActionLocalServiceUtil.updateDossierAction(dAction);
												}
											}
										}
									}
								}
							}
						} else {
							long dossierActionId = dossier.getDossierActionId();
							if (dossierActionId > 0) {
								DossierAction dAction = DossierActionLocalServiceUtil.fetchDossierAction(dossierActionId);
								if (dAction != null) {
									process = ServiceProcessLocalServiceUtil.fetchServiceProcess(dAction.getServiceProcessId());
									if (process != null) {
										dueDate = HolidayUtils.getDueDate(receiveDate, process.getDurationCount(),
												process.getDurationUnit(), groupId);
										if (dueDate != null) {
											dossier.setDueDate(dueDate);
											//
											DossierLocalServiceUtil.updateDossier(dossier);
										}
									}
									//Update DueDate Action
									String stepCode = dAction.getStepCode();
									if (Validator.isNotNull(stepCode)) {
										ProcessStep step = ProcessStepLocalServiceUtil.fetchBySC_GID(stepCode, groupId,
												process.getServiceProcessId());
										if (step != null) {
											double durationCountStep = step.getDurationCount();
											int durationUnit = process.getDurationUnit();
											long durationHour = 0;
											boolean checkDueDate = true;
											if (durationCountStep > 0) {
												if (durationUnit == 0) {
													durationHour = (long) durationCountStep * 8;
												} else {
													durationHour = (long) durationCountStep;
												}
												
												long createDateTimeStamp = dAction.getCreateDate().getTime();
												long dueDateActionTimeStamp = dAction.getDueDate() != null ? dAction.getDueDate().getTime() : 0;
												if (dueDateActionTimeStamp > 0) {
													checkDueDate = (dueDateActionTimeStamp - createDateTimeStamp
															- durationHour * VALUE_CONVERT_HOUR_TIMESTAMP) < 0 ? true
																	: false;
												}
											} else {
												checkDueDate = false;
											}
											if (checkDueDate) {
												Date dueDateAction = HolidayUtils.getDueDate(dAction.getCreateDate(), step.getDurationCount(),
														process.getDurationUnit(), groupId);
												if (dueDateAction != null) {
													dAction.setDueDate(dueDateAction);
													//
													DossierActionLocalServiceUtil.updateDossierAction(dAction);
												}
											}
										}
									}
								}
							}
						}
					}
				}
				//
				DossierDetailModel result = DossierUtils.mappingForGetDetail(dossier, user.getUserId());
				return Response.status(200).entity(result).build();
			} else {
				return Response.status(500).entity("Không update được dueDate của hồ sơ").build();
			}

		} catch (Exception e) {
			return BusinessExceptionImpl.processException(e);
		}		
	}

	@Override
	public Response fixReAssignedDossier(HttpServletRequest request, HttpHeaders header, Company company, Locale locale,
			User user, ServiceContext serviceContext, String id, String fromEmailUser, String toEmailUser) {
		long groupId = GetterUtil.getLong(header.getHeaderString("groupId"));
		BackendAuth auth = new BackendAuthImpl();
		org.opencps.dossiermgt.action.DossierActionUser actions = new DossierActionUserImpl();

		try {

			long dossierId = GetterUtil.getLong(id);
			Dossier dossier = null;
			if (dossierId > 0) {
				dossier = DossierLocalServiceUtil.fetchDossier(dossierId);
			} else {
				dossier = DossierLocalServiceUtil.getByDossierNo(groupId, id);
			}

			if (!auth.isAuth(serviceContext)) {
				throw new UnauthenticationException();
			}
			List<Role> userRoles = user.getRoles();
			boolean isAdmin = false;
			for (Role r : userRoles) {
				if (r.getName().startsWith("Administrator")) {
					isAdmin = true;
					break;
				}
			}
			
			if (!isAdmin) {
				throw new UnauthenticationException();
			}

			if (dossier != null && dossier.getDossierActionId() != 0) {
				User fromUser = UserLocalServiceUtil.fetchUserByEmailAddress(dossier.getCompanyId(), fromEmailUser);
				if (fromUser != null) {
					List<DossierActionUser> fromActionUserList = DossierActionUserLocalServiceUtil.getByDOSSIER_UID(dossier.getDossierId(),
							fromUser.getUserId());
					if (fromActionUserList != null && fromActionUserList.size() > 0) {
						DossierActionUser fromActUser = fromActionUserList.get(0);
						if (fromActUser != null) {
							User toUser = UserLocalServiceUtil.fetchUserByEmailAddress(dossier.getCompanyId(), toEmailUser);
							if (toUser != null) {
								//CreateDossierActionUser
								DossierActionUser toActUser = new org.opencps.dossiermgt.model.impl.DossierActionUserImpl();
								toActUser.setDossierActionId(fromActUser.getDossierActionId());
								toActUser.setUserId(toUser.getUserId());
								toActUser.setModerator(fromActUser.getModerator());
								toActUser.setAssigned(fromActUser.getAssigned());
								toActUser.setVisited(fromActUser.getVisited());
								toActUser.setDossierId(dossier.getDossierId());
								toActUser.setStepCode(fromActUser.getStepCode());
								//
								actions.updateDossierActionUser(toActUser);
								//Create DossierUser
								DossierUser fromDossierUser = DossierUserLocalServiceUtil.findByDID_UD(dossierId, fromUser.getUserId());
								if (fromDossierUser != null) {
									DossierUser dUser = new org.opencps.dossiermgt.model.impl.DossierUserImpl();
									dUser.setDossierId(dossier.getDossierId());
									dUser.setUserId(toUser.getUserId());
									dUser.setModerator(fromDossierUser.getModerator());
									dUser.setVisited(fromDossierUser.getVisited());
									//
									DossierUserLocalServiceUtil.updateDossierUser(dUser);
								}
							}
						}
					}
				}
				//Reindex dossier
				Indexer<Dossier> indexer = IndexerRegistryUtil
						.nullSafeGetIndexer(Dossier.class);
				indexer.reindex(dossier);
				
				return Response.status(200).entity(JSONFactoryUtil.looseSerialize("Phân Quyền thành công!!!")).build();
			}
			else {
				return Response.status(200).entity(StringPool.BLANK).build();
			}
		} catch (Exception e) {
			_log.error(e);
			return BusinessExceptionImpl.processException(e);
		}
	}

	@Override
	public Response fixSyncContactDossier(HttpServletRequest request, HttpHeaders header, Company company,
			Locale locale, User user, ServiceContext serviceContext, String id) {

		_log.info("START=====");
		long groupId = GetterUtil.getLong(header.getHeaderString("groupId"));
		BackendAuth auth = new BackendAuthImpl();

		try {

			long dossierId = GetterUtil.getLong(id);
			Dossier dossier = null;
			if (dossierId > 0) {
				dossier = DossierLocalServiceUtil.fetchDossier(dossierId);
			} else {
				dossier = DossierLocalServiceUtil.getByDossierNo(groupId, id);
			}

			_log.info("START=====: "+JSONFactoryUtil.looseSerialize(dossier));
			if (!auth.isAuth(serviceContext)) {
				throw new UnauthenticationException();
			}
			List<Role> userRoles = user.getRoles();
			boolean isAdmin = false;
			for (Role r : userRoles) {
				if (r.getName().startsWith("Administrator")) {
					isAdmin = true;
					break;
				}
			}
			
			if (!isAdmin) {
				throw new UnauthenticationException();
			}

			_log.info("START=====");
			if (dossier != null && dossier.getDossierActionId() != 0) {
				List<Dossier> dossierList = DossierLocalServiceUtil.getByGroupAndOriginDossierNo(groupId, dossier.getDossierNo());
				if (dossierList!= null && dossierList.size() > 0) {
					int countByGID_NO = dossierList.size();
					int countORI_NO = DossierLocalServiceUtil.countByOriginDossierNo(dossier.getDossierNo());
					if (countORI_NO == countByGID_NO) {
						//Delete dossier origin
						for (Dossier originDossier : dossierList) {
							//Delete DossierSync
							DossierSyncLocalServiceUtil.removeByDossierId(groupId, originDossier.getDossierId());
							//Delete dossier
							DossierLocalServiceUtil.deleteDossier(originDossier);
							
						}
					} else {
						//TODO: Don't process
					}
				} else {
					
				}
				_log.info("START=====");
				//Undo hồ sơ liên thông
				DossierAction dossierAction = DossierActionLocalServiceUtil.fetchDossierAction(dossier.getDossierActionId());
				if (dossierAction != null && dossierAction.isRollbackable()) {
					_log.info("START=====");
					DossierActionLocalServiceUtil.updateState(dossierAction.getDossierActionId(), DossierActionTerm.STATE_ROLLBACK);
				
					DossierAction previousAction = DossierActionLocalServiceUtil.fetchDossierAction(dossierAction.getPreviousActionId());
					_log.info("START=====");
					if (previousAction != null) {
						DossierActionLocalServiceUtil.updateState(previousAction.getDossierActionId(), DossierActionTerm.STATE_WAITING_PROCESSING);
						try {
							DossierActionLocalServiceUtil.updateNextActionId(previousAction.getDossierActionId(), 0);
							DossierLocalServiceUtil.rollback(dossier, previousAction);
						} catch (PortalException e) {
							_log.error(e);
							return BusinessExceptionImpl.processException(e);
						}
					}
					_log.info("START=====");
					DossierSync ds = DossierSyncLocalServiceUtil.getByDID_DAD(groupId, dossier.getDossierId(), dossierAction.getDossierActionId());
					if (ds != null && ((ds.getSyncType() == DossierSyncTerm.SYNCTYPE_INFORM && dossier.getOriginality() == DossierTerm.ORIGINALITY_LIENTHONG)
							|| (ds.getSyncType() == DossierSyncTerm.SYNCTYPE_REQUEST && dossier.getOriginality() == DossierTerm.ORIGINALITY_DVCTT))) {
						_log.info("START=====");
						DossierMgtUtils.processSyncRollbackDossier(dossier);					
					}
					_log.info("START=====");
				}
				else if (dossierAction != null && isAdmin) {
					_log.info("START=====");
					DossierActionLocalServiceUtil.updateState(dossierAction.getDossierActionId(), DossierActionTerm.STATE_ROLLBACK);
					_log.info("START=====");
					DossierAction previousAction = DossierActionLocalServiceUtil.fetchDossierAction(dossierAction.getPreviousActionId());
					_log.info("START=====");
					if (previousAction != null) {
						DossierActionLocalServiceUtil.updateState(previousAction.getDossierActionId(), DossierActionTerm.STATE_WAITING_PROCESSING);
						try {
							DossierActionLocalServiceUtil.updateNextActionId(previousAction.getDossierActionId(), 0);
							DossierLocalServiceUtil.rollback(dossier, previousAction);
						} catch (PortalException e) {
							_log.error(e);
							return BusinessExceptionImpl.processException(e);
						}
					}	
					
					DossierSync ds = DossierSyncLocalServiceUtil.getByDID_DAD(groupId, dossier.getDossierId(), dossierAction.getDossierActionId());
					if (ds != null && ((ds.getSyncType() == DossierSyncTerm.SYNCTYPE_INFORM && dossier.getOriginality() == DossierTerm.ORIGINALITY_LIENTHONG)
							|| (ds.getSyncType() == DossierSyncTerm.SYNCTYPE_REQUEST && dossier.getOriginality() == DossierTerm.ORIGINALITY_DVCTT))) {
						DossierMgtUtils.processSyncRollbackDossier(dossier);					
					}
				}

				_log.info("START=====");
				//Process liên thông hồ sơ
				String dossierStatus = dossier.getDossierStatus();
				String dossierSubStatus = dossier.getDossierSubStatus();
				long serviceProcessId = dossierAction.getServiceProcessId();
				
				ProcessStep step = ProcessStepLocalServiceUtil.getByProcessAndStatus(groupId, serviceProcessId,
						dossierStatus, dossierSubStatus);
				_log.info("START=====");
				if (step != null) {
					String stepCode = step.getStepCode();
					_log.info("START=====: "+stepCode);
					if (Validator.isNotNull(stepCode)) {
						List<ProcessAction> actionList = ProcessActionLocalServiceUtil.getByServiceStepCode(groupId,
								serviceProcessId, stepCode);
						_log.info("actionList=====: "+JSONFactoryUtil.looseSerialize(actionList));
						if (actionList != null && actionList.size() > 0) {
							String actionCode = StringPool.BLANK;
							for (ProcessAction processAction : actionList) {
								if (Validator.isNotNull(processAction.getCreateDossiers())) {
									actionCode = processAction.getActionCode();
									break;
								}
							}
							_log.info("actionCode=====: "+actionCode);
							if (Validator.isNotNull(actionCode)) {
								ActionConfig actConfig = ActionConfigLocalServiceUtil.getByCode(groupId, actionCode);
								_log.info("Action config: " + actConfig);
								//String serviceCode = dossier.getServiceCode();
								//String govAgencyCode = dossier.getGovAgencyCode();
								//String dossierTempNo = dossier.getDossierTemplateNo();
								if (actConfig != null) {
									DossierActions actions = new DossierActionsImpl();
									ErrorMsgModel errorModel = new ErrorMsgModel();
									//
									boolean insideProcess = actConfig.getInsideProcess();
									_log.info("insideProcess: " + insideProcess);
									//ProcessOption option = DossierUtils.getProcessOption(serviceCode, govAgencyCode,
											//dossierTempNo, groupId);
									if (insideProcess) {
										if (dossierAction != null) {
											_log.info("dossierAction: " + dossierAction);
											DossierTemplate dossierTemplate = DossierTemplateLocalServiceUtil.getByTemplateNo(groupId, dossier.getDossierTemplateNo());
											
											ProcessOption oldOption = ProcessOptionLocalServiceUtil.fetchBySP_DT(serviceProcessId, dossierTemplate.getDossierTemplateId());
											
											ProcessAction proAction = DossierUtils.getProcessAction(groupId, dossier, actionCode,
													serviceProcessId);
											if (proAction != null) {
												_log.info("DO ACTION: " + proAction.getActionCode());
												actions.doAction(groupId, user.getUserId(), dossier, oldOption, proAction,
														actionCode, StringPool.BLANK, StringPool.BLANK,
														StringPool.BLANK, StringPool.BLANK, StringPool.BLANK,
														actConfig.getSyncType(), serviceContext, errorModel);

												return Response.status(200).entity(null).build();
											}
										}
									}
								}
							}
						}
					}
				}
			}
			return Response.status(500).entity(null).build();
		} catch (Exception e) {
			_log.error(e);
			return BusinessExceptionImpl.processException(e);
		}
	}

	@Override
	public Response getUrlSiteInfo(HttpServletRequest request, HttpHeaders header, Company company, Locale locale,
			User user, ServiceContext serviceContext, String referenceUid) {

		long groupId = GetterUtil.getLong(header.getHeaderString("groupId"));
		JSONObject jsonData = JSONFactoryUtil.createJSONObject();
		try {
			Dossier dossier = DossierLocalServiceUtil.getByRef(groupId, referenceUid);
			if (dossier != null) {
				jsonData.put("dossierId", dossier.getDossierId());
				// Get info group
				Group group = GroupLocalServiceUtil.getGroup(groupId);
				jsonData.put("url", PortalUtil.getPathFriendlyURLPublic() + group.getFriendlyURL());
			}
			
		} catch (Exception e) {
			return BusinessExceptionImpl.processException(e);
		}
		return Response.status(HttpServletResponse.SC_OK).entity(JSONFactoryUtil.looseSerialize(jsonData)).build();
	}

	@Override
	public Response getValidateProcess(HttpServletRequest request, HttpHeaders header, Company company, Locale locale,
			User user, ServiceContext serviceContext, String id, String actionCode) {
		BackendAuth auth = new BackendAuthImpl();

		JSONObject results = JSONFactoryUtil.createJSONObject();
		results.put("value", false);
		try {
			if (!auth.isAuth(serviceContext)) {
				throw new UnauthenticationException();
			}

			long groupId = GetterUtil.getLong(header.getHeaderString("groupId"));
			long userId = user.getUserId();

			Dossier dossier = DossierUtils.getDossier(id, groupId);
			if (dossier != null) {
				long serviceProcessId = 0;
				String stepCode = StringPool.BLANK;
				boolean pending = false;
				long dossierActionId = dossier.getDossierActionId();
				// _log.info("dossierActionId: "+dossierActionId);
				DossierAction dossierAction = null;
				if (dossierActionId > 0) {
					dossierAction = DossierActionLocalServiceUtil.fetchDossierAction(dossierActionId);
				}

				if (dossierAction != null) {
					serviceProcessId = dossierAction.getServiceProcessId();
					stepCode = dossierAction.getStepCode();
					pending = dossierAction.getPending();
				}

				if (Validator.isNotNull(stepCode) && serviceProcessId > 0) {
					DossierActionUser dActionUser = DossierActionUserLocalServiceUtil
							.getByDossierAndUser(dossierActionId, userId);
					// _log.info("User id: " + userId);
					int enable = 2;
					if (dossier.getOriginality() == DossierTerm.ORIGINALITY_DVCTT) {
						if (dossier.getUserId() == userId && !pending) {
							enable = 1;
						}
					}
					if (dActionUser != null) {
						int assign = dActionUser.getAssigned();
						if (assign == 1 && !pending)
							enable = 1;
					}
					_log.info("enable: "+enable);
					List<ProcessAction> processActionList = ProcessActionLocalServiceUtil
							.getProcessActionByG_SPID_PRESC(groupId, serviceProcessId, stepCode);
					if (processActionList != null && processActionList.size() > 0) {
						StringBuilder sb = new StringBuilder();
						for (ProcessAction processAction : processActionList) {
							if (enable == 1 && (processCheckEnable(processAction.getPreCondition(),
									processAction.getAutoEvent(), dossier, actionCode, groupId))) {
									sb.append(processAction.getActionCode());
									sb.append(StringPool.SPACE);
							}
						}
						_log.info("SB: "+sb.toString());
						if (sb != null && sb.toString().contains(actionCode)) {
							results.put("value", true);
						}
					}
				}
			}

			return Response.status(200).entity(results.toJSONString()).build();
		} catch (Exception e) {
			_log.info(e);
			return Response.status(200).entity(results.toJSONString()).build();
		}
	}

	//LamTV_Process check permission action
	public static final String AUTO_EVENT_SUBMIT = "submit";
	public static final String AUTO_EVENT_TIMMER = "timer";
	public static final String AUTO_EVENT_LISTENER = "listener";
	public static final String AUTO_EVENT_SPECIAL = "special";
	private boolean processCheckEnable(String preCondition, String autoEvent, Dossier dossier, String actionCode,
			long groupId) {
		if (AUTO_EVENT_SUBMIT.equals(autoEvent) || AUTO_EVENT_TIMMER.equals(autoEvent)
				|| AUTO_EVENT_LISTENER.equals(autoEvent) || AUTO_EVENT_SPECIAL.equals(autoEvent)) {
			return false;
		}
		String[] preConditionArr = StringUtil.split(preCondition);
		if (preConditionArr != null && preConditionArr.length > 0) {
			return DossierMgtUtils.checkPreCondition(preConditionArr, dossier);
		}

		return true;
	}

	@Override
	public Response updateDossierInGroup(HttpServletRequest request, HttpHeaders header, Company company, Locale locale,
			User user, ServiceContext serviceContext, long groupDossierId, String dossierIds) {

		//long groupId = GetterUtil.getLong(header.getHeaderString("groupId"));
		try {
			if (dossierIds.contains(StringPool.COMMA)) {
				String[] dossierArr = dossierIds.split(StringPool.COMMA);
				DossierResultsModel results = new DossierResultsModel();

				results.setTotal(dossierArr.length);

				
				for (String dossierStr : dossierArr) {
					Dossier dossier = DossierLocalServiceUtil.fetchDossier(GetterUtil.getLong(dossierStr));
					if (dossier != null) {
						dossier.setGroupDossierId(groupDossierId);
						DossierLocalServiceUtil.updateDossier(dossier);
						
						DossierDataModel dataModel = new DossierDataModel();
						dataModel.setDossierNo(dossier.getDossierNo());
						results.getData().add(dataModel);
					}
				}
				//
				return Response.status(200).entity(results).build();
			}
			else {
				Dossier dossier = DossierLocalServiceUtil.fetchDossier(GetterUtil.getLong(dossierIds));
				if (dossier != null) {
					dossier.setGroupDossierId(groupDossierId);
					DossierLocalServiceUtil.updateDossier(dossier);
				}
				DossierDetailModel result = DossierUtils.mappingForGetDetail(dossier, user.getUserId());
				//
				return Response.status(200).entity(result).build();				
			}
		} catch (Exception e) {
			_log.info(e);
			return Response.status(500).entity("{error}").build();
		}
	}

	@Override
	public Response addMultipleDossier(HttpServletRequest request, HttpHeaders header, Company company, Locale locale,
			User user, ServiceContext serviceContext, DossierMultipleInputModel input) {
		long groupId = GetterUtil.getLong(header.getHeaderString("groupId"));
		try {
			_log.info("dossiers: "+input.getDossiers());
			if (Validator.isNotNull(input.getDossiers())) {
				JSONObject dossiers = JSONFactoryUtil.createJSONObject(input.getDossiers());
				String applicantName = Validator.isNotNull(dossiers.getString(DossierTerm.APPLICANT_NAME))
						? dossiers.getString(DossierTerm.APPLICANT_NAME)
						: StringPool.BLANK;
				String delegateName = Validator.isNotNull(dossiers.getString(DossierTerm.DELEGATE_NAME))
						? dossiers.getString(DossierTerm.DELEGATE_NAME)
						: StringPool.BLANK;
				
				_log.info("applicantName: "+applicantName);
				_log.info("delegateName: "+delegateName);
				String[] statusArr = {StringPool.BLANK};
				List<Dossier> dossierList = DossierLocalServiceUtil.getByGID_GC_SC_DTN_DS_APP_ORI(groupId,
						input.getGovAgencyCode(), input.getServiceCode(), input.getDossierTemplateNo(), statusArr,
						dossiers.getString(DossierTerm.APPLICANT_ID_TYPE),
						Validator.isNotNull(input.getOriginality()) ? Integer.valueOf(input.getOriginality()) : 0);
				if (dossierList != null && dossierList.size() > 0) {
					for (Dossier dossierImport : dossierList) {
						if (applicantName.equalsIgnoreCase(dossierImport.getApplicantName())
								&& delegateName.equalsIgnoreCase(dossierImport.getDelegateName())) {
							return Response.status(HttpStatus.SC_CONFLICT).entity("{CONFLICT}").build();
						}
					}
				}
			}

			Dossier dossier = CPSDossierBusinessLocalServiceUtil.addMultipleDossier(groupId, company, user, serviceContext,
					DossierUtils.convertFormModelToMultipleInputModel(input));
			DossierDetailModel result = DossierUtils.mappingForGetDetail(dossier, user.getUserId());
			return Response.status(HttpStatus.SC_OK).entity(result).build();
		} catch (Exception e) {
			_log.error(e);
			return BusinessExceptionImpl.processException(e);
		}
	}

	@Override
	public Response addFullDossier(HttpServletRequest request, HttpHeaders header, Company company, Locale locale,
			User user, ServiceContext serviceContext, DossierMultipleInputModel input) {
		long groupId = GetterUtil.getLong(header.getHeaderString("groupId"));
		try {
			_log.info("dossiers: "+input.getDossiers());
//			if (Validator.isNotNull(input.getDossiers())) {
//				JSONObject dossiers = JSONFactoryUtil.createJSONObject(input.getDossiers());
//				String applicantName = Validator.isNotNull(dossiers.getString(DossierTerm.APPLICANT_NAME))
//						? dossiers.getString(DossierTerm.APPLICANT_NAME)
//						: StringPool.BLANK;
//				String delegateName = Validator.isNotNull(dossiers.getString(DossierTerm.DELEGATE_NAME))
//						? dossiers.getString(DossierTerm.DELEGATE_NAME)
//						: StringPool.BLANK;
//				
//				_log.info("applicantName: "+applicantName);
//				_log.info("delegateName: "+delegateName);
//				String[] statusArr = {StringPool.BLANK, DossierTerm.DOSSIER_STATUS_NEW};
//				List<Dossier> dossierList = DossierLocalServiceUtil.getByGID_GC_SC_DTN_DS_APP_ORI(groupId,
//						input.getGovAgencyCode(), input.getServiceCode(), input.getDossierTemplateNo(), statusArr,
//						dossiers.getString(DossierTerm.APPLICANT_ID_TYPE),
//						Validator.isNotNull(input.getOriginality()) ? Integer.valueOf(input.getOriginality()) : 0);
//				if (dossierList != null && dossierList.size() > 0) {
//					for (Dossier dossierImport : dossierList) {
//						if (applicantName.equalsIgnoreCase(dossierImport.getApplicantName())
//								&& delegateName.equalsIgnoreCase(dossierImport.getDelegateName())) {
//							return Response.status(HttpStatus.SC_CONFLICT).entity("{CONFLICT}").build();
//						}
//					}
//				}
//			}

			Dossier dossier = CPSDossierBusinessLocalServiceUtil.addFullDossier(groupId, company, user, serviceContext,
					DossierUtils.convertFormModelToMultipleInputModel(input));
			DossierDetailModel result = DossierUtils.mappingForGetDetail(dossier, user.getUserId());
			return Response.status(HttpStatus.SC_OK).entity(result).build();
		} catch (Exception e) {
			_log.error(e);
			return BusinessExceptionImpl.processException(e);
		}
	}

	@Override
	public Response getDueDateByProcess(HttpServletRequest request, HttpHeaders header, Company company, Locale locale,
			User user, ServiceContext serviceContext, DossierSearchModel query) {

		long groupId = GetterUtil.getLong(header.getHeaderString("groupId"));
		try {
			JSONObject jsonData = JSONFactoryUtil.createJSONObject();
			String strReceiveDate = query.getFromReceiveDate();
			Date receiveDate = null;
			if (Validator.isNotNull(strReceiveDate)) {
				SimpleDateFormat sdf = new SimpleDateFormat(APIDateTimeUtils._NORMAL_PARTTERN);
				receiveDate = sdf.parse(strReceiveDate);
			} else {
				receiveDate = new Date();
			}
			//
			jsonData.put(DossierTerm.RECEIVE_DATE, receiveDate != null ? receiveDate.getTime() : (new Date()).getTime());
			Date dueDate = null;
			ProcessOption option = getProcessOption(query.getService(), query.getAgency(),
					query.getTemplate(), groupId);
			if (option != null) {
				ServiceProcess process = ServiceProcessLocalServiceUtil
						.fetchServiceProcess(option.getServiceProcessId());
				if (process != null) {
					Double durationCount = process.getDurationCount();
					if (Validator.isNotNull(String.valueOf(durationCount)) && durationCount > 0d) {
						dueDate = HolidayUtils.getDueDate(receiveDate, process.getDurationCount(),
								process.getDurationUnit(), groupId);
						jsonData.put(DossierTerm.DUE_DATE, dueDate != null ? dueDate.getTime() : 0);
					} else {
						jsonData.put(DossierTerm.DUE_DATE, 0);
					}
				} else {
					jsonData.put(DossierTerm.DUE_DATE, 0);
				}
			} else {
				jsonData.put(DossierTerm.DUE_DATE, 0);
			}
			return Response.status(HttpStatus.SC_OK).entity(JSONFactoryUtil.looseSerialize(jsonData)).build();
		} catch (Exception e) {
			_log.debug(e);
		}
		return Response.status(HttpStatus.SC_INTERNAL_SERVER_ERROR).entity("No Content").build();
	}

	@Override
	public Response getOptionDossier(HttpServletRequest request, HttpHeaders header, Company company, Locale locale,
			User user, ServiceContext serviceContext, String id) {
		return Response.status(HttpStatus.SC_OK)
				.header("Allow", "")
                .build();
	}

	@Override
	public Response generateDossierNo(HttpServletRequest request, HttpHeaders header, Company company, Locale locale,
			User user, ServiceContext serviceContext, String serviceCode, String govAgencyCode, String templateNo) {
		long groupId = GetterUtil.getLong(header.getHeaderString("groupId"));
		ServiceProcess serviceProcess = null;
		ProcessOption option;
		try {
			option = getProcessOption(serviceCode, govAgencyCode, templateNo, groupId);
			_log.debug("Process option: " + option);
			if (option != null) {
				long serviceProcessId = option.getServiceProcessId();
				serviceProcess = ServiceProcessLocalServiceUtil.fetchServiceProcess(serviceProcessId);

				String dossierRef = DossierNumberGenerator.generateDossierNumber(groupId, serviceProcess.getDossierNoPattern(), serviceCode, govAgencyCode, templateNo, serviceProcess.getProcessNo());
				_log.debug("Dossier no: " + dossierRef);
				JSONObject result = JSONFactoryUtil.createJSONObject();
				result.put(DossierTerm.DOSSIER_NO, dossierRef.trim());
				
				return Response.status(HttpURLConnection.HTTP_OK).entity(result.toJSONString()).build();
			}
			else {
				throw new Exception("Do not have service");
			}
		} catch (PortalException e) {
			return BusinessExceptionImpl.processException(e);
		}
		catch (Exception e) {
			return BusinessExceptionImpl.processException(e);
		}
	}

	@Override
	public Response getDelegacyUsers(HttpServletRequest request, HttpHeaders header, Company company, Locale locale,
			User user, String id, ServiceContext serviceContext) {
		long groupId = GetterUtil.getLong(header.getHeaderString("groupId"));
		BackendAuth auth = new BackendAuthImpl();

		try {

			if (!auth.isAuth(serviceContext)) {
				throw new UnauthenticationException();
			}

			JSONObject result = JSONFactoryUtil.createJSONObject();
			long dossierId = 0;
			dossierId = GetterUtil.getLong(id);
			Dossier dossier = null;
			dossier = DossierLocalServiceUtil.fetchDossier(dossierId);
			if (dossier == null) {
				dossier = DossierLocalServiceUtil.getByRef(groupId, id);
			}
			if (dossier != null) {
				long lastActionId = dossier.getDossierActionId();
				DossierAction da = DossierActionLocalServiceUtil.fetchDossierAction(lastActionId);
				if (da != null) {
					DossierActionUser checkDau = DossierActionUserLocalServiceUtil.getByD_DID_UID_SC(dossier.getDossierId(), da.getPreviousActionId(), user.getUserId(), da.getFromStepCode());
					if (checkDau == null || checkDau.getAssigned() != DossierActionUserTerm.ASSIGNED_TH) {
						return Response.status(HttpURLConnection.HTTP_OK).entity(result.toJSONString()).build();
					}
					
					ProcessAction pa = ProcessActionLocalServiceUtil.fetchByF_GID_SID_AC_PRE_POST(groupId, da.getServiceProcessId(), da.getActionCode(), da.getFromStepCode(), da.getStepCode());
					if (pa != null) {
						result.put(ProcessActionTerm.ALLOW_ASSIGN_USER, pa.getAllowAssignUser());
						
						ProcessStep processStep = ProcessStepLocalServiceUtil.fetchBySC_GID(da.getStepCode(), groupId,
								da.getServiceProcessId());
						List<User> lstUser = new ArrayList<>();
						List<Long> lstIds = new ArrayList<>();
						
						if (processStep != null) {	
							List<DossierActionUser> assignedUsers = DossierActionUserLocalServiceUtil.getByDossierAndStepCode(dossierId, processStep.getStepCode());
							for (DossierActionUser dau : assignedUsers) {
								User u = UserLocalServiceUtil.fetchUser(dau.getUserId());
								if (u != null) {
									if (!u.isLockout() && u.isActive() && !lstIds.contains(u.getUserId())) {
										lstUser.add(u);
										lstIds.add(u.getUserId());
									}
								}
							}
							
							if (processStep != null) {
								List<ProcessStepRole> processStepRoleList = ProcessStepRoleLocalServiceUtil
										.findByP_S_ID(processStep.getProcessStepId());
								if (processStepRoleList != null && !processStepRoleList.isEmpty()) {
									List<ProcessStepRole> lstStepRoles = new ArrayList<>();
									for (ProcessStepRole psr : processStepRoleList) {
										if (Validator.isNotNull(psr.getCondition())) {
											String[] conditions = StringUtil.split(psr.getCondition());
		
											if (DossierMgtUtils.checkPreCondition(conditions, dossier)) {
												lstStepRoles.add(psr);
											}
										}
										else {
											lstStepRoles.add(psr);
										}
									}
									
									for (ProcessStepRole role : processStepRoleList) {
										List<User> lstUsers = UserLocalServiceUtil.getRoleUsers(role.getRoleId());
										for (User u : lstUsers) {
											if (!u.isLockout() && u.isActive()) {
												HashMap<String, Object> assigned = new HashMap<>();
												assigned.put(ProcessStepRoleTerm.ASSIGNED, 0);	
												HashMap<String, Object> moderator = new HashMap<>();
												moderator.put(ProcessStepRoleTerm.MODERATOR, role.getModerator());
												u.setModelAttributes(moderator);
												u.setModelAttributes(assigned);
	
												if (!lstIds.contains(u.getUserId())) {
													lstUser.add(u);
													lstIds.add(u.getUserId());
												}
											}
										}
									}
								}
							}
						}
						
						JSONArray outputUserArr = JSONFactoryUtil.createJSONArray();
	
						if (lstUser != null && lstUser.size() > 0) {
							boolean moderator = false;
							int assigned = 0;
							for (User u: lstUser) {
								if (!u.isLockout() && u.isActive()) {
									JSONObject userObj = JSONFactoryUtil.createJSONObject();
									
									Map<String, Object> attr = u.getModelAttributes();
									long userId = GetterUtil.getLong(u.getUserId());
									moderator = false;
									assigned = 0;
									if (attr != null) {
										if (attr.containsKey(ProcessStepRoleTerm.MODERATOR)) {
											moderator = GetterUtil.getBoolean(attr.get(ProcessStepRoleTerm.MODERATOR));
										}
										if (attr.containsKey(ProcessStepRoleTerm.ASSIGNED)) {
											assigned = GetterUtil.getInteger(attr.get(ProcessStepRoleTerm.ASSIGNED));
										}
									}
									userObj.put("userId", userId);
									Employee emp = EmployeeLocalServiceUtil.fetchByF_mappingUserId(groupId, userId);
									if (emp != null) {
										userObj.put("fullName", emp.getFullName() != null ? emp.getFullName().toUpperCase()
												: StringPool.BLANK);
									} else {
										userObj.put("fullName",
												u.getFullName() != null ? u.getFullName().toUpperCase() : StringPool.BLANK);
									}
									
									userObj.put("moderator", moderator);
									userObj.put("assigned", assigned);
									
									outputUserArr.put(userObj);							
								}
							}
						}
						
						result.put("toUsers", outputUserArr);
					}
				}
			}
			else {
				
			}
			return Response.status(HttpURLConnection.HTTP_OK).entity(result.toJSONString()).build();
		}
		catch (Exception e) {
			return BusinessExceptionImpl.processException(e);
		}
	}

}
