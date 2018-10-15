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

package org.opencps.dossiermgt.model;

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
		soapModel.setPaymentFee(model.getPaymentFee());
		soapModel.setAdvanceAmount(model.getAdvanceAmount());
		soapModel.setFeeAmount(model.getFeeAmount());
		soapModel.setServiceAmount(model.getServiceAmount());
		soapModel.setShipAmount(model.getShipAmount());
		soapModel.setPaymentAmount(model.getPaymentAmount());
		soapModel.setPaymentNote(model.getPaymentNote());
		soapModel.setEpaymentProfile(model.getEpaymentProfile());
		soapModel.setBankInfo(model.getBankInfo());
		soapModel.setPaymentStatus(model.getPaymentStatus());
		soapModel.setPaymentMethod(model.getPaymentMethod());
		soapModel.setConfirmDatetime(model.getConfirmDatetime());
		soapModel.setConfirmPayload(model.getConfirmPayload());
		soapModel.setConfirmFileEntryId(model.getConfirmFileEntryId());
		soapModel.setConfirmNote(model.getConfirmNote());
		soapModel.setApproveDatetime(model.getApproveDatetime());
		soapModel.setAccountUserName(model.getAccountUserName());
		soapModel.setGovAgencyTaxNo(model.getGovAgencyTaxNo());
		soapModel.setInvoiceTemplateNo(model.getInvoiceTemplateNo());
		soapModel.setInvoiceIssueNo(model.getInvoiceIssueNo());
		soapModel.setInvoiceNo(model.getInvoiceNo());
		soapModel.setInvoicePayload(model.getInvoicePayload());
		soapModel.setEinvoice(model.getEinvoice());

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

	public String getPaymentFee() {
		return _paymentFee;
	}

	public void setPaymentFee(String paymentFee) {
		_paymentFee = paymentFee;
	}

	public long getAdvanceAmount() {
		return _advanceAmount;
	}

	public void setAdvanceAmount(long advanceAmount) {
		_advanceAmount = advanceAmount;
	}

	public long getFeeAmount() {
		return _feeAmount;
	}

	public void setFeeAmount(long feeAmount) {
		_feeAmount = feeAmount;
	}

	public long getServiceAmount() {
		return _serviceAmount;
	}

	public void setServiceAmount(long serviceAmount) {
		_serviceAmount = serviceAmount;
	}

	public long getShipAmount() {
		return _shipAmount;
	}

	public void setShipAmount(long shipAmount) {
		_shipAmount = shipAmount;
	}

	public long getPaymentAmount() {
		return _paymentAmount;
	}

	public void setPaymentAmount(long paymentAmount) {
		_paymentAmount = paymentAmount;
	}

	public String getPaymentNote() {
		return _paymentNote;
	}

	public void setPaymentNote(String paymentNote) {
		_paymentNote = paymentNote;
	}

	public String getEpaymentProfile() {
		return _epaymentProfile;
	}

	public void setEpaymentProfile(String epaymentProfile) {
		_epaymentProfile = epaymentProfile;
	}

	public String getBankInfo() {
		return _bankInfo;
	}

	public void setBankInfo(String bankInfo) {
		_bankInfo = bankInfo;
	}

	public int getPaymentStatus() {
		return _paymentStatus;
	}

	public void setPaymentStatus(int paymentStatus) {
		_paymentStatus = paymentStatus;
	}

	public String getPaymentMethod() {
		return _paymentMethod;
	}

	public void setPaymentMethod(String paymentMethod) {
		_paymentMethod = paymentMethod;
	}

	public Date getConfirmDatetime() {
		return _confirmDatetime;
	}

	public void setConfirmDatetime(Date confirmDatetime) {
		_confirmDatetime = confirmDatetime;
	}

	public String getConfirmPayload() {
		return _confirmPayload;
	}

	public void setConfirmPayload(String confirmPayload) {
		_confirmPayload = confirmPayload;
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

	public String getInvoicePayload() {
		return _invoicePayload;
	}

	public void setInvoicePayload(String invoicePayload) {
		_invoicePayload = invoicePayload;
	}

	public String getEinvoice() {
		return _einvoice;
	}

	public void setEinvoice(String einvoice) {
		_einvoice = einvoice;
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
	private String _paymentFee;
	private long _advanceAmount;
	private long _feeAmount;
	private long _serviceAmount;
	private long _shipAmount;
	private long _paymentAmount;
	private String _paymentNote;
	private String _epaymentProfile;
	private String _bankInfo;
	private int _paymentStatus;
	private String _paymentMethod;
	private Date _confirmDatetime;
	private String _confirmPayload;
	private long _confirmFileEntryId;
	private String _confirmNote;
	private Date _approveDatetime;
	private String _accountUserName;
	private String _govAgencyTaxNo;
	private String _invoiceTemplateNo;
	private String _invoiceIssueNo;
	private String _invoiceNo;
	private String _invoicePayload;
	private String _einvoice;
}