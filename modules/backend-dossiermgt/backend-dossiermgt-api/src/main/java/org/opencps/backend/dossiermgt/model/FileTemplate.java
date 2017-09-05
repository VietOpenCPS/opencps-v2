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

package org.opencps.backend.dossiermgt.model;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.annotation.ImplementationClassName;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.util.Accessor;

/**
 * The extended model interface for the FileTemplate service. Represents a row in the &quot;opencps_filetemplate&quot; database table, with each column mapped to a property of this class.
 *
 * @author huymq
 * @see FileTemplateModel
 * @see org.opencps.backend.dossiermgt.model.impl.FileTemplateImpl
 * @see org.opencps.backend.dossiermgt.model.impl.FileTemplateModelImpl
 * @generated
 */
@ImplementationClassName("org.opencps.backend.dossiermgt.model.impl.FileTemplateImpl")
@ProviderType
public interface FileTemplate extends FileTemplateModel, PersistedModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to {@link org.opencps.backend.dossiermgt.model.impl.FileTemplateImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public static final Accessor<FileTemplate, Long> FILE_TEMPLATE_ID_ACCESSOR = new Accessor<FileTemplate, Long>() {
			@Override
			public Long get(FileTemplate fileTemplate) {
				return fileTemplate.getFileTemplateId();
			}

			@Override
			public Class<Long> getAttributeClass() {
				return Long.class;
			}

			@Override
			public Class<FileTemplate> getTypeClass() {
				return FileTemplate.class;
			}
		};
}