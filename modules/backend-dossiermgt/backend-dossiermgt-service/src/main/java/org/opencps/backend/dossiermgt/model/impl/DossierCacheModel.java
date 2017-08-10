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

package org.opencps.backend.dossiermgt.model.impl;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.util.HashUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;

import org.opencps.backend.dossiermgt.model.Dossier;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing Dossier in entity cache.
 *
 * @author huymq
 * @see Dossier
 * @generated
 */
@ProviderType
public class DossierCacheModel implements CacheModel<Dossier>, Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof DossierCacheModel)) {
			return false;
		}

		DossierCacheModel dossierCacheModel = (DossierCacheModel)obj;

		if (dossierId == dossierCacheModel.dossierId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, dossierId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(97);

		sb.append("{uuid=");
		sb.append(uuid);
		sb.append(", dossierId=");
		sb.append(dossierId);
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
		sb.append(", referenceUid=");
		sb.append(referenceUid);
		sb.append(", counter=");
		sb.append(counter);
		sb.append(", serviceCode=");
		sb.append(serviceCode);
		sb.append(", serviceName=");
		sb.append(serviceName);
		sb.append(", optionCode=");
		sb.append(optionCode);
		sb.append(", optionName=");
		sb.append(optionName);
		sb.append(", govAgencyCode=");
		sb.append(govAgencyCode);
		sb.append(", govAgencyName=");
		sb.append(govAgencyName);
		sb.append(", applicantName=");
		sb.append(applicantName);
		sb.append(", applicantIdType=");
		sb.append(applicantIdType);
		sb.append(", applicantIdNo=");
		sb.append(applicantIdNo);
		sb.append(", applicantIdDate=");
		sb.append(applicantIdDate);
		sb.append(", address=");
		sb.append(address);
		sb.append(", cityCode=");
		sb.append(cityCode);
		sb.append(", cityName=");
		sb.append(cityName);
		sb.append(", districtCode=");
		sb.append(districtCode);
		sb.append(", districtName=");
		sb.append(districtName);
		sb.append(", wardCode=");
		sb.append(wardCode);
		sb.append(", wardName=");
		sb.append(wardName);
		sb.append(", contactName=");
		sb.append(contactName);
		sb.append(", contactTelNo=");
		sb.append(contactTelNo);
		sb.append(", contactEmail=");
		sb.append(contactEmail);
		sb.append(", dossierTemplateNo=");
		sb.append(dossierTemplateNo);
		sb.append(", dossierNote=");
		sb.append(dossierNote);
		sb.append(", dossierNo=");
		sb.append(dossierNo);
		sb.append(", submitting=");
		sb.append(submitting);
		sb.append(", submitDate=");
		sb.append(submitDate);
		sb.append(", receiveDate=");
		sb.append(receiveDate);
		sb.append(", dueDate=");
		sb.append(dueDate);
		sb.append(", releaseDate=");
		sb.append(releaseDate);
		sb.append(", finishDate=");
		sb.append(finishDate);
		sb.append(", cancellingDate=");
		sb.append(cancellingDate);
		sb.append(", correcttingDate=");
		sb.append(correcttingDate);
		sb.append(", dossierStatus=");
		sb.append(dossierStatus);
		sb.append(", dossierStatusText=");
		sb.append(dossierStatusText);
		sb.append(", dossierSubStatus=");
		sb.append(dossierSubStatus);
		sb.append(", dossierSubStatusText=");
		sb.append(dossierSubStatusText);
		sb.append(", paymentRedirectURL=");
		sb.append(paymentRedirectURL);
		sb.append(", overDue=");
		sb.append(overDue);
		sb.append(", folderId=");
		sb.append(folderId);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public Dossier toEntityModel() {
		DossierImpl dossierImpl = new DossierImpl();

		if (uuid == null) {
			dossierImpl.setUuid(StringPool.BLANK);
		}
		else {
			dossierImpl.setUuid(uuid);
		}

		dossierImpl.setDossierId(dossierId);
		dossierImpl.setGroupId(groupId);
		dossierImpl.setCompanyId(companyId);
		dossierImpl.setUserId(userId);

		if (userName == null) {
			dossierImpl.setUserName(StringPool.BLANK);
		}
		else {
			dossierImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			dossierImpl.setCreateDate(null);
		}
		else {
			dossierImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			dossierImpl.setModifiedDate(null);
		}
		else {
			dossierImpl.setModifiedDate(new Date(modifiedDate));
		}

		if (referenceUid == null) {
			dossierImpl.setReferenceUid(StringPool.BLANK);
		}
		else {
			dossierImpl.setReferenceUid(referenceUid);
		}

		dossierImpl.setCounter(counter);

		if (serviceCode == null) {
			dossierImpl.setServiceCode(StringPool.BLANK);
		}
		else {
			dossierImpl.setServiceCode(serviceCode);
		}

		if (serviceName == null) {
			dossierImpl.setServiceName(StringPool.BLANK);
		}
		else {
			dossierImpl.setServiceName(serviceName);
		}

		if (optionCode == null) {
			dossierImpl.setOptionCode(StringPool.BLANK);
		}
		else {
			dossierImpl.setOptionCode(optionCode);
		}

		if (optionName == null) {
			dossierImpl.setOptionName(StringPool.BLANK);
		}
		else {
			dossierImpl.setOptionName(optionName);
		}

		if (govAgencyCode == null) {
			dossierImpl.setGovAgencyCode(StringPool.BLANK);
		}
		else {
			dossierImpl.setGovAgencyCode(govAgencyCode);
		}

		if (govAgencyName == null) {
			dossierImpl.setGovAgencyName(StringPool.BLANK);
		}
		else {
			dossierImpl.setGovAgencyName(govAgencyName);
		}

		if (applicantName == null) {
			dossierImpl.setApplicantName(StringPool.BLANK);
		}
		else {
			dossierImpl.setApplicantName(applicantName);
		}

		if (applicantIdType == null) {
			dossierImpl.setApplicantIdType(StringPool.BLANK);
		}
		else {
			dossierImpl.setApplicantIdType(applicantIdType);
		}

		if (applicantIdNo == null) {
			dossierImpl.setApplicantIdNo(StringPool.BLANK);
		}
		else {
			dossierImpl.setApplicantIdNo(applicantIdNo);
		}

		if (applicantIdDate == Long.MIN_VALUE) {
			dossierImpl.setApplicantIdDate(null);
		}
		else {
			dossierImpl.setApplicantIdDate(new Date(applicantIdDate));
		}

		if (address == null) {
			dossierImpl.setAddress(StringPool.BLANK);
		}
		else {
			dossierImpl.setAddress(address);
		}

		if (cityCode == null) {
			dossierImpl.setCityCode(StringPool.BLANK);
		}
		else {
			dossierImpl.setCityCode(cityCode);
		}

		if (cityName == null) {
			dossierImpl.setCityName(StringPool.BLANK);
		}
		else {
			dossierImpl.setCityName(cityName);
		}

		if (districtCode == null) {
			dossierImpl.setDistrictCode(StringPool.BLANK);
		}
		else {
			dossierImpl.setDistrictCode(districtCode);
		}

		if (districtName == null) {
			dossierImpl.setDistrictName(StringPool.BLANK);
		}
		else {
			dossierImpl.setDistrictName(districtName);
		}

		if (wardCode == null) {
			dossierImpl.setWardCode(StringPool.BLANK);
		}
		else {
			dossierImpl.setWardCode(wardCode);
		}

		if (wardName == null) {
			dossierImpl.setWardName(StringPool.BLANK);
		}
		else {
			dossierImpl.setWardName(wardName);
		}

		if (contactName == null) {
			dossierImpl.setContactName(StringPool.BLANK);
		}
		else {
			dossierImpl.setContactName(contactName);
		}

		if (contactTelNo == null) {
			dossierImpl.setContactTelNo(StringPool.BLANK);
		}
		else {
			dossierImpl.setContactTelNo(contactTelNo);
		}

		if (contactEmail == null) {
			dossierImpl.setContactEmail(StringPool.BLANK);
		}
		else {
			dossierImpl.setContactEmail(contactEmail);
		}

		if (dossierTemplateNo == null) {
			dossierImpl.setDossierTemplateNo(StringPool.BLANK);
		}
		else {
			dossierImpl.setDossierTemplateNo(dossierTemplateNo);
		}

		if (dossierNote == null) {
			dossierImpl.setDossierNote(StringPool.BLANK);
		}
		else {
			dossierImpl.setDossierNote(dossierNote);
		}

		if (dossierNo == null) {
			dossierImpl.setDossierNo(StringPool.BLANK);
		}
		else {
			dossierImpl.setDossierNo(dossierNo);
		}

		dossierImpl.setSubmitting(submitting);

		if (submitDate == Long.MIN_VALUE) {
			dossierImpl.setSubmitDate(null);
		}
		else {
			dossierImpl.setSubmitDate(new Date(submitDate));
		}

		if (receiveDate == Long.MIN_VALUE) {
			dossierImpl.setReceiveDate(null);
		}
		else {
			dossierImpl.setReceiveDate(new Date(receiveDate));
		}

		if (dueDate == Long.MIN_VALUE) {
			dossierImpl.setDueDate(null);
		}
		else {
			dossierImpl.setDueDate(new Date(dueDate));
		}

		if (releaseDate == Long.MIN_VALUE) {
			dossierImpl.setReleaseDate(null);
		}
		else {
			dossierImpl.setReleaseDate(new Date(releaseDate));
		}

		if (finishDate == Long.MIN_VALUE) {
			dossierImpl.setFinishDate(null);
		}
		else {
			dossierImpl.setFinishDate(new Date(finishDate));
		}

		if (cancellingDate == Long.MIN_VALUE) {
			dossierImpl.setCancellingDate(null);
		}
		else {
			dossierImpl.setCancellingDate(new Date(cancellingDate));
		}

		if (correcttingDate == Long.MIN_VALUE) {
			dossierImpl.setCorrecttingDate(null);
		}
		else {
			dossierImpl.setCorrecttingDate(new Date(correcttingDate));
		}

		if (dossierStatus == null) {
			dossierImpl.setDossierStatus(StringPool.BLANK);
		}
		else {
			dossierImpl.setDossierStatus(dossierStatus);
		}

		if (dossierStatusText == null) {
			dossierImpl.setDossierStatusText(StringPool.BLANK);
		}
		else {
			dossierImpl.setDossierStatusText(dossierStatusText);
		}

		if (dossierSubStatus == null) {
			dossierImpl.setDossierSubStatus(StringPool.BLANK);
		}
		else {
			dossierImpl.setDossierSubStatus(dossierSubStatus);
		}

		if (dossierSubStatusText == null) {
			dossierImpl.setDossierSubStatusText(StringPool.BLANK);
		}
		else {
			dossierImpl.setDossierSubStatusText(dossierSubStatusText);
		}

		if (paymentRedirectURL == null) {
			dossierImpl.setPaymentRedirectURL(StringPool.BLANK);
		}
		else {
			dossierImpl.setPaymentRedirectURL(paymentRedirectURL);
		}

		if (overDue == null) {
			dossierImpl.setOverDue(StringPool.BLANK);
		}
		else {
			dossierImpl.setOverDue(overDue);
		}

		dossierImpl.setFolderId(folderId);

		dossierImpl.resetOriginalValues();

		return dossierImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		uuid = objectInput.readUTF();

		dossierId = objectInput.readLong();

		groupId = objectInput.readLong();

		companyId = objectInput.readLong();

		userId = objectInput.readLong();
		userName = objectInput.readUTF();
		createDate = objectInput.readLong();
		modifiedDate = objectInput.readLong();
		referenceUid = objectInput.readUTF();

		counter = objectInput.readInt();
		serviceCode = objectInput.readUTF();
		serviceName = objectInput.readUTF();
		optionCode = objectInput.readUTF();
		optionName = objectInput.readUTF();
		govAgencyCode = objectInput.readUTF();
		govAgencyName = objectInput.readUTF();
		applicantName = objectInput.readUTF();
		applicantIdType = objectInput.readUTF();
		applicantIdNo = objectInput.readUTF();
		applicantIdDate = objectInput.readLong();
		address = objectInput.readUTF();
		cityCode = objectInput.readUTF();
		cityName = objectInput.readUTF();
		districtCode = objectInput.readUTF();
		districtName = objectInput.readUTF();
		wardCode = objectInput.readUTF();
		wardName = objectInput.readUTF();
		contactName = objectInput.readUTF();
		contactTelNo = objectInput.readUTF();
		contactEmail = objectInput.readUTF();
		dossierTemplateNo = objectInput.readUTF();
		dossierNote = objectInput.readUTF();
		dossierNo = objectInput.readUTF();

		submitting = objectInput.readBoolean();
		submitDate = objectInput.readLong();
		receiveDate = objectInput.readLong();
		dueDate = objectInput.readLong();
		releaseDate = objectInput.readLong();
		finishDate = objectInput.readLong();
		cancellingDate = objectInput.readLong();
		correcttingDate = objectInput.readLong();
		dossierStatus = objectInput.readUTF();
		dossierStatusText = objectInput.readUTF();
		dossierSubStatus = objectInput.readUTF();
		dossierSubStatusText = objectInput.readUTF();
		paymentRedirectURL = objectInput.readUTF();
		overDue = objectInput.readUTF();

		folderId = objectInput.readLong();
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

		objectOutput.writeLong(dossierId);

		objectOutput.writeLong(groupId);

		objectOutput.writeLong(companyId);

		objectOutput.writeLong(userId);

		if (userName == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(userName);
		}

		objectOutput.writeLong(createDate);
		objectOutput.writeLong(modifiedDate);

		if (referenceUid == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(referenceUid);
		}

		objectOutput.writeInt(counter);

		if (serviceCode == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(serviceCode);
		}

		if (serviceName == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(serviceName);
		}

		if (optionCode == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(optionCode);
		}

		if (optionName == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(optionName);
		}

		if (govAgencyCode == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(govAgencyCode);
		}

		if (govAgencyName == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(govAgencyName);
		}

		if (applicantName == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(applicantName);
		}

		if (applicantIdType == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(applicantIdType);
		}

		if (applicantIdNo == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(applicantIdNo);
		}

		objectOutput.writeLong(applicantIdDate);

		if (address == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(address);
		}

		if (cityCode == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(cityCode);
		}

		if (cityName == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(cityName);
		}

		if (districtCode == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(districtCode);
		}

		if (districtName == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(districtName);
		}

		if (wardCode == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(wardCode);
		}

		if (wardName == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(wardName);
		}

		if (contactName == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(contactName);
		}

		if (contactTelNo == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(contactTelNo);
		}

		if (contactEmail == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(contactEmail);
		}

		if (dossierTemplateNo == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(dossierTemplateNo);
		}

		if (dossierNote == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(dossierNote);
		}

		if (dossierNo == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(dossierNo);
		}

		objectOutput.writeBoolean(submitting);
		objectOutput.writeLong(submitDate);
		objectOutput.writeLong(receiveDate);
		objectOutput.writeLong(dueDate);
		objectOutput.writeLong(releaseDate);
		objectOutput.writeLong(finishDate);
		objectOutput.writeLong(cancellingDate);
		objectOutput.writeLong(correcttingDate);

		if (dossierStatus == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(dossierStatus);
		}

		if (dossierStatusText == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(dossierStatusText);
		}

		if (dossierSubStatus == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(dossierSubStatus);
		}

		if (dossierSubStatusText == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(dossierSubStatusText);
		}

		if (paymentRedirectURL == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(paymentRedirectURL);
		}

		if (overDue == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(overDue);
		}

		objectOutput.writeLong(folderId);
	}

	public String uuid;
	public long dossierId;
	public long groupId;
	public long companyId;
	public long userId;
	public String userName;
	public long createDate;
	public long modifiedDate;
	public String referenceUid;
	public int counter;
	public String serviceCode;
	public String serviceName;
	public String optionCode;
	public String optionName;
	public String govAgencyCode;
	public String govAgencyName;
	public String applicantName;
	public String applicantIdType;
	public String applicantIdNo;
	public long applicantIdDate;
	public String address;
	public String cityCode;
	public String cityName;
	public String districtCode;
	public String districtName;
	public String wardCode;
	public String wardName;
	public String contactName;
	public String contactTelNo;
	public String contactEmail;
	public String dossierTemplateNo;
	public String dossierNote;
	public String dossierNo;
	public boolean submitting;
	public long submitDate;
	public long receiveDate;
	public long dueDate;
	public long releaseDate;
	public long finishDate;
	public long cancellingDate;
	public long correcttingDate;
	public String dossierStatus;
	public String dossierStatusText;
	public String dossierSubStatus;
	public String dossierSubStatusText;
	public String paymentRedirectURL;
	public String overDue;
	public long folderId;
}