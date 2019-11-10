package org.opencps.statistic.service.persistence.impl;

import com.liferay.petra.string.StringPool;
import com.liferay.portal.dao.orm.custom.sql.CustomSQL;
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

import org.opencps.statistic.model.OpencpsPersonStatistic;
import org.opencps.statistic.model.OpencpsVotingStatistic;
import org.opencps.statistic.model.impl.OpencpsPersonStatisticImpl;
import org.opencps.statistic.model.impl.OpencpsVotingStatisticImpl;
import org.opencps.statistic.service.persistence.OpencpsPersonStatisticFinder;

public class OpencpsPersonStatisticFinderImpl extends OpencpsPersonStatisticFinderBaseImpl
		implements OpencpsPersonStatisticFinder {

	private final static Log LOG = LogFactoryUtil.getLog(OpencpsPersonStatisticFinderImpl.class);

	private static final String SEARCH_PERSON_STATISTIC = OpencpsPersonStatisticFinder.class.getName()
			+ ".searchStatisic";

	private static final String TOTAL = ConstantUtils.TOTAL;

	private static final String CONDITION_EMPLOYEE = "(opencps_person_statistic.employeeId = ?) AND";
	private static final String CONDITION_GOV_AGENCY = "(opencps_person_statistic.govAgencyCode = ?) AND";
	private static final String CONDITION_GOV_AGENCY_REPLACE = "(opencps_person_statistic.govAgencyCode IS NULL) AND";
	private static final String CONDITION_VOTING_CODE = "(opencps_person_statistic.votingCode = ?) AND";
	private static final String CONDITION_VOTING_CODE_REPLACE = "(opencps_person_statistic.votingCode IS NULL) AND";
	private static final String CONDITION_MONTH = "(opencps_person_statistic.month = ?) AND";
	private static final String CONDITION_MONTH_REPLACE = "(opencps_person_statistic.month != 0) AND";
	private static final String CONDITION_YEAR = "(opencps_person_statistic.year = ?) AND";

	public static final int ALL_MONTH = -1;

	@SuppressWarnings("unchecked")
	public OpencpsPersonStatistic checkContains(long groupId, int month, int year, String govAgency, Long employeeId,
			String votingCode) {
		Session session = null;

		try {
			session = openSession();

			String sql = _customSQL.get(getClass(), SEARCH_PERSON_STATISTIC);

			if (Validator.isNull(votingCode)) {
				sql = StringUtil.replace(sql, CONDITION_VOTING_CODE, CONDITION_VOTING_CODE_REPLACE);
			}

			// if (employeeId == 0) {
			// sql = StringUtil.replace(sql, CONDITION_EMPLOYEE, StringPool.BLANK);
			// }

			if (Validator.isNull(govAgency)) {
				sql = StringUtil.replace(sql, CONDITION_GOV_AGENCY, CONDITION_GOV_AGENCY_REPLACE);
			}

			//LOG.info(sql);

			SQLQuery q = session.createSQLQuery(sql);

			q.setCacheable(false);
			q.addEntity("OpencpsPersonStatistic", OpencpsPersonStatisticImpl.class);

			QueryPos qPos = QueryPos.getInstance(q);

			/* add month parameter */
			qPos.add(month);

			/* add year parameter */
			qPos.add(year);

			/* add votingCode parameter */
			if (Validator.isNotNull(votingCode)) {
				qPos.add(votingCode);
			}

			/* add service parameter */
			if (Validator.isNull(employeeId)) {
				qPos.add(0);
			} else {
				qPos.add(employeeId);
			}

			/* add govAgency parameter */
			if (Validator.isNotNull(govAgency)) {
				qPos.add(govAgency);
			}

			/* add groupId */
			qPos.add(groupId);

			List<OpencpsPersonStatistic> ls = (List<OpencpsPersonStatistic>) QueryUtil.list(q, getDialect(),
					QueryUtil.ALL_POS, QueryUtil.ALL_POS);

			if (ls.size() > 0) {
				return ls.get(0);
			}

		} catch (Exception e) {
			LOG.error(e);
			try {
				throw new SystemException(e);
			} catch (SystemException se) {
				LOG.error(se);
			}
		} finally {
			closeSession(session);
		}

		return null;
	}

	@SuppressWarnings("unchecked")
	public List<OpencpsPersonStatistic> searchPersonStatistic(long groupId, int year, String votingCode,
			Long employeeId, String govAgency, int start, int end) {
		Session session = null;

		try {
			session = openSession();

			String sql = _customSQL.get(getClass(), SEARCH_PERSON_STATISTIC);

			sql = StringUtil.replace(sql, CONDITION_MONTH, CONDITION_MONTH_REPLACE);

			if (votingCode.contains(TOTAL)) {
				sql = StringUtil.replace(sql, CONDITION_VOTING_CODE, CONDITION_VOTING_CODE_REPLACE);
			}

			// if (domain.contains(TOTAL)){
			// sql = StringUtil.replace(sql, CONDITION_DOMAIN,CONDITION_DOMAIN_REPLACE);
			// }

			if (govAgency.contains(TOTAL)) {
				sql = StringUtil.replace(sql, CONDITION_GOV_AGENCY, CONDITION_GOV_AGENCY_REPLACE);
			}

			// sql = StringUtil.replace(sql, CONDITION_GROUP_AGENCY, StringPool.BLANK);

			//LOG.info(sql);

			SQLQuery q = session.createSQLQuery(sql);

			q.setCacheable(false);
			q.addEntity("OpencpsPersonStatistic", OpencpsPersonStatisticImpl.class);

			QueryPos qPos = QueryPos.getInstance(q);

			/* add year parameter */
			qPos.add(year);

			/* add votingCode parameter */
			if (!votingCode.contentEquals(TOTAL)) {
				qPos.add(votingCode);
			}

			/* add employeeId parameter */
			if (Validator.isNull(employeeId)) {
				qPos.add(0);
			} else {
				qPos.add(employeeId);
			}

			/* add govAgency parameter */
			if (!govAgency.contentEquals(TOTAL)) {
				qPos.add(govAgency);
			}

			/* add groupId */
			qPos.add(groupId);

			return (List<OpencpsPersonStatistic>) QueryUtil.list(q, getDialect(), QueryUtil.ALL_POS, QueryUtil.ALL_POS);

		} catch (Exception e) {
			LOG.error(e);
			try {
				throw new SystemException(e);
			} catch (SystemException se) {
				LOG.error(se);
			}
		} finally {
			closeSession(session);
		}

		return null;
	}

	@SuppressWarnings("unchecked")
	public List<OpencpsPersonStatistic> searchByPersonServiceGovAgencyGroup(long groupId, int month, int year,
			String votingCode, Long employeeId, String govAgency, int start, int end) {
		Session session = null;

		try {
			session = openSession();

			String sql = _customSQL.get(getClass(), SEARCH_PERSON_STATISTIC);

			if (month == ALL_MONTH) {
				sql = StringUtil.replace(sql, CONDITION_MONTH, StringPool.BLANK);
			}

			/* remove year */
			if (year == 0) {
				sql = StringUtil.replace(sql, CONDITION_YEAR, StringPool.BLANK);
			}

			if (Validator.isNull(votingCode)) {
				sql = StringUtil.replace(sql, CONDITION_VOTING_CODE, StringPool.BLANK);
			} else if (votingCode.contentEquals(TOTAL)) {
				sql = StringUtil.replace(sql, CONDITION_VOTING_CODE, CONDITION_VOTING_CODE_REPLACE);
			}

			if (Validator.isNull(employeeId)) {
				sql = StringUtil.replace(sql, CONDITION_EMPLOYEE, StringPool.BLANK);
			}

			if (Validator.isNull(govAgency)) {
				sql = StringUtil.replace(sql, CONDITION_GOV_AGENCY, StringPool.BLANK);
			} else if (govAgency.contentEquals(TOTAL)) {
				sql = StringUtil.replace(sql, CONDITION_GOV_AGENCY, CONDITION_GOV_AGENCY_REPLACE);
			}

			//LOG.info(sql);

			SQLQuery q = session.createSQLQuery(sql);

			q.setCacheable(false);
			q.addEntity("OpencpsVotingStatistic", OpencpsVotingStatisticImpl.class);

			QueryPos qPos = QueryPos.getInstance(q);

			/* add month parameter */

			if (month != ALL_MONTH) {
				qPos.add(month);
			}

			/* add year parameter */
			qPos.add(year);

			/* add domain parameter */
			if (Validator.isNotNull(votingCode) && !votingCode.contentEquals(TOTAL)) {
				qPos.add(votingCode);
			}

			/* add domain parameter */
			if (Validator.isNotNull(employeeId)) {
				qPos.add(employeeId);
			}

			/* add govAgency parameter */
			if (Validator.isNotNull(govAgency) && !govAgency.contentEquals(TOTAL)) {
				qPos.add(govAgency);
			}

			/* add groupId */
			qPos.add(groupId);

			return (List<OpencpsPersonStatistic>) QueryUtil.list(q, getDialect(), start, end);

		} catch (Exception e) {
			LOG.error(e);
			try {
				throw new SystemException(e);
			} catch (SystemException se) {
				LOG.error(se);
			}
		} finally {
			closeSession(session);
		}

		return null;
	}

	@ServiceReference(type = CustomSQL.class)
	private CustomSQL _customSQL;
}
