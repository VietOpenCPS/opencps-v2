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
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.search.Indexable;
import com.liferay.portal.kernel.search.IndexableType;
import com.liferay.portal.kernel.util.Validator;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.opencps.dossiermgt.model.DeliverableTypeRole;
import org.opencps.dossiermgt.service.base.DeliverableTypeRoleLocalServiceBaseImpl;


/**
 * The implementation of the deliverable type role local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link org.opencps.dossiermgt.service.DeliverableTypeRoleLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author huymq
 * @see DeliverableTypeRoleLocalServiceBaseImpl
 * @see org.opencps.dossiermgt.service.DeliverableTypeRoleLocalServiceUtil
 */
public class DeliverableTypeRoleLocalServiceImpl
	extends DeliverableTypeRoleLocalServiceBaseImpl {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this class directly. Always use {@link org.opencps.dossiermgt.service.DeliverableTypeRoleLocalServiceUtil} to access the deliverable type role local service.
	 */
	public List<Long> getRoleIdByTypes(long deliverableTypeId) {
		List<Long> result = new ArrayList<>();
		List<DeliverableTypeRole> results = deliverableTypeRolePersistence.findByF_deliverableTypeId(deliverableTypeId);
		
		for (DeliverableTypeRole openCPSDeliverableTypeRole : results) {
			result.add(openCPSDeliverableTypeRole.getRoleId());
		}
		
		return result;
	}
	
	// super_admin Generators
	@Indexable(type = IndexableType.DELETE)
	public DeliverableTypeRole adminProcessDelete(Long id) {

		DeliverableTypeRole object = deliverableTypeRolePersistence.fetchByPrimaryKey(id);

		if (Validator.isNull(object)) {
			return null;
		} else {
			deliverableTypeRolePersistence.remove(object);
		}

		return object;
	}

	@Indexable(type = IndexableType.REINDEX)
	public DeliverableTypeRole adminProcessData(JSONObject objectData) {

		DeliverableTypeRole object = null;

		if (objectData.getLong(ModelKeys.DELIVERABLETYPEROLEID) > 0) {

			object = deliverableTypeRolePersistence
					.fetchByPrimaryKey(objectData.getLong(ModelKeys.DELIVERABLETYPEROLEID));

			object.setModifiedDate(new Date());

		} else {

			long id = CounterLocalServiceUtil.increment(DeliverableTypeRole.class.getName());

			object = deliverableTypeRolePersistence.create(id);

			object.setGroupId(objectData.getLong(ModelKeys.GROUPID));
			object.setCompanyId(objectData.getLong(ModelKeys.COMPANYID));
			object.setCreateDate(new Date());

		}

		object.setUserId(objectData.getLong(ModelKeys.USERID));

		object.setDeliverableTypeId(objectData.getLong(ModelKeys.DELIVERABLETYPEID));
		object.setRoleId(objectData.getLong(ModelKeys.ROLEID));
		object.setModerator(objectData.getBoolean(ModelKeys.MODERATOR));

		deliverableTypeRolePersistence.update(object);

		return object;
	}

	public DeliverableTypeRole updateDeliverableTypeRoleDB(long userId, long groupId, long deliverableTypeId, long mappingRoleId,
			boolean moderator) {

		DeliverableTypeRole object = deliverableTypeRolePersistence.fetchByF_GID_DID_RID(groupId, deliverableTypeId,
				mappingRoleId);

		if (object == null) {
			long typeRoleId = CounterLocalServiceUtil.increment(DeliverableTypeRole.class.getName());
			object = deliverableTypeRolePersistence.create(typeRoleId);
			object.setCreateDate(new Date());
		}
		//
		User user = userPersistence.fetchByPrimaryKey(userId);
		if (user != null) {
			object.setCompanyId(user.getCompanyId());
			object.setUserName(user.getFullName());
		}

		object.setCompanyId(20099l);
		object.setUserId(userId);
		object.setGroupId(groupId);
		object.setModifiedDate(new Date());

		object.setDeliverableTypeId(deliverableTypeId);
		object.setRoleId(mappingRoleId);
		object.setModerator(moderator);

		return deliverableTypeRolePersistence.update(object);
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