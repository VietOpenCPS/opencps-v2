/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package org.opencps.dossiermgt.service.impl;

import com.liferay.counter.kernel.service.CounterLocalServiceUtil;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.json.JSONException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.search.BooleanClauseOccur;
import com.liferay.portal.kernel.search.BooleanQuery;
import com.liferay.portal.kernel.search.BooleanQueryFactoryUtil;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.search.Hits;
import com.liferay.portal.kernel.search.IndexSearcherHelperUtil;
import com.liferay.portal.kernel.search.Indexable;
import com.liferay.portal.kernel.search.IndexableType;
import com.liferay.portal.kernel.search.Indexer;
import com.liferay.portal.kernel.search.IndexerRegistryUtil;
import com.liferay.portal.kernel.search.ParseException;
import com.liferay.portal.kernel.search.SearchContext;
import com.liferay.portal.kernel.search.SearchException;
import com.liferay.portal.kernel.search.Sort;
import com.liferay.portal.kernel.search.generic.BooleanQueryImpl;
import com.liferay.portal.kernel.search.generic.MultiMatchQuery;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.Validator;

import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;

import org.opencps.dossiermgt.action.FileUploadUtils;
import org.opencps.dossiermgt.constants.DossierTerm;
import org.opencps.dossiermgt.constants.PaymentFileTerm;
import org.opencps.dossiermgt.constants.ProcessActionTerm;
import org.opencps.dossiermgt.exception.NoSuchPaymentFileException;
import org.opencps.dossiermgt.model.Dossier;
import org.opencps.dossiermgt.model.PaymentFile;
import org.opencps.dossiermgt.service.DossierLocalServiceUtil;
import org.opencps.dossiermgt.service.base.PaymentFileLocalServiceBaseImpl;

import aQute.bnd.annotation.ProviderType;

/**
 * The implementation of the payment file local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are
 * added, rerun ServiceBuilder to copy their definitions into the
 * {@link org.opencps.dossiermgt.service.PaymentFileLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security
 * checks based on the propagated JAAS credentials because this service can only
 * be accessed from within the same VM.
 * </p>
 *
 * @author huymq
 * @see PaymentFileLocalServiceBaseImpl
 * @see org.opencps.dossiermgt.service.PaymentFileLocalServiceUtil
 */
@ProviderType
public class PaymentFileLocalServiceImpl extends PaymentFileLocalServiceBaseImpl {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this class directly. Always use {@link
	 * org.opencps.dossiermgt.service.PaymentFileLocalServiceUtil} to access the
	 * payment file local service.
	 */

	public PaymentFile fectPaymentFile(long dossierId, String refId) {
		return paymentFilePersistence.fetchByD_RUID(dossierId, refId);
	}

