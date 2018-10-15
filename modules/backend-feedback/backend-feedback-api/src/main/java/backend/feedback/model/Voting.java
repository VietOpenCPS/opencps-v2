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
 * The extended model interface for the Voting service. Represents a row in the &quot;opencps_voting&quot; database table, with each column mapped to a property of this class.
 *
 * @author sondt
 * @see VotingModel
 * @see backend.feedback.model.impl.VotingImpl
 * @see backend.feedback.model.impl.VotingModelImpl
 * @generated
 */
@ImplementationClassName("backend.feedback.model.impl.VotingImpl")
@ProviderType
public interface Voting extends VotingModel, PersistedModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to {@link backend.feedback.model.impl.VotingImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public static final Accessor<Voting, Long> VOTING_ID_ACCESSOR = new Accessor<Voting, Long>() {
			@Override
			public Long get(Voting voting) {
				return voting.getVotingId();
			}

			@Override
			public Class<Long> getAttributeClass() {
				return Long.class;
			}

			@Override
			public Class<Voting> getTypeClass() {
				return Voting.class;
			}
		};
}