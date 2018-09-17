package org.opencps.api.controller.util;

import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.search.Document;
import com.liferay.portal.kernel.util.Validator;

import java.util.ArrayList;
import java.util.List;

import org.opencps.api.holiday.model.HolidayModel;
import org.opencps.datamgt.constants.HolidayTerm;
import org.opencps.datamgt.model.Holiday;

public class HolidayUtils {

	public static List<HolidayModel> mapperHolidayList(List<Document> listDocument) {

		List<HolidayModel> results = new ArrayList<>();

		try {

			HolidayModel ett = null;

			for (Document document : listDocument) {
				ett = new HolidayModel();

				ett.setHolidayDate(Validator.isNotNull(document.getDate(HolidayTerm.HOLIDAY_DATE)) ? 
						String.valueOf(document.getDate(HolidayTerm.HOLIDAY_DATE).getTime())
						: StringPool.BLANK);
				ett.setDescription(document.get(HolidayTerm.DESCRIPTION));

				results.add(ett);
			}

		} catch (Exception e) {
			_log.error(e);
		}

		return results;
	}

	public static HolidayModel mapperHolidayModel(Holiday holiday) {

		HolidayModel ett = new HolidayModel();

		try {

			ett.setHolidayDate(Validator.isNotNull(holiday.getHolidayDate()) ? 
					String.valueOf(holiday.getHolidayDate().getTime())
					: StringPool.BLANK);
			ett.setDescription(holiday.getDescription());

		} catch (Exception e) {
			_log.error(e);
		}

		return ett;
	}

	static Log _log = LogFactoryUtil.getLog(HolidayUtils.class);
}
