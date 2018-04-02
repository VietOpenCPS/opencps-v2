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

import aQute.bnd.annotation.ProviderType;

import java.util.Date;
import java.util.List;

import org.opencps.dossiermgt.model.DossierMark;
import org.opencps.dossiermgt.service.base.DossierMarkLocalServiceBaseImpl;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.ServiceContext;

/**
 * The implementation of the dossier mark local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link org.opencps.dossiermgt.service.DossierMarkLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author huymq
 * @see DossierMarkLocalServiceBaseImpl
 * @see org.opencps.dossiermgt.service.DossierMarkLocalServiceUtil
 */
@ProviderType
public class DossierMarkLocalServiceImpl extends DossierMarkLocalServiceBaseImpl {
	public DossierMark addDossierMark(long groupId, long dossierId, String dossierPartNo, Boolean fileCheck,
			int fileType, ServiceContext serviceContext) throws PortalException, SystemException {

		long userId = serviceContext.getUserId();

		Date now = new Date();

		User userAction = userLocalService.getUser(userId);

		long dossierMarkId = counterLocalService.increment(DossierMark.class.getName());

		DossierMark object = dossierMarkPersistence.create(dossierMarkId);

		/// Add audit fields
		object.setCompanyId(serviceContext.getCompanyId());
		object.setGroupId(groupId);
		object.setCreateDate(now);
		object.setModifiedDate(now);
		object.setUserId(userAction.getUserId());

		// Add other fields
		object.setDossierId(dossierId);
		object.setDossierPartNo(dossierPartNo);
		object.setFileCheck(fileCheck);
		object.setFileType(fileType);

		return dossierMarkPersistence.update(object);
	}

	public DossierMark getDossierMarkbyDossierId(long groupId, long dossierId, String dossierPartNo) {

		return dossierMarkPersistence.fetchByG_DID_PN(groupId, dossierId, dossierPartNo);
	}

	public List<DossierMark> getDossierMarks(long groupId, long dossierId) {

		return dossierMarkPersistence.findByG_DID(groupId, dossierId);
	}
}