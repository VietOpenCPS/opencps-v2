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

import org.opencps.dossiermgt.model.Deliverable;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing Deliverable in entity cache.
 *
 * @author huymq
 * @see Deliverable
 * @generated
 */
@ProviderType
public class DeliverableCacheModel implements CacheModel<Deliverable>,
	Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof DeliverableCacheModel)) {
			return false;
		}

		DeliverableCacheModel deliverableCacheModel = (DeliverableCacheModel)obj;

		if (deliverableId == deliverableCacheModel.deliverableId) {
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
		StringBundler sb = new StringBundler(47);

		sb.append("{uuid=");
		sb.append(uuid);
		sb.append(", deliverableId=");
		sb.append(deliverableId);
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
		sb.append(", formReport=");
		sb.append(formReport);
		sb.append(", expireDate=");
		sb.append(expireDate);
		sb.append(", issueDate=");
		sb.append(issueDate);
		sb.append(", revalidate=");
		sb.append(revalidate);
		sb.append(", deliverableState=");
		sb.append(deliverableState);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public Deliverable toEntityModel() {
		DeliverableImpl deliverableImpl = new DeliverableImpl();

		if (uuid == null) {
			deliverableImpl.setUuid("");
		}
		else {
			deliverableImpl.setUuid(uuid);
		}

		deliverableImpl.setDeliverableId(deliverableId);
		deliverableImpl.setCompanyId(companyId);
		deliverableImpl.setGroupId(groupId);
		deliverableImpl.setUserId(userId);

		if (userName == null) {
			deliverableImpl.setUserName("");
		}
		else {
			deliverableImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			deliverableImpl.setCreateDate(null);
		}
		else {
			deliverableImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			deliverableImpl.setModifiedDate(null);
		}
		else {
			deliverableImpl.setModifiedDate(new Date(modifiedDate));
		}

		if (deliverableCode == null) {
			deliverableImpl.setDeliverableCode("");
		}
		else {
			deliverableImpl.setDeliverableCode(deliverableCode);
		}

		if (deliverableName == null) {
			deliverableImpl.setDeliverableName("");
		}
		else {
			deliverableImpl.setDeliverableName(deliverableName);
		}

		if (deliverableType == null) {
			deliverableImpl.setDeliverableType("");
		}
		else {
			deliverableImpl.setDeliverableType(deliverableType);
		}

		if (govAgencyCode == null) {
			deliverableImpl.setGovAgencyCode("");
		}
		else {
			deliverableImpl.setGovAgencyCode(govAgencyCode);
		}

		if (govAgencyName == null) {
			deliverableImpl.setGovAgencyName("");
		}
		else {
			deliverableImpl.setGovAgencyName(govAgencyName);
		}

		if (applicantIdNo == null) {
			deliverableImpl.setApplicantIdNo("");
		}
		else {
			deliverableImpl.setApplicantIdNo(applicantIdNo);
		}

		if (applicantName == null) {
			deliverableImpl.setApplicantName("");
		}
		else {
			deliverableImpl.setApplicantName(applicantName);
		}

		if (subject == null) {
			deliverableImpl.setSubject("");
		}
		else {
			deliverableImpl.setSubject(subject);
		}

		if (formData == null) {
			deliverableImpl.setFormData("");
		}
		else {
			deliverableImpl.setFormData(formData);
		}

		if (formScript == null) {
			deliverableImpl.setFormScript("");
		}
		else {
			deliverableImpl.setFormScript(formScript);
		}

		if (formReport == null) {
			deliverableImpl.setFormReport("");
		}
		else {
			deliverableImpl.setFormReport(formReport);
		}

		if (expireDate == Long.MIN_VALUE) {
			deliverableImpl.setExpireDate(null);
		}
		else {
			deliverableImpl.setExpireDate(new Date(expireDate));
		}

		if (issueDate == Long.MIN_VALUE) {
			deliverableImpl.setIssueDate(null);
		}
		else {
			deliverableImpl.setIssueDate(new Date(issueDate));
		}

		if (revalidate == Long.MIN_VALUE) {
			deliverableImpl.setRevalidate(null);
		}
		else {
			deliverableImpl.setRevalidate(new Date(revalidate));
		}

		if (deliverableState == null) {
			deliverableImpl.setDeliverableState("");
		}
		else {
			deliverableImpl.setDeliverableState(deliverableState);
		}

		deliverableImpl.resetOriginalValues();

		return deliverableImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		uuid = objectInput.readUTF();

		deliverableId = objectInput.readLong();

		companyId = objectInput.readLong();

		groupId = objectInput.readLong();

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
		formReport = objectInput.readUTF();
		expireDate = objectInput.readLong();
		issueDate = objectInput.readLong();
		revalidate = objectInput.readLong();
		deliverableState = objectInput.readUTF();
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

		if (formReport == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(formReport);
		}

		objectOutput.writeLong(expireDate);
		objectOutput.writeLong(issueDate);
		objectOutput.writeLong(revalidate);

		if (deliverableState == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(deliverableState);
		}
	}

	public String uuid;
	public long deliverableId;
	public long companyId;
	public long groupId;
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
	public String formReport;
	public long expireDate;
	public long issueDate;
	public long revalidate;
	public String deliverableState;
}