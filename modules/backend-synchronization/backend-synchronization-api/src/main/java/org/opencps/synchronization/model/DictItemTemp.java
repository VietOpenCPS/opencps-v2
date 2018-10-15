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

package org.opencps.synchronization.model;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.annotation.ImplementationClassName;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.util.Accessor;

/**
 * The extended model interface for the DictItemTemp service. Represents a row in the &quot;opencps_dictitem_temp&quot; database table, with each column mapped to a property of this class.
 *
 * @author trungdk
 * @see DictItemTempModel
 * @see org.opencps.synchronization.model.impl.DictItemTempImpl
 * @see org.opencps.synchronization.model.impl.DictItemTempModelImpl
 * @generated
 */
@ImplementationClassName("org.opencps.synchronization.model.impl.DictItemTempImpl")
@ProviderType
public interface DictItemTemp extends DictItemTempModel, PersistedModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to {@link org.opencps.synchronization.model.impl.DictItemTempImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public static final Accessor<DictItemTemp, Long> DICT_ITEM_ID_ACCESSOR = new Accessor<DictItemTemp, Long>() {
			@Override
			public Long get(DictItemTemp dictItemTemp) {
				return dictItemTemp.getDictItemId();
			}

			@Override
			public Class<Long> getAttributeClass() {
				return Long.class;
			}

			@Override
			public Class<DictItemTemp> getTypeClass() {
				return DictItemTemp.class;
			}
		};
}