	/**
	 * Get list payment File using SearchLucene
	 * 
	 * @param
	 * @return Hits
	 */
	@SuppressWarnings("deprecation")
	public Hits searchLucene(LinkedHashMap<String, Object> params, Sort[] sorts, int start, int end,
			SearchContext searchContext) throws ParseException, SearchException {

		Indexer<PaymentFile> indexer = IndexerRegistryUtil.nullSafeGetIndexer(PaymentFile.class);

		searchContext.addFullQueryEntryClassName(CLASS_NAME);
		searchContext.setEntryClassNames(new String[] { CLASS_NAME });
		searchContext.setAttribute("paginationType", "regular");
		searchContext.setLike(true);
		searchContext.setStart(start);
		searchContext.setEnd(end);
		searchContext.setAndSearch(true);
		searchContext.setSorts(sorts);

		String keywords = (String) params.get(Field.KEYWORD_SEARCH);
		String groupId = (String) params.get(Field.GROUP_ID);
		String dossierId = (String) params.get(DossierTerm.DOSSIER_ID);
		String referenceUid = (String) params.get(PaymentFileTerm.REFERENCE_UID);
		String isNew = (String) params.get(PaymentFileTerm.IS_NEW);

		// Extra fields
		String service = String.valueOf((params.get(PaymentFileTerm.SERVICE)));
		String agency = GetterUtil.getString(params.get(PaymentFileTerm.AGENCY));
		String status = String.valueOf((params.get(PaymentFileTerm.STATUS)));

		BooleanQuery booleanQuery = null;

		if (Validator.isNotNull(keywords)) {
			booleanQuery = BooleanQueryFactoryUtil.create(searchContext);
		} else {
			booleanQuery = indexer.getFullQuery(searchContext);
		}

		if (Validator.isNotNull(keywords)) {

			String[] keyword = keywords.split(StringPool.SPACE);

			for (String string : keyword) {

				MultiMatchQuery query = new MultiMatchQuery(string);

				query.addFields(PaymentFileTerm.SERVICE_NAME, PaymentFileTerm.GOV_AGENCY_NAME);

				booleanQuery.add(query, BooleanClauseOccur.MUST);

			}
		}

		if (Validator.isNotNull(groupId)) {
			MultiMatchQuery query = new MultiMatchQuery(groupId);

			query.addFields(Field.GROUP_ID);

			booleanQuery.add(query, BooleanClauseOccur.MUST);

		}

		if (Validator.isNotNull(dossierId)) {
			MultiMatchQuery query = new MultiMatchQuery(dossierId);

			query.addFields(DossierTerm.DOSSIER_ID);

			booleanQuery.add(query, BooleanClauseOccur.MUST);

		}

		if (Validator.isNotNull(referenceUid)) {
			MultiMatchQuery query = new MultiMatchQuery(referenceUid);

			query.addFields(PaymentFileTerm.REFERENCE_UID);

			booleanQuery.add(query, BooleanClauseOccur.MUST);

		}

		if (Validator.isNotNull(service)) {
			MultiMatchQuery query = new MultiMatchQuery(service);

			query.addFields(PaymentFileTerm.SERVICE_CODE);

			booleanQuery.add(query, BooleanClauseOccur.MUST);
		}

		if (Validator.isNotNull(agency)) {
			MultiMatchQuery query = new MultiMatchQuery(agency);

			query.addFields(PaymentFileTerm.GOV_AGENCY_CODE);

			booleanQuery.add(query, BooleanClauseOccur.MUST);
		}

		if (Validator.isNotNull(status)) {
			String[] sliptStatus = status.split(StringPool.COMMA);
			if (sliptStatus != null && sliptStatus.length > 0) {
				BooleanQuery subQuery = new BooleanQueryImpl();
				for (String strStatus : sliptStatus) {
					if (Validator.isNotNull(strStatus)) {

						MultiMatchQuery query = new MultiMatchQuery(strStatus);

						query.addFields(PaymentFileTerm.PAYMENT_STATUS);
						subQuery.add(query, BooleanClauseOccur.SHOULD);
					}
				}
				booleanQuery.add(subQuery, BooleanClauseOccur.MUST);
			} else {
				MultiMatchQuery query = new MultiMatchQuery(status);

				query.addFields(PaymentFileTerm.PAYMENT_STATUS);

				booleanQuery.add(query, BooleanClauseOccur.MUST);
			}
		}

		if (Validator.isNotNull(isNew) && Boolean.parseBoolean(isNew)) {
			MultiMatchQuery query = new MultiMatchQuery(isNew);

			query.addFields(PaymentFileTerm.IS_NEW);

			booleanQuery.add(query, BooleanClauseOccur.MUST);
		}

		booleanQuery.addRequiredTerm(Field.ENTRY_CLASS_NAME, CLASS_NAME);

		return IndexSearcherHelperUtil.search(searchContext, booleanQuery);
	}

