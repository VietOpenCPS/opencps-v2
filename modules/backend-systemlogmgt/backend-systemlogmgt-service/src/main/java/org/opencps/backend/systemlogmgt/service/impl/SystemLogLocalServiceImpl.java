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
import com.liferay.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.liferay.portal.kernel.dao.orm.OrderFactoryUtil;
import com.liferay.portal.kernel.dao.orm.RestrictionsFactoryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.util.Validator;

import java.util.Date;
import java.util.List;

import org.opencps.backend.systemlogmgt.model.SystemLog;
import org.opencps.backend.systemlogmgt.service.SystemLogLocalServiceUtil;
import org.opencps.backend.systemlogmgt.service.base.SystemLogLocalServiceBaseImpl;

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
	public SystemLog addSystemLog(Long groupId, String moduleName, Integer preLine, String preMethod, Integer line, String method, String message, String type, String threadId, String param) {
		Date createDate = new Date();
		long logId = counterLocalService.increment(SystemLog.class.getName());
		SystemLog systemLog = systemLogPersistence.create(logId);
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
		systemLog.setParam(param);
		SystemLogLocalServiceUtil.updateSystemLog(systemLog);
		return systemLog;
	}
	public SystemLog deleteSystemLog(Long logId) throws PortalException {
		return SystemLogLocalServiceUtil.deleteSystemLog(logId);
	}
	public List<SystemLog> getSystemLogByDynamicQuery(Long logId, Long groupId, String moduleName, String method, String threadId, Date fromDate, Date toDate){
		
		ClassLoader classLoader = getClass().getClassLoader();
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(SystemLog.class, classLoader);
		if(Validator.isNotNull(logId)) {
			dynamicQuery.add(RestrictionsFactoryUtil.eq("logId", logId));
		}
		if(Validator.isNotNull(groupId)) {
			dynamicQuery.add(RestrictionsFactoryUtil.eq("groupId", groupId));
		}
		if(Validator.isNotNull(moduleName)) {
			dynamicQuery.add(RestrictionsFactoryUtil.eq("moduleName", moduleName));
		}
		if(Validator.isNotNull(method)) {
			dynamicQuery.add(RestrictionsFactoryUtil.eq("method", method));
		}
		if(Validator.isNotNull(threadId)) {
			dynamicQuery.add(RestrictionsFactoryUtil.eq("threadId", threadId));
		} 
		if(Validator.isNotNull(fromDate)) {
			dynamicQuery.add(RestrictionsFactoryUtil.ge("createDate", fromDate));
		}
		if(Validator.isNotNull(toDate)) {
			dynamicQuery.add(RestrictionsFactoryUtil.le("createDate", toDate));
		}
		dynamicQuery.addOrder(OrderFactoryUtil.asc("logId"));
		List<SystemLog> result = systemLogPersistence.findWithDynamicQuery(dynamicQuery);
		return result;
	}
}