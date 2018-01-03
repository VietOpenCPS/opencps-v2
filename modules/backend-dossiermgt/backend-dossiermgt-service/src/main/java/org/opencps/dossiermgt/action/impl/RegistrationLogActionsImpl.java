package org.opencps.dossiermgt.action.impl;

import java.util.List;

import org.opencps.dossiermgt.action.RegistrationLogActions;
import org.opencps.dossiermgt.model.RegistrationLog;
import org.opencps.dossiermgt.service.RegistrationLogLocalServiceUtil;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.service.ServiceContext;

public class RegistrationLogActionsImpl implements RegistrationLogActions {

	@Override
	public List<RegistrationLog> getRegistrationLogbyId(long groupId,long registrationId) throws PortalException {
		return RegistrationLogLocalServiceUtil.getRegistrationLogbyRegId(groupId, registrationId);
	}

	@Override
	public RegistrationLog addRegistrationLogById(long groupId, long registrationId, String author, String content,
			String payload, ServiceContext serviceContext) {
		// TODO Auto-generated method stub
		long userId = serviceContext.getUserId();
		return RegistrationLogLocalServiceUtil.addLog(author, groupId, userId, registrationId, content, payload);
	}
}