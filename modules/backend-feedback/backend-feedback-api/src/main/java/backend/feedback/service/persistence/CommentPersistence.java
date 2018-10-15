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

import backend.feedback.exception.NoSuchCommentException;

import backend.feedback.model.Comment;

import com.liferay.portal.kernel.service.persistence.BasePersistence;

/**
 * The persistence interface for the comment service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author sondt
 * @see backend.feedback.service.persistence.impl.CommentPersistenceImpl
 * @see CommentUtil
 * @generated
 */
@ProviderType
public interface CommentPersistence extends BasePersistence<Comment> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link CommentUtil} to access the comment persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Returns all the comments where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the matching comments
	*/
	public java.util.List<Comment> findByUuid(String uuid);

	/**
	* Returns a range of all the comments where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CommentModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of comments
	* @param end the upper bound of the range of comments (not inclusive)
	* @return the range of matching comments
	*/
	public java.util.List<Comment> findByUuid(String uuid, int start, int end);

	/**
	* Returns an ordered range of all the comments where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CommentModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of comments
	* @param end the upper bound of the range of comments (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching comments
	*/
	public java.util.List<Comment> findByUuid(String uuid, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Comment> orderByComparator);

	/**
	* Returns an ordered range of all the comments where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CommentModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of comments
	* @param end the upper bound of the range of comments (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching comments
	*/
	public java.util.List<Comment> findByUuid(String uuid, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Comment> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first comment in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching comment
	* @throws NoSuchCommentException if a matching comment could not be found
	*/
	public Comment findByUuid_First(String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<Comment> orderByComparator)
		throws NoSuchCommentException;

	/**
	* Returns the first comment in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching comment, or <code>null</code> if a matching comment could not be found
	*/
	public Comment fetchByUuid_First(String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<Comment> orderByComparator);

	/**
	* Returns the last comment in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching comment
	* @throws NoSuchCommentException if a matching comment could not be found
	*/
	public Comment findByUuid_Last(String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<Comment> orderByComparator)
		throws NoSuchCommentException;

	/**
	* Returns the last comment in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching comment, or <code>null</code> if a matching comment could not be found
	*/
	public Comment fetchByUuid_Last(String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<Comment> orderByComparator);

	/**
	* Returns the comments before and after the current comment in the ordered set where uuid = &#63;.
	*
	* @param commentId the primary key of the current comment
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next comment
	* @throws NoSuchCommentException if a comment with the primary key could not be found
	*/
	public Comment[] findByUuid_PrevAndNext(long commentId, String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<Comment> orderByComparator)
		throws NoSuchCommentException;

	/**
	* Removes all the comments where uuid = &#63; from the database.
	*
	* @param uuid the uuid
	*/
	public void removeByUuid(String uuid);

	/**
	* Returns the number of comments where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the number of matching comments
	*/
	public int countByUuid(String uuid);

	/**
	* Returns the comment where uuid = &#63; and groupId = &#63; or throws a {@link NoSuchCommentException} if it could not be found.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching comment
	* @throws NoSuchCommentException if a matching comment could not be found
	*/
	public Comment findByUUID_G(String uuid, long groupId)
		throws NoSuchCommentException;

	/**
	* Returns the comment where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching comment, or <code>null</code> if a matching comment could not be found
	*/
	public Comment fetchByUUID_G(String uuid, long groupId);

	/**
	* Returns the comment where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching comment, or <code>null</code> if a matching comment could not be found
	*/
	public Comment fetchByUUID_G(String uuid, long groupId,
		boolean retrieveFromCache);

	/**
	* Removes the comment where uuid = &#63; and groupId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the comment that was removed
	*/
	public Comment removeByUUID_G(String uuid, long groupId)
		throws NoSuchCommentException;

	/**
	* Returns the number of comments where uuid = &#63; and groupId = &#63;.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the number of matching comments
	*/
	public int countByUUID_G(String uuid, long groupId);

	/**
	* Returns all the comments where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the matching comments
	*/
	public java.util.List<Comment> findByUuid_C(String uuid, long companyId);

	/**
	* Returns a range of all the comments where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CommentModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of comments
	* @param end the upper bound of the range of comments (not inclusive)
	* @return the range of matching comments
	*/
	public java.util.List<Comment> findByUuid_C(String uuid, long companyId,
		int start, int end);

	/**
	* Returns an ordered range of all the comments where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CommentModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of comments
	* @param end the upper bound of the range of comments (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching comments
	*/
	public java.util.List<Comment> findByUuid_C(String uuid, long companyId,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Comment> orderByComparator);

	/**
	* Returns an ordered range of all the comments where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CommentModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of comments
	* @param end the upper bound of the range of comments (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching comments
	*/
	public java.util.List<Comment> findByUuid_C(String uuid, long companyId,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Comment> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first comment in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching comment
	* @throws NoSuchCommentException if a matching comment could not be found
	*/
	public Comment findByUuid_C_First(String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<Comment> orderByComparator)
		throws NoSuchCommentException;

	/**
	* Returns the first comment in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching comment, or <code>null</code> if a matching comment could not be found
	*/
	public Comment fetchByUuid_C_First(String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<Comment> orderByComparator);

	/**
	* Returns the last comment in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching comment
	* @throws NoSuchCommentException if a matching comment could not be found
	*/
	public Comment findByUuid_C_Last(String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<Comment> orderByComparator)
		throws NoSuchCommentException;

	/**
	* Returns the last comment in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching comment, or <code>null</code> if a matching comment could not be found
	*/
	public Comment fetchByUuid_C_Last(String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<Comment> orderByComparator);

	/**
	* Returns the comments before and after the current comment in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param commentId the primary key of the current comment
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next comment
	* @throws NoSuchCommentException if a comment with the primary key could not be found
	*/
	public Comment[] findByUuid_C_PrevAndNext(long commentId, String uuid,
		long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<Comment> orderByComparator)
		throws NoSuchCommentException;

	/**
	* Removes all the comments where uuid = &#63; and companyId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	*/
	public void removeByUuid_C(String uuid, long companyId);

	/**
	* Returns the number of comments where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the number of matching comments
	*/
	public int countByUuid_C(String uuid, long companyId);

	/**
	* Returns all the comments where groupId = &#63;.
	*
	* @param groupId the group ID
	* @return the matching comments
	*/
	public java.util.List<Comment> findByF_groupId(long groupId);

	/**
	* Returns a range of all the comments where groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CommentModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param start the lower bound of the range of comments
	* @param end the upper bound of the range of comments (not inclusive)
	* @return the range of matching comments
	*/
	public java.util.List<Comment> findByF_groupId(long groupId, int start,
		int end);

	/**
	* Returns an ordered range of all the comments where groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CommentModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param start the lower bound of the range of comments
	* @param end the upper bound of the range of comments (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching comments
	*/
	public java.util.List<Comment> findByF_groupId(long groupId, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<Comment> orderByComparator);

	/**
	* Returns an ordered range of all the comments where groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CommentModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param start the lower bound of the range of comments
	* @param end the upper bound of the range of comments (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching comments
	*/
	public java.util.List<Comment> findByF_groupId(long groupId, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<Comment> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first comment in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching comment
	* @throws NoSuchCommentException if a matching comment could not be found
	*/
	public Comment findByF_groupId_First(long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<Comment> orderByComparator)
		throws NoSuchCommentException;

	/**
	* Returns the first comment in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching comment, or <code>null</code> if a matching comment could not be found
	*/
	public Comment fetchByF_groupId_First(long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<Comment> orderByComparator);

	/**
	* Returns the last comment in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching comment
	* @throws NoSuchCommentException if a matching comment could not be found
	*/
	public Comment findByF_groupId_Last(long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<Comment> orderByComparator)
		throws NoSuchCommentException;

	/**
	* Returns the last comment in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching comment, or <code>null</code> if a matching comment could not be found
	*/
	public Comment fetchByF_groupId_Last(long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<Comment> orderByComparator);

	/**
	* Returns the comments before and after the current comment in the ordered set where groupId = &#63;.
	*
	* @param commentId the primary key of the current comment
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next comment
	* @throws NoSuchCommentException if a comment with the primary key could not be found
	*/
	public Comment[] findByF_groupId_PrevAndNext(long commentId, long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<Comment> orderByComparator)
		throws NoSuchCommentException;

	/**
	* Removes all the comments where groupId = &#63; from the database.
	*
	* @param groupId the group ID
	*/
	public void removeByF_groupId(long groupId);

	/**
	* Returns the number of comments where groupId = &#63;.
	*
	* @param groupId the group ID
	* @return the number of matching comments
	*/
	public int countByF_groupId(long groupId);

	/**
	* Caches the comment in the entity cache if it is enabled.
	*
	* @param comment the comment
	*/
	public void cacheResult(Comment comment);

	/**
	* Caches the comments in the entity cache if it is enabled.
	*
	* @param comments the comments
	*/
	public void cacheResult(java.util.List<Comment> comments);

	/**
	* Creates a new comment with the primary key. Does not add the comment to the database.
	*
	* @param commentId the primary key for the new comment
	* @return the new comment
	*/
	public Comment create(long commentId);

	/**
	* Removes the comment with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param commentId the primary key of the comment
	* @return the comment that was removed
	* @throws NoSuchCommentException if a comment with the primary key could not be found
	*/
	public Comment remove(long commentId) throws NoSuchCommentException;

	public Comment updateImpl(Comment comment);

	/**
	* Returns the comment with the primary key or throws a {@link NoSuchCommentException} if it could not be found.
	*
	* @param commentId the primary key of the comment
	* @return the comment
	* @throws NoSuchCommentException if a comment with the primary key could not be found
	*/
	public Comment findByPrimaryKey(long commentId)
		throws NoSuchCommentException;

	/**
	* Returns the comment with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param commentId the primary key of the comment
	* @return the comment, or <code>null</code> if a comment with the primary key could not be found
	*/
	public Comment fetchByPrimaryKey(long commentId);

	@Override
	public java.util.Map<java.io.Serializable, Comment> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the comments.
	*
	* @return the comments
	*/
	public java.util.List<Comment> findAll();

	/**
	* Returns a range of all the comments.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CommentModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of comments
	* @param end the upper bound of the range of comments (not inclusive)
	* @return the range of comments
	*/
	public java.util.List<Comment> findAll(int start, int end);

	/**
	* Returns an ordered range of all the comments.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CommentModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of comments
	* @param end the upper bound of the range of comments (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of comments
	*/
	public java.util.List<Comment> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Comment> orderByComparator);

	/**
	* Returns an ordered range of all the comments.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CommentModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of comments
	* @param end the upper bound of the range of comments (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of comments
	*/
	public java.util.List<Comment> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Comment> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the comments from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of comments.
	*
	* @return the number of comments
	*/
	public int countAll();

	@Override
	public java.util.Set<String> getBadColumnNames();
}