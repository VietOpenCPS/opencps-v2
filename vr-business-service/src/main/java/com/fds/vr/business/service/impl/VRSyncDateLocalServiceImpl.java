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

package com.fds.vr.business.service.impl;

import java.util.Date;
import java.util.List;

import org.opencps.dossiermgt.model.Deliverable;

import com.fds.vr.business.action.impl.VROutputDBActionsImpl;
import com.fds.vr.business.model.VRSyncDate;
import com.fds.vr.business.service.base.VRSyncDateLocalServiceBaseImpl;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

import aQute.bnd.annotation.ProviderType;

/**
 * The implementation of the vr sync date local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link com.fds.vr.business.service.VRSyncDateLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author LamTV
 * @see VRSyncDateLocalServiceBaseImpl
 * @see com.fds.vr.business.service.VRSyncDateLocalServiceUtil
 */
@ProviderType
public class VRSyncDateLocalServiceImpl extends VRSyncDateLocalServiceBaseImpl {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this class directly. Always use {@link com.fds.vr.business.service.VRSyncDateLocalServiceUtil} to access the vr sync date local service.
	 */

	private Log _log = LogFactoryUtil.getLog(VRSyncDateLocalServiceImpl.class);

	public Date getSyncDate() {
		Date syncDate = null;
		List<VRSyncDate> syncDateList = vrSyncDatePersistence.findAll();
		if (syncDateList != null && syncDateList.size() > 0) {
			syncDate = syncDateList.get(0).getSyncDate();
		}

		return syncDate;
	}

	public VRSyncDate updateSyncDate(Date modifiedDate) {

		VRSyncDate object = null;
		try {
			object = vrSyncDatePersistence.findByPrimaryKey(1);
			
			object.setSyncDate(modifiedDate);
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		return vrSyncDatePersistence.update(object);
	}
}