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

package org.opencps.backend.systemlogmgt.model.impl;

import aQute.bnd.annotation.ProviderType;

import com.liferay.expando.kernel.model.ExpandoBridge;
import com.liferay.expando.kernel.util.ExpandoBridgeFactoryUtil;

import com.liferay.petra.string.StringBundler;

import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.model.impl.BaseModelImpl;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.ProxyUtil;

import org.opencps.backend.systemlogmgt.model.SystemLog;
import org.opencps.backend.systemlogmgt.model.SystemLogModel;

import java.io.Serializable;

import java.sql.Types;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * The base model implementation for the SystemLog service. Represents a row in the &quot;opencps_systemlog&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This implementation and its corresponding interface {@link SystemLogModel} exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link SystemLogImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see SystemLogImpl
 * @see SystemLog
 * @see SystemLogModel
 * @generated
 */
@ProviderType
public class SystemLogModelImpl extends BaseModelImpl<SystemLog>
	implements SystemLogModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a system log model instance should use the {@link SystemLog} interface instead.
	 */
	public static final String TABLE_NAME = "opencps_systemlog";
	public static final Object[][] TABLE_COLUMNS = {
			{ "uuid_", Types.VARCHAR },
			{ "logId", Types.BIGINT },
			{ "groupId", Types.BIGINT },
			{ "createDate", Types.TIMESTAMP },
			{ "moduleName", Types.VARCHAR },
			{ "preLine", Types.INTEGER },
			{ "preMethod", Types.VARCHAR },
			{ "line", Types.INTEGER },
			{ "method", Types.VARCHAR },
			{ "message", Types.VARCHAR },
			{ "type_", Types.VARCHAR },
			{ "threadId", Types.VARCHAR }
		};
	public static final Map<String, Integer> TABLE_COLUMNS_MAP = new HashMap<String, Integer>();

	static {
		TABLE_COLUMNS_MAP.put("uuid_", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("logId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("groupId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("createDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("moduleName", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("preLine", Types.INTEGER);
		TABLE_COLUMNS_MAP.put("preMethod", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("line", Types.INTEGER);
		TABLE_COLUMNS_MAP.put("method", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("message", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("type_", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("threadId", Types.VARCHAR);
	}

	public static final String TABLE_SQL_CREATE = "create table opencps_systemlog (uuid_ VARCHAR(75) null,logId LONG not null primary key,groupId LONG,createDate DATE null,moduleName VARCHAR(75) null,preLine INTEGER,preMethod VARCHAR(75) null,line INTEGER,method VARCHAR(75) null,message VARCHAR(75) null,type_ VARCHAR(75) null,threadId VARCHAR(75) null)";
	public static final String TABLE_SQL_DROP = "drop table opencps_systemlog";
	public static final String ORDER_BY_JPQL = " ORDER BY systemLog.logId ASC";
	public static final String ORDER_BY_SQL = " ORDER BY opencps_systemlog.logId ASC";
	public static final String DATA_SOURCE = "liferayDataSource";
	public static final String SESSION_FACTORY = "liferaySessionFactory";
	public static final String TX_MANAGER = "liferayTransactionManager";
	public static final boolean ENTITY_CACHE_ENABLED = GetterUtil.getBoolean(org.opencps.backend.systemlogmgt.service.util.ServiceProps.get(
				"value.object.entity.cache.enabled.org.opencps.backend.systemlogmgt.model.SystemLog"),
			true);
	public static final boolean FINDER_CACHE_ENABLED = GetterUtil.getBoolean(org.opencps.backend.systemlogmgt.service.util.ServiceProps.get(
				"value.object.finder.cache.enabled.org.opencps.backend.systemlogmgt.model.SystemLog"),
			true);
	public static final boolean COLUMN_BITMASK_ENABLED = GetterUtil.getBoolean(org.opencps.backend.systemlogmgt.service.util.ServiceProps.get(
				"value.object.column.bitmask.enabled.org.opencps.backend.systemlogmgt.model.SystemLog"),
			true);
	public static final long GROUPID_COLUMN_BITMASK = 1L;
	public static final long MESSAGE_COLUMN_BITMASK = 2L;
	public static final long METHOD_COLUMN_BITMASK = 4L;
	public static final long MODULENAME_COLUMN_BITMASK = 8L;
	public static final long PREMETHOD_COLUMN_BITMASK = 16L;
	public static final long THREADID_COLUMN_BITMASK = 32L;
	public static final long TYPE_COLUMN_BITMASK = 64L;
	public static final long UUID_COLUMN_BITMASK = 128L;
	public static final long LOGID_COLUMN_BITMASK = 256L;
	public static final long LOCK_EXPIRATION_TIME = GetterUtil.getLong(org.opencps.backend.systemlogmgt.service.util.ServiceProps.get(
				"lock.expiration.time.org.opencps.backend.systemlogmgt.model.SystemLog"));

	public SystemLogModelImpl() {
	}

	@Override
	public long getPrimaryKey() {
		return _logId;
	}

	@Override
	public void setPrimaryKey(long primaryKey) {
		setLogId(primaryKey);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _logId;
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Class<?> getModelClass() {
		return SystemLog.class;
	}

	@Override
	public String getModelClassName() {
		return SystemLog.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("uuid", getUuid());
		attributes.put("logId", getLogId());
		attributes.put("groupId", getGroupId());
		attributes.put("createDate", getCreateDate());
		attributes.put("moduleName", getModuleName());
		attributes.put("preLine", getPreLine());
		attributes.put("preMethod", getPreMethod());
		attributes.put("line", getLine());
		attributes.put("method", getMethod());
		attributes.put("message", getMessage());
		attributes.put("type", getType());
		attributes.put("threadId", getThreadId());

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

		Long logId = (Long)attributes.get("logId");

		if (logId != null) {
			setLogId(logId);
		}

		Long groupId = (Long)attributes.get("groupId");

		if (groupId != null) {
			setGroupId(groupId);
		}

		Date createDate = (Date)attributes.get("createDate");

		if (createDate != null) {
			setCreateDate(createDate);
		}

		String moduleName = (String)attributes.get("moduleName");

		if (moduleName != null) {
			setModuleName(moduleName);
		}

		Integer preLine = (Integer)attributes.get("preLine");

		if (preLine != null) {
			setPreLine(preLine);
		}

		String preMethod = (String)attributes.get("preMethod");

		if (preMethod != null) {
			setPreMethod(preMethod);
		}

		Integer line = (Integer)attributes.get("line");

		if (line != null) {
			setLine(line);
		}

		String method = (String)attributes.get("method");

		if (method != null) {
			setMethod(method);
		}

		String message = (String)attributes.get("message");

		if (message != null) {
			setMessage(message);
		}

		String type = (String)attributes.get("type");

		if (type != null) {
			setType(type);
		}

		String threadId = (String)attributes.get("threadId");

		if (threadId != null) {
			setThreadId(threadId);
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
	public long getLogId() {
		return _logId;
	}

	@Override
	public void setLogId(long logId) {
		_logId = logId;
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
	public String getModuleName() {
		if (_moduleName == null) {
			return "";
		}
		else {
			return _moduleName;
		}
	}

	@Override
	public void setModuleName(String moduleName) {
		_columnBitmask |= MODULENAME_COLUMN_BITMASK;

		if (_originalModuleName == null) {
			_originalModuleName = _moduleName;
		}

		_moduleName = moduleName;
	}

	public String getOriginalModuleName() {
		return GetterUtil.getString(_originalModuleName);
	}

	@Override
	public int getPreLine() {
		return _preLine;
	}

	@Override
	public void setPreLine(int preLine) {
		_preLine = preLine;
	}

	@Override
	public String getPreMethod() {
		if (_preMethod == null) {
			return "";
		}
		else {
			return _preMethod;
		}
	}

	@Override
	public void setPreMethod(String preMethod) {
		_columnBitmask |= PREMETHOD_COLUMN_BITMASK;

		if (_originalPreMethod == null) {
			_originalPreMethod = _preMethod;
		}

		_preMethod = preMethod;
	}

	public String getOriginalPreMethod() {
		return GetterUtil.getString(_originalPreMethod);
	}

	@Override
	public int getLine() {
		return _line;
	}

	@Override
	public void setLine(int line) {
		_line = line;
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
	public String getMessage() {
		if (_message == null) {
			return "";
		}
		else {
			return _message;
		}
	}

	@Override
	public void setMessage(String message) {
		_columnBitmask |= MESSAGE_COLUMN_BITMASK;

		if (_originalMessage == null) {
			_originalMessage = _message;
		}

		_message = message;
	}

	public String getOriginalMessage() {
		return GetterUtil.getString(_originalMessage);
	}

	@Override
	public String getType() {
		if (_type == null) {
			return "";
		}
		else {
			return _type;
		}
	}

	@Override
	public void setType(String type) {
		_columnBitmask |= TYPE_COLUMN_BITMASK;

		if (_originalType == null) {
			_originalType = _type;
		}

		_type = type;
	}

	public String getOriginalType() {
		return GetterUtil.getString(_originalType);
	}

	@Override
	public String getThreadId() {
		if (_threadId == null) {
			return "";
		}
		else {
			return _threadId;
		}
	}

	@Override
	public void setThreadId(String threadId) {
		_columnBitmask |= THREADID_COLUMN_BITMASK;

		if (_originalThreadId == null) {
			_originalThreadId = _threadId;
		}

		_threadId = threadId;
	}

	public String getOriginalThreadId() {
		return GetterUtil.getString(_originalThreadId);
	}

	public long getColumnBitmask() {
		return _columnBitmask;
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return ExpandoBridgeFactoryUtil.getExpandoBridge(0,
			SystemLog.class.getName(), getPrimaryKey());
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		ExpandoBridge expandoBridge = getExpandoBridge();

		expandoBridge.setAttributes(serviceContext);
	}

	@Override
	public SystemLog toEscapedModel() {
		if (_escapedModel == null) {
			_escapedModel = (SystemLog)ProxyUtil.newProxyInstance(_classLoader,
					_escapedModelInterfaces, new AutoEscapeBeanHandler(this));
		}

		return _escapedModel;
	}

	@Override
	public Object clone() {
		SystemLogImpl systemLogImpl = new SystemLogImpl();

		systemLogImpl.setUuid(getUuid());
		systemLogImpl.setLogId(getLogId());
		systemLogImpl.setGroupId(getGroupId());
		systemLogImpl.setCreateDate(getCreateDate());
		systemLogImpl.setModuleName(getModuleName());
		systemLogImpl.setPreLine(getPreLine());
		systemLogImpl.setPreMethod(getPreMethod());
		systemLogImpl.setLine(getLine());
		systemLogImpl.setMethod(getMethod());
		systemLogImpl.setMessage(getMessage());
		systemLogImpl.setType(getType());
		systemLogImpl.setThreadId(getThreadId());

		systemLogImpl.resetOriginalValues();

		return systemLogImpl;
	}

	@Override
	public int compareTo(SystemLog systemLog) {
		long primaryKey = systemLog.getPrimaryKey();

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

		if (!(obj instanceof SystemLog)) {
			return false;
		}

		SystemLog systemLog = (SystemLog)obj;

		long primaryKey = systemLog.getPrimaryKey();

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
		SystemLogModelImpl systemLogModelImpl = this;

		systemLogModelImpl._originalUuid = systemLogModelImpl._uuid;

		systemLogModelImpl._originalGroupId = systemLogModelImpl._groupId;

		systemLogModelImpl._setOriginalGroupId = false;

		systemLogModelImpl._originalModuleName = systemLogModelImpl._moduleName;

		systemLogModelImpl._originalPreMethod = systemLogModelImpl._preMethod;

		systemLogModelImpl._originalMethod = systemLogModelImpl._method;

		systemLogModelImpl._originalMessage = systemLogModelImpl._message;

		systemLogModelImpl._originalType = systemLogModelImpl._type;

		systemLogModelImpl._originalThreadId = systemLogModelImpl._threadId;

		systemLogModelImpl._columnBitmask = 0;
	}

	@Override
	public CacheModel<SystemLog> toCacheModel() {
		SystemLogCacheModel systemLogCacheModel = new SystemLogCacheModel();

		systemLogCacheModel.uuid = getUuid();

		String uuid = systemLogCacheModel.uuid;

		if ((uuid != null) && (uuid.length() == 0)) {
			systemLogCacheModel.uuid = null;
		}

		systemLogCacheModel.logId = getLogId();

		systemLogCacheModel.groupId = getGroupId();

		Date createDate = getCreateDate();

		if (createDate != null) {
			systemLogCacheModel.createDate = createDate.getTime();
		}
		else {
			systemLogCacheModel.createDate = Long.MIN_VALUE;
		}

		systemLogCacheModel.moduleName = getModuleName();

		String moduleName = systemLogCacheModel.moduleName;

		if ((moduleName != null) && (moduleName.length() == 0)) {
			systemLogCacheModel.moduleName = null;
		}

		systemLogCacheModel.preLine = getPreLine();

		systemLogCacheModel.preMethod = getPreMethod();

		String preMethod = systemLogCacheModel.preMethod;

		if ((preMethod != null) && (preMethod.length() == 0)) {
			systemLogCacheModel.preMethod = null;
		}

		systemLogCacheModel.line = getLine();

		systemLogCacheModel.method = getMethod();

		String method = systemLogCacheModel.method;

		if ((method != null) && (method.length() == 0)) {
			systemLogCacheModel.method = null;
		}

		systemLogCacheModel.message = getMessage();

		String message = systemLogCacheModel.message;

		if ((message != null) && (message.length() == 0)) {
			systemLogCacheModel.message = null;
		}

		systemLogCacheModel.type = getType();

		String type = systemLogCacheModel.type;

		if ((type != null) && (type.length() == 0)) {
			systemLogCacheModel.type = null;
		}

		systemLogCacheModel.threadId = getThreadId();

		String threadId = systemLogCacheModel.threadId;

		if ((threadId != null) && (threadId.length() == 0)) {
			systemLogCacheModel.threadId = null;
		}

		return systemLogCacheModel;
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(25);

		sb.append("{uuid=");
		sb.append(getUuid());
		sb.append(", logId=");
		sb.append(getLogId());
		sb.append(", groupId=");
		sb.append(getGroupId());
		sb.append(", createDate=");
		sb.append(getCreateDate());
		sb.append(", moduleName=");
		sb.append(getModuleName());
		sb.append(", preLine=");
		sb.append(getPreLine());
		sb.append(", preMethod=");
		sb.append(getPreMethod());
		sb.append(", line=");
		sb.append(getLine());
		sb.append(", method=");
		sb.append(getMethod());
		sb.append(", message=");
		sb.append(getMessage());
		sb.append(", type=");
		sb.append(getType());
		sb.append(", threadId=");
		sb.append(getThreadId());
		sb.append("}");

		return sb.toString();
	}

	@Override
	public String toXmlString() {
		StringBundler sb = new StringBundler(40);

		sb.append("<model><model-name>");
		sb.append("org.opencps.backend.systemlogmgt.model.SystemLog");
		sb.append("</model-name>");

		sb.append(
			"<column><column-name>uuid</column-name><column-value><![CDATA[");
		sb.append(getUuid());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>logId</column-name><column-value><![CDATA[");
		sb.append(getLogId());
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
			"<column><column-name>moduleName</column-name><column-value><![CDATA[");
		sb.append(getModuleName());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>preLine</column-name><column-value><![CDATA[");
		sb.append(getPreLine());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>preMethod</column-name><column-value><![CDATA[");
		sb.append(getPreMethod());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>line</column-name><column-value><![CDATA[");
		sb.append(getLine());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>method</column-name><column-value><![CDATA[");
		sb.append(getMethod());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>message</column-name><column-value><![CDATA[");
		sb.append(getMessage());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>type</column-name><column-value><![CDATA[");
		sb.append(getType());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>threadId</column-name><column-value><![CDATA[");
		sb.append(getThreadId());
		sb.append("]]></column-value></column>");

		sb.append("</model>");

		return sb.toString();
	}

	private static final ClassLoader _classLoader = SystemLog.class.getClassLoader();
	private static final Class<?>[] _escapedModelInterfaces = new Class[] {
			SystemLog.class, ModelWrapper.class
		};
	private String _uuid;
	private String _originalUuid;
	private long _logId;
	private long _groupId;
	private long _originalGroupId;
	private boolean _setOriginalGroupId;
	private Date _createDate;
	private String _moduleName;
	private String _originalModuleName;
	private int _preLine;
	private String _preMethod;
	private String _originalPreMethod;
	private int _line;
	private String _method;
	private String _originalMethod;
	private String _message;
	private String _originalMessage;
	private String _type;
	private String _originalType;
	private String _threadId;
	private String _originalThreadId;
	private long _columnBitmask;
	private SystemLog _escapedModel;
}