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

import org.opencps.backend.dossiermgt.exception.NoSuchDossierLogException;
import org.opencps.backend.dossiermgt.model.DossierLog;

/**
 * The persistence interface for the dossier log service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author huymq
 * @see org.opencps.backend.dossiermgt.service.persistence.impl.DossierLogPersistenceImpl
 * @see DossierLogUtil
 * @generated
 */
@ProviderType
public interface DossierLogPersistence extends BasePersistence<DossierLog> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link DossierLogUtil} to access the dossier log persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Returns all the dossier logs where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the matching dossier logs
	*/
	public java.util.List<DossierLog> findByUuid(java.lang.String uuid);

	/**
	* Returns a range of all the dossier logs where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierLogModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of dossier logs
	* @param end the upper bound of the range of dossier logs (not inclusive)
	* @return the range of matching dossier logs
	*/
	public java.util.List<DossierLog> findByUuid(java.lang.String uuid,
		int start, int end);

	/**
	* Returns an ordered range of all the dossier logs where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierLogModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of dossier logs
	* @param end the upper bound of the range of dossier logs (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching dossier logs
	*/
	public java.util.List<DossierLog> findByUuid(java.lang.String uuid,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<DossierLog> orderByComparator);

	/**
	* Returns an ordered range of all the dossier logs where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierLogModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of dossier logs
	* @param end the upper bound of the range of dossier logs (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching dossier logs
	*/
	public java.util.List<DossierLog> findByUuid(java.lang.String uuid,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<DossierLog> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first dossier log in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching dossier log
	* @throws NoSuchDossierLogException if a matching dossier log could not be found
	*/
	public DossierLog findByUuid_First(java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<DossierLog> orderByComparator)
		throws NoSuchDossierLogException;

	/**
	* Returns the first dossier log in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching dossier log, or <code>null</code> if a matching dossier log could not be found
	*/
	public DossierLog fetchByUuid_First(java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<DossierLog> orderByComparator);

	/**
	* Returns the last dossier log in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching dossier log
	* @throws NoSuchDossierLogException if a matching dossier log could not be found
	*/
	public DossierLog findByUuid_Last(java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<DossierLog> orderByComparator)
		throws NoSuchDossierLogException;

	/**
	* Returns the last dossier log in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching dossier log, or <code>null</code> if a matching dossier log could not be found
	*/
	public DossierLog fetchByUuid_Last(java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<DossierLog> orderByComparator);

	/**
	* Returns the dossier logs before and after the current dossier log in the ordered set where uuid = &#63;.
	*
	* @param dossierLogId the primary key of the current dossier log
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next dossier log
	* @throws NoSuchDossierLogException if a dossier log with the primary key could not be found
	*/
	public DossierLog[] findByUuid_PrevAndNext(long dossierLogId,
		java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<DossierLog> orderByComparator)
		throws NoSuchDossierLogException;

	/**
	* Removes all the dossier logs where uuid = &#63; from the database.
	*
	* @param uuid the uuid
	*/
	public void removeByUuid(java.lang.String uuid);

	/**
	* Returns the number of dossier logs where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the number of matching dossier logs
	*/
	public int countByUuid(java.lang.String uuid);

	/**
	* Returns the dossier log where uuid = &#63; and groupId = &#63; or throws a {@link NoSuchDossierLogException} if it could not be found.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching dossier log
	* @throws NoSuchDossierLogException if a matching dossier log could not be found
	*/
	public DossierLog findByUUID_G(java.lang.String uuid, long groupId)
		throws NoSuchDossierLogException;

	/**
	* Returns the dossier log where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching dossier log, or <code>null</code> if a matching dossier log could not be found
	*/
	public DossierLog fetchByUUID_G(java.lang.String uuid, long groupId);

	/**
	* Returns the dossier log where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching dossier log, or <code>null</code> if a matching dossier log could not be found
	*/
	public DossierLog fetchByUUID_G(java.lang.String uuid, long groupId,
		boolean retrieveFromCache);

	/**
	* Removes the dossier log where uuid = &#63; and groupId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the dossier log that was removed
	*/
	public DossierLog removeByUUID_G(java.lang.String uuid, long groupId)
		throws NoSuchDossierLogException;

	/**
	* Returns the number of dossier logs where uuid = &#63; and groupId = &#63;.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the number of matching dossier logs
	*/
	public int countByUUID_G(java.lang.String uuid, long groupId);

	/**
	* Returns all the dossier logs where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the matching dossier logs
	*/
	public java.util.List<DossierLog> findByUuid_C(java.lang.String uuid,
		long companyId);

	/**
	* Returns a range of all the dossier logs where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierLogModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of dossier logs
	* @param end the upper bound of the range of dossier logs (not inclusive)
	* @return the range of matching dossier logs
	*/
	public java.util.List<DossierLog> findByUuid_C(java.lang.String uuid,
		long companyId, int start, int end);

	/**
	* Returns an ordered range of all the dossier logs where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierLogModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of dossier logs
	* @param end the upper bound of the range of dossier logs (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching dossier logs
	*/
	public java.util.List<DossierLog> findByUuid_C(java.lang.String uuid,
		long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<DossierLog> orderByComparator);

	/**
	* Returns an ordered range of all the dossier logs where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierLogModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of dossier logs
	* @param end the upper bound of the range of dossier logs (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching dossier logs
	*/
	public java.util.List<DossierLog> findByUuid_C(java.lang.String uuid,
		long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<DossierLog> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first dossier log in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching dossier log
	* @throws NoSuchDossierLogException if a matching dossier log could not be found
	*/
	public DossierLog findByUuid_C_First(java.lang.String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<DossierLog> orderByComparator)
		throws NoSuchDossierLogException;

	/**
	* Returns the first dossier log in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching dossier log, or <code>null</code> if a matching dossier log could not be found
	*/
	public DossierLog fetchByUuid_C_First(java.lang.String uuid,
		long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<DossierLog> orderByComparator);

	/**
	* Returns the last dossier log in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching dossier log
	* @throws NoSuchDossierLogException if a matching dossier log could not be found
	*/
	public DossierLog findByUuid_C_Last(java.lang.String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<DossierLog> orderByComparator)
		throws NoSuchDossierLogException;

	/**
	* Returns the last dossier log in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching dossier log, or <code>null</code> if a matching dossier log could not be found
	*/
	public DossierLog fetchByUuid_C_Last(java.lang.String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<DossierLog> orderByComparator);

	/**
	* Returns the dossier logs before and after the current dossier log in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param dossierLogId the primary key of the current dossier log
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next dossier log
	* @throws NoSuchDossierLogException if a dossier log with the primary key could not be found
	*/
	public DossierLog[] findByUuid_C_PrevAndNext(long dossierLogId,
		java.lang.String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<DossierLog> orderByComparator)
		throws NoSuchDossierLogException;

	/**
	* Removes all the dossier logs where uuid = &#63; and companyId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	*/
	public void removeByUuid_C(java.lang.String uuid, long companyId);

	/**
	* Returns the number of dossier logs where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the number of matching dossier logs
	*/
	public int countByUuid_C(java.lang.String uuid, long companyId);

	/**
	* Caches the dossier log in the entity cache if it is enabled.
	*
	* @param dossierLog the dossier log
	*/
	public void cacheResult(DossierLog dossierLog);

	/**
	* Caches the dossier logs in the entity cache if it is enabled.
	*
	* @param dossierLogs the dossier logs
	*/
	public void cacheResult(java.util.List<DossierLog> dossierLogs);

	/**
	* Creates a new dossier log with the primary key. Does not add the dossier log to the database.
	*
	* @param dossierLogId the primary key for the new dossier log
	* @return the new dossier log
	*/
	public DossierLog create(long dossierLogId);

	/**
	* Removes the dossier log with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param dossierLogId the primary key of the dossier log
	* @return the dossier log that was removed
	* @throws NoSuchDossierLogException if a dossier log with the primary key could not be found
	*/
	public DossierLog remove(long dossierLogId)
		throws NoSuchDossierLogException;

	public DossierLog updateImpl(DossierLog dossierLog);

	/**
	* Returns the dossier log with the primary key or throws a {@link NoSuchDossierLogException} if it could not be found.
	*
	* @param dossierLogId the primary key of the dossier log
	* @return the dossier log
	* @throws NoSuchDossierLogException if a dossier log with the primary key could not be found
	*/
	public DossierLog findByPrimaryKey(long dossierLogId)
		throws NoSuchDossierLogException;

	/**
	* Returns the dossier log with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param dossierLogId the primary key of the dossier log
	* @return the dossier log, or <code>null</code> if a dossier log with the primary key could not be found
	*/
	public DossierLog fetchByPrimaryKey(long dossierLogId);

	@Override
	public java.util.Map<java.io.Serializable, DossierLog> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the dossier logs.
	*
	* @return the dossier logs
	*/
	public java.util.List<DossierLog> findAll();

	/**
	* Returns a range of all the dossier logs.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierLogModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of dossier logs
	* @param end the upper bound of the range of dossier logs (not inclusive)
	* @return the range of dossier logs
	*/
	public java.util.List<DossierLog> findAll(int start, int end);

	/**
	* Returns an ordered range of all the dossier logs.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierLogModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of dossier logs
	* @param end the upper bound of the range of dossier logs (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of dossier logs
	*/
	public java.util.List<DossierLog> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<DossierLog> orderByComparator);

	/**
	* Returns an ordered range of all the dossier logs.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierLogModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of dossier logs
	* @param end the upper bound of the range of dossier logs (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of dossier logs
	*/
	public java.util.List<DossierLog> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<DossierLog> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the dossier logs from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of dossier logs.
	*
	* @return the number of dossier logs
	*/
	public int countAll();

	@Override
	public java.util.Set<java.lang.String> getBadColumnNames();
}