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

package org.opencps.backend.systemlogmgt.model;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.annotation.ImplementationClassName;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.util.Accessor;

/**
 * The extended model interface for the SystemLog service. Represents a row in the &quot;opencps_systemlog&quot; database table, with each column mapped to a property of this class.
 *
 * @author Brian Wing Shun Chan
 * @see SystemLogModel
 * @see org.opencps.backend.systemlogmgt.model.impl.SystemLogImpl
 * @see org.opencps.backend.systemlogmgt.model.impl.SystemLogModelImpl
 * @generated
 */
@ImplementationClassName("org.opencps.backend.systemlogmgt.model.impl.SystemLogImpl")
@ProviderType
public interface SystemLog extends SystemLogModel, PersistedModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to {@link org.opencps.backend.systemlogmgt.model.impl.SystemLogImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public static final Accessor<SystemLog, Long> LOG_ID_ACCESSOR = new Accessor<SystemLog, Long>() {
			@Override
			public Long get(SystemLog systemLog) {
				return systemLog.getLogId();
			}

			@Override
			public Class<Long> getAttributeClass() {
				return Long.class;
			}

			@Override
			public Class<SystemLog> getTypeClass() {
				return SystemLog.class;
			}
		};
}