package org.opencps.usermgt.service.indexer;

import java.util.LinkedHashMap;
import java.util.Locale;

import javax.portlet.PortletRequest;
import javax.portlet.PortletResponse;

import org.opencps.usermgt.constants.JobPosWorkTerm;
import org.opencps.usermgt.model.JobPosWork;
import org.opencps.usermgt.service.JobPosWorkLocalServiceUtil;
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
import com.liferay.portal.kernel.util.Validator;

@Component(
    immediate = true,
    service = BaseIndexer.class
)
public class JobPosWorkIndexer extends BaseIndexer<JobPosWork> {

	public static final String CLASS_NAME = JobPosWork.class.getName();

	@Override
	public String getClassName() {
		return CLASS_NAME;
	}

	@Override
	public void postProcessSearchQuery(BooleanQuery searchQuery, BooleanFilter fullQueryBooleanFilter,
			SearchContext searchContext) throws Exception {

		@SuppressWarnings("unchecked")
		LinkedHashMap<String, Object> params = (LinkedHashMap<String, Object>) searchContext.getAttribute("params");

		if (params != null) {
			String expandoAttributes = (String) params.get("expandoAttributes");

			if (Validator.isNotNull(expandoAttributes)) {
				addSearchExpando(searchQuery, searchContext, expandoAttributes);
			}
		}
	}

	@Override
	protected void doDelete(JobPosWork jobPosWork) throws Exception {
		deleteDocument(jobPosWork.getCompanyId(), jobPosWork.getJobPosWorkId());
	}

	@Override
	protected Document doGetDocument(JobPosWork jobPosWork) throws Exception {
		Document document = getBaseModelDocument(CLASS_NAME, jobPosWork);

		document.addKeywordSortable(Field.COMPANY_ID, String.valueOf(jobPosWork.getCompanyId()));
		document.addDateSortable(Field.MODIFIED_DATE, jobPosWork.getModifiedDate());
		document.addKeywordSortable(Field.USER_ID, String.valueOf(jobPosWork.getUserId()));
		document.addKeywordSortable(Field.USER_NAME, String.valueOf(jobPosWork.getUserName()));
		
		document.addNumberSortable(JobPosWorkTerm.GROUP_ID, jobPosWork.getGroupId());
		document.addNumberSortable(JobPosWorkTerm.JOBPOS_ID, jobPosWork.getJobPostId());
		document.addTextSortable(JobPosWorkTerm.CHECKLIST_CAT, jobPosWork.getChecklistCat());
		
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
	protected void doReindex(JobPosWork jobPosWork) throws Exception {
		Document document = getDocument(jobPosWork);
		IndexWriterHelperUtil.updateDocument(getSearchEngineId(), jobPosWork.getCompanyId(), document,
				isCommitImmediately());
	}

	@Override
	protected void doReindex(String className, long classPK) throws Exception {
		JobPosWork jobPosWork = JobPosWorkLocalServiceUtil.getJobPosWork(classPK);
		doReindex(jobPosWork);
	}

	@Override
	protected void doReindex(String[] ids) throws Exception {
		long companyId = GetterUtil.getLong(ids[0]);
		reindexJobPosWork(companyId);
	}

	protected void reindexJobPosWork(long companyId) throws PortalException {
		final IndexableActionableDynamicQuery indexableActionableDynamicQuery = JobPosWorkLocalServiceUtil
				.getIndexableActionableDynamicQuery();

		indexableActionableDynamicQuery.setCompanyId(companyId);
		indexableActionableDynamicQuery
				.setPerformActionMethod(new ActionableDynamicQuery.PerformActionMethod<JobPosWork>() {

					@Override
					public void performAction(JobPosWork jobPosWork) {
						try {
							Document document = getDocument(jobPosWork);

							indexableActionableDynamicQuery.addDocuments(document);
						} catch (PortalException pe) {
							if (_log.isWarnEnabled()) {
								_log.warn("Unable to index contact " + jobPosWork.getJobPosWorkId(), pe);
							}
						}
					}

				});
		indexableActionableDynamicQuery.setSearchEngineId(getSearchEngineId());

		indexableActionableDynamicQuery.performActions();
	}

	private static final Log _log = LogFactoryUtil.getLog(JobPosWorkIndexer.class);

}