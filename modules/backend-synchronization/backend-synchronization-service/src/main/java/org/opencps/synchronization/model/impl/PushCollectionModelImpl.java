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

import org.opencps.synchronization.model.PushCollection;
import org.opencps.synchronization.model.PushCollectionModel;

import java.io.Serializable;

import java.sql.Types;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * The base model implementation for the PushCollection service. Represents a row in the &quot;opencps_pushcollection&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This implementation and its corresponding interface {@link PushCollectionModel} exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link PushCollectionImpl}.
 * </p>
 *
 * @author trungdk
 * @see PushCollectionImpl
 * @see PushCollection
 * @see PushCollectionModel
 * @generated
 */
@ProviderType
public class PushCollectionModelImpl extends BaseModelImpl<PushCollection>
	implements PushCollectionModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a push collection model instance should use the {@link PushCollection} interface instead.
	 */
	public static final String TABLE_NAME = "opencps_pushcollection";
	public static final Object[][] TABLE_COLUMNS = {
			{ "uuid_", Types.VARCHAR },
			{ "pushCollectionId", Types.BIGINT },
			{ "groupId", Types.BIGINT },
			{ "companyId", Types.BIGINT },
			{ "userId", Types.BIGINT },
			{ "userName", Types.VARCHAR },
			{ "createDate", Types.TIMESTAMP },
			{ "modifiedDate", Types.TIMESTAMP },
			{ "serverNo", Types.VARCHAR },
			{ "collectionCode", Types.VARCHAR },
			{ "collectionName", Types.VARCHAR },
			{ "collectionNameEN", Types.VARCHAR },
			{ "description", Types.VARCHAR },
			{ "dataForm", Types.VARCHAR },
			{ "method", Types.VARCHAR }
		};
	public static final Map<String, Integer> TABLE_COLUMNS_MAP = new HashMap<String, Integer>();

	static {
		TABLE_COLUMNS_MAP.put("uuid_", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("pushCollectionId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("groupId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("companyId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("userId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("userName", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("createDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("modifiedDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("serverNo", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("collectionCode", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("collectionName", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("collectionNameEN", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("description", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("dataForm", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("method", Types.VARCHAR);
	}

	public static final String TABLE_SQL_CREATE = "create table opencps_pushcollection (uuid_ VARCHAR(75) null,pushCollectionId LONG not null primary key,groupId LONG,companyId LONG,userId LONG,userName VARCHAR(75) null,createDate DATE null,modifiedDate DATE null,serverNo VARCHAR(75) null,collectionCode VARCHAR(75) null,collectionName VARCHAR(75) null,collectionNameEN VARCHAR(75) null,description VARCHAR(75) null,dataForm VARCHAR(75) null,method VARCHAR(75) null)";
	public static final String TABLE_SQL_DROP = "drop table opencps_pushcollection";
	public static final String ORDER_BY_JPQL = " ORDER BY pushCollection.modifiedDate ASC";
	public static final String ORDER_BY_SQL = " ORDER BY opencps_pushcollection.modifiedDate ASC";
	public static final String DATA_SOURCE = "liferayDataSource";
	public static final String SESSION_FACTORY = "liferaySessionFactory";
	public static final String TX_MANAGER = "liferayTransactionManager";
	public static final boolean ENTITY_CACHE_ENABLED = GetterUtil.getBoolean(backend.synchronization.service.util.ServiceProps.get(
				"value.object.entity.cache.enabled.org.opencps.synchronization.model.PushCollection"),
			true);
	public static final boolean FINDER_CACHE_ENABLED = GetterUtil.getBoolean(backend.synchronization.service.util.ServiceProps.get(
				"value.object.finder.cache.enabled.org.opencps.synchronization.model.PushCollection"),
			true);
	public static final boolean COLUMN_BITMASK_ENABLED = GetterUtil.getBoolean(backend.synchronization.service.util.ServiceProps.get(
				"value.object.column.bitmask.enabled.org.opencps.synchronization.model.PushCollection"),
			true);
	public static final long COLLECTIONCODE_COLUMN_BITMASK = 1L;
	public static final long COMPANYID_COLUMN_BITMASK = 2L;
	public static final long GROUPID_COLUMN_BITMASK = 4L;
	public static final long METHOD_COLUMN_BITMASK = 8L;
	public static final long SERVERNO_COLUMN_BITMASK = 16L;
	public static final long UUID_COLUMN_BITMASK = 32L;
	public static final long MODIFIEDDATE_COLUMN_BITMASK = 64L;
	public static final long LOCK_EXPIRATION_TIME = GetterUtil.getLong(backend.synchronization.service.util.ServiceProps.get(
				"lock.expiration.time.org.opencps.synchronization.model.PushCollection"));

	public PushCollectionModelImpl() {
	}

	@Override
	public long getPrimaryKey() {
		return _pushCollectionId;
	}

	@Override
	public void setPrimaryKey(long primaryKey) {
		setPushCollectionId(primaryKey);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _pushCollectionId;
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Class<?> getModelClass() {
		return PushCollection.class;
	}

	@Override
	public String getModelClassName() {
		return PushCollection.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("uuid", getUuid());
		attributes.put("pushCollectionId", getPushCollectionId());
		attributes.put("groupId", getGroupId());
		attributes.put("companyId", getCompanyId());
		attributes.put("userId", getUserId());
		attributes.put("userName", getUserName());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("serverNo", getServerNo());
		attributes.put("collectionCode", getCollectionCode());
		attributes.put("collectionName", getCollectionName());
		attributes.put("collectionNameEN", getCollectionNameEN());
		attributes.put("description", getDescription());
		attributes.put("dataForm", getDataForm());
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

		Long pushCollectionId = (Long)attributes.get("pushCollectionId");

		if (pushCollectionId != null) {
			setPushCollectionId(pushCollectionId);
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

		String collectionName = (String)attributes.get("collectionName");

		if (collectionName != null) {
			setCollectionName(collectionName);
		}

		String collectionNameEN = (String)attributes.get("collectionNameEN");

		if (collectionNameEN != null) {
			setCollectionNameEN(collectionNameEN);
		}

		String description = (String)attributes.get("description");

		if (description != null) {
			setDescription(description);
		}

		String dataForm = (String)attributes.get("dataForm");

		if (dataForm != null) {
			setDataForm(dataForm);
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
	public long getPushCollectionId() {
		return _pushCollectionId;
	}

	@Override
	public void setPushCollectionId(long pushCollectionId) {
		_pushCollectionId = pushCollectionId;
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
	public String getCollectionName() {
		if (_collectionName == null) {
			return "";
		}
		else {
			return _collectionName;
		}
	}

	@Override
	public void setCollectionName(String collectionName) {
		_collectionName = collectionName;
	}

	@Override
	public String getCollectionNameEN() {
		if (_collectionNameEN == null) {
			return "";
		}
		else {
			return _collectionNameEN;
		}
	}

	@Override
	public void setCollectionNameEN(String collectionNameEN) {
		_collectionNameEN = collectionNameEN;
	}

	@Override
	public String getDescription() {
		if (_description == null) {
			return "";
		}
		else {
			return _description;
		}
	}

	@Override
	public void setDescription(String description) {
		_description = description;
	}

	@Override
	public String getDataForm() {
		if (_dataForm == null) {
			return "";
		}
		else {
			return _dataForm;
		}
	}

	@Override
	public void setDataForm(String dataForm) {
		_dataForm = dataForm;
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
				PushCollection.class.getName()));
	}

	public long getColumnBitmask() {
		return _columnBitmask;
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return ExpandoBridgeFactoryUtil.getExpandoBridge(getCompanyId(),
			PushCollection.class.getName(), getPrimaryKey());
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		ExpandoBridge expandoBridge = getExpandoBridge();

		expandoBridge.setAttributes(serviceContext);
	}

	@Override
	public PushCollection toEscapedModel() {
		if (_escapedModel == null) {
			_escapedModel = (PushCollection)ProxyUtil.newProxyInstance(_classLoader,
					_escapedModelInterfaces, new AutoEscapeBeanHandler(this));
		}

		return _escapedModel;
	}

	@Override
	public Object clone() {
		PushCollectionImpl pushCollectionImpl = new PushCollectionImpl();

		pushCollectionImpl.setUuid(getUuid());
		pushCollectionImpl.setPushCollectionId(getPushCollectionId());
		pushCollectionImpl.setGroupId(getGroupId());
		pushCollectionImpl.setCompanyId(getCompanyId());
		pushCollectionImpl.setUserId(getUserId());
		pushCollectionImpl.setUserName(getUserName());
		pushCollectionImpl.setCreateDate(getCreateDate());
		pushCollectionImpl.setModifiedDate(getModifiedDate());
		pushCollectionImpl.setServerNo(getServerNo());
		pushCollectionImpl.setCollectionCode(getCollectionCode());
		pushCollectionImpl.setCollectionName(getCollectionName());
		pushCollectionImpl.setCollectionNameEN(getCollectionNameEN());
		pushCollectionImpl.setDescription(getDescription());
		pushCollectionImpl.setDataForm(getDataForm());
		pushCollectionImpl.setMethod(getMethod());

		pushCollectionImpl.resetOriginalValues();

		return pushCollectionImpl;
	}

	@Override
	public int compareTo(PushCollection pushCollection) {
		int value = 0;

		value = DateUtil.compareTo(getModifiedDate(),
				pushCollection.getModifiedDate());

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

		if (!(obj instanceof PushCollection)) {
			return false;
		}

		PushCollection pushCollection = (PushCollection)obj;

		long primaryKey = pushCollection.getPrimaryKey();

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
		PushCollectionModelImpl pushCollectionModelImpl = this;

		pushCollectionModelImpl._originalUuid = pushCollectionModelImpl._uuid;

		pushCollectionModelImpl._originalGroupId = pushCollectionModelImpl._groupId;

		pushCollectionModelImpl._setOriginalGroupId = false;

		pushCollectionModelImpl._originalCompanyId = pushCollectionModelImpl._companyId;

		pushCollectionModelImpl._setOriginalCompanyId = false;

		pushCollectionModelImpl._setModifiedDate = false;

		pushCollectionModelImpl._originalServerNo = pushCollectionModelImpl._serverNo;

		pushCollectionModelImpl._originalCollectionCode = pushCollectionModelImpl._collectionCode;

		pushCollectionModelImpl._originalMethod = pushCollectionModelImpl._method;

		pushCollectionModelImpl._columnBitmask = 0;
	}

	@Override
	public CacheModel<PushCollection> toCacheModel() {
		PushCollectionCacheModel pushCollectionCacheModel = new PushCollectionCacheModel();

		pushCollectionCacheModel.uuid = getUuid();

		String uuid = pushCollectionCacheModel.uuid;

		if ((uuid != null) && (uuid.length() == 0)) {
			pushCollectionCacheModel.uuid = null;
		}

		pushCollectionCacheModel.pushCollectionId = getPushCollectionId();

		pushCollectionCacheModel.groupId = getGroupId();

		pushCollectionCacheModel.companyId = getCompanyId();

		pushCollectionCacheModel.userId = getUserId();

		pushCollectionCacheModel.userName = getUserName();

		String userName = pushCollectionCacheModel.userName;

		if ((userName != null) && (userName.length() == 0)) {
			pushCollectionCacheModel.userName = null;
		}

		Date createDate = getCreateDate();

		if (createDate != null) {
			pushCollectionCacheModel.createDate = createDate.getTime();
		}
		else {
			pushCollectionCacheModel.createDate = Long.MIN_VALUE;
		}

		Date modifiedDate = getModifiedDate();

		if (modifiedDate != null) {
			pushCollectionCacheModel.modifiedDate = modifiedDate.getTime();
		}
		else {
			pushCollectionCacheModel.modifiedDate = Long.MIN_VALUE;
		}

		pushCollectionCacheModel.serverNo = getServerNo();

		String serverNo = pushCollectionCacheModel.serverNo;

		if ((serverNo != null) && (serverNo.length() == 0)) {
			pushCollectionCacheModel.serverNo = null;
		}

		pushCollectionCacheModel.collectionCode = getCollectionCode();

		String collectionCode = pushCollectionCacheModel.collectionCode;

		if ((collectionCode != null) && (collectionCode.length() == 0)) {
			pushCollectionCacheModel.collectionCode = null;
		}

		pushCollectionCacheModel.collectionName = getCollectionName();

		String collectionName = pushCollectionCacheModel.collectionName;

		if ((collectionName != null) && (collectionName.length() == 0)) {
			pushCollectionCacheModel.collectionName = null;
		}

		pushCollectionCacheModel.collectionNameEN = getCollectionNameEN();

		String collectionNameEN = pushCollectionCacheModel.collectionNameEN;

		if ((collectionNameEN != null) && (collectionNameEN.length() == 0)) {
			pushCollectionCacheModel.collectionNameEN = null;
		}

		pushCollectionCacheModel.description = getDescription();

		String description = pushCollectionCacheModel.description;

		if ((description != null) && (description.length() == 0)) {
			pushCollectionCacheModel.description = null;
		}

		pushCollectionCacheModel.dataForm = getDataForm();

		String dataForm = pushCollectionCacheModel.dataForm;

		if ((dataForm != null) && (dataForm.length() == 0)) {
			pushCollectionCacheModel.dataForm = null;
		}

		pushCollectionCacheModel.method = getMethod();

		String method = pushCollectionCacheModel.method;

		if ((method != null) && (method.length() == 0)) {
			pushCollectionCacheModel.method = null;
		}

		return pushCollectionCacheModel;
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(31);

		sb.append("{uuid=");
		sb.append(getUuid());
		sb.append(", pushCollectionId=");
		sb.append(getPushCollectionId());
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
		sb.append(", collectionName=");
		sb.append(getCollectionName());
		sb.append(", collectionNameEN=");
		sb.append(getCollectionNameEN());
		sb.append(", description=");
		sb.append(getDescription());
		sb.append(", dataForm=");
		sb.append(getDataForm());
		sb.append(", method=");
		sb.append(getMethod());
		sb.append("}");

		return sb.toString();
	}

	@Override
	public String toXmlString() {
		StringBundler sb = new StringBundler(49);

		sb.append("<model><model-name>");
		sb.append("org.opencps.synchronization.model.PushCollection");
		sb.append("</model-name>");

		sb.append(
			"<column><column-name>uuid</column-name><column-value><![CDATA[");
		sb.append(getUuid());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>pushCollectionId</column-name><column-value><![CDATA[");
		sb.append(getPushCollectionId());
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
			"<column><column-name>collectionName</column-name><column-value><![CDATA[");
		sb.append(getCollectionName());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>collectionNameEN</column-name><column-value><![CDATA[");
		sb.append(getCollectionNameEN());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>description</column-name><column-value><![CDATA[");
		sb.append(getDescription());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>dataForm</column-name><column-value><![CDATA[");
		sb.append(getDataForm());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>method</column-name><column-value><![CDATA[");
		sb.append(getMethod());
		sb.append("]]></column-value></column>");

		sb.append("</model>");

		return sb.toString();
	}

	private static final ClassLoader _classLoader = PushCollection.class.getClassLoader();
	private static final Class<?>[] _escapedModelInterfaces = new Class[] {
			PushCollection.class, ModelWrapper.class
		};
	private String _uuid;
	private String _originalUuid;
	private long _pushCollectionId;
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
	private String _collectionName;
	private String _collectionNameEN;
	private String _description;
	private String _dataForm;
	private String _method;
	private String _originalMethod;
	private long _columnBitmask;
	private PushCollection _escapedModel;
}