	/**
	 * Count number payment File using countLucene
	 * 
	 * @param
	 * @return Long
	 */
	public long countLucene(LinkedHashMap<String, Object> params, SearchContext searchContext)
			throws ParseException, SearchException {

		Indexer<PaymentFile> indexer = IndexerRegistryUtil.nullSafeGetIndexer(PaymentFile.class);

		searchContext.addFullQueryEntryClassName(CLASS_NAME);
		searchContext.setEntryClassNames(new String[] { CLASS_NAME });
		searchContext.setAttribute("paginationType", "regular");
		searchContext.setLike(true);
		searchContext.setAndSearch(true);

		String keywords = (String) params.get(Field.KEYWORD_SEARCH);
		String groupId = (String) params.get(Field.GROUP_ID);
		String dossierId = (String) params.get(DossierTerm.DOSSIER_ID);

		// Extra fields
		String service = String.valueOf((params.get(PaymentFileTerm.SERVICE)));
		String agency = GetterUtil.getString(params.get(PaymentFileTerm.AGENCY));
		String status = String.valueOf((params.get(PaymentFileTerm.STATUS)));
		String isNew = (String) params.get(PaymentFileTerm.IS_NEW);

		BooleanQuery booleanQuery = null;

		if (Validator.isNotNull(keywords)) {
			booleanQuery = BooleanQueryFactoryUtil.create(searchContext);
		} else {
			booleanQuery = indexer.getFullQuery(searchContext);
		}

		if (Validator.isNotNull(keywords)) {

			String[] keyword = keywords.split(StringPool.SPACE);

			for (String string : keyword) {

				MultiMatchQuery query = new MultiMatchQuery(string);

				query.addFields(PaymentFileTerm.SERVICE_NAME, PaymentFileTerm.GOV_AGENCY_NAME);

				booleanQuery.add(query, BooleanClauseOccur.MUST);

			}
		}

		if (Validator.isNotNull(groupId)) {
			MultiMatchQuery query = new MultiMatchQuery(groupId);

			query.addFields(Field.GROUP_ID);

			booleanQuery.add(query, BooleanClauseOccur.MUST);
		}

		if (Validator.isNotNull(dossierId)) {
			MultiMatchQuery query = new MultiMatchQuery(dossierId);

			query.addFields(DossierTerm.DOSSIER_ID);

			booleanQuery.add(query, BooleanClauseOccur.MUST);

		}

		if (Validator.isNotNull(service)) {
			MultiMatchQuery query = new MultiMatchQuery(service);

			query.addFields(PaymentFileTerm.SERVICE_CODE);

			booleanQuery.add(query, BooleanClauseOccur.MUST);
		}

		if (Validator.isNotNull(agency)) {
			MultiMatchQuery query = new MultiMatchQuery(agency);

			query.addFields(PaymentFileTerm.GOV_AGENCY_CODE);

			booleanQuery.add(query, BooleanClauseOccur.MUST);
		}

		if (Validator.isNotNull(status)) {
			String[] sliptStatus = status.split(StringPool.COMMA);
			if (sliptStatus != null && sliptStatus.length > 0) {
				BooleanQuery subQuery = new BooleanQueryImpl();
				for (String strStatus : sliptStatus) {
					if (Validator.isNotNull(strStatus)) {

						MultiMatchQuery query = new MultiMatchQuery(strStatus);

						query.addFields(PaymentFileTerm.PAYMENT_STATUS);
						subQuery.add(query, BooleanClauseOccur.SHOULD);
					}
				}
				booleanQuery.add(subQuery, BooleanClauseOccur.MUST);
			} else {
				MultiMatchQuery query = new MultiMatchQuery(status);

				query.addFields(PaymentFileTerm.PAYMENT_STATUS);

				booleanQuery.add(query, BooleanClauseOccur.MUST);
			}
		}

		if (Validator.isNotNull(isNew) && Boolean.parseBoolean(isNew)) {
			MultiMatchQuery query = new MultiMatchQuery(isNew);

			query.addFields(PaymentFileTerm.IS_NEW);

			booleanQuery.add(query, BooleanClauseOccur.MUST);
		}

		booleanQuery.addRequiredTerm(Field.ENTRY_CLASS_NAME, CLASS_NAME);

		return IndexSearcherHelperUtil.searchCount(searchContext, booleanQuery);
	}

	public static final String CLASS_NAME = PaymentFile.class.getName();

