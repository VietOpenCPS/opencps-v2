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

import com.liferay.exportimport.kernel.lar.StagedModelType;

import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.model.impl.BaseModelImpl;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.StringBundler;

import org.opencps.dossiermgt.model.ReportLandTax;
import org.opencps.dossiermgt.model.ReportLandTaxModel;

import java.io.Serializable;

import java.sql.Types;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * The base model implementation for the ReportLandTax service. Represents a row in the &quot;opencps_reportlandtax&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This implementation and its corresponding interface {@link ReportLandTaxModel} exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link ReportLandTaxImpl}.
 * </p>
 *
 * @author huymq
 * @see ReportLandTaxImpl
 * @see ReportLandTax
 * @see ReportLandTaxModel
 * @generated
 */
@ProviderType
public class ReportLandTaxModelImpl extends BaseModelImpl<ReportLandTax>
	implements ReportLandTaxModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a report land tax model instance should use the {@link ReportLandTax} interface instead.
	 */
	public static final String TABLE_NAME = "opencps_reportlandtax";
	public static final Object[][] TABLE_COLUMNS = {
			{ "uuid_", Types.VARCHAR },
			{ "reportId", Types.BIGINT },
			{ "groupId", Types.BIGINT },
			{ "companyId", Types.BIGINT },
			{ "userId", Types.BIGINT },
			{ "userName", Types.VARCHAR },
			{ "createDate", Types.TIMESTAMP },
			{ "modifiedDate", Types.TIMESTAMP },
			{ "dossierNo", Types.VARCHAR },
			{ "bodyRequest", Types.VARCHAR },
			{ "response", Types.VARCHAR }
		};
	public static final Map<String, Integer> TABLE_COLUMNS_MAP = new HashMap<String, Integer>();

	static {
		TABLE_COLUMNS_MAP.put("uuid_", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("reportId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("groupId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("companyId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("userId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("userName", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("createDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("modifiedDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("dossierNo", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("bodyRequest", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("response", Types.VARCHAR);
	}

	public static final String TABLE_SQL_CREATE = "create table opencps_reportlandtax (uuid_ VARCHAR(75) null,reportId LONG not null primary key,groupId LONG,companyId LONG,userId LONG,userName VARCHAR(75) null,createDate DATE null,modifiedDate DATE null,dossierNo VARCHAR(75) null,bodyRequest VARCHAR(75) null,response VARCHAR(75) null)";
	public static final String TABLE_SQL_DROP = "drop table opencps_reportlandtax";
	public static final String ORDER_BY_JPQL = " ORDER BY reportLandTax.reportId ASC";
	public static final String ORDER_BY_SQL = " ORDER BY opencps_reportlandtax.reportId ASC";
	public static final String DATA_SOURCE = "liferayDataSource";
	public static final String SESSION_FACTORY = "liferaySessionFactory";
	public static final String TX_MANAGER = "liferayTransactionManager";
	public static final boolean ENTITY_CACHE_ENABLED = GetterUtil.getBoolean(org.opencps.backend.dossiermgt.service.util.ServiceProps.get(
				"value.object.entity.cache.enabled.org.opencps.dossiermgt.model.ReportLandTax"),
			true);
	public static final boolean FINDER_CACHE_ENABLED = GetterUtil.getBoolean(org.opencps.backend.dossiermgt.service.util.ServiceProps.get(
				"value.object.finder.cache.enabled.org.opencps.dossiermgt.model.ReportLandTax"),
			true);
	public static final boolean COLUMN_BITMASK_ENABLED = GetterUtil.getBoolean(org.opencps.backend.dossiermgt.service.util.ServiceProps.get(
				"value.object.column.bitmask.enabled.org.opencps.dossiermgt.model.ReportLandTax"),
			true);
	public static final long COMPANYID_COLUMN_BITMASK = 1L;
	public static final long GROUPID_COLUMN_BITMASK = 2L;
	public static final long UUID_COLUMN_BITMASK = 4L;
	public static final long REPORTID_COLUMN_BITMASK = 8L;
	public static final long LOCK_EXPIRATION_TIME = GetterUtil.getLong(org.opencps.backend.dossiermgt.service.util.ServiceProps.get(
				"lock.expiration.time.org.opencps.dossiermgt.model.ReportLandTax"));

	public ReportLandTaxModelImpl() {
	}

	@Override
	public long getPrimaryKey() {
		return _reportId;
	}

	@Override
	public void setPrimaryKey(long primaryKey) {
		setReportId(primaryKey);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _reportId;
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Class<?> getModelClass() {
		return ReportLandTax.class;
	}

	@Override
	public String getModelClassName() {
		return ReportLandTax.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("uuid", getUuid());
		attributes.put("reportId", getReportId());
		attributes.put("groupId", getGroupId());
		attributes.put("companyId", getCompanyId());
		attributes.put("userId", getUserId());
		attributes.put("userName", getUserName());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("dossierNo", getDossierNo());
		attributes.put("bodyRequest", getBodyRequest());
		attributes.put("response", getResponse());

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

		Long reportId = (Long)attributes.get("reportId");

		if (reportId != null) {
			setReportId(reportId);
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

		String dossierNo = (String)attributes.get("dossierNo");

		if (dossierNo != null) {
			setDossierNo(dossierNo);
		}

		String bodyRequest = (String)attributes.get("bodyRequest");

		if (bodyRequest != null) {
			setBodyRequest(bodyRequest);
		}

		String response = (String)attributes.get("response");

		if (response != null) {
			setResponse(response);
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
	public long getReportId() {
		return _reportId;
	}

	@Override
	public void setReportId(long reportId) {
		_reportId = reportId;
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

		_modifiedDate = modifiedDate;
	}

	@Override
	public String getDossierNo() {
		if (_dossierNo == null) {
			return "";
		}
		else {
			return _dossierNo;
		}
	}

	@Override
	public void setDossierNo(String dossierNo) {
		_dossierNo = dossierNo;
	}

	@Override
	public String getBodyRequest() {
		if (_bodyRequest == null) {
			return "";
		}
		else {
			return _bodyRequest;
		}
	}

	@Override
	public void setBodyRequest(String bodyRequest) {
		_bodyRequest = bodyRequest;
	}

	@Override
	public String getResponse() {
		if (_response == null) {
			return "";
		}
		else {
			return _response;
		}
	}

	@Override
	public void setResponse(String response) {
		_response = response;
	}

	@Override
	public StagedModelType getStagedModelType() {
		return new StagedModelType(PortalUtil.getClassNameId(
				ReportLandTax.class.getName()));
	}

	public long getColumnBitmask() {
		return _columnBitmask;
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return ExpandoBridgeFactoryUtil.getExpandoBridge(getCompanyId(),
			ReportLandTax.class.getName(), getPrimaryKey());
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		ExpandoBridge expandoBridge = getExpandoBridge();

		expandoBridge.setAttributes(serviceContext);
	}

	@Override
	public ReportLandTax toEscapedModel() {
		if (_escapedModel == null) {
			_escapedModel = (ReportLandTax)ProxyUtil.newProxyInstance(_classLoader,
					_escapedModelInterfaces, new AutoEscapeBeanHandler(this));
		}

		return _escapedModel;
	}

	@Override
	public Object clone() {
		ReportLandTaxImpl reportLandTaxImpl = new ReportLandTaxImpl();

		reportLandTaxImpl.setUuid(getUuid());
		reportLandTaxImpl.setReportId(getReportId());
		reportLandTaxImpl.setGroupId(getGroupId());
		reportLandTaxImpl.setCompanyId(getCompanyId());
		reportLandTaxImpl.setUserId(getUserId());
		reportLandTaxImpl.setUserName(getUserName());
		reportLandTaxImpl.setCreateDate(getCreateDate());
		reportLandTaxImpl.setModifiedDate(getModifiedDate());
		reportLandTaxImpl.setDossierNo(getDossierNo());
		reportLandTaxImpl.setBodyRequest(getBodyRequest());
		reportLandTaxImpl.setResponse(getResponse());

		reportLandTaxImpl.resetOriginalValues();

		return reportLandTaxImpl;
	}

	@Override
	public int compareTo(ReportLandTax reportLandTax) {
		long primaryKey = reportLandTax.getPrimaryKey();

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

		if (!(obj instanceof ReportLandTax)) {
			return false;
		}

		ReportLandTax reportLandTax = (ReportLandTax)obj;

		long primaryKey = reportLandTax.getPrimaryKey();

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
		ReportLandTaxModelImpl reportLandTaxModelImpl = this;

		reportLandTaxModelImpl._originalUuid = reportLandTaxModelImpl._uuid;

		reportLandTaxModelImpl._originalGroupId = reportLandTaxModelImpl._groupId;

		reportLandTaxModelImpl._setOriginalGroupId = false;

		reportLandTaxModelImpl._originalCompanyId = reportLandTaxModelImpl._companyId;

		reportLandTaxModelImpl._setOriginalCompanyId = false;

		reportLandTaxModelImpl._setModifiedDate = false;

		reportLandTaxModelImpl._columnBitmask = 0;
	}

	@Override
	public CacheModel<ReportLandTax> toCacheModel() {
		ReportLandTaxCacheModel reportLandTaxCacheModel = new ReportLandTaxCacheModel();

		reportLandTaxCacheModel.uuid = getUuid();

		String uuid = reportLandTaxCacheModel.uuid;

		if ((uuid != null) && (uuid.length() == 0)) {
			reportLandTaxCacheModel.uuid = null;
		}

		reportLandTaxCacheModel.reportId = getReportId();

		reportLandTaxCacheModel.groupId = getGroupId();

		reportLandTaxCacheModel.companyId = getCompanyId();

		reportLandTaxCacheModel.userId = getUserId();

		reportLandTaxCacheModel.userName = getUserName();

		String userName = reportLandTaxCacheModel.userName;

		if ((userName != null) && (userName.length() == 0)) {
			reportLandTaxCacheModel.userName = null;
		}

		Date createDate = getCreateDate();

		if (createDate != null) {
			reportLandTaxCacheModel.createDate = createDate.getTime();
		}
		else {
			reportLandTaxCacheModel.createDate = Long.MIN_VALUE;
		}

		Date modifiedDate = getModifiedDate();

		if (modifiedDate != null) {
			reportLandTaxCacheModel.modifiedDate = modifiedDate.getTime();
		}
		else {
			reportLandTaxCacheModel.modifiedDate = Long.MIN_VALUE;
		}

		reportLandTaxCacheModel.dossierNo = getDossierNo();

		String dossierNo = reportLandTaxCacheModel.dossierNo;

		if ((dossierNo != null) && (dossierNo.length() == 0)) {
			reportLandTaxCacheModel.dossierNo = null;
		}

		reportLandTaxCacheModel.bodyRequest = getBodyRequest();

		String bodyRequest = reportLandTaxCacheModel.bodyRequest;

		if ((bodyRequest != null) && (bodyRequest.length() == 0)) {
			reportLandTaxCacheModel.bodyRequest = null;
		}

		reportLandTaxCacheModel.response = getResponse();

		String response = reportLandTaxCacheModel.response;

		if ((response != null) && (response.length() == 0)) {
			reportLandTaxCacheModel.response = null;
		}

		return reportLandTaxCacheModel;
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(23);

		sb.append("{uuid=");
		sb.append(getUuid());
		sb.append(", reportId=");
		sb.append(getReportId());
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
		sb.append(", dossierNo=");
		sb.append(getDossierNo());
		sb.append(", bodyRequest=");
		sb.append(getBodyRequest());
		sb.append(", response=");
		sb.append(getResponse());
		sb.append("}");

		return sb.toString();
	}

	@Override
	public String toXmlString() {
		StringBundler sb = new StringBundler(37);

		sb.append("<model><model-name>");
		sb.append("org.opencps.dossiermgt.model.ReportLandTax");
		sb.append("</model-name>");

		sb.append(
			"<column><column-name>uuid</column-name><column-value><![CDATA[");
		sb.append(getUuid());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>reportId</column-name><column-value><![CDATA[");
		sb.append(getReportId());
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
			"<column><column-name>dossierNo</column-name><column-value><![CDATA[");
		sb.append(getDossierNo());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>bodyRequest</column-name><column-value><![CDATA[");
		sb.append(getBodyRequest());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>response</column-name><column-value><![CDATA[");
		sb.append(getResponse());
		sb.append("]]></column-value></column>");

		sb.append("</model>");

		return sb.toString();
	}

	private static final ClassLoader _classLoader = ReportLandTax.class.getClassLoader();
	private static final Class<?>[] _escapedModelInterfaces = new Class[] {
			ReportLandTax.class, ModelWrapper.class
		};
	private String _uuid;
	private String _originalUuid;
	private long _reportId;
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
	private String _dossierNo;
	private String _bodyRequest;
	private String _response;
	private long _columnBitmask;
	private ReportLandTax _escapedModel;
}