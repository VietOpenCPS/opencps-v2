package org.opencps.dossiermgt.action.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.opencps.dossiermgt.action.RegistrationActions;
import org.opencps.dossiermgt.action.RegistrationLogActions;
import org.opencps.dossiermgt.model.Registration;
import org.opencps.dossiermgt.model.RegistrationForm;
import org.opencps.dossiermgt.model.RegistrationLog;
import org.opencps.dossiermgt.model.RegistrationTemplates;
import org.opencps.dossiermgt.service.RegistrationFormLocalServiceUtil;
import org.opencps.dossiermgt.service.RegistrationLocalServiceUtil;
import org.opencps.dossiermgt.service.RegistrationLogLocalServiceUtil;
import org.opencps.dossiermgt.service.RegistrationTemplatesLocalServiceUtil;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.Validator;

public class RegistrationLogActionsImpl implements RegistrationLogActions {

	@Override
	public List<RegistrationLog> getRegistrationLogbyId(long groupId,long registrationId) throws PortalException {
		return RegistrationLogLocalServiceUtil.getRegistrationLogbyRegId(groupId, registrationId);

	}

	@Override
	public RegistrationLog addRegistrationLogById(long groupId, long registrationId, String author, String content,
			String payload, ServiceContext serviceContext) {
		// TODO Auto-generated method stub
		return RegistrationLogLocalServiceUtil.addLog(author, groupId, registrationId, registrationId, content, payload);
	}
}