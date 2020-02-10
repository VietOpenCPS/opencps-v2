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

import org.opencps.dossiermgt.model.Notarization;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing Notarization in entity cache.
 *
 * @author huymq
 * @see Notarization
 * @generated
 */
@ProviderType
public class NotarizationCacheModel implements CacheModel<Notarization>,
	Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof NotarizationCacheModel)) {
			return false;
		}

		NotarizationCacheModel notarizationCacheModel = (NotarizationCacheModel)obj;

		if (notarizationId == notarizationCacheModel.notarizationId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, notarizationId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(39);

		sb.append("{notarizationId=");
		sb.append(notarizationId);
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
		sb.append(", dossierId=");
		sb.append(dossierId);
		sb.append(", fileName=");
		sb.append(fileName);
		sb.append(", totalRecord=");
		sb.append(totalRecord);
		sb.append(", totalPage=");
		sb.append(totalPage);
		sb.append(", totalCopy=");
		sb.append(totalCopy);
		sb.append(", totalFee=");
		sb.append(totalFee);
		sb.append(", notarizationNo=");
		sb.append(notarizationNo);
		sb.append(", notarizationYear=");
		sb.append(notarizationYear);
		sb.append(", notarizationDate=");
		sb.append(notarizationDate);
		sb.append(", signerName=");
		sb.append(signerName);
		sb.append(", signerPosition=");
		sb.append(signerPosition);
		sb.append(", statusCode=");
		sb.append(statusCode);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public Notarization toEntityModel() {
		NotarizationImpl notarizationImpl = new NotarizationImpl();

		notarizationImpl.setNotarizationId(notarizationId);
		notarizationImpl.setGroupId(groupId);
		notarizationImpl.setCompanyId(companyId);
		notarizationImpl.setUserId(userId);

		if (userName == null) {
			notarizationImpl.setUserName("");
		}
		else {
			notarizationImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			notarizationImpl.setCreateDate(null);
		}
		else {
			notarizationImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			notarizationImpl.setModifiedDate(null);
		}
		else {
			notarizationImpl.setModifiedDate(new Date(modifiedDate));
		}

		notarizationImpl.setDossierId(dossierId);

		if (fileName == null) {
			notarizationImpl.setFileName("");
		}
		else {
			notarizationImpl.setFileName(fileName);
		}

		notarizationImpl.setTotalRecord(totalRecord);
		notarizationImpl.setTotalPage(totalPage);
		notarizationImpl.setTotalCopy(totalCopy);
		notarizationImpl.setTotalFee(totalFee);
		notarizationImpl.setNotarizationNo(notarizationNo);
		notarizationImpl.setNotarizationYear(notarizationYear);

		if (notarizationDate == Long.MIN_VALUE) {
			notarizationImpl.setNotarizationDate(null);
		}
		else {
			notarizationImpl.setNotarizationDate(new Date(notarizationDate));
		}

		if (signerName == null) {
			notarizationImpl.setSignerName("");
		}
		else {
			notarizationImpl.setSignerName(signerName);
		}

		if (signerPosition == null) {
			notarizationImpl.setSignerPosition("");
		}
		else {
			notarizationImpl.setSignerPosition(signerPosition);
		}

		if (statusCode == null) {
			notarizationImpl.setStatusCode("");
		}
		else {
			notarizationImpl.setStatusCode(statusCode);
		}

		notarizationImpl.resetOriginalValues();

		return notarizationImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		notarizationId = objectInput.readLong();

		groupId = objectInput.readLong();

		companyId = objectInput.readLong();

		userId = objectInput.readLong();
		userName = objectInput.readUTF();
		createDate = objectInput.readLong();
		modifiedDate = objectInput.readLong();

		dossierId = objectInput.readLong();
		fileName = objectInput.readUTF();

		totalRecord = objectInput.readInt();

		totalPage = objectInput.readInt();

		totalCopy = objectInput.readInt();

		totalFee = objectInput.readLong();

		notarizationNo = objectInput.readLong();

		notarizationYear = objectInput.readInt();
		notarizationDate = objectInput.readLong();
		signerName = objectInput.readUTF();
		signerPosition = objectInput.readUTF();
		statusCode = objectInput.readUTF();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput)
		throws IOException {
		objectOutput.writeLong(notarizationId);

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

		objectOutput.writeLong(dossierId);

		if (fileName == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(fileName);
		}

		objectOutput.writeInt(totalRecord);

		objectOutput.writeInt(totalPage);

		objectOutput.writeInt(totalCopy);

		objectOutput.writeLong(totalFee);

		objectOutput.writeLong(notarizationNo);

		objectOutput.writeInt(notarizationYear);
		objectOutput.writeLong(notarizationDate);

		if (signerName == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(signerName);
		}

		if (signerPosition == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(signerPosition);
		}

		if (statusCode == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(statusCode);
		}
	}

	public long notarizationId;
	public long groupId;
	public long companyId;
	public long userId;
	public String userName;
	public long createDate;
	public long modifiedDate;
	public long dossierId;
	public String fileName;
	public int totalRecord;
	public int totalPage;
	public int totalCopy;
	public long totalFee;
	public long notarizationNo;
	public int notarizationYear;
	public long notarizationDate;
	public String signerName;
	public String signerPosition;
	public String statusCode;
}