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
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.model.User;
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
import com.liferay.portal.kernel.util.Validator;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;

import org.opencps.dossiermgt.constants.PaymentConfigTerm;
import org.opencps.dossiermgt.model.DeliverableType;
import org.opencps.dossiermgt.model.PaymentConfig;
import org.opencps.dossiermgt.service.base.PaymentConfigLocalServiceBaseImpl;

import aQute.bnd.annotation.ProviderType;

/**
 * The implementation of the payment config local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are
 * added, rerun ServiceBuilder to copy their definitions into the
 * {@link org.opencps.dossiermgt.service.PaymentConfigLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security
 * checks based on the propagated JAAS credentials because this service can only
 * be accessed from within the same VM.
 * </p>
 *
 * @author huymq
 * @see PaymentConfigLocalServiceBaseImpl
 * @see org.opencps.dossiermgt.service.PaymentConfigLocalServiceUtil
 */
@ProviderType
public class PaymentConfigLocalServiceImpl extends PaymentConfigLocalServiceBaseImpl {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this class directly. Always use {@link
	 * org.opencps.dossiermgt.service.PaymentConfigLocalServiceUtil} to access the
	 * payment config local service.
	 */

	@Indexable(type = IndexableType.REINDEX)
	public PaymentConfig updateInvoidForm(long paymentConfigId, String invoiceForm, ServiceContext context)
			throws PortalException {

		PaymentConfig object = paymentConfigPersistence.fetchByPrimaryKey(paymentConfigId);

		Date now = new Date();

		User userAction = userLocalService.getUser(context.getUserId());

		// Update audit fields
		object.setModifiedDate(now);
		object.setUserId(userAction.getUserId());
		object.setUserName(userAction.getFullName());

		object.setInvoiceForm(invoiceForm);

		paymentConfigPersistence.update(object);

		return object;

	}

	@Indexable(type = IndexableType.REINDEX)
	public PaymentConfig updateEConfig(long paymentConfigId, String eConfig, ServiceContext context)
			throws PortalException {

		PaymentConfig object = paymentConfigPersistence.fetchByPrimaryKey(paymentConfigId);

		Date now = new Date();

		User userAction = userLocalService.getUser(context.getUserId());

		// Update audit fields
		object.setModifiedDate(now);
		object.setUserId(userAction.getUserId());
		object.setUserName(userAction.getFullName());

		object.setEpaymentConfig(eConfig);

		paymentConfigPersistence.update(object);

		return object;

	}

	@Indexable(type = IndexableType.REINDEX)
	public PaymentConfig updatePaymentConfig(long groupId, long paymentConfigId, String govAgencyCode,
			String govAgencyName, String govAgencyTaxNo, String invoiceTemplateNo, String invoiceIssueNo,
			String invoiceLastNo, String invoiceForm, String bankInfo, String epaymentConfig, ServiceContext context)
			throws PortalException {

		validateUpdate(groupId, paymentConfigId, govAgencyCode, govAgencyName, govAgencyTaxNo, invoiceTemplateNo,
				invoiceIssueNo, invoiceLastNo, invoiceForm, bankInfo, epaymentConfig);

		PaymentConfig object = null;

		Date now = new Date();

		User userAction = userLocalService.getUser(context.getUserId());

		if (paymentConfigId == 0) {

			paymentConfigId = counterLocalService.increment(PaymentConfig.class.getName());

			object = paymentConfigPersistence.create(paymentConfigId);

			// Add audit fields
			object.setCompanyId(context.getCompanyId());
			object.setGroupId(groupId);
			object.setCreateDate(now);
			object.setModifiedDate(now);
			object.setUserId(userAction.getUserId());
			object.setUserName(userAction.getFullName());

			object.setGovAgencyCode(govAgencyCode);
			object.setGovAgencyName(govAgencyName);
			object.setGovAgencyTaxNo(govAgencyTaxNo);
			object.setInvoiceTemplateNo(invoiceTemplateNo);
			object.setInvoiceIssueNo(invoiceIssueNo);
			object.setInvoiceLastNo(invoiceLastNo);
			object.setInvoiceForm(invoiceForm);
			object.setBankInfo(bankInfo);
			object.setEpaymentConfig(epaymentConfig);

		} else {
			object = paymentConfigPersistence.fetchByPrimaryKey(paymentConfigId);

			// Add audit fields
			object.setModifiedDate(now);
			object.setUserId(userAction.getUserId());
			object.setUserName(userAction.getFullName());

			object.setGovAgencyCode(govAgencyCode);
			object.setGovAgencyName(govAgencyName);
			object.setGovAgencyTaxNo(govAgencyTaxNo);
			object.setInvoiceTemplateNo(invoiceTemplateNo);
			object.setInvoiceIssueNo(invoiceIssueNo);
			object.setInvoiceLastNo(invoiceLastNo);
			// object.setInvoiceForm(invoiceForm);
			object.setBankInfo(bankInfo);
			// object.setEpaymentConfig(epaymentConfig);

		}

		paymentConfigPersistence.update(object);

		return object;
	}

