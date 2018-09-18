package org.opencps.dossiermgt.action.impl;

import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.search.SearchContext;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.Validator;

import java.util.LinkedHashMap;

import org.opencps.dossiermgt.action.StatisticActions;
import org.opencps.dossiermgt.constants.DossierTerm;
import org.opencps.dossiermgt.service.DossierLocalServiceUtil;

public class StatisticActionsImpl implements StatisticActions {

	private static Log _log = LogFactoryUtil.getLog(StatisticActionsImpl.class);
	@Override
	public long countTodoTest(long userId, long companyId, long groupId, LinkedHashMap<String, Object> params,
			Object object, ServiceContext serviceContext) {

		SearchContext searchContext = new SearchContext();
		searchContext.setCompanyId(companyId);
		String statusCode = StringPool.BLANK;
//		JSONArray statistics = JSONFactoryUtil.createJSONArray();
		long total = 0;
		try {
			statusCode = GetterUtil.getString(params.get(DossierTerm.STATUS));
//			_log.info("statusCode: "+statusCode);
			if (Validator.isNotNull(statusCode)) {
				total = DossierLocalServiceUtil.countLucene(params, searchContext);
//				_log.info("total: "+total);
			}
		} catch (Exception e) {
			_log.debug(e);
			//_log.error(e);
		}

		return total;
	}

}
