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

package org.mobilink.backend.usermgt.service.impl;

import java.util.Date;
import java.util.LinkedHashMap;

import org.mobilink.backend.usermgt.constants.ContactsTerm;
import org.mobilink.backend.usermgt.model.Contact;
import org.mobilink.backend.usermgt.service.base.ContactLocalServiceBaseImpl;

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
import com.liferay.portal.kernel.search.TermQuery;
import com.liferay.portal.kernel.search.generic.MatchQuery.Operator;
import com.liferay.portal.kernel.search.generic.MultiMatchQuery;
import com.liferay.portal.kernel.search.generic.TermQueryImpl;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.Validator;

import aQute.bnd.annotation.ProviderType;

/**
 * The implementation of the contact local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are
 * added, rerun ServiceBuilder to copy their definitions into the
 * {@link org.mobilink.backend.usermgt.service.ContactLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security
 * checks based on the propagated JAAS credentials because this service can only
 * be accessed from within the same VM.
 * </p>
 *
 * @author Binhth
 * @see ContactLocalServiceBaseImpl
 * @see org.mobilink.backend.usermgt.service.ContactLocalServiceUtil
 */
@ProviderType
public class ContactLocalServiceImpl extends ContactLocalServiceBaseImpl {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this class directly. Always use {@link
	 * org.mobilink.backend.usermgt.service.ContactLocalServiceUtil} to access
	 * the contact local service.
	 */
	@Indexable(type = IndexableType.REINDEX)
	@Override
	public Contact addContact(long userId, long groupId, String fullName, String companyName, String telNo,
			String email, long userMappingId, boolean isOrg, int shared, ServiceContext serviceContext)
			throws PortalException {
		// TODO
		Date now = new Date();
		User user = userPersistence.findByPrimaryKey(userId);
		long contactId = counterLocalService.increment(Contact.class.getName());

		Contact contact = contactPersistence.create(contactId);

		contact.setGroupId(groupId);
		contact.setUuid(serviceContext.getUuid());
		contact.setCompanyId(user.getCompanyId());
		contact.setUserId(user.getUserId());
		contact.setUserName(user.getFullName());
		contact.setCreateDate(serviceContext.getCreateDate(now));
		contact.setModifiedDate(serviceContext.getCreateDate(now));
		contact.setFullName(fullName);
		contact.setCompanyName(companyName);
		contact.setTelNo(telNo);
		contact.setEmail(email);
		contact.setUserMappingId(userMappingId);
		contact.setShared(shared);
		contact.setIsOrg(isOrg);
		contact.setExpandoBridgeAttributes(serviceContext);

		contactPersistence.update(contact);

		return contact;
	}

	@Indexable(type = IndexableType.DELETE)
	@Override
	public Contact deleteContact(long contactId, ServiceContext serviceContext) throws PortalException {
		// TODO
		Contact contact = contactPersistence.remove(contactId);

		return contact;
	}

	@Indexable(type = IndexableType.REINDEX)
	@Override
	public Contact updateContact(long userId, long contactId, String fullName, String companyName, String telNo,
			String email, long userMappingId, boolean isOrg, int shared, ServiceContext serviceContext)
			throws PortalException {
		// TODO
		Date now = new Date();
		User user = userPersistence.findByPrimaryKey(userId);

		Contact contact = contactPersistence.fetchByPrimaryKey(contactId);

		contact.setUserId(user.getUserId());
		contact.setUserName(user.getFullName());
		contact.setModifiedDate(serviceContext.getCreateDate(now));
		contact.setFullName(fullName);
		contact.setCompanyName(companyName);
		contact.setTelNo(telNo);
		contact.setEmail(email);
		contact.setUserMappingId(userMappingId);
		contact.setShared(shared);
		contact.setIsOrg(isOrg);
		contact.setExpandoBridgeAttributes(serviceContext);

		contactPersistence.update(contact);

		return contact;
	}

	@Indexable(type = IndexableType.REINDEX)
	@Override
	public Contact updateContactGroupId(long contactId, long groupId, ServiceContext serviceContext)
			throws PortalException {
		// TODO
		Date now = new Date();
		Contact contact = contactPersistence.fetchByPrimaryKey(contactId);

		contact.setGroupId(groupId);
		contact.setModifiedDate(serviceContext.getCreateDate(now));
		contact.setExpandoBridgeAttributes(serviceContext);

		contactPersistence.update(contact);

		return contact;
	}

	public Hits luceneSearchEngine(LinkedHashMap<String, Object> params, Sort[] sorts, int start, int end,
			SearchContext searchContext) throws ParseException, SearchException {
		// TODO
		MultiMatchQuery query = null;
		String keywords = (String) params.get("keywords");
		String groupId = (String) params.get("groupId");
		String userId = (String) params.get("userId");

		Indexer<Contact> indexer = IndexerRegistryUtil.nullSafeGetIndexer(Contact.class);

		searchContext.addFullQueryEntryClassName(Contact.class.getName());
		searchContext.setEntryClassNames(new String[] { Contact.class.getName() });
		searchContext.setAttribute("paginationType", "regular");
		searchContext.setLike(true);
		searchContext.setStart(start);
		searchContext.setEnd(end);
		searchContext.setAndSearch(true);
		searchContext.setSorts(sorts);

		BooleanQuery booleanQuery = null;

		booleanQuery = Validator.isNotNull((String) keywords)
				? BooleanQueryFactoryUtil.create((SearchContext) searchContext) : indexer.getFullQuery(searchContext);

		if (Validator.isNotNull((String) keywords)) {
			String[] keyword = keywords.split(" ");
			for (String string : keyword) {

				query = new MultiMatchQuery(string);

				query.addFields(ContactsTerm.FULL_NAME, ContactsTerm.EMAIL, ContactsTerm.TEL_NO);

				Operator operator = Operator.OR;

				query.setOperator(operator);

				booleanQuery.add(query, BooleanClauseOccur.MUST);

			}
		}

		if (Validator.isNotNull(groupId)) {
			query = new MultiMatchQuery(groupId);

			query.addFields(ContactsTerm.GROUP_ID);

			booleanQuery.add(query, BooleanClauseOccur.MUST);
		}

		if (Validator.isNotNull(userId)) {
			BooleanQuery categoryQuery = Validator.isNotNull((String) keywords)
					? BooleanQueryFactoryUtil.create((SearchContext) searchContext)
					: indexer.getFullQuery(searchContext);
			TermQuery catQuery1 = new TermQueryImpl(ContactsTerm.USER_ID, userId);
			TermQuery catQuery2 = new TermQueryImpl(ContactsTerm.SHARED, String.valueOf(1));

			categoryQuery.add(catQuery1, BooleanClauseOccur.SHOULD);
			categoryQuery.add(catQuery2, BooleanClauseOccur.SHOULD);
			booleanQuery.add(categoryQuery, BooleanClauseOccur.MUST);

		}

		booleanQuery.addRequiredTerm(Field.ENTRY_CLASS_NAME, Contact.class.getName());

		return IndexSearcherHelperUtil.search(searchContext, booleanQuery);
	}

	public Contact fetchByUserMappingId(long userMappingId) {
		return contactPersistence.fetchByF_userMappingId(userMappingId);
	}
}