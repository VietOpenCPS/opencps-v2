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

package org.mobilink.backend.datamgt.model;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.annotation.ImplementationClassName;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.util.Accessor;

/**
 * The extended model interface for the WorkspaceUser service. Represents a row in the &quot;m_workspaceuser&quot; database table, with each column mapped to a property of this class.
 *
 * @author Binhth
 * @see WorkspaceUserModel
 * @see org.mobilink.backend.datamgt.model.impl.WorkspaceUserImpl
 * @see org.mobilink.backend.datamgt.model.impl.WorkspaceUserModelImpl
 * @generated
 */
@ImplementationClassName("org.mobilink.backend.datamgt.model.impl.WorkspaceUserImpl")
@ProviderType
public interface WorkspaceUser extends WorkspaceUserModel, PersistedModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to {@link org.mobilink.backend.datamgt.model.impl.WorkspaceUserImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public static final Accessor<WorkspaceUser, Long> WORKSPACE_USER_ID_ACCESSOR =
		new Accessor<WorkspaceUser, Long>() {
			@Override
			public Long get(WorkspaceUser workspaceUser) {
				return workspaceUser.getWorkspaceUserId();
			}

			@Override
			public Class<Long> getAttributeClass() {
				return Long.class;
			}

			@Override
			public Class<WorkspaceUser> getTypeClass() {
				return WorkspaceUser.class;
			}
		};
}