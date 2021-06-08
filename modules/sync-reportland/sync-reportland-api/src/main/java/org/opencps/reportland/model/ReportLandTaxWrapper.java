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

package org.opencps.reportland.model;

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
 * This class is a wrapper for {@link ReportLandTax}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ReportLandTax
 * @generated
 */
@ProviderType
public class ReportLandTaxWrapper implements ReportLandTax,
	ModelWrapper<ReportLandTax> {
	public ReportLandTaxWrapper(ReportLandTax reportLandTax) {
		_reportLandTax = reportLandTax;
	}

	@Override
	public Class<?> getModelClass() {
		return ReportLandTax.class;
	}

	@Override
	public String getModelClassName() {
		return ReportLandTax.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("uuid", getUuid());
		attributes.put("reportId", getReportId());
		attributes.put("groupId", getGroupId());
		attributes.put("companyId", getCompanyId());
		attributes.put("userId", getUserId());
		attributes.put("userName", getUserName());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("dossierNo", getDossierNo());
		attributes.put("bodyRequest", getBodyRequest());
		attributes.put("response", getResponse());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		String uuid = (String)attributes.get("uuid");

		if (uuid != null) {
			setUuid(uuid);
		}

		Long reportId = (Long)attributes.get("reportId");

		if (reportId != null) {
			setReportId(reportId);
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

		String dossierNo = (String)attributes.get("dossierNo");

		if (dossierNo != null) {
			setDossierNo(dossierNo);
		}

		String bodyRequest = (String)attributes.get("bodyRequest");

		if (bodyRequest != null) {
			setBodyRequest(bodyRequest);
		}

		String response = (String)attributes.get("response");

		if (response != null) {
			setResponse(response);
		}
	}

	@Override
	public Object clone() {
		return new ReportLandTaxWrapper((ReportLandTax)_reportLandTax.clone());
	}

	@Override
	public int compareTo(ReportLandTax reportLandTax) {
		return _reportLandTax.compareTo(reportLandTax);
	}

	/**
	* Returns the body request of this report land tax.
	*
	* @return the body request of this report land tax
	*/
	@Override
	public String getBodyRequest() {
		return _reportLandTax.getBodyRequest();
	}

	/**
	* Returns the company ID of this report land tax.
	*
	* @return the company ID of this report land tax
	*/
	@Override
	public long getCompanyId() {
		return _reportLandTax.getCompanyId();
	}

	/**
	* Returns the create date of this report land tax.
	*
	* @return the create date of this report land tax
	*/
	@Override
	public Date getCreateDate() {
		return _reportLandTax.getCreateDate();
	}

	/**
	* Returns the dossier no of this report land tax.
	*
	* @return the dossier no of this report land tax
	*/
	@Override
	public String getDossierNo() {
		return _reportLandTax.getDossierNo();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _reportLandTax.getExpandoBridge();
	}

	/**
	* Returns the group ID of this report land tax.
	*
	* @return the group ID of this report land tax
	*/
	@Override
	public long getGroupId() {
		return _reportLandTax.getGroupId();
	}

	/**
	* Returns the modified date of this report land tax.
	*
	* @return the modified date of this report land tax
	*/
	@Override
	public Date getModifiedDate() {
		return _reportLandTax.getModifiedDate();
	}

	/**
	* Returns the primary key of this report land tax.
	*
	* @return the primary key of this report land tax
	*/
	@Override
	public long getPrimaryKey() {
		return _reportLandTax.getPrimaryKey();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _reportLandTax.getPrimaryKeyObj();
	}

	/**
	* Returns the report ID of this report land tax.
	*
	* @return the report ID of this report land tax
	*/
	@Override
	public long getReportId() {
		return _reportLandTax.getReportId();
	}

	/**
	* Returns the response of this report land tax.
	*
	* @return the response of this report land tax
	*/
	@Override
	public String getResponse() {
		return _reportLandTax.getResponse();
	}

	/**
	* Returns the user ID of this report land tax.
	*
	* @return the user ID of this report land tax
	*/
	@Override
	public long getUserId() {
		return _reportLandTax.getUserId();
	}

	/**
	* Returns the user name of this report land tax.
	*
	* @return the user name of this report land tax
	*/
	@Override
	public String getUserName() {
		return _reportLandTax.getUserName();
	}

	/**
	* Returns the user uuid of this report land tax.
	*
	* @return the user uuid of this report land tax
	*/
	@Override
	public String getUserUuid() {
		return _reportLandTax.getUserUuid();
	}

	/**
	* Returns the uuid of this report land tax.
	*
	* @return the uuid of this report land tax
	*/
	@Override
	public String getUuid() {
		return _reportLandTax.getUuid();
	}

	@Override
	public int hashCode() {
		return _reportLandTax.hashCode();
	}

	@Override
	public boolean isCachedModel() {
		return _reportLandTax.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _reportLandTax.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _reportLandTax.isNew();
	}

	@Override
	public void persist() {
		_reportLandTax.persist();
	}

	/**
	* Sets the body request of this report land tax.
	*
	* @param bodyRequest the body request of this report land tax
	*/
	@Override
	public void setBodyRequest(String bodyRequest) {
		_reportLandTax.setBodyRequest(bodyRequest);
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_reportLandTax.setCachedModel(cachedModel);
	}

	/**
	* Sets the company ID of this report land tax.
	*
	* @param companyId the company ID of this report land tax
	*/
	@Override
	public void setCompanyId(long companyId) {
		_reportLandTax.setCompanyId(companyId);
	}

	/**
	* Sets the create date of this report land tax.
	*
	* @param createDate the create date of this report land tax
	*/
	@Override
	public void setCreateDate(Date createDate) {
		_reportLandTax.setCreateDate(createDate);
	}

	/**
	* Sets the dossier no of this report land tax.
	*
	* @param dossierNo the dossier no of this report land tax
	*/
	@Override
	public void setDossierNo(String dossierNo) {
		_reportLandTax.setDossierNo(dossierNo);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_reportLandTax.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_reportLandTax.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_reportLandTax.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the group ID of this report land tax.
	*
	* @param groupId the group ID of this report land tax
	*/
	@Override
	public void setGroupId(long groupId) {
		_reportLandTax.setGroupId(groupId);
	}

	/**
	* Sets the modified date of this report land tax.
	*
	* @param modifiedDate the modified date of this report land tax
	*/
	@Override
	public void setModifiedDate(Date modifiedDate) {
		_reportLandTax.setModifiedDate(modifiedDate);
	}

	@Override
	public void setNew(boolean n) {
		_reportLandTax.setNew(n);
	}

	/**
	* Sets the primary key of this report land tax.
	*
	* @param primaryKey the primary key of this report land tax
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_reportLandTax.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_reportLandTax.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the report ID of this report land tax.
	*
	* @param reportId the report ID of this report land tax
	*/
	@Override
	public void setReportId(long reportId) {
		_reportLandTax.setReportId(reportId);
	}

	/**
	* Sets the response of this report land tax.
	*
	* @param response the response of this report land tax
	*/
	@Override
	public void setResponse(String response) {
		_reportLandTax.setResponse(response);
	}

	/**
	* Sets the user ID of this report land tax.
	*
	* @param userId the user ID of this report land tax
	*/
	@Override
	public void setUserId(long userId) {
		_reportLandTax.setUserId(userId);
	}

	/**
	* Sets the user name of this report land tax.
	*
	* @param userName the user name of this report land tax
	*/
	@Override
	public void setUserName(String userName) {
		_reportLandTax.setUserName(userName);
	}

	/**
	* Sets the user uuid of this report land tax.
	*
	* @param userUuid the user uuid of this report land tax
	*/
	@Override
	public void setUserUuid(String userUuid) {
		_reportLandTax.setUserUuid(userUuid);
	}

	/**
	* Sets the uuid of this report land tax.
	*
	* @param uuid the uuid of this report land tax
	*/
	@Override
	public void setUuid(String uuid) {
		_reportLandTax.setUuid(uuid);
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<ReportLandTax> toCacheModel() {
		return _reportLandTax.toCacheModel();
	}

	@Override
	public ReportLandTax toEscapedModel() {
		return new ReportLandTaxWrapper(_reportLandTax.toEscapedModel());
	}

	@Override
	public String toString() {
		return _reportLandTax.toString();
	}

	@Override
	public ReportLandTax toUnescapedModel() {
		return new ReportLandTaxWrapper(_reportLandTax.toUnescapedModel());
	}

	@Override
	public String toXmlString() {
		return _reportLandTax.toXmlString();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof ReportLandTaxWrapper)) {
			return false;
		}

		ReportLandTaxWrapper reportLandTaxWrapper = (ReportLandTaxWrapper)obj;

		if (Objects.equals(_reportLandTax, reportLandTaxWrapper._reportLandTax)) {
			return true;
		}

		return false;
	}

	@Override
	public StagedModelType getStagedModelType() {
		return _reportLandTax.getStagedModelType();
	}

	@Override
	public ReportLandTax getWrappedModel() {
		return _reportLandTax;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _reportLandTax.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _reportLandTax.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_reportLandTax.resetOriginalValues();
	}

	private final ReportLandTax _reportLandTax;
}