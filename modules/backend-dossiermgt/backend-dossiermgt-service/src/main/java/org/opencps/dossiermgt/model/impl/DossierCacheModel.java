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

import org.opencps.dossiermgt.model.Dossier;

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
		StringBundler sb = new StringBundler(181);

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
		sb.append(", registerBookCode=");
		sb.append(registerBookCode);
		sb.append(", registerBookName=");
		sb.append(registerBookName);
		sb.append(", dossierRegister=");
		sb.append(dossierRegister);
		sb.append(", processNo=");
		sb.append(processNo);
		sb.append(", serviceCode=");
		sb.append(serviceCode);
		sb.append(", serviceName=");
		sb.append(serviceName);
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
		sb.append(", delegateName=");
		sb.append(delegateName);
		sb.append(", delegateIdNo=");
		sb.append(delegateIdNo);
		sb.append(", delegateTelNo=");
		sb.append(delegateTelNo);
		sb.append(", delegateEmail=");
		sb.append(delegateEmail);
		sb.append(", delegateAddress=");
		sb.append(delegateAddress);
		sb.append(", delegateCityCode=");
		sb.append(delegateCityCode);
		sb.append(", delegateCityName=");
		sb.append(delegateCityName);
		sb.append(", delegateDistrictCode=");
		sb.append(delegateDistrictCode);
		sb.append(", delegateDistrictName=");
		sb.append(delegateDistrictName);
		sb.append(", delegateWardCode=");
		sb.append(delegateWardCode);
		sb.append(", delegateWardName=");
		sb.append(delegateWardName);
		sb.append(", dossierTemplateNo=");
		sb.append(dossierTemplateNo);
		sb.append(", dossierTemplateName=");
		sb.append(dossierTemplateName);
		sb.append(", dossierNote=");
		sb.append(dossierNote);
		sb.append(", submissionNote=");
		sb.append(submissionNote);
		sb.append(", applicantNote=");
		sb.append(applicantNote);
		sb.append(", briefNote=");
		sb.append(briefNote);
		sb.append(", dossierNo=");
		sb.append(dossierNo);
		sb.append(", submitting=");
		sb.append(submitting);
		sb.append(", processDate=");
		sb.append(processDate);
		sb.append(", submitDate=");
		sb.append(submitDate);
		sb.append(", receiveDate=");
		sb.append(receiveDate);
		sb.append(", dueDate=");
		sb.append(dueDate);
		sb.append(", extendDate=");
		sb.append(extendDate);
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
		sb.append(", folderId=");
		sb.append(folderId);
		sb.append(", dossierActionId=");
		sb.append(dossierActionId);
		sb.append(", viaPostal=");
		sb.append(viaPostal);
		sb.append(", postalServiceCode=");
		sb.append(postalServiceCode);
		sb.append(", postalServiceName=");
		sb.append(postalServiceName);
		sb.append(", postalAddress=");
		sb.append(postalAddress);
		sb.append(", postalCityCode=");
		sb.append(postalCityCode);
		sb.append(", postalCityName=");
		sb.append(postalCityName);
		sb.append(", postalDistrictCode=");
		sb.append(postalDistrictCode);
		sb.append(", postalDistrictName=");
		sb.append(postalDistrictName);
		sb.append(", postalWardCode=");
		sb.append(postalWardCode);
		sb.append(", postalWardName=");
		sb.append(postalWardName);
		sb.append(", postalTelNo=");
		sb.append(postalTelNo);
		sb.append(", password=");
		sb.append(password);
		sb.append(", notification=");
		sb.append(notification);
		sb.append(", online=");
		sb.append(online);
		sb.append(", original=");
		sb.append(original);
		sb.append(", serverNo=");
		sb.append(serverNo);
		sb.append(", endorsementDate=");
		sb.append(endorsementDate);
		sb.append(", lockState=");
		sb.append(lockState);
		sb.append(", originality=");
		sb.append(originality);
		sb.append(", originDossierId=");
		sb.append(originDossierId);
		sb.append(", sampleCount=");
		sb.append(sampleCount);
		sb.append(", durationUnit=");
		sb.append(durationUnit);
		sb.append(", durationCount=");
		sb.append(durationCount);
		sb.append(", dossierName=");
		sb.append(dossierName);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public Dossier toEntityModel() {
		DossierImpl dossierImpl = new DossierImpl();

		if (uuid == null) {
			dossierImpl.setUuid("");
		}
		else {
			dossierImpl.setUuid(uuid);
		}

		dossierImpl.setDossierId(dossierId);
		dossierImpl.setGroupId(groupId);
		dossierImpl.setCompanyId(companyId);
		dossierImpl.setUserId(userId);

		if (userName == null) {
			dossierImpl.setUserName("");
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
			dossierImpl.setReferenceUid("");
		}
		else {
			dossierImpl.setReferenceUid(referenceUid);
		}

		dossierImpl.setCounter(counter);

		if (registerBookCode == null) {
			dossierImpl.setRegisterBookCode("");
		}
		else {
			dossierImpl.setRegisterBookCode(registerBookCode);
		}

		if (registerBookName == null) {
			dossierImpl.setRegisterBookName("");
		}
		else {
			dossierImpl.setRegisterBookName(registerBookName);
		}

		if (dossierRegister == null) {
			dossierImpl.setDossierRegister("");
		}
		else {
			dossierImpl.setDossierRegister(dossierRegister);
		}

		if (processNo == null) {
			dossierImpl.setProcessNo("");
		}
		else {
			dossierImpl.setProcessNo(processNo);
		}

		if (serviceCode == null) {
			dossierImpl.setServiceCode("");
		}
		else {
			dossierImpl.setServiceCode(serviceCode);
		}

		if (serviceName == null) {
			dossierImpl.setServiceName("");
		}
		else {
			dossierImpl.setServiceName(serviceName);
		}

		if (govAgencyCode == null) {
			dossierImpl.setGovAgencyCode("");
		}
		else {
			dossierImpl.setGovAgencyCode(govAgencyCode);
		}

		if (govAgencyName == null) {
			dossierImpl.setGovAgencyName("");
		}
		else {
			dossierImpl.setGovAgencyName(govAgencyName);
		}

		if (applicantName == null) {
			dossierImpl.setApplicantName("");
		}
		else {
			dossierImpl.setApplicantName(applicantName);
		}

		if (applicantIdType == null) {
			dossierImpl.setApplicantIdType("");
		}
		else {
			dossierImpl.setApplicantIdType(applicantIdType);
		}

		if (applicantIdNo == null) {
			dossierImpl.setApplicantIdNo("");
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
			dossierImpl.setAddress("");
		}
		else {
			dossierImpl.setAddress(address);
		}

		if (cityCode == null) {
			dossierImpl.setCityCode("");
		}
		else {
			dossierImpl.setCityCode(cityCode);
		}

		if (cityName == null) {
			dossierImpl.setCityName("");
		}
		else {
			dossierImpl.setCityName(cityName);
		}

		if (districtCode == null) {
			dossierImpl.setDistrictCode("");
		}
		else {
			dossierImpl.setDistrictCode(districtCode);
		}

		if (districtName == null) {
			dossierImpl.setDistrictName("");
		}
		else {
			dossierImpl.setDistrictName(districtName);
		}

		if (wardCode == null) {
			dossierImpl.setWardCode("");
		}
		else {
			dossierImpl.setWardCode(wardCode);
		}

		if (wardName == null) {
			dossierImpl.setWardName("");
		}
		else {
			dossierImpl.setWardName(wardName);
		}

		if (contactName == null) {
			dossierImpl.setContactName("");
		}
		else {
			dossierImpl.setContactName(contactName);
		}

		if (contactTelNo == null) {
			dossierImpl.setContactTelNo("");
		}
		else {
			dossierImpl.setContactTelNo(contactTelNo);
		}

		if (contactEmail == null) {
			dossierImpl.setContactEmail("");
		}
		else {
			dossierImpl.setContactEmail(contactEmail);
		}

		if (delegateName == null) {
			dossierImpl.setDelegateName("");
		}
		else {
			dossierImpl.setDelegateName(delegateName);
		}

		if (delegateIdNo == null) {
			dossierImpl.setDelegateIdNo("");
		}
		else {
			dossierImpl.setDelegateIdNo(delegateIdNo);
		}

		if (delegateTelNo == null) {
			dossierImpl.setDelegateTelNo("");
		}
		else {
			dossierImpl.setDelegateTelNo(delegateTelNo);
		}

		if (delegateEmail == null) {
			dossierImpl.setDelegateEmail("");
		}
		else {
			dossierImpl.setDelegateEmail(delegateEmail);
		}

		if (delegateAddress == null) {
			dossierImpl.setDelegateAddress("");
		}
		else {
			dossierImpl.setDelegateAddress(delegateAddress);
		}

		if (delegateCityCode == null) {
			dossierImpl.setDelegateCityCode("");
		}
		else {
			dossierImpl.setDelegateCityCode(delegateCityCode);
		}

		if (delegateCityName == null) {
			dossierImpl.setDelegateCityName("");
		}
		else {
			dossierImpl.setDelegateCityName(delegateCityName);
		}

		if (delegateDistrictCode == null) {
			dossierImpl.setDelegateDistrictCode("");
		}
		else {
			dossierImpl.setDelegateDistrictCode(delegateDistrictCode);
		}

		if (delegateDistrictName == null) {
			dossierImpl.setDelegateDistrictName("");
		}
		else {
			dossierImpl.setDelegateDistrictName(delegateDistrictName);
		}

		if (delegateWardCode == null) {
			dossierImpl.setDelegateWardCode("");
		}
		else {
			dossierImpl.setDelegateWardCode(delegateWardCode);
		}

		if (delegateWardName == null) {
			dossierImpl.setDelegateWardName("");
		}
		else {
			dossierImpl.setDelegateWardName(delegateWardName);
		}

		if (dossierTemplateNo == null) {
			dossierImpl.setDossierTemplateNo("");
		}
		else {
			dossierImpl.setDossierTemplateNo(dossierTemplateNo);
		}

		if (dossierTemplateName == null) {
			dossierImpl.setDossierTemplateName("");
		}
		else {
			dossierImpl.setDossierTemplateName(dossierTemplateName);
		}

		if (dossierNote == null) {
			dossierImpl.setDossierNote("");
		}
		else {
			dossierImpl.setDossierNote(dossierNote);
		}

		if (submissionNote == null) {
			dossierImpl.setSubmissionNote("");
		}
		else {
			dossierImpl.setSubmissionNote(submissionNote);
		}

		if (applicantNote == null) {
			dossierImpl.setApplicantNote("");
		}
		else {
			dossierImpl.setApplicantNote(applicantNote);
		}

		if (briefNote == null) {
			dossierImpl.setBriefNote("");
		}
		else {
			dossierImpl.setBriefNote(briefNote);
		}

		if (dossierNo == null) {
			dossierImpl.setDossierNo("");
		}
		else {
			dossierImpl.setDossierNo(dossierNo);
		}

		dossierImpl.setSubmitting(submitting);

		if (processDate == Long.MIN_VALUE) {
			dossierImpl.setProcessDate(null);
		}
		else {
			dossierImpl.setProcessDate(new Date(processDate));
		}

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

		if (extendDate == Long.MIN_VALUE) {
			dossierImpl.setExtendDate(null);
		}
		else {
			dossierImpl.setExtendDate(new Date(extendDate));
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
			dossierImpl.setDossierStatus("");
		}
		else {
			dossierImpl.setDossierStatus(dossierStatus);
		}

		if (dossierStatusText == null) {
			dossierImpl.setDossierStatusText("");
		}
		else {
			dossierImpl.setDossierStatusText(dossierStatusText);
		}

		if (dossierSubStatus == null) {
			dossierImpl.setDossierSubStatus("");
		}
		else {
			dossierImpl.setDossierSubStatus(dossierSubStatus);
		}

		if (dossierSubStatusText == null) {
			dossierImpl.setDossierSubStatusText("");
		}
		else {
			dossierImpl.setDossierSubStatusText(dossierSubStatusText);
		}

		dossierImpl.setFolderId(folderId);
		dossierImpl.setDossierActionId(dossierActionId);
		dossierImpl.setViaPostal(viaPostal);

		if (postalServiceCode == null) {
			dossierImpl.setPostalServiceCode("");
		}
		else {
			dossierImpl.setPostalServiceCode(postalServiceCode);
		}

		if (postalServiceName == null) {
			dossierImpl.setPostalServiceName("");
		}
		else {
			dossierImpl.setPostalServiceName(postalServiceName);
		}

		if (postalAddress == null) {
			dossierImpl.setPostalAddress("");
		}
		else {
			dossierImpl.setPostalAddress(postalAddress);
		}

		if (postalCityCode == null) {
			dossierImpl.setPostalCityCode("");
		}
		else {
			dossierImpl.setPostalCityCode(postalCityCode);
		}

		if (postalCityName == null) {
			dossierImpl.setPostalCityName("");
		}
		else {
			dossierImpl.setPostalCityName(postalCityName);
		}

		if (postalDistrictCode == null) {
			dossierImpl.setPostalDistrictCode("");
		}
		else {
			dossierImpl.setPostalDistrictCode(postalDistrictCode);
		}

		if (postalDistrictName == null) {
			dossierImpl.setPostalDistrictName("");
		}
		else {
			dossierImpl.setPostalDistrictName(postalDistrictName);
		}

		if (postalWardCode == null) {
			dossierImpl.setPostalWardCode("");
		}
		else {
			dossierImpl.setPostalWardCode(postalWardCode);
		}

		if (postalWardName == null) {
			dossierImpl.setPostalWardName("");
		}
		else {
			dossierImpl.setPostalWardName(postalWardName);
		}

		if (postalTelNo == null) {
			dossierImpl.setPostalTelNo("");
		}
		else {
			dossierImpl.setPostalTelNo(postalTelNo);
		}

		if (password == null) {
			dossierImpl.setPassword("");
		}
		else {
			dossierImpl.setPassword(password);
		}

		dossierImpl.setNotification(notification);
		dossierImpl.setOnline(online);
		dossierImpl.setOriginal(original);

		if (serverNo == null) {
			dossierImpl.setServerNo("");
		}
		else {
			dossierImpl.setServerNo(serverNo);
		}

		if (endorsementDate == Long.MIN_VALUE) {
			dossierImpl.setEndorsementDate(null);
		}
		else {
			dossierImpl.setEndorsementDate(new Date(endorsementDate));
		}

		if (lockState == null) {
			dossierImpl.setLockState("");
		}
		else {
			dossierImpl.setLockState(lockState);
		}

		dossierImpl.setOriginality(originality);
		dossierImpl.setOriginDossierId(originDossierId);
		dossierImpl.setSampleCount(sampleCount);
		dossierImpl.setDurationUnit(durationUnit);
		dossierImpl.setDurationCount(durationCount);

		if (dossierName == null) {
			dossierImpl.setDossierName("");
		}
		else {
			dossierImpl.setDossierName(dossierName);
		}

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
		registerBookCode = objectInput.readUTF();
		registerBookName = objectInput.readUTF();
		dossierRegister = objectInput.readUTF();
		processNo = objectInput.readUTF();
		serviceCode = objectInput.readUTF();
		serviceName = objectInput.readUTF();
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
		delegateName = objectInput.readUTF();
		delegateIdNo = objectInput.readUTF();
		delegateTelNo = objectInput.readUTF();
		delegateEmail = objectInput.readUTF();
		delegateAddress = objectInput.readUTF();
		delegateCityCode = objectInput.readUTF();
		delegateCityName = objectInput.readUTF();
		delegateDistrictCode = objectInput.readUTF();
		delegateDistrictName = objectInput.readUTF();
		delegateWardCode = objectInput.readUTF();
		delegateWardName = objectInput.readUTF();
		dossierTemplateNo = objectInput.readUTF();
		dossierTemplateName = objectInput.readUTF();
		dossierNote = objectInput.readUTF();
		submissionNote = objectInput.readUTF();
		applicantNote = objectInput.readUTF();
		briefNote = objectInput.readUTF();
		dossierNo = objectInput.readUTF();

		submitting = objectInput.readBoolean();
		processDate = objectInput.readLong();
		submitDate = objectInput.readLong();
		receiveDate = objectInput.readLong();
		dueDate = objectInput.readLong();
		extendDate = objectInput.readLong();
		releaseDate = objectInput.readLong();
		finishDate = objectInput.readLong();
		cancellingDate = objectInput.readLong();
		correcttingDate = objectInput.readLong();
		dossierStatus = objectInput.readUTF();
		dossierStatusText = objectInput.readUTF();
		dossierSubStatus = objectInput.readUTF();
		dossierSubStatusText = objectInput.readUTF();

		folderId = objectInput.readLong();

		dossierActionId = objectInput.readLong();

		viaPostal = objectInput.readInt();
		postalServiceCode = objectInput.readUTF();
		postalServiceName = objectInput.readUTF();
		postalAddress = objectInput.readUTF();
		postalCityCode = objectInput.readUTF();
		postalCityName = objectInput.readUTF();
		postalDistrictCode = objectInput.readUTF();
		postalDistrictName = objectInput.readUTF();
		postalWardCode = objectInput.readUTF();
		postalWardName = objectInput.readUTF();
		postalTelNo = objectInput.readUTF();
		password = objectInput.readUTF();

		notification = objectInput.readBoolean();

		online = objectInput.readBoolean();

		original = objectInput.readBoolean();
		serverNo = objectInput.readUTF();
		endorsementDate = objectInput.readLong();
		lockState = objectInput.readUTF();

		originality = objectInput.readInt();

		originDossierId = objectInput.readLong();

		sampleCount = objectInput.readLong();

		durationUnit = objectInput.readInt();

		durationCount = objectInput.readDouble();
		dossierName = objectInput.readUTF();
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

		objectOutput.writeLong(dossierId);

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

		if (referenceUid == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(referenceUid);
		}

		objectOutput.writeInt(counter);

		if (registerBookCode == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(registerBookCode);
		}

		if (registerBookName == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(registerBookName);
		}

		if (dossierRegister == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(dossierRegister);
		}

		if (processNo == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(processNo);
		}

		if (serviceCode == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(serviceCode);
		}

		if (serviceName == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(serviceName);
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

		if (applicantName == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(applicantName);
		}

		if (applicantIdType == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(applicantIdType);
		}

		if (applicantIdNo == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(applicantIdNo);
		}

		objectOutput.writeLong(applicantIdDate);

		if (address == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(address);
		}

		if (cityCode == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(cityCode);
		}

		if (cityName == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(cityName);
		}

		if (districtCode == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(districtCode);
		}

		if (districtName == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(districtName);
		}

		if (wardCode == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(wardCode);
		}

		if (wardName == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(wardName);
		}

		if (contactName == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(contactName);
		}

		if (contactTelNo == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(contactTelNo);
		}

		if (contactEmail == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(contactEmail);
		}

		if (delegateName == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(delegateName);
		}

		if (delegateIdNo == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(delegateIdNo);
		}

		if (delegateTelNo == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(delegateTelNo);
		}

		if (delegateEmail == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(delegateEmail);
		}

		if (delegateAddress == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(delegateAddress);
		}

		if (delegateCityCode == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(delegateCityCode);
		}

		if (delegateCityName == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(delegateCityName);
		}

		if (delegateDistrictCode == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(delegateDistrictCode);
		}

		if (delegateDistrictName == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(delegateDistrictName);
		}

		if (delegateWardCode == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(delegateWardCode);
		}

		if (delegateWardName == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(delegateWardName);
		}

		if (dossierTemplateNo == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(dossierTemplateNo);
		}

		if (dossierTemplateName == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(dossierTemplateName);
		}

		if (dossierNote == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(dossierNote);
		}

		if (submissionNote == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(submissionNote);
		}

		if (applicantNote == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(applicantNote);
		}

		if (briefNote == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(briefNote);
		}

		if (dossierNo == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(dossierNo);
		}

		objectOutput.writeBoolean(submitting);
		objectOutput.writeLong(processDate);
		objectOutput.writeLong(submitDate);
		objectOutput.writeLong(receiveDate);
		objectOutput.writeLong(dueDate);
		objectOutput.writeLong(extendDate);
		objectOutput.writeLong(releaseDate);
		objectOutput.writeLong(finishDate);
		objectOutput.writeLong(cancellingDate);
		objectOutput.writeLong(correcttingDate);

		if (dossierStatus == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(dossierStatus);
		}

		if (dossierStatusText == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(dossierStatusText);
		}

		if (dossierSubStatus == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(dossierSubStatus);
		}

		if (dossierSubStatusText == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(dossierSubStatusText);
		}

		objectOutput.writeLong(folderId);

		objectOutput.writeLong(dossierActionId);

		objectOutput.writeInt(viaPostal);

		if (postalServiceCode == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(postalServiceCode);
		}

		if (postalServiceName == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(postalServiceName);
		}

		if (postalAddress == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(postalAddress);
		}

		if (postalCityCode == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(postalCityCode);
		}

		if (postalCityName == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(postalCityName);
		}

		if (postalDistrictCode == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(postalDistrictCode);
		}

		if (postalDistrictName == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(postalDistrictName);
		}

		if (postalWardCode == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(postalWardCode);
		}

		if (postalWardName == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(postalWardName);
		}

		if (postalTelNo == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(postalTelNo);
		}

		if (password == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(password);
		}

		objectOutput.writeBoolean(notification);

		objectOutput.writeBoolean(online);

		objectOutput.writeBoolean(original);

		if (serverNo == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(serverNo);
		}

		objectOutput.writeLong(endorsementDate);

		if (lockState == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(lockState);
		}

		objectOutput.writeInt(originality);

		objectOutput.writeLong(originDossierId);

		objectOutput.writeLong(sampleCount);

		objectOutput.writeInt(durationUnit);

		objectOutput.writeDouble(durationCount);

		if (dossierName == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(dossierName);
		}
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
	public String registerBookCode;
	public String registerBookName;
	public String dossierRegister;
	public String processNo;
	public String serviceCode;
	public String serviceName;
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
	public String delegateName;
	public String delegateIdNo;
	public String delegateTelNo;
	public String delegateEmail;
	public String delegateAddress;
	public String delegateCityCode;
	public String delegateCityName;
	public String delegateDistrictCode;
	public String delegateDistrictName;
	public String delegateWardCode;
	public String delegateWardName;
	public String dossierTemplateNo;
	public String dossierTemplateName;
	public String dossierNote;
	public String submissionNote;
	public String applicantNote;
	public String briefNote;
	public String dossierNo;
	public boolean submitting;
	public long processDate;
	public long submitDate;
	public long receiveDate;
	public long dueDate;
	public long extendDate;
	public long releaseDate;
	public long finishDate;
	public long cancellingDate;
	public long correcttingDate;
	public String dossierStatus;
	public String dossierStatusText;
	public String dossierSubStatus;
	public String dossierSubStatusText;
	public long folderId;
	public long dossierActionId;
	public int viaPostal;
	public String postalServiceCode;
	public String postalServiceName;
	public String postalAddress;
	public String postalCityCode;
	public String postalCityName;
	public String postalDistrictCode;
	public String postalDistrictName;
	public String postalWardCode;
	public String postalWardName;
	public String postalTelNo;
	public String password;
	public boolean notification;
	public boolean online;
	public boolean original;
	public String serverNo;
	public long endorsementDate;
	public String lockState;
	public int originality;
	public long originDossierId;
	public long sampleCount;
	public int durationUnit;
	public double durationCount;
	public String dossierName;
}