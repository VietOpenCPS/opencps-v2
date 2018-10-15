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
 * This class is a wrapper for {@link DeliverableType}.
 * </p>
 *
 * @author huymq
 * @see DeliverableType
 * @generated
 */
@ProviderType
public class DeliverableTypeWrapper implements DeliverableType,
	ModelWrapper<DeliverableType> {
	public DeliverableTypeWrapper(DeliverableType deliverableType) {
		_deliverableType = deliverableType;
	}

	@Override
	public Class<?> getModelClass() {
		return DeliverableType.class;
	}

	@Override
	public String getModelClassName() {
		return DeliverableType.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("uuid", getUuid());
		attributes.put("deliverableTypeId", getDeliverableTypeId());
		attributes.put("companyId", getCompanyId());
		attributes.put("groupId", getGroupId());
		attributes.put("userId", getUserId());
		attributes.put("userName", getUserName());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("typeCode", getTypeCode());
		attributes.put("typeName", getTypeName());
		attributes.put("formScript", getFormScript());
		attributes.put("formReport", getFormReport());
		attributes.put("codePattern", getCodePattern());
		attributes.put("counter", getCounter());
		attributes.put("mappingData", getMappingData());
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

		String typeCode = (String)attributes.get("typeCode");

		if (typeCode != null) {
			setTypeCode(typeCode);
		}

		String typeName = (String)attributes.get("typeName");

		if (typeName != null) {
			setTypeName(typeName);
		}

		String formScript = (String)attributes.get("formScript");

		if (formScript != null) {
			setFormScript(formScript);
		}

		String formReport = (String)attributes.get("formReport");

		if (formReport != null) {
			setFormReport(formReport);
		}

		String codePattern = (String)attributes.get("codePattern");

		if (codePattern != null) {
			setCodePattern(codePattern);
		}

		String counter = (String)attributes.get("counter");

		if (counter != null) {
			setCounter(counter);
		}

		String mappingData = (String)attributes.get("mappingData");

		if (mappingData != null) {
			setMappingData(mappingData);
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
		return new DeliverableTypeWrapper((DeliverableType)_deliverableType.clone());
	}

	@Override
	public int compareTo(DeliverableType deliverableType) {
		return _deliverableType.compareTo(deliverableType);
	}

	/**
	* Returns the code pattern of this deliverable type.
	*
	* @return the code pattern of this deliverable type
	*/
	@Override
	public String getCodePattern() {
		return _deliverableType.getCodePattern();
	}

	/**
	* Returns the company ID of this deliverable type.
	*
	* @return the company ID of this deliverable type
	*/
	@Override
	public long getCompanyId() {
		return _deliverableType.getCompanyId();
	}

	/**
	* Returns the counter of this deliverable type.
	*
	* @return the counter of this deliverable type
	*/
	@Override
	public String getCounter() {
		return _deliverableType.getCounter();
	}

	/**
	* Returns the create date of this deliverable type.
	*
	* @return the create date of this deliverable type
	*/
	@Override
	public Date getCreateDate() {
		return _deliverableType.getCreateDate();
	}

	/**
	* Returns the deliverable type ID of this deliverable type.
	*
	* @return the deliverable type ID of this deliverable type
	*/
	@Override
	public long getDeliverableTypeId() {
		return _deliverableType.getDeliverableTypeId();
	}

	/**
	* Returns the doc sync of this deliverable type.
	*
	* @return the doc sync of this deliverable type
	*/
	@Override
	public int getDocSync() {
		return _deliverableType.getDocSync();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _deliverableType.getExpandoBridge();
	}

	/**
	* Returns the form report of this deliverable type.
	*
	* @return the form report of this deliverable type
	*/
	@Override
	public String getFormReport() {
		return _deliverableType.getFormReport();
	}

	/**
	* Returns the form script of this deliverable type.
	*
	* @return the form script of this deliverable type
	*/
	@Override
	public String getFormScript() {
		return _deliverableType.getFormScript();
	}

	/**
	* Returns the gov agencies of this deliverable type.
	*
	* @return the gov agencies of this deliverable type
	*/
	@Override
	public String getGovAgencies() {
		return _deliverableType.getGovAgencies();
	}

	/**
	* Returns the group ID of this deliverable type.
	*
	* @return the group ID of this deliverable type
	*/
	@Override
	public long getGroupId() {
		return _deliverableType.getGroupId();
	}

	/**
	* Returns the mapping data of this deliverable type.
	*
	* @return the mapping data of this deliverable type
	*/
	@Override
	public String getMappingData() {
		return _deliverableType.getMappingData();
	}

	/**
	* Returns the modified date of this deliverable type.
	*
	* @return the modified date of this deliverable type
	*/
	@Override
	public Date getModifiedDate() {
		return _deliverableType.getModifiedDate();
	}

	/**
	* Returns the primary key of this deliverable type.
	*
	* @return the primary key of this deliverable type
	*/
	@Override
	public long getPrimaryKey() {
		return _deliverableType.getPrimaryKey();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _deliverableType.getPrimaryKeyObj();
	}

	/**
	* Returns the type code of this deliverable type.
	*
	* @return the type code of this deliverable type
	*/
	@Override
	public String getTypeCode() {
		return _deliverableType.getTypeCode();
	}

	/**
	* Returns the type name of this deliverable type.
	*
	* @return the type name of this deliverable type
	*/
	@Override
	public String getTypeName() {
		return _deliverableType.getTypeName();
	}

	/**
	* Returns the user ID of this deliverable type.
	*
	* @return the user ID of this deliverable type
	*/
	@Override
	public long getUserId() {
		return _deliverableType.getUserId();
	}

	/**
	* Returns the user name of this deliverable type.
	*
	* @return the user name of this deliverable type
	*/
	@Override
	public String getUserName() {
		return _deliverableType.getUserName();
	}

	/**
	* Returns the user uuid of this deliverable type.
	*
	* @return the user uuid of this deliverable type
	*/
	@Override
	public String getUserUuid() {
		return _deliverableType.getUserUuid();
	}

	/**
	* Returns the uuid of this deliverable type.
	*
	* @return the uuid of this deliverable type
	*/
	@Override
	public String getUuid() {
		return _deliverableType.getUuid();
	}

	@Override
	public int hashCode() {
		return _deliverableType.hashCode();
	}

	@Override
	public boolean isCachedModel() {
		return _deliverableType.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _deliverableType.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _deliverableType.isNew();
	}

	@Override
	public void persist() {
		_deliverableType.persist();
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_deliverableType.setCachedModel(cachedModel);
	}

	/**
	* Sets the code pattern of this deliverable type.
	*
	* @param codePattern the code pattern of this deliverable type
	*/
	@Override
	public void setCodePattern(String codePattern) {
		_deliverableType.setCodePattern(codePattern);
	}

	/**
	* Sets the company ID of this deliverable type.
	*
	* @param companyId the company ID of this deliverable type
	*/
	@Override
	public void setCompanyId(long companyId) {
		_deliverableType.setCompanyId(companyId);
	}

	/**
	* Sets the counter of this deliverable type.
	*
	* @param counter the counter of this deliverable type
	*/
	@Override
	public void setCounter(String counter) {
		_deliverableType.setCounter(counter);
	}

	/**
	* Sets the create date of this deliverable type.
	*
	* @param createDate the create date of this deliverable type
	*/
	@Override
	public void setCreateDate(Date createDate) {
		_deliverableType.setCreateDate(createDate);
	}

	/**
	* Sets the deliverable type ID of this deliverable type.
	*
	* @param deliverableTypeId the deliverable type ID of this deliverable type
	*/
	@Override
	public void setDeliverableTypeId(long deliverableTypeId) {
		_deliverableType.setDeliverableTypeId(deliverableTypeId);
	}

	/**
	* Sets the doc sync of this deliverable type.
	*
	* @param docSync the doc sync of this deliverable type
	*/
	@Override
	public void setDocSync(int docSync) {
		_deliverableType.setDocSync(docSync);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_deliverableType.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_deliverableType.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_deliverableType.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the form report of this deliverable type.
	*
	* @param formReport the form report of this deliverable type
	*/
	@Override
	public void setFormReport(String formReport) {
		_deliverableType.setFormReport(formReport);
	}

	/**
	* Sets the form script of this deliverable type.
	*
	* @param formScript the form script of this deliverable type
	*/
	@Override
	public void setFormScript(String formScript) {
		_deliverableType.setFormScript(formScript);
	}

	/**
	* Sets the gov agencies of this deliverable type.
	*
	* @param govAgencies the gov agencies of this deliverable type
	*/
	@Override
	public void setGovAgencies(String govAgencies) {
		_deliverableType.setGovAgencies(govAgencies);
	}

	/**
	* Sets the group ID of this deliverable type.
	*
	* @param groupId the group ID of this deliverable type
	*/
	@Override
	public void setGroupId(long groupId) {
		_deliverableType.setGroupId(groupId);
	}

	/**
	* Sets the mapping data of this deliverable type.
	*
	* @param mappingData the mapping data of this deliverable type
	*/
	@Override
	public void setMappingData(String mappingData) {
		_deliverableType.setMappingData(mappingData);
	}

	/**
	* Sets the modified date of this deliverable type.
	*
	* @param modifiedDate the modified date of this deliverable type
	*/
	@Override
	public void setModifiedDate(Date modifiedDate) {
		_deliverableType.setModifiedDate(modifiedDate);
	}

	@Override
	public void setNew(boolean n) {
		_deliverableType.setNew(n);
	}

	/**
	* Sets the primary key of this deliverable type.
	*
	* @param primaryKey the primary key of this deliverable type
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_deliverableType.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_deliverableType.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the type code of this deliverable type.
	*
	* @param typeCode the type code of this deliverable type
	*/
	@Override
	public void setTypeCode(String typeCode) {
		_deliverableType.setTypeCode(typeCode);
	}

	/**
	* Sets the type name of this deliverable type.
	*
	* @param typeName the type name of this deliverable type
	*/
	@Override
	public void setTypeName(String typeName) {
		_deliverableType.setTypeName(typeName);
	}

	/**
	* Sets the user ID of this deliverable type.
	*
	* @param userId the user ID of this deliverable type
	*/
	@Override
	public void setUserId(long userId) {
		_deliverableType.setUserId(userId);
	}

	/**
	* Sets the user name of this deliverable type.
	*
	* @param userName the user name of this deliverable type
	*/
	@Override
	public void setUserName(String userName) {
		_deliverableType.setUserName(userName);
	}

	/**
	* Sets the user uuid of this deliverable type.
	*
	* @param userUuid the user uuid of this deliverable type
	*/
	@Override
	public void setUserUuid(String userUuid) {
		_deliverableType.setUserUuid(userUuid);
	}

	/**
	* Sets the uuid of this deliverable type.
	*
	* @param uuid the uuid of this deliverable type
	*/
	@Override
	public void setUuid(String uuid) {
		_deliverableType.setUuid(uuid);
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<DeliverableType> toCacheModel() {
		return _deliverableType.toCacheModel();
	}

	@Override
	public DeliverableType toEscapedModel() {
		return new DeliverableTypeWrapper(_deliverableType.toEscapedModel());
	}

	@Override
	public String toString() {
		return _deliverableType.toString();
	}

	@Override
	public DeliverableType toUnescapedModel() {
		return new DeliverableTypeWrapper(_deliverableType.toUnescapedModel());
	}

	@Override
	public String toXmlString() {
		return _deliverableType.toXmlString();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof DeliverableTypeWrapper)) {
			return false;
		}

		DeliverableTypeWrapper deliverableTypeWrapper = (DeliverableTypeWrapper)obj;

		if (Objects.equals(_deliverableType,
					deliverableTypeWrapper._deliverableType)) {
			return true;
		}

		return false;
	}

	@Override
	public StagedModelType getStagedModelType() {
		return _deliverableType.getStagedModelType();
	}

	@Override
	public DeliverableType getWrappedModel() {
		return _deliverableType;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _deliverableType.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _deliverableType.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_deliverableType.resetOriginalValues();
	}

	private final DeliverableType _deliverableType;
}