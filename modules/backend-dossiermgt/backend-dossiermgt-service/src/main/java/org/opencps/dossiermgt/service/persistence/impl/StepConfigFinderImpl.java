package org.opencps.dossiermgt.service.persistence.impl;

import com.liferay.portal.dao.orm.custom.sql.CustomSQL;
import com.liferay.portal.kernel.dao.orm.QueryPos;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.SQLQuery;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.spring.extender.service.ServiceReference;

import java.util.List;

import org.opencps.dossiermgt.model.StepConfig;
import org.opencps.dossiermgt.model.impl.StepConfigImpl;
import org.opencps.dossiermgt.service.persistence.StepConfigFinder;

import aQute.bnd.annotation.ProviderType;

@ProviderType
public class StepConfigFinderImpl extends StepConfigFinderBaseImpl
		 implements StepConfigFinder {

	Log _log = LogFactoryUtil.getLog(StepConfigFinderImpl.class);

	private static final String SEARCH_STEP_CONFIG = StepConfigFinder.class.getName()
			+ ".finByStepConfig";

	private static final String CONDITION_STEP_TYPE = "opencps_stepconfig.stepType >= ?";

	@SuppressWarnings("unchecked")
	public List<StepConfig> finByStepConfig(int stepType) {

		Session session = null;

		try {
			session = openSession();

			_log.info("SEARCH_STEP_CONFIG: "+SEARCH_STEP_CONFIG);
			String sql = _customSQL.get(getClass(), SEARCH_STEP_CONFIG);

			SQLQuery q = session.createSQLQuery(sql);

			q.setCacheable(true);
			q.addEntity("StepConfig", StepConfigImpl.class);

			QueryPos qPos = QueryPos.getInstance(q);

			/* add dossierId parameter */
			qPos.add(stepType);

			List<StepConfig> ls = (List<StepConfig>) QueryUtil.list(q, getDialect(), QueryUtil.ALL_POS, QueryUtil.ALL_POS);
			
			if (ls.size() > 0) {
				return ls;
			} 


		} catch (Exception e) {
			_log.error(e);
			try {
				throw new SystemException(e);
			} catch (SystemException se) {
				_log.error(se);
			}
		} finally {
			closeSession(session);
		}

		return null;
	}

	@ServiceReference(type = CustomSQL.class)
	private CustomSQL _customSQL;
}
