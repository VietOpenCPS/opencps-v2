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

package org.opencps.synchronization.model.impl;

import aQute.bnd.annotation.ProviderType;

import com.liferay.expando.kernel.model.ExpandoBridge;
import com.liferay.expando.kernel.util.ExpandoBridgeFactoryUtil;

import com.liferay.exportimport.kernel.lar.StagedModelType;

import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.model.impl.BaseModelImpl;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.util.DateUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.StringBundler;

import org.opencps.synchronization.model.PushDictGroup;
import org.opencps.synchronization.model.PushDictGroupModel;

import java.io.Serializable;

import java.sql.Types;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * The base model implementation for the PushDictGroup service. Represents a row in the &quot;opencps_pushdictgroup&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This implementation and its corresponding interface {@link PushDictGroupModel} exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link PushDictGroupImpl}.
 * </p>
 *
 * @author trungdk
 * @see PushDictGroupImpl
 * @see PushDictGroup
 * @see PushDictGroupModel
 * @generated
 */
@ProviderType
public class PushDictGroupModelImpl extends BaseModelImpl<PushDictGroup>
	implements PushDictGroupModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a push dict group model instance should use the {@link PushDictGroup} interface instead.
	 */
	public static final String TABLE_NAME = "opencps_pushdictgroup";
	public static final Object[][] TABLE_COLUMNS = {
			{ "uuid_", Types.VARCHAR },
			{ "pushDictGroupId", Types.BIGINT },
			{ "groupId", Types.BIGINT },
			{ "companyId", Types.BIGINT },
			{ "userId", Types.BIGINT },
			{ "userName", Types.VARCHAR },
			{ "createDate", Types.TIMESTAMP },
			{ "modifiedDate", Types.TIMESTAMP },
			{ "serverNo", Types.VARCHAR },
			{ "collectionCode", Types.VARCHAR },
			{ "groupCode", Types.VARCHAR },
			{ "groupName", Types.VARCHAR },
			{ "groupNameEN", Types.VARCHAR },
			{ "groupDescription", Types.VARCHAR },
			{ "itemCode", Types.VARCHAR },
			{ "method", Types.VARCHAR }
		};
	public static final Map<String, Integer> TABLE_COLUMNS_MAP = new HashMap<String, Integer>();

	static {
		TABLE_COLUMNS_MAP.put("uuid_", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("pushDictGroupId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("groupId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("companyId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("userId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("userName", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("createDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("modifiedDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("serverNo", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("collectionCode", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("groupCode", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("groupName", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("groupNameEN", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("groupDescription", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("itemCode", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("method", Types.VARCHAR);
	}

	public static final String TABLE_SQL_CREATE = "create table opencps_pushdictgroup (uuid_ VARCHAR(75) null,pushDictGroupId LONG not null primary key,groupId LONG,companyId LONG,userId LONG,userName VARCHAR(75) null,createDate DATE null,modifiedDate DATE null,serverNo VARCHAR(75) null,collectionCode VARCHAR(75) null,groupCode VARCHAR(75) null,groupName VARCHAR(75) null,groupNameEN VARCHAR(75) null,groupDescription VARCHAR(75) null,itemCode VARCHAR(75) null,method VARCHAR(75) null)";
	public static final String TABLE_SQL_DROP = "drop table opencps_pushdictgroup";
	public static final String ORDER_BY_JPQL = " ORDER BY pushDictGroup.modifiedDate ASC";
	public static final String ORDER_BY_SQL = " ORDER BY opencps_pushdictgroup.modifiedDate ASC";
	public static final String DATA_SOURCE = "liferayDataSource";
	public static final String SESSION_FACTORY = "liferaySessionFactory";
	public static final String TX_MANAGER = "liferayTransactionManager";
	public static final boolean ENTITY_CACHE_ENABLED = GetterUtil.getBoolean(backend.synchronization.service.util.ServiceProps.get(
				"value.object.entity.cache.enabled.org.opencps.synchronization.model.PushDictGroup"),
			true);
	public static final boolean FINDER_CACHE_ENABLED = GetterUtil.getBoolean(backend.synchronization.service.util.ServiceProps.get(
				"value.object.finder.cache.enabled.org.opencps.synchronization.model.PushDictGroup"),
			true);
	public static final boolean COLUMN_BITMASK_ENABLED = GetterUtil.getBoolean(backend.synchronization.service.util.ServiceProps.get(
				"value.object.column.bitmask.enabled.org.opencps.synchronization.model.PushDictGroup"),
			true);
	public static final long COLLECTIONCODE_COLUMN_BITMASK = 1L;
	public static final long COMPANYID_COLUMN_BITMASK = 2L;
	public static final long GROUPCODE_COLUMN_BITMASK = 4L;
	public static final long GROUPID_COLUMN_BITMASK = 8L;
	public static final long ITEMCODE_COLUMN_BITMASK = 16L;
	public static final long METHOD_COLUMN_BITMASK = 32L;
	public static final long SERVERNO_COLUMN_BITMASK = 64L;
	public static final long UUID_COLUMN_BITMASK = 128L;
	public static final long MODIFIEDDATE_COLUMN_BITMASK = 256L;
	public static final long LOCK_EXPIRATION_TIME = GetterUtil.getLong(backend.synchronization.service.util.ServiceProps.get(
				"lock.expiration.time.org.opencps.synchronization.model.PushDictGroup"));

	public PushDictGroupModelImpl() {
	}

	@Override
	public long getPrimaryKey() {
		return _pushDictGroupId;
	}

	@Override
	public void setPrimaryKey(long primaryKey) {
		setPushDictGroupId(primaryKey);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _pushDictGroupId;
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Class<?> getModelClass() {
		return PushDictGroup.class;
	}

	@Override
	public String getModelClassName() {
		return PushDictGroup.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("uuid", getUuid());
		attributes.put("pushDictGroupId", getPushDictGroupId());
		attributes.put("groupId", getGroupId());
		attributes.put("companyId", getCompanyId());
		attributes.put("userId", getUserId());
		attributes.put("userName", getUserName());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("serverNo", getServerNo());
		attributes.put("collectionCode", getCollectionCode());
		attributes.put("groupCode", getGroupCode());
		attributes.put("groupName", getGroupName());
		attributes.put("groupNameEN", getGroupNameEN());
		attributes.put("groupDescription", getGroupDescription());
		attributes.put("itemCode", getItemCode());
		attributes.put("method", getMethod());

		attributes.put("entityCacheEnabled", isEntityCacheEnabled());
		attributes.put("finderCacheEnabled", isFinderCacheEnabled());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		String uuid = (String)attributes.get("uuid");

		if (uuid != null) {
			setUuid(uuid);
		}

		Long pushDictGroupId = (Long)attributes.get("pushDictGroupId");

		if (pushDictGroupId != null) {
			setPushDictGroupId(pushDictGroupId);
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

		String serverNo = (String)attributes.get("serverNo");

		if (serverNo != null) {
			setServerNo(serverNo);
		}

		String collectionCode = (String)attributes.get("collectionCode");

		if (collectionCode != null) {
			setCollectionCode(collectionCode);
		}

		String groupCode = (String)attributes.get("groupCode");

		if (groupCode != null) {
			setGroupCode(groupCode);
		}

		String groupName = (String)attributes.get("groupName");

		if (groupName != null) {
			setGroupName(groupName);
		}

		String groupNameEN = (String)attributes.get("groupNameEN");

		if (groupNameEN != null) {
			setGroupNameEN(groupNameEN);
		}

		String groupDescription = (String)attributes.get("groupDescription");

		if (groupDescription != null) {
			setGroupDescription(groupDescription);
		}

		String itemCode = (String)attributes.get("itemCode");

		if (itemCode != null) {
			setItemCode(itemCode);
		}

		String method = (String)attributes.get("method");

		if (method != null) {
			setMethod(method);
		}
	}

	@Override
	public String getUuid() {
		if (_uuid == null) {
			return "";
		}
		else {
			return _uuid;
		}
	}

	@Override
	public void setUuid(String uuid) {
		if (_originalUuid == null) {
			_originalUuid = _uuid;
		}

		_uuid = uuid;
	}

	public String getOriginalUuid() {
		return GetterUtil.getString(_originalUuid);
	}

	@Override
	public long getPushDictGroupId() {
		return _pushDictGroupId;
	}

	@Override
	public void setPushDictGroupId(long pushDictGroupId) {
		_pushDictGroupId = pushDictGroupId;
	}

	@Override
	public long getGroupId() {
		return _groupId;
	}

	@Override
	public void setGroupId(long groupId) {
		_columnBitmask |= GROUPID_COLUMN_BITMASK;

		if (!_setOriginalGroupId) {
			_setOriginalGroupId = true;

			_originalGroupId = _groupId;
		}

		_groupId = groupId;
	}

	public long getOriginalGroupId() {
		return _originalGroupId;
	}

	@Override
	public long getCompanyId() {
		return _companyId;
	}

	@Override
	public void setCompanyId(long companyId) {
		_columnBitmask |= COMPANYID_COLUMN_BITMASK;

		if (!_setOriginalCompanyId) {
			_setOriginalCompanyId = true;

			_originalCompanyId = _companyId;
		}

		_companyId = companyId;
	}

	public long getOriginalCompanyId() {
		return _originalCompanyId;
	}

	@Override
	public long getUserId() {
		return _userId;
	}

	@Override
	public void setUserId(long userId) {
		_userId = userId;
	}

	@Override
	public String getUserUuid() {
		try {
			User user = UserLocalServiceUtil.getUserById(getUserId());

			return user.getUuid();
		}
		catch (PortalException pe) {
			return "";
		}
	}

	@Override
	public void setUserUuid(String userUuid) {
	}

	@Override
	public String getUserName() {
		if (_userName == null) {
			return "";
		}
		else {
			return _userName;
		}
	}

	@Override
	public void setUserName(String userName) {
		_userName = userName;
	}

	@Override
	public Date getCreateDate() {
		return _createDate;
	}

	@Override
	public void setCreateDate(Date createDate) {
		_createDate = createDate;
	}

	@Override
	public Date getModifiedDate() {
		return _modifiedDate;
	}

	public boolean hasSetModifiedDate() {
		return _setModifiedDate;
	}

	@Override
	public void setModifiedDate(Date modifiedDate) {
		_setModifiedDate = true;

		_columnBitmask = -1L;

		_modifiedDate = modifiedDate;
	}

	@Override
	public String getServerNo() {
		if (_serverNo == null) {
			return "";
		}
		else {
			return _serverNo;
		}
	}

	@Override
	public void setServerNo(String serverNo) {
		_columnBitmask |= SERVERNO_COLUMN_BITMASK;

		if (_originalServerNo == null) {
			_originalServerNo = _serverNo;
		}

		_serverNo = serverNo;
	}

	public String getOriginalServerNo() {
		return GetterUtil.getString(_originalServerNo);
	}

	@Override
	public String getCollectionCode() {
		if (_collectionCode == null) {
			return "";
		}
		else {
			return _collectionCode;
		}
	}

	@Override
	public void setCollectionCode(String collectionCode) {
		_columnBitmask |= COLLECTIONCODE_COLUMN_BITMASK;

		if (_originalCollectionCode == null) {
			_originalCollectionCode = _collectionCode;
		}

		_collectionCode = collectionCode;
	}

	public String getOriginalCollectionCode() {
		return GetterUtil.getString(_originalCollectionCode);
	}

	@Override
	public String getGroupCode() {
		if (_groupCode == null) {
			return "";
		}
		else {
			return _groupCode;
		}
	}

	@Override
	public void setGroupCode(String groupCode) {
		_columnBitmask |= GROUPCODE_COLUMN_BITMASK;

		if (_originalGroupCode == null) {
			_originalGroupCode = _groupCode;
		}

		_groupCode = groupCode;
	}

	public String getOriginalGroupCode() {
		return GetterUtil.getString(_originalGroupCode);
	}

	@Override
	public String getGroupName() {
		if (_groupName == null) {
			return "";
		}
		else {
			return _groupName;
		}
	}

	@Override
	public void setGroupName(String groupName) {
		_groupName = groupName;
	}

	@Override
	public String getGroupNameEN() {
		if (_groupNameEN == null) {
			return "";
		}
		else {
			return _groupNameEN;
		}
	}

	@Override
	public void setGroupNameEN(String groupNameEN) {
		_groupNameEN = groupNameEN;
	}

	@Override
	public String getGroupDescription() {
		if (_groupDescription == null) {
			return "";
		}
		else {
			return _groupDescription;
		}
	}

	@Override
	public void setGroupDescription(String groupDescription) {
		_groupDescription = groupDescription;
	}

	@Override
	public String getItemCode() {
		if (_itemCode == null) {
			return "";
		}
		else {
			return _itemCode;
		}
	}

	@Override
	public void setItemCode(String itemCode) {
		_columnBitmask |= ITEMCODE_COLUMN_BITMASK;

		if (_originalItemCode == null) {
			_originalItemCode = _itemCode;
		}

		_itemCode = itemCode;
	}

	public String getOriginalItemCode() {
		return GetterUtil.getString(_originalItemCode);
	}

	@Override
	public String getMethod() {
		if (_method == null) {
			return "";
		}
		else {
			return _method;
		}
	}

	@Override
	public void setMethod(String method) {
		_columnBitmask |= METHOD_COLUMN_BITMASK;

		if (_originalMethod == null) {
			_originalMethod = _method;
		}

		_method = method;
	}

	public String getOriginalMethod() {
		return GetterUtil.getString(_originalMethod);
	}

	@Override
	public StagedModelType getStagedModelType() {
		return new StagedModelType(PortalUtil.getClassNameId(
				PushDictGroup.class.getName()));
	}

	public long getColumnBitmask() {
		return _columnBitmask;
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return ExpandoBridgeFactoryUtil.getExpandoBridge(getCompanyId(),
			PushDictGroup.class.getName(), getPrimaryKey());
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		ExpandoBridge expandoBridge = getExpandoBridge();

		expandoBridge.setAttributes(serviceContext);
	}

	@Override
	public PushDictGroup toEscapedModel() {
		if (_escapedModel == null) {
			_escapedModel = (PushDictGroup)ProxyUtil.newProxyInstance(_classLoader,
					_escapedModelInterfaces, new AutoEscapeBeanHandler(this));
		}

		return _escapedModel;
	}

	@Override
	public Object clone() {
		PushDictGroupImpl pushDictGroupImpl = new PushDictGroupImpl();

		pushDictGroupImpl.setUuid(getUuid());
		pushDictGroupImpl.setPushDictGroupId(getPushDictGroupId());
		pushDictGroupImpl.setGroupId(getGroupId());
		pushDictGroupImpl.setCompanyId(getCompanyId());
		pushDictGroupImpl.setUserId(getUserId());
		pushDictGroupImpl.setUserName(getUserName());
		pushDictGroupImpl.setCreateDate(getCreateDate());
		pushDictGroupImpl.setModifiedDate(getModifiedDate());
		pushDictGroupImpl.setServerNo(getServerNo());
		pushDictGroupImpl.setCollectionCode(getCollectionCode());
		pushDictGroupImpl.setGroupCode(getGroupCode());
		pushDictGroupImpl.setGroupName(getGroupName());
		pushDictGroupImpl.setGroupNameEN(getGroupNameEN());
		pushDictGroupImpl.setGroupDescription(getGroupDescription());
		pushDictGroupImpl.setItemCode(getItemCode());
		pushDictGroupImpl.setMethod(getMethod());

		pushDictGroupImpl.resetOriginalValues();

		return pushDictGroupImpl;
	}

	@Override
	public int compareTo(PushDictGroup pushDictGroup) {
		int value = 0;

		value = DateUtil.compareTo(getModifiedDate(),
				pushDictGroup.getModifiedDate());

		if (value != 0) {
			return value;
		}

		return 0;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof PushDictGroup)) {
			return false;
		}

		PushDictGroup pushDictGroup = (PushDictGroup)obj;

		long primaryKey = pushDictGroup.getPrimaryKey();

		if (getPrimaryKey() == primaryKey) {
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public int hashCode() {
		return (int)getPrimaryKey();
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return ENTITY_CACHE_ENABLED;
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return FINDER_CACHE_ENABLED;
	}

	@Override
	public void resetOriginalValues() {
		PushDictGroupModelImpl pushDictGroupModelImpl = this;

		pushDictGroupModelImpl._originalUuid = pushDictGroupModelImpl._uuid;

		pushDictGroupModelImpl._originalGroupId = pushDictGroupModelImpl._groupId;

		pushDictGroupModelImpl._setOriginalGroupId = false;

		pushDictGroupModelImpl._originalCompanyId = pushDictGroupModelImpl._companyId;

		pushDictGroupModelImpl._setOriginalCompanyId = false;

		pushDictGroupModelImpl._setModifiedDate = false;

		pushDictGroupModelImpl._originalServerNo = pushDictGroupModelImpl._serverNo;

		pushDictGroupModelImpl._originalCollectionCode = pushDictGroupModelImpl._collectionCode;

		pushDictGroupModelImpl._originalGroupCode = pushDictGroupModelImpl._groupCode;

		pushDictGroupModelImpl._originalItemCode = pushDictGroupModelImpl._itemCode;

		pushDictGroupModelImpl._originalMethod = pushDictGroupModelImpl._method;

		pushDictGroupModelImpl._columnBitmask = 0;
	}

	@Override
	public CacheModel<PushDictGroup> toCacheModel() {
		PushDictGroupCacheModel pushDictGroupCacheModel = new PushDictGroupCacheModel();

		pushDictGroupCacheModel.uuid = getUuid();

		String uuid = pushDictGroupCacheModel.uuid;

		if ((uuid != null) && (uuid.length() == 0)) {
			pushDictGroupCacheModel.uuid = null;
		}

		pushDictGroupCacheModel.pushDictGroupId = getPushDictGroupId();

		pushDictGroupCacheModel.groupId = getGroupId();

		pushDictGroupCacheModel.companyId = getCompanyId();

		pushDictGroupCacheModel.userId = getUserId();

		pushDictGroupCacheModel.userName = getUserName();

		String userName = pushDictGroupCacheModel.userName;

		if ((userName != null) && (userName.length() == 0)) {
			pushDictGroupCacheModel.userName = null;
		}

		Date createDate = getCreateDate();

		if (createDate != null) {
			pushDictGroupCacheModel.createDate = createDate.getTime();
		}
		else {
			pushDictGroupCacheModel.createDate = Long.MIN_VALUE;
		}

		Date modifiedDate = getModifiedDate();

		if (modifiedDate != null) {
			pushDictGroupCacheModel.modifiedDate = modifiedDate.getTime();
		}
		else {
			pushDictGroupCacheModel.modifiedDate = Long.MIN_VALUE;
		}

		pushDictGroupCacheModel.serverNo = getServerNo();

		String serverNo = pushDictGroupCacheModel.serverNo;

		if ((serverNo != null) && (serverNo.length() == 0)) {
			pushDictGroupCacheModel.serverNo = null;
		}

		pushDictGroupCacheModel.collectionCode = getCollectionCode();

		String collectionCode = pushDictGroupCacheModel.collectionCode;

		if ((collectionCode != null) && (collectionCode.length() == 0)) {
			pushDictGroupCacheModel.collectionCode = null;
		}

		pushDictGroupCacheModel.groupCode = getGroupCode();

		String groupCode = pushDictGroupCacheModel.groupCode;

		if ((groupCode != null) && (groupCode.length() == 0)) {
			pushDictGroupCacheModel.groupCode = null;
		}

		pushDictGroupCacheModel.groupName = getGroupName();

		String groupName = pushDictGroupCacheModel.groupName;

		if ((groupName != null) && (groupName.length() == 0)) {
			pushDictGroupCacheModel.groupName = null;
		}

		pushDictGroupCacheModel.groupNameEN = getGroupNameEN();

		String groupNameEN = pushDictGroupCacheModel.groupNameEN;

		if ((groupNameEN != null) && (groupNameEN.length() == 0)) {
			pushDictGroupCacheModel.groupNameEN = null;
		}

		pushDictGroupCacheModel.groupDescription = getGroupDescription();

		String groupDescription = pushDictGroupCacheModel.groupDescription;

		if ((groupDescription != null) && (groupDescription.length() == 0)) {
			pushDictGroupCacheModel.groupDescription = null;
		}

		pushDictGroupCacheModel.itemCode = getItemCode();

		String itemCode = pushDictGroupCacheModel.itemCode;

		if ((itemCode != null) && (itemCode.length() == 0)) {
			pushDictGroupCacheModel.itemCode = null;
		}

		pushDictGroupCacheModel.method = getMethod();

		String method = pushDictGroupCacheModel.method;

		if ((method != null) && (method.length() == 0)) {
			pushDictGroupCacheModel.method = null;
		}

		return pushDictGroupCacheModel;
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(33);

		sb.append("{uuid=");
		sb.append(getUuid());
		sb.append(", pushDictGroupId=");
		sb.append(getPushDictGroupId());
		sb.append(", groupId=");
		sb.append(getGroupId());
		sb.append(", companyId=");
		sb.append(getCompanyId());
		sb.append(", userId=");
		sb.append(getUserId());
		sb.append(", userName=");
		sb.append(getUserName());
		sb.append(", createDate=");
		sb.append(getCreateDate());
		sb.append(", modifiedDate=");
		sb.append(getModifiedDate());
		sb.append(", serverNo=");
		sb.append(getServerNo());
		sb.append(", collectionCode=");
		sb.append(getCollectionCode());
		sb.append(", groupCode=");
		sb.append(getGroupCode());
		sb.append(", groupName=");
		sb.append(getGroupName());
		sb.append(", groupNameEN=");
		sb.append(getGroupNameEN());
		sb.append(", groupDescription=");
		sb.append(getGroupDescription());
		sb.append(", itemCode=");
		sb.append(getItemCode());
		sb.append(", method=");
		sb.append(getMethod());
		sb.append("}");

		return sb.toString();
	}

	@Override
	public String toXmlString() {
		StringBundler sb = new StringBundler(52);

		sb.append("<model><model-name>");
		sb.append("org.opencps.synchronization.model.PushDictGroup");
		sb.append("</model-name>");

		sb.append(
			"<column><column-name>uuid</column-name><column-value><![CDATA[");
		sb.append(getUuid());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>pushDictGroupId</column-name><column-value><![CDATA[");
		sb.append(getPushDictGroupId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>groupId</column-name><column-value><![CDATA[");
		sb.append(getGroupId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>companyId</column-name><column-value><![CDATA[");
		sb.append(getCompanyId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>userId</column-name><column-value><![CDATA[");
		sb.append(getUserId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>userName</column-name><column-value><![CDATA[");
		sb.append(getUserName());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>createDate</column-name><column-value><![CDATA[");
		sb.append(getCreateDate());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>modifiedDate</column-name><column-value><![CDATA[");
		sb.append(getModifiedDate());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>serverNo</column-name><column-value><![CDATA[");
		sb.append(getServerNo());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>collectionCode</column-name><column-value><![CDATA[");
		sb.append(getCollectionCode());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>groupCode</column-name><column-value><![CDATA[");
		sb.append(getGroupCode());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>groupName</column-name><column-value><![CDATA[");
		sb.append(getGroupName());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>groupNameEN</column-name><column-value><![CDATA[");
		sb.append(getGroupNameEN());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>groupDescription</column-name><column-value><![CDATA[");
		sb.append(getGroupDescription());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>itemCode</column-name><column-value><![CDATA[");
		sb.append(getItemCode());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>method</column-name><column-value><![CDATA[");
		sb.append(getMethod());
		sb.append("]]></column-value></column>");

		sb.append("</model>");

		return sb.toString();
	}

	private static final ClassLoader _classLoader = PushDictGroup.class.getClassLoader();
	private static final Class<?>[] _escapedModelInterfaces = new Class[] {
			PushDictGroup.class, ModelWrapper.class
		};
	private String _uuid;
	private String _originalUuid;
	private long _pushDictGroupId;
	private long _groupId;
	private long _originalGroupId;
	private boolean _setOriginalGroupId;
	private long _companyId;
	private long _originalCompanyId;
	private boolean _setOriginalCompanyId;
	private long _userId;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private boolean _setModifiedDate;
	private String _serverNo;
	private String _originalServerNo;
	private String _collectionCode;
	private String _originalCollectionCode;
	private String _groupCode;
	private String _originalGroupCode;
	private String _groupName;
	private String _groupNameEN;
	private String _groupDescription;
	private String _itemCode;
	private String _originalItemCode;
	private String _method;
	private String _originalMethod;
	private long _columnBitmask;
	private PushDictGroup _escapedModel;
}