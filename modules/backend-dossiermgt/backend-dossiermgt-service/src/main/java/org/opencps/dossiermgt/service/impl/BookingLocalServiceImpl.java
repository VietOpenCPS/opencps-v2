/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package org.opencps.dossiermgt.service.impl;

import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.search.BooleanClauseOccur;
import com.liferay.portal.kernel.search.BooleanQuery;
import com.liferay.portal.kernel.search.BooleanQueryFactoryUtil;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.search.Hits;
import com.liferay.portal.kernel.search.IndexSearcherHelperUtil;
import com.liferay.portal.kernel.search.Indexable;
import com.liferay.portal.kernel.search.IndexableType;
import com.liferay.portal.kernel.search.Indexer;
import com.liferay.portal.kernel.search.IndexerRegistryUtil;
import com.liferay.portal.kernel.search.ParseException;
import com.liferay.portal.kernel.search.SearchContext;
import com.liferay.portal.kernel.search.SearchException;
import com.liferay.portal.kernel.search.Sort;
import com.liferay.portal.kernel.search.WildcardQuery;
import com.liferay.portal.kernel.search.generic.BooleanQueryImpl;
import com.liferay.portal.kernel.search.generic.MultiMatchQuery;
import com.liferay.portal.kernel.search.generic.TermRangeQueryImpl;
import com.liferay.portal.kernel.search.generic.WildcardQueryImpl;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;

import java.util.Date;
import java.util.LinkedHashMap;

import org.opencps.dossiermgt.action.util.SpecialCharacterUtils;
import org.opencps.dossiermgt.constants.BookingTerm;
import org.opencps.dossiermgt.constants.ConstantsTerm;
import org.opencps.dossiermgt.constants.EFormTerm;
import org.opencps.dossiermgt.model.Booking;
import org.opencps.dossiermgt.service.base.BookingLocalServiceBaseImpl;

/**
 * The implementation of the booking local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link org.opencps.dossiermgt.service.BookingLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author huymq
 * @see BookingLocalServiceBaseImpl
 * @see org.opencps.dossiermgt.service.BookingLocalServiceUtil
 */
public class BookingLocalServiceImpl extends BookingLocalServiceBaseImpl {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this class directly. Always use {@link org.opencps.dossiermgt.service.BookingLocalServiceUtil} to access the booking local service.
	 */

	private static final Log _log = LogFactoryUtil.getLog(BookingLocalServiceImpl.class);
	public static final String CLASS_NAME = Booking.class.getName();

