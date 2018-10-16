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
 * This class is a wrapper for {@link Dossier}.
 * </p>
 *
 * @author huymq
 * @see Dossier
 * @generated
 */
@ProviderType
public class DossierWrapper implements Dossier, ModelWrapper<Dossier> {
	public DossierWrapper(Dossier dossier) {
		_dossier = dossier;
	}

	@Override
	public Class<?> getModelClass() {
		return Dossier.class;
	}

	@Override
	public String getModelClassName() {
		return Dossier.class.getName();
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
		attributes.put("submitting", isSubmitting());
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
		attributes.put("notification", isNotification());
		attributes.put("online", isOnline());
		attributes.put("original", isOriginal());
		attributes.put("serverNo", getServerNo());
		attributes.put("endorsementDate", getEndorsementDate());
		attributes.put("lockState", getLockState());
		attributes.put("originality", getOriginality());
		attributes.put("originDossierId", getOriginDossierId());
		attributes.put("sampleCount", getSampleCount());
		attributes.put("durationUnit", getDurationUnit());
		attributes.put("durationCount", getDurationCount());
		attributes.put("dossierName", getDossierName());

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

		Integer durationUnit = (Integer)attributes.get("durationUnit");

		if (durationUnit != null) {
			setDurationUnit(durationUnit);
		}

		Double durationCount = (Double)attributes.get("durationCount");

		if (durationCount != null) {
			setDurationCount(durationCount);
		}

		String dossierName = (String)attributes.get("dossierName");

		if (dossierName != null) {
			setDossierName(dossierName);
		}
	}

	@Override
	public Object clone() {
		return new DossierWrapper((Dossier)_dossier.clone());
	}

	@Override
	public int compareTo(Dossier dossier) {
		return _dossier.compareTo(dossier);
	}

	/**
	* Returns the address of this dossier.
	*
	* @return the address of this dossier
	*/
	@Override
	public String getAddress() {
		return _dossier.getAddress();
	}

	/**
	* Returns the applicant ID date of this dossier.
	*
	* @return the applicant ID date of this dossier
	*/
	@Override
	public Date getApplicantIdDate() {
		return _dossier.getApplicantIdDate();
	}

	/**
	* Returns the applicant ID no of this dossier.
	*
	* @return the applicant ID no of this dossier
	*/
	@Override
	public String getApplicantIdNo() {
		return _dossier.getApplicantIdNo();
	}

	/**
	* Returns the applicant ID type of this dossier.
	*
	* @return the applicant ID type of this dossier
	*/
	@Override
	public String getApplicantIdType() {
		return _dossier.getApplicantIdType();
	}

	/**
	* Returns the applicant name of this dossier.
	*
	* @return the applicant name of this dossier
	*/
	@Override
	public String getApplicantName() {
		return _dossier.getApplicantName();
	}

	/**
	* Returns the applicant note of this dossier.
	*
	* @return the applicant note of this dossier
	*/
	@Override
	public String getApplicantNote() {
		return _dossier.getApplicantNote();
	}

	/**
	* Returns the brief note of this dossier.
	*
	* @return the brief note of this dossier
	*/
	@Override
	public String getBriefNote() {
		return _dossier.getBriefNote();
	}

	/**
	* Returns the cancelling date of this dossier.
	*
	* @return the cancelling date of this dossier
	*/
	@Override
	public Date getCancellingDate() {
		return _dossier.getCancellingDate();
	}

	/**
	* Returns the city code of this dossier.
	*
	* @return the city code of this dossier
	*/
	@Override
	public String getCityCode() {
		return _dossier.getCityCode();
	}

	/**
	* Returns the city name of this dossier.
	*
	* @return the city name of this dossier
	*/
	@Override
	public String getCityName() {
		return _dossier.getCityName();
	}

	/**
	* Returns the company ID of this dossier.
	*
	* @return the company ID of this dossier
	*/
	@Override
	public long getCompanyId() {
		return _dossier.getCompanyId();
	}

	/**
	* Returns the contact email of this dossier.
	*
	* @return the contact email of this dossier
	*/
	@Override
	public String getContactEmail() {
		return _dossier.getContactEmail();
	}

	/**
	* Returns the contact name of this dossier.
	*
	* @return the contact name of this dossier
	*/
	@Override
	public String getContactName() {
		return _dossier.getContactName();
	}

	/**
	* Returns the contact tel no of this dossier.
	*
	* @return the contact tel no of this dossier
	*/
	@Override
	public String getContactTelNo() {
		return _dossier.getContactTelNo();
	}

	/**
	* Returns the correctting date of this dossier.
	*
	* @return the correctting date of this dossier
	*/
	@Override
	public Date getCorrecttingDate() {
		return _dossier.getCorrecttingDate();
	}

	/**
	* Returns the counter of this dossier.
	*
	* @return the counter of this dossier
	*/
	@Override
	public int getCounter() {
		return _dossier.getCounter();
	}

	/**
	* Returns the create date of this dossier.
	*
	* @return the create date of this dossier
	*/
	@Override
	public Date getCreateDate() {
		return _dossier.getCreateDate();
	}

	/**
	* Returns the delegate address of this dossier.
	*
	* @return the delegate address of this dossier
	*/
	@Override
	public String getDelegateAddress() {
		return _dossier.getDelegateAddress();
	}

	/**
	* Returns the delegate city code of this dossier.
	*
	* @return the delegate city code of this dossier
	*/
	@Override
	public String getDelegateCityCode() {
		return _dossier.getDelegateCityCode();
	}

