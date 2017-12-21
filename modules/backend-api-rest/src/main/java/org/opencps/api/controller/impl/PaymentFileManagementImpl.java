package org.opencps.api.controller.impl;

import java.io.File;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;

import javax.activation.DataHandler;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

import org.apache.commons.httpclient.util.HttpURLConnection;
import org.apache.cxf.jaxrs.ext.multipart.Attachment;
import org.opencps.api.controller.PaymentFileManagement;
import org.opencps.api.controller.exception.ErrorMsg;
import org.opencps.api.controller.util.PaymentFileUtils;
import org.opencps.api.paymentfile.model.PaymentFileInputModel;
import org.opencps.api.paymentfile.model.PaymentFileModel;
import org.opencps.api.paymentfile.model.PaymentFileResultModel;
import org.opencps.api.paymentfile.model.PaymentFileSearchModel;
import org.opencps.api.paymentfile.model.PaymentFileSearchResultModel;
import org.opencps.auth.api.BackendAuth;
import org.opencps.auth.api.BackendAuthImpl;
import org.opencps.auth.api.exception.UnauthenticationException;
import org.opencps.auth.api.exception.UnauthorizationException;
import org.opencps.auth.api.keys.ActionKeys;
import org.opencps.dossiermgt.action.PaymentFileActions;
import org.opencps.dossiermgt.action.impl.PaymentFileActionsImpl;
import org.opencps.dossiermgt.constants.PaymentFileTerm;
import org.opencps.dossiermgt.model.PaymentConfig;
import org.opencps.dossiermgt.model.PaymentFile;

import com.liferay.document.library.kernel.service.DLAppLocalServiceUtil;
import com.liferay.document.library.kernel.service.DLFileEntryLocalServiceUtil;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
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

public class PaymentFileManagementImpl implements PaymentFileManagement {

