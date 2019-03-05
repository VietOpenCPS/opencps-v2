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

import com.liferay.counter.kernel.service.CounterLocalServiceUtil;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.exception.NoSuchUserException;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
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

import org.opencps.usermgt.constants.OfficeSiteTerm;
import org.opencps.usermgt.exception.NoSuchOfficeSiteException;
import org.opencps.usermgt.model.OfficeSite;
import org.opencps.usermgt.service.base.OfficeSiteLocalServiceBaseImpl;

import aQute.bnd.annotation.ProviderType;
import backend.auth.api.BackendAuthImpl;
import backend.auth.api.exception.NotFoundException;
import backend.auth.api.exception.UnauthenticationException;
import backend.auth.api.exception.UnauthorizationException;
import backend.auth.api.keys.ActionKeys;
import backend.auth.api.keys.ModelNameKeys;

/**
 * The implementation of the office site local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are
 * added, rerun ServiceBuilder to copy their definitions into the
 * {@link org.opencps.usermgt.service.OfficeSiteLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security
 * checks based on the propagated JAAS credentials because this service can only
 * be accessed from within the same VM.
 * </p>
 *
 * @author Binhth
 * @see OfficeSiteLocalServiceBaseImpl
 * @see org.opencps.usermgt.service.OfficeSiteLocalServiceUtil
 */
@ProviderType
public class OfficeSiteLocalServiceImpl extends OfficeSiteLocalServiceBaseImpl {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this class directly. Always use {@link
	 * org.opencps.usermgt.service.OfficeSiteLocalServiceUtil} to access the office
	 * site local service.
	 */

	private static Log _log = LogFactoryUtil.getLog(OfficeSiteLocalServiceImpl.class);

	@Indexable(type = IndexableType.REINDEX)
	@Override
	public OfficeSite addOfficeSite(long userId, long groupId, String name, String enName, String govAgencyCode,
			String address, String telNo, String faxNo, String email, String website, long logoFileEntryId,
			long siteGroupId, long adminUserId, String preferences, ServiceContext serviceContext)
			throws UnauthenticationException, UnauthorizationException, NoSuchUserException {
		// authen
		BackendAuthImpl authImpl = new BackendAuthImpl();

		boolean isAuth = authImpl.isAuth(serviceContext, StringPool.BLANK, StringPool.BLANK);

		if (!isAuth) {
			throw new UnauthenticationException();
		}

		boolean hasPermission = authImpl.hasResource(serviceContext, ModelNameKeys.WORKINGUNIT_MGT_CENTER,
				ActionKeys.EDIT_DATA);

		if (!hasPermission) {
			throw new UnauthorizationException();
		}

		Date now = new Date();
		User user = userPersistence.findByPrimaryKey(userId);
		long OfficeSiteId = counterLocalService.increment(OfficeSite.class.getName());

		OfficeSite officeSite = officeSitePersistence.create(OfficeSiteId);

		officeSite.setGroupId(groupId);
		officeSite.setUuid(serviceContext.getUuid());
		officeSite.setCompanyId(user.getCompanyId());
		officeSite.setUserId(user.getUserId());
		officeSite.setUserName(user.getFullName());
		officeSite.setCreateDate(serviceContext.getCreateDate(now));
		officeSite.setModifiedDate(serviceContext.getCreateDate(now));

		officeSite.setName(name);
		officeSite.setEnName(enName);
		officeSite.setGovAgencyCode(govAgencyCode);
		officeSite.setAddress(address);
		officeSite.setTelNo(telNo);
		officeSite.setFaxNo(faxNo);
		officeSite.setEmail(email);
		officeSite.setWebsite(website);
		officeSite.setLogoFileEntryId(logoFileEntryId);
		officeSite.setSiteGroupId(siteGroupId);
		officeSite.setAdminUserId(adminUserId);
		officeSite.setPreferences(preferences);

		officeSite.setExpandoBridgeAttributes(serviceContext);

		officeSitePersistence.update(officeSite);

		return officeSite;
	}

