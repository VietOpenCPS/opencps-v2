package org.opencps.statistic.service.persistence.impl;

import java.util.List;

import org.opencps.statistic.model.OpencpsDossierStatistic;
import org.opencps.statistic.model.impl.OpencpsDossierStatisticImpl;
import org.opencps.statistic.service.persistence.OpencpsDossierStatisticFinder;

import com.liferay.portal.dao.orm.custom.sql.CustomSQLUtil;
import com.liferay.portal.kernel.dao.orm.QueryPos;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.SQLQuery;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;

public class OpencpsDossierStatisticFinderImpl extends OpencpsDossierStatisticFinderBaseImpl
		implements OpencpsDossierStatisticFinder {

	private final static Log LOG = LogFactoryUtil.getLog(OpencpsDossierStatisticFinderImpl.class);

	private static final String SEARCH_DOSSIER_STATISTIC = OpencpsDossierStatisticFinder.class.getName()
			+ ".searchStatisic";

	private static final String TOTAL = "total";

	private static final String CONDITION_DOMAIN = "(opencps_statistic.domainCode = ?) AND";
	private static final String CONDITION_DOMAIN_REPLACE = "(opencps_statistic.domainCode IS NULL) AND";
	private static final String CONDITION_GOV_AGENCY = "(opencps_statistic.govAgencyCode = ?) AND";
	private static final String CONDITION_GOV_AGENCY_REPLACE = "(opencps_statistic.govAgencyCode IS NULL) AND";
	private static final String CONDITION_GROUP_AGENCY = "(opencps_statistic.groupAgencyCode = ?) AND";
	private static final String CONDITION_MONTH = "(opencps_statistic.month = ?) AND";
	private static final String CONDITION_MONTH_REPLACE = "(opencps_statistic.month != 0) AND";
	private static final String CONDITION_YEAR = "(opencps_statistic.year = ?) AND";
	
	public static final int ALL_MONTH = -1;
	
	@SuppressWarnings("unchecked")
	public OpencpsDossierStatistic checkContains(long groupId, int month, int year,
			String domain, String govAgency, boolean reporting) {
		Session session = null;

		try {
			session = openSession();
			
			String sql = CustomSQLUtil.get(getClass(), SEARCH_DOSSIER_STATISTIC);

		
			if (Validator.isNull(domain)) {
				sql = StringUtil.replace(sql, CONDITION_DOMAIN, CONDITION_DOMAIN_REPLACE);
			} 
			
			if (Validator.isNull(govAgency)) {
				sql = StringUtil.replace(sql, CONDITION_GOV_AGENCY, CONDITION_GOV_AGENCY_REPLACE);
			} 
			
			sql = StringUtil.replace(sql, CONDITION_GROUP_AGENCY, StringPool.BLANK);

			//LOG.info(sql);

			SQLQuery q = session.createSQLQuery(sql);

			q.setCacheable(false);
			q.addEntity("OpencpsDossierStatistic", OpencpsDossierStatisticImpl.class);

			QueryPos qPos = QueryPos.getInstance(q);

			/* add month parameter */

			qPos.add(month);

			/* add year parameter */
			qPos.add(year);

			/* add domain parameter */
			if (Validator.isNotNull(domain)) {
				qPos.add(domain);
			}

			/* add govAgency parameter */
			if (Validator.isNotNull(govAgency)) {
				qPos.add(govAgency);
			}

			/* add reporting */
			qPos.add(reporting);

			/* add groupId */
			qPos.add(groupId);
			
			List<OpencpsDossierStatistic> ls = (List<OpencpsDossierStatistic>) QueryUtil.list(q, getDialect(), QueryUtil.ALL_POS, QueryUtil.ALL_POS);
			
			if (ls.size() > 0) {
				return ls.get(0);
			} 


		} catch (Exception e) {
			try {
				throw new SystemException(e);
			} catch (SystemException se) {
				se.printStackTrace();
			}
		} finally {
			closeSession(session);
		}

		return null;
	}
	
	@SuppressWarnings("unchecked")
	public List<OpencpsDossierStatistic> searchDossierStatistic(long groupId, int year,
			String domain, String govAgency, String groupAgencyCode, boolean reporting, int start, int end) {
		Session session = null;
		
		try {
			session = openSession();

			String sql = CustomSQLUtil.get(getClass(), SEARCH_DOSSIER_STATISTIC);
			
			sql = StringUtil.replace(sql, CONDITION_MONTH, CONDITION_MONTH_REPLACE);
		
			if (domain.contains("total")){
				sql = StringUtil.replace(sql, CONDITION_DOMAIN, CONDITION_DOMAIN_REPLACE);
			}
			
			if (govAgency.contains("total")){
				sql = StringUtil.replace(sql, CONDITION_GOV_AGENCY,CONDITION_GOV_AGENCY_REPLACE);
			}
			
			sql = StringUtil.replace(sql, CONDITION_GROUP_AGENCY, StringPool.BLANK);

			//LOG.info(sql);

			SQLQuery q = session.createSQLQuery(sql);

			q.setCacheable(false);
			q.addEntity("OpencpsDossierStatistic", OpencpsDossierStatisticImpl.class);

			QueryPos qPos = QueryPos.getInstance(q);

			/* add month parameter */


			/* add year parameter */
			qPos.add(year);

			/* add domain parameter */
			if (!domain.contentEquals(TOTAL)) {
				qPos.add(domain);
			}

			/* add govAgency parameter */
			if (!govAgency.contentEquals(TOTAL)) {
				qPos.add(govAgency);
			}

			/* add reporting */
			qPos.add(reporting);

			/* add groupId */
			qPos.add(groupId);
			
			return (List<OpencpsDossierStatistic>) QueryUtil.list(q, getDialect(), QueryUtil.ALL_POS, QueryUtil.ALL_POS);
			

		} catch (Exception e) {
			try {
				throw new SystemException(e);
			} catch (SystemException se) {
				se.printStackTrace();
			}
		} finally {
			closeSession(session);
		}

		return null;
	}

	@SuppressWarnings("unchecked")
	public List<OpencpsDossierStatistic> searchByDomainGovAgencyGroupAndReporting(long groupId, int month, int year,
			String domain, String govAgency, String groupAgencyCode, boolean reporting, int start, int end) {
		Session session = null;

		try {
			session = openSession();

			String sql = CustomSQLUtil.get(getClass(), SEARCH_DOSSIER_STATISTIC);

			// LOG.info(sql);

			if (month == ALL_MONTH) {
				sql = StringUtil.replace(sql, CONDITION_MONTH, StringPool.BLANK);
			}

			/* remove year */
			if (year == 0) {
				sql = StringUtil.replace(sql, CONDITION_YEAR, StringPool.BLANK);
			}
			
			if (Validator.isNull(domain)) {
				sql = StringUtil.replace(sql, CONDITION_DOMAIN, StringPool.BLANK);
			} else if (domain.contentEquals(TOTAL)){
				sql = StringUtil.replace(sql, CONDITION_DOMAIN, CONDITION_DOMAIN_REPLACE);
			}
			
			if (Validator.isNull(govAgency)) {
				sql = StringUtil.replace(sql, CONDITION_GOV_AGENCY, StringPool.BLANK);
			} else if (govAgency.contentEquals(TOTAL)){
				sql = StringUtil.replace(sql, CONDITION_GOV_AGENCY,CONDITION_GOV_AGENCY_REPLACE);
			}
			
			/* remove condition groupAgency, find by all groupAgency */
			if (Validator.isNull(groupAgencyCode) || groupAgencyCode.contentEquals(TOTAL)) {
				sql = StringUtil.replace(sql, CONDITION_GROUP_AGENCY, StringPool.BLANK);
			}

			//LOG.info(sql);

			SQLQuery q = session.createSQLQuery(sql);

			q.setCacheable(false);
			q.addEntity("OpencpsDossierStatistic", OpencpsDossierStatisticImpl.class);

			QueryPos qPos = QueryPos.getInstance(q);

			/* add month parameter */

			if (month != ALL_MONTH) {
				qPos.add(month);
			}

			/* add year parameter */
			qPos.add(year);

			/* add domain parameter */
			if (Validator.isNotNull(domain) && !domain.contentEquals(TOTAL)) {
				qPos.add(domain);
			}

			/* add govAgency parameter */
			if (Validator.isNotNull(govAgency) && !govAgency.contentEquals(TOTAL)) {
				qPos.add(govAgency);
			}

			/* add groupAgency parameter */
			if (Validator.isNotNull(groupAgencyCode) && !groupAgencyCode.contentEquals(TOTAL)) {
				qPos.add(groupAgencyCode);
			}

			/* add reporting */
			qPos.add(reporting);

			/* add groupId */
			qPos.add(groupId);

			return (List<OpencpsDossierStatistic>) QueryUtil.list(q, getDialect(), start, end);

		} catch (Exception e) {
			try {
				throw new SystemException(e);
			} catch (SystemException se) {
				se.printStackTrace();
			}
		} finally {
			closeSession(session);
		}

		return null;
	}

}
