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

package org.opencps.dossiermgt.model.impl;

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

import org.opencps.dossiermgt.model.DossierActionUser;
import org.opencps.dossiermgt.model.DossierActionUserModel;

import java.io.Serializable;

import java.sql.Types;

import java.util.HashMap;
import java.util.Map;

/**
 * The base model implementation for the DossierActionUser service. Represents a row in the &quot;opencps_dossieractionuser&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This implementation and its corresponding interface {@link DossierActionUserModel} exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link DossierActionUserImpl}.
 * </p>
 *
 * @author huymq
 * @see DossierActionUserImpl
 * @see DossierActionUser
 * @see DossierActionUserModel
 * @generated
 */
@ProviderType
public class DossierActionUserModelImpl extends BaseModelImpl<DossierActionUser>
	implements DossierActionUserModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a dossier action user model instance should use the {@link DossierActionUser} interface instead.
	 */
	public static final String TABLE_NAME = "opencps_dossieractionuser";
	public static final Object[][] TABLE_COLUMNS = {
			{ "uuid_", Types.VARCHAR },
			{ "dossierActionUserId", Types.BIGINT },
			{ "dossierActionId", Types.BIGINT },
			{ "userId", Types.BIGINT },
			{ "dossierId", Types.BIGINT },
			{ "roleId", Types.BIGINT },
			{ "stepCode", Types.VARCHAR },
			{ "moderator", Types.INTEGER },
			{ "assigned", Types.INTEGER },
			{ "visited", Types.BOOLEAN }
		};
	public static final Map<String, Integer> TABLE_COLUMNS_MAP = new HashMap<String, Integer>();

	static {
		TABLE_COLUMNS_MAP.put("uuid_", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("dossierActionUserId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("dossierActionId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("userId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("dossierId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("roleId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("stepCode", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("moderator", Types.INTEGER);
		TABLE_COLUMNS_MAP.put("assigned", Types.INTEGER);
		TABLE_COLUMNS_MAP.put("visited", Types.BOOLEAN);
	}

	public static final String TABLE_SQL_CREATE = "create table opencps_dossieractionuser (uuid_ VARCHAR(75) null,dossierActionUserId LONG not null primary key,dossierActionId LONG,userId LONG,dossierId LONG,roleId LONG,stepCode VARCHAR(255) null,moderator INTEGER,assigned INTEGER,visited BOOLEAN)";
	public static final String TABLE_SQL_DROP = "drop table opencps_dossieractionuser";
	public static final String ORDER_BY_JPQL = " ORDER BY dossierActionUser.dossierActionId DESC";
	public static final String ORDER_BY_SQL = " ORDER BY opencps_dossieractionuser.dossierActionId DESC";
	public static final String DATA_SOURCE = "liferayDataSource";
	public static final String SESSION_FACTORY = "liferaySessionFactory";
	public static final String TX_MANAGER = "liferayTransactionManager";
	public static final boolean ENTITY_CACHE_ENABLED = GetterUtil.getBoolean(org.opencps.backend.dossiermgt.service.util.ServiceProps.get(
				"value.object.entity.cache.enabled.org.opencps.dossiermgt.model.DossierActionUser"),
			true);
	public static final boolean FINDER_CACHE_ENABLED = GetterUtil.getBoolean(org.opencps.backend.dossiermgt.service.util.ServiceProps.get(
				"value.object.finder.cache.enabled.org.opencps.dossiermgt.model.DossierActionUser"),
			true);
	public static final boolean COLUMN_BITMASK_ENABLED = GetterUtil.getBoolean(org.opencps.backend.dossiermgt.service.util.ServiceProps.get(
				"value.object.column.bitmask.enabled.org.opencps.dossiermgt.model.DossierActionUser"),
			true);
	public static final long ASSIGNED_COLUMN_BITMASK = 1L;
	public static final long DOSSIERACTIONID_COLUMN_BITMASK = 2L;
	public static final long DOSSIERID_COLUMN_BITMASK = 4L;
	public static final long ROLEID_COLUMN_BITMASK = 8L;
	public static final long STEPCODE_COLUMN_BITMASK = 16L;
	public static final long USERID_COLUMN_BITMASK = 32L;
	public static final long UUID_COLUMN_BITMASK = 64L;
	public static final long LOCK_EXPIRATION_TIME = GetterUtil.getLong(org.opencps.backend.dossiermgt.service.util.ServiceProps.get(
				"lock.expiration.time.org.opencps.dossiermgt.model.DossierActionUser"));

	public DossierActionUserModelImpl() {
	}

	@Override
	public long getPrimaryKey() {
		return _dossierActionUserId;
	}

	@Override
	public void setPrimaryKey(long primaryKey) {
		setDossierActionUserId(primaryKey);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _dossierActionUserId;
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Class<?> getModelClass() {
		return DossierActionUser.class;
	}

	@Override
	public String getModelClassName() {
		return DossierActionUser.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("uuid", getUuid());
		attributes.put("dossierActionUserId", getDossierActionUserId());
		attributes.put("dossierActionId", getDossierActionId());
		attributes.put("userId", getUserId());
		attributes.put("dossierId", getDossierId());
		attributes.put("roleId", getRoleId());
		attributes.put("stepCode", getStepCode());
		attributes.put("moderator", getModerator());
		attributes.put("assigned", getAssigned());
		attributes.put("visited", isVisited());

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

		Long dossierActionUserId = (Long)attributes.get("dossierActionUserId");

		if (dossierActionUserId != null) {
			setDossierActionUserId(dossierActionUserId);
		}

		Long dossierActionId = (Long)attributes.get("dossierActionId");

		if (dossierActionId != null) {
			setDossierActionId(dossierActionId);
		}

		Long userId = (Long)attributes.get("userId");

		if (userId != null) {
			setUserId(userId);
		}

		Long dossierId = (Long)attributes.get("dossierId");

		if (dossierId != null) {
			setDossierId(dossierId);
		}

		Long roleId = (Long)attributes.get("roleId");

		if (roleId != null) {
			setRoleId(roleId);
		}

		String stepCode = (String)attributes.get("stepCode");

		if (stepCode != null) {
			setStepCode(stepCode);
		}

		Integer moderator = (Integer)attributes.get("moderator");

		if (moderator != null) {
			setModerator(moderator);
		}

		Integer assigned = (Integer)attributes.get("assigned");

		if (assigned != null) {
			setAssigned(assigned);
		}

		Boolean visited = (Boolean)attributes.get("visited");

		if (visited != null) {
			setVisited(visited);
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
	public long getDossierActionUserId() {
		return _dossierActionUserId;
	}

	@Override
	public void setDossierActionUserId(long dossierActionUserId) {
		_dossierActionUserId = dossierActionUserId;
	}

	@Override
	public String getDossierActionUserUuid() {
		try {
			User user = UserLocalServiceUtil.getUserById(getDossierActionUserId());

			return user.getUuid();
		}
		catch (PortalException pe) {
			return "";
		}
	}

	@Override
	public void setDossierActionUserUuid(String dossierActionUserUuid) {
	}

	@Override
	public long getDossierActionId() {
		return _dossierActionId;
	}

	@Override
	public void setDossierActionId(long dossierActionId) {
		_columnBitmask = -1L;

		if (!_setOriginalDossierActionId) {
			_setOriginalDossierActionId = true;

			_originalDossierActionId = _dossierActionId;
		}

		_dossierActionId = dossierActionId;
	}

	public long getOriginalDossierActionId() {
		return _originalDossierActionId;
	}

	@Override
	public long getUserId() {
		return _userId;
	}

	@Override
	public void setUserId(long userId) {
		_columnBitmask |= USERID_COLUMN_BITMASK;

		if (!_setOriginalUserId) {
			_setOriginalUserId = true;

			_originalUserId = _userId;
		}

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

	public long getOriginalUserId() {
		return _originalUserId;
	}

	@Override
	public long getDossierId() {
		return _dossierId;
	}

	@Override
	public void setDossierId(long dossierId) {
		_columnBitmask |= DOSSIERID_COLUMN_BITMASK;

		if (!_setOriginalDossierId) {
			_setOriginalDossierId = true;

			_originalDossierId = _dossierId;
		}

		_dossierId = dossierId;
	}

	public long getOriginalDossierId() {
		return _originalDossierId;
	}

	@Override
	public long getRoleId() {
		return _roleId;
	}

	@Override
	public void setRoleId(long roleId) {
		_columnBitmask |= ROLEID_COLUMN_BITMASK;

		if (!_setOriginalRoleId) {
			_setOriginalRoleId = true;

			_originalRoleId = _roleId;
		}

		_roleId = roleId;
	}

	public long getOriginalRoleId() {
		return _originalRoleId;
	}

	@Override
	public String getStepCode() {
		if (_stepCode == null) {
			return "";
		}
		else {
			return _stepCode;
		}
	}

	@Override
	public void setStepCode(String stepCode) {
		_columnBitmask |= STEPCODE_COLUMN_BITMASK;

		if (_originalStepCode == null) {
			_originalStepCode = _stepCode;
		}

		_stepCode = stepCode;
	}

	public String getOriginalStepCode() {
		return GetterUtil.getString(_originalStepCode);
	}

	@Override
	public int getModerator() {
		return _moderator;
	}

	@Override
	public void setModerator(int moderator) {
		_moderator = moderator;
	}

	@Override
	public int getAssigned() {
		return _assigned;
	}

	@Override
	public void setAssigned(int assigned) {
		_columnBitmask |= ASSIGNED_COLUMN_BITMASK;

		if (!_setOriginalAssigned) {
			_setOriginalAssigned = true;

			_originalAssigned = _assigned;
		}

		_assigned = assigned;
	}

	public int getOriginalAssigned() {
		return _originalAssigned;
	}

	@Override
	public boolean getVisited() {
		return _visited;
	}

	@Override
	public boolean isVisited() {
		return _visited;
	}

	@Override
	public void setVisited(boolean visited) {
		_visited = visited;
	}

	public long getColumnBitmask() {
		return _columnBitmask;
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return ExpandoBridgeFactoryUtil.getExpandoBridge(0,
			DossierActionUser.class.getName(), getPrimaryKey());
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		ExpandoBridge expandoBridge = getExpandoBridge();

		expandoBridge.setAttributes(serviceContext);
	}

	@Override
	public DossierActionUser toEscapedModel() {
		if (_escapedModel == null) {
			_escapedModel = (DossierActionUser)ProxyUtil.newProxyInstance(_classLoader,
					_escapedModelInterfaces, new AutoEscapeBeanHandler(this));
		}

		return _escapedModel;
	}

	@Override
	public Object clone() {
		DossierActionUserImpl dossierActionUserImpl = new DossierActionUserImpl();

		dossierActionUserImpl.setUuid(getUuid());
		dossierActionUserImpl.setDossierActionUserId(getDossierActionUserId());
		dossierActionUserImpl.setDossierActionId(getDossierActionId());
		dossierActionUserImpl.setUserId(getUserId());
		dossierActionUserImpl.setDossierId(getDossierId());
		dossierActionUserImpl.setRoleId(getRoleId());
		dossierActionUserImpl.setStepCode(getStepCode());
		dossierActionUserImpl.setModerator(getModerator());
		dossierActionUserImpl.setAssigned(getAssigned());
		dossierActionUserImpl.setVisited(isVisited());

		dossierActionUserImpl.resetOriginalValues();

		return dossierActionUserImpl;
	}

	@Override
	public int compareTo(DossierActionUser dossierActionUser) {
		int value = 0;

		if (getDossierActionId() < dossierActionUser.getDossierActionId()) {
			value = -1;
		}
		else if (getDossierActionId() > dossierActionUser.getDossierActionId()) {
			value = 1;
		}
		else {
			value = 0;
		}

		value = value * -1;

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

		if (!(obj instanceof DossierActionUser)) {
			return false;
		}

		DossierActionUser dossierActionUser = (DossierActionUser)obj;

		long primaryKey = dossierActionUser.getPrimaryKey();

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
		DossierActionUserModelImpl dossierActionUserModelImpl = this;

		dossierActionUserModelImpl._originalUuid = dossierActionUserModelImpl._uuid;

		dossierActionUserModelImpl._originalDossierActionId = dossierActionUserModelImpl._dossierActionId;

		dossierActionUserModelImpl._setOriginalDossierActionId = false;

		dossierActionUserModelImpl._originalUserId = dossierActionUserModelImpl._userId;

		dossierActionUserModelImpl._setOriginalUserId = false;

		dossierActionUserModelImpl._originalDossierId = dossierActionUserModelImpl._dossierId;

		dossierActionUserModelImpl._setOriginalDossierId = false;

		dossierActionUserModelImpl._originalRoleId = dossierActionUserModelImpl._roleId;

		dossierActionUserModelImpl._setOriginalRoleId = false;

		dossierActionUserModelImpl._originalStepCode = dossierActionUserModelImpl._stepCode;

		dossierActionUserModelImpl._originalAssigned = dossierActionUserModelImpl._assigned;

		dossierActionUserModelImpl._setOriginalAssigned = false;

		dossierActionUserModelImpl._columnBitmask = 0;
	}

	@Override
	public CacheModel<DossierActionUser> toCacheModel() {
		DossierActionUserCacheModel dossierActionUserCacheModel = new DossierActionUserCacheModel();

		dossierActionUserCacheModel.uuid = getUuid();

		String uuid = dossierActionUserCacheModel.uuid;

		if ((uuid != null) && (uuid.length() == 0)) {
			dossierActionUserCacheModel.uuid = null;
		}

		dossierActionUserCacheModel.dossierActionUserId = getDossierActionUserId();

		dossierActionUserCacheModel.dossierActionId = getDossierActionId();

		dossierActionUserCacheModel.userId = getUserId();

		dossierActionUserCacheModel.dossierId = getDossierId();

		dossierActionUserCacheModel.roleId = getRoleId();

		dossierActionUserCacheModel.stepCode = getStepCode();

		String stepCode = dossierActionUserCacheModel.stepCode;

		if ((stepCode != null) && (stepCode.length() == 0)) {
			dossierActionUserCacheModel.stepCode = null;
		}

		dossierActionUserCacheModel.moderator = getModerator();

		dossierActionUserCacheModel.assigned = getAssigned();

		dossierActionUserCacheModel.visited = isVisited();

		return dossierActionUserCacheModel;
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(21);

		sb.append("{uuid=");
		sb.append(getUuid());
		sb.append(", dossierActionUserId=");
		sb.append(getDossierActionUserId());
		sb.append(", dossierActionId=");
		sb.append(getDossierActionId());
		sb.append(", userId=");
		sb.append(getUserId());
		sb.append(", dossierId=");
		sb.append(getDossierId());
		sb.append(", roleId=");
		sb.append(getRoleId());
		sb.append(", stepCode=");
		sb.append(getStepCode());
		sb.append(", moderator=");
		sb.append(getModerator());
		sb.append(", assigned=");
		sb.append(getAssigned());
		sb.append(", visited=");
		sb.append(isVisited());
		sb.append("}");

		return sb.toString();
	}

	@Override
	public String toXmlString() {
		StringBundler sb = new StringBundler(34);

		sb.append("<model><model-name>");
		sb.append("org.opencps.dossiermgt.model.DossierActionUser");
		sb.append("</model-name>");

		sb.append(
			"<column><column-name>uuid</column-name><column-value><![CDATA[");
		sb.append(getUuid());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>dossierActionUserId</column-name><column-value><![CDATA[");
		sb.append(getDossierActionUserId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>dossierActionId</column-name><column-value><![CDATA[");
		sb.append(getDossierActionId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>userId</column-name><column-value><![CDATA[");
		sb.append(getUserId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>dossierId</column-name><column-value><![CDATA[");
		sb.append(getDossierId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>roleId</column-name><column-value><![CDATA[");
		sb.append(getRoleId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>stepCode</column-name><column-value><![CDATA[");
		sb.append(getStepCode());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>moderator</column-name><column-value><![CDATA[");
		sb.append(getModerator());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>assigned</column-name><column-value><![CDATA[");
		sb.append(getAssigned());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>visited</column-name><column-value><![CDATA[");
		sb.append(isVisited());
		sb.append("]]></column-value></column>");

		sb.append("</model>");

		return sb.toString();
	}

	private static final ClassLoader _classLoader = DossierActionUser.class.getClassLoader();
	private static final Class<?>[] _escapedModelInterfaces = new Class[] {
			DossierActionUser.class, ModelWrapper.class
		};
	private String _uuid;
	private String _originalUuid;
	private long _dossierActionUserId;
	private long _dossierActionId;
	private long _originalDossierActionId;
	private boolean _setOriginalDossierActionId;
	private long _userId;
	private long _originalUserId;
	private boolean _setOriginalUserId;
	private long _dossierId;
	private long _originalDossierId;
	private boolean _setOriginalDossierId;
	private long _roleId;
	private long _originalRoleId;
	private boolean _setOriginalRoleId;
	private String _stepCode;
	private String _originalStepCode;
	private int _moderator;
	private int _assigned;
	private int _originalAssigned;
	private boolean _setOriginalAssigned;
	private boolean _visited;
	private long _columnBitmask;
	private DossierActionUser _escapedModel;
}