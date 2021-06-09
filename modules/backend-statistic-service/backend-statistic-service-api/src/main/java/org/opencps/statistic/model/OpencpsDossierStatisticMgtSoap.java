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

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * This class is used by SOAP remote services.
 *
 * @author khoavu
 * @generated
 */
@ProviderType
public class OpencpsDossierStatisticMgtSoap implements Serializable {
	public static OpencpsDossierStatisticMgtSoap toSoapModel(
		OpencpsDossierStatisticMgt model) {
		OpencpsDossierStatisticMgtSoap soapModel = new OpencpsDossierStatisticMgtSoap();

		soapModel.setUuid(model.getUuid());
		soapModel.setDossierStatisticMgtId(model.getDossierStatisticMgtId());
		soapModel.setGroupId(model.getGroupId());
		soapModel.setUserId(model.getUserId());
		soapModel.setUserName(model.getUserName());
		soapModel.setCreateDate(model.getCreateDate());
		soapModel.setModifiedDate(model.getModifiedDate());
		soapModel.setMonth(model.getMonth());
		soapModel.setYear(model.getYear());
		soapModel.setTotalCount(model.getTotalCount());
		soapModel.setProcessCount(model.getProcessCount());
		soapModel.setRemainingCount(model.getRemainingCount());
		soapModel.setReceivedCount(model.getReceivedCount());
		soapModel.setOnlineCount(model.getOnlineCount());
		soapModel.setOnegateCount(model.getOnegateCount());
		soapModel.setReleaseCount(model.getReleaseCount());
		soapModel.setBetimesCount(model.getBetimesCount());
		soapModel.setOntimeCount(model.getOntimeCount());
		soapModel.setOvertimeCount(model.getOvertimeCount());
		soapModel.setDoneCount(model.getDoneCount());
		soapModel.setReleasingCount(model.getReleasingCount());
		soapModel.setProcessingCount(model.getProcessingCount());
		soapModel.setUndueCount(model.getUndueCount());
		soapModel.setOverdueCount(model.getOverdueCount());
		soapModel.setOntimePercentage(model.getOntimePercentage());
		soapModel.setWaitingCount(model.getWaitingCount());
		soapModel.setGovAgencyCode(model.getGovAgencyCode());
		soapModel.setGovAgencyName(model.getGovAgencyName());
		soapModel.setDomainCode(model.getDomainCode());
		soapModel.setDomainName(model.getDomainName());
		soapModel.setServiceCode(model.getServiceCode());
		soapModel.setServiceName(model.getServiceName());
		soapModel.setGroupBy(model.getGroupBy());

		return soapModel;
	}

