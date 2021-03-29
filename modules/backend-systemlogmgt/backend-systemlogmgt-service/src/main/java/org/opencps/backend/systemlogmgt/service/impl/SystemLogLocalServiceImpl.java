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

import java.util.Date;
import java.util.List;

import org.opencps.backend.systemlogmgt.model.SystemLog;
import org.opencps.backend.systemlogmgt.service.SystemLogLocalServiceUtil;
import org.opencps.backend.systemlogmgt.service.base.SystemLogLocalServiceBaseImpl;
import org.opencps.backend.systemlogmgt.service.persistence.SystemLogPersistence;
import org.opencps.backend.systemlogmgt.service.persistence.impl.SystemLogPersistenceImpl;
import org.opencps.backend.systemlogmgt.util.ParamUtil;

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
	
	public List<SystemLog> getSystemLogByDynamicQuery(Long logId, String groupId, String moduleName, String createDate, Integer preLine, String preMethod, Integer line, String method, String message, String type, String threadId){
		
		ClassLoader classLoader = getClass().getClassLoader();
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(SystemLog.class, classLoader);

		if(Validator.isNotNull(logId)) {
			dynamicQuery.add(PropertyFactoryUtil.forName("logId").eq(logId));
		}
		if(Validator.isNotNull(groupId)) {
			Long groupIdLong = Long.parseLong(groupId);
			dynamicQuery.add(RestrictionsFactoryUtil.eq("groupId", groupIdLong));
		}
		if(Validator.isNotNull(createDate)) {
			dynamicQuery.add(RestrictionsFactoryUtil.eq("createDate", createDate));
		}
		if(Validator.isNotNull(moduleName)) {
			dynamicQuery.add(RestrictionsFactoryUtil.eq("moduleName", moduleName));
		}
		if(Validator.isNotNull(preLine)) {
			dynamicQuery.add(RestrictionsFactoryUtil.eq("preLine", preLine));
		}
		if(Validator.isNotNull(preMethod)) {
			dynamicQuery.add(RestrictionsFactoryUtil.eq("preMethod", preMethod));
		}
		if(Validator.isNotNull(line)) {
			dynamicQuery.add(RestrictionsFactoryUtil.eq("line", line));
		}
		if(Validator.isNotNull(method)) {
			dynamicQuery.add(RestrictionsFactoryUtil.eq("method", method));
		}
		if(Validator.isNotNull(message)) {
			dynamicQuery.add(RestrictionsFactoryUtil.like("message", message));
		}
		if(Validator.isNotNull(type)) {
			dynamicQuery.add(RestrictionsFactoryUtil.eq("type", type));
		}
		if(Validator.isNotNull(threadId)) {
			dynamicQuery.add(RestrictionsFactoryUtil.eq("threadId", threadId));
		}
		List<SystemLog> result = systemLogLocalService.dynamicQuery(dynamicQuery);
		
		
		return result;
	}

}