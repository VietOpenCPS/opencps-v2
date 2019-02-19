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

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

import java.util.List;

import org.opencps.dossiermgt.exception.NoSuchDossierUserException;
import org.opencps.dossiermgt.model.DossierUser;
import org.opencps.dossiermgt.service.base.DossierUserLocalServiceBaseImpl;

/**
 * The implementation of the dossier user local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link org.opencps.dossiermgt.service.DossierUserLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author huymq
 * @see DossierUserLocalServiceBaseImpl
 * @see org.opencps.dossiermgt.service.DossierUserLocalServiceUtil
 */
public class DossierUserLocalServiceImpl extends DossierUserLocalServiceBaseImpl {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this class directly. Always use {@link org.opencps.dossiermgt.service.DossierUserLocalServiceUtil} to access the dossier user local service.
	 */
	private static Log _log = LogFactoryUtil.getLog(DossierUserLocalServiceImpl.class);
//	@Indexable(type = IndexableType.REINDEX)
	public DossierUser addDossierUser(long groupId, long dossierId, long userId, int moderator, boolean visited) {
//		DossierUserPK pk = new DossierUserPK();
//		pk.setUserId(userId);
//		pk.setDossierId(dossierId);
//		DossierUser oldObject = null;
//		try {
//			oldObject = dossierUserPersistence.findByPrimaryKey(pk);
//		} catch (NoSuchDossierUserException e) {
//			_log.debug(e);
//			//_log.error(e);
//		}
//		DossierUser object = null;
//		
//		if (oldObject == null) {
//			object = dossierUserPersistence.create(pk);
//		}
//		else {
//			object = oldObject;
//		}
//			
//		object.setModerator(moderator);
//		object.setVisited(visited);
//		
//		return dossierUserPersistence.update(object);
		
		DossierUser dossierUser = dossierUserPersistence.fetchByDID_UID(dossierId, userId);
		if (dossierUser == null) {
			long dossierUserId = counterLocalService.increment(DossierUser.class.getName());
			dossierUser = dossierUserPersistence.create(dossierUserId);
			//
			dossierUser.setDossierId(dossierId);
			dossierUser.setUserId(userId);
		}

		dossierUser.setModerator(moderator);
		dossierUser.setVisited(visited);

		return dossierUserPersistence.update(dossierUser);
	}
	
//	@Indexable(type = IndexableType.REINDEX)
	public DossierUser updateDossierUser(long dossierId, long userId, int moderator, boolean visited) throws NoSuchDossierUserException {
//		DossierUserPK pk = new DossierUserPK();
//		pk.setUserId(userId);
//		pk.setDossierId(dossierId);
//		DossierUser object = dossierUserPersistence.findByPrimaryKey(pk);
//		
//		object.setModerator(moderator);
//		object.setVisited(visited);
		
		DossierUser dossierUser = dossierUserPersistence.fetchByDID_UID(dossierId, userId);
		if (dossierUser != null) {
			dossierUser.setModerator(moderator);
			dossierUser.setVisited(visited);
			//
			return dossierUserPersistence.update(dossierUser);
		} else {
			return null;
		}
	}
	
//	@Indexable(type = IndexableType.REINDEX)
	public DossierUser deleteDossierUser(long dossierId, long userId) throws NoSuchDossierUserException {
		//DossierUserPK pk = new DossierUserPK();
		//pk.setUserId(userId);
		//pk.setDossierId(dossierId);
		//return dossierUserPersistence.remove(pk);
		DossierUser dossierUser = dossierUserPersistence.fetchByDID_UID(dossierId, userId);
		if (dossierUser != null) {
			return dossierUserPersistence.remove(dossierUser);
		} else {
			return null;
		}
	}
	
	public DossierUser getByDossierUser(long dossierId, long userId) {
		return dossierUserPersistence.fetchByDID_UID(dossierId, userId);		
	}
	
	public List<DossierUser> findByDID(long dossierId) {
		return dossierUserPersistence.findByDID(dossierId);
	}
	
	public DossierUser findByDID_UD(long dossierId, long userId) {
		return dossierUserPersistence.fetchByDID_UID(dossierId, userId);
	}

	public DossierUser findByDID_RID(long dossierId, long roleId) {
		return dossierUserPersistence.fetchByDID_RID(dossierId, roleId);
	}
}