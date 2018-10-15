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
 * The extended model interface for the WorkingUnit service. Represents a row in the &quot;opencps_workingunit&quot; database table, with each column mapped to a property of this class.
 *
 * @author khoavu
 * @see WorkingUnitModel
 * @see org.opencps.usermgt.model.impl.WorkingUnitImpl
 * @see org.opencps.usermgt.model.impl.WorkingUnitModelImpl
 * @generated
 */
@ImplementationClassName("org.opencps.usermgt.model.impl.WorkingUnitImpl")
@ProviderType
public interface WorkingUnit extends WorkingUnitModel, PersistedModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to {@link org.opencps.usermgt.model.impl.WorkingUnitImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public static final Accessor<WorkingUnit, Long> WORKING_UNIT_ID_ACCESSOR = new Accessor<WorkingUnit, Long>() {
			@Override
			public Long get(WorkingUnit workingUnit) {
				return workingUnit.getWorkingUnitId();
			}

			@Override
			public Class<Long> getAttributeClass() {
				return Long.class;
			}

			@Override
			public Class<WorkingUnit> getTypeClass() {
				return WorkingUnit.class;
			}
		};
}