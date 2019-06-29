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
	public List<Question> findQuestionSearch(long groupId, String keyword, String govAgencyCode, Integer publish, int start,
			int limit) {

		Session session = null;
		List<Question> questionList = null;
		StringBuilder sb = new StringBuilder();
		
		sb.append("SELECT * FROM opencps_question ");
		if (Validator.isNotNull(keyword)) {
			sb.append("WHERE content like '%"+keyword+"%' ");
			if (Validator.isNotNull(govAgencyCode)) {
				sb.append("AND govAgencyCode = '"+ govAgencyCode +"' ");
			}
			if (Validator.isNotNull(publish)) {
				sb.append("AND publish = " + publish + " ");
			}
		} else if(Validator.isNotNull(govAgencyCode)){
			sb.append("WHERE govAgencyCode = '"+ govAgencyCode +"' ");
			if (Validator.isNotNull(publish)) {
				sb.append("AND publish = " + publish + " ");
			}
		} else if (Validator.isNotNull(publish)) {
			sb.append("WHERE publish = " + publish + " ");
		}

		if (limit > 0) {
			sb.append("LIMIT " + limit + " ");
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
	public int countQuestionSearch(long groupId, String keyword, String govAgencyCode, Integer publish) {

		Session session = null;
		int countQuestion = 0;
		StringBuilder sb = new StringBuilder();
		
		sb.append("SELECT * FROM opencps_question ");
		if (Validator.isNotNull(keyword)) {
			sb.append("WHERE content like '%"+keyword+"%' ");
			if (Validator.isNotNull(govAgencyCode)) {
				sb.append("AND govAgencyCode = '"+ govAgencyCode +"' ");
			}
			if (Validator.isNotNull(publish)) {
				sb.append("AND publish = " + publish + " ");
			}
		} else if(Validator.isNotNull(govAgencyCode)){
			sb.append("WHERE govAgencyCode = '"+ govAgencyCode +"' ");
			if (Validator.isNotNull(publish)) {
				sb.append("AND publish = " + publish + " ");
			}
		} else if (Validator.isNotNull(publish)) {
			sb.append("WHERE publish = " + publish + " ");
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
