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

package org.mobilink.backend.usermgt.model;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.annotation.ImplementationClassName;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.util.Accessor;

/**
 * The extended model interface for the EmployeeFile service. Represents a row in the &quot;m_employee_file&quot; database table, with each column mapped to a property of this class.
 *
 * @author Binhth
 * @see EmployeeFileModel
 * @see org.mobilink.backend.usermgt.model.impl.EmployeeFileImpl
 * @see org.mobilink.backend.usermgt.model.impl.EmployeeFileModelImpl
 * @generated
 */
@ImplementationClassName("org.mobilink.backend.usermgt.model.impl.EmployeeFileImpl")
@ProviderType
public interface EmployeeFile extends EmployeeFileModel, PersistedModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to {@link org.mobilink.backend.usermgt.model.impl.EmployeeFileImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public static final Accessor<EmployeeFile, Long> EMPLOYEE_FILE_ID_ACCESSOR = new Accessor<EmployeeFile, Long>() {
			@Override
			public Long get(EmployeeFile employeeFile) {
				return employeeFile.getEmployeeFileId();
			}

			@Override
			public Class<Long> getAttributeClass() {
				return Long.class;
			}

			@Override
			public Class<EmployeeFile> getTypeClass() {
				return EmployeeFile.class;
			}
		};
}