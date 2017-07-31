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

package org.mobilink.backend.datamgt.service.impl;

import java.util.Date;
import java.util.LinkedHashMap;

import org.mobilink.backend.datamgt.constants.LocationTerm;
import org.mobilink.backend.datamgt.model.Location;
import org.mobilink.backend.datamgt.service.base.LocationLocalServiceBaseImpl;

import com.liferay.portal.kernel.exception.PortalException;
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
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;

import aQute.bnd.annotation.ProviderType;

/**
 * The implementation of the location local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link org.mobilink.backend.datamgt.service.LocationLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author Binhth
 * @see LocationLocalServiceBaseImpl
 * @see org.mobilink.backend.datamgt.service.LocationLocalServiceUtil
 */
@ProviderType
public class LocationLocalServiceImpl extends LocationLocalServiceBaseImpl {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this class directly. Always use {@link org.mobilink.backend.datamgt.service.LocationLocalServiceUtil} to access the location local service.
	 */
	public Location addLocation(long userId, long groupId,
			String location, String geolocation, ServiceContext serviceContext) throws Exception {

		Date now = new Date();

		User user = userPersistence.findByPrimaryKey(userId);

		long resourceId = counterLocalService.increment(Location.class.getName());

		Location resource = locationPersistence.create(resourceId);

		// Group instance
		resource.setGroupId(groupId);

		// Audit fields
		resource.setUuid(serviceContext.getUuid());
		resource.setCompanyId(user.getCompanyId());
		resource.setUserId(user.getUserId());
		resource.setUserName(user.getFullName());
		resource.setCreateDate(serviceContext.getCreateDate(now));
		resource.setModifiedDate(serviceContext.getCreateDate(now));

		// Other fields
		resource.setLocation(location);
		resource.setGeolocation(geolocation);

		resource.setExpandoBridgeAttributes(serviceContext);

		locationPersistence.update(resource);

		return resource;
	}

	/**
	 * @param dictCollectionId
	 * @param serviceContext
	 * @return
	 */
	@Indexable(type = IndexableType.DELETE)
	@Override
	public Location deleteLocation(long locationId, ServiceContext serviceContext) throws PortalException {

		Location Location = locationPersistence.remove(locationId);

		return Location;
		
	}

	/**
	 * @param userId
	 * @param dictCollectionId
	 * @param fullName
	 * @param companyName
	 * @param telNo
	 * @param email
	 * @param mobilinkId
	 * @param userMappingId
	 * @param outSide
	 * @param isOrg
	 * @param serviceContext
	 * @return
	 * @throws Exception 
	 */
	@Indexable(type = IndexableType.REINDEX)
	@Override
	public Location updateLocation(long userId, long groupId, long locationId, 
			String location, String geolocation, ServiceContext serviceContext) throws Exception {

		Date now = new Date();

		User user = userPersistence.findByPrimaryKey(userId);

		Location resource = locationPersistence.fetchByPrimaryKey(locationId);

		// Group instance
		resource.setGroupId(groupId);

		// Audit fields
		resource.setUuid(serviceContext.getUuid());
		resource.setCompanyId(user.getCompanyId());
		resource.setUserId(user.getUserId());
		resource.setUserName(user.getFullName());
		resource.setCreateDate(serviceContext.getCreateDate(now));
		resource.setModifiedDate(serviceContext.getCreateDate(now));

		// Other fields
		resource.setLocation(location);
		resource.setGeolocation(geolocation);
				
		resource.setExpandoBridgeAttributes(serviceContext);

		locationPersistence.update(resource);

		return resource;
	}

	public Hits luceneSearchEngine(LinkedHashMap<String, Object> params, Sort[] sorts, int start, int end,
			SearchContext searchContext) throws ParseException, SearchException {
		// TODO Auto-generated method stub

		Indexer<Location> indexer = IndexerRegistryUtil.nullSafeGetIndexer(Location.class);

		searchContext.addFullQueryEntryClassName(Location.class.getName());
		searchContext.setEntryClassNames(new String[] { Location.class.getName() });
		searchContext.setAttribute("paginationType", "regular");
		searchContext.setLike(true);
		searchContext.setStart(start);
		searchContext.setEnd(end);
		searchContext.setAndSearch(true);
		searchContext.setSorts(sorts);

		searchContext.setAttribute("params", params);

		// LAY CAC THAM SO TRONG PARAMS.
		String keywords = (String) params.get("keywords");
		String groupId = (String) params.get("groupId");
		String userId = (String) params.get("userId");
		
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

				query.addFields(LocationTerm.LOCATION);

				booleanQuery.add(query, BooleanClauseOccur.MUST);

			}
		}

		if(Validator.isNotNull(groupId)){
			MultiMatchQuery query = new MultiMatchQuery(groupId);
			
			query.addFields(LocationTerm.GROUP_ID);
			
			booleanQuery.add(query, BooleanClauseOccur.MUST);
		}
		
		if(Validator.isNotNull(userId)){
			MultiMatchQuery query = new MultiMatchQuery(userId);
			
			query.addFields(LocationTerm.USER_ID);
			
			booleanQuery.add(query, BooleanClauseOccur.MUST);
		}
		booleanQuery.addRequiredTerm(Field.ENTRY_CLASS_NAME, Location.class.getName());

		return IndexSearcherHelperUtil.search(searchContext, booleanQuery);

	}
	
	private static final Log _log = LogFactoryUtil.getLog(LocationLocalServiceImpl.class);
}