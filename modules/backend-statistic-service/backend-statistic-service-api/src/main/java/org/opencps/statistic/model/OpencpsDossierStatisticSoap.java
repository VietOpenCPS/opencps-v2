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
public class OpencpsDossierStatisticSoap implements Serializable {
	public static OpencpsDossierStatisticSoap toSoapModel(
		OpencpsDossierStatistic model) {
		OpencpsDossierStatisticSoap soapModel = new OpencpsDossierStatisticSoap();

		soapModel.setUuid(model.getUuid());
		soapModel.setDossierStatisticId(model.getDossierStatisticId());
		soapModel.setCompanyId(model.getCompanyId());
		soapModel.setGroupId(model.getGroupId());
		soapModel.setUserId(model.getUserId());
		soapModel.setUserName(model.getUserName());
		soapModel.setCreateDate(model.getCreateDate());
		soapModel.setModifiedDate(model.getModifiedDate());
		soapModel.setMonth(model.getMonth());
		soapModel.setYear(model.getYear());
		soapModel.setTotalCount(model.getTotalCount());
		soapModel.setDeniedCount(model.getDeniedCount());
		soapModel.setCancelledCount(model.getCancelledCount());
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
		soapModel.setUnresolvedCount(model.getUnresolvedCount());
		soapModel.setProcessingCount(model.getProcessingCount());
		soapModel.setUndueCount(model.getUndueCount());
		soapModel.setOverdueCount(model.getOverdueCount());
		soapModel.setPausingCount(model.getPausingCount());
		soapModel.setOntimePercentage(model.getOntimePercentage());
		soapModel.setGovAgencyCode(model.getGovAgencyCode());
		soapModel.setGroupAgencyCode(model.getGroupAgencyCode());
		soapModel.setGovAgencyName(model.getGovAgencyName());
		soapModel.setDomainCode(model.getDomainCode());
		soapModel.setDomainName(model.getDomainName());
		soapModel.setReporting(model.isReporting());
		soapModel.setOvertimeInside(model.getOvertimeInside());
		soapModel.setOvertimeOutside(model.getOvertimeOutside());
		soapModel.setInteroperatingCount(model.getInteroperatingCount());
		soapModel.setWaitingCount(model.getWaitingCount());
		soapModel.setOutsideCount(model.getOutsideCount());
		soapModel.setInsideCount(model.getInsideCount());

		return soapModel;
	}

	public static OpencpsDossierStatisticSoap[] toSoapModels(
		OpencpsDossierStatistic[] models) {
		OpencpsDossierStatisticSoap[] soapModels = new OpencpsDossierStatisticSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static OpencpsDossierStatisticSoap[][] toSoapModels(
		OpencpsDossierStatistic[][] models) {
		OpencpsDossierStatisticSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new OpencpsDossierStatisticSoap[models.length][models[0].length];
		}
		else {
			soapModels = new OpencpsDossierStatisticSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static OpencpsDossierStatisticSoap[] toSoapModels(
		List<OpencpsDossierStatistic> models) {
		List<OpencpsDossierStatisticSoap> soapModels = new ArrayList<OpencpsDossierStatisticSoap>(models.size());

		for (OpencpsDossierStatistic model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new OpencpsDossierStatisticSoap[soapModels.size()]);
	}

	public OpencpsDossierStatisticSoap() {
	}

	public long getPrimaryKey() {
		return _dossierStatisticId;
	}

	public void setPrimaryKey(long pk) {
		setDossierStatisticId(pk);
	}

	public String getUuid() {
		return _uuid;
	}

	public void setUuid(String uuid) {
		_uuid = uuid;
	}

	public long getDossierStatisticId() {
		return _dossierStatisticId;
	}

	public void setDossierStatisticId(long dossierStatisticId) {
		_dossierStatisticId = dossierStatisticId;
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

	public int getDeniedCount() {
		return _deniedCount;
	}

	public void setDeniedCount(int deniedCount) {
		_deniedCount = deniedCount;
	}

	public int getCancelledCount() {
		return _cancelledCount;
	}

	public void setCancelledCount(int cancelledCount) {
		_cancelledCount = cancelledCount;
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

	public int getUnresolvedCount() {
		return _unresolvedCount;
	}

	public void setUnresolvedCount(int unresolvedCount) {
		_unresolvedCount = unresolvedCount;
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

	public int getPausingCount() {
		return _pausingCount;
	}

	public void setPausingCount(int pausingCount) {
		_pausingCount = pausingCount;
	}

	public int getOntimePercentage() {
		return _ontimePercentage;
	}

	public void setOntimePercentage(int ontimePercentage) {
		_ontimePercentage = ontimePercentage;
	}

	public String getGovAgencyCode() {
		return _govAgencyCode;
	}

	public void setGovAgencyCode(String govAgencyCode) {
		_govAgencyCode = govAgencyCode;
	}

	public String getGroupAgencyCode() {
		return _groupAgencyCode;
	}

	public void setGroupAgencyCode(String groupAgencyCode) {
		_groupAgencyCode = groupAgencyCode;
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

	public boolean getReporting() {
		return _reporting;
	}

	public boolean isReporting() {
		return _reporting;
	}

	public void setReporting(boolean reporting) {
		_reporting = reporting;
	}

	public int getOvertimeInside() {
		return _overtimeInside;
	}

	public void setOvertimeInside(int overtimeInside) {
		_overtimeInside = overtimeInside;
	}

	public int getOvertimeOutside() {
		return _overtimeOutside;
	}

	public void setOvertimeOutside(int overtimeOutside) {
		_overtimeOutside = overtimeOutside;
	}

	public int getInteroperatingCount() {
		return _interoperatingCount;
	}

	public void setInteroperatingCount(int interoperatingCount) {
		_interoperatingCount = interoperatingCount;
	}

	public int getWaitingCount() {
		return _waitingCount;
	}

	public void setWaitingCount(int waitingCount) {
		_waitingCount = waitingCount;
	}

	public int getOutsideCount() {
		return _outsideCount;
	}

	public void setOutsideCount(int outsideCount) {
		_outsideCount = outsideCount;
	}

	public int getInsideCount() {
		return _insideCount;
	}

	public void setInsideCount(int insideCount) {
		_insideCount = insideCount;
	}

	private String _uuid;
	private long _dossierStatisticId;
	private long _companyId;
	private long _groupId;
	private long _userId;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private int _month;
	private int _year;
	private int _totalCount;
	private int _deniedCount;
	private int _cancelledCount;
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
	private int _unresolvedCount;
	private int _processingCount;
	private int _undueCount;
	private int _overdueCount;
	private int _pausingCount;
	private int _ontimePercentage;
	private String _govAgencyCode;
	private String _groupAgencyCode;
	private String _govAgencyName;
	private String _domainCode;
	private String _domainName;
	private boolean _reporting;
	private int _overtimeInside;
	private int _overtimeOutside;
	private int _interoperatingCount;
	private int _waitingCount;
	private int _outsideCount;
	private int _insideCount;
}