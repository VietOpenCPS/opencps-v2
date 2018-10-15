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

import org.opencps.dossiermgt.exception.NoSuchDossierActionUserException;
import org.opencps.dossiermgt.model.DossierActionUser;

/**
 * The persistence interface for the dossier action user service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author huymq
 * @see org.opencps.dossiermgt.service.persistence.impl.DossierActionUserPersistenceImpl
 * @see DossierActionUserUtil
 * @generated
 */
@ProviderType
public interface DossierActionUserPersistence extends BasePersistence<DossierActionUser> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link DossierActionUserUtil} to access the dossier action user persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Returns all the dossier action users where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the matching dossier action users
	*/
	public java.util.List<DossierActionUser> findByUuid(String uuid);

	/**
	* Returns a range of all the dossier action users where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierActionUserModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of dossier action users
	* @param end the upper bound of the range of dossier action users (not inclusive)
	* @return the range of matching dossier action users
	*/
	public java.util.List<DossierActionUser> findByUuid(String uuid, int start,
		int end);

	/**
	* Returns an ordered range of all the dossier action users where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierActionUserModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of dossier action users
	* @param end the upper bound of the range of dossier action users (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching dossier action users
	*/
	public java.util.List<DossierActionUser> findByUuid(String uuid, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<DossierActionUser> orderByComparator);

	/**
	* Returns an ordered range of all the dossier action users where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierActionUserModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of dossier action users
	* @param end the upper bound of the range of dossier action users (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching dossier action users
	*/
	public java.util.List<DossierActionUser> findByUuid(String uuid, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<DossierActionUser> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first dossier action user in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching dossier action user
	* @throws NoSuchDossierActionUserException if a matching dossier action user could not be found
	*/
	public DossierActionUser findByUuid_First(String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<DossierActionUser> orderByComparator)
		throws NoSuchDossierActionUserException;

	/**
	* Returns the first dossier action user in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching dossier action user, or <code>null</code> if a matching dossier action user could not be found
	*/
	public DossierActionUser fetchByUuid_First(String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<DossierActionUser> orderByComparator);

	/**
	* Returns the last dossier action user in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching dossier action user
	* @throws NoSuchDossierActionUserException if a matching dossier action user could not be found
	*/
	public DossierActionUser findByUuid_Last(String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<DossierActionUser> orderByComparator)
		throws NoSuchDossierActionUserException;

	/**
	* Returns the last dossier action user in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching dossier action user, or <code>null</code> if a matching dossier action user could not be found
	*/
	public DossierActionUser fetchByUuid_Last(String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<DossierActionUser> orderByComparator);

	/**
	* Returns the dossier action users before and after the current dossier action user in the ordered set where uuid = &#63;.
	*
	* @param dossierActionUserPK the primary key of the current dossier action user
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next dossier action user
	* @throws NoSuchDossierActionUserException if a dossier action user with the primary key could not be found
	*/
	public DossierActionUser[] findByUuid_PrevAndNext(
		DossierActionUserPK dossierActionUserPK, String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<DossierActionUser> orderByComparator)
		throws NoSuchDossierActionUserException;

	/**
	* Removes all the dossier action users where uuid = &#63; from the database.
	*
	* @param uuid the uuid
	*/
	public void removeByUuid(String uuid);

	/**
	* Returns the number of dossier action users where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the number of matching dossier action users
	*/
	public int countByUuid(String uuid);

	/**
	* Returns all the dossier action users where dossierActionId = &#63;.
	*
	* @param dossierActionId the dossier action ID
	* @return the matching dossier action users
	*/
	public java.util.List<DossierActionUser> findByDID(long dossierActionId);

	/**
	* Returns a range of all the dossier action users where dossierActionId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierActionUserModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param dossierActionId the dossier action ID
	* @param start the lower bound of the range of dossier action users
	* @param end the upper bound of the range of dossier action users (not inclusive)
	* @return the range of matching dossier action users
	*/
	public java.util.List<DossierActionUser> findByDID(long dossierActionId,
		int start, int end);

	/**
	* Returns an ordered range of all the dossier action users where dossierActionId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierActionUserModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param dossierActionId the dossier action ID
	* @param start the lower bound of the range of dossier action users
	* @param end the upper bound of the range of dossier action users (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching dossier action users
	*/
	public java.util.List<DossierActionUser> findByDID(long dossierActionId,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<DossierActionUser> orderByComparator);

	/**
	* Returns an ordered range of all the dossier action users where dossierActionId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierActionUserModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param dossierActionId the dossier action ID
	* @param start the lower bound of the range of dossier action users
	* @param end the upper bound of the range of dossier action users (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching dossier action users
	*/
	public java.util.List<DossierActionUser> findByDID(long dossierActionId,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<DossierActionUser> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first dossier action user in the ordered set where dossierActionId = &#63;.
	*
	* @param dossierActionId the dossier action ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching dossier action user
	* @throws NoSuchDossierActionUserException if a matching dossier action user could not be found
	*/
	public DossierActionUser findByDID_First(long dossierActionId,
		com.liferay.portal.kernel.util.OrderByComparator<DossierActionUser> orderByComparator)
		throws NoSuchDossierActionUserException;

	/**
	* Returns the first dossier action user in the ordered set where dossierActionId = &#63;.
	*
	* @param dossierActionId the dossier action ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching dossier action user, or <code>null</code> if a matching dossier action user could not be found
	*/
	public DossierActionUser fetchByDID_First(long dossierActionId,
		com.liferay.portal.kernel.util.OrderByComparator<DossierActionUser> orderByComparator);

	/**
	* Returns the last dossier action user in the ordered set where dossierActionId = &#63;.
	*
	* @param dossierActionId the dossier action ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching dossier action user
	* @throws NoSuchDossierActionUserException if a matching dossier action user could not be found
	*/
	public DossierActionUser findByDID_Last(long dossierActionId,
		com.liferay.portal.kernel.util.OrderByComparator<DossierActionUser> orderByComparator)
		throws NoSuchDossierActionUserException;

	/**
	* Returns the last dossier action user in the ordered set where dossierActionId = &#63;.
	*
	* @param dossierActionId the dossier action ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching dossier action user, or <code>null</code> if a matching dossier action user could not be found
	*/
	public DossierActionUser fetchByDID_Last(long dossierActionId,
		com.liferay.portal.kernel.util.OrderByComparator<DossierActionUser> orderByComparator);

	/**
	* Returns the dossier action users before and after the current dossier action user in the ordered set where dossierActionId = &#63;.
	*
	* @param dossierActionUserPK the primary key of the current dossier action user
	* @param dossierActionId the dossier action ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next dossier action user
	* @throws NoSuchDossierActionUserException if a dossier action user with the primary key could not be found
	*/
	public DossierActionUser[] findByDID_PrevAndNext(
		DossierActionUserPK dossierActionUserPK, long dossierActionId,
		com.liferay.portal.kernel.util.OrderByComparator<DossierActionUser> orderByComparator)
		throws NoSuchDossierActionUserException;

	/**
	* Removes all the dossier action users where dossierActionId = &#63; from the database.
	*
	* @param dossierActionId the dossier action ID
	*/
	public void removeByDID(long dossierActionId);

	/**
	* Returns the number of dossier action users where dossierActionId = &#63;.
	*
	* @param dossierActionId the dossier action ID
	* @return the number of matching dossier action users
	*/
	public int countByDID(long dossierActionId);

	/**
	* Returns the dossier action user where dossierActionId = &#63; and userId = &#63; or throws a {@link NoSuchDossierActionUserException} if it could not be found.
	*
	* @param dossierActionId the dossier action ID
	* @param userId the user ID
	* @return the matching dossier action user
	* @throws NoSuchDossierActionUserException if a matching dossier action user could not be found
	*/
	public DossierActionUser findByDID_UID(long dossierActionId, long userId)
		throws NoSuchDossierActionUserException;

	/**
	* Returns the dossier action user where dossierActionId = &#63; and userId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param dossierActionId the dossier action ID
	* @param userId the user ID
	* @return the matching dossier action user, or <code>null</code> if a matching dossier action user could not be found
	*/
	public DossierActionUser fetchByDID_UID(long dossierActionId, long userId);

	/**
	* Returns the dossier action user where dossierActionId = &#63; and userId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param dossierActionId the dossier action ID
	* @param userId the user ID
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching dossier action user, or <code>null</code> if a matching dossier action user could not be found
	*/
	public DossierActionUser fetchByDID_UID(long dossierActionId, long userId,
		boolean retrieveFromCache);

	/**
	* Removes the dossier action user where dossierActionId = &#63; and userId = &#63; from the database.
	*
	* @param dossierActionId the dossier action ID
	* @param userId the user ID
	* @return the dossier action user that was removed
	*/
	public DossierActionUser removeByDID_UID(long dossierActionId, long userId)
		throws NoSuchDossierActionUserException;

	/**
	* Returns the number of dossier action users where dossierActionId = &#63; and userId = &#63;.
	*
	* @param dossierActionId the dossier action ID
	* @param userId the user ID
	* @return the number of matching dossier action users
	*/
	public int countByDID_UID(long dossierActionId, long userId);

	/**
	* Returns all the dossier action users where userId = &#63;.
	*
	* @param userId the user ID
	* @return the matching dossier action users
	*/
	public java.util.List<DossierActionUser> findByUID(long userId);

	/**
	* Returns a range of all the dossier action users where userId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierActionUserModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param userId the user ID
	* @param start the lower bound of the range of dossier action users
	* @param end the upper bound of the range of dossier action users (not inclusive)
	* @return the range of matching dossier action users
	*/
	public java.util.List<DossierActionUser> findByUID(long userId, int start,
		int end);

	/**
	* Returns an ordered range of all the dossier action users where userId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierActionUserModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param userId the user ID
	* @param start the lower bound of the range of dossier action users
	* @param end the upper bound of the range of dossier action users (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching dossier action users
	*/
	public java.util.List<DossierActionUser> findByUID(long userId, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<DossierActionUser> orderByComparator);

	/**
	* Returns an ordered range of all the dossier action users where userId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierActionUserModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param userId the user ID
	* @param start the lower bound of the range of dossier action users
	* @param end the upper bound of the range of dossier action users (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching dossier action users
	*/
	public java.util.List<DossierActionUser> findByUID(long userId, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<DossierActionUser> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first dossier action user in the ordered set where userId = &#63;.
	*
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching dossier action user
	* @throws NoSuchDossierActionUserException if a matching dossier action user could not be found
	*/
	public DossierActionUser findByUID_First(long userId,
		com.liferay.portal.kernel.util.OrderByComparator<DossierActionUser> orderByComparator)
		throws NoSuchDossierActionUserException;

	/**
	* Returns the first dossier action user in the ordered set where userId = &#63;.
	*
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching dossier action user, or <code>null</code> if a matching dossier action user could not be found
	*/
	public DossierActionUser fetchByUID_First(long userId,
		com.liferay.portal.kernel.util.OrderByComparator<DossierActionUser> orderByComparator);

	/**
	* Returns the last dossier action user in the ordered set where userId = &#63;.
	*
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching dossier action user
	* @throws NoSuchDossierActionUserException if a matching dossier action user could not be found
	*/
	public DossierActionUser findByUID_Last(long userId,
		com.liferay.portal.kernel.util.OrderByComparator<DossierActionUser> orderByComparator)
		throws NoSuchDossierActionUserException;

	/**
	* Returns the last dossier action user in the ordered set where userId = &#63;.
	*
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching dossier action user, or <code>null</code> if a matching dossier action user could not be found
	*/
	public DossierActionUser fetchByUID_Last(long userId,
		com.liferay.portal.kernel.util.OrderByComparator<DossierActionUser> orderByComparator);

	/**
	* Returns the dossier action users before and after the current dossier action user in the ordered set where userId = &#63;.
	*
	* @param dossierActionUserPK the primary key of the current dossier action user
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next dossier action user
	* @throws NoSuchDossierActionUserException if a dossier action user with the primary key could not be found
	*/
	public DossierActionUser[] findByUID_PrevAndNext(
		DossierActionUserPK dossierActionUserPK, long userId,
		com.liferay.portal.kernel.util.OrderByComparator<DossierActionUser> orderByComparator)
		throws NoSuchDossierActionUserException;

	/**
	* Removes all the dossier action users where userId = &#63; from the database.
	*
	* @param userId the user ID
	*/
	public void removeByUID(long userId);

	/**
	* Returns the number of dossier action users where userId = &#63;.
	*
	* @param userId the user ID
	* @return the number of matching dossier action users
	*/
	public int countByUID(long userId);

	/**
	* Returns all the dossier action users where dossierId = &#63; and stepCode = &#63;.
	*
	* @param dossierId the dossier ID
	* @param stepCode the step code
	* @return the matching dossier action users
	*/
	public java.util.List<DossierActionUser> findByDID_SC(long dossierId,
		String stepCode);

	/**
	* Returns a range of all the dossier action users where dossierId = &#63; and stepCode = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierActionUserModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param dossierId the dossier ID
	* @param stepCode the step code
	* @param start the lower bound of the range of dossier action users
	* @param end the upper bound of the range of dossier action users (not inclusive)
	* @return the range of matching dossier action users
	*/
	public java.util.List<DossierActionUser> findByDID_SC(long dossierId,
		String stepCode, int start, int end);

	/**
	* Returns an ordered range of all the dossier action users where dossierId = &#63; and stepCode = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierActionUserModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param dossierId the dossier ID
	* @param stepCode the step code
	* @param start the lower bound of the range of dossier action users
	* @param end the upper bound of the range of dossier action users (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching dossier action users
	*/
	public java.util.List<DossierActionUser> findByDID_SC(long dossierId,
		String stepCode, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<DossierActionUser> orderByComparator);

	/**
	* Returns an ordered range of all the dossier action users where dossierId = &#63; and stepCode = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierActionUserModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param dossierId the dossier ID
	* @param stepCode the step code
	* @param start the lower bound of the range of dossier action users
	* @param end the upper bound of the range of dossier action users (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching dossier action users
	*/
	public java.util.List<DossierActionUser> findByDID_SC(long dossierId,
		String stepCode, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<DossierActionUser> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first dossier action user in the ordered set where dossierId = &#63; and stepCode = &#63;.
	*
	* @param dossierId the dossier ID
	* @param stepCode the step code
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching dossier action user
	* @throws NoSuchDossierActionUserException if a matching dossier action user could not be found
	*/
	public DossierActionUser findByDID_SC_First(long dossierId,
		String stepCode,
		com.liferay.portal.kernel.util.OrderByComparator<DossierActionUser> orderByComparator)
		throws NoSuchDossierActionUserException;

	/**
	* Returns the first dossier action user in the ordered set where dossierId = &#63; and stepCode = &#63;.
	*
	* @param dossierId the dossier ID
	* @param stepCode the step code
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching dossier action user, or <code>null</code> if a matching dossier action user could not be found
	*/
	public DossierActionUser fetchByDID_SC_First(long dossierId,
		String stepCode,
		com.liferay.portal.kernel.util.OrderByComparator<DossierActionUser> orderByComparator);

	/**
	* Returns the last dossier action user in the ordered set where dossierId = &#63; and stepCode = &#63;.
	*
	* @param dossierId the dossier ID
	* @param stepCode the step code
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching dossier action user
	* @throws NoSuchDossierActionUserException if a matching dossier action user could not be found
	*/
	public DossierActionUser findByDID_SC_Last(long dossierId, String stepCode,
		com.liferay.portal.kernel.util.OrderByComparator<DossierActionUser> orderByComparator)
		throws NoSuchDossierActionUserException;

	/**
	* Returns the last dossier action user in the ordered set where dossierId = &#63; and stepCode = &#63;.
	*
	* @param dossierId the dossier ID
	* @param stepCode the step code
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching dossier action user, or <code>null</code> if a matching dossier action user could not be found
	*/
	public DossierActionUser fetchByDID_SC_Last(long dossierId,
		String stepCode,
		com.liferay.portal.kernel.util.OrderByComparator<DossierActionUser> orderByComparator);

	/**
	* Returns the dossier action users before and after the current dossier action user in the ordered set where dossierId = &#63; and stepCode = &#63;.
	*
	* @param dossierActionUserPK the primary key of the current dossier action user
	* @param dossierId the dossier ID
	* @param stepCode the step code
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next dossier action user
	* @throws NoSuchDossierActionUserException if a dossier action user with the primary key could not be found
	*/
	public DossierActionUser[] findByDID_SC_PrevAndNext(
		DossierActionUserPK dossierActionUserPK, long dossierId,
		String stepCode,
		com.liferay.portal.kernel.util.OrderByComparator<DossierActionUser> orderByComparator)
		throws NoSuchDossierActionUserException;

	/**
	* Removes all the dossier action users where dossierId = &#63; and stepCode = &#63; from the database.
	*
	* @param dossierId the dossier ID
	* @param stepCode the step code
	*/
	public void removeByDID_SC(long dossierId, String stepCode);

	/**
	* Returns the number of dossier action users where dossierId = &#63; and stepCode = &#63;.
	*
	* @param dossierId the dossier ID
	* @param stepCode the step code
	* @return the number of matching dossier action users
	*/
	public int countByDID_SC(long dossierId, String stepCode);

	/**
	* Caches the dossier action user in the entity cache if it is enabled.
	*
	* @param dossierActionUser the dossier action user
	*/
	public void cacheResult(DossierActionUser dossierActionUser);

	/**
	* Caches the dossier action users in the entity cache if it is enabled.
	*
	* @param dossierActionUsers the dossier action users
	*/
	public void cacheResult(
		java.util.List<DossierActionUser> dossierActionUsers);

	/**
	* Creates a new dossier action user with the primary key. Does not add the dossier action user to the database.
	*
	* @param dossierActionUserPK the primary key for the new dossier action user
	* @return the new dossier action user
	*/
	public DossierActionUser create(DossierActionUserPK dossierActionUserPK);

	/**
	* Removes the dossier action user with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param dossierActionUserPK the primary key of the dossier action user
	* @return the dossier action user that was removed
	* @throws NoSuchDossierActionUserException if a dossier action user with the primary key could not be found
	*/
	public DossierActionUser remove(DossierActionUserPK dossierActionUserPK)
		throws NoSuchDossierActionUserException;

	public DossierActionUser updateImpl(DossierActionUser dossierActionUser);

	/**
	* Returns the dossier action user with the primary key or throws a {@link NoSuchDossierActionUserException} if it could not be found.
	*
	* @param dossierActionUserPK the primary key of the dossier action user
	* @return the dossier action user
	* @throws NoSuchDossierActionUserException if a dossier action user with the primary key could not be found
	*/
	public DossierActionUser findByPrimaryKey(
		DossierActionUserPK dossierActionUserPK)
		throws NoSuchDossierActionUserException;

	/**
	* Returns the dossier action user with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param dossierActionUserPK the primary key of the dossier action user
	* @return the dossier action user, or <code>null</code> if a dossier action user with the primary key could not be found
	*/
	public DossierActionUser fetchByPrimaryKey(
		DossierActionUserPK dossierActionUserPK);

	@Override
	public java.util.Map<java.io.Serializable, DossierActionUser> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the dossier action users.
	*
	* @return the dossier action users
	*/
	public java.util.List<DossierActionUser> findAll();

	/**
	* Returns a range of all the dossier action users.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierActionUserModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of dossier action users
	* @param end the upper bound of the range of dossier action users (not inclusive)
	* @return the range of dossier action users
	*/
	public java.util.List<DossierActionUser> findAll(int start, int end);

	/**
	* Returns an ordered range of all the dossier action users.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierActionUserModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of dossier action users
	* @param end the upper bound of the range of dossier action users (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of dossier action users
	*/
	public java.util.List<DossierActionUser> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<DossierActionUser> orderByComparator);

	/**
	* Returns an ordered range of all the dossier action users.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierActionUserModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of dossier action users
	* @param end the upper bound of the range of dossier action users (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of dossier action users
	*/
	public java.util.List<DossierActionUser> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<DossierActionUser> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the dossier action users from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of dossier action users.
	*
	* @return the number of dossier action users
	*/
	public int countAll();

	@Override
	public java.util.Set<String> getBadColumnNames();

	public java.util.Set<String> getCompoundPKColumnNames();
}