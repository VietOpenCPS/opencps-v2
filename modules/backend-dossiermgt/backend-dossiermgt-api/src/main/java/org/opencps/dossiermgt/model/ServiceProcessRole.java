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

import com.liferay.portal.kernel.annotation.ImplementationClassName;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.util.Accessor;

/**
 * The extended model interface for the ServiceProcessRole service. Represents a row in the &quot;opencps_serviceprocessrole&quot; database table, with each column mapped to a property of this class.
 *
 * @author huymq
 * @see ServiceProcessRoleModel
 * @see org.opencps.dossiermgt.model.impl.ServiceProcessRoleImpl
 * @see org.opencps.dossiermgt.model.impl.ServiceProcessRoleModelImpl
 * @generated
 */
@ImplementationClassName("org.opencps.dossiermgt.model.impl.ServiceProcessRoleImpl")
@ProviderType
public interface ServiceProcessRole extends ServiceProcessRoleModel,
	PersistedModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to {@link org.opencps.dossiermgt.model.impl.ServiceProcessRoleImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public static final Accessor<ServiceProcessRole, Long> SERVICE_PROCESS_ID_ACCESSOR =
		new Accessor<ServiceProcessRole, Long>() {
			@Override
			public Long get(ServiceProcessRole serviceProcessRole) {
				return serviceProcessRole.getServiceProcessId();
			}

			@Override
			public Class<Long> getAttributeClass() {
				return Long.class;
			}

			@Override
			public Class<ServiceProcessRole> getTypeClass() {
				return ServiceProcessRole.class;
			}
		};

	public static final Accessor<ServiceProcessRole, Long> ROLE_ID_ACCESSOR = new Accessor<ServiceProcessRole, Long>() {
			@Override
			public Long get(ServiceProcessRole serviceProcessRole) {
				return serviceProcessRole.getRoleId();
			}

			@Override
			public Class<Long> getAttributeClass() {
				return Long.class;
			}

			@Override
			public Class<ServiceProcessRole> getTypeClass() {
				return ServiceProcessRole.class;
			}
		};
}