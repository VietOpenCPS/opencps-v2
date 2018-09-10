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

package org.opencps.jasper.model;

import aQute.bnd.annotation.ProviderType;

import com.liferay.expando.kernel.model.ExpandoBridge;

import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.service.ServiceContext;

import java.io.Serializable;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * <p>
 * This class is a wrapper for {@link jasper}.
 * </p>
 *
 * @author Binhth
 * @see jasper
 * @generated
 */
@ProviderType
public class jasperWrapper implements jasper, ModelWrapper<jasper> {
	public jasperWrapper(jasper jasper) {
		_jasper = jasper;
	}

	@Override
	public Class<?> getModelClass() {
		return jasper.class;
	}

	@Override
	public String getModelClassName() {
		return jasper.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("uuid", getUuid());
		attributes.put("jasperId", getJasperId());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		String uuid = (String)attributes.get("uuid");

		if (uuid != null) {
			setUuid(uuid);
		}

		Long jasperId = (Long)attributes.get("jasperId");

		if (jasperId != null) {
			setJasperId(jasperId);
		}
	}

	@Override
	public Object clone() {
		return new jasperWrapper((jasper)_jasper.clone());
	}

	@Override
	public int compareTo(org.opencps.jasper.model.jasper jasper) {
		return _jasper.compareTo(jasper);
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _jasper.getExpandoBridge();
	}

	/**
	* Returns the jasper ID of this jasper.
	*
	* @return the jasper ID of this jasper
	*/
	@Override
	public long getJasperId() {
		return _jasper.getJasperId();
	}

	/**
	* Returns the primary key of this jasper.
	*
	* @return the primary key of this jasper
	*/
	@Override
	public long getPrimaryKey() {
		return _jasper.getPrimaryKey();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _jasper.getPrimaryKeyObj();
	}

	/**
	* Returns the uuid of this jasper.
	*
	* @return the uuid of this jasper
	*/
	@Override
	public String getUuid() {
		return _jasper.getUuid();
	}

	@Override
	public int hashCode() {
		return _jasper.hashCode();
	}

	@Override
	public boolean isCachedModel() {
		return _jasper.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _jasper.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _jasper.isNew();
	}

	@Override
	public void persist() {
		_jasper.persist();
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_jasper.setCachedModel(cachedModel);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_jasper.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_jasper.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_jasper.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the jasper ID of this jasper.
	*
	* @param jasperId the jasper ID of this jasper
	*/
	@Override
	public void setJasperId(long jasperId) {
		_jasper.setJasperId(jasperId);
	}

	@Override
	public void setNew(boolean n) {
		_jasper.setNew(n);
	}

	/**
	* Sets the primary key of this jasper.
	*
	* @param primaryKey the primary key of this jasper
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_jasper.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_jasper.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the uuid of this jasper.
	*
	* @param uuid the uuid of this jasper
	*/
	@Override
	public void setUuid(String uuid) {
		_jasper.setUuid(uuid);
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<org.opencps.jasper.model.jasper> toCacheModel() {
		return _jasper.toCacheModel();
	}

	@Override
	public org.opencps.jasper.model.jasper toEscapedModel() {
		return new jasperWrapper(_jasper.toEscapedModel());
	}

	@Override
	public String toString() {
		return _jasper.toString();
	}

	@Override
	public org.opencps.jasper.model.jasper toUnescapedModel() {
		return new jasperWrapper(_jasper.toUnescapedModel());
	}

	@Override
	public String toXmlString() {
		return _jasper.toXmlString();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof jasperWrapper)) {
			return false;
		}

		jasperWrapper jasperWrapper = (jasperWrapper)obj;

		if (Objects.equals(_jasper, jasperWrapper._jasper)) {
			return true;
		}

		return false;
	}

	@Override
	public jasper getWrappedModel() {
		return _jasper;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _jasper.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _jasper.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_jasper.resetOriginalValues();
	}

	private final jasper _jasper;
}