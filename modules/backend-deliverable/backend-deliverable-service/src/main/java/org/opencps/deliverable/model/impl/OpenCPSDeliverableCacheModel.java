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

package org.opencps.deliverable.model.impl;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.util.HashUtil;
import com.liferay.portal.kernel.util.StringBundler;

import org.opencps.deliverable.model.OpenCPSDeliverable;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing OpenCPSDeliverable in entity cache.
 *
 * @author binhth
 * @see OpenCPSDeliverable
 * @generated
 */
@ProviderType
public class OpenCPSDeliverableCacheModel implements CacheModel<OpenCPSDeliverable>,
	Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof OpenCPSDeliverableCacheModel)) {
			return false;
		}

		OpenCPSDeliverableCacheModel openCPSDeliverableCacheModel = (OpenCPSDeliverableCacheModel)obj;

		if (deliverableId == openCPSDeliverableCacheModel.deliverableId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, deliverableId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(57);

		sb.append("{uuid=");
		sb.append(uuid);
		sb.append(", deliverableId=");
		sb.append(deliverableId);
		sb.append(", groupId=");
		sb.append(groupId);
		sb.append(", companyId=");
		sb.append(companyId);
		sb.append(", userId=");
		sb.append(userId);
		sb.append(", userName=");
		sb.append(userName);
		sb.append(", createDate=");
		sb.append(createDate);
		sb.append(", modifiedDate=");
		sb.append(modifiedDate);
		sb.append(", deliverableCode=");
		sb.append(deliverableCode);
		sb.append(", deliverableName=");
		sb.append(deliverableName);
		sb.append(", deliverableType=");
		sb.append(deliverableType);
		sb.append(", govAgencyCode=");
		sb.append(govAgencyCode);
		sb.append(", govAgencyName=");
		sb.append(govAgencyName);
		sb.append(", applicantIdNo=");
		sb.append(applicantIdNo);
		sb.append(", applicantName=");
		sb.append(applicantName);
		sb.append(", subject=");
		sb.append(subject);
		sb.append(", formData=");
		sb.append(formData);
		sb.append(", formScript=");
		sb.append(formScript);
		sb.append(", formScriptFileId=");
		sb.append(formScriptFileId);
		sb.append(", formReport=");
		sb.append(formReport);
		sb.append(", formReportFileId=");
		sb.append(formReportFileId);
		sb.append(", issueDate=");
		sb.append(issueDate);
		sb.append(", expireDate=");
		sb.append(expireDate);
		sb.append(", revalidate=");
		sb.append(revalidate);
		sb.append(", deliverableState=");
		sb.append(deliverableState);
		sb.append(", fileEntryId=");
		sb.append(fileEntryId);
		sb.append(", docSync=");
		sb.append(docSync);
		sb.append(", dossierId=");
		sb.append(dossierId);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public OpenCPSDeliverable toEntityModel() {
		OpenCPSDeliverableImpl openCPSDeliverableImpl = new OpenCPSDeliverableImpl();

		if (uuid == null) {
			openCPSDeliverableImpl.setUuid("");
		}
		else {
			openCPSDeliverableImpl.setUuid(uuid);
		}

		openCPSDeliverableImpl.setDeliverableId(deliverableId);
		openCPSDeliverableImpl.setGroupId(groupId);
		openCPSDeliverableImpl.setCompanyId(companyId);
		openCPSDeliverableImpl.setUserId(userId);

		if (userName == null) {
			openCPSDeliverableImpl.setUserName("");
		}
		else {
			openCPSDeliverableImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			openCPSDeliverableImpl.setCreateDate(null);
		}
		else {
			openCPSDeliverableImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			openCPSDeliverableImpl.setModifiedDate(null);
		}
		else {
			openCPSDeliverableImpl.setModifiedDate(new Date(modifiedDate));
		}

		if (deliverableCode == null) {
			openCPSDeliverableImpl.setDeliverableCode("");
		}
		else {
			openCPSDeliverableImpl.setDeliverableCode(deliverableCode);
		}

		if (deliverableName == null) {
			openCPSDeliverableImpl.setDeliverableName("");
		}
		else {
			openCPSDeliverableImpl.setDeliverableName(deliverableName);
		}

		if (deliverableType == null) {
			openCPSDeliverableImpl.setDeliverableType("");
		}
		else {
			openCPSDeliverableImpl.setDeliverableType(deliverableType);
		}

		if (govAgencyCode == null) {
			openCPSDeliverableImpl.setGovAgencyCode("");
		}
		else {
			openCPSDeliverableImpl.setGovAgencyCode(govAgencyCode);
		}

		if (govAgencyName == null) {
			openCPSDeliverableImpl.setGovAgencyName("");
		}
		else {
			openCPSDeliverableImpl.setGovAgencyName(govAgencyName);
		}

		if (applicantIdNo == null) {
			openCPSDeliverableImpl.setApplicantIdNo("");
		}
		else {
			openCPSDeliverableImpl.setApplicantIdNo(applicantIdNo);
		}

		if (applicantName == null) {
			openCPSDeliverableImpl.setApplicantName("");
		}
		else {
			openCPSDeliverableImpl.setApplicantName(applicantName);
		}

		if (subject == null) {
			openCPSDeliverableImpl.setSubject("");
		}
		else {
			openCPSDeliverableImpl.setSubject(subject);
		}

		if (formData == null) {
			openCPSDeliverableImpl.setFormData("");
		}
		else {
			openCPSDeliverableImpl.setFormData(formData);
		}

		if (formScript == null) {
			openCPSDeliverableImpl.setFormScript("");
		}
		else {
			openCPSDeliverableImpl.setFormScript(formScript);
		}

		openCPSDeliverableImpl.setFormScriptFileId(formScriptFileId);

		if (formReport == null) {
			openCPSDeliverableImpl.setFormReport("");
		}
		else {
			openCPSDeliverableImpl.setFormReport(formReport);
		}

		openCPSDeliverableImpl.setFormReportFileId(formReportFileId);

		if (issueDate == Long.MIN_VALUE) {
			openCPSDeliverableImpl.setIssueDate(null);
		}
		else {
			openCPSDeliverableImpl.setIssueDate(new Date(issueDate));
		}

		if (expireDate == Long.MIN_VALUE) {
			openCPSDeliverableImpl.setExpireDate(null);
		}
		else {
			openCPSDeliverableImpl.setExpireDate(new Date(expireDate));
		}

		if (revalidate == Long.MIN_VALUE) {
			openCPSDeliverableImpl.setRevalidate(null);
		}
		else {
			openCPSDeliverableImpl.setRevalidate(new Date(revalidate));
		}

		openCPSDeliverableImpl.setDeliverableState(deliverableState);
		openCPSDeliverableImpl.setFileEntryId(fileEntryId);
		openCPSDeliverableImpl.setDocSync(docSync);
		openCPSDeliverableImpl.setDossierId(dossierId);

		openCPSDeliverableImpl.resetOriginalValues();

		return openCPSDeliverableImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		uuid = objectInput.readUTF();

		deliverableId = objectInput.readLong();

		groupId = objectInput.readLong();

		companyId = objectInput.readLong();

		userId = objectInput.readLong();
		userName = objectInput.readUTF();
		createDate = objectInput.readLong();
		modifiedDate = objectInput.readLong();
		deliverableCode = objectInput.readUTF();
		deliverableName = objectInput.readUTF();
		deliverableType = objectInput.readUTF();
		govAgencyCode = objectInput.readUTF();
		govAgencyName = objectInput.readUTF();
		applicantIdNo = objectInput.readUTF();
		applicantName = objectInput.readUTF();
		subject = objectInput.readUTF();
		formData = objectInput.readUTF();
		formScript = objectInput.readUTF();

		formScriptFileId = objectInput.readLong();
		formReport = objectInput.readUTF();

		formReportFileId = objectInput.readLong();
		issueDate = objectInput.readLong();
		expireDate = objectInput.readLong();
		revalidate = objectInput.readLong();

		deliverableState = objectInput.readInt();

		fileEntryId = objectInput.readLong();

		docSync = objectInput.readInt();

		dossierId = objectInput.readLong();
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

		objectOutput.writeLong(deliverableId);

		objectOutput.writeLong(groupId);

		objectOutput.writeLong(companyId);

		objectOutput.writeLong(userId);

		if (userName == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(userName);
		}

		objectOutput.writeLong(createDate);
		objectOutput.writeLong(modifiedDate);

		if (deliverableCode == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(deliverableCode);
		}

		if (deliverableName == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(deliverableName);
		}

		if (deliverableType == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(deliverableType);
		}

		if (govAgencyCode == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(govAgencyCode);
		}

		if (govAgencyName == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(govAgencyName);
		}

		if (applicantIdNo == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(applicantIdNo);
		}

		if (applicantName == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(applicantName);
		}

		if (subject == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(subject);
		}

		if (formData == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(formData);
		}

		if (formScript == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(formScript);
		}

		objectOutput.writeLong(formScriptFileId);

		if (formReport == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(formReport);
		}

		objectOutput.writeLong(formReportFileId);
		objectOutput.writeLong(issueDate);
		objectOutput.writeLong(expireDate);
		objectOutput.writeLong(revalidate);

		objectOutput.writeInt(deliverableState);

		objectOutput.writeLong(fileEntryId);

		objectOutput.writeInt(docSync);

		objectOutput.writeLong(dossierId);
	}

	public String uuid;
	public long deliverableId;
	public long groupId;
	public long companyId;
	public long userId;
	public String userName;
	public long createDate;
	public long modifiedDate;
	public String deliverableCode;
	public String deliverableName;
	public String deliverableType;
	public String govAgencyCode;
	public String govAgencyName;
	public String applicantIdNo;
	public String applicantName;
	public String subject;
	public String formData;
	public String formScript;
	public long formScriptFileId;
	public String formReport;
	public long formReportFileId;
	public long issueDate;
	public long expireDate;
	public long revalidate;
	public int deliverableState;
	public long fileEntryId;
	public int docSync;
	public long dossierId;
}