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

import org.opencps.datamgt.model.VotingResult;
import org.opencps.datamgt.service.base.VotingResultLocalServiceBaseImpl;

import aQute.bnd.annotation.ProviderType;

/**
 * The implementation of the voting result local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are
 * added, rerun ServiceBuilder to copy their definitions into the
 * {@link org.opencps.datamgt.service.VotingResultLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security
 * checks based on the propagated JAAS credentials because this service can only
 * be accessed from within the same VM.
 * </p>
 *
 * @author Binhth
 * @see VotingResultLocalServiceBaseImpl
 * @see org.opencps.datamgt.service.VotingResultLocalServiceUtil
 */
@ProviderType
public class VotingResultLocalServiceImpl extends VotingResultLocalServiceBaseImpl {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this class directly. Always use {@link
	 * org.opencps.datamgt.service.VotingResultLocalServiceUtil} to access the
	 * voting result local service.
	 */

	// super_admin Generators
	@Indexable(type = IndexableType.DELETE)
	public VotingResult adminProcessDelete(Long id) {

		VotingResult object = votingResultPersistence.fetchByPrimaryKey(id);

		if (Validator.isNull(object)) {
			return null;
		} else {
			votingResultPersistence.remove(object);
		}

		return object;
	}

	@Indexable(type = IndexableType.REINDEX)
	public VotingResult adminProcessData(JSONObject objectData) {

		VotingResult object = null;

		if (objectData.getLong("votingResultId") > 0) {

			object = votingResultPersistence.fetchByPrimaryKey(objectData.getLong("votingResultId"));

			object.setModifiedDate(new Date());

		} else {

			long id = CounterLocalServiceUtil.increment(VotingResult.class.getName());

			object = votingResultPersistence.create(id);

			object.setGroupId(objectData.getLong("groupId"));
			object.setCompanyId(objectData.getLong("companyId"));
			object.setCreateDate(new Date());

		}

		object.setUserId(objectData.getLong("userId"));

		object.setVotingId(objectData.getLong("votingId"));
		object.setFullname(objectData.getString("fullname"));
		object.setEmail(objectData.getString("email"));
		object.setComment(objectData.getString("comment"));
		object.setSelected(objectData.getString("selected"));

		votingResultPersistence.update(object);

		return object;
	}
}