	@Indexable(type = IndexableType.DELETE)
	@Override
	public OfficeSite deleteOfficeSite(long officeSiteId, ServiceContext serviceContext)
			throws UnauthenticationException, UnauthorizationException, NotFoundException {
		// authen
		BackendAuthImpl authImpl = new BackendAuthImpl();

		boolean isAuth = authImpl.isAuth(serviceContext, StringPool.BLANK, StringPool.BLANK);

		if (!isAuth) {
			throw new UnauthenticationException();
		}

		boolean hasPermission = authImpl.hasResource(serviceContext, ModelNameKeys.WORKINGUNIT_MGT_CENTER,
				ActionKeys.EDIT_DATA);

		if (!hasPermission) {
			throw new UnauthorizationException();
		}

		OfficeSite officeSite = null;

		try {

			officeSite = officeSitePersistence.remove(officeSiteId);

		} catch (NoSuchOfficeSiteException e) {
			// // TODO Auto-generated catch block
			// throw new NotFoundException();
			_log.error(e);
		}
		return officeSite;
	}

	@Indexable(type = IndexableType.REINDEX)
	@Override
	public OfficeSite updateOfficeSite(long userId, long officeSiteId, String name, String enName, String govAgencyCode,
			String address, String telNo, String faxNo, String email, String website, long logoFileEntryId,
			long siteGroupId, long adminUserId, String preferences, ServiceContext serviceContext)
			throws UnauthenticationException, UnauthorizationException, NotFoundException, NoSuchUserException {
		// authen
		BackendAuthImpl authImpl = new BackendAuthImpl();

		boolean isAuth = authImpl.isAuth(serviceContext, StringPool.BLANK, StringPool.BLANK);

		if (!isAuth) {
			throw new UnauthenticationException();
		}

		boolean hasPermission = authImpl.hasResource(serviceContext, ModelNameKeys.WORKINGUNIT_MGT_CENTER,
				ActionKeys.EDIT_DATA);

		if (!hasPermission) {
			throw new UnauthorizationException();
		}

		Date now = new Date();
		User user = userPersistence.findByPrimaryKey(userId);

		OfficeSite officeSite = officeSitePersistence.fetchByPrimaryKey(officeSiteId);

		officeSite.setUserId(user.getUserId());
		officeSite.setUserName(user.getFullName());
		officeSite.setModifiedDate(serviceContext.getCreateDate(now));

		officeSite.setName(name);
		officeSite.setEnName(enName);
		officeSite.setGovAgencyCode(govAgencyCode);
		officeSite.setAddress(address);
		officeSite.setTelNo(telNo);
		officeSite.setFaxNo(faxNo);
		officeSite.setEmail(email);
		officeSite.setWebsite(website);
		officeSite.setLogoFileEntryId(logoFileEntryId);
		officeSite.setSiteGroupId(siteGroupId);
		officeSite.setAdminUserId(adminUserId);
		officeSite.setPreferences(preferences);

		officeSite.setExpandoBridgeAttributes(serviceContext);

		officeSitePersistence.update(officeSite);

		return officeSite;
	}

	public Hits luceneSearchEngine(LinkedHashMap<String, Object> params, Sort[] sorts, int start, int end,
			SearchContext searchContext) throws ParseException, SearchException {
		// TODO
		MultiMatchQuery query = null;
		String keywords = (String) params.get("keywords");
		String groupId = (String) params.get("groupId");
		String userId = (String) params.get("userId");
		Indexer<OfficeSite> indexer = IndexerRegistryUtil.nullSafeGetIndexer(OfficeSite.class);

		searchContext.addFullQueryEntryClassName(OfficeSite.class.getName());
		searchContext.setEntryClassNames(new String[] { OfficeSite.class.getName() });
		searchContext.setAttribute("paginationType", "regular");
		searchContext.setLike(true);
		searchContext.setStart(start);
		searchContext.setEnd(end);
		searchContext.setAndSearch(true);
		searchContext.setSorts(sorts);

		BooleanQuery booleanQuery = null;

		booleanQuery = Validator.isNotNull((String) keywords)
				? BooleanQueryFactoryUtil.create((SearchContext) searchContext)
				: indexer.getFullQuery(searchContext);

		if (Validator.isNotNull(groupId)) {
			query = new MultiMatchQuery(groupId);

			query.addFields(OfficeSiteTerm.GROUP_ID);

			booleanQuery.add(query, BooleanClauseOccur.MUST);
		}

		if (Validator.isNotNull(userId)) {
			query = new MultiMatchQuery(userId);

			query.addFields(OfficeSiteTerm.USER_ID);

			booleanQuery.add(query, BooleanClauseOccur.MUST);
		}

		booleanQuery.addRequiredTerm(Field.ENTRY_CLASS_NAME, OfficeSite.class.getName());

		return IndexSearcherHelperUtil.search(searchContext, booleanQuery);
	}