	public Hits searchLucene(LinkedHashMap<String, Object> params, Sort[] sorts, int start, int end,
			SearchContext searchContext) throws ParseException, SearchException {

		String keywords = (String) params.get(Field.KEYWORD_SEARCH);
		String groupId = (String) params.get(Field.GROUP_ID);
		// Extra fields
		String serviceCode = GetterUtil.getString(params.get(EFormTerm.SERVICE_CODE_SEARCH));
		String state = GetterUtil.getString((params.get(BookingTerm.STATE)));
		String from = GetterUtil.getString(params.get(BookingTerm.FROM_CREATE_DATE));
		String to = GetterUtil.getString(params.get(BookingTerm.TO_CREATE_DATE));
		String bookingFrom = GetterUtil.getString(params.get(BookingTerm.FROM_CREATE_DATE));
		String bookingTo = GetterUtil.getString(params.get(BookingTerm.TO_CREATE_DATE));
		String gateNumber = GetterUtil.getString(params.get(BookingTerm.GATE_NUMBER));
		String className = GetterUtil.getString(params.get(BookingTerm.CLASS_NAME));

		Indexer<Booking> indexer = IndexerRegistryUtil.nullSafeGetIndexer(Booking.class);

		searchContext.addFullQueryEntryClassName(CLASS_NAME);
		searchContext.setEntryClassNames(new String[] { CLASS_NAME });
		searchContext.setAttribute("paginationType", "regular");
		searchContext.setLike(true);
		searchContext.setStart(start);
		searchContext.setEnd(end);
		searchContext.setAndSearch(true);
		searchContext.setSorts(sorts);

		BooleanQuery booleanQuery = null;

		if (Validator.isNotNull(keywords)) {
			booleanQuery = BooleanQueryFactoryUtil.create(searchContext);
		} else {
			booleanQuery = indexer.getFullQuery(searchContext);
		}

		if (Validator.isNotNull(keywords)) {
			BooleanQuery queryBool = new BooleanQueryImpl();
			String[] subQuerieArr = new String[] { EFormTerm.EFORM_NO_SEARCH, EFormTerm.SERVICE_CODE_SEARCH};

			String[] keywordArr = keywords.split(StringPool.SPACE);
			for (String fieldSearch : subQuerieArr) {
				BooleanQuery query = new BooleanQueryImpl();
				for (String key : keywordArr) {
					WildcardQuery wildQuery = new WildcardQueryImpl(fieldSearch,
							StringPool.STAR + key.toLowerCase() + StringPool.STAR);
					query.add(wildQuery, BooleanClauseOccur.MUST);
				}
				queryBool.add(query, BooleanClauseOccur.SHOULD);
			}
			booleanQuery.add(queryBool, BooleanClauseOccur.MUST);
		}

		if (Validator.isNotNull(groupId)) {
			MultiMatchQuery query = new MultiMatchQuery(groupId);

			query.addFields(Field.GROUP_ID);

			booleanQuery.add(query, BooleanClauseOccur.MUST);
		}

		if (Validator.isNotNull(serviceCode)) {
			if (serviceCode.contains(StringPool.COMMA)) {
				String[] serviceArr = StringUtil.split(serviceCode);
				if (serviceArr != null && serviceArr.length > 0) {
					BooleanQuery subQuery = new BooleanQueryImpl();
					for (int i = 0; i < serviceArr.length; i++) {
						MultiMatchQuery query = new MultiMatchQuery(SpecialCharacterUtils.splitSpecial(serviceArr[i]));
						query.addField(EFormTerm.SERVICE_CODE_SEARCH);
						subQuery.add(query, BooleanClauseOccur.SHOULD);
					}
					booleanQuery.add(subQuery, BooleanClauseOccur.MUST);
				}
			} else {
				MultiMatchQuery query = new MultiMatchQuery(SpecialCharacterUtils.splitSpecial(serviceCode));
				query.addFields(EFormTerm.SERVICE_CODE_SEARCH);
				booleanQuery.add(query, BooleanClauseOccur.MUST);
			}
		}

		if (Validator.isNotNull(gateNumber)) {
			MultiMatchQuery query = new MultiMatchQuery(gateNumber);

			query.addFields(BookingTerm.GATE_NUMBER);

			booleanQuery.add(query, BooleanClauseOccur.MUST);
		}

		if (Validator.isNotNull(className)) {
			MultiMatchQuery query = new MultiMatchQuery(className);

			query.addFields(BookingTerm.CLASS_NAME);

			booleanQuery.add(query, BooleanClauseOccur.MUST);
		}

		if (Validator.isNotNull(state)) {
			String[] stateArr = StringUtil.split(state);

			if (stateArr != null && stateArr.length > 0) {
				BooleanQuery subQuery = new BooleanQueryImpl();
				for (int i = 0; i < stateArr.length; i++) {
					MultiMatchQuery query = new MultiMatchQuery(stateArr[i]);
					query.addField(EFormTerm.STATE);
					subQuery.add(query, BooleanClauseOccur.SHOULD);
				}
				booleanQuery.add(subQuery, BooleanClauseOccur.MUST);
			} else {
				MultiMatchQuery query = new MultiMatchQuery(state);
				query.addFields(EFormTerm.STATE);
				booleanQuery.add(query, BooleanClauseOccur.MUST);
			}
		}

		String fromCreateDateFilter = from + ConstantsTerm.HOUR_START;
		String toCreateDateFilter = to + ConstantsTerm.HOUR_END;

		if (Validator.isNotNull(from)) {
			if (Validator.isNotNull(to)) {
				TermRangeQueryImpl termRangeQuery = new TermRangeQueryImpl(BookingTerm.CREATE_DATE,
						fromCreateDateFilter, toCreateDateFilter, true, true);

				booleanQuery.add(termRangeQuery, BooleanClauseOccur.MUST);
			} else {
				TermRangeQueryImpl termRangeQuery = new TermRangeQueryImpl(BookingTerm.CREATE_DATE,
						fromCreateDateFilter, toCreateDateFilter, true, false);

				booleanQuery.add(termRangeQuery, BooleanClauseOccur.MUST);
			}
		} else {
			if (Validator.isNotNull(to)) {
				TermRangeQueryImpl termRangeQuery = new TermRangeQueryImpl(BookingTerm.CREATE_DATE,
						fromCreateDateFilter, toCreateDateFilter, false, true);

				booleanQuery.add(termRangeQuery, BooleanClauseOccur.MUST);
			}
		}

		String fromBookingDateFilter = bookingFrom + ConstantsTerm.HOUR_START;
		String toBookingDateFilter = bookingTo + ConstantsTerm.HOUR_END;

		if (Validator.isNotNull(bookingFrom)) {
			if (Validator.isNotNull(bookingTo)) {
				TermRangeQueryImpl termRangeQuery = new TermRangeQueryImpl(BookingTerm.BOOKING_DATE,
						fromBookingDateFilter, toBookingDateFilter, true, true);

				booleanQuery.add(termRangeQuery, BooleanClauseOccur.MUST);
			} else {
				TermRangeQueryImpl termRangeQuery = new TermRangeQueryImpl(BookingTerm.BOOKING_DATE,
						fromBookingDateFilter, toBookingDateFilter, true, false);

				booleanQuery.add(termRangeQuery, BooleanClauseOccur.MUST);
			}
		} else {
			if (Validator.isNotNull(bookingTo)) {
				TermRangeQueryImpl termRangeQuery = new TermRangeQueryImpl(BookingTerm.BOOKING_DATE,
						fromBookingDateFilter, toBookingDateFilter, false, true);

				booleanQuery.add(termRangeQuery, BooleanClauseOccur.MUST);
			}
		}

		booleanQuery.addRequiredTerm(Field.ENTRY_CLASS_NAME, CLASS_NAME);

		return IndexSearcherHelperUtil.search(searchContext, booleanQuery);
	}

