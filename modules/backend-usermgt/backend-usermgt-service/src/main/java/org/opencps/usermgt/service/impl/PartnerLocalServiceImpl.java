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

import java.util.Calendar;
import java.util.Date;
import java.util.LinkedHashMap;

import org.opencps.usermgt.model.Partner;
import org.opencps.usermgt.service.base.PartnerLocalServiceBaseImpl;

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
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.util.PwdGenerator;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;

import aQute.bnd.annotation.ProviderType;

/**
 * The implementation of the partner local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are
 * added, rerun ServiceBuilder to copy their definitions into the
 * {@link org.opencps.usermgt.service.PartnerLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security
 * checks based on the propagated JAAS credentials because this service can only
 * be accessed from within the same VM.
 * </p>
 *
 * @author Binhth
 * @see PartnerLocalServiceBaseImpl
 * @see org.opencps.usermgt.service.PartnerLocalServiceUtil
 */
@ProviderType
public class PartnerLocalServiceImpl extends PartnerLocalServiceBaseImpl {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this class directly. Always use {@link
	 * org.mobilink.backend.usermgt.service.PartnerLocalServiceUtil} to access
	 * the partner local service.
	 */

	@Indexable(type = IndexableType.REINDEX)
	@Override
	public Partner addPartner(long userId, long groupId, long organizationId, String name, String address, String telNo,
			String faxNo, String email, String website, int partnerClass, String docFileId,
			ServiceContext serviceContext) throws PortalException {
		Date now = new Date();

		User user = userPersistence.findByPrimaryKey(userId);

		long partnerId = counterLocalService.increment(Partner.class.getName());

		Partner partner = partnerPersistence.create(partnerId);

		// Group instance
		partner.setGroupId(groupId);

		// Audit fields
		partner.setUuid(serviceContext.getUuid());
		partner.setCompanyId(user.getCompanyId());
		partner.setUserId(user.getUserId());
		partner.setUserName(user.getFullName());
		partner.setCreateDate(serviceContext.getCreateDate(now));
		partner.setModifiedDate(serviceContext.getCreateDate(now));

		// Other fields
		partner.setName(name);
		partner.setAddress(address);
		partner.setTelNo(telNo);
		partner.setFaxNo(faxNo);
		partner.setEmail(email);
		partner.setWebsite(website);
		partner.setPartnerClass(partnerClass);
		partner.setDocFileId(docFileId);

		partner.setExpandoBridgeAttributes(serviceContext);

		// add user

		long[] userGroupIds = {};
		long[] roleIds = {};
		long[] organizationIds = new long[] { organizationId };
		long[] groupIds = { groupId };

		String screenName = email.substring(0, email.indexOf("@"));

		String passWord = PwdGenerator.getPassword();

		// System.out.println("PartnerLocalServiceImpl.mAddPartner()"+passWord);

		String[] fullNameSub = name.split(" ");

		String firstName = StringPool.BLANK;
		String lastName = StringPool.BLANK;

		if (fullNameSub.length > 1) {
			firstName = name.replaceFirst(fullNameSub[0], firstName);
			lastName = fullNameSub[0];
		} else {
			firstName = screenName;
			lastName = name;
		}

		User newUser = UserLocalServiceUtil.addUser(0, user.getCompanyId(), false, passWord, passWord, false,
				screenName.toLowerCase(), email, 0, StringPool.BLANK, serviceContext.getLocale(), firstName,
				StringPool.BLANK, lastName, 0, 0, true, Calendar.JANUARY, 1, 1979, StringPool.BLANK, groupIds,
				organizationIds, roleIds, userGroupIds, false, serviceContext);

		Indexer<User> indexer = IndexerRegistryUtil.nullSafeGetIndexer(User.class);

		indexer.reindex(newUser);

		partner.setAccountUserId(newUser.getUserId());

		partnerPersistence.update(partner);

		return partner;
	}

	@Indexable(type = IndexableType.DELETE)
	@Override
	public Partner deletePartner(long partnerId, ServiceContext serviceContext) throws Exception {

		Partner partner = partnerPersistence.remove(partnerId);

		return partner;

	}

	@Indexable(type = IndexableType.REINDEX)
	@Override
	public Partner updatePartner(long userId, long partnerId, String name, String address, String telNo,
			String faxNo, String email, String website, int partnerClass, String docFileId,
			ServiceContext serviceContext) throws PortalException {
		Date now = new Date();

		User user = userPersistence.findByPrimaryKey(userId);

		Partner partner = partnerPersistence.fetchByPrimaryKey(partnerId);

		// Audit fields
		partner.setUserId(user.getUserId());
		partner.setUserName(user.getFullName());
		partner.setModifiedDate(serviceContext.getCreateDate(now));

		// Other fields
		partner.setName(name);
		partner.setAddress(address);
		partner.setTelNo(telNo);
		partner.setFaxNo(faxNo);
		partner.setEmail(email);
		partner.setWebsite(website);
		partner.setPartnerClass(partnerClass);
		partner.setDocFileId(docFileId);

		partnerPersistence.update(partner);

		return partner;
	}

	@SuppressWarnings("deprecation")
	public Hits luceneSearchEngine(LinkedHashMap<String, Object> params, Sort[] sorts, int start, int end,
			SearchContext searchContext) throws ParseException, SearchException {
		// TODO Auto-generated method stub
		String keywords = (String) params.get("keywords");
		String groupId = (String) params.get("groupId");

		Indexer<Partner> indexer = IndexerRegistryUtil.nullSafeGetIndexer(Partner.class);

		searchContext.addFullQueryEntryClassName(Partner.class.getName());
		searchContext.setEntryClassNames(new String[] { Partner.class.getName() });
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

		if (Validator.isNotNull(groupId)) {
			MultiMatchQuery query = new MultiMatchQuery(groupId);

			query.addFields("groupId");

			booleanQuery.add(query, BooleanClauseOccur.MUST);
		}

		booleanQuery.addRequiredTerm(Field.ENTRY_CLASS_NAME, Partner.class.getName());

		return IndexSearcherHelperUtil.search(searchContext, booleanQuery);

	}
}