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

import org.opencps.dossiermgt.exception.NoSuchDossierUserException;
import org.opencps.dossiermgt.model.DossierUser;
import org.opencps.dossiermgt.service.base.DossierUserLocalServiceBaseImpl;
import org.opencps.dossiermgt.service.persistence.DossierUserPK;

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
//	private static Log _log = LogFactoryUtil.getLog(DossierUserLocalServiceImpl.class);
//	@Indexable(type = IndexableType.REINDEX)
	public DossierUser addDossierUser(long groupId, long dossierId, long userId, int moderator, boolean visited) {
		DossierUserPK pk = new DossierUserPK();
		pk.setUserId(userId);
		pk.setDossierId(dossierId);
		DossierUser object = dossierUserPersistence.fetchByPrimaryKey(pk);
		
		if (object == null) {
			object = dossierUserPersistence.create(pk);
		}
			
		object.setModerator(moderator);
		object.setVisited(visited);
		
		return dossierUserPersistence.update(object);
	}
	
//	@Indexable(type = IndexableType.REINDEX)
	public DossierUser updateDossierUser(long dossierId, long userId, int moderator, boolean visited) throws NoSuchDossierUserException {
		DossierUserPK pk = new DossierUserPK();
		pk.setUserId(userId);
		pk.setDossierId(dossierId);
		DossierUser object = dossierUserPersistence.fetchByPrimaryKey(pk);
		
		object.setModerator(moderator);
		object.setVisited(visited);
		object.setNew(false);
		
		return dossierUserPersistence.update(object);
	}
	
//	@Indexable(type = IndexableType.REINDEX)
	public DossierUser deleteDossierUser(long dossierId, long userId) throws NoSuchDossierUserException {
		DossierUserPK pk = new DossierUserPK();
		pk.setUserId(userId);
		pk.setDossierId(dossierId);
		
		return dossierUserPersistence.remove(pk);
	}
	
	public DossierUser getByDossierUser(long dossierId, long userId) {
		DossierUserPK pk = new DossierUserPK();
		pk.setUserId(userId);
		pk.setDossierId(dossierId);
		
		return dossierUserPersistence.fetchByPrimaryKey(pk);		
	}
	
	public List<DossierUser> findByDID(long dossierId) {
		return dossierUserPersistence.findByDID(dossierId);
	}
	
	public DossierUser findByDID_UD(long dossierId, long userId) {
		return dossierUserPersistence.fetchByDID_UID(dossierId, userId);
	}
}