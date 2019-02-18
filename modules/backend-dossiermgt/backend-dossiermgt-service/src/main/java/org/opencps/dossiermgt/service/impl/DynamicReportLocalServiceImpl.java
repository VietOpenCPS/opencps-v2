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

package org.opencps.dossiermgt.service.impl;

import com.liferay.portal.kernel.exception.NoSuchUserException;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.search.Indexable;
import com.liferay.portal.kernel.search.IndexableType;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.Validator;

import java.util.Date;

import org.opencps.communication.model.Notificationtemplate;
import org.opencps.dossiermgt.model.DynamicReport;
import org.opencps.dossiermgt.service.base.DynamicReportLocalServiceBaseImpl;

/**
 * The implementation of the dynamic report local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link org.opencps.dossiermgt.service.DynamicReportLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author huymq
 * @see DynamicReportLocalServiceBaseImpl
 * @see org.opencps.dossiermgt.service.DynamicReportLocalServiceUtil
 */
public class DynamicReportLocalServiceImpl
	extends DynamicReportLocalServiceBaseImpl {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this class directly. Always use {@link org.opencps.dossiermgt.service.DynamicReportLocalServiceUtil} to access the dynamic report local service.
	 */

	//LamTV_Add ouput DB
	@Indexable(type = IndexableType.REINDEX)
	public DynamicReport updateDynamicReportDB(long userId, long groupId, String reportCode, String reportName,
			int sharing, String filterConfig, String tableConfig,
			String userConfig, String reportType) throws NoSuchUserException {

		Date now = new Date();
		User user = userPersistence.findByPrimaryKey(userId);
		
		DynamicReport dynamicReport = dynamicReportPersistence.fetchByF_GID_CODE(groupId, reportCode);

		if (dynamicReport == null) {
			long dynamicReportId = counterLocalService.increment(DynamicReport.class.getName());
			dynamicReport = dynamicReportPersistence.create(dynamicReportId);

			// Group instance
			dynamicReport.setGroupId(groupId);
			// Audit fields
			dynamicReport.setCompanyId(user.getCompanyId());
			dynamicReport.setUserId(user.getUserId());
			dynamicReport.setUserName(user.getFullName());
			dynamicReport.setCreateDate(now);
			dynamicReport.setModifiedDate(now);

			dynamicReport.setReportCode(reportCode);
			dynamicReport.setReportName(reportName);
			dynamicReport.setSharing(sharing);
			dynamicReport.setFilterConfig(filterConfig);
			dynamicReport.setTableConfig(tableConfig);
			dynamicReport.setUserConfig(userConfig);
			dynamicReport.setReportType(reportType);
		} else {
			dynamicReport.setModifiedDate(now);
			if (Validator.isNotNull(reportCode))
				dynamicReport.setReportCode(reportCode);
			if (Validator.isNotNull(reportName))
				dynamicReport.setReportName(reportName);
			if (Validator.isNotNull(sharing))
				dynamicReport.setSharing(sharing);
			if (Validator.isNotNull(filterConfig))
				dynamicReport.setFilterConfig(filterConfig);
			if (Validator.isNotNull(tableConfig))
				dynamicReport.setTableConfig(tableConfig);
			if (Validator.isNotNull(userConfig))
				dynamicReport.setUserConfig(userConfig);
			if (Validator.isNotNull(reportType))
				dynamicReport.setReportType(reportType);
		}

		return dynamicReportPersistence.update(dynamicReport);
	}
}