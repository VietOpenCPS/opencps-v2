package org.opencps.usermgt.action;

import java.util.LinkedHashMap;

import org.opencps.auth.api.exception.NotFoundException;
import org.opencps.auth.api.exception.UnauthenticationException;
import org.opencps.auth.api.exception.UnauthorizationException;
import org.opencps.usermgt.model.Applicant;

import com.liferay.portal.kernel.exception.NoSuchUserException;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.search.Sort;
import com.liferay.portal.kernel.service.ServiceContext;

public interface ApplicantActions {

	public Applicant register(ServiceContext context, String applicantName, String applicantIdType, String applicantIdNo,
			String applicantIdDate, String contactEmail, String password) throws PortalException, SystemException;

	public Applicant removeApplicant(ServiceContext context, long applicantId)
			throws NoSuchUserException, NotFoundException, UnauthenticationException, UnauthorizationException;

	public JSONObject getApplicants(ServiceContext context, long userId, long companyId, long groupId, LinkedHashMap<String, Object> params,
			Sort[] sorts, int start, int end, ServiceContext serviceContext);

	public JSONObject getApplicantDetail(ServiceContext context, long applicantId)
			throws NoSuchUserException, NotFoundException, UnauthenticationException, UnauthorizationException;

	public Applicant updateApplicant(ServiceContext context, String address, String cityCode, String cityName, String districtCode,
			String districtName, String wardCode, String wardName, String contactName, String contactTelNo,
			String contactEmail)
			throws NoSuchUserException, NotFoundException, UnauthenticationException, UnauthorizationException;

	public Applicant updateProfile(ServiceContext context, long applicantId, String profile)
			throws NoSuchUserException, NotFoundException, UnauthenticationException, UnauthorizationException;

	public Applicant removeProfile(ServiceContext context, long applicantId)
			throws NoSuchUserException, NotFoundException, UnauthenticationException, UnauthorizationException;

	public Applicant lockApplicant(ServiceContext context, long applicantId)
			throws NoSuchUserException, NotFoundException, UnauthenticationException, UnauthorizationException;

}