	/**
	* Returns the delegate city name of this dossier.
	*
	* @return the delegate city name of this dossier
	*/
	@Override
	public String getDelegateCityName() {
		return _dossier.getDelegateCityName();
	}

	/**
	* Returns the delegate district code of this dossier.
	*
	* @return the delegate district code of this dossier
	*/
	@Override
	public String getDelegateDistrictCode() {
		return _dossier.getDelegateDistrictCode();
	}

	/**
	* Returns the delegate district name of this dossier.
	*
	* @return the delegate district name of this dossier
	*/
	@Override
	public String getDelegateDistrictName() {
		return _dossier.getDelegateDistrictName();
	}

	/**
	* Returns the delegate email of this dossier.
	*
	* @return the delegate email of this dossier
	*/
	@Override
	public String getDelegateEmail() {
		return _dossier.getDelegateEmail();
	}

	/**
	* Returns the delegate ID no of this dossier.
	*
	* @return the delegate ID no of this dossier
	*/
	@Override
	public String getDelegateIdNo() {
		return _dossier.getDelegateIdNo();
	}

	/**
	* Returns the delegate name of this dossier.
	*
	* @return the delegate name of this dossier
	*/
	@Override
	public String getDelegateName() {
		return _dossier.getDelegateName();
	}

	/**
	* Returns the delegate tel no of this dossier.
	*
	* @return the delegate tel no of this dossier
	*/
	@Override
	public String getDelegateTelNo() {
		return _dossier.getDelegateTelNo();
	}

	/**
	* Returns the delegate ward code of this dossier.
	*
	* @return the delegate ward code of this dossier
	*/
	@Override
	public String getDelegateWardCode() {
		return _dossier.getDelegateWardCode();
	}

	/**
	* Returns the delegate ward name of this dossier.
	*
	* @return the delegate ward name of this dossier
	*/
	@Override
	public String getDelegateWardName() {
		return _dossier.getDelegateWardName();
	}

	/**
	* Returns the district code of this dossier.
	*
	* @return the district code of this dossier
	*/
	@Override
	public String getDistrictCode() {
		return _dossier.getDistrictCode();
	}

	/**
	* Returns the district name of this dossier.
	*
	* @return the district name of this dossier
	*/
	@Override
	public String getDistrictName() {
		return _dossier.getDistrictName();
	}

	/**
	* Returns the dossier action ID of this dossier.
	*
	* @return the dossier action ID of this dossier
	*/
	@Override
	public long getDossierActionId() {
		return _dossier.getDossierActionId();
	}

	/**
	* Returns the dossier ID of this dossier.
	*
	* @return the dossier ID of this dossier
	*/
	@Override
	public long getDossierId() {
		return _dossier.getDossierId();
	}

	/**
	* Returns the dossier name of this dossier.
	*
	* @return the dossier name of this dossier
	*/
	@Override
	public String getDossierName() {
		return _dossier.getDossierName();
	}

	/**
	* Returns the dossier no of this dossier.
	*
	* @return the dossier no of this dossier
	*/
	@Override
	public String getDossierNo() {
		return _dossier.getDossierNo();
	}

	/**
	* Returns the dossier note of this dossier.
	*
	* @return the dossier note of this dossier
	*/
	@Override
	public String getDossierNote() {
		return _dossier.getDossierNote();
	}

	/**
	* Returns the dossier register of this dossier.
	*
	* @return the dossier register of this dossier
	*/
	@Override
	public String getDossierRegister() {
		return _dossier.getDossierRegister();
	}

	/**
	* Returns the dossier status of this dossier.
	*
	* @return the dossier status of this dossier
	*/
	@Override
	public String getDossierStatus() {
		return _dossier.getDossierStatus();
	}

	/**
	* Returns the dossier status text of this dossier.
	*
	* @return the dossier status text of this dossier
	*/
	@Override
	public String getDossierStatusText() {
		return _dossier.getDossierStatusText();
	}

	/**
	* Returns the dossier sub status of this dossier.
	*
	* @return the dossier sub status of this dossier
	*/
	@Override
	public String getDossierSubStatus() {
		return _dossier.getDossierSubStatus();
	}

	/**
	* Returns the dossier sub status text of this dossier.
	*
	* @return the dossier sub status text of this dossier
	*/
	@Override
	public String getDossierSubStatusText() {
		return _dossier.getDossierSubStatusText();
	}

	/**
	* Returns the dossier template name of this dossier.
	*
	* @return the dossier template name of this dossier
	*/
	@Override
	public String getDossierTemplateName() {
		return _dossier.getDossierTemplateName();
	}

	/**
	* Returns the dossier template no of this dossier.
	*
	* @return the dossier template no of this dossier
	*/
	@Override
	public String getDossierTemplateNo() {
		return _dossier.getDossierTemplateNo();
	}

	/**
	* Returns the due date of this dossier.
	*
	* @return the due date of this dossier
	*/
	@Override
	public Date getDueDate() {
		return _dossier.getDueDate();
	}

	/**
	* Returns the duration count of this dossier.
	*
	* @return the duration count of this dossier
	*/
	@Override
	public double getDurationCount() {
		return _dossier.getDurationCount();
	}

	/**
	* Returns the duration unit of this dossier.
	*
	* @return the duration unit of this dossier
	*/
	@Override
	public int getDurationUnit() {
		return _dossier.getDurationUnit();
	}

	/**
	* Returns the endorsement date of this dossier.
	*
	* @return the endorsement date of this dossier
	*/
	@Override
	public Date getEndorsementDate() {
		return _dossier.getEndorsementDate();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _dossier.getExpandoBridge();
	}

