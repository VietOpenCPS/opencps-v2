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
 * This class is a wrapper for {@link PaymentConfig}.
 * </p>
 *
 * @author huymq
 * @see PaymentConfig
 * @generated
 */
@ProviderType
public class PaymentConfigWrapper implements PaymentConfig,
	ModelWrapper<PaymentConfig> {
	public PaymentConfigWrapper(PaymentConfig paymentConfig) {
		_paymentConfig = paymentConfig;
	}

	@Override
	public Class<?> getModelClass() {
		return PaymentConfig.class;
	}

	@Override
	public String getModelClassName() {
		return PaymentConfig.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("uuid", getUuid());
		attributes.put("paymentConfigId", getPaymentConfigId());
		attributes.put("groupId", getGroupId());
		attributes.put("companyId", getCompanyId());
		attributes.put("userId", getUserId());
		attributes.put("userName", getUserName());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("govAgencyCode", getGovAgencyCode());
		attributes.put("govAgencyName", getGovAgencyName());
		attributes.put("govAgencyTaxNo", getGovAgencyTaxNo());
		attributes.put("invoiceTemplateNo", getInvoiceTemplateNo());
		attributes.put("invoiceIssueNo", getInvoiceIssueNo());
		attributes.put("invoiceLastNo", getInvoiceLastNo());
		attributes.put("invoiceForm", getInvoiceForm());
		attributes.put("bankInfo", getBankInfo());
		attributes.put("epaymentConfig", getEpaymentConfig());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		String uuid = (String)attributes.get("uuid");

		if (uuid != null) {
			setUuid(uuid);
		}

		Long paymentConfigId = (Long)attributes.get("paymentConfigId");

		if (paymentConfigId != null) {
			setPaymentConfigId(paymentConfigId);
		}

		Long groupId = (Long)attributes.get("groupId");

		if (groupId != null) {
			setGroupId(groupId);
		}

		Long companyId = (Long)attributes.get("companyId");

		if (companyId != null) {
			setCompanyId(companyId);
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

		String govAgencyCode = (String)attributes.get("govAgencyCode");

		if (govAgencyCode != null) {
			setGovAgencyCode(govAgencyCode);
		}

		String govAgencyName = (String)attributes.get("govAgencyName");

		if (govAgencyName != null) {
			setGovAgencyName(govAgencyName);
		}

		String govAgencyTaxNo = (String)attributes.get("govAgencyTaxNo");

		if (govAgencyTaxNo != null) {
			setGovAgencyTaxNo(govAgencyTaxNo);
		}

		String invoiceTemplateNo = (String)attributes.get("invoiceTemplateNo");

		if (invoiceTemplateNo != null) {
			setInvoiceTemplateNo(invoiceTemplateNo);
		}

		String invoiceIssueNo = (String)attributes.get("invoiceIssueNo");

		if (invoiceIssueNo != null) {
			setInvoiceIssueNo(invoiceIssueNo);
		}

		String invoiceLastNo = (String)attributes.get("invoiceLastNo");

		if (invoiceLastNo != null) {
			setInvoiceLastNo(invoiceLastNo);
		}

		String invoiceForm = (String)attributes.get("invoiceForm");

		if (invoiceForm != null) {
			setInvoiceForm(invoiceForm);
		}

		String bankInfo = (String)attributes.get("bankInfo");

		if (bankInfo != null) {
			setBankInfo(bankInfo);
		}

		String epaymentConfig = (String)attributes.get("epaymentConfig");

		if (epaymentConfig != null) {
			setEpaymentConfig(epaymentConfig);
		}
	}

	@Override
	public Object clone() {
		return new PaymentConfigWrapper((PaymentConfig)_paymentConfig.clone());
	}

	@Override
	public int compareTo(PaymentConfig paymentConfig) {
		return _paymentConfig.compareTo(paymentConfig);
	}

	/**
	* Returns the bank info of this payment config.
	*
	* @return the bank info of this payment config
	*/
	@Override
	public String getBankInfo() {
		return _paymentConfig.getBankInfo();
	}

	/**
	* Returns the company ID of this payment config.
	*
	* @return the company ID of this payment config
	*/
	@Override
	public long getCompanyId() {
		return _paymentConfig.getCompanyId();
	}

	/**
	* Returns the create date of this payment config.
	*
	* @return the create date of this payment config
	*/
	@Override
	public Date getCreateDate() {
		return _paymentConfig.getCreateDate();
	}

	/**
	* Returns the epayment config of this payment config.
	*
	* @return the epayment config of this payment config
	*/
	@Override
	public String getEpaymentConfig() {
		return _paymentConfig.getEpaymentConfig();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _paymentConfig.getExpandoBridge();
	}

	/**
	* Returns the gov agency code of this payment config.
	*
	* @return the gov agency code of this payment config
	*/
	@Override
	public String getGovAgencyCode() {
		return _paymentConfig.getGovAgencyCode();
	}

	/**
	* Returns the gov agency name of this payment config.
	*
	* @return the gov agency name of this payment config
	*/
	@Override
	public String getGovAgencyName() {
		return _paymentConfig.getGovAgencyName();
	}

	/**
	* Returns the gov agency tax no of this payment config.
	*
	* @return the gov agency tax no of this payment config
	*/
	@Override
	public String getGovAgencyTaxNo() {
		return _paymentConfig.getGovAgencyTaxNo();
	}

	/**
	* Returns the group ID of this payment config.
	*
	* @return the group ID of this payment config
	*/
	@Override
	public long getGroupId() {
		return _paymentConfig.getGroupId();
	}

	/**
	* Returns the invoice form of this payment config.
	*
	* @return the invoice form of this payment config
	*/
	@Override
	public String getInvoiceForm() {
		return _paymentConfig.getInvoiceForm();
	}

	/**
	* Returns the invoice issue no of this payment config.
	*
	* @return the invoice issue no of this payment config
	*/
	@Override
	public String getInvoiceIssueNo() {
		return _paymentConfig.getInvoiceIssueNo();
	}

	/**
	* Returns the invoice last no of this payment config.
	*
	* @return the invoice last no of this payment config
	*/
	@Override
	public String getInvoiceLastNo() {
		return _paymentConfig.getInvoiceLastNo();
	}

	/**
	* Returns the invoice template no of this payment config.
	*
	* @return the invoice template no of this payment config
	*/
	@Override
	public String getInvoiceTemplateNo() {
		return _paymentConfig.getInvoiceTemplateNo();
	}

	/**
	* Returns the modified date of this payment config.
	*
	* @return the modified date of this payment config
	*/
	@Override
	public Date getModifiedDate() {
		return _paymentConfig.getModifiedDate();
	}

	/**
	* Returns the payment config ID of this payment config.
	*
	* @return the payment config ID of this payment config
	*/
	@Override
	public long getPaymentConfigId() {
		return _paymentConfig.getPaymentConfigId();
	}

	/**
	* Returns the primary key of this payment config.
	*
	* @return the primary key of this payment config
	*/
	@Override
	public long getPrimaryKey() {
		return _paymentConfig.getPrimaryKey();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _paymentConfig.getPrimaryKeyObj();
	}

	/**
	* Returns the user ID of this payment config.
	*
	* @return the user ID of this payment config
	*/
	@Override
	public long getUserId() {
		return _paymentConfig.getUserId();
	}

	/**
	* Returns the user name of this payment config.
	*
	* @return the user name of this payment config
	*/
	@Override
	public String getUserName() {
		return _paymentConfig.getUserName();
	}

	/**
	* Returns the user uuid of this payment config.
	*
	* @return the user uuid of this payment config
	*/
	@Override
	public String getUserUuid() {
		return _paymentConfig.getUserUuid();
	}

	/**
	* Returns the uuid of this payment config.
	*
	* @return the uuid of this payment config
	*/
	@Override
	public String getUuid() {
		return _paymentConfig.getUuid();
	}

	@Override
	public int hashCode() {
		return _paymentConfig.hashCode();
	}

	@Override
	public boolean isCachedModel() {
		return _paymentConfig.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _paymentConfig.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _paymentConfig.isNew();
	}

	@Override
	public void persist() {
		_paymentConfig.persist();
	}

	/**
	* Sets the bank info of this payment config.
	*
	* @param bankInfo the bank info of this payment config
	*/
	@Override
	public void setBankInfo(String bankInfo) {
		_paymentConfig.setBankInfo(bankInfo);
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_paymentConfig.setCachedModel(cachedModel);
	}

	/**
	* Sets the company ID of this payment config.
	*
	* @param companyId the company ID of this payment config
	*/
	@Override
	public void setCompanyId(long companyId) {
		_paymentConfig.setCompanyId(companyId);
	}

	/**
	* Sets the create date of this payment config.
	*
	* @param createDate the create date of this payment config
	*/
	@Override
	public void setCreateDate(Date createDate) {
		_paymentConfig.setCreateDate(createDate);
	}

	/**
	* Sets the epayment config of this payment config.
	*
	* @param epaymentConfig the epayment config of this payment config
	*/
	@Override
	public void setEpaymentConfig(String epaymentConfig) {
		_paymentConfig.setEpaymentConfig(epaymentConfig);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_paymentConfig.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_paymentConfig.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_paymentConfig.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the gov agency code of this payment config.
	*
	* @param govAgencyCode the gov agency code of this payment config
	*/
	@Override
	public void setGovAgencyCode(String govAgencyCode) {
		_paymentConfig.setGovAgencyCode(govAgencyCode);
	}

	/**
	* Sets the gov agency name of this payment config.
	*
	* @param govAgencyName the gov agency name of this payment config
	*/
	@Override
	public void setGovAgencyName(String govAgencyName) {
		_paymentConfig.setGovAgencyName(govAgencyName);
	}

	/**
	* Sets the gov agency tax no of this payment config.
	*
	* @param govAgencyTaxNo the gov agency tax no of this payment config
	*/
	@Override
	public void setGovAgencyTaxNo(String govAgencyTaxNo) {
		_paymentConfig.setGovAgencyTaxNo(govAgencyTaxNo);
	}

	/**
	* Sets the group ID of this payment config.
	*
	* @param groupId the group ID of this payment config
	*/
	@Override
	public void setGroupId(long groupId) {
		_paymentConfig.setGroupId(groupId);
	}

	/**
	* Sets the invoice form of this payment config.
	*
	* @param invoiceForm the invoice form of this payment config
	*/
	@Override
	public void setInvoiceForm(String invoiceForm) {
		_paymentConfig.setInvoiceForm(invoiceForm);
	}

	/**
	* Sets the invoice issue no of this payment config.
	*
	* @param invoiceIssueNo the invoice issue no of this payment config
	*/
	@Override
	public void setInvoiceIssueNo(String invoiceIssueNo) {
		_paymentConfig.setInvoiceIssueNo(invoiceIssueNo);
	}

	/**
	* Sets the invoice last no of this payment config.
	*
	* @param invoiceLastNo the invoice last no of this payment config
	*/
	@Override
	public void setInvoiceLastNo(String invoiceLastNo) {
		_paymentConfig.setInvoiceLastNo(invoiceLastNo);
	}

	/**
	* Sets the invoice template no of this payment config.
	*
	* @param invoiceTemplateNo the invoice template no of this payment config
	*/
	@Override
	public void setInvoiceTemplateNo(String invoiceTemplateNo) {
		_paymentConfig.setInvoiceTemplateNo(invoiceTemplateNo);
	}

	/**
	* Sets the modified date of this payment config.
	*
	* @param modifiedDate the modified date of this payment config
	*/
	@Override
	public void setModifiedDate(Date modifiedDate) {
		_paymentConfig.setModifiedDate(modifiedDate);
	}

	@Override
	public void setNew(boolean n) {
		_paymentConfig.setNew(n);
	}

	/**
	* Sets the payment config ID of this payment config.
	*
	* @param paymentConfigId the payment config ID of this payment config
	*/
	@Override
	public void setPaymentConfigId(long paymentConfigId) {
		_paymentConfig.setPaymentConfigId(paymentConfigId);
	}

	/**
	* Sets the primary key of this payment config.
	*
	* @param primaryKey the primary key of this payment config
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_paymentConfig.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_paymentConfig.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the user ID of this payment config.
	*
	* @param userId the user ID of this payment config
	*/
	@Override
	public void setUserId(long userId) {
		_paymentConfig.setUserId(userId);
	}

	/**
	* Sets the user name of this payment config.
	*
	* @param userName the user name of this payment config
	*/
	@Override
	public void setUserName(String userName) {
		_paymentConfig.setUserName(userName);
	}

	/**
	* Sets the user uuid of this payment config.
	*
	* @param userUuid the user uuid of this payment config
	*/
	@Override
	public void setUserUuid(String userUuid) {
		_paymentConfig.setUserUuid(userUuid);
	}

	/**
	* Sets the uuid of this payment config.
	*
	* @param uuid the uuid of this payment config
	*/
	@Override
	public void setUuid(String uuid) {
		_paymentConfig.setUuid(uuid);
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<PaymentConfig> toCacheModel() {
		return _paymentConfig.toCacheModel();
	}

	@Override
	public PaymentConfig toEscapedModel() {
		return new PaymentConfigWrapper(_paymentConfig.toEscapedModel());
	}

	@Override
	public String toString() {
		return _paymentConfig.toString();
	}

	@Override
	public PaymentConfig toUnescapedModel() {
		return new PaymentConfigWrapper(_paymentConfig.toUnescapedModel());
	}

	@Override
	public String toXmlString() {
		return _paymentConfig.toXmlString();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof PaymentConfigWrapper)) {
			return false;
		}

		PaymentConfigWrapper paymentConfigWrapper = (PaymentConfigWrapper)obj;

		if (Objects.equals(_paymentConfig, paymentConfigWrapper._paymentConfig)) {
			return true;
		}

		return false;
	}

	@Override
	public StagedModelType getStagedModelType() {
		return _paymentConfig.getStagedModelType();
	}

	@Override
	public PaymentConfig getWrappedModel() {
		return _paymentConfig;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _paymentConfig.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _paymentConfig.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_paymentConfig.resetOriginalValues();
	}

	private final PaymentConfig _paymentConfig;
}