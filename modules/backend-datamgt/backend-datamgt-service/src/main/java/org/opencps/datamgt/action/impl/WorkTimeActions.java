package org.opencps.datamgt.action.impl;

import java.util.LinkedHashMap;

import org.opencps.datamgt.action.WorkTimeInterface;
import org.opencps.datamgt.model.WorkTime;
import org.opencps.datamgt.service.WorkTimeLocalServiceUtil;

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
import com.liferay.portal.kernel.util.Validator;

import org.opencps.auth.api.exception.NotFoundException;
import org.opencps.auth.api.exception.UnauthenticationException;
import org.opencps.auth.api.exception.UnauthorizationException;

public class WorkTimeActions implements WorkTimeInterface {

	public Log _log = LogFactoryUtil.getLog(WorkTimeActions.class);

	@Override
	public boolean delete(long userId, long groupId, long companyId, int n, ServiceContext serviceContext)
			throws NotFoundException, UnauthenticationException, UnauthorizationException {
		boolean flag = false;

		WorkTime workTime = WorkTimeLocalServiceUtil.fetchByF_day(groupId, n);

		if (Validator.isNotNull(workTime)) {

			WorkTimeLocalServiceUtil.deleteWorkTime(workTime.getWorkTimeId(), serviceContext);

			flag = true;

		}

		return flag;
	}

	@Override
	public JSONObject getWorkTimes(long userId, long companyId, long groupId, LinkedHashMap<String, Object> params,
			Sort[] sorts, int start, int end, ServiceContext serviceContext) {
		JSONObject result = JSONFactoryUtil.createJSONObject();
		Hits hits = null;
		SearchContext searchContext = new SearchContext();
		searchContext.setCompanyId(companyId);

		try {

			hits = WorkTimeLocalServiceUtil.luceneSearchEngine(params, sorts, start, end, searchContext);

			result.put("data", hits.toList());

			long total = WorkTimeLocalServiceUtil.countLuceneSearchEngine(params, searchContext);

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

	@Override
	public WorkTime read(long userId, long groupId, long companyId, int n, ServiceContext serviceContext) {
		WorkTime workTime = WorkTimeLocalServiceUtil.fetchByF_day(groupId, n);

		return workTime;
	}

	@Override
	public WorkTime create(long userId, long groupId, int n, String hours, ServiceContext serviceContext)
			throws NoSuchUserException, UnauthenticationException, UnauthorizationException {
		WorkTime ett = null;

		ett = WorkTimeLocalServiceUtil.addWorkTime(userId, groupId, n, hours, serviceContext);

		return ett;
	}

	@Override
	public WorkTime update(long userId, long groupId, int n, String hours, ServiceContext serviceContext)
			throws NoSuchUserException, NotFoundException, UnauthenticationException, UnauthorizationException {
		WorkTime workTime = WorkTimeLocalServiceUtil.fetchByF_day(groupId, n);

		if (Validator.isNotNull(workTime)) {

			workTime.setHours(hours);

		}

		workTime = WorkTimeLocalServiceUtil.updateWorkTime(userId, workTime.getWorkTimeId(), workTime.getDay(),
				workTime.getHours(), serviceContext);

		return workTime;
	}

	@Override
	public void updateWorkTimeDB(long userId, long groupId, int workTimeDay, String workTimeHours) throws NoSuchUserException {

		WorkTimeLocalServiceUtil.updateWorkTimeDB(userId, groupId, workTimeDay, workTimeHours);
	}

}
