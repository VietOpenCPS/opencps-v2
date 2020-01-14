package backend.api.rest.application.v21.impl;

import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

import java.util.ArrayList;
import java.util.List;

import org.opencps.rest.application.model.DossierStatisticModel;

import backend.api.rest.application.utils.ConstantTerm;

public class StatisticUtils {

	static Log _log = LogFactoryUtil.getLog(StatisticUtils.class);

	public static List<DossierStatisticModel> mapperStatisticDossierList(JSONArray statistics) {

		List<DossierStatisticModel> results = new ArrayList<DossierStatisticModel>();

		if (statistics != null && statistics.length() > 0) {
			for (int i = 0; i < statistics.length(); i++) {
				JSONObject statistic = statistics.getJSONObject(i);
				DossierStatisticModel ett = new DossierStatisticModel();
				ett.setTotalCount(statistic.getLong(ConstantTerm.JSON_TOTAL_COUNT));
				ett.setDossierStatus(statistic.getString(ConstantTerm.JSON_DOSSIER_STATUS));
				ett.setDossierSubStatus(statistic.getString(ConstantTerm.JSON_DOSSIER_SUB_STATUS));
				ett.setStepCode(statistic.getString(ConstantTerm.JSON_STEP_CODE));
				ett.setStepName(statistic.getString(ConstantTerm.JSON_STEP_NAME));
				results.add(ett);
			}
		}

		return results;
	}

}
