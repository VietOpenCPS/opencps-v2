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

import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;

import org.opencps.dossiermgt.action.FileUploadUtils;
import org.opencps.dossiermgt.constants.DossierTerm;
import org.opencps.dossiermgt.constants.PaymentFileTerm;
import org.opencps.dossiermgt.model.Dossier;
import org.opencps.dossiermgt.model.PaymentFile;
import org.opencps.dossiermgt.service.DossierLocalServiceUtil;
import org.opencps.dossiermgt.service.base.PaymentFileLocalServiceBaseImpl;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
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
import com.liferay.portal.kernel.search.generic.MultiMatchQuery;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;

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
			MultiMatchQuery query = new MultiMatchQuery(status);

			query.addFields(PaymentFileTerm.PAYMENT_STATUS);

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
			MultiMatchQuery query = new MultiMatchQuery(status);

			query.addFields(PaymentFileTerm.PAYMENT_STATUS);

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
			String govAgencyCode, String govAgencyName, String applicantName, String applicantIdNo, String paymentFee,
			long paymentAmount, String paymentNote, String epaymentProfile, String bankInfo,
			ServiceContext serviceContext) throws PortalException {

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
		paymentFile.setGovAgencyCode(govAgencyCode);
		paymentFile.setGovAgencyName(govAgencyName);
		paymentFile.setPaymentFee(paymentFee);
		paymentFile.setPaymentAmount(GetterUtil.getLong(paymentAmount));
		paymentFile.setPaymentNote(paymentNote);
		paymentFile.setEpaymentProfile(epaymentProfile);
		paymentFile.setBankInfo(bankInfo);

		try {
			Dossier dossier = DossierLocalServiceUtil.getDossier(dossierId);
			dossier.setApplicantName(applicantName);
			dossier.setApplicantIdNo(applicantIdNo);

			dossierPersistence.update(dossier);
		} catch (Exception e) {
			e.printStackTrace();
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
	// wtf?..... clgt?
	public PaymentFile getEpaymentProfile(long dossierId, String referenceUid) {

		return (PaymentFile) paymentFilePersistence.findByF_DUID(dossierId, referenceUid);

	}

	public PaymentFile getPaymentFile(long dossierId, String referenceUid) {
		return paymentFilePersistence.fetchByD_RUID(dossierId, referenceUid);
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

		PaymentFile object = (PaymentFile) paymentFilePersistence.findByD_RUID(dossierId, referenceUid);

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

		if (paymentFile != null) {

			Date now = new Date();

			paymentFile.setModifiedDate(now);
			paymentFile.setConfirmNote(confirmNote);
			paymentFile.setPaymentMethod(paymentMethod);
			paymentFile.setConfirmPayload(confirmPayload);
			paymentFile.setPaymentStatus(2);
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
				}
			}

			Date now = new Date();

			paymentFile.setModifiedDate(now);
			paymentFile.setConfirmNote(confirmNote);
			paymentFile.setPaymentMethod(paymentMethod);
			paymentFile.setConfirmPayload(confirmPayload);
			paymentFile.setConfirmFileEntryId(fileEntryId);
			// paymentFile.setPaymentStatus(2);

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

			long fileEntryId = 0;
			if (inputStream != null) {
				long userId = serviceContext.getUserId();
				try {
					FileEntry fileEntry = FileUploadUtils.uploadPaymentFile(userId, groupId, inputStream,
							sourceFileName, null, fileSize, serviceContext);

					if (fileEntry != null) {
						fileEntryId = fileEntry.getFileEntryId();
					}
				} catch (Exception e) {
					// e.printStackTrace();
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
			paymentFile.setConfirmFileEntryId(fileEntryId);

		}

		return paymentFilePersistence.update(paymentFile);
	}

	// 8

	public PaymentFile getPaymentFileByReferenceUid(long dossierId, String referenceUid) throws PortalException {

		return paymentFilePersistence.findByD_RUID(dossierId, referenceUid);
	}

	@Override
	public List<PaymentFile> getByDossierId(long dossierId) {
		// TODO Auto-generated method stub
		return null;
	}

}