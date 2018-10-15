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
 * The extended model interface for the DossierPart service. Represents a row in the &quot;opencps_dossierpart&quot; database table, with each column mapped to a property of this class.
 *
 * @author huymq
 * @see DossierPartModel
 * @see org.opencps.dossiermgt.model.impl.DossierPartImpl
 * @see org.opencps.dossiermgt.model.impl.DossierPartModelImpl
 * @generated
 */
@ImplementationClassName("org.opencps.dossiermgt.model.impl.DossierPartImpl")
@ProviderType
public interface DossierPart extends DossierPartModel, PersistedModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to {@link org.opencps.dossiermgt.model.impl.DossierPartImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public static final Accessor<DossierPart, Long> DOSSIER_PART_ID_ACCESSOR = new Accessor<DossierPart, Long>() {
			@Override
			public Long get(DossierPart dossierPart) {
				return dossierPart.getDossierPartId();
			}

			@Override
			public Class<Long> getAttributeClass() {
				return Long.class;
			}

			@Override
			public Class<DossierPart> getTypeClass() {
				return DossierPart.class;
			}
		};
}