	/**
	 * Create a payment File
	 * 
	 * @param
	 * @return PaymentFile
	 */
	@Indexable(type = IndexableType.REINDEX)
	public PaymentFile createPaymentFiles(long userId, long groupId, long dossierId, String referenceUid,
			String paymentFee, long advanceAmount, long feeAmount, long serviceAmount, long shipAmount,
			long paymentAmount, String paymentNote, String epaymentProfile, String bankInfo, int paymentStatus,
			String paymentMethod, ServiceContext serviceContext) throws PortalException {

		// validate(groupId, serviceConfigId, serviceInfoId, govAgencyCode,
		// serviceLevel, serviceUrl, objName);

		Date now = new Date();

		User auditUser = userPersistence.fetchByPrimaryKey(userId);

		long paymentFileId = counterLocalService.increment(PaymentFile.class.getName());

		PaymentFile paymentFile = paymentFilePersistence.create(paymentFileId);

		paymentFile.setGroupId(groupId);
		paymentFile.setCompanyId(auditUser.getCompanyId());
		paymentFile.setUserId(auditUser.getUserId());
		paymentFile.setUserName(auditUser.getFullName());
		paymentFile.setCreateDate(now);
		paymentFile.setModifiedDate(now);

		paymentFile.setDossierId(dossierId);
		paymentFile.setReferenceUid(referenceUid);
		paymentFile.setPaymentFee(paymentFee);
		paymentFile.setAdvanceAmount(advanceAmount);
		paymentFile.setFeeAmount(feeAmount);
		paymentFile.setServiceAmount(serviceAmount);
		paymentFile.setShipAmount(shipAmount);
		paymentFile.setPaymentAmount(GetterUtil.getLong(paymentAmount));
		paymentFile.setPaymentNote(paymentNote);
		paymentFile.setEpaymentProfile(epaymentProfile);
		paymentFile.setBankInfo(bankInfo);
		paymentFile.setPaymentStatus(paymentStatus);
		paymentFile.setPaymentMethod(paymentMethod);

		paymentFilePersistence.update(paymentFile);

		return paymentFile;
	}

	/**
	 * Get info Epayment Profile
	 * 
	 * @param
	 * @return PaymentFile
	 */
	public PaymentFile getEpaymentProfile(long dossierId, String referenceUid) {

		return paymentFilePersistence.fetchByD_RUID(dossierId, referenceUid);
	}

	public PaymentFile getPaymentFile(long dossierId, String referenceUid) {
		return paymentFilePersistence.fetchByD_RUID(dossierId, referenceUid);
	}

	public List<PaymentFile> getSyncPaymentFile(long groupId, boolean isNew) {
		// return paymentFilePersistence.findByISN_GID(groupId, isNew);
		return null;
	}

	/**
	 * Update info Epayment Profile
	 * 
	 * @param
	 * @return PaymentFile
	 */
	@Indexable(type = IndexableType.REINDEX)
	public PaymentFile updateEProfile(long dossierId, String referenceUid, String strInput, ServiceContext context)
			throws PortalException {

		PaymentFile object = (PaymentFile) paymentFilePersistence.fetchByD_RUID(dossierId, referenceUid);

		Date now = new Date();

		User userAction = userLocalService.getUser(context.getUserId());

		// Update audit fields
		object.setModifiedDate(now);
		object.setUserId(userAction.getUserId());
		object.setUserName(userAction.getFullName());

		object.setEpaymentProfile(strInput);

		paymentFilePersistence.update(object);

		return object;

	}

	Log _log = LogFactoryUtil.getLog(PaymentFileLocalServiceImpl.class.getName());

	/**
	 * update payment File Confirm
	 * 
	 * @param
	 * @return PaymentFile
	 */
	@Indexable(type = IndexableType.REINDEX)
	public PaymentFile updateFileConfirm(long groupId, long dossierId, String referenceUid, String confirmNote,
			String paymentMethod, String confirmPayload, ServiceContext serviceContext)
			throws PortalException, SystemException {

		PaymentFile paymentFile = paymentFilePersistence.fetchByD_RUID(dossierId, referenceUid);

		_log.info("paymentMethod: " + paymentMethod);
		_log.info("confirmPayload: " + confirmPayload);

		if (paymentFile != null) {

			Date now = new Date();

			paymentFile.setModifiedDate(now);
			paymentFile.setConfirmNote(confirmNote);
			paymentFile.setPaymentMethod(paymentMethod);
			paymentFile.setConfirmPayload(confirmPayload);
			paymentFile.setPaymentStatus(5);
			// if (Validator.isNotNull(paymentMethod) && "N\u1ED9p
			// online".equals(paymentMethod)) {
			// paymentFile.setPaymentStatus(5);
			// } else {
			// paymentFile.setPaymentStatus(5);
			// }
			// paymentFile.setIsNew(true);
		}

		// update dossier

		try {
			Dossier dossier = DossierLocalServiceUtil.getDossier(dossierId);

			dossier.setSubmitDate(new Date());
			dossier.setSubmitting(true);

			dossierLocalService.updateDossier(dossier);
			// dossierPersistence.update(dossier);

			// indexer.reindex(dossier);
		} catch (SearchException e) {
			_log.error(e);
		}

		return paymentFilePersistence.update(paymentFile);
	}

