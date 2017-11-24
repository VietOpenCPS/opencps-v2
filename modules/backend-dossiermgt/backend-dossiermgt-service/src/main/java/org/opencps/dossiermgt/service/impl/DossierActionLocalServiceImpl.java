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

import java.util.Date;

import org.opencps.dossiermgt.model.Dossier;
import org.opencps.dossiermgt.model.DossierAction;
import org.opencps.dossiermgt.service.base.DossierActionLocalServiceBaseImpl;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.search.Indexable;
import com.liferay.portal.kernel.search.IndexableType;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.StringPool;

import aQute.bnd.annotation.ProviderType;

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
		long userId = 0l;
		
		String fullName = StringPool.BLANK;
		
		Date now = new Date();
		
		
		if (context.getUserId() > 0) {
			User userAction = userLocalService.getUser(context.getUserId());
			
			userId = userAction.getUserId();
			fullName = userAction.getFullName();
			
		}

		if (dossierActionId == 0) {
			dossierActionId = counterLocalService.increment(DossierAction.class.getName());

			object = dossierActionPersistence.create(dossierActionId);

			// Add audit fields
			object.setCompanyId(context.getCompanyId());
			object.setGroupId(groupId);
			object.setCreateDate(now);
			object.setModifiedDate(now);
			object.setUserId(userId);
			object.setUserName(fullName);

			object.setDossierId(dossierId);
			object.setServiceProcessId(serviceProcessId);
			object.setPreviousActionId(previousActionId);
			object.setActionCode(actionCode);
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
			
			//Add DossierActionId to Dossier
			
			//TODO add Indexer for Dossier after update DossierAction
			Dossier dossier = dossierPersistence.fetchByPrimaryKey(dossierId);
			dossier.setDossierActionId(dossierActionId);
			dossierPersistence.update(dossier);

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
	public DossierAction updateNextActionId(long actionId, long nextActionId) throws PortalException {
		DossierAction action = dossierActionPersistence.fetchByPrimaryKey(actionId);

		action.setNextActionId(nextActionId);

		Date now = new Date();

		action.setModifiedDate(now);

		dossierActionPersistence.update(action);

		return action;
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
	
	public DossierAction getByPenddingStatus(long dossierId, boolean pending) {
		return dossierActionPersistence.fetchByDID_DPG(dossierId, pending);
	}
	
	public DossierAction getByNextActionId(long dossierId, long nextActionId) {
		return dossierActionPersistence.fetchByDID_NACTID(dossierId, nextActionId);
	}
	
	public DossierAction getDossierActionById(long dossierId, long userId) {
		return dossierActionPersistence.fetchByDID_UID(dossierId, userId);
	}
	

}