	public static OpencpsDossierStatisticMgtSoap[] toSoapModels(
		OpencpsDossierStatisticMgt[] models) {
		OpencpsDossierStatisticMgtSoap[] soapModels = new OpencpsDossierStatisticMgtSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static OpencpsDossierStatisticMgtSoap[][] toSoapModels(
		OpencpsDossierStatisticMgt[][] models) {
		OpencpsDossierStatisticMgtSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new OpencpsDossierStatisticMgtSoap[models.length][models[0].length];
		}
		else {
			soapModels = new OpencpsDossierStatisticMgtSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static OpencpsDossierStatisticMgtSoap[] toSoapModels(
		List<OpencpsDossierStatisticMgt> models) {
		List<OpencpsDossierStatisticMgtSoap> soapModels = new ArrayList<OpencpsDossierStatisticMgtSoap>(models.size());

		for (OpencpsDossierStatisticMgt model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new OpencpsDossierStatisticMgtSoap[soapModels.size()]);
	}

	public OpencpsDossierStatisticMgtSoap() {
	}

	public long getPrimaryKey() {
		return _dossierStatisticMgtId;
	}

	public void setPrimaryKey(long pk) {
		setDossierStatisticMgtId(pk);
	}

	public String getUuid() {
		return _uuid;
	}

	public void setUuid(String uuid) {
		_uuid = uuid;
	}

	public long getDossierStatisticMgtId() {
		return _dossierStatisticMgtId;
	}

	public void setDossierStatisticMgtId(long dossierStatisticMgtId) {
		_dossierStatisticMgtId = dossierStatisticMgtId;
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

	public String getUserName() {
		return _userName;
	}

	public void setUserName(String userName) {
		_userName = userName;
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

	public int getMonth() {
		return _month;
	}

	public void setMonth(int month) {
		_month = month;
	}

	public int getYear() {
		return _year;
	}

	public void setYear(int year) {
		_year = year;
	}

	public int getTotalCount() {
		return _totalCount;
	}

	public void setTotalCount(int totalCount) {
		_totalCount = totalCount;
	}

	public int getProcessCount() {
		return _processCount;
	}

	public void setProcessCount(int processCount) {
		_processCount = processCount;
	}

	public int getRemainingCount() {
		return _remainingCount;
	}

	public void setRemainingCount(int remainingCount) {
		_remainingCount = remainingCount;
	}

	public int getReceivedCount() {
		return _receivedCount;
	}

	public void setReceivedCount(int receivedCount) {
		_receivedCount = receivedCount;
	}

	public int getOnlineCount() {
		return _onlineCount;
	}

	public void setOnlineCount(int onlineCount) {
		_onlineCount = onlineCount;
	}

	public int getOnegateCount() {
		return _onegateCount;
	}

	public void setOnegateCount(int onegateCount) {
		_onegateCount = onegateCount;
	}

	public int getReleaseCount() {
		return _releaseCount;
	}

	public void setReleaseCount(int releaseCount) {
		_releaseCount = releaseCount;
	}

	public int getBetimesCount() {
		return _betimesCount;
	}

	public void setBetimesCount(int betimesCount) {
		_betimesCount = betimesCount;
	}

	public int getOntimeCount() {
		return _ontimeCount;
	}

	public void setOntimeCount(int ontimeCount) {
		_ontimeCount = ontimeCount;
	}

	public int getOvertimeCount() {
		return _overtimeCount;
	}

	public void setOvertimeCount(int overtimeCount) {
		_overtimeCount = overtimeCount;
	}

	public int getDoneCount() {
		return _doneCount;
	}

	public void setDoneCount(int doneCount) {
		_doneCount = doneCount;
	}

	public int getReleasingCount() {
		return _releasingCount;
	}

	public void setReleasingCount(int releasingCount) {
		_releasingCount = releasingCount;
	}

	public int getProcessingCount() {
		return _processingCount;
	}

	public void setProcessingCount(int processingCount) {
		_processingCount = processingCount;
	}

	public int getUndueCount() {
		return _undueCount;
	}

	public void setUndueCount(int undueCount) {
		_undueCount = undueCount;
	}

	public int getOverdueCount() {
		return _overdueCount;
	}

	public void setOverdueCount(int overdueCount) {
		_overdueCount = overdueCount;
	}

	public int getOntimePercentage() {
		return _ontimePercentage;
	}

	public void setOntimePercentage(int ontimePercentage) {
		_ontimePercentage = ontimePercentage;
	}

	public int getWaitingCount() {
		return _waitingCount;
	}

	public void setWaitingCount(int waitingCount) {
		_waitingCount = waitingCount;
	}

	public String getGovAgencyCode() {
		return _govAgencyCode;
	}

	public void setGovAgencyCode(String govAgencyCode) {
		_govAgencyCode = govAgencyCode;
	}

	public String getGovAgencyName() {
		return _govAgencyName;
	}

	public void setGovAgencyName(String govAgencyName) {
		_govAgencyName = govAgencyName;
	}

	public String getDomainCode() {
		return _domainCode;
	}

	public void setDomainCode(String domainCode) {
		_domainCode = domainCode;
	}

	public String getDomainName() {
		return _domainName;
	}

	public void setDomainName(String domainName) {
		_domainName = domainName;
	}

	public String getServiceCode() {
		return _serviceCode;
	}

	public void setServiceCode(String serviceCode) {
		_serviceCode = serviceCode;
	}

	public String getServiceName() {
		return _serviceName;
	}

	public void setServiceName(String serviceName) {
		_serviceName = serviceName;
	}

	public int getGroupBy() {
		return _groupBy;
	}

	public void setGroupBy(int groupBy) {
		_groupBy = groupBy;
	}

	private String _uuid;
	private long _dossierStatisticMgtId;
	private long _groupId;
	private long _userId;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private int _month;
	private int _year;
	private int _totalCount;
	private int _processCount;
	private int _remainingCount;
	private int _receivedCount;
	private int _onlineCount;
	private int _onegateCount;
	private int _releaseCount;
	private int _betimesCount;
	private int _ontimeCount;
	private int _overtimeCount;
	private int _doneCount;
	private int _releasingCount;
	private int _processingCount;
	private int _undueCount;
	private int _overdueCount;
	private int _ontimePercentage;
	private int _waitingCount;
	private String _govAgencyCode;
	private String _govAgencyName;
	private String _domainCode;
	private String _domainName;
	private String _serviceCode;
	private String _serviceName;
	private int _groupBy;
}