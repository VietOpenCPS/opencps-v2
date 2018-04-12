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

package org.opencps.thirdparty.system.service.impl;

import aQute.bnd.annotation.ProviderType;

import java.util.Date;
import java.util.List;

import org.opencps.thirdparty.system.model.ThirdPartyDossierSync;
import org.opencps.thirdparty.system.service.base.ThirdPartyDossierSyncLocalServiceBaseImpl;

import com.liferay.portal.kernel.util.Validator;

/**
 * The implementation of the third party dossier sync local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link org.opencps.thirdparty.system.service.ThirdPartyDossierSyncLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author trungdk
 * @see ThirdPartyDossierSyncLocalServiceBaseImpl
 * @see org.opencps.thirdparty.system.service.ThirdPartyDossierSyncLocalServiceUtil
 */
@ProviderType
public class ThirdPartyDossierSyncLocalServiceImpl
	extends ThirdPartyDossierSyncLocalServiceBaseImpl {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this class directly. Always use {@link org.opencps.thirdparty.system.service.ThirdPartyDossierSyncLocalServiceUtil} to access the third party dossier sync local service.
	 */
	
	public ThirdPartyDossierSync updateThirdPartyDossierSync(long groupId, long userId, long dossierId, String dossierReferenceUid,
			boolean createDossier, int method, long classPK, String fileReferenceUid, String serverNo) {

		ThirdPartyDossierSync thirdPartyDossierSync = null;

		thirdPartyDossierSync = thirdPartyDossierSyncPersistence.fetchByF_D_M_CPK_FR(dossierId, method, classPK, fileReferenceUid);

		if (Validator.isNull(thirdPartyDossierSync)) {
			long thirdPartyDossierSyncId = counterLocalService.increment(ThirdPartyDossierSync.class.getName());

			Date now = new Date();

			thirdPartyDossierSync = thirdPartyDossierSyncPersistence.create(thirdPartyDossierSyncId);

			thirdPartyDossierSync.setCreateDate(now);
			thirdPartyDossierSync.setModifiedDate(now);

			thirdPartyDossierSync.setGroupId(groupId);
			thirdPartyDossierSync.setUserId(userId);

			thirdPartyDossierSync.setDossierId(dossierId);
			thirdPartyDossierSync.setDossierReferenceUid(dossierReferenceUid);
			thirdPartyDossierSync.setCreateDossier(createDossier);
			thirdPartyDossierSync.setMethod(method);
			thirdPartyDossierSync.setClassPK(classPK);
			thirdPartyDossierSync.setFileReferenceUid(fileReferenceUid);
			thirdPartyDossierSync.setServerNo(serverNo);

			thirdPartyDossierSyncPersistence.update(thirdPartyDossierSync);

		}

		return thirdPartyDossierSync;
	}

	public ThirdPartyDossierSync updateCreateDossierStatus(long thirdPartyDossierSyncId, boolean status) {

		ThirdPartyDossierSync dossierSyn = thirdPartyDossierSyncPersistence.fetchByPrimaryKey(thirdPartyDossierSyncId);
		dossierSyn.setCreateDossier(status);

		return thirdPartyDossierSyncPersistence.update(dossierSyn);
	}

	public ThirdPartyDossierSync shiftCreateDossierStatus(long thirdPartyDossierSyncId) {
		ThirdPartyDossierSync dossierSyn = thirdPartyDossierSyncPersistence.fetchByPrimaryKey(thirdPartyDossierSyncId);

		boolean isCreate = dossierSyn.getCreateDossier();

		if (isCreate) {
			dossierSyn.setCreateDossier(false);
		} else {
			dossierSyn.setCreateDossier(true);
		}

		return thirdPartyDossierSyncPersistence.update(dossierSyn);
	}

	public List<ThirdPartyDossierSync> fetchByServerNo(String serverNo, int start, int end) {
		return thirdPartyDossierSyncPersistence.findBySRV_NO(serverNo, start, end);
	}

	public List<ThirdPartyDossierSync> fetchByGroupId(long groupId, int start, int end) {
		return thirdPartyDossierSyncPersistence.findByG_ID(groupId, start, end);
	}

	public List<ThirdPartyDossierSync> fetchByGroupDossierId(long groupId, long dossierId, int start, int end) {
		return thirdPartyDossierSyncPersistence.findByG_ID_DID(groupId, dossierId, start, end);
	}

	public List<ThirdPartyDossierSync> fetchByGroupDossierRef(long groupId, String dossierRef, int start, int end) {
		return thirdPartyDossierSyncPersistence.findByG_ID_DRF(groupId, dossierRef, start, end);
	}

	public int countByGroupDossierId(long groupId, long dossierId) {
		return thirdPartyDossierSyncPersistence.countByG_ID_DID(groupId, dossierId);
	}

	public int countByGroupDossierRef(long groupId, String dossierRef) {
		return thirdPartyDossierSyncPersistence.countByG_ID_DRF(groupId, dossierRef);
	}

	public int countByDossierId(long dossierId) {
		return thirdPartyDossierSyncPersistence.countByDossierId(dossierId);
	}
	
	public List<ThirdPartyDossierSync> findAll(int start, int end) {
		return thirdPartyDossierSyncPersistence.findAll(start, end);
	}
}