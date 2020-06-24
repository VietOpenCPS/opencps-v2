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
 * The extended model interface for the ApdungDVC service. Represents a row in the &quot;opencps_apdungDVC&quot; database table, with each column mapped to a property of this class.
 *
 * @author Brian Wing Shun Chan
 * @see ApdungDVCModel
 * @see pay.gate.integration.dvc.model.impl.ApdungDVCImpl
 * @see pay.gate.integration.dvc.model.impl.ApdungDVCModelImpl
 * @generated
 */
@ImplementationClassName("pay.gate.integration.dvc.model.impl.ApdungDVCImpl")
@ProviderType
public interface ApdungDVC extends ApdungDVCModel, PersistedModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to {@link pay.gate.integration.dvc.model.impl.ApdungDVCImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public static final Accessor<ApdungDVC, Long> APDUNG_DVC_ID_ACCESSOR = new Accessor<ApdungDVC, Long>() {
			@Override
			public Long get(ApdungDVC apdungDVC) {
				return apdungDVC.getApdungDVCId();
			}

			@Override
			public Class<Long> getAttributeClass() {
				return Long.class;
			}

			@Override
			public Class<ApdungDVC> getTypeClass() {
				return ApdungDVC.class;
			}
		};
}