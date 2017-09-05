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

package org.opencps.backend.dossiermgt.model;

import aQute.bnd.annotation.ProviderType;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * This class is used by SOAP remote services.
 *
 * @author huymq
 * @generated
 */
@ProviderType
public class PaymentFileSoap implements Serializable {
	public static PaymentFileSoap toSoapModel(PaymentFile model) {
		PaymentFileSoap soapModel = new PaymentFileSoap();

		soapModel.setUuid(model.getUuid());
		soapModel.setPaymentFileId(model.getPaymentFileId());
		soapModel.setGroupId(model.getGroupId());
		soapModel.setCompanyId(model.getCompanyId());
		soapModel.setUserId(model.getUserId());
		soapModel.setUserName(model.getUserName());
		soapModel.setCreateDate(model.getCreateDate());
		soapModel.setModifiedDate(model.getModifiedDate());
		soapModel.setDossierId(model.getDossierId());
		soapModel.setReferenceUid(model.getReferenceUid());
		soapModel.setGovAgencyCode(model.getGovAgencyCode());
		soapModel.setGovAgencyName(model.getGovAgencyName());
		soapModel.setIsNew(model.getIsNew());
		soapModel.setPaymentFee(model.getPaymentFee());
		soapModel.setPaymentAmount(model.getPaymentAmount());
		soapModel.setPaymentNote(model.getPaymentNote());
		soapModel.setPaymentOptions(model.getPaymentOptions());
		soapModel.setKeypayUrl(model.getKeypayUrl());
		soapModel.setKeypayTransactionId(model.getKeypayTransactionId());
		soapModel.setKeypayGoodCode(model.getKeypayGoodCode());
		soapModel.setKeypayMerchantCode(model.getKeypayMerchantCode());
		soapModel.setBankInfo(model.getBankInfo());
		soapModel.setPaymentStatus(model.getPaymentStatus());
		soapModel.setPaymentMethod(model.getPaymentMethod());
		soapModel.setConfirmDatetime(model.getConfirmDatetime());
		soapModel.setConfirmFileEntryId(model.getConfirmFileEntryId());
		soapModel.setConfirmNote(model.getConfirmNote());
		soapModel.setApproveDatetime(model.getApproveDatetime());
		soapModel.setAccountUserName(model.getAccountUserName());
		soapModel.setGovAgencyTaxNo(model.getGovAgencyTaxNo());
		soapModel.setInvoiceTemplateNo(model.getInvoiceTemplateNo());
		soapModel.setInvoiceIssueNo(model.getInvoiceIssueNo());
		soapModel.setInvoiceNo(model.getInvoiceNo());
		soapModel.setInvoiceFileEntryId(model.getInvoiceFileEntryId());
		soapModel.setPaymentGateStatusCode(model.getPaymentGateStatusCode());
		soapModel.setPaymentGateCheckCode(model.getPaymentGateCheckCode());

		return soapModel;
	}

