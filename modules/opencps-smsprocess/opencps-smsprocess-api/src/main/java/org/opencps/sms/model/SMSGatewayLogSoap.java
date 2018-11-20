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

package org.opencps.sms.model;

import aQute.bnd.annotation.ProviderType;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * This class is used by SOAP remote services, specifically {@link org.opencps.sms.service.http.SMSGatewayLogServiceSoap}.
 *
 * @author khoa
 * @see org.opencps.sms.service.http.SMSGatewayLogServiceSoap
 * @generated
 */
@ProviderType
public class SMSGatewayLogSoap implements Serializable {
	public static SMSGatewayLogSoap toSoapModel(SMSGatewayLog model) {
		SMSGatewayLogSoap soapModel = new SMSGatewayLogSoap();

		soapModel.setUuid(model.getUuid());
		soapModel.setSmsId(model.getSmsId());
		soapModel.setGroupId(model.getGroupId());
		soapModel.setCompanyId(model.getCompanyId());
		soapModel.setUserId(model.getUserId());
		soapModel.setCreateDate(model.getCreateDate());
		soapModel.setModifiedDate(model.getModifiedDate());
		soapModel.setSrc(model.getSrc());
		soapModel.setSmsReq(model.getSmsReq());
		soapModel.setSmsReply(model.getSmsReply());
		soapModel.setDossierNo(model.getDossierNo());
		soapModel.setApplicationName(model.getApplicationName());
		soapModel.setReqDate(model.getReqDate());
		soapModel.setReplyDate(model.getReplyDate());
		soapModel.setStatus(model.getStatus());
		soapModel.setSmsType(model.getSmsType());
		soapModel.setLastReplyManualDate(model.getLastReplyManualDate());
		soapModel.setLastReplyManualUserId(model.getLastReplyManualUserId());
		soapModel.setLastReplyManualUserName(model.getLastReplyManualUserName());

		return soapModel;
	}

	public static SMSGatewayLogSoap[] toSoapModels(SMSGatewayLog[] models) {
		SMSGatewayLogSoap[] soapModels = new SMSGatewayLogSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static SMSGatewayLogSoap[][] toSoapModels(SMSGatewayLog[][] models) {
		SMSGatewayLogSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new SMSGatewayLogSoap[models.length][models[0].length];
		}
		else {
			soapModels = new SMSGatewayLogSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static SMSGatewayLogSoap[] toSoapModels(List<SMSGatewayLog> models) {
		List<SMSGatewayLogSoap> soapModels = new ArrayList<SMSGatewayLogSoap>(models.size());

		for (SMSGatewayLog model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new SMSGatewayLogSoap[soapModels.size()]);
	}

	public SMSGatewayLogSoap() {
	}

	public long getPrimaryKey() {
		return _smsId;
	}

	public void setPrimaryKey(long pk) {
		setSmsId(pk);
	}

	public String getUuid() {
		return _uuid;
	}

	public void setUuid(String uuid) {
		_uuid = uuid;
	}

	public long getSmsId() {
		return _smsId;
	}

	public void setSmsId(long smsId) {
		_smsId = smsId;
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

	public String getSrc() {
		return _src;
	}

	public void setSrc(String src) {
		_src = src;
	}

	public String getSmsReq() {
		return _smsReq;
	}

	public void setSmsReq(String smsReq) {
		_smsReq = smsReq;
	}

	public String getSmsReply() {
		return _smsReply;
	}

	public void setSmsReply(String smsReply) {
		_smsReply = smsReply;
	}

	public String getDossierNo() {
		return _dossierNo;
	}

	public void setDossierNo(String dossierNo) {
		_dossierNo = dossierNo;
	}

	public String getApplicationName() {
		return _applicationName;
	}

	public void setApplicationName(String applicationName) {
		_applicationName = applicationName;
	}

	public Date getReqDate() {
		return _reqDate;
	}

	public void setReqDate(Date reqDate) {
		_reqDate = reqDate;
	}

	public Date getReplyDate() {
		return _replyDate;
	}

	public void setReplyDate(Date replyDate) {
		_replyDate = replyDate;
	}

	public int getStatus() {
		return _status;
	}

	public void setStatus(int status) {
		_status = status;
	}

	public int getSmsType() {
		return _smsType;
	}

	public void setSmsType(int smsType) {
		_smsType = smsType;
	}

	public Date getLastReplyManualDate() {
		return _lastReplyManualDate;
	}

	public void setLastReplyManualDate(Date lastReplyManualDate) {
		_lastReplyManualDate = lastReplyManualDate;
	}

	public long getLastReplyManualUserId() {
		return _lastReplyManualUserId;
	}

	public void setLastReplyManualUserId(long lastReplyManualUserId) {
		_lastReplyManualUserId = lastReplyManualUserId;
	}

	public String getLastReplyManualUserName() {
		return _lastReplyManualUserName;
	}

	public void setLastReplyManualUserName(String lastReplyManualUserName) {
		_lastReplyManualUserName = lastReplyManualUserName;
	}

	private String _uuid;
	private long _smsId;
	private long _groupId;
	private long _companyId;
	private long _userId;
	private Date _createDate;
	private Date _modifiedDate;
	private String _src;
	private String _smsReq;
	private String _smsReply;
	private String _dossierNo;
	private String _applicationName;
	private Date _reqDate;
	private Date _replyDate;
	private int _status;
	private int _smsType;
	private Date _lastReplyManualDate;
	private long _lastReplyManualUserId;
	private String _lastReplyManualUserName;
}