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

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * This class is used by SOAP remote services.
 *
 * @author khoavu
 * @generated
 */
@ProviderType
public class OpencpsDossierSoap implements Serializable {
	public static OpencpsDossierSoap toSoapModel(OpencpsDossier model) {
		OpencpsDossierSoap soapModel = new OpencpsDossierSoap();

		soapModel.setUuid(model.getUuid());
		soapModel.setDossierId(model.getDossierId());
		soapModel.setGroupId(model.getGroupId());
		soapModel.setCompanyId(model.getCompanyId());
		soapModel.setUserId(model.getUserId());
		soapModel.setUserName(model.getUserName());
		soapModel.setCreateDate(model.getCreateDate());
		soapModel.setModifiedDate(model.getModifiedDate());
		soapModel.setReferenceUid(model.getReferenceUid());
		soapModel.setCounter(model.getCounter());
		soapModel.setRegisterBookCode(model.getRegisterBookCode());
		soapModel.setRegisterBookName(model.getRegisterBookName());
		soapModel.setDossierRegister(model.getDossierRegister());
		soapModel.setProcessNo(model.getProcessNo());
		soapModel.setServiceCode(model.getServiceCode());
		soapModel.setServiceName(model.getServiceName());
		soapModel.setGovAgencyCode(model.getGovAgencyCode());
		soapModel.setGovAgencyName(model.getGovAgencyName());
		soapModel.setApplicantName(model.getApplicantName());
		soapModel.setApplicantIdType(model.getApplicantIdType());
		soapModel.setApplicantIdNo(model.getApplicantIdNo());
		soapModel.setApplicantIdDate(model.getApplicantIdDate());
		soapModel.setAddress(model.getAddress());
		soapModel.setCityCode(model.getCityCode());
		soapModel.setCityName(model.getCityName());
		soapModel.setDistrictCode(model.getDistrictCode());
		soapModel.setDistrictName(model.getDistrictName());
		soapModel.setWardCode(model.getWardCode());
		soapModel.setWardName(model.getWardName());
		soapModel.setContactName(model.getContactName());
		soapModel.setContactTelNo(model.getContactTelNo());
		soapModel.setContactEmail(model.getContactEmail());
		soapModel.setDelegateName(model.getDelegateName());
		soapModel.setDelegateIdNo(model.getDelegateIdNo());
		soapModel.setDelegateTelNo(model.getDelegateTelNo());
		soapModel.setDelegateEmail(model.getDelegateEmail());
		soapModel.setDelegateAddress(model.getDelegateAddress());
		soapModel.setDelegateCityCode(model.getDelegateCityCode());
		soapModel.setDelegateCityName(model.getDelegateCityName());
		soapModel.setDelegateDistrictCode(model.getDelegateDistrictCode());
		soapModel.setDelegateDistrictName(model.getDelegateDistrictName());
		soapModel.setDelegateWardCode(model.getDelegateWardCode());
		soapModel.setDelegateWardName(model.getDelegateWardName());
		soapModel.setDossierTemplateNo(model.getDossierTemplateNo());
		soapModel.setDossierTemplateName(model.getDossierTemplateName());
		soapModel.setDossierNote(model.getDossierNote());
		soapModel.setSubmissionNote(model.getSubmissionNote());
		soapModel.setApplicantNote(model.getApplicantNote());
		soapModel.setBriefNote(model.getBriefNote());
		soapModel.setDossierNo(model.getDossierNo());
		soapModel.setSubmitting(model.getSubmitting());
		soapModel.setProcessDate(model.getProcessDate());
		soapModel.setSubmitDate(model.getSubmitDate());
		soapModel.setReceiveDate(model.getReceiveDate());
		soapModel.setDueDate(model.getDueDate());
		soapModel.setExtendDate(model.getExtendDate());
		soapModel.setReleaseDate(model.getReleaseDate());
		soapModel.setFinishDate(model.getFinishDate());
		soapModel.setCancellingDate(model.getCancellingDate());
		soapModel.setCorrecttingDate(model.getCorrecttingDate());
		soapModel.setDossierStatus(model.getDossierStatus());
		soapModel.setDossierStatusText(model.getDossierStatusText());
		soapModel.setDossierSubStatus(model.getDossierSubStatus());
		soapModel.setDossierSubStatusText(model.getDossierSubStatusText());
		soapModel.setFolderId(model.getFolderId());
		soapModel.setDossierActionId(model.getDossierActionId());
		soapModel.setViaPostal(model.getViaPostal());
		soapModel.setPostalServiceCode(model.getPostalServiceCode());
		soapModel.setPostalServiceName(model.getPostalServiceName());
		soapModel.setPostalAddress(model.getPostalAddress());
		soapModel.setPostalCityCode(model.getPostalCityCode());
		soapModel.setPostalCityName(model.getPostalCityName());
		soapModel.setPostalDistrictCode(model.getPostalDistrictCode());
		soapModel.setPostalDistrictName(model.getPostalDistrictName());
		soapModel.setPostalWardCode(model.getPostalWardCode());
		soapModel.setPostalWardName(model.getPostalWardName());
		soapModel.setPostalTelNo(model.getPostalTelNo());
		soapModel.setPassword(model.getPassword());
		soapModel.setNotification(model.getNotification());
		soapModel.setOnline(model.getOnline());
		soapModel.setOriginal(model.getOriginal());
		soapModel.setServerNo(model.getServerNo());
		soapModel.setEndorsementDate(model.getEndorsementDate());
		soapModel.setLockState(model.getLockState());
		soapModel.setOriginality(model.getOriginality());
		soapModel.setOriginDossierId(model.getOriginDossierId());
		soapModel.setSampleCount(model.getSampleCount());
		soapModel.setDurationUnit(model.getDurationUnit());
		soapModel.setDurationCount(model.getDurationCount());

		return soapModel;
	}

