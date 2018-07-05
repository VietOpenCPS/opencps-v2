package org.opencps.dossiermgt.action.impl;

import javax.naming.AuthenticationException;

import org.opencps.dossiermgt.action.ActionConfigActions;
import org.opencps.dossiermgt.model.ActionConfig;
import org.opencps.dossiermgt.service.ActionConfigLocalServiceUtil;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;

import backend.auth.api.BackendAuthImpl;

public class ActionConfigActionsImpl implements ActionConfigActions {

	Log _log = LogFactoryUtil.getLog(ActionConfigActionsImpl.class);

	@Override
	public ActionConfig addActionConfig(long userId, long groupId, String actionCode, String actionName,
			Boolean extraForm, String formScript, String sampleData, Boolean insideProcess, Integer userNote,
			Integer syncType, Boolean pending, Boolean rollbackable, String notificationType, String documentType,
			ServiceContext serviceContext) throws PortalException, AuthenticationException {

		BackendAuthImpl authImpl = new BackendAuthImpl();

		if (authImpl.hasResource(serviceContext, StringPool.BLANK, StringPool.BLANK)) {
			ActionConfig object = null;

			if (Validator.isNotNull(actionCode)) {
				object = ActionConfigLocalServiceUtil.addActionConfig(userId, groupId, actionCode, actionName, extraForm,
						formScript, sampleData, insideProcess, userNote, syncType, pending, rollbackable, notificationType,
						documentType);
			}
			return object;
		} else {
			throw new AuthenticationException();
		}
		
	}

	@Override
	public ActionConfig updateActionConfig(Long actionConfigId, long userId, long groupId, String actionCode,
			String actionName, Boolean extraForm, String formScript, String sampleData, Boolean insideProcess,
			Integer userNote, Integer syncType, Boolean pending, Boolean rollbackable, String notificationType,
			String documentType, ServiceContext serviceContext) throws PortalException, AuthenticationException {

		BackendAuthImpl authImpl = new BackendAuthImpl();

		if (authImpl.hasResource(serviceContext, StringPool.BLANK, StringPool.BLANK)) {
			
			ActionConfig object = ActionConfigLocalServiceUtil.getActionConfig(actionConfigId);

			object = ActionConfigLocalServiceUtil.updateActionConfig(object.getActionConfigId(), userId, groupId, actionCode,
					actionName, extraForm, formScript, sampleData, insideProcess, userNote, syncType, pending,
					rollbackable, notificationType, documentType);

			return object;
		} else {
			throw new AuthenticationException();
		}

	}

	@Override
	public void deleteActionConfig(Long actionConfigId, ServiceContext serviceContext)
			throws PortalException, AuthenticationException {

		BackendAuthImpl authImpl = new BackendAuthImpl();

		if (authImpl.hasResource(serviceContext, StringPool.BLANK, StringPool.BLANK)) {
			ActionConfigLocalServiceUtil.deleteActionConfig(actionConfigId);
		} else {
			throw new AuthenticationException();
		}

	}

	@Override
	public void updateActionConfigDB(long userId, long groupId, String actionCode, String actionName, Boolean extraForm,
			String sampleData, Boolean insideProcess, Integer syncType, Boolean rollbackable, String notificationType,
			String documentType, String formConfig) {

		ActionConfigLocalServiceUtil.updateActionConfigDB(userId, groupId, actionCode, actionName, extraForm,
				sampleData, insideProcess, syncType, rollbackable, notificationType, documentType, formConfig);
	}

}
