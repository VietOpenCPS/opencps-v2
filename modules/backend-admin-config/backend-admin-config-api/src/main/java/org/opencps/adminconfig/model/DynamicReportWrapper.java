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

package org.opencps.adminconfig.model;

import aQute.bnd.annotation.ProviderType;

import com.liferay.expando.kernel.model.ExpandoBridge;

import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.service.ServiceContext;

import java.io.Serializable;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * <p>
 * This class is a wrapper for {@link DynamicReport}.
 * </p>
 *
 * @author binhth
 * @see DynamicReport
 * @generated
 */
@ProviderType
public class DynamicReportWrapper implements DynamicReport,
	ModelWrapper<DynamicReport> {
	public DynamicReportWrapper(DynamicReport dynamicReport) {
		_dynamicReport = dynamicReport;
	}

	@Override
	public Class<?> getModelClass() {
		return DynamicReport.class;
	}

	@Override
	public String getModelClassName() {
		return DynamicReport.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("dynamicReportId", getDynamicReportId());
		attributes.put("groupId", getGroupId());
		attributes.put("companyId", getCompanyId());
		attributes.put("userId", getUserId());
		attributes.put("userName", getUserName());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("sharing", getSharing());
		attributes.put("reportName", getReportName());
		attributes.put("reportCode", getReportCode());
		attributes.put("filterConfig", getFilterConfig());
		attributes.put("tableConfig", getTableConfig());
		attributes.put("userConfig", getUserConfig());
		attributes.put("reportType", getReportType());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long dynamicReportId = (Long)attributes.get("dynamicReportId");

		if (dynamicReportId != null) {
			setDynamicReportId(dynamicReportId);
		}

		Long groupId = (Long)attributes.get("groupId");

		if (groupId != null) {
			setGroupId(groupId);
		}

		Long companyId = (Long)attributes.get("companyId");

		if (companyId != null) {
			setCompanyId(companyId);
		}

		Long userId = (Long)attributes.get("userId");

		if (userId != null) {
			setUserId(userId);
		}

		String userName = (String)attributes.get("userName");

		if (userName != null) {
			setUserName(userName);
		}

		Date createDate = (Date)attributes.get("createDate");

		if (createDate != null) {
			setCreateDate(createDate);
		}

		Date modifiedDate = (Date)attributes.get("modifiedDate");

		if (modifiedDate != null) {
			setModifiedDate(modifiedDate);
		}

		Integer sharing = (Integer)attributes.get("sharing");

		if (sharing != null) {
			setSharing(sharing);
		}

		String reportName = (String)attributes.get("reportName");

		if (reportName != null) {
			setReportName(reportName);
		}

		String reportCode = (String)attributes.get("reportCode");

		if (reportCode != null) {
			setReportCode(reportCode);
		}

		String filterConfig = (String)attributes.get("filterConfig");

		if (filterConfig != null) {
			setFilterConfig(filterConfig);
		}

		String tableConfig = (String)attributes.get("tableConfig");

		if (tableConfig != null) {
			setTableConfig(tableConfig);
		}

		String userConfig = (String)attributes.get("userConfig");

		if (userConfig != null) {
			setUserConfig(userConfig);
		}

		String reportType = (String)attributes.get("reportType");

		if (reportType != null) {
			setReportType(reportType);
		}
	}

	@Override
	public Object clone() {
		return new DynamicReportWrapper((DynamicReport)_dynamicReport.clone());
	}

	@Override
	public int compareTo(DynamicReport dynamicReport) {
		return _dynamicReport.compareTo(dynamicReport);
	}

	/**
	* Returns the company ID of this dynamic report.
	*
	* @return the company ID of this dynamic report
	*/
	@Override
	public long getCompanyId() {
		return _dynamicReport.getCompanyId();
	}

	/**
	* Returns the create date of this dynamic report.
	*
	* @return the create date of this dynamic report
	*/
	@Override
	public Date getCreateDate() {
		return _dynamicReport.getCreateDate();
	}

	/**
	* Returns the dynamic report ID of this dynamic report.
	*
	* @return the dynamic report ID of this dynamic report
	*/
	@Override
	public long getDynamicReportId() {
		return _dynamicReport.getDynamicReportId();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _dynamicReport.getExpandoBridge();
	}

	/**
	* Returns the filter config of this dynamic report.
	*
	* @return the filter config of this dynamic report
	*/
	@Override
	public String getFilterConfig() {
		return _dynamicReport.getFilterConfig();
	}

	/**
	* Returns the group ID of this dynamic report.
	*
	* @return the group ID of this dynamic report
	*/
	@Override
	public long getGroupId() {
		return _dynamicReport.getGroupId();
	}

	/**
	* Returns the modified date of this dynamic report.
	*
	* @return the modified date of this dynamic report
	*/
	@Override
	public Date getModifiedDate() {
		return _dynamicReport.getModifiedDate();
	}

	/**
	* Returns the primary key of this dynamic report.
	*
	* @return the primary key of this dynamic report
	*/
	@Override
	public long getPrimaryKey() {
		return _dynamicReport.getPrimaryKey();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _dynamicReport.getPrimaryKeyObj();
	}

	/**
	* Returns the report code of this dynamic report.
	*
	* @return the report code of this dynamic report
	*/
	@Override
	public String getReportCode() {
		return _dynamicReport.getReportCode();
	}

	/**
	* Returns the report name of this dynamic report.
	*
	* @return the report name of this dynamic report
	*/
	@Override
	public String getReportName() {
		return _dynamicReport.getReportName();
	}

	/**
	* Returns the report type of this dynamic report.
	*
	* @return the report type of this dynamic report
	*/
	@Override
	public String getReportType() {
		return _dynamicReport.getReportType();
	}

	/**
	* Returns the sharing of this dynamic report.
	*
	* @return the sharing of this dynamic report
	*/
	@Override
	public int getSharing() {
		return _dynamicReport.getSharing();
	}

	/**
	* Returns the table config of this dynamic report.
	*
	* @return the table config of this dynamic report
	*/
	@Override
	public String getTableConfig() {
		return _dynamicReport.getTableConfig();
	}

	/**
	* Returns the user config of this dynamic report.
	*
	* @return the user config of this dynamic report
	*/
	@Override
	public String getUserConfig() {
		return _dynamicReport.getUserConfig();
	}

	/**
	* Returns the user ID of this dynamic report.
	*
	* @return the user ID of this dynamic report
	*/
	@Override
	public long getUserId() {
		return _dynamicReport.getUserId();
	}

	/**
	* Returns the user name of this dynamic report.
	*
	* @return the user name of this dynamic report
	*/
	@Override
	public String getUserName() {
		return _dynamicReport.getUserName();
	}

	/**
	* Returns the user uuid of this dynamic report.
	*
	* @return the user uuid of this dynamic report
	*/
	@Override
	public String getUserUuid() {
		return _dynamicReport.getUserUuid();
	}

	@Override
	public int hashCode() {
		return _dynamicReport.hashCode();
	}

	@Override
	public boolean isCachedModel() {
		return _dynamicReport.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _dynamicReport.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _dynamicReport.isNew();
	}

	@Override
	public void persist() {
		_dynamicReport.persist();
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_dynamicReport.setCachedModel(cachedModel);
	}

	/**
	* Sets the company ID of this dynamic report.
	*
	* @param companyId the company ID of this dynamic report
	*/
	@Override
	public void setCompanyId(long companyId) {
		_dynamicReport.setCompanyId(companyId);
	}

	/**
	* Sets the create date of this dynamic report.
	*
	* @param createDate the create date of this dynamic report
	*/
	@Override
	public void setCreateDate(Date createDate) {
		_dynamicReport.setCreateDate(createDate);
	}

	/**
	* Sets the dynamic report ID of this dynamic report.
	*
	* @param dynamicReportId the dynamic report ID of this dynamic report
	*/
	@Override
	public void setDynamicReportId(long dynamicReportId) {
		_dynamicReport.setDynamicReportId(dynamicReportId);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_dynamicReport.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_dynamicReport.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_dynamicReport.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the filter config of this dynamic report.
	*
	* @param filterConfig the filter config of this dynamic report
	*/
	@Override
	public void setFilterConfig(String filterConfig) {
		_dynamicReport.setFilterConfig(filterConfig);
	}

	/**
	* Sets the group ID of this dynamic report.
	*
	* @param groupId the group ID of this dynamic report
	*/
	@Override
	public void setGroupId(long groupId) {
		_dynamicReport.setGroupId(groupId);
	}

	/**
	* Sets the modified date of this dynamic report.
	*
	* @param modifiedDate the modified date of this dynamic report
	*/
	@Override
	public void setModifiedDate(Date modifiedDate) {
		_dynamicReport.setModifiedDate(modifiedDate);
	}

	@Override
	public void setNew(boolean n) {
		_dynamicReport.setNew(n);
	}

	/**
	* Sets the primary key of this dynamic report.
	*
	* @param primaryKey the primary key of this dynamic report
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_dynamicReport.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_dynamicReport.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the report code of this dynamic report.
	*
	* @param reportCode the report code of this dynamic report
	*/
	@Override
	public void setReportCode(String reportCode) {
		_dynamicReport.setReportCode(reportCode);
	}

	/**
	* Sets the report name of this dynamic report.
	*
	* @param reportName the report name of this dynamic report
	*/
	@Override
	public void setReportName(String reportName) {
		_dynamicReport.setReportName(reportName);
	}

	/**
	* Sets the report type of this dynamic report.
	*
	* @param reportType the report type of this dynamic report
	*/
	@Override
	public void setReportType(String reportType) {
		_dynamicReport.setReportType(reportType);
	}

	/**
	* Sets the sharing of this dynamic report.
	*
	* @param sharing the sharing of this dynamic report
	*/
	@Override
	public void setSharing(int sharing) {
		_dynamicReport.setSharing(sharing);
	}

	/**
	* Sets the table config of this dynamic report.
	*
	* @param tableConfig the table config of this dynamic report
	*/
	@Override
	public void setTableConfig(String tableConfig) {
		_dynamicReport.setTableConfig(tableConfig);
	}

	/**
	* Sets the user config of this dynamic report.
	*
	* @param userConfig the user config of this dynamic report
	*/
	@Override
	public void setUserConfig(String userConfig) {
		_dynamicReport.setUserConfig(userConfig);
	}

	/**
	* Sets the user ID of this dynamic report.
	*
	* @param userId the user ID of this dynamic report
	*/
	@Override
	public void setUserId(long userId) {
		_dynamicReport.setUserId(userId);
	}

	/**
	* Sets the user name of this dynamic report.
	*
	* @param userName the user name of this dynamic report
	*/
	@Override
	public void setUserName(String userName) {
		_dynamicReport.setUserName(userName);
	}

	/**
	* Sets the user uuid of this dynamic report.
	*
	* @param userUuid the user uuid of this dynamic report
	*/
	@Override
	public void setUserUuid(String userUuid) {
		_dynamicReport.setUserUuid(userUuid);
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<DynamicReport> toCacheModel() {
		return _dynamicReport.toCacheModel();
	}

	@Override
	public DynamicReport toEscapedModel() {
		return new DynamicReportWrapper(_dynamicReport.toEscapedModel());
	}

	@Override
	public String toString() {
		return _dynamicReport.toString();
	}

	@Override
	public DynamicReport toUnescapedModel() {
		return new DynamicReportWrapper(_dynamicReport.toUnescapedModel());
	}

	@Override
	public String toXmlString() {
		return _dynamicReport.toXmlString();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof DynamicReportWrapper)) {
			return false;
		}

		DynamicReportWrapper dynamicReportWrapper = (DynamicReportWrapper)obj;

		if (Objects.equals(_dynamicReport, dynamicReportWrapper._dynamicReport)) {
			return true;
		}

		return false;
	}

	@Override
	public DynamicReport getWrappedModel() {
		return _dynamicReport;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _dynamicReport.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _dynamicReport.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_dynamicReport.resetOriginalValues();
	}

	private final DynamicReport _dynamicReport;
}