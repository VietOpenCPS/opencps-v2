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
 * This class is a wrapper for {@link OpencpsDossierStatisticManual}.
 * </p>
 *
 * @author khoavu
 * @see OpencpsDossierStatisticManual
 * @generated
 */
@ProviderType
public class OpencpsDossierStatisticManualWrapper
	implements OpencpsDossierStatisticManual,
		ModelWrapper<OpencpsDossierStatisticManual> {
	public OpencpsDossierStatisticManualWrapper(
		OpencpsDossierStatisticManual opencpsDossierStatisticManual) {
		_opencpsDossierStatisticManual = opencpsDossierStatisticManual;
	}

	@Override
	public Class<?> getModelClass() {
		return OpencpsDossierStatisticManual.class;
	}

	@Override
	public String getModelClassName() {
		return OpencpsDossierStatisticManual.class.getName();
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
		attributes.put("system", getSystem());
		attributes.put("viaPostalCount", getViaPostalCount());
		attributes.put("notViaPostalCount", getNotViaPostalCount());
		attributes.put("saturdayCount", getSaturdayCount());
		attributes.put("dossierOnline3Count", getDossierOnline3Count());
		attributes.put("dossierOnline4Count", getDossierOnline4Count());
		attributes.put("receiveDossierSatCount", getReceiveDossierSatCount());
		attributes.put("releaseDossierSatCount", getReleaseDossierSatCount());
		attributes.put("fromViaPostalCount", getFromViaPostalCount());

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

		String system = (String)attributes.get("system");

		if (system != null) {
			setSystem(system);
		}

		Integer viaPostalCount = (Integer)attributes.get("viaPostalCount");

		if (viaPostalCount != null) {
			setViaPostalCount(viaPostalCount);
		}

		Integer notViaPostalCount = (Integer)attributes.get("notViaPostalCount");

		if (notViaPostalCount != null) {
			setNotViaPostalCount(notViaPostalCount);
		}

		Integer saturdayCount = (Integer)attributes.get("saturdayCount");

		if (saturdayCount != null) {
			setSaturdayCount(saturdayCount);
		}

		Integer dossierOnline3Count = (Integer)attributes.get(
				"dossierOnline3Count");

		if (dossierOnline3Count != null) {
			setDossierOnline3Count(dossierOnline3Count);
		}

		Integer dossierOnline4Count = (Integer)attributes.get(
				"dossierOnline4Count");

		if (dossierOnline4Count != null) {
			setDossierOnline4Count(dossierOnline4Count);
		}

		Integer receiveDossierSatCount = (Integer)attributes.get(
				"receiveDossierSatCount");

		if (receiveDossierSatCount != null) {
			setReceiveDossierSatCount(receiveDossierSatCount);
		}

		Integer releaseDossierSatCount = (Integer)attributes.get(
				"releaseDossierSatCount");

		if (releaseDossierSatCount != null) {
			setReleaseDossierSatCount(releaseDossierSatCount);
		}

		Integer fromViaPostalCount = (Integer)attributes.get(
				"fromViaPostalCount");

		if (fromViaPostalCount != null) {
			setFromViaPostalCount(fromViaPostalCount);
		}
	}

	@Override
	public Object clone() {
		return new OpencpsDossierStatisticManualWrapper((OpencpsDossierStatisticManual)_opencpsDossierStatisticManual.clone());
	}

	@Override
	public int compareTo(
		OpencpsDossierStatisticManual opencpsDossierStatisticManual) {
		return _opencpsDossierStatisticManual.compareTo(opencpsDossierStatisticManual);
	}

	/**
	* Returns the betimes count of this opencps dossier statistic manual.
	*
	* @return the betimes count of this opencps dossier statistic manual
	*/
	@Override
	public int getBetimesCount() {
		return _opencpsDossierStatisticManual.getBetimesCount();
	}

	/**
	* Returns the cancelled count of this opencps dossier statistic manual.
	*
	* @return the cancelled count of this opencps dossier statistic manual
	*/
	@Override
	public int getCancelledCount() {
		return _opencpsDossierStatisticManual.getCancelledCount();
	}

	/**
	* Returns the company ID of this opencps dossier statistic manual.
	*
	* @return the company ID of this opencps dossier statistic manual
	*/
	@Override
	public long getCompanyId() {
		return _opencpsDossierStatisticManual.getCompanyId();
	}

	/**
	* Returns the create date of this opencps dossier statistic manual.
	*
	* @return the create date of this opencps dossier statistic manual
	*/
	@Override
	public Date getCreateDate() {
		return _opencpsDossierStatisticManual.getCreateDate();
	}

	/**
	* Returns the denied count of this opencps dossier statistic manual.
	*
	* @return the denied count of this opencps dossier statistic manual
	*/
	@Override
	public int getDeniedCount() {
		return _opencpsDossierStatisticManual.getDeniedCount();
	}

	/**
	* Returns the domain code of this opencps dossier statistic manual.
	*
	* @return the domain code of this opencps dossier statistic manual
	*/
	@Override
	public String getDomainCode() {
		return _opencpsDossierStatisticManual.getDomainCode();
	}

	/**
	* Returns the domain name of this opencps dossier statistic manual.
	*
	* @return the domain name of this opencps dossier statistic manual
	*/
	@Override
	public String getDomainName() {
		return _opencpsDossierStatisticManual.getDomainName();
	}

	/**
	* Returns the done count of this opencps dossier statistic manual.
	*
	* @return the done count of this opencps dossier statistic manual
	*/
	@Override
	public int getDoneCount() {
		return _opencpsDossierStatisticManual.getDoneCount();
	}

	/**
	* Returns the dossier online3 count of this opencps dossier statistic manual.
	*
	* @return the dossier online3 count of this opencps dossier statistic manual
	*/
	@Override
	public int getDossierOnline3Count() {
		return _opencpsDossierStatisticManual.getDossierOnline3Count();
	}

	/**
	* Returns the dossier online4 count of this opencps dossier statistic manual.
	*
	* @return the dossier online4 count of this opencps dossier statistic manual
	*/
	@Override
	public int getDossierOnline4Count() {
		return _opencpsDossierStatisticManual.getDossierOnline4Count();
	}

	/**
	* Returns the dossier statistic ID of this opencps dossier statistic manual.
	*
	* @return the dossier statistic ID of this opencps dossier statistic manual
	*/
	@Override
	public long getDossierStatisticId() {
		return _opencpsDossierStatisticManual.getDossierStatisticId();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _opencpsDossierStatisticManual.getExpandoBridge();
	}

	/**
	* Returns the from via postal count of this opencps dossier statistic manual.
	*
	* @return the from via postal count of this opencps dossier statistic manual
	*/
	@Override
	public int getFromViaPostalCount() {
		return _opencpsDossierStatisticManual.getFromViaPostalCount();
	}

	/**
	* Returns the gov agency code of this opencps dossier statistic manual.
	*
	* @return the gov agency code of this opencps dossier statistic manual
	*/
	@Override
	public String getGovAgencyCode() {
		return _opencpsDossierStatisticManual.getGovAgencyCode();
	}

	/**
	* Returns the gov agency name of this opencps dossier statistic manual.
	*
	* @return the gov agency name of this opencps dossier statistic manual
	*/
	@Override
	public String getGovAgencyName() {
		return _opencpsDossierStatisticManual.getGovAgencyName();
	}

	/**
	* Returns the group agency code of this opencps dossier statistic manual.
	*
	* @return the group agency code of this opencps dossier statistic manual
	*/
	@Override
	public String getGroupAgencyCode() {
		return _opencpsDossierStatisticManual.getGroupAgencyCode();
	}

	/**
	* Returns the group ID of this opencps dossier statistic manual.
	*
	* @return the group ID of this opencps dossier statistic manual
	*/
	@Override
	public long getGroupId() {
		return _opencpsDossierStatisticManual.getGroupId();
	}

	/**
	* Returns the inside count of this opencps dossier statistic manual.
	*
	* @return the inside count of this opencps dossier statistic manual
	*/
	@Override
	public int getInsideCount() {
		return _opencpsDossierStatisticManual.getInsideCount();
	}

	/**
	* Returns the interoperating count of this opencps dossier statistic manual.
	*
	* @return the interoperating count of this opencps dossier statistic manual
	*/
	@Override
	public int getInteroperatingCount() {
		return _opencpsDossierStatisticManual.getInteroperatingCount();
	}

	/**
	* Returns the modified date of this opencps dossier statistic manual.
	*
	* @return the modified date of this opencps dossier statistic manual
	*/
	@Override
	public Date getModifiedDate() {
		return _opencpsDossierStatisticManual.getModifiedDate();
	}

	/**
	* Returns the month of this opencps dossier statistic manual.
	*
	* @return the month of this opencps dossier statistic manual
	*/
	@Override
	public int getMonth() {
		return _opencpsDossierStatisticManual.getMonth();
	}

	/**
	* Returns the not via postal count of this opencps dossier statistic manual.
	*
	* @return the not via postal count of this opencps dossier statistic manual
	*/
	@Override
	public int getNotViaPostalCount() {
		return _opencpsDossierStatisticManual.getNotViaPostalCount();
	}

	/**
	* Returns the onegate count of this opencps dossier statistic manual.
	*
	* @return the onegate count of this opencps dossier statistic manual
	*/
	@Override
	public int getOnegateCount() {
		return _opencpsDossierStatisticManual.getOnegateCount();
	}

	/**
	* Returns the online count of this opencps dossier statistic manual.
	*
	* @return the online count of this opencps dossier statistic manual
	*/
	@Override
	public int getOnlineCount() {
		return _opencpsDossierStatisticManual.getOnlineCount();
	}

	/**
	* Returns the ontime count of this opencps dossier statistic manual.
	*
	* @return the ontime count of this opencps dossier statistic manual
	*/
	@Override
	public int getOntimeCount() {
		return _opencpsDossierStatisticManual.getOntimeCount();
	}

	/**
	* Returns the ontime percentage of this opencps dossier statistic manual.
	*
	* @return the ontime percentage of this opencps dossier statistic manual
	*/
	@Override
	public int getOntimePercentage() {
		return _opencpsDossierStatisticManual.getOntimePercentage();
	}

	/**
	* Returns the outside count of this opencps dossier statistic manual.
	*
	* @return the outside count of this opencps dossier statistic manual
	*/
	@Override
	public int getOutsideCount() {
		return _opencpsDossierStatisticManual.getOutsideCount();
	}

	/**
	* Returns the overdue count of this opencps dossier statistic manual.
	*
	* @return the overdue count of this opencps dossier statistic manual
	*/
	@Override
	public int getOverdueCount() {
		return _opencpsDossierStatisticManual.getOverdueCount();
	}

	/**
	* Returns the overtime count of this opencps dossier statistic manual.
	*
	* @return the overtime count of this opencps dossier statistic manual
	*/
	@Override
	public int getOvertimeCount() {
		return _opencpsDossierStatisticManual.getOvertimeCount();
	}

	/**
	* Returns the overtime inside of this opencps dossier statistic manual.
	*
	* @return the overtime inside of this opencps dossier statistic manual
	*/
	@Override
	public int getOvertimeInside() {
		return _opencpsDossierStatisticManual.getOvertimeInside();
	}

	/**
	* Returns the overtime outside of this opencps dossier statistic manual.
	*
	* @return the overtime outside of this opencps dossier statistic manual
	*/
	@Override
	public int getOvertimeOutside() {
		return _opencpsDossierStatisticManual.getOvertimeOutside();
	}

	/**
	* Returns the pausing count of this opencps dossier statistic manual.
	*
	* @return the pausing count of this opencps dossier statistic manual
	*/
	@Override
	public int getPausingCount() {
		return _opencpsDossierStatisticManual.getPausingCount();
	}

	/**
	* Returns the primary key of this opencps dossier statistic manual.
	*
	* @return the primary key of this opencps dossier statistic manual
	*/
	@Override
	public long getPrimaryKey() {
		return _opencpsDossierStatisticManual.getPrimaryKey();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _opencpsDossierStatisticManual.getPrimaryKeyObj();
	}

	/**
	* Returns the process count of this opencps dossier statistic manual.
	*
	* @return the process count of this opencps dossier statistic manual
	*/
	@Override
	public int getProcessCount() {
		return _opencpsDossierStatisticManual.getProcessCount();
	}

	/**
	* Returns the processing count of this opencps dossier statistic manual.
	*
	* @return the processing count of this opencps dossier statistic manual
	*/
	@Override
	public int getProcessingCount() {
		return _opencpsDossierStatisticManual.getProcessingCount();
	}

	/**
	* Returns the received count of this opencps dossier statistic manual.
	*
	* @return the received count of this opencps dossier statistic manual
	*/
	@Override
	public int getReceivedCount() {
		return _opencpsDossierStatisticManual.getReceivedCount();
	}

	/**
	* Returns the receive dossier sat count of this opencps dossier statistic manual.
	*
	* @return the receive dossier sat count of this opencps dossier statistic manual
	*/
	@Override
	public int getReceiveDossierSatCount() {
		return _opencpsDossierStatisticManual.getReceiveDossierSatCount();
	}

	/**
	* Returns the release count of this opencps dossier statistic manual.
	*
	* @return the release count of this opencps dossier statistic manual
	*/
	@Override
	public int getReleaseCount() {
		return _opencpsDossierStatisticManual.getReleaseCount();
	}

	/**
	* Returns the release dossier sat count of this opencps dossier statistic manual.
	*
	* @return the release dossier sat count of this opencps dossier statistic manual
	*/
	@Override
	public int getReleaseDossierSatCount() {
		return _opencpsDossierStatisticManual.getReleaseDossierSatCount();
	}

	/**
	* Returns the releasing count of this opencps dossier statistic manual.
	*
	* @return the releasing count of this opencps dossier statistic manual
	*/
	@Override
	public int getReleasingCount() {
		return _opencpsDossierStatisticManual.getReleasingCount();
	}

	/**
	* Returns the remaining count of this opencps dossier statistic manual.
	*
	* @return the remaining count of this opencps dossier statistic manual
	*/
	@Override
	public int getRemainingCount() {
		return _opencpsDossierStatisticManual.getRemainingCount();
	}

	/**
	* Returns the reporting of this opencps dossier statistic manual.
	*
	* @return the reporting of this opencps dossier statistic manual
	*/
	@Override
	public boolean getReporting() {
		return _opencpsDossierStatisticManual.getReporting();
	}

	/**
	* Returns the saturday count of this opencps dossier statistic manual.
	*
	* @return the saturday count of this opencps dossier statistic manual
	*/
	@Override
	public int getSaturdayCount() {
		return _opencpsDossierStatisticManual.getSaturdayCount();
	}

	/**
	* Returns the system of this opencps dossier statistic manual.
	*
	* @return the system of this opencps dossier statistic manual
	*/
	@Override
	public String getSystem() {
		return _opencpsDossierStatisticManual.getSystem();
	}

	/**
	* Returns the total count of this opencps dossier statistic manual.
	*
	* @return the total count of this opencps dossier statistic manual
	*/
	@Override
	public int getTotalCount() {
		return _opencpsDossierStatisticManual.getTotalCount();
	}

	/**
	* Returns the undue count of this opencps dossier statistic manual.
	*
	* @return the undue count of this opencps dossier statistic manual
	*/
	@Override
	public int getUndueCount() {
		return _opencpsDossierStatisticManual.getUndueCount();
	}

	/**
	* Returns the unresolved count of this opencps dossier statistic manual.
	*
	* @return the unresolved count of this opencps dossier statistic manual
	*/
	@Override
	public int getUnresolvedCount() {
		return _opencpsDossierStatisticManual.getUnresolvedCount();
	}

	/**
	* Returns the user ID of this opencps dossier statistic manual.
	*
	* @return the user ID of this opencps dossier statistic manual
	*/
	@Override
	public long getUserId() {
		return _opencpsDossierStatisticManual.getUserId();
	}

	/**
	* Returns the user name of this opencps dossier statistic manual.
	*
	* @return the user name of this opencps dossier statistic manual
	*/
	@Override
	public String getUserName() {
		return _opencpsDossierStatisticManual.getUserName();
	}

	/**
	* Returns the user uuid of this opencps dossier statistic manual.
	*
	* @return the user uuid of this opencps dossier statistic manual
	*/
	@Override
	public String getUserUuid() {
		return _opencpsDossierStatisticManual.getUserUuid();
	}

	/**
	* Returns the uuid of this opencps dossier statistic manual.
	*
	* @return the uuid of this opencps dossier statistic manual
	*/
	@Override
	public String getUuid() {
		return _opencpsDossierStatisticManual.getUuid();
	}

	/**
	* Returns the via postal count of this opencps dossier statistic manual.
	*
	* @return the via postal count of this opencps dossier statistic manual
	*/
	@Override
	public int getViaPostalCount() {
		return _opencpsDossierStatisticManual.getViaPostalCount();
	}

	/**
	* Returns the waiting count of this opencps dossier statistic manual.
	*
	* @return the waiting count of this opencps dossier statistic manual
	*/
	@Override
	public int getWaitingCount() {
		return _opencpsDossierStatisticManual.getWaitingCount();
	}

	/**
	* Returns the year of this opencps dossier statistic manual.
	*
	* @return the year of this opencps dossier statistic manual
	*/
	@Override
	public int getYear() {
		return _opencpsDossierStatisticManual.getYear();
	}

	@Override
	public int hashCode() {
		return _opencpsDossierStatisticManual.hashCode();
	}

	@Override
	public boolean isCachedModel() {
		return _opencpsDossierStatisticManual.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _opencpsDossierStatisticManual.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _opencpsDossierStatisticManual.isNew();
	}

	/**
	* Returns <code>true</code> if this opencps dossier statistic manual is reporting.
	*
	* @return <code>true</code> if this opencps dossier statistic manual is reporting; <code>false</code> otherwise
	*/
	@Override
	public boolean isReporting() {
		return _opencpsDossierStatisticManual.isReporting();
	}

	@Override
	public void persist() {
		_opencpsDossierStatisticManual.persist();
	}

	/**
	* Sets the betimes count of this opencps dossier statistic manual.
	*
	* @param betimesCount the betimes count of this opencps dossier statistic manual
	*/
	@Override
	public void setBetimesCount(int betimesCount) {
		_opencpsDossierStatisticManual.setBetimesCount(betimesCount);
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_opencpsDossierStatisticManual.setCachedModel(cachedModel);
	}

	/**
	* Sets the cancelled count of this opencps dossier statistic manual.
	*
	* @param cancelledCount the cancelled count of this opencps dossier statistic manual
	*/
	@Override
	public void setCancelledCount(int cancelledCount) {
		_opencpsDossierStatisticManual.setCancelledCount(cancelledCount);
	}

	/**
	* Sets the company ID of this opencps dossier statistic manual.
	*
	* @param companyId the company ID of this opencps dossier statistic manual
	*/
	@Override
	public void setCompanyId(long companyId) {
		_opencpsDossierStatisticManual.setCompanyId(companyId);
	}

	/**
	* Sets the create date of this opencps dossier statistic manual.
	*
	* @param createDate the create date of this opencps dossier statistic manual
	*/
	@Override
	public void setCreateDate(Date createDate) {
		_opencpsDossierStatisticManual.setCreateDate(createDate);
	}

	/**
	* Sets the denied count of this opencps dossier statistic manual.
	*
	* @param deniedCount the denied count of this opencps dossier statistic manual
	*/
	@Override
	public void setDeniedCount(int deniedCount) {
		_opencpsDossierStatisticManual.setDeniedCount(deniedCount);
	}

	/**
	* Sets the domain code of this opencps dossier statistic manual.
	*
	* @param domainCode the domain code of this opencps dossier statistic manual
	*/
	@Override
	public void setDomainCode(String domainCode) {
		_opencpsDossierStatisticManual.setDomainCode(domainCode);
	}

	/**
	* Sets the domain name of this opencps dossier statistic manual.
	*
	* @param domainName the domain name of this opencps dossier statistic manual
	*/
	@Override
	public void setDomainName(String domainName) {
		_opencpsDossierStatisticManual.setDomainName(domainName);
	}

	/**
	* Sets the done count of this opencps dossier statistic manual.
	*
	* @param doneCount the done count of this opencps dossier statistic manual
	*/
	@Override
	public void setDoneCount(int doneCount) {
		_opencpsDossierStatisticManual.setDoneCount(doneCount);
	}

	/**
	* Sets the dossier online3 count of this opencps dossier statistic manual.
	*
	* @param dossierOnline3Count the dossier online3 count of this opencps dossier statistic manual
	*/
	@Override
	public void setDossierOnline3Count(int dossierOnline3Count) {
		_opencpsDossierStatisticManual.setDossierOnline3Count(dossierOnline3Count);
	}

	/**
	* Sets the dossier online4 count of this opencps dossier statistic manual.
	*
	* @param dossierOnline4Count the dossier online4 count of this opencps dossier statistic manual
	*/
	@Override
	public void setDossierOnline4Count(int dossierOnline4Count) {
		_opencpsDossierStatisticManual.setDossierOnline4Count(dossierOnline4Count);
	}

	/**
	* Sets the dossier statistic ID of this opencps dossier statistic manual.
	*
	* @param dossierStatisticId the dossier statistic ID of this opencps dossier statistic manual
	*/
	@Override
	public void setDossierStatisticId(long dossierStatisticId) {
		_opencpsDossierStatisticManual.setDossierStatisticId(dossierStatisticId);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_opencpsDossierStatisticManual.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_opencpsDossierStatisticManual.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_opencpsDossierStatisticManual.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the from via postal count of this opencps dossier statistic manual.
	*
	* @param fromViaPostalCount the from via postal count of this opencps dossier statistic manual
	*/
	@Override
	public void setFromViaPostalCount(int fromViaPostalCount) {
		_opencpsDossierStatisticManual.setFromViaPostalCount(fromViaPostalCount);
	}

	/**
	* Sets the gov agency code of this opencps dossier statistic manual.
	*
	* @param govAgencyCode the gov agency code of this opencps dossier statistic manual
	*/
	@Override
	public void setGovAgencyCode(String govAgencyCode) {
		_opencpsDossierStatisticManual.setGovAgencyCode(govAgencyCode);
	}

	/**
	* Sets the gov agency name of this opencps dossier statistic manual.
	*
	* @param govAgencyName the gov agency name of this opencps dossier statistic manual
	*/
	@Override
	public void setGovAgencyName(String govAgencyName) {
		_opencpsDossierStatisticManual.setGovAgencyName(govAgencyName);
	}

	/**
	* Sets the group agency code of this opencps dossier statistic manual.
	*
	* @param groupAgencyCode the group agency code of this opencps dossier statistic manual
	*/
	@Override
	public void setGroupAgencyCode(String groupAgencyCode) {
		_opencpsDossierStatisticManual.setGroupAgencyCode(groupAgencyCode);
	}

	/**
	* Sets the group ID of this opencps dossier statistic manual.
	*
	* @param groupId the group ID of this opencps dossier statistic manual
	*/
	@Override
	public void setGroupId(long groupId) {
		_opencpsDossierStatisticManual.setGroupId(groupId);
	}

	/**
	* Sets the inside count of this opencps dossier statistic manual.
	*
	* @param insideCount the inside count of this opencps dossier statistic manual
	*/
	@Override
	public void setInsideCount(int insideCount) {
		_opencpsDossierStatisticManual.setInsideCount(insideCount);
	}

	/**
	* Sets the interoperating count of this opencps dossier statistic manual.
	*
	* @param interoperatingCount the interoperating count of this opencps dossier statistic manual
	*/
	@Override
	public void setInteroperatingCount(int interoperatingCount) {
		_opencpsDossierStatisticManual.setInteroperatingCount(interoperatingCount);
	}

	/**
	* Sets the modified date of this opencps dossier statistic manual.
	*
	* @param modifiedDate the modified date of this opencps dossier statistic manual
	*/
	@Override
	public void setModifiedDate(Date modifiedDate) {
		_opencpsDossierStatisticManual.setModifiedDate(modifiedDate);
	}

	/**
	* Sets the month of this opencps dossier statistic manual.
	*
	* @param month the month of this opencps dossier statistic manual
	*/
	@Override
	public void setMonth(int month) {
		_opencpsDossierStatisticManual.setMonth(month);
	}

	@Override
	public void setNew(boolean n) {
		_opencpsDossierStatisticManual.setNew(n);
	}

	/**
	* Sets the not via postal count of this opencps dossier statistic manual.
	*
	* @param notViaPostalCount the not via postal count of this opencps dossier statistic manual
	*/
	@Override
	public void setNotViaPostalCount(int notViaPostalCount) {
		_opencpsDossierStatisticManual.setNotViaPostalCount(notViaPostalCount);
	}

	/**
	* Sets the onegate count of this opencps dossier statistic manual.
	*
	* @param onegateCount the onegate count of this opencps dossier statistic manual
	*/
	@Override
	public void setOnegateCount(int onegateCount) {
		_opencpsDossierStatisticManual.setOnegateCount(onegateCount);
	}

	/**
	* Sets the online count of this opencps dossier statistic manual.
	*
	* @param onlineCount the online count of this opencps dossier statistic manual
	*/
	@Override
	public void setOnlineCount(int onlineCount) {
		_opencpsDossierStatisticManual.setOnlineCount(onlineCount);
	}

	/**
	* Sets the ontime count of this opencps dossier statistic manual.
	*
	* @param ontimeCount the ontime count of this opencps dossier statistic manual
	*/
	@Override
	public void setOntimeCount(int ontimeCount) {
		_opencpsDossierStatisticManual.setOntimeCount(ontimeCount);
	}

	/**
	* Sets the ontime percentage of this opencps dossier statistic manual.
	*
	* @param ontimePercentage the ontime percentage of this opencps dossier statistic manual
	*/
	@Override
	public void setOntimePercentage(int ontimePercentage) {
		_opencpsDossierStatisticManual.setOntimePercentage(ontimePercentage);
	}

	/**
	* Sets the outside count of this opencps dossier statistic manual.
	*
	* @param outsideCount the outside count of this opencps dossier statistic manual
	*/
	@Override
	public void setOutsideCount(int outsideCount) {
		_opencpsDossierStatisticManual.setOutsideCount(outsideCount);
	}

	/**
	* Sets the overdue count of this opencps dossier statistic manual.
	*
	* @param overdueCount the overdue count of this opencps dossier statistic manual
	*/
	@Override
	public void setOverdueCount(int overdueCount) {
		_opencpsDossierStatisticManual.setOverdueCount(overdueCount);
	}

	/**
	* Sets the overtime count of this opencps dossier statistic manual.
	*
	* @param overtimeCount the overtime count of this opencps dossier statistic manual
	*/
	@Override
	public void setOvertimeCount(int overtimeCount) {
		_opencpsDossierStatisticManual.setOvertimeCount(overtimeCount);
	}

	/**
	* Sets the overtime inside of this opencps dossier statistic manual.
	*
	* @param overtimeInside the overtime inside of this opencps dossier statistic manual
	*/
	@Override
	public void setOvertimeInside(int overtimeInside) {
		_opencpsDossierStatisticManual.setOvertimeInside(overtimeInside);
	}

	/**
	* Sets the overtime outside of this opencps dossier statistic manual.
	*
	* @param overtimeOutside the overtime outside of this opencps dossier statistic manual
	*/
	@Override
	public void setOvertimeOutside(int overtimeOutside) {
		_opencpsDossierStatisticManual.setOvertimeOutside(overtimeOutside);
	}

	/**
	* Sets the pausing count of this opencps dossier statistic manual.
	*
	* @param pausingCount the pausing count of this opencps dossier statistic manual
	*/
	@Override
	public void setPausingCount(int pausingCount) {
		_opencpsDossierStatisticManual.setPausingCount(pausingCount);
	}

	/**
	* Sets the primary key of this opencps dossier statistic manual.
	*
	* @param primaryKey the primary key of this opencps dossier statistic manual
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_opencpsDossierStatisticManual.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_opencpsDossierStatisticManual.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the process count of this opencps dossier statistic manual.
	*
	* @param processCount the process count of this opencps dossier statistic manual
	*/
	@Override
	public void setProcessCount(int processCount) {
		_opencpsDossierStatisticManual.setProcessCount(processCount);
	}

	/**
	* Sets the processing count of this opencps dossier statistic manual.
	*
	* @param processingCount the processing count of this opencps dossier statistic manual
	*/
	@Override
	public void setProcessingCount(int processingCount) {
		_opencpsDossierStatisticManual.setProcessingCount(processingCount);
	}

	/**
	* Sets the received count of this opencps dossier statistic manual.
	*
	* @param receivedCount the received count of this opencps dossier statistic manual
	*/
	@Override
	public void setReceivedCount(int receivedCount) {
		_opencpsDossierStatisticManual.setReceivedCount(receivedCount);
	}

	/**
	* Sets the receive dossier sat count of this opencps dossier statistic manual.
	*
	* @param receiveDossierSatCount the receive dossier sat count of this opencps dossier statistic manual
	*/
	@Override
	public void setReceiveDossierSatCount(int receiveDossierSatCount) {
		_opencpsDossierStatisticManual.setReceiveDossierSatCount(receiveDossierSatCount);
	}

	/**
	* Sets the release count of this opencps dossier statistic manual.
	*
	* @param releaseCount the release count of this opencps dossier statistic manual
	*/
	@Override
	public void setReleaseCount(int releaseCount) {
		_opencpsDossierStatisticManual.setReleaseCount(releaseCount);
	}

	/**
	* Sets the release dossier sat count of this opencps dossier statistic manual.
	*
	* @param releaseDossierSatCount the release dossier sat count of this opencps dossier statistic manual
	*/
	@Override
	public void setReleaseDossierSatCount(int releaseDossierSatCount) {
		_opencpsDossierStatisticManual.setReleaseDossierSatCount(releaseDossierSatCount);
	}

	/**
	* Sets the releasing count of this opencps dossier statistic manual.
	*
	* @param releasingCount the releasing count of this opencps dossier statistic manual
	*/
	@Override
	public void setReleasingCount(int releasingCount) {
		_opencpsDossierStatisticManual.setReleasingCount(releasingCount);
	}

	/**
	* Sets the remaining count of this opencps dossier statistic manual.
	*
	* @param remainingCount the remaining count of this opencps dossier statistic manual
	*/
	@Override
	public void setRemainingCount(int remainingCount) {
		_opencpsDossierStatisticManual.setRemainingCount(remainingCount);
	}

	/**
	* Sets whether this opencps dossier statistic manual is reporting.
	*
	* @param reporting the reporting of this opencps dossier statistic manual
	*/
	@Override
	public void setReporting(boolean reporting) {
		_opencpsDossierStatisticManual.setReporting(reporting);
	}

	/**
	* Sets the saturday count of this opencps dossier statistic manual.
	*
	* @param saturdayCount the saturday count of this opencps dossier statistic manual
	*/
	@Override
	public void setSaturdayCount(int saturdayCount) {
		_opencpsDossierStatisticManual.setSaturdayCount(saturdayCount);
	}

	/**
	* Sets the system of this opencps dossier statistic manual.
	*
	* @param system the system of this opencps dossier statistic manual
	*/
	@Override
	public void setSystem(String system) {
		_opencpsDossierStatisticManual.setSystem(system);
	}

	/**
	* Sets the total count of this opencps dossier statistic manual.
	*
	* @param totalCount the total count of this opencps dossier statistic manual
	*/
	@Override
	public void setTotalCount(int totalCount) {
		_opencpsDossierStatisticManual.setTotalCount(totalCount);
	}

	/**
	* Sets the undue count of this opencps dossier statistic manual.
	*
	* @param undueCount the undue count of this opencps dossier statistic manual
	*/
	@Override
	public void setUndueCount(int undueCount) {
		_opencpsDossierStatisticManual.setUndueCount(undueCount);
	}

	/**
	* Sets the unresolved count of this opencps dossier statistic manual.
	*
	* @param unresolvedCount the unresolved count of this opencps dossier statistic manual
	*/
	@Override
	public void setUnresolvedCount(int unresolvedCount) {
		_opencpsDossierStatisticManual.setUnresolvedCount(unresolvedCount);
	}

	/**
	* Sets the user ID of this opencps dossier statistic manual.
	*
	* @param userId the user ID of this opencps dossier statistic manual
	*/
	@Override
	public void setUserId(long userId) {
		_opencpsDossierStatisticManual.setUserId(userId);
	}

	/**
	* Sets the user name of this opencps dossier statistic manual.
	*
	* @param userName the user name of this opencps dossier statistic manual
	*/
	@Override
	public void setUserName(String userName) {
		_opencpsDossierStatisticManual.setUserName(userName);
	}

	/**
	* Sets the user uuid of this opencps dossier statistic manual.
	*
	* @param userUuid the user uuid of this opencps dossier statistic manual
	*/
	@Override
	public void setUserUuid(String userUuid) {
		_opencpsDossierStatisticManual.setUserUuid(userUuid);
	}

	/**
	* Sets the uuid of this opencps dossier statistic manual.
	*
	* @param uuid the uuid of this opencps dossier statistic manual
	*/
	@Override
	public void setUuid(String uuid) {
		_opencpsDossierStatisticManual.setUuid(uuid);
	}

	/**
	* Sets the via postal count of this opencps dossier statistic manual.
	*
	* @param viaPostalCount the via postal count of this opencps dossier statistic manual
	*/
	@Override
	public void setViaPostalCount(int viaPostalCount) {
		_opencpsDossierStatisticManual.setViaPostalCount(viaPostalCount);
	}

	/**
	* Sets the waiting count of this opencps dossier statistic manual.
	*
	* @param waitingCount the waiting count of this opencps dossier statistic manual
	*/
	@Override
	public void setWaitingCount(int waitingCount) {
		_opencpsDossierStatisticManual.setWaitingCount(waitingCount);
	}

	/**
	* Sets the year of this opencps dossier statistic manual.
	*
	* @param year the year of this opencps dossier statistic manual
	*/
	@Override
	public void setYear(int year) {
		_opencpsDossierStatisticManual.setYear(year);
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<OpencpsDossierStatisticManual> toCacheModel() {
		return _opencpsDossierStatisticManual.toCacheModel();
	}

	@Override
	public OpencpsDossierStatisticManual toEscapedModel() {
		return new OpencpsDossierStatisticManualWrapper(_opencpsDossierStatisticManual.toEscapedModel());
	}

	@Override
	public String toString() {
		return _opencpsDossierStatisticManual.toString();
	}

	@Override
	public OpencpsDossierStatisticManual toUnescapedModel() {
		return new OpencpsDossierStatisticManualWrapper(_opencpsDossierStatisticManual.toUnescapedModel());
	}

	@Override
	public String toXmlString() {
		return _opencpsDossierStatisticManual.toXmlString();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof OpencpsDossierStatisticManualWrapper)) {
			return false;
		}

		OpencpsDossierStatisticManualWrapper opencpsDossierStatisticManualWrapper =
			(OpencpsDossierStatisticManualWrapper)obj;

		if (Objects.equals(_opencpsDossierStatisticManual,
					opencpsDossierStatisticManualWrapper._opencpsDossierStatisticManual)) {
			return true;
		}

		return false;
	}

	@Override
	public StagedModelType getStagedModelType() {
		return _opencpsDossierStatisticManual.getStagedModelType();
	}

	@Override
	public OpencpsDossierStatisticManual getWrappedModel() {
		return _opencpsDossierStatisticManual;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _opencpsDossierStatisticManual.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _opencpsDossierStatisticManual.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_opencpsDossierStatisticManual.resetOriginalValues();
	}

	private final OpencpsDossierStatisticManual _opencpsDossierStatisticManual;
}