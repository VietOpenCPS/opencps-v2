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

import org.opencps.deliverable.model.OpenCPSDeliverableLog;
import org.opencps.deliverable.service.base.OpenCPSDeliverableLogLocalServiceBaseImpl;

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

		if (objectData.getLong(ModelKeys.DELIVERABLELOGID) > 0) {

			object = openCPSDeliverableLogPersistence.fetchByPrimaryKey(objectData.getLong(ModelKeys.DELIVERABLELOGID));

			object.setModifiedDate(new Date());

		} else {

			long id = CounterLocalServiceUtil.increment(OpenCPSDeliverableLog.class.getName());

			object = openCPSDeliverableLogPersistence.create(id);

			object.setGroupId(objectData.getLong(ModelKeys.GROUPID));
			object.setCompanyId(objectData.getLong(ModelKeys.COMPANYID));
			object.setCreateDate(new Date());

		}

		object.setUserId(objectData.getLong(ModelKeys.USERID));

		object.setDeliverableId(objectData.getLong(ModelKeys.DELIVERABLEID));
		object.setDossierUid(objectData.getString(ModelKeys.DOSSIERUID));
		object.setAuthor(objectData.getString(ModelKeys.AUTHOR));
		object.setContent(objectData.getString(ModelKeys.CONTENT));
		object.setDeliverableAction(objectData.getInt(ModelKeys.DELIVERABLEACTION));
		if (Validator.isNotNull(objectData.getString(ModelKeys.ACTIONDATE))) {
			object.setActionDate(new Date(objectData.getLong(ModelKeys.ACTIONDATE)));
		}
		object.setPayload(objectData.getString(ModelKeys.PAYLOAD));

		openCPSDeliverableLogPersistence.update(object);

		return object;
	}

	class ModelKeys {
		public static final String DELIVERABLELOGID = "deliverableLogId";
		public static final String GROUPID = "groupId";
		public static final String COMPANYID = "companyId";
		public static final String USERID = "userId";
		public static final String USERNAME = "userName";
		public static final String CREATEDATE = "createDate";
		public static final String MODIFIEDDATE = "modifiedDate";
		public static final String DELIVERABLEID = "deliverableId";
		public static final String DOSSIERUID = "dossierUid";
		public static final String AUTHOR = "author";
		public static final String CONTENT = "content";
		public static final String DELIVERABLEACTION = "deliverableAction";
		public static final String ACTIONDATE = "actionDate";
		public static final String PAYLOAD = "payload";
	}
}