	public long countLucene(LinkedHashMap<String, Object> params, SearchContext searchContext)
			throws ParseException, SearchException {

		String keywords = (String) params.get(Field.KEYWORD_SEARCH);
		String groupId = (String) params.get(Field.GROUP_ID);
		String serviceCode = GetterUtil.getString(params.get(EFormTerm.SERVICE_CODE_SEARCH));
		String state = String.valueOf((params.get(EFormTerm.STATE)));
		String from = GetterUtil.getString(params.get(BookingTerm.FROM_CREATE_DATE));
		String to = GetterUtil.getString(params.get(BookingTerm.TO_CREATE_DATE));
		String bookingFrom = GetterUtil.getString(params.get(BookingTerm.FROM_CREATE_DATE));
		String bookingTo = GetterUtil.getString(params.get(BookingTerm.TO_CREATE_DATE));
		String gateNumber = GetterUtil.getString(params.get(BookingTerm.GATE_NUMBER));
		String className = GetterUtil.getString(params.get(BookingTerm.CLASS_NAME));

		Indexer<Booking> indexer = IndexerRegistryUtil.nullSafeGetIndexer(Booking.class);

		searchContext.addFullQueryEntryClassName(CLASS_NAME);
		searchContext.setEntryClassNames(new String[] { CLASS_NAME });
		searchContext.setAttribute("paginationType", "regular");
		searchContext.setLike(true);
		searchContext.setAndSearch(true);

		BooleanQuery booleanQuery = null;

		if (Validator.isNotNull(keywords)) {
			booleanQuery = BooleanQueryFactoryUtil.create(searchContext);
		} else {
			booleanQuery = indexer.getFullQuery(searchContext);
		}

		if (Validator.isNotNull(keywords)) {
			BooleanQuery queryBool = new BooleanQueryImpl();
			String[] subQuerieArr = new String[] { EFormTerm.EFORM_NO_SEARCH, EFormTerm.SERVICE_CODE_SEARCH};

			String[] keywordArr = keywords.split(StringPool.SPACE);
			for (String fieldSearch : subQuerieArr) {
				BooleanQuery query = new BooleanQueryImpl();
				for (String key : keywordArr) {
					WildcardQuery wildQuery = new WildcardQueryImpl(fieldSearch,
							StringPool.STAR + key.toLowerCase() + StringPool.STAR);
					query.add(wildQuery, BooleanClauseOccur.MUST);
				}
				queryBool.add(query, BooleanClauseOccur.SHOULD);
			}
			booleanQuery.add(queryBool, BooleanClauseOccur.MUST);
		}

		if (Validator.isNotNull(groupId)) {
			MultiMatchQuery query = new MultiMatchQuery(groupId);

			query.addFields(Field.GROUP_ID);

			booleanQuery.add(query, BooleanClauseOccur.MUST);
		}

		if (Validator.isNotNull(serviceCode)) {
			if (serviceCode.contains(StringPool.COMMA)) {
				String[] serviceArr = StringUtil.split(serviceCode);
				if (serviceArr != null && serviceArr.length > 0) {
					BooleanQuery subQuery = new BooleanQueryImpl();
					for (int i = 0; i < serviceArr.length; i++) {
						MultiMatchQuery query = new MultiMatchQuery(SpecialCharacterUtils.splitSpecial(serviceArr[i]));
						query.addField(EFormTerm.SERVICE_CODE_SEARCH);
						subQuery.add(query, BooleanClauseOccur.SHOULD);
					}
					booleanQuery.add(subQuery, BooleanClauseOccur.MUST);
				}
			} else {
				MultiMatchQuery query = new MultiMatchQuery(SpecialCharacterUtils.splitSpecial(serviceCode));
				query.addFields(EFormTerm.SERVICE_CODE_SEARCH);
				booleanQuery.add(query, BooleanClauseOccur.MUST);
			}
		}

		if (Validator.isNotNull(gateNumber)) {
			MultiMatchQuery query = new MultiMatchQuery(gateNumber);

			query.addFields(BookingTerm.GATE_NUMBER);

			booleanQuery.add(query, BooleanClauseOccur.MUST);
		}

		if (Validator.isNotNull(className)) {
			MultiMatchQuery query = new MultiMatchQuery(className);

			query.addFields(BookingTerm.CLASS_NAME);

			booleanQuery.add(query, BooleanClauseOccur.MUST);
		}

		if (Validator.isNotNull(state)) {
			String[] stateArr = StringUtil.split(state);

			if (stateArr != null && stateArr.length > 0) {
				BooleanQuery subQuery = new BooleanQueryImpl();
				for (int i = 0; i < stateArr.length; i++) {
					MultiMatchQuery query = new MultiMatchQuery(stateArr[i]);
					query.addField(EFormTerm.STATE);
					subQuery.add(query, BooleanClauseOccur.SHOULD);
				}
				booleanQuery.add(subQuery, BooleanClauseOccur.MUST);
			} else {
				MultiMatchQuery query = new MultiMatchQuery(state);
				query.addFields(EFormTerm.STATE);
				booleanQuery.add(query, BooleanClauseOccur.MUST);
			}
		}

		String fromCreateDateFilter = from + ConstantsTerm.HOUR_START;
		String toCreateDateFilter = to + ConstantsTerm.HOUR_END;

		if (Validator.isNotNull(from)) {
			if (Validator.isNotNull(to)) {
				TermRangeQueryImpl termRangeQuery = new TermRangeQueryImpl(BookingTerm.CREATE_DATE,
						fromCreateDateFilter, toCreateDateFilter, true, true);

				booleanQuery.add(termRangeQuery, BooleanClauseOccur.MUST);
			} else {
				TermRangeQueryImpl termRangeQuery = new TermRangeQueryImpl(BookingTerm.CREATE_DATE,
						fromCreateDateFilter, toCreateDateFilter, true, false);

				booleanQuery.add(termRangeQuery, BooleanClauseOccur.MUST);
			}
		} else {
			if (Validator.isNotNull(to)) {
				TermRangeQueryImpl termRangeQuery = new TermRangeQueryImpl(BookingTerm.CREATE_DATE,
						fromCreateDateFilter, toCreateDateFilter, false, true);

				booleanQuery.add(termRangeQuery, BooleanClauseOccur.MUST);
			}
		}

		String fromBookingDateFilter = bookingFrom + ConstantsTerm.HOUR_START;
		String toBookingDateFilter = bookingTo + ConstantsTerm.HOUR_END;

		if (Validator.isNotNull(bookingFrom)) {
			if (Validator.isNotNull(bookingTo)) {
				TermRangeQueryImpl termRangeQuery = new TermRangeQueryImpl(BookingTerm.BOOKING_DATE,
						fromBookingDateFilter, toBookingDateFilter, true, true);

				booleanQuery.add(termRangeQuery, BooleanClauseOccur.MUST);
			} else {
				TermRangeQueryImpl termRangeQuery = new TermRangeQueryImpl(BookingTerm.BOOKING_DATE,
						fromBookingDateFilter, toBookingDateFilter, true, false);

				booleanQuery.add(termRangeQuery, BooleanClauseOccur.MUST);
			}
		} else {
			if (Validator.isNotNull(bookingTo)) {
				TermRangeQueryImpl termRangeQuery = new TermRangeQueryImpl(BookingTerm.BOOKING_DATE,
						fromBookingDateFilter, toBookingDateFilter, false, true);

				booleanQuery.add(termRangeQuery, BooleanClauseOccur.MUST);
			}
		}

		booleanQuery.addRequiredTerm(Field.ENTRY_CLASS_NAME, CLASS_NAME);

		return IndexSearcherHelperUtil.searchCount(searchContext, booleanQuery);
	}

