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
public class PaymentFeeInfoSoap implements Serializable {
	public static PaymentFeeInfoSoap toSoapModel(PaymentFeeInfo model) {
		PaymentFeeInfoSoap soapModel = new PaymentFeeInfoSoap();

		soapModel.setUuid(model.getUuid());
		soapModel.setPaymentFeeInfoId(model.getPaymentFeeInfoId());
		soapModel.setGroupId(model.getGroupId());
		soapModel.setCompanyId(model.getCompanyId());
		soapModel.setUserId(model.getUserId());
		soapModel.setUserName(model.getUserName());
		soapModel.setCreateDate(model.getCreateDate());
		soapModel.setModifiedDate(model.getModifiedDate());
		soapModel.setType(model.getType());
		soapModel.setPaymentFeeCode(model.getPaymentFeeCode());
		soapModel.setPaymentFeeName(model.getPaymentFeeName());
		soapModel.setAmount(model.getAmount());
		soapModel.setServiceConfigMappingId(model.getServiceConfigMappingId());

		return soapModel;
	}

	public static PaymentFeeInfoSoap[] toSoapModels(PaymentFeeInfo[] models) {
		PaymentFeeInfoSoap[] soapModels = new PaymentFeeInfoSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static PaymentFeeInfoSoap[][] toSoapModels(PaymentFeeInfo[][] models) {
		PaymentFeeInfoSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new PaymentFeeInfoSoap[models.length][models[0].length];
		}
		else {
			soapModels = new PaymentFeeInfoSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static PaymentFeeInfoSoap[] toSoapModels(List<PaymentFeeInfo> models) {
		List<PaymentFeeInfoSoap> soapModels = new ArrayList<PaymentFeeInfoSoap>(models.size());

		for (PaymentFeeInfo model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new PaymentFeeInfoSoap[soapModels.size()]);
	}

	public PaymentFeeInfoSoap() {
	}

	public long getPrimaryKey() {
		return _paymentFeeInfoId;
	}

	public void setPrimaryKey(long pk) {
		setPaymentFeeInfoId(pk);
	}

	public String getUuid() {
		return _uuid;
	}

	public void setUuid(String uuid) {
		_uuid = uuid;
	}

	public long getPaymentFeeInfoId() {
		return _paymentFeeInfoId;
	}

	public void setPaymentFeeInfoId(long paymentFeeInfoId) {
		_paymentFeeInfoId = paymentFeeInfoId;
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

	public String getType() {
		return _type;
	}

	public void setType(String type) {
		_type = type;
	}

	public String getPaymentFeeCode() {
		return _paymentFeeCode;
	}

	public void setPaymentFeeCode(String paymentFeeCode) {
		_paymentFeeCode = paymentFeeCode;
	}

	public String getPaymentFeeName() {
		return _paymentFeeName;
	}

	public void setPaymentFeeName(String paymentFeeName) {
		_paymentFeeName = paymentFeeName;
	}

	public String getAmount() {
		return _amount;
	}

	public void setAmount(String amount) {
		_amount = amount;
	}

	public long getServiceConfigMappingId() {
		return _serviceConfigMappingId;
	}

	public void setServiceConfigMappingId(long serviceConfigMappingId) {
		_serviceConfigMappingId = serviceConfigMappingId;
	}

	private String _uuid;
	private long _paymentFeeInfoId;
	private long _groupId;
	private long _companyId;
	private long _userId;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private String _type;
	private String _paymentFeeCode;
	private String _paymentFeeName;
	private String _amount;
	private long _serviceConfigMappingId;
}