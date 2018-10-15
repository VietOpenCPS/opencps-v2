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
public class DocumentTypeSoap implements Serializable {
	public static DocumentTypeSoap toSoapModel(DocumentType model) {
		DocumentTypeSoap soapModel = new DocumentTypeSoap();

		soapModel.setUuid(model.getUuid());
		soapModel.setDocumentTypeId(model.getDocumentTypeId());
		soapModel.setGroupId(model.getGroupId());
		soapModel.setUserId(model.getUserId());
		soapModel.setCreateDate(model.getCreateDate());
		soapModel.setModifiedDate(model.getModifiedDate());
		soapModel.setTypeCode(model.getTypeCode());
		soapModel.setTemplateClass(model.getTemplateClass());
		soapModel.setDocumentName(model.getDocumentName());
		soapModel.setCodePattern(model.getCodePattern());
		soapModel.setDocumentScript(model.getDocumentScript());
		soapModel.setDocSync(model.getDocSync());

		return soapModel;
	}

	public static DocumentTypeSoap[] toSoapModels(DocumentType[] models) {
		DocumentTypeSoap[] soapModels = new DocumentTypeSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static DocumentTypeSoap[][] toSoapModels(DocumentType[][] models) {
		DocumentTypeSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new DocumentTypeSoap[models.length][models[0].length];
		}
		else {
			soapModels = new DocumentTypeSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static DocumentTypeSoap[] toSoapModels(List<DocumentType> models) {
		List<DocumentTypeSoap> soapModels = new ArrayList<DocumentTypeSoap>(models.size());

		for (DocumentType model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new DocumentTypeSoap[soapModels.size()]);
	}

	public DocumentTypeSoap() {
	}

	public long getPrimaryKey() {
		return _DocumentTypeId;
	}

	public void setPrimaryKey(long pk) {
		setDocumentTypeId(pk);
	}

	public String getUuid() {
		return _uuid;
	}

	public void setUuid(String uuid) {
		_uuid = uuid;
	}

	public long getDocumentTypeId() {
		return _DocumentTypeId;
	}

	public void setDocumentTypeId(long DocumentTypeId) {
		_DocumentTypeId = DocumentTypeId;
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

	public String getTypeCode() {
		return _typeCode;
	}

	public void setTypeCode(String typeCode) {
		_typeCode = typeCode;
	}

	public int getTemplateClass() {
		return _templateClass;
	}

	public void setTemplateClass(int templateClass) {
		_templateClass = templateClass;
	}

	public String getDocumentName() {
		return _documentName;
	}

	public void setDocumentName(String documentName) {
		_documentName = documentName;
	}

	public String getCodePattern() {
		return _codePattern;
	}

	public void setCodePattern(String codePattern) {
		_codePattern = codePattern;
	}

	public String getDocumentScript() {
		return _documentScript;
	}

	public void setDocumentScript(String documentScript) {
		_documentScript = documentScript;
	}

	public int getDocSync() {
		return _docSync;
	}

	public void setDocSync(int docSync) {
		_docSync = docSync;
	}

	private String _uuid;
	private long _DocumentTypeId;
	private long _groupId;
	private long _userId;
	private Date _createDate;
	private Date _modifiedDate;
	private String _typeCode;
	private int _templateClass;
	private String _documentName;
	private String _codePattern;
	private String _documentScript;
	private int _docSync;
}