	/**
	* Returns the extend date of this dossier.
	*
	* @return the extend date of this dossier
	*/
	@Override
	public Date getExtendDate() {
		return _dossier.getExtendDate();
	}

	/**
	* Returns the finish date of this dossier.
	*
	* @return the finish date of this dossier
	*/
	@Override
	public Date getFinishDate() {
		return _dossier.getFinishDate();
	}

	/**
	* Returns the folder ID of this dossier.
	*
	* @return the folder ID of this dossier
	*/
	@Override
	public long getFolderId() {
		return _dossier.getFolderId();
	}

	/**
	* Returns the gov agency code of this dossier.
	*
	* @return the gov agency code of this dossier
	*/
	@Override
	public String getGovAgencyCode() {
		return _dossier.getGovAgencyCode();
	}

	/**
	* Returns the gov agency name of this dossier.
	*
	* @return the gov agency name of this dossier
	*/
	@Override
	public String getGovAgencyName() {
		return _dossier.getGovAgencyName();
	}

	/**
	* Returns the group ID of this dossier.
	*
	* @return the group ID of this dossier
	*/
	@Override
	public long getGroupId() {
		return _dossier.getGroupId();
	}

	/**
	* Returns the lock state of this dossier.
	*
	* @return the lock state of this dossier
	*/
	@Override
	public String getLockState() {
		return _dossier.getLockState();
	}

	/**
	* Returns the modified date of this dossier.
	*
	* @return the modified date of this dossier
	*/
	@Override
	public Date getModifiedDate() {
		return _dossier.getModifiedDate();
	}

	/**
	* Returns the notification of this dossier.
	*
	* @return the notification of this dossier
	*/
	@Override
	public boolean getNotification() {
		return _dossier.getNotification();
	}

	/**
	* Returns the online of this dossier.
	*
	* @return the online of this dossier
	*/
	@Override
	public boolean getOnline() {
		return _dossier.getOnline();
	}

	/**
	* Returns the original of this dossier.
	*
	* @return the original of this dossier
	*/
	@Override
	public boolean getOriginal() {
		return _dossier.getOriginal();
	}

	/**
	* Returns the originality of this dossier.
	*
	* @return the originality of this dossier
	*/
	@Override
	public int getOriginality() {
		return _dossier.getOriginality();
	}

	/**
	* Returns the origin dossier ID of this dossier.
	*
	* @return the origin dossier ID of this dossier
	*/
	@Override
	public long getOriginDossierId() {
		return _dossier.getOriginDossierId();
	}

	/**
	* Returns the password of this dossier.
	*
	* @return the password of this dossier
	*/
	@Override
	public String getPassword() {
		return _dossier.getPassword();
	}

	/**
	* Returns the postal address of this dossier.
	*
	* @return the postal address of this dossier
	*/
	@Override
	public String getPostalAddress() {
		return _dossier.getPostalAddress();
	}

	/**
	* Returns the postal city code of this dossier.
	*
	* @return the postal city code of this dossier
	*/
	@Override
	public String getPostalCityCode() {
		return _dossier.getPostalCityCode();
	}

	/**
	* Returns the postal city name of this dossier.
	*
	* @return the postal city name of this dossier
	*/
	@Override
	public String getPostalCityName() {
		return _dossier.getPostalCityName();
	}

	/**
	* Returns the postal district code of this dossier.
	*
	* @return the postal district code of this dossier
	*/
	@Override
	public String getPostalDistrictCode() {
		return _dossier.getPostalDistrictCode();
	}

	/**
	* Returns the postal district name of this dossier.
	*
	* @return the postal district name of this dossier
	*/
	@Override
	public String getPostalDistrictName() {
		return _dossier.getPostalDistrictName();
	}

	/**
	* Returns the postal service code of this dossier.
	*
	* @return the postal service code of this dossier
	*/
	@Override
	public String getPostalServiceCode() {
		return _dossier.getPostalServiceCode();
	}

	/**
	* Returns the postal service name of this dossier.
	*
	* @return the postal service name of this dossier
	*/
	@Override
	public String getPostalServiceName() {
		return _dossier.getPostalServiceName();
	}

	/**
	* Returns the postal tel no of this dossier.
	*
	* @return the postal tel no of this dossier
	*/
	@Override
	public String getPostalTelNo() {
		return _dossier.getPostalTelNo();
	}

	/**
	* Returns the postal ward code of this dossier.
	*
	* @return the postal ward code of this dossier
	*/
	@Override
	public String getPostalWardCode() {
		return _dossier.getPostalWardCode();
	}

	/**
	* Returns the postal ward name of this dossier.
	*
	* @return the postal ward name of this dossier
	*/
	@Override
	public String getPostalWardName() {
		return _dossier.getPostalWardName();
	}

	/**
	* Returns the primary key of this dossier.
	*
	* @return the primary key of this dossier
	*/
	@Override
	public long getPrimaryKey() {
		return _dossier.getPrimaryKey();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _dossier.getPrimaryKeyObj();
	}

	/**
	* Returns the process date of this dossier.
	*
	* @return the process date of this dossier
	*/
	@Override
	public Date getProcessDate() {
		return _dossier.getProcessDate();
	}

	/**
	* Returns the process no of this dossier.
	*
	* @return the process no of this dossier
	*/
	@Override
	public String getProcessNo() {
		return _dossier.getProcessNo();
	}

	/**
	* Returns the receive date of this dossier.
	*
	* @return the receive date of this dossier
	*/
	@Override
	public Date getReceiveDate() {
		return _dossier.getReceiveDate();
	}

