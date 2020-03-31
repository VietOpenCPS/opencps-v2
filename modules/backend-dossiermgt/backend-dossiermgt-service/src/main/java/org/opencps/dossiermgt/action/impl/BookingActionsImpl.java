package org.opencps.dossiermgt.action.impl;

import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Company;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.search.Hits;
import com.liferay.portal.kernel.search.SearchContext;
import com.liferay.portal.kernel.search.Sort;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.Validator;

import java.util.Date;
import java.util.Enumeration;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.core.HttpHeaders;

import org.opencps.dossiermgt.action.BookingActions;
import org.opencps.dossiermgt.action.util.ConstantUtils;
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
			if (hits != null && hits.getLength() > 0) {
				result.put(ConstantUtils.DATA, hits.toList());

				long total = BookingLocalServiceUtil.countLucene(params, searchContext);

				result.put(ConstantUtils.TOTAL, total);
			} else {
				result.put(ConstantUtils.TOTAL, 0l);
			}

		} catch (Exception e) {
			_log.debug(e);
		}

		return result;
	}

	@Override
	public Booking updateBooking(long userId, long groupId, long bookingId, String className, long classPK,
			String serviceCode, String codeNumber, String bookingName, String gateNumber, Integer state,
			Date checkinDate, Date bookingDate, boolean speaking, String serviceGroupCode, boolean online,
			String bookingInTime, String telNo, ServiceContext serviceContext) {

		try {

			return BookingLocalServiceUtil.updateBooking(userId, groupId, bookingId, className, classPK, serviceCode,
					codeNumber, bookingName, gateNumber, state, checkinDate, bookingDate, speaking, serviceGroupCode,
					online, bookingInTime, telNo, serviceContext);
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

	@Override
	public List getBookingCounterOnline(long groupIdBooking, String bookingDate, boolean online, ServiceContext serviceContext) {
		try {

			return BookingLocalServiceUtil.getByGID_DATE(groupIdBooking, bookingDate, online, serviceContext);
		} catch (Exception e) {
			_log.debug(e);
		}

		return null;
	}

	@Override
	public Booking getByCodeNumber(String codeNumber) {
		return BookingLocalServiceUtil.getByCodeNumber(codeNumber);
	}

	@Override
	public boolean validateSimpleCaptcha(HttpServletRequest request, HttpHeaders header, Company company, Locale locale,
			User user, ServiceContext serviceContext, String value) {
		String captcha;// = StringPool.BLANK;
		HttpSession session = request.getSession();

		Enumeration<String> enumeration;// = session.getAttributeNames();
		
		/*List<String> values = Collections.list(enumeration);
		
		for (String tmp : values) {
			System.out.println("========================== > session.getAttributeNames() " + tmp);
		}*/

		if (Validator.isNull(value)) {
			
			session.removeAttribute("_SIMPLE_CAPTCHA");
			return false;
		}

		if (session.getAttribute("_SIMPLE_CAPTCHA") != null) {
			
			captcha = (String) session.getAttribute("_SIMPLE_CAPTCHA");
			
			if (value.equals(captcha)) {
				session.removeAttribute("_SIMPLE_CAPTCHA");
				return true;
			}
			session.removeAttribute("_SIMPLE_CAPTCHA");
			return false;
		} else {
			
			session.removeAttribute("_SIMPLE_CAPTCHA");
			return false;
		}
	}

}
