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
import com.liferay.portal.kernel.search.Indexable;
import com.liferay.portal.kernel.search.IndexableType;
import com.liferay.portal.kernel.util.Validator;

import java.util.List;

import org.opencps.adminconfig.model.ReportRole;
import org.opencps.adminconfig.service.base.ReportRoleLocalServiceBaseImpl;

import backend.admin.config.service.util.ModelKeysReportRole;

/**
 * The implementation of the report role local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link org.opencps.adminconfig.service.ReportRoleLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author binhth
 * @see ReportRoleLocalServiceBaseImpl
 * @see org.opencps.adminconfig.service.ReportRoleLocalServiceUtil
 */
public class ReportRoleLocalServiceImpl extends ReportRoleLocalServiceBaseImpl {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this class directly. Always use {@link org.opencps.adminconfig.service.ReportRoleLocalServiceUtil} to access the report role local service.
	 */
	@Indexable(type = IndexableType.DELETE)
	public ReportRole adminProcessDelete(Long id) {

		ReportRole reportRole = reportRolePersistence.fetchByPrimaryKey(id);

		if (Validator.isNull(reportRole)) {
			return null;
		} else {
			reportRolePersistence.remove(reportRole);
		}

		return reportRole;
	}

	@Indexable(type = IndexableType.REINDEX)
	public ReportRole adminProcessData(JSONObject objectData) {

		ReportRole object = null;

		if (objectData.getLong(ModelKeysReportRole.REPORT_ROLE_ID) > 0) {
			object = reportRolePersistence.fetchByPrimaryKey(objectData.getLong(ModelKeysReportRole.REPORT_ROLE_ID));
		} else {
			long id = CounterLocalServiceUtil.increment(ReportRole.class.getName());

			object = reportRolePersistence.create(id);
		}

		object.setRoleId(objectData.getLong(ModelKeysReportRole.ROLE_ID));
		object.setDynamicReportId(objectData.getLong(ModelKeysReportRole.DYNAMIC_REPORT_ID));
		
		reportRolePersistence.update(object);

		return object;
	}
	
	public List<ReportRole> findByDRID(long dynamicReportId) {
		return reportRolePersistence.findByF_DRID(dynamicReportId);
	}
	
	public ReportRole fetchByDRID_RID(long dynamicReportId, long roleId) {
		return reportRolePersistence.fetchByF_DRID_RID(dynamicReportId, roleId);
	}

	public List<ReportRole> findByRIDS(long[] roleIds) {
		return reportRolePersistence.findByF_RIDS(roleIds);
	}
	
	public int countAll() {
		return reportRolePersistence.countAll();
	}
}