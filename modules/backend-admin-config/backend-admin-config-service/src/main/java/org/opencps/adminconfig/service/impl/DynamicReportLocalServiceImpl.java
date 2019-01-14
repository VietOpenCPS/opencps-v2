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

package org.opencps.adminconfig.service.impl;

import com.liferay.counter.kernel.service.CounterLocalServiceUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.search.Indexable;
import com.liferay.portal.kernel.search.IndexableType;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.util.Validator;

import java.util.Date;
import java.util.List;

import org.opencps.adminconfig.model.DynamicReport;
import org.opencps.adminconfig.service.base.DynamicReportLocalServiceBaseImpl;

import backend.admin.config.service.util.ModelKeysDynamicReport;

/**
 * The implementation of the dynamic report local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link org.opencps.DynamicReport.service.DynamicReportLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author binhth
 * @see DynamicReportLocalServiceBaseImpl
 * @see org.opencps.DynamicReport.service.DynamicReportLocalServiceUtil
 */
public class DynamicReportLocalServiceImpl
	extends DynamicReportLocalServiceBaseImpl {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this class directly. Always use {@link org.opencps.DynamicReport.service.DynamicReportLocalServiceUtil} to access the dynamic report local service.
	 */
	
	public List<DynamicReport> getByGroupType(long groupId, String reportType, int start, int end) {

		return dynamicReportPersistence.findByF_reportType(groupId, reportType, start, end);

	}
	
	public List<DynamicReport> getByGroup(long groupId, int start, int end) {

		return dynamicReportPersistence.findByF_GroupId(groupId, start, end);

	}
	
	public DynamicReport fetchByCode(long groupId, String reportCode) {

		DynamicReport dynamicReport = dynamicReportPersistence.fetchByF_reportCode(groupId, reportCode);

		if (Validator.isNull(dynamicReport)) {
			return null;
		}

		return dynamicReport;
	}

	@Indexable(type = IndexableType.DELETE)
	public DynamicReport adminProcessDelete(Long id) {

		DynamicReport DynamicReport = dynamicReportPersistence.fetchByPrimaryKey(id);

		if (Validator.isNull(DynamicReport)) {
			return null;
		} else {
			dynamicReportPersistence.remove(DynamicReport);
		}

		return DynamicReport;
	}

	@Indexable(type = IndexableType.REINDEX)
	public DynamicReport adminProcessData(JSONObject objectData) {

		DynamicReport object = null;

		if (objectData.getLong(ModelKeysDynamicReport.DYNAMICREPORTID) > 0) {

			object = dynamicReportPersistence.fetchByPrimaryKey(objectData.getLong(ModelKeysDynamicReport.DYNAMICREPORTID));

			object.setModifiedDate(new Date());
			
		} else {

			long id = CounterLocalServiceUtil.increment(DynamicReport.class.getName());

			object = dynamicReportPersistence.create(id);
			
			object.setGroupId(objectData.getLong(ModelKeysDynamicReport.GROUPID));
			object.setCompanyId(objectData.getLong(ModelKeysDynamicReport.COMPANYID));
			object.setCreateDate(new Date());
			
		}

		long userId = objectData.getLong(ModelKeysDynamicReport.USERID);
		
		object.setUserId(objectData.getLong(ModelKeysDynamicReport.USERID));

		if (userId > 0) {
			User user = UserLocalServiceUtil.fetchUser(userId);
			object.setUserName(user.getFullName());
		}
		
		object.setSharing(objectData.getInt(ModelKeysDynamicReport.SHARING));
		object.setReportName(objectData.getString(ModelKeysDynamicReport.REPORTNAME));
		object.setReportCode(objectData.getString(ModelKeysDynamicReport.REPORTCODE));
		object.setFilterConfig(objectData.getString(ModelKeysDynamicReport.FILTERCONFIG));
		object.setTableConfig(objectData.getString(ModelKeysDynamicReport.TABLECONFIG));
		object.setUserConfig(objectData.getString(ModelKeysDynamicReport.USERCONFIG));
		object.setReportType(objectData.getString(ModelKeysDynamicReport.REPORTTYPE));
		
		dynamicReportPersistence.update(object);

		return object;
	}
	
}