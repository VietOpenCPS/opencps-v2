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

package org.opencps.usermgt.service.persistence.impl;

import com.liferay.portal.kernel.bean.BeanReference;
import com.liferay.portal.kernel.service.persistence.impl.BasePersistenceImpl;

import org.opencps.usermgt.model.Question;
import org.opencps.usermgt.service.persistence.QuestionPersistence;

/**
 * @author khoavu
 * @generated
 */
public class QuestionFinderBaseImpl extends BasePersistenceImpl<Question> {
	public QuestionFinderBaseImpl() {
		setModelClass(Question.class);
	}

	/**
	 * Returns the question persistence.
	 *
	 * @return the question persistence
	 */
	public QuestionPersistence getQuestionPersistence() {
		return questionPersistence;
	}

	/**
	 * Sets the question persistence.
	 *
	 * @param questionPersistence the question persistence
	 */
	public void setQuestionPersistence(QuestionPersistence questionPersistence) {
		this.questionPersistence = questionPersistence;
	}

	@BeanReference(type = QuestionPersistence.class)
	protected QuestionPersistence questionPersistence;
}