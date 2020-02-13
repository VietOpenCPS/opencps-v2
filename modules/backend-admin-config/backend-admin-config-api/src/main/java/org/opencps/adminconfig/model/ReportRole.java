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

package org.opencps.adminconfig.model;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.annotation.ImplementationClassName;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.util.Accessor;

/**
 * The extended model interface for the ReportRole service. Represents a row in the &quot;opencps_reportrole&quot; database table, with each column mapped to a property of this class.
 *
 * @author binhth
 * @see ReportRoleModel
 * @see org.opencps.adminconfig.model.impl.ReportRoleImpl
 * @see org.opencps.adminconfig.model.impl.ReportRoleModelImpl
 * @generated
 */
@ImplementationClassName("org.opencps.adminconfig.model.impl.ReportRoleImpl")
@ProviderType
public interface ReportRole extends ReportRoleModel, PersistedModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to {@link org.opencps.adminconfig.model.impl.ReportRoleImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public static final Accessor<ReportRole, Long> REPORT_ROLE_ID_ACCESSOR = new Accessor<ReportRole, Long>() {
			@Override
			public Long get(ReportRole reportRole) {
				return reportRole.getReportRoleId();
			}

			@Override
			public Class<Long> getAttributeClass() {
				return Long.class;
			}

			@Override
			public Class<ReportRole> getTypeClass() {
				return ReportRole.class;
			}
		};
}