	/**
	* Returns the reference uid of this dossier.
	*
	* @return the reference uid of this dossier
	*/
	@Override
	public String getReferenceUid() {
		return _dossier.getReferenceUid();
	}

	/**
	* Returns the register book code of this dossier.
	*
	* @return the register book code of this dossier
	*/
	@Override
	public String getRegisterBookCode() {
		return _dossier.getRegisterBookCode();
	}

	/**
	* Returns the register book name of this dossier.
	*
	* @return the register book name of this dossier
	*/
	@Override
	public String getRegisterBookName() {
		return _dossier.getRegisterBookName();
	}

	/**
	* Returns the release date of this dossier.
	*
	* @return the release date of this dossier
	*/
	@Override
	public Date getReleaseDate() {
		return _dossier.getReleaseDate();
	}

	/**
	* Returns the sample count of this dossier.
	*
	* @return the sample count of this dossier
	*/
	@Override
	public long getSampleCount() {
		return _dossier.getSampleCount();
	}

	/**
	* Returns the server no of this dossier.
	*
	* @return the server no of this dossier
	*/
	@Override
	public String getServerNo() {
		return _dossier.getServerNo();
	}

	/**
	* Returns the service code of this dossier.
	*
	* @return the service code of this dossier
	*/
	@Override
	public String getServiceCode() {
		return _dossier.getServiceCode();
	}

	/**
	* Returns the service name of this dossier.
	*
	* @return the service name of this dossier
	*/
	@Override
	public String getServiceName() {
		return _dossier.getServiceName();
	}

	/**
	* Returns the submission note of this dossier.
	*
	* @return the submission note of this dossier
	*/
	@Override
	public String getSubmissionNote() {
		return _dossier.getSubmissionNote();
	}

	/**
	* Returns the submit date of this dossier.
	*
	* @return the submit date of this dossier
	*/
	@Override
	public Date getSubmitDate() {
		return _dossier.getSubmitDate();
	}

	/**
	* Returns the submitting of this dossier.
	*
	* @return the submitting of this dossier
	*/
	@Override
	public boolean getSubmitting() {
		return _dossier.getSubmitting();
	}

	/**
	* Returns the user ID of this dossier.
	*
	* @return the user ID of this dossier
	*/
	@Override
	public long getUserId() {
		return _dossier.getUserId();
	}

	/**
	* Returns the user name of this dossier.
	*
	* @return the user name of this dossier
	*/
	@Override
	public String getUserName() {
		return _dossier.getUserName();
	}

	/**
	* Returns the user uuid of this dossier.
	*
	* @return the user uuid of this dossier
	*/
	@Override
	public String getUserUuid() {
		return _dossier.getUserUuid();
	}

	/**
	* Returns the uuid of this dossier.
	*
	* @return the uuid of this dossier
	*/
	@Override
	public String getUuid() {
		return _dossier.getUuid();
	}

	/**
	* Returns the via postal of this dossier.
	*
	* @return the via postal of this dossier
	*/
	@Override
	public int getViaPostal() {
		return _dossier.getViaPostal();
	}

	/**
	* Returns the ward code of this dossier.
	*
	* @return the ward code of this dossier
	*/
	@Override
	public String getWardCode() {
		return _dossier.getWardCode();
	}

	/**
	* Returns the ward name of this dossier.
	*
	* @return the ward name of this dossier
	*/
	@Override
	public String getWardName() {
		return _dossier.getWardName();
	}

	@Override
	public int hashCode() {
		return _dossier.hashCode();
	}

	@Override
	public boolean isCachedModel() {
		return _dossier.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _dossier.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _dossier.isNew();
	}

	/**
	* Returns <code>true</code> if this dossier is notification.
	*
	* @return <code>true</code> if this dossier is notification; <code>false</code> otherwise
	*/
	@Override
	public boolean isNotification() {
		return _dossier.isNotification();
	}

	/**
	* Returns <code>true</code> if this dossier is online.
	*
	* @return <code>true</code> if this dossier is online; <code>false</code> otherwise
	*/
	@Override
	public boolean isOnline() {
		return _dossier.isOnline();
	}

	/**
	* Returns <code>true</code> if this dossier is original.
	*
	* @return <code>true</code> if this dossier is original; <code>false</code> otherwise
	*/
	@Override
	public boolean isOriginal() {
		return _dossier.isOriginal();
	}

	/**
	* Returns <code>true</code> if this dossier is submitting.
	*
	* @return <code>true</code> if this dossier is submitting; <code>false</code> otherwise
	*/
	@Override
	public boolean isSubmitting() {
		return _dossier.isSubmitting();
	}

	@Override
	public void persist() {
		_dossier.persist();
	}

	/**
	* Sets the address of this dossier.
	*
	* @param address the address of this dossier
	*/
	@Override
	public void setAddress(String address) {
		_dossier.setAddress(address);
	}

	/**
	* Sets the applicant ID date of this dossier.
	*
	* @param applicantIdDate the applicant ID date of this dossier
	*/
	@Override
	public void setApplicantIdDate(Date applicantIdDate) {
		_dossier.setApplicantIdDate(applicantIdDate);
	}

	/**
	* Sets the applicant ID no of this dossier.
	*
	* @param applicantIdNo the applicant ID no of this dossier
	*/
	@Override
	public void setApplicantIdNo(String applicantIdNo) {
		_dossier.setApplicantIdNo(applicantIdNo);
	}

	/**
	* Sets the applicant ID type of this dossier.
	*
	* @param applicantIdType the applicant ID type of this dossier
	*/
	@Override
	public void setApplicantIdType(String applicantIdType) {
		_dossier.setApplicantIdType(applicantIdType);
	}

