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

import org.opencps.dossiermgt.exception.NoSuchDossierRequestUDException;
import org.opencps.dossiermgt.model.DossierRequestUD;

/**
 * The persistence interface for the dossier request ud service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author huymq
 * @see org.opencps.dossiermgt.service.persistence.impl.DossierRequestUDPersistenceImpl
 * @see DossierRequestUDUtil
 * @generated
 */
@ProviderType
public interface DossierRequestUDPersistence extends BasePersistence<DossierRequestUD> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link DossierRequestUDUtil} to access the dossier request ud persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Returns all the dossier request uds where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the matching dossier request uds
	*/
	public java.util.List<DossierRequestUD> findByUuid(String uuid);

	/**
	* Returns a range of all the dossier request uds where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierRequestUDModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of dossier request uds
	* @param end the upper bound of the range of dossier request uds (not inclusive)
	* @return the range of matching dossier request uds
	*/
	public java.util.List<DossierRequestUD> findByUuid(String uuid, int start,
		int end);

	/**
	* Returns an ordered range of all the dossier request uds where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierRequestUDModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of dossier request uds
	* @param end the upper bound of the range of dossier request uds (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching dossier request uds
	*/
	public java.util.List<DossierRequestUD> findByUuid(String uuid, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<DossierRequestUD> orderByComparator);

	/**
	* Returns an ordered range of all the dossier request uds where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierRequestUDModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of dossier request uds
	* @param end the upper bound of the range of dossier request uds (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching dossier request uds
	*/
	public java.util.List<DossierRequestUD> findByUuid(String uuid, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<DossierRequestUD> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first dossier request ud in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching dossier request ud
	* @throws NoSuchDossierRequestUDException if a matching dossier request ud could not be found
	*/
	public DossierRequestUD findByUuid_First(String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<DossierRequestUD> orderByComparator)
		throws NoSuchDossierRequestUDException;

	/**
	* Returns the first dossier request ud in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching dossier request ud, or <code>null</code> if a matching dossier request ud could not be found
	*/
	public DossierRequestUD fetchByUuid_First(String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<DossierRequestUD> orderByComparator);

	/**
	* Returns the last dossier request ud in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching dossier request ud
	* @throws NoSuchDossierRequestUDException if a matching dossier request ud could not be found
	*/
	public DossierRequestUD findByUuid_Last(String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<DossierRequestUD> orderByComparator)
		throws NoSuchDossierRequestUDException;

	/**
	* Returns the last dossier request ud in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching dossier request ud, or <code>null</code> if a matching dossier request ud could not be found
	*/
	public DossierRequestUD fetchByUuid_Last(String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<DossierRequestUD> orderByComparator);

	/**
	* Returns the dossier request uds before and after the current dossier request ud in the ordered set where uuid = &#63;.
	*
	* @param dossierRequestId the primary key of the current dossier request ud
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next dossier request ud
	* @throws NoSuchDossierRequestUDException if a dossier request ud with the primary key could not be found
	*/
	public DossierRequestUD[] findByUuid_PrevAndNext(long dossierRequestId,
		String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<DossierRequestUD> orderByComparator)
		throws NoSuchDossierRequestUDException;

	/**
	* Removes all the dossier request uds where uuid = &#63; from the database.
	*
	* @param uuid the uuid
	*/
	public void removeByUuid(String uuid);

	/**
	* Returns the number of dossier request uds where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the number of matching dossier request uds
	*/
	public int countByUuid(String uuid);

	/**
	* Returns the dossier request ud where uuid = &#63; and groupId = &#63; or throws a {@link NoSuchDossierRequestUDException} if it could not be found.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching dossier request ud
	* @throws NoSuchDossierRequestUDException if a matching dossier request ud could not be found
	*/
	public DossierRequestUD findByUUID_G(String uuid, long groupId)
		throws NoSuchDossierRequestUDException;

	/**
	* Returns the dossier request ud where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching dossier request ud, or <code>null</code> if a matching dossier request ud could not be found
	*/
	public DossierRequestUD fetchByUUID_G(String uuid, long groupId);

	/**
	* Returns the dossier request ud where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching dossier request ud, or <code>null</code> if a matching dossier request ud could not be found
	*/
	public DossierRequestUD fetchByUUID_G(String uuid, long groupId,
		boolean retrieveFromCache);

	/**
	* Removes the dossier request ud where uuid = &#63; and groupId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the dossier request ud that was removed
	*/
	public DossierRequestUD removeByUUID_G(String uuid, long groupId)
		throws NoSuchDossierRequestUDException;

	/**
	* Returns the number of dossier request uds where uuid = &#63; and groupId = &#63;.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the number of matching dossier request uds
	*/
	public int countByUUID_G(String uuid, long groupId);

	/**
	* Returns all the dossier request uds where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the matching dossier request uds
	*/
	public java.util.List<DossierRequestUD> findByUuid_C(String uuid,
		long companyId);

	/**
	* Returns a range of all the dossier request uds where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierRequestUDModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of dossier request uds
	* @param end the upper bound of the range of dossier request uds (not inclusive)
	* @return the range of matching dossier request uds
	*/
	public java.util.List<DossierRequestUD> findByUuid_C(String uuid,
		long companyId, int start, int end);

	/**
	* Returns an ordered range of all the dossier request uds where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierRequestUDModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of dossier request uds
	* @param end the upper bound of the range of dossier request uds (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching dossier request uds
	*/
	public java.util.List<DossierRequestUD> findByUuid_C(String uuid,
		long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<DossierRequestUD> orderByComparator);

	/**
	* Returns an ordered range of all the dossier request uds where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierRequestUDModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of dossier request uds
	* @param end the upper bound of the range of dossier request uds (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching dossier request uds
	*/
	public java.util.List<DossierRequestUD> findByUuid_C(String uuid,
		long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<DossierRequestUD> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first dossier request ud in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching dossier request ud
	* @throws NoSuchDossierRequestUDException if a matching dossier request ud could not be found
	*/
	public DossierRequestUD findByUuid_C_First(String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<DossierRequestUD> orderByComparator)
		throws NoSuchDossierRequestUDException;

	/**
	* Returns the first dossier request ud in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching dossier request ud, or <code>null</code> if a matching dossier request ud could not be found
	*/
	public DossierRequestUD fetchByUuid_C_First(String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<DossierRequestUD> orderByComparator);

	/**
	* Returns the last dossier request ud in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching dossier request ud
	* @throws NoSuchDossierRequestUDException if a matching dossier request ud could not be found
	*/
	public DossierRequestUD findByUuid_C_Last(String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<DossierRequestUD> orderByComparator)
		throws NoSuchDossierRequestUDException;

	/**
	* Returns the last dossier request ud in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching dossier request ud, or <code>null</code> if a matching dossier request ud could not be found
	*/
	public DossierRequestUD fetchByUuid_C_Last(String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<DossierRequestUD> orderByComparator);

	/**
	* Returns the dossier request uds before and after the current dossier request ud in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param dossierRequestId the primary key of the current dossier request ud
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next dossier request ud
	* @throws NoSuchDossierRequestUDException if a dossier request ud with the primary key could not be found
	*/
	public DossierRequestUD[] findByUuid_C_PrevAndNext(long dossierRequestId,
		String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<DossierRequestUD> orderByComparator)
		throws NoSuchDossierRequestUDException;

	/**
	* Removes all the dossier request uds where uuid = &#63; and companyId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	*/
	public void removeByUuid_C(String uuid, long companyId);

	/**
	* Returns the number of dossier request uds where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the number of matching dossier request uds
	*/
	public int countByUuid_C(String uuid, long companyId);

	/**
	* Returns all the dossier request uds where dossierId = &#63;.
	*
	* @param dossierId the dossier ID
	* @return the matching dossier request uds
	*/
	public java.util.List<DossierRequestUD> findByD_(long dossierId);

	/**
	* Returns a range of all the dossier request uds where dossierId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierRequestUDModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param dossierId the dossier ID
	* @param start the lower bound of the range of dossier request uds
	* @param end the upper bound of the range of dossier request uds (not inclusive)
	* @return the range of matching dossier request uds
	*/
	public java.util.List<DossierRequestUD> findByD_(long dossierId, int start,
		int end);

	/**
	* Returns an ordered range of all the dossier request uds where dossierId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierRequestUDModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param dossierId the dossier ID
	* @param start the lower bound of the range of dossier request uds
	* @param end the upper bound of the range of dossier request uds (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching dossier request uds
	*/
	public java.util.List<DossierRequestUD> findByD_(long dossierId, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<DossierRequestUD> orderByComparator);

	/**
	* Returns an ordered range of all the dossier request uds where dossierId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierRequestUDModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param dossierId the dossier ID
	* @param start the lower bound of the range of dossier request uds
	* @param end the upper bound of the range of dossier request uds (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching dossier request uds
	*/
	public java.util.List<DossierRequestUD> findByD_(long dossierId, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<DossierRequestUD> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first dossier request ud in the ordered set where dossierId = &#63;.
	*
	* @param dossierId the dossier ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching dossier request ud
	* @throws NoSuchDossierRequestUDException if a matching dossier request ud could not be found
	*/
	public DossierRequestUD findByD__First(long dossierId,
		com.liferay.portal.kernel.util.OrderByComparator<DossierRequestUD> orderByComparator)
		throws NoSuchDossierRequestUDException;

	/**
	* Returns the first dossier request ud in the ordered set where dossierId = &#63;.
	*
	* @param dossierId the dossier ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching dossier request ud, or <code>null</code> if a matching dossier request ud could not be found
	*/
	public DossierRequestUD fetchByD__First(long dossierId,
		com.liferay.portal.kernel.util.OrderByComparator<DossierRequestUD> orderByComparator);

	/**
	* Returns the last dossier request ud in the ordered set where dossierId = &#63;.
	*
	* @param dossierId the dossier ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching dossier request ud
	* @throws NoSuchDossierRequestUDException if a matching dossier request ud could not be found
	*/
	public DossierRequestUD findByD__Last(long dossierId,
		com.liferay.portal.kernel.util.OrderByComparator<DossierRequestUD> orderByComparator)
		throws NoSuchDossierRequestUDException;

	/**
	* Returns the last dossier request ud in the ordered set where dossierId = &#63;.
	*
	* @param dossierId the dossier ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching dossier request ud, or <code>null</code> if a matching dossier request ud could not be found
	*/
	public DossierRequestUD fetchByD__Last(long dossierId,
		com.liferay.portal.kernel.util.OrderByComparator<DossierRequestUD> orderByComparator);

	/**
	* Returns the dossier request uds before and after the current dossier request ud in the ordered set where dossierId = &#63;.
	*
	* @param dossierRequestId the primary key of the current dossier request ud
	* @param dossierId the dossier ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next dossier request ud
	* @throws NoSuchDossierRequestUDException if a dossier request ud with the primary key could not be found
	*/
	public DossierRequestUD[] findByD__PrevAndNext(long dossierRequestId,
		long dossierId,
		com.liferay.portal.kernel.util.OrderByComparator<DossierRequestUD> orderByComparator)
		throws NoSuchDossierRequestUDException;

	/**
	* Removes all the dossier request uds where dossierId = &#63; from the database.
	*
	* @param dossierId the dossier ID
	*/
	public void removeByD_(long dossierId);

	/**
	* Returns the number of dossier request uds where dossierId = &#63;.
	*
	* @param dossierId the dossier ID
	* @return the number of matching dossier request uds
	*/
	public int countByD_(long dossierId);

	/**
	* Returns all the dossier request uds where dossierId = &#63; and requestType = &#63;.
	*
	* @param dossierId the dossier ID
	* @param requestType the request type
	* @return the matching dossier request uds
	*/
	public java.util.List<DossierRequestUD> findByD_RT(long dossierId,
		String requestType);

	/**
	* Returns a range of all the dossier request uds where dossierId = &#63; and requestType = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierRequestUDModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param dossierId the dossier ID
	* @param requestType the request type
	* @param start the lower bound of the range of dossier request uds
	* @param end the upper bound of the range of dossier request uds (not inclusive)
	* @return the range of matching dossier request uds
	*/
	public java.util.List<DossierRequestUD> findByD_RT(long dossierId,
		String requestType, int start, int end);

	/**
	* Returns an ordered range of all the dossier request uds where dossierId = &#63; and requestType = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierRequestUDModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param dossierId the dossier ID
	* @param requestType the request type
	* @param start the lower bound of the range of dossier request uds
	* @param end the upper bound of the range of dossier request uds (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching dossier request uds
	*/
	public java.util.List<DossierRequestUD> findByD_RT(long dossierId,
		String requestType, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<DossierRequestUD> orderByComparator);

	/**
	* Returns an ordered range of all the dossier request uds where dossierId = &#63; and requestType = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierRequestUDModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param dossierId the dossier ID
	* @param requestType the request type
	* @param start the lower bound of the range of dossier request uds
	* @param end the upper bound of the range of dossier request uds (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching dossier request uds
	*/
	public java.util.List<DossierRequestUD> findByD_RT(long dossierId,
		String requestType, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<DossierRequestUD> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first dossier request ud in the ordered set where dossierId = &#63; and requestType = &#63;.
	*
	* @param dossierId the dossier ID
	* @param requestType the request type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching dossier request ud
	* @throws NoSuchDossierRequestUDException if a matching dossier request ud could not be found
	*/
	public DossierRequestUD findByD_RT_First(long dossierId,
		String requestType,
		com.liferay.portal.kernel.util.OrderByComparator<DossierRequestUD> orderByComparator)
		throws NoSuchDossierRequestUDException;

	/**
	* Returns the first dossier request ud in the ordered set where dossierId = &#63; and requestType = &#63;.
	*
	* @param dossierId the dossier ID
	* @param requestType the request type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching dossier request ud, or <code>null</code> if a matching dossier request ud could not be found
	*/
	public DossierRequestUD fetchByD_RT_First(long dossierId,
		String requestType,
		com.liferay.portal.kernel.util.OrderByComparator<DossierRequestUD> orderByComparator);

	/**
	* Returns the last dossier request ud in the ordered set where dossierId = &#63; and requestType = &#63;.
	*
	* @param dossierId the dossier ID
	* @param requestType the request type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching dossier request ud
	* @throws NoSuchDossierRequestUDException if a matching dossier request ud could not be found
	*/
	public DossierRequestUD findByD_RT_Last(long dossierId, String requestType,
		com.liferay.portal.kernel.util.OrderByComparator<DossierRequestUD> orderByComparator)
		throws NoSuchDossierRequestUDException;

	/**
	* Returns the last dossier request ud in the ordered set where dossierId = &#63; and requestType = &#63;.
	*
	* @param dossierId the dossier ID
	* @param requestType the request type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching dossier request ud, or <code>null</code> if a matching dossier request ud could not be found
	*/
	public DossierRequestUD fetchByD_RT_Last(long dossierId,
		String requestType,
		com.liferay.portal.kernel.util.OrderByComparator<DossierRequestUD> orderByComparator);

	/**
	* Returns the dossier request uds before and after the current dossier request ud in the ordered set where dossierId = &#63; and requestType = &#63;.
	*
	* @param dossierRequestId the primary key of the current dossier request ud
	* @param dossierId the dossier ID
	* @param requestType the request type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next dossier request ud
	* @throws NoSuchDossierRequestUDException if a dossier request ud with the primary key could not be found
	*/
	public DossierRequestUD[] findByD_RT_PrevAndNext(long dossierRequestId,
		long dossierId, String requestType,
		com.liferay.portal.kernel.util.OrderByComparator<DossierRequestUD> orderByComparator)
		throws NoSuchDossierRequestUDException;

	/**
	* Removes all the dossier request uds where dossierId = &#63; and requestType = &#63; from the database.
	*
	* @param dossierId the dossier ID
	* @param requestType the request type
	*/
	public void removeByD_RT(long dossierId, String requestType);

	/**
	* Returns the number of dossier request uds where dossierId = &#63; and requestType = &#63;.
	*
	* @param dossierId the dossier ID
	* @param requestType the request type
	* @return the number of matching dossier request uds
	*/
	public int countByD_RT(long dossierId, String requestType);

	/**
	* Returns all the dossier request uds where isNew = &#63;.
	*
	* @param isNew the is new
	* @return the matching dossier request uds
	*/
	public java.util.List<DossierRequestUD> findByIS_NEW(int isNew);

	/**
	* Returns a range of all the dossier request uds where isNew = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierRequestUDModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param isNew the is new
	* @param start the lower bound of the range of dossier request uds
	* @param end the upper bound of the range of dossier request uds (not inclusive)
	* @return the range of matching dossier request uds
	*/
	public java.util.List<DossierRequestUD> findByIS_NEW(int isNew, int start,
		int end);

	/**
	* Returns an ordered range of all the dossier request uds where isNew = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierRequestUDModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param isNew the is new
	* @param start the lower bound of the range of dossier request uds
	* @param end the upper bound of the range of dossier request uds (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching dossier request uds
	*/
	public java.util.List<DossierRequestUD> findByIS_NEW(int isNew, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<DossierRequestUD> orderByComparator);

	/**
	* Returns an ordered range of all the dossier request uds where isNew = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierRequestUDModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param isNew the is new
	* @param start the lower bound of the range of dossier request uds
	* @param end the upper bound of the range of dossier request uds (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching dossier request uds
	*/
	public java.util.List<DossierRequestUD> findByIS_NEW(int isNew, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<DossierRequestUD> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first dossier request ud in the ordered set where isNew = &#63;.
	*
	* @param isNew the is new
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching dossier request ud
	* @throws NoSuchDossierRequestUDException if a matching dossier request ud could not be found
	*/
	public DossierRequestUD findByIS_NEW_First(int isNew,
		com.liferay.portal.kernel.util.OrderByComparator<DossierRequestUD> orderByComparator)
		throws NoSuchDossierRequestUDException;

	/**
	* Returns the first dossier request ud in the ordered set where isNew = &#63;.
	*
	* @param isNew the is new
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching dossier request ud, or <code>null</code> if a matching dossier request ud could not be found
	*/
	public DossierRequestUD fetchByIS_NEW_First(int isNew,
		com.liferay.portal.kernel.util.OrderByComparator<DossierRequestUD> orderByComparator);

	/**
	* Returns the last dossier request ud in the ordered set where isNew = &#63;.
	*
	* @param isNew the is new
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching dossier request ud
	* @throws NoSuchDossierRequestUDException if a matching dossier request ud could not be found
	*/
	public DossierRequestUD findByIS_NEW_Last(int isNew,
		com.liferay.portal.kernel.util.OrderByComparator<DossierRequestUD> orderByComparator)
		throws NoSuchDossierRequestUDException;

	/**
	* Returns the last dossier request ud in the ordered set where isNew = &#63;.
	*
	* @param isNew the is new
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching dossier request ud, or <code>null</code> if a matching dossier request ud could not be found
	*/
	public DossierRequestUD fetchByIS_NEW_Last(int isNew,
		com.liferay.portal.kernel.util.OrderByComparator<DossierRequestUD> orderByComparator);

	/**
	* Returns the dossier request uds before and after the current dossier request ud in the ordered set where isNew = &#63;.
	*
	* @param dossierRequestId the primary key of the current dossier request ud
	* @param isNew the is new
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next dossier request ud
	* @throws NoSuchDossierRequestUDException if a dossier request ud with the primary key could not be found
	*/
	public DossierRequestUD[] findByIS_NEW_PrevAndNext(long dossierRequestId,
		int isNew,
		com.liferay.portal.kernel.util.OrderByComparator<DossierRequestUD> orderByComparator)
		throws NoSuchDossierRequestUDException;

	/**
	* Removes all the dossier request uds where isNew = &#63; from the database.
	*
	* @param isNew the is new
	*/
	public void removeByIS_NEW(int isNew);

	/**
	* Returns the number of dossier request uds where isNew = &#63;.
	*
	* @param isNew the is new
	* @return the number of matching dossier request uds
	*/
	public int countByIS_NEW(int isNew);

	/**
	* Returns all the dossier request uds where dossierId = &#63; and isNew = &#63;.
	*
	* @param dossierId the dossier ID
	* @param isNew the is new
	* @return the matching dossier request uds
	*/
	public java.util.List<DossierRequestUD> findByDID_IN(long dossierId,
		int isNew);

	/**
	* Returns a range of all the dossier request uds where dossierId = &#63; and isNew = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierRequestUDModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param dossierId the dossier ID
	* @param isNew the is new
	* @param start the lower bound of the range of dossier request uds
	* @param end the upper bound of the range of dossier request uds (not inclusive)
	* @return the range of matching dossier request uds
	*/
	public java.util.List<DossierRequestUD> findByDID_IN(long dossierId,
		int isNew, int start, int end);

	/**
	* Returns an ordered range of all the dossier request uds where dossierId = &#63; and isNew = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierRequestUDModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param dossierId the dossier ID
	* @param isNew the is new
	* @param start the lower bound of the range of dossier request uds
	* @param end the upper bound of the range of dossier request uds (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching dossier request uds
	*/
	public java.util.List<DossierRequestUD> findByDID_IN(long dossierId,
		int isNew, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<DossierRequestUD> orderByComparator);

	/**
	* Returns an ordered range of all the dossier request uds where dossierId = &#63; and isNew = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierRequestUDModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param dossierId the dossier ID
	* @param isNew the is new
	* @param start the lower bound of the range of dossier request uds
	* @param end the upper bound of the range of dossier request uds (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching dossier request uds
	*/
	public java.util.List<DossierRequestUD> findByDID_IN(long dossierId,
		int isNew, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<DossierRequestUD> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first dossier request ud in the ordered set where dossierId = &#63; and isNew = &#63;.
	*
	* @param dossierId the dossier ID
	* @param isNew the is new
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching dossier request ud
	* @throws NoSuchDossierRequestUDException if a matching dossier request ud could not be found
	*/
	public DossierRequestUD findByDID_IN_First(long dossierId, int isNew,
		com.liferay.portal.kernel.util.OrderByComparator<DossierRequestUD> orderByComparator)
		throws NoSuchDossierRequestUDException;

	/**
	* Returns the first dossier request ud in the ordered set where dossierId = &#63; and isNew = &#63;.
	*
	* @param dossierId the dossier ID
	* @param isNew the is new
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching dossier request ud, or <code>null</code> if a matching dossier request ud could not be found
	*/
	public DossierRequestUD fetchByDID_IN_First(long dossierId, int isNew,
		com.liferay.portal.kernel.util.OrderByComparator<DossierRequestUD> orderByComparator);

	/**
	* Returns the last dossier request ud in the ordered set where dossierId = &#63; and isNew = &#63;.
	*
	* @param dossierId the dossier ID
	* @param isNew the is new
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching dossier request ud
	* @throws NoSuchDossierRequestUDException if a matching dossier request ud could not be found
	*/
	public DossierRequestUD findByDID_IN_Last(long dossierId, int isNew,
		com.liferay.portal.kernel.util.OrderByComparator<DossierRequestUD> orderByComparator)
		throws NoSuchDossierRequestUDException;

	/**
	* Returns the last dossier request ud in the ordered set where dossierId = &#63; and isNew = &#63;.
	*
	* @param dossierId the dossier ID
	* @param isNew the is new
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching dossier request ud, or <code>null</code> if a matching dossier request ud could not be found
	*/
	public DossierRequestUD fetchByDID_IN_Last(long dossierId, int isNew,
		com.liferay.portal.kernel.util.OrderByComparator<DossierRequestUD> orderByComparator);

	/**
	* Returns the dossier request uds before and after the current dossier request ud in the ordered set where dossierId = &#63; and isNew = &#63;.
	*
	* @param dossierRequestId the primary key of the current dossier request ud
	* @param dossierId the dossier ID
	* @param isNew the is new
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next dossier request ud
	* @throws NoSuchDossierRequestUDException if a dossier request ud with the primary key could not be found
	*/
	public DossierRequestUD[] findByDID_IN_PrevAndNext(long dossierRequestId,
		long dossierId, int isNew,
		com.liferay.portal.kernel.util.OrderByComparator<DossierRequestUD> orderByComparator)
		throws NoSuchDossierRequestUDException;

	/**
	* Removes all the dossier request uds where dossierId = &#63; and isNew = &#63; from the database.
	*
	* @param dossierId the dossier ID
	* @param isNew the is new
	*/
	public void removeByDID_IN(long dossierId, int isNew);

	/**
	* Returns the number of dossier request uds where dossierId = &#63; and isNew = &#63;.
	*
	* @param dossierId the dossier ID
	* @param isNew the is new
	* @return the number of matching dossier request uds
	*/
	public int countByDID_IN(long dossierId, int isNew);

	/**
	* Caches the dossier request ud in the entity cache if it is enabled.
	*
	* @param dossierRequestUD the dossier request ud
	*/
	public void cacheResult(DossierRequestUD dossierRequestUD);

	/**
	* Caches the dossier request uds in the entity cache if it is enabled.
	*
	* @param dossierRequestUDs the dossier request uds
	*/
	public void cacheResult(java.util.List<DossierRequestUD> dossierRequestUDs);

	/**
	* Creates a new dossier request ud with the primary key. Does not add the dossier request ud to the database.
	*
	* @param dossierRequestId the primary key for the new dossier request ud
	* @return the new dossier request ud
	*/
	public DossierRequestUD create(long dossierRequestId);

	/**
	* Removes the dossier request ud with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param dossierRequestId the primary key of the dossier request ud
	* @return the dossier request ud that was removed
	* @throws NoSuchDossierRequestUDException if a dossier request ud with the primary key could not be found
	*/
	public DossierRequestUD remove(long dossierRequestId)
		throws NoSuchDossierRequestUDException;

	public DossierRequestUD updateImpl(DossierRequestUD dossierRequestUD);

	/**
	* Returns the dossier request ud with the primary key or throws a {@link NoSuchDossierRequestUDException} if it could not be found.
	*
	* @param dossierRequestId the primary key of the dossier request ud
	* @return the dossier request ud
	* @throws NoSuchDossierRequestUDException if a dossier request ud with the primary key could not be found
	*/
	public DossierRequestUD findByPrimaryKey(long dossierRequestId)
		throws NoSuchDossierRequestUDException;

	/**
	* Returns the dossier request ud with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param dossierRequestId the primary key of the dossier request ud
	* @return the dossier request ud, or <code>null</code> if a dossier request ud with the primary key could not be found
	*/
	public DossierRequestUD fetchByPrimaryKey(long dossierRequestId);

	@Override
	public java.util.Map<java.io.Serializable, DossierRequestUD> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the dossier request uds.
	*
	* @return the dossier request uds
	*/
	public java.util.List<DossierRequestUD> findAll();

	/**
	* Returns a range of all the dossier request uds.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierRequestUDModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of dossier request uds
	* @param end the upper bound of the range of dossier request uds (not inclusive)
	* @return the range of dossier request uds
	*/
	public java.util.List<DossierRequestUD> findAll(int start, int end);

	/**
	* Returns an ordered range of all the dossier request uds.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierRequestUDModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of dossier request uds
	* @param end the upper bound of the range of dossier request uds (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of dossier request uds
	*/
	public java.util.List<DossierRequestUD> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<DossierRequestUD> orderByComparator);

	/**
	* Returns an ordered range of all the dossier request uds.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierRequestUDModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of dossier request uds
	* @param end the upper bound of the range of dossier request uds (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of dossier request uds
	*/
	public java.util.List<DossierRequestUD> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<DossierRequestUD> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the dossier request uds from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of dossier request uds.
	*
	* @return the number of dossier request uds
	*/
	public int countAll();

	@Override
	public java.util.Set<String> getBadColumnNames();
}