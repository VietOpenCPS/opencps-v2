package org.opencps.usermgt.action;

import java.util.LinkedHashMap;

import org.opencps.usermgt.model.Applicant;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.search.Sort;
import com.liferay.portal.kernel.service.ServiceContext;

public interface ApplicantActions {

	public Applicant register(ServiceContext context, String applicantName, String applicantIdType, String applicantIdNo,
			String applicantIdDate, String contactEmail, String password) throws PortalException, SystemException;

	public Applicant removeApplicant(ServiceContext context, long applicantId)
			throws PortalException;

	public JSONObject getApplicants(ServiceContext context, long userId, long companyId, long groupId, LinkedHashMap<String, Object> params,
			Sort[] sorts, int start, int end, ServiceContext serviceContext);

	public Applicant getApplicantDetail(ServiceContext context, long applicantId)
			throws PortalException;

	public Applicant updateApplicant(ServiceContext context, long applicantId, String address, String cityCode, String cityName, String districtCode,
			String districtName, String wardCode, String wardName, String contactName, String contactTelNo,
			String contactEmail)
			throws PortalException;

	public Applicant updateProfile(ServiceContext context, long applicantId, String profile)
			throws PortalException;

	public Applicant removeProfile(ServiceContext context, long applicantId)
			throws PortalException;

	public Applicant activationApplicant(ServiceContext context, long applicantId, String activationCode)
			throws PortalException;
	
	public Applicant lockApplicant(ServiceContext context, long applicantId)
			throws PortalException;

}
