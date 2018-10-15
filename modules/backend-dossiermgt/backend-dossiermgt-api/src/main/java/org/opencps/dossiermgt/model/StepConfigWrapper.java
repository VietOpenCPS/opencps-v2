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
 * This class is a wrapper for {@link StepConfig}.
 * </p>
 *
 * @author huymq
 * @see StepConfig
 * @generated
 */
@ProviderType
public class StepConfigWrapper implements StepConfig, ModelWrapper<StepConfig> {
	public StepConfigWrapper(StepConfig stepConfig) {
		_stepConfig = stepConfig;
	}

	@Override
	public Class<?> getModelClass() {
		return StepConfig.class;
	}

	@Override
	public String getModelClassName() {
		return StepConfig.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("uuid", getUuid());
		attributes.put("stepConfigId", getStepConfigId());
		attributes.put("companyId", getCompanyId());
		attributes.put("groupId", getGroupId());
		attributes.put("userId", getUserId());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("stepCode", getStepCode());
		attributes.put("stepName", getStepName());
		attributes.put("stepType", getStepType());
		attributes.put("dossierStatus", getDossierStatus());
		attributes.put("dossierSubStatus", getDossierSubStatus());
		attributes.put("menuGroup", getMenuGroup());
		attributes.put("menuStepName", getMenuStepName());
		attributes.put("buttonConfig", getButtonConfig());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		String uuid = (String)attributes.get("uuid");

		if (uuid != null) {
			setUuid(uuid);
		}

		Long stepConfigId = (Long)attributes.get("stepConfigId");

		if (stepConfigId != null) {
			setStepConfigId(stepConfigId);
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

		Date createDate = (Date)attributes.get("createDate");

		if (createDate != null) {
			setCreateDate(createDate);
		}

		Date modifiedDate = (Date)attributes.get("modifiedDate");

		if (modifiedDate != null) {
			setModifiedDate(modifiedDate);
		}

		String stepCode = (String)attributes.get("stepCode");

		if (stepCode != null) {
			setStepCode(stepCode);
		}

		String stepName = (String)attributes.get("stepName");

		if (stepName != null) {
			setStepName(stepName);
		}

		Integer stepType = (Integer)attributes.get("stepType");

		if (stepType != null) {
			setStepType(stepType);
		}

		String dossierStatus = (String)attributes.get("dossierStatus");

		if (dossierStatus != null) {
			setDossierStatus(dossierStatus);
		}

		String dossierSubStatus = (String)attributes.get("dossierSubStatus");

		if (dossierSubStatus != null) {
			setDossierSubStatus(dossierSubStatus);
		}

		String menuGroup = (String)attributes.get("menuGroup");

		if (menuGroup != null) {
			setMenuGroup(menuGroup);
		}

		String menuStepName = (String)attributes.get("menuStepName");

		if (menuStepName != null) {
			setMenuStepName(menuStepName);
		}

		String buttonConfig = (String)attributes.get("buttonConfig");

		if (buttonConfig != null) {
			setButtonConfig(buttonConfig);
		}
	}

	@Override
	public Object clone() {
		return new StepConfigWrapper((StepConfig)_stepConfig.clone());
	}

	@Override
	public int compareTo(StepConfig stepConfig) {
		return _stepConfig.compareTo(stepConfig);
	}

	/**
	* Returns the button config of this step config.
	*
	* @return the button config of this step config
	*/
	@Override
	public String getButtonConfig() {
		return _stepConfig.getButtonConfig();
	}

	/**
	* Returns the company ID of this step config.
	*
	* @return the company ID of this step config
	*/
	@Override
	public long getCompanyId() {
		return _stepConfig.getCompanyId();
	}

	/**
	* Returns the create date of this step config.
	*
	* @return the create date of this step config
	*/
	@Override
	public Date getCreateDate() {
		return _stepConfig.getCreateDate();
	}

	/**
	* Returns the dossier status of this step config.
	*
	* @return the dossier status of this step config
	*/
	@Override
	public String getDossierStatus() {
		return _stepConfig.getDossierStatus();
	}

	/**
	* Returns the dossier sub status of this step config.
	*
	* @return the dossier sub status of this step config
	*/
	@Override
	public String getDossierSubStatus() {
		return _stepConfig.getDossierSubStatus();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _stepConfig.getExpandoBridge();
	}

	/**
	* Returns the group ID of this step config.
	*
	* @return the group ID of this step config
	*/
	@Override
	public long getGroupId() {
		return _stepConfig.getGroupId();
	}

	/**
	* Returns the menu group of this step config.
	*
	* @return the menu group of this step config
	*/
	@Override
	public String getMenuGroup() {
		return _stepConfig.getMenuGroup();
	}

	/**
	* Returns the menu step name of this step config.
	*
	* @return the menu step name of this step config
	*/
	@Override
	public String getMenuStepName() {
		return _stepConfig.getMenuStepName();
	}

	/**
	* Returns the modified date of this step config.
	*
	* @return the modified date of this step config
	*/
	@Override
	public Date getModifiedDate() {
		return _stepConfig.getModifiedDate();
	}

	/**
	* Returns the primary key of this step config.
	*
	* @return the primary key of this step config
	*/
	@Override
	public long getPrimaryKey() {
		return _stepConfig.getPrimaryKey();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _stepConfig.getPrimaryKeyObj();
	}

	/**
	* Returns the step code of this step config.
	*
	* @return the step code of this step config
	*/
	@Override
	public String getStepCode() {
		return _stepConfig.getStepCode();
	}

	/**
	* Returns the step config ID of this step config.
	*
	* @return the step config ID of this step config
	*/
	@Override
	public long getStepConfigId() {
		return _stepConfig.getStepConfigId();
	}

	/**
	* Returns the step name of this step config.
	*
	* @return the step name of this step config
	*/
	@Override
	public String getStepName() {
		return _stepConfig.getStepName();
	}

	/**
	* Returns the step type of this step config.
	*
	* @return the step type of this step config
	*/
	@Override
	public int getStepType() {
		return _stepConfig.getStepType();
	}

	/**
	* Returns the user ID of this step config.
	*
	* @return the user ID of this step config
	*/
	@Override
	public long getUserId() {
		return _stepConfig.getUserId();
	}

	/**
	* Returns the user uuid of this step config.
	*
	* @return the user uuid of this step config
	*/
	@Override
	public String getUserUuid() {
		return _stepConfig.getUserUuid();
	}

	/**
	* Returns the uuid of this step config.
	*
	* @return the uuid of this step config
	*/
	@Override
	public String getUuid() {
		return _stepConfig.getUuid();
	}

	@Override
	public int hashCode() {
		return _stepConfig.hashCode();
	}

	@Override
	public boolean isCachedModel() {
		return _stepConfig.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _stepConfig.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _stepConfig.isNew();
	}

	@Override
	public void persist() {
		_stepConfig.persist();
	}

	/**
	* Sets the button config of this step config.
	*
	* @param buttonConfig the button config of this step config
	*/
	@Override
	public void setButtonConfig(String buttonConfig) {
		_stepConfig.setButtonConfig(buttonConfig);
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_stepConfig.setCachedModel(cachedModel);
	}

	/**
	* Sets the company ID of this step config.
	*
	* @param companyId the company ID of this step config
	*/
	@Override
	public void setCompanyId(long companyId) {
		_stepConfig.setCompanyId(companyId);
	}

	/**
	* Sets the create date of this step config.
	*
	* @param createDate the create date of this step config
	*/
	@Override
	public void setCreateDate(Date createDate) {
		_stepConfig.setCreateDate(createDate);
	}

	/**
	* Sets the dossier status of this step config.
	*
	* @param dossierStatus the dossier status of this step config
	*/
	@Override
	public void setDossierStatus(String dossierStatus) {
		_stepConfig.setDossierStatus(dossierStatus);
	}

	/**
	* Sets the dossier sub status of this step config.
	*
	* @param dossierSubStatus the dossier sub status of this step config
	*/
	@Override
	public void setDossierSubStatus(String dossierSubStatus) {
		_stepConfig.setDossierSubStatus(dossierSubStatus);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_stepConfig.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_stepConfig.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_stepConfig.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the group ID of this step config.
	*
	* @param groupId the group ID of this step config
	*/
	@Override
	public void setGroupId(long groupId) {
		_stepConfig.setGroupId(groupId);
	}

	/**
	* Sets the menu group of this step config.
	*
	* @param menuGroup the menu group of this step config
	*/
	@Override
	public void setMenuGroup(String menuGroup) {
		_stepConfig.setMenuGroup(menuGroup);
	}

	/**
	* Sets the menu step name of this step config.
	*
	* @param menuStepName the menu step name of this step config
	*/
	@Override
	public void setMenuStepName(String menuStepName) {
		_stepConfig.setMenuStepName(menuStepName);
	}

	/**
	* Sets the modified date of this step config.
	*
	* @param modifiedDate the modified date of this step config
	*/
	@Override
	public void setModifiedDate(Date modifiedDate) {
		_stepConfig.setModifiedDate(modifiedDate);
	}

	@Override
	public void setNew(boolean n) {
		_stepConfig.setNew(n);
	}

	/**
	* Sets the primary key of this step config.
	*
	* @param primaryKey the primary key of this step config
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_stepConfig.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_stepConfig.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the step code of this step config.
	*
	* @param stepCode the step code of this step config
	*/
	@Override
	public void setStepCode(String stepCode) {
		_stepConfig.setStepCode(stepCode);
	}

	/**
	* Sets the step config ID of this step config.
	*
	* @param stepConfigId the step config ID of this step config
	*/
	@Override
	public void setStepConfigId(long stepConfigId) {
		_stepConfig.setStepConfigId(stepConfigId);
	}

	/**
	* Sets the step name of this step config.
	*
	* @param stepName the step name of this step config
	*/
	@Override
	public void setStepName(String stepName) {
		_stepConfig.setStepName(stepName);
	}

	/**
	* Sets the step type of this step config.
	*
	* @param stepType the step type of this step config
	*/
	@Override
	public void setStepType(int stepType) {
		_stepConfig.setStepType(stepType);
	}

	/**
	* Sets the user ID of this step config.
	*
	* @param userId the user ID of this step config
	*/
	@Override
	public void setUserId(long userId) {
		_stepConfig.setUserId(userId);
	}

	/**
	* Sets the user uuid of this step config.
	*
	* @param userUuid the user uuid of this step config
	*/
	@Override
	public void setUserUuid(String userUuid) {
		_stepConfig.setUserUuid(userUuid);
	}

	/**
	* Sets the uuid of this step config.
	*
	* @param uuid the uuid of this step config
	*/
	@Override
	public void setUuid(String uuid) {
		_stepConfig.setUuid(uuid);
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<StepConfig> toCacheModel() {
		return _stepConfig.toCacheModel();
	}

	@Override
	public StepConfig toEscapedModel() {
		return new StepConfigWrapper(_stepConfig.toEscapedModel());
	}

	@Override
	public String toString() {
		return _stepConfig.toString();
	}

	@Override
	public StepConfig toUnescapedModel() {
		return new StepConfigWrapper(_stepConfig.toUnescapedModel());
	}

	@Override
	public String toXmlString() {
		return _stepConfig.toXmlString();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof StepConfigWrapper)) {
			return false;
		}

		StepConfigWrapper stepConfigWrapper = (StepConfigWrapper)obj;

		if (Objects.equals(_stepConfig, stepConfigWrapper._stepConfig)) {
			return true;
		}

		return false;
	}

	@Override
	public StagedModelType getStagedModelType() {
		return _stepConfig.getStagedModelType();
	}

	@Override
	public StepConfig getWrappedModel() {
		return _stepConfig;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _stepConfig.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _stepConfig.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_stepConfig.resetOriginalValues();
	}

	private final StepConfig _stepConfig;
}