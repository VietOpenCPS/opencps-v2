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

import org.opencps.dossiermgt.model.DossierAction;
import org.opencps.dossiermgt.service.base.DossierActionLocalServiceBaseImpl;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.search.Indexable;
import com.liferay.portal.kernel.search.IndexableType;
import com.liferay.portal.kernel.service.ServiceContext;

/**
 * The implementation of the dossier action local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are
 * added, rerun ServiceBuilder to copy their definitions into the
 * {@link org.opencps.dossiermgt.service.DossierActionLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security
 * checks based on the propagated JAAS credentials because this service can only
 * be accessed from within the same VM.
 * </p>
 *
 * @author huymq
 * @see DossierActionLocalServiceBaseImpl
 * @see org.opencps.dossiermgt.service.DossierActionLocalServiceUtil
 */
@ProviderType
public class DossierActionLocalServiceImpl extends DossierActionLocalServiceBaseImpl {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this class directly. Always use {@link
	 * org.opencps.dossiermgt.service.DossierActionLocalServiceUtil} to access
	 * the dossier action local service.
	 */

	@Indexable(type = IndexableType.REINDEX)
	public DossierAction updateDossierAction(long groupId, long dossierActionId, long dossierId, long serviceProcessId,
			long previousActionId, String actionCode, String actionUser, String actionName, String actionNote,
			int actionOverdue, String syncActionCode, boolean pending, boolean rollbackable, String stepCode,
			String stepName, Date dueDate, long nextActionId, String payload, String stepInstruction,
			ServiceContext context) throws PortalException {

		validateUpdateAction(groupId, dossierActionId, dossierId, serviceProcessId, previousActionId, actionCode,
				actionUser, actionName, actionNote, actionOverdue, syncActionCode, pending, rollbackable, stepCode,
				stepName, dueDate, nextActionId, payload, stepInstruction);

		DossierAction object = null;

		Date now = new Date();

		User userAction = userLocalService.getUser(context.getUserId());

		if (dossierActionId != 0) {
			dossierActionId = counterLocalService.increment(DossierAction.class.getName());

			object = dossierActionPersistence.create(dossierActionId);

			// Add audit fields
			object.setCompanyId(context.getCompanyId());
			object.setGroupId(groupId);
			object.setCreateDate(now);
			object.setModifiedDate(now);
			object.setUserId(userAction.getUserId());
			object.setUserName(userAction.getFullName());

			object.setDossierId(dossierId);
			object.setServiceProcessId(serviceProcessId);
			object.setPreviousActionId(previousActionId);
			object.setActionCode(syncActionCode);
			object.setActionUser(actionUser);
			object.setActionName(actionName);
			object.setActionNote(actionNote);
			object.setActionOverdue(actionOverdue);
			object.setSyncActionCode(syncActionCode);
			object.setPending(pending);
			object.setRollbackable(rollbackable);
			object.setStepCode(stepCode);
			object.setStepName(stepName);
			object.setDueDate(dueDate);
			object.setNextActionId(nextActionId);
			object.setPayload(payload);
			object.setStepInstruction(stepInstruction);

		} else {

		}

		dossierActionPersistence.update(object);

		return object;
	}

	@Indexable(type = IndexableType.DELETE)
	public DossierAction removeAction(long actionId) throws PortalException {
		DossierAction action = dossierActionPersistence.fetchByPrimaryKey(actionId);

		return dossierActionPersistence.remove(action);
	}

	@Indexable(type = IndexableType.REINDEX)
	public DossierAction updatePending(long actionId, boolean pending) throws PortalException {
		DossierAction action = dossierActionPersistence.fetchByPrimaryKey(actionId);

		action.setPending(pending);

		Date now = new Date();

		action.setModifiedDate(now);

		dossierActionPersistence.update(action);

		return action;
	}

	private void validateUpdateAction(long groupId, long dossierActionId, long dossierId, long serviceProcessId,
			long previousActionId, String actionCode, String actionUser, String actionName, String actionNote,
			int actionOverdue, String syncActionCode, boolean pending, boolean rollbackable, String stepCode,
			String stepName, Date dueDate, long nextActionId, String payload, String stepInstruction)
			throws PortalException {

	}

}