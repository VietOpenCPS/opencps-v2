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
 * This class is a wrapper for {@link Deliverable}.
 * </p>
 *
 * @author huymq
 * @see Deliverable
 * @generated
 */
@ProviderType
public class DeliverableWrapper implements Deliverable,
	ModelWrapper<Deliverable> {
	public DeliverableWrapper(Deliverable deliverable) {
		_deliverable = deliverable;
	}

	@Override
	public Class<?> getModelClass() {
		return Deliverable.class;
	}

	@Override
	public String getModelClassName() {
		return Deliverable.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("uuid", getUuid());
		attributes.put("deliverableId", getDeliverableId());
		attributes.put("companyId", getCompanyId());
		attributes.put("groupId", getGroupId());
		attributes.put("userId", getUserId());
		attributes.put("userName", getUserName());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("deliverableCode", getDeliverableCode());
		attributes.put("deliverableName", getDeliverableName());
		attributes.put("deliverableType", getDeliverableType());
		attributes.put("govAgencyCode", getGovAgencyCode());
		attributes.put("govAgencyName", getGovAgencyName());
		attributes.put("applicantIdNo", getApplicantIdNo());
		attributes.put("applicantName", getApplicantName());
		attributes.put("subject", getSubject());
		attributes.put("formData", getFormData());
		attributes.put("formScript", getFormScript());
		attributes.put("formReport", getFormReport());
		attributes.put("expireDate", getExpireDate());
		attributes.put("issueDate", getIssueDate());
		attributes.put("revalidate", getRevalidate());
		attributes.put("deliverableState", getDeliverableState());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		String uuid = (String)attributes.get("uuid");

		if (uuid != null) {
			setUuid(uuid);
		}

		Long deliverableId = (Long)attributes.get("deliverableId");

		if (deliverableId != null) {
			setDeliverableId(deliverableId);
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

		String deliverableCode = (String)attributes.get("deliverableCode");

		if (deliverableCode != null) {
			setDeliverableCode(deliverableCode);
		}

		String deliverableName = (String)attributes.get("deliverableName");

		if (deliverableName != null) {
			setDeliverableName(deliverableName);
		}

		String deliverableType = (String)attributes.get("deliverableType");

		if (deliverableType != null) {
			setDeliverableType(deliverableType);
		}

		String govAgencyCode = (String)attributes.get("govAgencyCode");

		if (govAgencyCode != null) {
			setGovAgencyCode(govAgencyCode);
		}

		String govAgencyName = (String)attributes.get("govAgencyName");

		if (govAgencyName != null) {
			setGovAgencyName(govAgencyName);
		}

		String applicantIdNo = (String)attributes.get("applicantIdNo");

		if (applicantIdNo != null) {
			setApplicantIdNo(applicantIdNo);
		}

		String applicantName = (String)attributes.get("applicantName");

		if (applicantName != null) {
			setApplicantName(applicantName);
		}

		String subject = (String)attributes.get("subject");

		if (subject != null) {
			setSubject(subject);
		}

		String formData = (String)attributes.get("formData");

		if (formData != null) {
			setFormData(formData);
		}

		String formScript = (String)attributes.get("formScript");

		if (formScript != null) {
			setFormScript(formScript);
		}

		String formReport = (String)attributes.get("formReport");

		if (formReport != null) {
			setFormReport(formReport);
		}

		Date expireDate = (Date)attributes.get("expireDate");

		if (expireDate != null) {
			setExpireDate(expireDate);
		}

		Date issueDate = (Date)attributes.get("issueDate");

		if (issueDate != null) {
			setIssueDate(issueDate);
		}

		Date revalidate = (Date)attributes.get("revalidate");

		if (revalidate != null) {
			setRevalidate(revalidate);
		}

		String deliverableState = (String)attributes.get("deliverableState");

		if (deliverableState != null) {
			setDeliverableState(deliverableState);
		}
	}

	@Override
	public Object clone() {
		return new DeliverableWrapper((Deliverable)_deliverable.clone());
	}

	@Override
	public int compareTo(Deliverable deliverable) {
		return _deliverable.compareTo(deliverable);
	}

	/**
	* Returns the applicant ID no of this deliverable.
	*
	* @return the applicant ID no of this deliverable
	*/
	@Override
	public String getApplicantIdNo() {
		return _deliverable.getApplicantIdNo();
	}

	/**
	* Returns the applicant name of this deliverable.
	*
	* @return the applicant name of this deliverable
	*/
	@Override
	public String getApplicantName() {
		return _deliverable.getApplicantName();
	}

	/**
	* Returns the company ID of this deliverable.
	*
	* @return the company ID of this deliverable
	*/
	@Override
	public long getCompanyId() {
		return _deliverable.getCompanyId();
	}

	/**
	* Returns the create date of this deliverable.
	*
	* @return the create date of this deliverable
	*/
	@Override
	public Date getCreateDate() {
		return _deliverable.getCreateDate();
	}

	/**
	* Returns the deliverable code of this deliverable.
	*
	* @return the deliverable code of this deliverable
	*/
	@Override
	public String getDeliverableCode() {
		return _deliverable.getDeliverableCode();
	}

	/**
	* Returns the deliverable ID of this deliverable.
	*
	* @return the deliverable ID of this deliverable
	*/
	@Override
	public long getDeliverableId() {
		return _deliverable.getDeliverableId();
	}

	/**
	* Returns the deliverable name of this deliverable.
	*
	* @return the deliverable name of this deliverable
	*/
	@Override
	public String getDeliverableName() {
		return _deliverable.getDeliverableName();
	}

	/**
	* Returns the deliverable state of this deliverable.
	*
	* @return the deliverable state of this deliverable
	*/
	@Override
	public String getDeliverableState() {
		return _deliverable.getDeliverableState();
	}

	/**
	* Returns the deliverable type of this deliverable.
	*
	* @return the deliverable type of this deliverable
	*/
	@Override
	public String getDeliverableType() {
		return _deliverable.getDeliverableType();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _deliverable.getExpandoBridge();
	}

	/**
	* Returns the expire date of this deliverable.
	*
	* @return the expire date of this deliverable
	*/
	@Override
	public Date getExpireDate() {
		return _deliverable.getExpireDate();
	}

	/**
	* Returns the form data of this deliverable.
	*
	* @return the form data of this deliverable
	*/
	@Override
	public String getFormData() {
		return _deliverable.getFormData();
	}

	/**
	* Returns the form report of this deliverable.
	*
	* @return the form report of this deliverable
	*/
	@Override
	public String getFormReport() {
		return _deliverable.getFormReport();
	}

	/**
	* Returns the form script of this deliverable.
	*
	* @return the form script of this deliverable
	*/
	@Override
	public String getFormScript() {
		return _deliverable.getFormScript();
	}

	/**
	* Returns the gov agency code of this deliverable.
	*
	* @return the gov agency code of this deliverable
	*/
	@Override
	public String getGovAgencyCode() {
		return _deliverable.getGovAgencyCode();
	}

	/**
	* Returns the gov agency name of this deliverable.
	*
	* @return the gov agency name of this deliverable
	*/
	@Override
	public String getGovAgencyName() {
		return _deliverable.getGovAgencyName();
	}

	/**
	* Returns the group ID of this deliverable.
	*
	* @return the group ID of this deliverable
	*/
	@Override
	public long getGroupId() {
		return _deliverable.getGroupId();
	}

	/**
	* Returns the issue date of this deliverable.
	*
	* @return the issue date of this deliverable
	*/
	@Override
	public Date getIssueDate() {
		return _deliverable.getIssueDate();
	}

	/**
	* Returns the modified date of this deliverable.
	*
	* @return the modified date of this deliverable
	*/
	@Override
	public Date getModifiedDate() {
		return _deliverable.getModifiedDate();
	}

	/**
	* Returns the primary key of this deliverable.
	*
	* @return the primary key of this deliverable
	*/
	@Override
	public long getPrimaryKey() {
		return _deliverable.getPrimaryKey();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _deliverable.getPrimaryKeyObj();
	}

	/**
	* Returns the revalidate of this deliverable.
	*
	* @return the revalidate of this deliverable
	*/
	@Override
	public Date getRevalidate() {
		return _deliverable.getRevalidate();
	}

	/**
	* Returns the subject of this deliverable.
	*
	* @return the subject of this deliverable
	*/
	@Override
	public String getSubject() {
		return _deliverable.getSubject();
	}

	/**
	* Returns the user ID of this deliverable.
	*
	* @return the user ID of this deliverable
	*/
	@Override
	public long getUserId() {
		return _deliverable.getUserId();
	}

	/**
	* Returns the user name of this deliverable.
	*
	* @return the user name of this deliverable
	*/
	@Override
	public String getUserName() {
		return _deliverable.getUserName();
	}

	/**
	* Returns the user uuid of this deliverable.
	*
	* @return the user uuid of this deliverable
	*/
	@Override
	public String getUserUuid() {
		return _deliverable.getUserUuid();
	}

	/**
	* Returns the uuid of this deliverable.
	*
	* @return the uuid of this deliverable
	*/
	@Override
	public String getUuid() {
		return _deliverable.getUuid();
	}

	@Override
	public int hashCode() {
		return _deliverable.hashCode();
	}

	@Override
	public boolean isCachedModel() {
		return _deliverable.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _deliverable.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _deliverable.isNew();
	}

	@Override
	public void persist() {
		_deliverable.persist();
	}

	/**
	* Sets the applicant ID no of this deliverable.
	*
	* @param applicantIdNo the applicant ID no of this deliverable
	*/
	@Override
	public void setApplicantIdNo(String applicantIdNo) {
		_deliverable.setApplicantIdNo(applicantIdNo);
	}

	/**
	* Sets the applicant name of this deliverable.
	*
	* @param applicantName the applicant name of this deliverable
	*/
	@Override
	public void setApplicantName(String applicantName) {
		_deliverable.setApplicantName(applicantName);
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_deliverable.setCachedModel(cachedModel);
	}

	/**
	* Sets the company ID of this deliverable.
	*
	* @param companyId the company ID of this deliverable
	*/
	@Override
	public void setCompanyId(long companyId) {
		_deliverable.setCompanyId(companyId);
	}

	/**
	* Sets the create date of this deliverable.
	*
	* @param createDate the create date of this deliverable
	*/
	@Override
	public void setCreateDate(Date createDate) {
		_deliverable.setCreateDate(createDate);
	}

	/**
	* Sets the deliverable code of this deliverable.
	*
	* @param deliverableCode the deliverable code of this deliverable
	*/
	@Override
	public void setDeliverableCode(String deliverableCode) {
		_deliverable.setDeliverableCode(deliverableCode);
	}

	/**
	* Sets the deliverable ID of this deliverable.
	*
	* @param deliverableId the deliverable ID of this deliverable
	*/
	@Override
	public void setDeliverableId(long deliverableId) {
		_deliverable.setDeliverableId(deliverableId);
	}

	/**
	* Sets the deliverable name of this deliverable.
	*
	* @param deliverableName the deliverable name of this deliverable
	*/
	@Override
	public void setDeliverableName(String deliverableName) {
		_deliverable.setDeliverableName(deliverableName);
	}

	/**
	* Sets the deliverable state of this deliverable.
	*
	* @param deliverableState the deliverable state of this deliverable
	*/
	@Override
	public void setDeliverableState(String deliverableState) {
		_deliverable.setDeliverableState(deliverableState);
	}

	/**
	* Sets the deliverable type of this deliverable.
	*
	* @param deliverableType the deliverable type of this deliverable
	*/
	@Override
	public void setDeliverableType(String deliverableType) {
		_deliverable.setDeliverableType(deliverableType);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_deliverable.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_deliverable.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_deliverable.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the expire date of this deliverable.
	*
	* @param expireDate the expire date of this deliverable
	*/
	@Override
	public void setExpireDate(Date expireDate) {
		_deliverable.setExpireDate(expireDate);
	}

	/**
	* Sets the form data of this deliverable.
	*
	* @param formData the form data of this deliverable
	*/
	@Override
	public void setFormData(String formData) {
		_deliverable.setFormData(formData);
	}

	/**
	* Sets the form report of this deliverable.
	*
	* @param formReport the form report of this deliverable
	*/
	@Override
	public void setFormReport(String formReport) {
		_deliverable.setFormReport(formReport);
	}

	/**
	* Sets the form script of this deliverable.
	*
	* @param formScript the form script of this deliverable
	*/
	@Override
	public void setFormScript(String formScript) {
		_deliverable.setFormScript(formScript);
	}

	/**
	* Sets the gov agency code of this deliverable.
	*
	* @param govAgencyCode the gov agency code of this deliverable
	*/
	@Override
	public void setGovAgencyCode(String govAgencyCode) {
		_deliverable.setGovAgencyCode(govAgencyCode);
	}

	/**
	* Sets the gov agency name of this deliverable.
	*
	* @param govAgencyName the gov agency name of this deliverable
	*/
	@Override
	public void setGovAgencyName(String govAgencyName) {
		_deliverable.setGovAgencyName(govAgencyName);
	}

	/**
	* Sets the group ID of this deliverable.
	*
	* @param groupId the group ID of this deliverable
	*/
	@Override
	public void setGroupId(long groupId) {
		_deliverable.setGroupId(groupId);
	}

	/**
	* Sets the issue date of this deliverable.
	*
	* @param issueDate the issue date of this deliverable
	*/
	@Override
	public void setIssueDate(Date issueDate) {
		_deliverable.setIssueDate(issueDate);
	}

	/**
	* Sets the modified date of this deliverable.
	*
	* @param modifiedDate the modified date of this deliverable
	*/
	@Override
	public void setModifiedDate(Date modifiedDate) {
		_deliverable.setModifiedDate(modifiedDate);
	}

	@Override
	public void setNew(boolean n) {
		_deliverable.setNew(n);
	}

	/**
	* Sets the primary key of this deliverable.
	*
	* @param primaryKey the primary key of this deliverable
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_deliverable.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_deliverable.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the revalidate of this deliverable.
	*
	* @param revalidate the revalidate of this deliverable
	*/
	@Override
	public void setRevalidate(Date revalidate) {
		_deliverable.setRevalidate(revalidate);
	}

	/**
	* Sets the subject of this deliverable.
	*
	* @param subject the subject of this deliverable
	*/
	@Override
	public void setSubject(String subject) {
		_deliverable.setSubject(subject);
	}

	/**
	* Sets the user ID of this deliverable.
	*
	* @param userId the user ID of this deliverable
	*/
	@Override
	public void setUserId(long userId) {
		_deliverable.setUserId(userId);
	}

	/**
	* Sets the user name of this deliverable.
	*
	* @param userName the user name of this deliverable
	*/
	@Override
	public void setUserName(String userName) {
		_deliverable.setUserName(userName);
	}

	/**
	* Sets the user uuid of this deliverable.
	*
	* @param userUuid the user uuid of this deliverable
	*/
	@Override
	public void setUserUuid(String userUuid) {
		_deliverable.setUserUuid(userUuid);
	}

	/**
	* Sets the uuid of this deliverable.
	*
	* @param uuid the uuid of this deliverable
	*/
	@Override
	public void setUuid(String uuid) {
		_deliverable.setUuid(uuid);
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<Deliverable> toCacheModel() {
		return _deliverable.toCacheModel();
	}

	@Override
	public Deliverable toEscapedModel() {
		return new DeliverableWrapper(_deliverable.toEscapedModel());
	}

	@Override
	public String toString() {
		return _deliverable.toString();
	}

	@Override
	public Deliverable toUnescapedModel() {
		return new DeliverableWrapper(_deliverable.toUnescapedModel());
	}

	@Override
	public String toXmlString() {
		return _deliverable.toXmlString();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof DeliverableWrapper)) {
			return false;
		}

		DeliverableWrapper deliverableWrapper = (DeliverableWrapper)obj;

		if (Objects.equals(_deliverable, deliverableWrapper._deliverable)) {
			return true;
		}

		return false;
	}

	@Override
	public StagedModelType getStagedModelType() {
		return _deliverable.getStagedModelType();
	}

	@Override
	public Deliverable getWrappedModel() {
		return _deliverable;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _deliverable.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _deliverable.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_deliverable.resetOriginalValues();
	}

	private final Deliverable _deliverable;
}