	/**
	* Sets the applicant name of this dossier.
	*
	* @param applicantName the applicant name of this dossier
	*/
	@Override
	public void setApplicantName(String applicantName) {
		_dossier.setApplicantName(applicantName);
	}

	/**
	* Sets the applicant note of this dossier.
	*
	* @param applicantNote the applicant note of this dossier
	*/
	@Override
	public void setApplicantNote(String applicantNote) {
		_dossier.setApplicantNote(applicantNote);
	}

	/**
	* Sets the brief note of this dossier.
	*
	* @param briefNote the brief note of this dossier
	*/
	@Override
	public void setBriefNote(String briefNote) {
		_dossier.setBriefNote(briefNote);
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_dossier.setCachedModel(cachedModel);
	}

	/**
	* Sets the cancelling date of this dossier.
	*
	* @param cancellingDate the cancelling date of this dossier
	*/
	@Override
	public void setCancellingDate(Date cancellingDate) {
		_dossier.setCancellingDate(cancellingDate);
	}

	/**
	* Sets the city code of this dossier.
	*
	* @param cityCode the city code of this dossier
	*/
	@Override
	public void setCityCode(String cityCode) {
		_dossier.setCityCode(cityCode);
	}

	/**
	* Sets the city name of this dossier.
	*
	* @param cityName the city name of this dossier
	*/
	@Override
	public void setCityName(String cityName) {
		_dossier.setCityName(cityName);
	}

	/**
	* Sets the company ID of this dossier.
	*
	* @param companyId the company ID of this dossier
	*/
	@Override
	public void setCompanyId(long companyId) {
		_dossier.setCompanyId(companyId);
	}

	/**
	* Sets the contact email of this dossier.
	*
	* @param contactEmail the contact email of this dossier
	*/
	@Override
	public void setContactEmail(String contactEmail) {
		_dossier.setContactEmail(contactEmail);
	}

	/**
	* Sets the contact name of this dossier.
	*
	* @param contactName the contact name of this dossier
	*/
	@Override
	public void setContactName(String contactName) {
		_dossier.setContactName(contactName);
	}

	/**
	* Sets the contact tel no of this dossier.
	*
	* @param contactTelNo the contact tel no of this dossier
	*/
	@Override
	public void setContactTelNo(String contactTelNo) {
		_dossier.setContactTelNo(contactTelNo);
	}

	/**
	* Sets the correctting date of this dossier.
	*
	* @param correcttingDate the correctting date of this dossier
	*/
	@Override
	public void setCorrecttingDate(Date correcttingDate) {
		_dossier.setCorrecttingDate(correcttingDate);
	}

	/**
	* Sets the counter of this dossier.
	*
	* @param counter the counter of this dossier
	*/
	@Override
	public void setCounter(int counter) {
		_dossier.setCounter(counter);
	}

	/**
	* Sets the create date of this dossier.
	*
	* @param createDate the create date of this dossier
	*/
	@Override
	public void setCreateDate(Date createDate) {
		_dossier.setCreateDate(createDate);
	}

	/**
	* Sets the delegate address of this dossier.
	*
	* @param delegateAddress the delegate address of this dossier
	*/
	@Override
	public void setDelegateAddress(String delegateAddress) {
		_dossier.setDelegateAddress(delegateAddress);
	}

	/**
	* Sets the delegate city code of this dossier.
	*
	* @param delegateCityCode the delegate city code of this dossier
	*/
	@Override
	public void setDelegateCityCode(String delegateCityCode) {
		_dossier.setDelegateCityCode(delegateCityCode);
	}

	/**
	* Sets the delegate city name of this dossier.
	*
	* @param delegateCityName the delegate city name of this dossier
	*/
	@Override
	public void setDelegateCityName(String delegateCityName) {
		_dossier.setDelegateCityName(delegateCityName);
	}

	/**
	* Sets the delegate district code of this dossier.
	*
	* @param delegateDistrictCode the delegate district code of this dossier
	*/
	@Override
	public void setDelegateDistrictCode(String delegateDistrictCode) {
		_dossier.setDelegateDistrictCode(delegateDistrictCode);
	}

	/**
	* Sets the delegate district name of this dossier.
	*
	* @param delegateDistrictName the delegate district name of this dossier
	*/
	@Override
	public void setDelegateDistrictName(String delegateDistrictName) {
		_dossier.setDelegateDistrictName(delegateDistrictName);
	}

	/**
	* Sets the delegate email of this dossier.
	*
	* @param delegateEmail the delegate email of this dossier
	*/
	@Override
	public void setDelegateEmail(String delegateEmail) {
		_dossier.setDelegateEmail(delegateEmail);
	}

	/**
	* Sets the delegate ID no of this dossier.
	*
	* @param delegateIdNo the delegate ID no of this dossier
	*/
	@Override
	public void setDelegateIdNo(String delegateIdNo) {
		_dossier.setDelegateIdNo(delegateIdNo);
	}

	/**
	* Sets the delegate name of this dossier.
	*
	* @param delegateName the delegate name of this dossier
	*/
	@Override
	public void setDelegateName(String delegateName) {
		_dossier.setDelegateName(delegateName);
	}

	/**
	* Sets the delegate tel no of this dossier.
	*
	* @param delegateTelNo the delegate tel no of this dossier
	*/
	@Override
	public void setDelegateTelNo(String delegateTelNo) {
		_dossier.setDelegateTelNo(delegateTelNo);
	}

