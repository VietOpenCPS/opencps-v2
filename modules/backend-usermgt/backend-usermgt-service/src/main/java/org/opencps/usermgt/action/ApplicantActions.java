package org.opencps.usermgt.action;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.model.Company;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.search.Sort;
import com.liferay.portal.kernel.service.ServiceContext;
import javax.ws.rs.core.HttpHeaders;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.opencps.usermgt.model.Applicant;

public interface ApplicantActions {
	@Deprecated
	public Applicant register(ServiceContext context, long groupId, String applicantName, String applicantIdType, String applicantIdNo,
			String applicantIdDate, String contactEmail, String password) throws PortalException, SystemException;
	
	public Applicant register(ServiceContext context, long groupId, String applicantName, String applicantIdType,
			String applicantIdNo, String applicantIdDate, String contactEmail, String address, String cityCode,
			String cityName, String districtCode, String districtName, String wardCode, String wardName,
			String contactName, String contactTelNo, String profile, String password) throws PortalException, SystemException;

	public Applicant removeApplicant(ServiceContext context, long applicantId)
			throws PortalException;

	public JSONObject getApplicants(ServiceContext context, long userId, long companyId, long groupId, LinkedHashMap<String, Object> params,
			Sort[] sorts, int start, int end, ServiceContext serviceContext);

	public Applicant getApplicantDetail(ServiceContext context, long applicantId)
			throws PortalException;
	
	public String getApplicantByUserId(ServiceContext serviceContext) throws PortalException;
	
	public Applicant updateApplicant(ServiceContext context,long groupId, long applicantId, String applicantName, String address, String cityCode, String cityName, String districtCode,
			String districtName, String wardCode, String wardName, String contactName, String contactTelNo,
			String contactEmail)
			throws PortalException;

	public Applicant updateApplicant(ServiceContext context,long groupId, long applicantId, String applicantName, String address, String cityCode, String cityName, String districtCode,
			String districtName, String wardCode, String wardName, String contactName, String contactTelNo,
			String contactEmail, String profile)
			throws PortalException;

	public Applicant updateProfile(ServiceContext context, long groupId, long applicantId, String profile)
			throws PortalException;

	public Applicant removeProfile(ServiceContext context, long applicantId)
			throws PortalException;

	public Applicant activationApplicant(ServiceContext context, long applicantId, String activationCode)
			throws PortalException;

	public Applicant activationLGSPApplicant(ServiceContext context, long applicantId, String activationCode)
			throws PortalException;
	
	public Applicant lockApplicant(ServiceContext context, long applicantId)
			throws PortalException;

	public Applicant getApplicantByMappingUserId(long userId) throws PortalException;

	public void updateApplicantDB(long userId, long groupId, String applicantIdNo, String appliantName,
			String applicantIdType, Date applicantIdDate, String contactEmail, String contactTelNo,
			ServiceContext serviceContext) throws PortalException;

	public Applicant registerApproved(ServiceContext context, long groupId, String applicantName, String applicantIdType,
			String applicantIdNo, String applicantIdDate, String contactEmail, String address, String cityCode,
			String cityName, String districtCode, String districtName, String wardCode, String wardName,
			String contactName, String contactTelNo, String profile, String password) throws PortalException, SystemException;

	public JSONObject createApplicantAccount(long userId, long companyId, long groupId, long id, String screenName,
			String email, boolean exist, ServiceContext serviceContext) throws PortalException;

	public void importApplicantDB(long userId, long groupId, String applicantIdNo, String appliantName,
			String applicantIdType, String applicantIdDate, String contactEmail, String contactTelNo, String address,
			String cityCode, String districtCode, String wardCode, String contactName,
			String profile, boolean lgsp, ServiceContext serviceContext) throws PortalException;

public String getSimpleCaptcha(HttpServletRequest request, HttpHeaders header, Company company, Locale locale,
			User user, ServiceContext serviceContext, Integer width, Integer height);

	public boolean validateSimpleCaptcha(HttpServletRequest request, HttpHeaders header, Company company, Locale locale,
			User user, ServiceContext serviceContext, String value);

	public Applicant verifyApplicant(long applicantId) throws PortalException;
	public JSONObject updateAccountEmail(HttpServletRequest request, HttpHeaders header, Company company, Locale locale,
			User user, ServiceContext serviceContext, String oldEmail, String newEmail);

}
