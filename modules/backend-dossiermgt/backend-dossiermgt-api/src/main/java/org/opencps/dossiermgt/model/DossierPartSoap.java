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
public class DossierPartSoap implements Serializable {
	public static DossierPartSoap toSoapModel(DossierPart model) {
		DossierPartSoap soapModel = new DossierPartSoap();

		soapModel.setUuid(model.getUuid());
		soapModel.setDossierPartId(model.getDossierPartId());
		soapModel.setGroupId(model.getGroupId());
		soapModel.setCompanyId(model.getCompanyId());
		soapModel.setUserId(model.getUserId());
		soapModel.setUserName(model.getUserName());
		soapModel.setCreateDate(model.getCreateDate());
		soapModel.setModifiedDate(model.getModifiedDate());
		soapModel.setTemplateNo(model.getTemplateNo());
		soapModel.setPartNo(model.getPartNo());
		soapModel.setPartName(model.getPartName());
		soapModel.setPartTip(model.getPartTip());
		soapModel.setPartType(model.getPartType());
		soapModel.setMultiple(model.isMultiple());
		soapModel.setFormScript(model.getFormScript());
		soapModel.setFormReport(model.getFormReport());
		soapModel.setSampleData(model.getSampleData());
		soapModel.setRequired(model.isRequired());
		soapModel.setFileTemplateNo(model.getFileTemplateNo());
		soapModel.setESign(model.isESign());
		soapModel.setDeliverableType(model.getDeliverableType());
		soapModel.setDeliverableAction(model.getDeliverableAction());
		soapModel.setEForm(model.isEForm());
		soapModel.setFileMark(model.getFileMark());

		return soapModel;
	}

	public static DossierPartSoap[] toSoapModels(DossierPart[] models) {
		DossierPartSoap[] soapModels = new DossierPartSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static DossierPartSoap[][] toSoapModels(DossierPart[][] models) {
		DossierPartSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new DossierPartSoap[models.length][models[0].length];
		}
		else {
			soapModels = new DossierPartSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static DossierPartSoap[] toSoapModels(List<DossierPart> models) {
		List<DossierPartSoap> soapModels = new ArrayList<DossierPartSoap>(models.size());

		for (DossierPart model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new DossierPartSoap[soapModels.size()]);
	}

	public DossierPartSoap() {
	}

	public long getPrimaryKey() {
		return _dossierPartId;
	}

	public void setPrimaryKey(long pk) {
		setDossierPartId(pk);
	}

	public String getUuid() {
		return _uuid;
	}

	public void setUuid(String uuid) {
		_uuid = uuid;
	}

	public long getDossierPartId() {
		return _dossierPartId;
	}

	public void setDossierPartId(long dossierPartId) {
		_dossierPartId = dossierPartId;
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

	public String getTemplateNo() {
		return _templateNo;
	}

	public void setTemplateNo(String templateNo) {
		_templateNo = templateNo;
	}

	public String getPartNo() {
		return _partNo;
	}

	public void setPartNo(String partNo) {
		_partNo = partNo;
	}

	public String getPartName() {
		return _partName;
	}

	public void setPartName(String partName) {
		_partName = partName;
	}

	public String getPartTip() {
		return _partTip;
	}

	public void setPartTip(String partTip) {
		_partTip = partTip;
	}

	public int getPartType() {
		return _partType;
	}

	public void setPartType(int partType) {
		_partType = partType;
	}

	public boolean getMultiple() {
		return _multiple;
	}

	public boolean isMultiple() {
		return _multiple;
	}

	public void setMultiple(boolean multiple) {
		_multiple = multiple;
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

	public String getSampleData() {
		return _sampleData;
	}

	public void setSampleData(String sampleData) {
		_sampleData = sampleData;
	}

	public boolean getRequired() {
		return _required;
	}

	public boolean isRequired() {
		return _required;
	}

	public void setRequired(boolean required) {
		_required = required;
	}

	public String getFileTemplateNo() {
		return _fileTemplateNo;
	}

	public void setFileTemplateNo(String fileTemplateNo) {
		_fileTemplateNo = fileTemplateNo;
	}

	public boolean getESign() {
		return _eSign;
	}

	public boolean isESign() {
		return _eSign;
	}

	public void setESign(boolean eSign) {
		_eSign = eSign;
	}

	public String getDeliverableType() {
		return _deliverableType;
	}

	public void setDeliverableType(String deliverableType) {
		_deliverableType = deliverableType;
	}

	public int getDeliverableAction() {
		return _deliverableAction;
	}

	public void setDeliverableAction(int deliverableAction) {
		_deliverableAction = deliverableAction;
	}

	public boolean getEForm() {
		return _eForm;
	}

	public boolean isEForm() {
		return _eForm;
	}

	public void setEForm(boolean eForm) {
		_eForm = eForm;
	}

	public int getFileMark() {
		return _fileMark;
	}

	public void setFileMark(int fileMark) {
		_fileMark = fileMark;
	}

	private String _uuid;
	private long _dossierPartId;
	private long _groupId;
	private long _companyId;
	private long _userId;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private String _templateNo;
	private String _partNo;
	private String _partName;
	private String _partTip;
	private int _partType;
	private boolean _multiple;
	private String _formScript;
	private String _formReport;
	private String _sampleData;
	private boolean _required;
	private String _fileTemplateNo;
	private boolean _eSign;
	private String _deliverableType;
	private int _deliverableAction;
	private boolean _eForm;
	private int _fileMark;
}