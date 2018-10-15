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
 * The extended model interface for the DossierDocument service. Represents a row in the &quot;opencps_dossierdocument&quot; database table, with each column mapped to a property of this class.
 *
 * @author huymq
 * @see DossierDocumentModel
 * @see org.opencps.dossiermgt.model.impl.DossierDocumentImpl
 * @see org.opencps.dossiermgt.model.impl.DossierDocumentModelImpl
 * @generated
 */
@ImplementationClassName("org.opencps.dossiermgt.model.impl.DossierDocumentImpl")
@ProviderType
public interface DossierDocument extends DossierDocumentModel, PersistedModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to {@link org.opencps.dossiermgt.model.impl.DossierDocumentImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public static final Accessor<DossierDocument, Long> DOSSIER_DOCUMENT_ID_ACCESSOR =
		new Accessor<DossierDocument, Long>() {
			@Override
			public Long get(DossierDocument dossierDocument) {
				return dossierDocument.getDossierDocumentId();
			}

			@Override
			public Class<Long> getAttributeClass() {
				return Long.class;
			}

			@Override
			public Class<DossierDocument> getTypeClass() {
				return DossierDocument.class;
			}
		};
}