	/**
	* Sets the delegate ward code of this dossier.
	*
	* @param delegateWardCode the delegate ward code of this dossier
	*/
	@Override
	public void setDelegateWardCode(String delegateWardCode) {
		_dossier.setDelegateWardCode(delegateWardCode);
	}

	/**
	* Sets the delegate ward name of this dossier.
	*
	* @param delegateWardName the delegate ward name of this dossier
	*/
	@Override
	public void setDelegateWardName(String delegateWardName) {
		_dossier.setDelegateWardName(delegateWardName);
	}

	/**
	* Sets the district code of this dossier.
	*
	* @param districtCode the district code of this dossier
	*/
	@Override
	public void setDistrictCode(String districtCode) {
		_dossier.setDistrictCode(districtCode);
	}

	/**
	* Sets the district name of this dossier.
	*
	* @param districtName the district name of this dossier
	*/
	@Override
	public void setDistrictName(String districtName) {
		_dossier.setDistrictName(districtName);
	}

	/**
	* Sets the dossier action ID of this dossier.
	*
	* @param dossierActionId the dossier action ID of this dossier
	*/
	@Override
	public void setDossierActionId(long dossierActionId) {
		_dossier.setDossierActionId(dossierActionId);
	}

	/**
	* Sets the dossier ID of this dossier.
	*
	* @param dossierId the dossier ID of this dossier
	*/
	@Override
	public void setDossierId(long dossierId) {
		_dossier.setDossierId(dossierId);
	}

	/**
	* Sets the dossier name of this dossier.
	*
	* @param dossierName the dossier name of this dossier
	*/
	@Override
	public void setDossierName(String dossierName) {
		_dossier.setDossierName(dossierName);
	}

	/**
	* Sets the dossier no of this dossier.
	*
	* @param dossierNo the dossier no of this dossier
	*/
	@Override
	public void setDossierNo(String dossierNo) {
		_dossier.setDossierNo(dossierNo);
	}

	/**
	* Sets the dossier note of this dossier.
	*
	* @param dossierNote the dossier note of this dossier
	*/
	@Override
	public void setDossierNote(String dossierNote) {
		_dossier.setDossierNote(dossierNote);
	}

	/**
	* Sets the dossier register of this dossier.
	*
	* @param dossierRegister the dossier register of this dossier
	*/
	@Override
	public void setDossierRegister(String dossierRegister) {
		_dossier.setDossierRegister(dossierRegister);
	}

	/**
	* Sets the dossier status of this dossier.
	*
	* @param dossierStatus the dossier status of this dossier
	*/
	@Override
	public void setDossierStatus(String dossierStatus) {
		_dossier.setDossierStatus(dossierStatus);
	}

	/**
	* Sets the dossier status text of this dossier.
	*
	* @param dossierStatusText the dossier status text of this dossier
	*/
	@Override
	public void setDossierStatusText(String dossierStatusText) {
		_dossier.setDossierStatusText(dossierStatusText);
	}

	/**
	* Sets the dossier sub status of this dossier.
	*
	* @param dossierSubStatus the dossier sub status of this dossier
	*/
	@Override
	public void setDossierSubStatus(String dossierSubStatus) {
		_dossier.setDossierSubStatus(dossierSubStatus);
	}

	/**
	* Sets the dossier sub status text of this dossier.
	*
	* @param dossierSubStatusText the dossier sub status text of this dossier
	*/
	@Override
	public void setDossierSubStatusText(String dossierSubStatusText) {
		_dossier.setDossierSubStatusText(dossierSubStatusText);
	}

	/**
	* Sets the dossier template name of this dossier.
	*
	* @param dossierTemplateName the dossier template name of this dossier
	*/
	@Override
	public void setDossierTemplateName(String dossierTemplateName) {
		_dossier.setDossierTemplateName(dossierTemplateName);
	}

	/**
	* Sets the dossier template no of this dossier.
	*
	* @param dossierTemplateNo the dossier template no of this dossier
	*/
	@Override
	public void setDossierTemplateNo(String dossierTemplateNo) {
		_dossier.setDossierTemplateNo(dossierTemplateNo);
	}

	/**
	* Sets the due date of this dossier.
	*
	* @param dueDate the due date of this dossier
	*/
	@Override
	public void setDueDate(Date dueDate) {
		_dossier.setDueDate(dueDate);
	}

	/**
	* Sets the duration count of this dossier.
	*
	* @param durationCount the duration count of this dossier
	*/
	@Override
	public void setDurationCount(double durationCount) {
		_dossier.setDurationCount(durationCount);
	}

	/**
	* Sets the duration unit of this dossier.
	*
	* @param durationUnit the duration unit of this dossier
	*/
	@Override
	public void setDurationUnit(int durationUnit) {
		_dossier.setDurationUnit(durationUnit);
	}

