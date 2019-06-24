
package org.opencps.dossiermgt.service.indexer;

import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.search.BaseIndexer;
import com.liferay.portal.kernel.search.Document;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.search.IndexWriterHelperUtil;
import com.liferay.portal.kernel.search.Summary;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.Validator;

import java.util.Locale;

import javax.portlet.PortletRequest;
import javax.portlet.PortletResponse;

import org.opencps.auth.utils.APIDateTimeUtils;
import org.opencps.dossiermgt.action.util.SpecialCharacterUtils;
import org.opencps.dossiermgt.constants.BookingTerm;
import org.opencps.dossiermgt.constants.EFormTerm;
import org.opencps.dossiermgt.model.Booking;
import org.opencps.dossiermgt.service.BookingLocalServiceUtil;
import org.osgi.service.component.annotations.Component;

@Component(
    immediate = true,
    service = BaseIndexer.class
)
public class BookingIndexer extends BaseIndexer<Booking> {
	public static final String CLASS_NAME = Booking.class.getName();

	@Override
	public String getClassName() {
		return CLASS_NAME;
	}

	@Override
	protected void doDelete(Booking object) throws Exception {
		deleteDocument(object.getCompanyId(), object.getPrimaryKey());
	}

	@Override
	protected Document doGetDocument(Booking object) throws Exception {
		Document document = getBaseModelDocument(CLASS_NAME, object);

		try {
			// Indexer of audit fields
			document.addNumberSortable(Field.COMPANY_ID, object.getCompanyId());
			document.addNumberSortable(Field.GROUP_ID, object.getGroupId());
			document.addDateSortable(Field.CREATE_DATE, object.getCreateDate());
			document.addDateSortable(Field.MODIFIED_DATE, object.getModifiedDate());
			document.addNumberSortable(Field.USER_ID, object.getUserId());
			if (Validator.isNotNull(object.getUserName())) {
				document.addKeywordSortable(Field.USER_NAME, String.valueOf(object.getUserName()));
			}
			document.addKeywordSortable(Field.ENTRY_CLASS_NAME, CLASS_NAME);
			document.addNumberSortable(Field.ENTRY_CLASS_PK, object.getPrimaryKey());

			// add number fields
			document.addNumber(BookingTerm.BOOKING_ID, object.getBookingId());
			document.addNumber(BookingTerm.CLASS_PK, object.getClassPK());
			document.addNumber(BookingTerm.STATE, object.getState());

			// add text fields

			document.addTextSortable(BookingTerm.SERVICE_CODE, object.getServiceCode());
			if (Validator.isNotNull(object.getServiceCode())) {
				document.addTextSortable(BookingTerm.SERVICE_CODE_SEARCH,
						SpecialCharacterUtils.splitSpecial(object.getServiceCode()));
			}
			document.addTextSortable(BookingTerm.CLASS_NAME, object.getClassName());
			document.addTextSortable(BookingTerm.BOOKING_NAME, object.getBookingName());
			document.addTextSortable(BookingTerm.CODE_NUMBER, object.getCodeNumber());
			document.addTextSortable(BookingTerm.GATE_NUMBER, object.getGateNumber());
//
			if (Validator.isNotNull(object.getCheckinDate())) {
				document.addTextSortable(BookingTerm.CHECK_IN_DATE, APIDateTimeUtils
						.convertDateToString(object.getCheckinDate(), APIDateTimeUtils._NORMAL_PARTTERN));
			} else {
				document.addTextSortable(BookingTerm.CHECK_IN_DATE, StringPool.BLANK);
			}
//
			if (Validator.isNotNull(object.getCheckinDate())) {
				document.addDateSortable(BookingTerm.CHECK_IN_DATE_LUCENE, object.getCheckinDate());
			} else {
				document.addTextSortable(BookingTerm.CHECK_IN_DATE_LUCENE, StringPool.BLANK);
			}

			if (Validator.isNotNull(object.getBookingDate())) {
				document.addTextSortable(BookingTerm.BOOKING_DATE, APIDateTimeUtils
						.convertDateToString(object.getBookingDate(), APIDateTimeUtils._NORMAL_PARTTERN));
			} else {
				document.addTextSortable(BookingTerm.BOOKING_DATE, StringPool.BLANK);
			}
//
			if (Validator.isNotNull(object.getBookingDate())) {
				document.addDateSortable(BookingTerm.BOOKING_DATE_LUCENE, object.getBookingDate());
			} else {
				document.addTextSortable(BookingTerm.BOOKING_DATE_LUCENE, StringPool.BLANK);
			}

			document.addTextSortable(BookingTerm.SPEAKING, String.valueOf(object.getSpeaking()));

		} catch (Exception e) {
			_log.error(e);
		}

		return document;
	}

	@Override
	protected Summary doGetSummary(Document document, Locale locale, String snippet, PortletRequest portletRequest,
			PortletResponse portletResponse) throws Exception {
		Summary summary = createSummary(document);

		summary.setMaxContentLength(QueryUtil.ALL_POS);

		return summary;
	}

	@Override
	protected void doReindex(String className, long classPK) throws Exception {
		Booking object = BookingLocalServiceUtil.getBooking(classPK);
		doReindex(object);
	}

	@Override
	protected void doReindex(String[] ids) throws Exception {
		long companyId = GetterUtil.getLong(ids[0]);
		reindex(companyId);
	}

	@Override
	protected void doReindex(Booking object) throws Exception {
		Document document = getDocument(object);
		IndexWriterHelperUtil.updateDocument(getSearchEngineId(), object.getCompanyId(), document,
				isCommitImmediately());

	}

	protected void reindex(long companyId) throws PortalException {
		final IndexableActionableDynamicQuery indexableActionableDynamicQuery = BookingLocalServiceUtil
				.getIndexableActionableDynamicQuery();

		indexableActionableDynamicQuery.setCompanyId(companyId);
		indexableActionableDynamicQuery
				.setPerformActionMethod(new ActionableDynamicQuery.PerformActionMethod<Booking>() {

					@Override
					public void performAction(Booking object) {
						try {
							Document document = getDocument(object);

							indexableActionableDynamicQuery.addDocuments(document);
						} catch (PortalException pe) {
							if (_log.isWarnEnabled()) {
								_log.warn("Unable to index contact " + object.getPrimaryKey(), pe);
							}
						}
					}

				});
		indexableActionableDynamicQuery.setSearchEngineId(getSearchEngineId());

		indexableActionableDynamicQuery.performActions();
	}

	Log _log = LogFactoryUtil.getLog(BookingIndexer.class);

}
