package org.opencps.datamgt.action;

import java.util.Date;
import java.util.LinkedHashMap;

import org.opencps.datamgt.model.Holiday;

import com.liferay.portal.kernel.exception.NoSuchUserException;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.search.Sort;
import com.liferay.portal.kernel.service.ServiceContext;

import org.opencps.auth.api.exception.NotFoundException;
import org.opencps.auth.api.exception.UnauthenticationException;
import org.opencps.auth.api.exception.UnauthorizationException;

public interface HolidayInterface {

	public boolean delete(long userId, long groupId, long companyId, String day, ServiceContext serviceContext)
			throws NotFoundException, UnauthenticationException, UnauthorizationException;
	
	public JSONObject getHolidays(long userId, long companyId, long groupId, LinkedHashMap<String, Object> params,
			Sort[] sorts, int start, int end, ServiceContext serviceContext);
	
	public Holiday read(long userId, long groupId, long companyId, String day, ServiceContext serviceContext);
	
	public Holiday create(long userId, long groupId, String holidayDate, String description,
			ServiceContext serviceContext)
			throws NoSuchUserException, UnauthenticationException, UnauthorizationException;
	
	public Holiday update(long userId, long groupId, String day, String holidayDate, String description,
			ServiceContext serviceContext)
			throws NoSuchUserException, NotFoundException, UnauthenticationException, UnauthorizationException;

	public void updateHolidayDB(long userId, long groupId, Date time, String description, int holidayType)
			throws NoSuchUserException;
}
