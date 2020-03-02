package org.opencps.api.controller.impl;

import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Company;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.search.Document;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.search.Indexer;
import com.liferay.portal.kernel.search.IndexerRegistryUtil;
import com.liferay.portal.kernel.search.SearchException;
import com.liferay.portal.kernel.search.Sort;
import com.liferay.portal.kernel.search.SortFactoryUtil;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.Validator;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
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
import org.opencps.auth.utils.APIDateTimeUtils;
import org.opencps.communication.model.ServerConfig;
import org.opencps.communication.service.ServerConfigLocalServiceUtil;
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
				String online = search.getOnline();

				String from = APIDateTimeUtils.convertNormalDateToLuceneDate(search.getFrom());
				String to = APIDateTimeUtils.convertNormalDateToLuceneDate(search.getTo());
				String checkinFrom = APIDateTimeUtils.convertNormalDateToLuceneDate(search.getBookingFrom());
				String checkinTo = APIDateTimeUtils.convertNormalDateToLuceneDate(search.getBookingTo());

				params.put(BookingTerm.SERVICE_CODE_SEARCH, serviceCode);
				params.put(BookingTerm.STATE, state);
				params.put(BookingTerm.FROM_CREATE_DATE, from);
				params.put(BookingTerm.TO_CREATE_DATE, to);
				params.put(BookingTerm.FROM_CHECK_IN_DATE, checkinFrom);
				params.put(BookingTerm.TO_CHECK_IN_DATE, checkinTo);
				params.put(BookingTerm.GATE_NUMBER, search.getGateNumber());
				params.put(BookingTerm.CLASS_NAME, className);
				params.put(BookingTerm.ONLINE, online);
				params.put(BookingTerm.CODE_NUMBER, search.getCodeNumber());
				
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

