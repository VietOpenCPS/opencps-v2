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

package org.opencps.backend.processmgt.model.impl;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.util.HashUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;

import org.opencps.backend.processmgt.model.ProcessAction;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing ProcessAction in entity cache.
 *
 * @author khoavu
 * @see ProcessAction
 * @generated
 */
@ProviderType
public class ProcessActionCacheModel implements CacheModel<ProcessAction>,
	Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof ProcessActionCacheModel)) {
			return false;
		}

		ProcessActionCacheModel processActionCacheModel = (ProcessActionCacheModel)obj;

		if (processActionId == processActionCacheModel.processActionId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, processActionId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(47);

		sb.append("{uuid=");
		sb.append(uuid);
		sb.append(", processActionId=");
		sb.append(processActionId);
		sb.append(", companyId=");
		sb.append(companyId);
		sb.append(", groupId=");
		sb.append(groupId);
		sb.append(", userId=");
		sb.append(userId);
		sb.append(", userName=");
		sb.append(userName);
		sb.append(", createDate=");
		sb.append(createDate);
		sb.append(", modifiedDate=");
		sb.append(modifiedDate);
		sb.append(", serviceProcessId=");
		sb.append(serviceProcessId);
		sb.append(", preProcessStepId=");
		sb.append(preProcessStepId);
		sb.append(", postProcessStepId=");
		sb.append(postProcessStepId);
		sb.append(", autoEvent=");
		sb.append(autoEvent);
		sb.append(", preCondition=");
		sb.append(preCondition);
		sb.append(", actionCode=");
		sb.append(actionCode);
		sb.append(", actionName=");
		sb.append(actionName);
		sb.append(", allowAssignUser=");
		sb.append(allowAssignUser);
		sb.append(", assignUserId=");
		sb.append(assignUserId);
		sb.append(", requestPayment=");
		sb.append(requestPayment);
		sb.append(", paymentFee=");
		sb.append(paymentFee);
		sb.append(", createDossierFiles=");
		sb.append(createDossierFiles);
		sb.append(", returnDossierFiles=");
		sb.append(returnDossierFiles);
		sb.append(", syncActionCode=");
		sb.append(syncActionCode);
		sb.append(", rollback=");
		sb.append(rollback);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public ProcessAction toEntityModel() {
		ProcessActionImpl processActionImpl = new ProcessActionImpl();

		if (uuid == null) {
			processActionImpl.setUuid(StringPool.BLANK);
		}
		else {
			processActionImpl.setUuid(uuid);
		}

		processActionImpl.setProcessActionId(processActionId);
		processActionImpl.setCompanyId(companyId);
		processActionImpl.setGroupId(groupId);
		processActionImpl.setUserId(userId);

		if (userName == null) {
			processActionImpl.setUserName(StringPool.BLANK);
		}
		else {
			processActionImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			processActionImpl.setCreateDate(null);
		}
		else {
			processActionImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			processActionImpl.setModifiedDate(null);
		}
		else {
			processActionImpl.setModifiedDate(new Date(modifiedDate));
		}

		processActionImpl.setServiceProcessId(serviceProcessId);
		processActionImpl.setPreProcessStepId(preProcessStepId);
		processActionImpl.setPostProcessStepId(postProcessStepId);

		if (autoEvent == null) {
			processActionImpl.setAutoEvent(StringPool.BLANK);
		}
		else {
			processActionImpl.setAutoEvent(autoEvent);
		}

		if (preCondition == null) {
			processActionImpl.setPreCondition(StringPool.BLANK);
		}
		else {
			processActionImpl.setPreCondition(preCondition);
		}

		if (actionCode == null) {
			processActionImpl.setActionCode(StringPool.BLANK);
		}
		else {
			processActionImpl.setActionCode(actionCode);
		}

		if (actionName == null) {
			processActionImpl.setActionName(StringPool.BLANK);
		}
		else {
			processActionImpl.setActionName(actionName);
		}

		if (allowAssignUser == null) {
			processActionImpl.setAllowAssignUser(StringPool.BLANK);
		}
		else {
			processActionImpl.setAllowAssignUser(allowAssignUser);
		}

		processActionImpl.setAssignUserId(assignUserId);

		if (requestPayment == null) {
			processActionImpl.setRequestPayment(StringPool.BLANK);
		}
		else {
			processActionImpl.setRequestPayment(requestPayment);
		}

		if (paymentFee == null) {
			processActionImpl.setPaymentFee(StringPool.BLANK);
		}
		else {
			processActionImpl.setPaymentFee(paymentFee);
		}

		if (createDossierFiles == null) {
			processActionImpl.setCreateDossierFiles(StringPool.BLANK);
		}
		else {
			processActionImpl.setCreateDossierFiles(createDossierFiles);
		}

		if (returnDossierFiles == null) {
			processActionImpl.setReturnDossierFiles(StringPool.BLANK);
		}
		else {
			processActionImpl.setReturnDossierFiles(returnDossierFiles);
		}

		if (syncActionCode == null) {
			processActionImpl.setSyncActionCode(StringPool.BLANK);
		}
		else {
			processActionImpl.setSyncActionCode(syncActionCode);
		}

		if (rollback == null) {
			processActionImpl.setRollback(StringPool.BLANK);
		}
		else {
			processActionImpl.setRollback(rollback);
		}

		processActionImpl.resetOriginalValues();

		return processActionImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		uuid = objectInput.readUTF();

		processActionId = objectInput.readLong();

		companyId = objectInput.readLong();

		groupId = objectInput.readLong();

		userId = objectInput.readLong();
		userName = objectInput.readUTF();
		createDate = objectInput.readLong();
		modifiedDate = objectInput.readLong();

		serviceProcessId = objectInput.readLong();

		preProcessStepId = objectInput.readLong();

		postProcessStepId = objectInput.readLong();
		autoEvent = objectInput.readUTF();
		preCondition = objectInput.readUTF();
		actionCode = objectInput.readUTF();
		actionName = objectInput.readUTF();
		allowAssignUser = objectInput.readUTF();

		assignUserId = objectInput.readLong();
		requestPayment = objectInput.readUTF();
		paymentFee = objectInput.readUTF();
		createDossierFiles = objectInput.readUTF();
		returnDossierFiles = objectInput.readUTF();
		syncActionCode = objectInput.readUTF();
		rollback = objectInput.readUTF();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput)
		throws IOException {
		if (uuid == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(uuid);
		}

		objectOutput.writeLong(processActionId);

		objectOutput.writeLong(companyId);

		objectOutput.writeLong(groupId);

		objectOutput.writeLong(userId);

		if (userName == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(userName);
		}

		objectOutput.writeLong(createDate);
		objectOutput.writeLong(modifiedDate);

		objectOutput.writeLong(serviceProcessId);

		objectOutput.writeLong(preProcessStepId);

		objectOutput.writeLong(postProcessStepId);

		if (autoEvent == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(autoEvent);
		}

		if (preCondition == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(preCondition);
		}

		if (actionCode == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(actionCode);
		}

		if (actionName == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(actionName);
		}

		if (allowAssignUser == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(allowAssignUser);
		}

		objectOutput.writeLong(assignUserId);

		if (requestPayment == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(requestPayment);
		}

		if (paymentFee == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(paymentFee);
		}

		if (createDossierFiles == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(createDossierFiles);
		}

		if (returnDossierFiles == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(returnDossierFiles);
		}

		if (syncActionCode == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(syncActionCode);
		}

		if (rollback == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(rollback);
		}
	}

	public String uuid;
	public long processActionId;
	public long companyId;
	public long groupId;
	public long userId;
	public String userName;
	public long createDate;
	public long modifiedDate;
	public long serviceProcessId;
	public long preProcessStepId;
	public long postProcessStepId;
	public String autoEvent;
	public String preCondition;
	public String actionCode;
	public String actionName;
	public String allowAssignUser;
	public long assignUserId;
	public String requestPayment;
	public String paymentFee;
	public String createDossierFiles;
	public String returnDossierFiles;
	public String syncActionCode;
	public String rollback;
}