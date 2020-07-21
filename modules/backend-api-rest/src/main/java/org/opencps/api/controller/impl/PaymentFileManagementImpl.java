package org.opencps.api.controller.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.liferay.document.library.kernel.service.DLAppLocalServiceUtil;
import com.liferay.document.library.kernel.service.DLFileEntryLocalServiceUtil;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.messaging.Message;
import com.liferay.portal.kernel.messaging.MessageBusException;
import com.liferay.portal.kernel.messaging.MessageBusUtil;
import com.liferay.portal.kernel.model.Company;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.uuid.PortalUUIDUtil;

import java.io.File;
import java.io.InputStream;
import java.net.URI;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;

import javax.activation.DataHandler;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.HttpMethod;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

import org.apache.commons.httpclient.util.HttpURLConnection;
import org.apache.commons.lang3.StringUtils;
import org.apache.cxf.jaxrs.ext.multipart.Attachment;
import org.opencps.api.controller.PaymentFileManagement;
import org.opencps.api.controller.exception.ErrorMsg;
import org.opencps.api.controller.util.ConvertDossierFromV1Dot9Utils;
import org.opencps.api.controller.util.PaymentFileUtils;
import org.opencps.api.paymentfile.model.PaymentFileInputModel;
import org.opencps.api.paymentfile.model.PaymentFileModel;
import org.opencps.auth.api.BackendAuth;
import org.opencps.auth.api.BackendAuthImpl;
import org.opencps.auth.api.exception.UnauthenticationException;
import org.opencps.auth.api.exception.UnauthorizationException;
import org.opencps.auth.api.keys.ActionKeys;
import org.opencps.auth.utils.APIDateTimeUtils;
import org.opencps.dossiermgt.action.PaymentFileActions;
import org.opencps.dossiermgt.action.impl.PaymentFileActionsImpl;
import org.opencps.dossiermgt.constants.DossierTerm;
import org.opencps.dossiermgt.constants.KeyPayTerm;
import org.opencps.dossiermgt.constants.PaymentFileTerm;
import org.opencps.dossiermgt.model.Dossier;
import org.opencps.dossiermgt.model.PaymentConfig;
import org.opencps.dossiermgt.model.PaymentFile;
import org.opencps.dossiermgt.scheduler.InvokeREST;
import org.opencps.dossiermgt.scheduler.RESTFulConfiguration;
import org.opencps.dossiermgt.service.CPSDossierBusinessLocalServiceUtil;
import org.opencps.dossiermgt.service.DossierLocalServiceUtil;
import org.opencps.dossiermgt.service.PaymentConfigLocalServiceUtil;
import org.opencps.dossiermgt.service.PaymentFileLocalServiceUtil;
import org.opencps.dossiermgt.service.ProcessPluginLocalServiceUtil;
import org.opencps.usermgt.model.WorkingUnit;
import org.opencps.usermgt.service.WorkingUnitLocalServiceUtil;
import org.opencps.usermgt.service.impl.WorkingUnitLocalServiceImpl;

import backend.auth.api.exception.BusinessExceptionImpl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.liferay.document.library.kernel.service.DLAppLocalServiceUtil;
import com.liferay.document.library.kernel.service.DLFileEntryLocalServiceUtil;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.messaging.Message;
import com.liferay.portal.kernel.messaging.MessageBusException;
import com.liferay.portal.kernel.messaging.MessageBusUtil;
import com.liferay.portal.kernel.model.Company;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;

public class PaymentFileManagementImpl implements PaymentFileManagement {

	/**
	 * Get all PaymentFile of DossierId
	 * 
	 * @param dossierId
	 * @return Response
	 */
//	@SuppressWarnings("unchecked")
//	@Override
//	public Response getPaymentFilesByDossierId(HttpServletRequest request, HttpHeaders header, Company company,
//			Locale locale, User user, ServiceContext serviceContext, String id, PaymentFileSearchModel search) {
//
//		BackendAuth auth = new BackendAuthImpl();
//		try {
//
//			// Check user is login
//			if (!auth.isAuth(serviceContext)) {
//				throw new UnauthenticationException();
//			}
//			if (search.getEnd() == 0) {
//				search.setStart(-1);
//				search.setEnd(-1);
//			}
//
//			long groupId = GetterUtil.getLong(header.getHeaderString("groupId"));
//			long dossierId = GetterUtil.getLong(id);
//
//			// Default sort by modifiedDate
//			Sort[] sorts = new Sort[] {
//					SortFactoryUtil.create(Field.MODIFIED_DATE + "_sortable", Sort.STRING_TYPE, true) };
//
//			if (Validator.isNotNull(search.getSort()) && Validator.isNotNull(search.getOrder())) {
//				sorts = new Sort[] { SortFactoryUtil.create(search.getSort() + "_sortable", Sort.STRING_TYPE,
//						GetterUtil.getBoolean(search.getOrder())) };
//			}
//
//			PaymentFileActions actions = new PaymentFileActionsImpl();
//			PaymentFileResultModel results = new PaymentFileResultModel();
//
//			// get JSON data by dossierId
//			JSONObject jsonData = actions.getByDossierId(dossierId, serviceContext.getCompanyId(), groupId, sorts,
//					search.getStart(), search.getEnd(), serviceContext);
//
//			// Parse JSONObejct to PaymentFileResultModel Object
//			results.setTotal(jsonData.getInt("total"));
//			results.getData().addAll(PaymentFileUtils.mappingToPaymentFileModel((List<Document>) jsonData.get("data")));
//
//			return Response.status(200).entity(results).build();
//
//		} catch (Exception e) {
//			ErrorMsg error = new ErrorMsg();
//
//			error.setMessage("not found!");
//			error.setCode(404);
//			error.setDescription("not found!");
//
//			return Response.status(404).entity(error).build();
//		}
//	}

