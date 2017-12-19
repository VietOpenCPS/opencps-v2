package org.opencps.dossiermgt.action;

import java.util.List;

import org.opencps.dossiermgt.model.Registration;
import org.opencps.dossiermgt.model.RegistrationForm;
import org.opencps.dossiermgt.model.RegistrationLog;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.service.ServiceContext;

public interface RegistrationActions {
	public Registration update(Registration model) throws PortalException;

	public void delete(long registrationId) throws PortalException;

	public Registration getDetail(long registrationId) throws PortalException;

	public Registration insert(long groupid, String applicantName, String applicantIdType, String applicantIdNo,
			String applicantIdDate, String address, String cityCode, String cityName, String districtCode,
			String districtName, String wardCode, String wardName, String contactName, String contactTelNo,
			String contactEmail, String govAgencyCode, String govAgencyName, int registrationState,
			String registrationClass, ServiceContext serviceContext) throws SystemException, PortalException;
	
	public RegistrationLog addLog(String author, long groupId, long userId, long registrationId, String content, List<RegistrationForm> payload);
}