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
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.search.Indexable;
import com.liferay.portal.kernel.search.IndexableType;
import com.liferay.portal.kernel.util.Validator;

import java.util.Date;
import java.util.List;

import org.opencps.dossiermgt.constants.ActionConfigTerm;
import org.opencps.dossiermgt.exception.DuplicateActionCodeException;
import org.opencps.dossiermgt.model.ActionConfig;
import org.opencps.dossiermgt.service.base.ActionConfigLocalServiceBaseImpl;

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
	 * org.opencps.dossiermgt.service.ActionConfigLocalServiceUtil} to access the
	 * action config local service.
	 */

	@Indexable(type = IndexableType.REINDEX)
	public ActionConfig addActionConfig(long userId, long groupId, String actionCode, String actionName,
			Boolean extraForm, String formScript, String sampleData, Boolean insideProcess, Integer userNote,
			Integer syncType, Boolean pending, Boolean rollbackable, String notificationType, String documentType,
			String mappingAction) throws PortalException {

		validate(groupId, actionCode, 0);

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
		actionConfig.setFormConfig(formScript);
		actionConfig.setSampleData(sampleData);
		actionConfig.setInsideProcess(Validator.isNotNull(insideProcess) ? extraForm : Boolean.FALSE);
		actionConfig.setUserNote(Validator.isNotNull(userNote) ? userNote : 0);
		actionConfig.setSyncType(Validator.isNotNull(syncType) ? syncType : 0);
		actionConfig.setPending(Validator.isNotNull(pending) ? extraForm : Boolean.FALSE);
		actionConfig.setRollbackable(Validator.isNotNull(rollbackable) ? rollbackable : Boolean.FALSE);
		actionConfig.setNotificationType(notificationType);
		actionConfig.setDocumentType(documentType);
		actionConfig.setMappingAction(mappingAction);

		actionConfigPersistence.update(actionConfig);

		return actionConfig;

	}

	@Indexable(type = IndexableType.REINDEX)
	public ActionConfig updateActionConfig(long actionConfigId, long userId, long groupId, String actionCode,
			String actionName, Boolean extraForm, String formScript, String sampleData, Boolean insideProcess,
			Integer userNote, Integer syncType, Boolean pending, Boolean rollbackable, String notificationType,
			String documentType, String mappingAction) throws PortalException {

		validate(groupId, actionCode, actionConfigId);

		User user = userLocalService.getUser(userId);

		Date now = new Date();

		ActionConfig actionConfig = actionConfigPersistence.findByPrimaryKey(actionConfigId);

		actionConfig.setUserId(user.getUserId());
		actionConfig.setModifiedDate(now);

		if (actionCode != null) {
			actionConfig.setActionCode(actionCode);
		}
		if (actionName != null) {
			actionConfig.setActionName(actionName);
		}
		if (extraForm != null) {
			actionConfig.setExtraForm(extraForm);
		}
		if (formScript != null) {
			actionConfig.setFormConfig(formScript);
		}
		if (sampleData != null) {
			actionConfig.setSampleData(sampleData);
		}
		if (insideProcess != null) {
			actionConfig.setInsideProcess(insideProcess);
		}
		if (syncType != null) {
			actionConfig.setSyncType(syncType);
		}
		if (pending != null) {
			actionConfig.setPending(pending);
		}
		if (notificationType != null) {
			actionConfig.setNotificationType(notificationType);
		}
		if (userNote != null) {
			actionConfig.setUserNote(userNote);
		}
		if (rollbackable != null) {
			actionConfig.setRollbackable(rollbackable);
		}
		if (notificationType != null) {
			actionConfig.setNotificationType(notificationType);
		}
		if (documentType != null) {
			actionConfig.setDocumentType(documentType);
		}
		if (mappingAction != null) {
			actionConfig.setMappingAction(mappingAction);
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

	public ActionConfig getByCode(long groupId, String actionCode) {

		return actionConfigPersistence.fetchByF_BY_ActionCode(groupId, actionCode);

	}

	public JSONObject getForm(long groupId, String actionCode) {

		JSONObject result = JSONFactoryUtil.createJSONObject();

		ActionConfig actionConfig = actionConfigPersistence.fetchByF_BY_ActionCode(groupId, actionCode);

		result.put(ActionConfigTerm.FORM_CONFIG, actionConfig.getFormConfig());
		result.put(ActionConfigTerm.SAMPLE_DATA, actionConfig.getSampleData());

		return result;

	}

	// LamTV_ Process Update DB ActionConfig
	@Indexable(type = IndexableType.REINDEX)
	public ActionConfig updateActionConfigDB(long userId, long groupId, String actionCode, String actionName,
			Boolean extraForm, String sampleData, Boolean insideProcess, Integer userNote, Integer syncType,
			Integer eventType, Integer infoType, Boolean rollbackable, String notificationType, String documentType,
			String formConfig, String mappingAction, int dateOption) throws PortalException {

		User user = userLocalService.getUser(userId);
		Date now = new Date();

		ActionConfig actionConfig = actionConfigPersistence.fetchByF_BY_ActionCode(groupId, actionCode);

		if (actionConfig == null) {
			long actionConfigId = counterLocalService.increment(ActionConfig.class.getName());
			actionConfig = actionConfigPersistence.create(actionConfigId);

			actionConfig.setGroupId(groupId);
			actionConfig.setCompanyId(user.getCompanyId());
			actionConfig.setUserId(user.getUserId());
			actionConfig.setCreateDate(now);
			actionConfig.setModifiedDate(now);

			actionConfig.setActionCode(actionCode);
			actionConfig.setActionName(actionName);
			actionConfig.setExtraForm(Validator.isNotNull(extraForm) ? extraForm : Boolean.FALSE);
			actionConfig.setFormConfig(formConfig);
			actionConfig.setSampleData(sampleData);
			actionConfig.setInsideProcess(Validator.isNotNull(insideProcess) ? insideProcess : Boolean.FALSE);
			actionConfig.setUserNote(Validator.isNotNull(userNote) ? userNote : 0);
			actionConfig.setSyncType(Validator.isNotNull(syncType) ? syncType : 0);
			actionConfig.setEventType(Validator.isNotNull(eventType) ? eventType : 0);
			actionConfig.setInfoType(Validator.isNotNull(infoType) ? infoType : 0);
			actionConfig.setRollbackable(Validator.isNotNull(rollbackable) ? rollbackable : Boolean.FALSE);
			actionConfig.setNotificationType(notificationType);
			actionConfig.setDocumentType(documentType);
			actionConfig.setMappingAction(mappingAction);
			actionConfig.setDateOption(dateOption);
		} else {
			actionConfig.setUserId(user.getUserId());
			actionConfig.setModifiedDate(new Date());
			actionConfig.setActionName(actionName);
			actionConfig.setExtraForm(extraForm);
			actionConfig.setFormConfig(formConfig);
			actionConfig.setSampleData(sampleData);
			actionConfig.setInsideProcess(insideProcess);
			actionConfig.setSyncType(syncType);
			actionConfig.setUserNote(userNote);
			actionConfig.setEventType(eventType);
			actionConfig.setInfoType(infoType);
			actionConfig.setRollbackable(rollbackable);
			actionConfig.setNotificationType(notificationType);
			actionConfig.setDocumentType(documentType);
			actionConfig.setMappingAction(mappingAction);
			actionConfig.setDateOption(dateOption);
		}

		return actionConfigPersistence.update(actionConfig);

	}

	// LamTV_Add
	public List<ActionConfig> getByGroupId(long groupId) {
		return actionConfigPersistence.findByF_BY_GID(groupId);
	}

	private void validate(long groupId, String actionCode, long actionConfigId) throws PortalException {

		ActionConfig actionConfig = actionConfigPersistence.fetchByF_BY_ActionCode(groupId, actionCode);
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

	// super_admin Generators
	@Indexable(type = IndexableType.DELETE)
	public ActionConfig adminProcessDelete(Long id) {

		ActionConfig object = actionConfigPersistence.fetchByPrimaryKey(id);

		if (Validator.isNull(object)) {
			return null;
		} else {
			actionConfigPersistence.remove(object);
		}

		return object;
	}

	@Indexable(type = IndexableType.REINDEX)
	public ActionConfig adminProcessData(JSONObject objectData) {

		ActionConfig object = null;

		if (objectData.getLong("actionConfigId") > 0) {

			object = actionConfigPersistence.fetchByPrimaryKey(objectData.getLong("actionConfigId"));

			object.setModifiedDate(new Date());

		} else {

			long id = CounterLocalServiceUtil.increment(ActionConfig.class.getName());

			object = actionConfigPersistence.create(id);

			object.setGroupId(objectData.getLong("groupId"));
			object.setCompanyId(objectData.getLong("companyId"));
			object.setCreateDate(new Date());

		}

		object.setUserId(objectData.getLong("userId"));

		object.setActionCode(objectData.getString("actionCode"));
		object.setActionName(objectData.getString("actionName"));
		object.setExtraForm(objectData.getBoolean("extraForm"));
		object.setFormConfig(objectData.getString("formConfig"));
		object.setSampleData(objectData.getString("sampleData"));
		object.setInsideProcess(objectData.getBoolean("insideProcess"));
		object.setUserNote(objectData.getInt("userNote"));
		object.setSyncType(objectData.getInt("syncType"));
		object.setEventType(objectData.getInt("eventType"));
		object.setInfoType(objectData.getInt("infoType"));
		object.setPending(objectData.getBoolean("pending"));
		object.setRollbackable(objectData.getBoolean("rollbackable"));
		object.setNotificationType(objectData.getString("notificationType"));
		object.setDocumentType(objectData.getString("documentType"));
		object.setMappingAction(objectData.getString("mappingAction"));
		object.setDateOption(objectData.getInt("dateOption"));

		actionConfigPersistence.update(object);

		return object;
	}
	
	public List<ActionConfig> getByG_ET(long groupId, int eventType) {
		return actionConfigPersistence.findByG_ET(groupId, eventType);
	}
}