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

package org.opencps.backend.processmgt.model;

import aQute.bnd.annotation.ProviderType;

import com.liferay.expando.kernel.model.ExpandoBridge;

import com.liferay.exportimport.kernel.lar.StagedModelType;

import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.service.ServiceContext;

import java.io.Serializable;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * <p>
 * This class is a wrapper for {@link ServiceProcess}.
 * </p>
 *
 * @author khoavu
 * @see ServiceProcess
 * @generated
 */
@ProviderType
public class ServiceProcessWrapper implements ServiceProcess,
	ModelWrapper<ServiceProcess> {
	public ServiceProcessWrapper(ServiceProcess serviceProcess) {
		_serviceProcess = serviceProcess;
	}

	@Override
	public Class<?> getModelClass() {
		return ServiceProcess.class;
	}

	@Override
	public String getModelClassName() {
		return ServiceProcess.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("uuid", getUuid());
		attributes.put("serviceProcessId", getServiceProcessId());
		attributes.put("companyId", getCompanyId());
		attributes.put("groupId", getGroupId());
		attributes.put("userId", getUserId());
		attributes.put("userName", getUserName());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("processNo", getProcessNo());
		attributes.put("description", getDescription());
		attributes.put("durationCount", getDurationCount());
		attributes.put("durationUnit", getDurationUnit());
		attributes.put("counter", getCounter());
		attributes.put("generateDossierNo", getGenerateDossierNo());
		attributes.put("dossierNoPattern", getDossierNoPattern());
		attributes.put("generateDueDate", getGenerateDueDate());
		attributes.put("dueDatePattern", getDueDatePattern());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		String uuid = (String)attributes.get("uuid");

		if (uuid != null) {
			setUuid(uuid);
		}

		Long serviceProcessId = (Long)attributes.get("serviceProcessId");

		if (serviceProcessId != null) {
			setServiceProcessId(serviceProcessId);
		}

		Long companyId = (Long)attributes.get("companyId");

		if (companyId != null) {
			setCompanyId(companyId);
		}

		Long groupId = (Long)attributes.get("groupId");

		if (groupId != null) {
			setGroupId(groupId);
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

		String processNo = (String)attributes.get("processNo");

		if (processNo != null) {
			setProcessNo(processNo);
		}

		String description = (String)attributes.get("description");

		if (description != null) {
			setDescription(description);
		}

		Integer durationCount = (Integer)attributes.get("durationCount");

		if (durationCount != null) {
			setDurationCount(durationCount);
		}

		Integer durationUnit = (Integer)attributes.get("durationUnit");

		if (durationUnit != null) {
			setDurationUnit(durationUnit);
		}

		Integer counter = (Integer)attributes.get("counter");

		if (counter != null) {
			setCounter(counter);
		}

		String generateDossierNo = (String)attributes.get("generateDossierNo");

		if (generateDossierNo != null) {
			setGenerateDossierNo(generateDossierNo);
		}

		String dossierNoPattern = (String)attributes.get("dossierNoPattern");

		if (dossierNoPattern != null) {
			setDossierNoPattern(dossierNoPattern);
		}

		String generateDueDate = (String)attributes.get("generateDueDate");

		if (generateDueDate != null) {
			setGenerateDueDate(generateDueDate);
		}

		String dueDatePattern = (String)attributes.get("dueDatePattern");

		if (dueDatePattern != null) {
			setDueDatePattern(dueDatePattern);
		}
	}

	@Override
	public ServiceProcess toEscapedModel() {
		return new ServiceProcessWrapper(_serviceProcess.toEscapedModel());
	}

	@Override
	public ServiceProcess toUnescapedModel() {
		return new ServiceProcessWrapper(_serviceProcess.toUnescapedModel());
	}

	@Override
	public boolean isCachedModel() {
		return _serviceProcess.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _serviceProcess.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _serviceProcess.isNew();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _serviceProcess.getExpandoBridge();
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<ServiceProcess> toCacheModel() {
		return _serviceProcess.toCacheModel();
	}

	@Override
	public int compareTo(ServiceProcess serviceProcess) {
		return _serviceProcess.compareTo(serviceProcess);
	}

	/**
	* Returns the counter of this service process.
	*
	* @return the counter of this service process
	*/
	@Override
	public int getCounter() {
		return _serviceProcess.getCounter();
	}

	/**
	* Returns the duration count of this service process.
	*
	* @return the duration count of this service process
	*/
	@Override
	public int getDurationCount() {
		return _serviceProcess.getDurationCount();
	}

	/**
	* Returns the duration unit of this service process.
	*
	* @return the duration unit of this service process
	*/
	@Override
	public int getDurationUnit() {
		return _serviceProcess.getDurationUnit();
	}

	@Override
	public int hashCode() {
		return _serviceProcess.hashCode();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _serviceProcess.getPrimaryKeyObj();
	}

	@Override
	public java.lang.Object clone() {
		return new ServiceProcessWrapper((ServiceProcess)_serviceProcess.clone());
	}

	/**
	* Returns the description of this service process.
	*
	* @return the description of this service process
	*/
	@Override
	public java.lang.String getDescription() {
		return _serviceProcess.getDescription();
	}

	/**
	* Returns the dossier no pattern of this service process.
	*
	* @return the dossier no pattern of this service process
	*/
	@Override
	public java.lang.String getDossierNoPattern() {
		return _serviceProcess.getDossierNoPattern();
	}

	/**
	* Returns the due date pattern of this service process.
	*
	* @return the due date pattern of this service process
	*/
	@Override
	public java.lang.String getDueDatePattern() {
		return _serviceProcess.getDueDatePattern();
	}

	/**
	* Returns the generate dossier no of this service process.
	*
	* @return the generate dossier no of this service process
	*/
	@Override
	public java.lang.String getGenerateDossierNo() {
		return _serviceProcess.getGenerateDossierNo();
	}

	/**
	* Returns the generate due date of this service process.
	*
	* @return the generate due date of this service process
	*/
	@Override
	public java.lang.String getGenerateDueDate() {
		return _serviceProcess.getGenerateDueDate();
	}

	/**
	* Returns the process no of this service process.
	*
	* @return the process no of this service process
	*/
	@Override
	public java.lang.String getProcessNo() {
		return _serviceProcess.getProcessNo();
	}

	/**
	* Returns the user name of this service process.
	*
	* @return the user name of this service process
	*/
	@Override
	public java.lang.String getUserName() {
		return _serviceProcess.getUserName();
	}

	/**
	* Returns the user uuid of this service process.
	*
	* @return the user uuid of this service process
	*/
	@Override
	public java.lang.String getUserUuid() {
		return _serviceProcess.getUserUuid();
	}

	/**
	* Returns the uuid of this service process.
	*
	* @return the uuid of this service process
	*/
	@Override
	public java.lang.String getUuid() {
		return _serviceProcess.getUuid();
	}

	@Override
	public java.lang.String toString() {
		return _serviceProcess.toString();
	}

	@Override
	public java.lang.String toXmlString() {
		return _serviceProcess.toXmlString();
	}

	/**
	* Returns the create date of this service process.
	*
	* @return the create date of this service process
	*/
	@Override
	public Date getCreateDate() {
		return _serviceProcess.getCreateDate();
	}

	/**
	* Returns the modified date of this service process.
	*
	* @return the modified date of this service process
	*/
	@Override
	public Date getModifiedDate() {
		return _serviceProcess.getModifiedDate();
	}

	/**
	* Returns the company ID of this service process.
	*
	* @return the company ID of this service process
	*/
	@Override
	public long getCompanyId() {
		return _serviceProcess.getCompanyId();
	}

	/**
	* Returns the group ID of this service process.
	*
	* @return the group ID of this service process
	*/
	@Override
	public long getGroupId() {
		return _serviceProcess.getGroupId();
	}

	/**
	* Returns the primary key of this service process.
	*
	* @return the primary key of this service process
	*/
	@Override
	public long getPrimaryKey() {
		return _serviceProcess.getPrimaryKey();
	}

	/**
	* Returns the service process ID of this service process.
	*
	* @return the service process ID of this service process
	*/
	@Override
	public long getServiceProcessId() {
		return _serviceProcess.getServiceProcessId();
	}

	/**
	* Returns the user ID of this service process.
	*
	* @return the user ID of this service process
	*/
	@Override
	public long getUserId() {
		return _serviceProcess.getUserId();
	}

	@Override
	public void persist() {
		_serviceProcess.persist();
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_serviceProcess.setCachedModel(cachedModel);
	}

	/**
	* Sets the company ID of this service process.
	*
	* @param companyId the company ID of this service process
	*/
	@Override
	public void setCompanyId(long companyId) {
		_serviceProcess.setCompanyId(companyId);
	}

	/**
	* Sets the counter of this service process.
	*
	* @param counter the counter of this service process
	*/
	@Override
	public void setCounter(int counter) {
		_serviceProcess.setCounter(counter);
	}

	/**
	* Sets the create date of this service process.
	*
	* @param createDate the create date of this service process
	*/
	@Override
	public void setCreateDate(Date createDate) {
		_serviceProcess.setCreateDate(createDate);
	}

	/**
	* Sets the description of this service process.
	*
	* @param description the description of this service process
	*/
	@Override
	public void setDescription(java.lang.String description) {
		_serviceProcess.setDescription(description);
	}

	/**
	* Sets the dossier no pattern of this service process.
	*
	* @param dossierNoPattern the dossier no pattern of this service process
	*/
	@Override
	public void setDossierNoPattern(java.lang.String dossierNoPattern) {
		_serviceProcess.setDossierNoPattern(dossierNoPattern);
	}

	/**
	* Sets the due date pattern of this service process.
	*
	* @param dueDatePattern the due date pattern of this service process
	*/
	@Override
	public void setDueDatePattern(java.lang.String dueDatePattern) {
		_serviceProcess.setDueDatePattern(dueDatePattern);
	}

	/**
	* Sets the duration count of this service process.
	*
	* @param durationCount the duration count of this service process
	*/
	@Override
	public void setDurationCount(int durationCount) {
		_serviceProcess.setDurationCount(durationCount);
	}

	/**
	* Sets the duration unit of this service process.
	*
	* @param durationUnit the duration unit of this service process
	*/
	@Override
	public void setDurationUnit(int durationUnit) {
		_serviceProcess.setDurationUnit(durationUnit);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_serviceProcess.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_serviceProcess.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_serviceProcess.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the generate dossier no of this service process.
	*
	* @param generateDossierNo the generate dossier no of this service process
	*/
	@Override
	public void setGenerateDossierNo(java.lang.String generateDossierNo) {
		_serviceProcess.setGenerateDossierNo(generateDossierNo);
	}

	/**
	* Sets the generate due date of this service process.
	*
	* @param generateDueDate the generate due date of this service process
	*/
	@Override
	public void setGenerateDueDate(java.lang.String generateDueDate) {
		_serviceProcess.setGenerateDueDate(generateDueDate);
	}

	/**
	* Sets the group ID of this service process.
	*
	* @param groupId the group ID of this service process
	*/
	@Override
	public void setGroupId(long groupId) {
		_serviceProcess.setGroupId(groupId);
	}

	/**
	* Sets the modified date of this service process.
	*
	* @param modifiedDate the modified date of this service process
	*/
	@Override
	public void setModifiedDate(Date modifiedDate) {
		_serviceProcess.setModifiedDate(modifiedDate);
	}

	@Override
	public void setNew(boolean n) {
		_serviceProcess.setNew(n);
	}

	/**
	* Sets the primary key of this service process.
	*
	* @param primaryKey the primary key of this service process
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_serviceProcess.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_serviceProcess.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the process no of this service process.
	*
	* @param processNo the process no of this service process
	*/
	@Override
	public void setProcessNo(java.lang.String processNo) {
		_serviceProcess.setProcessNo(processNo);
	}

	/**
	* Sets the service process ID of this service process.
	*
	* @param serviceProcessId the service process ID of this service process
	*/
	@Override
	public void setServiceProcessId(long serviceProcessId) {
		_serviceProcess.setServiceProcessId(serviceProcessId);
	}

	/**
	* Sets the user ID of this service process.
	*
	* @param userId the user ID of this service process
	*/
	@Override
	public void setUserId(long userId) {
		_serviceProcess.setUserId(userId);
	}

	/**
	* Sets the user name of this service process.
	*
	* @param userName the user name of this service process
	*/
	@Override
	public void setUserName(java.lang.String userName) {
		_serviceProcess.setUserName(userName);
	}

	/**
	* Sets the user uuid of this service process.
	*
	* @param userUuid the user uuid of this service process
	*/
	@Override
	public void setUserUuid(java.lang.String userUuid) {
		_serviceProcess.setUserUuid(userUuid);
	}

	/**
	* Sets the uuid of this service process.
	*
	* @param uuid the uuid of this service process
	*/
	@Override
	public void setUuid(java.lang.String uuid) {
		_serviceProcess.setUuid(uuid);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof ServiceProcessWrapper)) {
			return false;
		}

		ServiceProcessWrapper serviceProcessWrapper = (ServiceProcessWrapper)obj;

		if (Objects.equals(_serviceProcess,
					serviceProcessWrapper._serviceProcess)) {
			return true;
		}

		return false;
	}

	@Override
	public StagedModelType getStagedModelType() {
		return _serviceProcess.getStagedModelType();
	}

	@Override
	public ServiceProcess getWrappedModel() {
		return _serviceProcess;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _serviceProcess.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _serviceProcess.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_serviceProcess.resetOriginalValues();
	}

	private final ServiceProcess _serviceProcess;
}