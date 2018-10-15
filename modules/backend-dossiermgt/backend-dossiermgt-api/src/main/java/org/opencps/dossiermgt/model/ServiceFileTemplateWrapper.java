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

import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.service.ServiceContext;

import java.io.Serializable;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * <p>
 * This class is a wrapper for {@link ServiceFileTemplate}.
 * </p>
 *
 * @author huymq
 * @see ServiceFileTemplate
 * @generated
 */
@ProviderType
public class ServiceFileTemplateWrapper implements ServiceFileTemplate,
	ModelWrapper<ServiceFileTemplate> {
	public ServiceFileTemplateWrapper(ServiceFileTemplate serviceFileTemplate) {
		_serviceFileTemplate = serviceFileTemplate;
	}

	@Override
	public Class<?> getModelClass() {
		return ServiceFileTemplate.class;
	}

	@Override
	public String getModelClassName() {
		return ServiceFileTemplate.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("uuid", getUuid());
		attributes.put("serviceInfoId", getServiceInfoId());
		attributes.put("fileTemplateNo", getFileTemplateNo());
		attributes.put("templateName", getTemplateName());
		attributes.put("fileEntryId", getFileEntryId());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		String uuid = (String)attributes.get("uuid");

		if (uuid != null) {
			setUuid(uuid);
		}

		Long serviceInfoId = (Long)attributes.get("serviceInfoId");

		if (serviceInfoId != null) {
			setServiceInfoId(serviceInfoId);
		}

		String fileTemplateNo = (String)attributes.get("fileTemplateNo");

		if (fileTemplateNo != null) {
			setFileTemplateNo(fileTemplateNo);
		}

		String templateName = (String)attributes.get("templateName");

		if (templateName != null) {
			setTemplateName(templateName);
		}

		Long fileEntryId = (Long)attributes.get("fileEntryId");

		if (fileEntryId != null) {
			setFileEntryId(fileEntryId);
		}
	}

	@Override
	public Object clone() {
		return new ServiceFileTemplateWrapper((ServiceFileTemplate)_serviceFileTemplate.clone());
	}

	@Override
	public int compareTo(ServiceFileTemplate serviceFileTemplate) {
		return _serviceFileTemplate.compareTo(serviceFileTemplate);
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _serviceFileTemplate.getExpandoBridge();
	}

	/**
	* Returns the file entry ID of this service file template.
	*
	* @return the file entry ID of this service file template
	*/
	@Override
	public long getFileEntryId() {
		return _serviceFileTemplate.getFileEntryId();
	}

	/**
	* Returns the file template no of this service file template.
	*
	* @return the file template no of this service file template
	*/
	@Override
	public String getFileTemplateNo() {
		return _serviceFileTemplate.getFileTemplateNo();
	}

	/**
	* Returns the primary key of this service file template.
	*
	* @return the primary key of this service file template
	*/
	@Override
	public org.opencps.dossiermgt.service.persistence.ServiceFileTemplatePK getPrimaryKey() {
		return _serviceFileTemplate.getPrimaryKey();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _serviceFileTemplate.getPrimaryKeyObj();
	}

	/**
	* Returns the service info ID of this service file template.
	*
	* @return the service info ID of this service file template
	*/
	@Override
	public long getServiceInfoId() {
		return _serviceFileTemplate.getServiceInfoId();
	}

	/**
	* Returns the template name of this service file template.
	*
	* @return the template name of this service file template
	*/
	@Override
	public String getTemplateName() {
		return _serviceFileTemplate.getTemplateName();
	}

	/**
	* Returns the uuid of this service file template.
	*
	* @return the uuid of this service file template
	*/
	@Override
	public String getUuid() {
		return _serviceFileTemplate.getUuid();
	}

	@Override
	public int hashCode() {
		return _serviceFileTemplate.hashCode();
	}

	@Override
	public boolean isCachedModel() {
		return _serviceFileTemplate.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _serviceFileTemplate.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _serviceFileTemplate.isNew();
	}

	@Override
	public void persist() {
		_serviceFileTemplate.persist();
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_serviceFileTemplate.setCachedModel(cachedModel);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_serviceFileTemplate.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_serviceFileTemplate.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_serviceFileTemplate.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the file entry ID of this service file template.
	*
	* @param fileEntryId the file entry ID of this service file template
	*/
	@Override
	public void setFileEntryId(long fileEntryId) {
		_serviceFileTemplate.setFileEntryId(fileEntryId);
	}

	/**
	* Sets the file template no of this service file template.
	*
	* @param fileTemplateNo the file template no of this service file template
	*/
	@Override
	public void setFileTemplateNo(String fileTemplateNo) {
		_serviceFileTemplate.setFileTemplateNo(fileTemplateNo);
	}

	@Override
	public void setNew(boolean n) {
		_serviceFileTemplate.setNew(n);
	}

	/**
	* Sets the primary key of this service file template.
	*
	* @param primaryKey the primary key of this service file template
	*/
	@Override
	public void setPrimaryKey(
		org.opencps.dossiermgt.service.persistence.ServiceFileTemplatePK primaryKey) {
		_serviceFileTemplate.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_serviceFileTemplate.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the service info ID of this service file template.
	*
	* @param serviceInfoId the service info ID of this service file template
	*/
	@Override
	public void setServiceInfoId(long serviceInfoId) {
		_serviceFileTemplate.setServiceInfoId(serviceInfoId);
	}

	/**
	* Sets the template name of this service file template.
	*
	* @param templateName the template name of this service file template
	*/
	@Override
	public void setTemplateName(String templateName) {
		_serviceFileTemplate.setTemplateName(templateName);
	}

	/**
	* Sets the uuid of this service file template.
	*
	* @param uuid the uuid of this service file template
	*/
	@Override
	public void setUuid(String uuid) {
		_serviceFileTemplate.setUuid(uuid);
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<ServiceFileTemplate> toCacheModel() {
		return _serviceFileTemplate.toCacheModel();
	}

	@Override
	public ServiceFileTemplate toEscapedModel() {
		return new ServiceFileTemplateWrapper(_serviceFileTemplate.toEscapedModel());
	}

	@Override
	public String toString() {
		return _serviceFileTemplate.toString();
	}

	@Override
	public ServiceFileTemplate toUnescapedModel() {
		return new ServiceFileTemplateWrapper(_serviceFileTemplate.toUnescapedModel());
	}

	@Override
	public String toXmlString() {
		return _serviceFileTemplate.toXmlString();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof ServiceFileTemplateWrapper)) {
			return false;
		}

		ServiceFileTemplateWrapper serviceFileTemplateWrapper = (ServiceFileTemplateWrapper)obj;

		if (Objects.equals(_serviceFileTemplate,
					serviceFileTemplateWrapper._serviceFileTemplate)) {
			return true;
		}

		return false;
	}

	@Override
	public ServiceFileTemplate getWrappedModel() {
		return _serviceFileTemplate;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _serviceFileTemplate.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _serviceFileTemplate.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_serviceFileTemplate.resetOriginalValues();
	}

	private final ServiceFileTemplate _serviceFileTemplate;
}