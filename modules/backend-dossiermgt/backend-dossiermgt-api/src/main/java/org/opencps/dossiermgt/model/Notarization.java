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
 * The extended model interface for the Notarization service. Represents a row in the &quot;opencps_notarization&quot; database table, with each column mapped to a property of this class.
 *
 * @author huymq
 * @see NotarizationModel
 * @see org.opencps.dossiermgt.model.impl.NotarizationImpl
 * @see org.opencps.dossiermgt.model.impl.NotarizationModelImpl
 * @generated
 */
@ImplementationClassName("org.opencps.dossiermgt.model.impl.NotarizationImpl")
@ProviderType
public interface Notarization extends NotarizationModel, PersistedModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to {@link org.opencps.dossiermgt.model.impl.NotarizationImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public static final Accessor<Notarization, Long> NOTARIZATION_ID_ACCESSOR = new Accessor<Notarization, Long>() {
			@Override
			public Long get(Notarization notarization) {
				return notarization.getNotarizationId();
			}

			@Override
			public Class<Long> getAttributeClass() {
				return Long.class;
			}

			@Override
			public Class<Notarization> getTypeClass() {
				return Notarization.class;
			}
		};
}