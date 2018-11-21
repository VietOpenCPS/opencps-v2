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

package org.opencps.deliverable.model;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.annotation.ImplementationClassName;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.util.Accessor;

/**
 * The extended model interface for the OpenCPSDeliverableType service. Represents a row in the &quot;opencps_deliverabletype&quot; database table, with each column mapped to a property of this class.
 *
 * @author binhth
 * @see OpenCPSDeliverableTypeModel
 * @see org.opencps.deliverable.model.impl.OpenCPSDeliverableTypeImpl
 * @see org.opencps.deliverable.model.impl.OpenCPSDeliverableTypeModelImpl
 * @generated
 */
@ImplementationClassName("org.opencps.deliverable.model.impl.OpenCPSDeliverableTypeImpl")
@ProviderType
public interface OpenCPSDeliverableType extends OpenCPSDeliverableTypeModel,
	PersistedModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to {@link org.opencps.deliverable.model.impl.OpenCPSDeliverableTypeImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public static final Accessor<OpenCPSDeliverableType, Long> DELIVERABLE_TYPE_ID_ACCESSOR =
		new Accessor<OpenCPSDeliverableType, Long>() {
			@Override
			public Long get(OpenCPSDeliverableType openCPSDeliverableType) {
				return openCPSDeliverableType.getDeliverableTypeId();
			}

			@Override
			public Class<Long> getAttributeClass() {
				return Long.class;
			}

			@Override
			public Class<OpenCPSDeliverableType> getTypeClass() {
				return OpenCPSDeliverableType.class;
			}
		};
}