	/**
	 * update payment File Confirm
	 * 
	 * @param
	 * @return PaymentFile
	 */
	@Indexable(type = IndexableType.REINDEX)
	public PaymentFile updateFileConfirm(long groupId, long dossierId, String referenceUid, String confirmNote,
			String paymentMethod, String confirmPayload, String sourceFileName, long fileSize, InputStream inputStream,
			ServiceContext serviceContext) throws PortalException, SystemException {

		long userId = serviceContext.getUserId();

		// User auditUser =
		// userPersistence.fetchByPrimaryKey(serviceContext.getUserId());

		PaymentFile paymentFile = paymentFilePersistence.fetchByD_RUID(dossierId, referenceUid);

		if (paymentFile != null) {

			long fileEntryId = 0;
			if (inputStream != null) {
				try {
					FileEntry fileEntry = FileUploadUtils.uploadPaymentFile(userId, groupId, inputStream,
							sourceFileName, null, fileSize, serviceContext);

					if (fileEntry != null) {
						fileEntryId = fileEntry.getFileEntryId();
					}
				} catch (Exception e) {
					// throw new SystemException(e);
					_log.error(e);
				}
			}

			Date now = new Date();

			paymentFile.setModifiedDate(now);
			paymentFile.setConfirmNote(confirmNote);
			paymentFile.setPaymentMethod(paymentMethod);
			paymentFile.setConfirmPayload(confirmPayload);
			paymentFile.setConfirmFileEntryId(fileEntryId);
			// TODO review payment status
			paymentFile.setPaymentStatus(3);
			// paymentFile.setIsNew(true);
		}

		// Indexer<Dossier> indexer =
		// IndexerRegistryUtil.nullSafeGetIndexer(Dossier.class);

		try {
			Dossier dossier = DossierLocalServiceUtil.getDossier(dossierId);

			dossier.setSubmitDate(new Date());
			dossier.setSubmitting(true);

			dossierLocalService.updateDossier(dossier);

			// indexer.reindex(dossier);
		} catch (SearchException e) {
			// e.printStackTrace();
			_log.error(e);
		}

		return paymentFilePersistence.update(paymentFile);
	}

	/**
	 * Update payment File Approval
	 * 
	 * @param
	 * @return PaymentFile
	 */

	@Indexable(type = IndexableType.REINDEX)
	public PaymentFile updateFileApproval(long groupId, long dossierId, String referenceUid, String approveDatetime,
			String accountUserName, String govAgencyTaxNo, String invoiceTemplateNo, String invoiceIssueNo,
			String invoiceNo, ServiceContext serviceContext)
			throws PortalException, SystemException, java.text.ParseException {

		PaymentFile paymentFile = paymentFilePersistence.fetchByD_RUID(dossierId, referenceUid);

		if (paymentFile != null) {

			long userId = serviceContext.getUserId();

			Date now = new Date();

			paymentFile.setModifiedDate(now);
			paymentFile.setUserId(userId);
			// Parse String to Date
			Date dateApproved = null;
			if (Validator.isNotNull(approveDatetime)) {
				SimpleDateFormat format = new SimpleDateFormat("DD-MM-YYYY HH:MM:SS");
				dateApproved = format.parse(approveDatetime);
			}
			paymentFile.setApproveDatetime(dateApproved);
			paymentFile.setAccountUserName(accountUserName);
			paymentFile.setGovAgencyTaxNo(govAgencyTaxNo);
			paymentFile.setInvoiceTemplateNo(invoiceTemplateNo);
			paymentFile.setInvoiceIssueNo(invoiceIssueNo);
			paymentFile.setInvoiceNo(invoiceNo);
			paymentFile.setPaymentStatus(2);

			// paymentFile.setIsNew(true);

		}

		return paymentFilePersistence.update(paymentFile);
	}

