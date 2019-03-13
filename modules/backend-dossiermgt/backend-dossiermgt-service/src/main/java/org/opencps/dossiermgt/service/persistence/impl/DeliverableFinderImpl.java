package org.opencps.dossiermgt.service.persistence.impl;

import java.util.List;

import org.opencps.dossiermgt.model.Deliverable;
import org.opencps.dossiermgt.model.DossierFile;
import org.opencps.dossiermgt.model.impl.DeliverableImpl;
import org.opencps.dossiermgt.model.impl.DossierFileImpl;
import org.opencps.dossiermgt.service.persistence.DeliverableFinder;

import com.liferay.portal.kernel.dao.orm.Query;
import com.liferay.portal.kernel.dao.orm.SQLQuery;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.service.persistence.impl.BasePersistenceImpl;

import aQute.bnd.annotation.ProviderType;

@ProviderType
public class DeliverableFinderImpl extends DeliverableFinderBaseImpl
		 implements DeliverableFinder {

	Log _log = LogFactoryUtil.getLog(DeliverableFinderImpl.class);

	@SuppressWarnings("unchecked")
	public List<Deliverable> findDeliverableByState(
		    String strDeliverableCode, int state) {

		    Session session = null;
		    List<Deliverable> deliverableList = null;
		    String sql = "SELECT * FROM opencps_deliverable WHERE"
		    				+ " deliverableCode IN ("+strDeliverableCode+") AND"
		    				+ " deliverableState IN ("+state+")";
//		    _log.info("SQL: "+ sql);
		    try {
		        session = openSession();

		        SQLQuery q = session.createSQLQuery(sql);
		        q.setCacheable(false);
		        q.addEntity("opencps_deliverable", DeliverableImpl.class);

		        deliverableList = q.list();
//		        _log.info("SQL list deliverable: "+ deliverableList);
		    }
		    catch (Exception e) {
		        try {
		            throw new SystemException(e);
		        }
		        catch (SystemException se) {
//		            se.printStackTrace();
		        	_log.error(se);
		        }
		    }
		    finally {
		        closeSession(session);
		    }

		    return deliverableList;
		}

	@SuppressWarnings("unchecked")
	public List<Deliverable> findDeliverableByModifiedDate(String syncDate, String deliverableType,
			long deliverableState) {

		    Session session = null;
		    List<Deliverable> deliverableList = null;
//		    String sql = "SELECT * FROM opencps_deliverable WHERE"
//		    				+ " modifiedDate BETWEEN "+startDate+" AND "+endDate+"";
//		    _log.info("SQL: "+ sql);
		    try {
		        session = openSession();

		        Query q = session.createQuery("FROM Deliverable deli WHERE"
		        		+ " deli.modifiedDate >= :syncDate AND"
		        		+ " deli.deliverableType =:deliverableType AND"
		        		+ " deli.deliverableState =:deliverableState"
		        		+ " ORDER BY modifiedDate ASC LIMIT 20");
		        q.setCacheable(false);
		        q.setString("syncDate", syncDate);
		        q.setString("deliverableType", deliverableType);
		        q.setLong("deliverableState", deliverableState);

		        deliverableList = q.list();
//		        _log.info("SQL list deliverable: "+ deliverableList);
		    }
		    catch (Exception e) {
		        try {
		            throw new SystemException(e);
		        }
		        catch (SystemException se) {
//		            se.printStackTrace();
		        	_log.error(se);
		        }
		    }
		    finally {
		        closeSession(session);
		    }

		    return deliverableList;
		}

	public DossierFile findFileTemplateNo(long id, String fileTemplateNo) {

		    Session session = null;
		    DossierFile dossierFile = null;
		    String sql = "SELECT * FROM opencps_dossierfile WHERE"
		    				+ " dossierId = "+id+" AND"
		    				+ " fileTemplateNo = '"+fileTemplateNo+"' AND"
		    				+ " length(formData) > 0"
		    				+ " ORDER BY dossierId DESC LIMIT 1";
		    _log.info("SQL: "+ sql);
		    try {
		        session = openSession();

		        SQLQuery q = session.createSQLQuery(sql);
		        q.setCacheable(false);
		        q.addEntity("opencps_dossierfile", DossierFileImpl.class);

		        dossierFile = (DossierFile) q.uniqueResult();
//		        _log.info("SQL list deliverable: "+ deliverableList);
		    }
		    catch (Exception e) {
		        try {
		            throw new SystemException(e);
		        }
		        catch (SystemException se) {
//		            se.printStackTrace();
		        	_log.error(se);
		        }
		    }
		    finally {
		        closeSession(session);
		    }

		    return dossierFile;
		}
}
