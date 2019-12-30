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

package org.opencps.dossiermgt.service.impl;

import org.opencps.dossiermgt.model.ServiceInfoMapping;
import org.opencps.dossiermgt.service.base.ServiceInfoMappingLocalServiceBaseImpl;

/**
 * The implementation of the service info mapping local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link org.opencps.dossiermgt.service.ServiceInfoMappingLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author huymq
 * @see ServiceInfoMappingLocalServiceBaseImpl
 * @see org.opencps.dossiermgt.service.ServiceInfoMappingLocalServiceUtil
 */
public class ServiceInfoMappingLocalServiceImpl
	extends ServiceInfoMappingLocalServiceBaseImpl {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this class directly. Always use {@link org.opencps.dossiermgt.service.ServiceInfoMappingLocalServiceUtil} to access the service info mapping local service.
	 */
	
	public ServiceInfoMapping fetchDVCQGServiceCode(long groupId, String serviceCode) {
		return serviceInfoMappingPersistence.fetchByF_GID_SC(groupId, serviceCode);
	}
}