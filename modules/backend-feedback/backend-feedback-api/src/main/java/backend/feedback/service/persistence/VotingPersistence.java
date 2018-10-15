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

import backend.feedback.exception.NoSuchVotingException;

import backend.feedback.model.Voting;

import com.liferay.portal.kernel.service.persistence.BasePersistence;

/**
 * The persistence interface for the voting service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author sondt
 * @see backend.feedback.service.persistence.impl.VotingPersistenceImpl
 * @see VotingUtil
 * @generated
 */
@ProviderType
public interface VotingPersistence extends BasePersistence<Voting> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link VotingUtil} to access the voting persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Returns all the votings where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the matching votings
	*/
	public java.util.List<Voting> findByUuid(String uuid);

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
	public java.util.List<Voting> findByUuid(String uuid, int start, int end);

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
	public java.util.List<Voting> findByUuid(String uuid, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Voting> orderByComparator);

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
	public java.util.List<Voting> findByUuid(String uuid, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Voting> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first voting in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching voting
	* @throws NoSuchVotingException if a matching voting could not be found
	*/
	public Voting findByUuid_First(String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<Voting> orderByComparator)
		throws NoSuchVotingException;

	/**
	* Returns the first voting in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching voting, or <code>null</code> if a matching voting could not be found
	*/
	public Voting fetchByUuid_First(String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<Voting> orderByComparator);

	/**
	* Returns the last voting in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching voting
	* @throws NoSuchVotingException if a matching voting could not be found
	*/
	public Voting findByUuid_Last(String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<Voting> orderByComparator)
		throws NoSuchVotingException;

	/**
	* Returns the last voting in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching voting, or <code>null</code> if a matching voting could not be found
	*/
	public Voting fetchByUuid_Last(String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<Voting> orderByComparator);

	/**
	* Returns the votings before and after the current voting in the ordered set where uuid = &#63;.
	*
	* @param votingId the primary key of the current voting
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next voting
	* @throws NoSuchVotingException if a voting with the primary key could not be found
	*/
	public Voting[] findByUuid_PrevAndNext(long votingId, String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<Voting> orderByComparator)
		throws NoSuchVotingException;

	/**
	* Removes all the votings where uuid = &#63; from the database.
	*
	* @param uuid the uuid
	*/
	public void removeByUuid(String uuid);

	/**
	* Returns the number of votings where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the number of matching votings
	*/
	public int countByUuid(String uuid);

	/**
	* Returns the voting where uuid = &#63; and groupId = &#63; or throws a {@link NoSuchVotingException} if it could not be found.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching voting
	* @throws NoSuchVotingException if a matching voting could not be found
	*/
	public Voting findByUUID_G(String uuid, long groupId)
		throws NoSuchVotingException;

	/**
	* Returns the voting where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching voting, or <code>null</code> if a matching voting could not be found
	*/
	public Voting fetchByUUID_G(String uuid, long groupId);

	/**
	* Returns the voting where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching voting, or <code>null</code> if a matching voting could not be found
	*/
	public Voting fetchByUUID_G(String uuid, long groupId,
		boolean retrieveFromCache);

	/**
	* Removes the voting where uuid = &#63; and groupId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the voting that was removed
	*/
	public Voting removeByUUID_G(String uuid, long groupId)
		throws NoSuchVotingException;

	/**
	* Returns the number of votings where uuid = &#63; and groupId = &#63;.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the number of matching votings
	*/
	public int countByUUID_G(String uuid, long groupId);

	/**
	* Returns all the votings where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the matching votings
	*/
	public java.util.List<Voting> findByUuid_C(String uuid, long companyId);

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
	public java.util.List<Voting> findByUuid_C(String uuid, long companyId,
		int start, int end);

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
	public java.util.List<Voting> findByUuid_C(String uuid, long companyId,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Voting> orderByComparator);

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
	public java.util.List<Voting> findByUuid_C(String uuid, long companyId,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Voting> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first voting in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching voting
	* @throws NoSuchVotingException if a matching voting could not be found
	*/
	public Voting findByUuid_C_First(String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<Voting> orderByComparator)
		throws NoSuchVotingException;

	/**
	* Returns the first voting in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching voting, or <code>null</code> if a matching voting could not be found
	*/
	public Voting fetchByUuid_C_First(String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<Voting> orderByComparator);

	/**
	* Returns the last voting in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching voting
	* @throws NoSuchVotingException if a matching voting could not be found
	*/
	public Voting findByUuid_C_Last(String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<Voting> orderByComparator)
		throws NoSuchVotingException;

	/**
	* Returns the last voting in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching voting, or <code>null</code> if a matching voting could not be found
	*/
	public Voting fetchByUuid_C_Last(String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<Voting> orderByComparator);

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
	public Voting[] findByUuid_C_PrevAndNext(long votingId, String uuid,
		long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<Voting> orderByComparator)
		throws NoSuchVotingException;

	/**
	* Removes all the votings where uuid = &#63; and companyId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	*/
	public void removeByUuid_C(String uuid, long companyId);

	/**
	* Returns the number of votings where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the number of matching votings
	*/
	public int countByUuid_C(String uuid, long companyId);

	/**
	* Returns all the votings where className = &#63; and classPK = &#63;.
	*
	* @param className the class name
	* @param classPK the class pk
	* @return the matching votings
	*/
	public java.util.List<Voting> findByF_CLNAME_CLPK(String className,
		String classPK);

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
	public java.util.List<Voting> findByF_CLNAME_CLPK(String className,
		String classPK, int start, int end);

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
	public java.util.List<Voting> findByF_CLNAME_CLPK(String className,
		String classPK, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Voting> orderByComparator);

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
	public java.util.List<Voting> findByF_CLNAME_CLPK(String className,
		String classPK, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Voting> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first voting in the ordered set where className = &#63; and classPK = &#63;.
	*
	* @param className the class name
	* @param classPK the class pk
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching voting
	* @throws NoSuchVotingException if a matching voting could not be found
	*/
	public Voting findByF_CLNAME_CLPK_First(String className, String classPK,
		com.liferay.portal.kernel.util.OrderByComparator<Voting> orderByComparator)
		throws NoSuchVotingException;

	/**
	* Returns the first voting in the ordered set where className = &#63; and classPK = &#63;.
	*
	* @param className the class name
	* @param classPK the class pk
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching voting, or <code>null</code> if a matching voting could not be found
	*/
	public Voting fetchByF_CLNAME_CLPK_First(String className, String classPK,
		com.liferay.portal.kernel.util.OrderByComparator<Voting> orderByComparator);

	/**
	* Returns the last voting in the ordered set where className = &#63; and classPK = &#63;.
	*
	* @param className the class name
	* @param classPK the class pk
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching voting
	* @throws NoSuchVotingException if a matching voting could not be found
	*/
	public Voting findByF_CLNAME_CLPK_Last(String className, String classPK,
		com.liferay.portal.kernel.util.OrderByComparator<Voting> orderByComparator)
		throws NoSuchVotingException;

	/**
	* Returns the last voting in the ordered set where className = &#63; and classPK = &#63;.
	*
	* @param className the class name
	* @param classPK the class pk
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching voting, or <code>null</code> if a matching voting could not be found
	*/
	public Voting fetchByF_CLNAME_CLPK_Last(String className, String classPK,
		com.liferay.portal.kernel.util.OrderByComparator<Voting> orderByComparator);

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
	public Voting[] findByF_CLNAME_CLPK_PrevAndNext(long votingId,
		String className, String classPK,
		com.liferay.portal.kernel.util.OrderByComparator<Voting> orderByComparator)
		throws NoSuchVotingException;

	/**
	* Removes all the votings where className = &#63; and classPK = &#63; from the database.
	*
	* @param className the class name
	* @param classPK the class pk
	*/
	public void removeByF_CLNAME_CLPK(String className, String classPK);

	/**
	* Returns the number of votings where className = &#63; and classPK = &#63;.
	*
	* @param className the class name
	* @param classPK the class pk
	* @return the number of matching votings
	*/
	public int countByF_CLNAME_CLPK(String className, String classPK);

	/**
	* Caches the voting in the entity cache if it is enabled.
	*
	* @param voting the voting
	*/
	public void cacheResult(Voting voting);

	/**
	* Caches the votings in the entity cache if it is enabled.
	*
	* @param votings the votings
	*/
	public void cacheResult(java.util.List<Voting> votings);

	/**
	* Creates a new voting with the primary key. Does not add the voting to the database.
	*
	* @param votingId the primary key for the new voting
	* @return the new voting
	*/
	public Voting create(long votingId);

	/**
	* Removes the voting with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param votingId the primary key of the voting
	* @return the voting that was removed
	* @throws NoSuchVotingException if a voting with the primary key could not be found
	*/
	public Voting remove(long votingId) throws NoSuchVotingException;

	public Voting updateImpl(Voting voting);

	/**
	* Returns the voting with the primary key or throws a {@link NoSuchVotingException} if it could not be found.
	*
	* @param votingId the primary key of the voting
	* @return the voting
	* @throws NoSuchVotingException if a voting with the primary key could not be found
	*/
	public Voting findByPrimaryKey(long votingId) throws NoSuchVotingException;

	/**
	* Returns the voting with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param votingId the primary key of the voting
	* @return the voting, or <code>null</code> if a voting with the primary key could not be found
	*/
	public Voting fetchByPrimaryKey(long votingId);

	@Override
	public java.util.Map<java.io.Serializable, Voting> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the votings.
	*
	* @return the votings
	*/
	public java.util.List<Voting> findAll();

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
	public java.util.List<Voting> findAll(int start, int end);

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
	public java.util.List<Voting> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Voting> orderByComparator);

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
	public java.util.List<Voting> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Voting> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the votings from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of votings.
	*
	* @return the number of votings
	*/
	public int countAll();

	@Override
	public java.util.Set<String> getBadColumnNames();
}