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
 * This class is a wrapper for {@link Evaluation}.
 * </p>
 *
 * @author sondt
 * @see Evaluation
 * @generated
 */
@ProviderType
public class EvaluationWrapper implements Evaluation, ModelWrapper<Evaluation> {
	public EvaluationWrapper(Evaluation evaluation) {
		_evaluation = evaluation;
	}

	@Override
	public Class<?> getModelClass() {
		return Evaluation.class;
	}

	@Override
	public String getModelClassName() {
		return Evaluation.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("uuid", getUuid());
		attributes.put("evaluationId", getEvaluationId());
		attributes.put("companyId", getCompanyId());
		attributes.put("groupId", getGroupId());
		attributes.put("userId", getUserId());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("employeeId", getEmployeeId());
		attributes.put("employeeName", getEmployeeName());
		attributes.put("score", getScore());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		String uuid = (String)attributes.get("uuid");

		if (uuid != null) {
			setUuid(uuid);
		}

		Long evaluationId = (Long)attributes.get("evaluationId");

		if (evaluationId != null) {
			setEvaluationId(evaluationId);
		}

		Long companyId = (Long)attributes.get("companyId");

		if (companyId != null) {
			setCompanyId(companyId);
		}

		Long groupId = (Long)attributes.get("groupId");

		if (groupId != null) {
			setGroupId(groupId);
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

		Long employeeId = (Long)attributes.get("employeeId");

		if (employeeId != null) {
			setEmployeeId(employeeId);
		}

		String employeeName = (String)attributes.get("employeeName");

		if (employeeName != null) {
			setEmployeeName(employeeName);
		}

		Integer score = (Integer)attributes.get("score");

		if (score != null) {
			setScore(score);
		}
	}

	@Override
	public backend.feedback.model.Evaluation toEscapedModel() {
		return new EvaluationWrapper(_evaluation.toEscapedModel());
	}

	@Override
	public backend.feedback.model.Evaluation toUnescapedModel() {
		return new EvaluationWrapper(_evaluation.toUnescapedModel());
	}

	@Override
	public boolean isCachedModel() {
		return _evaluation.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _evaluation.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _evaluation.isNew();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _evaluation.getExpandoBridge();
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<backend.feedback.model.Evaluation> toCacheModel() {
		return _evaluation.toCacheModel();
	}

	@Override
	public int compareTo(backend.feedback.model.Evaluation evaluation) {
		return _evaluation.compareTo(evaluation);
	}

	/**
	* Returns the score of this evaluation.
	*
	* @return the score of this evaluation
	*/
	@Override
	public int getScore() {
		return _evaluation.getScore();
	}

	@Override
	public int hashCode() {
		return _evaluation.hashCode();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _evaluation.getPrimaryKeyObj();
	}

	@Override
	public java.lang.Object clone() {
		return new EvaluationWrapper((Evaluation)_evaluation.clone());
	}

	/**
	* Returns the employee name of this evaluation.
	*
	* @return the employee name of this evaluation
	*/
	@Override
	public java.lang.String getEmployeeName() {
		return _evaluation.getEmployeeName();
	}

	/**
	* Returns the user uuid of this evaluation.
	*
	* @return the user uuid of this evaluation
	*/
	@Override
	public java.lang.String getUserUuid() {
		return _evaluation.getUserUuid();
	}

	/**
	* Returns the uuid of this evaluation.
	*
	* @return the uuid of this evaluation
	*/
	@Override
	public java.lang.String getUuid() {
		return _evaluation.getUuid();
	}

	@Override
	public java.lang.String toString() {
		return _evaluation.toString();
	}

	@Override
	public java.lang.String toXmlString() {
		return _evaluation.toXmlString();
	}

	/**
	* Returns the create date of this evaluation.
	*
	* @return the create date of this evaluation
	*/
	@Override
	public Date getCreateDate() {
		return _evaluation.getCreateDate();
	}

	/**
	* Returns the modified date of this evaluation.
	*
	* @return the modified date of this evaluation
	*/
	@Override
	public Date getModifiedDate() {
		return _evaluation.getModifiedDate();
	}

	/**
	* Returns the company ID of this evaluation.
	*
	* @return the company ID of this evaluation
	*/
	@Override
	public long getCompanyId() {
		return _evaluation.getCompanyId();
	}

	/**
	* Returns the employee ID of this evaluation.
	*
	* @return the employee ID of this evaluation
	*/
	@Override
	public long getEmployeeId() {
		return _evaluation.getEmployeeId();
	}

	/**
	* Returns the evaluation ID of this evaluation.
	*
	* @return the evaluation ID of this evaluation
	*/
	@Override
	public long getEvaluationId() {
		return _evaluation.getEvaluationId();
	}

	/**
	* Returns the group ID of this evaluation.
	*
	* @return the group ID of this evaluation
	*/
	@Override
	public long getGroupId() {
		return _evaluation.getGroupId();
	}

	/**
	* Returns the primary key of this evaluation.
	*
	* @return the primary key of this evaluation
	*/
	@Override
	public long getPrimaryKey() {
		return _evaluation.getPrimaryKey();
	}

	/**
	* Returns the user ID of this evaluation.
	*
	* @return the user ID of this evaluation
	*/
	@Override
	public long getUserId() {
		return _evaluation.getUserId();
	}

	@Override
	public void persist() {
		_evaluation.persist();
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_evaluation.setCachedModel(cachedModel);
	}

	/**
	* Sets the company ID of this evaluation.
	*
	* @param companyId the company ID of this evaluation
	*/
	@Override
	public void setCompanyId(long companyId) {
		_evaluation.setCompanyId(companyId);
	}

	/**
	* Sets the create date of this evaluation.
	*
	* @param createDate the create date of this evaluation
	*/
	@Override
	public void setCreateDate(Date createDate) {
		_evaluation.setCreateDate(createDate);
	}

	/**
	* Sets the employee ID of this evaluation.
	*
	* @param employeeId the employee ID of this evaluation
	*/
	@Override
	public void setEmployeeId(long employeeId) {
		_evaluation.setEmployeeId(employeeId);
	}

	/**
	* Sets the employee name of this evaluation.
	*
	* @param employeeName the employee name of this evaluation
	*/
	@Override
	public void setEmployeeName(java.lang.String employeeName) {
		_evaluation.setEmployeeName(employeeName);
	}

	/**
	* Sets the evaluation ID of this evaluation.
	*
	* @param evaluationId the evaluation ID of this evaluation
	*/
	@Override
	public void setEvaluationId(long evaluationId) {
		_evaluation.setEvaluationId(evaluationId);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_evaluation.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_evaluation.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_evaluation.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the group ID of this evaluation.
	*
	* @param groupId the group ID of this evaluation
	*/
	@Override
	public void setGroupId(long groupId) {
		_evaluation.setGroupId(groupId);
	}

	/**
	* Sets the modified date of this evaluation.
	*
	* @param modifiedDate the modified date of this evaluation
	*/
	@Override
	public void setModifiedDate(Date modifiedDate) {
		_evaluation.setModifiedDate(modifiedDate);
	}

	@Override
	public void setNew(boolean n) {
		_evaluation.setNew(n);
	}

	/**
	* Sets the primary key of this evaluation.
	*
	* @param primaryKey the primary key of this evaluation
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_evaluation.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_evaluation.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the score of this evaluation.
	*
	* @param score the score of this evaluation
	*/
	@Override
	public void setScore(int score) {
		_evaluation.setScore(score);
	}

	/**
	* Sets the user ID of this evaluation.
	*
	* @param userId the user ID of this evaluation
	*/
	@Override
	public void setUserId(long userId) {
		_evaluation.setUserId(userId);
	}

	/**
	* Sets the user uuid of this evaluation.
	*
	* @param userUuid the user uuid of this evaluation
	*/
	@Override
	public void setUserUuid(java.lang.String userUuid) {
		_evaluation.setUserUuid(userUuid);
	}

	/**
	* Sets the uuid of this evaluation.
	*
	* @param uuid the uuid of this evaluation
	*/
	@Override
	public void setUuid(java.lang.String uuid) {
		_evaluation.setUuid(uuid);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof EvaluationWrapper)) {
			return false;
		}

		EvaluationWrapper evaluationWrapper = (EvaluationWrapper)obj;

		if (Objects.equals(_evaluation, evaluationWrapper._evaluation)) {
			return true;
		}

		return false;
	}

	@Override
	public StagedModelType getStagedModelType() {
		return _evaluation.getStagedModelType();
	}

	@Override
	public Evaluation getWrappedModel() {
		return _evaluation;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _evaluation.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _evaluation.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_evaluation.resetOriginalValues();
	}

	private final Evaluation _evaluation;
}