	/**
	* Sets the endorsement date of this dossier.
	*
	* @param endorsementDate the endorsement date of this dossier
	*/
	@Override
	public void setEndorsementDate(Date endorsementDate) {
		_dossier.setEndorsementDate(endorsementDate);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_dossier.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_dossier.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_dossier.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the extend date of this dossier.
	*
	* @param extendDate the extend date of this dossier
	*/
	@Override
	public void setExtendDate(Date extendDate) {
		_dossier.setExtendDate(extendDate);
	}

	/**
	* Sets the finish date of this dossier.
	*
	* @param finishDate the finish date of this dossier
	*/
	@Override
	public void setFinishDate(Date finishDate) {
		_dossier.setFinishDate(finishDate);
	}

	/**
	* Sets the folder ID of this dossier.
	*
	* @param folderId the folder ID of this dossier
	*/
	@Override
	public void setFolderId(long folderId) {
		_dossier.setFolderId(folderId);
	}

	/**
	* Sets the gov agency code of this dossier.
	*
	* @param govAgencyCode the gov agency code of this dossier
	*/
	@Override
	public void setGovAgencyCode(String govAgencyCode) {
		_dossier.setGovAgencyCode(govAgencyCode);
	}

	/**
	* Sets the gov agency name of this dossier.
	*
	* @param govAgencyName the gov agency name of this dossier
	*/
	@Override
	public void setGovAgencyName(String govAgencyName) {
		_dossier.setGovAgencyName(govAgencyName);
	}

	/**
	* Sets the group ID of this dossier.
	*
	* @param groupId the group ID of this dossier
	*/
	@Override
	public void setGroupId(long groupId) {
		_dossier.setGroupId(groupId);
	}

	/**
	* Sets the lock state of this dossier.
	*
	* @param lockState the lock state of this dossier
	*/
	@Override
	public void setLockState(String lockState) {
		_dossier.setLockState(lockState);
	}

	/**
	* Sets the modified date of this dossier.
	*
	* @param modifiedDate the modified date of this dossier
	*/
	@Override
	public void setModifiedDate(Date modifiedDate) {
		_dossier.setModifiedDate(modifiedDate);
	}

	@Override
	public void setNew(boolean n) {
		_dossier.setNew(n);
	}

	/**
	* Sets whether this dossier is notification.
	*
	* @param notification the notification of this dossier
	*/
	@Override
	public void setNotification(boolean notification) {
		_dossier.setNotification(notification);
	}

	/**
	* Sets whether this dossier is online.
	*
	* @param online the online of this dossier
	*/
	@Override
	public void setOnline(boolean online) {
		_dossier.setOnline(online);
	}

	/**
	* Sets whether this dossier is original.
	*
	* @param original the original of this dossier
	*/
	@Override
	public void setOriginal(boolean original) {
		_dossier.setOriginal(original);
	}

	/**
	* Sets the originality of this dossier.
	*
	* @param originality the originality of this dossier
	*/
	@Override
	public void setOriginality(int originality) {
		_dossier.setOriginality(originality);
	}

	/**
	* Sets the origin dossier ID of this dossier.
	*
	* @param originDossierId the origin dossier ID of this dossier
	*/
	@Override
	public void setOriginDossierId(long originDossierId) {
		_dossier.setOriginDossierId(originDossierId);
	}

	/**
	* Sets the password of this dossier.
	*
	* @param password the password of this dossier
	*/
	@Override
	public void setPassword(String password) {
		_dossier.setPassword(password);
	}

	/**
	* Sets the postal address of this dossier.
	*
	* @param postalAddress the postal address of this dossier
	*/
	@Override
	public void setPostalAddress(String postalAddress) {
		_dossier.setPostalAddress(postalAddress);
	}

	/**
	* Sets the postal city code of this dossier.
	*
	* @param postalCityCode the postal city code of this dossier
	*/
	@Override
	public void setPostalCityCode(String postalCityCode) {
		_dossier.setPostalCityCode(postalCityCode);
	}

	/**
	* Sets the postal city name of this dossier.
	*
	* @param postalCityName the postal city name of this dossier
	*/
	@Override
	public void setPostalCityName(String postalCityName) {
		_dossier.setPostalCityName(postalCityName);
	}

	/**
	* Sets the postal district code of this dossier.
	*
	* @param postalDistrictCode the postal district code of this dossier
	*/
	@Override
	public void setPostalDistrictCode(String postalDistrictCode) {
		_dossier.setPostalDistrictCode(postalDistrictCode);
	}

	/**
	* Sets the postal district name of this dossier.
	*
	* @param postalDistrictName the postal district name of this dossier
	*/
	@Override
	public void setPostalDistrictName(String postalDistrictName) {
		_dossier.setPostalDistrictName(postalDistrictName);
	}

	/**
	* Sets the postal service code of this dossier.
	*
	* @param postalServiceCode the postal service code of this dossier
	*/
	@Override
	public void setPostalServiceCode(String postalServiceCode) {
		_dossier.setPostalServiceCode(postalServiceCode);
	}

	/**
	* Sets the postal service name of this dossier.
	*
	* @param postalServiceName the postal service name of this dossier
	*/
	@Override
	public void setPostalServiceName(String postalServiceName) {
		_dossier.setPostalServiceName(postalServiceName);
	}

	/**
	* Sets the postal tel no of this dossier.
	*
	* @param postalTelNo the postal tel no of this dossier
	*/
	@Override
	public void setPostalTelNo(String postalTelNo) {
		_dossier.setPostalTelNo(postalTelNo);
	}

	/**
	* Sets the postal ward code of this dossier.
	*
	* @param postalWardCode the postal ward code of this dossier
	*/
	@Override
	public void setPostalWardCode(String postalWardCode) {
		_dossier.setPostalWardCode(postalWardCode);
	}

	/**
	* Sets the postal ward name of this dossier.
	*
	* @param postalWardName the postal ward name of this dossier
	*/
	@Override
	public void setPostalWardName(String postalWardName) {
		_dossier.setPostalWardName(postalWardName);
	}

	/**
	* Sets the primary key of this dossier.
	*
	* @param primaryKey the primary key of this dossier
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_dossier.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_dossier.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the process date of this dossier.
	*
	* @param processDate the process date of this dossier
	*/
	@Override
	public void setProcessDate(Date processDate) {
		_dossier.setProcessDate(processDate);
	}

	/**
	* Sets the process no of this dossier.
	*
	* @param processNo the process no of this dossier
	*/
	@Override
	public void setProcessNo(String processNo) {
		_dossier.setProcessNo(processNo);
	}

	/**
	* Sets the receive date of this dossier.
	*
	* @param receiveDate the receive date of this dossier
	*/
	@Override
	public void setReceiveDate(Date receiveDate) {
		_dossier.setReceiveDate(receiveDate);
	}

	/**
	* Sets the reference uid of this dossier.
	*
	* @param referenceUid the reference uid of this dossier
	*/
	@Override
	public void setReferenceUid(String referenceUid) {
		_dossier.setReferenceUid(referenceUid);
	}

	/**
	* Sets the register book code of this dossier.
	*
	* @param registerBookCode the register book code of this dossier
	*/
	@Override
	public void setRegisterBookCode(String registerBookCode) {
		_dossier.setRegisterBookCode(registerBookCode);
	}

	/**
	* Sets the register book name of this dossier.
	*
	* @param registerBookName the register book name of this dossier
	*/
	@Override
	public void setRegisterBookName(String registerBookName) {
		_dossier.setRegisterBookName(registerBookName);
	}

	/**
	* Sets the release date of this dossier.
	*
	* @param releaseDate the release date of this dossier
	*/
	@Override
	public void setReleaseDate(Date releaseDate) {
		_dossier.setReleaseDate(releaseDate);
	}

	/**
	* Sets the sample count of this dossier.
	*
	* @param sampleCount the sample count of this dossier
	*/
	@Override
	public void setSampleCount(long sampleCount) {
		_dossier.setSampleCount(sampleCount);
	}

	/**
	* Sets the server no of this dossier.
	*
	* @param serverNo the server no of this dossier
	*/
	@Override
	public void setServerNo(String serverNo) {
		_dossier.setServerNo(serverNo);
	}

	/**
	* Sets the service code of this dossier.
	*
	* @param serviceCode the service code of this dossier
	*/
	@Override
	public void setServiceCode(String serviceCode) {
		_dossier.setServiceCode(serviceCode);
	}

	/**
	* Sets the service name of this dossier.
	*
	* @param serviceName the service name of this dossier
	*/
	@Override
	public void setServiceName(String serviceName) {
		_dossier.setServiceName(serviceName);
	}

	/**
	* Sets the submission note of this dossier.
	*
	* @param submissionNote the submission note of this dossier
	*/
	@Override
	public void setSubmissionNote(String submissionNote) {
		_dossier.setSubmissionNote(submissionNote);
	}

	/**
	* Sets the submit date of this dossier.
	*
	* @param submitDate the submit date of this dossier
	*/
	@Override
	public void setSubmitDate(Date submitDate) {
		_dossier.setSubmitDate(submitDate);
	}

	/**
	* Sets whether this dossier is submitting.
	*
	* @param submitting the submitting of this dossier
	*/
	@Override
	public void setSubmitting(boolean submitting) {
		_dossier.setSubmitting(submitting);
	}

	/**
	* Sets the user ID of this dossier.
	*
	* @param userId the user ID of this dossier
	*/
	@Override
	public void setUserId(long userId) {
		_dossier.setUserId(userId);
	}

	/**
	* Sets the user name of this dossier.
	*
	* @param userName the user name of this dossier
	*/
	@Override
	public void setUserName(String userName) {
		_dossier.setUserName(userName);
	}

	/**
	* Sets the user uuid of this dossier.
	*
	* @param userUuid the user uuid of this dossier
	*/
	@Override
	public void setUserUuid(String userUuid) {
		_dossier.setUserUuid(userUuid);
	}

	/**
	* Sets the uuid of this dossier.
	*
	* @param uuid the uuid of this dossier
	*/
	@Override
	public void setUuid(String uuid) {
		_dossier.setUuid(uuid);
	}

	/**
	* Sets the via postal of this dossier.
	*
	* @param viaPostal the via postal of this dossier
	*/
	@Override
	public void setViaPostal(int viaPostal) {
		_dossier.setViaPostal(viaPostal);
	}

	/**
	* Sets the ward code of this dossier.
	*
	* @param wardCode the ward code of this dossier
	*/
	@Override
	public void setWardCode(String wardCode) {
		_dossier.setWardCode(wardCode);
	}

	/**
	* Sets the ward name of this dossier.
	*
	* @param wardName the ward name of this dossier
	*/
	@Override
	public void setWardName(String wardName) {
		_dossier.setWardName(wardName);
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<Dossier> toCacheModel() {
		return _dossier.toCacheModel();
	}

	@Override
	public Dossier toEscapedModel() {
		return new DossierWrapper(_dossier.toEscapedModel());
	}

	@Override
	public String toString() {
		return _dossier.toString();
	}

	@Override
	public Dossier toUnescapedModel() {
		return new DossierWrapper(_dossier.toUnescapedModel());
	}

	@Override
	public String toXmlString() {
		return _dossier.toXmlString();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof DossierWrapper)) {
			return false;
		}

		DossierWrapper dossierWrapper = (DossierWrapper)obj;

		if (Objects.equals(_dossier, dossierWrapper._dossier)) {
			return true;
		}

		return false;
	}

	@Override
	public StagedModelType getStagedModelType() {
		return _dossier.getStagedModelType();
	}

	@Override
	public Dossier getWrappedModel() {
		return _dossier;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _dossier.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _dossier.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_dossier.resetOriginalValues();
	}

	private final Dossier _dossier;
}