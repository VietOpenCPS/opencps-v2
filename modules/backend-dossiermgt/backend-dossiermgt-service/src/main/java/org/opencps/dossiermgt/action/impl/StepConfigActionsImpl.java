package org.opencps.dossiermgt.action.impl;

import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.Validator;

import java.util.List;

import javax.naming.AuthenticationException;

import org.opencps.dossiermgt.action.StepConfigActions;
import org.opencps.dossiermgt.model.StepConfig;
import org.opencps.dossiermgt.service.StepConfigLocalServiceUtil;

import backend.auth.api.BackendAuthImpl;

public class StepConfigActionsImpl implements StepConfigActions {

	Log _log = LogFactoryUtil.getLog(StepConfigActionsImpl.class);

	@Override
	public StepConfig addStepConfig(long userId, long groupId, String stepCode, String stepName, Integer stepType,
			String dossierStatus, String dossierSubStatus, String menuGroup, String menuStepName, String buttonConfig,
			ServiceContext serviceContext) throws PortalException, AuthenticationException {
		
		BackendAuthImpl authImpl = new BackendAuthImpl();

		if (authImpl.hasResource(serviceContext, StringPool.BLANK, StringPool.BLANK)) {
			StepConfig object = null;

			if (Validator.isNotNull(stepCode)) {
				object = StepConfigLocalServiceUtil.addStepConfig(userId, groupId, stepCode, stepName, stepType,
						dossierStatus, dossierSubStatus, menuGroup, menuStepName, buttonConfig);
			}
			return object;
		} else {
			throw new AuthenticationException();
		}
		
	}

	@Override
	public StepConfig updateStepConfig(Long stepConfigId, long userId, long groupId, String stepCode, String stepName,
			Integer stepType, String dossierStatus, String dossierSubStatus, String menuGroup, String menuStepName,
			String buttonConfig, ServiceContext serviceContext) throws PortalException, AuthenticationException {

		BackendAuthImpl authImpl = new BackendAuthImpl();

		if (authImpl.hasResource(serviceContext, StringPool.BLANK, StringPool.BLANK)) {
			StepConfig object;

			object = StepConfigLocalServiceUtil.updateStepConfig(stepConfigId, userId, groupId, stepCode, stepName,
					stepType, dossierStatus, dossierSubStatus, menuGroup, menuStepName, buttonConfig);

			return object;
		} else {
			throw new AuthenticationException();
		}
		
	}

	@Override
	public void deleteStepConfig(Long stepConfigId, ServiceContext serviceContext)
			throws PortalException, AuthenticationException {

		BackendAuthImpl authImpl = new BackendAuthImpl();

		if (authImpl.hasResource(serviceContext, StringPool.BLANK, StringPool.BLANK)) {
			StepConfigLocalServiceUtil.removeStepConfig(stepConfigId);
		} else {
			throw new AuthenticationException();
		}

	}

	@Override
	public StepConfig updateStepConfigDB(long userId, long groupId, String stepCode, String stepName, Integer stepType,
			String dossierStatus, String dossierSubStatus, String menuGroup, String menuStepName, String buttonConfig)
			throws PortalException {

		return StepConfigLocalServiceUtil.updateStepConfigDB(userId, groupId, stepCode, stepName, stepType,
				dossierStatus, dossierSubStatus, menuGroup, menuStepName, buttonConfig);
	}

	@Override
	public boolean deleteAllStepConfig(long groupId, long userId, ServiceContext serviceContext) {
		boolean flag = false;
		List<StepConfig> stepList = StepConfigLocalServiceUtil.getStepByGroupId(groupId);
		if (stepList != null && stepList.size() > 0) {
			for (StepConfig step : stepList) {
				StepConfigLocalServiceUtil.deleteStepConfig(step);
				flag = true;
			}
		} else {
			flag = true;
		}
		return flag;
	}

}
