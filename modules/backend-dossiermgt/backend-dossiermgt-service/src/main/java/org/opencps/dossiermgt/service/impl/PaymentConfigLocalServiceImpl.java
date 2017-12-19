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

import java.util.Date;
import java.util.LinkedHashMap;

import org.opencps.dossiermgt.constants.PaymentConfigTerm;
import org.opencps.dossiermgt.model.PaymentConfig;
import org.opencps.dossiermgt.service.base.PaymentConfigLocalServiceBaseImpl;

import com.liferay.portal.kernel.exception.PortalException;
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
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;

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
	 * org.opencps.dossiermgt.service.PaymentConfigLocalServiceUtil} to access
	 * the payment config local service.
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
			//object.setInvoiceForm(invoiceForm);
			object.setBankInfo(bankInfo);
			//object.setEpaymentConfig(epaymentConfig);

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

	private void validateDelete(long paymentConfigId) throws PortalException {
		// TODO add logic fod remove paymentConfig in here
	}

	private void validateUpdate(long groupId, long paymentConfigId, String govAgencyCode, String govAgencyName,
			String govAgencyTaxNo, String invoiceTemplateNo, String invoiceIssueNo, String invoiceLastNo,
			String invoiceForm, String bankInfo, String epaymentConfig) throws PortalException {

	}

	public static final String CLASS_NAME = PaymentConfig.class.getName();

}