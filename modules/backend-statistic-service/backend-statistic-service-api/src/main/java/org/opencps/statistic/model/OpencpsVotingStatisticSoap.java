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
public class OpencpsVotingStatisticSoap implements Serializable {
	public static OpencpsVotingStatisticSoap toSoapModel(
		OpencpsVotingStatistic model) {
		OpencpsVotingStatisticSoap soapModel = new OpencpsVotingStatisticSoap();

		soapModel.setUuid(model.getUuid());
		soapModel.setVotingStatisticId(model.getVotingStatisticId());
		soapModel.setCompanyId(model.getCompanyId());
		soapModel.setGroupId(model.getGroupId());
		soapModel.setUserId(model.getUserId());
		soapModel.setUserName(model.getUserName());
		soapModel.setCreateDate(model.getCreateDate());
		soapModel.setModifiedDate(model.getModifiedDate());
		soapModel.setMonth(model.getMonth());
		soapModel.setYear(model.getYear());
		soapModel.setGovAgencyCode(model.getGovAgencyCode());
		soapModel.setGovAgencyName(model.getGovAgencyName());
		soapModel.setDomainCode(model.getDomainCode());
		soapModel.setDomainName(model.getDomainName());
		soapModel.setVotingCode(model.getVotingCode());
		soapModel.setVotingSubject(model.getVotingSubject());
		soapModel.setTotalVoted(model.getTotalVoted());
		soapModel.setVeryGoodCount(model.getVeryGoodCount());
		soapModel.setGoodCount(model.getGoodCount());
		soapModel.setBadCount(model.getBadCount());
		soapModel.setPercentVeryGood(model.getPercentVeryGood());
		soapModel.setPercentGood(model.getPercentGood());
		soapModel.setPercentBad(model.getPercentBad());
		soapModel.setTotalCount(model.getTotalCount());

		return soapModel;
	}

	public static OpencpsVotingStatisticSoap[] toSoapModels(
		OpencpsVotingStatistic[] models) {
		OpencpsVotingStatisticSoap[] soapModels = new OpencpsVotingStatisticSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static OpencpsVotingStatisticSoap[][] toSoapModels(
		OpencpsVotingStatistic[][] models) {
		OpencpsVotingStatisticSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new OpencpsVotingStatisticSoap[models.length][models[0].length];
		}
		else {
			soapModels = new OpencpsVotingStatisticSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static OpencpsVotingStatisticSoap[] toSoapModels(
		List<OpencpsVotingStatistic> models) {
		List<OpencpsVotingStatisticSoap> soapModels = new ArrayList<OpencpsVotingStatisticSoap>(models.size());

		for (OpencpsVotingStatistic model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new OpencpsVotingStatisticSoap[soapModels.size()]);
	}

	public OpencpsVotingStatisticSoap() {
	}

	public long getPrimaryKey() {
		return _votingStatisticId;
	}

	public void setPrimaryKey(long pk) {
		setVotingStatisticId(pk);
	}

	public String getUuid() {
		return _uuid;
	}

	public void setUuid(String uuid) {
		_uuid = uuid;
	}

	public long getVotingStatisticId() {
		return _votingStatisticId;
	}

	public void setVotingStatisticId(long votingStatisticId) {
		_votingStatisticId = votingStatisticId;
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

	public String getVotingCode() {
		return _votingCode;
	}

	public void setVotingCode(String votingCode) {
		_votingCode = votingCode;
	}

	public String getVotingSubject() {
		return _votingSubject;
	}

	public void setVotingSubject(String votingSubject) {
		_votingSubject = votingSubject;
	}

	public int getTotalVoted() {
		return _totalVoted;
	}

	public void setTotalVoted(int totalVoted) {
		_totalVoted = totalVoted;
	}

	public int getVeryGoodCount() {
		return _veryGoodCount;
	}

	public void setVeryGoodCount(int veryGoodCount) {
		_veryGoodCount = veryGoodCount;
	}

	public int getGoodCount() {
		return _goodCount;
	}

	public void setGoodCount(int goodCount) {
		_goodCount = goodCount;
	}

	public int getBadCount() {
		return _badCount;
	}

	public void setBadCount(int badCount) {
		_badCount = badCount;
	}

	public int getPercentVeryGood() {
		return _percentVeryGood;
	}

	public void setPercentVeryGood(int percentVeryGood) {
		_percentVeryGood = percentVeryGood;
	}

	public int getPercentGood() {
		return _percentGood;
	}

	public void setPercentGood(int percentGood) {
		_percentGood = percentGood;
	}

	public int getPercentBad() {
		return _percentBad;
	}

	public void setPercentBad(int percentBad) {
		_percentBad = percentBad;
	}

	public int getTotalCount() {
		return _totalCount;
	}

	public void setTotalCount(int totalCount) {
		_totalCount = totalCount;
	}

	private String _uuid;
	private long _votingStatisticId;
	private long _companyId;
	private long _groupId;
	private long _userId;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private int _month;
	private int _year;
	private String _govAgencyCode;
	private String _govAgencyName;
	private String _domainCode;
	private String _domainName;
	private String _votingCode;
	private String _votingSubject;
	private int _totalVoted;
	private int _veryGoodCount;
	private int _goodCount;
	private int _badCount;
	private int _percentVeryGood;
	private int _percentGood;
	private int _percentBad;
	private int _totalCount;
}