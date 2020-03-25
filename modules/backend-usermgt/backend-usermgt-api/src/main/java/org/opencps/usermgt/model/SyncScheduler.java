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
 * The extended model interface for the SyncScheduler service. Represents a row in the &quot;opencps_sync_scheduler&quot; database table, with each column mapped to a property of this class.
 *
 * @author khoavu
 * @see SyncSchedulerModel
 * @see org.opencps.usermgt.model.impl.SyncSchedulerImpl
 * @see org.opencps.usermgt.model.impl.SyncSchedulerModelImpl
 * @generated
 */
@ImplementationClassName("org.opencps.usermgt.model.impl.SyncSchedulerImpl")
@ProviderType
public interface SyncScheduler extends SyncSchedulerModel, PersistedModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to {@link org.opencps.usermgt.model.impl.SyncSchedulerImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public static final Accessor<SyncScheduler, Long> SYNC_SCHEDULER_ID_ACCESSOR =
		new Accessor<SyncScheduler, Long>() {
			@Override
			public Long get(SyncScheduler syncScheduler) {
				return syncScheduler.getSyncSchedulerId();
			}

			@Override
			public Class<Long> getAttributeClass() {
				return Long.class;
			}

			@Override
			public Class<SyncScheduler> getTypeClass() {
				return SyncScheduler.class;
			}
		};
}