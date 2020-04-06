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

import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;

import java.util.Date;
import java.util.List;

import org.opencps.datamgt.model.DictItemMapping;
import org.opencps.datamgt.service.base.DictItemMappingLocalServiceBaseImpl;

/**
 * The implementation of the dict item mapping local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link org.opencps.datamgt.service.DictItemMappingLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author khoavu
 * @see DictItemMappingLocalServiceBaseImpl
 * @see org.opencps.datamgt.service.DictItemMappingLocalServiceUtil
 */
public class DictItemMappingLocalServiceImpl
	extends DictItemMappingLocalServiceBaseImpl {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this class directly. Always use {@link org.opencps.datamgt.service.DictItemMappingLocalServiceUtil} to access the dict item mapping local service.
	 */
	public DictItemMapping createDictItemMapping(long companyId, long groupId, long userId, String itemCode, String itemCodeDVCQG, long collectionId) {
		long mappingId = counterLocalService.increment(DictItemMapping.class.getCanonicalName());
		DictItemMapping dictItemMapping = dictItemMappingPersistence.create(mappingId);
		Date date = new Date();
		User user = UserLocalServiceUtil.fetchUser(userId);
		dictItemMapping.setCompanyId(companyId);
		dictItemMapping.setCreateDate(date);
		dictItemMapping.setGroupId(groupId);
		dictItemMapping.setItemCode(itemCode);
		dictItemMapping.setItemCodeDVCQG(itemCodeDVCQG);
		dictItemMapping.setCollectionId(collectionId);
		dictItemMapping.setModifiedDate(date);
		dictItemMapping.setUserId(userId);
		dictItemMapping.setUserName(user != null ? user.getFullName() : StringPool.BLANK);
		
		return dictItemMappingPersistence.update(dictItemMapping);
	}
	
	public DictItemMapping fetchByF_GID_IC_CID(long groupId, String itemCode, long collectionId) {
		return dictItemMappingPersistence.fetchByF_GID_IC_CID(groupId, itemCode, collectionId);
	}
	
	public DictItemMapping fetchByF_GID_ICDVCQG_CID(long groupId, String itemCodeDVCQG, long collectionId) {
		return dictItemMappingPersistence.fetchByF_GID_ICDVCQG_CID(groupId, itemCodeDVCQG, collectionId);
	}
	
	public List<DictItemMapping> findByF_GID_CID(long groupId, long collectionId){
		return dictItemMappingPersistence.findByF_GID_CID(groupId, collectionId);
	}
	
}