	public static PaymentFileSoap[] toSoapModels(PaymentFile[] models) {
		PaymentFileSoap[] soapModels = new PaymentFileSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static PaymentFileSoap[][] toSoapModels(PaymentFile[][] models) {
		PaymentFileSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new PaymentFileSoap[models.length][models[0].length];
		}
		else {
			soapModels = new PaymentFileSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static PaymentFileSoap[] toSoapModels(List<PaymentFile> models) {
		List<PaymentFileSoap> soapModels = new ArrayList<PaymentFileSoap>(models.size());

		for (PaymentFile model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new PaymentFileSoap[soapModels.size()]);
	}

	public PaymentFileSoap() {
	}

	public long getPrimaryKey() {
		return _paymentFileId;
	}

	public void setPrimaryKey(long pk) {
		setPaymentFileId(pk);
	}

	public String getUuid() {
		return _uuid;
	}

	public void setUuid(String uuid) {
		_uuid = uuid;
	}

	public long getPaymentFileId() {
		return _paymentFileId;
	}

	public void setPaymentFileId(long paymentFileId) {
		_paymentFileId = paymentFileId;
	}

	public long getGroupId() {
		return _groupId;
	}

	public void setGroupId(long groupId) {
		_groupId = groupId;
	}

	public long getCompanyId() {
		return _companyId;
	}

	public void setCompanyId(long companyId) {
		_companyId = companyId;
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

	public long getDossierId() {
		return _dossierId;
	}

	public void setDossierId(long dossierId) {
		_dossierId = dossierId;
	}

	public String getReferenceUid() {
		return _referenceUid;
	}

	public void setReferenceUid(String referenceUid) {
		_referenceUid = referenceUid;
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

	public boolean getIsNew() {
		return _isNew;
	}

	public boolean isIsNew() {
		return _isNew;
	}

	public void setIsNew(boolean isNew) {
		_isNew = isNew;
	}

	public String getPaymentFee() {
		return _paymentFee;
	}

	public void setPaymentFee(String paymentFee) {
		_paymentFee = paymentFee;
	}

	public double getPaymentAmount() {
		return _paymentAmount;
	}

	public void setPaymentAmount(double paymentAmount) {
		_paymentAmount = paymentAmount;
	}

	public String getPaymentNote() {
		return _paymentNote;
	}

	public void setPaymentNote(String paymentNote) {
		_paymentNote = paymentNote;
	}

	public String getPaymentOptions() {
		return _paymentOptions;
	}

	public void setPaymentOptions(String paymentOptions) {
		_paymentOptions = paymentOptions;
	}

	public Date getKeypayUrl() {
		return _keypayUrl;
	}

	public void setKeypayUrl(Date keypayUrl) {
		_keypayUrl = keypayUrl;
	}

	public long getKeypayTransactionId() {
		return _keypayTransactionId;
	}

	public void setKeypayTransactionId(long keypayTransactionId) {
		_keypayTransactionId = keypayTransactionId;
	}

	public Date getKeypayGoodCode() {
		return _keypayGoodCode;
	}

	public void setKeypayGoodCode(Date keypayGoodCode) {
		_keypayGoodCode = keypayGoodCode;
	}

	public Date getKeypayMerchantCode() {
		return _keypayMerchantCode;
	}

	public void setKeypayMerchantCode(Date keypayMerchantCode) {
		_keypayMerchantCode = keypayMerchantCode;
	}

	public Date getBankInfo() {
		return _bankInfo;
	}

	public void setBankInfo(Date bankInfo) {
		_bankInfo = bankInfo;
	}

	public int getPaymentStatus() {
		return _paymentStatus;
	}

	public void setPaymentStatus(int paymentStatus) {
		_paymentStatus = paymentStatus;
	}

	public int getPaymentMethod() {
		return _paymentMethod;
	}

	public void setPaymentMethod(int paymentMethod) {
		_paymentMethod = paymentMethod;
	}

	public Date getConfirmDatetime() {
		return _confirmDatetime;
	}

	public void setConfirmDatetime(Date confirmDatetime) {
		_confirmDatetime = confirmDatetime;
	}

	public long getConfirmFileEntryId() {
		return _confirmFileEntryId;
	}

	public void setConfirmFileEntryId(long confirmFileEntryId) {
		_confirmFileEntryId = confirmFileEntryId;
	}

	public String getConfirmNote() {
		return _confirmNote;
	}

	public void setConfirmNote(String confirmNote) {
		_confirmNote = confirmNote;
	}

	public Date getApproveDatetime() {
		return _approveDatetime;
	}

	public void setApproveDatetime(Date approveDatetime) {
		_approveDatetime = approveDatetime;
	}

	public String getAccountUserName() {
		return _accountUserName;
	}

	public void setAccountUserName(String accountUserName) {
		_accountUserName = accountUserName;
	}

	public String getGovAgencyTaxNo() {
		return _govAgencyTaxNo;
	}

	public void setGovAgencyTaxNo(String govAgencyTaxNo) {
		_govAgencyTaxNo = govAgencyTaxNo;
	}

	public String getInvoiceTemplateNo() {
		return _invoiceTemplateNo;
	}

	public void setInvoiceTemplateNo(String invoiceTemplateNo) {
		_invoiceTemplateNo = invoiceTemplateNo;
	}

	public String getInvoiceIssueNo() {
		return _invoiceIssueNo;
	}

	public void setInvoiceIssueNo(String invoiceIssueNo) {
		_invoiceIssueNo = invoiceIssueNo;
	}

	public String getInvoiceNo() {
		return _invoiceNo;
	}

	public void setInvoiceNo(String invoiceNo) {
		_invoiceNo = invoiceNo;
	}

	public long getInvoiceFileEntryId() {
		return _invoiceFileEntryId;
	}

	public void setInvoiceFileEntryId(long invoiceFileEntryId) {
		_invoiceFileEntryId = invoiceFileEntryId;
	}

	public int getPaymentGateStatusCode() {
		return _paymentGateStatusCode;
	}

	public void setPaymentGateStatusCode(int paymentGateStatusCode) {
		_paymentGateStatusCode = paymentGateStatusCode;
	}

	public int getPaymentGateCheckCode() {
		return _paymentGateCheckCode;
	}

	public void setPaymentGateCheckCode(int paymentGateCheckCode) {
		_paymentGateCheckCode = paymentGateCheckCode;
	}

	private String _uuid;
	private long _paymentFileId;
	private long _groupId;
	private long _companyId;
	private long _userId;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private long _dossierId;
	private String _referenceUid;
	private String _govAgencyCode;
	private String _govAgencyName;
	private boolean _isNew;
	private String _paymentFee;
	private double _paymentAmount;
	private String _paymentNote;
	private String _paymentOptions;
	private Date _keypayUrl;
	private long _keypayTransactionId;
	private Date _keypayGoodCode;
	private Date _keypayMerchantCode;
	private Date _bankInfo;
	private int _paymentStatus;
	private int _paymentMethod;
	private Date _confirmDatetime;
	private long _confirmFileEntryId;
	private String _confirmNote;
	private Date _approveDatetime;
	private String _accountUserName;
	private String _govAgencyTaxNo;
	private String _invoiceTemplateNo;
	private String _invoiceIssueNo;
	private String _invoiceNo;
	private long _invoiceFileEntryId;
	private int _paymentGateStatusCode;
	private int _paymentGateCheckCode;
}