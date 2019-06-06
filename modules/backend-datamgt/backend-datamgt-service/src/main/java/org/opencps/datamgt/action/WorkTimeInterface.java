package org.opencps.datamgt.action;

import java.util.LinkedHashMap;

import org.opencps.datamgt.model.WorkTime;

import com.liferay.portal.kernel.exception.NoSuchUserException;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.search.Sort;
import com.liferay.portal.kernel.service.ServiceContext;

import org.opencps.auth.api.exception.NotFoundException;
import org.opencps.auth.api.exception.UnauthenticationException;
import org.opencps.auth.api.exception.UnauthorizationException;

public interface WorkTimeInterface {

	public boolean delete(long userId, long groupId, long companyId, int n, ServiceContext serviceContext)
			throws NotFoundException, UnauthenticationException, UnauthorizationException;
	
	public JSONObject getWorkTimes(long userId, long companyId, long groupId, LinkedHashMap<String, Object> params,
			Sort[] sorts, int start, int end, ServiceContext serviceContext);
	
	public WorkTime read(long userId, long groupId, long companyId, int n, ServiceContext serviceContext);
	
	public WorkTime create(long userId, long groupId, int n, String hours,
			ServiceContext serviceContext)
			throws NoSuchUserException, UnauthenticationException, UnauthorizationException;
	
	public WorkTime update(long userId, long groupId, int n, String hours,
			ServiceContext serviceContext)
			throws NoSuchUserException, NotFoundException, UnauthenticationException, UnauthorizationException;

	public void updateWorkTimeDB(long userId, long groupId, int workTimeDay, String workTimeHours) throws NoSuchUserException;
}