	/**
	 * Create PaymentFile of DossierId
	 * 
	 * @param form
	 *            params
	 * @return Response
	 */
//	@Override
//	public Response createPaymentFileByDossierId(HttpServletRequest request, HttpHeaders header, Company company,
//			Locale locale, User user, ServiceContext serviceContext, String id, PaymentFileInputModel input) {
//
//		long groupId = GetterUtil.getLong(header.getHeaderString("groupId"));
//		
//		_log.info("groupId_"+groupId);
//		_log.info("groupId_"+id);
//
//		long userId = serviceContext.getUserId();
//		
//
//		// TODO get Dossier by referenceUid if dossierId = 0
//		// String referenceUid = dossierId == 0 ? id : StringPool.BLANK;
//
////		BackendAuth auth = new BackendAuthImpl();
////
////		try {
////			/* Check user is login - START */
////			if (!auth.isAuth(serviceContext)) {
////				throw new UnauthenticationException();
////			}
////			Dossier dossier = getDossier(id, groupId);
////
////			long dossierId = dossier.getPrimaryKey();
////
////			if (!auth.hasResource(serviceContext, PaymentFile.class.getName(), ActionKeys.ADD_ENTRY)) {
////				throw new UnauthorizationException();
////			}
////			/* Check user is login - END */
////
////			PaymentFileActions actions = new PaymentFileActionsImpl();
////
////			PaymentFileInputModel PaymentFileInput = new PaymentFileInputModel();
////
////			PaymentFile paymentFile = actions.createPaymentFile(userId, groupId, dossierId, input.getReferenceUid(),
////					input.getGovAgencyCode(), input.getGovAgencyName(), input.getApplicantName(),
////					input.getApplicantIdNo(), input.getPaymentFee(), input.getPaymentAmount(), input.getPaymentNote(),
////					input.getEpaymentProfile(), input.getBankInfo(), serviceContext);
////			
////			paymentFile.setInvoiceTemplateNo(input.getInvoiceTemplateNo());
////			
////			PaymentFileLocalServiceUtil.updatePaymentFile(paymentFile);
////
////			PaymentFileInput = PaymentFileUtils.mappingToPaymentFileInputModel(paymentFile);
//
//			return Response.status(200).entity(PaymentFileInput).build();
//
//		} catch (Exception e) {
//			return processException(e);
//		}
//	}

	/**
	 * Get detail PaymentFile of DossierId and referenceUid
	 * 
	 * @param dossierId,
	 * @param referenceUid
	 * @return Response
	 */
//	@Override
//	public Response getPaymentFileByReferenceUid(HttpServletRequest request, HttpHeaders header, Company company,
//			Locale locale, User user, ServiceContext serviceContext, Long id, String referenceUid) {
//
//		// long groupId = GetterUtil.getLong(header.getHeaderString("groupId"));
//		long dossierId = GetterUtil.getLong(id);
//
//		// TODO get Dossier by referenceUid if dossierId = 0
//		// String referenceUid = dossierId == 0 ? id : StringPool.BLANK;
//		BackendAuth auth = new BackendAuthImpl();
//
//		try {
//
//			// Check user is login
//			if (!auth.isAuth(serviceContext)) {
//				throw new UnauthenticationException();
//			}
//			// Search full query
//			// int start = -1;
//			// int end = -1;
//
//			// TODO: Condition sort
//			// Sort[] sorts = new Sort[] {};
//			// Sort[] sorts = new Sort[] {SortFactoryUtil.create(query.getSort()
//			// + "_sortable",Sort.STRING_TYPE,
//			// GetterUtil.getBoolean(query.getOrder())) };
//
//			PaymentFileActions actions = new PaymentFileActionsImpl();
//			// PaymentFileResultModel PaymentFileDetail = new
//			// PaymentFileResultModel();
//			// PaymentFile paymentFile = actions.getPaymentFileDetail(dossierId,
//			// referenceUid);
//
//			// get JSON data by dossierId
//
//			// List<Document> data = actions.getPaymentFileDetail(dossierId,
//			// referenceUid, serviceContext.getCompanyId(),
//			// groupId, sorts, start, end, serviceContext);
//
//			// List<PaymentFileModel> paymentFileDetail =
//			// PaymentFileUtils.mappingToPaymentFileModel(data);
//			// dossierPermission.hasGetDetailDossier(groupId, user.getUserId(),
//			// dossier, option.getServiceProcessId());
//
//			// DossierDetailModel result =
//			// DossierUtils.mappingForGetDetail(dossier);
//			// PaymentFileModel results = paymentFileDetail.get(0);
//
//			PaymentFile paymentFile = actions.getPaymentFile(dossierId, referenceUid);
//
//			PaymentFileModel result = PaymentFileUtils.mappingToPaymentFileModel(paymentFile);
//
//			return Response.status(200).entity(result).build();
//
//		} catch (Exception e) {
//			return processException(e);
//		}
//	}

	/**
	 * Get info EpaymentProfile of DossierId and referenceUid
	 * 
	 * @param dossierId
	 * @param referenceUid
	 * @return Response
	 */
	@Override
	public Response getEpaymentProfile(HttpServletRequest request, HttpHeaders header, Company company, Locale locale,
			User user, ServiceContext serviceContext, String id, String referenceUid) {
		BackendAuth auth = new BackendAuthImpl();

		try {
			if (!auth.isAuth(serviceContext)) {
				throw new UnauthenticationException();
			}

			PaymentFileActions actions = new PaymentFileActionsImpl();

			long dossierId = GetterUtil.getLong(id);
			//_log.info("dossierId ============ " + dossierId);
			// TODO get Dossier by referenceUid if dossierId = 0
			// String referenceUid = dossierId == 0 ? id : StringPool.BLANK;

			PaymentFile paymentFile = actions.getPaymentFile(dossierId, referenceUid);

			String ePaymentProfile = paymentFile.getEpaymentProfile();
			//_log.info("ePaymentProfile ============ " + ePaymentProfile);
			JSONObject result = JSONFactoryUtil.createJSONObject(ePaymentProfile);

			return Response.status(200).entity(result.toJSONString()).build();

		} catch (Exception e) {
			_log.debug(e);
			ErrorMsg error = new ErrorMsg();

			error.setMessage("Content not found!");
			error.setCode(404);
			error.setDescription(e.getMessage());

			return Response.status(404).entity(error).build();
		}
	}

