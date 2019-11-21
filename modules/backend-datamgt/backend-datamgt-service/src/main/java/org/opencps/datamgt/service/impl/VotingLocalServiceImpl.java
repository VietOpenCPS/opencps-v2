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
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.search.Indexable;
import com.liferay.portal.kernel.search.IndexableType;
import com.liferay.portal.kernel.util.Validator;

import java.util.Date;

import org.opencps.datamgt.constants.VotingTerm;
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

		if (objectData.getLong(VotingTerm.VOTING_ID) > 0) {

			object = votingPersistence.fetchByPrimaryKey(objectData.getLong(VotingTerm.VOTING_ID));

			object.setModifiedDate(new Date());

		} else {

			long id = CounterLocalServiceUtil.increment(Voting.class.getName());

			object = votingPersistence.create(id);

			object.setGroupId(objectData.getLong(Field.GROUP_ID));
			object.setCompanyId(objectData.getLong(VotingTerm.COMPANY_ID));
			object.setCreateDate(new Date());

		}

		object.setUserId(objectData.getLong(VotingTerm.USER_ID));

		object.setClassName(objectData.getString(VotingTerm.CLASS_NAME));
		object.setClassPK(objectData.getString(VotingTerm.CLASS_PK));
		object.setSubject(objectData.getString(VotingTerm.SUBJECT));
		object.setChoices(objectData.getString(VotingTerm.CHOICES));
		object.setTemplateNo(objectData.getString(VotingTerm.TEMPLATE_NO));
		object.setCommentable(objectData.getBoolean(VotingTerm.COMMENTABLE));

		votingPersistence.update(object);

		return object;
	}

}