	@Indexable(type = IndexableType.REINDEX)
	public PaymentFile updateFileApproval(long groupId, long dossierId, String referenceUid, String approveDatetime,
			String accountUserName, String govAgencyTaxNo, String invoiceTemplateNo, String invoiceIssueNo,
			String invoiceNo, String sourceFileName, long fileSize, InputStream inputStream,
			ServiceContext serviceContext) throws PortalException, SystemException, java.text.ParseException {

		PaymentFile paymentFile = paymentFilePersistence.fetchByD_RUID(dossierId, referenceUid);

		if (paymentFile != null) {

			// long fileEntryId = 0;
			if (inputStream != null) {
				long userId = serviceContext.getUserId();
				try {
					FileEntry fileEntry = FileUploadUtils.uploadPaymentFile(userId, groupId, inputStream,
							sourceFileName, null, fileSize, serviceContext);

					if (fileEntry != null) {
						// fileEntryId = fileEntry.getFileEntryId();
					}
				} catch (Exception e) {
					// e.printStackTrace();
					_log.error(e);
					throw new SystemException(e);
				}

			}

			Date now = new Date();

			paymentFile.setModifiedDate(now);
			// Parse String to Date
			SimpleDateFormat format = new SimpleDateFormat("DD-MM-YYYY HH:MM:SS");
			Date dateApproved = format.parse(approveDatetime);
			paymentFile.setApproveDatetime(dateApproved);
			paymentFile.setAccountUserName(accountUserName);
			paymentFile.setGovAgencyTaxNo(govAgencyTaxNo);
			paymentFile.setInvoiceTemplateNo(invoiceTemplateNo);
			paymentFile.setInvoiceIssueNo(invoiceIssueNo);
			paymentFile.setInvoiceNo(invoiceNo);
			// paymentFile.setInvoiceFileEntryId(fileEntryId);
			// paymentFile.setIsNew(true);

		}

		return paymentFilePersistence.update(paymentFile);
	}

	// 8

	public PaymentFile getPaymentFileByReferenceUid(long dossierId, String referenceUid) {

		return paymentFilePersistence.fetchByD_RUID(dossierId, referenceUid);
	}

	@Override
	public PaymentFile getByDossierId(long groupId, long dossierId) {
		return paymentFilePersistence.fetchByDossierId(groupId, dossierId);
	}

	@Indexable(type = IndexableType.REINDEX)
	public PaymentFile updateApplicantFeeAmount(long paymentFileId, int requestPayment, Long feeAmount,
			Long serviceAmount, Long shipAmount, String paymentNote, int originality) {
		try {
			PaymentFile paymentFile = paymentFilePersistence.fetchByPrimaryKey(paymentFileId);
			paymentFile.setFeeAmount(feeAmount);
			paymentFile.setServiceAmount(serviceAmount);
			paymentFile.setShipAmount(shipAmount);
			paymentFile.setPaymentStatus(requestPayment);
			if (Validator.isNotNull(paymentNote))
				paymentFile.setPaymentNote(paymentNote);
			if (requestPayment == ProcessActionTerm.REQUEST_PAYMENT_YEU_CAU_NOP_TAM_UNG) {
				if(originality != DossierTerm.ORIGINALITY_MOTCUA) {
					long paymentAmount = feeAmount + serviceAmount + shipAmount - paymentFile.getAdvanceAmount();
					paymentFile.setPaymentAmount(paymentAmount);
				} else {
				paymentFile.setAdvanceAmount(feeAmount + serviceAmount + shipAmount);
				}
			} else if (requestPayment == ProcessActionTerm.REQUEST_PAYMENT_XAC_NHAN_HOAN_THANH_THU_PHI
					|| requestPayment == ProcessActionTerm.REQUEST_PAYMENT_YEU_CAU_QUYET_TOAN_PHI) {
				paymentFile.setPaymentAmount(feeAmount + serviceAmount + shipAmount - paymentFile.getAdvanceAmount());
			}
			// Update epayment Profile
			try {
				JSONObject epaymentProFile = JSONFactoryUtil.createJSONObject(paymentFile.getEpaymentProfile());
				if (Validator.isNull(epaymentProFile)) {
					epaymentProFile = JSONFactoryUtil.createJSONObject();
				}
				epaymentProFile.put("serviceAmount", serviceAmount);
				epaymentProFile.put("feeAmount", feeAmount);
				epaymentProFile.put("paymentNote", paymentNote);
				//
				paymentFile.setEpaymentProfile(epaymentProFile.toJSONString());
			} catch (JSONException e) {
				_log.debug(e);
			}

			return paymentFilePersistence.update(paymentFile);
		} catch (Exception e) {
			_log.error(e);
		}

		return null;
	}

