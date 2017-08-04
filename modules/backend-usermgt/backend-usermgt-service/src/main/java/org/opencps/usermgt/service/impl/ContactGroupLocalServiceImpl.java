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

package org.opencps.usermgt.service.impl;

import java.util.Date;
import java.util.LinkedHashMap;

import org.opencps.usermgt.constants.ContactGroupTerm;
import org.opencps.usermgt.model.ContactGroup;
import org.opencps.usermgt.service.base.ContactGroupLocalServiceBaseImpl;

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
import com.liferay.portal.kernel.util.Validator;

import aQute.bnd.annotation.ProviderType;

/**
 * The implementation of the contact group local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link org.opencps.usermgt.service.ContactGroupLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author Binhth
 * @see ContactGroupLocalServiceBaseImpl
 * @see org.opencps.usermgt.service.ContactGroupLocalServiceUtil
 */
@ProviderType
public class ContactGroupLocalServiceImpl
	extends ContactGroupLocalServiceBaseImpl {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this class directly. Always use {@link org.mobilink.backend.usermgt.service.ContactGroupLocalServiceUtil} to access the contact group local service.
	 */
	@Indexable(type = IndexableType.REINDEX)
	@Override
	public ContactGroup addContactGroup(long userId, long groupId, String groupName, String contactList, int shared,
			ServiceContext serviceContext) throws PortalException {
		// TODO
		Date now = new Date();
		User user = userPersistence.findByPrimaryKey(userId);
		long contactGroupId = counterLocalService.increment(ContactGroup.class.getName());

		ContactGroup contactGroup = contactGroupPersistence.create(contactGroupId);

		contactGroup.setGroupId(groupId);
		contactGroup.setUuid(serviceContext.getUuid());
		contactGroup.setCompanyId(user.getCompanyId());
		contactGroup.setUserId(user.getUserId());
		contactGroup.setUserName(user.getFullName());
		contactGroup.setCreateDate(serviceContext.getCreateDate(now));
		contactGroup.setModifiedDate(serviceContext.getCreateDate(now));
		
		contactGroup.setGroupName(groupName);
		contactGroup.setContactList(contactList);
		contactGroup.setShared(shared);

		contactGroup.setExpandoBridgeAttributes(serviceContext);

		contactGroupPersistence.update(contactGroup);

		return contactGroup;
	}

	@Indexable(type = IndexableType.DELETE)
	@Override
	public ContactGroup deleteContactGroup(long contactGroupId, ServiceContext serviceContext) throws PortalException {
		// TODO
		ContactGroup contactGroup = contactGroupPersistence.remove(contactGroupId);

		return contactGroup;
	}

	@Indexable(type = IndexableType.REINDEX)
	@Override
	public ContactGroup updateContactGroup(long userId, long contactGroupId, String groupName, String contactList, int shared,
			ServiceContext serviceContext) throws PortalException {
		// TODO
		Date now = new Date();
		User user = userPersistence.findByPrimaryKey(userId);

		ContactGroup contactGroup = contactGroupPersistence.fetchByPrimaryKey(contactGroupId);

		contactGroup.setUserId(user.getUserId());
		contactGroup.setUserName(user.getFullName());
		contactGroup.setModifiedDate(serviceContext.getCreateDate(now));
		
		contactGroup.setGroupName(groupName);
		contactGroup.setContactList(contactList);
		contactGroup.setShared(shared);
		
		contactGroup.setExpandoBridgeAttributes(serviceContext);

		contactGroupPersistence.update(contactGroup);
		
		return contactGroup;
	}

	public Hits luceneSearchEngine(LinkedHashMap<String, Object> params, Sort[] sorts, int start, int end,
			SearchContext searchContext) throws ParseException, SearchException {
		// TODO
		MultiMatchQuery query = null;
		String keywords = (String) params.get("keywords");
		String groupId = (String) params.get("groupId");
		String userId = (String) params.get("userId");
		Indexer<ContactGroup> indexer = IndexerRegistryUtil.nullSafeGetIndexer(ContactGroup.class);

		searchContext.addFullQueryEntryClassName(ContactGroup.class.getName());
		searchContext.setEntryClassNames(new String[] { ContactGroup.class.getName() });
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

				query.addFields(ContactGroupTerm.GROUP_NAME);

				booleanQuery.add(query, BooleanClauseOccur.MUST);

			}
		}
		
		if(Validator.isNotNull(groupId)){
			query = new MultiMatchQuery(groupId);
			
			query.addFields(ContactGroupTerm.GROUP_ID);
			
			booleanQuery.add(query, BooleanClauseOccur.MUST);
		}
		
		if(Validator.isNotNull(userId)){
			query = new MultiMatchQuery(userId);
			
			query.addFields(ContactGroupTerm.USER_ID);
			
			booleanQuery.add(query, BooleanClauseOccur.MUST);
		}
		
		booleanQuery.addRequiredTerm(Field.ENTRY_CLASS_NAME, ContactGroup.class.getName());

		return IndexSearcherHelperUtil.search(searchContext, booleanQuery);
	}
}