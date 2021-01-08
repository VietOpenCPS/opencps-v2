package org.opencps.usermgt.service.persistence.impl;

import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.dao.orm.QueryPos;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.SQLQuery;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.spring.extender.service.ServiceReference;

import java.util.List;

import org.opencps.usermgt.model.Question;
import org.opencps.usermgt.model.impl.QuestionImpl;
import org.opencps.usermgt.service.persistence.QuestionFinder;
import com.liferay.portal.dao.orm.custom.sql.CustomSQL;

import aQute.bnd.annotation.ProviderType;

@ProviderType
public class QuestionFinderImpl extends QuestionFinderBaseImpl
		 implements QuestionFinder {

	Log _log = LogFactoryUtil.getLog(QuestionFinderImpl.class);

	private static final String SEARCH_QUESTION = QuestionFinder.class.getName()
			+ ".findQuestionSearch";

	private static final String CONDITION_DOMAIN_CODE = "(q.domainCode = ?) AND";
	private static final String CONDITION_GOV_AGENCY = "(q.govAgencyCode = ?) AND";
	private static final String CONDITION_PUBLISH = "(q.publish = ?) AND";
	private static final String CONDITION_QUESTION_TYPE = "(q.questionType = ?) AND";
	private static final String CONDITION_SUB_DOMAIN_CODE = "(q.subDomainCode = ?) AND";
	private static final String CONDITION_ANSWER = "HAVING count(a.answerId) = ? ";
	private static final String CONDITION_HAVE_ANSWER = "having count(a.answerId) > 0 ";
	private static final String CONDITION_NOT_HAVE_ANSWER = "having count(a.answerId) = 0 ";
	private static final String CONDITION_LIMIT = "LIMIT ?";
	private static final String CONDITION_OFFSET = "OFFSET ?";
	private static final String QUESTION_ENTITY = "Question";

	@SuppressWarnings("unchecked")
	public List<Question> findQuestionSearch(long groupId, String keyword, String domainCode, String govAgencyCode, Integer publish, String questionType, 
			Boolean answer, String subDomainCode,
			int start,
			int limit) {
//
		Session session = null;

		try {
			session = openSession();

			//_log.info("SEARCH_QUESTION: "+SEARCH_QUESTION);
			String sql = _customSQL.get(getClass(), SEARCH_QUESTION);

			if (Validator.isNull(domainCode)) {
				sql = StringUtil.replace(sql, CONDITION_DOMAIN_CODE, StringPool.BLANK);
		}
			if (Validator.isNull(govAgencyCode)) {
				sql = StringUtil.replace(sql, CONDITION_GOV_AGENCY, StringPool.BLANK);
		}
			if (Validator.isNull(publish)) {
				sql = StringUtil.replace(sql, CONDITION_PUBLISH, StringPool.BLANK);
		}
			if (Validator.isNull(subDomainCode)) {
				sql = StringUtil.replace(sql, CONDITION_SUB_DOMAIN_CODE, StringPool.BLANK);
		}

		if (answer != null) {
			if (answer) {
					sql = StringUtil.replace(sql, CONDITION_ANSWER, CONDITION_HAVE_ANSWER);
			}
			else {
					sql = StringUtil.replace(sql, CONDITION_ANSWER, CONDITION_NOT_HAVE_ANSWER);
				}
			} else {
				sql = StringUtil.replace(sql, CONDITION_ANSWER, StringPool.BLANK);
			}

			//_log.info("sql search: "+sql);

			SQLQuery q = session.createSQLQuery(sql);

			q.setCacheable(true);
			q.addEntity(QUESTION_ENTITY, QuestionImpl.class);

			QueryPos qPos = QueryPos.getInstance(q);

			/* add domainCode parameter */
			if (Validator.isNotNull(domainCode)) {
				qPos.add(domainCode);
			}

			/* add govAgency parameter */
			if (Validator.isNotNull(govAgencyCode)) {
				qPos.add(govAgencyCode);
			}

			/* add publish parameter */
			if (Validator.isNotNull(publish)) {
				qPos.add(publish);
		}
		
			/* add publish parameter questionType */
				qPos.add(questionType);
			
			/* add publish parameter */
			if (Validator.isNotNull(subDomainCode)) {
				qPos.add(subDomainCode);
		}
			//
			/* add publish parameter */
			qPos.add(limit - start);

			/* add publish parameter */
			qPos.add(start);

			List<Question> ls = (List<Question>) QueryUtil.list(q, getDialect(), QueryUtil.ALL_POS, QueryUtil.ALL_POS);

			return ls;
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

		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public int countQuestionSearch(long groupId, String keyword, String domainCode, String govAgencyCode, Integer publish, String questionType, Boolean answer, String subDomainCode) {

		Session session = null;

		try {
			session = openSession();

			String sql = _customSQL.get(getClass(), SEARCH_QUESTION);

			if (Validator.isNull(domainCode)) {
				sql = StringUtil.replace(sql, CONDITION_DOMAIN_CODE, StringPool.BLANK);
			}
			if (Validator.isNull(govAgencyCode)) {
				sql = StringUtil.replace(sql, CONDITION_GOV_AGENCY, StringPool.BLANK);
			}
			if (Validator.isNull(publish)) {
				sql = StringUtil.replace(sql, CONDITION_PUBLISH, StringPool.BLANK);
			}
//			if (Validator.isNull(questionType)) {
//				sql = StringUtil.replace(sql, CONDITION_QUESTION_TYPE, StringPool.BLANK);
//			}
			if (Validator.isNull(subDomainCode)) {
				sql = StringUtil.replace(sql, CONDITION_SUB_DOMAIN_CODE, StringPool.BLANK);
			}

			if (answer != null) {
				if (answer) {
					sql = StringUtil.replace(sql, CONDITION_ANSWER, CONDITION_HAVE_ANSWER);
				}
				else {
					sql = StringUtil.replace(sql, CONDITION_ANSWER, CONDITION_NOT_HAVE_ANSWER);
				}
			} else {
				sql = StringUtil.replace(sql, CONDITION_ANSWER, StringPool.BLANK);
		}

			//
			sql = StringUtil.replace(sql, CONDITION_LIMIT, StringPool.BLANK);
			//
			sql = StringUtil.replace(sql, CONDITION_OFFSET, StringPool.BLANK);
			//_log.info("sql count: "+ sql);

			SQLQuery q = session.createSQLQuery(sql);

			q.setCacheable(true);
			q.addEntity(QUESTION_ENTITY, QuestionImpl.class);

			QueryPos qPos = QueryPos.getInstance(q);

			/* add domainCode parameter */
		if (Validator.isNotNull(domainCode)) {
				qPos.add(domainCode);
		}

			/* add govAgency parameter */
		if (Validator.isNotNull(govAgencyCode)) {
				qPos.add(govAgencyCode);
		}

			/* add publish parameter */
		if (Validator.isNotNull(publish)) {
				qPos.add(publish);
		}
			
			/* add publish parameter */
//		if (Validator.isNotNull(questionType)) {
				qPos.add(questionType);
//		}
			
			/* add publish parameter */
		if (Validator.isNotNull(subDomainCode)) {
				qPos.add(subDomainCode);
		}

			List<Question> ls = (List<Question>) QueryUtil.list(q, getDialect(), QueryUtil.ALL_POS, QueryUtil.ALL_POS);

			if (ls.size() > 0) {
				return ls.size();
			} 

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

		return 0;
	}

	@ServiceReference(type = CustomSQL.class)
	private CustomSQL _customSQL;
}
