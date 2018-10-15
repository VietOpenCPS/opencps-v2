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

package org.opencps.dossiermgt.model.impl;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.util.HashUtil;
import com.liferay.portal.kernel.util.StringBundler;

import org.opencps.dossiermgt.model.ProcessAction;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing ProcessAction in entity cache.
 *
 * @author huymq
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
		StringBundler sb = new StringBundler(63);

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
		sb.append(", preStepCode=");
		sb.append(preStepCode);
		sb.append(", postStepCode=");
		sb.append(postStepCode);
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
		sb.append(", makeBriefNote=");
		sb.append(makeBriefNote);
		sb.append(", syncActionCode=");
		sb.append(syncActionCode);
		sb.append(", rollbackable=");
		sb.append(rollbackable);
		sb.append(", createDossierNo=");
		sb.append(createDossierNo);
		sb.append(", eSignature=");
		sb.append(eSignature);
		sb.append(", configNote=");
		sb.append(configNote);
		sb.append(", dossierTemplateNo=");
		sb.append(dossierTemplateNo);
		sb.append(", signatureType=");
		sb.append(signatureType);
		sb.append(", createDossiers=");
		sb.append(createDossiers);
		sb.append(", checkInput=");
		sb.append(checkInput);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public ProcessAction toEntityModel() {
		ProcessActionImpl processActionImpl = new ProcessActionImpl();

		if (uuid == null) {
			processActionImpl.setUuid("");
		}
		else {
			processActionImpl.setUuid(uuid);
		}

		processActionImpl.setProcessActionId(processActionId);
		processActionImpl.setCompanyId(companyId);
		processActionImpl.setGroupId(groupId);
		processActionImpl.setUserId(userId);

		if (userName == null) {
			processActionImpl.setUserName("");
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

		if (preStepCode == null) {
			processActionImpl.setPreStepCode("");
		}
		else {
			processActionImpl.setPreStepCode(preStepCode);
		}

		if (postStepCode == null) {
			processActionImpl.setPostStepCode("");
		}
		else {
			processActionImpl.setPostStepCode(postStepCode);
		}

		if (autoEvent == null) {
			processActionImpl.setAutoEvent("");
		}
		else {
			processActionImpl.setAutoEvent(autoEvent);
		}

		if (preCondition == null) {
			processActionImpl.setPreCondition("");
		}
		else {
			processActionImpl.setPreCondition(preCondition);
		}

		if (actionCode == null) {
			processActionImpl.setActionCode("");
		}
		else {
			processActionImpl.setActionCode(actionCode);
		}

		if (actionName == null) {
			processActionImpl.setActionName("");
		}
		else {
			processActionImpl.setActionName(actionName);
		}

		processActionImpl.setAllowAssignUser(allowAssignUser);
		processActionImpl.setAssignUserId(assignUserId);
		processActionImpl.setRequestPayment(requestPayment);

		if (paymentFee == null) {
			processActionImpl.setPaymentFee("");
		}
		else {
			processActionImpl.setPaymentFee(paymentFee);
		}

		if (createDossierFiles == null) {
			processActionImpl.setCreateDossierFiles("");
		}
		else {
			processActionImpl.setCreateDossierFiles(createDossierFiles);
		}

		if (returnDossierFiles == null) {
			processActionImpl.setReturnDossierFiles("");
		}
		else {
			processActionImpl.setReturnDossierFiles(returnDossierFiles);
		}

		if (makeBriefNote == null) {
			processActionImpl.setMakeBriefNote("");
		}
		else {
			processActionImpl.setMakeBriefNote(makeBriefNote);
		}

		if (syncActionCode == null) {
			processActionImpl.setSyncActionCode("");
		}
		else {
			processActionImpl.setSyncActionCode(syncActionCode);
		}

		processActionImpl.setRollbackable(rollbackable);
		processActionImpl.setCreateDossierNo(createDossierNo);
		processActionImpl.setESignature(eSignature);

		if (configNote == null) {
			processActionImpl.setConfigNote("");
		}
		else {
			processActionImpl.setConfigNote(configNote);
		}

		if (dossierTemplateNo == null) {
			processActionImpl.setDossierTemplateNo("");
		}
		else {
			processActionImpl.setDossierTemplateNo(dossierTemplateNo);
		}

		if (signatureType == null) {
			processActionImpl.setSignatureType("");
		}
		else {
			processActionImpl.setSignatureType(signatureType);
		}

		if (createDossiers == null) {
			processActionImpl.setCreateDossiers("");
		}
		else {
			processActionImpl.setCreateDossiers(createDossiers);
		}

		processActionImpl.setCheckInput(checkInput);

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
		preStepCode = objectInput.readUTF();
		postStepCode = objectInput.readUTF();
		autoEvent = objectInput.readUTF();
		preCondition = objectInput.readUTF();
		actionCode = objectInput.readUTF();
		actionName = objectInput.readUTF();

		allowAssignUser = objectInput.readInt();

		assignUserId = objectInput.readLong();

		requestPayment = objectInput.readInt();
		paymentFee = objectInput.readUTF();
		createDossierFiles = objectInput.readUTF();
		returnDossierFiles = objectInput.readUTF();
		makeBriefNote = objectInput.readUTF();
		syncActionCode = objectInput.readUTF();

		rollbackable = objectInput.readBoolean();

		createDossierNo = objectInput.readBoolean();

		eSignature = objectInput.readBoolean();
		configNote = objectInput.readUTF();
		dossierTemplateNo = objectInput.readUTF();
		signatureType = objectInput.readUTF();
		createDossiers = objectInput.readUTF();

		checkInput = objectInput.readInt();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput)
		throws IOException {
		if (uuid == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(uuid);
		}

		objectOutput.writeLong(processActionId);

		objectOutput.writeLong(companyId);

		objectOutput.writeLong(groupId);

		objectOutput.writeLong(userId);

		if (userName == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(userName);
		}

		objectOutput.writeLong(createDate);
		objectOutput.writeLong(modifiedDate);

		objectOutput.writeLong(serviceProcessId);

		if (preStepCode == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(preStepCode);
		}

		if (postStepCode == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(postStepCode);
		}

		if (autoEvent == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(autoEvent);
		}

		if (preCondition == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(preCondition);
		}

		if (actionCode == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(actionCode);
		}

		if (actionName == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(actionName);
		}

		objectOutput.writeInt(allowAssignUser);

		objectOutput.writeLong(assignUserId);

		objectOutput.writeInt(requestPayment);

		if (paymentFee == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(paymentFee);
		}

		if (createDossierFiles == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(createDossierFiles);
		}

		if (returnDossierFiles == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(returnDossierFiles);
		}

		if (makeBriefNote == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(makeBriefNote);
		}

		if (syncActionCode == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(syncActionCode);
		}

		objectOutput.writeBoolean(rollbackable);

		objectOutput.writeBoolean(createDossierNo);

		objectOutput.writeBoolean(eSignature);

		if (configNote == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(configNote);
		}

		if (dossierTemplateNo == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(dossierTemplateNo);
		}

		if (signatureType == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(signatureType);
		}

		if (createDossiers == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(createDossiers);
		}

		objectOutput.writeInt(checkInput);
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
	public String preStepCode;
	public String postStepCode;
	public String autoEvent;
	public String preCondition;
	public String actionCode;
	public String actionName;
	public int allowAssignUser;
	public long assignUserId;
	public int requestPayment;
	public String paymentFee;
	public String createDossierFiles;
	public String returnDossierFiles;
	public String makeBriefNote;
	public String syncActionCode;
	public boolean rollbackable;
	public boolean createDossierNo;
	public boolean eSignature;
	public String configNote;
	public String dossierTemplateNo;
	public String signatureType;
	public String createDossiers;
	public int checkInput;
}