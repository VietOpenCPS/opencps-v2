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

package pay.gate.integration.dvc.model;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.annotation.ImplementationClassName;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.util.Accessor;

/**
 * The extended model interface for the PhiLePhi service. Represents a row in the &quot;opencps_philephi&quot; database table, with each column mapped to a property of this class.
 *
 * @author Brian Wing Shun Chan
 * @see PhiLePhiModel
 * @see pay.gate.integration.dvc.model.impl.PhiLePhiImpl
 * @see pay.gate.integration.dvc.model.impl.PhiLePhiModelImpl
 * @generated
 */
@ImplementationClassName("pay.gate.integration.dvc.model.impl.PhiLePhiImpl")
@ProviderType
public interface PhiLePhi extends PhiLePhiModel, PersistedModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to {@link pay.gate.integration.dvc.model.impl.PhiLePhiImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public static final Accessor<PhiLePhi, Long> PHI_LE_PHI_ID_ACCESSOR = new Accessor<PhiLePhi, Long>() {
			@Override
			public Long get(PhiLePhi phiLePhi) {
				return phiLePhi.getPhiLePhiId();
			}

			@Override
			public Class<Long> getAttributeClass() {
				return Long.class;
			}

			@Override
			public Class<PhiLePhi> getTypeClass() {
				return PhiLePhi.class;
			}
		};
}