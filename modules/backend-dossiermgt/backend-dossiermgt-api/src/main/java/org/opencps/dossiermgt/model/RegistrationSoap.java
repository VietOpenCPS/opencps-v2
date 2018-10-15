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
public class RegistrationSoap implements Serializable {
	public static RegistrationSoap toSoapModel(Registration model) {
		RegistrationSoap soapModel = new RegistrationSoap();

		soapModel.setUuid(model.getUuid());
		soapModel.setRegistrationId(model.getRegistrationId());
		soapModel.setCompanyId(model.getCompanyId());
		soapModel.setGroupId(model.getGroupId());
		soapModel.setUserId(model.getUserId());
		soapModel.setCreateDate(model.getCreateDate());
		soapModel.setModifiedDate(model.getModifiedDate());
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
		soapModel.setGovAgencyCode(model.getGovAgencyCode());
		soapModel.setGovAgencyName(model.getGovAgencyName());
		soapModel.setRegistrationState(model.getRegistrationState());
		soapModel.setRegistrationClass(model.getRegistrationClass());
		soapModel.setSubmitting(model.isSubmitting());
		soapModel.setRepresentativeEnterprise(model.getRepresentativeEnterprise());

		return soapModel;
	}

	public static RegistrationSoap[] toSoapModels(Registration[] models) {
		RegistrationSoap[] soapModels = new RegistrationSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static RegistrationSoap[][] toSoapModels(Registration[][] models) {
		RegistrationSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new RegistrationSoap[models.length][models[0].length];
		}
		else {
			soapModels = new RegistrationSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static RegistrationSoap[] toSoapModels(List<Registration> models) {
		List<RegistrationSoap> soapModels = new ArrayList<RegistrationSoap>(models.size());

		for (Registration model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new RegistrationSoap[soapModels.size()]);
	}

	public RegistrationSoap() {
	}

	public long getPrimaryKey() {
		return _registrationId;
	}

	public void setPrimaryKey(long pk) {
		setRegistrationId(pk);
	}

	public String getUuid() {
		return _uuid;
	}

	public void setUuid(String uuid) {
		_uuid = uuid;
	}

	public long getRegistrationId() {
		return _registrationId;
	}

	public void setRegistrationId(long registrationId) {
		_registrationId = registrationId;
	}

	public long getCompanyId() {
		return _companyId;
	}

	public void setCompanyId(long companyId) {
		_companyId = companyId;
	}

	public long getGroupId() {
		return _groupId;
	}

	public void setGroupId(long groupId) {
		_groupId = groupId;
	}

	public long getUserId() {
		return _userId;
	}

	public void setUserId(long userId) {
		_userId = userId;
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

	public int getRegistrationState() {
		return _registrationState;
	}

	public void setRegistrationState(int registrationState) {
		_registrationState = registrationState;
	}

	public String getRegistrationClass() {
		return _registrationClass;
	}

	public void setRegistrationClass(String registrationClass) {
		_registrationClass = registrationClass;
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

	public String getRepresentativeEnterprise() {
		return _representativeEnterprise;
	}

	public void setRepresentativeEnterprise(String representativeEnterprise) {
		_representativeEnterprise = representativeEnterprise;
	}

	private String _uuid;
	private long _registrationId;
	private long _companyId;
	private long _groupId;
	private long _userId;
	private Date _createDate;
	private Date _modifiedDate;
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
	private String _govAgencyCode;
	private String _govAgencyName;
	private int _registrationState;
	private String _registrationClass;
	private boolean _submitting;
	private String _representativeEnterprise;
}