package org.opencps.dossiermgt.service.persistence.impl;

import com.liferay.portal.kernel.dao.orm.QueryPos;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.SQLQuery;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.dao.orm.Type;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.spring.extender.service.ServiceReference;

import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.opencps.dossiermgt.input.model.PersonDossierStatistic;
import org.opencps.dossiermgt.service.persistence.DossierActionFinder;
import com.liferay.portal.dao.orm.custom.sql.CustomSQL;

public class DossierActionFinderImpl extends DossierActionFinderBaseImpl implements DossierActionFinder {
	@ServiceReference(type = CustomSQL.class)
	private CustomSQL _customSQL;

	public List findActionOverdue(Date fromDate, Date toDate, long groupId) {
		Session session = null;
		try {
			session = openSession();

			String sql = _customSQL.get(getClass(), FIND_ACTION_OVERDUE);
			SQLQuery q = session.createSQLQuery(sql);
			q.setCacheable(false);
			q.addScalar("userId", Type.INTEGER);
			q.addScalar("soluonghoso", Type.INTEGER);

			QueryPos qPos = QueryPos.getInstance(q);
			qPos.add(fromDate);
			qPos.add(toDate);
			qPos.add(groupId);

			return q.list();
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

	public List findActionUndue(Date fromDate, Date toDate, long groupId) {
		Session session = null;
		try {
			session = openSession();
			String sql = _customSQL.get(getClass(), FIND_ACTION_UNDUE);

			SQLQuery q = session.createSQLQuery(sql);
			q.setCacheable(false);
			q.addScalar("userId", Type.INTEGER);
			q.addScalar("soluonghoso", Type.INTEGER);

			QueryPos qPos = QueryPos.getInstance(q);
			qPos.add(fromDate);
			qPos.add(toDate);
			qPos.add(groupId);
			
			return q.list();
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
	
	Log _log = LogFactoryUtil.getLog(DossierActionFinderImpl.class);
	public static final String FIND_ACTION_OVERDUE = DossierActionFinder.class.getName() + ".findActionOverdue";
	public static final String FIND_ACTION_UNDUE = DossierActionFinder.class.getName() + ".findActionUndue";
}
