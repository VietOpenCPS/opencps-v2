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
 * The extended model interface for the StepConfig service. Represents a row in the &quot;opencps_stepconfig&quot; database table, with each column mapped to a property of this class.
 *
 * @author huymq
 * @see StepConfigModel
 * @see org.opencps.dossiermgt.model.impl.StepConfigImpl
 * @see org.opencps.dossiermgt.model.impl.StepConfigModelImpl
 * @generated
 */
@ImplementationClassName("org.opencps.dossiermgt.model.impl.StepConfigImpl")
@ProviderType
public interface StepConfig extends StepConfigModel, PersistedModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to {@link org.opencps.dossiermgt.model.impl.StepConfigImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public static final Accessor<StepConfig, Long> STEP_CONFIG_ID_ACCESSOR = new Accessor<StepConfig, Long>() {
			@Override
			public Long get(StepConfig stepConfig) {
				return stepConfig.getStepConfigId();
			}

			@Override
			public Class<Long> getAttributeClass() {
				return Long.class;
			}

			@Override
			public Class<StepConfig> getTypeClass() {
				return StepConfig.class;
			}
		};
}