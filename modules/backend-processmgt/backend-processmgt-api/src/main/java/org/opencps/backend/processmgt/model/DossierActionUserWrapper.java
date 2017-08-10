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

import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.service.ServiceContext;

import java.io.Serializable;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * <p>
 * This class is a wrapper for {@link DossierActionUser}.
 * </p>
 *
 * @author khoavu
 * @see DossierActionUser
 * @generated
 */
@ProviderType
public class DossierActionUserWrapper implements DossierActionUser,
	ModelWrapper<DossierActionUser> {
	public DossierActionUserWrapper(DossierActionUser dossierActionUser) {
		_dossierActionUser = dossierActionUser;
	}

	@Override
	public Class<?> getModelClass() {
		return DossierActionUser.class;
	}

	@Override
	public String getModelClassName() {
		return DossierActionUser.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("uuid", getUuid());
		attributes.put("dossierActionId", getDossierActionId());
		attributes.put("userId", getUserId());
		attributes.put("moderator", getModerator());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		String uuid = (String)attributes.get("uuid");

		if (uuid != null) {
			setUuid(uuid);
		}

		Long dossierActionId = (Long)attributes.get("dossierActionId");

		if (dossierActionId != null) {
			setDossierActionId(dossierActionId);
		}

		Long userId = (Long)attributes.get("userId");

		if (userId != null) {
			setUserId(userId);
		}

		Integer moderator = (Integer)attributes.get("moderator");

		if (moderator != null) {
			setModerator(moderator);
		}
	}

	@Override
	public DossierActionUser toEscapedModel() {
		return new DossierActionUserWrapper(_dossierActionUser.toEscapedModel());
	}

	@Override
	public DossierActionUser toUnescapedModel() {
		return new DossierActionUserWrapper(_dossierActionUser.toUnescapedModel());
	}

	@Override
	public boolean isCachedModel() {
		return _dossierActionUser.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _dossierActionUser.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _dossierActionUser.isNew();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _dossierActionUser.getExpandoBridge();
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<DossierActionUser> toCacheModel() {
		return _dossierActionUser.toCacheModel();
	}

	@Override
	public int compareTo(DossierActionUser dossierActionUser) {
		return _dossierActionUser.compareTo(dossierActionUser);
	}

	/**
	* Returns the moderator of this dossier action user.
	*
	* @return the moderator of this dossier action user
	*/
	@Override
	public int getModerator() {
		return _dossierActionUser.getModerator();
	}

	@Override
	public int hashCode() {
		return _dossierActionUser.hashCode();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _dossierActionUser.getPrimaryKeyObj();
	}

	@Override
	public java.lang.Object clone() {
		return new DossierActionUserWrapper((DossierActionUser)_dossierActionUser.clone());
	}

	/**
	* Returns the user uuid of this dossier action user.
	*
	* @return the user uuid of this dossier action user
	*/
	@Override
	public java.lang.String getUserUuid() {
		return _dossierActionUser.getUserUuid();
	}

	/**
	* Returns the uuid of this dossier action user.
	*
	* @return the uuid of this dossier action user
	*/
	@Override
	public java.lang.String getUuid() {
		return _dossierActionUser.getUuid();
	}

	@Override
	public java.lang.String toString() {
		return _dossierActionUser.toString();
	}

	@Override
	public java.lang.String toXmlString() {
		return _dossierActionUser.toXmlString();
	}

	/**
	* Returns the dossier action ID of this dossier action user.
	*
	* @return the dossier action ID of this dossier action user
	*/
	@Override
	public long getDossierActionId() {
		return _dossierActionUser.getDossierActionId();
	}

	/**
	* Returns the user ID of this dossier action user.
	*
	* @return the user ID of this dossier action user
	*/
	@Override
	public long getUserId() {
		return _dossierActionUser.getUserId();
	}

	/**
	* Returns the primary key of this dossier action user.
	*
	* @return the primary key of this dossier action user
	*/
	@Override
	public org.opencps.backend.processmgt.service.persistence.DossierActionUserPK getPrimaryKey() {
		return _dossierActionUser.getPrimaryKey();
	}

	@Override
	public void persist() {
		_dossierActionUser.persist();
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_dossierActionUser.setCachedModel(cachedModel);
	}

	/**
	* Sets the dossier action ID of this dossier action user.
	*
	* @param dossierActionId the dossier action ID of this dossier action user
	*/
	@Override
	public void setDossierActionId(long dossierActionId) {
		_dossierActionUser.setDossierActionId(dossierActionId);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_dossierActionUser.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_dossierActionUser.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_dossierActionUser.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the moderator of this dossier action user.
	*
	* @param moderator the moderator of this dossier action user
	*/
	@Override
	public void setModerator(int moderator) {
		_dossierActionUser.setModerator(moderator);
	}

	@Override
	public void setNew(boolean n) {
		_dossierActionUser.setNew(n);
	}

	/**
	* Sets the primary key of this dossier action user.
	*
	* @param primaryKey the primary key of this dossier action user
	*/
	@Override
	public void setPrimaryKey(
		org.opencps.backend.processmgt.service.persistence.DossierActionUserPK primaryKey) {
		_dossierActionUser.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_dossierActionUser.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the user ID of this dossier action user.
	*
	* @param userId the user ID of this dossier action user
	*/
	@Override
	public void setUserId(long userId) {
		_dossierActionUser.setUserId(userId);
	}

	/**
	* Sets the user uuid of this dossier action user.
	*
	* @param userUuid the user uuid of this dossier action user
	*/
	@Override
	public void setUserUuid(java.lang.String userUuid) {
		_dossierActionUser.setUserUuid(userUuid);
	}

	/**
	* Sets the uuid of this dossier action user.
	*
	* @param uuid the uuid of this dossier action user
	*/
	@Override
	public void setUuid(java.lang.String uuid) {
		_dossierActionUser.setUuid(uuid);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof DossierActionUserWrapper)) {
			return false;
		}

		DossierActionUserWrapper dossierActionUserWrapper = (DossierActionUserWrapper)obj;

		if (Objects.equals(_dossierActionUser,
					dossierActionUserWrapper._dossierActionUser)) {
			return true;
		}

		return false;
	}

	@Override
	public DossierActionUser getWrappedModel() {
		return _dossierActionUser;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _dossierActionUser.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _dossierActionUser.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_dossierActionUser.resetOriginalValues();
	}

	private final DossierActionUser _dossierActionUser;
}