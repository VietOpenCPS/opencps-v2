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

import com.liferay.portal.kernel.bean.AutoEscape;
import com.liferay.portal.kernel.model.BaseModel;
import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.model.ShardedModel;
import com.liferay.portal.kernel.model.StagedModel;
import com.liferay.portal.kernel.service.ServiceContext;

import java.io.Serializable;

import java.util.Date;

/**
 * The base model interface for the DossierMark service. Represents a row in the &quot;opencps_dossiermark&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This interface and its corresponding implementation {@link org.opencps.dossiermgt.model.impl.DossierMarkModelImpl} exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link org.opencps.dossiermgt.model.impl.DossierMarkImpl}.
 * </p>
 *
 * @author huymq
 * @see DossierMark
 * @see org.opencps.dossiermgt.model.impl.DossierMarkImpl
 * @see org.opencps.dossiermgt.model.impl.DossierMarkModelImpl
 * @generated
 */
@ProviderType
public interface DossierMarkModel extends BaseModel<DossierMark>, ShardedModel,
	StagedModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. All methods that expect a dossier mark model instance should use the {@link DossierMark} interface instead.
	 */

	/**
	 * Returns the primary key of this dossier mark.
	 *
	 * @return the primary key of this dossier mark
	 */
	public long getPrimaryKey();

	/**
	 * Sets the primary key of this dossier mark.
	 *
	 * @param primaryKey the primary key of this dossier mark
	 */
	public void setPrimaryKey(long primaryKey);

	/**
	 * Returns the uuid of this dossier mark.
	 *
	 * @return the uuid of this dossier mark
	 */
	@AutoEscape
	@Override
	public String getUuid();

	/**
	 * Sets the uuid of this dossier mark.
	 *
	 * @param uuid the uuid of this dossier mark
	 */
	@Override
	public void setUuid(String uuid);

	/**
	 * Returns the dossier mark ID of this dossier mark.
	 *
	 * @return the dossier mark ID of this dossier mark
	 */
	public long getDossierMarkId();

	/**
	 * Sets the dossier mark ID of this dossier mark.
	 *
	 * @param dossierMarkId the dossier mark ID of this dossier mark
	 */
	public void setDossierMarkId(long dossierMarkId);

	/**
	 * Returns the company ID of this dossier mark.
	 *
	 * @return the company ID of this dossier mark
	 */
	@Override
	public long getCompanyId();

	/**
	 * Sets the company ID of this dossier mark.
	 *
	 * @param companyId the company ID of this dossier mark
	 */
	@Override
	public void setCompanyId(long companyId);

	/**
	 * Returns the group ID of this dossier mark.
	 *
	 * @return the group ID of this dossier mark
	 */
	public long getGroupId();

	/**
	 * Sets the group ID of this dossier mark.
	 *
	 * @param groupId the group ID of this dossier mark
	 */
	public void setGroupId(long groupId);

	/**
	 * Returns the user ID of this dossier mark.
	 *
	 * @return the user ID of this dossier mark
	 */
	public long getUserId();

	/**
	 * Sets the user ID of this dossier mark.
	 *
	 * @param userId the user ID of this dossier mark
	 */
	public void setUserId(long userId);

	/**
	 * Returns the user uuid of this dossier mark.
	 *
	 * @return the user uuid of this dossier mark
	 */
	public String getUserUuid();

	/**
	 * Sets the user uuid of this dossier mark.
	 *
	 * @param userUuid the user uuid of this dossier mark
	 */
	public void setUserUuid(String userUuid);

	/**
	 * Returns the create date of this dossier mark.
	 *
	 * @return the create date of this dossier mark
	 */
	@Override
	public Date getCreateDate();

	/**
	 * Sets the create date of this dossier mark.
	 *
	 * @param createDate the create date of this dossier mark
	 */
	@Override
	public void setCreateDate(Date createDate);

	/**
	 * Returns the modified date of this dossier mark.
	 *
	 * @return the modified date of this dossier mark
	 */
	@Override
	public Date getModifiedDate();

	/**
	 * Sets the modified date of this dossier mark.
	 *
	 * @param modifiedDate the modified date of this dossier mark
	 */
	@Override
	public void setModifiedDate(Date modifiedDate);

	/**
	 * Returns the dossier ID of this dossier mark.
	 *
	 * @return the dossier ID of this dossier mark
	 */
	public long getDossierId();

	/**
	 * Sets the dossier ID of this dossier mark.
	 *
	 * @param dossierId the dossier ID of this dossier mark
	 */
	public void setDossierId(long dossierId);

	/**
	 * Returns the dossier part no of this dossier mark.
	 *
	 * @return the dossier part no of this dossier mark
	 */
	@AutoEscape
	public String getDossierPartNo();

	/**
	 * Sets the dossier part no of this dossier mark.
	 *
	 * @param dossierPartNo the dossier part no of this dossier mark
	 */
	public void setDossierPartNo(String dossierPartNo);

	/**
	 * Returns the file check of this dossier mark.
	 *
	 * @return the file check of this dossier mark
	 */
	public int getFileCheck();

	/**
	 * Sets the file check of this dossier mark.
	 *
	 * @param fileCheck the file check of this dossier mark
	 */
	public void setFileCheck(int fileCheck);

	/**
	 * Returns the file mark of this dossier mark.
	 *
	 * @return the file mark of this dossier mark
	 */
	public int getFileMark();

	/**
	 * Sets the file mark of this dossier mark.
	 *
	 * @param fileMark the file mark of this dossier mark
	 */
	public void setFileMark(int fileMark);

	/**
	 * Returns the file comment of this dossier mark.
	 *
	 * @return the file comment of this dossier mark
	 */
	@AutoEscape
	public String getFileComment();

	/**
	 * Sets the file comment of this dossier mark.
	 *
	 * @param fileComment the file comment of this dossier mark
	 */
	public void setFileComment(String fileComment);

	/**
	 * Returns the record count of this dossier mark.
	 *
	 * @return the record count of this dossier mark
	 */
	@AutoEscape
	public String getRecordCount();

	/**
	 * Sets the record count of this dossier mark.
	 *
	 * @param recordCount the record count of this dossier mark
	 */
	public void setRecordCount(String recordCount);

	@Override
	public boolean isNew();

	@Override
	public void setNew(boolean n);

	@Override
	public boolean isCachedModel();

	@Override
	public void setCachedModel(boolean cachedModel);

	@Override
	public boolean isEscapedModel();

	@Override
	public Serializable getPrimaryKeyObj();

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj);

	@Override
	public ExpandoBridge getExpandoBridge();

	@Override
	public void setExpandoBridgeAttributes(BaseModel<?> baseModel);

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge);

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext);

	@Override
	public Object clone();

	@Override
	public int compareTo(DossierMark dossierMark);

	@Override
	public int hashCode();

	@Override
	public CacheModel<DossierMark> toCacheModel();

	@Override
	public DossierMark toEscapedModel();

	@Override
	public DossierMark toUnescapedModel();

	@Override
	public String toString();

	@Override
	public String toXmlString();
}