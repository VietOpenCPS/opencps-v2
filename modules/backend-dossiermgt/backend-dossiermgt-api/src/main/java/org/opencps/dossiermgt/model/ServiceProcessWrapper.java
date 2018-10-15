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

package org.opencps.dossiermgt.model;

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
 * @author huymq
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
		attributes.put("processName", getProcessName());
		attributes.put("description", getDescription());
		attributes.put("durationCount", getDurationCount());
		attributes.put("durationUnit", getDurationUnit());
		attributes.put("counter", getCounter());
		attributes.put("generateDossierNo", isGenerateDossierNo());
		attributes.put("dossierNoPattern", getDossierNoPattern());
		attributes.put("generateDueDate", isGenerateDueDate());
		attributes.put("dueDatePattern", getDueDatePattern());
		attributes.put("generatePassword", isGeneratePassword());
		attributes.put("directNotification", isDirectNotification());
		attributes.put("serverNo", getServerNo());
		attributes.put("serverName", getServerName());
		attributes.put("requestPayment", isRequestPayment());
		attributes.put("paymentFee", getPaymentFee());

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

		String processName = (String)attributes.get("processName");

		if (processName != null) {
			setProcessName(processName);
		}

		String description = (String)attributes.get("description");

		if (description != null) {
			setDescription(description);
		}

		Double durationCount = (Double)attributes.get("durationCount");

		if (durationCount != null) {
			setDurationCount(durationCount);
		}

		Integer durationUnit = (Integer)attributes.get("durationUnit");

		if (durationUnit != null) {
			setDurationUnit(durationUnit);
		}

		Long counter = (Long)attributes.get("counter");

		if (counter != null) {
			setCounter(counter);
		}

		Boolean generateDossierNo = (Boolean)attributes.get("generateDossierNo");

		if (generateDossierNo != null) {
			setGenerateDossierNo(generateDossierNo);
		}

		String dossierNoPattern = (String)attributes.get("dossierNoPattern");

		if (dossierNoPattern != null) {
			setDossierNoPattern(dossierNoPattern);
		}

		Boolean generateDueDate = (Boolean)attributes.get("generateDueDate");

		if (generateDueDate != null) {
			setGenerateDueDate(generateDueDate);
		}

		String dueDatePattern = (String)attributes.get("dueDatePattern");

		if (dueDatePattern != null) {
			setDueDatePattern(dueDatePattern);
		}

		Boolean generatePassword = (Boolean)attributes.get("generatePassword");

		if (generatePassword != null) {
			setGeneratePassword(generatePassword);
		}

		Boolean directNotification = (Boolean)attributes.get(
				"directNotification");

		if (directNotification != null) {
			setDirectNotification(directNotification);
		}

		String serverNo = (String)attributes.get("serverNo");

		if (serverNo != null) {
			setServerNo(serverNo);
		}

		String serverName = (String)attributes.get("serverName");

		if (serverName != null) {
			setServerName(serverName);
		}

		Boolean requestPayment = (Boolean)attributes.get("requestPayment");

		if (requestPayment != null) {
			setRequestPayment(requestPayment);
		}

		String paymentFee = (String)attributes.get("paymentFee");

		if (paymentFee != null) {
			setPaymentFee(paymentFee);
		}
	}

	@Override
	public Object clone() {
		return new ServiceProcessWrapper((ServiceProcess)_serviceProcess.clone());
	}

	@Override
	public int compareTo(ServiceProcess serviceProcess) {
		return _serviceProcess.compareTo(serviceProcess);
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
	* Returns the counter of this service process.
	*
	* @return the counter of this service process
	*/
	@Override
	public long getCounter() {
		return _serviceProcess.getCounter();
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
	* Returns the description of this service process.
	*
	* @return the description of this service process
	*/
	@Override
	public String getDescription() {
		return _serviceProcess.getDescription();
	}

	/**
	* Returns the direct notification of this service process.
	*
	* @return the direct notification of this service process
	*/
	@Override
	public boolean getDirectNotification() {
		return _serviceProcess.getDirectNotification();
	}

	/**
	* Returns the dossier no pattern of this service process.
	*
	* @return the dossier no pattern of this service process
	*/
	@Override
	public String getDossierNoPattern() {
		return _serviceProcess.getDossierNoPattern();
	}

	/**
	* Returns the due date pattern of this service process.
	*
	* @return the due date pattern of this service process
	*/
	@Override
	public String getDueDatePattern() {
		return _serviceProcess.getDueDatePattern();
	}

	/**
	* Returns the duration count of this service process.
	*
	* @return the duration count of this service process
	*/
	@Override
	public double getDurationCount() {
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
	public ExpandoBridge getExpandoBridge() {
		return _serviceProcess.getExpandoBridge();
	}

	/**
	* Returns the generate dossier no of this service process.
	*
	* @return the generate dossier no of this service process
	*/
	@Override
	public boolean getGenerateDossierNo() {
		return _serviceProcess.getGenerateDossierNo();
	}

	/**
	* Returns the generate due date of this service process.
	*
	* @return the generate due date of this service process
	*/
	@Override
	public boolean getGenerateDueDate() {
		return _serviceProcess.getGenerateDueDate();
	}

	/**
	* Returns the generate password of this service process.
	*
	* @return the generate password of this service process
	*/
	@Override
	public boolean getGeneratePassword() {
		return _serviceProcess.getGeneratePassword();
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
	* Returns the modified date of this service process.
	*
	* @return the modified date of this service process
	*/
	@Override
	public Date getModifiedDate() {
		return _serviceProcess.getModifiedDate();
	}

	/**
	* Returns the payment fee of this service process.
	*
	* @return the payment fee of this service process
	*/
	@Override
	public String getPaymentFee() {
		return _serviceProcess.getPaymentFee();
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

	@Override
	public Serializable getPrimaryKeyObj() {
		return _serviceProcess.getPrimaryKeyObj();
	}

	/**
	* Returns the process name of this service process.
	*
	* @return the process name of this service process
	*/
	@Override
	public String getProcessName() {
		return _serviceProcess.getProcessName();
	}

	/**
	* Returns the process no of this service process.
	*
	* @return the process no of this service process
	*/
	@Override
	public String getProcessNo() {
		return _serviceProcess.getProcessNo();
	}

	/**
	* Returns the request payment of this service process.
	*
	* @return the request payment of this service process
	*/
	@Override
	public boolean getRequestPayment() {
		return _serviceProcess.getRequestPayment();
	}

	/**
	* Returns the server name of this service process.
	*
	* @return the server name of this service process
	*/
	@Override
	public String getServerName() {
		return _serviceProcess.getServerName();
	}

	/**
	* Returns the server no of this service process.
	*
	* @return the server no of this service process
	*/
	@Override
	public String getServerNo() {
		return _serviceProcess.getServerNo();
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

	/**
	* Returns the user name of this service process.
	*
	* @return the user name of this service process
	*/
	@Override
	public String getUserName() {
		return _serviceProcess.getUserName();
	}

	/**
	* Returns the user uuid of this service process.
	*
	* @return the user uuid of this service process
	*/
	@Override
	public String getUserUuid() {
		return _serviceProcess.getUserUuid();
	}

	/**
	* Returns the uuid of this service process.
	*
	* @return the uuid of this service process
	*/
	@Override
	public String getUuid() {
		return _serviceProcess.getUuid();
	}

	@Override
	public int hashCode() {
		return _serviceProcess.hashCode();
	}

	@Override
	public boolean isCachedModel() {
		return _serviceProcess.isCachedModel();
	}

	/**
	* Returns <code>true</code> if this service process is direct notification.
	*
	* @return <code>true</code> if this service process is direct notification; <code>false</code> otherwise
	*/
	@Override
	public boolean isDirectNotification() {
		return _serviceProcess.isDirectNotification();
	}

	@Override
	public boolean isEscapedModel() {
		return _serviceProcess.isEscapedModel();
	}

	/**
	* Returns <code>true</code> if this service process is generate dossier no.
	*
	* @return <code>true</code> if this service process is generate dossier no; <code>false</code> otherwise
	*/
	@Override
	public boolean isGenerateDossierNo() {
		return _serviceProcess.isGenerateDossierNo();
	}

	/**
	* Returns <code>true</code> if this service process is generate due date.
	*
	* @return <code>true</code> if this service process is generate due date; <code>false</code> otherwise
	*/
	@Override
	public boolean isGenerateDueDate() {
		return _serviceProcess.isGenerateDueDate();
	}

	/**
	* Returns <code>true</code> if this service process is generate password.
	*
	* @return <code>true</code> if this service process is generate password; <code>false</code> otherwise
	*/
	@Override
	public boolean isGeneratePassword() {
		return _serviceProcess.isGeneratePassword();
	}

	@Override
	public boolean isNew() {
		return _serviceProcess.isNew();
	}

	/**
	* Returns <code>true</code> if this service process is request payment.
	*
	* @return <code>true</code> if this service process is request payment; <code>false</code> otherwise
	*/
	@Override
	public boolean isRequestPayment() {
		return _serviceProcess.isRequestPayment();
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
	public void setCounter(long counter) {
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
	public void setDescription(String description) {
		_serviceProcess.setDescription(description);
	}

	/**
	* Sets whether this service process is direct notification.
	*
	* @param directNotification the direct notification of this service process
	*/
	@Override
	public void setDirectNotification(boolean directNotification) {
		_serviceProcess.setDirectNotification(directNotification);
	}

	/**
	* Sets the dossier no pattern of this service process.
	*
	* @param dossierNoPattern the dossier no pattern of this service process
	*/
	@Override
	public void setDossierNoPattern(String dossierNoPattern) {
		_serviceProcess.setDossierNoPattern(dossierNoPattern);
	}

	/**
	* Sets the due date pattern of this service process.
	*
	* @param dueDatePattern the due date pattern of this service process
	*/
	@Override
	public void setDueDatePattern(String dueDatePattern) {
		_serviceProcess.setDueDatePattern(dueDatePattern);
	}

	/**
	* Sets the duration count of this service process.
	*
	* @param durationCount the duration count of this service process
	*/
	@Override
	public void setDurationCount(double durationCount) {
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
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_serviceProcess.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_serviceProcess.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_serviceProcess.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets whether this service process is generate dossier no.
	*
	* @param generateDossierNo the generate dossier no of this service process
	*/
	@Override
	public void setGenerateDossierNo(boolean generateDossierNo) {
		_serviceProcess.setGenerateDossierNo(generateDossierNo);
	}

	/**
	* Sets whether this service process is generate due date.
	*
	* @param generateDueDate the generate due date of this service process
	*/
	@Override
	public void setGenerateDueDate(boolean generateDueDate) {
		_serviceProcess.setGenerateDueDate(generateDueDate);
	}

	/**
	* Sets whether this service process is generate password.
	*
	* @param generatePassword the generate password of this service process
	*/
	@Override
	public void setGeneratePassword(boolean generatePassword) {
		_serviceProcess.setGeneratePassword(generatePassword);
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
	* Sets the payment fee of this service process.
	*
	* @param paymentFee the payment fee of this service process
	*/
	@Override
	public void setPaymentFee(String paymentFee) {
		_serviceProcess.setPaymentFee(paymentFee);
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
	* Sets the process name of this service process.
	*
	* @param processName the process name of this service process
	*/
	@Override
	public void setProcessName(String processName) {
		_serviceProcess.setProcessName(processName);
	}

	/**
	* Sets the process no of this service process.
	*
	* @param processNo the process no of this service process
	*/
	@Override
	public void setProcessNo(String processNo) {
		_serviceProcess.setProcessNo(processNo);
	}

	/**
	* Sets whether this service process is request payment.
	*
	* @param requestPayment the request payment of this service process
	*/
	@Override
	public void setRequestPayment(boolean requestPayment) {
		_serviceProcess.setRequestPayment(requestPayment);
	}

	/**
	* Sets the server name of this service process.
	*
	* @param serverName the server name of this service process
	*/
	@Override
	public void setServerName(String serverName) {
		_serviceProcess.setServerName(serverName);
	}

	/**
	* Sets the server no of this service process.
	*
	* @param serverNo the server no of this service process
	*/
	@Override
	public void setServerNo(String serverNo) {
		_serviceProcess.setServerNo(serverNo);
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
	public void setUserName(String userName) {
		_serviceProcess.setUserName(userName);
	}

	/**
	* Sets the user uuid of this service process.
	*
	* @param userUuid the user uuid of this service process
	*/
	@Override
	public void setUserUuid(String userUuid) {
		_serviceProcess.setUserUuid(userUuid);
	}

	/**
	* Sets the uuid of this service process.
	*
	* @param uuid the uuid of this service process
	*/
	@Override
	public void setUuid(String uuid) {
		_serviceProcess.setUuid(uuid);
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<ServiceProcess> toCacheModel() {
		return _serviceProcess.toCacheModel();
	}

	@Override
	public ServiceProcess toEscapedModel() {
		return new ServiceProcessWrapper(_serviceProcess.toEscapedModel());
	}

	@Override
	public String toString() {
		return _serviceProcess.toString();
	}

	@Override
	public ServiceProcess toUnescapedModel() {
		return new ServiceProcessWrapper(_serviceProcess.toUnescapedModel());
	}

	@Override
	public String toXmlString() {
		return _serviceProcess.toXmlString();
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