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

import java.util.List;

import org.opencps.dossiermgt.model.DeliverableType;
import org.opencps.dossiermgt.model.DossierVnpost;
import org.opencps.dossiermgt.service.base.DossierVnpostLocalServiceBaseImpl;

import com.liferay.portal.kernel.exception.PortalException;

import aQute.bnd.annotation.ProviderType;

/**
 * The implementation of the dossier vnpost local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are
 * added, rerun ServiceBuilder to copy their definitions into the
 * {@link org.opencps.dossiermgt.service.DossierVnpostLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security
 * checks based on the propagated JAAS credentials because this service can only
 * be accessed from within the same VM.
 * </p>
 *
 * @author huymq
 * @see DossierVnpostLocalServiceBaseImpl
 * @see org.opencps.dossiermgt.service.DossierVnpostLocalServiceUtil
 */
@ProviderType
public class DossierVnpostLocalServiceImpl extends DossierVnpostLocalServiceBaseImpl {

	public DossierVnpost getByDossierVnpostId(long dossierVnpostId) throws PortalException {
		return dossierVnpostPersistence.fetchByDVID(dossierVnpostId);
	}

	public List<DossierVnpost> getByDossierId(long dossierId) throws PortalException {
		return dossierVnpostPersistence.findByDID(dossierId);
	}

	public DossierVnpost addDossierVnpost(String fullName, String phoneNumber,
			String deliverAddress, String cityCode, String cityName, String districtCode, String districtName,
			String wardCode, String wardName, long dossierId, long groupId) {
		
		long dossierVnpostId = counterLocalService.increment(DossierVnpost.class.getName());
		DossierVnpost vnpost = dossierVnpostPersistence.create(dossierVnpostId);
		
		vnpost.setFullName(fullName);
		vnpost.setPhoneNumber(phoneNumber);
		vnpost.setDeliverAddress(deliverAddress);
		vnpost.setCityCode(cityCode);
		vnpost.setCityName(cityName);
		vnpost.setDistrictCode(districtCode);
		vnpost.setDistrictName(districtName);
		vnpost.setWardCode(wardCode);
		vnpost.setWardName(wardName);
		vnpost.setDossierId(dossierId);
		vnpost.setGroupId(groupId);
		
		return dossierVnpostPersistence.update(vnpost);
	}

}