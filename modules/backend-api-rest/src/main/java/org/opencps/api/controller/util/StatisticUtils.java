package org.opencps.api.controller.util;

import java.util.ArrayList;
import java.util.List;

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
					ett.setCount(statistic.getLong("count"));
					ett.setDossierStatus(statistic.getString("dossierStatus"));
					ett.setDossierSubStatus(statistic.getString("dossierSubStatus"));
					ett.setLevel(statistic.getInt("level"));
					ett.setStatusName(statistic.getString("statusName"));
					results.add(ett);
				}
			}

		} catch (Exception e) {
			_log.error(e);
		}

		return results;
	}

}
