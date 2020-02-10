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
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.util.Validator;

import java.util.Date;
import java.util.List;

import org.opencps.dossiermgt.constants.DossierStatusMappingTerm;
import org.opencps.dossiermgt.model.DossierStatusMapping;
import org.opencps.dossiermgt.service.base.DossierStatusMappingLocalServiceBaseImpl;

/**
 * The implementation of the dossier status mapping local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link org.opencps.dossiermgt.service.DossierStatusMappingLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author huymq
 * @see DossierStatusMappingLocalServiceBaseImpl
 * @see org.opencps.dossiermgt.service.DossierStatusMappingLocalServiceUtil
 */
public class DossierStatusMappingLocalServiceImpl
	extends DossierStatusMappingLocalServiceBaseImpl {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this class directly. Always use {@link org.opencps.dossiermgt.service.DossierStatusMappingLocalServiceUtil} to access the dossier status mapping local service.
	 */
	
	public DossierStatusMapping fetchByF_GID_SUBSC(long groupId, String subStatusCode) {
		return dossierStatusMappingPersistence.fetchByF_GID_SUBSC(groupId, subStatusCode);
	}
	public List<DossierStatusMapping> findByF_GID_SC(long groupId, String statusCode) {
		return dossierStatusMappingPersistence.findByF_GID_SC(groupId, statusCode);
	}

	// super_admin Generators
	@Override
	public DossierStatusMapping adminProcessDelete(Long id) {

		DossierStatusMapping object = dossierStatusMappingPersistence.fetchByPrimaryKey(id);

		if (Validator.isNull(object)) {
			return null;
		} else {
			dossierStatusMappingPersistence.remove(object);
		}

		return object;
	}

	@Override
	public DossierStatusMapping adminProcessData(JSONObject objectData) {

		DossierStatusMapping object = null;

		long dossierStatusMappingId = objectData.getLong(DossierStatusMappingTerm.DOSSIER_STATUS_MAPPING_ID);
		long groupId = objectData.getLong(Field.GROUP_ID);

		if (dossierStatusMappingId > 0) {

			object = dossierStatusMappingPersistence.fetchByPrimaryKey(dossierStatusMappingId);
		} else {

			dossierStatusMappingId = CounterLocalServiceUtil.increment(DossierStatusMapping.class.getName());
			object = dossierStatusMappingPersistence.create(dossierStatusMappingId);

			object.setGroupId(groupId);
			object.setCompanyId(objectData.getLong(Field.COMPANY_ID));
			object.setCreateDate(new Date());
		}

		object.setModifiedDate(new Date());
		object.setUserId(objectData.getLong(Field.USER_ID));
		object.setUserName(objectData.getString(Field.USER_NAME));

		object.setStatusCode(objectData.getString(DossierStatusMappingTerm.STATUS_CODE));
		object.setStatusCodeDVCQG(objectData.getString(DossierStatusMappingTerm.STATUS_CODE_DVCQG));
		object.setSubStatusCode(objectData.getString(DossierStatusMappingTerm.SUB_STATUS_CODE));

		object = dossierStatusMappingPersistence.update(object);

		return object;
	}
}