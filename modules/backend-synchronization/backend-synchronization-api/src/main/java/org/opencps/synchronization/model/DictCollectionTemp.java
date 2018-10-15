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
 * The extended model interface for the DictCollectionTemp service. Represents a row in the &quot;opencps_dictcollection_temp&quot; database table, with each column mapped to a property of this class.
 *
 * @author trungdk
 * @see DictCollectionTempModel
 * @see org.opencps.synchronization.model.impl.DictCollectionTempImpl
 * @see org.opencps.synchronization.model.impl.DictCollectionTempModelImpl
 * @generated
 */
@ImplementationClassName("org.opencps.synchronization.model.impl.DictCollectionTempImpl")
@ProviderType
public interface DictCollectionTemp extends DictCollectionTempModel,
	PersistedModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to {@link org.opencps.synchronization.model.impl.DictCollectionTempImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public static final Accessor<DictCollectionTemp, Long> DICT_COLLECTION_ID_ACCESSOR =
		new Accessor<DictCollectionTemp, Long>() {
			@Override
			public Long get(DictCollectionTemp dictCollectionTemp) {
				return dictCollectionTemp.getDictCollectionId();
			}

			@Override
			public Class<Long> getAttributeClass() {
				return Long.class;
			}

			@Override
			public Class<DictCollectionTemp> getTypeClass() {
				return DictCollectionTemp.class;
			}
		};
}