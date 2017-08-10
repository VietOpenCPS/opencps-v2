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

package org.opencps.backend.dossiermgt.service.persistence;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.service.persistence.BasePersistence;

import org.opencps.backend.dossiermgt.exception.NoSuchDossierPartException;
import org.opencps.backend.dossiermgt.model.DossierPart;

/**
 * The persistence interface for the dossier part service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author huymq
 * @see org.opencps.backend.dossiermgt.service.persistence.impl.DossierPartPersistenceImpl
 * @see DossierPartUtil
 * @generated
 */
@ProviderType
public interface DossierPartPersistence extends BasePersistence<DossierPart> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link DossierPartUtil} to access the dossier part persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Returns all the dossier parts where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the matching dossier parts
	*/
	public java.util.List<DossierPart> findByUuid(java.lang.String uuid);

	/**
	* Returns a range of all the dossier parts where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierPartModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of dossier parts
	* @param end the upper bound of the range of dossier parts (not inclusive)
	* @return the range of matching dossier parts
	*/
	public java.util.List<DossierPart> findByUuid(java.lang.String uuid,
		int start, int end);

	/**
	* Returns an ordered range of all the dossier parts where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierPartModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of dossier parts
	* @param end the upper bound of the range of dossier parts (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching dossier parts
	*/
	public java.util.List<DossierPart> findByUuid(java.lang.String uuid,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<DossierPart> orderByComparator);

	/**
	* Returns an ordered range of all the dossier parts where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierPartModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of dossier parts
	* @param end the upper bound of the range of dossier parts (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching dossier parts
	*/
	public java.util.List<DossierPart> findByUuid(java.lang.String uuid,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<DossierPart> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first dossier part in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching dossier part
	* @throws NoSuchDossierPartException if a matching dossier part could not be found
	*/
	public DossierPart findByUuid_First(java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<DossierPart> orderByComparator)
		throws NoSuchDossierPartException;

	/**
	* Returns the first dossier part in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching dossier part, or <code>null</code> if a matching dossier part could not be found
	*/
	public DossierPart fetchByUuid_First(java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<DossierPart> orderByComparator);

	/**
	* Returns the last dossier part in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching dossier part
	* @throws NoSuchDossierPartException if a matching dossier part could not be found
	*/
	public DossierPart findByUuid_Last(java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<DossierPart> orderByComparator)
		throws NoSuchDossierPartException;

	/**
	* Returns the last dossier part in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching dossier part, or <code>null</code> if a matching dossier part could not be found
	*/
	public DossierPart fetchByUuid_Last(java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<DossierPart> orderByComparator);

	/**
	* Returns the dossier parts before and after the current dossier part in the ordered set where uuid = &#63;.
	*
	* @param dossierPartId the primary key of the current dossier part
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next dossier part
	* @throws NoSuchDossierPartException if a dossier part with the primary key could not be found
	*/
	public DossierPart[] findByUuid_PrevAndNext(long dossierPartId,
		java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<DossierPart> orderByComparator)
		throws NoSuchDossierPartException;

	/**
	* Removes all the dossier parts where uuid = &#63; from the database.
	*
	* @param uuid the uuid
	*/
	public void removeByUuid(java.lang.String uuid);

	/**
	* Returns the number of dossier parts where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the number of matching dossier parts
	*/
	public int countByUuid(java.lang.String uuid);

	/**
	* Returns the dossier part where uuid = &#63; and groupId = &#63; or throws a {@link NoSuchDossierPartException} if it could not be found.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching dossier part
	* @throws NoSuchDossierPartException if a matching dossier part could not be found
	*/
	public DossierPart findByUUID_G(java.lang.String uuid, long groupId)
		throws NoSuchDossierPartException;

	/**
	* Returns the dossier part where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching dossier part, or <code>null</code> if a matching dossier part could not be found
	*/
	public DossierPart fetchByUUID_G(java.lang.String uuid, long groupId);

	/**
	* Returns the dossier part where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching dossier part, or <code>null</code> if a matching dossier part could not be found
	*/
	public DossierPart fetchByUUID_G(java.lang.String uuid, long groupId,
		boolean retrieveFromCache);

	/**
	* Removes the dossier part where uuid = &#63; and groupId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the dossier part that was removed
	*/
	public DossierPart removeByUUID_G(java.lang.String uuid, long groupId)
		throws NoSuchDossierPartException;

	/**
	* Returns the number of dossier parts where uuid = &#63; and groupId = &#63;.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the number of matching dossier parts
	*/
	public int countByUUID_G(java.lang.String uuid, long groupId);

	/**
	* Returns all the dossier parts where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the matching dossier parts
	*/
	public java.util.List<DossierPart> findByUuid_C(java.lang.String uuid,
		long companyId);

	/**
	* Returns a range of all the dossier parts where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierPartModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of dossier parts
	* @param end the upper bound of the range of dossier parts (not inclusive)
	* @return the range of matching dossier parts
	*/
	public java.util.List<DossierPart> findByUuid_C(java.lang.String uuid,
		long companyId, int start, int end);

	/**
	* Returns an ordered range of all the dossier parts where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierPartModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of dossier parts
	* @param end the upper bound of the range of dossier parts (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching dossier parts
	*/
	public java.util.List<DossierPart> findByUuid_C(java.lang.String uuid,
		long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<DossierPart> orderByComparator);

	/**
	* Returns an ordered range of all the dossier parts where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierPartModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of dossier parts
	* @param end the upper bound of the range of dossier parts (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching dossier parts
	*/
	public java.util.List<DossierPart> findByUuid_C(java.lang.String uuid,
		long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<DossierPart> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first dossier part in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching dossier part
	* @throws NoSuchDossierPartException if a matching dossier part could not be found
	*/
	public DossierPart findByUuid_C_First(java.lang.String uuid,
		long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<DossierPart> orderByComparator)
		throws NoSuchDossierPartException;

	/**
	* Returns the first dossier part in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching dossier part, or <code>null</code> if a matching dossier part could not be found
	*/
	public DossierPart fetchByUuid_C_First(java.lang.String uuid,
		long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<DossierPart> orderByComparator);

	/**
	* Returns the last dossier part in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching dossier part
	* @throws NoSuchDossierPartException if a matching dossier part could not be found
	*/
	public DossierPart findByUuid_C_Last(java.lang.String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<DossierPart> orderByComparator)
		throws NoSuchDossierPartException;

	/**
	* Returns the last dossier part in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching dossier part, or <code>null</code> if a matching dossier part could not be found
	*/
	public DossierPart fetchByUuid_C_Last(java.lang.String uuid,
		long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<DossierPart> orderByComparator);

	/**
	* Returns the dossier parts before and after the current dossier part in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param dossierPartId the primary key of the current dossier part
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next dossier part
	* @throws NoSuchDossierPartException if a dossier part with the primary key could not be found
	*/
	public DossierPart[] findByUuid_C_PrevAndNext(long dossierPartId,
		java.lang.String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<DossierPart> orderByComparator)
		throws NoSuchDossierPartException;

	/**
	* Removes all the dossier parts where uuid = &#63; and companyId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	*/
	public void removeByUuid_C(java.lang.String uuid, long companyId);

	/**
	* Returns the number of dossier parts where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the number of matching dossier parts
	*/
	public int countByUuid_C(java.lang.String uuid, long companyId);

	/**
	* Caches the dossier part in the entity cache if it is enabled.
	*
	* @param dossierPart the dossier part
	*/
	public void cacheResult(DossierPart dossierPart);

	/**
	* Caches the dossier parts in the entity cache if it is enabled.
	*
	* @param dossierParts the dossier parts
	*/
	public void cacheResult(java.util.List<DossierPart> dossierParts);

	/**
	* Creates a new dossier part with the primary key. Does not add the dossier part to the database.
	*
	* @param dossierPartId the primary key for the new dossier part
	* @return the new dossier part
	*/
	public DossierPart create(long dossierPartId);

	/**
	* Removes the dossier part with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param dossierPartId the primary key of the dossier part
	* @return the dossier part that was removed
	* @throws NoSuchDossierPartException if a dossier part with the primary key could not be found
	*/
	public DossierPart remove(long dossierPartId)
		throws NoSuchDossierPartException;

	public DossierPart updateImpl(DossierPart dossierPart);

	/**
	* Returns the dossier part with the primary key or throws a {@link NoSuchDossierPartException} if it could not be found.
	*
	* @param dossierPartId the primary key of the dossier part
	* @return the dossier part
	* @throws NoSuchDossierPartException if a dossier part with the primary key could not be found
	*/
	public DossierPart findByPrimaryKey(long dossierPartId)
		throws NoSuchDossierPartException;

	/**
	* Returns the dossier part with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param dossierPartId the primary key of the dossier part
	* @return the dossier part, or <code>null</code> if a dossier part with the primary key could not be found
	*/
	public DossierPart fetchByPrimaryKey(long dossierPartId);

	@Override
	public java.util.Map<java.io.Serializable, DossierPart> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the dossier parts.
	*
	* @return the dossier parts
	*/
	public java.util.List<DossierPart> findAll();

	/**
	* Returns a range of all the dossier parts.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierPartModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of dossier parts
	* @param end the upper bound of the range of dossier parts (not inclusive)
	* @return the range of dossier parts
	*/
	public java.util.List<DossierPart> findAll(int start, int end);

	/**
	* Returns an ordered range of all the dossier parts.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierPartModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of dossier parts
	* @param end the upper bound of the range of dossier parts (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of dossier parts
	*/
	public java.util.List<DossierPart> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<DossierPart> orderByComparator);

	/**
	* Returns an ordered range of all the dossier parts.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierPartModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of dossier parts
	* @param end the upper bound of the range of dossier parts (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of dossier parts
	*/
	public java.util.List<DossierPart> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<DossierPart> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the dossier parts from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of dossier parts.
	*
	* @return the number of dossier parts
	*/
	public int countAll();

	@Override
	public java.util.Set<java.lang.String> getBadColumnNames();
}