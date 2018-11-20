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

package org.opencps.sms.model;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.annotation.ImplementationClassName;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.util.Accessor;

/**
 * The extended model interface for the SMSLookUpQueue service. Represents a row in the &quot;opencps_smsqueue&quot; database table, with each column mapped to a property of this class.
 *
 * @author khoa
 * @see SMSLookUpQueueModel
 * @see org.opencps.sms.model.impl.SMSLookUpQueueImpl
 * @see org.opencps.sms.model.impl.SMSLookUpQueueModelImpl
 * @generated
 */
@ImplementationClassName("org.opencps.sms.model.impl.SMSLookUpQueueImpl")
@ProviderType
public interface SMSLookUpQueue extends SMSLookUpQueueModel, PersistedModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to {@link org.opencps.sms.model.impl.SMSLookUpQueueImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public static final Accessor<SMSLookUpQueue, Long> QUEUE_ID_ACCESSOR = new Accessor<SMSLookUpQueue, Long>() {
			@Override
			public Long get(SMSLookUpQueue smsLookUpQueue) {
				return smsLookUpQueue.getQueueId();
			}

			@Override
			public Class<Long> getAttributeClass() {
				return Long.class;
			}

			@Override
			public Class<SMSLookUpQueue> getTypeClass() {
				return SMSLookUpQueue.class;
			}
		};
}