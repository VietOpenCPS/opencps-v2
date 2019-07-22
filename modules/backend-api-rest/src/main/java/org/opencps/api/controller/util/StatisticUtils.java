package org.opencps.api.controller.util;

import java.util.ArrayList;
import java.util.List;

import org.opencps.api.statistic.model.StatisticCountModel;
import org.opencps.api.statistic.model.StatisticDossierModel;

import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

public class StatisticUtils {

	static Log _log = LogFactoryUtil.getLog(StatisticUtils.class);

	public static List<StatisticDossierModel> mapperStatisticDossierList(JSONArray statistics) {

		List<StatisticDossierModel> results = new ArrayList<StatisticDossierModel>();

		try {
			if (statistics != null && statistics.length() > 0) {
				for (int i = 0; i < statistics.length(); i++) {
					JSONObject statistic = statistics.getJSONObject(i);
					StatisticDossierModel ett = new StatisticDossierModel();
					ett.setTotalCount(statistic.getLong("totalCount"));
					ett.setDossierStatus(statistic.getString("dossierStatus"));
					ett.setDossierSubStatus(statistic.getString("dossierSubStatus"));
					ett.setStepCode(statistic.getString("stepCode"));
					ett.setStepName(statistic.getString("stepName"));
					ett.setMenuGroup(statistic.getString("menuGroup"));
					results.add(ett);
				}
			}

		} catch (Exception e) {
			_log.error(e);
		}

		return results;
	}

	public static List<StatisticCountModel> mapperStatisticDossierCountList(JSONArray statisticArr) {

		List<StatisticCountModel> results = new ArrayList<StatisticCountModel>();

		try {
			if (statisticArr != null && statisticArr.length() > 0) {
				for (int i = 0; i < statisticArr.length(); i++) {
					JSONObject statistic = statisticArr.getJSONObject(i);
					StatisticCountModel statisticCount = new StatisticCountModel();
					statisticCount.setKey(statistic.getString("key"));
					statisticCount.setTitle(statistic.getString("title"));
					statisticCount.setCount(statistic.getLong("count"));
					results.add(statisticCount);
				}
			}

		} catch (Exception e) {
			_log.error(e);
		}

		return results;
	}

}
