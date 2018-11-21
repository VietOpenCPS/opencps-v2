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

package org.opencps.deliverable.service.persistence;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.service.persistence.BasePersistence;

import org.opencps.deliverable.exception.NoSuchOpenCPSDeliverableException;
import org.opencps.deliverable.model.OpenCPSDeliverable;

/**
 * The persistence interface for the open cps deliverable service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author binhth
 * @see org.opencps.deliverable.service.persistence.impl.OpenCPSDeliverablePersistenceImpl
 * @see OpenCPSDeliverableUtil
 * @generated
 */
@ProviderType
public interface OpenCPSDeliverablePersistence extends BasePersistence<OpenCPSDeliverable> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link OpenCPSDeliverableUtil} to access the open cps deliverable persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Returns all the open cps deliverables where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the matching open cps deliverables
	*/
	public java.util.List<OpenCPSDeliverable> findByUuid(String uuid);

	/**
	* Returns a range of all the open cps deliverables where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OpenCPSDeliverableModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of open cps deliverables
	* @param end the upper bound of the range of open cps deliverables (not inclusive)
	* @return the range of matching open cps deliverables
	*/
	public java.util.List<OpenCPSDeliverable> findByUuid(String uuid,
		int start, int end);

	/**
	* Returns an ordered range of all the open cps deliverables where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OpenCPSDeliverableModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of open cps deliverables
	* @param end the upper bound of the range of open cps deliverables (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching open cps deliverables
	*/
	public java.util.List<OpenCPSDeliverable> findByUuid(String uuid,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<OpenCPSDeliverable> orderByComparator);

	/**
	* Returns an ordered range of all the open cps deliverables where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OpenCPSDeliverableModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of open cps deliverables
	* @param end the upper bound of the range of open cps deliverables (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching open cps deliverables
	*/
	public java.util.List<OpenCPSDeliverable> findByUuid(String uuid,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<OpenCPSDeliverable> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first open cps deliverable in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching open cps deliverable
	* @throws NoSuchOpenCPSDeliverableException if a matching open cps deliverable could not be found
	*/
	public OpenCPSDeliverable findByUuid_First(String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<OpenCPSDeliverable> orderByComparator)
		throws NoSuchOpenCPSDeliverableException;

	/**
	* Returns the first open cps deliverable in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching open cps deliverable, or <code>null</code> if a matching open cps deliverable could not be found
	*/
	public OpenCPSDeliverable fetchByUuid_First(String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<OpenCPSDeliverable> orderByComparator);

	/**
	* Returns the last open cps deliverable in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching open cps deliverable
	* @throws NoSuchOpenCPSDeliverableException if a matching open cps deliverable could not be found
	*/
	public OpenCPSDeliverable findByUuid_Last(String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<OpenCPSDeliverable> orderByComparator)
		throws NoSuchOpenCPSDeliverableException;

	/**
	* Returns the last open cps deliverable in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching open cps deliverable, or <code>null</code> if a matching open cps deliverable could not be found
	*/
	public OpenCPSDeliverable fetchByUuid_Last(String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<OpenCPSDeliverable> orderByComparator);

	/**
	* Returns the open cps deliverables before and after the current open cps deliverable in the ordered set where uuid = &#63;.
	*
	* @param deliverableId the primary key of the current open cps deliverable
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next open cps deliverable
	* @throws NoSuchOpenCPSDeliverableException if a open cps deliverable with the primary key could not be found
	*/
	public OpenCPSDeliverable[] findByUuid_PrevAndNext(long deliverableId,
		String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<OpenCPSDeliverable> orderByComparator)
		throws NoSuchOpenCPSDeliverableException;

	/**
	* Removes all the open cps deliverables where uuid = &#63; from the database.
	*
	* @param uuid the uuid
	*/
	public void removeByUuid(String uuid);

	/**
	* Returns the number of open cps deliverables where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the number of matching open cps deliverables
	*/
	public int countByUuid(String uuid);

	/**
	* Returns the open cps deliverable where uuid = &#63; and groupId = &#63; or throws a {@link NoSuchOpenCPSDeliverableException} if it could not be found.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching open cps deliverable
	* @throws NoSuchOpenCPSDeliverableException if a matching open cps deliverable could not be found
	*/
	public OpenCPSDeliverable findByUUID_G(String uuid, long groupId)
		throws NoSuchOpenCPSDeliverableException;

	/**
	* Returns the open cps deliverable where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching open cps deliverable, or <code>null</code> if a matching open cps deliverable could not be found
	*/
	public OpenCPSDeliverable fetchByUUID_G(String uuid, long groupId);

	/**
	* Returns the open cps deliverable where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching open cps deliverable, or <code>null</code> if a matching open cps deliverable could not be found
	*/
	public OpenCPSDeliverable fetchByUUID_G(String uuid, long groupId,
		boolean retrieveFromCache);

	/**
	* Removes the open cps deliverable where uuid = &#63; and groupId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the open cps deliverable that was removed
	*/
	public OpenCPSDeliverable removeByUUID_G(String uuid, long groupId)
		throws NoSuchOpenCPSDeliverableException;

	/**
	* Returns the number of open cps deliverables where uuid = &#63; and groupId = &#63;.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the number of matching open cps deliverables
	*/
	public int countByUUID_G(String uuid, long groupId);

	/**
	* Returns all the open cps deliverables where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the matching open cps deliverables
	*/
	public java.util.List<OpenCPSDeliverable> findByUuid_C(String uuid,
		long companyId);

	/**
	* Returns a range of all the open cps deliverables where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OpenCPSDeliverableModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of open cps deliverables
	* @param end the upper bound of the range of open cps deliverables (not inclusive)
	* @return the range of matching open cps deliverables
	*/
	public java.util.List<OpenCPSDeliverable> findByUuid_C(String uuid,
		long companyId, int start, int end);

	/**
	* Returns an ordered range of all the open cps deliverables where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OpenCPSDeliverableModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of open cps deliverables
	* @param end the upper bound of the range of open cps deliverables (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching open cps deliverables
	*/
	public java.util.List<OpenCPSDeliverable> findByUuid_C(String uuid,
		long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<OpenCPSDeliverable> orderByComparator);

	/**
	* Returns an ordered range of all the open cps deliverables where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OpenCPSDeliverableModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of open cps deliverables
	* @param end the upper bound of the range of open cps deliverables (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching open cps deliverables
	*/
	public java.util.List<OpenCPSDeliverable> findByUuid_C(String uuid,
		long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<OpenCPSDeliverable> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first open cps deliverable in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching open cps deliverable
	* @throws NoSuchOpenCPSDeliverableException if a matching open cps deliverable could not be found
	*/
	public OpenCPSDeliverable findByUuid_C_First(String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<OpenCPSDeliverable> orderByComparator)
		throws NoSuchOpenCPSDeliverableException;

	/**
	* Returns the first open cps deliverable in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching open cps deliverable, or <code>null</code> if a matching open cps deliverable could not be found
	*/
	public OpenCPSDeliverable fetchByUuid_C_First(String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<OpenCPSDeliverable> orderByComparator);

	/**
	* Returns the last open cps deliverable in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching open cps deliverable
	* @throws NoSuchOpenCPSDeliverableException if a matching open cps deliverable could not be found
	*/
	public OpenCPSDeliverable findByUuid_C_Last(String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<OpenCPSDeliverable> orderByComparator)
		throws NoSuchOpenCPSDeliverableException;

	/**
	* Returns the last open cps deliverable in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching open cps deliverable, or <code>null</code> if a matching open cps deliverable could not be found
	*/
	public OpenCPSDeliverable fetchByUuid_C_Last(String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<OpenCPSDeliverable> orderByComparator);

	/**
	* Returns the open cps deliverables before and after the current open cps deliverable in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param deliverableId the primary key of the current open cps deliverable
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next open cps deliverable
	* @throws NoSuchOpenCPSDeliverableException if a open cps deliverable with the primary key could not be found
	*/
	public OpenCPSDeliverable[] findByUuid_C_PrevAndNext(long deliverableId,
		String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<OpenCPSDeliverable> orderByComparator)
		throws NoSuchOpenCPSDeliverableException;

	/**
	* Removes all the open cps deliverables where uuid = &#63; and companyId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	*/
	public void removeByUuid_C(String uuid, long companyId);

	/**
	* Returns the number of open cps deliverables where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the number of matching open cps deliverables
	*/
	public int countByUuid_C(String uuid, long companyId);

	/**
	* Caches the open cps deliverable in the entity cache if it is enabled.
	*
	* @param openCPSDeliverable the open cps deliverable
	*/
	public void cacheResult(OpenCPSDeliverable openCPSDeliverable);

	/**
	* Caches the open cps deliverables in the entity cache if it is enabled.
	*
	* @param openCPSDeliverables the open cps deliverables
	*/
	public void cacheResult(
		java.util.List<OpenCPSDeliverable> openCPSDeliverables);

	/**
	* Creates a new open cps deliverable with the primary key. Does not add the open cps deliverable to the database.
	*
	* @param deliverableId the primary key for the new open cps deliverable
	* @return the new open cps deliverable
	*/
	public OpenCPSDeliverable create(long deliverableId);

	/**
	* Removes the open cps deliverable with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param deliverableId the primary key of the open cps deliverable
	* @return the open cps deliverable that was removed
	* @throws NoSuchOpenCPSDeliverableException if a open cps deliverable with the primary key could not be found
	*/
	public OpenCPSDeliverable remove(long deliverableId)
		throws NoSuchOpenCPSDeliverableException;

	public OpenCPSDeliverable updateImpl(OpenCPSDeliverable openCPSDeliverable);

	/**
	* Returns the open cps deliverable with the primary key or throws a {@link NoSuchOpenCPSDeliverableException} if it could not be found.
	*
	* @param deliverableId the primary key of the open cps deliverable
	* @return the open cps deliverable
	* @throws NoSuchOpenCPSDeliverableException if a open cps deliverable with the primary key could not be found
	*/
	public OpenCPSDeliverable findByPrimaryKey(long deliverableId)
		throws NoSuchOpenCPSDeliverableException;

	/**
	* Returns the open cps deliverable with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param deliverableId the primary key of the open cps deliverable
	* @return the open cps deliverable, or <code>null</code> if a open cps deliverable with the primary key could not be found
	*/
	public OpenCPSDeliverable fetchByPrimaryKey(long deliverableId);

	@Override
	public java.util.Map<java.io.Serializable, OpenCPSDeliverable> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the open cps deliverables.
	*
	* @return the open cps deliverables
	*/
	public java.util.List<OpenCPSDeliverable> findAll();

	/**
	* Returns a range of all the open cps deliverables.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OpenCPSDeliverableModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of open cps deliverables
	* @param end the upper bound of the range of open cps deliverables (not inclusive)
	* @return the range of open cps deliverables
	*/
	public java.util.List<OpenCPSDeliverable> findAll(int start, int end);

	/**
	* Returns an ordered range of all the open cps deliverables.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OpenCPSDeliverableModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of open cps deliverables
	* @param end the upper bound of the range of open cps deliverables (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of open cps deliverables
	*/
	public java.util.List<OpenCPSDeliverable> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<OpenCPSDeliverable> orderByComparator);

	/**
	* Returns an ordered range of all the open cps deliverables.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OpenCPSDeliverableModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of open cps deliverables
	* @param end the upper bound of the range of open cps deliverables (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of open cps deliverables
	*/
	public java.util.List<OpenCPSDeliverable> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<OpenCPSDeliverable> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the open cps deliverables from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of open cps deliverables.
	*
	* @return the number of open cps deliverables
	*/
	public int countAll();

	@Override
	public java.util.Set<String> getBadColumnNames();
}