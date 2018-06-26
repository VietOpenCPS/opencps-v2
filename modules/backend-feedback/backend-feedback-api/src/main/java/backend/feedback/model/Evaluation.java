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

package backend.feedback.model;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.annotation.ImplementationClassName;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.util.Accessor;

/**
 * The extended model interface for the Evaluation service. Represents a row in the &quot;opencps_evaluation&quot; database table, with each column mapped to a property of this class.
 *
 * @author sondt
 * @see EvaluationModel
 * @see backend.feedback.model.impl.EvaluationImpl
 * @see backend.feedback.model.impl.EvaluationModelImpl
 * @generated
 */
@ImplementationClassName("backend.feedback.model.impl.EvaluationImpl")
@ProviderType
public interface Evaluation extends EvaluationModel, PersistedModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to {@link backend.feedback.model.impl.EvaluationImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public static final Accessor<Evaluation, Long> EVALUATION_ID_ACCESSOR = new Accessor<Evaluation, Long>() {
			@Override
			public Long get(Evaluation evaluation) {
				return evaluation.getEvaluationId();
			}

			@Override
			public Class<Long> getAttributeClass() {
				return Long.class;
			}

			@Override
			public Class<Evaluation> getTypeClass() {
				return Evaluation.class;
			}
		};
}