package org.opencps.dossiermgt.action;

import javax.naming.AuthenticationException;

import org.opencps.dossiermgt.model.ActionConfig;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.service.ServiceContext;

public interface ActionConfigActions {

	public ActionConfig addActionConfig(long userId, long groupId, String actionCode, String actionName,
			Boolean extraForm, String formScript, String sampleData, Boolean insideProcess, Integer userNote,
			Integer syncType, Boolean pending, Boolean rollbackable, String notificationType, String documentType, ServiceContext serviceContext)
			throws PortalException, AuthenticationException;

	public ActionConfig updateActionConfig(Long actionConfigId, long userId, long groupId, String actionCode,
			String actionName, Boolean extraForm, String formScript, String sampleData, Boolean insideProcess,
			Integer userNote, Integer syncType, Boolean pending, Boolean rollbackable, String notificationType,
			String documentType, ServiceContext serviceContext) throws PortalException, AuthenticationException;

	public void deleteActionConfig(Long actionConfigId, ServiceContext serviceContext) throws PortalException, AuthenticationException;

	public void updateActionConfigDB(long userId, long groupId, String actionCode, String actionName, Boolean extraForm,
			String sampleData, Boolean insideProcess, Integer userNote, Integer syncType, Integer eventType,
			Integer infoType, Boolean rollbackable, String notificationType, String formConfig);

	public boolean deleteAllActionConfig(long groupId, long userId, ServiceContext serviceContext);

}
