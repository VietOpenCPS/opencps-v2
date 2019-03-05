package backend.api.rest.application.v21.impl;

import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

import java.util.ArrayList;
import java.util.List;

import org.opencps.rest.application.model.DossierStatisticModel;

public class StatisticUtils {

	static Log _log = LogFactoryUtil.getLog(StatisticUtils.class);

	public static List<DossierStatisticModel> mapperStatisticDossierList(JSONArray statistics) {

		List<DossierStatisticModel> results = new ArrayList<DossierStatisticModel>();

		if (statistics != null && statistics.length() > 0) {
			for (int i = 0; i < statistics.length(); i++) {
				JSONObject statistic = statistics.getJSONObject(i);
				DossierStatisticModel ett = new DossierStatisticModel();
				ett.setTotalCount(statistic.getLong("totalCount"));
				ett.setDossierStatus(statistic.getString("dossierStatus"));
				ett.setDossierSubStatus(statistic.getString("dossierSubStatus"));
				ett.setStepCode(statistic.getString("stepCode"));
				ett.setStepName(statistic.getString("stepName"));
				results.add(ett);
			}
		}

		return results;
	}

}
