package org.opencps.usermgt.service.persistence.impl;

import com.liferay.portal.kernel.dao.orm.Query;
import com.liferay.portal.kernel.dao.orm.SQLQuery;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.Validator;

import java.util.List;

import org.opencps.usermgt.model.Question;
import org.opencps.usermgt.model.impl.QuestionImpl;
import org.opencps.usermgt.service.persistence.QuestionFinder;

import aQute.bnd.annotation.ProviderType;

@ProviderType
public class QuestionFinderImpl extends QuestionFinderBaseImpl
		 implements QuestionFinder {

	Log _log = LogFactoryUtil.getLog(QuestionFinderImpl.class);

	@SuppressWarnings("unchecked")
	public List<Question> findQuestionSearch(long groupId, String keyword, String govAgencyCode, Integer publish, String questionType, 
			Boolean answer, String subDomainCode,
			int start,
			int limit) {

		Session session = null;
		List<Question> questionList = null;
		StringBuilder sb = new StringBuilder();
		
		sb.append("SELECT q.* FROM opencps_question AS q left join opencps_answer as a on q.questionId=a.questionId ");
		if (Validator.isNotNull(keyword)) {
			sb.append("WHERE q.content like '%"+keyword+"%' ");
			if (Validator.isNotNull(govAgencyCode)) {
				sb.append("AND q.govAgencyCode = '"+ govAgencyCode +"' ");
			}
			if (Validator.isNotNull(publish)) {
				sb.append("AND q.publish = " + publish + " ");
			}
			if (Validator.isNotNull(questionType)) {
				sb.append("AND q.questionType = '" + questionType + "' ");
			}
			if (Validator.isNotNull(subDomainCode)) {
				sb.append("AND q.subDomainCode = '" + subDomainCode + "' ");
			}
		} else if(Validator.isNotNull(govAgencyCode)){
			sb.append("WHERE q.govAgencyCode = '"+ govAgencyCode +"' ");
			if (Validator.isNotNull(publish)) {
				sb.append("AND q.publish = " + publish + " ");
			}
			if (Validator.isNotNull(questionType)) {
				sb.append("AND q.questionType = '" + questionType + "' ");
			}
			if (Validator.isNotNull(subDomainCode)) {
				sb.append("AND q.subDomainCode = '" + subDomainCode + "' ");
			}
		} else if (Validator.isNotNull(publish)) {
			sb.append("WHERE q.publish = " + publish + " ");
			if (Validator.isNotNull(questionType)) {
				sb.append("AND q.questionType = '" + questionType + "' ");
			}
			if (Validator.isNotNull(subDomainCode)) {
				sb.append("AND q.subDomainCode = '" + subDomainCode + "' ");
			}
		}
		else if (Validator.isNotNull(questionType)) {
			sb.append("WHERE q.questionType = '" + questionType + "' ");
			if (Validator.isNotNull(subDomainCode)) {
				sb.append("AND q.subDomainCode = '" + subDomainCode + "' ");
			}
		} else if (Validator.isNotNull(subDomainCode)) {
			sb.append("WHERE q.subDomainCode = '" + subDomainCode + "' ");
		}
		
		if (answer != null) {
			if (answer) {
				sb.append("group by q.questionId having count(a.answerId) > 0 ");
			}
			else {
				sb.append("group by q.questionId having count(a.answerId) = 0 ");
			}			
		}
		
		if (limit > 0) {
			sb.append("LIMIT " + (limit - start) + " ");
		}
		if (start >= 0) {
			sb.append("OFFSET " + start + "");
		}
		
		System.out.println("SQL: "+ sb.toString());
		try {
			session = openSession();

			SQLQuery q = session.createSQLQuery(sb.toString());
			q.setCacheable(false);
			q.addEntity("opencps_question", QuestionImpl.class);

			questionList = q.list();
//		        _log.info("SQL list deliverable: "+ deliverableList);
		} catch (Exception e) {
			try {
				throw new SystemException(e);
			} catch (SystemException se) {
//		            se.printStackTrace();
				_log.error(se);
			}
		} finally {
			closeSession(session);
		}

		return questionList;
	}

	@SuppressWarnings("unchecked")
	public int countQuestionSearch(long groupId, String keyword, String govAgencyCode, Integer publish, String questionType, Boolean answer, String subDomainCode) {

		Session session = null;
		int countQuestion = 0;
		StringBuilder sb = new StringBuilder();
		
		sb.append("SELECT q.* FROM opencps_question AS q left join opencps_answer as a on q.questionId=a.questionId ");
		if (Validator.isNotNull(keyword)) {
			sb.append("WHERE q.content like '%"+keyword+"%' ");
			if (Validator.isNotNull(govAgencyCode)) {
				sb.append("AND q.govAgencyCode = '"+ govAgencyCode +"' ");
			}
			if (Validator.isNotNull(publish)) {
				sb.append("AND q.publish = " + publish + " ");
			}
			if (Validator.isNotNull(questionType)) {
				sb.append("AND q.questionType = '" + questionType + "' ");
			}
			if (Validator.isNotNull(subDomainCode)) {
				sb.append("AND q.subDomainCode = '" + subDomainCode + "' ");
			}
		} else if(Validator.isNotNull(govAgencyCode)){
			sb.append("WHERE q.govAgencyCode = '"+ govAgencyCode +"' ");
			if (Validator.isNotNull(publish)) {
				sb.append("AND q.publish = " + publish + " ");
			}
			if (Validator.isNotNull(questionType)) {
				sb.append("AND q.questionType = '" + questionType + "' ");
			}
			if (Validator.isNotNull(subDomainCode)) {
				sb.append("AND q.subDomainCode = '" + subDomainCode + "' ");
			}
		} else if (Validator.isNotNull(publish)) {
			sb.append("WHERE q.publish = " + publish + " ");
			if (Validator.isNotNull(questionType)) {
				sb.append("AND q.questionType = '" + questionType + "' ");
			}
			if (Validator.isNotNull(subDomainCode)) {
				sb.append("AND q.subDomainCode = '" + subDomainCode + "' ");
			}
		}
		else if (Validator.isNotNull(questionType)) {
			sb.append("WHERE q.questionType = '" + questionType + "' ");
			if (Validator.isNotNull(subDomainCode)) {
				sb.append("AND q.subDomainCode = '" + subDomainCode + "' ");
			}
		} else if (Validator.isNotNull(subDomainCode)) {
			sb.append("WHERE q.subDomainCode = '" + subDomainCode + "' ");
		}
		
		if (answer != null) {
			if (answer) {
				sb.append("group by q.questionId having count(a.answerId) > 0 ");
			}
			else {
				sb.append("group by q.questionId having count(a.answerId) = 0 ");
			}			
		}

		_log.info("SQL: "+ sb.toString());
		try {
			session = openSession();

			SQLQuery q = session.createSQLQuery(sb.toString());
			q.setCacheable(false);
			q.addEntity("opencps_question", QuestionImpl.class);

			List<Question> questionList = q.list();
			if (questionList != null) {
				countQuestion = questionList.size();
			}
//		        _log.info("SQL list deliverable: "+ deliverableList);
		} catch (Exception e) {
			try {
				throw new SystemException(e);
			} catch (SystemException se) {
//		            se.printStackTrace();
				_log.error(se);
			}
		} finally {
			closeSession(session);
		}

		return countQuestion;
	}
}