	/**
	 * Update info EpaymentProfile of DossierId and referenceUid
	 * 
	 * @param form
	 *            params
	 * @return Response
	 */
//	@Override
//	public Response updateEpaymentProfile(HttpServletRequest request, HttpHeaders header, Company company,
//			Locale locale, User user, ServiceContext serviceContext, String id, String referenceUid,
//			PaymentFileInputModel input) {
//		BackendAuth auth = new BackendAuthImpl();
//
//		try {
//
//			if (!auth.isAuth(serviceContext)) {
//				throw new UnauthenticationException();
//			}
//
//			if (!auth.hasResource(serviceContext, PaymentConfig.class.getName(), ActionKeys.ADD_ENTRY)) {
//				throw new UnauthorizationException();
//			}
//
//			long dossierId = GetterUtil.getLong(id);
//
//			// TODO get Dossier by referenceUid if dossierId = 0
//			// String referenceUid = dossierId == 0 ? id : StringPool.BLANK;
//
//			PaymentFileActions actions = new PaymentFileActionsImpl();
//
//			PaymentFile paymentFile = actions.updateEProfile(dossierId, referenceUid, input.getEpaymentProfile(),
//					serviceContext);
//
//			String result = paymentFile.getEpaymentProfile();
//
//			return Response.status(200).entity(result).build();
//
//		} catch (Exception e) {
//			return processException(e);
//		}
//	}

	/**
	 * Update Payment File Confirm of DossierId and referenceUid
	 * 
	 * @param form
	 *            params
	 * @return Response
	 */
	@Override
	public Response updatePaymentFileConfirm(HttpServletRequest request, HttpHeaders header, Company company,
			Locale locale, User user, ServiceContext serviceContext, String id, String referenceUid, Attachment file/*,
			String confirmNote, String paymentMethod, String confirmPayload*/) {

		BackendAuth auth = new BackendAuthImpl();

		long groupId = GetterUtil.getLong(header.getHeaderString("groupId"));

		long dossierId = GetterUtil.getLong(id);

		// TODO get Dossier by referenceUid if dossierId = 0
		// String referenceUid = dossierId == 0 ? id : StringPool.BLANK;

		try {

			if (!auth.isAuth(serviceContext)) {
				throw new UnauthenticationException();
			}

			DataHandler dataHandler = null;

			if (file != null) {
				dataHandler = file.getDataHandler();
			}

			PaymentFileActions action = new PaymentFileActionsImpl();

			PaymentFile paymentFile = action.updateFileConfirm(groupId, dossierId, referenceUid/*, confirmNote,
					paymentMethod, confirmPayload*/,StringPool.BLANK, StringPool.BLANK,StringPool.BLANK, dataHandler != null ? dataHandler.getName() : StringPool.BLANK, 0L,
					dataHandler != null ? dataHandler.getInputStream() : null, serviceContext);

			PaymentFileModel result = PaymentFileUtils.mappingToPaymentFileModel(paymentFile);

			return Response.status(200).entity(result).build();

		} catch (Exception e) {
			return BusinessExceptionImpl.processException(e);
		}
	}

//	@Override
//	public Response updatePaymentFileConfirmNoAttachment(HttpServletRequest request, HttpHeaders header,
//			Company company, Locale locale, User user, ServiceContext serviceContext, String id, String referenceUid,
//			PaymentFileInputModel input) {
//
//		BackendAuth auth = new BackendAuthImpl();
//
//		long groupId = GetterUtil.getLong(header.getHeaderString("groupId"));
//
//		long dossierId = GetterUtil.getLong(id);
//
//		// TODO get Dossier by referenceUid if dossierId = 0
//		// String referenceUid = dossierId == 0 ? id : StringPool.BLANK;
//
//		try {
//
//			if (!auth.isAuth(serviceContext)) {
//				throw new UnauthenticationException();
//			}
//
//			PaymentFileActions action = new PaymentFileActionsImpl();
//
//			PaymentFile paymentFile = action.updateFileConfirm(groupId, dossierId, referenceUid, input.getConfirmNote(),
//					input.getPaymentMethod(), input.getConfirmPayload(), serviceContext);
//
//			PaymentFileModel result = PaymentFileUtils.mappingToPaymentFileModel(paymentFile);
//
//			return Response.status(200).entity(result).build();
//
//		} catch (Exception e) {
//			e.printStackTrace();
//
//			return processException(e);
//		}
//	}

	/**
	 * Update Payment File Approval of DossierId and referenceUid
	 * 
	 * @param form
	 *            params
	 * @return Response
	 */
//	@Override
//	public Response updatePaymentFileApproval(HttpServletRequest request, HttpHeaders header, Company company,
//			Locale locale, User user, ServiceContext serviceContext, String id, String referenceUid, Attachment file,
//			String approveDatetime, String accountUserName, String govAgencyTaxNo, String invoiceTemplateNo,
//			String invoiceIssueNo, String invoiceNo) {
//		BackendAuth auth = new BackendAuthImpl();
//		long groupId = GetterUtil.getLong(header.getHeaderString("groupId"));
//
//		long dossierId = GetterUtil.getLong(id);
//
//		// TODO get Dossier by referenceUid if dossierId = 0
//		// String referenceUid = dossierId == 0 ? id : StringPool.BLANK;
//
//		try {
//
//			if (!auth.isAuth(serviceContext)) {
//				throw new UnauthenticationException();
//			}
//
//			DataHandler dataHandler = null;
//
//			if (file != null) {
//				dataHandler = file.getDataHandler();
//			}
//
//			PaymentFileActions action = new PaymentFileActionsImpl();
//
//			PaymentFile paymentFile = action.updateFileApproval(groupId, dossierId, referenceUid, approveDatetime,
//					accountUserName, govAgencyTaxNo, invoiceTemplateNo, invoiceIssueNo, invoiceNo,
//					dataHandler != null ? dataHandler.getName() : StringPool.BLANK, 0L,
//					dataHandler != null ? dataHandler.getInputStream() : null, serviceContext);
//
//			PaymentFileModel result = PaymentFileUtils.mappingToPaymentFileModel(paymentFile);
//
//			return Response.status(200).entity(result).build();
//
//		} catch (Exception e) {
//			return processException(e);
//		}
//	}

//	@Override
//	public Response updatePaymentFileApprovalNoAttachment(HttpServletRequest request, HttpHeaders header,
//			Company company, Locale locale, User user, ServiceContext serviceContext, String id, String referenceUid,
//			PaymentFileInputModel input) {
//		BackendAuth auth = new BackendAuthImpl();
//		long groupId = GetterUtil.getLong(header.getHeaderString("groupId"));
//
//		long dossierId = GetterUtil.getLong(id);
//
//		// TODO get Dossier by referenceUid if dossierId = 0
//		// String referenceUid = dossierId == 0 ? id : StringPool.BLANK;
//		
//		
//		Dossier dossier = null;
//
//		if (dossierId != 0) {
//			dossier = DossierLocalServiceUtil.fetchDossier(dossierId);
//			if (Validator.isNull(dossier)) {
//				dossier = DossierLocalServiceUtil.getByRef(groupId, id);
//			}
//		} else {
//			dossier = DossierLocalServiceUtil.getByRef(groupId, id);
//			
//		}
//		
//		if (Validator.isNotNull(dossier))
//			dossierId = dossier.getDossierId();
//
//		try {
//
//			if (!auth.isAuth(serviceContext)) {
//				throw new UnauthenticationException();
//			}
//
//			PaymentFileActions action = new PaymentFileActionsImpl();
//
//			boolean isSync = GetterUtil.getBoolean(input.getIsSync());
//			PaymentFile paymentFile = action.updateFileApproval(groupId, dossierId, referenceUid,
//					input.getApproveDatetime(), input.getAccountUserName(), input.getGovAgencyTaxNo(),
//					input.getInvoiceTemplateNo(), input.getInvoiceIssueNo(), input.getInvoiceNo(), isSync, serviceContext);
//			
////			if (!isSync) {
////				paymentFile.setIsNew(false);
////				
////				PaymentFileLocalServiceUtil.updatePaymentFile(paymentFile);
////			}
//			
//			
//			
//			PaymentFileModel result = PaymentFileUtils.mappingToPaymentFileModel(paymentFile);
//
//			return Response.status(200).entity(result).build();
//
//		} catch (Exception e) {
//			return processException(e);
//		}
//	}

