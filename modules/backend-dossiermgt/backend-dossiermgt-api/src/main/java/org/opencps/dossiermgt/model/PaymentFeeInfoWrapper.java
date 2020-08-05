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
 * This class is a wrapper for {@link PaymentFeeInfo}.
 * </p>
 *
 * @author huymq
 * @see PaymentFeeInfo
 * @generated
 */
@ProviderType
public class PaymentFeeInfoWrapper implements PaymentFeeInfo,
	ModelWrapper<PaymentFeeInfo> {
	public PaymentFeeInfoWrapper(PaymentFeeInfo paymentFeeInfo) {
		_paymentFeeInfo = paymentFeeInfo;
	}

	@Override
	public Class<?> getModelClass() {
		return PaymentFeeInfo.class;
	}

	@Override
	public String getModelClassName() {
		return PaymentFeeInfo.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("uuid", getUuid());
		attributes.put("paymentFeeInfoId", getPaymentFeeInfoId());
		attributes.put("groupId", getGroupId());
		attributes.put("companyId", getCompanyId());
		attributes.put("userId", getUserId());
		attributes.put("userName", getUserName());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("type", getType());
		attributes.put("paymentFeeCode", getPaymentFeeCode());
		attributes.put("paymentFeeName", getPaymentFeeName());
		attributes.put("amount", getAmount());
		attributes.put("serviceConfigMappingId", getServiceConfigMappingId());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		String uuid = (String)attributes.get("uuid");

		if (uuid != null) {
			setUuid(uuid);
		}

		Long paymentFeeInfoId = (Long)attributes.get("paymentFeeInfoId");

		if (paymentFeeInfoId != null) {
			setPaymentFeeInfoId(paymentFeeInfoId);
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

		String type = (String)attributes.get("type");

		if (type != null) {
			setType(type);
		}

		String paymentFeeCode = (String)attributes.get("paymentFeeCode");

		if (paymentFeeCode != null) {
			setPaymentFeeCode(paymentFeeCode);
		}

		String paymentFeeName = (String)attributes.get("paymentFeeName");

		if (paymentFeeName != null) {
			setPaymentFeeName(paymentFeeName);
		}

		String amount = (String)attributes.get("amount");

		if (amount != null) {
			setAmount(amount);
		}

		Long serviceConfigMappingId = (Long)attributes.get(
				"serviceConfigMappingId");

		if (serviceConfigMappingId != null) {
			setServiceConfigMappingId(serviceConfigMappingId);
		}
	}

	@Override
	public Object clone() {
		return new PaymentFeeInfoWrapper((PaymentFeeInfo)_paymentFeeInfo.clone());
	}

	@Override
	public int compareTo(PaymentFeeInfo paymentFeeInfo) {
		return _paymentFeeInfo.compareTo(paymentFeeInfo);
	}

	/**
	* Returns the amount of this payment fee info.
	*
	* @return the amount of this payment fee info
	*/
	@Override
	public String getAmount() {
		return _paymentFeeInfo.getAmount();
	}

	/**
	* Returns the company ID of this payment fee info.
	*
	* @return the company ID of this payment fee info
	*/
	@Override
	public long getCompanyId() {
		return _paymentFeeInfo.getCompanyId();
	}

	/**
	* Returns the create date of this payment fee info.
	*
	* @return the create date of this payment fee info
	*/
	@Override
	public Date getCreateDate() {
		return _paymentFeeInfo.getCreateDate();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _paymentFeeInfo.getExpandoBridge();
	}

	/**
	* Returns the group ID of this payment fee info.
	*
	* @return the group ID of this payment fee info
	*/
	@Override
	public long getGroupId() {
		return _paymentFeeInfo.getGroupId();
	}

	/**
	* Returns the modified date of this payment fee info.
	*
	* @return the modified date of this payment fee info
	*/
	@Override
	public Date getModifiedDate() {
		return _paymentFeeInfo.getModifiedDate();
	}

	/**
	* Returns the payment fee code of this payment fee info.
	*
	* @return the payment fee code of this payment fee info
	*/
	@Override
	public String getPaymentFeeCode() {
		return _paymentFeeInfo.getPaymentFeeCode();
	}

	/**
	* Returns the payment fee info ID of this payment fee info.
	*
	* @return the payment fee info ID of this payment fee info
	*/
	@Override
	public long getPaymentFeeInfoId() {
		return _paymentFeeInfo.getPaymentFeeInfoId();
	}

	/**
	* Returns the payment fee name of this payment fee info.
	*
	* @return the payment fee name of this payment fee info
	*/
	@Override
	public String getPaymentFeeName() {
		return _paymentFeeInfo.getPaymentFeeName();
	}

	/**
	* Returns the primary key of this payment fee info.
	*
	* @return the primary key of this payment fee info
	*/
	@Override
	public long getPrimaryKey() {
		return _paymentFeeInfo.getPrimaryKey();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _paymentFeeInfo.getPrimaryKeyObj();
	}

	/**
	* Returns the service config mapping ID of this payment fee info.
	*
	* @return the service config mapping ID of this payment fee info
	*/
	@Override
	public long getServiceConfigMappingId() {
		return _paymentFeeInfo.getServiceConfigMappingId();
	}

	/**
	* Returns the type of this payment fee info.
	*
	* @return the type of this payment fee info
	*/
	@Override
	public String getType() {
		return _paymentFeeInfo.getType();
	}

	/**
	* Returns the user ID of this payment fee info.
	*
	* @return the user ID of this payment fee info
	*/
	@Override
	public long getUserId() {
		return _paymentFeeInfo.getUserId();
	}

	/**
	* Returns the user name of this payment fee info.
	*
	* @return the user name of this payment fee info
	*/
	@Override
	public String getUserName() {
		return _paymentFeeInfo.getUserName();
	}

	/**
	* Returns the user uuid of this payment fee info.
	*
	* @return the user uuid of this payment fee info
	*/
	@Override
	public String getUserUuid() {
		return _paymentFeeInfo.getUserUuid();
	}

	/**
	* Returns the uuid of this payment fee info.
	*
	* @return the uuid of this payment fee info
	*/
	@Override
	public String getUuid() {
		return _paymentFeeInfo.getUuid();
	}

	@Override
	public int hashCode() {
		return _paymentFeeInfo.hashCode();
	}

	@Override
	public boolean isCachedModel() {
		return _paymentFeeInfo.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _paymentFeeInfo.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _paymentFeeInfo.isNew();
	}

	@Override
	public void persist() {
		_paymentFeeInfo.persist();
	}

	/**
	* Sets the amount of this payment fee info.
	*
	* @param amount the amount of this payment fee info
	*/
	@Override
	public void setAmount(String amount) {
		_paymentFeeInfo.setAmount(amount);
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_paymentFeeInfo.setCachedModel(cachedModel);
	}

	/**
	* Sets the company ID of this payment fee info.
	*
	* @param companyId the company ID of this payment fee info
	*/
	@Override
	public void setCompanyId(long companyId) {
		_paymentFeeInfo.setCompanyId(companyId);
	}

	/**
	* Sets the create date of this payment fee info.
	*
	* @param createDate the create date of this payment fee info
	*/
	@Override
	public void setCreateDate(Date createDate) {
		_paymentFeeInfo.setCreateDate(createDate);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_paymentFeeInfo.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_paymentFeeInfo.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_paymentFeeInfo.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the group ID of this payment fee info.
	*
	* @param groupId the group ID of this payment fee info
	*/
	@Override
	public void setGroupId(long groupId) {
		_paymentFeeInfo.setGroupId(groupId);
	}

	/**
	* Sets the modified date of this payment fee info.
	*
	* @param modifiedDate the modified date of this payment fee info
	*/
	@Override
	public void setModifiedDate(Date modifiedDate) {
		_paymentFeeInfo.setModifiedDate(modifiedDate);
	}

	@Override
	public void setNew(boolean n) {
		_paymentFeeInfo.setNew(n);
	}

	/**
	* Sets the payment fee code of this payment fee info.
	*
	* @param paymentFeeCode the payment fee code of this payment fee info
	*/
	@Override
	public void setPaymentFeeCode(String paymentFeeCode) {
		_paymentFeeInfo.setPaymentFeeCode(paymentFeeCode);
	}

	/**
	* Sets the payment fee info ID of this payment fee info.
	*
	* @param paymentFeeInfoId the payment fee info ID of this payment fee info
	*/
	@Override
	public void setPaymentFeeInfoId(long paymentFeeInfoId) {
		_paymentFeeInfo.setPaymentFeeInfoId(paymentFeeInfoId);
	}

	/**
	* Sets the payment fee name of this payment fee info.
	*
	* @param paymentFeeName the payment fee name of this payment fee info
	*/
	@Override
	public void setPaymentFeeName(String paymentFeeName) {
		_paymentFeeInfo.setPaymentFeeName(paymentFeeName);
	}

	/**
	* Sets the primary key of this payment fee info.
	*
	* @param primaryKey the primary key of this payment fee info
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_paymentFeeInfo.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_paymentFeeInfo.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the service config mapping ID of this payment fee info.
	*
	* @param serviceConfigMappingId the service config mapping ID of this payment fee info
	*/
	@Override
	public void setServiceConfigMappingId(long serviceConfigMappingId) {
		_paymentFeeInfo.setServiceConfigMappingId(serviceConfigMappingId);
	}

	/**
	* Sets the type of this payment fee info.
	*
	* @param type the type of this payment fee info
	*/
	@Override
	public void setType(String type) {
		_paymentFeeInfo.setType(type);
	}

	/**
	* Sets the user ID of this payment fee info.
	*
	* @param userId the user ID of this payment fee info
	*/
	@Override
	public void setUserId(long userId) {
		_paymentFeeInfo.setUserId(userId);
	}

	/**
	* Sets the user name of this payment fee info.
	*
	* @param userName the user name of this payment fee info
	*/
	@Override
	public void setUserName(String userName) {
		_paymentFeeInfo.setUserName(userName);
	}

	/**
	* Sets the user uuid of this payment fee info.
	*
	* @param userUuid the user uuid of this payment fee info
	*/
	@Override
	public void setUserUuid(String userUuid) {
		_paymentFeeInfo.setUserUuid(userUuid);
	}

	/**
	* Sets the uuid of this payment fee info.
	*
	* @param uuid the uuid of this payment fee info
	*/
	@Override
	public void setUuid(String uuid) {
		_paymentFeeInfo.setUuid(uuid);
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<PaymentFeeInfo> toCacheModel() {
		return _paymentFeeInfo.toCacheModel();
	}

	@Override
	public PaymentFeeInfo toEscapedModel() {
		return new PaymentFeeInfoWrapper(_paymentFeeInfo.toEscapedModel());
	}

	@Override
	public String toString() {
		return _paymentFeeInfo.toString();
	}

	@Override
	public PaymentFeeInfo toUnescapedModel() {
		return new PaymentFeeInfoWrapper(_paymentFeeInfo.toUnescapedModel());
	}

	@Override
	public String toXmlString() {
		return _paymentFeeInfo.toXmlString();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof PaymentFeeInfoWrapper)) {
			return false;
		}

		PaymentFeeInfoWrapper paymentFeeInfoWrapper = (PaymentFeeInfoWrapper)obj;

		if (Objects.equals(_paymentFeeInfo,
					paymentFeeInfoWrapper._paymentFeeInfo)) {
			return true;
		}

		return false;
	}

	@Override
	public StagedModelType getStagedModelType() {
		return _paymentFeeInfo.getStagedModelType();
	}

	@Override
	public PaymentFeeInfo getWrappedModel() {
		return _paymentFeeInfo;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _paymentFeeInfo.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _paymentFeeInfo.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_paymentFeeInfo.resetOriginalValues();
	}

	private final PaymentFeeInfo _paymentFeeInfo;
}