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

package org.opencps.synctracking.model;

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
 * This class is a wrapper for {@link SyncTracking}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see SyncTracking
 * @generated
 */
@ProviderType
public class SyncTrackingWrapper implements SyncTracking,
	ModelWrapper<SyncTracking> {
	public SyncTrackingWrapper(SyncTracking syncTracking) {
		_syncTracking = syncTracking;
	}

	@Override
	public Class<?> getModelClass() {
		return SyncTracking.class;
	}

	@Override
	public String getModelClassName() {
		return SyncTracking.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("uuid", getUuid());
		attributes.put("trackingId", getTrackingId());
		attributes.put("companyId", getCompanyId());
		attributes.put("groupId", getGroupId());
		attributes.put("userId", getUserId());
		attributes.put("userName", getUserName());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("dossierNo", getDossierNo());
		attributes.put("referenceUid", getReferenceUid());
		attributes.put("serverNo", getServerNo());
		attributes.put("protocol", getProtocol());
		attributes.put("stateSync", getStateSync());
		attributes.put("serviceCode", getServiceCode());
		attributes.put("api", getApi());
		attributes.put("fromUnit", getFromUnit());
		attributes.put("toUnit", getToUnit());
		attributes.put("bodyRequest", getBodyRequest());
		attributes.put("response", getResponse());
		attributes.put("metaData", getMetaData());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		String uuid = (String)attributes.get("uuid");

		if (uuid != null) {
			setUuid(uuid);
		}

		Long trackingId = (Long)attributes.get("trackingId");

		if (trackingId != null) {
			setTrackingId(trackingId);
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

		String dossierNo = (String)attributes.get("dossierNo");

		if (dossierNo != null) {
			setDossierNo(dossierNo);
		}

		String referenceUid = (String)attributes.get("referenceUid");

		if (referenceUid != null) {
			setReferenceUid(referenceUid);
		}

		String serverNo = (String)attributes.get("serverNo");

		if (serverNo != null) {
			setServerNo(serverNo);
		}

		String protocol = (String)attributes.get("protocol");

		if (protocol != null) {
			setProtocol(protocol);
		}

		Integer stateSync = (Integer)attributes.get("stateSync");

		if (stateSync != null) {
			setStateSync(stateSync);
		}

		String serviceCode = (String)attributes.get("serviceCode");

		if (serviceCode != null) {
			setServiceCode(serviceCode);
		}

		String api = (String)attributes.get("api");

		if (api != null) {
			setApi(api);
		}

		String fromUnit = (String)attributes.get("fromUnit");

		if (fromUnit != null) {
			setFromUnit(fromUnit);
		}

		String toUnit = (String)attributes.get("toUnit");

		if (toUnit != null) {
			setToUnit(toUnit);
		}

		String bodyRequest = (String)attributes.get("bodyRequest");

		if (bodyRequest != null) {
			setBodyRequest(bodyRequest);
		}

		String response = (String)attributes.get("response");

		if (response != null) {
			setResponse(response);
		}

		String metaData = (String)attributes.get("metaData");

		if (metaData != null) {
			setMetaData(metaData);
		}
	}

	@Override
	public Object clone() {
		return new SyncTrackingWrapper((SyncTracking)_syncTracking.clone());
	}

	@Override
	public int compareTo(SyncTracking syncTracking) {
		return _syncTracking.compareTo(syncTracking);
	}

	/**
	* Returns the api of this sync tracking.
	*
	* @return the api of this sync tracking
	*/
	@Override
	public String getApi() {
		return _syncTracking.getApi();
	}

	/**
	* Returns the body request of this sync tracking.
	*
	* @return the body request of this sync tracking
	*/
	@Override
	public String getBodyRequest() {
		return _syncTracking.getBodyRequest();
	}

	/**
	* Returns the company ID of this sync tracking.
	*
	* @return the company ID of this sync tracking
	*/
	@Override
	public long getCompanyId() {
		return _syncTracking.getCompanyId();
	}

	/**
	* Returns the create date of this sync tracking.
	*
	* @return the create date of this sync tracking
	*/
	@Override
	public Date getCreateDate() {
		return _syncTracking.getCreateDate();
	}

	/**
	* Returns the dossier no of this sync tracking.
	*
	* @return the dossier no of this sync tracking
	*/
	@Override
	public String getDossierNo() {
		return _syncTracking.getDossierNo();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _syncTracking.getExpandoBridge();
	}

	/**
	* Returns the from unit of this sync tracking.
	*
	* @return the from unit of this sync tracking
	*/
	@Override
	public String getFromUnit() {
		return _syncTracking.getFromUnit();
	}

	/**
	* Returns the group ID of this sync tracking.
	*
	* @return the group ID of this sync tracking
	*/
	@Override
	public long getGroupId() {
		return _syncTracking.getGroupId();
	}

	/**
	* Returns the meta data of this sync tracking.
	*
	* @return the meta data of this sync tracking
	*/
	@Override
	public String getMetaData() {
		return _syncTracking.getMetaData();
	}

	/**
	* Returns the modified date of this sync tracking.
	*
	* @return the modified date of this sync tracking
	*/
	@Override
	public Date getModifiedDate() {
		return _syncTracking.getModifiedDate();
	}

	/**
	* Returns the primary key of this sync tracking.
	*
	* @return the primary key of this sync tracking
	*/
	@Override
	public long getPrimaryKey() {
		return _syncTracking.getPrimaryKey();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _syncTracking.getPrimaryKeyObj();
	}

	/**
	* Returns the protocol of this sync tracking.
	*
	* @return the protocol of this sync tracking
	*/
	@Override
	public String getProtocol() {
		return _syncTracking.getProtocol();
	}

	/**
	* Returns the reference uid of this sync tracking.
	*
	* @return the reference uid of this sync tracking
	*/
	@Override
	public String getReferenceUid() {
		return _syncTracking.getReferenceUid();
	}

	/**
	* Returns the response of this sync tracking.
	*
	* @return the response of this sync tracking
	*/
	@Override
	public String getResponse() {
		return _syncTracking.getResponse();
	}

	/**
	* Returns the server no of this sync tracking.
	*
	* @return the server no of this sync tracking
	*/
	@Override
	public String getServerNo() {
		return _syncTracking.getServerNo();
	}

	/**
	* Returns the service code of this sync tracking.
	*
	* @return the service code of this sync tracking
	*/
	@Override
	public String getServiceCode() {
		return _syncTracking.getServiceCode();
	}

	/**
	* Returns the state sync of this sync tracking.
	*
	* @return the state sync of this sync tracking
	*/
	@Override
	public int getStateSync() {
		return _syncTracking.getStateSync();
	}

	/**
	* Returns the to unit of this sync tracking.
	*
	* @return the to unit of this sync tracking
	*/
	@Override
	public String getToUnit() {
		return _syncTracking.getToUnit();
	}

	/**
	* Returns the tracking ID of this sync tracking.
	*
	* @return the tracking ID of this sync tracking
	*/
	@Override
	public long getTrackingId() {
		return _syncTracking.getTrackingId();
	}

	/**
	* Returns the user ID of this sync tracking.
	*
	* @return the user ID of this sync tracking
	*/
	@Override
	public long getUserId() {
		return _syncTracking.getUserId();
	}

	/**
	* Returns the user name of this sync tracking.
	*
	* @return the user name of this sync tracking
	*/
	@Override
	public String getUserName() {
		return _syncTracking.getUserName();
	}

	/**
	* Returns the user uuid of this sync tracking.
	*
	* @return the user uuid of this sync tracking
	*/
	@Override
	public String getUserUuid() {
		return _syncTracking.getUserUuid();
	}

	/**
	* Returns the uuid of this sync tracking.
	*
	* @return the uuid of this sync tracking
	*/
	@Override
	public String getUuid() {
		return _syncTracking.getUuid();
	}

	@Override
	public int hashCode() {
		return _syncTracking.hashCode();
	}

	@Override
	public boolean isCachedModel() {
		return _syncTracking.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _syncTracking.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _syncTracking.isNew();
	}

	@Override
	public void persist() {
		_syncTracking.persist();
	}

	/**
	* Sets the api of this sync tracking.
	*
	* @param api the api of this sync tracking
	*/
	@Override
	public void setApi(String api) {
		_syncTracking.setApi(api);
	}

	/**
	* Sets the body request of this sync tracking.
	*
	* @param bodyRequest the body request of this sync tracking
	*/
	@Override
	public void setBodyRequest(String bodyRequest) {
		_syncTracking.setBodyRequest(bodyRequest);
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_syncTracking.setCachedModel(cachedModel);
	}

	/**
	* Sets the company ID of this sync tracking.
	*
	* @param companyId the company ID of this sync tracking
	*/
	@Override
	public void setCompanyId(long companyId) {
		_syncTracking.setCompanyId(companyId);
	}

	/**
	* Sets the create date of this sync tracking.
	*
	* @param createDate the create date of this sync tracking
	*/
	@Override
	public void setCreateDate(Date createDate) {
		_syncTracking.setCreateDate(createDate);
	}

	/**
	* Sets the dossier no of this sync tracking.
	*
	* @param dossierNo the dossier no of this sync tracking
	*/
	@Override
	public void setDossierNo(String dossierNo) {
		_syncTracking.setDossierNo(dossierNo);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_syncTracking.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_syncTracking.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_syncTracking.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the from unit of this sync tracking.
	*
	* @param fromUnit the from unit of this sync tracking
	*/
	@Override
	public void setFromUnit(String fromUnit) {
		_syncTracking.setFromUnit(fromUnit);
	}

	/**
	* Sets the group ID of this sync tracking.
	*
	* @param groupId the group ID of this sync tracking
	*/
	@Override
	public void setGroupId(long groupId) {
		_syncTracking.setGroupId(groupId);
	}

	/**
	* Sets the meta data of this sync tracking.
	*
	* @param metaData the meta data of this sync tracking
	*/
	@Override
	public void setMetaData(String metaData) {
		_syncTracking.setMetaData(metaData);
	}

	/**
	* Sets the modified date of this sync tracking.
	*
	* @param modifiedDate the modified date of this sync tracking
	*/
	@Override
	public void setModifiedDate(Date modifiedDate) {
		_syncTracking.setModifiedDate(modifiedDate);
	}

	@Override
	public void setNew(boolean n) {
		_syncTracking.setNew(n);
	}

	/**
	* Sets the primary key of this sync tracking.
	*
	* @param primaryKey the primary key of this sync tracking
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_syncTracking.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_syncTracking.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the protocol of this sync tracking.
	*
	* @param protocol the protocol of this sync tracking
	*/
	@Override
	public void setProtocol(String protocol) {
		_syncTracking.setProtocol(protocol);
	}

	/**
	* Sets the reference uid of this sync tracking.
	*
	* @param referenceUid the reference uid of this sync tracking
	*/
	@Override
	public void setReferenceUid(String referenceUid) {
		_syncTracking.setReferenceUid(referenceUid);
	}

	/**
	* Sets the response of this sync tracking.
	*
	* @param response the response of this sync tracking
	*/
	@Override
	public void setResponse(String response) {
		_syncTracking.setResponse(response);
	}

	/**
	* Sets the server no of this sync tracking.
	*
	* @param serverNo the server no of this sync tracking
	*/
	@Override
	public void setServerNo(String serverNo) {
		_syncTracking.setServerNo(serverNo);
	}

	/**
	* Sets the service code of this sync tracking.
	*
	* @param serviceCode the service code of this sync tracking
	*/
	@Override
	public void setServiceCode(String serviceCode) {
		_syncTracking.setServiceCode(serviceCode);
	}

	/**
	* Sets the state sync of this sync tracking.
	*
	* @param stateSync the state sync of this sync tracking
	*/
	@Override
	public void setStateSync(int stateSync) {
		_syncTracking.setStateSync(stateSync);
	}

	/**
	* Sets the to unit of this sync tracking.
	*
	* @param toUnit the to unit of this sync tracking
	*/
	@Override
	public void setToUnit(String toUnit) {
		_syncTracking.setToUnit(toUnit);
	}

	/**
	* Sets the tracking ID of this sync tracking.
	*
	* @param trackingId the tracking ID of this sync tracking
	*/
	@Override
	public void setTrackingId(long trackingId) {
		_syncTracking.setTrackingId(trackingId);
	}

	/**
	* Sets the user ID of this sync tracking.
	*
	* @param userId the user ID of this sync tracking
	*/
	@Override
	public void setUserId(long userId) {
		_syncTracking.setUserId(userId);
	}

	/**
	* Sets the user name of this sync tracking.
	*
	* @param userName the user name of this sync tracking
	*/
	@Override
	public void setUserName(String userName) {
		_syncTracking.setUserName(userName);
	}

	/**
	* Sets the user uuid of this sync tracking.
	*
	* @param userUuid the user uuid of this sync tracking
	*/
	@Override
	public void setUserUuid(String userUuid) {
		_syncTracking.setUserUuid(userUuid);
	}

	/**
	* Sets the uuid of this sync tracking.
	*
	* @param uuid the uuid of this sync tracking
	*/
	@Override
	public void setUuid(String uuid) {
		_syncTracking.setUuid(uuid);
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<SyncTracking> toCacheModel() {
		return _syncTracking.toCacheModel();
	}

	@Override
	public SyncTracking toEscapedModel() {
		return new SyncTrackingWrapper(_syncTracking.toEscapedModel());
	}

	@Override
	public String toString() {
		return _syncTracking.toString();
	}

	@Override
	public SyncTracking toUnescapedModel() {
		return new SyncTrackingWrapper(_syncTracking.toUnescapedModel());
	}

	@Override
	public String toXmlString() {
		return _syncTracking.toXmlString();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof SyncTrackingWrapper)) {
			return false;
		}

		SyncTrackingWrapper syncTrackingWrapper = (SyncTrackingWrapper)obj;

		if (Objects.equals(_syncTracking, syncTrackingWrapper._syncTracking)) {
			return true;
		}

		return false;
	}

	@Override
	public StagedModelType getStagedModelType() {
		return _syncTracking.getStagedModelType();
	}

	@Override
	public SyncTracking getWrappedModel() {
		return _syncTracking;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _syncTracking.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _syncTracking.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_syncTracking.resetOriginalValues();
	}

	private final SyncTracking _syncTracking;
}