package org.opencps.api.controller.util;

import com.liferay.portal.kernel.search.Document;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.Validator;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.opencps.api.booking.model.BookingDataModel;
import org.opencps.auth.utils.APIDateTimeUtils;
import org.opencps.dossiermgt.constants.BookingTerm;
import org.opencps.dossiermgt.constants.EFormTerm;
import org.opencps.dossiermgt.model.Booking;

public class BookingUtils {

	public static List<BookingDataModel> mappingForGetList(List<Document> docs) {
		List<BookingDataModel> ouputs = new ArrayList<BookingDataModel>();

		if (docs != null && docs.size() > 0) {
			for (Document doc : docs) {
				BookingDataModel model = new BookingDataModel();
				
				model.setBookingId(GetterUtil.getLong(doc.get(BookingTerm.BOOKING_ID)));
				
				if (Validator.isNotNull(doc.get(Field.CREATE_DATE))) {
					Date createDate = APIDateTimeUtils.convertStringToDate(doc.get(Field.CREATE_DATE),
							APIDateTimeUtils._LUCENE_PATTERN);
					model.setCreateDate(createDate.getTime());
				} else {
					model.setCreateDate(0l);
				}
				if (Validator.isNotNull(doc.get(Field.MODIFIED_DATE))) {
					Date modifiedDate = APIDateTimeUtils.convertStringToDate(doc.get(Field.MODIFIED_DATE),
							APIDateTimeUtils._LUCENE_PATTERN);
					model.setModifiedDate(modifiedDate.getTime());
				} else {
					model.setModifiedDate(0l);
				}
				
				model.setClassName(doc.get(BookingTerm.CLASS_NAME));
				model.setClassPK(Long.valueOf(doc.get(BookingTerm.CLASS_PK)));
				model.setServiceCode(doc.get(BookingTerm.SERVICE_CODE));
				model.setCodeNumber(doc.get(BookingTerm.CODE_NUMBER));
				model.setBookingName(doc.get(BookingTerm.BOOKING_NAME));
				if (Validator.isNotNull(doc.get(BookingTerm.CHECK_IN_DATE))) {
					Date checkInDate = APIDateTimeUtils.convertStringToDate(doc.get(BookingTerm.CHECK_IN_DATE_LUCENE),
							APIDateTimeUtils._LUCENE_PATTERN);
					model.setCheckinDate(checkInDate.getTime());
				} else {
					model.setCheckinDate(0l);
				}
				model.setGateNumber(doc.get(EFormTerm.GATE_NUMBER));
				model.setState(GetterUtil.getInteger(doc.get(EFormTerm.STATE)));
				if (Validator.isNotNull(doc.get(BookingTerm.BOOKING_DATE))) {
					Date bookingDate = APIDateTimeUtils.convertStringToDate(doc.get(BookingTerm.CHECK_IN_DATE_LUCENE),
							APIDateTimeUtils._LUCENE_PATTERN);
					model.setBookingDate(bookingDate.getTime());
				} else {
					model.setBookingDate(0l);
				}
				model.setSpeaking(Boolean.valueOf(doc.get(BookingTerm.SPEAKING)));

				ouputs.add(model);
			}
		}
		return ouputs;
	}

	public static BookingDataModel mappingForGetDetail(Booking input) {

		BookingDataModel model = new BookingDataModel();

		if (Validator.isNull(input)) {
			return model;
		}

		model.setBookingId(input.getBookingId());
		model.setCreateDate(input.getCreateDate() != null ? input.getCreateDate().getTime() : 0l);
		model.setModifiedDate(input.getModifiedDate() != null ? input.getModifiedDate().getTime() : 0l);
		model.setClassName(input.getClassName());
		model.setClassPK(input.getClassPK());
		model.setServiceCode(input.getServiceCode());
		model.setCodeNumber(input.getCodeNumber());
		model.setBookingName(input.getBookingName());
		model.setCheckinDate(input.getCheckinDate() != null ? input.getCheckinDate().getTime() : 0l);
		model.setGateNumber(input.getGateNumber());
		model.setState(input.getState());
		model.setBookingDate(input.getBookingDate() != null ? input.getBookingDate().getTime() : 0l);
		model.setSpeaking(input.getSpeaking());

		return model;
	}
}