	@Indexable(type = IndexableType.REINDEX)
	public PaymentFile updatePaymentFileCustom(PaymentFile oldpaymentFile) {
		try {
			PaymentFile paymentFile = paymentFilePersistence.fetchByPrimaryKey(oldpaymentFile.getPaymentFileId());
			
			paymentFile.setEinvoice(oldpaymentFile.getEinvoice());
			
			return paymentFilePersistence.update(paymentFile);
		} catch (Exception e) {
			_log.error(e);
		}
		
		return null;
	}

	// super_admin Generators
	@Indexable(type = IndexableType.DELETE)
	public PaymentFile adminProcessDelete(Long id) {

		PaymentFile object = paymentFilePersistence.fetchByPrimaryKey(id);

		if (Validator.isNull(object)) {
			return null;
		} else {
			paymentFilePersistence.remove(object);
		}

		return object;
	}

	@Indexable(type = IndexableType.REINDEX)
	public PaymentFile adminProcessData(JSONObject objectData) {

		PaymentFile object = null;

		if (objectData.getLong("paymentFileId") > 0) {

			object = paymentFilePersistence.fetchByPrimaryKey(objectData.getLong("paymentFileId"));

			object.setModifiedDate(new Date());

		} else {

			long id = CounterLocalServiceUtil.increment(PaymentFile.class.getName());

			object = paymentFilePersistence.create(id);

			object.setGroupId(objectData.getLong("groupId"));
			object.setCompanyId(objectData.getLong("companyId"));
			object.setCreateDate(new Date());

		}

		object.setUserId(objectData.getLong("userId"));
		object.setUserName(objectData.getString("userName"));

		object.setDossierId(objectData.getLong("dossierId"));
		object.setReferenceUid(objectData.getString("referenceUid"));
		object.setPaymentFee(objectData.getString("paymentFee"));
		object.setAdvanceAmount(objectData.getLong("advanceAmount"));
		object.setFeeAmount(objectData.getLong("feeAmount"));
		object.setServiceAmount(objectData.getLong("serviceAmount"));
		object.setShipAmount(objectData.getLong("shipAmount"));
		object.setPaymentAmount(objectData.getLong("paymentAmount"));
		object.setPaymentNote(objectData.getString("paymentNote"));
		object.setEpaymentProfile(objectData.getString("epaymentProfile"));
		object.setBankInfo(objectData.getString("bankInfo"));
		object.setPaymentStatus(objectData.getInt("paymentStatus"));
		object.setPaymentMethod(objectData.getString("paymentMethod"));
		object.setConfirmDatetime(new Date(objectData.getLong("confirmDatetime")));
		object.setConfirmPayload(objectData.getString("confirmPayload"));
		// object.setConfirmFileEntryId(objectData.getString("userName")confirmFileEntryId);
		object.setConfirmNote(objectData.getString("confirmNote"));
		object.setApproveDatetime(new Date(objectData.getLong("approveDatetime")));
		object.setAccountUserName(objectData.getString("accountUserName"));
		object.setGovAgencyTaxNo(objectData.getString("govAgencyTaxNo"));
		object.setInvoiceTemplateNo(objectData.getString("invoiceTemplateNo"));
		object.setInvoiceIssueNo(objectData.getString("invoiceIssueNo"));
		object.setInvoiceNo(objectData.getString("invoiceNo"));
		object.setInvoicePayload(objectData.getString("invoicePayload"));
		object.setEinvoice(objectData.getString("einvoice"));

		paymentFilePersistence.update(object);

		return object;

	}

	public PaymentFile getByG_DID(long groupId, long dossierId) {
		return paymentFilePersistence.fetchByDossierId(groupId, dossierId);
	}
}