	public long countLuceneSearchEngine(LinkedHashMap<String, Object> params, SearchContext searchContext)
			throws ParseException, SearchException {
		// TODO
		MultiMatchQuery query = null;
		String keywords = (String) params.get("keywords");
		String groupId = (String) params.get("groupId");
		String userId = (String) params.get("userId");
		Indexer<OfficeSite> indexer = IndexerRegistryUtil.nullSafeGetIndexer(OfficeSite.class);

		searchContext.addFullQueryEntryClassName(OfficeSite.class.getName());
		searchContext.setEntryClassNames(new String[] { OfficeSite.class.getName() });
		searchContext.setAttribute("paginationType", "regular");
		searchContext.setLike(true);
		searchContext.setAndSearch(true);

		BooleanQuery booleanQuery = null;

		booleanQuery = Validator.isNotNull((String) keywords)
				? BooleanQueryFactoryUtil.create((SearchContext) searchContext)
				: indexer.getFullQuery(searchContext);

		if (Validator.isNotNull(groupId)) {
			query = new MultiMatchQuery(groupId);

			query.addFields(OfficeSiteTerm.GROUP_ID);

			booleanQuery.add(query, BooleanClauseOccur.MUST);
		}

		if (Validator.isNotNull(userId)) {
			query = new MultiMatchQuery(userId);

			query.addFields(OfficeSiteTerm.USER_ID);

			booleanQuery.add(query, BooleanClauseOccur.MUST);
		}

		booleanQuery.addRequiredTerm(Field.ENTRY_CLASS_NAME, OfficeSite.class.getName());

		return IndexSearcherHelperUtil.searchCount(searchContext, booleanQuery);
	}

	public OfficeSite fetchF_groupId_siteGroupId(long groupId, long siteGroupId) {

		OfficeSite officeSite = officeSitePersistence.fetchByF_groupId_siteGroupId(groupId, siteGroupId);

		return officeSite;

	}

	// super_admin Generators
	@Indexable(type = IndexableType.DELETE)
	public OfficeSite adminProcessDelete(Long id) {

		OfficeSite object = officeSitePersistence.fetchByPrimaryKey(id);

		if (Validator.isNull(object)) {
			return null;
		} else {
			officeSitePersistence.remove(object);
		}

		return object;
	}

	@Indexable(type = IndexableType.REINDEX)
	public OfficeSite adminProcessData(JSONObject objectData) {

		OfficeSite object = null;

		if (objectData.getLong("officeSiteId") > 0) {

			object = officeSitePersistence.fetchByPrimaryKey(objectData.getLong("officeSiteId"));

			object.setModifiedDate(new Date());

		} else {

			long id = CounterLocalServiceUtil.increment(OfficeSite.class.getName());

			object = officeSitePersistence.create(id);

			object.setGroupId(objectData.getLong("groupId"));
			object.setCompanyId(objectData.getLong("companyId"));
			object.setCreateDate(new Date());

		}

		object.setUserId(objectData.getLong("userId"));

		object.setName(objectData.getString("name"));
		object.setEnName(objectData.getString("enName"));
		object.setGovAgencyCode(objectData.getString("govAgencyCode"));
		object.setAddress(objectData.getString("address"));
		object.setTelNo(objectData.getString("telNo"));
		object.setFaxNo(objectData.getString("faxNo"));
		object.setEmail(objectData.getString("email"));
		object.setWebsite(objectData.getString("website"));
		// object.setLogoFileEntryId(objectData.getString("actionCode")logoFileEntryId);
		object.setSiteGroupId(objectData.getLong("siteGroupId"));
		object.setAdminUserId(objectData.getLong("adminUserId"));
		object.setPreferences(objectData.getString("preferences"));
		object.setCeremonyDate(new Date(objectData.getLong("ceremonyDate")));

		officeSitePersistence.update(object);

		return object;
	}
}