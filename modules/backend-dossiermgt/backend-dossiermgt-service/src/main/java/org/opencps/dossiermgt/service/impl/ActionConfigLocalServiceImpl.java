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

import org.opencps.dossiermgt.constants.ActionConfigTerm;
import org.opencps.dossiermgt.exception.DuplicateActionCodeException;
import org.opencps.dossiermgt.model.ActionConfig;
import org.opencps.dossiermgt.service.base.ActionConfigLocalServiceBaseImpl;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.search.Indexable;
import com.liferay.portal.kernel.search.IndexableType;
import com.liferay.portal.kernel.util.Validator;

/**
 * The implementation of the action config local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are
 * added, rerun ServiceBuilder to copy their definitions into the
 * {@link org.opencps.dossiermgt.service.ActionConfigLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security
 * checks based on the propagated JAAS credentials because this service can only
 * be accessed from within the same VM.
 * </p>
 *
 * @author huymq
 * @see ActionConfigLocalServiceBaseImpl
 * @see org.opencps.dossiermgt.service.ActionConfigLocalServiceUtil
 */
public class ActionConfigLocalServiceImpl extends ActionConfigLocalServiceBaseImpl {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this class directly. Always use {@link
	 * org.opencps.dossiermgt.service.ActionConfigLocalServiceUtil} to access
	 * the action config local service.
	 */

	@Indexable(type = IndexableType.REINDEX)
	public ActionConfig addActionConfig(long userId, long groupId, String actionCode, String actionName,
			Boolean extraForm, String formScript, String sampleData, Boolean insideProcess, Integer userNote,
			Integer syncType, Boolean pending, Boolean rollbackable, String notificationType, String documentType)
			throws PortalException {

		validate(actionCode, 0);

		User user = userLocalService.getUser(userId);

		Date now = new Date();

		long actionConfigId = counterLocalService.increment(ActionConfig.class.getName());

		ActionConfig actionConfig = actionConfigPersistence.create(actionConfigId);

		actionConfig.setGroupId(groupId);
		actionConfig.setCompanyId(user.getCompanyId());
		actionConfig.setUserId(user.getUserId());
		
		actionConfig.setCreateDate(now);
		actionConfig.setModifiedDate(now);

		actionConfig.setActionCode(actionCode);
		actionConfig.setActionName(actionName);
		actionConfig.setExtraForm(Validator.isNotNull(extraForm) ? extraForm : Boolean.FALSE);
		actionConfig.setFormScript(formScript);
		actionConfig.setSampleData(sampleData);
		actionConfig.setInsideProcess(Validator.isNotNull(insideProcess) ? extraForm : Boolean.FALSE);
		actionConfig.setUserNote(Validator.isNotNull(userNote) ? userNote : 0);
		actionConfig.setSyncType(Validator.isNotNull(syncType) ? syncType : 0);
		actionConfig.setPending(Validator.isNotNull(pending) ? extraForm : Boolean.FALSE);
		actionConfig.setRollbackable(Validator.isNotNull(rollbackable) ? rollbackable : Boolean.FALSE);
		actionConfig.setNotificationType(notificationType);
		actionConfig.setDocumentType(documentType);

		actionConfigPersistence.update(actionConfig);

		return actionConfig;

	}

	@Indexable(type = IndexableType.REINDEX)
	public ActionConfig updateActionConfig(long actionConfigId, long userId, long groupId, String actionCode, String actionName,
			Boolean extraForm, String formScript, String sampleData, Boolean insideProcess, Integer userNote,
			Integer syncType, Boolean pending, Boolean rollbackable, String notificationType, String documentType) throws PortalException {

		validate(actionCode, actionConfigId);

		User user = userLocalService.getUser(userId);

		Date now = new Date();

		ActionConfig actionConfig = actionConfigPersistence.findByPrimaryKey(actionConfigId);

		actionConfig.setUserId(user.getUserId());
		actionConfig.setModifiedDate(now);

		if (Validator.isNotNull(actionCode)) {
			actionConfig.setActionCode(actionCode);
		}
		if (Validator.isNotNull(actionCode)) {
			actionConfig.setActionName(actionName);
		}
		if (Validator.isNotNull(extraForm)) {
			actionConfig.setExtraForm(extraForm);
		}
		if (Validator.isNotNull(formScript)) {
			actionConfig.setFormScript(formScript);
		}
		if (Validator.isNotNull(sampleData)) {
			actionConfig.setSampleData(sampleData);
		}
		if (Validator.isNotNull(insideProcess)) {
			actionConfig.setInsideProcess(insideProcess);
		}
		if (Validator.isNotNull(syncType)) {
			actionConfig.setSyncType(syncType);
		}
		if (Validator.isNotNull(pending)) {
			actionConfig.setPending(pending);
		}
		if (Validator.isNotNull(notificationType)) {
			actionConfig.setNotificationType(notificationType);
		}
		if (Validator.isNotNull(userNote)) {
			actionConfig.setUserNote(userNote);
		}
		if (Validator.isNotNull(rollbackable)) {
			actionConfig.setRollbackable(rollbackable);
		}
		if (Validator.isNotNull(notificationType)) {
			actionConfig.setNotificationType(notificationType);
		}
		if (Validator.isNotNull(documentType)) {
			actionConfig.setDocumentType(documentType);
		}

		actionConfigPersistence.update(actionConfig);

		return actionConfig;

	}

	@Indexable(type = IndexableType.DELETE)
	public ActionConfig removeActionConfig(long actionConfigId) throws PortalException {
		ActionConfig actionConfig = actionConfigPersistence.findByPrimaryKey(actionConfigId);

		actionConfig = actionConfigPersistence.remove(actionConfig);

		return actionConfig;
	}

	public ActionConfig getByCode(String actionCode) {

		return actionConfigPersistence.fetchByF_BY_ActionCode(actionCode);

	}

	public JSONObject getForm(String actionCode) {

		JSONObject result = JSONFactoryUtil.createJSONObject();

		ActionConfig actionConfig = actionConfigPersistence.fetchByF_BY_ActionCode(actionCode);

		result.put(ActionConfigTerm.FORM_SCRIPT, actionConfig.getFormScript());
		result.put(ActionConfigTerm.SAMPLE_DATA, actionConfig.getSampleData());

		return result;

	}

	private void validate(String actionCode, long actionConfigId) throws PortalException {

		ActionConfig actionConfig = actionConfigPersistence.fetchByF_BY_ActionCode(actionCode);

		if (Validator.isNull(actionCode)) {
			throw new DuplicateActionCodeException("DuplicateActionCodeException");
		}

		if (Validator.isNotNull(actionConfig) && actionConfigId == 0) {
			throw new DuplicateActionCodeException("DuplicateActionCodeException");
		}
		
		if (Validator.isNotNull(actionConfig) && actionConfigId != actionConfig.getActionConfigId()) {
			throw new DuplicateActionCodeException("DuplicateStepCodeException");
		}

	}
}