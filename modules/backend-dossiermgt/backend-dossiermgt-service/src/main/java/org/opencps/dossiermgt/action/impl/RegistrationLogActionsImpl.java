package org.opencps.dossiermgt.action.impl;

import java.util.LinkedHashMap;
import java.util.List;

import org.opencps.dossiermgt.action.RegistrationLogActions;
import org.opencps.dossiermgt.model.RegistrationLog;
import org.opencps.dossiermgt.service.RegistrationLogLocalServiceUtil;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.search.Hits;
import com.liferay.portal.kernel.search.SearchContext;
import com.liferay.portal.kernel.search.Sort;
import com.liferay.portal.kernel.search.SortFactoryUtil;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.GetterUtil;

public class RegistrationLogActionsImpl implements RegistrationLogActions {
	
	Log _log = LogFactoryUtil.getLog(RegistrationActionsImpl.class);

	@Override
	public List<RegistrationLog> getRegistrationLogbyId(long groupId,long registrationId) throws PortalException {
		return RegistrationLogLocalServiceUtil.getRegistrationLogbyRegId(groupId, registrationId);
	}

	@Override
	public RegistrationLog addRegistrationLogById(long groupId, long registrationId, String author, String content,
			String payload, ServiceContext serviceContext) {
		// TODO Auto-generated method stub
		long userId = serviceContext.getUserId();
		return RegistrationLogLocalServiceUtil.addLog(author, groupId, userId, registrationId, content, payload);
	}
	
	//search Lucene
	@Override
	public JSONObject getRegistrationLog(long groupId, long registrationId, int start, int end, Sort[] sort, String order, ServiceContext serviceContext) throws PortalException {
		JSONObject result = JSONFactoryUtil.createJSONObject();

		SearchContext searchContext = new SearchContext();
		searchContext.setCompanyId(serviceContext.getCompanyId());
		try {
			
			if (start == 0) {
				start = -1;
				end = -1;
			} 
			
			LinkedHashMap<String, Object> params = new LinkedHashMap<String, Object>();

			params.put("groupId", String.valueOf(groupId));
			params.put("registrationId", String.valueOf(registrationId));
			
			
			Sort[] sorts = new Sort[] { SortFactoryUtil.create(sort + "_sortable", Sort.STRING_TYPE,
					GetterUtil.getBoolean(order)) };

			Hits hits = RegistrationLogLocalServiceUtil.searchLucene(params, sorts, start, end, searchContext);

			result.put("data", hits.toList());

			long total = RegistrationLogLocalServiceUtil.countLucense(params, sorts, start, end, searchContext);

			result.put("total", total);

		} catch (Exception e) {
			_log.error(e);
		}

		return result;
	}
}