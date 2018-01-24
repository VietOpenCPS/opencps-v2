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

import aQute.bnd.annotation.ProviderType;

import java.util.Date;
import java.util.LinkedHashMap;

import com.fds.vr.business.model.VRVehicleSpecification;
import com.fds.vr.business.service.base.VRVehicleSpecificationLocalServiceBaseImpl;

/**
 * The implementation of the vr vehicle specification local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link com.fds.vr.business.service.VRVehicleSpecificationLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author khoavd
 * @see VRVehicleSpecificationLocalServiceBaseImpl
 * @see com.fds.vr.business.service.VRVehicleSpecificationLocalServiceUtil
 */
@ProviderType
public class VRVehicleSpecificationLocalServiceImpl
	extends VRVehicleSpecificationLocalServiceBaseImpl {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this class directly. Always use {@link com.fds.vr.business.service.VRVehicleSpecificationLocalServiceUtil} to access the vr vehicle specification local service.
	 */

	public VRVehicleSpecification updateVehicleSpecification(LinkedHashMap<String, String> mapValues, long vrVehicleTypeCertificateId) {
		
		Date now = new Date();

		long vrVehicleSpecificationId = counterLocalService.increment(VRVehicleSpecification.class.getName());

		VRVehicleSpecification object = vrVehicleSpecificationPersistence.create(vrVehicleSpecificationId);

		/// Add audit fields
		object.setSyncDate(now);

		// Add other fields
		//TODO
		object.setVehicleCertificateId(vrVehicleTypeCertificateId);
		object.setSpecificationCode(mapValues.get(""));
		object.setSpecificationName(mapValues.get(""));
		object.setSpecificationValue(mapValues.get(""));
		object.setSpecificationValueDescription(mapValues.get(""));
		//TODO
		object.setSequenceNo(Long.valueOf(mapValues.get("")));
		object.setSpecificationBasicUnit(mapValues.get(""));
		object.setSpecificationStandard(mapValues.get(""));
		object.setSpecificationGroup(mapValues.get(""));
		object.setSpecificationDataCollectionID(mapValues.get(""));
		object.setSpecificationResult(mapValues.get(""));
//		object.setModifyDate(DATEEEEEEE);

		return vrVehicleSpecificationPersistence.update(object);
	}
}