package org.opencps.dossiermgt.action;

import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.model.Company;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.search.Sort;
import com.liferay.portal.kernel.service.ServiceContext;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.HttpHeaders;

import org.opencps.dossiermgt.model.Booking;

public interface BookingActions {

	public JSONObject getBookingList(long userId, long companyId, long groupId, LinkedHashMap<String, Object> params,
			Sort[] sorts, int start, int end, ServiceContext serviceContext);

	public Booking updateBooking(long userId, long groupId, long bookingId, String className, long classPK,
			String serviceCode, String codeNumber, String bookingName, String gateNumber, Integer state,
			Date checkinDate, Date bookingDate, boolean speaking, String serviceGroupCode, Boolean online,
			String bookingInTime, String telNo, ServiceContext serviceContext);

	public Booking deleteBookingById(long id, ServiceContext serviceContext);

	public List getBookingCounterOnline(long groupIdBooking, String bookingDate, boolean online, ServiceContext serviceContext);

	public Booking getByCodeNumber(String codeNumber);

	public boolean validateSimpleCaptcha(HttpServletRequest request, HttpHeaders header, Company company, Locale locale,
			User user, ServiceContext serviceContext, String value);

}
