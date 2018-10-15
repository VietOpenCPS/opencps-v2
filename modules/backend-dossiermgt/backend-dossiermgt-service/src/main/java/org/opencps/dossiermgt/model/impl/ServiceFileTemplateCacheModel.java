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

import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.util.HashUtil;
import com.liferay.portal.kernel.util.StringBundler;

import org.opencps.dossiermgt.model.ServiceFileTemplate;
import org.opencps.dossiermgt.service.persistence.ServiceFileTemplatePK;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

/**
 * The cache model class for representing ServiceFileTemplate in entity cache.
 *
 * @author huymq
 * @see ServiceFileTemplate
 * @generated
 */
@ProviderType
public class ServiceFileTemplateCacheModel implements CacheModel<ServiceFileTemplate>,
	Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof ServiceFileTemplateCacheModel)) {
			return false;
		}

		ServiceFileTemplateCacheModel serviceFileTemplateCacheModel = (ServiceFileTemplateCacheModel)obj;

		if (serviceFileTemplatePK.equals(
					serviceFileTemplateCacheModel.serviceFileTemplatePK)) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, serviceFileTemplatePK);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(11);

		sb.append("{uuid=");
		sb.append(uuid);
		sb.append(", serviceInfoId=");
		sb.append(serviceInfoId);
		sb.append(", fileTemplateNo=");
		sb.append(fileTemplateNo);
		sb.append(", templateName=");
		sb.append(templateName);
		sb.append(", fileEntryId=");
		sb.append(fileEntryId);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public ServiceFileTemplate toEntityModel() {
		ServiceFileTemplateImpl serviceFileTemplateImpl = new ServiceFileTemplateImpl();

		if (uuid == null) {
			serviceFileTemplateImpl.setUuid("");
		}
		else {
			serviceFileTemplateImpl.setUuid(uuid);
		}

		serviceFileTemplateImpl.setServiceInfoId(serviceInfoId);

		if (fileTemplateNo == null) {
			serviceFileTemplateImpl.setFileTemplateNo("");
		}
		else {
			serviceFileTemplateImpl.setFileTemplateNo(fileTemplateNo);
		}

		if (templateName == null) {
			serviceFileTemplateImpl.setTemplateName("");
		}
		else {
			serviceFileTemplateImpl.setTemplateName(templateName);
		}

		serviceFileTemplateImpl.setFileEntryId(fileEntryId);

		serviceFileTemplateImpl.resetOriginalValues();

		return serviceFileTemplateImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		uuid = objectInput.readUTF();

		serviceInfoId = objectInput.readLong();
		fileTemplateNo = objectInput.readUTF();
		templateName = objectInput.readUTF();

		fileEntryId = objectInput.readLong();

		serviceFileTemplatePK = new ServiceFileTemplatePK(serviceInfoId,
				fileTemplateNo);
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput)
		throws IOException {
		if (uuid == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(uuid);
		}

		objectOutput.writeLong(serviceInfoId);

		if (fileTemplateNo == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(fileTemplateNo);
		}

		if (templateName == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(templateName);
		}

		objectOutput.writeLong(fileEntryId);
	}

	public String uuid;
	public long serviceInfoId;
	public String fileTemplateNo;
	public String templateName;
	public long fileEntryId;
	public transient ServiceFileTemplatePK serviceFileTemplatePK;
}