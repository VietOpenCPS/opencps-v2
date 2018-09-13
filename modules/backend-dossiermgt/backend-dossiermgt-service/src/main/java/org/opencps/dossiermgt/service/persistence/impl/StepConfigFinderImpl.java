package org.opencps.dossiermgt.service.persistence.impl;

import java.util.List;

import org.opencps.dossiermgt.model.StepConfig;
import org.opencps.dossiermgt.service.persistence.StepConfigFinder;

import com.liferay.portal.kernel.dao.orm.Query;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

import aQute.bnd.annotation.ProviderType;

@ProviderType
public class StepConfigFinderImpl extends StepConfigFinderBaseImpl
		 implements StepConfigFinder {

	Log _log = LogFactoryUtil.getLog(StepConfigFinderImpl.class);

	@SuppressWarnings("unchecked")
	public List<StepConfig> finByStepConfig(int stepType) {

		Session session = null;
		List<StepConfig> stepConfigList = null;
	    String hql = "FROM StepConfig step WHERE"
        		+ " step.stepType >= :stepType"
        		+ " ORDER BY stepCode ASC";
//		    _log.info("SQL: "+ sql);
		try {
			session = openSession();

			Query q = session.createQuery(hql);
			q.setCacheable(false);
			q.setInteger("stepType", stepType);

			stepConfigList = q.list();
			// _log.info("SQL list deliverable: "+ deliverableList);
		} catch (Exception e) {
			try {
				throw new SystemException(e);
			} catch (SystemException se) {
//				se.printStackTrace();
				_log.error(se);
			}
		} finally {
			closeSession(session);
		}

		return stepConfigList;
	}

}
