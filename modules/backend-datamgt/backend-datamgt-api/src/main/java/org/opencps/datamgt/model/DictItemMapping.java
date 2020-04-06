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

package org.opencps.datamgt.model;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.annotation.ImplementationClassName;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.util.Accessor;

/**
 * The extended model interface for the DictItemMapping service. Represents a row in the &quot;opencps_dictItemmapping&quot; database table, with each column mapped to a property of this class.
 *
 * @author khoavu
 * @see DictItemMappingModel
 * @see org.opencps.datamgt.model.impl.DictItemMappingImpl
 * @see org.opencps.datamgt.model.impl.DictItemMappingModelImpl
 * @generated
 */
@ImplementationClassName("org.opencps.datamgt.model.impl.DictItemMappingImpl")
@ProviderType
public interface DictItemMapping extends DictItemMappingModel, PersistedModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to {@link org.opencps.datamgt.model.impl.DictItemMappingImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public static final Accessor<DictItemMapping, Long> MAPPING_ID_ACCESSOR = new Accessor<DictItemMapping, Long>() {
			@Override
			public Long get(DictItemMapping dictItemMapping) {
				return dictItemMapping.getMappingId();
			}

			@Override
			public Class<Long> getAttributeClass() {
				return Long.class;
			}

			@Override
			public Class<DictItemMapping> getTypeClass() {
				return DictItemMapping.class;
			}
		};
}