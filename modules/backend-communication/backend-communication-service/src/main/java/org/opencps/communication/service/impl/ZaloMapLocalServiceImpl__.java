///**
// * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
// *
// * This library is free software; you can redistribute it and/or modify it under
// * the terms of the GNU Lesser General Public License as published by the Free
// * Software Foundation; either version 2.1 of the License, or (at your option)
// * any later version.
// *
// * This library is distributed in the hope that it will be useful, but WITHOUT
// * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
// * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
// * details.
// */
//
//package org.opencps.communication.service.impl;
//
//import java.util.Date;
//import java.util.List;
//
//import org.opencps.communication.model.ZaloMap;
//import org.opencps.communication.service.base.ZaloMapLocalServiceBaseImpl;
//
//import com.liferay.portal.kernel.exception.PortalException;
//import com.liferay.portal.kernel.log.Log;
//import com.liferay.portal.kernel.log.LogFactoryUtil;
//import com.liferay.portal.kernel.service.ServiceContext;
//import com.liferay.portal.kernel.util.Validator;
//
///**
// * The implementation of the zalo map local service. <p> All custom service
// * methods should be put in this class. Whenever methods are added, rerun
// * ServiceBuilder to copy their definitions into the
// * {@link org.opencps.communication.service.ZaloMapLocalService} interface. <p>
// * This is a local service. Methods of this service will not have security
// * checks based on the propagated JAAS credentials because this service can only
// * be accessed from within the same VM. </p>
// *
// * @author khoavd
// * @see ZaloMapLocalServiceBaseImpl
// * @see org.opencps.communication.service.ZaloMapLocalServiceUtil
// */
//public class ZaloMapLocalServiceImpl__ extends ZaloMapLocalServiceBaseImpl {
//	/*
//	 * NOTE FOR DEVELOPERS: Never reference this class directly. Always use
//	 * {@link org.opencps.communication.service.ZaloMapLocalServiceUtil} to
//	 * access the zalo map local service.
//	 */
//
//	private static Log _log =
//		LogFactoryUtil.getLog(ZaloMapLocalServiceImpl__.class);
//
//	public ZaloMap updateZaloMap(
//		long zaloMapId, long groupId, String uId, String telNo, String oAId,
//		int isFollow, String payload)
//		throws PortalException {
//
//		ZaloMap zaloMap = null;
//		Date now = new Date();
//
//		if (Validator.isNull(zaloMapId) || zaloMapId == 0) {
//
//			long zaloMapIdInc =
//				counterLocalService.increment(ZaloMap.class.getName());
//
//			zaloMap = zaloMapPersistence.create(zaloMapIdInc);
//
//			zaloMap.setCreateDate(now);
//			zaloMap.setModifiedDate(now);
//			zaloMap.setGroupId(groupId);
//
//			zaloMap.setUId(uId);
//
//			zaloMap.setTelNo(telNo);
//
//			zaloMap.setTelNo(telNo);
//
//			zaloMap.setOAId(oAId);
//
//			zaloMap.setIsFollowed(isFollow);
//
//			zaloMap.setPayload(payload);
//
//		}
//		else {
//			zaloMap = zaloMapPersistence.fetchByPrimaryKey(zaloMapId);
//
//			zaloMap.setModifiedDate(now);
//
//			zaloMap.setGroupId(groupId);
//
//			zaloMap.setUId(uId);
//
//			zaloMap.setTelNo(telNo);
//
//			zaloMap.setTelNo(telNo);
//
//			zaloMap.setOAId(oAId);
//
//			zaloMap.setIsFollowed(isFollow);
//
//			zaloMap.setPayload(payload);
//		}
//
//		zaloMapPersistence.update(zaloMap);
//
//		return zaloMap;
//	}
//
//	public ZaloMap removeByPrimaryKey(long zaloMapId) {
//
//		ZaloMap zaloMap = zaloMapPersistence.fetchByPrimaryKey(zaloMapId);
//
//		if (Validator.isNotNull(zaloMap)) {
//			zaloMapPersistence.remove(zaloMap);
//		}
//
//		return zaloMap;
//	}
//
//	public ZaloMap getByUId(String uId) {
//
//		return zaloMapPersistence.fetchByF_UId(uId);
//	}
//
//	public ZaloMap getByTelNo(String telNo) {
//
//		return zaloMapPersistence.fetchByF_TelNo(telNo);
//	}
//
//	public List<ZaloMap> getByGroupId(long groupId) {
//
//		return zaloMapPersistence.findByF_GID(groupId);
//	}
//
//	public List<ZaloMap> getByOAId(String oAId) {
//
//		return zaloMapPersistence.findByF_OAId(oAId);
//	}
//
//}
