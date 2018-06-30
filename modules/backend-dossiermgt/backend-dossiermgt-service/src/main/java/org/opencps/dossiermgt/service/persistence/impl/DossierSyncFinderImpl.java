package org.opencps.dossiermgt.service.persistence.impl;

import java.util.List;

import org.opencps.dossiermgt.model.DossierSync;
import org.opencps.dossiermgt.service.persistence.DossierSyncFinder;

import com.liferay.portal.kernel.dao.orm.Query;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.Validator;

public class DossierSyncFinderImpl extends DossierSyncFinderBaseImpl implements DossierSyncFinder {

	Log _log = LogFactoryUtil.getLog(DossierSyncFinderImpl.class);

	@SuppressWarnings("unchecked")
	public List<DossierSync> findDossierSyncByActionOrTop(String actionCode, int syncType, Integer start, Integer limit) {

		Session session = null;
		List<DossierSync> dossierSyncList = null;
		
		String hql = generateCustomSQL(actionCode, syncType);
		_log.info("hql: "+ hql);
		try {
			session = openSession();

			Query q = session.createQuery(hql);
			q.setCacheable(false);
			if (Validator.isNotNull(limit) && limit > 0) {
				q.setFirstResult(start);
				q.setMaxResults(limit);
			}
			
			if (Validator.isNotNull(actionCode)) {
				q.setString("actionCode", actionCode);
			}
			if (Validator.isNotNull(syncType) && syncType > 0) {
				q.setInteger("syncType", syncType);
			}

			dossierSyncList = q.list();
			// _log.info("SQL list deliverable: "+ deliverableList);
		} catch (Exception e) {
			try {
				throw new SystemException(e);
			} catch (SystemException se) {
				se.printStackTrace();
			}
		} finally {
			closeSession(session);
		}

		return dossierSyncList;
	}

	@SuppressWarnings("unchecked")
	public long countDossierSyncByActionOrTop(String actionCode, int syncType) {

		Session session = null;
		long total = 0;
		
		String hql = generateCustomSQL(actionCode, syncType);
		_log.info("hql: "+ hql);
		try {
			session = openSession();

			Query q = session.createQuery(hql);
			q.setCacheable(false);
			if (Validator.isNotNull(actionCode)) {
				q.setString("actionCode", actionCode);
			}
			if (Validator.isNotNull(syncType) && syncType > 0) {
				q.setInteger("syncType", syncType);
			}

			List<DossierSync> dossierSyncList = q.list();
			if (dossierSyncList != null && dossierSyncList.size() > 0) {
				total = dossierSyncList.size();
			}
			 _log.info("total: "+ total);
		} catch (Exception e) {
			try {
				throw new SystemException(e);
			} catch (SystemException se) {
				se.printStackTrace();
			}
		} finally {
			closeSession(session);
		}

		return total;
	}

	@SuppressWarnings("unchecked")
	public List<DossierSync> findDossierSyncByIdList(Long dossierId, Integer model, int actionCodeNo, Integer start, Integer limit) {
		Session session = null;
		List<DossierSync> dossierSyncList = null;
		
		String hql = generateCustomSQL(dossierId, model, actionCodeNo);
		_log.info("hql: "+ hql);
		try {
			session = openSession();

			Query q = session.createQuery(hql);
			q.setCacheable(false);
			q.setInteger("actionCodeNo", actionCodeNo);
			if (Validator.isNotNull(limit) && limit > 0) {
				q.setFirstResult(start);
				q.setMaxResults(limit);
			}

			dossierSyncList = q.list();
			// _log.info("SQL list deliverable: "+ deliverableList);
		} catch (Exception e) {
			try {
				throw new SystemException(e);
			} catch (SystemException se) {
				se.printStackTrace();
			}
		} finally {
			closeSession(session);
		}

		return dossierSyncList;
	}

	@SuppressWarnings("unchecked")
	public long countDossierSyncByIdList(Long dossierId, Integer model, int actionCodeNo) {
		Session session = null;
		long total = 0;
		
		String hql = generateCustomSQL(dossierId, model, actionCodeNo);
		_log.info("hql: "+ hql);
		try {
			session = openSession();

			Query q = session.createQuery(hql);
			q.setCacheable(false);
			q.setInteger("actionCodeNo", actionCodeNo);

			List<DossierSync> dossierSyncList = q.list();
			if (dossierSyncList != null && dossierSyncList.size() > 0) {
				total = dossierSyncList.size();
			}
			 _log.info("total: "+ total);
		} catch (Exception e) {
			try {
				throw new SystemException(e);
			} catch (SystemException se) {
				se.printStackTrace();
			}
		} finally {
			closeSession(session);
		}

		return total;
	}

	private static String generateCustomSQL(String action, int syncType) {
		StringBuilder sb = new StringBuilder();
		if (Validator.isNotNull(action) || syncType > 0) {
			sb.append("FROM DossierSync ds WHERE");
			if (Validator.isNotNull(action)) {
				sb.append(" ds.actionCode =:actionCode");
				if (syncType > 0) {
					sb.append(" AND ds.syncType =:syncType");
				}
			} else if (syncType > 0) {
				sb.append(" ds.syncType =:syncType");
			}
		} else {
		sb.append("FROM DossierSync");
		}

		return sb.toString();
	}

	private static String generateCustomSQL(Long dossierId, Integer model, int actionCodeNo) {
		StringBuilder sb = new StringBuilder();
		if (Validator.isNotNull(model) || Validator.isNotNull(dossierId)) {
			sb.append("FROM DossierSync ds WHERE");
			if (dossierId > 0) {
				sb.append(" ds.dossierId =:dossierId");
			} else {
				sb.append(" ds.dossierRefUid =:dossierId");
			}
			if (model == 0) {
				sb.append(" AND cast(ds.actionCode as int) < :actionCodeNo");
			} else {
				sb.append(" AND cast(ds.actionCode as int) >= :actionCodeNo ");
			}
		} else {
		sb.append("FROM DossierSync");
		}

		return sb.toString();
	}

}