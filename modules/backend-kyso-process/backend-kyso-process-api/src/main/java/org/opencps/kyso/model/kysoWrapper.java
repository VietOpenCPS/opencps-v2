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

package org.opencps.kyso.model;

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
 * This class is a wrapper for {@link kyso}.
 * </p>
 *
 * @author Binhth
 * @see kyso
 * @generated
 */
@ProviderType
public class kysoWrapper implements kyso, ModelWrapper<kyso> {
	public kysoWrapper(kyso kyso) {
		_kyso = kyso;
	}

	@Override
	public Class<?> getModelClass() {
		return kyso.class;
	}

	@Override
	public String getModelClassName() {
		return kyso.class.getName();
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
	public boolean isCachedModel() {
		return _kyso.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _kyso.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _kyso.isNew();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _kyso.getExpandoBridge();
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<org.opencps.kyso.model.kyso> toCacheModel() {
		return _kyso.toCacheModel();
	}

	@Override
	public int compareTo(org.opencps.kyso.model.kyso kyso) {
		return _kyso.compareTo(kyso);
	}

	@Override
	public int hashCode() {
		return _kyso.hashCode();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _kyso.getPrimaryKeyObj();
	}

	@Override
	public java.lang.Object clone() {
		return new kysoWrapper((kyso)_kyso.clone());
	}

	/**
	* Returns the uuid of this kyso.
	*
	* @return the uuid of this kyso
	*/
	@Override
	public java.lang.String getUuid() {
		return _kyso.getUuid();
	}

	@Override
	public java.lang.String toString() {
		return _kyso.toString();
	}

	@Override
	public java.lang.String toXmlString() {
		return _kyso.toXmlString();
	}

	/**
	* Returns the jasper ID of this kyso.
	*
	* @return the jasper ID of this kyso
	*/
	@Override
	public long getJasperId() {
		return _kyso.getJasperId();
	}

	/**
	* Returns the primary key of this kyso.
	*
	* @return the primary key of this kyso
	*/
	@Override
	public long getPrimaryKey() {
		return _kyso.getPrimaryKey();
	}

	@Override
	public org.opencps.kyso.model.kyso toEscapedModel() {
		return new kysoWrapper(_kyso.toEscapedModel());
	}

	@Override
	public org.opencps.kyso.model.kyso toUnescapedModel() {
		return new kysoWrapper(_kyso.toUnescapedModel());
	}

	@Override
	public void persist() {
		_kyso.persist();
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_kyso.setCachedModel(cachedModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_kyso.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_kyso.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_kyso.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the jasper ID of this kyso.
	*
	* @param jasperId the jasper ID of this kyso
	*/
	@Override
	public void setJasperId(long jasperId) {
		_kyso.setJasperId(jasperId);
	}

	@Override
	public void setNew(boolean n) {
		_kyso.setNew(n);
	}

	/**
	* Sets the primary key of this kyso.
	*
	* @param primaryKey the primary key of this kyso
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_kyso.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_kyso.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the uuid of this kyso.
	*
	* @param uuid the uuid of this kyso
	*/
	@Override
	public void setUuid(java.lang.String uuid) {
		_kyso.setUuid(uuid);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof kysoWrapper)) {
			return false;
		}

		kysoWrapper kysoWrapper = (kysoWrapper)obj;

		if (Objects.equals(_kyso, kysoWrapper._kyso)) {
			return true;
		}

		return false;
	}

	@Override
	public kyso getWrappedModel() {
		return _kyso;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _kyso.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _kyso.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_kyso.resetOriginalValues();
	}

	private final kyso _kyso;
}