	/**
	 * Download payment File Confirm
	 * 
	 * @param
	 * @return Response
	 */
	@Override
	public Response downloadConfirmFile(HttpServletRequest request, HttpHeaders header, Company company, Locale locale,
			User user, ServiceContext serviceContext, String id, String referenceUid) {

		BackendAuth auth = new BackendAuthImpl();

		long dossierId = GetterUtil.getLong(id);

		// TODO get Dossier by referenceUid if dossierId = 0
		// String referenceUid = dossierId == 0 ? id : StringPool.BLANK;

		try {

			if (!auth.isAuth(serviceContext)) {
				throw new UnauthenticationException();
			}

			PaymentFileActions action = new PaymentFileActionsImpl();
			PaymentFile paymentFile = action.getPaymentFileByReferenceUid(dossierId, referenceUid);

			if (paymentFile != null && paymentFile.getConfirmFileEntryId() > 0) {

				FileEntry fileEntry = DLAppLocalServiceUtil.getFileEntry(paymentFile.getConfirmFileEntryId());

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

	/**
	 * Download Invoice File Confirm
	 * 
	 * @param
	 * @return Response
	 */
	@Override
	public Response downloadInvoiceFile(HttpServletRequest request, HttpHeaders header, Company company, Locale locale,
			User user, ServiceContext serviceContext, String id, String referenceUid) {
		BackendAuth auth = new BackendAuthImpl();

		long dossierId = GetterUtil.getLong(id);

		// TODO get Dossier by referenceUid if dossierId = 0
		// String referenceUid = dossierId == 0 ? id : StringPool.BLANK;

		try {

			if (!auth.isAuth(serviceContext)) {
				throw new UnauthenticationException();
			}

			PaymentFileActions action = new PaymentFileActionsImpl();
			PaymentFile paymentFile = action.getPaymentFileByReferenceUid(dossierId, referenceUid);

			if (paymentFile != null && paymentFile.getInvoiceFileEntryId() > 0) {

				FileEntry fileEntry = DLAppLocalServiceUtil.getFileEntry(paymentFile.getInvoiceFileEntryId());

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

	/**
	 * Get all Payment File
	 * 
	 * @param
	 * @return Response
	 */
//	@SuppressWarnings("unchecked")
//	@Override
//	public Response getPaymentFiles(HttpServletRequest request, HttpHeaders header, Company company, Locale locale,
//			User user, ServiceContext serviceContext, PaymentFileSearchModel search) {
//
//		BackendAuth auth = new BackendAuthImpl();
//
//		try {
//			// Check user is login
//			if (!auth.isAuth(serviceContext)) {
//				throw new UnauthenticationException();
//			}
//			/* Search full Query -START */
//			if (search.getEnd() == 0) {
//				search.setStart(-1);
//				search.setEnd(-1);
//			}
//			/* Search full Query -END */
//			long groupId = GetterUtil.getLong(header.getHeaderString("groupId"));
//
//			PaymentFileSearchResultModel results = new PaymentFileSearchResultModel();
//			PaymentFileActions action = new PaymentFileActionsImpl();
//
//			/* Add params in Map - START */
//			LinkedHashMap<String, Object> params = new LinkedHashMap<String, Object>();
//			params.put(Field.GROUP_ID, String.valueOf(groupId));
//			params.put(Field.KEYWORD_SEARCH, search.getKeyword());
//			params.put(PaymentFileTerm.SERVICE, search.getService());
//			params.put(PaymentFileTerm.AGENCY, search.getAgency());
//			params.put(PaymentFileTerm.STATUS, search.getStatus());
//			params.put(PaymentFileTerm.IS_NEW, search.getIsNew());
//			/* Add params in Map - END */
//
//			// Default sort by modifiedDate
//			Sort[] sorts = new Sort[] {
//					SortFactoryUtil.create(Field.MODIFIED_DATE + "_sortable", Sort.STRING_TYPE, true) };
//
//			if (Validator.isNotNull(search.getSort()) && Validator.isNotNull(search.getOrder())) {
//				sorts = new Sort[] { SortFactoryUtil.create(search.getSort() + "_sortable", Sort.STRING_TYPE,
//						GetterUtil.getBoolean(search.getOrder())) };
//			}
//
//			JSONObject paymentFileJsonObject = action.getPaymentFiles(serviceContext.getUserId(),
//					serviceContext.getCompanyId(), groupId, params, sorts, search.getStart(), search.getEnd(),
//					serviceContext);
//
//			List<Document> documents = (List<Document>) paymentFileJsonObject.get("data");
//
//			results.setTotal(paymentFileJsonObject.getInt("total"));
//
//			results.getData().addAll(PaymentFileUtils.mappingToPaymentFileSearchResultModel(documents));
//
//			return Response.status(200).entity(results).build();
//
//		} catch (Exception e) {
//			return processException(e);
//		}
//	}

	@Override
	public Response processingKeyPay(HttpServletRequest request, HttpHeaders header, Company company, Locale locale,
			User user, ServiceContext serviceContext, String dossierUUid, String paymentFileUUid) {
		// TODO Auto-generated method stub
		//URI uri = null;
		try {
			
			long groupId = GetterUtil.getLong(header.getHeaderString("groupId"));
			Dossier dossier = DossierLocalServiceUtil.getByRef(groupId, dossierUUid);
			_log.info("SONDT PROCESS KEYPAY  ======== " + JSONFactoryUtil.looseSerialize(dossier));
			
			PaymentFile paymentFile = PaymentFileLocalServiceUtil.getPaymentFileByReferenceUid(dossier.getDossierId(), paymentFileUUid);
			
			PaymentFileActions actions = new PaymentFileActionsImpl();
			
			// Change payment Status = 5
			actions.updateFileConfirm(paymentFile.getGroupId(), paymentFile.getDossierId(), paymentFile.getReferenceUid(), StringPool.BLANK, "Keypay", JSONFactoryUtil.createJSONObject().toJSONString(), serviceContext);
			
			JSONObject result = JSONFactoryUtil.createJSONObject();
			result.put("dossierNo", dossier.getDossierNo());
			result.put("serviceName", dossier.getServiceName());
			result.put("govAgencyName", dossier.getGovAgencyName());
			result.put("paymentFee", paymentFile.getPaymentFee());
			result.put("paymentAmount", paymentFile.getFeeAmount());
			result.put("paymentPortal", "KEYPAY");
			
			return Response.status(200).entity(result.toJSONString()).build();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			_log.debug(e);
			return Response.noContent().build();
		}
		
	}
	
	protected Dossier getDossier(String id, long groupId) throws PortalException {
		// TODO update logic here
		long dossierId = GetterUtil.getLong(id);

		Dossier dossier = null;
		
		if (dossierId != 0) {
			dossier = DossierLocalServiceUtil.fetchDossier(dossierId);
		}

		if (Validator.isNull(dossier)) {
			dossier = DossierLocalServiceUtil.getByRef(groupId, id);
		}

		return dossier;
	}
	
	Log _log = LogFactoryUtil.getLog(PaymentFileManagementImpl.class);

	//LamTV_Process new API Payment
	@Override
	public Response getPaymentFileByDossierId(HttpServletRequest request, HttpHeaders header, Company company,
			Locale locale, User user, ServiceContext serviceContext, String id) {

		long groupId = GetterUtil.getLong(header.getHeaderString("groupId"));
		long dossierId = GetterUtil.getLong(id);

		BackendAuth auth = new BackendAuthImpl();

		try {
			// Check user is login
			if (!auth.isAuth(serviceContext)) {
				throw new UnauthenticationException();
			}

			if (dossierId == 0) {
				Dossier dossier = DossierLocalServiceUtil.getByRef(groupId, id);
				if (dossier != null) {
					dossierId = dossier.getDossierId();
				}
			}

			PaymentFile paymentFile = PaymentFileLocalServiceUtil.getByDossierId(groupId, dossierId);

			PaymentFileModel result = PaymentFileUtils.mappingToPaymentFileModel(paymentFile);

			return Response.status(200).entity(result).build();

		} catch (Exception e) {
			return BusinessExceptionImpl.processException(e);
		}
	}

	@Override
	public Response createPaymentFileByDossierId(HttpServletRequest request, HttpHeaders header, Company company,
			Locale locale, User user, ServiceContext serviceContext, String id, PaymentFileInputModel input) {
		long groupId = GetterUtil.getLong(header.getHeaderString("groupId"));
		
		try {
			_log.info("======input payment create=============" + input);
			PaymentFile paymentFile = CPSDossierBusinessLocalServiceUtil.createPaymentFileByDossierId(groupId, serviceContext, id, PaymentFileUtils.convertFormModelToInputModel(input));		

			PaymentFileInputModel result = PaymentFileUtils.mappingToPaymentFileInputModel(paymentFile);

			return Response.status(200).entity(result).build();

		} catch (Exception e) {
			return BusinessExceptionImpl.processException(e);
		}	
	}

	@Override
	public Response previewInvoiceFile(HttpServletRequest request, HttpHeaders header, Company company, Locale locale,
			User user, ServiceContext serviceContext, String id, String referenceUid) {
		BackendAuth auth = new BackendAuthImpl();
		
		long groupId = GetterUtil.getLong(header.getHeaderString("groupId"));

		try {

			if (!auth.isAuth(serviceContext)) {
				throw new UnauthenticationException();
			}

			Dossier dossier = getDossier(id, groupId);

			if (dossier != null) {
				PaymentFileActions action = new PaymentFileActionsImpl();
				PaymentFile paymentFile = action.getPaymentFileByReferenceUid(dossier.getDossierId(), referenceUid);
				PaymentConfig paymentConfig = PaymentConfigLocalServiceUtil.getByInvoiceTemplateNo(groupId, paymentFile.getInvoiceTemplateNo());
				
				//String formData = JSONFactoryUtil.looseSerialize(paymentFile);
				JSONObject jsonData = JSONFactoryUtil.createJSONObject(JSONFactoryUtil.looseSerialize(paymentFile));
				String formReport = paymentConfig.getInvoiceForm();

				//ObjectMapper mapper = new ObjectMapper();
				//Map<String, String> map = (Map<String, String>) mapper.readValue(formData, Map.class);

				jsonData.put("applicantName", dossier.getApplicantName());

				StringBuilder address = new StringBuilder();
				address.append(dossier.getAddress());
				address.append(", ");
				address.append(dossier.getWardName());
				address.append(", ");
				address.append(dossier.getDistrictName());
				address.append(", ");
				address.append(dossier.getCityName());

				jsonData.put("address", address.toString());
				jsonData.put("metaData", JSONFactoryUtil.createJSONObject(dossier.getMetaData()));
				jsonData.put(DossierTerm.GOV_AGENCY_CODE, dossier.getGovAgencyCode());
				jsonData.put(DossierTerm.GOV_AGENCY_NAME, dossier.getGovAgencyName());
				jsonData.put(DossierTerm.APPLICANT_ID_NO, dossier.getApplicantIdNo());
				jsonData.put(DossierTerm.APPLICANT_ID_TYPE, dossier.getApplicantIdType());
				jsonData.put(DossierTerm.APPLICANT_ID_DATE, APIDateTimeUtils.convertDateToString(dossier.getApplicantIdDate(),
						APIDateTimeUtils._NORMAL_PARTTERN));
				jsonData.put(DossierTerm.CITY_CODE, dossier.getCityCode());
				jsonData.put(DossierTerm.CITY_NAME, dossier.getCityName());
				jsonData.put(DossierTerm.DISTRICT_CODE, dossier.getDistrictCode());
				jsonData.put(DossierTerm.DISTRICT_NAME, dossier.getDistrictName());
				jsonData.put(DossierTerm.WARD_CODE, dossier.getWardCode());
				jsonData.put(DossierTerm.WARD_NAME, dossier.getWardName());
				jsonData.put(DossierTerm.DOSSIER_NO, dossier.getDossierNo());
				jsonData.put(DossierTerm.APPLICANT_NAME, dossier.getApplicantName());
				jsonData.put(DossierTerm.ADDRESS, dossier.getAddress());
				jsonData.put(DossierTerm.CONTACT_TEL_NO, dossier.getContactTelNo());
				jsonData.put(DossierTerm.CONTACT_EMAIL, dossier.getContactEmail());
				jsonData.put(DossierTerm.CONTACT_NAME, dossier.getContactName());
				jsonData.put(DossierTerm.DELEGATE_ADDRESS, dossier.getDelegateAddress());
				jsonData.put(DossierTerm.SERVICE_CODE, dossier.getServiceCode());
				jsonData.put(DossierTerm.SERVICE_NAME, dossier.getServiceName());
				// map.put(DossierTerm.SAMPLE_COUNT, dossier.getSampleCount());
				// map.put(DossierTerm.DURATION_UNIT, dossier.getDurationUnit());
				// map.put(DossierTerm.DURATION_COUNT, dossier.getDurationCount());
				jsonData.put(DossierTerm.SECRET_KEY, dossier.getPassword());
				jsonData.put(DossierTerm.RECEIVE_DATE,
						APIDateTimeUtils.convertDateToString(dossier.getReceiveDate(), APIDateTimeUtils._NORMAL_PARTTERN));
				jsonData.put(DossierTerm.DELEGATE_NAME, dossier.getDelegateName());
				jsonData.put(DossierTerm.DELEGATE_EMAIL, dossier.getDelegateEmail());
				jsonData.put(DossierTerm.DELEGATE_TELNO, dossier.getDelegateTelNo());
				jsonData.put(DossierTerm.DOSSIER_NAME, dossier.getDossierName());
				// map.put(DossierTerm.VIA_POSTAL, dossier.getViaPostal());
				jsonData.put(DossierTerm.POSTAL_ADDRESS, dossier.getPostalAddress());
				jsonData.put(DossierTerm.APPLICANT_NOTE, dossier.getApplicantNote());
				jsonData.put(DossierTerm.DOSSIER_COUNTER, dossier.getDossierCounter());

				String num = PaymentFileUtils.readNum(Long.toString(paymentFile.getPaymentAmount()));
				jsonData.put("numToWord", num);
				jsonData.put("invoiceTemplateNo", paymentConfig.getInvoiceTemplateNo());
				jsonData.put("invoiceIssueNo", paymentConfig.getInvoiceIssueNo());
				jsonData.put("govAgencyTaxNo", paymentConfig.getGovAgencyTaxNo());

				WorkingUnit workingUnit = WorkingUnitLocalServiceUtil.fetchByF_govAgencyCode(groupId,
						dossier.getGovAgencyCode());
				if (Validator.isNotNull(workingUnit)) {
					jsonData.put("govAddress", workingUnit.getAddress());
				} else {
					jsonData.put("govAddress", "");
				}

				//formData = mapper.writeValueAsString(map);
				_log.info("PREVIEW PAYMENTFILE FORMDATA ============================== " + jsonData);
				
				Message message = new Message();

				message.put("formReport", formReport);

				message.put("formData", jsonData.toJSONString());

				message.setResponseId(String.valueOf(dossier.getPrimaryKeyObj()));
				message.setResponseDestinationName("jasper/engine/preview/callback");

				try {
					String previewResponse = (String) MessageBusUtil
							.sendSynchronousMessage("jasper/engine/preview/destination", message, 10000);

					if (Validator.isNotNull(previewResponse)) {
						// jsonObject =
						// JSONFactoryUtil.createJSONObject(previewResponse);
					}

					// String fileDes = jsonObject.getString("fileDes");

					File file = new File(previewResponse);

					ResponseBuilder responseBuilder = Response.ok((Object) file);

					responseBuilder.header("Content-Disposition",
								"attachment; filename=\"" + file.getName() + "\"");
					responseBuilder.header("Content-Type", "application/pdf");

					return responseBuilder.build();

				} catch (MessageBusException e) {
					_log.error(e);
					throw new Exception("Preview rendering not available");
				}
			} else {
				throw new Exception("Cant get dossier with id_" + id);
			}

		} catch (Exception e) {
			return BusinessExceptionImpl.processException(e);
		}		
	}

	@Override
	public Response updateByPaymentFileId(HttpServletRequest request, HttpHeaders header, Company company,
			Locale locale, User user, ServiceContext serviceContext, String id, PaymentFileInputModel input) {
		
		long groupId = GetterUtil.getLong(header.getHeaderString("groupId"));
		BackendAuth auth = new BackendAuthImpl();

		try {
			Dossier dossier = getDossier(id, groupId);
			if (!auth.isAuth(serviceContext)) {
				throw new UnauthenticationException();
			}
			long dossierId = dossier.getDossierId();
	
			PaymentFile paymentFile = PaymentFileLocalServiceUtil.getByDossierId(groupId, dossierId);
			String referenceUid = input.getReferenceUid();
			if (Validator.isNull(referenceUid)) {
				referenceUid = PortalUUIDUtil.generate();
			}
	
			if (Validator.isNotNull(input.getInvoiceTemplateNo())) {
				paymentFile.setInvoiceTemplateNo(input.getInvoiceTemplateNo());
			}
			if(Validator.isNotNull(input.getConfirmFileEntryId())){
				paymentFile.setConfirmFileEntryId(input.getConfirmFileEntryId());
			}
			if(Validator.isNotNull(input.getPaymentStatus())){
				paymentFile.setPaymentStatus(input.getPaymentStatus());
			}
			if(Validator.isNotNull(input.getEinvoice())) {
				paymentFile.setEinvoice(input.getEinvoice());
			}
			if(Validator.isNotNull(input.getPaymentAmount())) {
				paymentFile.setPaymentAmount(input.getPaymentAmount());
			}
			if(Validator.isNotNull(input.getPaymentMethod())){
				paymentFile.setPaymentMethod(input.getPaymentMethod());
			}
			if(Validator.isNotNull(input.getServiceAmount())){
				paymentFile.setServiceAmount(input.getServiceAmount());
			}
			if(Validator.isNotNull(input.getShipAmount())){
				paymentFile.setShipAmount(input.getShipAmount());
			}
			if(Validator.isNotNull(input.getAdvanceAmount())){
				paymentFile.setAdvanceAmount(input.getAdvanceAmount());
			}
			if(Validator.isNotNull(input.getFeeAmount())){
				paymentFile.setFeeAmount(input.getFeeAmount());
			}
			if(Validator.isNotNull(input.getPaymentNote())){
				paymentFile.setPaymentNote(input.getPaymentNote());
			}
			//Update Invoice File EntryId
			//PaymentFileActions action = new PaymentFileActionsImpl();
			//PaymentFile paymentFile = action.getPaymentFileByReferenceUid(dossier.getDossierId(), referenceUid);
			PaymentConfig paymentConfig = PaymentConfigLocalServiceUtil.getByInvoiceTemplateNo(groupId, paymentFile.getInvoiceTemplateNo());
			
			//String formData = JSONFactoryUtil.looseSerialize(paymentFile);
			JSONObject jsonData = JSONFactoryUtil.createJSONObject(JSONFactoryUtil.looseSerialize(paymentFile));
			String formReport = paymentConfig.getInvoiceForm();

			//ObjectMapper mapper = new ObjectMapper();
			//Map<String, String> map = (Map<String, String>) mapper.readValue(formData, Map.class);

			jsonData.put("applicantName", dossier.getApplicantName());

			StringBuilder address = new StringBuilder();
			address.append(dossier.getAddress());
			address.append(", ");
			address.append(dossier.getWardName());
			address.append(", ");
			address.append(dossier.getDistrictName());
			address.append(", ");
			address.append(dossier.getCityName());

			jsonData.put("address", address.toString());
			//
			jsonData.put("metaData", JSONFactoryUtil.createJSONObject(dossier.getMetaData()));
			jsonData.put(DossierTerm.GOV_AGENCY_CODE, dossier.getGovAgencyCode());
			jsonData.put(DossierTerm.GOV_AGENCY_NAME, dossier.getGovAgencyName());
			jsonData.put(DossierTerm.APPLICANT_ID_NO, dossier.getApplicantIdNo());
			jsonData.put(DossierTerm.APPLICANT_ID_TYPE, dossier.getApplicantIdType());
			jsonData.put(DossierTerm.APPLICANT_ID_DATE, APIDateTimeUtils.convertDateToString(dossier.getApplicantIdDate(),
					APIDateTimeUtils._NORMAL_PARTTERN));
			jsonData.put(DossierTerm.CITY_CODE, dossier.getCityCode());
			jsonData.put(DossierTerm.CITY_NAME, dossier.getCityName());
			jsonData.put(DossierTerm.DISTRICT_CODE, dossier.getDistrictCode());
			jsonData.put(DossierTerm.DISTRICT_NAME, dossier.getDistrictName());
			jsonData.put(DossierTerm.WARD_CODE, dossier.getWardCode());
			jsonData.put(DossierTerm.WARD_NAME, dossier.getWardName());
			jsonData.put(DossierTerm.DOSSIER_NO, dossier.getDossierNo());
			jsonData.put(DossierTerm.APPLICANT_NAME, dossier.getApplicantName());
			jsonData.put(DossierTerm.ADDRESS, dossier.getAddress());
			jsonData.put(DossierTerm.CONTACT_TEL_NO, dossier.getContactTelNo());
			jsonData.put(DossierTerm.CONTACT_EMAIL, dossier.getContactEmail());
			jsonData.put(DossierTerm.CONTACT_NAME, dossier.getContactName());
			jsonData.put(DossierTerm.DELEGATE_ADDRESS, dossier.getDelegateAddress());
			jsonData.put(DossierTerm.SERVICE_CODE, dossier.getServiceCode());
			jsonData.put(DossierTerm.SERVICE_NAME, dossier.getServiceName());
			// map.put(DossierTerm.SAMPLE_COUNT, dossier.getSampleCount());
			// map.put(DossierTerm.DURATION_UNIT, dossier.getDurationUnit());
			// map.put(DossierTerm.DURATION_COUNT, dossier.getDurationCount());
			jsonData.put(DossierTerm.SECRET_KEY, dossier.getPassword());
			jsonData.put(DossierTerm.RECEIVE_DATE,
					APIDateTimeUtils.convertDateToString(dossier.getReceiveDate(), APIDateTimeUtils._NORMAL_PARTTERN));
			jsonData.put(DossierTerm.DELEGATE_NAME, dossier.getDelegateName());
			jsonData.put(DossierTerm.DELEGATE_EMAIL, dossier.getDelegateEmail());
			jsonData.put(DossierTerm.DELEGATE_TELNO, dossier.getDelegateTelNo());
			jsonData.put(DossierTerm.DOSSIER_NAME, dossier.getDossierName());
			// map.put(DossierTerm.VIA_POSTAL, dossier.getViaPostal());
			jsonData.put(DossierTerm.POSTAL_ADDRESS, dossier.getPostalAddress());
			jsonData.put(DossierTerm.APPLICANT_NOTE, dossier.getApplicantNote());
			jsonData.put(DossierTerm.DOSSIER_COUNTER, dossier.getDossierCounter());

			String num = PaymentFileUtils.readNum(Long.toString(paymentFile.getPaymentAmount()));
			jsonData.put("numToWord", num);
			jsonData.put("invoiceTemplateNo", paymentConfig.getInvoiceTemplateNo());
			jsonData.put("invoiceIssueNo", paymentConfig.getInvoiceIssueNo());
			jsonData.put("govAgencyTaxNo", paymentConfig.getGovAgencyTaxNo());

			WorkingUnit workingUnit = WorkingUnitLocalServiceUtil.fetchByF_govAgencyCode(groupId,
					dossier.getGovAgencyCode());
			if (Validator.isNotNull(workingUnit)) {
				jsonData.put("govAddress", workingUnit.getAddress());
			} else {
				jsonData.put("govAddress", "");
			}

			//formData = mapper.writeValueAsString(map);
			_log.info("PREVIEW PAYMENTFILE FORMDATA ============================== " + JSONFactoryUtil.looseSerialize(jsonData));

			Message message = new Message();

			JSONObject msgData = JSONFactoryUtil.createJSONObject();
			msgData.put("className", PaymentFile.class.getName());
			msgData.put("classPK", paymentFile.getPaymentFileId());
			msgData.put("jrxmlTemplate", formReport);
			msgData.put("formData", jsonData.toJSONString());
			msgData.put("userId", serviceContext.getUserId());
			
			message.put("msgToEngine", msgData);
			MessageBusUtil.sendMessage("jasper/engine/out/destination", message);
			
			//message.put("formReport", formReport);
			//message.put("formData", formData);
			//message.setResponseId(String.valueOf(dossier.getPrimaryKeyObj()));
			//message.setResponseDestinationName("jasper/engine/preview/callback");

			paymentFile = PaymentFileLocalServiceUtil.updatePaymentFile(paymentFile);
			//
			PaymentFileInputModel result = PaymentFileUtils.mappingToPaymentFileInputModel(paymentFile);
	
			return Response.status(200).entity(result).build();
		} catch (Exception e) {
			return BusinessExceptionImpl.processException(e);
		}
	}
	
	@Override
	public Response downloadInvoiceFileDVCQG(HttpServletRequest request, HttpHeaders header, Company company, Locale locale,
			User user, ServiceContext serviceContext, String id, String referenceUid) {
		BackendAuth auth = new BackendAuthImpl();

		long dossierId = GetterUtil.getLong(id);

		// TODO get Dossier by referenceUid if dossierId = 0
		// String referenceUid = dossierId == 0 ? id : StringPool.BLANK;

		try {

			if (!auth.isAuth(serviceContext)) {
				throw new UnauthenticationException();
			}

			PaymentFileActions action = new PaymentFileActionsImpl();
			PaymentFile paymentFile = action.getPaymentFileByReferenceUid(dossierId, referenceUid);

			_log.info(PaymentFileTerm.PAYMENT_METHOD_PAY_PLAT_DVCQG +"===========dossierId, referenceUid=======" + dossierId + referenceUid);
			_log.info("===========paymentFile=======" + paymentFile);
			if (paymentFile != null && paymentFile.getInvoiceFileEntryId() > 0) {

				FileEntry fileEntry = DLAppLocalServiceUtil.getFileEntry(paymentFile.getInvoiceFileEntryId());

				File file = DLFileEntryLocalServiceUtil.getFile(fileEntry.getFileEntryId(), fileEntry.getVersion(),
						true);

				ResponseBuilder responseBuilder = Response.ok((Object) file);

				responseBuilder.header("Content-Disposition",
						"attachment; filename=\"" + fileEntry.getFileName() + "\"");
				responseBuilder.header("Content-Type", fileEntry.getMimeType());

				return responseBuilder.build();

			} else if (paymentFile != null && PaymentFileTerm.PAYMENT_METHOD_PAY_PLAT_DVCQG.equals(paymentFile.getPaymentMethod())){
				
				JSONObject payload = JSONFactoryUtil.createJSONObject(paymentFile.getConfirmPayload());
				String url = payload.getString("UrlBienLai");
				JSONObject ppConfig = JSONFactoryUtil.createJSONObject(paymentFile.getEpaymentProfile())
						.getJSONObject(KeyPayTerm.PP_DVCGQ_CONFIG);
				if (Validator.isNull(url)) {

					InvokeREST callRest = new InvokeREST();
					HashMap<String, String> properties = new HashMap<String, String>();
					Map<String, Object> params = new HashMap<>();
					Dossier dossier = DossierLocalServiceUtil.getDossier(dossierId);
					params.put("dossierNo", dossier.getDossierNo());

					String baseUrl = ppConfig.getJSONObject("actionIsOnline").getString("url");//RESTFulConfiguration.SERVER_PATH_BASE;
					JSONObject resultObj = callRest.callPostAPI(
						0, HttpMethod.POST, "application/json", baseUrl, "/o/pgi/ppdvcqg/bills",
						"",
						"", properties, params,
						new ServiceContext());

					JSONObject eInvoice = JSONFactoryUtil.createJSONObject(
						resultObj.getString(RESTFulConfiguration.MESSAGE));
					
					_log.info("eInvoice toJSONString: " + eInvoice.toJSONString());
					
					url = eInvoice.getString("UrlBienLai");
				}
				String ssEndpoint = ppConfig.getString("ss_endpoint");
				String ssEndpointTerm = ppConfig.getString("ss_endpoint_term");
				url = StringUtils.replaceOnce(url, ssEndpointTerm, ssEndpoint);
				_log.info("endpoint get invoice: " + url);
				
				InputStream file = ConvertDossierFromV1Dot9Utils.getFileFromDVCOld(url);
				return Response.ok(file).header(
							"Content-Disposition", "attachment; filename=\"" + new Date().getTime() + ".pdf" + "\"").build();
			} else {
				return Response.status(HttpURLConnection.HTTP_NO_CONTENT).build();
			}

		} catch (Exception e) {
			return BusinessExceptionImpl.processException(e);
		}
	}
}
