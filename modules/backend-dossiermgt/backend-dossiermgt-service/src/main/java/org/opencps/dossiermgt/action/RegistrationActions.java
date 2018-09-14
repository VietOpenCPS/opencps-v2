package org.opencps.dossiermgt.action;

import java.util.LinkedHashMap;
import java.util.List;

import org.opencps.dossiermgt.model.Registration;
import org.opencps.dossiermgt.model.RegistrationForm;
import org.opencps.dossiermgt.model.RegistrationLog;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.search.Sort;
import com.liferay.portal.kernel.service.ServiceContext;

public interface RegistrationActions {
	public Registration delete(long registrationId) throws PortalException;

	public Registration getDetail(long registrationId) throws PortalException;

	public Registration insert(long groupid, long companyId, String applicantName, String applicantIdType,
			String applicantIdNo, String applicantIdDate, String address, String cityCode, String cityName,
			String districtCode, String districtName, String wardCode, String wardName, String contactName,
			String contactTelNo, String contactEmail, String govAgencyCode, String govAgencyName, int registrationState,
			String registrationClass, String representativeEnterprise, ServiceContext serviceContext)
			throws SystemException, PortalException;
	
	public RegistrationLog addLog(String author, long groupId, long userId, long registrationId, String content, List<RegistrationForm> payload);

	public Registration updateRegistration(long groupId, long registrationId, String applicantName,
			String applicantIdType, String applicantIdNo, String applicantIdDate, String address, String cityCode,
			String cityName, String districtCode, String districtName, String wardCode, String wardName,
			String contactName, String contactTelNo, String contactEmail, String govAgencyCode, String govAgencyName,
			int registrationState, String registrationClass, String representativeEnterprise,
			ServiceContext serviceContext) throws PortalException;
	public JSONObject getRegistrations(long userId, long companyId, long groupId, LinkedHashMap<String, Object> params,
			Sort[] sorts, int start, int end, ServiceContext serviceContext);
	
	//TODO: 18
	public JSONObject getFormDataByFormNo(long userId, long companyId, LinkedHashMap<String, Object> params, Sort[] object, int start,
			int end, ServiceContext serviceContext);
}