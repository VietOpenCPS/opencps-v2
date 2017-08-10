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
public class DossierFileSoap implements Serializable {
	public static DossierFileSoap toSoapModel(DossierFile model) {
		DossierFileSoap soapModel = new DossierFileSoap();

		soapModel.setUuid(model.getUuid());
		soapModel.setDossierFileId(model.getDossierFileId());
		soapModel.setGroupId(model.getGroupId());
		soapModel.setCompanyId(model.getCompanyId());
		soapModel.setUserId(model.getUserId());
		soapModel.setUserName(model.getUserName());
		soapModel.setCreateDate(model.getCreateDate());
		soapModel.setModifiedDate(model.getModifiedDate());
		soapModel.setDossierId(model.getDossierId());
		soapModel.setReferenceUid(model.getReferenceUid());
		soapModel.setDossierTemplateNo(model.getDossierTemplateNo());
		soapModel.setDossierPartNo(model.getDossierPartNo());
		soapModel.setFileTemplateNo(model.getFileTemplateNo());
		soapModel.setDisplayName(model.getDisplayName());
		soapModel.setFormData(model.getFormData());
		soapModel.setFileEntryId(model.getFileEntryId());
		soapModel.setDossierFileNo(model.getDossierFileNo());
		soapModel.setDossierFileDate(model.getDossierFileDate());
		soapModel.setOriginal(model.getOriginal());
		soapModel.setIsNew(model.getIsNew());
		soapModel.setSigned(model.getSigned());
		soapModel.setSignCheck(model.getSignCheck());
		soapModel.setSignInfo(model.getSignInfo());
		soapModel.setFormScript(model.getFormScript());
		soapModel.setFormReport(model.getFormReport());

		return soapModel;
	}

	public static DossierFileSoap[] toSoapModels(DossierFile[] models) {
		DossierFileSoap[] soapModels = new DossierFileSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static DossierFileSoap[][] toSoapModels(DossierFile[][] models) {
		DossierFileSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new DossierFileSoap[models.length][models[0].length];
		}
		else {
			soapModels = new DossierFileSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static DossierFileSoap[] toSoapModels(List<DossierFile> models) {
		List<DossierFileSoap> soapModels = new ArrayList<DossierFileSoap>(models.size());

		for (DossierFile model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new DossierFileSoap[soapModels.size()]);
	}

	public DossierFileSoap() {
	}

	public long getPrimaryKey() {
		return _dossierFileId;
	}

	public void setPrimaryKey(long pk) {
		setDossierFileId(pk);
	}

	public String getUuid() {
		return _uuid;
	}

	public void setUuid(String uuid) {
		_uuid = uuid;
	}

	public long getDossierFileId() {
		return _dossierFileId;
	}

	public void setDossierFileId(long dossierFileId) {
		_dossierFileId = dossierFileId;
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

	public long getDossierId() {
		return _dossierId;
	}

	public void setDossierId(long dossierId) {
		_dossierId = dossierId;
	}

	public String getReferenceUid() {
		return _referenceUid;
	}

	public void setReferenceUid(String referenceUid) {
		_referenceUid = referenceUid;
	}

	public String getDossierTemplateNo() {
		return _dossierTemplateNo;
	}

	public void setDossierTemplateNo(String dossierTemplateNo) {
		_dossierTemplateNo = dossierTemplateNo;
	}

	public String getDossierPartNo() {
		return _dossierPartNo;
	}

	public void setDossierPartNo(String dossierPartNo) {
		_dossierPartNo = dossierPartNo;
	}

	public String getFileTemplateNo() {
		return _fileTemplateNo;
	}

	public void setFileTemplateNo(String fileTemplateNo) {
		_fileTemplateNo = fileTemplateNo;
	}

	public String getDisplayName() {
		return _displayName;
	}

	public void setDisplayName(String displayName) {
		_displayName = displayName;
	}

	public String getFormData() {
		return _formData;
	}

	public void setFormData(String formData) {
		_formData = formData;
	}

	public long getFileEntryId() {
		return _fileEntryId;
	}

	public void setFileEntryId(long fileEntryId) {
		_fileEntryId = fileEntryId;
	}

	public Date getDossierFileNo() {
		return _dossierFileNo;
	}

	public void setDossierFileNo(Date dossierFileNo) {
		_dossierFileNo = dossierFileNo;
	}

	public String getDossierFileDate() {
		return _dossierFileDate;
	}

	public void setDossierFileDate(String dossierFileDate) {
		_dossierFileDate = dossierFileDate;
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

	public boolean getIsNew() {
		return _isNew;
	}

	public boolean isIsNew() {
		return _isNew;
	}

	public void setIsNew(boolean isNew) {
		_isNew = isNew;
	}

	public boolean getSigned() {
		return _signed;
	}

	public boolean isSigned() {
		return _signed;
	}

	public void setSigned(boolean signed) {
		_signed = signed;
	}

	public int getSignCheck() {
		return _signCheck;
	}

	public void setSignCheck(int signCheck) {
		_signCheck = signCheck;
	}

	public String getSignInfo() {
		return _signInfo;
	}

	public void setSignInfo(String signInfo) {
		_signInfo = signInfo;
	}

	public String getFormScript() {
		return _formScript;
	}

	public void setFormScript(String formScript) {
		_formScript = formScript;
	}

	public String getFormReport() {
		return _formReport;
	}

	public void setFormReport(String formReport) {
		_formReport = formReport;
	}

	private String _uuid;
	private long _dossierFileId;
	private long _groupId;
	private long _companyId;
	private long _userId;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private long _dossierId;
	private String _referenceUid;
	private String _dossierTemplateNo;
	private String _dossierPartNo;
	private String _fileTemplateNo;
	private String _displayName;
	private String _formData;
	private long _fileEntryId;
	private Date _dossierFileNo;
	private String _dossierFileDate;
	private boolean _original;
	private boolean _isNew;
	private boolean _signed;
	private int _signCheck;
	private String _signInfo;
	private String _formScript;
	private String _formReport;
}