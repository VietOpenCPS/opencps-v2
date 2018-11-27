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

package org.opencps.deliverable.service.impl;

import com.liferay.counter.kernel.service.CounterLocalServiceUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.search.Indexable;
import com.liferay.portal.kernel.search.IndexableType;
import com.liferay.portal.kernel.util.Validator;

import java.util.Date;
import java.util.List;

import org.opencps.deliverable.model.OpenCPSDeliverableLog;
import org.opencps.deliverable.service.base.OpenCPSDeliverableLogLocalServiceBaseImpl;
import org.opencps.deliverable.service.persistence.OpenCPSDeliverableLogPersistence;

import backend.deliverable.service.util.ModelKeysDeliverableLog;

/**
 * The implementation of the open cps deliverable log local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are
 * added, rerun ServiceBuilder to copy their definitions into the
 * {@link org.opencps.deliverable.service.OpenCPSDeliverableLogLocalService}
 * interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security
 * checks based on the propagated JAAS credentials because this service can only
 * be accessed from within the same VM.
 * </p>
 *
 * @author binhth
 * @see OpenCPSDeliverableLogLocalServiceBaseImpl
 * @see org.opencps.deliverable.service.OpenCPSDeliverableLogLocalServiceUtil
 */
public class OpenCPSDeliverableLogLocalServiceImpl extends OpenCPSDeliverableLogLocalServiceBaseImpl {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this class directly. Always use {@link
	 * org.opencps.deliverable.service.OpenCPSDeliverableLogLocalServiceUtil} to
	 * access the open cps deliverable log local service.
	 */

	public List<OpenCPSDeliverableLog> findByF_deliverableId(long deliverableId, int start, int end) {
		return openCPSDeliverableLogPersistence.findByF_deliverableId(deliverableId, start, end);
	}
	
	// super_admin Generators
	@Indexable(type = IndexableType.DELETE)
	public OpenCPSDeliverableLog adminProcessDelete(Long id) {

		OpenCPSDeliverableLog object = openCPSDeliverableLogPersistence.fetchByPrimaryKey(id);

		if (Validator.isNull(object)) {
			return null;
		} else {
			openCPSDeliverableLogPersistence.remove(object);
		}

		return object;
	}

	@Indexable(type = IndexableType.REINDEX)
	public OpenCPSDeliverableLog adminProcessData(JSONObject objectData) {

		OpenCPSDeliverableLog object = null;

		if (objectData.getLong(ModelKeysDeliverableLog.DELIVERABLELOGID) > 0) {

			object = openCPSDeliverableLogPersistence.fetchByPrimaryKey(objectData.getLong(ModelKeysDeliverableLog.DELIVERABLELOGID));

			object.setModifiedDate(new Date());

		} else {

			long id = CounterLocalServiceUtil.increment(OpenCPSDeliverableLog.class.getName());

			object = openCPSDeliverableLogPersistence.create(id);

			object.setGroupId(objectData.getLong(ModelKeysDeliverableLog.GROUPID));
			object.setCompanyId(objectData.getLong(ModelKeysDeliverableLog.COMPANYID));
			object.setCreateDate(new Date());

		}

		object.setUserId(objectData.getLong(ModelKeysDeliverableLog.USERID));

		object.setDeliverableId(objectData.getLong(ModelKeysDeliverableLog.DELIVERABLEID));
		object.setDossierUid(objectData.getString(ModelKeysDeliverableLog.DOSSIERUID));
		object.setAuthor(objectData.getString(ModelKeysDeliverableLog.AUTHOR));
		object.setContent(objectData.getString(ModelKeysDeliverableLog.CONTENT));
		object.setDeliverableAction(objectData.getInt(ModelKeysDeliverableLog.DELIVERABLEACTION));
		if (Validator.isNotNull(objectData.getString(ModelKeysDeliverableLog.ACTIONDATE))) {
			object.setActionDate(new Date(objectData.getLong(ModelKeysDeliverableLog.ACTIONDATE)));
		}
		object.setPayload(objectData.getString(ModelKeysDeliverableLog.PAYLOAD));

		openCPSDeliverableLogPersistence.update(object);

		return object;
	}

}