	@Indexable(type = IndexableType.REINDEX)
	public Booking updateBooking(long userId, long groupId, long bookingId, String className, long classPK,
			String serviceCode, String codeNumber, String bookingName, String gateNumber, Integer state,
			Date checkinDate, Date bookingDate, boolean speaking, ServiceContext serviceContext) {

		Date now = new Date();

		if (bookingId > 0) {
			Booking booking = bookingPersistence.fetchByPrimaryKey(bookingId);
			if (booking.getState() != 4) {
				//
				booking.setModifiedDate(now);

				if (Validator.isNotNull(className))
					booking.setClassName(className);
				if (Validator.isNotNull(classPK))
					booking.setClassPK(classPK);
				if (Validator.isNotNull(serviceCode))
					booking.setServiceCode(serviceCode);
				if (Validator.isNotNull(codeNumber))
					booking.setCodeNumber(codeNumber);
				if (Validator.isNotNull(bookingName))
					booking.setBookingName(bookingName);
				if (Validator.isNotNull(checkinDate))
					booking.setCheckinDate(checkinDate);
				if (Validator.isNotNull(gateNumber))
					booking.setGateNumber(gateNumber);
				if (Validator.isNotNull(state))
					booking.setState(state);
				if (Validator.isNotNull(bookingDate))
					booking.setBookingDate(bookingDate);
				booking.setSpeaking(speaking);
			}
			//
			return bookingPersistence.update(booking);
		} else {
			bookingId = counterLocalService.increment(Booking.class.getName());
			Booking booking = bookingPersistence.create(bookingId);
			//
			booking.setCreateDate(now);
			booking.setModifiedDate(now);
			booking.setCompanyId(serviceContext.getCompanyId());
			booking.setGroupId(groupId);
			booking.setUserId(userId);
			//
			booking.setClassName(className);
			booking.setClassPK(classPK);
			booking.setServiceCode(serviceCode);
			booking.setCodeNumber(codeNumber);
			booking.setBookingName(bookingName);
			booking.setCheckinDate(checkinDate);
			booking.setGateNumber(gateNumber);
			booking.setState(state);
			booking.setBookingDate(bookingDate);
			booking.setSpeaking(speaking);

			return bookingPersistence.update(booking);
		}
	}

	public Booking getByClassName_PK(String className, long classPK) {
		return bookingPersistence.fetchByF_CLASS_NAME_PK(className, classPK);
	}
}