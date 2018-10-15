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

import org.opencps.dossiermgt.exception.NoSuchProcessSequenceException;
import org.opencps.dossiermgt.model.ProcessSequence;

/**
 * The persistence interface for the process sequence service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author huymq
 * @see org.opencps.dossiermgt.service.persistence.impl.ProcessSequencePersistenceImpl
 * @see ProcessSequenceUtil
 * @generated
 */
@ProviderType
public interface ProcessSequencePersistence extends BasePersistence<ProcessSequence> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link ProcessSequenceUtil} to access the process sequence persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Returns all the process sequences where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the matching process sequences
	*/
	public java.util.List<ProcessSequence> findByUuid(String uuid);

	/**
	* Returns a range of all the process sequences where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ProcessSequenceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of process sequences
	* @param end the upper bound of the range of process sequences (not inclusive)
	* @return the range of matching process sequences
	*/
	public java.util.List<ProcessSequence> findByUuid(String uuid, int start,
		int end);

	/**
	* Returns an ordered range of all the process sequences where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ProcessSequenceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of process sequences
	* @param end the upper bound of the range of process sequences (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching process sequences
	*/
	public java.util.List<ProcessSequence> findByUuid(String uuid, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<ProcessSequence> orderByComparator);

	/**
	* Returns an ordered range of all the process sequences where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ProcessSequenceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of process sequences
	* @param end the upper bound of the range of process sequences (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching process sequences
	*/
	public java.util.List<ProcessSequence> findByUuid(String uuid, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<ProcessSequence> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first process sequence in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching process sequence
	* @throws NoSuchProcessSequenceException if a matching process sequence could not be found
	*/
	public ProcessSequence findByUuid_First(String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<ProcessSequence> orderByComparator)
		throws NoSuchProcessSequenceException;

	/**
	* Returns the first process sequence in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching process sequence, or <code>null</code> if a matching process sequence could not be found
	*/
	public ProcessSequence fetchByUuid_First(String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<ProcessSequence> orderByComparator);

	/**
	* Returns the last process sequence in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching process sequence
	* @throws NoSuchProcessSequenceException if a matching process sequence could not be found
	*/
	public ProcessSequence findByUuid_Last(String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<ProcessSequence> orderByComparator)
		throws NoSuchProcessSequenceException;

	/**
	* Returns the last process sequence in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching process sequence, or <code>null</code> if a matching process sequence could not be found
	*/
	public ProcessSequence fetchByUuid_Last(String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<ProcessSequence> orderByComparator);

	/**
	* Returns the process sequences before and after the current process sequence in the ordered set where uuid = &#63;.
	*
	* @param processSequenceId the primary key of the current process sequence
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next process sequence
	* @throws NoSuchProcessSequenceException if a process sequence with the primary key could not be found
	*/
	public ProcessSequence[] findByUuid_PrevAndNext(long processSequenceId,
		String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<ProcessSequence> orderByComparator)
		throws NoSuchProcessSequenceException;

	/**
	* Removes all the process sequences where uuid = &#63; from the database.
	*
	* @param uuid the uuid
	*/
	public void removeByUuid(String uuid);

	/**
	* Returns the number of process sequences where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the number of matching process sequences
	*/
	public int countByUuid(String uuid);

	/**
	* Returns the process sequence where uuid = &#63; and groupId = &#63; or throws a {@link NoSuchProcessSequenceException} if it could not be found.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching process sequence
	* @throws NoSuchProcessSequenceException if a matching process sequence could not be found
	*/
	public ProcessSequence findByUUID_G(String uuid, long groupId)
		throws NoSuchProcessSequenceException;

	/**
	* Returns the process sequence where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching process sequence, or <code>null</code> if a matching process sequence could not be found
	*/
	public ProcessSequence fetchByUUID_G(String uuid, long groupId);

	/**
	* Returns the process sequence where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching process sequence, or <code>null</code> if a matching process sequence could not be found
	*/
	public ProcessSequence fetchByUUID_G(String uuid, long groupId,
		boolean retrieveFromCache);

	/**
	* Removes the process sequence where uuid = &#63; and groupId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the process sequence that was removed
	*/
	public ProcessSequence removeByUUID_G(String uuid, long groupId)
		throws NoSuchProcessSequenceException;

	/**
	* Returns the number of process sequences where uuid = &#63; and groupId = &#63;.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the number of matching process sequences
	*/
	public int countByUUID_G(String uuid, long groupId);

	/**
	* Returns all the process sequences where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the matching process sequences
	*/
	public java.util.List<ProcessSequence> findByUuid_C(String uuid,
		long companyId);

	/**
	* Returns a range of all the process sequences where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ProcessSequenceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of process sequences
	* @param end the upper bound of the range of process sequences (not inclusive)
	* @return the range of matching process sequences
	*/
	public java.util.List<ProcessSequence> findByUuid_C(String uuid,
		long companyId, int start, int end);

	/**
	* Returns an ordered range of all the process sequences where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ProcessSequenceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of process sequences
	* @param end the upper bound of the range of process sequences (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching process sequences
	*/
	public java.util.List<ProcessSequence> findByUuid_C(String uuid,
		long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ProcessSequence> orderByComparator);

	/**
	* Returns an ordered range of all the process sequences where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ProcessSequenceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of process sequences
	* @param end the upper bound of the range of process sequences (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching process sequences
	*/
	public java.util.List<ProcessSequence> findByUuid_C(String uuid,
		long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ProcessSequence> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first process sequence in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching process sequence
	* @throws NoSuchProcessSequenceException if a matching process sequence could not be found
	*/
	public ProcessSequence findByUuid_C_First(String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<ProcessSequence> orderByComparator)
		throws NoSuchProcessSequenceException;

	/**
	* Returns the first process sequence in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching process sequence, or <code>null</code> if a matching process sequence could not be found
	*/
	public ProcessSequence fetchByUuid_C_First(String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<ProcessSequence> orderByComparator);

	/**
	* Returns the last process sequence in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching process sequence
	* @throws NoSuchProcessSequenceException if a matching process sequence could not be found
	*/
	public ProcessSequence findByUuid_C_Last(String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<ProcessSequence> orderByComparator)
		throws NoSuchProcessSequenceException;

	/**
	* Returns the last process sequence in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching process sequence, or <code>null</code> if a matching process sequence could not be found
	*/
	public ProcessSequence fetchByUuid_C_Last(String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<ProcessSequence> orderByComparator);

	/**
	* Returns the process sequences before and after the current process sequence in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param processSequenceId the primary key of the current process sequence
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next process sequence
	* @throws NoSuchProcessSequenceException if a process sequence with the primary key could not be found
	*/
	public ProcessSequence[] findByUuid_C_PrevAndNext(long processSequenceId,
		String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<ProcessSequence> orderByComparator)
		throws NoSuchProcessSequenceException;

	/**
	* Removes all the process sequences where uuid = &#63; and companyId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	*/
	public void removeByUuid_C(String uuid, long companyId);

	/**
	* Returns the number of process sequences where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the number of matching process sequences
	*/
	public int countByUuid_C(String uuid, long companyId);

	/**
	* Returns all the process sequences where groupId = &#63; and sequenceNo = &#63;.
	*
	* @param groupId the group ID
	* @param sequenceNo the sequence no
	* @return the matching process sequences
	*/
	public java.util.List<ProcessSequence> findByG_SN(long groupId,
		String sequenceNo);

	/**
	* Returns a range of all the process sequences where groupId = &#63; and sequenceNo = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ProcessSequenceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param sequenceNo the sequence no
	* @param start the lower bound of the range of process sequences
	* @param end the upper bound of the range of process sequences (not inclusive)
	* @return the range of matching process sequences
	*/
	public java.util.List<ProcessSequence> findByG_SN(long groupId,
		String sequenceNo, int start, int end);

	/**
	* Returns an ordered range of all the process sequences where groupId = &#63; and sequenceNo = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ProcessSequenceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param sequenceNo the sequence no
	* @param start the lower bound of the range of process sequences
	* @param end the upper bound of the range of process sequences (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching process sequences
	*/
	public java.util.List<ProcessSequence> findByG_SN(long groupId,
		String sequenceNo, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ProcessSequence> orderByComparator);

	/**
	* Returns an ordered range of all the process sequences where groupId = &#63; and sequenceNo = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ProcessSequenceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param sequenceNo the sequence no
	* @param start the lower bound of the range of process sequences
	* @param end the upper bound of the range of process sequences (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching process sequences
	*/
	public java.util.List<ProcessSequence> findByG_SN(long groupId,
		String sequenceNo, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ProcessSequence> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first process sequence in the ordered set where groupId = &#63; and sequenceNo = &#63;.
	*
	* @param groupId the group ID
	* @param sequenceNo the sequence no
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching process sequence
	* @throws NoSuchProcessSequenceException if a matching process sequence could not be found
	*/
	public ProcessSequence findByG_SN_First(long groupId, String sequenceNo,
		com.liferay.portal.kernel.util.OrderByComparator<ProcessSequence> orderByComparator)
		throws NoSuchProcessSequenceException;

	/**
	* Returns the first process sequence in the ordered set where groupId = &#63; and sequenceNo = &#63;.
	*
	* @param groupId the group ID
	* @param sequenceNo the sequence no
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching process sequence, or <code>null</code> if a matching process sequence could not be found
	*/
	public ProcessSequence fetchByG_SN_First(long groupId, String sequenceNo,
		com.liferay.portal.kernel.util.OrderByComparator<ProcessSequence> orderByComparator);

	/**
	* Returns the last process sequence in the ordered set where groupId = &#63; and sequenceNo = &#63;.
	*
	* @param groupId the group ID
	* @param sequenceNo the sequence no
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching process sequence
	* @throws NoSuchProcessSequenceException if a matching process sequence could not be found
	*/
	public ProcessSequence findByG_SN_Last(long groupId, String sequenceNo,
		com.liferay.portal.kernel.util.OrderByComparator<ProcessSequence> orderByComparator)
		throws NoSuchProcessSequenceException;

	/**
	* Returns the last process sequence in the ordered set where groupId = &#63; and sequenceNo = &#63;.
	*
	* @param groupId the group ID
	* @param sequenceNo the sequence no
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching process sequence, or <code>null</code> if a matching process sequence could not be found
	*/
	public ProcessSequence fetchByG_SN_Last(long groupId, String sequenceNo,
		com.liferay.portal.kernel.util.OrderByComparator<ProcessSequence> orderByComparator);

	/**
	* Returns the process sequences before and after the current process sequence in the ordered set where groupId = &#63; and sequenceNo = &#63;.
	*
	* @param processSequenceId the primary key of the current process sequence
	* @param groupId the group ID
	* @param sequenceNo the sequence no
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next process sequence
	* @throws NoSuchProcessSequenceException if a process sequence with the primary key could not be found
	*/
	public ProcessSequence[] findByG_SN_PrevAndNext(long processSequenceId,
		long groupId, String sequenceNo,
		com.liferay.portal.kernel.util.OrderByComparator<ProcessSequence> orderByComparator)
		throws NoSuchProcessSequenceException;

	/**
	* Removes all the process sequences where groupId = &#63; and sequenceNo = &#63; from the database.
	*
	* @param groupId the group ID
	* @param sequenceNo the sequence no
	*/
	public void removeByG_SN(long groupId, String sequenceNo);

	/**
	* Returns the number of process sequences where groupId = &#63; and sequenceNo = &#63;.
	*
	* @param groupId the group ID
	* @param sequenceNo the sequence no
	* @return the number of matching process sequences
	*/
	public int countByG_SN(long groupId, String sequenceNo);

	/**
	* Returns all the process sequences where groupId = &#63; and serviceProcessId = &#63;.
	*
	* @param groupId the group ID
	* @param serviceProcessId the service process ID
	* @return the matching process sequences
	*/
	public java.util.List<ProcessSequence> findByF_GID_SID(long groupId,
		long serviceProcessId);

	/**
	* Returns a range of all the process sequences where groupId = &#63; and serviceProcessId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ProcessSequenceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param serviceProcessId the service process ID
	* @param start the lower bound of the range of process sequences
	* @param end the upper bound of the range of process sequences (not inclusive)
	* @return the range of matching process sequences
	*/
	public java.util.List<ProcessSequence> findByF_GID_SID(long groupId,
		long serviceProcessId, int start, int end);

	/**
	* Returns an ordered range of all the process sequences where groupId = &#63; and serviceProcessId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ProcessSequenceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param serviceProcessId the service process ID
	* @param start the lower bound of the range of process sequences
	* @param end the upper bound of the range of process sequences (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching process sequences
	*/
	public java.util.List<ProcessSequence> findByF_GID_SID(long groupId,
		long serviceProcessId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ProcessSequence> orderByComparator);

	/**
	* Returns an ordered range of all the process sequences where groupId = &#63; and serviceProcessId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ProcessSequenceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param serviceProcessId the service process ID
	* @param start the lower bound of the range of process sequences
	* @param end the upper bound of the range of process sequences (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching process sequences
	*/
	public java.util.List<ProcessSequence> findByF_GID_SID(long groupId,
		long serviceProcessId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ProcessSequence> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first process sequence in the ordered set where groupId = &#63; and serviceProcessId = &#63;.
	*
	* @param groupId the group ID
	* @param serviceProcessId the service process ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching process sequence
	* @throws NoSuchProcessSequenceException if a matching process sequence could not be found
	*/
	public ProcessSequence findByF_GID_SID_First(long groupId,
		long serviceProcessId,
		com.liferay.portal.kernel.util.OrderByComparator<ProcessSequence> orderByComparator)
		throws NoSuchProcessSequenceException;

	/**
	* Returns the first process sequence in the ordered set where groupId = &#63; and serviceProcessId = &#63;.
	*
	* @param groupId the group ID
	* @param serviceProcessId the service process ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching process sequence, or <code>null</code> if a matching process sequence could not be found
	*/
	public ProcessSequence fetchByF_GID_SID_First(long groupId,
		long serviceProcessId,
		com.liferay.portal.kernel.util.OrderByComparator<ProcessSequence> orderByComparator);

	/**
	* Returns the last process sequence in the ordered set where groupId = &#63; and serviceProcessId = &#63;.
	*
	* @param groupId the group ID
	* @param serviceProcessId the service process ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching process sequence
	* @throws NoSuchProcessSequenceException if a matching process sequence could not be found
	*/
	public ProcessSequence findByF_GID_SID_Last(long groupId,
		long serviceProcessId,
		com.liferay.portal.kernel.util.OrderByComparator<ProcessSequence> orderByComparator)
		throws NoSuchProcessSequenceException;

	/**
	* Returns the last process sequence in the ordered set where groupId = &#63; and serviceProcessId = &#63;.
	*
	* @param groupId the group ID
	* @param serviceProcessId the service process ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching process sequence, or <code>null</code> if a matching process sequence could not be found
	*/
	public ProcessSequence fetchByF_GID_SID_Last(long groupId,
		long serviceProcessId,
		com.liferay.portal.kernel.util.OrderByComparator<ProcessSequence> orderByComparator);

	/**
	* Returns the process sequences before and after the current process sequence in the ordered set where groupId = &#63; and serviceProcessId = &#63;.
	*
	* @param processSequenceId the primary key of the current process sequence
	* @param groupId the group ID
	* @param serviceProcessId the service process ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next process sequence
	* @throws NoSuchProcessSequenceException if a process sequence with the primary key could not be found
	*/
	public ProcessSequence[] findByF_GID_SID_PrevAndNext(
		long processSequenceId, long groupId, long serviceProcessId,
		com.liferay.portal.kernel.util.OrderByComparator<ProcessSequence> orderByComparator)
		throws NoSuchProcessSequenceException;

	/**
	* Removes all the process sequences where groupId = &#63; and serviceProcessId = &#63; from the database.
	*
	* @param groupId the group ID
	* @param serviceProcessId the service process ID
	*/
	public void removeByF_GID_SID(long groupId, long serviceProcessId);

	/**
	* Returns the number of process sequences where groupId = &#63; and serviceProcessId = &#63;.
	*
	* @param groupId the group ID
	* @param serviceProcessId the service process ID
	* @return the number of matching process sequences
	*/
	public int countByF_GID_SID(long groupId, long serviceProcessId);

	/**
	* Returns the process sequence where groupId = &#63; and serviceProcessId = &#63; and sequenceNo = &#63; or throws a {@link NoSuchProcessSequenceException} if it could not be found.
	*
	* @param groupId the group ID
	* @param serviceProcessId the service process ID
	* @param sequenceNo the sequence no
	* @return the matching process sequence
	* @throws NoSuchProcessSequenceException if a matching process sequence could not be found
	*/
	public ProcessSequence findByF_GID_SID_SNO(long groupId,
		long serviceProcessId, String sequenceNo)
		throws NoSuchProcessSequenceException;

	/**
	* Returns the process sequence where groupId = &#63; and serviceProcessId = &#63; and sequenceNo = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param groupId the group ID
	* @param serviceProcessId the service process ID
	* @param sequenceNo the sequence no
	* @return the matching process sequence, or <code>null</code> if a matching process sequence could not be found
	*/
	public ProcessSequence fetchByF_GID_SID_SNO(long groupId,
		long serviceProcessId, String sequenceNo);

	/**
	* Returns the process sequence where groupId = &#63; and serviceProcessId = &#63; and sequenceNo = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param groupId the group ID
	* @param serviceProcessId the service process ID
	* @param sequenceNo the sequence no
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching process sequence, or <code>null</code> if a matching process sequence could not be found
	*/
	public ProcessSequence fetchByF_GID_SID_SNO(long groupId,
		long serviceProcessId, String sequenceNo, boolean retrieveFromCache);

	/**
	* Removes the process sequence where groupId = &#63; and serviceProcessId = &#63; and sequenceNo = &#63; from the database.
	*
	* @param groupId the group ID
	* @param serviceProcessId the service process ID
	* @param sequenceNo the sequence no
	* @return the process sequence that was removed
	*/
	public ProcessSequence removeByF_GID_SID_SNO(long groupId,
		long serviceProcessId, String sequenceNo)
		throws NoSuchProcessSequenceException;

	/**
	* Returns the number of process sequences where groupId = &#63; and serviceProcessId = &#63; and sequenceNo = &#63;.
	*
	* @param groupId the group ID
	* @param serviceProcessId the service process ID
	* @param sequenceNo the sequence no
	* @return the number of matching process sequences
	*/
	public int countByF_GID_SID_SNO(long groupId, long serviceProcessId,
		String sequenceNo);

	/**
	* Caches the process sequence in the entity cache if it is enabled.
	*
	* @param processSequence the process sequence
	*/
	public void cacheResult(ProcessSequence processSequence);

	/**
	* Caches the process sequences in the entity cache if it is enabled.
	*
	* @param processSequences the process sequences
	*/
	public void cacheResult(java.util.List<ProcessSequence> processSequences);

	/**
	* Creates a new process sequence with the primary key. Does not add the process sequence to the database.
	*
	* @param processSequenceId the primary key for the new process sequence
	* @return the new process sequence
	*/
	public ProcessSequence create(long processSequenceId);

	/**
	* Removes the process sequence with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param processSequenceId the primary key of the process sequence
	* @return the process sequence that was removed
	* @throws NoSuchProcessSequenceException if a process sequence with the primary key could not be found
	*/
	public ProcessSequence remove(long processSequenceId)
		throws NoSuchProcessSequenceException;

	public ProcessSequence updateImpl(ProcessSequence processSequence);

	/**
	* Returns the process sequence with the primary key or throws a {@link NoSuchProcessSequenceException} if it could not be found.
	*
	* @param processSequenceId the primary key of the process sequence
	* @return the process sequence
	* @throws NoSuchProcessSequenceException if a process sequence with the primary key could not be found
	*/
	public ProcessSequence findByPrimaryKey(long processSequenceId)
		throws NoSuchProcessSequenceException;

	/**
	* Returns the process sequence with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param processSequenceId the primary key of the process sequence
	* @return the process sequence, or <code>null</code> if a process sequence with the primary key could not be found
	*/
	public ProcessSequence fetchByPrimaryKey(long processSequenceId);

	@Override
	public java.util.Map<java.io.Serializable, ProcessSequence> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the process sequences.
	*
	* @return the process sequences
	*/
	public java.util.List<ProcessSequence> findAll();

	/**
	* Returns a range of all the process sequences.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ProcessSequenceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of process sequences
	* @param end the upper bound of the range of process sequences (not inclusive)
	* @return the range of process sequences
	*/
	public java.util.List<ProcessSequence> findAll(int start, int end);

	/**
	* Returns an ordered range of all the process sequences.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ProcessSequenceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of process sequences
	* @param end the upper bound of the range of process sequences (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of process sequences
	*/
	public java.util.List<ProcessSequence> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ProcessSequence> orderByComparator);

	/**
	* Returns an ordered range of all the process sequences.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ProcessSequenceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of process sequences
	* @param end the upper bound of the range of process sequences (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of process sequences
	*/
	public java.util.List<ProcessSequence> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ProcessSequence> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the process sequences from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of process sequences.
	*
	* @return the number of process sequences
	*/
	public int countAll();

	@Override
	public java.util.Set<String> getBadColumnNames();
}