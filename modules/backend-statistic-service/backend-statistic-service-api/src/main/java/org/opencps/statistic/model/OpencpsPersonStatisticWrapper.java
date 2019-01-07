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

package org.opencps.statistic.model;

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
 * This class is a wrapper for {@link OpencpsPersonStatistic}.
 * </p>
 *
 * @author khoavu
 * @see OpencpsPersonStatistic
 * @generated
 */
@ProviderType
public class OpencpsPersonStatisticWrapper implements OpencpsPersonStatistic,
	ModelWrapper<OpencpsPersonStatistic> {
	public OpencpsPersonStatisticWrapper(
		OpencpsPersonStatistic opencpsPersonStatistic) {
		_opencpsPersonStatistic = opencpsPersonStatistic;
	}

	@Override
	public Class<?> getModelClass() {
		return OpencpsPersonStatistic.class;
	}

	@Override
	public String getModelClassName() {
		return OpencpsPersonStatistic.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("uuid", getUuid());
		attributes.put("personStatisticId", getPersonStatisticId());
		attributes.put("companyId", getCompanyId());
		attributes.put("groupId", getGroupId());
		attributes.put("userId", getUserId());
		attributes.put("userName", getUserName());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("month", getMonth());
		attributes.put("year", getYear());
		attributes.put("govAgencyCode", getGovAgencyCode());
		attributes.put("govAgencyName", getGovAgencyName());
		attributes.put("employeeId", getEmployeeId());
		attributes.put("votingCode", getVotingCode());
		attributes.put("votingSubject", getVotingSubject());
		attributes.put("totalVoted", getTotalVoted());
		attributes.put("veryGoodCount", getVeryGoodCount());
		attributes.put("goodCount", getGoodCount());
		attributes.put("badCount", getBadCount());
		attributes.put("percentVeryGood", getPercentVeryGood());
		attributes.put("percentGood", getPercentGood());
		attributes.put("percentBad", getPercentBad());
		attributes.put("totalCount", getTotalCount());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		String uuid = (String)attributes.get("uuid");

		if (uuid != null) {
			setUuid(uuid);
		}

		Long personStatisticId = (Long)attributes.get("personStatisticId");

		if (personStatisticId != null) {
			setPersonStatisticId(personStatisticId);
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

		String userName = (String)attributes.get("userName");

		if (userName != null) {
			setUserName(userName);
		}

		Date createDate = (Date)attributes.get("createDate");

		if (createDate != null) {
			setCreateDate(createDate);
		}

		Date modifiedDate = (Date)attributes.get("modifiedDate");

		if (modifiedDate != null) {
			setModifiedDate(modifiedDate);
		}

		Integer month = (Integer)attributes.get("month");

		if (month != null) {
			setMonth(month);
		}

		Integer year = (Integer)attributes.get("year");

		if (year != null) {
			setYear(year);
		}

		String govAgencyCode = (String)attributes.get("govAgencyCode");

		if (govAgencyCode != null) {
			setGovAgencyCode(govAgencyCode);
		}

		String govAgencyName = (String)attributes.get("govAgencyName");

		if (govAgencyName != null) {
			setGovAgencyName(govAgencyName);
		}

		Long employeeId = (Long)attributes.get("employeeId");

		if (employeeId != null) {
			setEmployeeId(employeeId);
		}

		String votingCode = (String)attributes.get("votingCode");

		if (votingCode != null) {
			setVotingCode(votingCode);
		}

		String votingSubject = (String)attributes.get("votingSubject");

		if (votingSubject != null) {
			setVotingSubject(votingSubject);
		}

		Integer totalVoted = (Integer)attributes.get("totalVoted");

		if (totalVoted != null) {
			setTotalVoted(totalVoted);
		}

		Integer veryGoodCount = (Integer)attributes.get("veryGoodCount");

		if (veryGoodCount != null) {
			setVeryGoodCount(veryGoodCount);
		}

		Integer goodCount = (Integer)attributes.get("goodCount");

		if (goodCount != null) {
			setGoodCount(goodCount);
		}

		Integer badCount = (Integer)attributes.get("badCount");

		if (badCount != null) {
			setBadCount(badCount);
		}

		Integer percentVeryGood = (Integer)attributes.get("percentVeryGood");

		if (percentVeryGood != null) {
			setPercentVeryGood(percentVeryGood);
		}

		Integer percentGood = (Integer)attributes.get("percentGood");

		if (percentGood != null) {
			setPercentGood(percentGood);
		}

		Integer percentBad = (Integer)attributes.get("percentBad");

		if (percentBad != null) {
			setPercentBad(percentBad);
		}

		Integer totalCount = (Integer)attributes.get("totalCount");

		if (totalCount != null) {
			setTotalCount(totalCount);
		}
	}

	@Override
	public Object clone() {
		return new OpencpsPersonStatisticWrapper((OpencpsPersonStatistic)_opencpsPersonStatistic.clone());
	}

	@Override
	public int compareTo(OpencpsPersonStatistic opencpsPersonStatistic) {
		return _opencpsPersonStatistic.compareTo(opencpsPersonStatistic);
	}

	/**
	* Returns the bad count of this opencps person statistic.
	*
	* @return the bad count of this opencps person statistic
	*/
	@Override
	public int getBadCount() {
		return _opencpsPersonStatistic.getBadCount();
	}

	/**
	* Returns the company ID of this opencps person statistic.
	*
	* @return the company ID of this opencps person statistic
	*/
	@Override
	public long getCompanyId() {
		return _opencpsPersonStatistic.getCompanyId();
	}

	/**
	* Returns the create date of this opencps person statistic.
	*
	* @return the create date of this opencps person statistic
	*/
	@Override
	public Date getCreateDate() {
		return _opencpsPersonStatistic.getCreateDate();
	}

	/**
	* Returns the employee ID of this opencps person statistic.
	*
	* @return the employee ID of this opencps person statistic
	*/
	@Override
	public long getEmployeeId() {
		return _opencpsPersonStatistic.getEmployeeId();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _opencpsPersonStatistic.getExpandoBridge();
	}

	/**
	* Returns the good count of this opencps person statistic.
	*
	* @return the good count of this opencps person statistic
	*/
	@Override
	public int getGoodCount() {
		return _opencpsPersonStatistic.getGoodCount();
	}

	/**
	* Returns the gov agency code of this opencps person statistic.
	*
	* @return the gov agency code of this opencps person statistic
	*/
	@Override
	public String getGovAgencyCode() {
		return _opencpsPersonStatistic.getGovAgencyCode();
	}

	/**
	* Returns the gov agency name of this opencps person statistic.
	*
	* @return the gov agency name of this opencps person statistic
	*/
	@Override
	public String getGovAgencyName() {
		return _opencpsPersonStatistic.getGovAgencyName();
	}

	/**
	* Returns the group ID of this opencps person statistic.
	*
	* @return the group ID of this opencps person statistic
	*/
	@Override
	public long getGroupId() {
		return _opencpsPersonStatistic.getGroupId();
	}

	/**
	* Returns the modified date of this opencps person statistic.
	*
	* @return the modified date of this opencps person statistic
	*/
	@Override
	public Date getModifiedDate() {
		return _opencpsPersonStatistic.getModifiedDate();
	}

	/**
	* Returns the month of this opencps person statistic.
	*
	* @return the month of this opencps person statistic
	*/
	@Override
	public int getMonth() {
		return _opencpsPersonStatistic.getMonth();
	}

	/**
	* Returns the percent bad of this opencps person statistic.
	*
	* @return the percent bad of this opencps person statistic
	*/
	@Override
	public int getPercentBad() {
		return _opencpsPersonStatistic.getPercentBad();
	}

	/**
	* Returns the percent good of this opencps person statistic.
	*
	* @return the percent good of this opencps person statistic
	*/
	@Override
	public int getPercentGood() {
		return _opencpsPersonStatistic.getPercentGood();
	}

	/**
	* Returns the percent very good of this opencps person statistic.
	*
	* @return the percent very good of this opencps person statistic
	*/
	@Override
	public int getPercentVeryGood() {
		return _opencpsPersonStatistic.getPercentVeryGood();
	}

	/**
	* Returns the person statistic ID of this opencps person statistic.
	*
	* @return the person statistic ID of this opencps person statistic
	*/
	@Override
	public long getPersonStatisticId() {
		return _opencpsPersonStatistic.getPersonStatisticId();
	}

	/**
	* Returns the primary key of this opencps person statistic.
	*
	* @return the primary key of this opencps person statistic
	*/
	@Override
	public long getPrimaryKey() {
		return _opencpsPersonStatistic.getPrimaryKey();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _opencpsPersonStatistic.getPrimaryKeyObj();
	}

	/**
	* Returns the total count of this opencps person statistic.
	*
	* @return the total count of this opencps person statistic
	*/
	@Override
	public int getTotalCount() {
		return _opencpsPersonStatistic.getTotalCount();
	}

	/**
	* Returns the total voted of this opencps person statistic.
	*
	* @return the total voted of this opencps person statistic
	*/
	@Override
	public int getTotalVoted() {
		return _opencpsPersonStatistic.getTotalVoted();
	}

	/**
	* Returns the user ID of this opencps person statistic.
	*
	* @return the user ID of this opencps person statistic
	*/
	@Override
	public long getUserId() {
		return _opencpsPersonStatistic.getUserId();
	}

	/**
	* Returns the user name of this opencps person statistic.
	*
	* @return the user name of this opencps person statistic
	*/
	@Override
	public String getUserName() {
		return _opencpsPersonStatistic.getUserName();
	}

	/**
	* Returns the user uuid of this opencps person statistic.
	*
	* @return the user uuid of this opencps person statistic
	*/
	@Override
	public String getUserUuid() {
		return _opencpsPersonStatistic.getUserUuid();
	}

	/**
	* Returns the uuid of this opencps person statistic.
	*
	* @return the uuid of this opencps person statistic
	*/
	@Override
	public String getUuid() {
		return _opencpsPersonStatistic.getUuid();
	}

	/**
	* Returns the very good count of this opencps person statistic.
	*
	* @return the very good count of this opencps person statistic
	*/
	@Override
	public int getVeryGoodCount() {
		return _opencpsPersonStatistic.getVeryGoodCount();
	}

	/**
	* Returns the voting code of this opencps person statistic.
	*
	* @return the voting code of this opencps person statistic
	*/
	@Override
	public String getVotingCode() {
		return _opencpsPersonStatistic.getVotingCode();
	}

	/**
	* Returns the voting subject of this opencps person statistic.
	*
	* @return the voting subject of this opencps person statistic
	*/
	@Override
	public String getVotingSubject() {
		return _opencpsPersonStatistic.getVotingSubject();
	}

	/**
	* Returns the year of this opencps person statistic.
	*
	* @return the year of this opencps person statistic
	*/
	@Override
	public int getYear() {
		return _opencpsPersonStatistic.getYear();
	}

	@Override
	public int hashCode() {
		return _opencpsPersonStatistic.hashCode();
	}

	@Override
	public boolean isCachedModel() {
		return _opencpsPersonStatistic.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _opencpsPersonStatistic.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _opencpsPersonStatistic.isNew();
	}

	@Override
	public void persist() {
		_opencpsPersonStatistic.persist();
	}

	/**
	* Sets the bad count of this opencps person statistic.
	*
	* @param badCount the bad count of this opencps person statistic
	*/
	@Override
	public void setBadCount(int badCount) {
		_opencpsPersonStatistic.setBadCount(badCount);
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_opencpsPersonStatistic.setCachedModel(cachedModel);
	}

	/**
	* Sets the company ID of this opencps person statistic.
	*
	* @param companyId the company ID of this opencps person statistic
	*/
	@Override
	public void setCompanyId(long companyId) {
		_opencpsPersonStatistic.setCompanyId(companyId);
	}

	/**
	* Sets the create date of this opencps person statistic.
	*
	* @param createDate the create date of this opencps person statistic
	*/
	@Override
	public void setCreateDate(Date createDate) {
		_opencpsPersonStatistic.setCreateDate(createDate);
	}

	/**
	* Sets the employee ID of this opencps person statistic.
	*
	* @param employeeId the employee ID of this opencps person statistic
	*/
	@Override
	public void setEmployeeId(long employeeId) {
		_opencpsPersonStatistic.setEmployeeId(employeeId);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_opencpsPersonStatistic.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_opencpsPersonStatistic.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_opencpsPersonStatistic.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the good count of this opencps person statistic.
	*
	* @param goodCount the good count of this opencps person statistic
	*/
	@Override
	public void setGoodCount(int goodCount) {
		_opencpsPersonStatistic.setGoodCount(goodCount);
	}

	/**
	* Sets the gov agency code of this opencps person statistic.
	*
	* @param govAgencyCode the gov agency code of this opencps person statistic
	*/
	@Override
	public void setGovAgencyCode(String govAgencyCode) {
		_opencpsPersonStatistic.setGovAgencyCode(govAgencyCode);
	}

	/**
	* Sets the gov agency name of this opencps person statistic.
	*
	* @param govAgencyName the gov agency name of this opencps person statistic
	*/
	@Override
	public void setGovAgencyName(String govAgencyName) {
		_opencpsPersonStatistic.setGovAgencyName(govAgencyName);
	}

	/**
	* Sets the group ID of this opencps person statistic.
	*
	* @param groupId the group ID of this opencps person statistic
	*/
	@Override
	public void setGroupId(long groupId) {
		_opencpsPersonStatistic.setGroupId(groupId);
	}

	/**
	* Sets the modified date of this opencps person statistic.
	*
	* @param modifiedDate the modified date of this opencps person statistic
	*/
	@Override
	public void setModifiedDate(Date modifiedDate) {
		_opencpsPersonStatistic.setModifiedDate(modifiedDate);
	}

	/**
	* Sets the month of this opencps person statistic.
	*
	* @param month the month of this opencps person statistic
	*/
	@Override
	public void setMonth(int month) {
		_opencpsPersonStatistic.setMonth(month);
	}

	@Override
	public void setNew(boolean n) {
		_opencpsPersonStatistic.setNew(n);
	}

	/**
	* Sets the percent bad of this opencps person statistic.
	*
	* @param percentBad the percent bad of this opencps person statistic
	*/
	@Override
	public void setPercentBad(int percentBad) {
		_opencpsPersonStatistic.setPercentBad(percentBad);
	}

	/**
	* Sets the percent good of this opencps person statistic.
	*
	* @param percentGood the percent good of this opencps person statistic
	*/
	@Override
	public void setPercentGood(int percentGood) {
		_opencpsPersonStatistic.setPercentGood(percentGood);
	}

	/**
	* Sets the percent very good of this opencps person statistic.
	*
	* @param percentVeryGood the percent very good of this opencps person statistic
	*/
	@Override
	public void setPercentVeryGood(int percentVeryGood) {
		_opencpsPersonStatistic.setPercentVeryGood(percentVeryGood);
	}

	/**
	* Sets the person statistic ID of this opencps person statistic.
	*
	* @param personStatisticId the person statistic ID of this opencps person statistic
	*/
	@Override
	public void setPersonStatisticId(long personStatisticId) {
		_opencpsPersonStatistic.setPersonStatisticId(personStatisticId);
	}

	/**
	* Sets the primary key of this opencps person statistic.
	*
	* @param primaryKey the primary key of this opencps person statistic
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_opencpsPersonStatistic.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_opencpsPersonStatistic.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the total count of this opencps person statistic.
	*
	* @param totalCount the total count of this opencps person statistic
	*/
	@Override
	public void setTotalCount(int totalCount) {
		_opencpsPersonStatistic.setTotalCount(totalCount);
	}

	/**
	* Sets the total voted of this opencps person statistic.
	*
	* @param totalVoted the total voted of this opencps person statistic
	*/
	@Override
	public void setTotalVoted(int totalVoted) {
		_opencpsPersonStatistic.setTotalVoted(totalVoted);
	}

	/**
	* Sets the user ID of this opencps person statistic.
	*
	* @param userId the user ID of this opencps person statistic
	*/
	@Override
	public void setUserId(long userId) {
		_opencpsPersonStatistic.setUserId(userId);
	}

	/**
	* Sets the user name of this opencps person statistic.
	*
	* @param userName the user name of this opencps person statistic
	*/
	@Override
	public void setUserName(String userName) {
		_opencpsPersonStatistic.setUserName(userName);
	}

	/**
	* Sets the user uuid of this opencps person statistic.
	*
	* @param userUuid the user uuid of this opencps person statistic
	*/
	@Override
	public void setUserUuid(String userUuid) {
		_opencpsPersonStatistic.setUserUuid(userUuid);
	}

	/**
	* Sets the uuid of this opencps person statistic.
	*
	* @param uuid the uuid of this opencps person statistic
	*/
	@Override
	public void setUuid(String uuid) {
		_opencpsPersonStatistic.setUuid(uuid);
	}

	/**
	* Sets the very good count of this opencps person statistic.
	*
	* @param veryGoodCount the very good count of this opencps person statistic
	*/
	@Override
	public void setVeryGoodCount(int veryGoodCount) {
		_opencpsPersonStatistic.setVeryGoodCount(veryGoodCount);
	}

	/**
	* Sets the voting code of this opencps person statistic.
	*
	* @param votingCode the voting code of this opencps person statistic
	*/
	@Override
	public void setVotingCode(String votingCode) {
		_opencpsPersonStatistic.setVotingCode(votingCode);
	}

	/**
	* Sets the voting subject of this opencps person statistic.
	*
	* @param votingSubject the voting subject of this opencps person statistic
	*/
	@Override
	public void setVotingSubject(String votingSubject) {
		_opencpsPersonStatistic.setVotingSubject(votingSubject);
	}

	/**
	* Sets the year of this opencps person statistic.
	*
	* @param year the year of this opencps person statistic
	*/
	@Override
	public void setYear(int year) {
		_opencpsPersonStatistic.setYear(year);
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<OpencpsPersonStatistic> toCacheModel() {
		return _opencpsPersonStatistic.toCacheModel();
	}

	@Override
	public OpencpsPersonStatistic toEscapedModel() {
		return new OpencpsPersonStatisticWrapper(_opencpsPersonStatistic.toEscapedModel());
	}

	@Override
	public String toString() {
		return _opencpsPersonStatistic.toString();
	}

	@Override
	public OpencpsPersonStatistic toUnescapedModel() {
		return new OpencpsPersonStatisticWrapper(_opencpsPersonStatistic.toUnescapedModel());
	}

	@Override
	public String toXmlString() {
		return _opencpsPersonStatistic.toXmlString();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof OpencpsPersonStatisticWrapper)) {
			return false;
		}

		OpencpsPersonStatisticWrapper opencpsPersonStatisticWrapper = (OpencpsPersonStatisticWrapper)obj;

		if (Objects.equals(_opencpsPersonStatistic,
					opencpsPersonStatisticWrapper._opencpsPersonStatistic)) {
			return true;
		}

		return false;
	}

	@Override
	public StagedModelType getStagedModelType() {
		return _opencpsPersonStatistic.getStagedModelType();
	}

	@Override
	public OpencpsPersonStatistic getWrappedModel() {
		return _opencpsPersonStatistic;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _opencpsPersonStatistic.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _opencpsPersonStatistic.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_opencpsPersonStatistic.resetOriginalValues();
	}

	private final OpencpsPersonStatistic _opencpsPersonStatistic;
}