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

package org.opencps.dossiermgt.service.persistence;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.service.persistence.BasePersistence;

import org.opencps.dossiermgt.exception.NoSuchNotarizationException;
import org.opencps.dossiermgt.model.Notarization;

/**
 * The persistence interface for the notarization service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author huymq
 * @see org.opencps.dossiermgt.service.persistence.impl.NotarizationPersistenceImpl
 * @see NotarizationUtil
 * @generated
 */
@ProviderType
public interface NotarizationPersistence extends BasePersistence<Notarization> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link NotarizationUtil} to access the notarization persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Returns all the notarizations where groupId = &#63; and dossierId = &#63;.
	*
	* @param groupId the group ID
	* @param dossierId the dossier ID
	* @return the matching notarizations
	*/
	public java.util.List<Notarization> findByF_G_DID(long groupId,
		long dossierId);

	/**
	* Returns a range of all the notarizations where groupId = &#63; and dossierId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link NotarizationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param dossierId the dossier ID
	* @param start the lower bound of the range of notarizations
	* @param end the upper bound of the range of notarizations (not inclusive)
	* @return the range of matching notarizations
	*/
	public java.util.List<Notarization> findByF_G_DID(long groupId,
		long dossierId, int start, int end);

	/**
	* Returns an ordered range of all the notarizations where groupId = &#63; and dossierId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link NotarizationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param dossierId the dossier ID
	* @param start the lower bound of the range of notarizations
	* @param end the upper bound of the range of notarizations (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching notarizations
	*/
	public java.util.List<Notarization> findByF_G_DID(long groupId,
		long dossierId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Notarization> orderByComparator);

	/**
	* Returns an ordered range of all the notarizations where groupId = &#63; and dossierId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link NotarizationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param dossierId the dossier ID
	* @param start the lower bound of the range of notarizations
	* @param end the upper bound of the range of notarizations (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching notarizations
	*/
	public java.util.List<Notarization> findByF_G_DID(long groupId,
		long dossierId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Notarization> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first notarization in the ordered set where groupId = &#63; and dossierId = &#63;.
	*
	* @param groupId the group ID
	* @param dossierId the dossier ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching notarization
	* @throws NoSuchNotarizationException if a matching notarization could not be found
	*/
	public Notarization findByF_G_DID_First(long groupId, long dossierId,
		com.liferay.portal.kernel.util.OrderByComparator<Notarization> orderByComparator)
		throws NoSuchNotarizationException;

	/**
	* Returns the first notarization in the ordered set where groupId = &#63; and dossierId = &#63;.
	*
	* @param groupId the group ID
	* @param dossierId the dossier ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching notarization, or <code>null</code> if a matching notarization could not be found
	*/
	public Notarization fetchByF_G_DID_First(long groupId, long dossierId,
		com.liferay.portal.kernel.util.OrderByComparator<Notarization> orderByComparator);

	/**
	* Returns the last notarization in the ordered set where groupId = &#63; and dossierId = &#63;.
	*
	* @param groupId the group ID
	* @param dossierId the dossier ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching notarization
	* @throws NoSuchNotarizationException if a matching notarization could not be found
	*/
	public Notarization findByF_G_DID_Last(long groupId, long dossierId,
		com.liferay.portal.kernel.util.OrderByComparator<Notarization> orderByComparator)
		throws NoSuchNotarizationException;

	/**
	* Returns the last notarization in the ordered set where groupId = &#63; and dossierId = &#63;.
	*
	* @param groupId the group ID
	* @param dossierId the dossier ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching notarization, or <code>null</code> if a matching notarization could not be found
	*/
	public Notarization fetchByF_G_DID_Last(long groupId, long dossierId,
		com.liferay.portal.kernel.util.OrderByComparator<Notarization> orderByComparator);

	/**
	* Returns the notarizations before and after the current notarization in the ordered set where groupId = &#63; and dossierId = &#63;.
	*
	* @param notarizationId the primary key of the current notarization
	* @param groupId the group ID
	* @param dossierId the dossier ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next notarization
	* @throws NoSuchNotarizationException if a notarization with the primary key could not be found
	*/
	public Notarization[] findByF_G_DID_PrevAndNext(long notarizationId,
		long groupId, long dossierId,
		com.liferay.portal.kernel.util.OrderByComparator<Notarization> orderByComparator)
		throws NoSuchNotarizationException;

	/**
	* Removes all the notarizations where groupId = &#63; and dossierId = &#63; from the database.
	*
	* @param groupId the group ID
	* @param dossierId the dossier ID
	*/
	public void removeByF_G_DID(long groupId, long dossierId);

	/**
	* Returns the number of notarizations where groupId = &#63; and dossierId = &#63;.
	*
	* @param groupId the group ID
	* @param dossierId the dossier ID
	* @return the number of matching notarizations
	*/
	public int countByF_G_DID(long groupId, long dossierId);

	/**
	* Caches the notarization in the entity cache if it is enabled.
	*
	* @param notarization the notarization
	*/
	public void cacheResult(Notarization notarization);

	/**
	* Caches the notarizations in the entity cache if it is enabled.
	*
	* @param notarizations the notarizations
	*/
	public void cacheResult(java.util.List<Notarization> notarizations);

	/**
	* Creates a new notarization with the primary key. Does not add the notarization to the database.
	*
	* @param notarizationId the primary key for the new notarization
	* @return the new notarization
	*/
	public Notarization create(long notarizationId);

	/**
	* Removes the notarization with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param notarizationId the primary key of the notarization
	* @return the notarization that was removed
	* @throws NoSuchNotarizationException if a notarization with the primary key could not be found
	*/
	public Notarization remove(long notarizationId)
		throws NoSuchNotarizationException;

	public Notarization updateImpl(Notarization notarization);

	/**
	* Returns the notarization with the primary key or throws a {@link NoSuchNotarizationException} if it could not be found.
	*
	* @param notarizationId the primary key of the notarization
	* @return the notarization
	* @throws NoSuchNotarizationException if a notarization with the primary key could not be found
	*/
	public Notarization findByPrimaryKey(long notarizationId)
		throws NoSuchNotarizationException;

	/**
	* Returns the notarization with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param notarizationId the primary key of the notarization
	* @return the notarization, or <code>null</code> if a notarization with the primary key could not be found
	*/
	public Notarization fetchByPrimaryKey(long notarizationId);

	@Override
	public java.util.Map<java.io.Serializable, Notarization> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the notarizations.
	*
	* @return the notarizations
	*/
	public java.util.List<Notarization> findAll();

	/**
	* Returns a range of all the notarizations.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link NotarizationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of notarizations
	* @param end the upper bound of the range of notarizations (not inclusive)
	* @return the range of notarizations
	*/
	public java.util.List<Notarization> findAll(int start, int end);

	/**
	* Returns an ordered range of all the notarizations.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link NotarizationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of notarizations
	* @param end the upper bound of the range of notarizations (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of notarizations
	*/
	public java.util.List<Notarization> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Notarization> orderByComparator);

	/**
	* Returns an ordered range of all the notarizations.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link NotarizationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of notarizations
	* @param end the upper bound of the range of notarizations (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of notarizations
	*/
	public java.util.List<Notarization> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Notarization> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the notarizations from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of notarizations.
	*
	* @return the number of notarizations
	*/
	public int countAll();
}