	@Indexable(type = IndexableType.DELETE)
	public PaymentConfig removePaymentConfig(long paymentConfigId) throws PortalException {
		validateDelete(paymentConfigId);

		PaymentConfig paymentConfig = paymentConfigPersistence.fetchByPrimaryKey(paymentConfigId);

		paymentConfigPersistence.remove(paymentConfig);

		return paymentConfig;
	}

	public PaymentConfig getPaymentConfigByGovAgencyCode(long groupId, String govAgencyCode) {
		return paymentConfigPersistence.fetchByFB_GID_govAgencyCode(groupId, govAgencyCode);
	}

	public Hits searchLucene(LinkedHashMap<String, Object> params, Sort[] sorts, int start, int end,
			SearchContext searchContext) throws ParseException, SearchException {

		String keywords = (String) params.get(Field.KEYWORD_SEARCH);
		String groupId = (String) params.get(Field.GROUP_ID);

		Indexer<PaymentConfig> indexer = IndexerRegistryUtil.nullSafeGetIndexer(PaymentConfig.class);

		searchContext.addFullQueryEntryClassName(CLASS_NAME);
		searchContext.setEntryClassNames(new String[] { CLASS_NAME });
		searchContext.setAttribute("paginationType", "regular");
		searchContext.setLike(true);
		searchContext.setStart(start);
		searchContext.setEnd(end);
		searchContext.setAndSearch(true);
		searchContext.setSorts(sorts);

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

				query.addFields(PaymentConfigTerm.GOV_AGENCY_CODE, PaymentConfigTerm.GOV_AGENCY_NAME,
						PaymentConfigTerm.GOV_AGENCY_TAX_NO);

				booleanQuery.add(query, BooleanClauseOccur.MUST);

			}
		}

		if (Validator.isNotNull(groupId)) {
			MultiMatchQuery query = new MultiMatchQuery(groupId);

			query.addFields(Field.GROUP_ID);

			booleanQuery.add(query, BooleanClauseOccur.MUST);
		}

		booleanQuery.addRequiredTerm(Field.ENTRY_CLASS_NAME, CLASS_NAME);

		return IndexSearcherHelperUtil.search(searchContext, booleanQuery);
	}

	public long countLucene(LinkedHashMap<String, Object> params, SearchContext searchContext)
			throws ParseException, SearchException {

		String keywords = (String) params.get(Field.KEYWORD_SEARCH);
		String groupId = (String) params.get(Field.GROUP_ID);

		Indexer<PaymentConfig> indexer = IndexerRegistryUtil.nullSafeGetIndexer(PaymentConfig.class);

		searchContext.addFullQueryEntryClassName(CLASS_NAME);
		searchContext.setEntryClassNames(new String[] { CLASS_NAME });
		searchContext.setAttribute("paginationType", "regular");
		searchContext.setLike(true);
		searchContext.setAndSearch(true);

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

				query.addFields(PaymentConfigTerm.GOV_AGENCY_CODE, PaymentConfigTerm.GOV_AGENCY_NAME,
						PaymentConfigTerm.GOV_AGENCY_TAX_NO);

				booleanQuery.add(query, BooleanClauseOccur.MUST);

			}
		}

		if (Validator.isNotNull(groupId)) {
			MultiMatchQuery query = new MultiMatchQuery(groupId);

			query.addFields(Field.GROUP_ID);

			booleanQuery.add(query, BooleanClauseOccur.MUST);
		}

		booleanQuery.addRequiredTerm(Field.ENTRY_CLASS_NAME, CLASS_NAME);

		return IndexSearcherHelperUtil.searchCount(searchContext, booleanQuery);
	}

	// LamTV_Process output DB
	@Indexable(type = IndexableType.REINDEX)
	public PaymentConfig updatePaymentConfigDB(long userId, long groupId, String govAgencyCode, String govAgencyName,
			String govAgencyTaxNo, String invoiceTemplateNo, String invoiceIssueNo, String invoiceLastNo,
			String bankInfo, String epaymentConfig, ServiceContext serviceContext) throws PortalException {

		Date now = new Date();
		User userAction = userPersistence.fetchByPrimaryKey(userId);

		PaymentConfig object = paymentConfigPersistence.fetchByFB_GID_govAgencyCode(groupId, govAgencyCode);

		if (object == null) {
			long paymentConfigId = counterLocalService.increment(PaymentConfig.class.getName());
			object = paymentConfigPersistence.create(paymentConfigId);

		// Add audit fields
		object.setCompanyId(serviceContext.getCompanyId());
		object.setGroupId(groupId);
		object.setCreateDate(now);
		object.setModifiedDate(now);
		object.setUserId(userAction.getUserId());
		object.setUserName(userAction.getFullName());

			object.setGovAgencyCode(govAgencyCode);
			object.setGovAgencyName(govAgencyName);
			object.setGovAgencyTaxNo(govAgencyTaxNo);
			object.setInvoiceTemplateNo(invoiceTemplateNo);
			object.setInvoiceIssueNo(invoiceIssueNo);
			object.setInvoiceLastNo(invoiceLastNo);
			object.setBankInfo(bankInfo);
			object.setEpaymentConfig(epaymentConfig);
		} else {
			// Add audit fields
			object.setModifiedDate(now);
			object.setUserId(userAction.getUserId());
			object.setUserName(userAction.getFullName());

			if (Validator.isNotNull(govAgencyName)) {
				object.setGovAgencyName(govAgencyName);
			}
			
			if (Validator.isNotNull(govAgencyTaxNo)) {
				object.setGovAgencyTaxNo(govAgencyTaxNo);
			}
			if (Validator.isNotNull(invoiceTemplateNo)) {
				object.setInvoiceTemplateNo(invoiceTemplateNo);
			}
			if (Validator.isNotNull(invoiceIssueNo)) {
				object.setInvoiceIssueNo(invoiceIssueNo);
			}
			if (Validator.isNotNull(invoiceLastNo)) {
				object.setInvoiceLastNo(invoiceLastNo);
			}
			if (Validator.isNotNull(bankInfo)) {
				object.setBankInfo(bankInfo);
			}
			if (Validator.isNotNull(epaymentConfig)) {
				object.setEpaymentConfig(epaymentConfig);
			}
		}

		return paymentConfigPersistence.update(object);
	}

	private void validateDelete(long paymentConfigId) throws PortalException {
		// TODO add logic fod remove paymentConfig in here
	}

	private void validateUpdate(long groupId, long paymentConfigId, String govAgencyCode, String govAgencyName,
			String govAgencyTaxNo, String invoiceTemplateNo, String invoiceIssueNo, String invoiceLastNo,
			String invoiceForm, String bankInfo, String epaymentConfig) throws PortalException {

	}

	// super_admin Generators
	@Indexable(type = IndexableType.DELETE)
	public PaymentConfig adminProcessDelete(Long id) {

		PaymentConfig object = paymentConfigPersistence.fetchByPrimaryKey(id);

		if (Validator.isNull(object)) {
			return null;
		} else {
			paymentConfigPersistence.remove(object);
		}

		return object;
	}

	@Indexable(type = IndexableType.REINDEX)
	public PaymentConfig adminProcessData(JSONObject objectData) {

		PaymentConfig object = null;

		if (objectData.getLong("paymentConfigId") > 0) {

			object = paymentConfigPersistence.fetchByPrimaryKey(objectData.getLong("paymentConfigId"));

			object.setModifiedDate(new Date());

		} else {

			long id = CounterLocalServiceUtil.increment(PaymentConfig.class.getName());

			object = paymentConfigPersistence.create(id);

			object.setGroupId(objectData.getLong("groupId"));
			object.setCompanyId(objectData.getLong("companyId"));
			object.setCreateDate(new Date());

		}

		object.setUserId(objectData.getLong("userId"));
		object.setUserName(objectData.getString("userName"));

		object.setGovAgencyCode(objectData.getString("govAgencyCode"));
		object.setGovAgencyName(objectData.getString("govAgencyName"));
		object.setGovAgencyTaxNo(objectData.getString("govAgencyTaxNo"));
		object.setInvoiceTemplateNo(objectData.getString("invoiceTemplateNo"));
		object.setInvoiceIssueNo(objectData.getString("invoiceIssueNo"));
		object.setInvoiceLastNo(objectData.getString("invoiceLastNo"));
		object.setInvoiceForm(objectData.getString("invoiceForm"));
		object.setBankInfo(objectData.getString("bankInfo"));
		object.setEpaymentConfig(objectData.getString("epaymentConfig"));

		paymentConfigPersistence.update(object);

		return object;
	}

	public PaymentConfig getByInvoiceTemplateNo(long groupId, String invoiceTemplateNo) {
		return paymentConfigPersistence.fetchByG_ITN(groupId, invoiceTemplateNo);
	}

	public List<PaymentConfig> findByG(long groupId) {
		return paymentConfigPersistence.findByFB_GID(groupId);
	}
	public static final String CLASS_NAME = PaymentConfig.class.getName();

}