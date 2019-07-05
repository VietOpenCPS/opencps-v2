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

package org.opencps.communication.model;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.annotation.ImplementationClassName;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.util.Accessor;

/**
 * The extended model interface for the Notificationtemplate service. Represents a row in the &quot;opencps_notificationtemplate&quot; database table, with each column mapped to a property of this class.
 *
 * @author khoavd
 * @see NotificationtemplateModel
 * @see org.opencps.communication.model.impl.NotificationtemplateImpl
 * @see org.opencps.communication.model.impl.NotificationtemplateModelImpl
 * @generated
 */
@ImplementationClassName("org.opencps.communication.model.impl.NotificationtemplateImpl")
@ProviderType
public interface Notificationtemplate extends NotificationtemplateModel,
	PersistedModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to {@link org.opencps.communication.model.impl.NotificationtemplateImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public static final Accessor<Notificationtemplate, Long> NOTIFICATION_TEMPLATE_ID_ACCESSOR =
		new Accessor<Notificationtemplate, Long>() {
			@Override
			public Long get(Notificationtemplate notificationtemplate) {
				return notificationtemplate.getNotificationTemplateId();
			}

			@Override
			public Class<Long> getAttributeClass() {
				return Long.class;
			}

			@Override
			public Class<Notificationtemplate> getTypeClass() {
				return Notificationtemplate.class;
			}
		};
}