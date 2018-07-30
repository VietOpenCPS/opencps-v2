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
	private static final String CONDITION_GOV_AGENCY = "(opencps_statistic.govAgencyCode = ?) AND";
	private static final String CONDITION_GROUP_AGENCY = "(opencps_statistic.groupAgencyCode = ?) AND";

	@SuppressWarnings("unchecked")
	public List<OpencpsDossierStatistic> searchByDomainGovAgencyGroupAndReporting(long groupId, String domain,
			String govAgency, String groupAgencyCode, boolean reporting, int start, int end) {
		Session session = null;

		try {
			session = openSession();

			String sql = CustomSQLUtil.get(getClass(), SEARCH_DOSSIER_STATISTIC);

			LOG.info(sql);

			/* remove condition domain, find by all domain */
			if (Validator.isNull(domain) || domain.contentEquals(TOTAL)) {
				sql = StringUtil.replace(sql, CONDITION_DOMAIN, StringPool.BLANK);
			}

			/* remove condition govAgency, find by all govAgency */
			if (Validator.isNull(govAgency) || govAgency.contentEquals(TOTAL)) {
				sql = StringUtil.replace(sql, CONDITION_GOV_AGENCY, StringPool.BLANK);
			}

			/* remove condition groupAgency, find by all groupAgency */
			if (Validator.isNull(groupAgencyCode) || groupAgencyCode.contentEquals(TOTAL)) {
				sql = StringUtil.replace(sql, CONDITION_GROUP_AGENCY, StringPool.BLANK);
			}

			LOG.info(sql);

			
			SQLQuery q = session.createSQLQuery(sql);

			q.setCacheable(false);
			q.addEntity("OpencpsDossierStatistic", OpencpsDossierStatisticImpl.class);

			QueryPos qPos = QueryPos.getInstance(q);

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
