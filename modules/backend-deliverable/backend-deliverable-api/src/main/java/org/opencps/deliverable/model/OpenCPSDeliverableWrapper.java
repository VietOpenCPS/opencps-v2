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

package org.opencps.deliverable.model;

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
 * This class is a wrapper for {@link OpenCPSDeliverable}.
 * </p>
 *
 * @author binhth
 * @see OpenCPSDeliverable
 * @generated
 */
@ProviderType
public class OpenCPSDeliverableWrapper implements OpenCPSDeliverable,
	ModelWrapper<OpenCPSDeliverable> {
	public OpenCPSDeliverableWrapper(OpenCPSDeliverable openCPSDeliverable) {
		_openCPSDeliverable = openCPSDeliverable;
	}

	@Override
	public Class<?> getModelClass() {
		return OpenCPSDeliverable.class;
	}

	@Override
	public String getModelClassName() {
		return OpenCPSDeliverable.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("uuid", getUuid());
		attributes.put("deliverableId", getDeliverableId());
		attributes.put("groupId", getGroupId());
		attributes.put("companyId", getCompanyId());
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
		attributes.put("formScriptFileId", getFormScriptFileId());
		attributes.put("formReport", getFormReport());
		attributes.put("formReportFileId", getFormReportFileId());
		attributes.put("issueDate", getIssueDate());
		attributes.put("expireDate", getExpireDate());
		attributes.put("revalidate", getRevalidate());
		attributes.put("deliverableState", getDeliverableState());
		attributes.put("fileEntryId", getFileEntryId());
		attributes.put("docSync", getDocSync());
		attributes.put("dossierId", getDossierId());

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

		Long formScriptFileId = (Long)attributes.get("formScriptFileId");

		if (formScriptFileId != null) {
			setFormScriptFileId(formScriptFileId);
		}

		String formReport = (String)attributes.get("formReport");

		if (formReport != null) {
			setFormReport(formReport);
		}

		Long formReportFileId = (Long)attributes.get("formReportFileId");

		if (formReportFileId != null) {
			setFormReportFileId(formReportFileId);
		}

		Date issueDate = (Date)attributes.get("issueDate");

		if (issueDate != null) {
			setIssueDate(issueDate);
		}

		Date expireDate = (Date)attributes.get("expireDate");

		if (expireDate != null) {
			setExpireDate(expireDate);
		}

		Date revalidate = (Date)attributes.get("revalidate");

		if (revalidate != null) {
			setRevalidate(revalidate);
		}

		Integer deliverableState = (Integer)attributes.get("deliverableState");

		if (deliverableState != null) {
			setDeliverableState(deliverableState);
		}

		Long fileEntryId = (Long)attributes.get("fileEntryId");

		if (fileEntryId != null) {
			setFileEntryId(fileEntryId);
		}

		Integer docSync = (Integer)attributes.get("docSync");

		if (docSync != null) {
			setDocSync(docSync);
		}

		Long dossierId = (Long)attributes.get("dossierId");

		if (dossierId != null) {
			setDossierId(dossierId);
		}
	}

	@Override
	public Object clone() {
		return new OpenCPSDeliverableWrapper((OpenCPSDeliverable)_openCPSDeliverable.clone());
	}

	@Override
	public int compareTo(OpenCPSDeliverable openCPSDeliverable) {
		return _openCPSDeliverable.compareTo(openCPSDeliverable);
	}

	/**
	* Returns the applicant ID no of this open cps deliverable.
	*
	* @return the applicant ID no of this open cps deliverable
	*/
	@Override
	public String getApplicantIdNo() {
		return _openCPSDeliverable.getApplicantIdNo();
	}

	/**
	* Returns the applicant name of this open cps deliverable.
	*
	* @return the applicant name of this open cps deliverable
	*/
	@Override
	public String getApplicantName() {
		return _openCPSDeliverable.getApplicantName();
	}

	/**
	* Returns the company ID of this open cps deliverable.
	*
	* @return the company ID of this open cps deliverable
	*/
	@Override
	public long getCompanyId() {
		return _openCPSDeliverable.getCompanyId();
	}

	/**
	* Returns the create date of this open cps deliverable.
	*
	* @return the create date of this open cps deliverable
	*/
	@Override
	public Date getCreateDate() {
		return _openCPSDeliverable.getCreateDate();
	}

	/**
	* Returns the deliverable code of this open cps deliverable.
	*
	* @return the deliverable code of this open cps deliverable
	*/
	@Override
	public String getDeliverableCode() {
		return _openCPSDeliverable.getDeliverableCode();
	}

	/**
	* Returns the deliverable ID of this open cps deliverable.
	*
	* @return the deliverable ID of this open cps deliverable
	*/
	@Override
	public long getDeliverableId() {
		return _openCPSDeliverable.getDeliverableId();
	}

	/**
	* Returns the deliverable name of this open cps deliverable.
	*
	* @return the deliverable name of this open cps deliverable
	*/
	@Override
	public String getDeliverableName() {
		return _openCPSDeliverable.getDeliverableName();
	}

	/**
	* Returns the deliverable state of this open cps deliverable.
	*
	* @return the deliverable state of this open cps deliverable
	*/
	@Override
	public int getDeliverableState() {
		return _openCPSDeliverable.getDeliverableState();
	}

	/**
	* Returns the deliverable type of this open cps deliverable.
	*
	* @return the deliverable type of this open cps deliverable
	*/
	@Override
	public String getDeliverableType() {
		return _openCPSDeliverable.getDeliverableType();
	}

	/**
	* Returns the doc sync of this open cps deliverable.
	*
	* @return the doc sync of this open cps deliverable
	*/
	@Override
	public int getDocSync() {
		return _openCPSDeliverable.getDocSync();
	}

	/**
	* Returns the dossier ID of this open cps deliverable.
	*
	* @return the dossier ID of this open cps deliverable
	*/
	@Override
	public long getDossierId() {
		return _openCPSDeliverable.getDossierId();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _openCPSDeliverable.getExpandoBridge();
	}

	/**
	* Returns the expire date of this open cps deliverable.
	*
	* @return the expire date of this open cps deliverable
	*/
	@Override
	public Date getExpireDate() {
		return _openCPSDeliverable.getExpireDate();
	}

	/**
	* Returns the file entry ID of this open cps deliverable.
	*
	* @return the file entry ID of this open cps deliverable
	*/
	@Override
	public long getFileEntryId() {
		return _openCPSDeliverable.getFileEntryId();
	}

	/**
	* Returns the form data of this open cps deliverable.
	*
	* @return the form data of this open cps deliverable
	*/
	@Override
	public String getFormData() {
		return _openCPSDeliverable.getFormData();
	}

	/**
	* Returns the form report of this open cps deliverable.
	*
	* @return the form report of this open cps deliverable
	*/
	@Override
	public String getFormReport() {
		return _openCPSDeliverable.getFormReport();
	}

	/**
	* Returns the form report file ID of this open cps deliverable.
	*
	* @return the form report file ID of this open cps deliverable
	*/
	@Override
	public long getFormReportFileId() {
		return _openCPSDeliverable.getFormReportFileId();
	}

	/**
	* Returns the form script of this open cps deliverable.
	*
	* @return the form script of this open cps deliverable
	*/
	@Override
	public String getFormScript() {
		return _openCPSDeliverable.getFormScript();
	}

	/**
	* Returns the form script file ID of this open cps deliverable.
	*
	* @return the form script file ID of this open cps deliverable
	*/
	@Override
	public long getFormScriptFileId() {
		return _openCPSDeliverable.getFormScriptFileId();
	}

	/**
	* Returns the gov agency code of this open cps deliverable.
	*
	* @return the gov agency code of this open cps deliverable
	*/
	@Override
	public String getGovAgencyCode() {
		return _openCPSDeliverable.getGovAgencyCode();
	}

	/**
	* Returns the gov agency name of this open cps deliverable.
	*
	* @return the gov agency name of this open cps deliverable
	*/
	@Override
	public String getGovAgencyName() {
		return _openCPSDeliverable.getGovAgencyName();
	}

	/**
	* Returns the group ID of this open cps deliverable.
	*
	* @return the group ID of this open cps deliverable
	*/
	@Override
	public long getGroupId() {
		return _openCPSDeliverable.getGroupId();
	}

	/**
	* Returns the issue date of this open cps deliverable.
	*
	* @return the issue date of this open cps deliverable
	*/
	@Override
	public Date getIssueDate() {
		return _openCPSDeliverable.getIssueDate();
	}

	/**
	* Returns the modified date of this open cps deliverable.
	*
	* @return the modified date of this open cps deliverable
	*/
	@Override
	public Date getModifiedDate() {
		return _openCPSDeliverable.getModifiedDate();
	}

	/**
	* Returns the primary key of this open cps deliverable.
	*
	* @return the primary key of this open cps deliverable
	*/
	@Override
	public long getPrimaryKey() {
		return _openCPSDeliverable.getPrimaryKey();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _openCPSDeliverable.getPrimaryKeyObj();
	}

	/**
	* Returns the revalidate of this open cps deliverable.
	*
	* @return the revalidate of this open cps deliverable
	*/
	@Override
	public Date getRevalidate() {
		return _openCPSDeliverable.getRevalidate();
	}

	/**
	* Returns the subject of this open cps deliverable.
	*
	* @return the subject of this open cps deliverable
	*/
	@Override
	public String getSubject() {
		return _openCPSDeliverable.getSubject();
	}

	/**
	* Returns the user ID of this open cps deliverable.
	*
	* @return the user ID of this open cps deliverable
	*/
	@Override
	public long getUserId() {
		return _openCPSDeliverable.getUserId();
	}

	/**
	* Returns the user name of this open cps deliverable.
	*
	* @return the user name of this open cps deliverable
	*/
	@Override
	public String getUserName() {
		return _openCPSDeliverable.getUserName();
	}

	/**
	* Returns the user uuid of this open cps deliverable.
	*
	* @return the user uuid of this open cps deliverable
	*/
	@Override
	public String getUserUuid() {
		return _openCPSDeliverable.getUserUuid();
	}

	/**
	* Returns the uuid of this open cps deliverable.
	*
	* @return the uuid of this open cps deliverable
	*/
	@Override
	public String getUuid() {
		return _openCPSDeliverable.getUuid();
	}

	@Override
	public int hashCode() {
		return _openCPSDeliverable.hashCode();
	}

	@Override
	public boolean isCachedModel() {
		return _openCPSDeliverable.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _openCPSDeliverable.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _openCPSDeliverable.isNew();
	}

	@Override
	public void persist() {
		_openCPSDeliverable.persist();
	}

	/**
	* Sets the applicant ID no of this open cps deliverable.
	*
	* @param applicantIdNo the applicant ID no of this open cps deliverable
	*/
	@Override
	public void setApplicantIdNo(String applicantIdNo) {
		_openCPSDeliverable.setApplicantIdNo(applicantIdNo);
	}

	/**
	* Sets the applicant name of this open cps deliverable.
	*
	* @param applicantName the applicant name of this open cps deliverable
	*/
	@Override
	public void setApplicantName(String applicantName) {
		_openCPSDeliverable.setApplicantName(applicantName);
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_openCPSDeliverable.setCachedModel(cachedModel);
	}

	/**
	* Sets the company ID of this open cps deliverable.
	*
	* @param companyId the company ID of this open cps deliverable
	*/
	@Override
	public void setCompanyId(long companyId) {
		_openCPSDeliverable.setCompanyId(companyId);
	}

	/**
	* Sets the create date of this open cps deliverable.
	*
	* @param createDate the create date of this open cps deliverable
	*/
	@Override
	public void setCreateDate(Date createDate) {
		_openCPSDeliverable.setCreateDate(createDate);
	}

	/**
	* Sets the deliverable code of this open cps deliverable.
	*
	* @param deliverableCode the deliverable code of this open cps deliverable
	*/
	@Override
	public void setDeliverableCode(String deliverableCode) {
		_openCPSDeliverable.setDeliverableCode(deliverableCode);
	}

	/**
	* Sets the deliverable ID of this open cps deliverable.
	*
	* @param deliverableId the deliverable ID of this open cps deliverable
	*/
	@Override
	public void setDeliverableId(long deliverableId) {
		_openCPSDeliverable.setDeliverableId(deliverableId);
	}

	/**
	* Sets the deliverable name of this open cps deliverable.
	*
	* @param deliverableName the deliverable name of this open cps deliverable
	*/
	@Override
	public void setDeliverableName(String deliverableName) {
		_openCPSDeliverable.setDeliverableName(deliverableName);
	}

	/**
	* Sets the deliverable state of this open cps deliverable.
	*
	* @param deliverableState the deliverable state of this open cps deliverable
	*/
	@Override
	public void setDeliverableState(int deliverableState) {
		_openCPSDeliverable.setDeliverableState(deliverableState);
	}

	/**
	* Sets the deliverable type of this open cps deliverable.
	*
	* @param deliverableType the deliverable type of this open cps deliverable
	*/
	@Override
	public void setDeliverableType(String deliverableType) {
		_openCPSDeliverable.setDeliverableType(deliverableType);
	}

	/**
	* Sets the doc sync of this open cps deliverable.
	*
	* @param docSync the doc sync of this open cps deliverable
	*/
	@Override
	public void setDocSync(int docSync) {
		_openCPSDeliverable.setDocSync(docSync);
	}

	/**
	* Sets the dossier ID of this open cps deliverable.
	*
	* @param dossierId the dossier ID of this open cps deliverable
	*/
	@Override
	public void setDossierId(long dossierId) {
		_openCPSDeliverable.setDossierId(dossierId);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_openCPSDeliverable.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_openCPSDeliverable.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_openCPSDeliverable.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the expire date of this open cps deliverable.
	*
	* @param expireDate the expire date of this open cps deliverable
	*/
	@Override
	public void setExpireDate(Date expireDate) {
		_openCPSDeliverable.setExpireDate(expireDate);
	}

	/**
	* Sets the file entry ID of this open cps deliverable.
	*
	* @param fileEntryId the file entry ID of this open cps deliverable
	*/
	@Override
	public void setFileEntryId(long fileEntryId) {
		_openCPSDeliverable.setFileEntryId(fileEntryId);
	}

	/**
	* Sets the form data of this open cps deliverable.
	*
	* @param formData the form data of this open cps deliverable
	*/
	@Override
	public void setFormData(String formData) {
		_openCPSDeliverable.setFormData(formData);
	}

	/**
	* Sets the form report of this open cps deliverable.
	*
	* @param formReport the form report of this open cps deliverable
	*/
	@Override
	public void setFormReport(String formReport) {
		_openCPSDeliverable.setFormReport(formReport);
	}

	/**
	* Sets the form report file ID of this open cps deliverable.
	*
	* @param formReportFileId the form report file ID of this open cps deliverable
	*/
	@Override
	public void setFormReportFileId(long formReportFileId) {
		_openCPSDeliverable.setFormReportFileId(formReportFileId);
	}

	/**
	* Sets the form script of this open cps deliverable.
	*
	* @param formScript the form script of this open cps deliverable
	*/
	@Override
	public void setFormScript(String formScript) {
		_openCPSDeliverable.setFormScript(formScript);
	}

	/**
	* Sets the form script file ID of this open cps deliverable.
	*
	* @param formScriptFileId the form script file ID of this open cps deliverable
	*/
	@Override
	public void setFormScriptFileId(long formScriptFileId) {
		_openCPSDeliverable.setFormScriptFileId(formScriptFileId);
	}

	/**
	* Sets the gov agency code of this open cps deliverable.
	*
	* @param govAgencyCode the gov agency code of this open cps deliverable
	*/
	@Override
	public void setGovAgencyCode(String govAgencyCode) {
		_openCPSDeliverable.setGovAgencyCode(govAgencyCode);
	}

	/**
	* Sets the gov agency name of this open cps deliverable.
	*
	* @param govAgencyName the gov agency name of this open cps deliverable
	*/
	@Override
	public void setGovAgencyName(String govAgencyName) {
		_openCPSDeliverable.setGovAgencyName(govAgencyName);
	}

	/**
	* Sets the group ID of this open cps deliverable.
	*
	* @param groupId the group ID of this open cps deliverable
	*/
	@Override
	public void setGroupId(long groupId) {
		_openCPSDeliverable.setGroupId(groupId);
	}

	/**
	* Sets the issue date of this open cps deliverable.
	*
	* @param issueDate the issue date of this open cps deliverable
	*/
	@Override
	public void setIssueDate(Date issueDate) {
		_openCPSDeliverable.setIssueDate(issueDate);
	}

	/**
	* Sets the modified date of this open cps deliverable.
	*
	* @param modifiedDate the modified date of this open cps deliverable
	*/
	@Override
	public void setModifiedDate(Date modifiedDate) {
		_openCPSDeliverable.setModifiedDate(modifiedDate);
	}

	@Override
	public void setNew(boolean n) {
		_openCPSDeliverable.setNew(n);
	}

	/**
	* Sets the primary key of this open cps deliverable.
	*
	* @param primaryKey the primary key of this open cps deliverable
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_openCPSDeliverable.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_openCPSDeliverable.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the revalidate of this open cps deliverable.
	*
	* @param revalidate the revalidate of this open cps deliverable
	*/
	@Override
	public void setRevalidate(Date revalidate) {
		_openCPSDeliverable.setRevalidate(revalidate);
	}

	/**
	* Sets the subject of this open cps deliverable.
	*
	* @param subject the subject of this open cps deliverable
	*/
	@Override
	public void setSubject(String subject) {
		_openCPSDeliverable.setSubject(subject);
	}

	/**
	* Sets the user ID of this open cps deliverable.
	*
	* @param userId the user ID of this open cps deliverable
	*/
	@Override
	public void setUserId(long userId) {
		_openCPSDeliverable.setUserId(userId);
	}

	/**
	* Sets the user name of this open cps deliverable.
	*
	* @param userName the user name of this open cps deliverable
	*/
	@Override
	public void setUserName(String userName) {
		_openCPSDeliverable.setUserName(userName);
	}

	/**
	* Sets the user uuid of this open cps deliverable.
	*
	* @param userUuid the user uuid of this open cps deliverable
	*/
	@Override
	public void setUserUuid(String userUuid) {
		_openCPSDeliverable.setUserUuid(userUuid);
	}

	/**
	* Sets the uuid of this open cps deliverable.
	*
	* @param uuid the uuid of this open cps deliverable
	*/
	@Override
	public void setUuid(String uuid) {
		_openCPSDeliverable.setUuid(uuid);
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<OpenCPSDeliverable> toCacheModel() {
		return _openCPSDeliverable.toCacheModel();
	}

	@Override
	public OpenCPSDeliverable toEscapedModel() {
		return new OpenCPSDeliverableWrapper(_openCPSDeliverable.toEscapedModel());
	}

	@Override
	public String toString() {
		return _openCPSDeliverable.toString();
	}

	@Override
	public OpenCPSDeliverable toUnescapedModel() {
		return new OpenCPSDeliverableWrapper(_openCPSDeliverable.toUnescapedModel());
	}

	@Override
	public String toXmlString() {
		return _openCPSDeliverable.toXmlString();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof OpenCPSDeliverableWrapper)) {
			return false;
		}

		OpenCPSDeliverableWrapper openCPSDeliverableWrapper = (OpenCPSDeliverableWrapper)obj;

		if (Objects.equals(_openCPSDeliverable,
					openCPSDeliverableWrapper._openCPSDeliverable)) {
			return true;
		}

		return false;
	}

	@Override
	public StagedModelType getStagedModelType() {
		return _openCPSDeliverable.getStagedModelType();
	}

	@Override
	public OpenCPSDeliverable getWrappedModel() {
		return _openCPSDeliverable;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _openCPSDeliverable.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _openCPSDeliverable.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_openCPSDeliverable.resetOriginalValues();
	}

	private final OpenCPSDeliverable _openCPSDeliverable;
}