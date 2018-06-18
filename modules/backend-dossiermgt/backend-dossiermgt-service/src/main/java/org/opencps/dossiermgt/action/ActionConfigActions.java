package org.opencps.dossiermgt.action;

import org.opencps.dossiermgt.model.ActionConfig;

import com.liferay.portal.kernel.exception.PortalException;

public interface ActionConfigActions {

	public ActionConfig addActionConfig(long userId, long groupId, String actionCode, String actionName,
			Boolean extraForm, String formScript, String sampleData, Boolean insideProcess, Integer syncType,
			Boolean pending, String notificationType, Boolean createDocument, String documentName,
			String documentScript, String documentCode, Integer sendDocument) throws PortalException;
	
	public ActionConfig updateActionConfig(String actionCodePK, long userId, long groupId, String actionCode, String actionName,
			Boolean extraForm, String formScript, String sampleData, Boolean insideProcess, Integer syncType,
			Boolean pending, String notificationType, Boolean createDocument, String documentName,
			String documentScript, String documentCode, Integer sendDocument) throws PortalException;
	
	public void deleteActionConfig(Long actionConfigId) throws PortalException;
}
