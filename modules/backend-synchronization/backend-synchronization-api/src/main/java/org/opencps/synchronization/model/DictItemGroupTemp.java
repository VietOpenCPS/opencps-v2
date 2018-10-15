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
 * The extended model interface for the DictItemGroupTemp service. Represents a row in the &quot;opencps_dictitemgroup_temp&quot; database table, with each column mapped to a property of this class.
 *
 * @author trungdk
 * @see DictItemGroupTempModel
 * @see org.opencps.synchronization.model.impl.DictItemGroupTempImpl
 * @see org.opencps.synchronization.model.impl.DictItemGroupTempModelImpl
 * @generated
 */
@ImplementationClassName("org.opencps.synchronization.model.impl.DictItemGroupTempImpl")
@ProviderType
public interface DictItemGroupTemp extends DictItemGroupTempModel, PersistedModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to {@link org.opencps.synchronization.model.impl.DictItemGroupTempImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public static final Accessor<DictItemGroupTemp, Long> DICT_ITEM_GROUP_ID_ACCESSOR =
		new Accessor<DictItemGroupTemp, Long>() {
			@Override
			public Long get(DictItemGroupTemp dictItemGroupTemp) {
				return dictItemGroupTemp.getDictItemGroupId();
			}

			@Override
			public Class<Long> getAttributeClass() {
				return Long.class;
			}

			@Override
			public Class<DictItemGroupTemp> getTypeClass() {
				return DictItemGroupTemp.class;
			}
		};
}