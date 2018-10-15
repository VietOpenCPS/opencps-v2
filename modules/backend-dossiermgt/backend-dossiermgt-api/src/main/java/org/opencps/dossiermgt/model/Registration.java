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
 * The extended model interface for the Registration service. Represents a row in the &quot;opencps_registration&quot; database table, with each column mapped to a property of this class.
 *
 * @author huymq
 * @see RegistrationModel
 * @see org.opencps.dossiermgt.model.impl.RegistrationImpl
 * @see org.opencps.dossiermgt.model.impl.RegistrationModelImpl
 * @generated
 */
@ImplementationClassName("org.opencps.dossiermgt.model.impl.RegistrationImpl")
@ProviderType
public interface Registration extends RegistrationModel, PersistedModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to {@link org.opencps.dossiermgt.model.impl.RegistrationImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public static final Accessor<Registration, Long> REGISTRATION_ID_ACCESSOR = new Accessor<Registration, Long>() {
			@Override
			public Long get(Registration registration) {
				return registration.getRegistrationId();
			}

			@Override
			public Class<Long> getAttributeClass() {
				return Long.class;
			}

			@Override
			public Class<Registration> getTypeClass() {
				return Registration.class;
			}
		};
}