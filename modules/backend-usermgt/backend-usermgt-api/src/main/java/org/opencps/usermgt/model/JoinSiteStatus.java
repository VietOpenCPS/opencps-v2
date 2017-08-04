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
 * The extended model interface for the JoinSiteStatus service. Represents a row in the &quot;m_join_site_status&quot; database table, with each column mapped to a property of this class.
 *
 * @author Binhth
 * @see JoinSiteStatusModel
 * @see org.mobilink.backend.usermgt.model.impl.JoinSiteStatusImpl
 * @see org.mobilink.backend.usermgt.model.impl.JoinSiteStatusModelImpl
 * @generated
 */
@ImplementationClassName("org.mobilink.backend.usermgt.model.impl.JoinSiteStatusImpl")
@ProviderType
public interface JoinSiteStatus extends JoinSiteStatusModel, PersistedModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to {@link org.mobilink.backend.usermgt.model.impl.JoinSiteStatusImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public static final Accessor<JoinSiteStatus, Long> JOIN_SITE_STATUS_ID_ACCESSOR =
		new Accessor<JoinSiteStatus, Long>() {
			@Override
			public Long get(JoinSiteStatus joinSiteStatus) {
				return joinSiteStatus.getJoinSiteStatusId();
			}

			@Override
			public Class<Long> getAttributeClass() {
				return Long.class;
			}

			@Override
			public Class<JoinSiteStatus> getTypeClass() {
				return JoinSiteStatus.class;
			}
		};
}