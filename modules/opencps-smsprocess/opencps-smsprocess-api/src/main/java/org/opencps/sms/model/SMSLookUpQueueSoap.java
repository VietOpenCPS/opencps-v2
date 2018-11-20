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
 * This class is used by SOAP remote services, specifically {@link org.opencps.sms.service.http.SMSLookUpQueueServiceSoap}.
 *
 * @author khoa
 * @see org.opencps.sms.service.http.SMSLookUpQueueServiceSoap
 * @generated
 */
@ProviderType
public class SMSLookUpQueueSoap implements Serializable {
	public static SMSLookUpQueueSoap toSoapModel(SMSLookUpQueue model) {
		SMSLookUpQueueSoap soapModel = new SMSLookUpQueueSoap();

		soapModel.setUuid(model.getUuid());
		soapModel.setQueueId(model.getQueueId());
		soapModel.setGroupId(model.getGroupId());
		soapModel.setCompanyId(model.getCompanyId());
		soapModel.setUserId(model.getUserId());
		soapModel.setCreateDate(model.getCreateDate());
		soapModel.setModifiedDate(model.getModifiedDate());
		soapModel.setMoid(model.getMoid());
		soapModel.setSrc(model.getSrc());
		soapModel.setDest(model.getDest());
		soapModel.setMoseq(model.getMoseq());
		soapModel.setCmdcode(model.getCmdcode());
		soapModel.setMsgbody(model.getMsgbody());
		soapModel.setPassword(model.getPassword());
		soapModel.setStatus(model.getStatus());
		soapModel.setReceivedDate(model.getReceivedDate());
		soapModel.setUserName(model.getUserName());

		return soapModel;
	}

	public static SMSLookUpQueueSoap[] toSoapModels(SMSLookUpQueue[] models) {
		SMSLookUpQueueSoap[] soapModels = new SMSLookUpQueueSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static SMSLookUpQueueSoap[][] toSoapModels(SMSLookUpQueue[][] models) {
		SMSLookUpQueueSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new SMSLookUpQueueSoap[models.length][models[0].length];
		}
		else {
			soapModels = new SMSLookUpQueueSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static SMSLookUpQueueSoap[] toSoapModels(List<SMSLookUpQueue> models) {
		List<SMSLookUpQueueSoap> soapModels = new ArrayList<SMSLookUpQueueSoap>(models.size());

		for (SMSLookUpQueue model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new SMSLookUpQueueSoap[soapModels.size()]);
	}

	public SMSLookUpQueueSoap() {
	}

	public long getPrimaryKey() {
		return _queueId;
	}

	public void setPrimaryKey(long pk) {
		setQueueId(pk);
	}

	public String getUuid() {
		return _uuid;
	}

	public void setUuid(String uuid) {
		_uuid = uuid;
	}

	public long getQueueId() {
		return _queueId;
	}

	public void setQueueId(long queueId) {
		_queueId = queueId;
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

	public String getMoid() {
		return _moid;
	}

	public void setMoid(String moid) {
		_moid = moid;
	}

	public String getSrc() {
		return _src;
	}

	public void setSrc(String src) {
		_src = src;
	}

	public String getDest() {
		return _dest;
	}

	public void setDest(String dest) {
		_dest = dest;
	}

	public String getMoseq() {
		return _moseq;
	}

	public void setMoseq(String moseq) {
		_moseq = moseq;
	}

	public String getCmdcode() {
		return _cmdcode;
	}

	public void setCmdcode(String cmdcode) {
		_cmdcode = cmdcode;
	}

	public String getMsgbody() {
		return _msgbody;
	}

	public void setMsgbody(String msgbody) {
		_msgbody = msgbody;
	}

	public String getPassword() {
		return _password;
	}

	public void setPassword(String password) {
		_password = password;
	}

	public int getStatus() {
		return _status;
	}

	public void setStatus(int status) {
		_status = status;
	}

	public Date getReceivedDate() {
		return _receivedDate;
	}

	public void setReceivedDate(Date receivedDate) {
		_receivedDate = receivedDate;
	}

	public String getUserName() {
		return _userName;
	}

	public void setUserName(String userName) {
		_userName = userName;
	}

	private String _uuid;
	private long _queueId;
	private long _groupId;
	private long _companyId;
	private long _userId;
	private Date _createDate;
	private Date _modifiedDate;
	private String _moid;
	private String _src;
	private String _dest;
	private String _moseq;
	private String _cmdcode;
	private String _msgbody;
	private String _password;
	private int _status;
	private Date _receivedDate;
	private String _userName;
}