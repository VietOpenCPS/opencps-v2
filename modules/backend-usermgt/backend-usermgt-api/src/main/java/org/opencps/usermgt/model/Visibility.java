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

package org.opencps.usermgt.model;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.annotation.ImplementationClassName;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.util.Accessor;

/**
 * The extended model interface for the Visibility service. Represents a row in the &quot;opencps_visibility&quot; database table, with each column mapped to a property of this class.
 *
 * @author khoavu
 * @see VisibilityModel
 * @see org.opencps.usermgt.model.impl.VisibilityImpl
 * @see org.opencps.usermgt.model.impl.VisibilityModelImpl
 * @generated
 */
@ImplementationClassName("org.opencps.usermgt.model.impl.VisibilityImpl")
@ProviderType
public interface Visibility extends VisibilityModel, PersistedModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to {@link org.opencps.usermgt.model.impl.VisibilityImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public static final Accessor<Visibility, Long> VISIBILITY_ID_ACCESSOR = new Accessor<Visibility, Long>() {
			@Override
			public Long get(Visibility visibility) {
				return visibility.getVisibilityId();
			}

			@Override
			public Class<Long> getAttributeClass() {
				return Long.class;
			}

			@Override
			public Class<Visibility> getTypeClass() {
				return Visibility.class;
			}
		};
}