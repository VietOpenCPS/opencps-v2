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

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * <p>
 * This class is a wrapper for {@link Notarization}.
 * </p>
 *
 * @author huymq
 * @see Notarization
 * @generated
 */
@ProviderType
public class NotarizationWrapper implements Notarization,
	ModelWrapper<Notarization> {
	public NotarizationWrapper(Notarization notarization) {
		_notarization = notarization;
	}

	@Override
	public Class<?> getModelClass() {
		return Notarization.class;
	}

	@Override
	public String getModelClassName() {
		return Notarization.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("notarizationId", getNotarizationId());
		attributes.put("groupId", getGroupId());
		attributes.put("companyId", getCompanyId());
		attributes.put("userId", getUserId());
		attributes.put("userName", getUserName());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("dossierId", getDossierId());
		attributes.put("fileName", getFileName());
		attributes.put("totalRecord", getTotalRecord());
		attributes.put("totalPage", getTotalPage());
		attributes.put("totalCopy", getTotalCopy());
		attributes.put("totalFee", getTotalFee());
		attributes.put("notarizationNo", getNotarizationNo());
		attributes.put("notarizationYear", getNotarizationYear());
		attributes.put("notarizationDate", getNotarizationDate());
		attributes.put("signerName", getSignerName());
		attributes.put("signerPosition", getSignerPosition());
		attributes.put("statusCode", getStatusCode());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long notarizationId = (Long)attributes.get("notarizationId");

		if (notarizationId != null) {
			setNotarizationId(notarizationId);
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

		Long dossierId = (Long)attributes.get("dossierId");

		if (dossierId != null) {
			setDossierId(dossierId);
		}

		String fileName = (String)attributes.get("fileName");

		if (fileName != null) {
			setFileName(fileName);
		}

		Integer totalRecord = (Integer)attributes.get("totalRecord");

		if (totalRecord != null) {
			setTotalRecord(totalRecord);
		}

		Integer totalPage = (Integer)attributes.get("totalPage");

		if (totalPage != null) {
			setTotalPage(totalPage);
		}

		Integer totalCopy = (Integer)attributes.get("totalCopy");

		if (totalCopy != null) {
			setTotalCopy(totalCopy);
		}

		Long totalFee = (Long)attributes.get("totalFee");

		if (totalFee != null) {
			setTotalFee(totalFee);
		}

		Long notarizationNo = (Long)attributes.get("notarizationNo");

		if (notarizationNo != null) {
			setNotarizationNo(notarizationNo);
		}

		Integer notarizationYear = (Integer)attributes.get("notarizationYear");

		if (notarizationYear != null) {
			setNotarizationYear(notarizationYear);
		}

		Date notarizationDate = (Date)attributes.get("notarizationDate");

		if (notarizationDate != null) {
			setNotarizationDate(notarizationDate);
		}

		String signerName = (String)attributes.get("signerName");

		if (signerName != null) {
			setSignerName(signerName);
		}

		String signerPosition = (String)attributes.get("signerPosition");

		if (signerPosition != null) {
			setSignerPosition(signerPosition);
		}

		String statusCode = (String)attributes.get("statusCode");

		if (statusCode != null) {
			setStatusCode(statusCode);
		}
	}

	@Override
	public Object clone() {
		return new NotarizationWrapper((Notarization)_notarization.clone());
	}

	@Override
	public int compareTo(Notarization notarization) {
		return _notarization.compareTo(notarization);
	}

	/**
	* Returns the company ID of this notarization.
	*
	* @return the company ID of this notarization
	*/
	@Override
	public long getCompanyId() {
		return _notarization.getCompanyId();
	}

	/**
	* Returns the create date of this notarization.
	*
	* @return the create date of this notarization
	*/
	@Override
	public Date getCreateDate() {
		return _notarization.getCreateDate();
	}

	/**
	* Returns the dossier ID of this notarization.
	*
	* @return the dossier ID of this notarization
	*/
	@Override
	public long getDossierId() {
		return _notarization.getDossierId();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _notarization.getExpandoBridge();
	}

	/**
	* Returns the file name of this notarization.
	*
	* @return the file name of this notarization
	*/
	@Override
	public String getFileName() {
		return _notarization.getFileName();
	}

	/**
	* Returns the group ID of this notarization.
	*
	* @return the group ID of this notarization
	*/
	@Override
	public long getGroupId() {
		return _notarization.getGroupId();
	}

	/**
	* Returns the modified date of this notarization.
	*
	* @return the modified date of this notarization
	*/
	@Override
	public Date getModifiedDate() {
		return _notarization.getModifiedDate();
	}

	/**
	* Returns the notarization date of this notarization.
	*
	* @return the notarization date of this notarization
	*/
	@Override
	public Date getNotarizationDate() {
		return _notarization.getNotarizationDate();
	}

	/**
	* Returns the notarization ID of this notarization.
	*
	* @return the notarization ID of this notarization
	*/
	@Override
	public long getNotarizationId() {
		return _notarization.getNotarizationId();
	}

	/**
	* Returns the notarization no of this notarization.
	*
	* @return the notarization no of this notarization
	*/
	@Override
	public long getNotarizationNo() {
		return _notarization.getNotarizationNo();
	}

	/**
	* Returns the notarization year of this notarization.
	*
	* @return the notarization year of this notarization
	*/
	@Override
	public int getNotarizationYear() {
		return _notarization.getNotarizationYear();
	}

	/**
	* Returns the primary key of this notarization.
	*
	* @return the primary key of this notarization
	*/
	@Override
	public long getPrimaryKey() {
		return _notarization.getPrimaryKey();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _notarization.getPrimaryKeyObj();
	}

	/**
	* Returns the signer name of this notarization.
	*
	* @return the signer name of this notarization
	*/
	@Override
	public String getSignerName() {
		return _notarization.getSignerName();
	}

	/**
	* Returns the signer position of this notarization.
	*
	* @return the signer position of this notarization
	*/
	@Override
	public String getSignerPosition() {
		return _notarization.getSignerPosition();
	}

	/**
	* Returns the status code of this notarization.
	*
	* @return the status code of this notarization
	*/
	@Override
	public String getStatusCode() {
		return _notarization.getStatusCode();
	}

	/**
	* Returns the total copy of this notarization.
	*
	* @return the total copy of this notarization
	*/
	@Override
	public int getTotalCopy() {
		return _notarization.getTotalCopy();
	}

	/**
	* Returns the total fee of this notarization.
	*
	* @return the total fee of this notarization
	*/
	@Override
	public long getTotalFee() {
		return _notarization.getTotalFee();
	}

	/**
	* Returns the total page of this notarization.
	*
	* @return the total page of this notarization
	*/
	@Override
	public int getTotalPage() {
		return _notarization.getTotalPage();
	}

	/**
	* Returns the total record of this notarization.
	*
	* @return the total record of this notarization
	*/
	@Override
	public int getTotalRecord() {
		return _notarization.getTotalRecord();
	}

	/**
	* Returns the user ID of this notarization.
	*
	* @return the user ID of this notarization
	*/
	@Override
	public long getUserId() {
		return _notarization.getUserId();
	}

	/**
	* Returns the user name of this notarization.
	*
	* @return the user name of this notarization
	*/
	@Override
	public String getUserName() {
		return _notarization.getUserName();
	}

	/**
	* Returns the user uuid of this notarization.
	*
	* @return the user uuid of this notarization
	*/
	@Override
	public String getUserUuid() {
		return _notarization.getUserUuid();
	}

	@Override
	public int hashCode() {
		return _notarization.hashCode();
	}

	@Override
	public boolean isCachedModel() {
		return _notarization.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _notarization.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _notarization.isNew();
	}

	@Override
	public void persist() {
		_notarization.persist();
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_notarization.setCachedModel(cachedModel);
	}

	/**
	* Sets the company ID of this notarization.
	*
	* @param companyId the company ID of this notarization
	*/
	@Override
	public void setCompanyId(long companyId) {
		_notarization.setCompanyId(companyId);
	}

	/**
	* Sets the create date of this notarization.
	*
	* @param createDate the create date of this notarization
	*/
	@Override
	public void setCreateDate(Date createDate) {
		_notarization.setCreateDate(createDate);
	}

	/**
	* Sets the dossier ID of this notarization.
	*
	* @param dossierId the dossier ID of this notarization
	*/
	@Override
	public void setDossierId(long dossierId) {
		_notarization.setDossierId(dossierId);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_notarization.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_notarization.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_notarization.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the file name of this notarization.
	*
	* @param fileName the file name of this notarization
	*/
	@Override
	public void setFileName(String fileName) {
		_notarization.setFileName(fileName);
	}

	/**
	* Sets the group ID of this notarization.
	*
	* @param groupId the group ID of this notarization
	*/
	@Override
	public void setGroupId(long groupId) {
		_notarization.setGroupId(groupId);
	}

	/**
	* Sets the modified date of this notarization.
	*
	* @param modifiedDate the modified date of this notarization
	*/
	@Override
	public void setModifiedDate(Date modifiedDate) {
		_notarization.setModifiedDate(modifiedDate);
	}

	@Override
	public void setNew(boolean n) {
		_notarization.setNew(n);
	}

	/**
	* Sets the notarization date of this notarization.
	*
	* @param notarizationDate the notarization date of this notarization
	*/
	@Override
	public void setNotarizationDate(Date notarizationDate) {
		_notarization.setNotarizationDate(notarizationDate);
	}

	/**
	* Sets the notarization ID of this notarization.
	*
	* @param notarizationId the notarization ID of this notarization
	*/
	@Override
	public void setNotarizationId(long notarizationId) {
		_notarization.setNotarizationId(notarizationId);
	}

	/**
	* Sets the notarization no of this notarization.
	*
	* @param notarizationNo the notarization no of this notarization
	*/
	@Override
	public void setNotarizationNo(long notarizationNo) {
		_notarization.setNotarizationNo(notarizationNo);
	}

	/**
	* Sets the notarization year of this notarization.
	*
	* @param notarizationYear the notarization year of this notarization
	*/
	@Override
	public void setNotarizationYear(int notarizationYear) {
		_notarization.setNotarizationYear(notarizationYear);
	}

	/**
	* Sets the primary key of this notarization.
	*
	* @param primaryKey the primary key of this notarization
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_notarization.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_notarization.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the signer name of this notarization.
	*
	* @param signerName the signer name of this notarization
	*/
	@Override
	public void setSignerName(String signerName) {
		_notarization.setSignerName(signerName);
	}

	/**
	* Sets the signer position of this notarization.
	*
	* @param signerPosition the signer position of this notarization
	*/
	@Override
	public void setSignerPosition(String signerPosition) {
		_notarization.setSignerPosition(signerPosition);
	}

	/**
	* Sets the status code of this notarization.
	*
	* @param statusCode the status code of this notarization
	*/
	@Override
	public void setStatusCode(String statusCode) {
		_notarization.setStatusCode(statusCode);
	}

	/**
	* Sets the total copy of this notarization.
	*
	* @param totalCopy the total copy of this notarization
	*/
	@Override
	public void setTotalCopy(int totalCopy) {
		_notarization.setTotalCopy(totalCopy);
	}

	/**
	* Sets the total fee of this notarization.
	*
	* @param totalFee the total fee of this notarization
	*/
	@Override
	public void setTotalFee(long totalFee) {
		_notarization.setTotalFee(totalFee);
	}

	/**
	* Sets the total page of this notarization.
	*
	* @param totalPage the total page of this notarization
	*/
	@Override
	public void setTotalPage(int totalPage) {
		_notarization.setTotalPage(totalPage);
	}

	/**
	* Sets the total record of this notarization.
	*
	* @param totalRecord the total record of this notarization
	*/
	@Override
	public void setTotalRecord(int totalRecord) {
		_notarization.setTotalRecord(totalRecord);
	}

	/**
	* Sets the user ID of this notarization.
	*
	* @param userId the user ID of this notarization
	*/
	@Override
	public void setUserId(long userId) {
		_notarization.setUserId(userId);
	}

	/**
	* Sets the user name of this notarization.
	*
	* @param userName the user name of this notarization
	*/
	@Override
	public void setUserName(String userName) {
		_notarization.setUserName(userName);
	}

	/**
	* Sets the user uuid of this notarization.
	*
	* @param userUuid the user uuid of this notarization
	*/
	@Override
	public void setUserUuid(String userUuid) {
		_notarization.setUserUuid(userUuid);
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<Notarization> toCacheModel() {
		return _notarization.toCacheModel();
	}

	@Override
	public Notarization toEscapedModel() {
		return new NotarizationWrapper(_notarization.toEscapedModel());
	}

	@Override
	public String toString() {
		return _notarization.toString();
	}

	@Override
	public Notarization toUnescapedModel() {
		return new NotarizationWrapper(_notarization.toUnescapedModel());
	}

	@Override
	public String toXmlString() {
		return _notarization.toXmlString();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof NotarizationWrapper)) {
			return false;
		}

		NotarizationWrapper notarizationWrapper = (NotarizationWrapper)obj;

		if (Objects.equals(_notarization, notarizationWrapper._notarization)) {
			return true;
		}

		return false;
	}

	@Override
	public Notarization getWrappedModel() {
		return _notarization;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _notarization.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _notarization.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_notarization.resetOriginalValues();
	}

	private final Notarization _notarization;
}