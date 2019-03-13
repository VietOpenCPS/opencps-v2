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

import com.liferay.counter.kernel.service.CounterLocalServiceUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.search.Indexable;
import com.liferay.portal.kernel.search.IndexableType;
import com.liferay.portal.kernel.util.Validator;

import java.util.Date;
import java.util.List;

import org.opencps.dossiermgt.model.DeliverableLog;
import org.opencps.dossiermgt.service.base.DeliverableLogLocalServiceBaseImpl;

import aQute.bnd.annotation.ProviderType;

/**
 * The implementation of the deliverable log local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are
 * added, rerun ServiceBuilder to copy their definitions into the
 * {@link org.opencps.dossiermgt.service.DeliverableLogLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security
 * checks based on the propagated JAAS credentials because this service can only
 * be accessed from within the same VM.
 * </p>
 *
 * @author huymq
 * @see DeliverableLogLocalServiceBaseImpl
 * @see org.opencps.dossiermgt.service.DeliverableLogLocalServiceUtil
 */
@ProviderType
public class DeliverableLogLocalServiceImpl extends DeliverableLogLocalServiceBaseImpl {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this class directly. Always use {@link
	 * org.opencps.dossiermgt.service.DeliverableLogLocalServiceUtil} to access the
	 * deliverable log local service.
	 */
	// super_admin Generators
	@Indexable(type = IndexableType.DELETE)
	public DeliverableLog adminProcessDelete(Long id) {

		DeliverableLog object = deliverableLogPersistence.fetchByPrimaryKey(id);

		if (Validator.isNull(object)) {
			return null;
		} else {
			deliverableLogPersistence.remove(object);
		}

		return object;
	}

	@Indexable(type = IndexableType.REINDEX)
	public DeliverableLog adminProcessData(JSONObject objectData) {

		DeliverableLog object = null;

		if (objectData.getLong("deliverableLogId") > 0) {

			object = deliverableLogPersistence.fetchByPrimaryKey(objectData.getLong("deliverableLogId"));

			object.setModifiedDate(new Date());

		} else {

			long id = CounterLocalServiceUtil.increment(DeliverableLog.class.getName());

			object = deliverableLogPersistence.create(id);

			object.setGroupId(objectData.getLong("groupId"));
			object.setCompanyId(objectData.getLong("companyId"));
			object.setCreateDate(new Date());

		}

		object.setUserId(objectData.getLong("userId"));
		object.setUserName(objectData.getString("userName"));

		object.setDeliverableId(objectData.getInt("deliverableId"));
		object.setDossierUid(objectData.getString("dossierUid"));
		object.setAuthor(objectData.getString("author"));
		object.setContent(objectData.getString("content"));
		object.setDeliverableAction(objectData.getInt("deliverableAction"));
		object.setActionDate(new Date(objectData.getLong("actionDate")));
		object.setPayload(objectData.getString("payload"));

		deliverableLogPersistence.update(object);

		return object;
	}
	
	public List<DeliverableLog> findByF_deliverableId(long deliverableId, int start, int end) {
		return deliverableLogPersistence.findByF_deliverableId(deliverableId, start, end);
	}
	
}