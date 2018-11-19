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

import com.liferay.expando.kernel.model.ExpandoBridge;

import com.liferay.exportimport.kernel.lar.StagedModelType;

import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.service.ServiceContext;

import java.io.Serializable;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * <p>
 * This class is a wrapper for {@link SMSGatewayLog}.
 * </p>
 *
 * @author khoa
 * @see SMSGatewayLog
 * @generated
 */
@ProviderType
public class SMSGatewayLogWrapper implements SMSGatewayLog,
	ModelWrapper<SMSGatewayLog> {
	public SMSGatewayLogWrapper(SMSGatewayLog smsGatewayLog) {
		_smsGatewayLog = smsGatewayLog;
	}

	@Override
	public Class<?> getModelClass() {
		return SMSGatewayLog.class;
	}

	@Override
	public String getModelClassName() {
		return SMSGatewayLog.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("uuid", getUuid());
		attributes.put("smsId", getSmsId());
		attributes.put("groupId", getGroupId());
		attributes.put("companyId", getCompanyId());
		attributes.put("userId", getUserId());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("src", getSrc());
		attributes.put("smsReq", getSmsReq());
		attributes.put("smsReply", getSmsReply());
		attributes.put("dossierNo", getDossierNo());
		attributes.put("applicationName", getApplicationName());
		attributes.put("reqDate", getReqDate());
		attributes.put("replyDate", getReplyDate());
		attributes.put("status", getStatus());
		attributes.put("smsType", getSmsType());
		attributes.put("lastReplyManualDate", getLastReplyManualDate());
		attributes.put("lastReplyManualUserId", getLastReplyManualUserId());
		attributes.put("lastReplyManualUserName", getLastReplyManualUserName());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		String uuid = (String)attributes.get("uuid");

		if (uuid != null) {
			setUuid(uuid);
		}

		Long smsId = (Long)attributes.get("smsId");

		if (smsId != null) {
			setSmsId(smsId);
		}

		Long groupId = (Long)attributes.get("groupId");

		if (groupId != null) {
			setGroupId(groupId);
		}

		Long companyId = (Long)attributes.get("companyId");

		if (companyId != null) {
			setCompanyId(companyId);
		}

		Long userId = (Long)attributes.get("userId");

		if (userId != null) {
			setUserId(userId);
		}

		Date createDate = (Date)attributes.get("createDate");

		if (createDate != null) {
			setCreateDate(createDate);
		}

		Date modifiedDate = (Date)attributes.get("modifiedDate");

		if (modifiedDate != null) {
			setModifiedDate(modifiedDate);
		}

		String src = (String)attributes.get("src");

		if (src != null) {
			setSrc(src);
		}

		String smsReq = (String)attributes.get("smsReq");

		if (smsReq != null) {
			setSmsReq(smsReq);
		}

		String smsReply = (String)attributes.get("smsReply");

		if (smsReply != null) {
			setSmsReply(smsReply);
		}

		String dossierNo = (String)attributes.get("dossierNo");

		if (dossierNo != null) {
			setDossierNo(dossierNo);
		}

		String applicationName = (String)attributes.get("applicationName");

		if (applicationName != null) {
			setApplicationName(applicationName);
		}

		Date reqDate = (Date)attributes.get("reqDate");

		if (reqDate != null) {
			setReqDate(reqDate);
		}

		Date replyDate = (Date)attributes.get("replyDate");

		if (replyDate != null) {
			setReplyDate(replyDate);
		}

		Integer status = (Integer)attributes.get("status");

		if (status != null) {
			setStatus(status);
		}

		Integer smsType = (Integer)attributes.get("smsType");

		if (smsType != null) {
			setSmsType(smsType);
		}

		Date lastReplyManualDate = (Date)attributes.get("lastReplyManualDate");

		if (lastReplyManualDate != null) {
			setLastReplyManualDate(lastReplyManualDate);
		}

		Long lastReplyManualUserId = (Long)attributes.get(
				"lastReplyManualUserId");

		if (lastReplyManualUserId != null) {
			setLastReplyManualUserId(lastReplyManualUserId);
		}

		String lastReplyManualUserName = (String)attributes.get(
				"lastReplyManualUserName");

		if (lastReplyManualUserName != null) {
			setLastReplyManualUserName(lastReplyManualUserName);
		}
	}

	@Override
	public Object clone() {
		return new SMSGatewayLogWrapper((SMSGatewayLog)_smsGatewayLog.clone());
	}

	@Override
	public int compareTo(SMSGatewayLog smsGatewayLog) {
		return _smsGatewayLog.compareTo(smsGatewayLog);
	}

	/**
	* Returns the application name of this sms gateway log.
	*
	* @return the application name of this sms gateway log
	*/
	@Override
	public String getApplicationName() {
		return _smsGatewayLog.getApplicationName();
	}

	/**
	* Returns the company ID of this sms gateway log.
	*
	* @return the company ID of this sms gateway log
	*/
	@Override
	public long getCompanyId() {
		return _smsGatewayLog.getCompanyId();
	}

	/**
	* Returns the create date of this sms gateway log.
	*
	* @return the create date of this sms gateway log
	*/
	@Override
	public Date getCreateDate() {
		return _smsGatewayLog.getCreateDate();
	}

	/**
	* Returns the dossier no of this sms gateway log.
	*
	* @return the dossier no of this sms gateway log
	*/
	@Override
	public String getDossierNo() {
		return _smsGatewayLog.getDossierNo();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _smsGatewayLog.getExpandoBridge();
	}

	/**
	* Returns the group ID of this sms gateway log.
	*
	* @return the group ID of this sms gateway log
	*/
	@Override
	public long getGroupId() {
		return _smsGatewayLog.getGroupId();
	}

	/**
	* Returns the last reply manual date of this sms gateway log.
	*
	* @return the last reply manual date of this sms gateway log
	*/
	@Override
	public Date getLastReplyManualDate() {
		return _smsGatewayLog.getLastReplyManualDate();
	}

	/**
	* Returns the last reply manual user ID of this sms gateway log.
	*
	* @return the last reply manual user ID of this sms gateway log
	*/
	@Override
	public long getLastReplyManualUserId() {
		return _smsGatewayLog.getLastReplyManualUserId();
	}

	/**
	* Returns the last reply manual user name of this sms gateway log.
	*
	* @return the last reply manual user name of this sms gateway log
	*/
	@Override
	public String getLastReplyManualUserName() {
		return _smsGatewayLog.getLastReplyManualUserName();
	}

	/**
	* Returns the last reply manual user uuid of this sms gateway log.
	*
	* @return the last reply manual user uuid of this sms gateway log
	*/
	@Override
	public String getLastReplyManualUserUuid() {
		return _smsGatewayLog.getLastReplyManualUserUuid();
	}

	/**
	* Returns the modified date of this sms gateway log.
	*
	* @return the modified date of this sms gateway log
	*/
	@Override
	public Date getModifiedDate() {
		return _smsGatewayLog.getModifiedDate();
	}

	/**
	* Returns the primary key of this sms gateway log.
	*
	* @return the primary key of this sms gateway log
	*/
	@Override
	public long getPrimaryKey() {
		return _smsGatewayLog.getPrimaryKey();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _smsGatewayLog.getPrimaryKeyObj();
	}

	/**
	* Returns the reply date of this sms gateway log.
	*
	* @return the reply date of this sms gateway log
	*/
	@Override
	public Date getReplyDate() {
		return _smsGatewayLog.getReplyDate();
	}

	/**
	* Returns the req date of this sms gateway log.
	*
	* @return the req date of this sms gateway log
	*/
	@Override
	public Date getReqDate() {
		return _smsGatewayLog.getReqDate();
	}

	/**
	* Returns the sms ID of this sms gateway log.
	*
	* @return the sms ID of this sms gateway log
	*/
	@Override
	public long getSmsId() {
		return _smsGatewayLog.getSmsId();
	}

	/**
	* Returns the sms reply of this sms gateway log.
	*
	* @return the sms reply of this sms gateway log
	*/
	@Override
	public String getSmsReply() {
		return _smsGatewayLog.getSmsReply();
	}

	/**
	* Returns the sms req of this sms gateway log.
	*
	* @return the sms req of this sms gateway log
	*/
	@Override
	public String getSmsReq() {
		return _smsGatewayLog.getSmsReq();
	}

	/**
	* Returns the sms type of this sms gateway log.
	*
	* @return the sms type of this sms gateway log
	*/
	@Override
	public int getSmsType() {
		return _smsGatewayLog.getSmsType();
	}

	/**
	* Returns the src of this sms gateway log.
	*
	* @return the src of this sms gateway log
	*/
	@Override
	public String getSrc() {
		return _smsGatewayLog.getSrc();
	}

	/**
	* Returns the status of this sms gateway log.
	*
	* @return the status of this sms gateway log
	*/
	@Override
	public int getStatus() {
		return _smsGatewayLog.getStatus();
	}

	/**
	* Returns the user ID of this sms gateway log.
	*
	* @return the user ID of this sms gateway log
	*/
	@Override
	public long getUserId() {
		return _smsGatewayLog.getUserId();
	}

	/**
	* Returns the user uuid of this sms gateway log.
	*
	* @return the user uuid of this sms gateway log
	*/
	@Override
	public String getUserUuid() {
		return _smsGatewayLog.getUserUuid();
	}

	/**
	* Returns the uuid of this sms gateway log.
	*
	* @return the uuid of this sms gateway log
	*/
	@Override
	public String getUuid() {
		return _smsGatewayLog.getUuid();
	}

	@Override
	public int hashCode() {
		return _smsGatewayLog.hashCode();
	}

	@Override
	public boolean isCachedModel() {
		return _smsGatewayLog.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _smsGatewayLog.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _smsGatewayLog.isNew();
	}

	@Override
	public void persist() {
		_smsGatewayLog.persist();
	}

	/**
	* Sets the application name of this sms gateway log.
	*
	* @param applicationName the application name of this sms gateway log
	*/
	@Override
	public void setApplicationName(String applicationName) {
		_smsGatewayLog.setApplicationName(applicationName);
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_smsGatewayLog.setCachedModel(cachedModel);
	}

	/**
	* Sets the company ID of this sms gateway log.
	*
	* @param companyId the company ID of this sms gateway log
	*/
	@Override
	public void setCompanyId(long companyId) {
		_smsGatewayLog.setCompanyId(companyId);
	}

	/**
	* Sets the create date of this sms gateway log.
	*
	* @param createDate the create date of this sms gateway log
	*/
	@Override
	public void setCreateDate(Date createDate) {
		_smsGatewayLog.setCreateDate(createDate);
	}

	/**
	* Sets the dossier no of this sms gateway log.
	*
	* @param dossierNo the dossier no of this sms gateway log
	*/
	@Override
	public void setDossierNo(String dossierNo) {
		_smsGatewayLog.setDossierNo(dossierNo);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_smsGatewayLog.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_smsGatewayLog.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_smsGatewayLog.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the group ID of this sms gateway log.
	*
	* @param groupId the group ID of this sms gateway log
	*/
	@Override
	public void setGroupId(long groupId) {
		_smsGatewayLog.setGroupId(groupId);
	}

	/**
	* Sets the last reply manual date of this sms gateway log.
	*
	* @param lastReplyManualDate the last reply manual date of this sms gateway log
	*/
	@Override
	public void setLastReplyManualDate(Date lastReplyManualDate) {
		_smsGatewayLog.setLastReplyManualDate(lastReplyManualDate);
	}

	/**
	* Sets the last reply manual user ID of this sms gateway log.
	*
	* @param lastReplyManualUserId the last reply manual user ID of this sms gateway log
	*/
	@Override
	public void setLastReplyManualUserId(long lastReplyManualUserId) {
		_smsGatewayLog.setLastReplyManualUserId(lastReplyManualUserId);
	}

	/**
	* Sets the last reply manual user name of this sms gateway log.
	*
	* @param lastReplyManualUserName the last reply manual user name of this sms gateway log
	*/
	@Override
	public void setLastReplyManualUserName(String lastReplyManualUserName) {
		_smsGatewayLog.setLastReplyManualUserName(lastReplyManualUserName);
	}

	/**
	* Sets the last reply manual user uuid of this sms gateway log.
	*
	* @param lastReplyManualUserUuid the last reply manual user uuid of this sms gateway log
	*/
	@Override
	public void setLastReplyManualUserUuid(String lastReplyManualUserUuid) {
		_smsGatewayLog.setLastReplyManualUserUuid(lastReplyManualUserUuid);
	}

	/**
	* Sets the modified date of this sms gateway log.
	*
	* @param modifiedDate the modified date of this sms gateway log
	*/
	@Override
	public void setModifiedDate(Date modifiedDate) {
		_smsGatewayLog.setModifiedDate(modifiedDate);
	}

	@Override
	public void setNew(boolean n) {
		_smsGatewayLog.setNew(n);
	}

	/**
	* Sets the primary key of this sms gateway log.
	*
	* @param primaryKey the primary key of this sms gateway log
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_smsGatewayLog.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_smsGatewayLog.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the reply date of this sms gateway log.
	*
	* @param replyDate the reply date of this sms gateway log
	*/
	@Override
	public void setReplyDate(Date replyDate) {
		_smsGatewayLog.setReplyDate(replyDate);
	}

	/**
	* Sets the req date of this sms gateway log.
	*
	* @param reqDate the req date of this sms gateway log
	*/
	@Override
	public void setReqDate(Date reqDate) {
		_smsGatewayLog.setReqDate(reqDate);
	}

	/**
	* Sets the sms ID of this sms gateway log.
	*
	* @param smsId the sms ID of this sms gateway log
	*/
	@Override
	public void setSmsId(long smsId) {
		_smsGatewayLog.setSmsId(smsId);
	}

	/**
	* Sets the sms reply of this sms gateway log.
	*
	* @param smsReply the sms reply of this sms gateway log
	*/
	@Override
	public void setSmsReply(String smsReply) {
		_smsGatewayLog.setSmsReply(smsReply);
	}

	/**
	* Sets the sms req of this sms gateway log.
	*
	* @param smsReq the sms req of this sms gateway log
	*/
	@Override
	public void setSmsReq(String smsReq) {
		_smsGatewayLog.setSmsReq(smsReq);
	}

	/**
	* Sets the sms type of this sms gateway log.
	*
	* @param smsType the sms type of this sms gateway log
	*/
	@Override
	public void setSmsType(int smsType) {
		_smsGatewayLog.setSmsType(smsType);
	}

	/**
	* Sets the src of this sms gateway log.
	*
	* @param src the src of this sms gateway log
	*/
	@Override
	public void setSrc(String src) {
		_smsGatewayLog.setSrc(src);
	}

	/**
	* Sets the status of this sms gateway log.
	*
	* @param status the status of this sms gateway log
	*/
	@Override
	public void setStatus(int status) {
		_smsGatewayLog.setStatus(status);
	}

	/**
	* Sets the user ID of this sms gateway log.
	*
	* @param userId the user ID of this sms gateway log
	*/
	@Override
	public void setUserId(long userId) {
		_smsGatewayLog.setUserId(userId);
	}

	/**
	* Sets the user uuid of this sms gateway log.
	*
	* @param userUuid the user uuid of this sms gateway log
	*/
	@Override
	public void setUserUuid(String userUuid) {
		_smsGatewayLog.setUserUuid(userUuid);
	}

	/**
	* Sets the uuid of this sms gateway log.
	*
	* @param uuid the uuid of this sms gateway log
	*/
	@Override
	public void setUuid(String uuid) {
		_smsGatewayLog.setUuid(uuid);
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<SMSGatewayLog> toCacheModel() {
		return _smsGatewayLog.toCacheModel();
	}

	@Override
	public SMSGatewayLog toEscapedModel() {
		return new SMSGatewayLogWrapper(_smsGatewayLog.toEscapedModel());
	}

	@Override
	public String toString() {
		return _smsGatewayLog.toString();
	}

	@Override
	public SMSGatewayLog toUnescapedModel() {
		return new SMSGatewayLogWrapper(_smsGatewayLog.toUnescapedModel());
	}

	@Override
	public String toXmlString() {
		return _smsGatewayLog.toXmlString();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof SMSGatewayLogWrapper)) {
			return false;
		}

		SMSGatewayLogWrapper smsGatewayLogWrapper = (SMSGatewayLogWrapper)obj;

		if (Objects.equals(_smsGatewayLog, smsGatewayLogWrapper._smsGatewayLog)) {
			return true;
		}

		return false;
	}

	@Override
	public StagedModelType getStagedModelType() {
		return _smsGatewayLog.getStagedModelType();
	}

	@Override
	public SMSGatewayLog getWrappedModel() {
		return _smsGatewayLog;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _smsGatewayLog.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _smsGatewayLog.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_smsGatewayLog.resetOriginalValues();
	}

	private final SMSGatewayLog _smsGatewayLog;
}