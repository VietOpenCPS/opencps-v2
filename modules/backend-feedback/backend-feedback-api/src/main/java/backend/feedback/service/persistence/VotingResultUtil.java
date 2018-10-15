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

import backend.feedback.model.VotingResult;

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;

import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;

import org.osgi.util.tracker.ServiceTracker;

import java.util.List;

/**
 * The persistence utility for the voting result service. This utility wraps {@link backend.feedback.service.persistence.impl.VotingResultPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author sondt
 * @see VotingResultPersistence
 * @see backend.feedback.service.persistence.impl.VotingResultPersistenceImpl
 * @generated
 */
@ProviderType
public class VotingResultUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#clearCache()
	 */
	public static void clearCache() {
		getPersistence().clearCache();
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#clearCache(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static void clearCache(VotingResult votingResult) {
		getPersistence().clearCache(votingResult);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#countWithDynamicQuery(DynamicQuery)
	 */
	public static long countWithDynamicQuery(DynamicQuery dynamicQuery) {
		return getPersistence().countWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
	 */
	public static List<VotingResult> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<VotingResult> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<VotingResult> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<VotingResult> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static VotingResult update(VotingResult votingResult) {
		return getPersistence().update(votingResult);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static VotingResult update(VotingResult votingResult,
		ServiceContext serviceContext) {
		return getPersistence().update(votingResult, serviceContext);
	}

	/**
	* Returns all the voting results where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the matching voting results
	*/
	public static List<VotingResult> findByUuid(String uuid) {
		return getPersistence().findByUuid(uuid);
	}

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
	public static List<VotingResult> findByUuid(String uuid, int start, int end) {
		return getPersistence().findByUuid(uuid, start, end);
	}

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
	public static List<VotingResult> findByUuid(String uuid, int start,
		int end, OrderByComparator<VotingResult> orderByComparator) {
		return getPersistence().findByUuid(uuid, start, end, orderByComparator);
	}

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
	public static List<VotingResult> findByUuid(String uuid, int start,
		int end, OrderByComparator<VotingResult> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByUuid(uuid, start, end, orderByComparator,
			retrieveFromCache);
	}

	/**
	* Returns the first voting result in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching voting result
	* @throws NoSuchVotingResultException if a matching voting result could not be found
	*/
	public static VotingResult findByUuid_First(String uuid,
		OrderByComparator<VotingResult> orderByComparator)
		throws backend.feedback.exception.NoSuchVotingResultException {
		return getPersistence().findByUuid_First(uuid, orderByComparator);
	}

	/**
	* Returns the first voting result in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching voting result, or <code>null</code> if a matching voting result could not be found
	*/
	public static VotingResult fetchByUuid_First(String uuid,
		OrderByComparator<VotingResult> orderByComparator) {
		return getPersistence().fetchByUuid_First(uuid, orderByComparator);
	}

	/**
	* Returns the last voting result in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching voting result
	* @throws NoSuchVotingResultException if a matching voting result could not be found
	*/
	public static VotingResult findByUuid_Last(String uuid,
		OrderByComparator<VotingResult> orderByComparator)
		throws backend.feedback.exception.NoSuchVotingResultException {
		return getPersistence().findByUuid_Last(uuid, orderByComparator);
	}

	/**
	* Returns the last voting result in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching voting result, or <code>null</code> if a matching voting result could not be found
	*/
	public static VotingResult fetchByUuid_Last(String uuid,
		OrderByComparator<VotingResult> orderByComparator) {
		return getPersistence().fetchByUuid_Last(uuid, orderByComparator);
	}

	/**
	* Returns the voting results before and after the current voting result in the ordered set where uuid = &#63;.
	*
	* @param votingResultId the primary key of the current voting result
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next voting result
	* @throws NoSuchVotingResultException if a voting result with the primary key could not be found
	*/
	public static VotingResult[] findByUuid_PrevAndNext(long votingResultId,
		String uuid, OrderByComparator<VotingResult> orderByComparator)
		throws backend.feedback.exception.NoSuchVotingResultException {
		return getPersistence()
				   .findByUuid_PrevAndNext(votingResultId, uuid,
			orderByComparator);
	}

	/**
	* Removes all the voting results where uuid = &#63; from the database.
	*
	* @param uuid the uuid
	*/
	public static void removeByUuid(String uuid) {
		getPersistence().removeByUuid(uuid);
	}

	/**
	* Returns the number of voting results where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the number of matching voting results
	*/
	public static int countByUuid(String uuid) {
		return getPersistence().countByUuid(uuid);
	}

	/**
	* Returns the voting result where uuid = &#63; and groupId = &#63; or throws a {@link NoSuchVotingResultException} if it could not be found.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching voting result
	* @throws NoSuchVotingResultException if a matching voting result could not be found
	*/
	public static VotingResult findByUUID_G(String uuid, long groupId)
		throws backend.feedback.exception.NoSuchVotingResultException {
		return getPersistence().findByUUID_G(uuid, groupId);
	}

	/**
	* Returns the voting result where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching voting result, or <code>null</code> if a matching voting result could not be found
	*/
	public static VotingResult fetchByUUID_G(String uuid, long groupId) {
		return getPersistence().fetchByUUID_G(uuid, groupId);
	}

	/**
	* Returns the voting result where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching voting result, or <code>null</code> if a matching voting result could not be found
	*/
	public static VotingResult fetchByUUID_G(String uuid, long groupId,
		boolean retrieveFromCache) {
		return getPersistence().fetchByUUID_G(uuid, groupId, retrieveFromCache);
	}

	/**
	* Removes the voting result where uuid = &#63; and groupId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the voting result that was removed
	*/
	public static VotingResult removeByUUID_G(String uuid, long groupId)
		throws backend.feedback.exception.NoSuchVotingResultException {
		return getPersistence().removeByUUID_G(uuid, groupId);
	}

	/**
	* Returns the number of voting results where uuid = &#63; and groupId = &#63;.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the number of matching voting results
	*/
	public static int countByUUID_G(String uuid, long groupId) {
		return getPersistence().countByUUID_G(uuid, groupId);
	}

	/**
	* Returns all the voting results where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the matching voting results
	*/
	public static List<VotingResult> findByUuid_C(String uuid, long companyId) {
		return getPersistence().findByUuid_C(uuid, companyId);
	}

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
	public static List<VotingResult> findByUuid_C(String uuid, long companyId,
		int start, int end) {
		return getPersistence().findByUuid_C(uuid, companyId, start, end);
	}

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
	public static List<VotingResult> findByUuid_C(String uuid, long companyId,
		int start, int end, OrderByComparator<VotingResult> orderByComparator) {
		return getPersistence()
				   .findByUuid_C(uuid, companyId, start, end, orderByComparator);
	}

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
	public static List<VotingResult> findByUuid_C(String uuid, long companyId,
		int start, int end, OrderByComparator<VotingResult> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByUuid_C(uuid, companyId, start, end,
			orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first voting result in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching voting result
	* @throws NoSuchVotingResultException if a matching voting result could not be found
	*/
	public static VotingResult findByUuid_C_First(String uuid, long companyId,
		OrderByComparator<VotingResult> orderByComparator)
		throws backend.feedback.exception.NoSuchVotingResultException {
		return getPersistence()
				   .findByUuid_C_First(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the first voting result in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching voting result, or <code>null</code> if a matching voting result could not be found
	*/
	public static VotingResult fetchByUuid_C_First(String uuid, long companyId,
		OrderByComparator<VotingResult> orderByComparator) {
		return getPersistence()
				   .fetchByUuid_C_First(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the last voting result in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching voting result
	* @throws NoSuchVotingResultException if a matching voting result could not be found
	*/
	public static VotingResult findByUuid_C_Last(String uuid, long companyId,
		OrderByComparator<VotingResult> orderByComparator)
		throws backend.feedback.exception.NoSuchVotingResultException {
		return getPersistence()
				   .findByUuid_C_Last(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the last voting result in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching voting result, or <code>null</code> if a matching voting result could not be found
	*/
	public static VotingResult fetchByUuid_C_Last(String uuid, long companyId,
		OrderByComparator<VotingResult> orderByComparator) {
		return getPersistence()
				   .fetchByUuid_C_Last(uuid, companyId, orderByComparator);
	}

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
	public static VotingResult[] findByUuid_C_PrevAndNext(long votingResultId,
		String uuid, long companyId,
		OrderByComparator<VotingResult> orderByComparator)
		throws backend.feedback.exception.NoSuchVotingResultException {
		return getPersistence()
				   .findByUuid_C_PrevAndNext(votingResultId, uuid, companyId,
			orderByComparator);
	}

	/**
	* Removes all the voting results where uuid = &#63; and companyId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	*/
	public static void removeByUuid_C(String uuid, long companyId) {
		getPersistence().removeByUuid_C(uuid, companyId);
	}

	/**
	* Returns the number of voting results where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the number of matching voting results
	*/
	public static int countByUuid_C(String uuid, long companyId) {
		return getPersistence().countByUuid_C(uuid, companyId);
	}

	/**
	* Returns the voting result where userId = &#63; and votingId = &#63; or throws a {@link NoSuchVotingResultException} if it could not be found.
	*
	* @param userId the user ID
	* @param votingId the voting ID
	* @return the matching voting result
	* @throws NoSuchVotingResultException if a matching voting result could not be found
	*/
	public static VotingResult findByF_votingId_userId(long userId,
		long votingId)
		throws backend.feedback.exception.NoSuchVotingResultException {
		return getPersistence().findByF_votingId_userId(userId, votingId);
	}

	/**
	* Returns the voting result where userId = &#63; and votingId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param userId the user ID
	* @param votingId the voting ID
	* @return the matching voting result, or <code>null</code> if a matching voting result could not be found
	*/
	public static VotingResult fetchByF_votingId_userId(long userId,
		long votingId) {
		return getPersistence().fetchByF_votingId_userId(userId, votingId);
	}

	/**
	* Returns the voting result where userId = &#63; and votingId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param userId the user ID
	* @param votingId the voting ID
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching voting result, or <code>null</code> if a matching voting result could not be found
	*/
	public static VotingResult fetchByF_votingId_userId(long userId,
		long votingId, boolean retrieveFromCache) {
		return getPersistence()
				   .fetchByF_votingId_userId(userId, votingId, retrieveFromCache);
	}

	/**
	* Removes the voting result where userId = &#63; and votingId = &#63; from the database.
	*
	* @param userId the user ID
	* @param votingId the voting ID
	* @return the voting result that was removed
	*/
	public static VotingResult removeByF_votingId_userId(long userId,
		long votingId)
		throws backend.feedback.exception.NoSuchVotingResultException {
		return getPersistence().removeByF_votingId_userId(userId, votingId);
	}

	/**
	* Returns the number of voting results where userId = &#63; and votingId = &#63;.
	*
	* @param userId the user ID
	* @param votingId the voting ID
	* @return the number of matching voting results
	*/
	public static int countByF_votingId_userId(long userId, long votingId) {
		return getPersistence().countByF_votingId_userId(userId, votingId);
	}

	/**
	* Returns all the voting results where votingId = &#63; and selected = &#63;.
	*
	* @param votingId the voting ID
	* @param selected the selected
	* @return the matching voting results
	*/
	public static List<VotingResult> findByF_votingId_selected(long votingId,
		String selected) {
		return getPersistence().findByF_votingId_selected(votingId, selected);
	}

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
	public static List<VotingResult> findByF_votingId_selected(long votingId,
		String selected, int start, int end) {
		return getPersistence()
				   .findByF_votingId_selected(votingId, selected, start, end);
	}

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
	public static List<VotingResult> findByF_votingId_selected(long votingId,
		String selected, int start, int end,
		OrderByComparator<VotingResult> orderByComparator) {
		return getPersistence()
				   .findByF_votingId_selected(votingId, selected, start, end,
			orderByComparator);
	}

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
	public static List<VotingResult> findByF_votingId_selected(long votingId,
		String selected, int start, int end,
		OrderByComparator<VotingResult> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByF_votingId_selected(votingId, selected, start, end,
			orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first voting result in the ordered set where votingId = &#63; and selected = &#63;.
	*
	* @param votingId the voting ID
	* @param selected the selected
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching voting result
	* @throws NoSuchVotingResultException if a matching voting result could not be found
	*/
	public static VotingResult findByF_votingId_selected_First(long votingId,
		String selected, OrderByComparator<VotingResult> orderByComparator)
		throws backend.feedback.exception.NoSuchVotingResultException {
		return getPersistence()
				   .findByF_votingId_selected_First(votingId, selected,
			orderByComparator);
	}

	/**
	* Returns the first voting result in the ordered set where votingId = &#63; and selected = &#63;.
	*
	* @param votingId the voting ID
	* @param selected the selected
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching voting result, or <code>null</code> if a matching voting result could not be found
	*/
	public static VotingResult fetchByF_votingId_selected_First(long votingId,
		String selected, OrderByComparator<VotingResult> orderByComparator) {
		return getPersistence()
				   .fetchByF_votingId_selected_First(votingId, selected,
			orderByComparator);
	}

	/**
	* Returns the last voting result in the ordered set where votingId = &#63; and selected = &#63;.
	*
	* @param votingId the voting ID
	* @param selected the selected
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching voting result
	* @throws NoSuchVotingResultException if a matching voting result could not be found
	*/
	public static VotingResult findByF_votingId_selected_Last(long votingId,
		String selected, OrderByComparator<VotingResult> orderByComparator)
		throws backend.feedback.exception.NoSuchVotingResultException {
		return getPersistence()
				   .findByF_votingId_selected_Last(votingId, selected,
			orderByComparator);
	}

	/**
	* Returns the last voting result in the ordered set where votingId = &#63; and selected = &#63;.
	*
	* @param votingId the voting ID
	* @param selected the selected
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching voting result, or <code>null</code> if a matching voting result could not be found
	*/
	public static VotingResult fetchByF_votingId_selected_Last(long votingId,
		String selected, OrderByComparator<VotingResult> orderByComparator) {
		return getPersistence()
				   .fetchByF_votingId_selected_Last(votingId, selected,
			orderByComparator);
	}

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
	public static VotingResult[] findByF_votingId_selected_PrevAndNext(
		long votingResultId, long votingId, String selected,
		OrderByComparator<VotingResult> orderByComparator)
		throws backend.feedback.exception.NoSuchVotingResultException {
		return getPersistence()
				   .findByF_votingId_selected_PrevAndNext(votingResultId,
			votingId, selected, orderByComparator);
	}

	/**
	* Removes all the voting results where votingId = &#63; and selected = &#63; from the database.
	*
	* @param votingId the voting ID
	* @param selected the selected
	*/
	public static void removeByF_votingId_selected(long votingId,
		String selected) {
		getPersistence().removeByF_votingId_selected(votingId, selected);
	}

	/**
	* Returns the number of voting results where votingId = &#63; and selected = &#63;.
	*
	* @param votingId the voting ID
	* @param selected the selected
	* @return the number of matching voting results
	*/
	public static int countByF_votingId_selected(long votingId, String selected) {
		return getPersistence().countByF_votingId_selected(votingId, selected);
	}

	/**
	* Caches the voting result in the entity cache if it is enabled.
	*
	* @param votingResult the voting result
	*/
	public static void cacheResult(VotingResult votingResult) {
		getPersistence().cacheResult(votingResult);
	}

	/**
	* Caches the voting results in the entity cache if it is enabled.
	*
	* @param votingResults the voting results
	*/
	public static void cacheResult(List<VotingResult> votingResults) {
		getPersistence().cacheResult(votingResults);
	}

	/**
	* Creates a new voting result with the primary key. Does not add the voting result to the database.
	*
	* @param votingResultId the primary key for the new voting result
	* @return the new voting result
	*/
	public static VotingResult create(long votingResultId) {
		return getPersistence().create(votingResultId);
	}

	/**
	* Removes the voting result with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param votingResultId the primary key of the voting result
	* @return the voting result that was removed
	* @throws NoSuchVotingResultException if a voting result with the primary key could not be found
	*/
	public static VotingResult remove(long votingResultId)
		throws backend.feedback.exception.NoSuchVotingResultException {
		return getPersistence().remove(votingResultId);
	}

	public static VotingResult updateImpl(VotingResult votingResult) {
		return getPersistence().updateImpl(votingResult);
	}

	/**
	* Returns the voting result with the primary key or throws a {@link NoSuchVotingResultException} if it could not be found.
	*
	* @param votingResultId the primary key of the voting result
	* @return the voting result
	* @throws NoSuchVotingResultException if a voting result with the primary key could not be found
	*/
	public static VotingResult findByPrimaryKey(long votingResultId)
		throws backend.feedback.exception.NoSuchVotingResultException {
		return getPersistence().findByPrimaryKey(votingResultId);
	}

	/**
	* Returns the voting result with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param votingResultId the primary key of the voting result
	* @return the voting result, or <code>null</code> if a voting result with the primary key could not be found
	*/
	public static VotingResult fetchByPrimaryKey(long votingResultId) {
		return getPersistence().fetchByPrimaryKey(votingResultId);
	}

	public static java.util.Map<java.io.Serializable, VotingResult> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	* Returns all the voting results.
	*
	* @return the voting results
	*/
	public static List<VotingResult> findAll() {
		return getPersistence().findAll();
	}

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
	public static List<VotingResult> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

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
	public static List<VotingResult> findAll(int start, int end,
		OrderByComparator<VotingResult> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

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
	public static List<VotingResult> findAll(int start, int end,
		OrderByComparator<VotingResult> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findAll(start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the voting results from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of voting results.
	*
	* @return the number of voting results
	*/
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static java.util.Set<String> getBadColumnNames() {
		return getPersistence().getBadColumnNames();
	}

	public static VotingResultPersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<VotingResultPersistence, VotingResultPersistence> _serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(VotingResultPersistence.class);

		ServiceTracker<VotingResultPersistence, VotingResultPersistence> serviceTracker =
			new ServiceTracker<VotingResultPersistence, VotingResultPersistence>(bundle.getBundleContext(),
				VotingResultPersistence.class, null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}
}