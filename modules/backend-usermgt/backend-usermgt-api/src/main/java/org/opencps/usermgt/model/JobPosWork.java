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
 * The extended model interface for the JobPosWork service. Represents a row in the &quot;m_jobposwork&quot; database table, with each column mapped to a property of this class.
 *
 * @author khoavu
 * @see JobPosWorkModel
 * @see org.opencps.usermgt.model.impl.JobPosWorkImpl
 * @see org.opencps.usermgt.model.impl.JobPosWorkModelImpl
 * @generated
 */
@ImplementationClassName("org.opencps.usermgt.model.impl.JobPosWorkImpl")
@ProviderType
public interface JobPosWork extends JobPosWorkModel, PersistedModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to {@link org.opencps.usermgt.model.impl.JobPosWorkImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public static final Accessor<JobPosWork, Long> JOB_POS_WORK_ID_ACCESSOR = new Accessor<JobPosWork, Long>() {
			@Override
			public Long get(JobPosWork jobPosWork) {
				return jobPosWork.getJobPosWorkId();
			}

			@Override
			public Class<Long> getAttributeClass() {
				return Long.class;
			}

			@Override
			public Class<JobPosWork> getTypeClass() {
				return JobPosWork.class;
			}
		};
}