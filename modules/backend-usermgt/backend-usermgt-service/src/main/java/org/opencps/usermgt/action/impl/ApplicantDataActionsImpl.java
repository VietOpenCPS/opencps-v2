package org.opencps.usermgt.action.impl;

import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.search.Hits;
import com.liferay.portal.kernel.search.SearchContext;
import com.liferay.portal.kernel.search.Sort;
import com.liferay.portal.kernel.service.ServiceContext;

import java.util.LinkedHashMap;

import org.opencps.usermgt.action.ApplicantDataActions;
import org.opencps.usermgt.constants.ApplicantDataTerm;
import org.opencps.usermgt.service.ApplicantDataLocalServiceUtil;

public class ApplicantDataActionsImpl implements ApplicantDataActions {

	@Override
	public JSONObject getApplicantDatas(long userId, long companyId, long groupId, LinkedHashMap<String, Object> params,
			Sort[] sorts, int start, int end, ServiceContext serviceContext) {
		JSONObject result = JSONFactoryUtil.createJSONObject();
		Hits hits = null;
		SearchContext searchContext = new SearchContext();
		searchContext.setCompanyId(companyId);

		try {

			hits = ApplicantDataLocalServiceUtil.searchLucene(params, sorts, start, end, searchContext);

			result.put(ApplicantDataTerm.DATA, hits.toList());

			long total = ApplicantDataLocalServiceUtil.countLucene(params, searchContext);

			result.put(ApplicantDataTerm.TOTAL, total);

		} catch (Exception e) {
			_log.error(e);
		}

		return result;
	}

	Log _log = LogFactoryUtil.getLog(ApplicantDataActionsImpl.class);
}
