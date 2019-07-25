package org.opencps.api.controller.impl;

import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Company;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.search.Document;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.search.Sort;
import com.liferay.portal.kernel.search.SortFactoryUtil;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.Validator;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;

import org.opencps.api.booking.model.BookingDataModel;
import org.opencps.api.booking.model.BookingInputModel;
import org.opencps.api.booking.model.BookingResultsModel;
import org.opencps.api.booking.model.BookingSearchModel;
import org.opencps.api.controller.BookingManagement;
import org.opencps.api.controller.util.BookingUtils;
import org.opencps.auth.api.BackendAuth;
import org.opencps.auth.api.BackendAuthImpl;
import org.opencps.auth.api.exception.UnauthenticationException;
import org.opencps.auth.utils.APIDateTimeUtils;
import org.opencps.dossiermgt.action.BookingActions;
import org.opencps.dossiermgt.action.impl.BookingActionsImpl;
import org.opencps.dossiermgt.action.util.SpecialCharacterUtils;
import org.opencps.dossiermgt.constants.BookingTerm;
import org.opencps.dossiermgt.constants.EFormTerm;
import org.opencps.dossiermgt.model.Booking;
import org.opencps.dossiermgt.service.BookingLocalServiceUtil;

import backend.auth.api.exception.BusinessExceptionImpl;

public class BookingManagementImpl implements BookingManagement{

	private static final Log _log = LogFactoryUtil.getLog(BookingManagementImpl.class);

	@Override
	public Response getBokkingListByClassName(HttpServletRequest request, HttpHeaders header, Company company,
			Locale locale, User user, ServiceContext serviceContext, String className, BookingSearchModel search) {

		//BackendAuth auth = new BackendAuthImpl();
		long groupId = GetterUtil.getLong(header.getHeaderString("groupId"));

		try {
//			if (!auth.isAuth(serviceContext)) {
//				throw new UnauthenticationException();
//			}

			if (Validator.isNull(search.getEnd()) || search.getEnd() == 0) {
				search.setStart(-1);
				search.setEnd(-1);
			}

			BookingResultsModel results = new BookingResultsModel();

				LinkedHashMap<String, Object> params = new LinkedHashMap<String, Object>();
				params.put(Field.GROUP_ID, String.valueOf(groupId));
				// LamTV_Process search LIKE
				String keywordSearch = search.getKeyword();
				String keySearch = StringPool.BLANK;
				if (Validator.isNotNull(keywordSearch)) {
					keySearch = SpecialCharacterUtils.splitSpecial(keywordSearch);
				}
				params.put(Field.KEYWORD_SEARCH, keySearch);

				String serviceCode = search.getService();
				String state = search.getState();

				String from = APIDateTimeUtils.convertNormalDateToLuceneDate(search.getFrom());
				String to = APIDateTimeUtils.convertNormalDateToLuceneDate(search.getTo());
				String bookingFrom = APIDateTimeUtils.convertNormalDateToLuceneDate(search.getBookingFrom());
				String bookingTo = APIDateTimeUtils.convertNormalDateToLuceneDate(search.getBookingTo());

				params.put(BookingTerm.SERVICE_CODE_SEARCH, serviceCode);
				params.put(BookingTerm.STATE, state);
				params.put(BookingTerm.FROM_CREATE_DATE, from);
				params.put(BookingTerm.TO_CREATE_DATE, to);
				params.put(BookingTerm.FROM_BOOKING_DATE, bookingFrom);
				params.put(BookingTerm.TO_BOOKING_DATE, bookingTo);
				params.put(BookingTerm.GATE_NUMBER, search.getGateNumber());
				params.put(BookingTerm.CLASS_NAME, className);
				
				Sort[] sorts = null;
				if (Validator.isNull(search.getSort())) {
					sorts = new Sort[] { SortFactoryUtil.create(EFormTerm.CHECK_IN_DATE + "_sortable", Sort.STRING_TYPE,
							GetterUtil.getBoolean(search.getOrder())) };
				} else {
					sorts = new Sort[] { SortFactoryUtil.create(search.getSort() + "_sortable", Sort.STRING_TYPE,
							GetterUtil.getBoolean(search.getOrder())) };
				}


				BookingActions actions = new BookingActionsImpl();
				JSONObject jsonData = actions.getBookingList(user.getUserId(), company.getCompanyId(), groupId, params, sorts,
							search.getStart(), search.getEnd(), serviceContext);

				results.setTotal(jsonData.getInt("total"));

				results.getData().addAll(BookingUtils.mappingForGetList((List<Document>) jsonData.get("data")));

			return Response.status(200).entity(results).build();

		} catch (Exception e) {
			_log.error(e);
			return BusinessExceptionImpl.processException(e);
		}
	}

