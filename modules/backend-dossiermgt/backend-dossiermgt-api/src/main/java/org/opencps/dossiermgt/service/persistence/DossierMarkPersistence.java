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

import org.opencps.dossiermgt.exception.NoSuchDossierMarkException;
import org.opencps.dossiermgt.model.DossierMark;

/**
 * The persistence interface for the dossier mark service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author huymq
 * @see org.opencps.dossiermgt.service.persistence.impl.DossierMarkPersistenceImpl
 * @see DossierMarkUtil
 * @generated
 */
@ProviderType
public interface DossierMarkPersistence extends BasePersistence<DossierMark> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link DossierMarkUtil} to access the dossier mark persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Returns all the dossier marks where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the matching dossier marks
	*/
	public java.util.List<DossierMark> findByUuid(String uuid);

	/**
	* Returns a range of all the dossier marks where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierMarkModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of dossier marks
	* @param end the upper bound of the range of dossier marks (not inclusive)
	* @return the range of matching dossier marks
	*/
	public java.util.List<DossierMark> findByUuid(String uuid, int start,
		int end);

	/**
	* Returns an ordered range of all the dossier marks where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierMarkModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of dossier marks
	* @param end the upper bound of the range of dossier marks (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching dossier marks
	*/
	public java.util.List<DossierMark> findByUuid(String uuid, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<DossierMark> orderByComparator);

	/**
	* Returns an ordered range of all the dossier marks where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierMarkModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of dossier marks
	* @param end the upper bound of the range of dossier marks (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching dossier marks
	*/
	public java.util.List<DossierMark> findByUuid(String uuid, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<DossierMark> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first dossier mark in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching dossier mark
	* @throws NoSuchDossierMarkException if a matching dossier mark could not be found
	*/
	public DossierMark findByUuid_First(String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<DossierMark> orderByComparator)
		throws NoSuchDossierMarkException;

	/**
	* Returns the first dossier mark in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching dossier mark, or <code>null</code> if a matching dossier mark could not be found
	*/
	public DossierMark fetchByUuid_First(String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<DossierMark> orderByComparator);

	/**
	* Returns the last dossier mark in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching dossier mark
	* @throws NoSuchDossierMarkException if a matching dossier mark could not be found
	*/
	public DossierMark findByUuid_Last(String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<DossierMark> orderByComparator)
		throws NoSuchDossierMarkException;

	/**
	* Returns the last dossier mark in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching dossier mark, or <code>null</code> if a matching dossier mark could not be found
	*/
	public DossierMark fetchByUuid_Last(String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<DossierMark> orderByComparator);

	/**
	* Returns the dossier marks before and after the current dossier mark in the ordered set where uuid = &#63;.
	*
	* @param dossierMarkId the primary key of the current dossier mark
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next dossier mark
	* @throws NoSuchDossierMarkException if a dossier mark with the primary key could not be found
	*/
	public DossierMark[] findByUuid_PrevAndNext(long dossierMarkId,
		String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<DossierMark> orderByComparator)
		throws NoSuchDossierMarkException;

	/**
	* Removes all the dossier marks where uuid = &#63; from the database.
	*
	* @param uuid the uuid
	*/
	public void removeByUuid(String uuid);

	/**
	* Returns the number of dossier marks where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the number of matching dossier marks
	*/
	public int countByUuid(String uuid);

	/**
	* Returns the dossier mark where uuid = &#63; and groupId = &#63; or throws a {@link NoSuchDossierMarkException} if it could not be found.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching dossier mark
	* @throws NoSuchDossierMarkException if a matching dossier mark could not be found
	*/
	public DossierMark findByUUID_G(String uuid, long groupId)
		throws NoSuchDossierMarkException;

	/**
	* Returns the dossier mark where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching dossier mark, or <code>null</code> if a matching dossier mark could not be found
	*/
	public DossierMark fetchByUUID_G(String uuid, long groupId);

	/**
	* Returns the dossier mark where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching dossier mark, or <code>null</code> if a matching dossier mark could not be found
	*/
	public DossierMark fetchByUUID_G(String uuid, long groupId,
		boolean retrieveFromCache);

	/**
	* Removes the dossier mark where uuid = &#63; and groupId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the dossier mark that was removed
	*/
	public DossierMark removeByUUID_G(String uuid, long groupId)
		throws NoSuchDossierMarkException;

	/**
	* Returns the number of dossier marks where uuid = &#63; and groupId = &#63;.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the number of matching dossier marks
	*/
	public int countByUUID_G(String uuid, long groupId);

	/**
	* Returns all the dossier marks where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the matching dossier marks
	*/
	public java.util.List<DossierMark> findByUuid_C(String uuid, long companyId);

	/**
	* Returns a range of all the dossier marks where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierMarkModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of dossier marks
	* @param end the upper bound of the range of dossier marks (not inclusive)
	* @return the range of matching dossier marks
	*/
	public java.util.List<DossierMark> findByUuid_C(String uuid,
		long companyId, int start, int end);

	/**
	* Returns an ordered range of all the dossier marks where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierMarkModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of dossier marks
	* @param end the upper bound of the range of dossier marks (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching dossier marks
	*/
	public java.util.List<DossierMark> findByUuid_C(String uuid,
		long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<DossierMark> orderByComparator);

	/**
	* Returns an ordered range of all the dossier marks where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierMarkModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of dossier marks
	* @param end the upper bound of the range of dossier marks (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching dossier marks
	*/
	public java.util.List<DossierMark> findByUuid_C(String uuid,
		long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<DossierMark> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first dossier mark in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching dossier mark
	* @throws NoSuchDossierMarkException if a matching dossier mark could not be found
	*/
	public DossierMark findByUuid_C_First(String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<DossierMark> orderByComparator)
		throws NoSuchDossierMarkException;

	/**
	* Returns the first dossier mark in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching dossier mark, or <code>null</code> if a matching dossier mark could not be found
	*/
	public DossierMark fetchByUuid_C_First(String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<DossierMark> orderByComparator);

	/**
	* Returns the last dossier mark in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching dossier mark
	* @throws NoSuchDossierMarkException if a matching dossier mark could not be found
	*/
	public DossierMark findByUuid_C_Last(String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<DossierMark> orderByComparator)
		throws NoSuchDossierMarkException;

	/**
	* Returns the last dossier mark in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching dossier mark, or <code>null</code> if a matching dossier mark could not be found
	*/
	public DossierMark fetchByUuid_C_Last(String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<DossierMark> orderByComparator);

	/**
	* Returns the dossier marks before and after the current dossier mark in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param dossierMarkId the primary key of the current dossier mark
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next dossier mark
	* @throws NoSuchDossierMarkException if a dossier mark with the primary key could not be found
	*/
	public DossierMark[] findByUuid_C_PrevAndNext(long dossierMarkId,
		String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<DossierMark> orderByComparator)
		throws NoSuchDossierMarkException;

	/**
	* Removes all the dossier marks where uuid = &#63; and companyId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	*/
	public void removeByUuid_C(String uuid, long companyId);

	/**
	* Returns the number of dossier marks where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the number of matching dossier marks
	*/
	public int countByUuid_C(String uuid, long companyId);

	/**
	* Returns the dossier mark where groupId = &#63; and dossierId = &#63; and dossierPartNo = &#63; or throws a {@link NoSuchDossierMarkException} if it could not be found.
	*
	* @param groupId the group ID
	* @param dossierId the dossier ID
	* @param dossierPartNo the dossier part no
	* @return the matching dossier mark
	* @throws NoSuchDossierMarkException if a matching dossier mark could not be found
	*/
	public DossierMark findByG_DID_PN(long groupId, long dossierId,
		String dossierPartNo) throws NoSuchDossierMarkException;

	/**
	* Returns the dossier mark where groupId = &#63; and dossierId = &#63; and dossierPartNo = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param groupId the group ID
	* @param dossierId the dossier ID
	* @param dossierPartNo the dossier part no
	* @return the matching dossier mark, or <code>null</code> if a matching dossier mark could not be found
	*/
	public DossierMark fetchByG_DID_PN(long groupId, long dossierId,
		String dossierPartNo);

	/**
	* Returns the dossier mark where groupId = &#63; and dossierId = &#63; and dossierPartNo = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param groupId the group ID
	* @param dossierId the dossier ID
	* @param dossierPartNo the dossier part no
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching dossier mark, or <code>null</code> if a matching dossier mark could not be found
	*/
	public DossierMark fetchByG_DID_PN(long groupId, long dossierId,
		String dossierPartNo, boolean retrieveFromCache);

	/**
	* Removes the dossier mark where groupId = &#63; and dossierId = &#63; and dossierPartNo = &#63; from the database.
	*
	* @param groupId the group ID
	* @param dossierId the dossier ID
	* @param dossierPartNo the dossier part no
	* @return the dossier mark that was removed
	*/
	public DossierMark removeByG_DID_PN(long groupId, long dossierId,
		String dossierPartNo) throws NoSuchDossierMarkException;

	/**
	* Returns the number of dossier marks where groupId = &#63; and dossierId = &#63; and dossierPartNo = &#63;.
	*
	* @param groupId the group ID
	* @param dossierId the dossier ID
	* @param dossierPartNo the dossier part no
	* @return the number of matching dossier marks
	*/
	public int countByG_DID_PN(long groupId, long dossierId,
		String dossierPartNo);

	/**
	* Returns all the dossier marks where groupId = &#63; and dossierId = &#63;.
	*
	* @param groupId the group ID
	* @param dossierId the dossier ID
	* @return the matching dossier marks
	*/
	public java.util.List<DossierMark> findByG_DID(long groupId, long dossierId);

	/**
	* Returns a range of all the dossier marks where groupId = &#63; and dossierId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierMarkModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param dossierId the dossier ID
	* @param start the lower bound of the range of dossier marks
	* @param end the upper bound of the range of dossier marks (not inclusive)
	* @return the range of matching dossier marks
	*/
	public java.util.List<DossierMark> findByG_DID(long groupId,
		long dossierId, int start, int end);

	/**
	* Returns an ordered range of all the dossier marks where groupId = &#63; and dossierId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierMarkModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param dossierId the dossier ID
	* @param start the lower bound of the range of dossier marks
	* @param end the upper bound of the range of dossier marks (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching dossier marks
	*/
	public java.util.List<DossierMark> findByG_DID(long groupId,
		long dossierId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<DossierMark> orderByComparator);

	/**
	* Returns an ordered range of all the dossier marks where groupId = &#63; and dossierId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierMarkModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param dossierId the dossier ID
	* @param start the lower bound of the range of dossier marks
	* @param end the upper bound of the range of dossier marks (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching dossier marks
	*/
	public java.util.List<DossierMark> findByG_DID(long groupId,
		long dossierId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<DossierMark> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first dossier mark in the ordered set where groupId = &#63; and dossierId = &#63;.
	*
	* @param groupId the group ID
	* @param dossierId the dossier ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching dossier mark
	* @throws NoSuchDossierMarkException if a matching dossier mark could not be found
	*/
	public DossierMark findByG_DID_First(long groupId, long dossierId,
		com.liferay.portal.kernel.util.OrderByComparator<DossierMark> orderByComparator)
		throws NoSuchDossierMarkException;

	/**
	* Returns the first dossier mark in the ordered set where groupId = &#63; and dossierId = &#63;.
	*
	* @param groupId the group ID
	* @param dossierId the dossier ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching dossier mark, or <code>null</code> if a matching dossier mark could not be found
	*/
	public DossierMark fetchByG_DID_First(long groupId, long dossierId,
		com.liferay.portal.kernel.util.OrderByComparator<DossierMark> orderByComparator);

	/**
	* Returns the last dossier mark in the ordered set where groupId = &#63; and dossierId = &#63;.
	*
	* @param groupId the group ID
	* @param dossierId the dossier ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching dossier mark
	* @throws NoSuchDossierMarkException if a matching dossier mark could not be found
	*/
	public DossierMark findByG_DID_Last(long groupId, long dossierId,
		com.liferay.portal.kernel.util.OrderByComparator<DossierMark> orderByComparator)
		throws NoSuchDossierMarkException;

	/**
	* Returns the last dossier mark in the ordered set where groupId = &#63; and dossierId = &#63;.
	*
	* @param groupId the group ID
	* @param dossierId the dossier ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching dossier mark, or <code>null</code> if a matching dossier mark could not be found
	*/
	public DossierMark fetchByG_DID_Last(long groupId, long dossierId,
		com.liferay.portal.kernel.util.OrderByComparator<DossierMark> orderByComparator);

	/**
	* Returns the dossier marks before and after the current dossier mark in the ordered set where groupId = &#63; and dossierId = &#63;.
	*
	* @param dossierMarkId the primary key of the current dossier mark
	* @param groupId the group ID
	* @param dossierId the dossier ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next dossier mark
	* @throws NoSuchDossierMarkException if a dossier mark with the primary key could not be found
	*/
	public DossierMark[] findByG_DID_PrevAndNext(long dossierMarkId,
		long groupId, long dossierId,
		com.liferay.portal.kernel.util.OrderByComparator<DossierMark> orderByComparator)
		throws NoSuchDossierMarkException;

	/**
	* Removes all the dossier marks where groupId = &#63; and dossierId = &#63; from the database.
	*
	* @param groupId the group ID
	* @param dossierId the dossier ID
	*/
	public void removeByG_DID(long groupId, long dossierId);

	/**
	* Returns the number of dossier marks where groupId = &#63; and dossierId = &#63;.
	*
	* @param groupId the group ID
	* @param dossierId the dossier ID
	* @return the number of matching dossier marks
	*/
	public int countByG_DID(long groupId, long dossierId);

	/**
	* Returns all the dossier marks where groupId = &#63; and dossierId = &#63; and fileMark &ne; &#63;.
	*
	* @param groupId the group ID
	* @param dossierId the dossier ID
	* @param fileMark the file mark
	* @return the matching dossier marks
	*/
	public java.util.List<DossierMark> findByG_DID_MARK(long groupId,
		long dossierId, int fileMark);

	/**
	* Returns a range of all the dossier marks where groupId = &#63; and dossierId = &#63; and fileMark &ne; &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierMarkModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param dossierId the dossier ID
	* @param fileMark the file mark
	* @param start the lower bound of the range of dossier marks
	* @param end the upper bound of the range of dossier marks (not inclusive)
	* @return the range of matching dossier marks
	*/
	public java.util.List<DossierMark> findByG_DID_MARK(long groupId,
		long dossierId, int fileMark, int start, int end);

	/**
	* Returns an ordered range of all the dossier marks where groupId = &#63; and dossierId = &#63; and fileMark &ne; &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierMarkModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param dossierId the dossier ID
	* @param fileMark the file mark
	* @param start the lower bound of the range of dossier marks
	* @param end the upper bound of the range of dossier marks (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching dossier marks
	*/
	public java.util.List<DossierMark> findByG_DID_MARK(long groupId,
		long dossierId, int fileMark, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<DossierMark> orderByComparator);

	/**
	* Returns an ordered range of all the dossier marks where groupId = &#63; and dossierId = &#63; and fileMark &ne; &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierMarkModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param dossierId the dossier ID
	* @param fileMark the file mark
	* @param start the lower bound of the range of dossier marks
	* @param end the upper bound of the range of dossier marks (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching dossier marks
	*/
	public java.util.List<DossierMark> findByG_DID_MARK(long groupId,
		long dossierId, int fileMark, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<DossierMark> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first dossier mark in the ordered set where groupId = &#63; and dossierId = &#63; and fileMark &ne; &#63;.
	*
	* @param groupId the group ID
	* @param dossierId the dossier ID
	* @param fileMark the file mark
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching dossier mark
	* @throws NoSuchDossierMarkException if a matching dossier mark could not be found
	*/
	public DossierMark findByG_DID_MARK_First(long groupId, long dossierId,
		int fileMark,
		com.liferay.portal.kernel.util.OrderByComparator<DossierMark> orderByComparator)
		throws NoSuchDossierMarkException;

	/**
	* Returns the first dossier mark in the ordered set where groupId = &#63; and dossierId = &#63; and fileMark &ne; &#63;.
	*
	* @param groupId the group ID
	* @param dossierId the dossier ID
	* @param fileMark the file mark
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching dossier mark, or <code>null</code> if a matching dossier mark could not be found
	*/
	public DossierMark fetchByG_DID_MARK_First(long groupId, long dossierId,
		int fileMark,
		com.liferay.portal.kernel.util.OrderByComparator<DossierMark> orderByComparator);

	/**
	* Returns the last dossier mark in the ordered set where groupId = &#63; and dossierId = &#63; and fileMark &ne; &#63;.
	*
	* @param groupId the group ID
	* @param dossierId the dossier ID
	* @param fileMark the file mark
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching dossier mark
	* @throws NoSuchDossierMarkException if a matching dossier mark could not be found
	*/
	public DossierMark findByG_DID_MARK_Last(long groupId, long dossierId,
		int fileMark,
		com.liferay.portal.kernel.util.OrderByComparator<DossierMark> orderByComparator)
		throws NoSuchDossierMarkException;

	/**
	* Returns the last dossier mark in the ordered set where groupId = &#63; and dossierId = &#63; and fileMark &ne; &#63;.
	*
	* @param groupId the group ID
	* @param dossierId the dossier ID
	* @param fileMark the file mark
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching dossier mark, or <code>null</code> if a matching dossier mark could not be found
	*/
	public DossierMark fetchByG_DID_MARK_Last(long groupId, long dossierId,
		int fileMark,
		com.liferay.portal.kernel.util.OrderByComparator<DossierMark> orderByComparator);

	/**
	* Returns the dossier marks before and after the current dossier mark in the ordered set where groupId = &#63; and dossierId = &#63; and fileMark &ne; &#63;.
	*
	* @param dossierMarkId the primary key of the current dossier mark
	* @param groupId the group ID
	* @param dossierId the dossier ID
	* @param fileMark the file mark
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next dossier mark
	* @throws NoSuchDossierMarkException if a dossier mark with the primary key could not be found
	*/
	public DossierMark[] findByG_DID_MARK_PrevAndNext(long dossierMarkId,
		long groupId, long dossierId, int fileMark,
		com.liferay.portal.kernel.util.OrderByComparator<DossierMark> orderByComparator)
		throws NoSuchDossierMarkException;

	/**
	* Removes all the dossier marks where groupId = &#63; and dossierId = &#63; and fileMark &ne; &#63; from the database.
	*
	* @param groupId the group ID
	* @param dossierId the dossier ID
	* @param fileMark the file mark
	*/
	public void removeByG_DID_MARK(long groupId, long dossierId, int fileMark);

	/**
	* Returns the number of dossier marks where groupId = &#63; and dossierId = &#63; and fileMark &ne; &#63;.
	*
	* @param groupId the group ID
	* @param dossierId the dossier ID
	* @param fileMark the file mark
	* @return the number of matching dossier marks
	*/
	public int countByG_DID_MARK(long groupId, long dossierId, int fileMark);

	/**
	* Caches the dossier mark in the entity cache if it is enabled.
	*
	* @param dossierMark the dossier mark
	*/
	public void cacheResult(DossierMark dossierMark);

	/**
	* Caches the dossier marks in the entity cache if it is enabled.
	*
	* @param dossierMarks the dossier marks
	*/
	public void cacheResult(java.util.List<DossierMark> dossierMarks);

	/**
	* Creates a new dossier mark with the primary key. Does not add the dossier mark to the database.
	*
	* @param dossierMarkId the primary key for the new dossier mark
	* @return the new dossier mark
	*/
	public DossierMark create(long dossierMarkId);

	/**
	* Removes the dossier mark with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param dossierMarkId the primary key of the dossier mark
	* @return the dossier mark that was removed
	* @throws NoSuchDossierMarkException if a dossier mark with the primary key could not be found
	*/
	public DossierMark remove(long dossierMarkId)
		throws NoSuchDossierMarkException;

	public DossierMark updateImpl(DossierMark dossierMark);

	/**
	* Returns the dossier mark with the primary key or throws a {@link NoSuchDossierMarkException} if it could not be found.
	*
	* @param dossierMarkId the primary key of the dossier mark
	* @return the dossier mark
	* @throws NoSuchDossierMarkException if a dossier mark with the primary key could not be found
	*/
	public DossierMark findByPrimaryKey(long dossierMarkId)
		throws NoSuchDossierMarkException;

	/**
	* Returns the dossier mark with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param dossierMarkId the primary key of the dossier mark
	* @return the dossier mark, or <code>null</code> if a dossier mark with the primary key could not be found
	*/
	public DossierMark fetchByPrimaryKey(long dossierMarkId);

	@Override
	public java.util.Map<java.io.Serializable, DossierMark> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the dossier marks.
	*
	* @return the dossier marks
	*/
	public java.util.List<DossierMark> findAll();

	/**
	* Returns a range of all the dossier marks.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierMarkModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of dossier marks
	* @param end the upper bound of the range of dossier marks (not inclusive)
	* @return the range of dossier marks
	*/
	public java.util.List<DossierMark> findAll(int start, int end);

	/**
	* Returns an ordered range of all the dossier marks.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierMarkModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of dossier marks
	* @param end the upper bound of the range of dossier marks (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of dossier marks
	*/
	public java.util.List<DossierMark> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<DossierMark> orderByComparator);

	/**
	* Returns an ordered range of all the dossier marks.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierMarkModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of dossier marks
	* @param end the upper bound of the range of dossier marks (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of dossier marks
	*/
	public java.util.List<DossierMark> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<DossierMark> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the dossier marks from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of dossier marks.
	*
	* @return the number of dossier marks
	*/
	public int countAll();

	@Override
	public java.util.Set<String> getBadColumnNames();
}