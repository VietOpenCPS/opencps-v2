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
 * The extended model interface for the Comment service. Represents a row in the &quot;opencps_comment&quot; database table, with each column mapped to a property of this class.
 *
 * @author sondt
 * @see CommentModel
 * @see backend.feedback.model.impl.CommentImpl
 * @see backend.feedback.model.impl.CommentModelImpl
 * @generated
 */
@ImplementationClassName("backend.feedback.model.impl.CommentImpl")
@ProviderType
public interface Comment extends CommentModel, PersistedModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to {@link backend.feedback.model.impl.CommentImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public static final Accessor<Comment, Long> COMMENT_ID_ACCESSOR = new Accessor<Comment, Long>() {
			@Override
			public Long get(Comment comment) {
				return comment.getCommentId();
			}

			@Override
			public Class<Long> getAttributeClass() {
				return Long.class;
			}

			@Override
			public Class<Comment> getTypeClass() {
				return Comment.class;
			}
		};
}