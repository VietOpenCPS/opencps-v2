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
 * The extended model interface for the ServiceFileTemplate service. Represents a row in the &quot;opencps_services_filetemplates&quot; database table, with each column mapped to a property of this class.
 *
 * @author huymq
 * @see ServiceFileTemplateModel
 * @see org.opencps.dossiermgt.model.impl.ServiceFileTemplateImpl
 * @see org.opencps.dossiermgt.model.impl.ServiceFileTemplateModelImpl
 * @generated
 */
@ImplementationClassName("org.opencps.dossiermgt.model.impl.ServiceFileTemplateImpl")
@ProviderType
public interface ServiceFileTemplate extends ServiceFileTemplateModel,
	PersistedModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to {@link org.opencps.dossiermgt.model.impl.ServiceFileTemplateImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public static final Accessor<ServiceFileTemplate, Long> SERVICE_INFO_ID_ACCESSOR =
		new Accessor<ServiceFileTemplate, Long>() {
			@Override
			public Long get(ServiceFileTemplate serviceFileTemplate) {
				return serviceFileTemplate.getServiceInfoId();
			}

			@Override
			public Class<Long> getAttributeClass() {
				return Long.class;
			}

			@Override
			public Class<ServiceFileTemplate> getTypeClass() {
				return ServiceFileTemplate.class;
			}
		};

	public static final Accessor<ServiceFileTemplate, String> FILE_TEMPLATE_NO_ACCESSOR =
		new Accessor<ServiceFileTemplate, String>() {
			@Override
			public String get(ServiceFileTemplate serviceFileTemplate) {
				return serviceFileTemplate.getFileTemplateNo();
			}

			@Override
			public Class<String> getAttributeClass() {
				return String.class;
			}

			@Override
			public Class<ServiceFileTemplate> getTypeClass() {
				return ServiceFileTemplate.class;
			}
		};
}