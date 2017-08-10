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

package org.opencps.backend.dossiermgt.service.impl;

import aQute.bnd.annotation.ProviderType;

import java.util.List;

import org.opencps.backend.dossiermgt.model.ServiceInfo;
import org.opencps.backend.dossiermgt.service.base.ServiceInfoLocalServiceBaseImpl;

/**
 * The implementation of the service info local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link org.opencps.backend.dossiermgt.service.ServiceInfoLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ServiceInfoLocalServiceBaseImpl
 * @see org.opencps.backend.dossiermgt.service.ServiceInfoLocalServiceUtil
 */
@ProviderType
public class ServiceInfoLocalServiceImpl extends ServiceInfoLocalServiceBaseImpl {
	
	public List<ServiceInfo> getServiceInfosByCompanyId(long companyId) {
		return serviceInfoPersistence.findByCompanyId(companyId);
	}
	
	public List<ServiceInfo> getServiceInfosByCompanyId(long companyId, int start, int end) {
		return serviceInfoPersistence.findByCompanyId(companyId, start, end);
	}
	
	public List<ServiceInfo> getServiceInfosByGroupId(long groupId) {
		return serviceInfoPersistence.findByGroupId(groupId);
	}
	
	public List<ServiceInfo> getServiceInfosByGroupId(long groupId, int start, int end) {
		return serviceInfoPersistence.findByGroupId(groupId, start, end);
	}
}