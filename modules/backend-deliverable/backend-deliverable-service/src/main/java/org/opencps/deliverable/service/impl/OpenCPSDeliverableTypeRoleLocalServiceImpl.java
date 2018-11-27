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

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.opencps.deliverable.model.OpenCPSDeliverableTypeRole;
import org.opencps.deliverable.service.base.OpenCPSDeliverableTypeRoleLocalServiceBaseImpl;

/**
 * The implementation of the open cps deliverable type role local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are
 * added, rerun ServiceBuilder to copy their definitions into the
 * {@link org.opencps.deliverable.service.OpenCPSDeliverableTypeRoleRoleLocalService}
 * interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security
 * checks based on the propagated JAAS credentials because this service can only
 * be accessed from within the same VM.
 * </p>
 *
 * @author binhth
 * @see OpenCPSDeliverableTypeRoleRoleLocalServiceBaseImpl
 * @see org.opencps.deliverable.service.OpenCPSDeliverableTypeRoleRoleLocalServiceUtil
 */
public class OpenCPSDeliverableTypeRoleLocalServiceImpl extends OpenCPSDeliverableTypeRoleLocalServiceBaseImpl {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this class directly. Always use {@link
	 * org.opencps.deliverable.service.
	 * OpenCPSDeliverableTypeRoleRoleLocalServiceUtil} to access the open cps
	 * deliverable type role local service.
	 */

	public List<Long> getRoleIdByTypes(long deliverableTypeId) {
		List<Long> result = new ArrayList<>();
		List<OpenCPSDeliverableTypeRole> results = openCPSDeliverableTypeRolePersistence.findByF_deliverableTypeId(deliverableTypeId);
		
		for (OpenCPSDeliverableTypeRole openCPSDeliverableTypeRole : results) {
			result.add(openCPSDeliverableTypeRole.getRoleId());
		}
		
		return result;
	}
	
	// super_admin Generators
	@Indexable(type = IndexableType.DELETE)
	public OpenCPSDeliverableTypeRole adminProcessDelete(Long id) {

		OpenCPSDeliverableTypeRole object = openCPSDeliverableTypeRolePersistence.fetchByPrimaryKey(id);

		if (Validator.isNull(object)) {
			return null;
		} else {
			openCPSDeliverableTypeRolePersistence.remove(object);
		}

		return object;
	}

	@Indexable(type = IndexableType.REINDEX)
	public OpenCPSDeliverableTypeRole adminProcessData(JSONObject objectData) {

		OpenCPSDeliverableTypeRole object = null;

		if (objectData.getLong(ModelKeys.DELIVERABLETYPEROLEID) > 0) {

			object = openCPSDeliverableTypeRolePersistence
					.fetchByPrimaryKey(objectData.getLong(ModelKeys.DELIVERABLETYPEROLEID));

			object.setModifiedDate(new Date());

		} else {

			long id = CounterLocalServiceUtil.increment(OpenCPSDeliverableTypeRole.class.getName());

			object = openCPSDeliverableTypeRolePersistence.create(id);

			object.setGroupId(objectData.getLong(ModelKeys.GROUPID));
			object.setCompanyId(objectData.getLong(ModelKeys.COMPANYID));
			object.setCreateDate(new Date());

		}

		object.setUserId(objectData.getLong(ModelKeys.USERID));

		object.setDeliverableTypeId(objectData.getLong(ModelKeys.DELIVERABLETYPEID));
		object.setRoleId(objectData.getLong(ModelKeys.ROLEID));
		object.setModerator(objectData.getBoolean(ModelKeys.MODERATOR));

		openCPSDeliverableTypeRolePersistence.update(object);

		return object;
	}

	class ModelKeys {
		public static final String DELIVERABLETYPEROLEID = "deliverableTypeRoleId";
		public static final String GROUPID = "groupId";
		public static final String COMPANYID = "companyId";
		public static final String USERID = "userId";
		public static final String USERNAME = "userName";
		public static final String CREATEDATE = "createDate";
		public static final String MODIFIEDDATE = "modifiedDate";
		public static final String DELIVERABLETYPEID = "deliverableTypeId";
		public static final String ROLEID = "roleId";
		public static final String MODERATOR = "moderator";
	}
}