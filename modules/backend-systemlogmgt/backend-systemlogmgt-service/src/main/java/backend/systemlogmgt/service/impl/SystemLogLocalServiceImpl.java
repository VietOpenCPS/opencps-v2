/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package backend.systemlogmgt.service.impl;

import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.exception.PortalException;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


import backend.systemlogmgt.model.SystemLog;
import backend.systemlogmgt.service.base.SystemLogLocalServiceBaseImpl;
import backend.systemlogmgt.service.persistence.impl.SystemLogPersistenceImpl;
import backend.systemlogmgt.service.util.DatetimeUtil;
/**
 * The implementation of the system log local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link backend.systemlogmgt.service.SystemLogLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see SystemLogLocalServiceBaseImpl
 * @see backend.systemlogmgt.service.SystemLogLocalServiceUtil
 */
public class SystemLogLocalServiceImpl extends SystemLogLocalServiceBaseImpl {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this class directly. Always use {@link backend.systemlogmgt.service.SystemLogLocalServiceUtil} to access the system log local service.
	 */

	public SystemLog createSystemLog(long groupId, String className, String moduleName, String message, String type, int line) {
		Date createDate = new Date();
		long logId = counterLocalService.increment(SystemLog.class.getName());
		SystemLog systemLog = systemLogLocalService.createSystemLog(logId);
		systemLog.setGroupId(groupId);
		systemLog.setCreateDate(createDate);
		systemLog.setClassName(className);
		systemLog.setModuleName(moduleName);
		systemLog.setMessage(message);
		systemLog.setType(type);
		systemLog.setLine(line);
		systemLogLocalService.updateSystemLog(systemLog);
		return systemLog;
	}
	public SystemLog updateSystemLog(long logId, long groupId, String className, String moduleName, String message, String type, int line) throws PortalException {
		SystemLog systemLog = systemLogLocalService.getSystemLog(logId);
		systemLog.setGroupId(groupId);
		systemLog.setClassName(className);
		systemLog.setModuleName(moduleName);
		systemLog.setMessage(message);
		systemLog.setType(type);
		systemLog.setLine(line);
		systemLogLocalService.updateSystemLog(systemLog);
		return systemLog;
	}
	public SystemLog deleteSystemLog(long logId) throws PortalException {
		return systemLogLocalService.deleteSystemLog(logId);
	}
	public SystemLog getSystemLogByLogId(long logId) throws PortalException {
		return systemLogLocalService.getSystemLog(logId);
	}
	public List<SystemLog> getAllSystemLog(){
		return systemLogLocalService.getAllSystemLog();
	}
	public List<SystemLog> getSystemLogs(int start, int end){
		return systemLogLocalService.getSystemLogs(start, end);
	}
	public List<SystemLog> getSystemLogsByGroupId(long groupId){
		return systemLogPersistence.findByGroupId(groupId);
	}
	public List<SystemLog> getSystemLogsFromDateToDate(Date fromDate, Date toDate) throws ParseException{
		int count = systemLogLocalService.getSystemLogsCount();
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy"); 
		fromDate = dateFormat.parse(dateFormat.format(fromDate));
		toDate = dateFormat.parse(dateFormat.format(toDate));
		List<SystemLog> listSystemLog = systemLogLocalService.getSystemLogs(0, count);
		List<SystemLog> result = new ArrayList<SystemLog>();
		for (SystemLog systemLog : listSystemLog) {
			if(dateFormat.parse(dateFormat.format(systemLog.getCreateDate())).compareTo(fromDate) >= 0 && dateFormat.parse(dateFormat.format(systemLog.getCreateDate())).compareTo(toDate)<=0) {
				result.add(systemLog);
			}
		}
		return result;
	}
	public List<SystemLog> getSystemLogsByClassName(String className){
		return systemLogPersistence.findByClassName(className);
	}
	public List<SystemLog> getSystemLogsByModuleName(String moduleName){
		return systemLogPersistence.findByModuleName(moduleName);
	}
	public List<SystemLog> getSystemLogsByType(String type){
		return systemLogPersistence.findByType(type);
	}
	public List<SystemLog> getSystemLogsByLine(int line){
		return systemLogPersistence.findByLine(line);
	}
	public List<SystemLog> getSystemLogsByDate(long fromDate, long toDate) throws ParseException{
		String strFromDate = DatetimeUtil.convertTimestampToStringDatetime(fromDate, DatetimeUtil._YYYY_MM_DD);
		String strToDate = DatetimeUtil.convertTimestampToStringDatetime(toDate, DatetimeUtil._YYYY_MM_DD);
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");  
		Date fromdate = dateFormat.parse(strFromDate);
		Date todate = dateFormat.parse(strToDate);
		List<SystemLog> result1 = systemLogPersistence.findByCreateDateGreater(fromdate);
		List<SystemLog> result2 = new ArrayList<SystemLog>();
		for (SystemLog systemLog : result1) {
			if(dateFormat.parse(dateFormat.format(systemLog.getCreateDate())).compareTo(fromdate) >= 0 && dateFormat.parse(dateFormat.format(systemLog.getCreateDate())).compareTo(todate)<=0) {
				result2.add(systemLog);
			}
		}
		return result2;
	}
	
	
}