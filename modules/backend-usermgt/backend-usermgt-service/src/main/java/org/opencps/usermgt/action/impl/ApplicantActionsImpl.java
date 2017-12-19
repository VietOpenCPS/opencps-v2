package org.opencps.usermgt.action.impl;

import java.util.LinkedHashMap;

import org.opencps.usermgt.action.ApplicantActions;
import org.opencps.usermgt.model.Applicant;
import org.opencps.usermgt.service.ApplicantLocalServiceUtil;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.search.Hits;
import com.liferay.portal.kernel.search.SearchContext;
import com.liferay.portal.kernel.search.Sort;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;

public class ApplicantActionsImpl implements ApplicantActions {

	@Override
	public Applicant register(ServiceContext context, long groupId, String applicantName, String applicantIdType,
			String applicantIdNo, String applicantIdDate, String contactEmail, String password)
			throws PortalException, SystemException {

		Applicant applicant = ApplicantLocalServiceUtil.updateApplication(context,groupId, 0, applicantName, applicantIdType,
				applicantIdNo, applicantIdDate, StringPool.BLANK, StringPool.BLANK, StringPool.BLANK, StringPool.BLANK,
				StringPool.BLANK, StringPool.BLANK, StringPool.BLANK, StringPool.BLANK, StringPool.BLANK, contactEmail,
				StringPool.BLANK, password);

		return applicant;
	}

	public Applicant register(ServiceContext context, long groupId,  String applicantName, String applicantIdType,
			String applicantIdNo, String applicantIdDate, String contactEmail, String address, String cityCode,
			String cityName, String districtCode, String districtName, String wardCode, String wardName,
			String contactName, String contactTelNo, String password) throws PortalException, SystemException {

		Applicant applicant = ApplicantLocalServiceUtil.updateApplication(context, groupId, 0l, applicantName, applicantIdType,
				applicantIdNo, applicantIdDate, address, cityCode, cityName, districtCode, districtName, wardCode,
				wardName, contactName, contactTelNo, contactEmail, StringPool.BLANK, password);

		return applicant;
	}

	@Override
	public Applicant removeApplicant(ServiceContext context, long applicantId) throws PortalException {

		return ApplicantLocalServiceUtil.removeApplicant(applicantId);
	}

	@Override
	public JSONObject getApplicants(ServiceContext context, long userId, long companyId, long groupId,
			LinkedHashMap<String, Object> params, Sort[] sorts, int start, int end, ServiceContext serviceContext) {

		JSONObject result = JSONFactoryUtil.createJSONObject();
		Hits hits = null;
		SearchContext searchContext = new SearchContext();
		searchContext.setCompanyId(companyId);

		try {

			hits = ApplicantLocalServiceUtil.searchLucene(params, sorts, start, end, searchContext);

			result.put("data", hits.toList());

			long total = ApplicantLocalServiceUtil.countLucene(params, searchContext);

			result.put("total", total);

		} catch (Exception e) {
			_log.error(e);
		}

		return result;

	}

	@Override
	public Applicant getApplicantDetail(ServiceContext context, long applicantId) throws PortalException {
		return ApplicantLocalServiceUtil.getApplicant(applicantId);
	}

	@Override
	public String getApplicantByUserId(ServiceContext serviceContext) throws PortalException {
		return JSONFactoryUtil.looseSerialize(ApplicantLocalServiceUtil.fetchByMappingID(serviceContext.getUserId()));
	}
	
	@Override
	public Applicant updateApplicant(ServiceContext context,long groupId, long applicantId, String applicantName, String address, String cityCode,
			String cityName, String districtCode, String districtName, String wardCode, String wardName,
			String contactName, String contactTelNo, String contactEmail) throws PortalException {

		Applicant applicant = ApplicantLocalServiceUtil.updateApplication(context, groupId,  applicantId, applicantName,
				StringPool.BLANK, StringPool.BLANK, StringPool.BLANK, address, cityCode, cityName, districtCode,
				districtName, wardCode, wardName, contactName, contactTelNo, contactEmail, StringPool.BLANK,
				StringPool.BLANK);

		return applicant;
	}

	@Override
	public Applicant updateProfile(ServiceContext context, long groupId, long applicantId, String profile) throws PortalException {
		// TODO Auto-generated method stub
		Applicant applicant = ApplicantLocalServiceUtil.updateApplication(context, groupId, applicantId, StringPool.BLANK,
				StringPool.BLANK, StringPool.BLANK, StringPool.BLANK, StringPool.BLANK, StringPool.BLANK,
				StringPool.BLANK, StringPool.BLANK, StringPool.BLANK, StringPool.BLANK, StringPool.BLANK,
				StringPool.BLANK, StringPool.BLANK, StringPool.BLANK, profile, StringPool.BLANK);

		return applicant;
	}

	@Override
	public Applicant removeProfile(ServiceContext context, long applicantId) throws PortalException {
		// TODO Auto-generated method stub
		return ApplicantLocalServiceUtil.removeProfile(applicantId);
	}

	@Override
	public Applicant lockApplicant(ServiceContext context, long applicantId) throws PortalException {
		Applicant applicant = ApplicantLocalServiceUtil.lockoutApplicant(applicantId);
		return applicant;
	}

	@Override
	public Applicant activationApplicant(ServiceContext context, long applicantId, String activationCode)
			throws PortalException {

		Applicant applicant = ApplicantLocalServiceUtil.getApplicant(applicantId);

		if (Validator.isNotNull(applicant.getActivationCode())
				&& applicant.getActivationCode().toLowerCase().contentEquals(activationCode.toLowerCase())) {
			return ApplicantLocalServiceUtil.activateApplicant(applicantId, context);
		} else {
			return null;
		}

	}

	Log _log = LogFactoryUtil.getLog(ApplicantActionsImpl.class);

}
