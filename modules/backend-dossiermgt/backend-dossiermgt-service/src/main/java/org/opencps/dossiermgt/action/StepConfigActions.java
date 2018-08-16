package org.opencps.dossiermgt.action;

import javax.naming.AuthenticationException;

import org.opencps.dossiermgt.model.StepConfig;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.service.ServiceContext;

public interface StepConfigActions {

	public StepConfig addStepConfig(long userId, long groupId, String stepCode, String stepName, Integer stepType,
			String dossierStatus, String dossierSubStatus, String menuGroup, String menuStepName, String buttonConfig, ServiceContext serviceContext)
			throws PortalException, AuthenticationException;

	public StepConfig updateStepConfig(Long stepConfigId, long userId, long groupId, String stepCode, String stepName, Integer stepType,
			String dossierStatus, String dossierSubStatus, String menuGroup, String menuStepName, String buttonConfig, ServiceContext serviceContext) throws PortalException, AuthenticationException;

	public void deleteStepConfig(Long stepConfigId, ServiceContext serviceContext) throws PortalException, AuthenticationException;

	public StepConfig updateStepConfigDB(long userId, long groupId, String stepCode, String stepName, Integer stepType,
			String dossierStatus, String dossierSubStatus, String menuGroup, String menuStepName, String buttonConfig)
			throws PortalException;

	public boolean deleteAllStepConfig(long groupId, long userId, ServiceContext serviceContext);
}
