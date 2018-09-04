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
 * This class is a wrapper for {@link OpencpsDossierStatistic}.
 * </p>
 *
 * @author khoavu
 * @see OpencpsDossierStatistic
 * @generated
 */
@ProviderType
public class OpencpsDossierStatisticWrapper implements OpencpsDossierStatistic,
	ModelWrapper<OpencpsDossierStatistic> {
	public OpencpsDossierStatisticWrapper(
		OpencpsDossierStatistic opencpsDossierStatistic) {
		_opencpsDossierStatistic = opencpsDossierStatistic;
	}

	@Override
	public Class<?> getModelClass() {
		return OpencpsDossierStatistic.class;
	}

	@Override
	public String getModelClassName() {
		return OpencpsDossierStatistic.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("uuid", getUuid());
		attributes.put("dossierStatisticId", getDossierStatisticId());
		attributes.put("companyId", getCompanyId());
		attributes.put("groupId", getGroupId());
		attributes.put("userId", getUserId());
		attributes.put("userName", getUserName());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("month", getMonth());
		attributes.put("year", getYear());
		attributes.put("totalCount", getTotalCount());
		attributes.put("deniedCount", getDeniedCount());
		attributes.put("cancelledCount", getCancelledCount());
		attributes.put("processCount", getProcessCount());
		attributes.put("remainingCount", getRemainingCount());
		attributes.put("receivedCount", getReceivedCount());
		attributes.put("onlineCount", getOnlineCount());
		attributes.put("onegateCount", getOnegateCount());
		attributes.put("releaseCount", getReleaseCount());
		attributes.put("betimesCount", getBetimesCount());
		attributes.put("ontimeCount", getOntimeCount());
		attributes.put("overtimeCount", getOvertimeCount());
		attributes.put("doneCount", getDoneCount());
		attributes.put("releasingCount", getReleasingCount());
		attributes.put("unresolvedCount", getUnresolvedCount());
		attributes.put("processingCount", getProcessingCount());
		attributes.put("undueCount", getUndueCount());
		attributes.put("overdueCount", getOverdueCount());
		attributes.put("pausingCount", getPausingCount());
		attributes.put("ontimePercentage", getOntimePercentage());
		attributes.put("govAgencyCode", getGovAgencyCode());
		attributes.put("groupAgencyCode", getGroupAgencyCode());
		attributes.put("govAgencyName", getGovAgencyName());
		attributes.put("domainCode", getDomainCode());
		attributes.put("domainName", getDomainName());
		attributes.put("reporting", isReporting());
		attributes.put("overtimeInside", getOvertimeInside());
		attributes.put("overtimeOutside", getOvertimeOutside());
		attributes.put("interoperatingCount", getInteroperatingCount());
		attributes.put("waitingCount", getWaitingCount());
		attributes.put("outsideCount", getOutsideCount());
		attributes.put("insideCount", getInsideCount());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		String uuid = (String)attributes.get("uuid");

		if (uuid != null) {
			setUuid(uuid);
		}

		Long dossierStatisticId = (Long)attributes.get("dossierStatisticId");

		if (dossierStatisticId != null) {
			setDossierStatisticId(dossierStatisticId);
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

		Integer totalCount = (Integer)attributes.get("totalCount");

		if (totalCount != null) {
			setTotalCount(totalCount);
		}

		Integer deniedCount = (Integer)attributes.get("deniedCount");

		if (deniedCount != null) {
			setDeniedCount(deniedCount);
		}

		Integer cancelledCount = (Integer)attributes.get("cancelledCount");

		if (cancelledCount != null) {
			setCancelledCount(cancelledCount);
		}

		Integer processCount = (Integer)attributes.get("processCount");

		if (processCount != null) {
			setProcessCount(processCount);
		}

		Integer remainingCount = (Integer)attributes.get("remainingCount");

		if (remainingCount != null) {
			setRemainingCount(remainingCount);
		}

		Integer receivedCount = (Integer)attributes.get("receivedCount");

		if (receivedCount != null) {
			setReceivedCount(receivedCount);
		}

		Integer onlineCount = (Integer)attributes.get("onlineCount");

		if (onlineCount != null) {
			setOnlineCount(onlineCount);
		}

		Integer onegateCount = (Integer)attributes.get("onegateCount");

		if (onegateCount != null) {
			setOnegateCount(onegateCount);
		}

		Integer releaseCount = (Integer)attributes.get("releaseCount");

		if (releaseCount != null) {
			setReleaseCount(releaseCount);
		}

		Integer betimesCount = (Integer)attributes.get("betimesCount");

		if (betimesCount != null) {
			setBetimesCount(betimesCount);
		}

		Integer ontimeCount = (Integer)attributes.get("ontimeCount");

		if (ontimeCount != null) {
			setOntimeCount(ontimeCount);
		}

		Integer overtimeCount = (Integer)attributes.get("overtimeCount");

		if (overtimeCount != null) {
			setOvertimeCount(overtimeCount);
		}

		Integer doneCount = (Integer)attributes.get("doneCount");

		if (doneCount != null) {
			setDoneCount(doneCount);
		}

		Integer releasingCount = (Integer)attributes.get("releasingCount");

		if (releasingCount != null) {
			setReleasingCount(releasingCount);
		}

		Integer unresolvedCount = (Integer)attributes.get("unresolvedCount");

		if (unresolvedCount != null) {
			setUnresolvedCount(unresolvedCount);
		}

		Integer processingCount = (Integer)attributes.get("processingCount");

		if (processingCount != null) {
			setProcessingCount(processingCount);
		}

		Integer undueCount = (Integer)attributes.get("undueCount");

		if (undueCount != null) {
			setUndueCount(undueCount);
		}

		Integer overdueCount = (Integer)attributes.get("overdueCount");

		if (overdueCount != null) {
			setOverdueCount(overdueCount);
		}

		Integer pausingCount = (Integer)attributes.get("pausingCount");

		if (pausingCount != null) {
			setPausingCount(pausingCount);
		}

		Integer ontimePercentage = (Integer)attributes.get("ontimePercentage");

		if (ontimePercentage != null) {
			setOntimePercentage(ontimePercentage);
		}

		String govAgencyCode = (String)attributes.get("govAgencyCode");

		if (govAgencyCode != null) {
			setGovAgencyCode(govAgencyCode);
		}

		String groupAgencyCode = (String)attributes.get("groupAgencyCode");

		if (groupAgencyCode != null) {
			setGroupAgencyCode(groupAgencyCode);
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

		Boolean reporting = (Boolean)attributes.get("reporting");

		if (reporting != null) {
			setReporting(reporting);
		}

		Integer overtimeInside = (Integer)attributes.get("overtimeInside");

		if (overtimeInside != null) {
			setOvertimeInside(overtimeInside);
		}

		Integer overtimeOutside = (Integer)attributes.get("overtimeOutside");

		if (overtimeOutside != null) {
			setOvertimeOutside(overtimeOutside);
		}

		Integer interoperatingCount = (Integer)attributes.get(
				"interoperatingCount");

		if (interoperatingCount != null) {
			setInteroperatingCount(interoperatingCount);
		}

		Integer waitingCount = (Integer)attributes.get("waitingCount");

		if (waitingCount != null) {
			setWaitingCount(waitingCount);
		}

		Integer outsideCount = (Integer)attributes.get("outsideCount");

		if (outsideCount != null) {
			setOutsideCount(outsideCount);
		}

		Integer insideCount = (Integer)attributes.get("insideCount");

		if (insideCount != null) {
			setInsideCount(insideCount);
		}
	}

	@Override
	public Object clone() {
		return new OpencpsDossierStatisticWrapper((OpencpsDossierStatistic)_opencpsDossierStatistic.clone());
	}

	@Override
	public int compareTo(OpencpsDossierStatistic opencpsDossierStatistic) {
		return _opencpsDossierStatistic.compareTo(opencpsDossierStatistic);
	}

	/**
	* Returns the betimes count of this opencps dossier statistic.
	*
	* @return the betimes count of this opencps dossier statistic
	*/
	@Override
	public int getBetimesCount() {
		return _opencpsDossierStatistic.getBetimesCount();
	}

	/**
	* Returns the cancelled count of this opencps dossier statistic.
	*
	* @return the cancelled count of this opencps dossier statistic
	*/
	@Override
	public int getCancelledCount() {
		return _opencpsDossierStatistic.getCancelledCount();
	}

	/**
	* Returns the company ID of this opencps dossier statistic.
	*
	* @return the company ID of this opencps dossier statistic
	*/
	@Override
	public long getCompanyId() {
		return _opencpsDossierStatistic.getCompanyId();
	}

	/**
	* Returns the create date of this opencps dossier statistic.
	*
	* @return the create date of this opencps dossier statistic
	*/
	@Override
	public Date getCreateDate() {
		return _opencpsDossierStatistic.getCreateDate();
	}

	/**
	* Returns the denied count of this opencps dossier statistic.
	*
	* @return the denied count of this opencps dossier statistic
	*/
	@Override
	public int getDeniedCount() {
		return _opencpsDossierStatistic.getDeniedCount();
	}

	/**
	* Returns the domain code of this opencps dossier statistic.
	*
	* @return the domain code of this opencps dossier statistic
	*/
	@Override
	public String getDomainCode() {
		return _opencpsDossierStatistic.getDomainCode();
	}

	/**
	* Returns the domain name of this opencps dossier statistic.
	*
	* @return the domain name of this opencps dossier statistic
	*/
	@Override
	public String getDomainName() {
		return _opencpsDossierStatistic.getDomainName();
	}

	/**
	* Returns the done count of this opencps dossier statistic.
	*
	* @return the done count of this opencps dossier statistic
	*/
	@Override
	public int getDoneCount() {
		return _opencpsDossierStatistic.getDoneCount();
	}

	/**
	* Returns the dossier statistic ID of this opencps dossier statistic.
	*
	* @return the dossier statistic ID of this opencps dossier statistic
	*/
	@Override
	public long getDossierStatisticId() {
		return _opencpsDossierStatistic.getDossierStatisticId();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _opencpsDossierStatistic.getExpandoBridge();
	}

	/**
	* Returns the gov agency code of this opencps dossier statistic.
	*
	* @return the gov agency code of this opencps dossier statistic
	*/
	@Override
	public String getGovAgencyCode() {
		return _opencpsDossierStatistic.getGovAgencyCode();
	}

	/**
	* Returns the gov agency name of this opencps dossier statistic.
	*
	* @return the gov agency name of this opencps dossier statistic
	*/
	@Override
	public String getGovAgencyName() {
		return _opencpsDossierStatistic.getGovAgencyName();
	}

	/**
	* Returns the group agency code of this opencps dossier statistic.
	*
	* @return the group agency code of this opencps dossier statistic
	*/
	@Override
	public String getGroupAgencyCode() {
		return _opencpsDossierStatistic.getGroupAgencyCode();
	}

	/**
	* Returns the group ID of this opencps dossier statistic.
	*
	* @return the group ID of this opencps dossier statistic
	*/
	@Override
	public long getGroupId() {
		return _opencpsDossierStatistic.getGroupId();
	}

	/**
	* Returns the inside count of this opencps dossier statistic.
	*
	* @return the inside count of this opencps dossier statistic
	*/
	@Override
	public int getInsideCount() {
		return _opencpsDossierStatistic.getInsideCount();
	}

	/**
	* Returns the interoperating count of this opencps dossier statistic.
	*
	* @return the interoperating count of this opencps dossier statistic
	*/
	@Override
	public int getInteroperatingCount() {
		return _opencpsDossierStatistic.getInteroperatingCount();
	}

	/**
	* Returns the modified date of this opencps dossier statistic.
	*
	* @return the modified date of this opencps dossier statistic
	*/
	@Override
	public Date getModifiedDate() {
		return _opencpsDossierStatistic.getModifiedDate();
	}

	/**
	* Returns the month of this opencps dossier statistic.
	*
	* @return the month of this opencps dossier statistic
	*/
	@Override
	public int getMonth() {
		return _opencpsDossierStatistic.getMonth();
	}

	/**
	* Returns the onegate count of this opencps dossier statistic.
	*
	* @return the onegate count of this opencps dossier statistic
	*/
	@Override
	public int getOnegateCount() {
		return _opencpsDossierStatistic.getOnegateCount();
	}

	/**
	* Returns the online count of this opencps dossier statistic.
	*
	* @return the online count of this opencps dossier statistic
	*/
	@Override
	public int getOnlineCount() {
		return _opencpsDossierStatistic.getOnlineCount();
	}

	/**
	* Returns the ontime count of this opencps dossier statistic.
	*
	* @return the ontime count of this opencps dossier statistic
	*/
	@Override
	public int getOntimeCount() {
		return _opencpsDossierStatistic.getOntimeCount();
	}

	/**
	* Returns the ontime percentage of this opencps dossier statistic.
	*
	* @return the ontime percentage of this opencps dossier statistic
	*/
	@Override
	public int getOntimePercentage() {
		return _opencpsDossierStatistic.getOntimePercentage();
	}

	/**
	* Returns the outside count of this opencps dossier statistic.
	*
	* @return the outside count of this opencps dossier statistic
	*/
	@Override
	public int getOutsideCount() {
		return _opencpsDossierStatistic.getOutsideCount();
	}

	/**
	* Returns the overdue count of this opencps dossier statistic.
	*
	* @return the overdue count of this opencps dossier statistic
	*/
	@Override
	public int getOverdueCount() {
		return _opencpsDossierStatistic.getOverdueCount();
	}

	/**
	* Returns the overtime count of this opencps dossier statistic.
	*
	* @return the overtime count of this opencps dossier statistic
	*/
	@Override
	public int getOvertimeCount() {
		return _opencpsDossierStatistic.getOvertimeCount();
	}

	/**
	* Returns the overtime inside of this opencps dossier statistic.
	*
	* @return the overtime inside of this opencps dossier statistic
	*/
	@Override
	public int getOvertimeInside() {
		return _opencpsDossierStatistic.getOvertimeInside();
	}

	/**
	* Returns the overtime outside of this opencps dossier statistic.
	*
	* @return the overtime outside of this opencps dossier statistic
	*/
	@Override
	public int getOvertimeOutside() {
		return _opencpsDossierStatistic.getOvertimeOutside();
	}

	/**
	* Returns the pausing count of this opencps dossier statistic.
	*
	* @return the pausing count of this opencps dossier statistic
	*/
	@Override
	public int getPausingCount() {
		return _opencpsDossierStatistic.getPausingCount();
	}

	/**
	* Returns the primary key of this opencps dossier statistic.
	*
	* @return the primary key of this opencps dossier statistic
	*/
	@Override
	public long getPrimaryKey() {
		return _opencpsDossierStatistic.getPrimaryKey();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _opencpsDossierStatistic.getPrimaryKeyObj();
	}

	/**
	* Returns the process count of this opencps dossier statistic.
	*
	* @return the process count of this opencps dossier statistic
	*/
	@Override
	public int getProcessCount() {
		return _opencpsDossierStatistic.getProcessCount();
	}

	/**
	* Returns the processing count of this opencps dossier statistic.
	*
	* @return the processing count of this opencps dossier statistic
	*/
	@Override
	public int getProcessingCount() {
		return _opencpsDossierStatistic.getProcessingCount();
	}

	/**
	* Returns the received count of this opencps dossier statistic.
	*
	* @return the received count of this opencps dossier statistic
	*/
	@Override
	public int getReceivedCount() {
		return _opencpsDossierStatistic.getReceivedCount();
	}

	/**
	* Returns the release count of this opencps dossier statistic.
	*
	* @return the release count of this opencps dossier statistic
	*/
	@Override
	public int getReleaseCount() {
		return _opencpsDossierStatistic.getReleaseCount();
	}

	/**
	* Returns the releasing count of this opencps dossier statistic.
	*
	* @return the releasing count of this opencps dossier statistic
	*/
	@Override
	public int getReleasingCount() {
		return _opencpsDossierStatistic.getReleasingCount();
	}

	/**
	* Returns the remaining count of this opencps dossier statistic.
	*
	* @return the remaining count of this opencps dossier statistic
	*/
	@Override
	public int getRemainingCount() {
		return _opencpsDossierStatistic.getRemainingCount();
	}

	/**
	* Returns the reporting of this opencps dossier statistic.
	*
	* @return the reporting of this opencps dossier statistic
	*/
	@Override
	public boolean getReporting() {
		return _opencpsDossierStatistic.getReporting();
	}

	/**
	* Returns the total count of this opencps dossier statistic.
	*
	* @return the total count of this opencps dossier statistic
	*/
	@Override
	public int getTotalCount() {
		return _opencpsDossierStatistic.getTotalCount();
	}

	/**
	* Returns the undue count of this opencps dossier statistic.
	*
	* @return the undue count of this opencps dossier statistic
	*/
	@Override
	public int getUndueCount() {
		return _opencpsDossierStatistic.getUndueCount();
	}

	/**
	* Returns the unresolved count of this opencps dossier statistic.
	*
	* @return the unresolved count of this opencps dossier statistic
	*/
	@Override
	public int getUnresolvedCount() {
		return _opencpsDossierStatistic.getUnresolvedCount();
	}

	/**
	* Returns the user ID of this opencps dossier statistic.
	*
	* @return the user ID of this opencps dossier statistic
	*/
	@Override
	public long getUserId() {
		return _opencpsDossierStatistic.getUserId();
	}

	/**
	* Returns the user name of this opencps dossier statistic.
	*
	* @return the user name of this opencps dossier statistic
	*/
	@Override
	public String getUserName() {
		return _opencpsDossierStatistic.getUserName();
	}

	/**
	* Returns the user uuid of this opencps dossier statistic.
	*
	* @return the user uuid of this opencps dossier statistic
	*/
	@Override
	public String getUserUuid() {
		return _opencpsDossierStatistic.getUserUuid();
	}

	/**
	* Returns the uuid of this opencps dossier statistic.
	*
	* @return the uuid of this opencps dossier statistic
	*/
	@Override
	public String getUuid() {
		return _opencpsDossierStatistic.getUuid();
	}

	/**
	* Returns the waiting count of this opencps dossier statistic.
	*
	* @return the waiting count of this opencps dossier statistic
	*/
	@Override
	public int getWaitingCount() {
		return _opencpsDossierStatistic.getWaitingCount();
	}

	/**
	* Returns the year of this opencps dossier statistic.
	*
	* @return the year of this opencps dossier statistic
	*/
	@Override
	public int getYear() {
		return _opencpsDossierStatistic.getYear();
	}

	@Override
	public int hashCode() {
		return _opencpsDossierStatistic.hashCode();
	}

	@Override
	public boolean isCachedModel() {
		return _opencpsDossierStatistic.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _opencpsDossierStatistic.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _opencpsDossierStatistic.isNew();
	}

	/**
	* Returns <code>true</code> if this opencps dossier statistic is reporting.
	*
	* @return <code>true</code> if this opencps dossier statistic is reporting; <code>false</code> otherwise
	*/
	@Override
	public boolean isReporting() {
		return _opencpsDossierStatistic.isReporting();
	}

	@Override
	public void persist() {
		_opencpsDossierStatistic.persist();
	}

	/**
	* Sets the betimes count of this opencps dossier statistic.
	*
	* @param betimesCount the betimes count of this opencps dossier statistic
	*/
	@Override
	public void setBetimesCount(int betimesCount) {
		_opencpsDossierStatistic.setBetimesCount(betimesCount);
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_opencpsDossierStatistic.setCachedModel(cachedModel);
	}

	/**
	* Sets the cancelled count of this opencps dossier statistic.
	*
	* @param cancelledCount the cancelled count of this opencps dossier statistic
	*/
	@Override
	public void setCancelledCount(int cancelledCount) {
		_opencpsDossierStatistic.setCancelledCount(cancelledCount);
	}

	/**
	* Sets the company ID of this opencps dossier statistic.
	*
	* @param companyId the company ID of this opencps dossier statistic
	*/
	@Override
	public void setCompanyId(long companyId) {
		_opencpsDossierStatistic.setCompanyId(companyId);
	}

	/**
	* Sets the create date of this opencps dossier statistic.
	*
	* @param createDate the create date of this opencps dossier statistic
	*/
	@Override
	public void setCreateDate(Date createDate) {
		_opencpsDossierStatistic.setCreateDate(createDate);
	}

	/**
	* Sets the denied count of this opencps dossier statistic.
	*
	* @param deniedCount the denied count of this opencps dossier statistic
	*/
	@Override
	public void setDeniedCount(int deniedCount) {
		_opencpsDossierStatistic.setDeniedCount(deniedCount);
	}

	/**
	* Sets the domain code of this opencps dossier statistic.
	*
	* @param domainCode the domain code of this opencps dossier statistic
	*/
	@Override
	public void setDomainCode(String domainCode) {
		_opencpsDossierStatistic.setDomainCode(domainCode);
	}

	/**
	* Sets the domain name of this opencps dossier statistic.
	*
	* @param domainName the domain name of this opencps dossier statistic
	*/
	@Override
	public void setDomainName(String domainName) {
		_opencpsDossierStatistic.setDomainName(domainName);
	}

	/**
	* Sets the done count of this opencps dossier statistic.
	*
	* @param doneCount the done count of this opencps dossier statistic
	*/
	@Override
	public void setDoneCount(int doneCount) {
		_opencpsDossierStatistic.setDoneCount(doneCount);
	}

	/**
	* Sets the dossier statistic ID of this opencps dossier statistic.
	*
	* @param dossierStatisticId the dossier statistic ID of this opencps dossier statistic
	*/
	@Override
	public void setDossierStatisticId(long dossierStatisticId) {
		_opencpsDossierStatistic.setDossierStatisticId(dossierStatisticId);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_opencpsDossierStatistic.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_opencpsDossierStatistic.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_opencpsDossierStatistic.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the gov agency code of this opencps dossier statistic.
	*
	* @param govAgencyCode the gov agency code of this opencps dossier statistic
	*/
	@Override
	public void setGovAgencyCode(String govAgencyCode) {
		_opencpsDossierStatistic.setGovAgencyCode(govAgencyCode);
	}

	/**
	* Sets the gov agency name of this opencps dossier statistic.
	*
	* @param govAgencyName the gov agency name of this opencps dossier statistic
	*/
	@Override
	public void setGovAgencyName(String govAgencyName) {
		_opencpsDossierStatistic.setGovAgencyName(govAgencyName);
	}

	/**
	* Sets the group agency code of this opencps dossier statistic.
	*
	* @param groupAgencyCode the group agency code of this opencps dossier statistic
	*/
	@Override
	public void setGroupAgencyCode(String groupAgencyCode) {
		_opencpsDossierStatistic.setGroupAgencyCode(groupAgencyCode);
	}

	/**
	* Sets the group ID of this opencps dossier statistic.
	*
	* @param groupId the group ID of this opencps dossier statistic
	*/
	@Override
	public void setGroupId(long groupId) {
		_opencpsDossierStatistic.setGroupId(groupId);
	}

	/**
	* Sets the inside count of this opencps dossier statistic.
	*
	* @param insideCount the inside count of this opencps dossier statistic
	*/
	@Override
	public void setInsideCount(int insideCount) {
		_opencpsDossierStatistic.setInsideCount(insideCount);
	}

	/**
	* Sets the interoperating count of this opencps dossier statistic.
	*
	* @param interoperatingCount the interoperating count of this opencps dossier statistic
	*/
	@Override
	public void setInteroperatingCount(int interoperatingCount) {
		_opencpsDossierStatistic.setInteroperatingCount(interoperatingCount);
	}

	/**
	* Sets the modified date of this opencps dossier statistic.
	*
	* @param modifiedDate the modified date of this opencps dossier statistic
	*/
	@Override
	public void setModifiedDate(Date modifiedDate) {
		_opencpsDossierStatistic.setModifiedDate(modifiedDate);
	}

	/**
	* Sets the month of this opencps dossier statistic.
	*
	* @param month the month of this opencps dossier statistic
	*/
	@Override
	public void setMonth(int month) {
		_opencpsDossierStatistic.setMonth(month);
	}

	@Override
	public void setNew(boolean n) {
		_opencpsDossierStatistic.setNew(n);
	}

	/**
	* Sets the onegate count of this opencps dossier statistic.
	*
	* @param onegateCount the onegate count of this opencps dossier statistic
	*/
	@Override
	public void setOnegateCount(int onegateCount) {
		_opencpsDossierStatistic.setOnegateCount(onegateCount);
	}

	/**
	* Sets the online count of this opencps dossier statistic.
	*
	* @param onlineCount the online count of this opencps dossier statistic
	*/
	@Override
	public void setOnlineCount(int onlineCount) {
		_opencpsDossierStatistic.setOnlineCount(onlineCount);
	}

	/**
	* Sets the ontime count of this opencps dossier statistic.
	*
	* @param ontimeCount the ontime count of this opencps dossier statistic
	*/
	@Override
	public void setOntimeCount(int ontimeCount) {
		_opencpsDossierStatistic.setOntimeCount(ontimeCount);
	}

	/**
	* Sets the ontime percentage of this opencps dossier statistic.
	*
	* @param ontimePercentage the ontime percentage of this opencps dossier statistic
	*/
	@Override
	public void setOntimePercentage(int ontimePercentage) {
		_opencpsDossierStatistic.setOntimePercentage(ontimePercentage);
	}

	/**
	* Sets the outside count of this opencps dossier statistic.
	*
	* @param outsideCount the outside count of this opencps dossier statistic
	*/
	@Override
	public void setOutsideCount(int outsideCount) {
		_opencpsDossierStatistic.setOutsideCount(outsideCount);
	}

	/**
	* Sets the overdue count of this opencps dossier statistic.
	*
	* @param overdueCount the overdue count of this opencps dossier statistic
	*/
	@Override
	public void setOverdueCount(int overdueCount) {
		_opencpsDossierStatistic.setOverdueCount(overdueCount);
	}

	/**
	* Sets the overtime count of this opencps dossier statistic.
	*
	* @param overtimeCount the overtime count of this opencps dossier statistic
	*/
	@Override
	public void setOvertimeCount(int overtimeCount) {
		_opencpsDossierStatistic.setOvertimeCount(overtimeCount);
	}

	/**
	* Sets the overtime inside of this opencps dossier statistic.
	*
	* @param overtimeInside the overtime inside of this opencps dossier statistic
	*/
	@Override
	public void setOvertimeInside(int overtimeInside) {
		_opencpsDossierStatistic.setOvertimeInside(overtimeInside);
	}

	/**
	* Sets the overtime outside of this opencps dossier statistic.
	*
	* @param overtimeOutside the overtime outside of this opencps dossier statistic
	*/
	@Override
	public void setOvertimeOutside(int overtimeOutside) {
		_opencpsDossierStatistic.setOvertimeOutside(overtimeOutside);
	}

	/**
	* Sets the pausing count of this opencps dossier statistic.
	*
	* @param pausingCount the pausing count of this opencps dossier statistic
	*/
	@Override
	public void setPausingCount(int pausingCount) {
		_opencpsDossierStatistic.setPausingCount(pausingCount);
	}

	/**
	* Sets the primary key of this opencps dossier statistic.
	*
	* @param primaryKey the primary key of this opencps dossier statistic
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_opencpsDossierStatistic.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_opencpsDossierStatistic.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the process count of this opencps dossier statistic.
	*
	* @param processCount the process count of this opencps dossier statistic
	*/
	@Override
	public void setProcessCount(int processCount) {
		_opencpsDossierStatistic.setProcessCount(processCount);
	}

	/**
	* Sets the processing count of this opencps dossier statistic.
	*
	* @param processingCount the processing count of this opencps dossier statistic
	*/
	@Override
	public void setProcessingCount(int processingCount) {
		_opencpsDossierStatistic.setProcessingCount(processingCount);
	}

	/**
	* Sets the received count of this opencps dossier statistic.
	*
	* @param receivedCount the received count of this opencps dossier statistic
	*/
	@Override
	public void setReceivedCount(int receivedCount) {
		_opencpsDossierStatistic.setReceivedCount(receivedCount);
	}

	/**
	* Sets the release count of this opencps dossier statistic.
	*
	* @param releaseCount the release count of this opencps dossier statistic
	*/
	@Override
	public void setReleaseCount(int releaseCount) {
		_opencpsDossierStatistic.setReleaseCount(releaseCount);
	}

	/**
	* Sets the releasing count of this opencps dossier statistic.
	*
	* @param releasingCount the releasing count of this opencps dossier statistic
	*/
	@Override
	public void setReleasingCount(int releasingCount) {
		_opencpsDossierStatistic.setReleasingCount(releasingCount);
	}

	/**
	* Sets the remaining count of this opencps dossier statistic.
	*
	* @param remainingCount the remaining count of this opencps dossier statistic
	*/
	@Override
	public void setRemainingCount(int remainingCount) {
		_opencpsDossierStatistic.setRemainingCount(remainingCount);
	}

	/**
	* Sets whether this opencps dossier statistic is reporting.
	*
	* @param reporting the reporting of this opencps dossier statistic
	*/
	@Override
	public void setReporting(boolean reporting) {
		_opencpsDossierStatistic.setReporting(reporting);
	}

	/**
	* Sets the total count of this opencps dossier statistic.
	*
	* @param totalCount the total count of this opencps dossier statistic
	*/
	@Override
	public void setTotalCount(int totalCount) {
		_opencpsDossierStatistic.setTotalCount(totalCount);
	}

	/**
	* Sets the undue count of this opencps dossier statistic.
	*
	* @param undueCount the undue count of this opencps dossier statistic
	*/
	@Override
	public void setUndueCount(int undueCount) {
		_opencpsDossierStatistic.setUndueCount(undueCount);
	}

	/**
	* Sets the unresolved count of this opencps dossier statistic.
	*
	* @param unresolvedCount the unresolved count of this opencps dossier statistic
	*/
	@Override
	public void setUnresolvedCount(int unresolvedCount) {
		_opencpsDossierStatistic.setUnresolvedCount(unresolvedCount);
	}

	/**
	* Sets the user ID of this opencps dossier statistic.
	*
	* @param userId the user ID of this opencps dossier statistic
	*/
	@Override
	public void setUserId(long userId) {
		_opencpsDossierStatistic.setUserId(userId);
	}

	/**
	* Sets the user name of this opencps dossier statistic.
	*
	* @param userName the user name of this opencps dossier statistic
	*/
	@Override
	public void setUserName(String userName) {
		_opencpsDossierStatistic.setUserName(userName);
	}

	/**
	* Sets the user uuid of this opencps dossier statistic.
	*
	* @param userUuid the user uuid of this opencps dossier statistic
	*/
	@Override
	public void setUserUuid(String userUuid) {
		_opencpsDossierStatistic.setUserUuid(userUuid);
	}

	/**
	* Sets the uuid of this opencps dossier statistic.
	*
	* @param uuid the uuid of this opencps dossier statistic
	*/
	@Override
	public void setUuid(String uuid) {
		_opencpsDossierStatistic.setUuid(uuid);
	}

	/**
	* Sets the waiting count of this opencps dossier statistic.
	*
	* @param waitingCount the waiting count of this opencps dossier statistic
	*/
	@Override
	public void setWaitingCount(int waitingCount) {
		_opencpsDossierStatistic.setWaitingCount(waitingCount);
	}

	/**
	* Sets the year of this opencps dossier statistic.
	*
	* @param year the year of this opencps dossier statistic
	*/
	@Override
	public void setYear(int year) {
		_opencpsDossierStatistic.setYear(year);
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<OpencpsDossierStatistic> toCacheModel() {
		return _opencpsDossierStatistic.toCacheModel();
	}

	@Override
	public OpencpsDossierStatistic toEscapedModel() {
		return new OpencpsDossierStatisticWrapper(_opencpsDossierStatistic.toEscapedModel());
	}

	@Override
	public String toString() {
		return _opencpsDossierStatistic.toString();
	}

	@Override
	public OpencpsDossierStatistic toUnescapedModel() {
		return new OpencpsDossierStatisticWrapper(_opencpsDossierStatistic.toUnescapedModel());
	}

	@Override
	public String toXmlString() {
		return _opencpsDossierStatistic.toXmlString();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof OpencpsDossierStatisticWrapper)) {
			return false;
		}

		OpencpsDossierStatisticWrapper opencpsDossierStatisticWrapper = (OpencpsDossierStatisticWrapper)obj;

		if (Objects.equals(_opencpsDossierStatistic,
					opencpsDossierStatisticWrapper._opencpsDossierStatistic)) {
			return true;
		}

		return false;
	}

	@Override
	public StagedModelType getStagedModelType() {
		return _opencpsDossierStatistic.getStagedModelType();
	}

	@Override
	public OpencpsDossierStatistic getWrappedModel() {
		return _opencpsDossierStatistic;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _opencpsDossierStatistic.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _opencpsDossierStatistic.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_opencpsDossierStatistic.resetOriginalValues();
	}

	private final OpencpsDossierStatistic _opencpsDossierStatistic;
}