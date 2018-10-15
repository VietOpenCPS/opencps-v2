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

package backend.feedback.model.impl;

import aQute.bnd.annotation.ProviderType;

import backend.feedback.model.Evaluation;

import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.util.HashUtil;
import com.liferay.portal.kernel.util.StringBundler;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing Evaluation in entity cache.
 *
 * @author sondt
 * @see Evaluation
 * @generated
 */
@ProviderType
public class EvaluationCacheModel implements CacheModel<Evaluation>,
	Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof EvaluationCacheModel)) {
			return false;
		}

		EvaluationCacheModel evaluationCacheModel = (EvaluationCacheModel)obj;

		if (evaluationId == evaluationCacheModel.evaluationId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, evaluationId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(21);

		sb.append("{uuid=");
		sb.append(uuid);
		sb.append(", evaluationId=");
		sb.append(evaluationId);
		sb.append(", companyId=");
		sb.append(companyId);
		sb.append(", groupId=");
		sb.append(groupId);
		sb.append(", userId=");
		sb.append(userId);
		sb.append(", createDate=");
		sb.append(createDate);
		sb.append(", modifiedDate=");
		sb.append(modifiedDate);
		sb.append(", employeeId=");
		sb.append(employeeId);
		sb.append(", employeeName=");
		sb.append(employeeName);
		sb.append(", score=");
		sb.append(score);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public Evaluation toEntityModel() {
		EvaluationImpl evaluationImpl = new EvaluationImpl();

		if (uuid == null) {
			evaluationImpl.setUuid("");
		}
		else {
			evaluationImpl.setUuid(uuid);
		}

		evaluationImpl.setEvaluationId(evaluationId);
		evaluationImpl.setCompanyId(companyId);
		evaluationImpl.setGroupId(groupId);
		evaluationImpl.setUserId(userId);

		if (createDate == Long.MIN_VALUE) {
			evaluationImpl.setCreateDate(null);
		}
		else {
			evaluationImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			evaluationImpl.setModifiedDate(null);
		}
		else {
			evaluationImpl.setModifiedDate(new Date(modifiedDate));
		}

		evaluationImpl.setEmployeeId(employeeId);

		if (employeeName == null) {
			evaluationImpl.setEmployeeName("");
		}
		else {
			evaluationImpl.setEmployeeName(employeeName);
		}

		evaluationImpl.setScore(score);

		evaluationImpl.resetOriginalValues();

		return evaluationImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		uuid = objectInput.readUTF();

		evaluationId = objectInput.readLong();

		companyId = objectInput.readLong();

		groupId = objectInput.readLong();

		userId = objectInput.readLong();
		createDate = objectInput.readLong();
		modifiedDate = objectInput.readLong();

		employeeId = objectInput.readLong();
		employeeName = objectInput.readUTF();

		score = objectInput.readInt();
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

		objectOutput.writeLong(evaluationId);

		objectOutput.writeLong(companyId);

		objectOutput.writeLong(groupId);

		objectOutput.writeLong(userId);
		objectOutput.writeLong(createDate);
		objectOutput.writeLong(modifiedDate);

		objectOutput.writeLong(employeeId);

		if (employeeName == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(employeeName);
		}

		objectOutput.writeInt(score);
	}

	public String uuid;
	public long evaluationId;
	public long companyId;
	public long groupId;
	public long userId;
	public long createDate;
	public long modifiedDate;
	public long employeeId;
	public String employeeName;
	public int score;
}