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

package org.opencps.datamgt.service.impl;

import java.util.Date;
import java.util.LinkedHashMap;

import org.opencps.datamgt.constants.LabelTerm;
import org.opencps.datamgt.model.Label;
import org.opencps.datamgt.service.base.LabelLocalServiceBaseImpl;

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
import com.liferay.portal.kernel.search.TermQuery;
import com.liferay.portal.kernel.search.generic.MultiMatchQuery;
import com.liferay.portal.kernel.search.generic.TermQueryImpl;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;

import aQute.bnd.annotation.ProviderType;

/**
 * The implementation of the label local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link org.opencps.datamgt.service.LabelLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author Binhth
 * @see LabelLocalServiceBaseImpl
 * @see org.opencps.datamgt.service.LabelLocalServiceUtil
 */
@ProviderType
public class LabelLocalServiceImpl extends LabelLocalServiceBaseImpl {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this class directly. Always use {@link org.mobilink.backend.datamgt.service.LabelLocalServiceUtil} to access the label local service.
	 */
	@Indexable(type = IndexableType.REINDEX)
	@Override
	public Label addLable(long userId, long groupId, String name, String color, int scope,
			ServiceContext serviceContext) throws Exception {

		Date now = new Date();

		User user = userPersistence.findByPrimaryKey(userId);

		long labelId = counterLocalService.increment(Label.class.getName());

		Label label = labelPersistence.create(labelId);

		// Group instance
		label.setGroupId(groupId);

		// Audit fields
		label.setUuid(serviceContext.getUuid());
		label.setCompanyId(user.getCompanyId());
		label.setUserId(user.getUserId());
		label.setUserName(user.getFullName());
		label.setCreateDate(serviceContext.getCreateDate(now));
		label.setModifiedDate(serviceContext.getCreateDate(now));

		label.setName(name);
		label.setColor(color);
		label.setScope(scope);
		// BaseModel<?> baseModel =
		// LabelLocalServiceUtil.fetchLabel(LabelId);

		label.setExpandoBridgeAttributes(serviceContext);

		labelPersistence.update(label);

		return label;
	}

	/**
	 * @param dictCollectionId
	 * @param serviceContext
	 * @return
	 * @throws Exception
	 */
	@Indexable(type = IndexableType.DELETE)
	@Override
	public Label deleteLabel(long labelId, ServiceContext serviceContext) throws Exception {

		Label label = labelPersistence.remove(labelId);

		return label;

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
	public Label updateLabel(long userId, long groupId, long labelId, String name, String color, int scope,
			ServiceContext serviceContext) throws Exception {

		Date now = new Date();

		User user = userPersistence.findByPrimaryKey(userId);

		Label label = labelPersistence.fetchByPrimaryKey(labelId);

		// Group instance
		label.setGroupId(groupId);

		// Audit fields
		label.setUserId(user.getUserId());
		label.setUserName(user.getFullName());
		label.setModifiedDate(serviceContext.getCreateDate(now));

		// Other fields

		label.setName(name);
		label.setColor(color);
		label.setScope(scope);

		label.setExpandoBridgeAttributes(serviceContext);

		labelPersistence.update(label);

		return label;
	}

	public Hits luceneSearchEngine(LinkedHashMap<String, Object> params, Sort[] sorts, int start, int end,
			SearchContext searchContext) throws ParseException, SearchException {
		// TODO Auto-generated method stub

		Indexer<Label> indexer = IndexerRegistryUtil.nullSafeGetIndexer(Label.class);

		searchContext.addFullQueryEntryClassName(Label.class.getName());
		searchContext.setEntryClassNames(new String[] { Label.class.getName() });
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
		String labelIds = (String) params.get("labelIds");
		String scopes = (String) params.get("scopes");

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

				query.addFields(LabelTerm.LABEL_NAME);

				booleanQuery.add(query, BooleanClauseOccur.MUST);

			}
		}

		if (Validator.isNotNull(groupId)) {
			MultiMatchQuery query = new MultiMatchQuery(groupId);

			query.addFields(LabelTerm.GROUP_ID);

			booleanQuery.add(query, BooleanClauseOccur.MUST);
		}

		if (Validator.isNotNull(userId)) {
			MultiMatchQuery query = new MultiMatchQuery(userId);

			query.addFields(LabelTerm.USER_ID);

			booleanQuery.add(query, BooleanClauseOccur.MUST);
		}

		if (Validator.isNotNull(labelIds)) {

			BooleanQuery categoryQuery = Validator.isNotNull((String) keywords)
					? BooleanQueryFactoryUtil.create((SearchContext) searchContext)
					: indexer.getFullQuery(searchContext);

			String[] labelIdArray = labelIds.split(",");

			for (String string : labelIdArray) {

				TermQuery catQuery1 = new TermQueryImpl(LabelTerm.LABEL_ID, string);

				categoryQuery.add(catQuery1, BooleanClauseOccur.SHOULD);
			}

			booleanQuery.add(categoryQuery, BooleanClauseOccur.MUST);
		}

		if (Validator.isNotNull(scopes)) {
			BooleanQuery categoryQuery = Validator.isNotNull((String) keywords)
					? BooleanQueryFactoryUtil.create((SearchContext) searchContext)
					: indexer.getFullQuery(searchContext);

			String[] scopeArray = scopes.split(",");

			for (String string : scopeArray) {

				TermQuery catQuery1 = new TermQueryImpl(LabelTerm.SCOPE, string);

				categoryQuery.add(catQuery1, BooleanClauseOccur.SHOULD);
			}

			booleanQuery.add(categoryQuery, BooleanClauseOccur.MUST);
		}

		booleanQuery.addRequiredTerm(Field.ENTRY_CLASS_NAME, Label.class.getName());

		return IndexSearcherHelperUtil.search(searchContext, booleanQuery);

	}

	private static final Log _log = LogFactoryUtil.getLog(LabelLocalServiceImpl.class);
}