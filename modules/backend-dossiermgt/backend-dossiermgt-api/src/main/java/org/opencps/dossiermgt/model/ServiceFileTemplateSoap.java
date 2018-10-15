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

import org.opencps.dossiermgt.service.persistence.ServiceFileTemplatePK;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is used by SOAP remote services.
 *
 * @author huymq
 * @generated
 */
@ProviderType
public class ServiceFileTemplateSoap implements Serializable {
	public static ServiceFileTemplateSoap toSoapModel(ServiceFileTemplate model) {
		ServiceFileTemplateSoap soapModel = new ServiceFileTemplateSoap();

		soapModel.setUuid(model.getUuid());
		soapModel.setServiceInfoId(model.getServiceInfoId());
		soapModel.setFileTemplateNo(model.getFileTemplateNo());
		soapModel.setTemplateName(model.getTemplateName());
		soapModel.setFileEntryId(model.getFileEntryId());

		return soapModel;
	}

	public static ServiceFileTemplateSoap[] toSoapModels(
		ServiceFileTemplate[] models) {
		ServiceFileTemplateSoap[] soapModels = new ServiceFileTemplateSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static ServiceFileTemplateSoap[][] toSoapModels(
		ServiceFileTemplate[][] models) {
		ServiceFileTemplateSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new ServiceFileTemplateSoap[models.length][models[0].length];
		}
		else {
			soapModels = new ServiceFileTemplateSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static ServiceFileTemplateSoap[] toSoapModels(
		List<ServiceFileTemplate> models) {
		List<ServiceFileTemplateSoap> soapModels = new ArrayList<ServiceFileTemplateSoap>(models.size());

		for (ServiceFileTemplate model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new ServiceFileTemplateSoap[soapModels.size()]);
	}

	public ServiceFileTemplateSoap() {
	}

	public ServiceFileTemplatePK getPrimaryKey() {
		return new ServiceFileTemplatePK(_serviceInfoId, _fileTemplateNo);
	}

	public void setPrimaryKey(ServiceFileTemplatePK pk) {
		setServiceInfoId(pk.serviceInfoId);
		setFileTemplateNo(pk.fileTemplateNo);
	}

	public String getUuid() {
		return _uuid;
	}

	public void setUuid(String uuid) {
		_uuid = uuid;
	}

	public long getServiceInfoId() {
		return _serviceInfoId;
	}

	public void setServiceInfoId(long serviceInfoId) {
		_serviceInfoId = serviceInfoId;
	}

	public String getFileTemplateNo() {
		return _fileTemplateNo;
	}

	public void setFileTemplateNo(String fileTemplateNo) {
		_fileTemplateNo = fileTemplateNo;
	}

	public String getTemplateName() {
		return _templateName;
	}

	public void setTemplateName(String templateName) {
		_templateName = templateName;
	}

	public long getFileEntryId() {
		return _fileEntryId;
	}

	public void setFileEntryId(long fileEntryId) {
		_fileEntryId = fileEntryId;
	}

	private String _uuid;
	private long _serviceInfoId;
	private String _fileTemplateNo;
	private String _templateName;
	private long _fileEntryId;
}