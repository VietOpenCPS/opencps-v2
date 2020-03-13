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

import com.liferay.counter.kernel.service.CounterLocalServiceUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.search.Indexable;
import com.liferay.portal.kernel.search.IndexableType;
import com.liferay.portal.kernel.util.Validator;

import java.util.Date;

import org.opencps.datamgt.model.Voting;
import org.opencps.datamgt.service.base.VotingLocalServiceBaseImpl;

import aQute.bnd.annotation.ProviderType;

/**
 * The implementation of the voting local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are
 * added, rerun ServiceBuilder to copy their definitions into the
 * {@link org.opencps.datamgt.service.VotingLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security
 * checks based on the propagated JAAS credentials because this service can only
 * be accessed from within the same VM.
 * </p>
 *
 * @author Binhth
 * @see VotingLocalServiceBaseImpl
 * @see org.opencps.datamgt.service.VotingLocalServiceUtil
 */
@ProviderType
public class VotingLocalServiceImpl extends VotingLocalServiceBaseImpl {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this class directly. Always use {@link
	 * org.opencps.datamgt.service.VotingLocalServiceUtil} to access the voting
	 * local service.
	 */

	// super_admin Generators
	@Indexable(type = IndexableType.DELETE)
	public Voting adminProcessDelete(Long id) {

		Voting object = votingPersistence.fetchByPrimaryKey(id);

		if (Validator.isNull(object)) {
			return null;
		} else {
			votingPersistence.remove(object);
		}

		return object;
	}

	@Indexable(type = IndexableType.REINDEX)
	public Voting adminProcessData(JSONObject objectData) {

		Voting object = null;

		if (objectData.getLong("votingId") > 0) {

			object = votingPersistence.fetchByPrimaryKey(objectData.getLong("votingId"));

			object.setModifiedDate(new Date());

		} else {

			long id = CounterLocalServiceUtil.increment(Voting.class.getName());

			object = votingPersistence.create(id);

			object.setGroupId(objectData.getLong("groupId"));
			object.setCompanyId(objectData.getLong("companyId"));
			object.setCreateDate(new Date());

		}

		object.setUserId(objectData.getLong("userId"));

		object.setClassName(objectData.getString("className"));
		object.setClassPK(objectData.getString("classPK"));
		object.setSubject(objectData.getString("subject"));
		object.setChoices(objectData.getString("choices"));
		object.setTemplateNo(objectData.getString("templateNo"));
		object.setCommentable(objectData.getBoolean("commentable"));

		votingPersistence.update(object);

		return object;
	}

}