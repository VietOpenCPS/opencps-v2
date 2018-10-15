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
 * This class is a wrapper for {@link DossierActionUser}.
 * </p>
 *
 * @author huymq
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
		attributes.put("dossierId", getDossierId());
		attributes.put("stepCode", getStepCode());
		attributes.put("moderator", getModerator());
		attributes.put("assigned", getAssigned());
		attributes.put("visited", isVisited());

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

		Long dossierId = (Long)attributes.get("dossierId");

		if (dossierId != null) {
			setDossierId(dossierId);
		}

		String stepCode = (String)attributes.get("stepCode");

		if (stepCode != null) {
			setStepCode(stepCode);
		}

		Integer moderator = (Integer)attributes.get("moderator");

		if (moderator != null) {
			setModerator(moderator);
		}

		Integer assigned = (Integer)attributes.get("assigned");

		if (assigned != null) {
			setAssigned(assigned);
		}

		Boolean visited = (Boolean)attributes.get("visited");

		if (visited != null) {
			setVisited(visited);
		}
	}

	@Override
	public Object clone() {
		return new DossierActionUserWrapper((DossierActionUser)_dossierActionUser.clone());
	}

	@Override
	public int compareTo(DossierActionUser dossierActionUser) {
		return _dossierActionUser.compareTo(dossierActionUser);
	}

	/**
	* Returns the assigned of this dossier action user.
	*
	* @return the assigned of this dossier action user
	*/
	@Override
	public int getAssigned() {
		return _dossierActionUser.getAssigned();
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
	* Returns the dossier ID of this dossier action user.
	*
	* @return the dossier ID of this dossier action user
	*/
	@Override
	public long getDossierId() {
		return _dossierActionUser.getDossierId();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _dossierActionUser.getExpandoBridge();
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

	/**
	* Returns the primary key of this dossier action user.
	*
	* @return the primary key of this dossier action user
	*/
	@Override
	public org.opencps.dossiermgt.service.persistence.DossierActionUserPK getPrimaryKey() {
		return _dossierActionUser.getPrimaryKey();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _dossierActionUser.getPrimaryKeyObj();
	}

	/**
	* Returns the step code of this dossier action user.
	*
	* @return the step code of this dossier action user
	*/
	@Override
	public String getStepCode() {
		return _dossierActionUser.getStepCode();
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
	* Returns the user uuid of this dossier action user.
	*
	* @return the user uuid of this dossier action user
	*/
	@Override
	public String getUserUuid() {
		return _dossierActionUser.getUserUuid();
	}

	/**
	* Returns the uuid of this dossier action user.
	*
	* @return the uuid of this dossier action user
	*/
	@Override
	public String getUuid() {
		return _dossierActionUser.getUuid();
	}

	/**
	* Returns the visited of this dossier action user.
	*
	* @return the visited of this dossier action user
	*/
	@Override
	public boolean getVisited() {
		return _dossierActionUser.getVisited();
	}

	@Override
	public int hashCode() {
		return _dossierActionUser.hashCode();
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

	/**
	* Returns <code>true</code> if this dossier action user is visited.
	*
	* @return <code>true</code> if this dossier action user is visited; <code>false</code> otherwise
	*/
	@Override
	public boolean isVisited() {
		return _dossierActionUser.isVisited();
	}

	@Override
	public void persist() {
		_dossierActionUser.persist();
	}

	/**
	* Sets the assigned of this dossier action user.
	*
	* @param assigned the assigned of this dossier action user
	*/
	@Override
	public void setAssigned(int assigned) {
		_dossierActionUser.setAssigned(assigned);
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

	/**
	* Sets the dossier ID of this dossier action user.
	*
	* @param dossierId the dossier ID of this dossier action user
	*/
	@Override
	public void setDossierId(long dossierId) {
		_dossierActionUser.setDossierId(dossierId);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_dossierActionUser.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_dossierActionUser.setExpandoBridgeAttributes(expandoBridge);
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
		org.opencps.dossiermgt.service.persistence.DossierActionUserPK primaryKey) {
		_dossierActionUser.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_dossierActionUser.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the step code of this dossier action user.
	*
	* @param stepCode the step code of this dossier action user
	*/
	@Override
	public void setStepCode(String stepCode) {
		_dossierActionUser.setStepCode(stepCode);
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
	public void setUserUuid(String userUuid) {
		_dossierActionUser.setUserUuid(userUuid);
	}

	/**
	* Sets the uuid of this dossier action user.
	*
	* @param uuid the uuid of this dossier action user
	*/
	@Override
	public void setUuid(String uuid) {
		_dossierActionUser.setUuid(uuid);
	}

	/**
	* Sets whether this dossier action user is visited.
	*
	* @param visited the visited of this dossier action user
	*/
	@Override
	public void setVisited(boolean visited) {
		_dossierActionUser.setVisited(visited);
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<DossierActionUser> toCacheModel() {
		return _dossierActionUser.toCacheModel();
	}

	@Override
	public DossierActionUser toEscapedModel() {
		return new DossierActionUserWrapper(_dossierActionUser.toEscapedModel());
	}

	@Override
	public String toString() {
		return _dossierActionUser.toString();
	}

	@Override
	public DossierActionUser toUnescapedModel() {
		return new DossierActionUserWrapper(_dossierActionUser.toUnescapedModel());
	}

	@Override
	public String toXmlString() {
		return _dossierActionUser.toXmlString();
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