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

package org.opencps.backend.systemlogmgt.service.impl;

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQueryFactory;
import com.liferay.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.liferay.portal.kernel.dao.orm.Order;
import com.liferay.portal.kernel.dao.orm.OrderFactoryUtil;
import com.liferay.portal.kernel.dao.orm.PropertyFactoryUtil;
import com.liferay.portal.kernel.dao.orm.RestrictionsFactoryUtil;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.portlet.PortletClassLoaderUtil;
import com.liferay.portal.kernel.util.PortalClassLoaderUtil;
import com.liferay.portal.kernel.util.Validator;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.opencps.backend.systemlogmgt.model.SystemLog;
import org.opencps.backend.systemlogmgt.service.SystemLogLocalServiceUtil;
import org.opencps.backend.systemlogmgt.service.base.SystemLogLocalServiceBaseImpl;
import org.opencps.backend.systemlogmgt.service.persistence.SystemLogPersistence;
import org.opencps.backend.systemlogmgt.service.persistence.impl.SystemLogPersistenceImpl;
import org.opencps.backend.systemlogmgt.service.util.ParamUtil;

/**
 * The implementation of the system log local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link org.opencps.backend.systemlogmgt.service.SystemLogLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see SystemLogLocalServiceBaseImpl
 * @see org.opencps.backend.systemlogmgt.service.SystemLogLocalServiceUtil
 */
public class SystemLogLocalServiceImpl extends SystemLogLocalServiceBaseImpl {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this class directly. Always use {@link org.opencps.backend.systemlogmgt.service.SystemLogLocalServiceUtil} to access the system log local service.
	 */
	public SystemLog addNewSystemLog(Long groupId, String moduleName, Integer preLine, String preMethod, Integer line, String method, String message, String type, String threadId) {
		Date createDate = new Date();
		long logId = counterLocalService.increment(SystemLog.class.getName());
		SystemLog systemLog = systemLogLocalService.createSystemLog(logId);
		systemLog.setGroupId(groupId);
		systemLog.setCreateDate(createDate);
		systemLog.setModuleName(moduleName);
		systemLog.setPreLine(preLine);
		systemLog.setPreMethod(preMethod);
		systemLog.setLine(line);
		systemLog.setMethod(method);
		systemLog.setMessage(message);
		systemLog.setType(type);
		systemLog.setThreadId(threadId);
		systemLogLocalService.updateSystemLog(systemLog);
		return systemLog;	
	}
	public SystemLog updateOldSystemLog(Long logId, Long groupId, String moduleName, Integer preLine, String preMethod, Integer line, String method, String message, String type, String threadId) throws PortalException {
		SystemLog systemLog = systemLogLocalService.getSystemLog(logId);
		systemLog.setGroupId(groupId);
		systemLog.setModuleName(moduleName);
		systemLog.setPreLine(preLine);
		systemLog.setPreMethod(preMethod);
		systemLog.setLine(line);
		systemLog.setMethod(method);
		systemLog.setMessage(message);
		systemLog.setType(type);
		systemLog.setThreadId(threadId);
		systemLogLocalService.updateSystemLog(systemLog);
		return systemLog;
	}
	public SystemLog deleteOldSystemLog(long logId) throws PortalException{
		return systemLogLocalService.deleteSystemLog(logId);
	}
	public SystemLog getSystemLogByLogId(long logId) throws PortalException {
		return systemLogLocalService.getSystemLog(logId);
	}
	public List<SystemLog> getSystemLogByMultipleGroupId(long[] groupIds){
		return systemLogPersistence.findBymultipleGroupId(groupIds);
	}
	public List<SystemLog> getSystemLogByMultipleType(String[] types){
		return systemLogPersistence.findBymultipleType(types);
	}
	public List<SystemLog> getSystemLogByMultipleModuleName(String[] moduleNames){
		return systemLogPersistence.findBymultiplemoduleName(moduleNames);
	}
	public List<SystemLog> getSystemLogByMultiplePreMethod(String[] preMethods){
		return systemLogPersistence.findBymultiplePreMethod(preMethods);
	}
	public List<SystemLog> getSystemLogByMultipleMethod(String[] methods){
		return systemLogPersistence.findBymultipleMethod(methods);
	}
	public List<SystemLog> getSystemLogByMultipleThreadId(String[] threadIds){
		return systemLogPersistence.findBymultipleThreadId(threadIds);
	}
	public List<SystemLog> getSystemLogByLikeMessage(String message){
		return systemLogPersistence.findBylikeMessage(message);
	}
	public List<SystemLog> getSystemLogByThreadId(String threadId){
		return systemLogPersistence.findBythreadId(threadId);
	}
	

	public List<SystemLog> getSystemLogByDynamicQuery(String logId, String groupId, String moduleNames, String createDate, Integer preLine, String preMethod, Integer line, String method, String message, String type, String threadId, Date fromDate, Date toDate) throws ParseException{
		
		ClassLoader classLoader = getClass().getClassLoader();
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(SystemLog.class, classLoader);

		if(Validator.isNotNull(logId)) {
			Long logIdLong = Long.parseLong(logId);
			dynamicQuery.add(PropertyFactoryUtil.forName("logId").eq(logIdLong));
		}
		if(Validator.isNotNull(groupId)) {
			Long groupIdLong = Long.parseLong(groupId);
			dynamicQuery.add(RestrictionsFactoryUtil.eq("groupId", groupIdLong));
		}
		if(Validator.isNotNull(createDate)) {
			 Date date1= new SimpleDateFormat("yyyy-MM-dd").parse(createDate);  
			dynamicQuery.add(RestrictionsFactoryUtil.eq("createDate", date1));
		}
		if(Validator.isNotNull(moduleNames)) {
			String[] moduleName = ParamUtil.getArrayParams(moduleNames);
			dynamicQuery.add(RestrictionsFactoryUtil.in("moduleName", moduleName));
		}
		if(Validator.isNotNull(preLine)) {
			dynamicQuery.add(RestrictionsFactoryUtil.eq("preLine", preLine));
		}
		if(Validator.isNotNull(preMethod)) {
			String[] preMethods = ParamUtil.getArrayParams(preMethod);
			dynamicQuery.add(RestrictionsFactoryUtil.eq("preMethod", preMethods));
		}
		if(Validator.isNotNull(line)) {
			dynamicQuery.add(RestrictionsFactoryUtil.eq("line", line));
		}
		if(Validator.isNotNull(method)) {
			String[] methods = ParamUtil.getArrayParams(method);
			dynamicQuery.add(RestrictionsFactoryUtil.in("method", methods));
		}
		if(Validator.isNotNull(message)) {
			dynamicQuery.add(RestrictionsFactoryUtil.like("message", message));
		}
		if(Validator.isNotNull(type)) {
			String[] types = ParamUtil.getArrayParams(type);
			dynamicQuery.add(RestrictionsFactoryUtil.in("type", types));
		}
		if(Validator.isNotNull(threadId)) {
			String[] threadIds = ParamUtil.getArrayParams(threadId);
			dynamicQuery.add(RestrictionsFactoryUtil.in("threadId", threadIds));
		} 
		if(Validator.isNotNull(fromDate)) {
			dynamicQuery.add(RestrictionsFactoryUtil.ge("createDate", fromDate));
		}
		if(Validator.isNotNull(toDate)) {
			dynamicQuery.add(RestrictionsFactoryUtil.le("createDate", toDate));
		}
		
		List<SystemLog> result = systemLogLocalService.dynamicQuery(dynamicQuery);
		return result;
	}
	
	

}