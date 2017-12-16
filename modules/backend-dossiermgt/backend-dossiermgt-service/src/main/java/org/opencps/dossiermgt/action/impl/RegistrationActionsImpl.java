package org.opencps.dossiermgt.action.impl;

import org.opencps.dossiermgt.action.RegistrationActions;
import org.opencps.dossiermgt.model.Registration;
import org.opencps.dossiermgt.service.RegistrationLocalServiceUtil;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.service.ServiceContext;

public class RegistrationActionsImpl implements RegistrationActions {

	@Override
	public Registration insert(long groupId, String applicantName, String applicantIdType, String applicantIdNo,
			String applicantIdDate, String address, String cityCode, String cityName, String districtCode,
			String districtName, String wardCode, String wardName, String contactName, String contactTelNo,
			String contactEmail, String govAgencyCode, String govAgencyName, int registrationState,
			String registrationClass, boolean submitting, ServiceContext serviceContext)
			throws SystemException, PortalException {

		return RegistrationLocalServiceUtil.insert(groupId, applicantName, applicantIdType, applicantIdNo, applicantIdDate,
				address, cityCode, cityName, districtCode, districtName, wardCode, wardName, contactName, contactTelNo,
				contactEmail, govAgencyCode, govAgencyName, registrationState, registrationClass, submitting,
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

}
