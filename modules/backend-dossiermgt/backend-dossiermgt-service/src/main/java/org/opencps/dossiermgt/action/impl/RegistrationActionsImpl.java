package org.opencps.dossiermgt.action.impl;

import java.util.List;

import org.opencps.dossiermgt.action.RegistrationActions;
import org.opencps.dossiermgt.model.Registration;
import org.opencps.dossiermgt.model.RegistrationForm;
import org.opencps.dossiermgt.model.RegistrationLog;
import org.opencps.dossiermgt.service.RegistrationLocalServiceUtil;
import org.opencps.dossiermgt.service.RegistrationLogLocalServiceUtil;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.service.ServiceContext;

public class RegistrationActionsImpl implements RegistrationActions {

	@Override
	public Registration insert(long groupId, String applicantName, String applicantIdType, String applicantIdNo,
			String applicantIdDate, String address, String cityCode, String cityName, String districtCode,
			String districtName, String wardCode, String wardName, String contactName, String contactTelNo,
			String contactEmail, String govAgencyCode, String govAgencyName, int registrationState,
			String registrationClass, ServiceContext serviceContext)
			throws SystemException, PortalException {

		return RegistrationLocalServiceUtil.insert(groupId, applicantName, applicantIdType, applicantIdNo, applicantIdDate,
				address, cityCode, cityName, districtCode, districtName, wardCode, wardName, contactName, contactTelNo,
				contactEmail, govAgencyCode, govAgencyName, registrationState, registrationClass,
				serviceContext);
	}

	@Override
	public Registration update(Registration model) throws PortalException {
		Registration registration = RegistrationLocalServiceUtil.getRegistration(model.getRegistrationId());
		
		model.setCreateDate(registration.getCreateDate());
		model.setGroupId(registration.getGroupId());
		model.setUserId(registration.getUserId());
		model.setCompanyId(registration.getCompanyId());
		
		return RegistrationLocalServiceUtil.updateRegistration(model);

	}

	@Override
	public void delete(long registrationId) throws PortalException {
		RegistrationLocalServiceUtil.deleteRegistration(registrationId);

	}

	@Override
	public Registration getDetail(long registrationId) throws PortalException {
		return RegistrationLocalServiceUtil.getRegistration(registrationId);

	}

	@Override
	public RegistrationLog addLog(String author, long groupId, long userId, long registrationId, String content,
			List<RegistrationForm> payload) {
		JSONObject jsonObj = JSONFactoryUtil.createJSONObject();
		jsonObj.put("result", payload);
		String strPayload = jsonObj.toJSONString();
		return RegistrationLogLocalServiceUtil.addLog(author, groupId, userId, registrationId, content, strPayload);
		
		
	}

}