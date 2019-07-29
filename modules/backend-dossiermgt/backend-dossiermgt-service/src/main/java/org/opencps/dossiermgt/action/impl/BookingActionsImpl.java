package org.opencps.dossiermgt.action.impl;

import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.search.Hits;
import com.liferay.portal.kernel.search.SearchContext;
import com.liferay.portal.kernel.search.Sort;
import com.liferay.portal.kernel.service.ServiceContext;

import java.util.Date;
import java.util.LinkedHashMap;

import org.opencps.dossiermgt.action.BookingActions;
import org.opencps.dossiermgt.model.Booking;
import org.opencps.dossiermgt.service.BookingLocalServiceUtil;

public class BookingActionsImpl implements BookingActions {

	private static final Log _log = LogFactoryUtil.getLog(BookingActionsImpl.class);

	@Override
	public JSONObject getBookingList(long userId, long companyId, long groupId, LinkedHashMap<String, Object> params,
			Sort[] sorts, int start, int end, ServiceContext serviceContext) {

		JSONObject result = JSONFactoryUtil.createJSONObject();

		Hits hits = null;
		SearchContext searchContext = new SearchContext();
		searchContext.setCompanyId(companyId);

		try {

			hits = BookingLocalServiceUtil.searchLucene(params, sorts, start, end, searchContext);
			if (hits != null) {
				result.put("data", hits.toList());

				long total = BookingLocalServiceUtil.countLucene(params, searchContext);

				result.put("total", total);
			} else {
				result.put("total", 0l);
			}

		} catch (Exception e) {
			_log.debug(e);
		}

		return result;
	}

	@Override
	public Booking updateBooking(long userId, long groupId, long bookingId, String className, long classPK,
			String serviceCode, String codeNumber, String bookingName, String gateNumber, Integer state,
			Date checkinDate, Date bookingDate, boolean speaking, ServiceContext serviceContext) {

		try {

			return BookingLocalServiceUtil.updateBooking(userId, groupId, bookingId, className, classPK, serviceCode,
					codeNumber, bookingName, gateNumber, state, checkinDate, bookingDate, speaking, serviceContext);
		} catch (Exception e) {
			_log.debug(e);
		}

		return null;
	}

	@Override
	public Booking deleteBookingById(long id, ServiceContext serviceContext) {
		try {
			return BookingLocalServiceUtil.deleteBooking(id);
		} catch (Exception e) {
			_log.debug(e);
			return null;
		}
	}


}
