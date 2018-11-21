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

package org.opencps.deliverable.model;

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
 * This class is a wrapper for {@link OpenCPSDeliverableType}.
 * </p>
 *
 * @author binhth
 * @see OpenCPSDeliverableType
 * @generated
 */
@ProviderType
public class OpenCPSDeliverableTypeWrapper implements OpenCPSDeliverableType,
	ModelWrapper<OpenCPSDeliverableType> {
	public OpenCPSDeliverableTypeWrapper(
		OpenCPSDeliverableType openCPSDeliverableType) {
		_openCPSDeliverableType = openCPSDeliverableType;
	}

	@Override
	public Class<?> getModelClass() {
		return OpenCPSDeliverableType.class;
	}

	@Override
	public String getModelClassName() {
		return OpenCPSDeliverableType.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("uuid", getUuid());
		attributes.put("deliverableTypeId", getDeliverableTypeId());
		attributes.put("groupId", getGroupId());
		attributes.put("companyId", getCompanyId());
		attributes.put("userId", getUserId());
		attributes.put("userName", getUserName());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("typeCode", getTypeCode());
		attributes.put("typeName", getTypeName());
		attributes.put("formScriptFileId", getFormScriptFileId());
		attributes.put("formReportFileId", getFormReportFileId());
		attributes.put("codePattern", getCodePattern());
		attributes.put("counter", getCounter());
		attributes.put("mappingData", getMappingData());
		attributes.put("dataConfig", getDataConfig());
		attributes.put("tableConfig", getTableConfig());
		attributes.put("docSync", getDocSync());
		attributes.put("govAgencies", getGovAgencies());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		String uuid = (String)attributes.get("uuid");

		if (uuid != null) {
			setUuid(uuid);
		}

		Long deliverableTypeId = (Long)attributes.get("deliverableTypeId");

		if (deliverableTypeId != null) {
			setDeliverableTypeId(deliverableTypeId);
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

		String typeCode = (String)attributes.get("typeCode");

		if (typeCode != null) {
			setTypeCode(typeCode);
		}

		String typeName = (String)attributes.get("typeName");

		if (typeName != null) {
			setTypeName(typeName);
		}

		Long formScriptFileId = (Long)attributes.get("formScriptFileId");

		if (formScriptFileId != null) {
			setFormScriptFileId(formScriptFileId);
		}

		Long formReportFileId = (Long)attributes.get("formReportFileId");

		if (formReportFileId != null) {
			setFormReportFileId(formReportFileId);
		}

		String codePattern = (String)attributes.get("codePattern");

		if (codePattern != null) {
			setCodePattern(codePattern);
		}

		Long counter = (Long)attributes.get("counter");

		if (counter != null) {
			setCounter(counter);
		}

		String mappingData = (String)attributes.get("mappingData");

		if (mappingData != null) {
			setMappingData(mappingData);
		}

		String dataConfig = (String)attributes.get("dataConfig");

		if (dataConfig != null) {
			setDataConfig(dataConfig);
		}

		String tableConfig = (String)attributes.get("tableConfig");

		if (tableConfig != null) {
			setTableConfig(tableConfig);
		}

		Integer docSync = (Integer)attributes.get("docSync");

		if (docSync != null) {
			setDocSync(docSync);
		}

		String govAgencies = (String)attributes.get("govAgencies");

		if (govAgencies != null) {
			setGovAgencies(govAgencies);
		}
	}

	@Override
	public Object clone() {
		return new OpenCPSDeliverableTypeWrapper((OpenCPSDeliverableType)_openCPSDeliverableType.clone());
	}

	@Override
	public int compareTo(OpenCPSDeliverableType openCPSDeliverableType) {
		return _openCPSDeliverableType.compareTo(openCPSDeliverableType);
	}

	/**
	* Returns the code pattern of this open cps deliverable type.
	*
	* @return the code pattern of this open cps deliverable type
	*/
	@Override
	public String getCodePattern() {
		return _openCPSDeliverableType.getCodePattern();
	}

	/**
	* Returns the company ID of this open cps deliverable type.
	*
	* @return the company ID of this open cps deliverable type
	*/
	@Override
	public long getCompanyId() {
		return _openCPSDeliverableType.getCompanyId();
	}

	/**
	* Returns the counter of this open cps deliverable type.
	*
	* @return the counter of this open cps deliverable type
	*/
	@Override
	public long getCounter() {
		return _openCPSDeliverableType.getCounter();
	}

	/**
	* Returns the create date of this open cps deliverable type.
	*
	* @return the create date of this open cps deliverable type
	*/
	@Override
	public Date getCreateDate() {
		return _openCPSDeliverableType.getCreateDate();
	}

	/**
	* Returns the data config of this open cps deliverable type.
	*
	* @return the data config of this open cps deliverable type
	*/
	@Override
	public String getDataConfig() {
		return _openCPSDeliverableType.getDataConfig();
	}

	/**
	* Returns the deliverable type ID of this open cps deliverable type.
	*
	* @return the deliverable type ID of this open cps deliverable type
	*/
	@Override
	public long getDeliverableTypeId() {
		return _openCPSDeliverableType.getDeliverableTypeId();
	}

	/**
	* Returns the doc sync of this open cps deliverable type.
	*
	* @return the doc sync of this open cps deliverable type
	*/
	@Override
	public int getDocSync() {
		return _openCPSDeliverableType.getDocSync();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _openCPSDeliverableType.getExpandoBridge();
	}

	/**
	* Returns the form report file ID of this open cps deliverable type.
	*
	* @return the form report file ID of this open cps deliverable type
	*/
	@Override
	public long getFormReportFileId() {
		return _openCPSDeliverableType.getFormReportFileId();
	}

	/**
	* Returns the form script file ID of this open cps deliverable type.
	*
	* @return the form script file ID of this open cps deliverable type
	*/
	@Override
	public long getFormScriptFileId() {
		return _openCPSDeliverableType.getFormScriptFileId();
	}

	/**
	* Returns the gov agencies of this open cps deliverable type.
	*
	* @return the gov agencies of this open cps deliverable type
	*/
	@Override
	public String getGovAgencies() {
		return _openCPSDeliverableType.getGovAgencies();
	}

	/**
	* Returns the group ID of this open cps deliverable type.
	*
	* @return the group ID of this open cps deliverable type
	*/
	@Override
	public long getGroupId() {
		return _openCPSDeliverableType.getGroupId();
	}

	/**
	* Returns the mapping data of this open cps deliverable type.
	*
	* @return the mapping data of this open cps deliverable type
	*/
	@Override
	public String getMappingData() {
		return _openCPSDeliverableType.getMappingData();
	}

	/**
	* Returns the modified date of this open cps deliverable type.
	*
	* @return the modified date of this open cps deliverable type
	*/
	@Override
	public Date getModifiedDate() {
		return _openCPSDeliverableType.getModifiedDate();
	}

	/**
	* Returns the primary key of this open cps deliverable type.
	*
	* @return the primary key of this open cps deliverable type
	*/
	@Override
	public long getPrimaryKey() {
		return _openCPSDeliverableType.getPrimaryKey();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _openCPSDeliverableType.getPrimaryKeyObj();
	}

	/**
	* Returns the table config of this open cps deliverable type.
	*
	* @return the table config of this open cps deliverable type
	*/
	@Override
	public String getTableConfig() {
		return _openCPSDeliverableType.getTableConfig();
	}

	/**
	* Returns the type code of this open cps deliverable type.
	*
	* @return the type code of this open cps deliverable type
	*/
	@Override
	public String getTypeCode() {
		return _openCPSDeliverableType.getTypeCode();
	}

	/**
	* Returns the type name of this open cps deliverable type.
	*
	* @return the type name of this open cps deliverable type
	*/
	@Override
	public String getTypeName() {
		return _openCPSDeliverableType.getTypeName();
	}

	/**
	* Returns the user ID of this open cps deliverable type.
	*
	* @return the user ID of this open cps deliverable type
	*/
	@Override
	public long getUserId() {
		return _openCPSDeliverableType.getUserId();
	}

	/**
	* Returns the user name of this open cps deliverable type.
	*
	* @return the user name of this open cps deliverable type
	*/
	@Override
	public String getUserName() {
		return _openCPSDeliverableType.getUserName();
	}

	/**
	* Returns the user uuid of this open cps deliverable type.
	*
	* @return the user uuid of this open cps deliverable type
	*/
	@Override
	public String getUserUuid() {
		return _openCPSDeliverableType.getUserUuid();
	}

	/**
	* Returns the uuid of this open cps deliverable type.
	*
	* @return the uuid of this open cps deliverable type
	*/
	@Override
	public String getUuid() {
		return _openCPSDeliverableType.getUuid();
	}

	@Override
	public int hashCode() {
		return _openCPSDeliverableType.hashCode();
	}

	@Override
	public boolean isCachedModel() {
		return _openCPSDeliverableType.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _openCPSDeliverableType.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _openCPSDeliverableType.isNew();
	}

	@Override
	public void persist() {
		_openCPSDeliverableType.persist();
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_openCPSDeliverableType.setCachedModel(cachedModel);
	}

	/**
	* Sets the code pattern of this open cps deliverable type.
	*
	* @param codePattern the code pattern of this open cps deliverable type
	*/
	@Override
	public void setCodePattern(String codePattern) {
		_openCPSDeliverableType.setCodePattern(codePattern);
	}

	/**
	* Sets the company ID of this open cps deliverable type.
	*
	* @param companyId the company ID of this open cps deliverable type
	*/
	@Override
	public void setCompanyId(long companyId) {
		_openCPSDeliverableType.setCompanyId(companyId);
	}

	/**
	* Sets the counter of this open cps deliverable type.
	*
	* @param counter the counter of this open cps deliverable type
	*/
	@Override
	public void setCounter(long counter) {
		_openCPSDeliverableType.setCounter(counter);
	}

	/**
	* Sets the create date of this open cps deliverable type.
	*
	* @param createDate the create date of this open cps deliverable type
	*/
	@Override
	public void setCreateDate(Date createDate) {
		_openCPSDeliverableType.setCreateDate(createDate);
	}

	/**
	* Sets the data config of this open cps deliverable type.
	*
	* @param dataConfig the data config of this open cps deliverable type
	*/
	@Override
	public void setDataConfig(String dataConfig) {
		_openCPSDeliverableType.setDataConfig(dataConfig);
	}

	/**
	* Sets the deliverable type ID of this open cps deliverable type.
	*
	* @param deliverableTypeId the deliverable type ID of this open cps deliverable type
	*/
	@Override
	public void setDeliverableTypeId(long deliverableTypeId) {
		_openCPSDeliverableType.setDeliverableTypeId(deliverableTypeId);
	}

	/**
	* Sets the doc sync of this open cps deliverable type.
	*
	* @param docSync the doc sync of this open cps deliverable type
	*/
	@Override
	public void setDocSync(int docSync) {
		_openCPSDeliverableType.setDocSync(docSync);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_openCPSDeliverableType.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_openCPSDeliverableType.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_openCPSDeliverableType.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the form report file ID of this open cps deliverable type.
	*
	* @param formReportFileId the form report file ID of this open cps deliverable type
	*/
	@Override
	public void setFormReportFileId(long formReportFileId) {
		_openCPSDeliverableType.setFormReportFileId(formReportFileId);
	}

	/**
	* Sets the form script file ID of this open cps deliverable type.
	*
	* @param formScriptFileId the form script file ID of this open cps deliverable type
	*/
	@Override
	public void setFormScriptFileId(long formScriptFileId) {
		_openCPSDeliverableType.setFormScriptFileId(formScriptFileId);
	}

	/**
	* Sets the gov agencies of this open cps deliverable type.
	*
	* @param govAgencies the gov agencies of this open cps deliverable type
	*/
	@Override
	public void setGovAgencies(String govAgencies) {
		_openCPSDeliverableType.setGovAgencies(govAgencies);
	}

	/**
	* Sets the group ID of this open cps deliverable type.
	*
	* @param groupId the group ID of this open cps deliverable type
	*/
	@Override
	public void setGroupId(long groupId) {
		_openCPSDeliverableType.setGroupId(groupId);
	}

	/**
	* Sets the mapping data of this open cps deliverable type.
	*
	* @param mappingData the mapping data of this open cps deliverable type
	*/
	@Override
	public void setMappingData(String mappingData) {
		_openCPSDeliverableType.setMappingData(mappingData);
	}

	/**
	* Sets the modified date of this open cps deliverable type.
	*
	* @param modifiedDate the modified date of this open cps deliverable type
	*/
	@Override
	public void setModifiedDate(Date modifiedDate) {
		_openCPSDeliverableType.setModifiedDate(modifiedDate);
	}

	@Override
	public void setNew(boolean n) {
		_openCPSDeliverableType.setNew(n);
	}

	/**
	* Sets the primary key of this open cps deliverable type.
	*
	* @param primaryKey the primary key of this open cps deliverable type
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_openCPSDeliverableType.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_openCPSDeliverableType.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the table config of this open cps deliverable type.
	*
	* @param tableConfig the table config of this open cps deliverable type
	*/
	@Override
	public void setTableConfig(String tableConfig) {
		_openCPSDeliverableType.setTableConfig(tableConfig);
	}

	/**
	* Sets the type code of this open cps deliverable type.
	*
	* @param typeCode the type code of this open cps deliverable type
	*/
	@Override
	public void setTypeCode(String typeCode) {
		_openCPSDeliverableType.setTypeCode(typeCode);
	}

	/**
	* Sets the type name of this open cps deliverable type.
	*
	* @param typeName the type name of this open cps deliverable type
	*/
	@Override
	public void setTypeName(String typeName) {
		_openCPSDeliverableType.setTypeName(typeName);
	}

	/**
	* Sets the user ID of this open cps deliverable type.
	*
	* @param userId the user ID of this open cps deliverable type
	*/
	@Override
	public void setUserId(long userId) {
		_openCPSDeliverableType.setUserId(userId);
	}

	/**
	* Sets the user name of this open cps deliverable type.
	*
	* @param userName the user name of this open cps deliverable type
	*/
	@Override
	public void setUserName(String userName) {
		_openCPSDeliverableType.setUserName(userName);
	}

	/**
	* Sets the user uuid of this open cps deliverable type.
	*
	* @param userUuid the user uuid of this open cps deliverable type
	*/
	@Override
	public void setUserUuid(String userUuid) {
		_openCPSDeliverableType.setUserUuid(userUuid);
	}

	/**
	* Sets the uuid of this open cps deliverable type.
	*
	* @param uuid the uuid of this open cps deliverable type
	*/
	@Override
	public void setUuid(String uuid) {
		_openCPSDeliverableType.setUuid(uuid);
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<OpenCPSDeliverableType> toCacheModel() {
		return _openCPSDeliverableType.toCacheModel();
	}

	@Override
	public OpenCPSDeliverableType toEscapedModel() {
		return new OpenCPSDeliverableTypeWrapper(_openCPSDeliverableType.toEscapedModel());
	}

	@Override
	public String toString() {
		return _openCPSDeliverableType.toString();
	}

	@Override
	public OpenCPSDeliverableType toUnescapedModel() {
		return new OpenCPSDeliverableTypeWrapper(_openCPSDeliverableType.toUnescapedModel());
	}

	@Override
	public String toXmlString() {
		return _openCPSDeliverableType.toXmlString();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof OpenCPSDeliverableTypeWrapper)) {
			return false;
		}

		OpenCPSDeliverableTypeWrapper openCPSDeliverableTypeWrapper = (OpenCPSDeliverableTypeWrapper)obj;

		if (Objects.equals(_openCPSDeliverableType,
					openCPSDeliverableTypeWrapper._openCPSDeliverableType)) {
			return true;
		}

		return false;
	}

	@Override
	public StagedModelType getStagedModelType() {
		return _openCPSDeliverableType.getStagedModelType();
	}

	@Override
	public OpenCPSDeliverableType getWrappedModel() {
		return _openCPSDeliverableType;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _openCPSDeliverableType.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _openCPSDeliverableType.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_openCPSDeliverableType.resetOriginalValues();
	}

	private final OpenCPSDeliverableType _openCPSDeliverableType;
}