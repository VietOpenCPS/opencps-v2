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

package org.opencps.statistic.model;

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
 * This class is a wrapper for {@link OpencpsDossier}.
 * </p>
 *
 * @author khoavu
 * @see OpencpsDossier
 * @generated
 */
@ProviderType
public class OpencpsDossierWrapper implements OpencpsDossier,
	ModelWrapper<OpencpsDossier> {
	public OpencpsDossierWrapper(OpencpsDossier opencpsDossier) {
		_opencpsDossier = opencpsDossier;
	}

	@Override
	public Class<?> getModelClass() {
		return OpencpsDossier.class;
	}

	@Override
	public String getModelClassName() {
		return OpencpsDossier.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("uuid", getUuid());
		attributes.put("dossierId", getDossierId());
		attributes.put("groupId", getGroupId());
		attributes.put("companyId", getCompanyId());
		attributes.put("userId", getUserId());
		attributes.put("userName", getUserName());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("referenceUid", getReferenceUid());
		attributes.put("counter", getCounter());
		attributes.put("registerBookCode", getRegisterBookCode());
		attributes.put("registerBookName", getRegisterBookName());
		attributes.put("dossierRegister", getDossierRegister());
		attributes.put("processNo", getProcessNo());
		attributes.put("serviceCode", getServiceCode());
		attributes.put("serviceName", getServiceName());
		attributes.put("govAgencyCode", getGovAgencyCode());
		attributes.put("govAgencyName", getGovAgencyName());
		attributes.put("applicantName", getApplicantName());
		attributes.put("applicantIdType", getApplicantIdType());
		attributes.put("applicantIdNo", getApplicantIdNo());
		attributes.put("applicantIdDate", getApplicantIdDate());
		attributes.put("address", getAddress());
		attributes.put("cityCode", getCityCode());
		attributes.put("cityName", getCityName());
		attributes.put("districtCode", getDistrictCode());
		attributes.put("districtName", getDistrictName());
		attributes.put("wardCode", getWardCode());
		attributes.put("wardName", getWardName());
		attributes.put("contactName", getContactName());
		attributes.put("contactTelNo", getContactTelNo());
		attributes.put("contactEmail", getContactEmail());
		attributes.put("delegateName", getDelegateName());
		attributes.put("delegateIdNo", getDelegateIdNo());
		attributes.put("delegateTelNo", getDelegateTelNo());
		attributes.put("delegateEmail", getDelegateEmail());
		attributes.put("delegateAddress", getDelegateAddress());
		attributes.put("delegateCityCode", getDelegateCityCode());
		attributes.put("delegateCityName", getDelegateCityName());
		attributes.put("delegateDistrictCode", getDelegateDistrictCode());
		attributes.put("delegateDistrictName", getDelegateDistrictName());
		attributes.put("delegateWardCode", getDelegateWardCode());
		attributes.put("delegateWardName", getDelegateWardName());
		attributes.put("dossierTemplateNo", getDossierTemplateNo());
		attributes.put("dossierTemplateName", getDossierTemplateName());
		attributes.put("dossierNote", getDossierNote());
		attributes.put("submissionNote", getSubmissionNote());
		attributes.put("applicantNote", getApplicantNote());
		attributes.put("briefNote", getBriefNote());
		attributes.put("dossierNo", getDossierNo());
		attributes.put("submitting", getSubmitting());
		attributes.put("processDate", getProcessDate());
		attributes.put("submitDate", getSubmitDate());
		attributes.put("receiveDate", getReceiveDate());
		attributes.put("dueDate", getDueDate());
		attributes.put("extendDate", getExtendDate());
		attributes.put("releaseDate", getReleaseDate());
		attributes.put("finishDate", getFinishDate());
		attributes.put("cancellingDate", getCancellingDate());
		attributes.put("correcttingDate", getCorrecttingDate());
		attributes.put("dossierStatus", getDossierStatus());
		attributes.put("dossierStatusText", getDossierStatusText());
		attributes.put("dossierSubStatus", getDossierSubStatus());
		attributes.put("dossierSubStatusText", getDossierSubStatusText());
		attributes.put("folderId", getFolderId());
		attributes.put("dossierActionId", getDossierActionId());
		attributes.put("viaPostal", getViaPostal());
		attributes.put("postalServiceCode", getPostalServiceCode());
		attributes.put("postalServiceName", getPostalServiceName());
		attributes.put("postalAddress", getPostalAddress());
		attributes.put("postalCityCode", getPostalCityCode());
		attributes.put("postalCityName", getPostalCityName());
		attributes.put("postalDistrictCode", getPostalDistrictCode());
		attributes.put("postalDistrictName", getPostalDistrictName());
		attributes.put("postalWardCode", getPostalWardCode());
		attributes.put("postalWardName", getPostalWardName());
		attributes.put("postalTelNo", getPostalTelNo());
		attributes.put("password", getPassword());
		attributes.put("notification", getNotification());
		attributes.put("online", getOnline());
		attributes.put("original", getOriginal());
		attributes.put("serverNo", getServerNo());
		attributes.put("endorsementDate", getEndorsementDate());
		attributes.put("lockState", getLockState());
		attributes.put("originality", getOriginality());
		attributes.put("originDossierId", getOriginDossierId());
		attributes.put("sampleCount", getSampleCount());
		attributes.put("durationUnit", getDurationUnit());
		attributes.put("durationCount", getDurationCount());

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

		String referenceUid = (String)attributes.get("referenceUid");

		if (referenceUid != null) {
			setReferenceUid(referenceUid);
		}

		Integer counter = (Integer)attributes.get("counter");

		if (counter != null) {
			setCounter(counter);
		}

		String registerBookCode = (String)attributes.get("registerBookCode");

		if (registerBookCode != null) {
			setRegisterBookCode(registerBookCode);
		}

		String registerBookName = (String)attributes.get("registerBookName");

		if (registerBookName != null) {
			setRegisterBookName(registerBookName);
		}

		String dossierRegister = (String)attributes.get("dossierRegister");

		if (dossierRegister != null) {
			setDossierRegister(dossierRegister);
		}

		String processNo = (String)attributes.get("processNo");

		if (processNo != null) {
			setProcessNo(processNo);
		}

		String serviceCode = (String)attributes.get("serviceCode");

		if (serviceCode != null) {
			setServiceCode(serviceCode);
		}

		String serviceName = (String)attributes.get("serviceName");

		if (serviceName != null) {
			setServiceName(serviceName);
		}

		String govAgencyCode = (String)attributes.get("govAgencyCode");

		if (govAgencyCode != null) {
			setGovAgencyCode(govAgencyCode);
		}

		String govAgencyName = (String)attributes.get("govAgencyName");

		if (govAgencyName != null) {
			setGovAgencyName(govAgencyName);
		}

		String applicantName = (String)attributes.get("applicantName");

		if (applicantName != null) {
			setApplicantName(applicantName);
		}

		String applicantIdType = (String)attributes.get("applicantIdType");

		if (applicantIdType != null) {
			setApplicantIdType(applicantIdType);
		}

		String applicantIdNo = (String)attributes.get("applicantIdNo");

		if (applicantIdNo != null) {
			setApplicantIdNo(applicantIdNo);
		}

		Date applicantIdDate = (Date)attributes.get("applicantIdDate");

		if (applicantIdDate != null) {
			setApplicantIdDate(applicantIdDate);
		}

		String address = (String)attributes.get("address");

		if (address != null) {
			setAddress(address);
		}

		String cityCode = (String)attributes.get("cityCode");

		if (cityCode != null) {
			setCityCode(cityCode);
		}

		String cityName = (String)attributes.get("cityName");

		if (cityName != null) {
			setCityName(cityName);
		}

		String districtCode = (String)attributes.get("districtCode");

		if (districtCode != null) {
			setDistrictCode(districtCode);
		}

		String districtName = (String)attributes.get("districtName");

		if (districtName != null) {
			setDistrictName(districtName);
		}

		String wardCode = (String)attributes.get("wardCode");

		if (wardCode != null) {
			setWardCode(wardCode);
		}

		String wardName = (String)attributes.get("wardName");

		if (wardName != null) {
			setWardName(wardName);
		}

		String contactName = (String)attributes.get("contactName");

		if (contactName != null) {
			setContactName(contactName);
		}

		String contactTelNo = (String)attributes.get("contactTelNo");

		if (contactTelNo != null) {
			setContactTelNo(contactTelNo);
		}

		String contactEmail = (String)attributes.get("contactEmail");

		if (contactEmail != null) {
			setContactEmail(contactEmail);
		}

		String delegateName = (String)attributes.get("delegateName");

		if (delegateName != null) {
			setDelegateName(delegateName);
		}

		String delegateIdNo = (String)attributes.get("delegateIdNo");

		if (delegateIdNo != null) {
			setDelegateIdNo(delegateIdNo);
		}

		String delegateTelNo = (String)attributes.get("delegateTelNo");

		if (delegateTelNo != null) {
			setDelegateTelNo(delegateTelNo);
		}

		String delegateEmail = (String)attributes.get("delegateEmail");

		if (delegateEmail != null) {
			setDelegateEmail(delegateEmail);
		}

		String delegateAddress = (String)attributes.get("delegateAddress");

		if (delegateAddress != null) {
			setDelegateAddress(delegateAddress);
		}

		String delegateCityCode = (String)attributes.get("delegateCityCode");

		if (delegateCityCode != null) {
			setDelegateCityCode(delegateCityCode);
		}

		String delegateCityName = (String)attributes.get("delegateCityName");

		if (delegateCityName != null) {
			setDelegateCityName(delegateCityName);
		}

		String delegateDistrictCode = (String)attributes.get(
				"delegateDistrictCode");

		if (delegateDistrictCode != null) {
			setDelegateDistrictCode(delegateDistrictCode);
		}

		String delegateDistrictName = (String)attributes.get(
				"delegateDistrictName");

		if (delegateDistrictName != null) {
			setDelegateDistrictName(delegateDistrictName);
		}

		String delegateWardCode = (String)attributes.get("delegateWardCode");

		if (delegateWardCode != null) {
			setDelegateWardCode(delegateWardCode);
		}

		String delegateWardName = (String)attributes.get("delegateWardName");

		if (delegateWardName != null) {
			setDelegateWardName(delegateWardName);
		}

		String dossierTemplateNo = (String)attributes.get("dossierTemplateNo");

		if (dossierTemplateNo != null) {
			setDossierTemplateNo(dossierTemplateNo);
		}

		String dossierTemplateName = (String)attributes.get(
				"dossierTemplateName");

		if (dossierTemplateName != null) {
			setDossierTemplateName(dossierTemplateName);
		}

		String dossierNote = (String)attributes.get("dossierNote");

		if (dossierNote != null) {
			setDossierNote(dossierNote);
		}

		String submissionNote = (String)attributes.get("submissionNote");

		if (submissionNote != null) {
			setSubmissionNote(submissionNote);
		}

		String applicantNote = (String)attributes.get("applicantNote");

		if (applicantNote != null) {
			setApplicantNote(applicantNote);
		}

		String briefNote = (String)attributes.get("briefNote");

		if (briefNote != null) {
			setBriefNote(briefNote);
		}

		String dossierNo = (String)attributes.get("dossierNo");

		if (dossierNo != null) {
			setDossierNo(dossierNo);
		}

		Boolean submitting = (Boolean)attributes.get("submitting");

		if (submitting != null) {
			setSubmitting(submitting);
		}

		Date processDate = (Date)attributes.get("processDate");

		if (processDate != null) {
			setProcessDate(processDate);
		}

		Date submitDate = (Date)attributes.get("submitDate");

		if (submitDate != null) {
			setSubmitDate(submitDate);
		}

		Date receiveDate = (Date)attributes.get("receiveDate");

		if (receiveDate != null) {
			setReceiveDate(receiveDate);
		}

		Date dueDate = (Date)attributes.get("dueDate");

		if (dueDate != null) {
			setDueDate(dueDate);
		}

		Date extendDate = (Date)attributes.get("extendDate");

		if (extendDate != null) {
			setExtendDate(extendDate);
		}

		Date releaseDate = (Date)attributes.get("releaseDate");

		if (releaseDate != null) {
			setReleaseDate(releaseDate);
		}

		Date finishDate = (Date)attributes.get("finishDate");

		if (finishDate != null) {
			setFinishDate(finishDate);
		}

		Date cancellingDate = (Date)attributes.get("cancellingDate");

		if (cancellingDate != null) {
			setCancellingDate(cancellingDate);
		}

		Date correcttingDate = (Date)attributes.get("correcttingDate");

		if (correcttingDate != null) {
			setCorrecttingDate(correcttingDate);
		}

		String dossierStatus = (String)attributes.get("dossierStatus");

		if (dossierStatus != null) {
			setDossierStatus(dossierStatus);
		}

		String dossierStatusText = (String)attributes.get("dossierStatusText");

		if (dossierStatusText != null) {
			setDossierStatusText(dossierStatusText);
		}

		String dossierSubStatus = (String)attributes.get("dossierSubStatus");

		if (dossierSubStatus != null) {
			setDossierSubStatus(dossierSubStatus);
		}

		String dossierSubStatusText = (String)attributes.get(
				"dossierSubStatusText");

		if (dossierSubStatusText != null) {
			setDossierSubStatusText(dossierSubStatusText);
		}

		Long folderId = (Long)attributes.get("folderId");

		if (folderId != null) {
			setFolderId(folderId);
		}

		Long dossierActionId = (Long)attributes.get("dossierActionId");

		if (dossierActionId != null) {
			setDossierActionId(dossierActionId);
		}

		Integer viaPostal = (Integer)attributes.get("viaPostal");

		if (viaPostal != null) {
			setViaPostal(viaPostal);
		}

		String postalServiceCode = (String)attributes.get("postalServiceCode");

		if (postalServiceCode != null) {
			setPostalServiceCode(postalServiceCode);
		}

		String postalServiceName = (String)attributes.get("postalServiceName");

		if (postalServiceName != null) {
			setPostalServiceName(postalServiceName);
		}

		String postalAddress = (String)attributes.get("postalAddress");

		if (postalAddress != null) {
			setPostalAddress(postalAddress);
		}

		String postalCityCode = (String)attributes.get("postalCityCode");

		if (postalCityCode != null) {
			setPostalCityCode(postalCityCode);
		}

		String postalCityName = (String)attributes.get("postalCityName");

		if (postalCityName != null) {
			setPostalCityName(postalCityName);
		}

		String postalDistrictCode = (String)attributes.get("postalDistrictCode");

		if (postalDistrictCode != null) {
			setPostalDistrictCode(postalDistrictCode);
		}

		String postalDistrictName = (String)attributes.get("postalDistrictName");

		if (postalDistrictName != null) {
			setPostalDistrictName(postalDistrictName);
		}

		String postalWardCode = (String)attributes.get("postalWardCode");

		if (postalWardCode != null) {
			setPostalWardCode(postalWardCode);
		}

		String postalWardName = (String)attributes.get("postalWardName");

		if (postalWardName != null) {
			setPostalWardName(postalWardName);
		}

		String postalTelNo = (String)attributes.get("postalTelNo");

		if (postalTelNo != null) {
			setPostalTelNo(postalTelNo);
		}

		String password = (String)attributes.get("password");

		if (password != null) {
			setPassword(password);
		}

		Boolean notification = (Boolean)attributes.get("notification");

		if (notification != null) {
			setNotification(notification);
		}

		Boolean online = (Boolean)attributes.get("online");

		if (online != null) {
			setOnline(online);
		}

		Boolean original = (Boolean)attributes.get("original");

		if (original != null) {
			setOriginal(original);
		}

		String serverNo = (String)attributes.get("serverNo");

		if (serverNo != null) {
			setServerNo(serverNo);
		}

		Date endorsementDate = (Date)attributes.get("endorsementDate");

		if (endorsementDate != null) {
			setEndorsementDate(endorsementDate);
		}

		String lockState = (String)attributes.get("lockState");

		if (lockState != null) {
			setLockState(lockState);
		}

		Integer originality = (Integer)attributes.get("originality");

		if (originality != null) {
			setOriginality(originality);
		}

		Long originDossierId = (Long)attributes.get("originDossierId");

		if (originDossierId != null) {
			setOriginDossierId(originDossierId);
		}

		Long sampleCount = (Long)attributes.get("sampleCount");

		if (sampleCount != null) {
			setSampleCount(sampleCount);
		}

		Double durationUnit = (Double)attributes.get("durationUnit");

		if (durationUnit != null) {
			setDurationUnit(durationUnit);
		}

		Double durationCount = (Double)attributes.get("durationCount");

		if (durationCount != null) {
			setDurationCount(durationCount);
		}
	}

	@Override
	public OpencpsDossier toEscapedModel() {
		return new OpencpsDossierWrapper(_opencpsDossier.toEscapedModel());
	}

	@Override
	public OpencpsDossier toUnescapedModel() {
		return new OpencpsDossierWrapper(_opencpsDossier.toUnescapedModel());
	}

	/**
	* Returns the notification of this opencps dossier.
	*
	* @return the notification of this opencps dossier
	*/
	@Override
	public boolean getNotification() {
		return _opencpsDossier.getNotification();
	}

	/**
	* Returns the online of this opencps dossier.
	*
	* @return the online of this opencps dossier
	*/
	@Override
	public boolean getOnline() {
		return _opencpsDossier.getOnline();
	}

	/**
	* Returns the original of this opencps dossier.
	*
	* @return the original of this opencps dossier
	*/
	@Override
	public boolean getOriginal() {
		return _opencpsDossier.getOriginal();
	}

	/**
	* Returns the submitting of this opencps dossier.
	*
	* @return the submitting of this opencps dossier
	*/
	@Override
	public boolean getSubmitting() {
		return _opencpsDossier.getSubmitting();
	}

	@Override
	public boolean isCachedModel() {
		return _opencpsDossier.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _opencpsDossier.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _opencpsDossier.isNew();
	}

	/**
	* Returns <code>true</code> if this opencps dossier is notification.
	*
	* @return <code>true</code> if this opencps dossier is notification; <code>false</code> otherwise
	*/
	@Override
	public boolean isNotification() {
		return _opencpsDossier.isNotification();
	}

	/**
	* Returns <code>true</code> if this opencps dossier is online.
	*
	* @return <code>true</code> if this opencps dossier is online; <code>false</code> otherwise
	*/
	@Override
	public boolean isOnline() {
		return _opencpsDossier.isOnline();
	}

	/**
	* Returns <code>true</code> if this opencps dossier is original.
	*
	* @return <code>true</code> if this opencps dossier is original; <code>false</code> otherwise
	*/
	@Override
	public boolean isOriginal() {
		return _opencpsDossier.isOriginal();
	}

	/**
	* Returns <code>true</code> if this opencps dossier is submitting.
	*
	* @return <code>true</code> if this opencps dossier is submitting; <code>false</code> otherwise
	*/
	@Override
	public boolean isSubmitting() {
		return _opencpsDossier.isSubmitting();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _opencpsDossier.getExpandoBridge();
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<OpencpsDossier> toCacheModel() {
		return _opencpsDossier.toCacheModel();
	}

	/**
	* Returns the duration count of this opencps dossier.
	*
	* @return the duration count of this opencps dossier
	*/
	@Override
	public double getDurationCount() {
		return _opencpsDossier.getDurationCount();
	}

	/**
	* Returns the duration unit of this opencps dossier.
	*
	* @return the duration unit of this opencps dossier
	*/
	@Override
	public double getDurationUnit() {
		return _opencpsDossier.getDurationUnit();
	}

	@Override
	public int compareTo(OpencpsDossier opencpsDossier) {
		return _opencpsDossier.compareTo(opencpsDossier);
	}

	/**
	* Returns the counter of this opencps dossier.
	*
	* @return the counter of this opencps dossier
	*/
	@Override
	public int getCounter() {
		return _opencpsDossier.getCounter();
	}

	/**
	* Returns the originality of this opencps dossier.
	*
	* @return the originality of this opencps dossier
	*/
	@Override
	public int getOriginality() {
		return _opencpsDossier.getOriginality();
	}

	/**
	* Returns the via postal of this opencps dossier.
	*
	* @return the via postal of this opencps dossier
	*/
	@Override
	public int getViaPostal() {
		return _opencpsDossier.getViaPostal();
	}

	@Override
	public int hashCode() {
		return _opencpsDossier.hashCode();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _opencpsDossier.getPrimaryKeyObj();
	}

	@Override
	public java.lang.Object clone() {
		return new OpencpsDossierWrapper((OpencpsDossier)_opencpsDossier.clone());
	}

	/**
	* Returns the address of this opencps dossier.
	*
	* @return the address of this opencps dossier
	*/
	@Override
	public java.lang.String getAddress() {
		return _opencpsDossier.getAddress();
	}

	/**
	* Returns the applicant ID no of this opencps dossier.
	*
	* @return the applicant ID no of this opencps dossier
	*/
	@Override
	public java.lang.String getApplicantIdNo() {
		return _opencpsDossier.getApplicantIdNo();
	}

	/**
	* Returns the applicant ID type of this opencps dossier.
	*
	* @return the applicant ID type of this opencps dossier
	*/
	@Override
	public java.lang.String getApplicantIdType() {
		return _opencpsDossier.getApplicantIdType();
	}

	/**
	* Returns the applicant name of this opencps dossier.
	*
	* @return the applicant name of this opencps dossier
	*/
	@Override
	public java.lang.String getApplicantName() {
		return _opencpsDossier.getApplicantName();
	}

	/**
	* Returns the applicant note of this opencps dossier.
	*
	* @return the applicant note of this opencps dossier
	*/
	@Override
	public java.lang.String getApplicantNote() {
		return _opencpsDossier.getApplicantNote();
	}

	/**
	* Returns the brief note of this opencps dossier.
	*
	* @return the brief note of this opencps dossier
	*/
	@Override
	public java.lang.String getBriefNote() {
		return _opencpsDossier.getBriefNote();
	}

	/**
	* Returns the city code of this opencps dossier.
	*
	* @return the city code of this opencps dossier
	*/
	@Override
	public java.lang.String getCityCode() {
		return _opencpsDossier.getCityCode();
	}

	/**
	* Returns the city name of this opencps dossier.
	*
	* @return the city name of this opencps dossier
	*/
	@Override
	public java.lang.String getCityName() {
		return _opencpsDossier.getCityName();
	}

	/**
	* Returns the contact email of this opencps dossier.
	*
	* @return the contact email of this opencps dossier
	*/
	@Override
	public java.lang.String getContactEmail() {
		return _opencpsDossier.getContactEmail();
	}

	/**
	* Returns the contact name of this opencps dossier.
	*
	* @return the contact name of this opencps dossier
	*/
	@Override
	public java.lang.String getContactName() {
		return _opencpsDossier.getContactName();
	}

	/**
	* Returns the contact tel no of this opencps dossier.
	*
	* @return the contact tel no of this opencps dossier
	*/
	@Override
	public java.lang.String getContactTelNo() {
		return _opencpsDossier.getContactTelNo();
	}

	/**
	* Returns the delegate address of this opencps dossier.
	*
	* @return the delegate address of this opencps dossier
	*/
	@Override
	public java.lang.String getDelegateAddress() {
		return _opencpsDossier.getDelegateAddress();
	}

	/**
	* Returns the delegate city code of this opencps dossier.
	*
	* @return the delegate city code of this opencps dossier
	*/
	@Override
	public java.lang.String getDelegateCityCode() {
		return _opencpsDossier.getDelegateCityCode();
	}

	/**
	* Returns the delegate city name of this opencps dossier.
	*
	* @return the delegate city name of this opencps dossier
	*/
	@Override
	public java.lang.String getDelegateCityName() {
		return _opencpsDossier.getDelegateCityName();
	}

	/**
	* Returns the delegate district code of this opencps dossier.
	*
	* @return the delegate district code of this opencps dossier
	*/
	@Override
	public java.lang.String getDelegateDistrictCode() {
		return _opencpsDossier.getDelegateDistrictCode();
	}

	/**
	* Returns the delegate district name of this opencps dossier.
	*
	* @return the delegate district name of this opencps dossier
	*/
	@Override
	public java.lang.String getDelegateDistrictName() {
		return _opencpsDossier.getDelegateDistrictName();
	}

	/**
	* Returns the delegate email of this opencps dossier.
	*
	* @return the delegate email of this opencps dossier
	*/
	@Override
	public java.lang.String getDelegateEmail() {
		return _opencpsDossier.getDelegateEmail();
	}

	/**
	* Returns the delegate ID no of this opencps dossier.
	*
	* @return the delegate ID no of this opencps dossier
	*/
	@Override
	public java.lang.String getDelegateIdNo() {
		return _opencpsDossier.getDelegateIdNo();
	}

	/**
	* Returns the delegate name of this opencps dossier.
	*
	* @return the delegate name of this opencps dossier
	*/
	@Override
	public java.lang.String getDelegateName() {
		return _opencpsDossier.getDelegateName();
	}

	/**
	* Returns the delegate tel no of this opencps dossier.
	*
	* @return the delegate tel no of this opencps dossier
	*/
	@Override
	public java.lang.String getDelegateTelNo() {
		return _opencpsDossier.getDelegateTelNo();
	}

	/**
	* Returns the delegate ward code of this opencps dossier.
	*
	* @return the delegate ward code of this opencps dossier
	*/
	@Override
	public java.lang.String getDelegateWardCode() {
		return _opencpsDossier.getDelegateWardCode();
	}

	/**
	* Returns the delegate ward name of this opencps dossier.
	*
	* @return the delegate ward name of this opencps dossier
	*/
	@Override
	public java.lang.String getDelegateWardName() {
		return _opencpsDossier.getDelegateWardName();
	}

	/**
	* Returns the district code of this opencps dossier.
	*
	* @return the district code of this opencps dossier
	*/
	@Override
	public java.lang.String getDistrictCode() {
		return _opencpsDossier.getDistrictCode();
	}

	/**
	* Returns the district name of this opencps dossier.
	*
	* @return the district name of this opencps dossier
	*/
	@Override
	public java.lang.String getDistrictName() {
		return _opencpsDossier.getDistrictName();
	}

	/**
	* Returns the dossier no of this opencps dossier.
	*
	* @return the dossier no of this opencps dossier
	*/
	@Override
	public java.lang.String getDossierNo() {
		return _opencpsDossier.getDossierNo();
	}

	/**
	* Returns the dossier note of this opencps dossier.
	*
	* @return the dossier note of this opencps dossier
	*/
	@Override
	public java.lang.String getDossierNote() {
		return _opencpsDossier.getDossierNote();
	}

	/**
	* Returns the dossier register of this opencps dossier.
	*
	* @return the dossier register of this opencps dossier
	*/
	@Override
	public java.lang.String getDossierRegister() {
		return _opencpsDossier.getDossierRegister();
	}

	/**
	* Returns the dossier status of this opencps dossier.
	*
	* @return the dossier status of this opencps dossier
	*/
	@Override
	public java.lang.String getDossierStatus() {
		return _opencpsDossier.getDossierStatus();
	}

	/**
	* Returns the dossier status text of this opencps dossier.
	*
	* @return the dossier status text of this opencps dossier
	*/
	@Override
	public java.lang.String getDossierStatusText() {
		return _opencpsDossier.getDossierStatusText();
	}

	/**
	* Returns the dossier sub status of this opencps dossier.
	*
	* @return the dossier sub status of this opencps dossier
	*/
	@Override
	public java.lang.String getDossierSubStatus() {
		return _opencpsDossier.getDossierSubStatus();
	}

	/**
	* Returns the dossier sub status text of this opencps dossier.
	*
	* @return the dossier sub status text of this opencps dossier
	*/
	@Override
	public java.lang.String getDossierSubStatusText() {
		return _opencpsDossier.getDossierSubStatusText();
	}

	/**
	* Returns the dossier template name of this opencps dossier.
	*
	* @return the dossier template name of this opencps dossier
	*/
	@Override
	public java.lang.String getDossierTemplateName() {
		return _opencpsDossier.getDossierTemplateName();
	}

	/**
	* Returns the dossier template no of this opencps dossier.
	*
	* @return the dossier template no of this opencps dossier
	*/
	@Override
	public java.lang.String getDossierTemplateNo() {
		return _opencpsDossier.getDossierTemplateNo();
	}

	/**
	* Returns the gov agency code of this opencps dossier.
	*
	* @return the gov agency code of this opencps dossier
	*/
	@Override
	public java.lang.String getGovAgencyCode() {
		return _opencpsDossier.getGovAgencyCode();
	}

	/**
	* Returns the gov agency name of this opencps dossier.
	*
	* @return the gov agency name of this opencps dossier
	*/
	@Override
	public java.lang.String getGovAgencyName() {
		return _opencpsDossier.getGovAgencyName();
	}

	/**
	* Returns the lock state of this opencps dossier.
	*
	* @return the lock state of this opencps dossier
	*/
	@Override
	public java.lang.String getLockState() {
		return _opencpsDossier.getLockState();
	}

	/**
	* Returns the password of this opencps dossier.
	*
	* @return the password of this opencps dossier
	*/
	@Override
	public java.lang.String getPassword() {
		return _opencpsDossier.getPassword();
	}

	/**
	* Returns the postal address of this opencps dossier.
	*
	* @return the postal address of this opencps dossier
	*/
	@Override
	public java.lang.String getPostalAddress() {
		return _opencpsDossier.getPostalAddress();
	}

	/**
	* Returns the postal city code of this opencps dossier.
	*
	* @return the postal city code of this opencps dossier
	*/
	@Override
	public java.lang.String getPostalCityCode() {
		return _opencpsDossier.getPostalCityCode();
	}

	/**
	* Returns the postal city name of this opencps dossier.
	*
	* @return the postal city name of this opencps dossier
	*/
	@Override
	public java.lang.String getPostalCityName() {
		return _opencpsDossier.getPostalCityName();
	}

	/**
	* Returns the postal district code of this opencps dossier.
	*
	* @return the postal district code of this opencps dossier
	*/
	@Override
	public java.lang.String getPostalDistrictCode() {
		return _opencpsDossier.getPostalDistrictCode();
	}

	/**
	* Returns the postal district name of this opencps dossier.
	*
	* @return the postal district name of this opencps dossier
	*/
	@Override
	public java.lang.String getPostalDistrictName() {
		return _opencpsDossier.getPostalDistrictName();
	}

	/**
	* Returns the postal service code of this opencps dossier.
	*
	* @return the postal service code of this opencps dossier
	*/
	@Override
	public java.lang.String getPostalServiceCode() {
		return _opencpsDossier.getPostalServiceCode();
	}

	/**
	* Returns the postal service name of this opencps dossier.
	*
	* @return the postal service name of this opencps dossier
	*/
	@Override
	public java.lang.String getPostalServiceName() {
		return _opencpsDossier.getPostalServiceName();
	}

	/**
	* Returns the postal tel no of this opencps dossier.
	*
	* @return the postal tel no of this opencps dossier
	*/
	@Override
	public java.lang.String getPostalTelNo() {
		return _opencpsDossier.getPostalTelNo();
	}

	/**
	* Returns the postal ward code of this opencps dossier.
	*
	* @return the postal ward code of this opencps dossier
	*/
	@Override
	public java.lang.String getPostalWardCode() {
		return _opencpsDossier.getPostalWardCode();
	}

	/**
	* Returns the postal ward name of this opencps dossier.
	*
	* @return the postal ward name of this opencps dossier
	*/
	@Override
	public java.lang.String getPostalWardName() {
		return _opencpsDossier.getPostalWardName();
	}

	/**
	* Returns the process no of this opencps dossier.
	*
	* @return the process no of this opencps dossier
	*/
	@Override
	public java.lang.String getProcessNo() {
		return _opencpsDossier.getProcessNo();
	}

	/**
	* Returns the reference uid of this opencps dossier.
	*
	* @return the reference uid of this opencps dossier
	*/
	@Override
	public java.lang.String getReferenceUid() {
		return _opencpsDossier.getReferenceUid();
	}

	/**
	* Returns the register book code of this opencps dossier.
	*
	* @return the register book code of this opencps dossier
	*/
	@Override
	public java.lang.String getRegisterBookCode() {
		return _opencpsDossier.getRegisterBookCode();
	}

	/**
	* Returns the register book name of this opencps dossier.
	*
	* @return the register book name of this opencps dossier
	*/
	@Override
	public java.lang.String getRegisterBookName() {
		return _opencpsDossier.getRegisterBookName();
	}

	/**
	* Returns the server no of this opencps dossier.
	*
	* @return the server no of this opencps dossier
	*/
	@Override
	public java.lang.String getServerNo() {
		return _opencpsDossier.getServerNo();
	}

	/**
	* Returns the service code of this opencps dossier.
	*
	* @return the service code of this opencps dossier
	*/
	@Override
	public java.lang.String getServiceCode() {
		return _opencpsDossier.getServiceCode();
	}

	/**
	* Returns the service name of this opencps dossier.
	*
	* @return the service name of this opencps dossier
	*/
	@Override
	public java.lang.String getServiceName() {
		return _opencpsDossier.getServiceName();
	}

	/**
	* Returns the submission note of this opencps dossier.
	*
	* @return the submission note of this opencps dossier
	*/
	@Override
	public java.lang.String getSubmissionNote() {
		return _opencpsDossier.getSubmissionNote();
	}

	/**
	* Returns the user name of this opencps dossier.
	*
	* @return the user name of this opencps dossier
	*/
	@Override
	public java.lang.String getUserName() {
		return _opencpsDossier.getUserName();
	}

	/**
	* Returns the user uuid of this opencps dossier.
	*
	* @return the user uuid of this opencps dossier
	*/
	@Override
	public java.lang.String getUserUuid() {
		return _opencpsDossier.getUserUuid();
	}

	/**
	* Returns the uuid of this opencps dossier.
	*
	* @return the uuid of this opencps dossier
	*/
	@Override
	public java.lang.String getUuid() {
		return _opencpsDossier.getUuid();
	}

	/**
	* Returns the ward code of this opencps dossier.
	*
	* @return the ward code of this opencps dossier
	*/
	@Override
	public java.lang.String getWardCode() {
		return _opencpsDossier.getWardCode();
	}

	/**
	* Returns the ward name of this opencps dossier.
	*
	* @return the ward name of this opencps dossier
	*/
	@Override
	public java.lang.String getWardName() {
		return _opencpsDossier.getWardName();
	}

	@Override
	public java.lang.String toString() {
		return _opencpsDossier.toString();
	}

	@Override
	public java.lang.String toXmlString() {
		return _opencpsDossier.toXmlString();
	}

	/**
	* Returns the applicant ID date of this opencps dossier.
	*
	* @return the applicant ID date of this opencps dossier
	*/
	@Override
	public Date getApplicantIdDate() {
		return _opencpsDossier.getApplicantIdDate();
	}

	/**
	* Returns the cancelling date of this opencps dossier.
	*
	* @return the cancelling date of this opencps dossier
	*/
	@Override
	public Date getCancellingDate() {
		return _opencpsDossier.getCancellingDate();
	}

	/**
	* Returns the correctting date of this opencps dossier.
	*
	* @return the correctting date of this opencps dossier
	*/
	@Override
	public Date getCorrecttingDate() {
		return _opencpsDossier.getCorrecttingDate();
	}

	/**
	* Returns the create date of this opencps dossier.
	*
	* @return the create date of this opencps dossier
	*/
	@Override
	public Date getCreateDate() {
		return _opencpsDossier.getCreateDate();
	}

	/**
	* Returns the due date of this opencps dossier.
	*
	* @return the due date of this opencps dossier
	*/
	@Override
	public Date getDueDate() {
		return _opencpsDossier.getDueDate();
	}

	/**
	* Returns the endorsement date of this opencps dossier.
	*
	* @return the endorsement date of this opencps dossier
	*/
	@Override
	public Date getEndorsementDate() {
		return _opencpsDossier.getEndorsementDate();
	}

	/**
	* Returns the extend date of this opencps dossier.
	*
	* @return the extend date of this opencps dossier
	*/
	@Override
	public Date getExtendDate() {
		return _opencpsDossier.getExtendDate();
	}

	/**
	* Returns the finish date of this opencps dossier.
	*
	* @return the finish date of this opencps dossier
	*/
	@Override
	public Date getFinishDate() {
		return _opencpsDossier.getFinishDate();
	}

	/**
	* Returns the modified date of this opencps dossier.
	*
	* @return the modified date of this opencps dossier
	*/
	@Override
	public Date getModifiedDate() {
		return _opencpsDossier.getModifiedDate();
	}

	/**
	* Returns the process date of this opencps dossier.
	*
	* @return the process date of this opencps dossier
	*/
	@Override
	public Date getProcessDate() {
		return _opencpsDossier.getProcessDate();
	}

	/**
	* Returns the receive date of this opencps dossier.
	*
	* @return the receive date of this opencps dossier
	*/
	@Override
	public Date getReceiveDate() {
		return _opencpsDossier.getReceiveDate();
	}

	/**
	* Returns the release date of this opencps dossier.
	*
	* @return the release date of this opencps dossier
	*/
	@Override
	public Date getReleaseDate() {
		return _opencpsDossier.getReleaseDate();
	}

	/**
	* Returns the submit date of this opencps dossier.
	*
	* @return the submit date of this opencps dossier
	*/
	@Override
	public Date getSubmitDate() {
		return _opencpsDossier.getSubmitDate();
	}

	/**
	* Returns the company ID of this opencps dossier.
	*
	* @return the company ID of this opencps dossier
	*/
	@Override
	public long getCompanyId() {
		return _opencpsDossier.getCompanyId();
	}

	/**
	* Returns the dossier action ID of this opencps dossier.
	*
	* @return the dossier action ID of this opencps dossier
	*/
	@Override
	public long getDossierActionId() {
		return _opencpsDossier.getDossierActionId();
	}

	/**
	* Returns the dossier ID of this opencps dossier.
	*
	* @return the dossier ID of this opencps dossier
	*/
	@Override
	public long getDossierId() {
		return _opencpsDossier.getDossierId();
	}

	/**
	* Returns the folder ID of this opencps dossier.
	*
	* @return the folder ID of this opencps dossier
	*/
	@Override
	public long getFolderId() {
		return _opencpsDossier.getFolderId();
	}

	/**
	* Returns the group ID of this opencps dossier.
	*
	* @return the group ID of this opencps dossier
	*/
	@Override
	public long getGroupId() {
		return _opencpsDossier.getGroupId();
	}

	/**
	* Returns the origin dossier ID of this opencps dossier.
	*
	* @return the origin dossier ID of this opencps dossier
	*/
	@Override
	public long getOriginDossierId() {
		return _opencpsDossier.getOriginDossierId();
	}

	/**
	* Returns the primary key of this opencps dossier.
	*
	* @return the primary key of this opencps dossier
	*/
	@Override
	public long getPrimaryKey() {
		return _opencpsDossier.getPrimaryKey();
	}

	/**
	* Returns the sample count of this opencps dossier.
	*
	* @return the sample count of this opencps dossier
	*/
	@Override
	public long getSampleCount() {
		return _opencpsDossier.getSampleCount();
	}

	/**
	* Returns the user ID of this opencps dossier.
	*
	* @return the user ID of this opencps dossier
	*/
	@Override
	public long getUserId() {
		return _opencpsDossier.getUserId();
	}

	@Override
	public void persist() {
		_opencpsDossier.persist();
	}

	/**
	* Sets the address of this opencps dossier.
	*
	* @param address the address of this opencps dossier
	*/
	@Override
	public void setAddress(java.lang.String address) {
		_opencpsDossier.setAddress(address);
	}

	/**
	* Sets the applicant ID date of this opencps dossier.
	*
	* @param applicantIdDate the applicant ID date of this opencps dossier
	*/
	@Override
	public void setApplicantIdDate(Date applicantIdDate) {
		_opencpsDossier.setApplicantIdDate(applicantIdDate);
	}

	/**
	* Sets the applicant ID no of this opencps dossier.
	*
	* @param applicantIdNo the applicant ID no of this opencps dossier
	*/
	@Override
	public void setApplicantIdNo(java.lang.String applicantIdNo) {
		_opencpsDossier.setApplicantIdNo(applicantIdNo);
	}

	/**
	* Sets the applicant ID type of this opencps dossier.
	*
	* @param applicantIdType the applicant ID type of this opencps dossier
	*/
	@Override
	public void setApplicantIdType(java.lang.String applicantIdType) {
		_opencpsDossier.setApplicantIdType(applicantIdType);
	}

	/**
	* Sets the applicant name of this opencps dossier.
	*
	* @param applicantName the applicant name of this opencps dossier
	*/
	@Override
	public void setApplicantName(java.lang.String applicantName) {
		_opencpsDossier.setApplicantName(applicantName);
	}

	/**
	* Sets the applicant note of this opencps dossier.
	*
	* @param applicantNote the applicant note of this opencps dossier
	*/
	@Override
	public void setApplicantNote(java.lang.String applicantNote) {
		_opencpsDossier.setApplicantNote(applicantNote);
	}

	/**
	* Sets the brief note of this opencps dossier.
	*
	* @param briefNote the brief note of this opencps dossier
	*/
	@Override
	public void setBriefNote(java.lang.String briefNote) {
		_opencpsDossier.setBriefNote(briefNote);
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_opencpsDossier.setCachedModel(cachedModel);
	}

	/**
	* Sets the cancelling date of this opencps dossier.
	*
	* @param cancellingDate the cancelling date of this opencps dossier
	*/
	@Override
	public void setCancellingDate(Date cancellingDate) {
		_opencpsDossier.setCancellingDate(cancellingDate);
	}

	/**
	* Sets the city code of this opencps dossier.
	*
	* @param cityCode the city code of this opencps dossier
	*/
	@Override
	public void setCityCode(java.lang.String cityCode) {
		_opencpsDossier.setCityCode(cityCode);
	}

	/**
	* Sets the city name of this opencps dossier.
	*
	* @param cityName the city name of this opencps dossier
	*/
	@Override
	public void setCityName(java.lang.String cityName) {
		_opencpsDossier.setCityName(cityName);
	}

	/**
	* Sets the company ID of this opencps dossier.
	*
	* @param companyId the company ID of this opencps dossier
	*/
	@Override
	public void setCompanyId(long companyId) {
		_opencpsDossier.setCompanyId(companyId);
	}

	/**
	* Sets the contact email of this opencps dossier.
	*
	* @param contactEmail the contact email of this opencps dossier
	*/
	@Override
	public void setContactEmail(java.lang.String contactEmail) {
		_opencpsDossier.setContactEmail(contactEmail);
	}

	/**
	* Sets the contact name of this opencps dossier.
	*
	* @param contactName the contact name of this opencps dossier
	*/
	@Override
	public void setContactName(java.lang.String contactName) {
		_opencpsDossier.setContactName(contactName);
	}

	/**
	* Sets the contact tel no of this opencps dossier.
	*
	* @param contactTelNo the contact tel no of this opencps dossier
	*/
	@Override
	public void setContactTelNo(java.lang.String contactTelNo) {
		_opencpsDossier.setContactTelNo(contactTelNo);
	}

	/**
	* Sets the correctting date of this opencps dossier.
	*
	* @param correcttingDate the correctting date of this opencps dossier
	*/
	@Override
	public void setCorrecttingDate(Date correcttingDate) {
		_opencpsDossier.setCorrecttingDate(correcttingDate);
	}

	/**
	* Sets the counter of this opencps dossier.
	*
	* @param counter the counter of this opencps dossier
	*/
	@Override
	public void setCounter(int counter) {
		_opencpsDossier.setCounter(counter);
	}

	/**
	* Sets the create date of this opencps dossier.
	*
	* @param createDate the create date of this opencps dossier
	*/
	@Override
	public void setCreateDate(Date createDate) {
		_opencpsDossier.setCreateDate(createDate);
	}

	/**
	* Sets the delegate address of this opencps dossier.
	*
	* @param delegateAddress the delegate address of this opencps dossier
	*/
	@Override
	public void setDelegateAddress(java.lang.String delegateAddress) {
		_opencpsDossier.setDelegateAddress(delegateAddress);
	}

	/**
	* Sets the delegate city code of this opencps dossier.
	*
	* @param delegateCityCode the delegate city code of this opencps dossier
	*/
	@Override
	public void setDelegateCityCode(java.lang.String delegateCityCode) {
		_opencpsDossier.setDelegateCityCode(delegateCityCode);
	}

	/**
	* Sets the delegate city name of this opencps dossier.
	*
	* @param delegateCityName the delegate city name of this opencps dossier
	*/
	@Override
	public void setDelegateCityName(java.lang.String delegateCityName) {
		_opencpsDossier.setDelegateCityName(delegateCityName);
	}

	/**
	* Sets the delegate district code of this opencps dossier.
	*
	* @param delegateDistrictCode the delegate district code of this opencps dossier
	*/
	@Override
	public void setDelegateDistrictCode(java.lang.String delegateDistrictCode) {
		_opencpsDossier.setDelegateDistrictCode(delegateDistrictCode);
	}

	/**
	* Sets the delegate district name of this opencps dossier.
	*
	* @param delegateDistrictName the delegate district name of this opencps dossier
	*/
	@Override
	public void setDelegateDistrictName(java.lang.String delegateDistrictName) {
		_opencpsDossier.setDelegateDistrictName(delegateDistrictName);
	}

	/**
	* Sets the delegate email of this opencps dossier.
	*
	* @param delegateEmail the delegate email of this opencps dossier
	*/
	@Override
	public void setDelegateEmail(java.lang.String delegateEmail) {
		_opencpsDossier.setDelegateEmail(delegateEmail);
	}

	/**
	* Sets the delegate ID no of this opencps dossier.
	*
	* @param delegateIdNo the delegate ID no of this opencps dossier
	*/
	@Override
	public void setDelegateIdNo(java.lang.String delegateIdNo) {
		_opencpsDossier.setDelegateIdNo(delegateIdNo);
	}

	/**
	* Sets the delegate name of this opencps dossier.
	*
	* @param delegateName the delegate name of this opencps dossier
	*/
	@Override
	public void setDelegateName(java.lang.String delegateName) {
		_opencpsDossier.setDelegateName(delegateName);
	}

	/**
	* Sets the delegate tel no of this opencps dossier.
	*
	* @param delegateTelNo the delegate tel no of this opencps dossier
	*/
	@Override
	public void setDelegateTelNo(java.lang.String delegateTelNo) {
		_opencpsDossier.setDelegateTelNo(delegateTelNo);
	}

	/**
	* Sets the delegate ward code of this opencps dossier.
	*
	* @param delegateWardCode the delegate ward code of this opencps dossier
	*/
	@Override
	public void setDelegateWardCode(java.lang.String delegateWardCode) {
		_opencpsDossier.setDelegateWardCode(delegateWardCode);
	}

	/**
	* Sets the delegate ward name of this opencps dossier.
	*
	* @param delegateWardName the delegate ward name of this opencps dossier
	*/
	@Override
	public void setDelegateWardName(java.lang.String delegateWardName) {
		_opencpsDossier.setDelegateWardName(delegateWardName);
	}

	/**
	* Sets the district code of this opencps dossier.
	*
	* @param districtCode the district code of this opencps dossier
	*/
	@Override
	public void setDistrictCode(java.lang.String districtCode) {
		_opencpsDossier.setDistrictCode(districtCode);
	}

	/**
	* Sets the district name of this opencps dossier.
	*
	* @param districtName the district name of this opencps dossier
	*/
	@Override
	public void setDistrictName(java.lang.String districtName) {
		_opencpsDossier.setDistrictName(districtName);
	}

	/**
	* Sets the dossier action ID of this opencps dossier.
	*
	* @param dossierActionId the dossier action ID of this opencps dossier
	*/
	@Override
	public void setDossierActionId(long dossierActionId) {
		_opencpsDossier.setDossierActionId(dossierActionId);
	}

	/**
	* Sets the dossier ID of this opencps dossier.
	*
	* @param dossierId the dossier ID of this opencps dossier
	*/
	@Override
	public void setDossierId(long dossierId) {
		_opencpsDossier.setDossierId(dossierId);
	}

	/**
	* Sets the dossier no of this opencps dossier.
	*
	* @param dossierNo the dossier no of this opencps dossier
	*/
	@Override
	public void setDossierNo(java.lang.String dossierNo) {
		_opencpsDossier.setDossierNo(dossierNo);
	}

	/**
	* Sets the dossier note of this opencps dossier.
	*
	* @param dossierNote the dossier note of this opencps dossier
	*/
	@Override
	public void setDossierNote(java.lang.String dossierNote) {
		_opencpsDossier.setDossierNote(dossierNote);
	}

	/**
	* Sets the dossier register of this opencps dossier.
	*
	* @param dossierRegister the dossier register of this opencps dossier
	*/
	@Override
	public void setDossierRegister(java.lang.String dossierRegister) {
		_opencpsDossier.setDossierRegister(dossierRegister);
	}

	/**
	* Sets the dossier status of this opencps dossier.
	*
	* @param dossierStatus the dossier status of this opencps dossier
	*/
	@Override
	public void setDossierStatus(java.lang.String dossierStatus) {
		_opencpsDossier.setDossierStatus(dossierStatus);
	}

	/**
	* Sets the dossier status text of this opencps dossier.
	*
	* @param dossierStatusText the dossier status text of this opencps dossier
	*/
	@Override
	public void setDossierStatusText(java.lang.String dossierStatusText) {
		_opencpsDossier.setDossierStatusText(dossierStatusText);
	}

	/**
	* Sets the dossier sub status of this opencps dossier.
	*
	* @param dossierSubStatus the dossier sub status of this opencps dossier
	*/
	@Override
	public void setDossierSubStatus(java.lang.String dossierSubStatus) {
		_opencpsDossier.setDossierSubStatus(dossierSubStatus);
	}

	/**
	* Sets the dossier sub status text of this opencps dossier.
	*
	* @param dossierSubStatusText the dossier sub status text of this opencps dossier
	*/
	@Override
	public void setDossierSubStatusText(java.lang.String dossierSubStatusText) {
		_opencpsDossier.setDossierSubStatusText(dossierSubStatusText);
	}

	/**
	* Sets the dossier template name of this opencps dossier.
	*
	* @param dossierTemplateName the dossier template name of this opencps dossier
	*/
	@Override
	public void setDossierTemplateName(java.lang.String dossierTemplateName) {
		_opencpsDossier.setDossierTemplateName(dossierTemplateName);
	}

	/**
	* Sets the dossier template no of this opencps dossier.
	*
	* @param dossierTemplateNo the dossier template no of this opencps dossier
	*/
	@Override
	public void setDossierTemplateNo(java.lang.String dossierTemplateNo) {
		_opencpsDossier.setDossierTemplateNo(dossierTemplateNo);
	}

	/**
	* Sets the due date of this opencps dossier.
	*
	* @param dueDate the due date of this opencps dossier
	*/
	@Override
	public void setDueDate(Date dueDate) {
		_opencpsDossier.setDueDate(dueDate);
	}

	/**
	* Sets the duration count of this opencps dossier.
	*
	* @param durationCount the duration count of this opencps dossier
	*/
	@Override
	public void setDurationCount(double durationCount) {
		_opencpsDossier.setDurationCount(durationCount);
	}

	/**
	* Sets the duration unit of this opencps dossier.
	*
	* @param durationUnit the duration unit of this opencps dossier
	*/
	@Override
	public void setDurationUnit(double durationUnit) {
		_opencpsDossier.setDurationUnit(durationUnit);
	}

	/**
	* Sets the endorsement date of this opencps dossier.
	*
	* @param endorsementDate the endorsement date of this opencps dossier
	*/
	@Override
	public void setEndorsementDate(Date endorsementDate) {
		_opencpsDossier.setEndorsementDate(endorsementDate);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_opencpsDossier.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_opencpsDossier.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_opencpsDossier.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the extend date of this opencps dossier.
	*
	* @param extendDate the extend date of this opencps dossier
	*/
	@Override
	public void setExtendDate(Date extendDate) {
		_opencpsDossier.setExtendDate(extendDate);
	}

	/**
	* Sets the finish date of this opencps dossier.
	*
	* @param finishDate the finish date of this opencps dossier
	*/
	@Override
	public void setFinishDate(Date finishDate) {
		_opencpsDossier.setFinishDate(finishDate);
	}

	/**
	* Sets the folder ID of this opencps dossier.
	*
	* @param folderId the folder ID of this opencps dossier
	*/
	@Override
	public void setFolderId(long folderId) {
		_opencpsDossier.setFolderId(folderId);
	}

	/**
	* Sets the gov agency code of this opencps dossier.
	*
	* @param govAgencyCode the gov agency code of this opencps dossier
	*/
	@Override
	public void setGovAgencyCode(java.lang.String govAgencyCode) {
		_opencpsDossier.setGovAgencyCode(govAgencyCode);
	}

	/**
	* Sets the gov agency name of this opencps dossier.
	*
	* @param govAgencyName the gov agency name of this opencps dossier
	*/
	@Override
	public void setGovAgencyName(java.lang.String govAgencyName) {
		_opencpsDossier.setGovAgencyName(govAgencyName);
	}

	/**
	* Sets the group ID of this opencps dossier.
	*
	* @param groupId the group ID of this opencps dossier
	*/
	@Override
	public void setGroupId(long groupId) {
		_opencpsDossier.setGroupId(groupId);
	}

	/**
	* Sets the lock state of this opencps dossier.
	*
	* @param lockState the lock state of this opencps dossier
	*/
	@Override
	public void setLockState(java.lang.String lockState) {
		_opencpsDossier.setLockState(lockState);
	}

	/**
	* Sets the modified date of this opencps dossier.
	*
	* @param modifiedDate the modified date of this opencps dossier
	*/
	@Override
	public void setModifiedDate(Date modifiedDate) {
		_opencpsDossier.setModifiedDate(modifiedDate);
	}

	@Override
	public void setNew(boolean n) {
		_opencpsDossier.setNew(n);
	}

	/**
	* Sets whether this opencps dossier is notification.
	*
	* @param notification the notification of this opencps dossier
	*/
	@Override
	public void setNotification(boolean notification) {
		_opencpsDossier.setNotification(notification);
	}

	/**
	* Sets whether this opencps dossier is online.
	*
	* @param online the online of this opencps dossier
	*/
	@Override
	public void setOnline(boolean online) {
		_opencpsDossier.setOnline(online);
	}

	/**
	* Sets the origin dossier ID of this opencps dossier.
	*
	* @param originDossierId the origin dossier ID of this opencps dossier
	*/
	@Override
	public void setOriginDossierId(long originDossierId) {
		_opencpsDossier.setOriginDossierId(originDossierId);
	}

	/**
	* Sets whether this opencps dossier is original.
	*
	* @param original the original of this opencps dossier
	*/
	@Override
	public void setOriginal(boolean original) {
		_opencpsDossier.setOriginal(original);
	}

	/**
	* Sets the originality of this opencps dossier.
	*
	* @param originality the originality of this opencps dossier
	*/
	@Override
	public void setOriginality(int originality) {
		_opencpsDossier.setOriginality(originality);
	}

	/**
	* Sets the password of this opencps dossier.
	*
	* @param password the password of this opencps dossier
	*/
	@Override
	public void setPassword(java.lang.String password) {
		_opencpsDossier.setPassword(password);
	}

	/**
	* Sets the postal address of this opencps dossier.
	*
	* @param postalAddress the postal address of this opencps dossier
	*/
	@Override
	public void setPostalAddress(java.lang.String postalAddress) {
		_opencpsDossier.setPostalAddress(postalAddress);
	}

	/**
	* Sets the postal city code of this opencps dossier.
	*
	* @param postalCityCode the postal city code of this opencps dossier
	*/
	@Override
	public void setPostalCityCode(java.lang.String postalCityCode) {
		_opencpsDossier.setPostalCityCode(postalCityCode);
	}

	/**
	* Sets the postal city name of this opencps dossier.
	*
	* @param postalCityName the postal city name of this opencps dossier
	*/
	@Override
	public void setPostalCityName(java.lang.String postalCityName) {
		_opencpsDossier.setPostalCityName(postalCityName);
	}

	/**
	* Sets the postal district code of this opencps dossier.
	*
	* @param postalDistrictCode the postal district code of this opencps dossier
	*/
	@Override
	public void setPostalDistrictCode(java.lang.String postalDistrictCode) {
		_opencpsDossier.setPostalDistrictCode(postalDistrictCode);
	}

	/**
	* Sets the postal district name of this opencps dossier.
	*
	* @param postalDistrictName the postal district name of this opencps dossier
	*/
	@Override
	public void setPostalDistrictName(java.lang.String postalDistrictName) {
		_opencpsDossier.setPostalDistrictName(postalDistrictName);
	}

	/**
	* Sets the postal service code of this opencps dossier.
	*
	* @param postalServiceCode the postal service code of this opencps dossier
	*/
	@Override
	public void setPostalServiceCode(java.lang.String postalServiceCode) {
		_opencpsDossier.setPostalServiceCode(postalServiceCode);
	}

	/**
	* Sets the postal service name of this opencps dossier.
	*
	* @param postalServiceName the postal service name of this opencps dossier
	*/
	@Override
	public void setPostalServiceName(java.lang.String postalServiceName) {
		_opencpsDossier.setPostalServiceName(postalServiceName);
	}

	/**
	* Sets the postal tel no of this opencps dossier.
	*
	* @param postalTelNo the postal tel no of this opencps dossier
	*/
	@Override
	public void setPostalTelNo(java.lang.String postalTelNo) {
		_opencpsDossier.setPostalTelNo(postalTelNo);
	}

	/**
	* Sets the postal ward code of this opencps dossier.
	*
	* @param postalWardCode the postal ward code of this opencps dossier
	*/
	@Override
	public void setPostalWardCode(java.lang.String postalWardCode) {
		_opencpsDossier.setPostalWardCode(postalWardCode);
	}

	/**
	* Sets the postal ward name of this opencps dossier.
	*
	* @param postalWardName the postal ward name of this opencps dossier
	*/
	@Override
	public void setPostalWardName(java.lang.String postalWardName) {
		_opencpsDossier.setPostalWardName(postalWardName);
	}

	/**
	* Sets the primary key of this opencps dossier.
	*
	* @param primaryKey the primary key of this opencps dossier
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_opencpsDossier.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_opencpsDossier.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the process date of this opencps dossier.
	*
	* @param processDate the process date of this opencps dossier
	*/
	@Override
	public void setProcessDate(Date processDate) {
		_opencpsDossier.setProcessDate(processDate);
	}

	/**
	* Sets the process no of this opencps dossier.
	*
	* @param processNo the process no of this opencps dossier
	*/
	@Override
	public void setProcessNo(java.lang.String processNo) {
		_opencpsDossier.setProcessNo(processNo);
	}

	/**
	* Sets the receive date of this opencps dossier.
	*
	* @param receiveDate the receive date of this opencps dossier
	*/
	@Override
	public void setReceiveDate(Date receiveDate) {
		_opencpsDossier.setReceiveDate(receiveDate);
	}

	/**
	* Sets the reference uid of this opencps dossier.
	*
	* @param referenceUid the reference uid of this opencps dossier
	*/
	@Override
	public void setReferenceUid(java.lang.String referenceUid) {
		_opencpsDossier.setReferenceUid(referenceUid);
	}

	/**
	* Sets the register book code of this opencps dossier.
	*
	* @param registerBookCode the register book code of this opencps dossier
	*/
	@Override
	public void setRegisterBookCode(java.lang.String registerBookCode) {
		_opencpsDossier.setRegisterBookCode(registerBookCode);
	}

	/**
	* Sets the register book name of this opencps dossier.
	*
	* @param registerBookName the register book name of this opencps dossier
	*/
	@Override
	public void setRegisterBookName(java.lang.String registerBookName) {
		_opencpsDossier.setRegisterBookName(registerBookName);
	}

	/**
	* Sets the release date of this opencps dossier.
	*
	* @param releaseDate the release date of this opencps dossier
	*/
	@Override
	public void setReleaseDate(Date releaseDate) {
		_opencpsDossier.setReleaseDate(releaseDate);
	}

	/**
	* Sets the sample count of this opencps dossier.
	*
	* @param sampleCount the sample count of this opencps dossier
	*/
	@Override
	public void setSampleCount(long sampleCount) {
		_opencpsDossier.setSampleCount(sampleCount);
	}

	/**
	* Sets the server no of this opencps dossier.
	*
	* @param serverNo the server no of this opencps dossier
	*/
	@Override
	public void setServerNo(java.lang.String serverNo) {
		_opencpsDossier.setServerNo(serverNo);
	}

	/**
	* Sets the service code of this opencps dossier.
	*
	* @param serviceCode the service code of this opencps dossier
	*/
	@Override
	public void setServiceCode(java.lang.String serviceCode) {
		_opencpsDossier.setServiceCode(serviceCode);
	}

	/**
	* Sets the service name of this opencps dossier.
	*
	* @param serviceName the service name of this opencps dossier
	*/
	@Override
	public void setServiceName(java.lang.String serviceName) {
		_opencpsDossier.setServiceName(serviceName);
	}

	/**
	* Sets the submission note of this opencps dossier.
	*
	* @param submissionNote the submission note of this opencps dossier
	*/
	@Override
	public void setSubmissionNote(java.lang.String submissionNote) {
		_opencpsDossier.setSubmissionNote(submissionNote);
	}

	/**
	* Sets the submit date of this opencps dossier.
	*
	* @param submitDate the submit date of this opencps dossier
	*/
	@Override
	public void setSubmitDate(Date submitDate) {
		_opencpsDossier.setSubmitDate(submitDate);
	}

	/**
	* Sets whether this opencps dossier is submitting.
	*
	* @param submitting the submitting of this opencps dossier
	*/
	@Override
	public void setSubmitting(boolean submitting) {
		_opencpsDossier.setSubmitting(submitting);
	}

	/**
	* Sets the user ID of this opencps dossier.
	*
	* @param userId the user ID of this opencps dossier
	*/
	@Override
	public void setUserId(long userId) {
		_opencpsDossier.setUserId(userId);
	}

	/**
	* Sets the user name of this opencps dossier.
	*
	* @param userName the user name of this opencps dossier
	*/
	@Override
	public void setUserName(java.lang.String userName) {
		_opencpsDossier.setUserName(userName);
	}

	/**
	* Sets the user uuid of this opencps dossier.
	*
	* @param userUuid the user uuid of this opencps dossier
	*/
	@Override
	public void setUserUuid(java.lang.String userUuid) {
		_opencpsDossier.setUserUuid(userUuid);
	}

	/**
	* Sets the uuid of this opencps dossier.
	*
	* @param uuid the uuid of this opencps dossier
	*/
	@Override
	public void setUuid(java.lang.String uuid) {
		_opencpsDossier.setUuid(uuid);
	}

	/**
	* Sets the via postal of this opencps dossier.
	*
	* @param viaPostal the via postal of this opencps dossier
	*/
	@Override
	public void setViaPostal(int viaPostal) {
		_opencpsDossier.setViaPostal(viaPostal);
	}

	/**
	* Sets the ward code of this opencps dossier.
	*
	* @param wardCode the ward code of this opencps dossier
	*/
	@Override
	public void setWardCode(java.lang.String wardCode) {
		_opencpsDossier.setWardCode(wardCode);
	}

	/**
	* Sets the ward name of this opencps dossier.
	*
	* @param wardName the ward name of this opencps dossier
	*/
	@Override
	public void setWardName(java.lang.String wardName) {
		_opencpsDossier.setWardName(wardName);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof OpencpsDossierWrapper)) {
			return false;
		}

		OpencpsDossierWrapper opencpsDossierWrapper = (OpencpsDossierWrapper)obj;

		if (Objects.equals(_opencpsDossier,
					opencpsDossierWrapper._opencpsDossier)) {
			return true;
		}

		return false;
	}

	@Override
	public StagedModelType getStagedModelType() {
		return _opencpsDossier.getStagedModelType();
	}

	@Override
	public OpencpsDossier getWrappedModel() {
		return _opencpsDossier;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _opencpsDossier.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _opencpsDossier.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_opencpsDossier.resetOriginalValues();
	}

	private final OpencpsDossier _opencpsDossier;
}