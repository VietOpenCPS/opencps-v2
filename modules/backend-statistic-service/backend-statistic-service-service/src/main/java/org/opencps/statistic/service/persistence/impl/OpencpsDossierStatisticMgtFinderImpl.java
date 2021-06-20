package org.opencps.statistic.service.persistence.impl;

import com.liferay.petra.string.StringPool;
import com.liferay.portal.dao.orm.custom.sql.CustomSQL;
import com.liferay.portal.kernel.dao.orm.QueryPos;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.SQLQuery;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.spring.extender.service.ServiceReference;

import java.util.List;

import org.opencps.statistic.model.OpencpsDossierStatistic;
import org.opencps.statistic.model.OpencpsDossierStatisticMgt;
import org.opencps.statistic.model.impl.OpencpsDossierStatisticImpl;
import org.opencps.statistic.model.impl.OpencpsDossierStatisticMgtImpl;
import org.opencps.statistic.service.persistence.OpencpsDossierStatisticFinder;
import org.opencps.statistic.service.persistence.OpencpsDossierStatisticMgtFinder;

public class OpencpsDossierStatisticMgtFinderImpl extends OpencpsDossierStatisticMgtFinderBaseImpl
	implements OpencpsDossierStatisticMgtFinder{

	private static Log _log = LogFactoryUtil.getLog(OpencpsDossierStatisticMgtFinderImpl.class);

	private static final String SEARCH_DOSSIER_STATISTIC_MGT = OpencpsDossierStatisticMgtFinder.class.getName()
			+ ".searchStatisicMgt";
	private static final String SEARCH_DOSSIER_STATISTIC_MGT_GB = OpencpsDossierStatisticMgtFinder.class.getName()
			+ ".searchStatisicMgtGroupBy";
	
	private static final String TOTAL = "total";

	private static final String CONDITION_DOMAIN = "(opencps_statistic_mgt.domainCode = ?) AND";
	private static final String CONDITION_DOMAIN_REPLACE = "(opencps_statistic_mgt.domainCode IS NULL) AND";
	private static final String CONDITION_GOV_AGENCY = "(opencps_statistic_mgt.govAgencyCode = ?) AND";
	private static final String CONDITION_GOV_AGENCY_REPLACE = "(opencps_statistic_mgt.govAgencyCode IS NULL) AND";
	private static final String CONDITION_MONTH = "(opencps_statistic_mgt.month = ?) AND";
	private static final String CONDITION_MONTH_REPLACE = "(opencps_statistic_mgt.month != 0) AND";
	private static final String CONDITION_YEAR = "(opencps_statistic_mgt.year = ?) AND";
	private static final String CONDITION_YEAR_REPLACE = "(opencps_statistic_mgt.year != 0) AND";
	
	public static final int ALL_MONTH = -1;
	
	public OpencpsDossierStatisticMgt checkContains(long groupId, int month, int year, String govAgencyCode, String domainCode) {
		Session session = null;

		try {
			
			session = openSession();

			String sql = _customSQL.get(getClass(), SEARCH_DOSSIER_STATISTIC_MGT);

		
			if (Validator.isNull(domainCode)) {
				sql = StringUtil.replace(sql, CONDITION_DOMAIN, CONDITION_DOMAIN_REPLACE);
			} 
			
			if (Validator.isNull(govAgencyCode)) {
				sql = StringUtil.replace(sql, CONDITION_GOV_AGENCY, CONDITION_GOV_AGENCY_REPLACE);
			}			

			//LOG.info(sql);

			SQLQuery q = session.createSQLQuery(sql);

			q.setCacheable(true);
			q.addEntity("OpencpsDossierStatisticMgt", OpencpsDossierStatisticMgtImpl.class);

			QueryPos qPos = QueryPos.getInstance(q);

			/* add month parameter */

			qPos.add(month);

			/* add year parameter */
			qPos.add(year);

			/* add domain parameter */
			if (Validator.isNotNull(domainCode)) {
				qPos.add(domainCode);
			}

			/* add govAgency parameter */
			if (Validator.isNotNull(govAgencyCode)) {
				qPos.add(govAgencyCode);
			}
			
			/* add groupId */
			qPos.add(groupId);
			
			List<OpencpsDossierStatisticMgt> ls = (List<OpencpsDossierStatisticMgt>) QueryUtil.list(q, getDialect(),
					0, 1);
			
			if (ls != null && ls.size() > 0) {
				return ls.get(0);
			} 

			
		} catch (Exception e) {
			_log.error(e.getMessage());
			try {
				throw new SystemException(e);
			} catch (SystemException se) {
				_log.error(e.getMessage());
			}
		} finally {
			closeSession(session);
		}
		
		return null;
	}
	
	public OpencpsDossierStatisticMgt checkContainsGroupBy(long groupId, int month, int year, String govAgencyCode, String domainCode, int groupBy) {
		Session session = null;

		try {
			
			session = openSession();

			String sql = _customSQL.get(getClass(), SEARCH_DOSSIER_STATISTIC_MGT_GB);

		
			if (Validator.isNull(domainCode)) {
				sql = StringUtil.replace(sql, CONDITION_DOMAIN, CONDITION_DOMAIN_REPLACE);
			} 
			
			if (Validator.isNull(govAgencyCode)) {
				sql = StringUtil.replace(sql, CONDITION_GOV_AGENCY, CONDITION_GOV_AGENCY_REPLACE);
			}			

			//LOG.info(sql);

			SQLQuery q = session.createSQLQuery(sql);

			q.setCacheable(true);
			q.addEntity("OpencpsDossierStatisticMgt", OpencpsDossierStatisticMgtImpl.class);

			QueryPos qPos = QueryPos.getInstance(q);

			/* add month parameter */

			qPos.add(month);

			/* add year parameter */
			qPos.add(year);

			/* add domain parameter */
			if (Validator.isNotNull(domainCode)) {
				qPos.add(domainCode);
			}

			/* add govAgency parameter */
			if (Validator.isNotNull(govAgencyCode)) {
				qPos.add(govAgencyCode);
			}
			
			/* add groupId */
			qPos.add(groupId);
			
			/* add groupBy - thong ke cho gov: 1, domain: 2, service: 3 */
			qPos.add(groupBy);
			
			List<OpencpsDossierStatisticMgt> ls = (List<OpencpsDossierStatisticMgt>) QueryUtil.list(q, getDialect(),
					0, 1);
			
			if (ls != null && ls.size() > 0) {
				return ls.get(0);
			} 

			
		} catch (Exception e) {
			_log.error(e.getMessage());
			try {
				throw new SystemException(e);
			} catch (SystemException se) {
				_log.error(e.getMessage());
			}
		} finally {
			closeSession(session);
		}
		
		return null;
	}
	
	@SuppressWarnings("unchecked")
	public List<OpencpsDossierStatisticMgt> searchByDomainAgencyGroupBy(long groupId, int month, int year, String domainCode,
			String govAgencyCode, int groupBy, int start, int end) {
		Session session = null;

		try {
			session = openSession();
			String sql = _customSQL.get(getClass(), SEARCH_DOSSIER_STATISTIC_MGT_GB);

			// LOG.info(sql);

			if (month == ALL_MONTH) {
				sql = StringUtil.replace(sql, CONDITION_MONTH, StringPool.BLANK);
			}

			/* remove year */
			if (year == ALL_MONTH) {
				sql = StringUtil.replace(sql, CONDITION_YEAR, StringPool.BLANK);
			}

			if (Validator.isNull(domainCode)) {
				sql = StringUtil.replace(sql, CONDITION_DOMAIN, StringPool.BLANK);
			} else if (domainCode.contentEquals(TOTAL)){
				sql = StringUtil.replace(sql, CONDITION_DOMAIN, CONDITION_DOMAIN_REPLACE);
			}
			
			if (Validator.isNull(govAgencyCode)) {
				sql = StringUtil.replace(sql, CONDITION_GOV_AGENCY, StringPool.BLANK);
			} else if (govAgencyCode.contentEquals(TOTAL)){
				sql = StringUtil.replace(sql, CONDITION_GOV_AGENCY,CONDITION_GOV_AGENCY_REPLACE);
			}
			

			SQLQuery q = session.createSQLQuery(sql);

			_log.debug(q);
			q.setCacheable(true);
			q.addEntity("OpencpsDossierStatisticMgt", OpencpsDossierStatisticMgtImpl.class);

			QueryPos qPos = QueryPos.getInstance(q);

			/* add month parameter */

			if (month != ALL_MONTH) {
				qPos.add(month);
			}

			/* add year parameter */
			if (year != ALL_MONTH) {
				qPos.add(year);
			}

			/* add domain parameter */
			if (Validator.isNotNull(domainCode) && !domainCode.contentEquals(TOTAL)) {
				qPos.add(domainCode);
			}

			/* add govAgency parameter */
			if (Validator.isNotNull(govAgencyCode) && !govAgencyCode.contentEquals(TOTAL)) {
				qPos.add(govAgencyCode);
			}

			/* add groupId */
			qPos.add(groupId);
			
			/* add groupBy */
			qPos.add(groupBy);
			
			return (List<OpencpsDossierStatisticMgt>) QueryUtil.list(q, getDialect(), start, end);
		} catch (Exception e) {
			_log.error(e.getMessage());
			try {
				throw new SystemException(e);
			} catch (SystemException se) {
				_log.error(e.getMessage());
			}
		} finally {
			closeSession(session);
		}

		return null;
	}
		
	
	@ServiceReference(type = CustomSQL.class)
	private CustomSQL _customSQL;
}
