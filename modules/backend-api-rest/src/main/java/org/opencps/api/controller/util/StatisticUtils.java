package org.opencps.api.controller.util;

import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

import java.util.ArrayList;
import java.util.List;

import org.opencps.api.statistic.model.StatisticCountModel;
import org.opencps.api.statistic.model.StatisticDossierModel;
import org.opencps.dossiermgt.action.util.ConstantUtils;
import org.opencps.dossiermgt.constants.DossierTerm;
import org.opencps.dossiermgt.constants.ProcessStepTerm;

public class StatisticUtils {

	static Log _log = LogFactoryUtil.getLog(StatisticUtils.class);

	public static List<StatisticDossierModel> mapperStatisticDossierList(JSONArray statistics) {

		List<StatisticDossierModel> results = new ArrayList<StatisticDossierModel>();

		try {
			if (statistics != null && statistics.length() > 0) {
				for (int i = 0; i < statistics.length(); i++) {
					JSONObject statistic = statistics.getJSONObject(i);
					StatisticDossierModel ett = new StatisticDossierModel();
					ett.setTotalCount(statistic.getLong(ConstantUtils.VALUE_TOTAL_COUNT));
					ett.setDossierStatus(statistic.getString(DossierTerm.DOSSIER_STATUS));
					ett.setDossierSubStatus(statistic.getString(DossierTerm.DOSSIER_SUB_STATUS));
					ett.setStepCode(statistic.getString(ProcessStepTerm.STEP_CODE));
					ett.setStepName(statistic.getString(ProcessStepTerm.STEP_NAME));
					ett.setMenuGroup(statistic.getString(ConstantUtils.VALUE_MENU_GROUP));
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
					statisticCount.setKey(statistic.getString(ConstantUtils.VALUE_KEY));
					statisticCount.setTitle(statistic.getString(ConstantUtils.VALUE_TITLE));
					statisticCount.setCount(statistic.getLong(ConstantUtils.VALUE_COUNT));
					results.add(statisticCount);
				}
			}

		} catch (Exception e) {
			_log.error(e);
		}

		return results;
	}

}
