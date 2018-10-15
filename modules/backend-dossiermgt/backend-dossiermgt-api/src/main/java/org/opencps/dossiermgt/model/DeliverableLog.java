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
 * The extended model interface for the DeliverableLog service. Represents a row in the &quot;opencps_deliverablelog&quot; database table, with each column mapped to a property of this class.
 *
 * @author huymq
 * @see DeliverableLogModel
 * @see org.opencps.dossiermgt.model.impl.DeliverableLogImpl
 * @see org.opencps.dossiermgt.model.impl.DeliverableLogModelImpl
 * @generated
 */
@ImplementationClassName("org.opencps.dossiermgt.model.impl.DeliverableLogImpl")
@ProviderType
public interface DeliverableLog extends DeliverableLogModel, PersistedModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to {@link org.opencps.dossiermgt.model.impl.DeliverableLogImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public static final Accessor<DeliverableLog, Long> DELIVERABLE_LOG_ID_ACCESSOR =
		new Accessor<DeliverableLog, Long>() {
			@Override
			public Long get(DeliverableLog deliverableLog) {
				return deliverableLog.getDeliverableLogId();
			}

			@Override
			public Class<Long> getAttributeClass() {
				return Long.class;
			}

			@Override
			public Class<DeliverableLog> getTypeClass() {
				return DeliverableLog.class;
			}
		};
}