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

import org.opencps.adminconfig.model.ApiManager;
import org.opencps.adminconfig.model.ApiManagerModel;

import java.io.Serializable;

import java.sql.Types;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * The base model implementation for the ApiManager service. Represents a row in the &quot;opencps_api_manager&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This implementation and its corresponding interface {@link ApiManagerModel} exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link ApiManagerImpl}.
 * </p>
 *
 * @author binhth
 * @see ApiManagerImpl
 * @see ApiManager
 * @see ApiManagerModel
 * @generated
 */
@ProviderType
public class ApiManagerModelImpl extends BaseModelImpl<ApiManager>
	implements ApiManagerModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a api manager model instance should use the {@link ApiManager} interface instead.
	 */
	public static final String TABLE_NAME = "opencps_api_manager";
	public static final Object[][] TABLE_COLUMNS = {
			{ "apiManagerId", Types.BIGINT },
			{ "groupId", Types.BIGINT },
			{ "apiCode", Types.VARCHAR },
			{ "createDate", Types.TIMESTAMP },
			{ "modifiedDate", Types.TIMESTAMP },
			{ "userId", Types.BIGINT },
			{ "apiName", Types.VARCHAR },
			{ "apiDescription", Types.VARCHAR },
			{ "apiStatus", Types.INTEGER },
			{ "className", Types.VARCHAR }
		};
	public static final Map<String, Integer> TABLE_COLUMNS_MAP = new HashMap<String, Integer>();

	static {
		TABLE_COLUMNS_MAP.put("apiManagerId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("groupId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("apiCode", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("createDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("modifiedDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("userId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("apiName", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("apiDescription", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("apiStatus", Types.INTEGER);
		TABLE_COLUMNS_MAP.put("className", Types.VARCHAR);
	}

	public static final String TABLE_SQL_CREATE = "create table opencps_api_manager (apiManagerId LONG not null primary key,groupId LONG,apiCode VARCHAR(75) null,createDate DATE null,modifiedDate DATE null,userId LONG,apiName VARCHAR(75) null,apiDescription VARCHAR(75) null,apiStatus INTEGER,className VARCHAR(75) null)";
	public static final String TABLE_SQL_DROP = "drop table opencps_api_manager";
	public static final String ORDER_BY_JPQL = " ORDER BY apiManager.apiManagerId ASC";
	public static final String ORDER_BY_SQL = " ORDER BY opencps_api_manager.apiManagerId ASC";
	public static final String DATA_SOURCE = "liferayDataSource";
	public static final String SESSION_FACTORY = "liferaySessionFactory";
	public static final String TX_MANAGER = "liferayTransactionManager";
	public static final boolean ENTITY_CACHE_ENABLED = GetterUtil.getBoolean(backend.admin.config.service.util.ServiceProps.get(
				"value.object.entity.cache.enabled.org.opencps.adminconfig.model.ApiManager"),
			true);
	public static final boolean FINDER_CACHE_ENABLED = GetterUtil.getBoolean(backend.admin.config.service.util.ServiceProps.get(
				"value.object.finder.cache.enabled.org.opencps.adminconfig.model.ApiManager"),
			true);
	public static final boolean COLUMN_BITMASK_ENABLED = GetterUtil.getBoolean(backend.admin.config.service.util.ServiceProps.get(
				"value.object.column.bitmask.enabled.org.opencps.adminconfig.model.ApiManager"),
			true);
	public static final long APICODE_COLUMN_BITMASK = 1L;
	public static final long APIMANAGERID_COLUMN_BITMASK = 2L;
	public static final long GROUPID_COLUMN_BITMASK = 4L;
	public static final long LOCK_EXPIRATION_TIME = GetterUtil.getLong(backend.admin.config.service.util.ServiceProps.get(
				"lock.expiration.time.org.opencps.adminconfig.model.ApiManager"));

	public ApiManagerModelImpl() {
	}

	@Override
	public long getPrimaryKey() {
		return _apiManagerId;
	}

	@Override
	public void setPrimaryKey(long primaryKey) {
		setApiManagerId(primaryKey);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _apiManagerId;
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Class<?> getModelClass() {
		return ApiManager.class;
	}

	@Override
	public String getModelClassName() {
		return ApiManager.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("apiManagerId", getApiManagerId());
		attributes.put("groupId", getGroupId());
		attributes.put("apiCode", getApiCode());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("userId", getUserId());
		attributes.put("apiName", getApiName());
		attributes.put("apiDescription", getApiDescription());
		attributes.put("apiStatus", getApiStatus());
		attributes.put("className", getClassName());

		attributes.put("entityCacheEnabled", isEntityCacheEnabled());
		attributes.put("finderCacheEnabled", isFinderCacheEnabled());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long apiManagerId = (Long)attributes.get("apiManagerId");

		if (apiManagerId != null) {
			setApiManagerId(apiManagerId);
		}

		Long groupId = (Long)attributes.get("groupId");

		if (groupId != null) {
			setGroupId(groupId);
		}

		String apiCode = (String)attributes.get("apiCode");

		if (apiCode != null) {
			setApiCode(apiCode);
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

		String apiName = (String)attributes.get("apiName");

		if (apiName != null) {
			setApiName(apiName);
		}

		String apiDescription = (String)attributes.get("apiDescription");

		if (apiDescription != null) {
			setApiDescription(apiDescription);
		}

		Integer apiStatus = (Integer)attributes.get("apiStatus");

		if (apiStatus != null) {
			setApiStatus(apiStatus);
		}

		String className = (String)attributes.get("className");

		if (className != null) {
			setClassName(className);
		}
	}

	@Override
	public long getApiManagerId() {
		return _apiManagerId;
	}

	@Override
	public void setApiManagerId(long apiManagerId) {
		_columnBitmask |= APIMANAGERID_COLUMN_BITMASK;

		if (!_setOriginalApiManagerId) {
			_setOriginalApiManagerId = true;

			_originalApiManagerId = _apiManagerId;
		}

		_apiManagerId = apiManagerId;
	}

	public long getOriginalApiManagerId() {
		return _originalApiManagerId;
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
		_columnBitmask |= APICODE_COLUMN_BITMASK;

		if (_originalApiCode == null) {
			_originalApiCode = _apiCode;
		}

		_apiCode = apiCode;
	}

	public String getOriginalApiCode() {
		return GetterUtil.getString(_originalApiCode);
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
	public String getApiName() {
		if (_apiName == null) {
			return "";
		}
		else {
			return _apiName;
		}
	}

	@Override
	public void setApiName(String apiName) {
		_apiName = apiName;
	}

	@Override
	public String getApiDescription() {
		if (_apiDescription == null) {
			return "";
		}
		else {
			return _apiDescription;
		}
	}

	@Override
	public void setApiDescription(String apiDescription) {
		_apiDescription = apiDescription;
	}

	@Override
	public int getApiStatus() {
		return _apiStatus;
	}

	@Override
	public void setApiStatus(int apiStatus) {
		_apiStatus = apiStatus;
	}

	@Override
	public String getClassName() {
		if (_className == null) {
			return "";
		}
		else {
			return _className;
		}
	}

	@Override
	public void setClassName(String className) {
		_className = className;
	}

	public long getColumnBitmask() {
		return _columnBitmask;
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return ExpandoBridgeFactoryUtil.getExpandoBridge(0,
			ApiManager.class.getName(), getPrimaryKey());
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		ExpandoBridge expandoBridge = getExpandoBridge();

		expandoBridge.setAttributes(serviceContext);
	}

	@Override
	public ApiManager toEscapedModel() {
		if (_escapedModel == null) {
			_escapedModel = (ApiManager)ProxyUtil.newProxyInstance(_classLoader,
					_escapedModelInterfaces, new AutoEscapeBeanHandler(this));
		}

		return _escapedModel;
	}

	@Override
	public Object clone() {
		ApiManagerImpl apiManagerImpl = new ApiManagerImpl();

		apiManagerImpl.setApiManagerId(getApiManagerId());
		apiManagerImpl.setGroupId(getGroupId());
		apiManagerImpl.setApiCode(getApiCode());
		apiManagerImpl.setCreateDate(getCreateDate());
		apiManagerImpl.setModifiedDate(getModifiedDate());
		apiManagerImpl.setUserId(getUserId());
		apiManagerImpl.setApiName(getApiName());
		apiManagerImpl.setApiDescription(getApiDescription());
		apiManagerImpl.setApiStatus(getApiStatus());
		apiManagerImpl.setClassName(getClassName());

		apiManagerImpl.resetOriginalValues();

		return apiManagerImpl;
	}

	@Override
	public int compareTo(ApiManager apiManager) {
		long primaryKey = apiManager.getPrimaryKey();

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

		if (!(obj instanceof ApiManager)) {
			return false;
		}

		ApiManager apiManager = (ApiManager)obj;

		long primaryKey = apiManager.getPrimaryKey();

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
		ApiManagerModelImpl apiManagerModelImpl = this;

		apiManagerModelImpl._originalApiManagerId = apiManagerModelImpl._apiManagerId;

		apiManagerModelImpl._setOriginalApiManagerId = false;

		apiManagerModelImpl._originalGroupId = apiManagerModelImpl._groupId;

		apiManagerModelImpl._setOriginalGroupId = false;

		apiManagerModelImpl._originalApiCode = apiManagerModelImpl._apiCode;

		apiManagerModelImpl._setModifiedDate = false;

		apiManagerModelImpl._columnBitmask = 0;
	}

	@Override
	public CacheModel<ApiManager> toCacheModel() {
		ApiManagerCacheModel apiManagerCacheModel = new ApiManagerCacheModel();

		apiManagerCacheModel.apiManagerId = getApiManagerId();

		apiManagerCacheModel.groupId = getGroupId();

		apiManagerCacheModel.apiCode = getApiCode();

		String apiCode = apiManagerCacheModel.apiCode;

		if ((apiCode != null) && (apiCode.length() == 0)) {
			apiManagerCacheModel.apiCode = null;
		}

		Date createDate = getCreateDate();

		if (createDate != null) {
			apiManagerCacheModel.createDate = createDate.getTime();
		}
		else {
			apiManagerCacheModel.createDate = Long.MIN_VALUE;
		}

		Date modifiedDate = getModifiedDate();

		if (modifiedDate != null) {
			apiManagerCacheModel.modifiedDate = modifiedDate.getTime();
		}
		else {
			apiManagerCacheModel.modifiedDate = Long.MIN_VALUE;
		}

		apiManagerCacheModel.userId = getUserId();

		apiManagerCacheModel.apiName = getApiName();

		String apiName = apiManagerCacheModel.apiName;

		if ((apiName != null) && (apiName.length() == 0)) {
			apiManagerCacheModel.apiName = null;
		}

		apiManagerCacheModel.apiDescription = getApiDescription();

		String apiDescription = apiManagerCacheModel.apiDescription;

		if ((apiDescription != null) && (apiDescription.length() == 0)) {
			apiManagerCacheModel.apiDescription = null;
		}

		apiManagerCacheModel.apiStatus = getApiStatus();

		apiManagerCacheModel.className = getClassName();

		String className = apiManagerCacheModel.className;

		if ((className != null) && (className.length() == 0)) {
			apiManagerCacheModel.className = null;
		}

		return apiManagerCacheModel;
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(21);

		sb.append("{apiManagerId=");
		sb.append(getApiManagerId());
		sb.append(", groupId=");
		sb.append(getGroupId());
		sb.append(", apiCode=");
		sb.append(getApiCode());
		sb.append(", createDate=");
		sb.append(getCreateDate());
		sb.append(", modifiedDate=");
		sb.append(getModifiedDate());
		sb.append(", userId=");
		sb.append(getUserId());
		sb.append(", apiName=");
		sb.append(getApiName());
		sb.append(", apiDescription=");
		sb.append(getApiDescription());
		sb.append(", apiStatus=");
		sb.append(getApiStatus());
		sb.append(", className=");
		sb.append(getClassName());
		sb.append("}");

		return sb.toString();
	}

	@Override
	public String toXmlString() {
		StringBundler sb = new StringBundler(34);

		sb.append("<model><model-name>");
		sb.append("org.opencps.adminconfig.model.ApiManager");
		sb.append("</model-name>");

		sb.append(
			"<column><column-name>apiManagerId</column-name><column-value><![CDATA[");
		sb.append(getApiManagerId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>groupId</column-name><column-value><![CDATA[");
		sb.append(getGroupId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>apiCode</column-name><column-value><![CDATA[");
		sb.append(getApiCode());
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
			"<column><column-name>apiName</column-name><column-value><![CDATA[");
		sb.append(getApiName());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>apiDescription</column-name><column-value><![CDATA[");
		sb.append(getApiDescription());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>apiStatus</column-name><column-value><![CDATA[");
		sb.append(getApiStatus());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>className</column-name><column-value><![CDATA[");
		sb.append(getClassName());
		sb.append("]]></column-value></column>");

		sb.append("</model>");

		return sb.toString();
	}

	private static final ClassLoader _classLoader = ApiManager.class.getClassLoader();
	private static final Class<?>[] _escapedModelInterfaces = new Class[] {
			ApiManager.class, ModelWrapper.class
		};
	private long _apiManagerId;
	private long _originalApiManagerId;
	private boolean _setOriginalApiManagerId;
	private long _groupId;
	private long _originalGroupId;
	private boolean _setOriginalGroupId;
	private String _apiCode;
	private String _originalApiCode;
	private Date _createDate;
	private Date _modifiedDate;
	private boolean _setModifiedDate;
	private long _userId;
	private String _apiName;
	private String _apiDescription;
	private int _apiStatus;
	private String _className;
	private long _columnBitmask;
	private ApiManager _escapedModel;
}