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

package org.opencps.dossiermgt.service.persistence;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.util.HashUtil;
import com.liferay.portal.kernel.util.StringBundler;

import java.io.Serializable;

/**
 * @author huymq
 * @generated
 */
@ProviderType
public class ServiceFileTemplatePK implements Comparable<ServiceFileTemplatePK>,
	Serializable {
	public long serviceInfoId;
	public String fileTemplateNo;

	public ServiceFileTemplatePK() {
	}

	public ServiceFileTemplatePK(long serviceInfoId, String fileTemplateNo) {
		this.serviceInfoId = serviceInfoId;
		this.fileTemplateNo = fileTemplateNo;
	}

	public long getServiceInfoId() {
		return serviceInfoId;
	}

	public void setServiceInfoId(long serviceInfoId) {
		this.serviceInfoId = serviceInfoId;
	}

	public String getFileTemplateNo() {
		return fileTemplateNo;
	}

	public void setFileTemplateNo(String fileTemplateNo) {
		this.fileTemplateNo = fileTemplateNo;
	}

	@Override
	public int compareTo(ServiceFileTemplatePK pk) {
		if (pk == null) {
			return -1;
		}

		int value = 0;

		if (serviceInfoId < pk.serviceInfoId) {
			value = -1;
		}
		else if (serviceInfoId > pk.serviceInfoId) {
			value = 1;
		}
		else {
			value = 0;
		}

		if (value != 0) {
			return value;
		}

		value = fileTemplateNo.compareTo(pk.fileTemplateNo);

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

		if (!(obj instanceof ServiceFileTemplatePK)) {
			return false;
		}

		ServiceFileTemplatePK pk = (ServiceFileTemplatePK)obj;

		if ((serviceInfoId == pk.serviceInfoId) &&
				(fileTemplateNo.equals(pk.fileTemplateNo))) {
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public int hashCode() {
		int hashCode = 0;

		hashCode = HashUtil.hash(hashCode, serviceInfoId);
		hashCode = HashUtil.hash(hashCode, fileTemplateNo);

		return hashCode;
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(6);

		sb.append("{");

		sb.append("serviceInfoId=");

		sb.append(serviceInfoId);
		sb.append(", fileTemplateNo=");

		sb.append(fileTemplateNo);

		sb.append("}");

		return sb.toString();
	}
}