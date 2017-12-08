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

package org.opencps.jasper.model;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.annotation.ImplementationClassName;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.util.Accessor;

/**
 * The extended model interface for the jasper service. Represents a row in the &quot;temp_jasper&quot; database table, with each column mapped to a property of this class.
 *
 * @author Binhth
 * @see jasperModel
 * @see org.opencps.jasper.model.impl.jasperImpl
 * @see org.opencps.jasper.model.impl.jasperModelImpl
 * @generated
 */
@ImplementationClassName("org.opencps.jasper.model.impl.jasperImpl")
@ProviderType
public interface jasper extends jasperModel, PersistedModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to {@link org.opencps.jasper.model.impl.jasperImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public static final Accessor<jasper, Long> JASPER_ID_ACCESSOR = new Accessor<jasper, Long>() {
			@Override
			public Long get(jasper jasper) {
				return jasper.getJasperId();
			}

			@Override
			public Class<Long> getAttributeClass() {
				return Long.class;
			}

			@Override
			public Class<jasper> getTypeClass() {
				return jasper.class;
			}
		};
}