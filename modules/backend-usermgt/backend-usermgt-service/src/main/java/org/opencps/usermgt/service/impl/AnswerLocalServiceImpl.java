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

package org.opencps.usermgt.service.impl;

import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;

import java.util.Date;
import java.util.List;

import org.opencps.usermgt.model.Answer;
import org.opencps.usermgt.service.base.AnswerLocalServiceBaseImpl;

/**
 * The implementation of the answer local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link org.opencps.usermgt.service.AnswerLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author khoavu
 * @see AnswerLocalServiceBaseImpl
 * @see org.opencps.usermgt.service.AnswerLocalServiceUtil
 */
public class AnswerLocalServiceImpl extends AnswerLocalServiceBaseImpl {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this class directly. Always use {@link org.opencps.usermgt.service.AnswerLocalServiceUtil} to access the answer local service.
	 */
	public Answer updateAnswer(long userId, long groupId, long answerId, long questionId, String content, int publish) {
		Answer answer = null;
		Date now = new Date();
		User user = UserLocalServiceUtil.fetchUser(userId);
		
		if (answerId == 0) {
			answerId = counterLocalService.increment(Answer.class.getName());
			
			answer = answerPersistence.create(answerId);
			answer.setCreateDate(now);
			answer.setModifiedDate(now);
			
			answer.setCompanyId(user.getCompanyId());
			answer.setGroupId(groupId);
			answer.setUserName(user.getFullName());
			answer.setUserId(userId);
			answer.setContent(content);
			answer.setQuestionId(questionId);
			answer.setPublish(publish);
		}
		else {
			answer = answerPersistence.fetchByPrimaryKey(answerId);
			
			answer.setModifiedDate(now);
			
			answer.setCompanyId(user.getCompanyId());
			answer.setGroupId(groupId);
			answer.setUserName(user.getFullName());
			answer.setUserId(userId);
			answer.setContent(content);
			answer.setQuestionId(questionId);
			answer.setPublish(publish);			
		}
		
		return answerPersistence.update(answer);
	}
	
	public List<Answer> findByG_Q_PL(long groupId, long questionId, int[] publishs, int start, int end) {
		return answerPersistence.findByG_Q_PL(groupId, questionId, publishs, start, end);
	}
	
	public int countByG_Q_PL(long groupId, long questionId, int[] publishs) {
		return answerPersistence.countByG_Q_PL(groupId, questionId, publishs);
	}
	
}