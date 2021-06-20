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

import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.service.ServiceContext;

import java.io.Serializable;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * <p>
 * This class is a wrapper for {@link OpencpsDossierStatisticMgt}.
 * </p>
 *
 * @author khoavu
 * @see OpencpsDossierStatisticMgt
 * @generated
 */
@ProviderType
public class OpencpsDossierStatisticMgtWrapper
	implements OpencpsDossierStatisticMgt,
		ModelWrapper<OpencpsDossierStatisticMgt> {
	public OpencpsDossierStatisticMgtWrapper(
		OpencpsDossierStatisticMgt opencpsDossierStatisticMgt) {
		_opencpsDossierStatisticMgt = opencpsDossierStatisticMgt;
	}

	@Override
	public Class<?> getModelClass() {
		return OpencpsDossierStatisticMgt.class;
	}

	@Override
	public String getModelClassName() {
		return OpencpsDossierStatisticMgt.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("uuid", getUuid());
		attributes.put("dossierStatisticMgtId", getDossierStatisticMgtId());
		attributes.put("groupId", getGroupId());
		attributes.put("userId", getUserId());
		attributes.put("userName", getUserName());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("month", getMonth());
		attributes.put("year", getYear());
		attributes.put("totalCount", getTotalCount());
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
		attributes.put("processingCount", getProcessingCount());
		attributes.put("undueCount", getUndueCount());
		attributes.put("overdueCount", getOverdueCount());
		attributes.put("ontimePercentage", getOntimePercentage());
		attributes.put("waitingCount", getWaitingCount());
		attributes.put("govAgencyCode", getGovAgencyCode());
		attributes.put("govAgencyName", getGovAgencyName());
		attributes.put("domainCode", getDomainCode());
		attributes.put("domainName", getDomainName());
		attributes.put("serviceCode", getServiceCode());
		attributes.put("serviceName", getServiceName());
		attributes.put("groupBy", getGroupBy());
		attributes.put("cancelledCount", getCancelledCount());
		attributes.put("unresolvedCount", getUnresolvedCount());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		String uuid = (String)attributes.get("uuid");

		if (uuid != null) {
			setUuid(uuid);
		}

		Long dossierStatisticMgtId = (Long)attributes.get(
				"dossierStatisticMgtId");

		if (dossierStatisticMgtId != null) {
			setDossierStatisticMgtId(dossierStatisticMgtId);
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

		Integer ontimePercentage = (Integer)attributes.get("ontimePercentage");

		if (ontimePercentage != null) {
			setOntimePercentage(ontimePercentage);
		}

		Integer waitingCount = (Integer)attributes.get("waitingCount");

		if (waitingCount != null) {
			setWaitingCount(waitingCount);
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

		String serviceCode = (String)attributes.get("serviceCode");

		if (serviceCode != null) {
			setServiceCode(serviceCode);
		}

		String serviceName = (String)attributes.get("serviceName");

		if (serviceName != null) {
			setServiceName(serviceName);
		}

		Integer groupBy = (Integer)attributes.get("groupBy");

		if (groupBy != null) {
			setGroupBy(groupBy);
		}

		Integer cancelledCount = (Integer)attributes.get("cancelledCount");

		if (cancelledCount != null) {
			setCancelledCount(cancelledCount);
		}

		Integer unresolvedCount = (Integer)attributes.get("unresolvedCount");

		if (unresolvedCount != null) {
			setUnresolvedCount(unresolvedCount);
		}
	}

	@Override
	public Object clone() {
		return new OpencpsDossierStatisticMgtWrapper((OpencpsDossierStatisticMgt)_opencpsDossierStatisticMgt.clone());
	}

	@Override
	public int compareTo(OpencpsDossierStatisticMgt opencpsDossierStatisticMgt) {
		return _opencpsDossierStatisticMgt.compareTo(opencpsDossierStatisticMgt);
	}

	/**
	* Returns the betimes count of this opencps dossier statistic mgt.
	*
	* @return the betimes count of this opencps dossier statistic mgt
	*/
	@Override
	public int getBetimesCount() {
		return _opencpsDossierStatisticMgt.getBetimesCount();
	}

	/**
	* Returns the cancelled count of this opencps dossier statistic mgt.
	*
	* @return the cancelled count of this opencps dossier statistic mgt
	*/
	@Override
	public int getCancelledCount() {
		return _opencpsDossierStatisticMgt.getCancelledCount();
	}

	/**
	* Returns the create date of this opencps dossier statistic mgt.
	*
	* @return the create date of this opencps dossier statistic mgt
	*/
	@Override
	public Date getCreateDate() {
		return _opencpsDossierStatisticMgt.getCreateDate();
	}

	/**
	* Returns the domain code of this opencps dossier statistic mgt.
	*
	* @return the domain code of this opencps dossier statistic mgt
	*/
	@Override
	public String getDomainCode() {
		return _opencpsDossierStatisticMgt.getDomainCode();
	}

	/**
	* Returns the domain name of this opencps dossier statistic mgt.
	*
	* @return the domain name of this opencps dossier statistic mgt
	*/
	@Override
	public String getDomainName() {
		return _opencpsDossierStatisticMgt.getDomainName();
	}

	/**
	* Returns the done count of this opencps dossier statistic mgt.
	*
	* @return the done count of this opencps dossier statistic mgt
	*/
	@Override
	public int getDoneCount() {
		return _opencpsDossierStatisticMgt.getDoneCount();
	}

	/**
	* Returns the dossier statistic mgt ID of this opencps dossier statistic mgt.
	*
	* @return the dossier statistic mgt ID of this opencps dossier statistic mgt
	*/
	@Override
	public long getDossierStatisticMgtId() {
		return _opencpsDossierStatisticMgt.getDossierStatisticMgtId();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _opencpsDossierStatisticMgt.getExpandoBridge();
	}

	/**
	* Returns the gov agency code of this opencps dossier statistic mgt.
	*
	* @return the gov agency code of this opencps dossier statistic mgt
	*/
	@Override
	public String getGovAgencyCode() {
		return _opencpsDossierStatisticMgt.getGovAgencyCode();
	}

	/**
	* Returns the gov agency name of this opencps dossier statistic mgt.
	*
	* @return the gov agency name of this opencps dossier statistic mgt
	*/
	@Override
	public String getGovAgencyName() {
		return _opencpsDossierStatisticMgt.getGovAgencyName();
	}

	/**
	* Returns the group by of this opencps dossier statistic mgt.
	*
	* @return the group by of this opencps dossier statistic mgt
	*/
	@Override
	public int getGroupBy() {
		return _opencpsDossierStatisticMgt.getGroupBy();
	}

	/**
	* Returns the group ID of this opencps dossier statistic mgt.
	*
	* @return the group ID of this opencps dossier statistic mgt
	*/
	@Override
	public long getGroupId() {
		return _opencpsDossierStatisticMgt.getGroupId();
	}

	/**
	* Returns the modified date of this opencps dossier statistic mgt.
	*
	* @return the modified date of this opencps dossier statistic mgt
	*/
	@Override
	public Date getModifiedDate() {
		return _opencpsDossierStatisticMgt.getModifiedDate();
	}

	/**
	* Returns the month of this opencps dossier statistic mgt.
	*
	* @return the month of this opencps dossier statistic mgt
	*/
	@Override
	public int getMonth() {
		return _opencpsDossierStatisticMgt.getMonth();
	}

	/**
	* Returns the onegate count of this opencps dossier statistic mgt.
	*
	* @return the onegate count of this opencps dossier statistic mgt
	*/
	@Override
	public int getOnegateCount() {
		return _opencpsDossierStatisticMgt.getOnegateCount();
	}

	/**
	* Returns the online count of this opencps dossier statistic mgt.
	*
	* @return the online count of this opencps dossier statistic mgt
	*/
	@Override
	public int getOnlineCount() {
		return _opencpsDossierStatisticMgt.getOnlineCount();
	}

	/**
	* Returns the ontime count of this opencps dossier statistic mgt.
	*
	* @return the ontime count of this opencps dossier statistic mgt
	*/
	@Override
	public int getOntimeCount() {
		return _opencpsDossierStatisticMgt.getOntimeCount();
	}

	/**
	* Returns the ontime percentage of this opencps dossier statistic mgt.
	*
	* @return the ontime percentage of this opencps dossier statistic mgt
	*/
	@Override
	public int getOntimePercentage() {
		return _opencpsDossierStatisticMgt.getOntimePercentage();
	}

	/**
	* Returns the overdue count of this opencps dossier statistic mgt.
	*
	* @return the overdue count of this opencps dossier statistic mgt
	*/
	@Override
	public int getOverdueCount() {
		return _opencpsDossierStatisticMgt.getOverdueCount();
	}

	/**
	* Returns the overtime count of this opencps dossier statistic mgt.
	*
	* @return the overtime count of this opencps dossier statistic mgt
	*/
	@Override
	public int getOvertimeCount() {
		return _opencpsDossierStatisticMgt.getOvertimeCount();
	}

	/**
	* Returns the primary key of this opencps dossier statistic mgt.
	*
	* @return the primary key of this opencps dossier statistic mgt
	*/
	@Override
	public long getPrimaryKey() {
		return _opencpsDossierStatisticMgt.getPrimaryKey();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _opencpsDossierStatisticMgt.getPrimaryKeyObj();
	}

	/**
	* Returns the process count of this opencps dossier statistic mgt.
	*
	* @return the process count of this opencps dossier statistic mgt
	*/
	@Override
	public int getProcessCount() {
		return _opencpsDossierStatisticMgt.getProcessCount();
	}

	/**
	* Returns the processing count of this opencps dossier statistic mgt.
	*
	* @return the processing count of this opencps dossier statistic mgt
	*/
	@Override
	public int getProcessingCount() {
		return _opencpsDossierStatisticMgt.getProcessingCount();
	}

	/**
	* Returns the received count of this opencps dossier statistic mgt.
	*
	* @return the received count of this opencps dossier statistic mgt
	*/
	@Override
	public int getReceivedCount() {
		return _opencpsDossierStatisticMgt.getReceivedCount();
	}

	/**
	* Returns the release count of this opencps dossier statistic mgt.
	*
	* @return the release count of this opencps dossier statistic mgt
	*/
	@Override
	public int getReleaseCount() {
		return _opencpsDossierStatisticMgt.getReleaseCount();
	}

	/**
	* Returns the releasing count of this opencps dossier statistic mgt.
	*
	* @return the releasing count of this opencps dossier statistic mgt
	*/
	@Override
	public int getReleasingCount() {
		return _opencpsDossierStatisticMgt.getReleasingCount();
	}

	/**
	* Returns the remaining count of this opencps dossier statistic mgt.
	*
	* @return the remaining count of this opencps dossier statistic mgt
	*/
	@Override
	public int getRemainingCount() {
		return _opencpsDossierStatisticMgt.getRemainingCount();
	}

	/**
	* Returns the service code of this opencps dossier statistic mgt.
	*
	* @return the service code of this opencps dossier statistic mgt
	*/
	@Override
	public String getServiceCode() {
		return _opencpsDossierStatisticMgt.getServiceCode();
	}

	/**
	* Returns the service name of this opencps dossier statistic mgt.
	*
	* @return the service name of this opencps dossier statistic mgt
	*/
	@Override
	public String getServiceName() {
		return _opencpsDossierStatisticMgt.getServiceName();
	}

	/**
	* Returns the total count of this opencps dossier statistic mgt.
	*
	* @return the total count of this opencps dossier statistic mgt
	*/
	@Override
	public int getTotalCount() {
		return _opencpsDossierStatisticMgt.getTotalCount();
	}

	/**
	* Returns the undue count of this opencps dossier statistic mgt.
	*
	* @return the undue count of this opencps dossier statistic mgt
	*/
	@Override
	public int getUndueCount() {
		return _opencpsDossierStatisticMgt.getUndueCount();
	}

	/**
	* Returns the unresolved count of this opencps dossier statistic mgt.
	*
	* @return the unresolved count of this opencps dossier statistic mgt
	*/
	@Override
	public int getUnresolvedCount() {
		return _opencpsDossierStatisticMgt.getUnresolvedCount();
	}

	/**
	* Returns the user ID of this opencps dossier statistic mgt.
	*
	* @return the user ID of this opencps dossier statistic mgt
	*/
	@Override
	public long getUserId() {
		return _opencpsDossierStatisticMgt.getUserId();
	}

	/**
	* Returns the user name of this opencps dossier statistic mgt.
	*
	* @return the user name of this opencps dossier statistic mgt
	*/
	@Override
	public String getUserName() {
		return _opencpsDossierStatisticMgt.getUserName();
	}

	/**
	* Returns the user uuid of this opencps dossier statistic mgt.
	*
	* @return the user uuid of this opencps dossier statistic mgt
	*/
	@Override
	public String getUserUuid() {
		return _opencpsDossierStatisticMgt.getUserUuid();
	}

	/**
	* Returns the uuid of this opencps dossier statistic mgt.
	*
	* @return the uuid of this opencps dossier statistic mgt
	*/
	@Override
	public String getUuid() {
		return _opencpsDossierStatisticMgt.getUuid();
	}

	/**
	* Returns the waiting count of this opencps dossier statistic mgt.
	*
	* @return the waiting count of this opencps dossier statistic mgt
	*/
	@Override
	public int getWaitingCount() {
		return _opencpsDossierStatisticMgt.getWaitingCount();
	}

	/**
	* Returns the year of this opencps dossier statistic mgt.
	*
	* @return the year of this opencps dossier statistic mgt
	*/
	@Override
	public int getYear() {
		return _opencpsDossierStatisticMgt.getYear();
	}

	@Override
	public int hashCode() {
		return _opencpsDossierStatisticMgt.hashCode();
	}

	@Override
	public boolean isCachedModel() {
		return _opencpsDossierStatisticMgt.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _opencpsDossierStatisticMgt.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _opencpsDossierStatisticMgt.isNew();
	}

	@Override
	public void persist() {
		_opencpsDossierStatisticMgt.persist();
	}

	/**
	* Sets the betimes count of this opencps dossier statistic mgt.
	*
	* @param betimesCount the betimes count of this opencps dossier statistic mgt
	*/
	@Override
	public void setBetimesCount(int betimesCount) {
		_opencpsDossierStatisticMgt.setBetimesCount(betimesCount);
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_opencpsDossierStatisticMgt.setCachedModel(cachedModel);
	}

	/**
	* Sets the cancelled count of this opencps dossier statistic mgt.
	*
	* @param cancelledCount the cancelled count of this opencps dossier statistic mgt
	*/
	@Override
	public void setCancelledCount(int cancelledCount) {
		_opencpsDossierStatisticMgt.setCancelledCount(cancelledCount);
	}

	/**
	* Sets the create date of this opencps dossier statistic mgt.
	*
	* @param createDate the create date of this opencps dossier statistic mgt
	*/
	@Override
	public void setCreateDate(Date createDate) {
		_opencpsDossierStatisticMgt.setCreateDate(createDate);
	}

	/**
	* Sets the domain code of this opencps dossier statistic mgt.
	*
	* @param domainCode the domain code of this opencps dossier statistic mgt
	*/
	@Override
	public void setDomainCode(String domainCode) {
		_opencpsDossierStatisticMgt.setDomainCode(domainCode);
	}

	/**
	* Sets the domain name of this opencps dossier statistic mgt.
	*
	* @param domainName the domain name of this opencps dossier statistic mgt
	*/
	@Override
	public void setDomainName(String domainName) {
		_opencpsDossierStatisticMgt.setDomainName(domainName);
	}

	/**
	* Sets the done count of this opencps dossier statistic mgt.
	*
	* @param doneCount the done count of this opencps dossier statistic mgt
	*/
	@Override
	public void setDoneCount(int doneCount) {
		_opencpsDossierStatisticMgt.setDoneCount(doneCount);
	}

	/**
	* Sets the dossier statistic mgt ID of this opencps dossier statistic mgt.
	*
	* @param dossierStatisticMgtId the dossier statistic mgt ID of this opencps dossier statistic mgt
	*/
	@Override
	public void setDossierStatisticMgtId(long dossierStatisticMgtId) {
		_opencpsDossierStatisticMgt.setDossierStatisticMgtId(dossierStatisticMgtId);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_opencpsDossierStatisticMgt.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_opencpsDossierStatisticMgt.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_opencpsDossierStatisticMgt.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the gov agency code of this opencps dossier statistic mgt.
	*
	* @param govAgencyCode the gov agency code of this opencps dossier statistic mgt
	*/
	@Override
	public void setGovAgencyCode(String govAgencyCode) {
		_opencpsDossierStatisticMgt.setGovAgencyCode(govAgencyCode);
	}

	/**
	* Sets the gov agency name of this opencps dossier statistic mgt.
	*
	* @param govAgencyName the gov agency name of this opencps dossier statistic mgt
	*/
	@Override
	public void setGovAgencyName(String govAgencyName) {
		_opencpsDossierStatisticMgt.setGovAgencyName(govAgencyName);
	}

	/**
	* Sets the group by of this opencps dossier statistic mgt.
	*
	* @param groupBy the group by of this opencps dossier statistic mgt
	*/
	@Override
	public void setGroupBy(int groupBy) {
		_opencpsDossierStatisticMgt.setGroupBy(groupBy);
	}

	/**
	* Sets the group ID of this opencps dossier statistic mgt.
	*
	* @param groupId the group ID of this opencps dossier statistic mgt
	*/
	@Override
	public void setGroupId(long groupId) {
		_opencpsDossierStatisticMgt.setGroupId(groupId);
	}

	/**
	* Sets the modified date of this opencps dossier statistic mgt.
	*
	* @param modifiedDate the modified date of this opencps dossier statistic mgt
	*/
	@Override
	public void setModifiedDate(Date modifiedDate) {
		_opencpsDossierStatisticMgt.setModifiedDate(modifiedDate);
	}

	/**
	* Sets the month of this opencps dossier statistic mgt.
	*
	* @param month the month of this opencps dossier statistic mgt
	*/
	@Override
	public void setMonth(int month) {
		_opencpsDossierStatisticMgt.setMonth(month);
	}

	@Override
	public void setNew(boolean n) {
		_opencpsDossierStatisticMgt.setNew(n);
	}

	/**
	* Sets the onegate count of this opencps dossier statistic mgt.
	*
	* @param onegateCount the onegate count of this opencps dossier statistic mgt
	*/
	@Override
	public void setOnegateCount(int onegateCount) {
		_opencpsDossierStatisticMgt.setOnegateCount(onegateCount);
	}

	/**
	* Sets the online count of this opencps dossier statistic mgt.
	*
	* @param onlineCount the online count of this opencps dossier statistic mgt
	*/
	@Override
	public void setOnlineCount(int onlineCount) {
		_opencpsDossierStatisticMgt.setOnlineCount(onlineCount);
	}

	/**
	* Sets the ontime count of this opencps dossier statistic mgt.
	*
	* @param ontimeCount the ontime count of this opencps dossier statistic mgt
	*/
	@Override
	public void setOntimeCount(int ontimeCount) {
		_opencpsDossierStatisticMgt.setOntimeCount(ontimeCount);
	}

	/**
	* Sets the ontime percentage of this opencps dossier statistic mgt.
	*
	* @param ontimePercentage the ontime percentage of this opencps dossier statistic mgt
	*/
	@Override
	public void setOntimePercentage(int ontimePercentage) {
		_opencpsDossierStatisticMgt.setOntimePercentage(ontimePercentage);
	}

	/**
	* Sets the overdue count of this opencps dossier statistic mgt.
	*
	* @param overdueCount the overdue count of this opencps dossier statistic mgt
	*/
	@Override
	public void setOverdueCount(int overdueCount) {
		_opencpsDossierStatisticMgt.setOverdueCount(overdueCount);
	}

	/**
	* Sets the overtime count of this opencps dossier statistic mgt.
	*
	* @param overtimeCount the overtime count of this opencps dossier statistic mgt
	*/
	@Override
	public void setOvertimeCount(int overtimeCount) {
		_opencpsDossierStatisticMgt.setOvertimeCount(overtimeCount);
	}

	/**
	* Sets the primary key of this opencps dossier statistic mgt.
	*
	* @param primaryKey the primary key of this opencps dossier statistic mgt
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_opencpsDossierStatisticMgt.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_opencpsDossierStatisticMgt.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the process count of this opencps dossier statistic mgt.
	*
	* @param processCount the process count of this opencps dossier statistic mgt
	*/
	@Override
	public void setProcessCount(int processCount) {
		_opencpsDossierStatisticMgt.setProcessCount(processCount);
	}

	/**
	* Sets the processing count of this opencps dossier statistic mgt.
	*
	* @param processingCount the processing count of this opencps dossier statistic mgt
	*/
	@Override
	public void setProcessingCount(int processingCount) {
		_opencpsDossierStatisticMgt.setProcessingCount(processingCount);
	}

	/**
	* Sets the received count of this opencps dossier statistic mgt.
	*
	* @param receivedCount the received count of this opencps dossier statistic mgt
	*/
	@Override
	public void setReceivedCount(int receivedCount) {
		_opencpsDossierStatisticMgt.setReceivedCount(receivedCount);
	}

	/**
	* Sets the release count of this opencps dossier statistic mgt.
	*
	* @param releaseCount the release count of this opencps dossier statistic mgt
	*/
	@Override
	public void setReleaseCount(int releaseCount) {
		_opencpsDossierStatisticMgt.setReleaseCount(releaseCount);
	}

	/**
	* Sets the releasing count of this opencps dossier statistic mgt.
	*
	* @param releasingCount the releasing count of this opencps dossier statistic mgt
	*/
	@Override
	public void setReleasingCount(int releasingCount) {
		_opencpsDossierStatisticMgt.setReleasingCount(releasingCount);
	}

	/**
	* Sets the remaining count of this opencps dossier statistic mgt.
	*
	* @param remainingCount the remaining count of this opencps dossier statistic mgt
	*/
	@Override
	public void setRemainingCount(int remainingCount) {
		_opencpsDossierStatisticMgt.setRemainingCount(remainingCount);
	}

	/**
	* Sets the service code of this opencps dossier statistic mgt.
	*
	* @param serviceCode the service code of this opencps dossier statistic mgt
	*/
	@Override
	public void setServiceCode(String serviceCode) {
		_opencpsDossierStatisticMgt.setServiceCode(serviceCode);
	}

	/**
	* Sets the service name of this opencps dossier statistic mgt.
	*
	* @param serviceName the service name of this opencps dossier statistic mgt
	*/
	@Override
	public void setServiceName(String serviceName) {
		_opencpsDossierStatisticMgt.setServiceName(serviceName);
	}

	/**
	* Sets the total count of this opencps dossier statistic mgt.
	*
	* @param totalCount the total count of this opencps dossier statistic mgt
	*/
	@Override
	public void setTotalCount(int totalCount) {
		_opencpsDossierStatisticMgt.setTotalCount(totalCount);
	}

	/**
	* Sets the undue count of this opencps dossier statistic mgt.
	*
	* @param undueCount the undue count of this opencps dossier statistic mgt
	*/
	@Override
	public void setUndueCount(int undueCount) {
		_opencpsDossierStatisticMgt.setUndueCount(undueCount);
	}

	/**
	* Sets the unresolved count of this opencps dossier statistic mgt.
	*
	* @param unresolvedCount the unresolved count of this opencps dossier statistic mgt
	*/
	@Override
	public void setUnresolvedCount(int unresolvedCount) {
		_opencpsDossierStatisticMgt.setUnresolvedCount(unresolvedCount);
	}

	/**
	* Sets the user ID of this opencps dossier statistic mgt.
	*
	* @param userId the user ID of this opencps dossier statistic mgt
	*/
	@Override
	public void setUserId(long userId) {
		_opencpsDossierStatisticMgt.setUserId(userId);
	}

	/**
	* Sets the user name of this opencps dossier statistic mgt.
	*
	* @param userName the user name of this opencps dossier statistic mgt
	*/
	@Override
	public void setUserName(String userName) {
		_opencpsDossierStatisticMgt.setUserName(userName);
	}

	/**
	* Sets the user uuid of this opencps dossier statistic mgt.
	*
	* @param userUuid the user uuid of this opencps dossier statistic mgt
	*/
	@Override
	public void setUserUuid(String userUuid) {
		_opencpsDossierStatisticMgt.setUserUuid(userUuid);
	}

	/**
	* Sets the uuid of this opencps dossier statistic mgt.
	*
	* @param uuid the uuid of this opencps dossier statistic mgt
	*/
	@Override
	public void setUuid(String uuid) {
		_opencpsDossierStatisticMgt.setUuid(uuid);
	}

	/**
	* Sets the waiting count of this opencps dossier statistic mgt.
	*
	* @param waitingCount the waiting count of this opencps dossier statistic mgt
	*/
	@Override
	public void setWaitingCount(int waitingCount) {
		_opencpsDossierStatisticMgt.setWaitingCount(waitingCount);
	}

	/**
	* Sets the year of this opencps dossier statistic mgt.
	*
	* @param year the year of this opencps dossier statistic mgt
	*/
	@Override
	public void setYear(int year) {
		_opencpsDossierStatisticMgt.setYear(year);
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<OpencpsDossierStatisticMgt> toCacheModel() {
		return _opencpsDossierStatisticMgt.toCacheModel();
	}

	@Override
	public OpencpsDossierStatisticMgt toEscapedModel() {
		return new OpencpsDossierStatisticMgtWrapper(_opencpsDossierStatisticMgt.toEscapedModel());
	}

	@Override
	public String toString() {
		return _opencpsDossierStatisticMgt.toString();
	}

	@Override
	public OpencpsDossierStatisticMgt toUnescapedModel() {
		return new OpencpsDossierStatisticMgtWrapper(_opencpsDossierStatisticMgt.toUnescapedModel());
	}

	@Override
	public String toXmlString() {
		return _opencpsDossierStatisticMgt.toXmlString();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof OpencpsDossierStatisticMgtWrapper)) {
			return false;
		}

		OpencpsDossierStatisticMgtWrapper opencpsDossierStatisticMgtWrapper = (OpencpsDossierStatisticMgtWrapper)obj;

		if (Objects.equals(_opencpsDossierStatisticMgt,
					opencpsDossierStatisticMgtWrapper._opencpsDossierStatisticMgt)) {
			return true;
		}

		return false;
	}

	@Override
	public OpencpsDossierStatisticMgt getWrappedModel() {
		return _opencpsDossierStatisticMgt;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _opencpsDossierStatisticMgt.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _opencpsDossierStatisticMgt.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_opencpsDossierStatisticMgt.resetOriginalValues();
	}

	private final OpencpsDossierStatisticMgt _opencpsDossierStatisticMgt;
}