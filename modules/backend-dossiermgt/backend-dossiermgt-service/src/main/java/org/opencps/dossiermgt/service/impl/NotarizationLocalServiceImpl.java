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

package org.opencps.dossiermgt.service.impl;

import com.liferay.portal.kernel.search.Indexable;
import com.liferay.portal.kernel.search.IndexableType;
import com.liferay.portal.kernel.service.ServiceContext;

import java.util.Date;
import java.util.List;

import org.opencps.dossiermgt.exception.NoSuchNotarizationException;
import org.opencps.dossiermgt.model.Notarization;
import org.opencps.dossiermgt.service.base.NotarizationLocalServiceBaseImpl;

/**
 * The implementation of the notarization local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link org.opencps.dossiermgt.service.NotarizationLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author huymq
 * @see NotarizationLocalServiceBaseImpl
 * @see org.opencps.dossiermgt.service.NotarizationLocalServiceUtil
 */
public class NotarizationLocalServiceImpl
	extends NotarizationLocalServiceBaseImpl {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this class directly. Always use {@link org.opencps.dossiermgt.service.NotarizationLocalServiceUtil} to access the notarization local service.
	 */
	@Indexable(type = IndexableType.REINDEX)
	public Notarization addNotarization(
		long groupId, long dossierId, String fileName,
		int totalRecord, int totalPage, int totalCopy,
		long totalFee, long notarizationNo, int notarizationYear,
		Date notarizationDate, String signerName, String signerPosition,
		String statusCode,
		ServiceContext serviceContext) {

		long userId = serviceContext.getUserId();
		Date now = new Date();

		long notarizationId =
			counterLocalService.increment(Notarization.class.getName());

		Notarization object = notarizationPersistence.create(notarizationId);

		/// Add audit fields
		object.setGroupId(groupId);
		object.setCreateDate(now);
		object.setModifiedDate(now);
		object.setUserId(userId);

		object.setDossierId(dossierId);
		object.setFileName(fileName);
		object.setTotalRecord(totalRecord);
		object.setTotalPage(totalPage);
		object.setTotalCopy(totalCopy);
		object.setTotalFee(totalFee);
		object.setNotarizationNo(notarizationNo);
		object.setNotarizationYear(notarizationYear);
		object.setNotarizationDate(notarizationDate);
		object.setSignerName(signerName);
		object.setSignerPosition(signerPosition);
		object.setStatusCode(statusCode);

		return notarizationPersistence.update(object);
	}	

	@Indexable(type = IndexableType.REINDEX)
	public Notarization updateNotarization(
		long groupId, long notarizationId, long dossierId, String fileName,
		int totalRecord, int totalPage, int totalCopy,
		long totalFee, long notarizationNo, int notarizationYear,
		Date notarizationDate, String signerName, String signerPosition,
		String statusCode,
		ServiceContext serviceContext) {

		long userId = serviceContext.getUserId();
		Date now = new Date();

		Notarization object = notarizationPersistence.fetchByPrimaryKey(notarizationId);

		/// Add audit fields
		object.setGroupId(groupId);
		object.setModifiedDate(now);
		object.setUserId(userId);

		object.setDossierId(dossierId);
		object.setFileName(fileName);
		object.setTotalRecord(totalRecord);
		object.setTotalPage(totalPage);
		object.setTotalCopy(totalCopy);
		object.setTotalFee(totalFee);
		object.setNotarizationNo(notarizationNo);
		object.setNotarizationYear(notarizationYear);
		object.setNotarizationDate(notarizationDate);
		object.setSignerName(signerName);
		object.setSignerPosition(signerPosition);
		object.setStatusCode(statusCode);

		return notarizationPersistence.update(object);
	}	

	@Indexable(type = IndexableType.DELETE)
	public void removeNotarization(long notarizationId) throws NoSuchNotarizationException {
		notarizationPersistence.remove(notarizationId);
	}
	
	public List<Notarization> findByG_DID(long groupId, long dossierId) {
		return notarizationPersistence.findByF_G_DID(groupId, dossierId);
	}
	
	public List<Notarization> findByG_DID(long groupId, long dossierId, int start, int end) {
		return notarizationPersistence.findByF_G_DID(groupId, dossierId, start, end);
	}
	
	public int countByG_DID(long groupId, long dossierId) {
		return notarizationPersistence.countByF_G_DID(groupId, dossierId);
	}
}