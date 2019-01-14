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

package org.opencps.statistic.model;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.annotation.ImplementationClassName;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.util.Accessor;

/**
 * The extended model interface for the OpencpsPersonStatistic service. Represents a row in the &quot;opencps_person_statistic&quot; database table, with each column mapped to a property of this class.
 *
 * @author khoavu
 * @see OpencpsPersonStatisticModel
 * @see org.opencps.statistic.model.impl.OpencpsPersonStatisticImpl
 * @see org.opencps.statistic.model.impl.OpencpsPersonStatisticModelImpl
 * @generated
 */
@ImplementationClassName("org.opencps.statistic.model.impl.OpencpsPersonStatisticImpl")
@ProviderType
public interface OpencpsPersonStatistic extends OpencpsPersonStatisticModel,
	PersistedModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to {@link org.opencps.statistic.model.impl.OpencpsPersonStatisticImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public static final Accessor<OpencpsPersonStatistic, Long> PERSON_STATISTIC_ID_ACCESSOR =
		new Accessor<OpencpsPersonStatistic, Long>() {
			@Override
			public Long get(OpencpsPersonStatistic opencpsPersonStatistic) {
				return opencpsPersonStatistic.getPersonStatisticId();
			}

			@Override
			public Class<Long> getAttributeClass() {
				return Long.class;
			}

			@Override
			public Class<OpencpsPersonStatistic> getTypeClass() {
				return OpencpsPersonStatistic.class;
			}
		};
}