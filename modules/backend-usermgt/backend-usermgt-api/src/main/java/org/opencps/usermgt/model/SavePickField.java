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
 * The extended model interface for the SavePickField service. Represents a row in the &quot;opencps_save_pick_field&quot; database table, with each column mapped to a property of this class.
 *
 * @author khoavu
 * @see SavePickFieldModel
 * @see org.opencps.usermgt.model.impl.SavePickFieldImpl
 * @see org.opencps.usermgt.model.impl.SavePickFieldModelImpl
 * @generated
 */
@ImplementationClassName("org.opencps.usermgt.model.impl.SavePickFieldImpl")
@ProviderType
public interface SavePickField extends SavePickFieldModel, PersistedModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to {@link org.opencps.usermgt.model.impl.SavePickFieldImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public static final Accessor<SavePickField, Long> FIELD_PICK_ID_ACCESSOR = new Accessor<SavePickField, Long>() {
			@Override
			public Long get(SavePickField savePickField) {
				return savePickField.getFieldPickId();
			}

			@Override
			public Class<Long> getAttributeClass() {
				return Long.class;
			}

			@Override
			public Class<SavePickField> getTypeClass() {
				return SavePickField.class;
			}
		};
}