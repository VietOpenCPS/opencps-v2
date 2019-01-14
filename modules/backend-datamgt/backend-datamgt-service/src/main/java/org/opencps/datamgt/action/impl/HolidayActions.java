package org.opencps.datamgt.action.impl;

import java.util.Date;
import java.util.LinkedHashMap;

import org.opencps.datamgt.action.HolidayInterface;
import org.opencps.datamgt.model.Holiday;
import org.opencps.datamgt.service.HolidayLocalServiceUtil;
import org.opencps.datamgt.utils.DateTimeUtils;

import com.liferay.portal.kernel.exception.NoSuchUserException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.search.Hits;
import com.liferay.portal.kernel.search.ParseException;
import com.liferay.portal.kernel.search.SearchContext;
import com.liferay.portal.kernel.search.SearchException;
import com.liferay.portal.kernel.search.Sort;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.Validator;

import org.opencps.auth.api.exception.NotFoundException;
import org.opencps.auth.api.exception.UnauthenticationException;
import org.opencps.auth.api.exception.UnauthorizationException;

public class HolidayActions implements HolidayInterface {

	public Log _log = LogFactoryUtil.getLog(HolidayActions.class);

	public boolean delete(long userId, long groupId, long companyId, String day, ServiceContext serviceContext)
			throws NotFoundException, UnauthenticationException, UnauthorizationException {
		boolean flag = false;

		Holiday holiday = HolidayLocalServiceUtil.fetchByF_holidayDate(groupId,
				new Date(GetterUtil.getLong(day)));

		if (Validator.isNotNull(holiday)) {

			HolidayLocalServiceUtil.deleteHoliday(holiday.getHolidayId(), serviceContext);
			flag = true;

		}

		return flag;
	}

	public JSONObject getHolidays(long userId, long companyId, long groupId, LinkedHashMap<String, Object> params,
			Sort[] sorts, int start, int end, ServiceContext serviceContext) {
		JSONObject result = JSONFactoryUtil.createJSONObject();
		Hits hits = null;
		SearchContext searchContext = new SearchContext();
		searchContext.setCompanyId(companyId);

		try {

			hits = HolidayLocalServiceUtil.luceneSearchEngine(params, sorts, start, end, searchContext);

			result.put("data", hits.toList());

			long total = HolidayLocalServiceUtil.countLuceneSearchEngine(params, searchContext);

			result.put("total", total);

		} catch (ParseException e) {
			_log.debug(e);
			//_log.error(e);
		} catch (SearchException e) {
			_log.debug(e);
			//_log.error(e);
		}

		return result;
	}

	public Holiday read(long userId, long groupId, long companyId, String day, ServiceContext serviceContext) {
		Holiday holiday = HolidayLocalServiceUtil.fetchByF_holidayDate(groupId,
				DateTimeUtils.convertStringToDateAPI(day));

		return holiday;
	}

	public Holiday create(long userId, long groupId, String holidayDate, String description,
			ServiceContext serviceContext)
			throws NoSuchUserException, UnauthenticationException, UnauthorizationException {
		Holiday ett = null;

		ett = HolidayLocalServiceUtil.addHoliday(userId, groupId, new Date(GetterUtil.getLong(holidayDate)),
				description, serviceContext);

		return ett;
	}

	public Holiday update(long userId, long groupId, String day, String holidayDate, String description,
			ServiceContext serviceContext)
			throws NoSuchUserException, NotFoundException, UnauthenticationException, UnauthorizationException {
		Holiday holiday = HolidayLocalServiceUtil.fetchByF_holidayDate(groupId,
				new Date(GetterUtil.getLong(day)));

		if(Validator.isNotNull(holidayDate)){
			
			holiday.setHolidayDate(new Date(GetterUtil.getLong(holidayDate)));
			
		}
				
		if(Validator.isNotNull(description)){
			
			holiday.setDescription(description);
			
		}
			
		holiday = HolidayLocalServiceUtil.updateHoliday(userId, holiday.getHolidayId(), holiday.getHolidayDate(),
				holiday.getDescription(), serviceContext);

		return holiday;
	}

	@Override
	public void updateHolidayDB(long userId, long groupId, Date holidayDate, String description, int holidayType)
			throws NoSuchUserException {
		HolidayLocalServiceUtil.updateHolidayDB(userId, groupId, holidayDate, description, holidayType);
	}

}
