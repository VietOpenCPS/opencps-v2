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
 * The extended model interface for the PaymentFile service. Represents a row in the &quot;opencps_paymentfile&quot; database table, with each column mapped to a property of this class.
 *
 * @author huymq
 * @see PaymentFileModel
 * @see org.opencps.dossiermgt.model.impl.PaymentFileImpl
 * @see org.opencps.dossiermgt.model.impl.PaymentFileModelImpl
 * @generated
 */
@ImplementationClassName("org.opencps.dossiermgt.model.impl.PaymentFileImpl")
@ProviderType
public interface PaymentFile extends PaymentFileModel, PersistedModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to {@link org.opencps.dossiermgt.model.impl.PaymentFileImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public static final Accessor<PaymentFile, Long> PAYMENT_FILE_ID_ACCESSOR = new Accessor<PaymentFile, Long>() {
			@Override
			public Long get(PaymentFile paymentFile) {
				return paymentFile.getPaymentFileId();
			}

			@Override
			public Class<Long> getAttributeClass() {
				return Long.class;
			}

			@Override
			public Class<PaymentFile> getTypeClass() {
				return PaymentFile.class;
			}
		};
}