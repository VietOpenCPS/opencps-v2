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

package org.opencps.dossiermgt.service.impl;

import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.Validator;

import java.util.Date;
import java.util.List;

import org.opencps.dossiermgt.model.NewsBoard;
import org.opencps.dossiermgt.service.base.NewsBoardLocalServiceBaseImpl;

/**
 * The implementation of the news board local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link org.opencps.dossiermgt.service.NewsBoardLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author huymq
 * @see NewsBoardLocalServiceBaseImpl
 * @see org.opencps.dossiermgt.service.NewsBoardLocalServiceUtil
 */
public class NewsBoardLocalServiceImpl extends NewsBoardLocalServiceBaseImpl {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this class directly. Always use {@link org.opencps.dossiermgt.service.NewsBoardLocalServiceUtil} to access the news board local service.
	 */

	public List<NewsBoard> getNewsBoardList(long groupId, int start, int end) {

		return newsBoardPersistence.findByF_GID(groupId, start, end);

	}

	public int countByNewsBoardList(long groupId) {

		return newsBoardPersistence.countByF_GID(groupId);

	}
	
	public NewsBoard createNewsBoard(long groupId, long userId, String newsTitle, String newsContent, int newsStatus,
			ServiceContext context) {

		Date now = new Date();

		User auditUser = userPersistence.fetchByPrimaryKey(userId);

		long newsBoardId = counterLocalService.increment(NewsBoard.class.getName());

		NewsBoard newsBoard = newsBoardPersistence.create(newsBoardId);

		newsBoard.setGroupId(groupId);
		newsBoard.setCompanyId(auditUser.getCompanyId());
		newsBoard.setUserId(auditUser.getUserId());
		newsBoard.setUserName(auditUser.getFullName());
		newsBoard.setCreateDate(now);
		newsBoard.setModifiedDate(now);

		newsBoard.setNewsTitle(newsTitle);
		newsBoard.setNewsContent(newsContent);
		newsBoard.setNewsStatus(newsStatus);

		return newsBoardPersistence.update(newsBoard);

	}

	public NewsBoard updateNewsBoard(long newsBoardId, long groupId, long userId, String newsTitle, String newsContent, int newsStatus,
			ServiceContext context) {

		Date now = new Date();

		User auditUser = userPersistence.fetchByPrimaryKey(userId);

		NewsBoard newsBoard = newsBoardPersistence.fetchByPrimaryKey(newsBoardId);
		if (newsBoard != null) {
			// Audit field
			newsBoard.setUserId(auditUser.getUserId());
			newsBoard.setModifiedDate(now);

			if (Validator.isNotNull(newsTitle)) {
				newsBoard.setNewsTitle(newsTitle);
			}
			if (Validator.isNotNull(newsContent)) {
				newsBoard.setNewsContent(newsContent);
			}
			newsBoard.setNewsStatus(newsStatus);
		} else {
			newsBoardId = counterLocalService.increment(NewsBoard.class.getName());
			newsBoard = newsBoardPersistence.create(newsBoardId);

			// Audit field
			newsBoard.setGroupId(groupId);
			newsBoard.setCompanyId(auditUser.getCompanyId());
			newsBoard.setUserId(auditUser.getUserId());
			newsBoard.setUserName(auditUser.getFullName());
			newsBoard.setCreateDate(now);
			newsBoard.setModifiedDate(now);

			newsBoard.setNewsTitle(newsTitle);
			newsBoard.setNewsContent(newsContent);
			newsBoard.setNewsStatus(newsStatus);
		}

		return newsBoardPersistence.update(newsBoard);

	}

}