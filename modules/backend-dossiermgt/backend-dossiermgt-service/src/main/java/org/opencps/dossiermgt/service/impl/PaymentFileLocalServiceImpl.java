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

import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.opencps.auth.utils.APIDateTimeUtils;
import org.opencps.dossiermgt.action.FileUploadUtils;
import org.opencps.dossiermgt.action.util.PaymentUrlGenerator;
import org.opencps.dossiermgt.constants.ConstantsTerm;
import org.opencps.dossiermgt.constants.DossierTerm;
import org.opencps.dossiermgt.constants.KeyPayTerm;
import org.opencps.dossiermgt.constants.PaymentFileTerm;
import org.opencps.dossiermgt.constants.ProcessActionTerm;
import org.opencps.dossiermgt.constants.VTPayTerm;
import org.opencps.dossiermgt.exception.NoSuchPaymentFileException;
import org.opencps.dossiermgt.input.model.PaymentFileInputModel;
import org.opencps.dossiermgt.model.Dossier;
import org.opencps.dossiermgt.model.PaymentConfig;
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
		searchContext.setAttribute(ConstantsTerm.PAGINATION_TYPE, ConstantsTerm.REGULAR);
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
		searchContext.setAttribute(ConstantsTerm.PAGINATION_TYPE, ConstantsTerm.REGULAR);
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
		if (paymentStatus == 5) {
		paymentFile.setApproveDatetime(now);
		}

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

		long userId = context != null && context.getUserId() > 0 ? context.getUserId() : object.getUserId();
		User userAction = userLocalService.getUser(userId);

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
			SimpleDateFormat format = new SimpleDateFormat(APIDateTimeUtils._NORMAL_DATE_TIME);
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
			if (paymentFile.getPaymentStatus() == 5) {
			paymentFile.setApproveDatetime(new Date());
			}
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
				epaymentProFile.put(PaymentFileTerm.SERVICE_AMOUNT, serviceAmount);
				epaymentProFile.put(PaymentFileTerm.FEE_AMOUNT, feeAmount);
				epaymentProFile.put(PaymentFileTerm.PAYMENT_NOTE, paymentNote);
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

		if (objectData.getLong(PaymentFileTerm.PAYMENT_FILE_ID) > 0) {

			object = paymentFilePersistence.fetchByPrimaryKey(objectData.getLong(PaymentFileTerm.PAYMENT_FILE_ID));

			object.setModifiedDate(new Date());

		} else {

			long id = CounterLocalServiceUtil.increment(PaymentFile.class.getName());

			object = paymentFilePersistence.create(id);

			object.setGroupId(objectData.getLong(Field.GROUP_ID));
			object.setCompanyId(objectData.getLong(Field.COMPANY_ID));
			object.setCreateDate(new Date());

		}

		object.setUserId(objectData.getLong(Field.USER_ID));
		object.setUserName(objectData.getString(Field.USER_NAME));

		object.setDossierId(objectData.getLong(PaymentFileTerm.DOSSIER_ID));
		object.setReferenceUid(objectData.getString(PaymentFileTerm.REFERENCE_UID));
		object.setPaymentFee(objectData.getString(PaymentFileTerm.PAYMENT_FEE));
		object.setAdvanceAmount(objectData.getLong(PaymentFileTerm.ADVANCE_AMOUNT));
		object.setFeeAmount(objectData.getLong(PaymentFileTerm.FEE_AMOUNT));
		object.setServiceAmount(objectData.getLong(PaymentFileTerm.SERVICE_AMOUNT));
		object.setShipAmount(objectData.getLong(PaymentFileTerm.SHIP_AMOUNT));
		object.setPaymentAmount(objectData.getLong(PaymentFileTerm.PAYMENT_AMOUNT));
		object.setPaymentNote(objectData.getString(PaymentFileTerm.PAYMENT_NOTE));
		object.setEpaymentProfile(objectData.getString(PaymentFileTerm.EPAYMENT_PROFILE));
		object.setBankInfo(objectData.getString(PaymentFileTerm.BANK_INFO));
		object.setPaymentStatus(objectData.getInt(PaymentFileTerm.PAYMENT_STATUS));
		object.setPaymentMethod(objectData.getString(PaymentFileTerm.PAYMENT_METHOD));
		object.setConfirmDatetime(new Date(objectData.getLong(PaymentFileTerm.CONFIRM_DATETIME)));
		object.setConfirmPayload(objectData.getString(PaymentFileTerm.CONFIRM_PAYLOAD));
		object.setConfirmNote(objectData.getString(PaymentFileTerm.CONFIRM_NOTE));
		object.setApproveDatetime(new Date(objectData.getLong(PaymentFileTerm.APPROVE_DATETIME)));
		object.setAccountUserName(objectData.getString(PaymentFileTerm.ACCOUNT_USER_NAME));
		object.setGovAgencyTaxNo(objectData.getString(PaymentFileTerm.GOV_AGENCY_TAX_NO));
		object.setInvoiceTemplateNo(objectData.getString(PaymentFileTerm.INVOICE_TEMPLATE_NO));
		object.setInvoiceIssueNo(objectData.getString(PaymentFileTerm.INVOICE_ISSUE_NO));
		object.setInvoiceNo(objectData.getString(PaymentFileTerm.INVOICE_NO));
		object.setInvoicePayload(objectData.getString(PaymentFileTerm.INVOICE_PAYLOAD));
		object.setEinvoice(objectData.getString(PaymentFileTerm.EINVOICE));

		paymentFilePersistence.update(object);

		return object;

	}

	public PaymentFile getByG_DID(long groupId, long dossierId) {
		return paymentFilePersistence.fetchByDossierId(groupId, dossierId);
	}

	/**
	 * Create a payment File
	 * 
	 * @param
	 * @return PaymentFile
	 */
	@Indexable(type = IndexableType.REINDEX)
	public PaymentFile createPaymentFileByDossierId(long userId, long groupId, long dossierId,
			PaymentFileInputModel input, ServiceContext serviceContext) throws PortalException {

		Date now = new Date();
		User auditUser = userPersistence.fetchByPrimaryKey(userId);
		Dossier dossier = DossierLocalServiceUtil.fetchDossier(dossierId);
		PaymentFile paymentFile = paymentFilePersistence.fetchByDossierId(groupId, dossier.getDossierId());

		if (Validator.isNull(paymentFile)) {

			long paymentFileId = counterLocalService.increment(PaymentFile.class.getName());

			paymentFile = paymentFilePersistence.create(paymentFileId);

			paymentFile.setGroupId(groupId);
			paymentFile.setCompanyId(auditUser.getCompanyId());
			paymentFile.setUserId(auditUser.getUserId());
			paymentFile.setUserName(auditUser.getFullName());
			paymentFile.setCreateDate(now);
			paymentFile.setDossierId(dossierId);
			paymentFile.setReferenceUid(UUID.randomUUID().toString());
		}

		paymentFile.setModifiedDate(now);
		paymentFile.setPaymentFee(input.getPaymentFee());
		paymentFile.setAdvanceAmount(input.getAdvanceAmount());
		paymentFile.setFeeAmount(input.getFeeAmount());
		paymentFile.setServiceAmount(input.getServiceAmount());
		paymentFile.setShipAmount(input.getShipAmount());
		paymentFile.setPaymentAmount(GetterUtil.getLong(input.getPaymentAmount()));
		paymentFile.setPaymentNote(input.getPaymentNote());
		paymentFile.setBankInfo(input.getBankInfo());
		paymentFile.setPaymentStatus(input.getPaymentStatus());
		paymentFile.setPaymentMethod(input.getPaymentMethod());
		if (input.getPaymentStatus() == 5) {
		paymentFile.setApproveDatetime(now);
		}

		paymentFilePersistence.update(paymentFile);
		
		JSONObject epaymentConfigJSON = input.getEpaymentProfile() != null
				? JSONFactoryUtil.createJSONObject(input.getEpaymentProfile())
				: JSONFactoryUtil.createJSONObject();
		JSONObject epaymentProfileJSON = JSONFactoryUtil.createJSONObject();

		if (epaymentConfigJSON.has("paymentKeypayDomain")) {
			try {
				String generatorPayURL = PaymentUrlGenerator.generatorPayURLEpar(groupId, dossier, input.getPaymentAmount(), input.getPaymentFee(), epaymentConfigJSON);
				epaymentProfileJSON.put(KeyPayTerm.KEYPAYURL, generatorPayURL);

				String pattern1 = KeyPayTerm.GOOD_CODE_EQ;
				String pattern2 = StringPool.AMPERSAND;

				String regexString = Pattern.quote(pattern1) + "(.*?)" + Pattern.quote(pattern2);

				Pattern p = Pattern.compile(regexString);
				Matcher m = p.matcher(generatorPayURL);

				if (m.find()) {
					String goodCode = m.group(1);

					epaymentProfileJSON.put(KeyPayTerm.KEYPAYGOODCODE, goodCode);
				} else {
					epaymentProfileJSON.put(KeyPayTerm.KEYPAYGOODCODE, StringPool.BLANK);
				}

				epaymentProfileJSON.put(KeyPayTerm.KEYPAYMERCHANTCODE,
						epaymentConfigJSON.get("paymentMerchantCode"));
				epaymentProfileJSON.put(KeyPayTerm.BANK, String.valueOf(true));
				epaymentProfileJSON.put(KeyPayTerm.PAYGATE, String.valueOf(true));
				epaymentProfileJSON.put(KeyPayTerm.SERVICEAMOUNT, input.getServiceAmount());
				epaymentProfileJSON.put(KeyPayTerm.PAYMENTNOTE, input.getPaymentNote());
				epaymentProfileJSON.put(KeyPayTerm.PAYMENTFEE, input.getPaymentFee());

			} catch (IOException e) {
				_log.error(e);
			}

		}

		if (epaymentConfigJSON.has(VTPayTerm.VTP_CONFIG)) {
			try {
				JSONObject schema = epaymentConfigJSON.getJSONObject(VTPayTerm.VTP_CONFIG);
				JSONObject data = JSONFactoryUtil.createJSONObject();

				data.put(schema.getJSONObject(VTPayTerm.PRIORITY).getString(VTPayTerm.KEY),
						schema.getJSONObject(VTPayTerm.PRIORITY).getString(VTPayTerm.VALUE));
				data.put(schema.getJSONObject(VTPayTerm.VERSION).getString(VTPayTerm.KEY),
						schema.getJSONObject(VTPayTerm.VERSION).getString(VTPayTerm.VALUE));
				data.put(schema.getJSONObject(VTPayTerm.TYPE).getString(VTPayTerm.KEY),
						schema.getJSONObject(VTPayTerm.TYPE).getString(VTPayTerm.VALUE));
				data.put(schema.getJSONObject(VTPayTerm.BILLCODE).getString(VTPayTerm.KEY),
						VTPayTerm.createBillCode(dossier.getGovAgencyCode(), paymentFile.getInvoiceNo()));
				data.put(schema.getJSONObject(VTPayTerm.ORDER_ID).getString(VTPayTerm.KEY),
						VTPayTerm.createOrderId(dossier.getDossierId(), dossier.getDossierNo()));
				data.put(schema.getJSONObject(VTPayTerm.AMOUNT).getString(VTPayTerm.KEY),
						paymentFile.getPaymentAmount());
				data.put(schema.getJSONObject(VTPayTerm.MERCHANT_CODE).getString(VTPayTerm.KEY),
						schema.getJSONObject(VTPayTerm.MERCHANT_CODE).getString(VTPayTerm.VALUE));

				epaymentProfileJSON.put(VTPayTerm.VTPAY_GENQR, data);
			} catch (Exception e) {
				_log.error(e);
			}

		}

		paymentFile.setEpaymentProfile(epaymentProfileJSON.toString());
		paymentFilePersistence.update(paymentFile);

		return paymentFile;
	}
	public String findSumPaymentAmountDay(long groupId,String date)
	{
		_log.info("groupId" + groupId + "date " +date);
		return paymentFileFinder.findSumPaymentAmountDay(groupId,date,0,2);
	}
	
	public List<PaymentFile> findByG_PT(long groupId, int paymentStatus) {
		return paymentFilePersistence.findByG_PT(groupId, paymentStatus);
	}
	
	public List<PaymentFile> findByG(long groupId) {
		return paymentFilePersistence.findByG(groupId);
	}
	
	public List<PaymentFile> findAll() {
		return paymentFilePersistence.findAll();
	}
	
	public List<PaymentFile> findByPT(int paymentStatus) {
		return paymentFilePersistence.findByPT(paymentStatus);
	}
}