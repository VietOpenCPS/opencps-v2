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

package org.opencps.adminconfig.model.impl;

import aQute.bnd.annotation.ProviderType;

import com.liferay.expando.kernel.model.ExpandoBridge;
import com.liferay.expando.kernel.util.ExpandoBridgeFactoryUtil;

import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.model.impl.BaseModelImpl;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.StringBundler;

import org.opencps.adminconfig.model.ApiRole;
import org.opencps.adminconfig.model.ApiRoleModel;

import java.io.Serializable;

import java.sql.Types;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * The base model implementation for the ApiRole service. Represents a row in the &quot;opencps_api_role&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This implementation and its corresponding interface {@link ApiRoleModel} exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link ApiRoleImpl}.
 * </p>
 *
 * @author binhth
 * @see ApiRoleImpl
 * @see ApiRole
 * @see ApiRoleModel
 * @generated
 */
@ProviderType
public class ApiRoleModelImpl extends BaseModelImpl<ApiRole>
	implements ApiRoleModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a api role model instance should use the {@link ApiRole} interface instead.
	 */
	public static final String TABLE_NAME = "opencps_api_role";
	public static final Object[][] TABLE_COLUMNS = {
			{ "apiRoleId", Types.BIGINT },
			{ "groupId", Types.BIGINT },
			{ "createDate", Types.TIMESTAMP },
			{ "modifiedDate", Types.TIMESTAMP },
			{ "userId", Types.BIGINT },
			{ "apiCode", Types.VARCHAR },
			{ "roleId", Types.INTEGER },
			{ "roleCode", Types.VARCHAR },
			{ "apiRoleStatus", Types.INTEGER }
		};
	public static final Map<String, Integer> TABLE_COLUMNS_MAP = new HashMap<String, Integer>();

	static {
		TABLE_COLUMNS_MAP.put("apiRoleId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("groupId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("createDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("modifiedDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("userId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("apiCode", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("roleId", Types.INTEGER);
		TABLE_COLUMNS_MAP.put("roleCode", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("apiRoleStatus", Types.INTEGER);
	}

	public static final String TABLE_SQL_CREATE = "create table opencps_api_role (apiRoleId LONG not null primary key,groupId LONG,createDate DATE null,modifiedDate DATE null,userId LONG,apiCode VARCHAR(75) null,roleId INTEGER,roleCode VARCHAR(75) null,apiRoleStatus INTEGER)";
	public static final String TABLE_SQL_DROP = "drop table opencps_api_role";
	public static final String ORDER_BY_JPQL = " ORDER BY apiRole.apiRoleId ASC";
	public static final String ORDER_BY_SQL = " ORDER BY opencps_api_role.apiRoleId ASC";
	public static final String DATA_SOURCE = "liferayDataSource";
	public static final String SESSION_FACTORY = "liferaySessionFactory";
	public static final String TX_MANAGER = "liferayTransactionManager";
	public static final boolean ENTITY_CACHE_ENABLED = GetterUtil.getBoolean(backend.admin.config.service.util.ServiceProps.get(
				"value.object.entity.cache.enabled.org.opencps.adminconfig.model.ApiRole"),
			true);
	public static final boolean FINDER_CACHE_ENABLED = GetterUtil.getBoolean(backend.admin.config.service.util.ServiceProps.get(
				"value.object.finder.cache.enabled.org.opencps.adminconfig.model.ApiRole"),
			true);
	public static final boolean COLUMN_BITMASK_ENABLED = GetterUtil.getBoolean(backend.admin.config.service.util.ServiceProps.get(
				"value.object.column.bitmask.enabled.org.opencps.adminconfig.model.ApiRole"),
			true);
	public static final long APIROLEID_COLUMN_BITMASK = 1L;
	public static final long GROUPID_COLUMN_BITMASK = 2L;
	public static final long ROLECODE_COLUMN_BITMASK = 4L;
	public static final long LOCK_EXPIRATION_TIME = GetterUtil.getLong(backend.admin.config.service.util.ServiceProps.get(
				"lock.expiration.time.org.opencps.adminconfig.model.ApiRole"));

	public ApiRoleModelImpl() {
	}

	@Override
	public long getPrimaryKey() {
		return _apiRoleId;
	}

	@Override
	public void setPrimaryKey(long primaryKey) {
		setApiRoleId(primaryKey);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _apiRoleId;
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Class<?> getModelClass() {
		return ApiRole.class;
	}

	@Override
	public String getModelClassName() {
		return ApiRole.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("apiRoleId", getApiRoleId());
		attributes.put("groupId", getGroupId());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("userId", getUserId());
		attributes.put("apiCode", getApiCode());
		attributes.put("roleId", getRoleId());
		attributes.put("roleCode", getRoleCode());
		attributes.put("apiRoleStatus", getApiRoleStatus());

		attributes.put("entityCacheEnabled", isEntityCacheEnabled());
		attributes.put("finderCacheEnabled", isFinderCacheEnabled());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long apiRoleId = (Long)attributes.get("apiRoleId");

		if (apiRoleId != null) {
			setApiRoleId(apiRoleId);
		}

		Long groupId = (Long)attributes.get("groupId");

		if (groupId != null) {
			setGroupId(groupId);
		}

		Date createDate = (Date)attributes.get("createDate");

		if (createDate != null) {
			setCreateDate(createDate);
		}

		Date modifiedDate = (Date)attributes.get("modifiedDate");

		if (modifiedDate != null) {
			setModifiedDate(modifiedDate);
		}

		Long userId = (Long)attributes.get("userId");

		if (userId != null) {
			setUserId(userId);
		}

		String apiCode = (String)attributes.get("apiCode");

		if (apiCode != null) {
			setApiCode(apiCode);
		}

		Integer roleId = (Integer)attributes.get("roleId");

		if (roleId != null) {
			setRoleId(roleId);
		}

		String roleCode = (String)attributes.get("roleCode");

		if (roleCode != null) {
			setRoleCode(roleCode);
		}

		Integer apiRoleStatus = (Integer)attributes.get("apiRoleStatus");

		if (apiRoleStatus != null) {
			setApiRoleStatus(apiRoleStatus);
		}
	}

	@Override
	public long getApiRoleId() {
		return _apiRoleId;
	}

	@Override
	public void setApiRoleId(long apiRoleId) {
		_columnBitmask |= APIROLEID_COLUMN_BITMASK;

		if (!_setOriginalApiRoleId) {
			_setOriginalApiRoleId = true;

			_originalApiRoleId = _apiRoleId;
		}

		_apiRoleId = apiRoleId;
	}

	public long getOriginalApiRoleId() {
		return _originalApiRoleId;
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

		_modifiedDate = modifiedDate;
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
	public String getApiCode() {
		if (_apiCode == null) {
			return "";
		}
		else {
			return _apiCode;
		}
	}

	@Override
	public void setApiCode(String apiCode) {
		_apiCode = apiCode;
	}

	@Override
	public int getRoleId() {
		return _roleId;
	}

	@Override
	public void setRoleId(int roleId) {
		_roleId = roleId;
	}

	@Override
	public String getRoleCode() {
		if (_roleCode == null) {
			return "";
		}
		else {
			return _roleCode;
		}
	}

	@Override
	public void setRoleCode(String roleCode) {
		_columnBitmask |= ROLECODE_COLUMN_BITMASK;

		if (_originalRoleCode == null) {
			_originalRoleCode = _roleCode;
		}

		_roleCode = roleCode;
	}

	public String getOriginalRoleCode() {
		return GetterUtil.getString(_originalRoleCode);
	}

	@Override
	public int getApiRoleStatus() {
		return _apiRoleStatus;
	}

	@Override
	public void setApiRoleStatus(int apiRoleStatus) {
		_apiRoleStatus = apiRoleStatus;
	}

	public long getColumnBitmask() {
		return _columnBitmask;
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return ExpandoBridgeFactoryUtil.getExpandoBridge(0,
			ApiRole.class.getName(), getPrimaryKey());
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		ExpandoBridge expandoBridge = getExpandoBridge();

		expandoBridge.setAttributes(serviceContext);
	}

	@Override
	public ApiRole toEscapedModel() {
		if (_escapedModel == null) {
			_escapedModel = (ApiRole)ProxyUtil.newProxyInstance(_classLoader,
					_escapedModelInterfaces, new AutoEscapeBeanHandler(this));
		}

		return _escapedModel;
	}

	@Override
	public Object clone() {
		ApiRoleImpl apiRoleImpl = new ApiRoleImpl();

		apiRoleImpl.setApiRoleId(getApiRoleId());
		apiRoleImpl.setGroupId(getGroupId());
		apiRoleImpl.setCreateDate(getCreateDate());
		apiRoleImpl.setModifiedDate(getModifiedDate());
		apiRoleImpl.setUserId(getUserId());
		apiRoleImpl.setApiCode(getApiCode());
		apiRoleImpl.setRoleId(getRoleId());
		apiRoleImpl.setRoleCode(getRoleCode());
		apiRoleImpl.setApiRoleStatus(getApiRoleStatus());

		apiRoleImpl.resetOriginalValues();

		return apiRoleImpl;
	}

	@Override
	public int compareTo(ApiRole apiRole) {
		long primaryKey = apiRole.getPrimaryKey();

		if (getPrimaryKey() < primaryKey) {
			return -1;
		}
		else if (getPrimaryKey() > primaryKey) {
			return 1;
		}
		else {
			return 0;
		}
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof ApiRole)) {
			return false;
		}

		ApiRole apiRole = (ApiRole)obj;

		long primaryKey = apiRole.getPrimaryKey();

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
		ApiRoleModelImpl apiRoleModelImpl = this;

		apiRoleModelImpl._originalApiRoleId = apiRoleModelImpl._apiRoleId;

		apiRoleModelImpl._setOriginalApiRoleId = false;

		apiRoleModelImpl._originalGroupId = apiRoleModelImpl._groupId;

		apiRoleModelImpl._setOriginalGroupId = false;

		apiRoleModelImpl._setModifiedDate = false;

		apiRoleModelImpl._originalRoleCode = apiRoleModelImpl._roleCode;

		apiRoleModelImpl._columnBitmask = 0;
	}

	@Override
	public CacheModel<ApiRole> toCacheModel() {
		ApiRoleCacheModel apiRoleCacheModel = new ApiRoleCacheModel();

		apiRoleCacheModel.apiRoleId = getApiRoleId();

		apiRoleCacheModel.groupId = getGroupId();

		Date createDate = getCreateDate();

		if (createDate != null) {
			apiRoleCacheModel.createDate = createDate.getTime();
		}
		else {
			apiRoleCacheModel.createDate = Long.MIN_VALUE;
		}

		Date modifiedDate = getModifiedDate();

		if (modifiedDate != null) {
			apiRoleCacheModel.modifiedDate = modifiedDate.getTime();
		}
		else {
			apiRoleCacheModel.modifiedDate = Long.MIN_VALUE;
		}

		apiRoleCacheModel.userId = getUserId();

		apiRoleCacheModel.apiCode = getApiCode();

		String apiCode = apiRoleCacheModel.apiCode;

		if ((apiCode != null) && (apiCode.length() == 0)) {
			apiRoleCacheModel.apiCode = null;
		}

		apiRoleCacheModel.roleId = getRoleId();

		apiRoleCacheModel.roleCode = getRoleCode();

		String roleCode = apiRoleCacheModel.roleCode;

		if ((roleCode != null) && (roleCode.length() == 0)) {
			apiRoleCacheModel.roleCode = null;
		}

		apiRoleCacheModel.apiRoleStatus = getApiRoleStatus();

		return apiRoleCacheModel;
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(19);

		sb.append("{apiRoleId=");
		sb.append(getApiRoleId());
		sb.append(", groupId=");
		sb.append(getGroupId());
		sb.append(", createDate=");
		sb.append(getCreateDate());
		sb.append(", modifiedDate=");
		sb.append(getModifiedDate());
		sb.append(", userId=");
		sb.append(getUserId());
		sb.append(", apiCode=");
		sb.append(getApiCode());
		sb.append(", roleId=");
		sb.append(getRoleId());
		sb.append(", roleCode=");
		sb.append(getRoleCode());
		sb.append(", apiRoleStatus=");
		sb.append(getApiRoleStatus());
		sb.append("}");

		return sb.toString();
	}

	@Override
	public String toXmlString() {
		StringBundler sb = new StringBundler(31);

		sb.append("<model><model-name>");
		sb.append("org.opencps.adminconfig.model.ApiRole");
		sb.append("</model-name>");

		sb.append(
			"<column><column-name>apiRoleId</column-name><column-value><![CDATA[");
		sb.append(getApiRoleId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>groupId</column-name><column-value><![CDATA[");
		sb.append(getGroupId());
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
			"<column><column-name>userId</column-name><column-value><![CDATA[");
		sb.append(getUserId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>apiCode</column-name><column-value><![CDATA[");
		sb.append(getApiCode());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>roleId</column-name><column-value><![CDATA[");
		sb.append(getRoleId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>roleCode</column-name><column-value><![CDATA[");
		sb.append(getRoleCode());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>apiRoleStatus</column-name><column-value><![CDATA[");
		sb.append(getApiRoleStatus());
		sb.append("]]></column-value></column>");

		sb.append("</model>");

		return sb.toString();
	}

	private static final ClassLoader _classLoader = ApiRole.class.getClassLoader();
	private static final Class<?>[] _escapedModelInterfaces = new Class[] {
			ApiRole.class, ModelWrapper.class
		};
	private long _apiRoleId;
	private long _originalApiRoleId;
	private boolean _setOriginalApiRoleId;
	private long _groupId;
	private long _originalGroupId;
	private boolean _setOriginalGroupId;
	private Date _createDate;
	private Date _modifiedDate;
	private boolean _setModifiedDate;
	private long _userId;
	private String _apiCode;
	private int _roleId;
	private String _roleCode;
	private String _originalRoleCode;
	private int _apiRoleStatus;
	private long _columnBitmask;
	private ApiRole _escapedModel;
}