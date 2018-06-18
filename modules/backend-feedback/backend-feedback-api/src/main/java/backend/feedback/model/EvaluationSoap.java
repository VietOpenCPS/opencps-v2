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

package backend.feedback.model;

import aQute.bnd.annotation.ProviderType;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * This class is used by SOAP remote services.
 *
 * @author sondt
 * @generated
 */
@ProviderType
public class EvaluationSoap implements Serializable {
	public static EvaluationSoap toSoapModel(Evaluation model) {
		EvaluationSoap soapModel = new EvaluationSoap();

		soapModel.setUuid(model.getUuid());
		soapModel.setEvaluationId(model.getEvaluationId());
		soapModel.setCompanyId(model.getCompanyId());
		soapModel.setGroupId(model.getGroupId());
		soapModel.setUserId(model.getUserId());
		soapModel.setCreateDate(model.getCreateDate());
		soapModel.setModifiedDate(model.getModifiedDate());
		soapModel.setEmployeeId(model.getEmployeeId());
		soapModel.setEmployeeName(model.getEmployeeName());
		soapModel.setScore(model.getScore());

		return soapModel;
	}

	public static EvaluationSoap[] toSoapModels(Evaluation[] models) {
		EvaluationSoap[] soapModels = new EvaluationSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static EvaluationSoap[][] toSoapModels(Evaluation[][] models) {
		EvaluationSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new EvaluationSoap[models.length][models[0].length];
		}
		else {
			soapModels = new EvaluationSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static EvaluationSoap[] toSoapModels(List<Evaluation> models) {
		List<EvaluationSoap> soapModels = new ArrayList<EvaluationSoap>(models.size());

		for (Evaluation model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new EvaluationSoap[soapModels.size()]);
	}

	public EvaluationSoap() {
	}

	public long getPrimaryKey() {
		return _evaluationId;
	}

	public void setPrimaryKey(long pk) {
		setEvaluationId(pk);
	}

	public String getUuid() {
		return _uuid;
	}

	public void setUuid(String uuid) {
		_uuid = uuid;
	}

	public long getEvaluationId() {
		return _evaluationId;
	}

	public void setEvaluationId(long evaluationId) {
		_evaluationId = evaluationId;
	}

	public long getCompanyId() {
		return _companyId;
	}

	public void setCompanyId(long companyId) {
		_companyId = companyId;
	}

	public long getGroupId() {
		return _groupId;
	}

	public void setGroupId(long groupId) {
		_groupId = groupId;
	}

	public long getUserId() {
		return _userId;
	}

	public void setUserId(long userId) {
		_userId = userId;
	}

	public Date getCreateDate() {
		return _createDate;
	}

	public void setCreateDate(Date createDate) {
		_createDate = createDate;
	}

	public Date getModifiedDate() {
		return _modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		_modifiedDate = modifiedDate;
	}

	public long getEmployeeId() {
		return _employeeId;
	}

	public void setEmployeeId(long employeeId) {
		_employeeId = employeeId;
	}

	public String getEmployeeName() {
		return _employeeName;
	}

	public void setEmployeeName(String employeeName) {
		_employeeName = employeeName;
	}

	public int getScore() {
		return _score;
	}

	public void setScore(int score) {
		_score = score;
	}

	private String _uuid;
	private long _evaluationId;
	private long _companyId;
	private long _groupId;
	private long _userId;
	private Date _createDate;
	private Date _modifiedDate;
	private long _employeeId;
	private String _employeeName;
	private int _score;
}