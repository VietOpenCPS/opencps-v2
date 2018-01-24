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
import java.util.LinkedHashMap;

import com.fds.vr.business.model.VRInspectionStandard;
import com.fds.vr.business.service.base.VRInspectionStandardLocalServiceBaseImpl;

import aQute.bnd.annotation.ProviderType;

/**
 * The implementation of the vr inspection standard local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link com.fds.vr.business.service.VRInspectionStandardLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author khoavd
 * @see VRInspectionStandardLocalServiceBaseImpl
 * @see com.fds.vr.business.service.VRInspectionStandardLocalServiceUtil
 */
@ProviderType
public class VRInspectionStandardLocalServiceImpl
	extends VRInspectionStandardLocalServiceBaseImpl {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this class directly. Always use {@link com.fds.vr.business.service.VRInspectionStandardLocalServiceUtil} to access the vr inspection standard local service.
	 */

	public VRInspectionStandard updateInspectionStandard(LinkedHashMap<String, String> mapValues, long vrVehicleCertificateId) {
		
		Date now = new Date();

		long vrInspectionStandardId = counterLocalService.increment(VRInspectionStandard.class.getName());

		VRInspectionStandard object = vrInspectionStandardPersistence.create(vrInspectionStandardId);

		/// Add audit fields
		object.setSyncDate(now);

		// Add other fields
		object.setVehicleCertificateId(vrVehicleCertificateId);
		// TODO
		object.setCertificateRecordId(2222222);
		object.setInspectionRecordId(333333);
		//
		object.setModule(mapValues.get(""));
//		object.setModifyDate(DATEEEEEEE);

		return vrInspectionStandardPersistence.update(object);
	}
}