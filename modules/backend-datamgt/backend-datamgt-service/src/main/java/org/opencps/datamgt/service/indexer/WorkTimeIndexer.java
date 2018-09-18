package org.opencps.datamgt.service.indexer;

import java.util.Locale;

import javax.portlet.PortletRequest;
import javax.portlet.PortletResponse;

import org.opencps.datamgt.constants.WorkTimeTerm;
import org.opencps.datamgt.model.WorkTime;
import org.opencps.datamgt.service.WorkTimeLocalServiceUtil;
import org.osgi.service.component.annotations.Component;

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

@Component(
    immediate = true,
    service = BaseIndexer.class
)
public class WorkTimeIndexer extends BaseIndexer<WorkTime> {

	public static final String CLASS_NAME = WorkTime.class.getName();

	@Override
	public String getClassName() {
		return CLASS_NAME;
	}

	@Override
	public void postProcessSearchQuery(BooleanQuery searchQuery, BooleanFilter fullQueryBooleanFilter,
			SearchContext searchContext) throws Exception {

	}

	@Override
	protected void doDelete(WorkTime workTime) throws Exception {
		deleteDocument(workTime.getCompanyId(), workTime.getWorkTimeId());
	}

	@Override
	protected Document doGetDocument(WorkTime workTime) throws Exception {
		Document document = getBaseModelDocument(CLASS_NAME, workTime);

		document.addKeywordSortable(Field.COMPANY_ID, String.valueOf(workTime.getCompanyId()));
		document.addDateSortable(Field.MODIFIED_DATE, workTime.getModifiedDate());
		document.addKeywordSortable(Field.USER_ID, String.valueOf(workTime.getUserId()));
		document.addKeywordSortable(Field.USER_NAME, String.valueOf(workTime.getUserName()));

		document.addNumberSortable(WorkTimeTerm.WORKTIME_ID, workTime.getWorkTimeId());
		document.addNumberSortable(WorkTimeTerm.DAY, workTime.getDay());
		
		document.addNumberSortable(WorkTimeTerm.GROUP_ID, workTime.getGroupId());
		document.addTextSortable(WorkTimeTerm.HOURS, workTime.getHours());
		
		document.setSortableTextFields(
				new String[] { WorkTimeTerm.CREATE_DATE });

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
	protected void doReindex(WorkTime workTime) throws Exception {
		Document document = getDocument(workTime);

		IndexWriterHelperUtil.updateDocument(getSearchEngineId(), workTime.getCompanyId(), document,
				isCommitImmediately());
	}

	@Override
	protected void doReindex(String className, long classPK) throws Exception {
		WorkTime workTime = WorkTimeLocalServiceUtil.fetchWorkTime(classPK);

		doReindex(workTime);
	}

	@Override
	protected void doReindex(String[] ids) throws Exception {
		long companyId = GetterUtil.getLong(ids[0]);

		reindexMWorkTime(companyId);
	}

	protected void reindexMWorkTime(long companyId) throws PortalException {
		final IndexableActionableDynamicQuery indexableActionableDynamicQuery = WorkTimeLocalServiceUtil
				.getIndexableActionableDynamicQuery();

		indexableActionableDynamicQuery.setCompanyId(companyId);
		indexableActionableDynamicQuery
				.setPerformActionMethod(new ActionableDynamicQuery.PerformActionMethod<WorkTime>() {

					@Override
					public void performAction(WorkTime workTime) {
						try {
							Document document = getDocument(workTime);

							indexableActionableDynamicQuery.addDocuments(document);
						} catch (PortalException pe) {
							if (_log.isWarnEnabled()) {
								_log.warn("Unable to index WorkTime " + workTime.getWorkTimeId(), pe);
							}
						}
					}

				});
		indexableActionableDynamicQuery.setSearchEngineId(getSearchEngineId());

		indexableActionableDynamicQuery.performActions();
	}

	private static final Log _log = LogFactoryUtil.getLog(WorkTimeIndexer.class);

}
