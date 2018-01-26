package org.opencps.dossiermgt.service.persistence.impl;

import java.util.List;

import org.opencps.dossiermgt.action.impl.DeliverableActionsImpl;
import org.opencps.dossiermgt.model.Deliverable;
import org.opencps.dossiermgt.model.impl.DeliverableImpl;
import org.opencps.dossiermgt.service.persistence.DeliverableFinder;

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

	public List<Deliverable> findDeliverableByState(
		    String strDeliverableCode, String state) {

		    Session session = null;
		    List<Deliverable> deliverableList = null;
		    String sql = "SELECT * FROM opencps_deliverable WHERE"
		    				+ " deliverableCode IN ("+strDeliverableCode+") AND"
		    				+ " deliverableState IN ("+state+")";
		    try {
		        session = openSession();

		        SQLQuery q = session.createSQLQuery(sql);
		        q.setCacheable(false);
		        q.addEntity("opencps_deliverable", DeliverableImpl.class);

		        deliverableList = q.list();
		        _log.info("SQL list deliverable: "+ deliverableList);
		    }
		    catch (Exception e) {
		        try {
		            throw new SystemException(e);
		        }
		        catch (SystemException se) {
		            se.printStackTrace();
		        }
		    }
		    finally {
		        closeSession(session);
		    }

		    return deliverableList;
		}
}
