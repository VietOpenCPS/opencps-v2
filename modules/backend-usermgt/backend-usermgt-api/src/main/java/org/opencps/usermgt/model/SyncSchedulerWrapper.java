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

package org.opencps.usermgt.model;

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
 * This class is a wrapper for {@link SyncScheduler}.
 * </p>
 *
 * @author khoavu
 * @see SyncScheduler
 * @generated
 */
@ProviderType
public class SyncSchedulerWrapper implements SyncScheduler,
	ModelWrapper<SyncScheduler> {
	public SyncSchedulerWrapper(SyncScheduler syncScheduler) {
		_syncScheduler = syncScheduler;
	}

	@Override
	public Class<?> getModelClass() {
		return SyncScheduler.class;
	}

	@Override
	public String getModelClassName() {
		return SyncScheduler.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("uuid", getUuid());
		attributes.put("syncSchedulerId", getSyncSchedulerId());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("className", getClassName());
		attributes.put("typeCode", getTypeCode());
		attributes.put("syncDate", getSyncDate());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		String uuid = (String)attributes.get("uuid");

		if (uuid != null) {
			setUuid(uuid);
		}

		Long syncSchedulerId = (Long)attributes.get("syncSchedulerId");

		if (syncSchedulerId != null) {
			setSyncSchedulerId(syncSchedulerId);
		}

		Date createDate = (Date)attributes.get("createDate");

		if (createDate != null) {
			setCreateDate(createDate);
		}

		Date modifiedDate = (Date)attributes.get("modifiedDate");

		if (modifiedDate != null) {
			setModifiedDate(modifiedDate);
		}

		String className = (String)attributes.get("className");

		if (className != null) {
			setClassName(className);
		}

		String typeCode = (String)attributes.get("typeCode");

		if (typeCode != null) {
			setTypeCode(typeCode);
		}

		Date syncDate = (Date)attributes.get("syncDate");

		if (syncDate != null) {
			setSyncDate(syncDate);
		}
	}

	@Override
	public Object clone() {
		return new SyncSchedulerWrapper((SyncScheduler)_syncScheduler.clone());
	}

	@Override
	public int compareTo(SyncScheduler syncScheduler) {
		return _syncScheduler.compareTo(syncScheduler);
	}

	/**
	* Returns the class name of this sync scheduler.
	*
	* @return the class name of this sync scheduler
	*/
	@Override
	public String getClassName() {
		return _syncScheduler.getClassName();
	}

	/**
	* Returns the create date of this sync scheduler.
	*
	* @return the create date of this sync scheduler
	*/
	@Override
	public Date getCreateDate() {
		return _syncScheduler.getCreateDate();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _syncScheduler.getExpandoBridge();
	}

	/**
	* Returns the modified date of this sync scheduler.
	*
	* @return the modified date of this sync scheduler
	*/
	@Override
	public Date getModifiedDate() {
		return _syncScheduler.getModifiedDate();
	}

	/**
	* Returns the primary key of this sync scheduler.
	*
	* @return the primary key of this sync scheduler
	*/
	@Override
	public long getPrimaryKey() {
		return _syncScheduler.getPrimaryKey();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _syncScheduler.getPrimaryKeyObj();
	}

	/**
	* Returns the sync date of this sync scheduler.
	*
	* @return the sync date of this sync scheduler
	*/
	@Override
	public Date getSyncDate() {
		return _syncScheduler.getSyncDate();
	}

	/**
	* Returns the sync scheduler ID of this sync scheduler.
	*
	* @return the sync scheduler ID of this sync scheduler
	*/
	@Override
	public long getSyncSchedulerId() {
		return _syncScheduler.getSyncSchedulerId();
	}

	/**
	* Returns the type code of this sync scheduler.
	*
	* @return the type code of this sync scheduler
	*/
	@Override
	public String getTypeCode() {
		return _syncScheduler.getTypeCode();
	}

	/**
	* Returns the uuid of this sync scheduler.
	*
	* @return the uuid of this sync scheduler
	*/
	@Override
	public String getUuid() {
		return _syncScheduler.getUuid();
	}

	@Override
	public int hashCode() {
		return _syncScheduler.hashCode();
	}

	@Override
	public boolean isCachedModel() {
		return _syncScheduler.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _syncScheduler.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _syncScheduler.isNew();
	}

	@Override
	public void persist() {
		_syncScheduler.persist();
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_syncScheduler.setCachedModel(cachedModel);
	}

	/**
	* Sets the class name of this sync scheduler.
	*
	* @param className the class name of this sync scheduler
	*/
	@Override
	public void setClassName(String className) {
		_syncScheduler.setClassName(className);
	}

	/**
	* Sets the create date of this sync scheduler.
	*
	* @param createDate the create date of this sync scheduler
	*/
	@Override
	public void setCreateDate(Date createDate) {
		_syncScheduler.setCreateDate(createDate);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_syncScheduler.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_syncScheduler.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_syncScheduler.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the modified date of this sync scheduler.
	*
	* @param modifiedDate the modified date of this sync scheduler
	*/
	@Override
	public void setModifiedDate(Date modifiedDate) {
		_syncScheduler.setModifiedDate(modifiedDate);
	}

	@Override
	public void setNew(boolean n) {
		_syncScheduler.setNew(n);
	}

	/**
	* Sets the primary key of this sync scheduler.
	*
	* @param primaryKey the primary key of this sync scheduler
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_syncScheduler.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_syncScheduler.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the sync date of this sync scheduler.
	*
	* @param syncDate the sync date of this sync scheduler
	*/
	@Override
	public void setSyncDate(Date syncDate) {
		_syncScheduler.setSyncDate(syncDate);
	}

	/**
	* Sets the sync scheduler ID of this sync scheduler.
	*
	* @param syncSchedulerId the sync scheduler ID of this sync scheduler
	*/
	@Override
	public void setSyncSchedulerId(long syncSchedulerId) {
		_syncScheduler.setSyncSchedulerId(syncSchedulerId);
	}

	/**
	* Sets the type code of this sync scheduler.
	*
	* @param typeCode the type code of this sync scheduler
	*/
	@Override
	public void setTypeCode(String typeCode) {
		_syncScheduler.setTypeCode(typeCode);
	}

	/**
	* Sets the uuid of this sync scheduler.
	*
	* @param uuid the uuid of this sync scheduler
	*/
	@Override
	public void setUuid(String uuid) {
		_syncScheduler.setUuid(uuid);
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<SyncScheduler> toCacheModel() {
		return _syncScheduler.toCacheModel();
	}

	@Override
	public SyncScheduler toEscapedModel() {
		return new SyncSchedulerWrapper(_syncScheduler.toEscapedModel());
	}

	@Override
	public String toString() {
		return _syncScheduler.toString();
	}

	@Override
	public SyncScheduler toUnescapedModel() {
		return new SyncSchedulerWrapper(_syncScheduler.toUnescapedModel());
	}

	@Override
	public String toXmlString() {
		return _syncScheduler.toXmlString();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof SyncSchedulerWrapper)) {
			return false;
		}

		SyncSchedulerWrapper syncSchedulerWrapper = (SyncSchedulerWrapper)obj;

		if (Objects.equals(_syncScheduler, syncSchedulerWrapper._syncScheduler)) {
			return true;
		}

		return false;
	}

	@Override
	public SyncScheduler getWrappedModel() {
		return _syncScheduler;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _syncScheduler.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _syncScheduler.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_syncScheduler.resetOriginalValues();
	}

	private final SyncScheduler _syncScheduler;
}