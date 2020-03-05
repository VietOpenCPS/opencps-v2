package org.opencps.dossiermgt.service.persistence.impl;

import com.liferay.portal.dao.orm.custom.sql.CustomSQL;
import com.liferay.portal.kernel.dao.orm.Query;
import com.liferay.portal.kernel.dao.orm.QueryPos;
import com.liferay.portal.kernel.dao.orm.SQLQuery;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.dao.orm.Type;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.spring.extender.service.ServiceReference;

import java.util.List;

import org.opencps.dossiermgt.model.Booking;
import org.opencps.dossiermgt.model.Deliverable;
import org.opencps.dossiermgt.model.DossierFile;
import org.opencps.dossiermgt.model.impl.BookingImpl;
import org.opencps.dossiermgt.model.impl.DeliverableImpl;
import org.opencps.dossiermgt.model.impl.DossierFileImpl;
import org.opencps.dossiermgt.service.persistence.BookingFinder;
import org.opencps.dossiermgt.service.persistence.DossierActionFinder;

import aQute.bnd.annotation.ProviderType;

@ProviderType
public class BookingFinderImpl extends BookingFinderBaseImpl
		 implements BookingFinder {
	
	@ServiceReference(type = CustomSQL.class)
	private CustomSQL _customSQL;

	Log _log = LogFactoryUtil.getLog(BookingFinderImpl.class);
	public static final String FIND_BOOKING_DATE_ONLINE = BookingFinder.class.getName() + ".findBookingDateOnline";

	public int findBookingMaxByServiceGroupCode(long groupId, String serviceGroupCode) {

		Session session = null;
		int count = 0;
		String sql = "SELECT * FROM opencps_booking WHERE "
				+ " groupId = " + groupId + " AND serviceGroupCode = '"+serviceGroupCode+"'"
				+ " AND DATE(checkinDate) = Date(NOW()) AND count ="
				+ " (SELECT max(count) FROM opencps_booking WHERE"
				+ " groupId = " + groupId + " AND serviceGroupCode = '"+serviceGroupCode+"'"
				+ " AND DATE(checkinDate) = Date(NOW())) LIMIT 1";
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

	public List findBookingDateOnline(long groupId, String bookingDate, boolean online) {
		Session session = null;
		try {
			session = openSession();

			String sql = _customSQL.get(getClass(), FIND_BOOKING_DATE_ONLINE);
			String sql1 = "select bk.bookingId, bk.className, bk.classPK, bk.bookingDate, bk.bookingInTime from opencps_booking as bk " + 
					" where groupId = "+groupId+" and DATE(bookingDate) = '"+bookingDate+"' "
					+ " and online_ = "+online+" ORDER BY bookingId desc LIMIT 5";
			_log.info("SQL: " + sql1);
			_log.info("bookingDate: " + bookingDate);
			_log.info("online: " + online);
			_log.info("groupId: " + groupId);
			SQLQuery q = session.createSQLQuery(sql);
			q.setCacheable(false);
			q.addScalar("bookingId", Type.LONG);
			q.addScalar("className", Type.STRING);
			q.addScalar("classPK", Type.STRING);
			q.addScalar("bookingDate", Type.DATE);
			q.addScalar("bookingInTime", Type.STRING);

			QueryPos qPos = QueryPos.getInstance(q);
			qPos.add(groupId);
			qPos.add(bookingDate);
			qPos.add(online);

			_log.info("q.list(): " + q.list());
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

}
