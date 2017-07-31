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

package org.mobilink.backend.usermgt.service.persistence;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.service.persistence.BasePersistence;

import org.mobilink.backend.usermgt.exception.NoSuchOfficeSiteException;
import org.mobilink.backend.usermgt.model.OfficeSite;

/**
 * The persistence interface for the office site service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Binhth
 * @see org.mobilink.backend.usermgt.service.persistence.impl.OfficeSitePersistenceImpl
 * @see OfficeSiteUtil
 * @generated
 */
@ProviderType
public interface OfficeSitePersistence extends BasePersistence<OfficeSite> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link OfficeSiteUtil} to access the office site persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Returns all the office sites where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the matching office sites
	*/
	public java.util.List<OfficeSite> findByUuid(java.lang.String uuid);

	/**
	* Returns a range of all the office sites where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OfficeSiteModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of office sites
	* @param end the upper bound of the range of office sites (not inclusive)
	* @return the range of matching office sites
	*/
	public java.util.List<OfficeSite> findByUuid(java.lang.String uuid,
		int start, int end);

	/**
	* Returns an ordered range of all the office sites where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OfficeSiteModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of office sites
	* @param end the upper bound of the range of office sites (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching office sites
	*/
	public java.util.List<OfficeSite> findByUuid(java.lang.String uuid,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<OfficeSite> orderByComparator);

	/**
	* Returns an ordered range of all the office sites where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OfficeSiteModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of office sites
	* @param end the upper bound of the range of office sites (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching office sites
	*/
	public java.util.List<OfficeSite> findByUuid(java.lang.String uuid,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<OfficeSite> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first office site in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching office site
	* @throws NoSuchOfficeSiteException if a matching office site could not be found
	*/
	public OfficeSite findByUuid_First(java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<OfficeSite> orderByComparator)
		throws NoSuchOfficeSiteException;

	/**
	* Returns the first office site in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching office site, or <code>null</code> if a matching office site could not be found
	*/
	public OfficeSite fetchByUuid_First(java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<OfficeSite> orderByComparator);

	/**
	* Returns the last office site in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching office site
	* @throws NoSuchOfficeSiteException if a matching office site could not be found
	*/
	public OfficeSite findByUuid_Last(java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<OfficeSite> orderByComparator)
		throws NoSuchOfficeSiteException;

	/**
	* Returns the last office site in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching office site, or <code>null</code> if a matching office site could not be found
	*/
	public OfficeSite fetchByUuid_Last(java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<OfficeSite> orderByComparator);

	/**
	* Returns the office sites before and after the current office site in the ordered set where uuid = &#63;.
	*
	* @param officeSiteId the primary key of the current office site
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next office site
	* @throws NoSuchOfficeSiteException if a office site with the primary key could not be found
	*/
	public OfficeSite[] findByUuid_PrevAndNext(long officeSiteId,
		java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<OfficeSite> orderByComparator)
		throws NoSuchOfficeSiteException;

	/**
	* Removes all the office sites where uuid = &#63; from the database.
	*
	* @param uuid the uuid
	*/
	public void removeByUuid(java.lang.String uuid);

	/**
	* Returns the number of office sites where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the number of matching office sites
	*/
	public int countByUuid(java.lang.String uuid);

	/**
	* Returns the office site where uuid = &#63; and groupId = &#63; or throws a {@link NoSuchOfficeSiteException} if it could not be found.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching office site
	* @throws NoSuchOfficeSiteException if a matching office site could not be found
	*/
	public OfficeSite findByUUID_G(java.lang.String uuid, long groupId)
		throws NoSuchOfficeSiteException;

	/**
	* Returns the office site where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching office site, or <code>null</code> if a matching office site could not be found
	*/
	public OfficeSite fetchByUUID_G(java.lang.String uuid, long groupId);

	/**
	* Returns the office site where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching office site, or <code>null</code> if a matching office site could not be found
	*/
	public OfficeSite fetchByUUID_G(java.lang.String uuid, long groupId,
		boolean retrieveFromCache);

	/**
	* Removes the office site where uuid = &#63; and groupId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the office site that was removed
	*/
	public OfficeSite removeByUUID_G(java.lang.String uuid, long groupId)
		throws NoSuchOfficeSiteException;

	/**
	* Returns the number of office sites where uuid = &#63; and groupId = &#63;.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the number of matching office sites
	*/
	public int countByUUID_G(java.lang.String uuid, long groupId);

	/**
	* Returns all the office sites where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the matching office sites
	*/
	public java.util.List<OfficeSite> findByUuid_C(java.lang.String uuid,
		long companyId);

	/**
	* Returns a range of all the office sites where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OfficeSiteModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of office sites
	* @param end the upper bound of the range of office sites (not inclusive)
	* @return the range of matching office sites
	*/
	public java.util.List<OfficeSite> findByUuid_C(java.lang.String uuid,
		long companyId, int start, int end);

	/**
	* Returns an ordered range of all the office sites where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OfficeSiteModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of office sites
	* @param end the upper bound of the range of office sites (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching office sites
	*/
	public java.util.List<OfficeSite> findByUuid_C(java.lang.String uuid,
		long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<OfficeSite> orderByComparator);

	/**
	* Returns an ordered range of all the office sites where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OfficeSiteModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of office sites
	* @param end the upper bound of the range of office sites (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching office sites
	*/
	public java.util.List<OfficeSite> findByUuid_C(java.lang.String uuid,
		long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<OfficeSite> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first office site in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching office site
	* @throws NoSuchOfficeSiteException if a matching office site could not be found
	*/
	public OfficeSite findByUuid_C_First(java.lang.String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<OfficeSite> orderByComparator)
		throws NoSuchOfficeSiteException;

	/**
	* Returns the first office site in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching office site, or <code>null</code> if a matching office site could not be found
	*/
	public OfficeSite fetchByUuid_C_First(java.lang.String uuid,
		long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<OfficeSite> orderByComparator);

	/**
	* Returns the last office site in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching office site
	* @throws NoSuchOfficeSiteException if a matching office site could not be found
	*/
	public OfficeSite findByUuid_C_Last(java.lang.String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<OfficeSite> orderByComparator)
		throws NoSuchOfficeSiteException;

	/**
	* Returns the last office site in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching office site, or <code>null</code> if a matching office site could not be found
	*/
	public OfficeSite fetchByUuid_C_Last(java.lang.String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<OfficeSite> orderByComparator);

	/**
	* Returns the office sites before and after the current office site in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param officeSiteId the primary key of the current office site
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next office site
	* @throws NoSuchOfficeSiteException if a office site with the primary key could not be found
	*/
	public OfficeSite[] findByUuid_C_PrevAndNext(long officeSiteId,
		java.lang.String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<OfficeSite> orderByComparator)
		throws NoSuchOfficeSiteException;

	/**
	* Removes all the office sites where uuid = &#63; and companyId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	*/
	public void removeByUuid_C(java.lang.String uuid, long companyId);

	/**
	* Returns the number of office sites where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the number of matching office sites
	*/
	public int countByUuid_C(java.lang.String uuid, long companyId);

	/**
	* Caches the office site in the entity cache if it is enabled.
	*
	* @param officeSite the office site
	*/
	public void cacheResult(OfficeSite officeSite);

	/**
	* Caches the office sites in the entity cache if it is enabled.
	*
	* @param officeSites the office sites
	*/
	public void cacheResult(java.util.List<OfficeSite> officeSites);

	/**
	* Creates a new office site with the primary key. Does not add the office site to the database.
	*
	* @param officeSiteId the primary key for the new office site
	* @return the new office site
	*/
	public OfficeSite create(long officeSiteId);

	/**
	* Removes the office site with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param officeSiteId the primary key of the office site
	* @return the office site that was removed
	* @throws NoSuchOfficeSiteException if a office site with the primary key could not be found
	*/
	public OfficeSite remove(long officeSiteId)
		throws NoSuchOfficeSiteException;

	public OfficeSite updateImpl(OfficeSite officeSite);

	/**
	* Returns the office site with the primary key or throws a {@link NoSuchOfficeSiteException} if it could not be found.
	*
	* @param officeSiteId the primary key of the office site
	* @return the office site
	* @throws NoSuchOfficeSiteException if a office site with the primary key could not be found
	*/
	public OfficeSite findByPrimaryKey(long officeSiteId)
		throws NoSuchOfficeSiteException;

	/**
	* Returns the office site with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param officeSiteId the primary key of the office site
	* @return the office site, or <code>null</code> if a office site with the primary key could not be found
	*/
	public OfficeSite fetchByPrimaryKey(long officeSiteId);

	@Override
	public java.util.Map<java.io.Serializable, OfficeSite> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the office sites.
	*
	* @return the office sites
	*/
	public java.util.List<OfficeSite> findAll();

	/**
	* Returns a range of all the office sites.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OfficeSiteModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of office sites
	* @param end the upper bound of the range of office sites (not inclusive)
	* @return the range of office sites
	*/
	public java.util.List<OfficeSite> findAll(int start, int end);

	/**
	* Returns an ordered range of all the office sites.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OfficeSiteModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of office sites
	* @param end the upper bound of the range of office sites (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of office sites
	*/
	public java.util.List<OfficeSite> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<OfficeSite> orderByComparator);

	/**
	* Returns an ordered range of all the office sites.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OfficeSiteModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of office sites
	* @param end the upper bound of the range of office sites (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of office sites
	*/
	public java.util.List<OfficeSite> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<OfficeSite> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the office sites from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of office sites.
	*
	* @return the number of office sites
	*/
	public int countAll();

	@Override
	public java.util.Set<java.lang.String> getBadColumnNames();
}