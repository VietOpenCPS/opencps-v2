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

package org.opencps.usermgt.model;

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
public class WorkingUnitSoap implements Serializable {
	public static WorkingUnitSoap toSoapModel(WorkingUnit model) {
		WorkingUnitSoap soapModel = new WorkingUnitSoap();

		soapModel.setUuid(model.getUuid());
		soapModel.setWorkingUnitId(model.getWorkingUnitId());
		soapModel.setCompanyId(model.getCompanyId());
		soapModel.setGroupId(model.getGroupId());
		soapModel.setUserId(model.getUserId());
		soapModel.setUserName(model.getUserName());
		soapModel.setCreateDate(model.getCreateDate());
		soapModel.setModifiedDate(model.getModifiedDate());
		soapModel.setName(model.getName());
		soapModel.setEnName(model.getEnName());
		soapModel.setGovAgencyCode(model.getGovAgencyCode());
		soapModel.setParentWorkingUnitId(model.getParentWorkingUnitId());
		soapModel.setSibling(model.getSibling());
		soapModel.setTreeIndex(model.getTreeIndex());
		soapModel.setAddress(model.getAddress());
		soapModel.setTelNo(model.getTelNo());
		soapModel.setFaxNo(model.getFaxNo());
		soapModel.setEmail(model.getEmail());
		soapModel.setWebsite(model.getWebsite());
		soapModel.setLogoFileEntryId(model.getLogoFileEntryId());
		soapModel.setLevel(model.getLevel());
		soapModel.setCeremonyDate(model.getCeremonyDate());

		return soapModel;
	}

	public static WorkingUnitSoap[] toSoapModels(WorkingUnit[] models) {
		WorkingUnitSoap[] soapModels = new WorkingUnitSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static WorkingUnitSoap[][] toSoapModels(WorkingUnit[][] models) {
		WorkingUnitSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new WorkingUnitSoap[models.length][models[0].length];
		}
		else {
			soapModels = new WorkingUnitSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static WorkingUnitSoap[] toSoapModels(List<WorkingUnit> models) {
		List<WorkingUnitSoap> soapModels = new ArrayList<WorkingUnitSoap>(models.size());

		for (WorkingUnit model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new WorkingUnitSoap[soapModels.size()]);
	}

	public WorkingUnitSoap() {
	}

	public long getPrimaryKey() {
		return _workingUnitId;
	}

	public void setPrimaryKey(long pk) {
		setWorkingUnitId(pk);
	}

	public String getUuid() {
		return _uuid;
	}

	public void setUuid(String uuid) {
		_uuid = uuid;
	}

	public long getWorkingUnitId() {
		return _workingUnitId;
	}

	public void setWorkingUnitId(long workingUnitId) {
		_workingUnitId = workingUnitId;
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

	public String getName() {
		return _name;
	}

	public void setName(String name) {
		_name = name;
	}

	public String getEnName() {
		return _enName;
	}

	public void setEnName(String enName) {
		_enName = enName;
	}

	public String getGovAgencyCode() {
		return _govAgencyCode;
	}

	public void setGovAgencyCode(String govAgencyCode) {
		_govAgencyCode = govAgencyCode;
	}

	public long getParentWorkingUnitId() {
		return _parentWorkingUnitId;
	}

	public void setParentWorkingUnitId(long parentWorkingUnitId) {
		_parentWorkingUnitId = parentWorkingUnitId;
	}

	public String getSibling() {
		return _sibling;
	}

	public void setSibling(String sibling) {
		_sibling = sibling;
	}

	public String getTreeIndex() {
		return _treeIndex;
	}

	public void setTreeIndex(String treeIndex) {
		_treeIndex = treeIndex;
	}

	public String getAddress() {
		return _address;
	}

	public void setAddress(String address) {
		_address = address;
	}

	public String getTelNo() {
		return _telNo;
	}

	public void setTelNo(String telNo) {
		_telNo = telNo;
	}

	public String getFaxNo() {
		return _faxNo;
	}

	public void setFaxNo(String faxNo) {
		_faxNo = faxNo;
	}

	public String getEmail() {
		return _email;
	}

	public void setEmail(String email) {
		_email = email;
	}

	public String getWebsite() {
		return _website;
	}

	public void setWebsite(String website) {
		_website = website;
	}

	public long getLogoFileEntryId() {
		return _logoFileEntryId;
	}

	public void setLogoFileEntryId(long logoFileEntryId) {
		_logoFileEntryId = logoFileEntryId;
	}

	public int getLevel() {
		return _level;
	}

	public void setLevel(int level) {
		_level = level;
	}

	public Date getCeremonyDate() {
		return _ceremonyDate;
	}

	public void setCeremonyDate(Date ceremonyDate) {
		_ceremonyDate = ceremonyDate;
	}

	private String _uuid;
	private long _workingUnitId;
	private long _companyId;
	private long _groupId;
	private long _userId;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private String _name;
	private String _enName;
	private String _govAgencyCode;
	private long _parentWorkingUnitId;
	private String _sibling;
	private String _treeIndex;
	private String _address;
	private String _telNo;
	private String _faxNo;
	private String _email;
	private String _website;
	private long _logoFileEntryId;
	private int _level;
	private Date _ceremonyDate;
}