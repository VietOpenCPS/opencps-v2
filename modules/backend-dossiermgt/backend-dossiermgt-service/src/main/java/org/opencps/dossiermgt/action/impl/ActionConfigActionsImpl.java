package org.opencps.dossiermgt.action.impl;

import org.opencps.dossiermgt.action.ActionConfigActions;
import org.opencps.dossiermgt.model.ActionConfig;
import org.opencps.dossiermgt.service.ActionConfigLocalServiceUtil;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.Validator;

public class ActionConfigActionsImpl implements ActionConfigActions {

	Log _log = LogFactoryUtil.getLog(ActionConfigActionsImpl.class);

	@Override
	public ActionConfig addActionConfig(long userId, long groupId, String actionCode, String actionName,
			Boolean extraForm, String formScript, String sampleData, Boolean insideProcess, Integer syncType,
			Boolean pending, String notificationType, Boolean createDocument, String documentName,
			String documentScript, String documentCode, Integer sendDocument) throws PortalException {

		ActionConfig object = null;
		
		if (Validator.isNotNull(actionCode)) {
			object = ActionConfigLocalServiceUtil.addActionConfig(userId, groupId, actionCode, actionName,
				extraForm, formScript, sampleData, insideProcess, syncType, pending, notificationType, createDocument,
				documentName, documentScript, documentCode, sendDocument);
		}
		return object;
	}

	@Override
	public ActionConfig updateActionConfig(String actionCodePK, long userId, long groupId, String actionCode,
			String actionName, Boolean extraForm, String formScript, String sampleData, Boolean insideProcess,
			Integer syncType, Boolean pending, String notificationType, Boolean createDocument, String documentName,
			String documentScript, String documentCode, Integer sendDocument) throws PortalException {

		ActionConfig object = ActionConfigLocalServiceUtil.getByCode(actionCodePK);

		long actionConfigId = object.getActionConfigId();

		object = ActionConfigLocalServiceUtil.updateActionConfig(actionConfigId, userId, groupId, actionCode,
				actionName, extraForm, formScript, sampleData, insideProcess, syncType, pending, notificationType,
				createDocument, documentName, documentScript, documentCode, sendDocument);

		return object;
	}

	@Override
	public void deleteActionConfig(Long actionConfigId) throws PortalException {
		ActionConfigLocalServiceUtil.deleteActionConfig(actionConfigId);
	}

}
