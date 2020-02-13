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

package org.opencps.dossiermgt.service.persistence.impl;

import com.liferay.portal.kernel.bean.BeanReference;
import com.liferay.portal.kernel.service.persistence.impl.BasePersistenceImpl;

import org.opencps.dossiermgt.model.Notarization;
import org.opencps.dossiermgt.service.persistence.NotarizationPersistence;

/**
 * @author huymq
 * @generated
 */
public class NotarizationFinderBaseImpl extends BasePersistenceImpl<Notarization> {
	public NotarizationFinderBaseImpl() {
		setModelClass(Notarization.class);
	}

	/**
	 * Returns the notarization persistence.
	 *
	 * @return the notarization persistence
	 */
	public NotarizationPersistence getNotarizationPersistence() {
		return notarizationPersistence;
	}

	/**
	 * Sets the notarization persistence.
	 *
	 * @param notarizationPersistence the notarization persistence
	 */
	public void setNotarizationPersistence(
		NotarizationPersistence notarizationPersistence) {
		this.notarizationPersistence = notarizationPersistence;
	}

	@BeanReference(type = NotarizationPersistence.class)
	protected NotarizationPersistence notarizationPersistence;
}