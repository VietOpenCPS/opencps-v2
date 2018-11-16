package org.opencps.usermgt.service.indexer;

import java.util.LinkedHashMap;
import java.util.Locale;

import javax.portlet.PortletRequest;
import javax.portlet.PortletResponse;

import org.opencps.usermgt.constants.JobPosTerm;
import org.opencps.usermgt.model.JobPos;
import org.opencps.usermgt.service.JobPosLocalServiceUtil;
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
public class JobPosIndexer extends BaseIndexer<JobPos> {

	public static final String CLASS_NAME = JobPos.class.getName();

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
	protected void doDelete(JobPos jobPos) throws Exception {
		deleteDocument(jobPos.getCompanyId(), jobPos.getJobPosId());
	}

	@Override
	protected Document doGetDocument(JobPos jobPos) throws Exception {
		Document document = getBaseModelDocument(CLASS_NAME, jobPos);

		document.addKeywordSortable(Field.COMPANY_ID, String.valueOf(jobPos.getCompanyId()));
		document.addDateSortable(Field.MODIFIED_DATE, jobPos.getModifiedDate());
		document.addKeywordSortable(Field.USER_ID, String.valueOf(jobPos.getUserId()));
		document.addKeywordSortable(Field.USER_NAME, String.valueOf(jobPos.getUserName()));
		
		document.addNumberSortable(JobPosTerm.GROUP_ID, jobPos.getGroupId());
		document.addNumberSortable(JobPosTerm.JOBPOS_ID, jobPos.getJobPosId());
		document.addTextSortable(JobPosTerm.TITLE, jobPos.getTitle());
		document.addTextSortable(JobPosTerm.DESCRIPTION, jobPos.getDescription());
		document.addNumberSortable(JobPosTerm.MAPPING_ROLE_ID, jobPos.getMappingRoleId());
		document.addNumberSortable(JobPosTerm.LEADER, jobPos.getLeader());
		document.addTextSortable(JobPosTerm.JOBPOS_CODE, jobPos.getJobPosCode());
		
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
	protected void doReindex(JobPos jobPos) throws Exception {
		Document document = getDocument(jobPos);
		IndexWriterHelperUtil.updateDocument(getSearchEngineId(), jobPos.getCompanyId(), document,
				isCommitImmediately());
	}

	@Override
	protected void doReindex(String className, long classPK) throws Exception {
		JobPos jobPos = JobPosLocalServiceUtil.getJobPos(classPK);
		doReindex(jobPos);
	}

	@Override
	protected void doReindex(String[] ids) throws Exception {
		long companyId = GetterUtil.getLong(ids[0]);
		reindexJobPos(companyId);
	}

	protected void reindexJobPos(long companyId) throws PortalException {
		final IndexableActionableDynamicQuery indexableActionableDynamicQuery = JobPosLocalServiceUtil
				.getIndexableActionableDynamicQuery();

		indexableActionableDynamicQuery.setCompanyId(companyId);
		indexableActionableDynamicQuery
				.setPerformActionMethod(new ActionableDynamicQuery.PerformActionMethod<JobPos>() {

					@Override
					public void performAction(JobPos jobPos) {
						try {
							Document document = getDocument(jobPos);

							indexableActionableDynamicQuery.addDocuments(document);
						} catch (PortalException pe) {
							if (_log.isWarnEnabled()) {
								_log.warn("Unable to index contact " + jobPos.getJobPosId(), pe);
							}
						}
					}

				});
		indexableActionableDynamicQuery.setSearchEngineId(getSearchEngineId());

		indexableActionableDynamicQuery.performActions();
	}

	private static final Log _log = LogFactoryUtil.getLog(JobPosIndexer.class);

}