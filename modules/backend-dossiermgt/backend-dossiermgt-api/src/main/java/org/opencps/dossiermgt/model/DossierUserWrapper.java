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

import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.service.ServiceContext;

import java.io.Serializable;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * <p>
 * This class is a wrapper for {@link DossierUser}.
 * </p>
 *
 * @author huymq
 * @see DossierUser
 * @generated
 */
@ProviderType
public class DossierUserWrapper implements DossierUser,
	ModelWrapper<DossierUser> {
	public DossierUserWrapper(DossierUser dossierUser) {
		_dossierUser = dossierUser;
	}

	@Override
	public Class<?> getModelClass() {
		return DossierUser.class;
	}

	@Override
	public String getModelClassName() {
		return DossierUser.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("uuid", getUuid());
		attributes.put("dossierId", getDossierId());
		attributes.put("userId", getUserId());
		attributes.put("moderator", getModerator());
		attributes.put("visited", isVisited());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		String uuid = (String)attributes.get("uuid");

		if (uuid != null) {
			setUuid(uuid);
		}

		Long dossierId = (Long)attributes.get("dossierId");

		if (dossierId != null) {
			setDossierId(dossierId);
		}

		Long userId = (Long)attributes.get("userId");

		if (userId != null) {
			setUserId(userId);
		}

		Integer moderator = (Integer)attributes.get("moderator");

		if (moderator != null) {
			setModerator(moderator);
		}

		Boolean visited = (Boolean)attributes.get("visited");

		if (visited != null) {
			setVisited(visited);
		}
	}

	@Override
	public Object clone() {
		return new DossierUserWrapper((DossierUser)_dossierUser.clone());
	}

	@Override
	public int compareTo(DossierUser dossierUser) {
		return _dossierUser.compareTo(dossierUser);
	}

	/**
	* Returns the dossier ID of this dossier user.
	*
	* @return the dossier ID of this dossier user
	*/
	@Override
	public long getDossierId() {
		return _dossierUser.getDossierId();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _dossierUser.getExpandoBridge();
	}

	/**
	* Returns the moderator of this dossier user.
	*
	* @return the moderator of this dossier user
	*/
	@Override
	public int getModerator() {
		return _dossierUser.getModerator();
	}

	/**
	* Returns the primary key of this dossier user.
	*
	* @return the primary key of this dossier user
	*/
	@Override
	public org.opencps.dossiermgt.service.persistence.DossierUserPK getPrimaryKey() {
		return _dossierUser.getPrimaryKey();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _dossierUser.getPrimaryKeyObj();
	}

	/**
	* Returns the user ID of this dossier user.
	*
	* @return the user ID of this dossier user
	*/
	@Override
	public long getUserId() {
		return _dossierUser.getUserId();
	}

	/**
	* Returns the user uuid of this dossier user.
	*
	* @return the user uuid of this dossier user
	*/
	@Override
	public String getUserUuid() {
		return _dossierUser.getUserUuid();
	}

	/**
	* Returns the uuid of this dossier user.
	*
	* @return the uuid of this dossier user
	*/
	@Override
	public String getUuid() {
		return _dossierUser.getUuid();
	}

	/**
	* Returns the visited of this dossier user.
	*
	* @return the visited of this dossier user
	*/
	@Override
	public boolean getVisited() {
		return _dossierUser.getVisited();
	}

	@Override
	public int hashCode() {
		return _dossierUser.hashCode();
	}

	@Override
	public boolean isCachedModel() {
		return _dossierUser.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _dossierUser.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _dossierUser.isNew();
	}

	/**
	* Returns <code>true</code> if this dossier user is visited.
	*
	* @return <code>true</code> if this dossier user is visited; <code>false</code> otherwise
	*/
	@Override
	public boolean isVisited() {
		return _dossierUser.isVisited();
	}

	@Override
	public void persist() {
		_dossierUser.persist();
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_dossierUser.setCachedModel(cachedModel);
	}

	/**
	* Sets the dossier ID of this dossier user.
	*
	* @param dossierId the dossier ID of this dossier user
	*/
	@Override
	public void setDossierId(long dossierId) {
		_dossierUser.setDossierId(dossierId);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_dossierUser.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_dossierUser.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_dossierUser.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the moderator of this dossier user.
	*
	* @param moderator the moderator of this dossier user
	*/
	@Override
	public void setModerator(int moderator) {
		_dossierUser.setModerator(moderator);
	}

	@Override
	public void setNew(boolean n) {
		_dossierUser.setNew(n);
	}

	/**
	* Sets the primary key of this dossier user.
	*
	* @param primaryKey the primary key of this dossier user
	*/
	@Override
	public void setPrimaryKey(
		org.opencps.dossiermgt.service.persistence.DossierUserPK primaryKey) {
		_dossierUser.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_dossierUser.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the user ID of this dossier user.
	*
	* @param userId the user ID of this dossier user
	*/
	@Override
	public void setUserId(long userId) {
		_dossierUser.setUserId(userId);
	}

	/**
	* Sets the user uuid of this dossier user.
	*
	* @param userUuid the user uuid of this dossier user
	*/
	@Override
	public void setUserUuid(String userUuid) {
		_dossierUser.setUserUuid(userUuid);
	}

	/**
	* Sets the uuid of this dossier user.
	*
	* @param uuid the uuid of this dossier user
	*/
	@Override
	public void setUuid(String uuid) {
		_dossierUser.setUuid(uuid);
	}

	/**
	* Sets whether this dossier user is visited.
	*
	* @param visited the visited of this dossier user
	*/
	@Override
	public void setVisited(boolean visited) {
		_dossierUser.setVisited(visited);
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<DossierUser> toCacheModel() {
		return _dossierUser.toCacheModel();
	}

	@Override
	public DossierUser toEscapedModel() {
		return new DossierUserWrapper(_dossierUser.toEscapedModel());
	}

	@Override
	public String toString() {
		return _dossierUser.toString();
	}

	@Override
	public DossierUser toUnescapedModel() {
		return new DossierUserWrapper(_dossierUser.toUnescapedModel());
	}

	@Override
	public String toXmlString() {
		return _dossierUser.toXmlString();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof DossierUserWrapper)) {
			return false;
		}

		DossierUserWrapper dossierUserWrapper = (DossierUserWrapper)obj;

		if (Objects.equals(_dossierUser, dossierUserWrapper._dossierUser)) {
			return true;
		}

		return false;
	}

	@Override
	public DossierUser getWrappedModel() {
		return _dossierUser;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _dossierUser.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _dossierUser.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_dossierUser.resetOriginalValues();
	}

	private final DossierUser _dossierUser;
}