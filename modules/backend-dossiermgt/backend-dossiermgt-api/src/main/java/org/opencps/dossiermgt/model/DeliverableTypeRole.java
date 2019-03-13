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
 * The extended model interface for the DeliverableTypeRole service. Represents a row in the &quot;opencps_deliverabletyperole&quot; database table, with each column mapped to a property of this class.
 *
 * @author huymq
 * @see DeliverableTypeRoleModel
 * @see org.opencps.dossiermgt.model.impl.DeliverableTypeRoleImpl
 * @see org.opencps.dossiermgt.model.impl.DeliverableTypeRoleModelImpl
 * @generated
 */
@ImplementationClassName("org.opencps.dossiermgt.model.impl.DeliverableTypeRoleImpl")
@ProviderType
public interface DeliverableTypeRole extends DeliverableTypeRoleModel,
	PersistedModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to {@link org.opencps.dossiermgt.model.impl.DeliverableTypeRoleImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public static final Accessor<DeliverableTypeRole, Long> DELIVERABLE_TYPE_ROLE_ID_ACCESSOR =
		new Accessor<DeliverableTypeRole, Long>() {
			@Override
			public Long get(DeliverableTypeRole deliverableTypeRole) {
				return deliverableTypeRole.getDeliverableTypeRoleId();
			}

			@Override
			public Class<Long> getAttributeClass() {
				return Long.class;
			}

			@Override
			public Class<DeliverableTypeRole> getTypeClass() {
				return DeliverableTypeRole.class;
			}
		};
}