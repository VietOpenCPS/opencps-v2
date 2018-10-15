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

import backend.feedback.model.Voting;

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;

import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;

import org.osgi.util.tracker.ServiceTracker;

import java.util.List;

/**
 * The persistence utility for the voting service. This utility wraps {@link backend.feedback.service.persistence.impl.VotingPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author sondt
 * @see VotingPersistence
 * @see backend.feedback.service.persistence.impl.VotingPersistenceImpl
 * @generated
 */
@ProviderType
public class VotingUtil {
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
	public static void clearCache(Voting voting) {
		getPersistence().clearCache(voting);
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
	public static List<Voting> findWithDynamicQuery(DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<Voting> findWithDynamicQuery(DynamicQuery dynamicQuery,
		int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<Voting> findWithDynamicQuery(DynamicQuery dynamicQuery,
		int start, int end, OrderByComparator<Voting> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static Voting update(Voting voting) {
		return getPersistence().update(voting);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static Voting update(Voting voting, ServiceContext serviceContext) {
		return getPersistence().update(voting, serviceContext);
	}

	/**
	* Returns all the votings where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the matching votings
	*/
	public static List<Voting> findByUuid(String uuid) {
		return getPersistence().findByUuid(uuid);
	}

	/**
	* Returns a range of all the votings where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link VotingModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of votings
	* @param end the upper bound of the range of votings (not inclusive)
	* @return the range of matching votings
	*/
	public static List<Voting> findByUuid(String uuid, int start, int end) {
		return getPersistence().findByUuid(uuid, start, end);
	}

	/**
	* Returns an ordered range of all the votings where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link VotingModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of votings
	* @param end the upper bound of the range of votings (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching votings
	*/
	public static List<Voting> findByUuid(String uuid, int start, int end,
		OrderByComparator<Voting> orderByComparator) {
		return getPersistence().findByUuid(uuid, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the votings where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link VotingModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of votings
	* @param end the upper bound of the range of votings (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching votings
	*/
	public static List<Voting> findByUuid(String uuid, int start, int end,
		OrderByComparator<Voting> orderByComparator, boolean retrieveFromCache) {
		return getPersistence()
				   .findByUuid(uuid, start, end, orderByComparator,
			retrieveFromCache);
	}

	/**
	* Returns the first voting in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching voting
	* @throws NoSuchVotingException if a matching voting could not be found
	*/
	public static Voting findByUuid_First(String uuid,
		OrderByComparator<Voting> orderByComparator)
		throws backend.feedback.exception.NoSuchVotingException {
		return getPersistence().findByUuid_First(uuid, orderByComparator);
	}

	/**
	* Returns the first voting in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching voting, or <code>null</code> if a matching voting could not be found
	*/
	public static Voting fetchByUuid_First(String uuid,
		OrderByComparator<Voting> orderByComparator) {
		return getPersistence().fetchByUuid_First(uuid, orderByComparator);
	}

	/**
	* Returns the last voting in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching voting
	* @throws NoSuchVotingException if a matching voting could not be found
	*/
	public static Voting findByUuid_Last(String uuid,
		OrderByComparator<Voting> orderByComparator)
		throws backend.feedback.exception.NoSuchVotingException {
		return getPersistence().findByUuid_Last(uuid, orderByComparator);
	}

	/**
	* Returns the last voting in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching voting, or <code>null</code> if a matching voting could not be found
	*/
	public static Voting fetchByUuid_Last(String uuid,
		OrderByComparator<Voting> orderByComparator) {
		return getPersistence().fetchByUuid_Last(uuid, orderByComparator);
	}

	/**
	* Returns the votings before and after the current voting in the ordered set where uuid = &#63;.
	*
	* @param votingId the primary key of the current voting
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next voting
	* @throws NoSuchVotingException if a voting with the primary key could not be found
	*/
	public static Voting[] findByUuid_PrevAndNext(long votingId, String uuid,
		OrderByComparator<Voting> orderByComparator)
		throws backend.feedback.exception.NoSuchVotingException {
		return getPersistence()
				   .findByUuid_PrevAndNext(votingId, uuid, orderByComparator);
	}

	/**
	* Removes all the votings where uuid = &#63; from the database.
	*
	* @param uuid the uuid
	*/
	public static void removeByUuid(String uuid) {
		getPersistence().removeByUuid(uuid);
	}

	/**
	* Returns the number of votings where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the number of matching votings
	*/
	public static int countByUuid(String uuid) {
		return getPersistence().countByUuid(uuid);
	}

	/**
	* Returns the voting where uuid = &#63; and groupId = &#63; or throws a {@link NoSuchVotingException} if it could not be found.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching voting
	* @throws NoSuchVotingException if a matching voting could not be found
	*/
	public static Voting findByUUID_G(String uuid, long groupId)
		throws backend.feedback.exception.NoSuchVotingException {
		return getPersistence().findByUUID_G(uuid, groupId);
	}

	/**
	* Returns the voting where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching voting, or <code>null</code> if a matching voting could not be found
	*/
	public static Voting fetchByUUID_G(String uuid, long groupId) {
		return getPersistence().fetchByUUID_G(uuid, groupId);
	}

	/**
	* Returns the voting where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching voting, or <code>null</code> if a matching voting could not be found
	*/
	public static Voting fetchByUUID_G(String uuid, long groupId,
		boolean retrieveFromCache) {
		return getPersistence().fetchByUUID_G(uuid, groupId, retrieveFromCache);
	}

	/**
	* Removes the voting where uuid = &#63; and groupId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the voting that was removed
	*/
	public static Voting removeByUUID_G(String uuid, long groupId)
		throws backend.feedback.exception.NoSuchVotingException {
		return getPersistence().removeByUUID_G(uuid, groupId);
	}

	/**
	* Returns the number of votings where uuid = &#63; and groupId = &#63;.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the number of matching votings
	*/
	public static int countByUUID_G(String uuid, long groupId) {
		return getPersistence().countByUUID_G(uuid, groupId);
	}

	/**
	* Returns all the votings where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the matching votings
	*/
	public static List<Voting> findByUuid_C(String uuid, long companyId) {
		return getPersistence().findByUuid_C(uuid, companyId);
	}

	/**
	* Returns a range of all the votings where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link VotingModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of votings
	* @param end the upper bound of the range of votings (not inclusive)
	* @return the range of matching votings
	*/
	public static List<Voting> findByUuid_C(String uuid, long companyId,
		int start, int end) {
		return getPersistence().findByUuid_C(uuid, companyId, start, end);
	}

	/**
	* Returns an ordered range of all the votings where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link VotingModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of votings
	* @param end the upper bound of the range of votings (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching votings
	*/
	public static List<Voting> findByUuid_C(String uuid, long companyId,
		int start, int end, OrderByComparator<Voting> orderByComparator) {
		return getPersistence()
				   .findByUuid_C(uuid, companyId, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the votings where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link VotingModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of votings
	* @param end the upper bound of the range of votings (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching votings
	*/
	public static List<Voting> findByUuid_C(String uuid, long companyId,
		int start, int end, OrderByComparator<Voting> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByUuid_C(uuid, companyId, start, end,
			orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first voting in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching voting
	* @throws NoSuchVotingException if a matching voting could not be found
	*/
	public static Voting findByUuid_C_First(String uuid, long companyId,
		OrderByComparator<Voting> orderByComparator)
		throws backend.feedback.exception.NoSuchVotingException {
		return getPersistence()
				   .findByUuid_C_First(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the first voting in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching voting, or <code>null</code> if a matching voting could not be found
	*/
	public static Voting fetchByUuid_C_First(String uuid, long companyId,
		OrderByComparator<Voting> orderByComparator) {
		return getPersistence()
				   .fetchByUuid_C_First(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the last voting in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching voting
	* @throws NoSuchVotingException if a matching voting could not be found
	*/
	public static Voting findByUuid_C_Last(String uuid, long companyId,
		OrderByComparator<Voting> orderByComparator)
		throws backend.feedback.exception.NoSuchVotingException {
		return getPersistence()
				   .findByUuid_C_Last(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the last voting in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching voting, or <code>null</code> if a matching voting could not be found
	*/
	public static Voting fetchByUuid_C_Last(String uuid, long companyId,
		OrderByComparator<Voting> orderByComparator) {
		return getPersistence()
				   .fetchByUuid_C_Last(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the votings before and after the current voting in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param votingId the primary key of the current voting
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next voting
	* @throws NoSuchVotingException if a voting with the primary key could not be found
	*/
	public static Voting[] findByUuid_C_PrevAndNext(long votingId, String uuid,
		long companyId, OrderByComparator<Voting> orderByComparator)
		throws backend.feedback.exception.NoSuchVotingException {
		return getPersistence()
				   .findByUuid_C_PrevAndNext(votingId, uuid, companyId,
			orderByComparator);
	}

	/**
	* Removes all the votings where uuid = &#63; and companyId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	*/
	public static void removeByUuid_C(String uuid, long companyId) {
		getPersistence().removeByUuid_C(uuid, companyId);
	}

	/**
	* Returns the number of votings where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the number of matching votings
	*/
	public static int countByUuid_C(String uuid, long companyId) {
		return getPersistence().countByUuid_C(uuid, companyId);
	}

	/**
	* Returns all the votings where className = &#63; and classPK = &#63;.
	*
	* @param className the class name
	* @param classPK the class pk
	* @return the matching votings
	*/
	public static List<Voting> findByF_CLNAME_CLPK(String className,
		String classPK) {
		return getPersistence().findByF_CLNAME_CLPK(className, classPK);
	}

	/**
	* Returns a range of all the votings where className = &#63; and classPK = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link VotingModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param className the class name
	* @param classPK the class pk
	* @param start the lower bound of the range of votings
	* @param end the upper bound of the range of votings (not inclusive)
	* @return the range of matching votings
	*/
	public static List<Voting> findByF_CLNAME_CLPK(String className,
		String classPK, int start, int end) {
		return getPersistence()
				   .findByF_CLNAME_CLPK(className, classPK, start, end);
	}

	/**
	* Returns an ordered range of all the votings where className = &#63; and classPK = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link VotingModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param className the class name
	* @param classPK the class pk
	* @param start the lower bound of the range of votings
	* @param end the upper bound of the range of votings (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching votings
	*/
	public static List<Voting> findByF_CLNAME_CLPK(String className,
		String classPK, int start, int end,
		OrderByComparator<Voting> orderByComparator) {
		return getPersistence()
				   .findByF_CLNAME_CLPK(className, classPK, start, end,
			orderByComparator);
	}

	/**
	* Returns an ordered range of all the votings where className = &#63; and classPK = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link VotingModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param className the class name
	* @param classPK the class pk
	* @param start the lower bound of the range of votings
	* @param end the upper bound of the range of votings (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching votings
	*/
	public static List<Voting> findByF_CLNAME_CLPK(String className,
		String classPK, int start, int end,
		OrderByComparator<Voting> orderByComparator, boolean retrieveFromCache) {
		return getPersistence()
				   .findByF_CLNAME_CLPK(className, classPK, start, end,
			orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first voting in the ordered set where className = &#63; and classPK = &#63;.
	*
	* @param className the class name
	* @param classPK the class pk
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching voting
	* @throws NoSuchVotingException if a matching voting could not be found
	*/
	public static Voting findByF_CLNAME_CLPK_First(String className,
		String classPK, OrderByComparator<Voting> orderByComparator)
		throws backend.feedback.exception.NoSuchVotingException {
		return getPersistence()
				   .findByF_CLNAME_CLPK_First(className, classPK,
			orderByComparator);
	}

	/**
	* Returns the first voting in the ordered set where className = &#63; and classPK = &#63;.
	*
	* @param className the class name
	* @param classPK the class pk
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching voting, or <code>null</code> if a matching voting could not be found
	*/
	public static Voting fetchByF_CLNAME_CLPK_First(String className,
		String classPK, OrderByComparator<Voting> orderByComparator) {
		return getPersistence()
				   .fetchByF_CLNAME_CLPK_First(className, classPK,
			orderByComparator);
	}

	/**
	* Returns the last voting in the ordered set where className = &#63; and classPK = &#63;.
	*
	* @param className the class name
	* @param classPK the class pk
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching voting
	* @throws NoSuchVotingException if a matching voting could not be found
	*/
	public static Voting findByF_CLNAME_CLPK_Last(String className,
		String classPK, OrderByComparator<Voting> orderByComparator)
		throws backend.feedback.exception.NoSuchVotingException {
		return getPersistence()
				   .findByF_CLNAME_CLPK_Last(className, classPK,
			orderByComparator);
	}

	/**
	* Returns the last voting in the ordered set where className = &#63; and classPK = &#63;.
	*
	* @param className the class name
	* @param classPK the class pk
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching voting, or <code>null</code> if a matching voting could not be found
	*/
	public static Voting fetchByF_CLNAME_CLPK_Last(String className,
		String classPK, OrderByComparator<Voting> orderByComparator) {
		return getPersistence()
				   .fetchByF_CLNAME_CLPK_Last(className, classPK,
			orderByComparator);
	}

	/**
	* Returns the votings before and after the current voting in the ordered set where className = &#63; and classPK = &#63;.
	*
	* @param votingId the primary key of the current voting
	* @param className the class name
	* @param classPK the class pk
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next voting
	* @throws NoSuchVotingException if a voting with the primary key could not be found
	*/
	public static Voting[] findByF_CLNAME_CLPK_PrevAndNext(long votingId,
		String className, String classPK,
		OrderByComparator<Voting> orderByComparator)
		throws backend.feedback.exception.NoSuchVotingException {
		return getPersistence()
				   .findByF_CLNAME_CLPK_PrevAndNext(votingId, className,
			classPK, orderByComparator);
	}

	/**
	* Removes all the votings where className = &#63; and classPK = &#63; from the database.
	*
	* @param className the class name
	* @param classPK the class pk
	*/
	public static void removeByF_CLNAME_CLPK(String className, String classPK) {
		getPersistence().removeByF_CLNAME_CLPK(className, classPK);
	}

	/**
	* Returns the number of votings where className = &#63; and classPK = &#63;.
	*
	* @param className the class name
	* @param classPK the class pk
	* @return the number of matching votings
	*/
	public static int countByF_CLNAME_CLPK(String className, String classPK) {
		return getPersistence().countByF_CLNAME_CLPK(className, classPK);
	}

	/**
	* Caches the voting in the entity cache if it is enabled.
	*
	* @param voting the voting
	*/
	public static void cacheResult(Voting voting) {
		getPersistence().cacheResult(voting);
	}

	/**
	* Caches the votings in the entity cache if it is enabled.
	*
	* @param votings the votings
	*/
	public static void cacheResult(List<Voting> votings) {
		getPersistence().cacheResult(votings);
	}

	/**
	* Creates a new voting with the primary key. Does not add the voting to the database.
	*
	* @param votingId the primary key for the new voting
	* @return the new voting
	*/
	public static Voting create(long votingId) {
		return getPersistence().create(votingId);
	}

	/**
	* Removes the voting with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param votingId the primary key of the voting
	* @return the voting that was removed
	* @throws NoSuchVotingException if a voting with the primary key could not be found
	*/
	public static Voting remove(long votingId)
		throws backend.feedback.exception.NoSuchVotingException {
		return getPersistence().remove(votingId);
	}

	public static Voting updateImpl(Voting voting) {
		return getPersistence().updateImpl(voting);
	}

	/**
	* Returns the voting with the primary key or throws a {@link NoSuchVotingException} if it could not be found.
	*
	* @param votingId the primary key of the voting
	* @return the voting
	* @throws NoSuchVotingException if a voting with the primary key could not be found
	*/
	public static Voting findByPrimaryKey(long votingId)
		throws backend.feedback.exception.NoSuchVotingException {
		return getPersistence().findByPrimaryKey(votingId);
	}

	/**
	* Returns the voting with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param votingId the primary key of the voting
	* @return the voting, or <code>null</code> if a voting with the primary key could not be found
	*/
	public static Voting fetchByPrimaryKey(long votingId) {
		return getPersistence().fetchByPrimaryKey(votingId);
	}

	public static java.util.Map<java.io.Serializable, Voting> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	* Returns all the votings.
	*
	* @return the votings
	*/
	public static List<Voting> findAll() {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the votings.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link VotingModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of votings
	* @param end the upper bound of the range of votings (not inclusive)
	* @return the range of votings
	*/
	public static List<Voting> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the votings.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link VotingModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of votings
	* @param end the upper bound of the range of votings (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of votings
	*/
	public static List<Voting> findAll(int start, int end,
		OrderByComparator<Voting> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the votings.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link VotingModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of votings
	* @param end the upper bound of the range of votings (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of votings
	*/
	public static List<Voting> findAll(int start, int end,
		OrderByComparator<Voting> orderByComparator, boolean retrieveFromCache) {
		return getPersistence()
				   .findAll(start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the votings from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of votings.
	*
	* @return the number of votings
	*/
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static java.util.Set<String> getBadColumnNames() {
		return getPersistence().getBadColumnNames();
	}

	public static VotingPersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<VotingPersistence, VotingPersistence> _serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(VotingPersistence.class);

		ServiceTracker<VotingPersistence, VotingPersistence> serviceTracker = new ServiceTracker<VotingPersistence, VotingPersistence>(bundle.getBundleContext(),
				VotingPersistence.class, null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}
}