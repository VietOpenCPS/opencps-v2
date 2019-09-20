package org.opencps.dossiermgt.service.persistence.impl;

import com.liferay.portal.kernel.dao.orm.Query;
import com.liferay.portal.kernel.dao.orm.SQLQuery;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

import java.util.List;

import org.opencps.dossiermgt.model.Booking;
import org.opencps.dossiermgt.model.Deliverable;
import org.opencps.dossiermgt.model.DossierFile;
import org.opencps.dossiermgt.model.impl.BookingImpl;
import org.opencps.dossiermgt.model.impl.DeliverableImpl;
import org.opencps.dossiermgt.model.impl.DossierFileImpl;
import org.opencps.dossiermgt.service.persistence.BookingFinder;

import aQute.bnd.annotation.ProviderType;

@ProviderType
public class BookingFinderImpl extends BookingFinderBaseImpl
		 implements BookingFinder {

	Log _log = LogFactoryUtil.getLog(BookingFinderImpl.class);

	public int findBookingMaxByServiceGroupCode(long groupId, String serviceGroupCode) {

		Session session = null;
		int count = 0;
		String sql = "SELECT * FROM opencps_booking WHERE "
				+ " groupId = " + groupId + " AND serviceGroupCode = '"+serviceGroupCode+"'"
				+ " AND count ="
				+ " (SELECT max(count) FROM opencps_booking WHERE"
				+ " groupId = " + groupId + " AND serviceGroupCode = '"+serviceGroupCode+"'"
				+ " AND DATE(checkinDate) = Date(NOW()))";
		_log.info("SQL: " + sql);
		try {
			session = openSession();

			SQLQuery q = session.createSQLQuery(sql);
			q.setCacheable(false);
			q.addEntity("opencps_booking", BookingImpl.class);

			_log.info("q: " + q.toString());
			Booking booking = (Booking) q.uniqueResult();
			if (booking != null) {
				count = booking.getCount();
			}
//		        _log.info("SQL list deliverable: "+ deliverableList);
		} catch (Exception e) {
			try {
				throw new SystemException(e);
			} catch (SystemException se) {
//		            se.printStackTrace();
				_log.error(se);
			}
		} finally {
			closeSession(session);
		}

		return count;
	}

}
