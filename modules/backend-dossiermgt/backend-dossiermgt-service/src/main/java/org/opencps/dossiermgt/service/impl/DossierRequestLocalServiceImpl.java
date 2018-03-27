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

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.opencps.dossiermgt.model.DossierRequest;
import org.opencps.dossiermgt.service.DossierLocalServiceUtil;
import org.opencps.dossiermgt.service.base.DossierRequestLocalServiceBaseImpl;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;

/**
 * The implementation of the dossier request local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are
 * added, rerun ServiceBuilder to copy their definitions into the
 * {@link org.opencps.dossiermgt.service.DossierRequestLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security
 * checks based on the propagated JAAS credentials because this service can only
 * be accessed from within the same VM.
 * </p>
 *
 * @author huymq
 * @see DossierRequestLocalServiceBaseImpl
 * @see org.opencps.dossiermgt.service.DossierRequestLocalServiceUtil
 */
@ProviderType
public class DossierRequestLocalServiceImpl extends DossierRequestLocalServiceBaseImpl {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this class directly. Always use {@link
	 * org.opencps.dossiermgt.service.DossierRequestLocalServiceUtil} to access
	 * the dossier request local service.
	 */

	public DossierRequest updateDossierRequest(long dossierRequestId, long dossierId, String referenceUid,
			String requestType, String comment, int isNew, ServiceContext context)
			throws PortalException, SystemException {
		
		Date now = new Date();
		
		
		long userId = context.getUserId();
		
		String userName = StringPool.BLANK;
		
		if (userId != 0) {
			User user = userLocalService.fetchUser(userId);
			
			if (Validator.isNotNull(user)) {
				userName = user.getFullName();
			}
		}
		
		DossierRequest dossierRequest = null;
		
		if (dossierRequestId != 0) {
			dossierRequest = dossierRequestPersistence.fetchByPrimaryKey(dossierRequestId);
//			Audit fields
			dossierRequest.setGroupId(context.getScopeGroupId());
			dossierRequest.setCreateDate(now);
			dossierRequest.setModifiedDate(now);
			dossierRequest.setUserId(userId);
			dossierRequest.setUserName(userName);
			
//			Content fields
			dossierRequest.setDossierId(dossierId);
			dossierRequest.setReferenceUid(referenceUid);
			dossierRequest.setRequestType(requestType);
			dossierRequest.setComment(comment);
			dossierRequest.setIsNew(isNew);
			
			
		} else {
			dossierRequestId = counterLocalService.increment(DossierRequest.class.getName());
			
			dossierRequest = dossierRequestPersistence.create(dossierRequestId);
			
			dossierRequest.setModifiedDate(now);
			dossierRequest.setUserId(userId);
			dossierRequest.setUserName(userName);
			
			dossierRequest.setComment(comment);
			dossierRequest.setIsNew(isNew);
			
		}
		
		dossierRequestPersistence.update(dossierRequest);
		
		return dossierRequest;
		
	}
	
	public List<DossierRequest> getDossierRequest(long dossierId, int isNew) throws PortalException, SystemException {
		
		List<DossierRequest> dossierRequestList = new ArrayList<DossierRequest>();
		
		return null;
	}

}