	public static OpencpsDossierSoap[] toSoapModels(OpencpsDossier[] models) {
		OpencpsDossierSoap[] soapModels = new OpencpsDossierSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static OpencpsDossierSoap[][] toSoapModels(OpencpsDossier[][] models) {
		OpencpsDossierSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new OpencpsDossierSoap[models.length][models[0].length];
		}
		else {
			soapModels = new OpencpsDossierSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static OpencpsDossierSoap[] toSoapModels(List<OpencpsDossier> models) {
		List<OpencpsDossierSoap> soapModels = new ArrayList<OpencpsDossierSoap>(models.size());

		for (OpencpsDossier model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new OpencpsDossierSoap[soapModels.size()]);
	}

	public OpencpsDossierSoap() {
	}

	public long getPrimaryKey() {
		return _dossierId;
	}

	public void setPrimaryKey(long pk) {
		setDossierId(pk);
	}

	public String getUuid() {
		return _uuid;
	}

	public void setUuid(String uuid) {
		_uuid = uuid;
	}

	public long getDossierId() {
		return _dossierId;
	}

	public void setDossierId(long dossierId) {
		_dossierId = dossierId;
	}

	public long getGroupId() {
		return _groupId;
	}

	public void setGroupId(long groupId) {
		_groupId = groupId;
	}

	public long getCompanyId() {
		return _companyId;
	}

	public void setCompanyId(long companyId) {
		_companyId = companyId;
	}

	public long getUserId() {
		return _userId;
	}

	public void setUserId(long userId) {
		_userId = userId;
	}

	public String getUserName() {
		return _userName;
	}

	public void setUserName(String userName) {
		_userName = userName;
	}

	public Date getCreateDate() {
		return _createDate;
	}

	public void setCreateDate(Date createDate) {
		_createDate = createDate;
	}

	public Date getModifiedDate() {
		return _modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		_modifiedDate = modifiedDate;
	}

	public String getReferenceUid() {
		return _referenceUid;
	}

	public void setReferenceUid(String referenceUid) {
		_referenceUid = referenceUid;
	}

	public int getCounter() {
		return _counter;
	}

	public void setCounter(int counter) {
		_counter = counter;
	}

	public String getRegisterBookCode() {
		return _registerBookCode;
	}

	public void setRegisterBookCode(String registerBookCode) {
		_registerBookCode = registerBookCode;
	}

	public String getRegisterBookName() {
		return _registerBookName;
	}

	public void setRegisterBookName(String registerBookName) {
		_registerBookName = registerBookName;
	}

	public String getDossierRegister() {
		return _dossierRegister;
	}

	public void setDossierRegister(String dossierRegister) {
		_dossierRegister = dossierRegister;
	}

	public String getProcessNo() {
		return _processNo;
	}

	public void setProcessNo(String processNo) {
		_processNo = processNo;
	}

	public String getServiceCode() {
		return _serviceCode;
	}

	public void setServiceCode(String serviceCode) {
		_serviceCode = serviceCode;
	}

	public String getServiceName() {
		return _serviceName;
	}

	public void setServiceName(String serviceName) {
		_serviceName = serviceName;
	}

	public String getGovAgencyCode() {
		return _govAgencyCode;
	}

	public void setGovAgencyCode(String govAgencyCode) {
		_govAgencyCode = govAgencyCode;
	}

	public String getGovAgencyName() {
		return _govAgencyName;
	}

	public void setGovAgencyName(String govAgencyName) {
		_govAgencyName = govAgencyName;
	}

	public String getApplicantName() {
		return _applicantName;
	}

	public void setApplicantName(String applicantName) {
		_applicantName = applicantName;
	}

	public String getApplicantIdType() {
		return _applicantIdType;
	}

	public void setApplicantIdType(String applicantIdType) {
		_applicantIdType = applicantIdType;
	}

	public String getApplicantIdNo() {
		return _applicantIdNo;
	}

	public void setApplicantIdNo(String applicantIdNo) {
		_applicantIdNo = applicantIdNo;
	}

	public Date getApplicantIdDate() {
		return _applicantIdDate;
	}

	public void setApplicantIdDate(Date applicantIdDate) {
		_applicantIdDate = applicantIdDate;
	}

	public String getAddress() {
		return _address;
	}

	public void setAddress(String address) {
		_address = address;
	}

	public String getCityCode() {
		return _cityCode;
	}

	public void setCityCode(String cityCode) {
		_cityCode = cityCode;
	}

	public String getCityName() {
		return _cityName;
	}

	public void setCityName(String cityName) {
		_cityName = cityName;
	}

	public String getDistrictCode() {
		return _districtCode;
	}

	public void setDistrictCode(String districtCode) {
		_districtCode = districtCode;
	}

	public String getDistrictName() {
		return _districtName;
	}

	public void setDistrictName(String districtName) {
		_districtName = districtName;
	}

	public String getWardCode() {
		return _wardCode;
	}

	public void setWardCode(String wardCode) {
		_wardCode = wardCode;
	}

	public String getWardName() {
		return _wardName;
	}

	public void setWardName(String wardName) {
		_wardName = wardName;
	}

	public String getContactName() {
		return _contactName;
	}

	public void setContactName(String contactName) {
		_contactName = contactName;
	}

	public String getContactTelNo() {
		return _contactTelNo;
	}

	public void setContactTelNo(String contactTelNo) {
		_contactTelNo = contactTelNo;
	}

	public String getContactEmail() {
		return _contactEmail;
	}

	public void setContactEmail(String contactEmail) {
		_contactEmail = contactEmail;
	}

	public String getDelegateName() {
		return _delegateName;
	}

	public void setDelegateName(String delegateName) {
		_delegateName = delegateName;
	}

	public String getDelegateIdNo() {
		return _delegateIdNo;
	}

	public void setDelegateIdNo(String delegateIdNo) {
		_delegateIdNo = delegateIdNo;
	}

	public String getDelegateTelNo() {
		return _delegateTelNo;
	}

	public void setDelegateTelNo(String delegateTelNo) {
		_delegateTelNo = delegateTelNo;
	}

	public String getDelegateEmail() {
		return _delegateEmail;
	}

	public void setDelegateEmail(String delegateEmail) {
		_delegateEmail = delegateEmail;
	}

	public String getDelegateAddress() {
		return _delegateAddress;
	}

	public void setDelegateAddress(String delegateAddress) {
		_delegateAddress = delegateAddress;
	}

	public String getDelegateCityCode() {
		return _delegateCityCode;
	}

	public void setDelegateCityCode(String delegateCityCode) {
		_delegateCityCode = delegateCityCode;
	}

	public String getDelegateCityName() {
		return _delegateCityName;
	}

	public void setDelegateCityName(String delegateCityName) {
		_delegateCityName = delegateCityName;
	}

	public String getDelegateDistrictCode() {
		return _delegateDistrictCode;
	}

	public void setDelegateDistrictCode(String delegateDistrictCode) {
		_delegateDistrictCode = delegateDistrictCode;
	}

	public String getDelegateDistrictName() {
		return _delegateDistrictName;
	}

	public void setDelegateDistrictName(String delegateDistrictName) {
		_delegateDistrictName = delegateDistrictName;
	}

	public String getDelegateWardCode() {
		return _delegateWardCode;
	}

	public void setDelegateWardCode(String delegateWardCode) {
		_delegateWardCode = delegateWardCode;
	}

	public String getDelegateWardName() {
		return _delegateWardName;
	}

	public void setDelegateWardName(String delegateWardName) {
		_delegateWardName = delegateWardName;
	}

	public String getDossierTemplateNo() {
		return _dossierTemplateNo;
	}

	public void setDossierTemplateNo(String dossierTemplateNo) {
		_dossierTemplateNo = dossierTemplateNo;
	}

	public String getDossierTemplateName() {
		return _dossierTemplateName;
	}

	public void setDossierTemplateName(String dossierTemplateName) {
		_dossierTemplateName = dossierTemplateName;
	}

	public String getDossierNote() {
		return _dossierNote;
	}

	public void setDossierNote(String dossierNote) {
		_dossierNote = dossierNote;
	}

	public String getSubmissionNote() {
		return _submissionNote;
	}

	public void setSubmissionNote(String submissionNote) {
		_submissionNote = submissionNote;
	}

	public String getApplicantNote() {
		return _applicantNote;
	}

	public void setApplicantNote(String applicantNote) {
		_applicantNote = applicantNote;
	}

	public String getBriefNote() {
		return _briefNote;
	}

	public void setBriefNote(String briefNote) {
		_briefNote = briefNote;
	}

	public String getDossierNo() {
		return _dossierNo;
	}

	public void setDossierNo(String dossierNo) {
		_dossierNo = dossierNo;
	}

	public boolean getSubmitting() {
		return _submitting;
	}

	public boolean isSubmitting() {
		return _submitting;
	}

	public void setSubmitting(boolean submitting) {
		_submitting = submitting;
	}

	public Date getProcessDate() {
		return _processDate;
	}

	public void setProcessDate(Date processDate) {
		_processDate = processDate;
	}

	public Date getSubmitDate() {
		return _submitDate;
	}

	public void setSubmitDate(Date submitDate) {
		_submitDate = submitDate;
	}

	public Date getReceiveDate() {
		return _receiveDate;
	}

	public void setReceiveDate(Date receiveDate) {
		_receiveDate = receiveDate;
	}

	public Date getDueDate() {
		return _dueDate;
	}

	public void setDueDate(Date dueDate) {
		_dueDate = dueDate;
	}

	public Date getExtendDate() {
		return _extendDate;
	}

	public void setExtendDate(Date extendDate) {
		_extendDate = extendDate;
	}

	public Date getReleaseDate() {
		return _releaseDate;
	}

	public void setReleaseDate(Date releaseDate) {
		_releaseDate = releaseDate;
	}

	public Date getFinishDate() {
		return _finishDate;
	}

	public void setFinishDate(Date finishDate) {
		_finishDate = finishDate;
	}

	public Date getCancellingDate() {
		return _cancellingDate;
	}

	public void setCancellingDate(Date cancellingDate) {
		_cancellingDate = cancellingDate;
	}

	public Date getCorrecttingDate() {
		return _correcttingDate;
	}

	public void setCorrecttingDate(Date correcttingDate) {
		_correcttingDate = correcttingDate;
	}

	public String getDossierStatus() {
		return _dossierStatus;
	}

	public void setDossierStatus(String dossierStatus) {
		_dossierStatus = dossierStatus;
	}

	public String getDossierStatusText() {
		return _dossierStatusText;
	}

	public void setDossierStatusText(String dossierStatusText) {
		_dossierStatusText = dossierStatusText;
	}

	public String getDossierSubStatus() {
		return _dossierSubStatus;
	}

	public void setDossierSubStatus(String dossierSubStatus) {
		_dossierSubStatus = dossierSubStatus;
	}

	public String getDossierSubStatusText() {
		return _dossierSubStatusText;
	}

	public void setDossierSubStatusText(String dossierSubStatusText) {
		_dossierSubStatusText = dossierSubStatusText;
	}

	public long getFolderId() {
		return _folderId;
	}

	public void setFolderId(long folderId) {
		_folderId = folderId;
	}

	public long getDossierActionId() {
		return _dossierActionId;
	}

	public void setDossierActionId(long dossierActionId) {
		_dossierActionId = dossierActionId;
	}

	public int getViaPostal() {
		return _viaPostal;
	}

	public void setViaPostal(int viaPostal) {
		_viaPostal = viaPostal;
	}

	public String getPostalServiceCode() {
		return _postalServiceCode;
	}

	public void setPostalServiceCode(String postalServiceCode) {
		_postalServiceCode = postalServiceCode;
	}

	public String getPostalServiceName() {
		return _postalServiceName;
	}

	public void setPostalServiceName(String postalServiceName) {
		_postalServiceName = postalServiceName;
	}

	public String getPostalAddress() {
		return _postalAddress;
	}

	public void setPostalAddress(String postalAddress) {
		_postalAddress = postalAddress;
	}

	public String getPostalCityCode() {
		return _postalCityCode;
	}

	public void setPostalCityCode(String postalCityCode) {
		_postalCityCode = postalCityCode;
	}

	public String getPostalCityName() {
		return _postalCityName;
	}

	public void setPostalCityName(String postalCityName) {
		_postalCityName = postalCityName;
	}

	public String getPostalDistrictCode() {
		return _postalDistrictCode;
	}

	public void setPostalDistrictCode(String postalDistrictCode) {
		_postalDistrictCode = postalDistrictCode;
	}

	public String getPostalDistrictName() {
		return _postalDistrictName;
	}

	public void setPostalDistrictName(String postalDistrictName) {
		_postalDistrictName = postalDistrictName;
	}

	public String getPostalWardCode() {
		return _postalWardCode;
	}

	public void setPostalWardCode(String postalWardCode) {
		_postalWardCode = postalWardCode;
	}

	public String getPostalWardName() {
		return _postalWardName;
	}

	public void setPostalWardName(String postalWardName) {
		_postalWardName = postalWardName;
	}

	public String getPostalTelNo() {
		return _postalTelNo;
	}

	public void setPostalTelNo(String postalTelNo) {
		_postalTelNo = postalTelNo;
	}

	public String getPassword() {
		return _password;
	}

	public void setPassword(String password) {
		_password = password;
	}

	public boolean getNotification() {
		return _notification;
	}

	public boolean isNotification() {
		return _notification;
	}

	public void setNotification(boolean notification) {
		_notification = notification;
	}

	public boolean getOnline() {
		return _online;
	}

	public boolean isOnline() {
		return _online;
	}

	public void setOnline(boolean online) {
		_online = online;
	}

	public boolean getOriginal() {
		return _original;
	}

	public boolean isOriginal() {
		return _original;
	}

	public void setOriginal(boolean original) {
		_original = original;
	}

	public String getServerNo() {
		return _serverNo;
	}

	public void setServerNo(String serverNo) {
		_serverNo = serverNo;
	}

	public Date getEndorsementDate() {
		return _endorsementDate;
	}

	public void setEndorsementDate(Date endorsementDate) {
		_endorsementDate = endorsementDate;
	}

	public String getLockState() {
		return _lockState;
	}

	public void setLockState(String lockState) {
		_lockState = lockState;
	}

	public int getOriginality() {
		return _originality;
	}

	public void setOriginality(int originality) {
		_originality = originality;
	}

	public long getOriginDossierId() {
		return _originDossierId;
	}

	public void setOriginDossierId(long originDossierId) {
		_originDossierId = originDossierId;
	}

	public long getSampleCount() {
		return _sampleCount;
	}

	public void setSampleCount(long sampleCount) {
		_sampleCount = sampleCount;
	}

	public double getDurationUnit() {
		return _durationUnit;
	}

	public void setDurationUnit(double durationUnit) {
		_durationUnit = durationUnit;
	}

	public double getDurationCount() {
		return _durationCount;
	}

	public void setDurationCount(double durationCount) {
		_durationCount = durationCount;
	}

	private String _uuid;
	private long _dossierId;
	private long _groupId;
	private long _companyId;
	private long _userId;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private String _referenceUid;
	private int _counter;
	private String _registerBookCode;
	private String _registerBookName;
	private String _dossierRegister;
	private String _processNo;
	private String _serviceCode;
	private String _serviceName;
	private String _govAgencyCode;
	private String _govAgencyName;
	private String _applicantName;
	private String _applicantIdType;
	private String _applicantIdNo;
	private Date _applicantIdDate;
	private String _address;
	private String _cityCode;
	private String _cityName;
	private String _districtCode;
	private String _districtName;
	private String _wardCode;
	private String _wardName;
	private String _contactName;
	private String _contactTelNo;
	private String _contactEmail;
	private String _delegateName;
	private String _delegateIdNo;
	private String _delegateTelNo;
	private String _delegateEmail;
	private String _delegateAddress;
	private String _delegateCityCode;
	private String _delegateCityName;
	private String _delegateDistrictCode;
	private String _delegateDistrictName;
	private String _delegateWardCode;
	private String _delegateWardName;
	private String _dossierTemplateNo;
	private String _dossierTemplateName;
	private String _dossierNote;
	private String _submissionNote;
	private String _applicantNote;
	private String _briefNote;
	private String _dossierNo;
	private boolean _submitting;
	private Date _processDate;
	private Date _submitDate;
	private Date _receiveDate;
	private Date _dueDate;
	private Date _extendDate;
	private Date _releaseDate;
	private Date _finishDate;
	private Date _cancellingDate;
	private Date _correcttingDate;
	private String _dossierStatus;
	private String _dossierStatusText;
	private String _dossierSubStatus;
	private String _dossierSubStatusText;
	private long _folderId;
	private long _dossierActionId;
	private int _viaPostal;
	private String _postalServiceCode;
	private String _postalServiceName;
	private String _postalAddress;
	private String _postalCityCode;
	private String _postalCityName;
	private String _postalDistrictCode;
	private String _postalDistrictName;
	private String _postalWardCode;
	private String _postalWardName;
	private String _postalTelNo;
	private String _password;
	private boolean _notification;
	private boolean _online;
	private boolean _original;
	private String _serverNo;
	private Date _endorsementDate;
	private String _lockState;
	private int _originality;
	private long _originDossierId;
	private long _sampleCount;
	private double _durationUnit;
	private double _durationCount;
}