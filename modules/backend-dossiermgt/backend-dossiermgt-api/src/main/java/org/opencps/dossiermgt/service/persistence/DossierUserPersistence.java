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

import org.opencps.dossiermgt.exception.NoSuchDossierUserException;
import org.opencps.dossiermgt.model.DossierUser;

/**
 * The persistence interface for the dossier user service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author huymq
 * @see org.opencps.dossiermgt.service.persistence.impl.DossierUserPersistenceImpl
 * @see DossierUserUtil
 * @generated
 */
@ProviderType
public interface DossierUserPersistence extends BasePersistence<DossierUser> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link DossierUserUtil} to access the dossier user persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Returns all the dossier users where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the matching dossier users
	*/
	public java.util.List<DossierUser> findByUuid(String uuid);

	/**
	* Returns a range of all the dossier users where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierUserModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of dossier users
	* @param end the upper bound of the range of dossier users (not inclusive)
	* @return the range of matching dossier users
	*/
	public java.util.List<DossierUser> findByUuid(String uuid, int start,
		int end);

	/**
	* Returns an ordered range of all the dossier users where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierUserModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of dossier users
	* @param end the upper bound of the range of dossier users (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching dossier users
	*/
	public java.util.List<DossierUser> findByUuid(String uuid, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<DossierUser> orderByComparator);

	/**
	* Returns an ordered range of all the dossier users where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierUserModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of dossier users
	* @param end the upper bound of the range of dossier users (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching dossier users
	*/
	public java.util.List<DossierUser> findByUuid(String uuid, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<DossierUser> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first dossier user in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching dossier user
	* @throws NoSuchDossierUserException if a matching dossier user could not be found
	*/
	public DossierUser findByUuid_First(String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<DossierUser> orderByComparator)
		throws NoSuchDossierUserException;

	/**
	* Returns the first dossier user in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching dossier user, or <code>null</code> if a matching dossier user could not be found
	*/
	public DossierUser fetchByUuid_First(String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<DossierUser> orderByComparator);

	/**
	* Returns the last dossier user in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching dossier user
	* @throws NoSuchDossierUserException if a matching dossier user could not be found
	*/
	public DossierUser findByUuid_Last(String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<DossierUser> orderByComparator)
		throws NoSuchDossierUserException;

	/**
	* Returns the last dossier user in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching dossier user, or <code>null</code> if a matching dossier user could not be found
	*/
	public DossierUser fetchByUuid_Last(String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<DossierUser> orderByComparator);

	/**
	* Returns the dossier users before and after the current dossier user in the ordered set where uuid = &#63;.
	*
	* @param dossierUserPK the primary key of the current dossier user
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next dossier user
	* @throws NoSuchDossierUserException if a dossier user with the primary key could not be found
	*/
	public DossierUser[] findByUuid_PrevAndNext(DossierUserPK dossierUserPK,
		String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<DossierUser> orderByComparator)
		throws NoSuchDossierUserException;

	/**
	* Removes all the dossier users where uuid = &#63; from the database.
	*
	* @param uuid the uuid
	*/
	public void removeByUuid(String uuid);

	/**
	* Returns the number of dossier users where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the number of matching dossier users
	*/
	public int countByUuid(String uuid);

	/**
	* Returns all the dossier users where dossierId = &#63;.
	*
	* @param dossierId the dossier ID
	* @return the matching dossier users
	*/
	public java.util.List<DossierUser> findByDID(long dossierId);

	/**
	* Returns a range of all the dossier users where dossierId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierUserModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param dossierId the dossier ID
	* @param start the lower bound of the range of dossier users
	* @param end the upper bound of the range of dossier users (not inclusive)
	* @return the range of matching dossier users
	*/
	public java.util.List<DossierUser> findByDID(long dossierId, int start,
		int end);

	/**
	* Returns an ordered range of all the dossier users where dossierId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierUserModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param dossierId the dossier ID
	* @param start the lower bound of the range of dossier users
	* @param end the upper bound of the range of dossier users (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching dossier users
	*/
	public java.util.List<DossierUser> findByDID(long dossierId, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<DossierUser> orderByComparator);

	/**
	* Returns an ordered range of all the dossier users where dossierId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierUserModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param dossierId the dossier ID
	* @param start the lower bound of the range of dossier users
	* @param end the upper bound of the range of dossier users (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching dossier users
	*/
	public java.util.List<DossierUser> findByDID(long dossierId, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<DossierUser> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first dossier user in the ordered set where dossierId = &#63;.
	*
	* @param dossierId the dossier ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching dossier user
	* @throws NoSuchDossierUserException if a matching dossier user could not be found
	*/
	public DossierUser findByDID_First(long dossierId,
		com.liferay.portal.kernel.util.OrderByComparator<DossierUser> orderByComparator)
		throws NoSuchDossierUserException;

	/**
	* Returns the first dossier user in the ordered set where dossierId = &#63;.
	*
	* @param dossierId the dossier ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching dossier user, or <code>null</code> if a matching dossier user could not be found
	*/
	public DossierUser fetchByDID_First(long dossierId,
		com.liferay.portal.kernel.util.OrderByComparator<DossierUser> orderByComparator);

	/**
	* Returns the last dossier user in the ordered set where dossierId = &#63;.
	*
	* @param dossierId the dossier ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching dossier user
	* @throws NoSuchDossierUserException if a matching dossier user could not be found
	*/
	public DossierUser findByDID_Last(long dossierId,
		com.liferay.portal.kernel.util.OrderByComparator<DossierUser> orderByComparator)
		throws NoSuchDossierUserException;

	/**
	* Returns the last dossier user in the ordered set where dossierId = &#63;.
	*
	* @param dossierId the dossier ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching dossier user, or <code>null</code> if a matching dossier user could not be found
	*/
	public DossierUser fetchByDID_Last(long dossierId,
		com.liferay.portal.kernel.util.OrderByComparator<DossierUser> orderByComparator);

	/**
	* Returns the dossier users before and after the current dossier user in the ordered set where dossierId = &#63;.
	*
	* @param dossierUserPK the primary key of the current dossier user
	* @param dossierId the dossier ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next dossier user
	* @throws NoSuchDossierUserException if a dossier user with the primary key could not be found
	*/
	public DossierUser[] findByDID_PrevAndNext(DossierUserPK dossierUserPK,
		long dossierId,
		com.liferay.portal.kernel.util.OrderByComparator<DossierUser> orderByComparator)
		throws NoSuchDossierUserException;

	/**
	* Removes all the dossier users where dossierId = &#63; from the database.
	*
	* @param dossierId the dossier ID
	*/
	public void removeByDID(long dossierId);

	/**
	* Returns the number of dossier users where dossierId = &#63;.
	*
	* @param dossierId the dossier ID
	* @return the number of matching dossier users
	*/
	public int countByDID(long dossierId);

	/**
	* Returns the dossier user where dossierId = &#63; and userId = &#63; or throws a {@link NoSuchDossierUserException} if it could not be found.
	*
	* @param dossierId the dossier ID
	* @param userId the user ID
	* @return the matching dossier user
	* @throws NoSuchDossierUserException if a matching dossier user could not be found
	*/
	public DossierUser findByDID_UID(long dossierId, long userId)
		throws NoSuchDossierUserException;

	/**
	* Returns the dossier user where dossierId = &#63; and userId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param dossierId the dossier ID
	* @param userId the user ID
	* @return the matching dossier user, or <code>null</code> if a matching dossier user could not be found
	*/
	public DossierUser fetchByDID_UID(long dossierId, long userId);

	/**
	* Returns the dossier user where dossierId = &#63; and userId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param dossierId the dossier ID
	* @param userId the user ID
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching dossier user, or <code>null</code> if a matching dossier user could not be found
	*/
	public DossierUser fetchByDID_UID(long dossierId, long userId,
		boolean retrieveFromCache);

	/**
	* Removes the dossier user where dossierId = &#63; and userId = &#63; from the database.
	*
	* @param dossierId the dossier ID
	* @param userId the user ID
	* @return the dossier user that was removed
	*/
	public DossierUser removeByDID_UID(long dossierId, long userId)
		throws NoSuchDossierUserException;

	/**
	* Returns the number of dossier users where dossierId = &#63; and userId = &#63;.
	*
	* @param dossierId the dossier ID
	* @param userId the user ID
	* @return the number of matching dossier users
	*/
	public int countByDID_UID(long dossierId, long userId);

	/**
	* Returns all the dossier users where userId = &#63;.
	*
	* @param userId the user ID
	* @return the matching dossier users
	*/
	public java.util.List<DossierUser> findByUID(long userId);

	/**
	* Returns a range of all the dossier users where userId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierUserModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param userId the user ID
	* @param start the lower bound of the range of dossier users
	* @param end the upper bound of the range of dossier users (not inclusive)
	* @return the range of matching dossier users
	*/
	public java.util.List<DossierUser> findByUID(long userId, int start, int end);

	/**
	* Returns an ordered range of all the dossier users where userId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierUserModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param userId the user ID
	* @param start the lower bound of the range of dossier users
	* @param end the upper bound of the range of dossier users (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching dossier users
	*/
	public java.util.List<DossierUser> findByUID(long userId, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<DossierUser> orderByComparator);

	/**
	* Returns an ordered range of all the dossier users where userId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierUserModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param userId the user ID
	* @param start the lower bound of the range of dossier users
	* @param end the upper bound of the range of dossier users (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching dossier users
	*/
	public java.util.List<DossierUser> findByUID(long userId, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<DossierUser> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first dossier user in the ordered set where userId = &#63;.
	*
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching dossier user
	* @throws NoSuchDossierUserException if a matching dossier user could not be found
	*/
	public DossierUser findByUID_First(long userId,
		com.liferay.portal.kernel.util.OrderByComparator<DossierUser> orderByComparator)
		throws NoSuchDossierUserException;

	/**
	* Returns the first dossier user in the ordered set where userId = &#63;.
	*
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching dossier user, or <code>null</code> if a matching dossier user could not be found
	*/
	public DossierUser fetchByUID_First(long userId,
		com.liferay.portal.kernel.util.OrderByComparator<DossierUser> orderByComparator);

	/**
	* Returns the last dossier user in the ordered set where userId = &#63;.
	*
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching dossier user
	* @throws NoSuchDossierUserException if a matching dossier user could not be found
	*/
	public DossierUser findByUID_Last(long userId,
		com.liferay.portal.kernel.util.OrderByComparator<DossierUser> orderByComparator)
		throws NoSuchDossierUserException;

	/**
	* Returns the last dossier user in the ordered set where userId = &#63;.
	*
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching dossier user, or <code>null</code> if a matching dossier user could not be found
	*/
	public DossierUser fetchByUID_Last(long userId,
		com.liferay.portal.kernel.util.OrderByComparator<DossierUser> orderByComparator);

	/**
	* Returns the dossier users before and after the current dossier user in the ordered set where userId = &#63;.
	*
	* @param dossierUserPK the primary key of the current dossier user
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next dossier user
	* @throws NoSuchDossierUserException if a dossier user with the primary key could not be found
	*/
	public DossierUser[] findByUID_PrevAndNext(DossierUserPK dossierUserPK,
		long userId,
		com.liferay.portal.kernel.util.OrderByComparator<DossierUser> orderByComparator)
		throws NoSuchDossierUserException;

	/**
	* Removes all the dossier users where userId = &#63; from the database.
	*
	* @param userId the user ID
	*/
	public void removeByUID(long userId);

	/**
	* Returns the number of dossier users where userId = &#63;.
	*
	* @param userId the user ID
	* @return the number of matching dossier users
	*/
	public int countByUID(long userId);

	/**
	* Caches the dossier user in the entity cache if it is enabled.
	*
	* @param dossierUser the dossier user
	*/
	public void cacheResult(DossierUser dossierUser);

	/**
	* Caches the dossier users in the entity cache if it is enabled.
	*
	* @param dossierUsers the dossier users
	*/
	public void cacheResult(java.util.List<DossierUser> dossierUsers);

	/**
	* Creates a new dossier user with the primary key. Does not add the dossier user to the database.
	*
	* @param dossierUserPK the primary key for the new dossier user
	* @return the new dossier user
	*/
	public DossierUser create(DossierUserPK dossierUserPK);

	/**
	* Removes the dossier user with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param dossierUserPK the primary key of the dossier user
	* @return the dossier user that was removed
	* @throws NoSuchDossierUserException if a dossier user with the primary key could not be found
	*/
	public DossierUser remove(DossierUserPK dossierUserPK)
		throws NoSuchDossierUserException;

	public DossierUser updateImpl(DossierUser dossierUser);

	/**
	* Returns the dossier user with the primary key or throws a {@link NoSuchDossierUserException} if it could not be found.
	*
	* @param dossierUserPK the primary key of the dossier user
	* @return the dossier user
	* @throws NoSuchDossierUserException if a dossier user with the primary key could not be found
	*/
	public DossierUser findByPrimaryKey(DossierUserPK dossierUserPK)
		throws NoSuchDossierUserException;

	/**
	* Returns the dossier user with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param dossierUserPK the primary key of the dossier user
	* @return the dossier user, or <code>null</code> if a dossier user with the primary key could not be found
	*/
	public DossierUser fetchByPrimaryKey(DossierUserPK dossierUserPK);

	@Override
	public java.util.Map<java.io.Serializable, DossierUser> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the dossier users.
	*
	* @return the dossier users
	*/
	public java.util.List<DossierUser> findAll();

	/**
	* Returns a range of all the dossier users.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierUserModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of dossier users
	* @param end the upper bound of the range of dossier users (not inclusive)
	* @return the range of dossier users
	*/
	public java.util.List<DossierUser> findAll(int start, int end);

	/**
	* Returns an ordered range of all the dossier users.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierUserModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of dossier users
	* @param end the upper bound of the range of dossier users (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of dossier users
	*/
	public java.util.List<DossierUser> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<DossierUser> orderByComparator);

	/**
	* Returns an ordered range of all the dossier users.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierUserModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of dossier users
	* @param end the upper bound of the range of dossier users (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of dossier users
	*/
	public java.util.List<DossierUser> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<DossierUser> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the dossier users from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of dossier users.
	*
	* @return the number of dossier users
	*/
	public int countAll();

	@Override
	public java.util.Set<String> getBadColumnNames();

	public java.util.Set<String> getCompoundPKColumnNames();
}