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

package backend.feedback.service.persistence;

import aQute.bnd.annotation.ProviderType;

import backend.feedback.exception.NoSuchVotingResultException;

import backend.feedback.model.VotingResult;

import com.liferay.portal.kernel.service.persistence.BasePersistence;

/**
 * The persistence interface for the voting result service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author sondt
 * @see backend.feedback.service.persistence.impl.VotingResultPersistenceImpl
 * @see VotingResultUtil
 * @generated
 */
@ProviderType
public interface VotingResultPersistence extends BasePersistence<VotingResult> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link VotingResultUtil} to access the voting result persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Returns all the voting results where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the matching voting results
	*/
	public java.util.List<VotingResult> findByUuid(String uuid);

	/**
	* Returns a range of all the voting results where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link VotingResultModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of voting results
	* @param end the upper bound of the range of voting results (not inclusive)
	* @return the range of matching voting results
	*/
	public java.util.List<VotingResult> findByUuid(String uuid, int start,
		int end);

	/**
	* Returns an ordered range of all the voting results where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link VotingResultModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of voting results
	* @param end the upper bound of the range of voting results (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching voting results
	*/
	public java.util.List<VotingResult> findByUuid(String uuid, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<VotingResult> orderByComparator);

	/**
	* Returns an ordered range of all the voting results where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link VotingResultModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of voting results
	* @param end the upper bound of the range of voting results (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching voting results
	*/
	public java.util.List<VotingResult> findByUuid(String uuid, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<VotingResult> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first voting result in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching voting result
	* @throws NoSuchVotingResultException if a matching voting result could not be found
	*/
	public VotingResult findByUuid_First(String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<VotingResult> orderByComparator)
		throws NoSuchVotingResultException;

	/**
	* Returns the first voting result in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching voting result, or <code>null</code> if a matching voting result could not be found
	*/
	public VotingResult fetchByUuid_First(String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<VotingResult> orderByComparator);

	/**
	* Returns the last voting result in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching voting result
	* @throws NoSuchVotingResultException if a matching voting result could not be found
	*/
	public VotingResult findByUuid_Last(String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<VotingResult> orderByComparator)
		throws NoSuchVotingResultException;

	/**
	* Returns the last voting result in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching voting result, or <code>null</code> if a matching voting result could not be found
	*/
	public VotingResult fetchByUuid_Last(String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<VotingResult> orderByComparator);

	/**
	* Returns the voting results before and after the current voting result in the ordered set where uuid = &#63;.
	*
	* @param votingResultId the primary key of the current voting result
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next voting result
	* @throws NoSuchVotingResultException if a voting result with the primary key could not be found
	*/
	public VotingResult[] findByUuid_PrevAndNext(long votingResultId,
		String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<VotingResult> orderByComparator)
		throws NoSuchVotingResultException;

	/**
	* Removes all the voting results where uuid = &#63; from the database.
	*
	* @param uuid the uuid
	*/
	public void removeByUuid(String uuid);

	/**
	* Returns the number of voting results where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the number of matching voting results
	*/
	public int countByUuid(String uuid);

	/**
	* Returns the voting result where uuid = &#63; and groupId = &#63; or throws a {@link NoSuchVotingResultException} if it could not be found.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching voting result
	* @throws NoSuchVotingResultException if a matching voting result could not be found
	*/
	public VotingResult findByUUID_G(String uuid, long groupId)
		throws NoSuchVotingResultException;

	/**
	* Returns the voting result where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching voting result, or <code>null</code> if a matching voting result could not be found
	*/
	public VotingResult fetchByUUID_G(String uuid, long groupId);

	/**
	* Returns the voting result where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching voting result, or <code>null</code> if a matching voting result could not be found
	*/
	public VotingResult fetchByUUID_G(String uuid, long groupId,
		boolean retrieveFromCache);

	/**
	* Removes the voting result where uuid = &#63; and groupId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the voting result that was removed
	*/
	public VotingResult removeByUUID_G(String uuid, long groupId)
		throws NoSuchVotingResultException;

	/**
	* Returns the number of voting results where uuid = &#63; and groupId = &#63;.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the number of matching voting results
	*/
	public int countByUUID_G(String uuid, long groupId);

	/**
	* Returns all the voting results where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the matching voting results
	*/
	public java.util.List<VotingResult> findByUuid_C(String uuid, long companyId);

	/**
	* Returns a range of all the voting results where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link VotingResultModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of voting results
	* @param end the upper bound of the range of voting results (not inclusive)
	* @return the range of matching voting results
	*/
	public java.util.List<VotingResult> findByUuid_C(String uuid,
		long companyId, int start, int end);

	/**
	* Returns an ordered range of all the voting results where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link VotingResultModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of voting results
	* @param end the upper bound of the range of voting results (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching voting results
	*/
	public java.util.List<VotingResult> findByUuid_C(String uuid,
		long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<VotingResult> orderByComparator);

	/**
	* Returns an ordered range of all the voting results where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link VotingResultModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of voting results
	* @param end the upper bound of the range of voting results (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching voting results
	*/
	public java.util.List<VotingResult> findByUuid_C(String uuid,
		long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<VotingResult> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first voting result in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching voting result
	* @throws NoSuchVotingResultException if a matching voting result could not be found
	*/
	public VotingResult findByUuid_C_First(String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<VotingResult> orderByComparator)
		throws NoSuchVotingResultException;

	/**
	* Returns the first voting result in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching voting result, or <code>null</code> if a matching voting result could not be found
	*/
	public VotingResult fetchByUuid_C_First(String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<VotingResult> orderByComparator);

	/**
	* Returns the last voting result in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching voting result
	* @throws NoSuchVotingResultException if a matching voting result could not be found
	*/
	public VotingResult findByUuid_C_Last(String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<VotingResult> orderByComparator)
		throws NoSuchVotingResultException;

	/**
	* Returns the last voting result in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching voting result, or <code>null</code> if a matching voting result could not be found
	*/
	public VotingResult fetchByUuid_C_Last(String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<VotingResult> orderByComparator);

	/**
	* Returns the voting results before and after the current voting result in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param votingResultId the primary key of the current voting result
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next voting result
	* @throws NoSuchVotingResultException if a voting result with the primary key could not be found
	*/
	public VotingResult[] findByUuid_C_PrevAndNext(long votingResultId,
		String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<VotingResult> orderByComparator)
		throws NoSuchVotingResultException;

	/**
	* Removes all the voting results where uuid = &#63; and companyId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	*/
	public void removeByUuid_C(String uuid, long companyId);

	/**
	* Returns the number of voting results where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the number of matching voting results
	*/
	public int countByUuid_C(String uuid, long companyId);

	/**
	* Returns the voting result where userId = &#63; and votingId = &#63; or throws a {@link NoSuchVotingResultException} if it could not be found.
	*
	* @param userId the user ID
	* @param votingId the voting ID
	* @return the matching voting result
	* @throws NoSuchVotingResultException if a matching voting result could not be found
	*/
	public VotingResult findByF_votingId_userId(long userId, long votingId)
		throws NoSuchVotingResultException;

	/**
	* Returns the voting result where userId = &#63; and votingId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param userId the user ID
	* @param votingId the voting ID
	* @return the matching voting result, or <code>null</code> if a matching voting result could not be found
	*/
	public VotingResult fetchByF_votingId_userId(long userId, long votingId);

	/**
	* Returns the voting result where userId = &#63; and votingId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param userId the user ID
	* @param votingId the voting ID
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching voting result, or <code>null</code> if a matching voting result could not be found
	*/
	public VotingResult fetchByF_votingId_userId(long userId, long votingId,
		boolean retrieveFromCache);

	/**
	* Removes the voting result where userId = &#63; and votingId = &#63; from the database.
	*
	* @param userId the user ID
	* @param votingId the voting ID
	* @return the voting result that was removed
	*/
	public VotingResult removeByF_votingId_userId(long userId, long votingId)
		throws NoSuchVotingResultException;

	/**
	* Returns the number of voting results where userId = &#63; and votingId = &#63;.
	*
	* @param userId the user ID
	* @param votingId the voting ID
	* @return the number of matching voting results
	*/
	public int countByF_votingId_userId(long userId, long votingId);

	/**
	* Returns all the voting results where votingId = &#63; and selected = &#63;.
	*
	* @param votingId the voting ID
	* @param selected the selected
	* @return the matching voting results
	*/
	public java.util.List<VotingResult> findByF_votingId_selected(
		long votingId, String selected);

	/**
	* Returns a range of all the voting results where votingId = &#63; and selected = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link VotingResultModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param votingId the voting ID
	* @param selected the selected
	* @param start the lower bound of the range of voting results
	* @param end the upper bound of the range of voting results (not inclusive)
	* @return the range of matching voting results
	*/
	public java.util.List<VotingResult> findByF_votingId_selected(
		long votingId, String selected, int start, int end);

	/**
	* Returns an ordered range of all the voting results where votingId = &#63; and selected = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link VotingResultModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param votingId the voting ID
	* @param selected the selected
	* @param start the lower bound of the range of voting results
	* @param end the upper bound of the range of voting results (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching voting results
	*/
	public java.util.List<VotingResult> findByF_votingId_selected(
		long votingId, String selected, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<VotingResult> orderByComparator);

	/**
	* Returns an ordered range of all the voting results where votingId = &#63; and selected = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link VotingResultModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param votingId the voting ID
	* @param selected the selected
	* @param start the lower bound of the range of voting results
	* @param end the upper bound of the range of voting results (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching voting results
	*/
	public java.util.List<VotingResult> findByF_votingId_selected(
		long votingId, String selected, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<VotingResult> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first voting result in the ordered set where votingId = &#63; and selected = &#63;.
	*
	* @param votingId the voting ID
	* @param selected the selected
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching voting result
	* @throws NoSuchVotingResultException if a matching voting result could not be found
	*/
	public VotingResult findByF_votingId_selected_First(long votingId,
		String selected,
		com.liferay.portal.kernel.util.OrderByComparator<VotingResult> orderByComparator)
		throws NoSuchVotingResultException;

	/**
	* Returns the first voting result in the ordered set where votingId = &#63; and selected = &#63;.
	*
	* @param votingId the voting ID
	* @param selected the selected
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching voting result, or <code>null</code> if a matching voting result could not be found
	*/
	public VotingResult fetchByF_votingId_selected_First(long votingId,
		String selected,
		com.liferay.portal.kernel.util.OrderByComparator<VotingResult> orderByComparator);

	/**
	* Returns the last voting result in the ordered set where votingId = &#63; and selected = &#63;.
	*
	* @param votingId the voting ID
	* @param selected the selected
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching voting result
	* @throws NoSuchVotingResultException if a matching voting result could not be found
	*/
	public VotingResult findByF_votingId_selected_Last(long votingId,
		String selected,
		com.liferay.portal.kernel.util.OrderByComparator<VotingResult> orderByComparator)
		throws NoSuchVotingResultException;

	/**
	* Returns the last voting result in the ordered set where votingId = &#63; and selected = &#63;.
	*
	* @param votingId the voting ID
	* @param selected the selected
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching voting result, or <code>null</code> if a matching voting result could not be found
	*/
	public VotingResult fetchByF_votingId_selected_Last(long votingId,
		String selected,
		com.liferay.portal.kernel.util.OrderByComparator<VotingResult> orderByComparator);

	/**
	* Returns the voting results before and after the current voting result in the ordered set where votingId = &#63; and selected = &#63;.
	*
	* @param votingResultId the primary key of the current voting result
	* @param votingId the voting ID
	* @param selected the selected
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next voting result
	* @throws NoSuchVotingResultException if a voting result with the primary key could not be found
	*/
	public VotingResult[] findByF_votingId_selected_PrevAndNext(
		long votingResultId, long votingId, String selected,
		com.liferay.portal.kernel.util.OrderByComparator<VotingResult> orderByComparator)
		throws NoSuchVotingResultException;

	/**
	* Removes all the voting results where votingId = &#63; and selected = &#63; from the database.
	*
	* @param votingId the voting ID
	* @param selected the selected
	*/
	public void removeByF_votingId_selected(long votingId, String selected);

	/**
	* Returns the number of voting results where votingId = &#63; and selected = &#63;.
	*
	* @param votingId the voting ID
	* @param selected the selected
	* @return the number of matching voting results
	*/
	public int countByF_votingId_selected(long votingId, String selected);

	/**
	* Caches the voting result in the entity cache if it is enabled.
	*
	* @param votingResult the voting result
	*/
	public void cacheResult(VotingResult votingResult);

	/**
	* Caches the voting results in the entity cache if it is enabled.
	*
	* @param votingResults the voting results
	*/
	public void cacheResult(java.util.List<VotingResult> votingResults);

	/**
	* Creates a new voting result with the primary key. Does not add the voting result to the database.
	*
	* @param votingResultId the primary key for the new voting result
	* @return the new voting result
	*/
	public VotingResult create(long votingResultId);

	/**
	* Removes the voting result with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param votingResultId the primary key of the voting result
	* @return the voting result that was removed
	* @throws NoSuchVotingResultException if a voting result with the primary key could not be found
	*/
	public VotingResult remove(long votingResultId)
		throws NoSuchVotingResultException;

	public VotingResult updateImpl(VotingResult votingResult);

	/**
	* Returns the voting result with the primary key or throws a {@link NoSuchVotingResultException} if it could not be found.
	*
	* @param votingResultId the primary key of the voting result
	* @return the voting result
	* @throws NoSuchVotingResultException if a voting result with the primary key could not be found
	*/
	public VotingResult findByPrimaryKey(long votingResultId)
		throws NoSuchVotingResultException;

	/**
	* Returns the voting result with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param votingResultId the primary key of the voting result
	* @return the voting result, or <code>null</code> if a voting result with the primary key could not be found
	*/
	public VotingResult fetchByPrimaryKey(long votingResultId);

	@Override
	public java.util.Map<java.io.Serializable, VotingResult> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the voting results.
	*
	* @return the voting results
	*/
	public java.util.List<VotingResult> findAll();

	/**
	* Returns a range of all the voting results.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link VotingResultModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of voting results
	* @param end the upper bound of the range of voting results (not inclusive)
	* @return the range of voting results
	*/
	public java.util.List<VotingResult> findAll(int start, int end);

	/**
	* Returns an ordered range of all the voting results.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link VotingResultModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of voting results
	* @param end the upper bound of the range of voting results (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of voting results
	*/
	public java.util.List<VotingResult> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<VotingResult> orderByComparator);

	/**
	* Returns an ordered range of all the voting results.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link VotingResultModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of voting results
	* @param end the upper bound of the range of voting results (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of voting results
	*/
	public java.util.List<VotingResult> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<VotingResult> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the voting results from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of voting results.
	*
	* @return the number of voting results
	*/
	public int countAll();

	@Override
	public java.util.Set<String> getBadColumnNames();
}