	@Override
	public Response addBooking(HttpServletRequest request, HttpHeaders header, Company company, Locale locale,
			User user, ServiceContext serviceContext, BookingInputModel input) {

		long groupId = GetterUtil.getLong(header.getHeaderString("groupId"));
		long userId = serviceContext.getUserId();

		//BackendAuth auth = new BackendAuthImpl();
		try {
			BookingActions actions = new BookingActionsImpl();

			String className = input.getClassName();
			long classPK = input.getClassPK();
			String serviceCode = input.getServiceCode();
			String codeNumber = input.getCodeNumber();
			String bookingName = input.getBookingName();
			String gateNumber = input.getGateNumber();
			Integer state = input.getState();
			Date checkinDate = null;
			if (Validator.isNotNull(state) && state == 1) {
				checkinDate = new Date();
			}

			Date bookingDate = null;
			if (Validator.isNotNull(input.getBookingDate())) {
				bookingDate = APIDateTimeUtils.convertStringToDate(input.getBookingDate(),
						APIDateTimeUtils._NORMAL_PARTTERN);
			}
			boolean speaking = Boolean.valueOf(input.getSpeaking());

			Booking booking = BookingLocalServiceUtil.getByClassName_PK(className, classPK);
			if (booking != null) {
				booking = actions.updateBooking(userId, groupId, booking.getBookingId(), className, classPK,
						serviceCode, codeNumber, bookingName, gateNumber, state, checkinDate, bookingDate, speaking,
						serviceContext);
			} else {
				booking = actions.updateBooking(userId, groupId, 0, className, classPK, serviceCode, codeNumber,
						bookingName, gateNumber, state, checkinDate, bookingDate, speaking, serviceContext);
			}

			BookingDataModel result = BookingUtils.mappingForGetDetail(booking);

			return Response.status(200).entity(result).build();

		} catch (Exception e) {

			return BusinessExceptionImpl.processException(e);
		}
	}

	@Override
	public Response updateBookingById(HttpServletRequest request, HttpHeaders header, Company company, Locale locale,
			User user, ServiceContext serviceContext, String id, BookingInputModel input) {

		long groupId = GetterUtil.getLong(header.getHeaderString("groupId"));
		long userId = serviceContext.getUserId();
		long bookingId = GetterUtil.getLong(id);

		try {

			Booking booking = BookingLocalServiceUtil.fetchBooking(bookingId);

			if (booking != null) {
				BookingActions actions = new BookingActionsImpl();

				String className = booking.getClassName();
				long classPK = booking.getClassPK();
				String serviceCode = input.getServiceCode();
				String codeNumber = input.getCodeNumber();
				String bookingName = input.getBookingName();
				String gateNumber = input.getGateNumber();
				Integer state = input.getState();
				Date checkinDate = null;
				if (Validator.isNotNull(state) && state == 1) {
					checkinDate = new Date();
				}

				Date bookingDate = null;
				if (Validator.isNotNull(input.getBookingDate())) {
					bookingDate = APIDateTimeUtils.convertStringToDate(input.getBookingDate(),
							APIDateTimeUtils._NORMAL_PARTTERN);
				}
				boolean speaking = Boolean.valueOf(input.getSpeaking());

				booking = actions.updateBooking(userId, groupId, booking.getBookingId(), className, classPK,
						serviceCode, codeNumber, bookingName, gateNumber, state, checkinDate, bookingDate, speaking,
						serviceContext);
			}

			BookingDataModel result = BookingUtils.mappingForGetDetail(booking);

			return Response.status(200).entity(result).build();

		} catch (Exception e) {
			_log.error(e);
			return BusinessExceptionImpl.processException(e);
		}
	}

	@Override
	public Response deleteBookingById(HttpServletRequest request, HttpHeaders header, Company company, Locale locale,
			User user, ServiceContext serviceContext, long id) {

		BackendAuth auth = new BackendAuthImpl();
		try {

			if (!auth.isAuth(serviceContext)) {
				throw new UnauthenticationException();
			}

			BookingActions actions = new BookingActionsImpl();
			Booking booking = actions.deleteBookingById(id, serviceContext);

			BookingDataModel result = BookingUtils.mappingForGetDetail(booking);

			return Response.status(200).entity(result).build();

		} catch (Exception e) {
			_log.error(e);
			return BusinessExceptionImpl.processException(e);
		}
	}

}
