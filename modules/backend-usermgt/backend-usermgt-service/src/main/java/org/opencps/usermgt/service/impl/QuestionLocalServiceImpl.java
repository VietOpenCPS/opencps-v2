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

import java.util.Date;
import java.util.List;

import org.opencps.usermgt.model.Question;
import org.opencps.usermgt.service.base.QuestionLocalServiceBaseImpl;

/**
 * The implementation of the question local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link org.opencps.usermgt.service.QuestionLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author khoavu
 * @see QuestionLocalServiceBaseImpl
 * @see org.opencps.usermgt.service.QuestionLocalServiceUtil
 */
public class QuestionLocalServiceImpl extends QuestionLocalServiceBaseImpl {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this class directly. Always use {@link org.opencps.usermgt.service.QuestionLocalServiceUtil} to access the question local service.
	 */
	public Question updateQuestion(long companyId, long groupId, long questionId, String fullname, String email,
			String content, int publish, String govAgencyCode, String govAgencyName) {
		Question question = null;
		Date now = new Date();
		
		if (questionId == 0) {
			questionId = counterLocalService.increment(Question.class.getName());
			question = questionPersistence.create(questionId);
			
			question.setCreateDate(now);
			question.setModifiedDate(now);
			question.setCompanyId(companyId);
			question.setGroupId(groupId);
			question.setFullname(fullname);
			question.setEmail(email);
			question.setContent(content);
			question.setPublish(publish);
			question.setGovAgencyCode(govAgencyCode);
			question.setGovAgencyName(govAgencyName);
		}
		else {
			question = questionPersistence.fetchByPrimaryKey(questionId);
			
			question.setModifiedDate(now);
			question.setCompanyId(companyId);
			question.setGroupId(groupId);
			question.setFullname(fullname);
			question.setEmail(email);
			question.setContent(content);
			question.setPublish(publish);
			question.setGovAgencyCode(govAgencyCode);
			question.setGovAgencyName(govAgencyName);
		}
		return questionPersistence.update(question);
	}
	
	public List<Question> findByG_PL(long groupId, int[] publishs, int start, int end) {
		return questionPersistence.findByG_PL(groupId, publishs, start, end);
	}

	public int countByG_PL(long groupId, int[] publishs) {
		return questionPersistence.countByG_PL(groupId, publishs);
	}

	public List<Question> findByQuerySearch(long groupId, String keyword, String govAgencyCode, Integer publish,
			String questionType, Boolean answer,
			int start, int limit) {
		return questionFinder.findQuestionSearch(groupId, keyword, govAgencyCode, publish, questionType, answer, start, limit);
	}

	public int countByQuerySearch(long groupId, String keyword, String govAgencyCode, Integer publish, String questionType, Boolean answer) {
		return questionFinder.countQuestionSearch(groupId, keyword, govAgencyCode, publish, questionType, answer);
	}
}