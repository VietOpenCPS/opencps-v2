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
 * The extended model interface for the HmacAuthen service. Represents a row in the &quot;opencps_hmacauth&quot; database table, with each column mapped to a property of this class.
 *
 * @author khoavu
 * @see HmacAuthenModel
 * @see org.opencps.usermgt.model.impl.HmacAuthenImpl
 * @see org.opencps.usermgt.model.impl.HmacAuthenModelImpl
 * @generated
 */
@ImplementationClassName("org.opencps.usermgt.model.impl.HmacAuthenImpl")
@ProviderType
public interface HmacAuthen extends HmacAuthenModel, PersistedModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to {@link org.opencps.usermgt.model.impl.HmacAuthenImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public static final Accessor<HmacAuthen, Long> HMAC_AUTH_ID_ACCESSOR = new Accessor<HmacAuthen, Long>() {
			@Override
			public Long get(HmacAuthen hmacAuthen) {
				return hmacAuthen.getHmacAuthId();
			}

			@Override
			public Class<Long> getAttributeClass() {
				return Long.class;
			}

			@Override
			public Class<HmacAuthen> getTypeClass() {
				return HmacAuthen.class;
			}
		};
}