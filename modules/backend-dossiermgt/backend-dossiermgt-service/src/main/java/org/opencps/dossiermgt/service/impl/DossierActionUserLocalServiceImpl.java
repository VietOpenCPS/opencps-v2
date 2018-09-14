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

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.User;

import java.util.List;

import org.opencps.dossiermgt.model.DossierActionUser;
import org.opencps.dossiermgt.service.base.DossierActionUserLocalServiceBaseImpl;
import org.opencps.dossiermgt.service.persistence.DossierActionUserPK;

import aQute.bnd.annotation.ProviderType;

/**
 * The implementation of the dossier action user local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link org.opencps.dossiermgt.service.DossierActionUserLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author huymq
 * @see DossierActionUserLocalServiceBaseImpl
 * @see org.opencps.dossiermgt.service.DossierActionUserLocalServiceUtil
 */
@ProviderType
public class DossierActionUserLocalServiceImpl
	extends DossierActionUserLocalServiceBaseImpl {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this class directly. Always use {@link org.opencps.dossiermgt.service.DossierActionUserLocalServiceUtil} to access the dossier action user local service.
	 */
	public List<DossierActionUser> getListUser(long dossierActionId) {
		return dossierActionUserPersistence.findByDID(dossierActionId);
	}

	public DossierActionUser getByDossierAndUser(long dossierActionId, long userId) {
		return dossierActionUserPersistence.fetchByDID_UID(dossierActionId, userId);
	}

	public List<DossierActionUser> getListUserByUserId(long userId) {
		return dossierActionUserPersistence.findByUID(userId);
	}

	public List<DossierActionUser> getByDossierAndStepCode(long dossierId, String stepCode) {
		return dossierActionUserPersistence.findByDID_SC(dossierId, stepCode);
	}
	
//	@Indexable(type = IndexableType.REINDEX)
	public void deleteByDossierAndStepCode(long dossierId, String stepCode) {
		dossierActionUserPersistence.removeByDID_SC(dossierId, stepCode);
	}
	
//	@Indexable(type = IndexableType.REINDEX)
	public DossierActionUser addDossierActionUser(long userId, long groupId, 
			long dossierActionId,
			long dossierId, String stepCode, int moderator, 
			int assigned, boolean visited) throws PortalException {
		User user = userLocalService.getUser(userId);
		
		DossierActionUserPK pk = new DossierActionUserPK(dossierActionId, user.getUserId());
		
		DossierActionUser dau = dossierActionUserPersistence.create(pk);
		dau.setAssigned(assigned);
		dau.setStepCode(stepCode);
		dau.setModerator(moderator);
		dau.setVisited(visited);
		dau.setDossierId(dossierId);

		return dossierActionUserPersistence.update(dau);
	}

//	@Indexable(type = IndexableType.REINDEX)
	public DossierActionUser updateDossierActionUser(long userId, long groupId, 
			long dossierActionId,
			long dossierId, String stepCode, int moderator, 
			int assigned, boolean visited) throws PortalException {
		User user = userLocalService.getUser(userId);
		
		DossierActionUserPK pk = new DossierActionUserPK(dossierActionId, user.getUserId());
		
		DossierActionUser dau = dossierActionUserPersistence.findByPrimaryKey(pk);
		
		dau.setAssigned(assigned);
		dau.setStepCode(stepCode);
		dau.setModerator(moderator);
		dau.setVisited(visited);
		dau.setDossierId(dossierId);
				
		return dossierActionUserPersistence.update(dau);
	}

}