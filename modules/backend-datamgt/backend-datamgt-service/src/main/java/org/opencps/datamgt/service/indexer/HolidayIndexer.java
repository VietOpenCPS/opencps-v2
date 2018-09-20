package org.opencps.datamgt.service.indexer;

import com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.search.BaseIndexer;
import com.liferay.portal.kernel.search.BooleanQuery;
import com.liferay.portal.kernel.search.Document;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.search.IndexWriterHelperUtil;
import com.liferay.portal.kernel.search.SearchContext;
import com.liferay.portal.kernel.search.Summary;
import com.liferay.portal.kernel.search.filter.BooleanFilter;
import com.liferay.portal.kernel.util.GetterUtil;

import java.util.Calendar;
import java.util.Locale;

import javax.portlet.PortletRequest;
import javax.portlet.PortletResponse;

import org.opencps.datamgt.constants.HolidayTerm;
import org.opencps.datamgt.model.Holiday;
import org.opencps.datamgt.service.HolidayLocalServiceUtil;
import org.osgi.service.component.annotations.Component;

@Component(
    immediate = true,
    service = BaseIndexer.class
)
public class HolidayIndexer extends BaseIndexer<Holiday> {

	public static final String CLASS_NAME = Holiday.class.getName();

	@Override
	public String getClassName() {
		return CLASS_NAME;
	}

	@Override
	public void postProcessSearchQuery(BooleanQuery searchQuery, BooleanFilter fullQueryBooleanFilter,
			SearchContext searchContext) throws Exception {

	}

	@Override
	protected void doDelete(Holiday holiday) throws Exception {
		deleteDocument(holiday.getCompanyId(), holiday.getHolidayId());
	}

	@Override
	protected Document doGetDocument(Holiday holiday) throws Exception {
		Document document = getBaseModelDocument(CLASS_NAME, holiday);

		document.addKeywordSortable(Field.COMPANY_ID, String.valueOf(holiday.getCompanyId()));
		document.addDateSortable(Field.MODIFIED_DATE, holiday.getModifiedDate());
		document.addKeywordSortable(Field.USER_ID, String.valueOf(holiday.getUserId()));
		document.addKeywordSortable(Field.USER_NAME, String.valueOf(holiday.getUserName()));

		document.addNumberSortable(HolidayTerm.HOLIDAY_ID, holiday.getHolidayId());
		document.addDateSortable(HolidayTerm.HOLIDAY_DATE, holiday.getHolidayDate());
		
		document.addNumberSortable(HolidayTerm.GROUP_ID, holiday.getGroupId());
		document.addTextSortable(HolidayTerm.DESCRIPTION, holiday.getDescription());
		
		Calendar cal = Calendar.getInstance();
		cal.setTime(holiday.getHolidayDate());
		
		String dateTime = String.valueOf(cal.get(Calendar.YEAR));
		
		document.addTextSortable("year", dateTime);
		
		document.setSortableTextFields(
				new String[] { HolidayTerm.CREATE_DATE});

		return document;
	}

	@Override
	protected Summary doGetSummary(Document document, Locale locale, String snippet, PortletRequest portletRequest,
			PortletResponse portletResponse) {

		Summary summary = createSummary(document);

		summary.setMaxContentLength(QueryUtil.ALL_POS);

		return summary;
	}

	@Override
	protected void doReindex(Holiday holiday) throws Exception {
		Document document = getDocument(holiday);

		IndexWriterHelperUtil.updateDocument(getSearchEngineId(), holiday.getCompanyId(), document,
				isCommitImmediately());
	}

	@Override
	protected void doReindex(String className, long classPK) throws Exception {
		Holiday holiday = HolidayLocalServiceUtil.fetchHoliday(classPK);

		doReindex(holiday);
	}

	@Override
	protected void doReindex(String[] ids) throws Exception {
		long companyId = GetterUtil.getLong(ids[0]);

		reindexMHoliday(companyId);
	}

	protected void reindexMHoliday(long companyId) throws PortalException {
		final IndexableActionableDynamicQuery indexableActionableDynamicQuery = HolidayLocalServiceUtil
				.getIndexableActionableDynamicQuery();

		indexableActionableDynamicQuery.setCompanyId(companyId);
		indexableActionableDynamicQuery
				.setPerformActionMethod(new ActionableDynamicQuery.PerformActionMethod<Holiday>() {

					@Override
					public void performAction(Holiday holiday) {
						try {
							Document document = getDocument(holiday);

							indexableActionableDynamicQuery.addDocuments(document);
						} catch (PortalException pe) {
							if (_log.isWarnEnabled()) {
								_log.warn("Unable to index Holiday " + holiday.getHolidayId(), pe);
							}
						}
					}

				});
		indexableActionableDynamicQuery.setSearchEngineId(getSearchEngineId());

		indexableActionableDynamicQuery.performActions();
	}

	private static final Log _log = LogFactoryUtil.getLog(HolidayIndexer.class);

}