	/**
	 * Get all PaymentFile of DossierId
	 * 
	 * @param dossierId
	 * @return Response
	 */
	@SuppressWarnings("unchecked")
	@Override
	public Response getPaymentFilesByDossierId(HttpServletRequest request, HttpHeaders header, Company company,
			Locale locale, User user, ServiceContext serviceContext, String id, PaymentFileSearchModel search) {

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

			long groupId = GetterUtil.getLong(header.getHeaderString("groupId"));
			long dossierId = GetterUtil.getLong(id);

			// Default sort by modifiedDate
			Sort[] sorts = new Sort[] {
					SortFactoryUtil.create(Field.MODIFIED_DATE + "_sortable", Sort.STRING_TYPE, true) };

			if (Validator.isNotNull(search.getSort()) && Validator.isNotNull(search.getOrder())) {
				sorts = new Sort[] { SortFactoryUtil.create(search.getSort() + "_sortable", Sort.STRING_TYPE,
						GetterUtil.getBoolean(search.getOrder())) };
			}

			PaymentFileActions actions = new PaymentFileActionsImpl();
			PaymentFileResultModel results = new PaymentFileResultModel();

			// get JSON data by dossierId
			JSONObject jsonData = actions.getByDossierId(dossierId, serviceContext.getCompanyId(), groupId, sorts,
					search.getStart(), search.getEnd(), serviceContext);

			// Parse JSONObejct to PaymentFileResultModel Object
			results.setTotal(jsonData.getInt("total"));
			results.getData().addAll(PaymentFileUtils.mappingToPaymentFileModel((List<Document>) jsonData.get("data")));

			return Response.status(200).entity(results).build();

		} catch (Exception e) {
			ErrorMsg error = new ErrorMsg();

			error.setMessage("not found!");
			error.setCode(404);
			error.setDescription("not found!");

			return Response.status(404).entity(error).build();
		}
	}

	/**
	 * Create PaymentFile of DossierId
	 * 
	 * @param form
	 *            params
	 * @return Response
	 */
	@Override
	public Response createPaymentFileByDossierId(HttpServletRequest request, HttpHeaders header, Company company,
			Locale locale, User user, ServiceContext serviceContext, String id, PaymentFileInputModel input) {

		long groupId = GetterUtil.getLong(header.getHeaderString("groupId"));

		long userId = serviceContext.getUserId();

		long dossierId = GetterUtil.getLong(id);

		// TODO get Dossier by referenceUid if dossierId = 0
		// String referenceUid = dossierId == 0 ? id : StringPool.BLANK;

		BackendAuth auth = new BackendAuthImpl();

		try {
			/* Check user is login - START */
			if (!auth.isAuth(serviceContext)) {
				throw new UnauthenticationException();
			}

			if (!auth.hasResource(serviceContext, PaymentFile.class.getName(), ActionKeys.ADD_ENTRY)) {
				throw new UnauthorizationException();
			}
			/* Check user is login - END */

			PaymentFileActions actions = new PaymentFileActionsImpl();

			PaymentFileInputModel PaymentFileInput = new PaymentFileInputModel();

			PaymentFile paymentFile = actions.createPaymentFile(userId, groupId, dossierId, input.getReferenceUid(),
					input.getGovAgencyCode(), input.getGovAgencyName(), input.getApplicantName(),
					input.getApplicantIdNo(), input.getPaymentFee(), input.getPaymentAmount(), input.getPaymentNote(),
					input.getEpaymentProfile(), input.getBankInfo(), serviceContext);

			PaymentFileInput = PaymentFileUtils.mappingToPaymentFileInputModel(paymentFile);

			return Response.status(200).entity(PaymentFileInput).build();

		} catch (Exception e) {
			return processException(e);
		}
	}

	/**
	 * Get detail PaymentFile of DossierId and referenceUid
	 * 
	 * @param dossierId,
	 * @param referenceUid
	 * @return Response
	 */
	@Override
	public Response getPaymentFileByReferenceUid(HttpServletRequest request, HttpHeaders header, Company company,
			Locale locale, User user, ServiceContext serviceContext, Long id, String referenceUid) {

		// long groupId = GetterUtil.getLong(header.getHeaderString("groupId"));
		long dossierId = GetterUtil.getLong(id);

		// TODO get Dossier by referenceUid if dossierId = 0
		// String referenceUid = dossierId == 0 ? id : StringPool.BLANK;
		BackendAuth auth = new BackendAuthImpl();

		try {

			// Check user is login
			if (!auth.isAuth(serviceContext)) {
				throw new UnauthenticationException();
			}
			// Search full query
			// int start = -1;
			// int end = -1;

			// TODO: Condition sort
			// Sort[] sorts = new Sort[] {};
			// Sort[] sorts = new Sort[] {SortFactoryUtil.create(query.getSort()
			// + "_sortable",Sort.STRING_TYPE,
			// GetterUtil.getBoolean(query.getOrder())) };

			PaymentFileActions actions = new PaymentFileActionsImpl();
			// PaymentFileResultModel PaymentFileDetail = new
			// PaymentFileResultModel();
			// PaymentFile paymentFile = actions.getPaymentFileDetail(dossierId,
			// referenceUid);

			// get JSON data by dossierId

			// List<Document> data = actions.getPaymentFileDetail(dossierId,
			// referenceUid, serviceContext.getCompanyId(),
			// groupId, sorts, start, end, serviceContext);

			// List<PaymentFileModel> paymentFileDetail =
			// PaymentFileUtils.mappingToPaymentFileModel(data);
			// dossierPermission.hasGetDetailDossier(groupId, user.getUserId(),
			// dossier, option.getServiceProcessId());

			// DossierDetailModel result =
			// DossierUtils.mappingForGetDetail(dossier);
			// PaymentFileModel results = paymentFileDetail.get(0);

			PaymentFile paymentFile = actions.getPaymentFile(dossierId, referenceUid);

			PaymentFileModel result = PaymentFileUtils.mappingToPaymentFileModel(paymentFile);

			return Response.status(200).entity(result).build();

		} catch (Exception e) {
			return processException(e);
		}
	}

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

			// TODO get Dossier by referenceUid if dossierId = 0
			// String referenceUid = dossierId == 0 ? id : StringPool.BLANK;

			PaymentFile paymentFile = actions.getPaymentFile(dossierId, referenceUid);

			String result = paymentFile.getEpaymentProfile();

			// TODO process result before response

			System.out.println("/////////////////////////////////// result " + result);

			return Response.status(200).entity(null).build();

		} catch (Exception e) {
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
	@Override
	public Response updateEpaymentProfile(HttpServletRequest request, HttpHeaders header, Company company,
			Locale locale, User user, ServiceContext serviceContext, String id, String referenceUid, JSONObject input) {
		BackendAuth auth = new BackendAuthImpl();

		try {
			if (!auth.isAuth(serviceContext)) {
				throw new UnauthenticationException();
			}

			if (!auth.hasResource(serviceContext, PaymentConfig.class.getName(), ActionKeys.ADD_ENTRY)) {
				throw new UnauthorizationException();
			}

			long dossierId = GetterUtil.getLong(id);
			PaymentFileActions actions = new PaymentFileActionsImpl();

			PaymentFile paymentFile = actions.updateEProfile(dossierId, referenceUid, input.toString(), serviceContext);

			String result = paymentFile.getEpaymentProfile();

			return Response.status(200).entity(result).build();

		} catch (Exception e) {
			return processException(e);
		}
	}

	/**
	 * Update Payment File Confirm of DossierId and referenceUid
	 * 
	 * @param form
	 *            params
	 * @return Response
	 */
	@Override
	public Response updatePaymentFileConfirm(HttpServletRequest request, HttpHeaders header, Company company,
			Locale locale, User user, ServiceContext serviceContext, String id, String referenceUid, Attachment file,
			String confirmNote, String paymentMethod, String confirmPayload) {

		BackendAuth auth = new BackendAuthImpl();

		long groupId = GetterUtil.getLong(header.getHeaderString("groupId"));

		long dossierId = GetterUtil.getLong(id);

		// TODO get Dossier by referenceUid if dossierId = 0
		// String referenceUid = dossierId == 0 ? id : StringPool.BLANK;

		try {

			if (!auth.isAuth(serviceContext)) {
				throw new UnauthenticationException();
			}

			DataHandler dataHandler = file.getDataHandler();

			PaymentFileActions action = new PaymentFileActionsImpl();

			PaymentFile paymentFile = action.updateFileConfirm(groupId, dossierId, referenceUid, confirmNote,
					paymentMethod, confirmPayload, dataHandler.getName(), 0L, dataHandler.getInputStream(),
					serviceContext);
			
			System.out.println("/////////////////////////////////// Done " + paymentFile.getPaymentFileId());

			PaymentFileModel result = PaymentFileUtils.mappingToPaymentFileModel(paymentFile);

			return Response.status(200).entity(result).build();

		} catch (Exception e) {
			return processException(e);
		}
	}

	/**
	 * Update Payment File Approval of DossierId and referenceUid
	 * 
	 * @param form
	 *            params
	 * @return Response
	 */
	@Override
	public Response updatePaymentFileApproval(HttpServletRequest request, HttpHeaders header, Company company,
			Locale locale, User user, ServiceContext serviceContext, long id, String referenceUid, Attachment file,
			String approveDatetime, String accountUserName, String govAgencyTaxNo, String invoiceTemplateNo,
			String invoiceIssueNo, String invoiceNo) {
		BackendAuth auth = new BackendAuthImpl();
		long groupId = GetterUtil.getLong(header.getHeaderString("groupId"));

		try {

			if (!auth.isAuth(serviceContext)) {
				throw new UnauthenticationException();
			}

			DataHandler dataHandler = file.getDataHandler();

			PaymentFileActions action = new PaymentFileActionsImpl();

			PaymentFile paymentFile = action.updateFileApproval(groupId, id, referenceUid, approveDatetime,
					accountUserName, govAgencyTaxNo, invoiceTemplateNo, invoiceIssueNo, invoiceNo,
					dataHandler.getName(), 0L, dataHandler.getInputStream(), serviceContext);

			PaymentFileModel result = PaymentFileUtils.mappingToPaymentFileModel(paymentFile);

			return Response.status(200).entity(result).build();

		} catch (Exception e) {
			return processException(e);
		}
	}

	/**
	 * Download payment File Confirm
	 * 
	 * @param
	 * @return Response
	 */
	@Override
	public Response downloadConfirmFile(HttpServletRequest request, HttpHeaders header, Company company, Locale locale,
			User user, ServiceContext serviceContext, Long id, String referenceUid) {

		BackendAuth auth = new BackendAuthImpl();

		try {

			if (!auth.isAuth(serviceContext)) {
				throw new UnauthenticationException();
			}

			PaymentFileActions action = new PaymentFileActionsImpl();
			PaymentFile paymentFile = action.getPaymentFileByReferenceUid(id, referenceUid);

			if (paymentFile.getConfirmFileEntryId() > 0) {
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
			return processException(e);
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
			User user, ServiceContext serviceContext, Long id, String referenceUid) {
		BackendAuth auth = new BackendAuthImpl();

		try {
			System.out.println("////////////////////////////////// " + 1);
			if (!auth.isAuth(serviceContext)) {
				throw new UnauthenticationException();
			}

			PaymentFileActions action = new PaymentFileActionsImpl();

			System.out.println("////////////////////////////////// id" + id);

			System.out.println("////////////////////////////////// referenceUid" + referenceUid);

			PaymentFile paymentFile = action.getPaymentFileByReferenceUid(id, referenceUid);

			System.out.println("////////////////////////////////// paymentFile" + paymentFile.getDossierId());

			if (paymentFile.getInvoiceFileEntryId() > 0) {
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
			return processException(e);
		}
	}

	/**
	 * Get all Payment File
	 * 
	 * @param
	 * @return Response
	 */
	@SuppressWarnings("unchecked")
	@Override
	public Response getPaymentFiles(HttpServletRequest request, HttpHeaders header, Company company, Locale locale,
			User user, ServiceContext serviceContext, PaymentFileSearchModel search) {

		BackendAuth auth = new BackendAuthImpl();

		try {
			// Check user is login
			if (!auth.isAuth(serviceContext)) {
				throw new UnauthenticationException();
			}
			/* Search full Query -START */
			if (search.getEnd() == 0) {
				search.setStart(-1);
				search.setEnd(-1);
			}
			/* Search full Query -END */
			long groupId = GetterUtil.getLong(header.getHeaderString("groupId"));

			PaymentFileSearchResultModel results = new PaymentFileSearchResultModel();
			PaymentFileActions action = new PaymentFileActionsImpl();

			/* Add params in Map - START */
			LinkedHashMap<String, Object> params = new LinkedHashMap<String, Object>();
			params.put(Field.GROUP_ID, String.valueOf(groupId));
			params.put(Field.KEYWORD_SEARCH, search.getKeyword());
			params.put(PaymentFileTerm.SERVICE, search.getService());
			params.put(PaymentFileTerm.AGENCY, search.getAgency());
			params.put(PaymentFileTerm.STATUS, search.getStatus());
			/* Add params in Map - END */

			// Default sort by modifiedDate
			Sort[] sorts = new Sort[] {
					SortFactoryUtil.create(Field.MODIFIED_DATE + "_sortable", Sort.STRING_TYPE, true) };

			if (Validator.isNotNull(search.getSort()) && Validator.isNotNull(search.getOrder())) {
				sorts = new Sort[] { SortFactoryUtil.create(search.getSort() + "_sortable", Sort.STRING_TYPE,
						GetterUtil.getBoolean(search.getOrder())) };
			}

			JSONObject paymentFileJsonObject = action.getPaymentFiles(serviceContext.getUserId(),
					serviceContext.getCompanyId(), groupId, params, sorts, search.getStart(), search.getEnd(),
					serviceContext);

			List<Document> documents = (List<Document>) paymentFileJsonObject.get("data");

			results.setTotal(paymentFileJsonObject.getInt("total"));

			results.getData().addAll(PaymentFileUtils.mappingToPaymentFileSearchResultModel(documents));

			return Response.status(200).entity(results).build();

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
