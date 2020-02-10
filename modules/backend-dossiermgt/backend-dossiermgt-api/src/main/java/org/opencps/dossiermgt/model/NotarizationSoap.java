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
public class NotarizationSoap implements Serializable {
	public static NotarizationSoap toSoapModel(Notarization model) {
		NotarizationSoap soapModel = new NotarizationSoap();

		soapModel.setNotarizationId(model.getNotarizationId());
		soapModel.setGroupId(model.getGroupId());
		soapModel.setCompanyId(model.getCompanyId());
		soapModel.setUserId(model.getUserId());
		soapModel.setUserName(model.getUserName());
		soapModel.setCreateDate(model.getCreateDate());
		soapModel.setModifiedDate(model.getModifiedDate());
		soapModel.setDossierId(model.getDossierId());
		soapModel.setFileName(model.getFileName());
		soapModel.setTotalRecord(model.getTotalRecord());
		soapModel.setTotalPage(model.getTotalPage());
		soapModel.setTotalCopy(model.getTotalCopy());
		soapModel.setTotalFee(model.getTotalFee());
		soapModel.setNotarizationNo(model.getNotarizationNo());
		soapModel.setNotarizationYear(model.getNotarizationYear());
		soapModel.setNotarizationDate(model.getNotarizationDate());
		soapModel.setSignerName(model.getSignerName());
		soapModel.setSignerPosition(model.getSignerPosition());
		soapModel.setStatusCode(model.getStatusCode());

		return soapModel;
	}

	public static NotarizationSoap[] toSoapModels(Notarization[] models) {
		NotarizationSoap[] soapModels = new NotarizationSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static NotarizationSoap[][] toSoapModels(Notarization[][] models) {
		NotarizationSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new NotarizationSoap[models.length][models[0].length];
		}
		else {
			soapModels = new NotarizationSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static NotarizationSoap[] toSoapModels(List<Notarization> models) {
		List<NotarizationSoap> soapModels = new ArrayList<NotarizationSoap>(models.size());

		for (Notarization model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new NotarizationSoap[soapModels.size()]);
	}

	public NotarizationSoap() {
	}

	public long getPrimaryKey() {
		return _notarizationId;
	}

	public void setPrimaryKey(long pk) {
		setNotarizationId(pk);
	}

	public long getNotarizationId() {
		return _notarizationId;
	}

	public void setNotarizationId(long notarizationId) {
		_notarizationId = notarizationId;
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

	public String getFileName() {
		return _fileName;
	}

	public void setFileName(String fileName) {
		_fileName = fileName;
	}

	public int getTotalRecord() {
		return _totalRecord;
	}

	public void setTotalRecord(int totalRecord) {
		_totalRecord = totalRecord;
	}

	public int getTotalPage() {
		return _totalPage;
	}

	public void setTotalPage(int totalPage) {
		_totalPage = totalPage;
	}

	public int getTotalCopy() {
		return _totalCopy;
	}

	public void setTotalCopy(int totalCopy) {
		_totalCopy = totalCopy;
	}

	public long getTotalFee() {
		return _totalFee;
	}

	public void setTotalFee(long totalFee) {
		_totalFee = totalFee;
	}

	public long getNotarizationNo() {
		return _notarizationNo;
	}

	public void setNotarizationNo(long notarizationNo) {
		_notarizationNo = notarizationNo;
	}

	public int getNotarizationYear() {
		return _notarizationYear;
	}

	public void setNotarizationYear(int notarizationYear) {
		_notarizationYear = notarizationYear;
	}

	public Date getNotarizationDate() {
		return _notarizationDate;
	}

	public void setNotarizationDate(Date notarizationDate) {
		_notarizationDate = notarizationDate;
	}

	public String getSignerName() {
		return _signerName;
	}

	public void setSignerName(String signerName) {
		_signerName = signerName;
	}

	public String getSignerPosition() {
		return _signerPosition;
	}

	public void setSignerPosition(String signerPosition) {
		_signerPosition = signerPosition;
	}

	public String getStatusCode() {
		return _statusCode;
	}

	public void setStatusCode(String statusCode) {
		_statusCode = statusCode;
	}

	private long _notarizationId;
	private long _groupId;
	private long _companyId;
	private long _userId;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private long _dossierId;
	private String _fileName;
	private int _totalRecord;
	private int _totalPage;
	private int _totalCopy;
	private long _totalFee;
	private long _notarizationNo;
	private int _notarizationYear;
	private Date _notarizationDate;
	private String _signerName;
	private String _signerPosition;
	private String _statusCode;
}