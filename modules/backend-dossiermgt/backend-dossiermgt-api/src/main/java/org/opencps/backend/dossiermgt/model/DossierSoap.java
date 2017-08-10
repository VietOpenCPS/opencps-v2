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

package org.opencps.backend.dossiermgt.model;

import aQute.bnd.annotation.ProviderType;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * This class is used by SOAP remote services.
 *
 * @author huymq
 * @generated
 */
@ProviderType
public class DossierSoap implements Serializable {
	public static DossierSoap toSoapModel(Dossier model) {
		DossierSoap soapModel = new DossierSoap();

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
		soapModel.setServiceCode(model.getServiceCode());
		soapModel.setServiceName(model.getServiceName());
		soapModel.setOptionCode(model.getOptionCode());
		soapModel.setOptionName(model.getOptionName());
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
		soapModel.setDossierTemplateNo(model.getDossierTemplateNo());
		soapModel.setDossierNote(model.getDossierNote());
		soapModel.setDossierNo(model.getDossierNo());
		soapModel.setSubmitting(model.getSubmitting());
		soapModel.setSubmitDate(model.getSubmitDate());
		soapModel.setReceiveDate(model.getReceiveDate());
		soapModel.setDueDate(model.getDueDate());
		soapModel.setReleaseDate(model.getReleaseDate());
		soapModel.setFinishDate(model.getFinishDate());
		soapModel.setCancellingDate(model.getCancellingDate());
		soapModel.setCorrecttingDate(model.getCorrecttingDate());
		soapModel.setDossierStatus(model.getDossierStatus());
		soapModel.setDossierStatusText(model.getDossierStatusText());
		soapModel.setDossierSubStatus(model.getDossierSubStatus());
		soapModel.setDossierSubStatusText(model.getDossierSubStatusText());
		soapModel.setPaymentRedirectURL(model.getPaymentRedirectURL());
		soapModel.setOverDue(model.getOverDue());
		soapModel.setFolderId(model.getFolderId());

		return soapModel;
	}

	public static DossierSoap[] toSoapModels(Dossier[] models) {
		DossierSoap[] soapModels = new DossierSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static DossierSoap[][] toSoapModels(Dossier[][] models) {
		DossierSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new DossierSoap[models.length][models[0].length];
		}
		else {
			soapModels = new DossierSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static DossierSoap[] toSoapModels(List<Dossier> models) {
		List<DossierSoap> soapModels = new ArrayList<DossierSoap>(models.size());

		for (Dossier model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new DossierSoap[soapModels.size()]);
	}

	public DossierSoap() {
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

	public String getOptionCode() {
		return _optionCode;
	}

	public void setOptionCode(String optionCode) {
		_optionCode = optionCode;
	}

	public String getOptionName() {
		return _optionName;
	}

	public void setOptionName(String optionName) {
		_optionName = optionName;
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

	public String getDossierTemplateNo() {
		return _dossierTemplateNo;
	}

	public void setDossierTemplateNo(String dossierTemplateNo) {
		_dossierTemplateNo = dossierTemplateNo;
	}

	public String getDossierNote() {
		return _dossierNote;
	}

	public void setDossierNote(String dossierNote) {
		_dossierNote = dossierNote;
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

	public String getPaymentRedirectURL() {
		return _paymentRedirectURL;
	}

	public void setPaymentRedirectURL(String paymentRedirectURL) {
		_paymentRedirectURL = paymentRedirectURL;
	}

	public String getOverDue() {
		return _overDue;
	}

	public void setOverDue(String overDue) {
		_overDue = overDue;
	}

	public long getFolderId() {
		return _folderId;
	}

	public void setFolderId(long folderId) {
		_folderId = folderId;
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
	private String _serviceCode;
	private String _serviceName;
	private String _optionCode;
	private String _optionName;
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
	private String _dossierTemplateNo;
	private String _dossierNote;
	private String _dossierNo;
	private boolean _submitting;
	private Date _submitDate;
	private Date _receiveDate;
	private Date _dueDate;
	private Date _releaseDate;
	private Date _finishDate;
	private Date _cancellingDate;
	private Date _correcttingDate;
	private String _dossierStatus;
	private String _dossierStatusText;
	private String _dossierSubStatus;
	private String _dossierSubStatusText;
	private String _paymentRedirectURL;
	private String _overDue;
	private long _folderId;
}