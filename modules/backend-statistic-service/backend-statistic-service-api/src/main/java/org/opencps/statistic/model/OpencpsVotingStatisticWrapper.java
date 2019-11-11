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
 * This class is a wrapper for {@link OpencpsVotingStatistic}.
 * </p>
 *
 * @author khoavu
 * @see OpencpsVotingStatistic
 * @generated
 */
@ProviderType
public class OpencpsVotingStatisticWrapper implements OpencpsVotingStatistic,
	ModelWrapper<OpencpsVotingStatistic> {
	public OpencpsVotingStatisticWrapper(
		OpencpsVotingStatistic opencpsVotingStatistic) {
		_opencpsVotingStatistic = opencpsVotingStatistic;
	}

	@Override
	public Class<?> getModelClass() {
		return OpencpsVotingStatistic.class;
	}

	@Override
	public String getModelClassName() {
		return OpencpsVotingStatistic.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("uuid", getUuid());
		attributes.put("votingStatisticId", getVotingStatisticId());
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
		attributes.put("domainCode", getDomainCode());
		attributes.put("domainName", getDomainName());
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

		Long votingStatisticId = (Long)attributes.get("votingStatisticId");

		if (votingStatisticId != null) {
			setVotingStatisticId(votingStatisticId);
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

		String domainCode = (String)attributes.get("domainCode");

		if (domainCode != null) {
			setDomainCode(domainCode);
		}

		String domainName = (String)attributes.get("domainName");

		if (domainName != null) {
			setDomainName(domainName);
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
		return new OpencpsVotingStatisticWrapper((OpencpsVotingStatistic)_opencpsVotingStatistic.clone());
	}

	@Override
	public int compareTo(OpencpsVotingStatistic opencpsVotingStatistic) {
		return _opencpsVotingStatistic.compareTo(opencpsVotingStatistic);
	}

	/**
	* Returns the bad count of this opencps voting statistic.
	*
	* @return the bad count of this opencps voting statistic
	*/
	@Override
	public int getBadCount() {
		return _opencpsVotingStatistic.getBadCount();
	}

	/**
	* Returns the company ID of this opencps voting statistic.
	*
	* @return the company ID of this opencps voting statistic
	*/
	@Override
	public long getCompanyId() {
		return _opencpsVotingStatistic.getCompanyId();
	}

	/**
	* Returns the create date of this opencps voting statistic.
	*
	* @return the create date of this opencps voting statistic
	*/
	@Override
	public Date getCreateDate() {
		return _opencpsVotingStatistic.getCreateDate();
	}

	/**
	* Returns the domain code of this opencps voting statistic.
	*
	* @return the domain code of this opencps voting statistic
	*/
	@Override
	public String getDomainCode() {
		return _opencpsVotingStatistic.getDomainCode();
	}

	/**
	* Returns the domain name of this opencps voting statistic.
	*
	* @return the domain name of this opencps voting statistic
	*/
	@Override
	public String getDomainName() {
		return _opencpsVotingStatistic.getDomainName();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _opencpsVotingStatistic.getExpandoBridge();
	}

	/**
	* Returns the good count of this opencps voting statistic.
	*
	* @return the good count of this opencps voting statistic
	*/
	@Override
	public int getGoodCount() {
		return _opencpsVotingStatistic.getGoodCount();
	}

	/**
	* Returns the gov agency code of this opencps voting statistic.
	*
	* @return the gov agency code of this opencps voting statistic
	*/
	@Override
	public String getGovAgencyCode() {
		return _opencpsVotingStatistic.getGovAgencyCode();
	}

	/**
	* Returns the gov agency name of this opencps voting statistic.
	*
	* @return the gov agency name of this opencps voting statistic
	*/
	@Override
	public String getGovAgencyName() {
		return _opencpsVotingStatistic.getGovAgencyName();
	}

	/**
	* Returns the group ID of this opencps voting statistic.
	*
	* @return the group ID of this opencps voting statistic
	*/
	@Override
	public long getGroupId() {
		return _opencpsVotingStatistic.getGroupId();
	}

	/**
	* Returns the modified date of this opencps voting statistic.
	*
	* @return the modified date of this opencps voting statistic
	*/
	@Override
	public Date getModifiedDate() {
		return _opencpsVotingStatistic.getModifiedDate();
	}

	/**
	* Returns the month of this opencps voting statistic.
	*
	* @return the month of this opencps voting statistic
	*/
	@Override
	public int getMonth() {
		return _opencpsVotingStatistic.getMonth();
	}

	/**
	* Returns the percent bad of this opencps voting statistic.
	*
	* @return the percent bad of this opencps voting statistic
	*/
	@Override
	public int getPercentBad() {
		return _opencpsVotingStatistic.getPercentBad();
	}

	/**
	* Returns the percent good of this opencps voting statistic.
	*
	* @return the percent good of this opencps voting statistic
	*/
	@Override
	public int getPercentGood() {
		return _opencpsVotingStatistic.getPercentGood();
	}

	/**
	* Returns the percent very good of this opencps voting statistic.
	*
	* @return the percent very good of this opencps voting statistic
	*/
	@Override
	public int getPercentVeryGood() {
		return _opencpsVotingStatistic.getPercentVeryGood();
	}

	/**
	* Returns the primary key of this opencps voting statistic.
	*
	* @return the primary key of this opencps voting statistic
	*/
	@Override
	public long getPrimaryKey() {
		return _opencpsVotingStatistic.getPrimaryKey();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _opencpsVotingStatistic.getPrimaryKeyObj();
	}

	/**
	* Returns the total count of this opencps voting statistic.
	*
	* @return the total count of this opencps voting statistic
	*/
	@Override
	public int getTotalCount() {
		return _opencpsVotingStatistic.getTotalCount();
	}

	/**
	* Returns the total voted of this opencps voting statistic.
	*
	* @return the total voted of this opencps voting statistic
	*/
	@Override
	public int getTotalVoted() {
		return _opencpsVotingStatistic.getTotalVoted();
	}

	/**
	* Returns the user ID of this opencps voting statistic.
	*
	* @return the user ID of this opencps voting statistic
	*/
	@Override
	public long getUserId() {
		return _opencpsVotingStatistic.getUserId();
	}

	/**
	* Returns the user name of this opencps voting statistic.
	*
	* @return the user name of this opencps voting statistic
	*/
	@Override
	public String getUserName() {
		return _opencpsVotingStatistic.getUserName();
	}

	/**
	* Returns the user uuid of this opencps voting statistic.
	*
	* @return the user uuid of this opencps voting statistic
	*/
	@Override
	public String getUserUuid() {
		return _opencpsVotingStatistic.getUserUuid();
	}

	/**
	* Returns the uuid of this opencps voting statistic.
	*
	* @return the uuid of this opencps voting statistic
	*/
	@Override
	public String getUuid() {
		return _opencpsVotingStatistic.getUuid();
	}

	/**
	* Returns the very good count of this opencps voting statistic.
	*
	* @return the very good count of this opencps voting statistic
	*/
	@Override
	public int getVeryGoodCount() {
		return _opencpsVotingStatistic.getVeryGoodCount();
	}

	/**
	* Returns the voting code of this opencps voting statistic.
	*
	* @return the voting code of this opencps voting statistic
	*/
	@Override
	public String getVotingCode() {
		return _opencpsVotingStatistic.getVotingCode();
	}

	/**
	* Returns the voting statistic ID of this opencps voting statistic.
	*
	* @return the voting statistic ID of this opencps voting statistic
	*/
	@Override
	public long getVotingStatisticId() {
		return _opencpsVotingStatistic.getVotingStatisticId();
	}

	/**
	* Returns the voting subject of this opencps voting statistic.
	*
	* @return the voting subject of this opencps voting statistic
	*/
	@Override
	public String getVotingSubject() {
		return _opencpsVotingStatistic.getVotingSubject();
	}

	/**
	* Returns the year of this opencps voting statistic.
	*
	* @return the year of this opencps voting statistic
	*/
	@Override
	public int getYear() {
		return _opencpsVotingStatistic.getYear();
	}

	@Override
	public int hashCode() {
		return _opencpsVotingStatistic.hashCode();
	}

	@Override
	public boolean isCachedModel() {
		return _opencpsVotingStatistic.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _opencpsVotingStatistic.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _opencpsVotingStatistic.isNew();
	}

	@Override
	public void persist() {
		_opencpsVotingStatistic.persist();
	}

	/**
	* Sets the bad count of this opencps voting statistic.
	*
	* @param badCount the bad count of this opencps voting statistic
	*/
	@Override
	public void setBadCount(int badCount) {
		_opencpsVotingStatistic.setBadCount(badCount);
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_opencpsVotingStatistic.setCachedModel(cachedModel);
	}

	/**
	* Sets the company ID of this opencps voting statistic.
	*
	* @param companyId the company ID of this opencps voting statistic
	*/
	@Override
	public void setCompanyId(long companyId) {
		_opencpsVotingStatistic.setCompanyId(companyId);
	}

	/**
	* Sets the create date of this opencps voting statistic.
	*
	* @param createDate the create date of this opencps voting statistic
	*/
	@Override
	public void setCreateDate(Date createDate) {
		_opencpsVotingStatistic.setCreateDate(createDate);
	}

	/**
	* Sets the domain code of this opencps voting statistic.
	*
	* @param domainCode the domain code of this opencps voting statistic
	*/
	@Override
	public void setDomainCode(String domainCode) {
		_opencpsVotingStatistic.setDomainCode(domainCode);
	}

	/**
	* Sets the domain name of this opencps voting statistic.
	*
	* @param domainName the domain name of this opencps voting statistic
	*/
	@Override
	public void setDomainName(String domainName) {
		_opencpsVotingStatistic.setDomainName(domainName);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_opencpsVotingStatistic.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_opencpsVotingStatistic.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_opencpsVotingStatistic.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the good count of this opencps voting statistic.
	*
	* @param goodCount the good count of this opencps voting statistic
	*/
	@Override
	public void setGoodCount(int goodCount) {
		_opencpsVotingStatistic.setGoodCount(goodCount);
	}

	/**
	* Sets the gov agency code of this opencps voting statistic.
	*
	* @param govAgencyCode the gov agency code of this opencps voting statistic
	*/
	@Override
	public void setGovAgencyCode(String govAgencyCode) {
		_opencpsVotingStatistic.setGovAgencyCode(govAgencyCode);
	}

	/**
	* Sets the gov agency name of this opencps voting statistic.
	*
	* @param govAgencyName the gov agency name of this opencps voting statistic
	*/
	@Override
	public void setGovAgencyName(String govAgencyName) {
		_opencpsVotingStatistic.setGovAgencyName(govAgencyName);
	}

	/**
	* Sets the group ID of this opencps voting statistic.
	*
	* @param groupId the group ID of this opencps voting statistic
	*/
	@Override
	public void setGroupId(long groupId) {
		_opencpsVotingStatistic.setGroupId(groupId);
	}

	/**
	* Sets the modified date of this opencps voting statistic.
	*
	* @param modifiedDate the modified date of this opencps voting statistic
	*/
	@Override
	public void setModifiedDate(Date modifiedDate) {
		_opencpsVotingStatistic.setModifiedDate(modifiedDate);
	}

	/**
	* Sets the month of this opencps voting statistic.
	*
	* @param month the month of this opencps voting statistic
	*/
	@Override
	public void setMonth(int month) {
		_opencpsVotingStatistic.setMonth(month);
	}

	@Override
	public void setNew(boolean n) {
		_opencpsVotingStatistic.setNew(n);
	}

	/**
	* Sets the percent bad of this opencps voting statistic.
	*
	* @param percentBad the percent bad of this opencps voting statistic
	*/
	@Override
	public void setPercentBad(int percentBad) {
		_opencpsVotingStatistic.setPercentBad(percentBad);
	}

	/**
	* Sets the percent good of this opencps voting statistic.
	*
	* @param percentGood the percent good of this opencps voting statistic
	*/
	@Override
	public void setPercentGood(int percentGood) {
		_opencpsVotingStatistic.setPercentGood(percentGood);
	}

	/**
	* Sets the percent very good of this opencps voting statistic.
	*
	* @param percentVeryGood the percent very good of this opencps voting statistic
	*/
	@Override
	public void setPercentVeryGood(int percentVeryGood) {
		_opencpsVotingStatistic.setPercentVeryGood(percentVeryGood);
	}

	/**
	* Sets the primary key of this opencps voting statistic.
	*
	* @param primaryKey the primary key of this opencps voting statistic
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_opencpsVotingStatistic.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_opencpsVotingStatistic.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the total count of this opencps voting statistic.
	*
	* @param totalCount the total count of this opencps voting statistic
	*/
	@Override
	public void setTotalCount(int totalCount) {
		_opencpsVotingStatistic.setTotalCount(totalCount);
	}

	/**
	* Sets the total voted of this opencps voting statistic.
	*
	* @param totalVoted the total voted of this opencps voting statistic
	*/
	@Override
	public void setTotalVoted(int totalVoted) {
		_opencpsVotingStatistic.setTotalVoted(totalVoted);
	}

	/**
	* Sets the user ID of this opencps voting statistic.
	*
	* @param userId the user ID of this opencps voting statistic
	*/
	@Override
	public void setUserId(long userId) {
		_opencpsVotingStatistic.setUserId(userId);
	}

	/**
	* Sets the user name of this opencps voting statistic.
	*
	* @param userName the user name of this opencps voting statistic
	*/
	@Override
	public void setUserName(String userName) {
		_opencpsVotingStatistic.setUserName(userName);
	}

	/**
	* Sets the user uuid of this opencps voting statistic.
	*
	* @param userUuid the user uuid of this opencps voting statistic
	*/
	@Override
	public void setUserUuid(String userUuid) {
		_opencpsVotingStatistic.setUserUuid(userUuid);
	}

	/**
	* Sets the uuid of this opencps voting statistic.
	*
	* @param uuid the uuid of this opencps voting statistic
	*/
	@Override
	public void setUuid(String uuid) {
		_opencpsVotingStatistic.setUuid(uuid);
	}

	/**
	* Sets the very good count of this opencps voting statistic.
	*
	* @param veryGoodCount the very good count of this opencps voting statistic
	*/
	@Override
	public void setVeryGoodCount(int veryGoodCount) {
		_opencpsVotingStatistic.setVeryGoodCount(veryGoodCount);
	}

	/**
	* Sets the voting code of this opencps voting statistic.
	*
	* @param votingCode the voting code of this opencps voting statistic
	*/
	@Override
	public void setVotingCode(String votingCode) {
		_opencpsVotingStatistic.setVotingCode(votingCode);
	}

	/**
	* Sets the voting statistic ID of this opencps voting statistic.
	*
	* @param votingStatisticId the voting statistic ID of this opencps voting statistic
	*/
	@Override
	public void setVotingStatisticId(long votingStatisticId) {
		_opencpsVotingStatistic.setVotingStatisticId(votingStatisticId);
	}

	/**
	* Sets the voting subject of this opencps voting statistic.
	*
	* @param votingSubject the voting subject of this opencps voting statistic
	*/
	@Override
	public void setVotingSubject(String votingSubject) {
		_opencpsVotingStatistic.setVotingSubject(votingSubject);
	}

	/**
	* Sets the year of this opencps voting statistic.
	*
	* @param year the year of this opencps voting statistic
	*/
	@Override
	public void setYear(int year) {
		_opencpsVotingStatistic.setYear(year);
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<OpencpsVotingStatistic> toCacheModel() {
		return _opencpsVotingStatistic.toCacheModel();
	}

	@Override
	public OpencpsVotingStatistic toEscapedModel() {
		return new OpencpsVotingStatisticWrapper(_opencpsVotingStatistic.toEscapedModel());
	}

	@Override
	public String toString() {
		return _opencpsVotingStatistic.toString();
	}

	@Override
	public OpencpsVotingStatistic toUnescapedModel() {
		return new OpencpsVotingStatisticWrapper(_opencpsVotingStatistic.toUnescapedModel());
	}

	@Override
	public String toXmlString() {
		return _opencpsVotingStatistic.toXmlString();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof OpencpsVotingStatisticWrapper)) {
			return false;
		}

		OpencpsVotingStatisticWrapper opencpsVotingStatisticWrapper = (OpencpsVotingStatisticWrapper)obj;

		if (Objects.equals(_opencpsVotingStatistic,
					opencpsVotingStatisticWrapper._opencpsVotingStatistic)) {
			return true;
		}

		return false;
	}

	@Override
	public StagedModelType getStagedModelType() {
		return _opencpsVotingStatistic.getStagedModelType();
	}

	@Override
	public OpencpsVotingStatistic getWrappedModel() {
		return _opencpsVotingStatistic;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _opencpsVotingStatistic.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _opencpsVotingStatistic.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_opencpsVotingStatistic.resetOriginalValues();
	}

	private final OpencpsVotingStatistic _opencpsVotingStatistic;
}