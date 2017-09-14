package org.opencps.api.controller.util;

import java.util.ArrayList;
import java.util.List;

import org.opencps.api.worktime.model.WorkTimeModel;
import org.opencps.datamgt.constants.WorkTimeTerm;
import org.opencps.datamgt.model.WorkTime;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.search.Document;

public class WorkTimeUtils {

	public static List<WorkTimeModel> mapperWorkTimeList(List<Document> listDocument) {

		List<WorkTimeModel> results = new ArrayList<>();

		try {

			WorkTimeModel ett = null;

			for (Document document : listDocument) {
				ett = new WorkTimeModel();

				ett.setDay(Integer.valueOf(document.get(WorkTimeTerm.DAY)));
				ett.setHours(document.get(WorkTimeTerm.HOURS));

				results.add(ett);
			}

		} catch (Exception e) {
			_log.error(e);
		}

		return results;
	}

	public static WorkTimeModel mapperWorkTimeModel(WorkTime workTime) {

		WorkTimeModel ett = new WorkTimeModel();

		try {

			ett.setDay(workTime.getDay());
			ett.setHours(workTime.getHours());

		} catch (Exception e) {
			_log.error(e);
		}

		return ett;
	}

	static Log _log = LogFactoryUtil.getLog(WorkTimeUtils.class);
}
