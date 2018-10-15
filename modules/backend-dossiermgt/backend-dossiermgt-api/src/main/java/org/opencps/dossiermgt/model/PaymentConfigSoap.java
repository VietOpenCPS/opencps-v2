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
public class PaymentConfigSoap implements Serializable {
	public static PaymentConfigSoap toSoapModel(PaymentConfig model) {
		PaymentConfigSoap soapModel = new PaymentConfigSoap();

		soapModel.setUuid(model.getUuid());
		soapModel.setPaymentConfigId(model.getPaymentConfigId());
		soapModel.setGroupId(model.getGroupId());
		soapModel.setCompanyId(model.getCompanyId());
		soapModel.setUserId(model.getUserId());
		soapModel.setUserName(model.getUserName());
		soapModel.setCreateDate(model.getCreateDate());
		soapModel.setModifiedDate(model.getModifiedDate());
		soapModel.setGovAgencyCode(model.getGovAgencyCode());
		soapModel.setGovAgencyName(model.getGovAgencyName());
		soapModel.setGovAgencyTaxNo(model.getGovAgencyTaxNo());
		soapModel.setInvoiceTemplateNo(model.getInvoiceTemplateNo());
		soapModel.setInvoiceIssueNo(model.getInvoiceIssueNo());
		soapModel.setInvoiceLastNo(model.getInvoiceLastNo());
		soapModel.setInvoiceForm(model.getInvoiceForm());
		soapModel.setBankInfo(model.getBankInfo());
		soapModel.setEpaymentConfig(model.getEpaymentConfig());

		return soapModel;
	}

	public static PaymentConfigSoap[] toSoapModels(PaymentConfig[] models) {
		PaymentConfigSoap[] soapModels = new PaymentConfigSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static PaymentConfigSoap[][] toSoapModels(PaymentConfig[][] models) {
		PaymentConfigSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new PaymentConfigSoap[models.length][models[0].length];
		}
		else {
			soapModels = new PaymentConfigSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static PaymentConfigSoap[] toSoapModels(List<PaymentConfig> models) {
		List<PaymentConfigSoap> soapModels = new ArrayList<PaymentConfigSoap>(models.size());

		for (PaymentConfig model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new PaymentConfigSoap[soapModels.size()]);
	}

	public PaymentConfigSoap() {
	}

	public long getPrimaryKey() {
		return _paymentConfigId;
	}

	public void setPrimaryKey(long pk) {
		setPaymentConfigId(pk);
	}

	public String getUuid() {
		return _uuid;
	}

	public void setUuid(String uuid) {
		_uuid = uuid;
	}

	public long getPaymentConfigId() {
		return _paymentConfigId;
	}

	public void setPaymentConfigId(long paymentConfigId) {
		_paymentConfigId = paymentConfigId;
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

	public String getInvoiceLastNo() {
		return _invoiceLastNo;
	}

	public void setInvoiceLastNo(String invoiceLastNo) {
		_invoiceLastNo = invoiceLastNo;
	}

	public String getInvoiceForm() {
		return _invoiceForm;
	}

	public void setInvoiceForm(String invoiceForm) {
		_invoiceForm = invoiceForm;
	}

	public String getBankInfo() {
		return _bankInfo;
	}

	public void setBankInfo(String bankInfo) {
		_bankInfo = bankInfo;
	}

	public String getEpaymentConfig() {
		return _epaymentConfig;
	}

	public void setEpaymentConfig(String epaymentConfig) {
		_epaymentConfig = epaymentConfig;
	}

	private String _uuid;
	private long _paymentConfigId;
	private long _groupId;
	private long _companyId;
	private long _userId;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private String _govAgencyCode;
	private String _govAgencyName;
	private String _govAgencyTaxNo;
	private String _invoiceTemplateNo;
	private String _invoiceIssueNo;
	private String _invoiceLastNo;
	private String _invoiceForm;
	private String _bankInfo;
	private String _epaymentConfig;
}