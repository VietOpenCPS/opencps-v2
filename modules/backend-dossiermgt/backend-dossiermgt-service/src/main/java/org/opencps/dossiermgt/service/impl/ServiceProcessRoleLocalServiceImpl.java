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

import aQute.bnd.annotation.ProviderType;

import java.util.List;

import org.opencps.dossiermgt.model.ServiceProcessRole;
import org.opencps.dossiermgt.service.base.ServiceProcessRoleLocalServiceBaseImpl;

/**
 * The implementation of the service process role local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link org.opencps.dossiermgt.service.ServiceProcessRoleLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author huymq
 * @see ServiceProcessRoleLocalServiceBaseImpl
 * @see org.opencps.dossiermgt.service.ServiceProcessRoleLocalServiceUtil
 */
@ProviderType
public class ServiceProcessRoleLocalServiceImpl
	extends ServiceProcessRoleLocalServiceBaseImpl {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this class directly. Always use {@link org.opencps.dossiermgt.service.ServiceProcessRoleLocalServiceUtil} to access the service process role local service.
	 */
	
	public List<ServiceProcessRole> findByS_P_ID(long serviceProcessId) {
		return serviceProcessRolePersistence.findByP_S_ID(serviceProcessId);
	}
}