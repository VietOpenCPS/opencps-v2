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

package org.opencps.backend.processmgt.model;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.annotation.ImplementationClassName;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.util.Accessor;

/**
 * The extended model interface for the StepAllowance service. Represents a row in the &quot;opencps_stepallowance&quot; database table, with each column mapped to a property of this class.
 *
 * @author khoavu
 * @see StepAllowanceModel
 * @see org.opencps.backend.processmgt.model.impl.StepAllowanceImpl
 * @see org.opencps.backend.processmgt.model.impl.StepAllowanceModelImpl
 * @generated
 */
@ImplementationClassName("org.opencps.backend.processmgt.model.impl.StepAllowanceImpl")
@ProviderType
public interface StepAllowance extends StepAllowanceModel, PersistedModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to {@link org.opencps.backend.processmgt.model.impl.StepAllowanceImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public static final Accessor<StepAllowance, Long> PROCESS_STEP_ID_ACCESSOR = new Accessor<StepAllowance, Long>() {
			@Override
			public Long get(StepAllowance stepAllowance) {
				return stepAllowance.getProcessStepId();
			}

			@Override
			public Class<Long> getAttributeClass() {
				return Long.class;
			}

			@Override
			public Class<StepAllowance> getTypeClass() {
				return StepAllowance.class;
			}
		};

	public static final Accessor<StepAllowance, Long> ROLE_ID_ACCESSOR = new Accessor<StepAllowance, Long>() {
			@Override
			public Long get(StepAllowance stepAllowance) {
				return stepAllowance.getRoleId();
			}

			@Override
			public Class<Long> getAttributeClass() {
				return Long.class;
			}

			@Override
			public Class<StepAllowance> getTypeClass() {
				return StepAllowance.class;
			}
		};
}