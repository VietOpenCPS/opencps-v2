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

import com.liferay.counter.kernel.service.CounterLocalServiceUtil;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.search.Indexable;
import com.liferay.portal.kernel.search.IndexableType;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.Validator;

import java.util.Date;
import java.util.List;

import org.opencps.dossiermgt.model.DossierRequestUD;
import org.opencps.dossiermgt.service.base.DossierRequestUDLocalServiceBaseImpl;

import aQute.bnd.annotation.ProviderType;

/**
 * The implementation of the dossier request ud local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are
 * added, rerun ServiceBuilder to copy their definitions into the
 * {@link org.opencps.dossiermgt.service.DossierRequestUDLocalService}
 * interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security
 * checks based on the propagated JAAS credentials because this service can only
 * be accessed from within the same VM.
 * </p>
 *
 * @author huymq
 * @see DossierRequestUDLocalServiceBaseImpl
 * @see org.opencps.dossiermgt.service.DossierRequestUDLocalServiceUtil
 */
@ProviderType
public class DossierRequestUDLocalServiceImpl extends DossierRequestUDLocalServiceBaseImpl {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this class directly. Always use {@link
	 * org.opencps.dossiermgt.service.DossierRequestUDLocalServiceUtil} to access
	 * the dossier request ud local service.
	 */

	public DossierRequestUD updateDossierRequest(long dossierRequestId, long dossierId, String referenceUid,
			String requestType, String comment, int isNew, int status, ServiceContext context)
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

		DossierRequestUD dossierRequest = null;

		if (dossierRequestId != 0) {
			dossierRequest = dossierRequestUDPersistence.fetchByPrimaryKey(dossierRequestId);

			dossierRequest.setModifiedDate(now);
			dossierRequest.setUserId(userId);
			dossierRequest.setUserName(userName);

			dossierRequest.setComment(comment);
			dossierRequest.setIsNew(isNew);
			dossierRequest.setStatusReg(status);

		} else {
			dossierRequestId = counterLocalService.increment(DossierRequestUD.class.getName());

			dossierRequest = dossierRequestUDPersistence.create(dossierRequestId);
			// Audit fields
			dossierRequest.setGroupId(context.getScopeGroupId());
			dossierRequest.setCreateDate(now);
			dossierRequest.setModifiedDate(now);
			dossierRequest.setUserId(userId);
			dossierRequest.setUserName(userName);

			// Content fields
			dossierRequest.setDossierId(dossierId);
			dossierRequest.setReferenceUid(referenceUid);
			dossierRequest.setRequestType(requestType);
			dossierRequest.setComment(comment);
			dossierRequest.setIsNew(isNew);
			dossierRequest.setStatusReg(status);

		}

		dossierRequestUDPersistence.update(dossierRequest);

		return dossierRequest;

	}

	public List<DossierRequestUD> getDossierRequest(long dossierId, int isNew) throws PortalException, SystemException {
		// TODO dont know why can use finder in service.xml file

		// This is temporary function

		return dossierRequestUDPersistence.findByDID_IN(dossierId, isNew);
	}

	// LamTV: Update get list dossierRequest
	public DossierRequestUD getDossierRequestByDossierId(long dossierId) {
		List<DossierRequestUD> dReqUD = dossierRequestUDPersistence.findByD_(dossierId);
		if (dReqUD != null && dReqUD.size() > 0) {
			return dReqUD.get(0);
		} else {
			return null;
		}
	}

	// super_admin Generators
	@Indexable(type = IndexableType.DELETE)
	public DossierRequestUD adminProcessDelete(Long id) {

		DossierRequestUD object = dossierRequestUDPersistence.fetchByPrimaryKey(id);

		if (Validator.isNull(object)) {
			return null;
		} else {
			dossierRequestUDPersistence.remove(object);
		}

		return object;
	}

	@Indexable(type = IndexableType.REINDEX)
	public DossierRequestUD adminProcessData(JSONObject objectData) {

		DossierRequestUD object = null;

		if (objectData.getLong("DossierRequestUDId") > 0) {

			object = dossierRequestUDPersistence.fetchByPrimaryKey(objectData.getLong("DossierRequestUDId"));

			object.setModifiedDate(new Date());

		} else {

			long id = CounterLocalServiceUtil.increment(DossierRequestUD.class.getName());

			object = dossierRequestUDPersistence.create(id);

			object.setGroupId(objectData.getLong("groupId"));
			object.setCompanyId(objectData.getLong("companyId"));
			object.setCreateDate(new Date());

		}

		object.setUserId(objectData.getLong("userId"));
		object.setUserName(objectData.getString("userName"));

		object.setDossierId(objectData.getLong("dossierId"));
		object.setReferenceUid(objectData.getString("referenceUid"));
		object.setRequestType(objectData.getString("requestType"));
		object.setComment(objectData.getString("comment"));
		object.setIsNew(objectData.getInt("isNew"));
		object.setStatusReg(objectData.getInt("statusReg"));

		dossierRequestUDPersistence.update(object);

		return object;
	}
}