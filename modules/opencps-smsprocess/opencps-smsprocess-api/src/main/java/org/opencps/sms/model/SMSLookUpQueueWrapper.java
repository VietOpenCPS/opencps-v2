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
 * This class is a wrapper for {@link SMSLookUpQueue}.
 * </p>
 *
 * @author khoa
 * @see SMSLookUpQueue
 * @generated
 */
@ProviderType
public class SMSLookUpQueueWrapper implements SMSLookUpQueue,
	ModelWrapper<SMSLookUpQueue> {
	public SMSLookUpQueueWrapper(SMSLookUpQueue smsLookUpQueue) {
		_smsLookUpQueue = smsLookUpQueue;
	}

	@Override
	public Class<?> getModelClass() {
		return SMSLookUpQueue.class;
	}

	@Override
	public String getModelClassName() {
		return SMSLookUpQueue.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("uuid", getUuid());
		attributes.put("queueId", getQueueId());
		attributes.put("groupId", getGroupId());
		attributes.put("companyId", getCompanyId());
		attributes.put("userId", getUserId());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("moid", getMoid());
		attributes.put("src", getSrc());
		attributes.put("dest", getDest());
		attributes.put("moseq", getMoseq());
		attributes.put("cmdcode", getCmdcode());
		attributes.put("msgbody", getMsgbody());
		attributes.put("password", getPassword());
		attributes.put("status", getStatus());
		attributes.put("receivedDate", getReceivedDate());
		attributes.put("userName", getUserName());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		String uuid = (String)attributes.get("uuid");

		if (uuid != null) {
			setUuid(uuid);
		}

		Long queueId = (Long)attributes.get("queueId");

		if (queueId != null) {
			setQueueId(queueId);
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

		String moid = (String)attributes.get("moid");

		if (moid != null) {
			setMoid(moid);
		}

		String src = (String)attributes.get("src");

		if (src != null) {
			setSrc(src);
		}

		String dest = (String)attributes.get("dest");

		if (dest != null) {
			setDest(dest);
		}

		String moseq = (String)attributes.get("moseq");

		if (moseq != null) {
			setMoseq(moseq);
		}

		String cmdcode = (String)attributes.get("cmdcode");

		if (cmdcode != null) {
			setCmdcode(cmdcode);
		}

		String msgbody = (String)attributes.get("msgbody");

		if (msgbody != null) {
			setMsgbody(msgbody);
		}

		String password = (String)attributes.get("password");

		if (password != null) {
			setPassword(password);
		}

		Integer status = (Integer)attributes.get("status");

		if (status != null) {
			setStatus(status);
		}

		Date receivedDate = (Date)attributes.get("receivedDate");

		if (receivedDate != null) {
			setReceivedDate(receivedDate);
		}

		String userName = (String)attributes.get("userName");

		if (userName != null) {
			setUserName(userName);
		}
	}

	@Override
	public Object clone() {
		return new SMSLookUpQueueWrapper((SMSLookUpQueue)_smsLookUpQueue.clone());
	}

	@Override
	public int compareTo(SMSLookUpQueue smsLookUpQueue) {
		return _smsLookUpQueue.compareTo(smsLookUpQueue);
	}

	/**
	* Returns the cmdcode of this sms look up queue.
	*
	* @return the cmdcode of this sms look up queue
	*/
	@Override
	public String getCmdcode() {
		return _smsLookUpQueue.getCmdcode();
	}

	/**
	* Returns the company ID of this sms look up queue.
	*
	* @return the company ID of this sms look up queue
	*/
	@Override
	public long getCompanyId() {
		return _smsLookUpQueue.getCompanyId();
	}

	/**
	* Returns the create date of this sms look up queue.
	*
	* @return the create date of this sms look up queue
	*/
	@Override
	public Date getCreateDate() {
		return _smsLookUpQueue.getCreateDate();
	}

	/**
	* Returns the dest of this sms look up queue.
	*
	* @return the dest of this sms look up queue
	*/
	@Override
	public String getDest() {
		return _smsLookUpQueue.getDest();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _smsLookUpQueue.getExpandoBridge();
	}

	/**
	* Returns the group ID of this sms look up queue.
	*
	* @return the group ID of this sms look up queue
	*/
	@Override
	public long getGroupId() {
		return _smsLookUpQueue.getGroupId();
	}

	/**
	* Returns the modified date of this sms look up queue.
	*
	* @return the modified date of this sms look up queue
	*/
	@Override
	public Date getModifiedDate() {
		return _smsLookUpQueue.getModifiedDate();
	}

	/**
	* Returns the moid of this sms look up queue.
	*
	* @return the moid of this sms look up queue
	*/
	@Override
	public String getMoid() {
		return _smsLookUpQueue.getMoid();
	}

	/**
	* Returns the moseq of this sms look up queue.
	*
	* @return the moseq of this sms look up queue
	*/
	@Override
	public String getMoseq() {
		return _smsLookUpQueue.getMoseq();
	}

	/**
	* Returns the msgbody of this sms look up queue.
	*
	* @return the msgbody of this sms look up queue
	*/
	@Override
	public String getMsgbody() {
		return _smsLookUpQueue.getMsgbody();
	}

	/**
	* Returns the password of this sms look up queue.
	*
	* @return the password of this sms look up queue
	*/
	@Override
	public String getPassword() {
		return _smsLookUpQueue.getPassword();
	}

	/**
	* Returns the primary key of this sms look up queue.
	*
	* @return the primary key of this sms look up queue
	*/
	@Override
	public long getPrimaryKey() {
		return _smsLookUpQueue.getPrimaryKey();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _smsLookUpQueue.getPrimaryKeyObj();
	}

	/**
	* Returns the queue ID of this sms look up queue.
	*
	* @return the queue ID of this sms look up queue
	*/
	@Override
	public long getQueueId() {
		return _smsLookUpQueue.getQueueId();
	}

	/**
	* Returns the received date of this sms look up queue.
	*
	* @return the received date of this sms look up queue
	*/
	@Override
	public Date getReceivedDate() {
		return _smsLookUpQueue.getReceivedDate();
	}

	/**
	* Returns the src of this sms look up queue.
	*
	* @return the src of this sms look up queue
	*/
	@Override
	public String getSrc() {
		return _smsLookUpQueue.getSrc();
	}

	/**
	* Returns the status of this sms look up queue.
	*
	* @return the status of this sms look up queue
	*/
	@Override
	public int getStatus() {
		return _smsLookUpQueue.getStatus();
	}

	/**
	* Returns the user ID of this sms look up queue.
	*
	* @return the user ID of this sms look up queue
	*/
	@Override
	public long getUserId() {
		return _smsLookUpQueue.getUserId();
	}

	/**
	* Returns the user name of this sms look up queue.
	*
	* @return the user name of this sms look up queue
	*/
	@Override
	public String getUserName() {
		return _smsLookUpQueue.getUserName();
	}

	/**
	* Returns the user uuid of this sms look up queue.
	*
	* @return the user uuid of this sms look up queue
	*/
	@Override
	public String getUserUuid() {
		return _smsLookUpQueue.getUserUuid();
	}

	/**
	* Returns the uuid of this sms look up queue.
	*
	* @return the uuid of this sms look up queue
	*/
	@Override
	public String getUuid() {
		return _smsLookUpQueue.getUuid();
	}

	@Override
	public int hashCode() {
		return _smsLookUpQueue.hashCode();
	}

	@Override
	public boolean isCachedModel() {
		return _smsLookUpQueue.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _smsLookUpQueue.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _smsLookUpQueue.isNew();
	}

	@Override
	public void persist() {
		_smsLookUpQueue.persist();
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_smsLookUpQueue.setCachedModel(cachedModel);
	}

	/**
	* Sets the cmdcode of this sms look up queue.
	*
	* @param cmdcode the cmdcode of this sms look up queue
	*/
	@Override
	public void setCmdcode(String cmdcode) {
		_smsLookUpQueue.setCmdcode(cmdcode);
	}

	/**
	* Sets the company ID of this sms look up queue.
	*
	* @param companyId the company ID of this sms look up queue
	*/
	@Override
	public void setCompanyId(long companyId) {
		_smsLookUpQueue.setCompanyId(companyId);
	}

	/**
	* Sets the create date of this sms look up queue.
	*
	* @param createDate the create date of this sms look up queue
	*/
	@Override
	public void setCreateDate(Date createDate) {
		_smsLookUpQueue.setCreateDate(createDate);
	}

	/**
	* Sets the dest of this sms look up queue.
	*
	* @param dest the dest of this sms look up queue
	*/
	@Override
	public void setDest(String dest) {
		_smsLookUpQueue.setDest(dest);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_smsLookUpQueue.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_smsLookUpQueue.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_smsLookUpQueue.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the group ID of this sms look up queue.
	*
	* @param groupId the group ID of this sms look up queue
	*/
	@Override
	public void setGroupId(long groupId) {
		_smsLookUpQueue.setGroupId(groupId);
	}

	/**
	* Sets the modified date of this sms look up queue.
	*
	* @param modifiedDate the modified date of this sms look up queue
	*/
	@Override
	public void setModifiedDate(Date modifiedDate) {
		_smsLookUpQueue.setModifiedDate(modifiedDate);
	}

	/**
	* Sets the moid of this sms look up queue.
	*
	* @param moid the moid of this sms look up queue
	*/
	@Override
	public void setMoid(String moid) {
		_smsLookUpQueue.setMoid(moid);
	}

	/**
	* Sets the moseq of this sms look up queue.
	*
	* @param moseq the moseq of this sms look up queue
	*/
	@Override
	public void setMoseq(String moseq) {
		_smsLookUpQueue.setMoseq(moseq);
	}

	/**
	* Sets the msgbody of this sms look up queue.
	*
	* @param msgbody the msgbody of this sms look up queue
	*/
	@Override
	public void setMsgbody(String msgbody) {
		_smsLookUpQueue.setMsgbody(msgbody);
	}

	@Override
	public void setNew(boolean n) {
		_smsLookUpQueue.setNew(n);
	}

	/**
	* Sets the password of this sms look up queue.
	*
	* @param password the password of this sms look up queue
	*/
	@Override
	public void setPassword(String password) {
		_smsLookUpQueue.setPassword(password);
	}

	/**
	* Sets the primary key of this sms look up queue.
	*
	* @param primaryKey the primary key of this sms look up queue
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_smsLookUpQueue.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_smsLookUpQueue.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the queue ID of this sms look up queue.
	*
	* @param queueId the queue ID of this sms look up queue
	*/
	@Override
	public void setQueueId(long queueId) {
		_smsLookUpQueue.setQueueId(queueId);
	}

	/**
	* Sets the received date of this sms look up queue.
	*
	* @param receivedDate the received date of this sms look up queue
	*/
	@Override
	public void setReceivedDate(Date receivedDate) {
		_smsLookUpQueue.setReceivedDate(receivedDate);
	}

	/**
	* Sets the src of this sms look up queue.
	*
	* @param src the src of this sms look up queue
	*/
	@Override
	public void setSrc(String src) {
		_smsLookUpQueue.setSrc(src);
	}

	/**
	* Sets the status of this sms look up queue.
	*
	* @param status the status of this sms look up queue
	*/
	@Override
	public void setStatus(int status) {
		_smsLookUpQueue.setStatus(status);
	}

	/**
	* Sets the user ID of this sms look up queue.
	*
	* @param userId the user ID of this sms look up queue
	*/
	@Override
	public void setUserId(long userId) {
		_smsLookUpQueue.setUserId(userId);
	}

	/**
	* Sets the user name of this sms look up queue.
	*
	* @param userName the user name of this sms look up queue
	*/
	@Override
	public void setUserName(String userName) {
		_smsLookUpQueue.setUserName(userName);
	}

	/**
	* Sets the user uuid of this sms look up queue.
	*
	* @param userUuid the user uuid of this sms look up queue
	*/
	@Override
	public void setUserUuid(String userUuid) {
		_smsLookUpQueue.setUserUuid(userUuid);
	}

	/**
	* Sets the uuid of this sms look up queue.
	*
	* @param uuid the uuid of this sms look up queue
	*/
	@Override
	public void setUuid(String uuid) {
		_smsLookUpQueue.setUuid(uuid);
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<SMSLookUpQueue> toCacheModel() {
		return _smsLookUpQueue.toCacheModel();
	}

	@Override
	public SMSLookUpQueue toEscapedModel() {
		return new SMSLookUpQueueWrapper(_smsLookUpQueue.toEscapedModel());
	}

	@Override
	public String toString() {
		return _smsLookUpQueue.toString();
	}

	@Override
	public SMSLookUpQueue toUnescapedModel() {
		return new SMSLookUpQueueWrapper(_smsLookUpQueue.toUnescapedModel());
	}

	@Override
	public String toXmlString() {
		return _smsLookUpQueue.toXmlString();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof SMSLookUpQueueWrapper)) {
			return false;
		}

		SMSLookUpQueueWrapper smsLookUpQueueWrapper = (SMSLookUpQueueWrapper)obj;

		if (Objects.equals(_smsLookUpQueue,
					smsLookUpQueueWrapper._smsLookUpQueue)) {
			return true;
		}

		return false;
	}

	@Override
	public StagedModelType getStagedModelType() {
		return _smsLookUpQueue.getStagedModelType();
	}

	@Override
	public SMSLookUpQueue getWrappedModel() {
		return _smsLookUpQueue;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _smsLookUpQueue.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _smsLookUpQueue.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_smsLookUpQueue.resetOriginalValues();
	}

	private final SMSLookUpQueue _smsLookUpQueue;
}