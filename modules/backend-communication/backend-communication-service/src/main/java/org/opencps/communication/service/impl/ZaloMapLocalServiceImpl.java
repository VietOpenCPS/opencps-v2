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

package org.opencps.communication.service.impl;

import org.opencps.communication.service.base.ZaloMapLocalServiceBaseImpl;
import java.util.Date;
import java.util.List;

import org.opencps.communication.model.ZaloMap;
import org.opencps.communication.model.ZaloMap;
import org.opencps.communication.model.ZaloMap;
import org.opencps.communication.service.base.ZaloMapLocalServiceBaseImpl;

import com.liferay.counter.kernel.service.CounterLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.search.Indexable;
import com.liferay.portal.kernel.search.IndexableType;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.Validator;

public class ZaloMapLocalServiceImpl extends ZaloMapLocalServiceBaseImpl {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this class directly. Always use {@link org.opencps.communication.service.ZaloMapLocalServiceUtil} to access the zalo map local service.
	 */

	private static Log _log =
		LogFactoryUtil.getLog(ZaloMapLocalServiceImpl.class);

	@Indexable(type = IndexableType.REINDEX)
	@Override
	public ZaloMap updateZaloMap(
		long zaloMapId, long groupId, String uId, String telNo, String oAId,
		int isFollow, String payload)
		throws PortalException {

		ZaloMap zaloMap = null;
		Date now = new Date();

		if (Validator.isNull(zaloMapId) || zaloMapId == 0) {

			long zaloMapIdInc =
				counterLocalService.increment(ZaloMap.class.getName());

			zaloMap = zaloMapPersistence.create(zaloMapIdInc);

			zaloMap.setCreateDate(now);
			zaloMap.setModifiedDate(now);
			zaloMap.setGroupId(groupId);

			zaloMap.setUId(uId);

			zaloMap.setTelNo(telNo);

			zaloMap.setTelNo(telNo);

			zaloMap.setZaloOAId(oAId);

			zaloMap.setIsFollowed(isFollow);

			zaloMap.setPayload(payload);

		}
		else {
			zaloMap = zaloMapPersistence.fetchByPrimaryKey(zaloMapId);

			zaloMap.setModifiedDate(now);

			zaloMap.setGroupId(groupId);

			zaloMap.setUId(uId);

			zaloMap.setTelNo(telNo);

			zaloMap.setTelNo(telNo);

			zaloMap.setZaloOAId(oAId);

			zaloMap.setIsFollowed(isFollow);

			zaloMap.setPayload(payload);
		}

		zaloMapPersistence.update(zaloMap);

		return zaloMap;
	}

	@Indexable(type = IndexableType.REINDEX)
	@Override
	public ZaloMap removeByPrimaryKey(long zaloMapId) {

		ZaloMap zaloMap = zaloMapPersistence.fetchByPrimaryKey(zaloMapId);

		if (Validator.isNotNull(zaloMap)) {
			zaloMapPersistence.remove(zaloMap);
		}

		return zaloMap;
	}

	@Override
	public ZaloMap getByUId(String uId) {

		return zaloMapPersistence.fetchByF_UId(uId);
	}

	@Override
	public ZaloMap getByTelNo(String telNo) {

		return zaloMapPersistence.fetchByF_TelNo(telNo);
	}

	@Override
	public List<ZaloMap> getByGroupId(long groupId) {

		return zaloMapPersistence.findByF_GID(groupId);
	}

	@Override
	public List<ZaloMap> getByOAId(String oAId) {

		return zaloMapPersistence.findByF_ZALO_OAID(oAId);
	}
	
	@Indexable(type = IndexableType.DELETE)
	@Override
	public ZaloMap adminProcessDelete(Long id) {

		ZaloMap object = zaloMapPersistence.fetchByPrimaryKey(id);

		if (Validator.isNull(object)) {
			return null;
		} else {
			zaloMapPersistence.remove(object);
		}

		return object;
	}
	
	@Indexable(type = IndexableType.REINDEX)
	@Override
	public ZaloMap adminProcessData(JSONObject objectData) {

		ZaloMap object = null;

		if (objectData.getLong("zaloMapId") > 0) {

			object = zaloMapPersistence.fetchByPrimaryKey(objectData.getLong("zaloMapId"));

			object.setModifiedDate(new Date());

		} else {

			long id = CounterLocalServiceUtil.increment(ZaloMap.class.getName());

			object = zaloMapPersistence.create(id);

			object.setGroupId(objectData.getLong("groupId"));
			object.setCompanyId(objectData.getLong("companyId"));
			object.setCreateDate(new Date());

		}

		object.setUserId(objectData.getLong("userId"));

		object.setIsFollowed(objectData.getInt("isFollowed"));
		object.setZaloOAId(objectData.getString("zaloOAId"));
		object.setPayload(objectData.getString("payload"));
		object.setTelNo(objectData.getString("telNo"));
		object.setUId(objectData.getString("uId"));

		zaloMapPersistence.update(object);

		return object;
	}

}