//	public static void main(String[] args) throws ParseException {
//
//		int rate = 6;
//		
//		int bbb = (int) Math.ceil((double)rate / 5) * 5;
//		System.out.println(bbb);
//	}
	@Override
	public Response addBooking(HttpServletRequest request, HttpHeaders header, Company company, Locale locale,
			User user, ServiceContext serviceContext, BookingInputModel input) {

		long groupId = GetterUtil.getLong(input.getGroupIdBooking()) > 0 ? GetterUtil.getLong(input.getGroupIdBooking())
				: GetterUtil.getLong(header.getHeaderString("groupId"));
		//long groupIdBooking = GetterUtil.getLong(input.getGroupIdBooking());
		long userId = serviceContext.getUserId();
		_log.info("groupId: "+groupId);

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
			String serviceGroupCode = input.getServiceGroupCode();
			boolean online = Boolean.valueOf(input.getOnline());
			String telNo = input.getTelNo();
			Date checkinDate = null;
			if (Validator.isNotNull(state) && state == 1 && !online) {
				checkinDate = new Date();
			}

			Date bookingDate = null;
			String bookingDateSearch = "";
			String strBookingDate = input.getBookingDate();
			String[] splitBookingDate = null;
			if (Validator.isNotNull(input.getBookingDate())) {
				splitBookingDate = strBookingDate.split("/");
				bookingDateSearch = splitBookingDate[2] + "-" + splitBookingDate[1] + "-" + splitBookingDate[0];

				bookingDate = APIDateTimeUtils.convertStringToDate(input.getBookingDate(),
						APIDateTimeUtils._NORMAL_PARTTERN);
				if (bookingDate == null) {
					bookingDate = APIDateTimeUtils.convertStringToDate(input.getBookingDate(),
							APIDateTimeUtils._NORMAL_DATE);
				}
			}
			//Convert Date to sql Date
			Calendar calCurrent = Calendar.getInstance();
			calCurrent.setTime(new Date());
			//
			boolean speaking = Boolean.valueOf(input.getSpeaking());
			String bookingInTime = StringPool.BLANK;
			if (online) {
				_log.info("bookingDate: "+bookingDate);
				List bookingList = actions.getBookingCounterOnline(groupId, bookingDateSearch, online, serviceContext);
				if (bookingList != null && bookingList.size() > 0) {
					
					_log.info("JSONFactoryUtil.serialize(bookingList.get(0)): "+JSONFactoryUtil.serialize(bookingList.get(0)));
					//_log.info("bookingDate: "+bookingDate);
					JSONArray bookingLast = JSONFactoryUtil.createJSONArray(JSONFactoryUtil.serialize(bookingList.get(0)));
					_log.info("splitBookingDate[0]: "+splitBookingDate[0]);
					_log.info("splitBookingDate[1]: "+splitBookingDate[1]);
					_log.info("splitBookingDate[2]: "+splitBookingDate[2]);
					_log.info("calCurrent.get(Calendar.DATE: "+calCurrent.get(Calendar.DATE));
					_log.info("calCurrent.get(Calendar.MONTH): "+calCurrent.get(Calendar.MONTH));
					_log.info("calCurrent.get(Calendar.YEAR): "+calCurrent.get(Calendar.YEAR));
					_log.info("bookingLast: "+bookingLast);
					if (splitBookingDate.length == 3 && GetterUtil.getInteger(splitBookingDate[0]) == calCurrent.get(Calendar.DATE)
							&& GetterUtil.getInteger(splitBookingDate[1]) == (calCurrent.get(Calendar.MONTH) + 1)
							&& GetterUtil.getInteger(splitBookingDate[2]) == calCurrent.get(Calendar.YEAR)) {
						_log.info("VAO CHO NAY HAVE BOOKING");
						
						if (Validator.isNotNull(bookingLast.getString(4)) && bookingLast.getString(4).contains("-")) {
							String endTimeBook = bookingLast.getString(4).split("-")[1].trim();
							_log.info("endTimeBook: "+endTimeBook);
							if (Validator.isNotNull(endTimeBook)) {
								String[] splitTimeBook = endTimeBook.split(":");
								int hourTimeBook = GetterUtil.getInteger(splitTimeBook[0]);
								int minuteTimeBook = GetterUtil.getInteger(splitTimeBook[1]);
								_log.info("hourTimeBook: "+hourTimeBook);
								_log.info("minuteTimeBook: "+minuteTimeBook);
								//
								int timeBook = hourTimeBook * 60 + minuteTimeBook;
								//
								ServerConfig config = ServerConfigLocalServiceUtil.getByServerNoAndProtocol(groupId,
										"BOOKING_CONFIG", "MULTIMEDIA");
								if (config != null) {
									JSONObject jsonData = JSONFactoryUtil.createJSONObject(config.getConfigs());
									if (jsonData.has("bookingOnline")
											&& Validator.isNotNull(jsonData.getString("bookingOnline"))
											&& jsonData.getString("bookingOnline").contains(";")) {
										
										// "8h00-11h00; 13h00-16h00"
										String[] splitInTimeBook = jsonData.getString("bookingOnline").split(";");
										int bookingOnlineBreek = GetterUtil.getInteger(jsonData.getString("bookingOnlineBreek"));
										int bookingOnlineWaiting = GetterUtil.getInteger(jsonData.getString("bookingOnlineWaiting"));
										String inTimeMorning = splitInTimeBook[0].trim();
										String inTimeAfternoon = splitInTimeBook[1].trim();
										_log.info("inTimeMorning: "+inTimeMorning);
										_log.info("inTimeAfternoon: "+inTimeAfternoon);
										if (Validator.isNotNull(inTimeMorning) && Validator.isNotNull(inTimeAfternoon)) {
											
											String[] splitTimeMoring = inTimeMorning.split("-");
											String endMoring = splitTimeMoring[1].trim();
											String[] splitTimeAfternoon = inTimeAfternoon.split("-");
											String startAfternoon = splitTimeAfternoon[0].trim();
											String endAfternoon = splitTimeAfternoon[1].trim();
											//
											String[] splitEndMoring = endMoring.split(":");
											int endHourMoring = GetterUtil.getInteger(splitEndMoring[0]);
											int endMinuteMoring = GetterUtil.getInteger(splitEndMoring[1]);
											//
											int endTimeMoring = endHourMoring * 60 + endMinuteMoring;
											String[] splitStartAfternoon = startAfternoon.split(":");
											int startHourAfternoon = GetterUtil.getInteger(splitStartAfternoon[0]);
											int startMinuteAfternoon = GetterUtil.getInteger(splitStartAfternoon[1]);
											//
											int startTimeAfternoon = startHourAfternoon * 60 + startMinuteAfternoon;
											String[] splitEndAfternoon = endAfternoon.split(":");
											int endHourAfternoon = GetterUtil.getInteger(splitEndAfternoon[0]);
											int endMinuteAfternoon = GetterUtil.getInteger(splitEndAfternoon[1]);
											//
											int endTimeAfternoon = endHourAfternoon * 60 + endMinuteAfternoon;
											//
											Calendar calNow = Calendar.getInstance();
											calNow.setTime(new Date());
											//
											int timeCurrentBooking = calNow.get(Calendar.HOUR_OF_DAY) * 60 + calNow.get(Calendar.MINUTE);
											
											int timeBookWaitting = 0;
											
											if (timeBook < endTimeMoring) {
												_log.info("AAAAAAA: "+timeBook);
												if (timeBook - timeCurrentBooking >= bookingOnlineWaiting ) {
													timeBook += bookingOnlineBreek;
													timeBookWaitting = timeBook - bookingOnlineWaiting;
												} else if (timeBook - timeCurrentBooking < bookingOnlineWaiting && timeBook - timeCurrentBooking >= 0) {
													timeBook += bookingOnlineWaiting;
													if (timeBook > endTimeMoring) {
														timeBook = startTimeAfternoon;
														timeBookWaitting = startTimeAfternoon - bookingOnlineWaiting;
													} else {
														timeBookWaitting = timeBook;
													}
												} else {
													// timeCurrentBooking > timeBook
													int timeCeilCurrent = (int) (Math.ceil((double)timeCurrentBooking / 5) * 5);
													_log.info("timeCeilCurrent: "+timeCeilCurrent);
													if (timeCeilCurrent < endTimeMoring) {
														timeBook = timeCeilCurrent + bookingOnlineWaiting;
														_log.info("timeBook: "+timeBook);
														if (timeBook <= endTimeMoring) {
															timeBookWaitting = timeCeilCurrent;
														} else {
															timeBook = startTimeAfternoon;
															timeBookWaitting = timeBook - bookingOnlineWaiting;
														}
														_log.info("timeBookWaitting: "+timeBookWaitting);
													} else {
														if (startTimeAfternoon - timeCeilCurrent > bookingOnlineWaiting) {
															timeBook = startTimeAfternoon;
															timeBookWaitting = timeBook - bookingOnlineWaiting;
														} else if (endTimeAfternoon - timeCeilCurrent >= bookingOnlineWaiting) {
															timeBookWaitting = timeCeilCurrent;
															timeBook = timeCeilCurrent + bookingOnlineWaiting;
														}
													}
												}
											} 
											else if (timeBook == endTimeMoring) {
												if (timeBook - timeCurrentBooking >= 0) {
													timeBook = startTimeAfternoon;
													timeBookWaitting = timeBook - bookingOnlineWaiting;
												} else {
													// timeCurrentBooking > timeBook
													int timeCeilCurrent = (int) (Math.ceil((double)timeCurrentBooking / 5) * 5);
													if (startTimeAfternoon - timeCeilCurrent > bookingOnlineWaiting) {
														timeBook = startTimeAfternoon;
														timeBookWaitting = timeBook - bookingOnlineWaiting;
													} else if (endTimeAfternoon - timeCeilCurrent >= bookingOnlineWaiting) {
														timeBookWaitting = timeCeilCurrent;
														timeBook = timeCeilCurrent + bookingOnlineWaiting;
													}
												}
											}
											else if(timeBook > endTimeMoring && timeBook < endTimeAfternoon){
												if (timeBook - timeCurrentBooking >= bookingOnlineWaiting ) {
													timeBook += bookingOnlineBreek;
													timeBookWaitting = timeBook - bookingOnlineWaiting;
												} else if (timeBook - timeCurrentBooking < bookingOnlineWaiting && timeBook - timeCurrentBooking >= 0) {
													timeBook += bookingOnlineWaiting;
													timeBookWaitting = timeBook;
												} else {
													// timeCurrentBooking > timeBook
													int timeCeilCurrent = (int) (Math.ceil((double)timeCurrentBooking / 5) * 5);
													timeBook = timeCeilCurrent + bookingOnlineWaiting;
													timeBookWaitting = timeCeilCurrent;
												}
											}
											
											//
											String startHourWaitting = (timeBookWaitting/60) < 10 ? ("0" + timeBookWaitting/60) : String.valueOf(timeBookWaitting/60);
											String startMinuteWaitting = (timeBookWaitting % 60) < 10 ? "0" + timeBookWaitting % 60 : String.valueOf(timeBookWaitting % 60);
											String endHourWaitting = (timeBook / 60) < 10 ? ("0" + timeBook/60) : String.valueOf(timeBook/60);
											String endMinuteWaitting = (timeBook % 60) < 10 ? ("0" + timeBook % 60) : String.valueOf(timeBook % 60);
											bookingInTime = startHourWaitting + ":" + startMinuteWaitting + "-" + endHourWaitting + ":" + endMinuteWaitting;
											_log.info("bookingInTime: "+bookingInTime);
											//Set lai bookingDate
											Calendar cal = Calendar.getInstance();
											cal.setTime(bookingDate);
											cal.set(Calendar.HOUR_OF_DAY, timeBook / 60);
											cal.set(Calendar.MINUTE, timeBook % 60);
											//
											bookingDate = cal.getTime();
											checkinDate = cal.getTime();
										}
									}
								}
							}
						}
						
					} else {
						if (Validator.isNotNull(bookingLast.getString(4)) && bookingLast.getString(4).contains("-")) {
							String endTimeBook = bookingLast.getString(4).split("-")[1].trim();
							if (Validator.isNotNull(endTimeBook)) {
								String[] splitTimeBook = endTimeBook.split(":");
								int hourTimeBook = GetterUtil.getInteger(splitTimeBook[0]);
								int minuteTimeBook = GetterUtil.getInteger(splitTimeBook[1]);
								//
								int timeBook = hourTimeBook * 60 + minuteTimeBook;
								//
								ServerConfig config = ServerConfigLocalServiceUtil.getByServerNoAndProtocol(groupId,
										"BOOKING_CONFIG", "MULTIMEDIA");
								if (config != null) {
									JSONObject jsonData = JSONFactoryUtil.createJSONObject(config.getConfigs());
									if (jsonData.has("bookingOnline")
											&& Validator.isNotNull(jsonData.getString("bookingOnline"))
											&& jsonData.getString("bookingOnline").contains(";")) {
										
										// "8h00-11h00; 13h00-16h00"
										String[] splitInTimeBook = jsonData.getString("bookingOnline").split(";");
										int bookingOnlineBreek = GetterUtil.getInteger(jsonData.getString("bookingOnlineBreek"));
										int bookingOnlineWaiting = GetterUtil.getInteger(jsonData.getString("bookingOnlineWaiting"));
										String inTimeMorning = splitInTimeBook[0].trim();
										String inTimeAfternoon = splitInTimeBook[1].trim();
										if (Validator.isNotNull(inTimeMorning) && Validator.isNotNull(inTimeAfternoon)) {
											
											String[] splitTimeMoring = inTimeMorning.split("-");
											String endMoring = splitTimeMoring[1].trim();
											String[] splitTimeAfternoon = inTimeAfternoon.split("-");
											String startAfternoon = splitTimeAfternoon[0].trim();
											String endAfternoon = splitTimeAfternoon[1].trim();
											//
											String[] splitEndMoring = endMoring.split(":");
											int endHourMoring = GetterUtil.getInteger(splitEndMoring[0]);
											int endMinuteMoring = GetterUtil.getInteger(splitEndMoring[1]);
											//
											int endTimeMoring = endHourMoring * 60 + endMinuteMoring;
											String[] splitStartAfternoon = startAfternoon.split(":");
											int startHourAfternoon = GetterUtil.getInteger(splitStartAfternoon[0]);
											int startMinuteAfternoon = GetterUtil.getInteger(splitStartAfternoon[1]);
											//
											int startTimeAfternoon = startHourAfternoon * 60 + startMinuteAfternoon;
											String[] splitEndAfternoon = endAfternoon.split(":");
											int endHourAfternoon = GetterUtil.getInteger(splitEndAfternoon[0]);
											int endMinuteAfternoon = GetterUtil.getInteger(splitEndAfternoon[1]);
											//
											int endTimeAfternoon = endHourAfternoon * 60 + endMinuteAfternoon;
											
											//
											int timeBookWaitting = 0;
											if (timeBook < endTimeMoring || (timeBook > endTimeMoring && timeBook < endTimeAfternoon)) {
												timeBook += bookingOnlineBreek;
												timeBookWaitting = timeBook - bookingOnlineWaiting;
											} else if (timeBook == endTimeMoring) {
												timeBook = startTimeAfternoon;
												timeBookWaitting = timeBook - bookingOnlineWaiting;
											}
											//
											String startHourWaitting = (timeBookWaitting/60) < 10 ? ("0" + timeBookWaitting/60) : String.valueOf(timeBookWaitting/60);
											String startMinuteWaitting = (timeBookWaitting % 60) < 10 ? "0" + timeBookWaitting % 60 : String.valueOf(timeBookWaitting % 60);
											String endHourWaitting = (timeBook / 60) < 10 ? ("0" + timeBook/60) : String.valueOf(timeBook/60);
											String endMinuteWaitting = (timeBook % 60) < 10 ? ("0" + timeBook % 60) : String.valueOf(timeBook % 60);
											bookingInTime = startHourWaitting + ":" + startMinuteWaitting + "-" + endHourWaitting + ":" + endMinuteWaitting;
											_log.info("bookingInTime: "+bookingInTime);
											//Set lai bookingDate
											Calendar cal = Calendar.getInstance();
											cal.setTime(bookingDate);
											cal.set(Calendar.HOUR_OF_DAY, timeBook / 60);
											cal.set(Calendar.MINUTE, timeBook % 60);
											//
											bookingDate = cal.getTime();
											checkinDate = cal.getTime();
										}
									}
								}
							}
						}
					}
				} else {
					if (splitBookingDate.length == 3 && GetterUtil.getInteger(splitBookingDate[0]) == calCurrent.get(Calendar.DATE)
							&& GetterUtil.getInteger(splitBookingDate[1]) == (calCurrent.get(Calendar.MONTH) + 1)
							&& GetterUtil.getInteger(splitBookingDate[2]) == calCurrent.get(Calendar.YEAR)) {
						ServerConfig config = ServerConfigLocalServiceUtil.getByServerNoAndProtocol(groupId,
								"BOOKING_CONFIG", "MULTIMEDIA");
						
						if (config != null) {
							JSONObject jsonData = JSONFactoryUtil.createJSONObject(config.getConfigs());
							_log.info("jsonData: "+jsonData);
							if (jsonData.has("bookingOnline")
									&& Validator.isNotNull(jsonData.getString("bookingOnline"))
									&& jsonData.getString("bookingOnline").contains(";")) {
								
								// "8h00-11h00;13h00-16h00"
								String[] splitInTimeBook = jsonData.getString("bookingOnline").split(";");
								int bookingOnlineWaiting = GetterUtil.getInteger(jsonData.getString("bookingOnlineWaiting"));
								String inTimeMorning = splitInTimeBook[0].trim();
								String inTimeAfternoon = splitInTimeBook[1].trim();
								
								if (Validator.isNotNull(inTimeMorning) && Validator.isNotNull(inTimeAfternoon)) {
									
									String[] splitTimeMoring = inTimeMorning.split("-");
									String startMoring = splitTimeMoring[0].trim();
									String endMoring = splitTimeMoring[1].trim();
									String[] splitTimeAfternoon = inTimeAfternoon.split("-");
									String startAfternoon = splitTimeAfternoon[0].trim();
									String endAfternoon = splitTimeAfternoon[1].trim();
									//
									String[] splitStartMoring = startMoring.split(":");
									_log.info("splitStartMoring: "+splitStartMoring);
									int startHourMoring = GetterUtil.getInteger(splitStartMoring[0]);
									int startMinuteMoring = GetterUtil.getInteger(splitStartMoring[1]);
									//
									int startTimeMoring = startHourMoring * 60 + startMinuteMoring;
									//
									String[] splitEndMoring = endMoring.split(":");
									int endHourMoring = GetterUtil.getInteger(splitEndMoring[0]);
									int endMinuteMoring = GetterUtil.getInteger(splitEndMoring[1]);
									//
									int endTimeMoring = endHourMoring * 60 + endMinuteMoring;
									String[] splitStartAfternoon = startAfternoon.split(":");
									int startHourAfternoon = GetterUtil.getInteger(splitStartAfternoon[0]);
									int startMinuteAfternoon = GetterUtil.getInteger(splitStartAfternoon[1]);
									//
									int startTimeAfternoon = startHourAfternoon * 60 + startMinuteAfternoon;
									String[] splitEndAfternoon = endAfternoon.split(":");
									int endHourAfternoon = GetterUtil.getInteger(splitEndAfternoon[0]);
									int endMinuteAfternoon = GetterUtil.getInteger(splitEndAfternoon[1]);
									//
									int endTimeAfternoon = endHourAfternoon * 60 + endMinuteAfternoon;
									//
									Calendar calNow = Calendar.getInstance();
									calNow.setTime(new Date());
									//
									int timeCurrentBooking = calNow.get(Calendar.HOUR_OF_DAY) * 60 + calNow.get(Calendar.MINUTE);
									int timeCeilCurrent = (int) (Math.ceil((double)timeCurrentBooking / 5) * 5);
									
									int timeBookWaitting = 0;
									int timeBook = 0;
									
									if (startTimeMoring - timeCeilCurrent >= 15) {
										timeBook = startTimeMoring;
										timeBookWaitting = timeBook - bookingOnlineWaiting;
									} 
									else if (startTimeMoring - timeCeilCurrent < 15 && endTimeMoring - timeCeilCurrent >= 15) {
										timeBook = timeCeilCurrent + bookingOnlineWaiting;
										timeBookWaitting = timeCeilCurrent;
									}
									else if(endTimeMoring - timeCeilCurrent < 15 && startTimeAfternoon - timeCeilCurrent >= 15){
										timeBook = startTimeAfternoon;
										timeBookWaitting = timeBook - bookingOnlineWaiting;
									} else if(endTimeAfternoon - timeCeilCurrent >= 15){
										timeBook = timeCeilCurrent + bookingOnlineWaiting;
										timeBookWaitting = timeCeilCurrent;
									}
									
									//
									String startHourWaitting = (timeBookWaitting/60) < 10 ? ("0" + timeBookWaitting/60) : String.valueOf(timeBookWaitting/60);
									String startMinuteWaitting = (timeBookWaitting % 60) < 10 ? "0" + timeBookWaitting % 60 : String.valueOf(timeBookWaitting % 60);
									String endHourWaitting = (timeBook / 60) < 10 ? ("0" + timeBook/60) : String.valueOf(timeBook/60);
									String endMinuteWaitting = (timeBook % 60) < 10 ? ("0" + timeBook % 60) : String.valueOf(timeBook % 60);
									bookingInTime = startHourWaitting + ":" + startMinuteWaitting + "-" + endHourWaitting + ":" + endMinuteWaitting;
									_log.info("bookingInTime: "+bookingInTime);
									//Set lai bookingDate
									Calendar cal = Calendar.getInstance();
									cal.setTime(bookingDate);
									cal.set(Calendar.HOUR_OF_DAY, timeBook / 60);
									cal.set(Calendar.MINUTE, timeBook % 60);
									//
									bookingDate = cal.getTime();
									checkinDate = cal.getTime();
								}
							}
						}
					}
					else {
						ServerConfig config = ServerConfigLocalServiceUtil.getByServerNoAndProtocol(groupId,
								"BOOKING_CONFIG", "MULTIMEDIA");
						
						if (config != null) {
							JSONObject jsonData = JSONFactoryUtil.createJSONObject(config.getConfigs());
							_log.info("jsonData: "+jsonData);
							if (jsonData.has("bookingOnline")
									&& Validator.isNotNull(jsonData.getString("bookingOnline"))
									&& jsonData.getString("bookingOnline").contains(";")) {
								
								// "8h00-11h00;13h00-16h00"
								String[] splitInTimeBook = jsonData.getString("bookingOnline").split(";");
								int bookingOnlineWaiting = GetterUtil.getInteger(jsonData.getString("bookingOnlineWaiting"));
								String inTimeMorning = splitInTimeBook[0].trim();
								_log.info("inTimeMorning: "+inTimeMorning);
								if (Validator.isNotNull(inTimeMorning)) {
									
									String[] splitTimeMoring = inTimeMorning.split("-");
									String startMoring = splitTimeMoring[0].trim();
									_log.info("startMoring: "+startMoring);
									//
									String[] splitStartMoring = startMoring.split(":");
									_log.info("splitStartMoring: "+splitStartMoring);
									int startHourMoring = GetterUtil.getInteger(splitStartMoring[0]);
									int startMinuteMoring = GetterUtil.getInteger(splitStartMoring[1]);
									//
									int startTimeMoring = startHourMoring * 60 + startMinuteMoring;
									int timeBookWaitting = startTimeMoring - bookingOnlineWaiting;
									//
									String startHourWaitting = (timeBookWaitting/60) < 10 ? ("0" + timeBookWaitting/60) : String.valueOf(timeBookWaitting/60);
									String startMinuteWaitting = (timeBookWaitting % 60) < 10 ? "0" + timeBookWaitting % 60 : String.valueOf(timeBookWaitting % 60);
									bookingInTime = startHourWaitting + ":" + startMinuteWaitting + "-" + startMoring;
									_log.info("bookingInTime: "+bookingInTime);
									//Set lai bookingDate
									Calendar cal = Calendar.getInstance();
									cal.setTime(bookingDate);
									cal.set(Calendar.HOUR_OF_DAY, startHourMoring);
									cal.set(Calendar.MINUTE, startMinuteMoring);
									//
									bookingDate = cal.getTime();
									checkinDate = cal.getTime();
								}
							}
						}
					}

				}
			}


			Booking booking = BookingLocalServiceUtil.getByClassName_PK(groupId, className, classPK);
			if (booking != null) {
				booking = actions.updateBooking(userId, groupId, booking.getBookingId(), className, classPK,
						serviceCode, codeNumber, bookingName, gateNumber, state, checkinDate, bookingDate, speaking,
						serviceGroupCode, online, bookingInTime, telNo, serviceContext);
			} else {
				booking = actions.updateBooking(userId, groupId, 0, className, classPK, serviceCode, codeNumber,
						bookingName, gateNumber, state, checkinDate, bookingDate, speaking, serviceGroupCode,
						online, bookingInTime, telNo, serviceContext);
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
				_log.info("state: "+state);
				String serviceGroupCode = input.getServiceGroupCode();
				String telNo = input.getTelNo();
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
				boolean online = Boolean.valueOf(input.getOnline());
				String bookingInTime = StringPool.BLANK;

				booking = actions.updateBooking(userId, groupId, booking.getBookingId(), className, classPK,
						serviceCode, codeNumber, bookingName, gateNumber, state, checkinDate, bookingDate, speaking,
						serviceGroupCode, online, bookingInTime, telNo, serviceContext);
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

		//BackendAuth auth = new BackendAuthImpl();
		try {

			//if (!auth.isAuth(serviceContext)) {
			//	throw new UnauthenticationException();
			//}

			BookingActions actions = new BookingActionsImpl();
			Booking booking = actions.deleteBookingById(id, serviceContext);

			BookingDataModel result = BookingUtils.mappingForGetDetail(booking);

			return Response.status(200).entity(result).build();

		} catch (Exception e) {
			_log.error(e);
			return BusinessExceptionImpl.processException(e);
		}
	}

	@Override
	public Response getBookingCounterOnline(HttpServletRequest request, HttpHeaders header, Company company,
			Locale locale, User user, ServiceContext serviceContext, String groupIdBooking, BookingSearchModel search) {

		String strBookingDate = search.getBookingDate();
		long groupId = GetterUtil.getLong(groupIdBooking);
		_log.info("groupId: "+groupId);
		boolean online = Boolean.valueOf(search.getOnline());
		
		try {

			String bookingDateSearch = "";
			String[] splitBookingDate = null;
			if (Validator.isNotNull(strBookingDate)) {
				splitBookingDate = strBookingDate.split("/");
				bookingDateSearch = splitBookingDate[2] + "-" + splitBookingDate[1] + "-" + splitBookingDate[0];
			}

			BookingActions actions = new BookingActionsImpl();
			List bookingList = actions.getBookingCounterOnline(groupId, bookingDateSearch, online, serviceContext);
			_log.info("bookingList: "+bookingList);
			_log.info("splitBookingDate[0]: "+splitBookingDate[0]);
			_log.info("splitBookingDate[1]: "+splitBookingDate[1]);
			_log.info("splitBookingDate[2]: "+splitBookingDate[2]);

			JSONObject jsonValue = JSONFactoryUtil.createJSONObject();
			jsonValue.put("booking", false);
			Calendar cal = Calendar.getInstance();
			cal.setTime(new Date());
			_log.info("calCurrent.get(Calendar.DATE: "+cal.get(Calendar.DATE));
			_log.info("calCurrent.get(Calendar.MONTH): "+cal.get(Calendar.MONTH));
			_log.info("calCurrent.get(Calendar.YEAR): "+cal.get(Calendar.YEAR));
			if (bookingList != null && bookingList.size() > 0) {
				JSONArray bookingLast = JSONFactoryUtil.createJSONArray(JSONFactoryUtil.serialize(bookingList.get(0)));
				_log.info("bookingLast: "+bookingLast);
				
				if (Validator.isNotNull(bookingLast.getString(4)) && bookingLast.getString(4).contains("-")) {
					if (splitBookingDate.length == 3 && GetterUtil.getInteger(splitBookingDate[0]) == cal.get(Calendar.DATE)
						&& GetterUtil.getInteger(splitBookingDate[1]) == (cal.get(Calendar.MONTH) + 1)
						&& GetterUtil.getInteger(splitBookingDate[2]) == cal.get(Calendar.YEAR)) {
						
						String endTimeBook = bookingLast.getString(4).split("-")[1].trim();
						_log.info("endTimeBook: "+endTimeBook);
						if (Validator.isNotNull(endTimeBook)) {
							String[] splitTimeBook = endTimeBook.split(":");
							//int hourTimeBook = GetterUtil.getInteger(splitTimeBook[0]);
							//int minuteTimeBook = GetterUtil.getInteger(splitTimeBook[1]);
							//
							_log.info("GetterUtil.getInteger(splitTimeBook[0]): "+GetterUtil.getInteger(splitTimeBook[0]));
							_log.info("GetterUtil.getInteger(splitTimeBook[1]): "+GetterUtil.getInteger(splitTimeBook[1]));
							int timeBook = GetterUtil.getInteger(splitTimeBook[0]) * 60
									+ GetterUtil.getInteger(splitTimeBook[1]);
							_log.info("timeBook: "+timeBook);
							
							ServerConfig config = ServerConfigLocalServiceUtil.getByServerNoAndProtocol(groupId,
									"BOOKING_CONFIG", "MULTIMEDIA");
							if (config != null) {
								JSONObject jsonData = JSONFactoryUtil.createJSONObject(config.getConfigs());
								if (jsonData.has("bookingOnline")
										&& Validator.isNotNull(jsonData.getString("bookingOnline"))
										&& jsonData.getString("bookingOnline").contains(";")) {
									String endLongTime = jsonData.getString("bookingOnline").split(";")[1].trim();
									_log.info("endLongTime: "+endLongTime);
									int bookingOnlineWaiting = GetterUtil.getInteger(jsonData.getString("bookingOnlineWaiting"));
									if (Validator.isNotNull(endLongTime) && endLongTime.contains("-")) {
										String endReceiveTime = endLongTime.split("-")[1].trim();
										_log.info("endReceiveTime: "+endReceiveTime);
										if (Validator.isNotNull(endReceiveTime)) {
											String[] splitReceiveTime = endReceiveTime.split(":");
											//int hourReceiveBook = GetterUtil.getInteger(splitReceiveTime[0]);
											//int minuteReceiveBook = GetterUtil.getInteger(splitReceiveTime[1]);
											int endTimeReceiveBook = GetterUtil.getInteger(splitReceiveTime[0]) * 60
													+ GetterUtil.getInteger(splitReceiveTime[1]);
											_log.info("endTimeReceiveBook: "+endTimeReceiveBook);
											// Current time book
											int timeCurrentBooking = cal.get(Calendar.HOUR_OF_DAY) * 60 + cal.get(Calendar.MINUTE);
											int timeCeilCurrent = (int) (Math.ceil((double)timeCurrentBooking / 5) * 5);
											_log.info("timeCeilCurrent: "+timeCeilCurrent);

											if (timeCeilCurrent < timeBook && timeBook < endTimeReceiveBook
													&& endTimeReceiveBook - timeCeilCurrent > bookingOnlineWaiting) {
												jsonValue.put("booking", true);
											} else if (timeCeilCurrent > timeBook
													&& endTimeReceiveBook - timeCeilCurrent > bookingOnlineWaiting) {
												jsonValue.put("booking", true);
											}
										}
									}
								}
							}
						}
					} else {
						String endTimeBook = bookingLast.getString(4).split("-")[1].trim();
						_log.info("endTimeBook: "+endTimeBook);
						if (Validator.isNotNull(endTimeBook)) {
							String[] splitTimeBook = endTimeBook.split(":");
							int hourTimeBook = GetterUtil.getInteger(splitTimeBook[0]);
							int minuteTimeBook = GetterUtil.getInteger(splitTimeBook[1]);
							ServerConfig config = ServerConfigLocalServiceUtil.getByServerNoAndProtocol(groupId,
									"BOOKING_CONFIG", "MULTIMEDIA");
							if (config != null) {
								JSONObject jsonData = JSONFactoryUtil.createJSONObject(config.getConfigs());
								if (jsonData.has("bookingOnline")
										&& Validator.isNotNull(jsonData.getString("bookingOnline"))
										&& jsonData.getString("bookingOnline").contains(";")) {
									String endLongTime = jsonData.getString("bookingOnline").split(";")[1].trim();
									if (Validator.isNotNull(endLongTime) && endLongTime.contains("-")) {
										String endReceiveTime = endLongTime.split("-")[1].trim();
										if (Validator.isNotNull(endReceiveTime)) {
											String[] splitReceiveTime = endReceiveTime.split(":");
											int hourReceiveBook = GetterUtil.getInteger(splitReceiveTime[0]);
											int minuteReceiveBook = GetterUtil.getInteger(splitReceiveTime[1]);
											if (hourReceiveBook > hourTimeBook || (hourTimeBook == hourReceiveBook
													&& minuteTimeBook < minuteReceiveBook)) {
												jsonValue.put("booking", true);
											}
										}
									}
								}
							}
						}
					}
				}
			} else {
				if (splitBookingDate.length == 3 && GetterUtil.getInteger(splitBookingDate[0]) == cal.get(Calendar.DATE)
						&& GetterUtil.getInteger(splitBookingDate[1]) == (cal.get(Calendar.MONTH) + 1)
						&& GetterUtil.getInteger(splitBookingDate[2]) == cal.get(Calendar.YEAR)) {

					ServerConfig config = ServerConfigLocalServiceUtil.getByServerNoAndProtocol(groupId,
							"BOOKING_CONFIG", "MULTIMEDIA");
					if (config != null) {
						JSONObject jsonData = JSONFactoryUtil.createJSONObject(config.getConfigs());
						if (jsonData.has("bookingOnline")
								&& Validator.isNotNull(jsonData.getString("bookingOnline"))
								&& jsonData.getString("bookingOnline").contains(";")) {
							String endLongTime = jsonData.getString("bookingOnline").split(";")[1].trim();
							_log.info("endLongTime: "+endLongTime);
							int bookingOnlineWaiting = GetterUtil.getInteger(jsonData.getString("bookingOnlineWaiting"));
							_log.info("bookingOnlineWaiting: "+bookingOnlineWaiting);
							if (Validator.isNotNull(endLongTime) && endLongTime.contains("-")) {
								String endReceiveTime = endLongTime.split("-")[1].trim();
								if (Validator.isNotNull(endReceiveTime)) {
									String[] splitReceiveTime = endReceiveTime.split(":");
									int hourReceiveBook = GetterUtil.getInteger(splitReceiveTime[0]);
									int minuteReceiveBook = GetterUtil.getInteger(splitReceiveTime[1]);
									_log.info("hourReceiveBook: "+hourReceiveBook);
									_log.info("minuteReceiveBook: "+minuteReceiveBook);
									//
									int endTimeReceiveBook = GetterUtil.getInteger(splitReceiveTime[0]) * 60
											+ GetterUtil.getInteger(splitReceiveTime[1]);
									_log.info("endTimeReceiveBook: "+endTimeReceiveBook);
									//
									int timeCurrentBooking = cal.get(Calendar.HOUR_OF_DAY) * 60 + cal.get(Calendar.MINUTE);
									_log.info("cal.get(Calendar.HOUR_OF_DAY): "+cal.get(Calendar.HOUR_OF_DAY));
									_log.info("cal.get(Calendar.HOUR): "+cal.get(Calendar.HOUR));
									_log.info("cal.get(Calendar.MINUTE): "+cal.get(Calendar.MINUTE));
									int timeCeilCurrent = (int) (Math.ceil((double)timeCurrentBooking / 5) * 5);
									_log.info("timeCeilCurrent: "+timeCeilCurrent);
									
									if(endTimeReceiveBook - timeCeilCurrent >= bookingOnlineWaiting){
										jsonValue.put("booking", true);
									}
								}
							}
						}
					}
				} else {
					jsonValue.put("booking", true);
				}
			}

			return Response.status(200).entity(JSONFactoryUtil.looseSerializeDeep(jsonValue)).build();

		} catch (Exception e) {
			_log.error(e);
			return BusinessExceptionImpl.processException(e);
		}
	}

	@Override
	public Response getBookingByCodeNumber(HttpServletRequest request, HttpHeaders header, Company company,
			Locale locale, User user, ServiceContext serviceContext, BookingSearchModel search) {
		try {


			if (Validator.isNotNull(search.getCodeNumber())) {
				BookingActions actions = new BookingActionsImpl();
				Booking booking = actions.getByCodeNumber(search.getCodeNumber());

				BookingDataModel result = null;
				if (Validator.isNotNull(booking)) {
					result = BookingUtils.mappingForGetDetail(booking);
				}

				return Response.status(200).entity(result).build();
			}

		} catch (Exception e) {
			_log.error(e);
			return BusinessExceptionImpl.processException(e);
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Response resolveConflictBooking(
		HttpServletRequest request, HttpHeaders header, Company company,
		Locale locale, User user, ServiceContext serviceContext) {

		long groupId = GetterUtil.getLong(header.getHeaderString("groupId"));
		// long userId = user.getUserId();
		BookingActions actions = new BookingActionsImpl();
		Indexer<Booking> indexer =
			IndexerRegistryUtil.nullSafeGetIndexer(Booking.class);

		LinkedHashMap<String, Object> params =
			new LinkedHashMap<String, Object>();
		params.put(Field.GROUP_ID, String.valueOf(groupId));

		JSONObject jsonData = actions.getBookingList(user.getUserId(), company.getCompanyId(), groupId, params, null,
				-1, -1, serviceContext);

		long total = jsonData.getLong("total");

		if (total > 0) {
			List<Document> lstDocuments = (List<Document>) jsonData.get("data");
			for (Document document : lstDocuments) {
				long bookingId =
					GetterUtil.getLong(document.get(BookingTerm.BOOKING_ID));
				long companyId =
					GetterUtil.getLong(document.get(Field.COMPANY_ID));
				String uid = document.get(Field.UID);
				Booking booking =
					BookingLocalServiceUtil.fetchBooking(bookingId);
				if (booking == null) {
					try {
						indexer.delete(companyId, uid);
					}
					catch (SearchException e) {
						_log.error(e);
					}
				}
			}
		}

		return Response.status(200).entity("{}").build();
	}
}
