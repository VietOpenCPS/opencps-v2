package org.opencps.usermgt.service.indexer;

import com.liferay.petra.string.StringPool;
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

import java.util.LinkedHashMap;
import java.util.Locale;

import javax.portlet.PortletRequest;
import javax.portlet.PortletResponse;

import org.opencps.usermgt.constants.EmployeeJobPosTerm;
import org.opencps.usermgt.model.EmployeeJobPos;
import org.opencps.usermgt.model.JobPos;
import org.opencps.usermgt.model.WorkingUnit;
import org.opencps.usermgt.service.EmployeeJobPosLocalServiceUtil;
import org.opencps.usermgt.service.JobPosLocalServiceUtil;
import org.opencps.usermgt.service.WorkingUnitLocalServiceUtil;
import org.osgi.service.component.annotations.Component;

@Component(
    immediate = true,
    service = BaseIndexer.class
)
public class EmployeeJobPosIndexer extends BaseIndexer<EmployeeJobPos> {

	public static final String CLASS_NAME = EmployeeJobPos.class.getName();

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
	protected void doDelete(EmployeeJobPos employeeJobPos) throws Exception {
		deleteDocument(employeeJobPos.getCompanyId(), employeeJobPos.getEmployeeJobPosId());
	}

	@Override
	protected Document doGetDocument(EmployeeJobPos employeeJobPos) throws Exception {
		Document document = getBaseModelDocument(CLASS_NAME, employeeJobPos);

		document.addKeywordSortable(Field.COMPANY_ID, String.valueOf(employeeJobPos.getCompanyId()));
		document.addDateSortable(Field.MODIFIED_DATE, employeeJobPos.getModifiedDate());
		document.addKeywordSortable(Field.USER_ID, String.valueOf(employeeJobPos.getUserId()));
		document.addKeywordSortable(Field.USER_NAME, String.valueOf(employeeJobPos.getUserName()));
		
		document.addNumberSortable(EmployeeJobPosTerm.GROUP_ID, employeeJobPos.getGroupId());
		document.addNumberSortable(EmployeeJobPosTerm.EMPLOYEE_JOBPOST_ID, employeeJobPos.getEmployeeJobPosId());
		document.addNumberSortable(EmployeeJobPosTerm.EMPLOYEE_ID, employeeJobPos.getEmployeeId());
		document.addNumberSortable(EmployeeJobPosTerm.JOBPOST_ID, employeeJobPos.getJobPostId());
		document.addNumberSortable(EmployeeJobPosTerm.WORKING_UNIT_ID, employeeJobPos.getWorkingUnitId());
		
		WorkingUnit workingUnit = WorkingUnitLocalServiceUtil.fetchWorkingUnit(employeeJobPos.getWorkingUnitId());
		
		document.addTextSortable(EmployeeJobPosTerm.WORKING_UNIT_NAME, Validator.isNotNull(workingUnit)?workingUnit.getName():StringPool.BLANK);
		
		String title = StringPool.BLANK;
		int leader = 0;
		
		JobPos jobPos = JobPosLocalServiceUtil.fetchJobPos(employeeJobPos.getJobPostId());
		
		if(Validator.isNotNull(jobPos)){
			title = jobPos.getTitle();
			leader = jobPos.getLeader();
		}
		
		document.addTextSortable(EmployeeJobPosTerm.JOBPOST_TITLE, title);
		document.addNumberSortable(EmployeeJobPosTerm.LEADER, leader);
		
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
	protected void doReindex(EmployeeJobPos employeeJobPos) throws Exception {
		Document document = getDocument(employeeJobPos);
		IndexWriterHelperUtil.updateDocument(getSearchEngineId(), employeeJobPos.getCompanyId(), document,
				isCommitImmediately());
	}

	@Override
	protected void doReindex(String className, long classPK) throws Exception {
		EmployeeJobPos employeeJobPos = EmployeeJobPosLocalServiceUtil.getEmployeeJobPos(classPK);
		doReindex(employeeJobPos);
	}

	@Override
	protected void doReindex(String[] ids) throws Exception {
		long companyId = GetterUtil.getLong(ids[0]);
		reindexEmployeeJobPos(companyId);
	}

	protected void reindexEmployeeJobPos(long companyId) throws PortalException {
		final IndexableActionableDynamicQuery indexableActionableDynamicQuery = EmployeeJobPosLocalServiceUtil
				.getIndexableActionableDynamicQuery();

		indexableActionableDynamicQuery.setCompanyId(companyId);
		indexableActionableDynamicQuery
				.setPerformActionMethod(new ActionableDynamicQuery.PerformActionMethod<EmployeeJobPos>() {

					@Override
					public void performAction(EmployeeJobPos employeeJobPos) {
						try {
							Document document = getDocument(employeeJobPos);
							
							indexableActionableDynamicQuery.addDocuments(document);
						} catch (PortalException pe) {
							if (_log.isWarnEnabled()) {
								_log.warn("Unable to index contact " + employeeJobPos.getEmployeeJobPosId(), pe);
							}
						}
					}

				});
		indexableActionableDynamicQuery.setSearchEngineId(getSearchEngineId());

		indexableActionableDynamicQuery.performActions();
	}

	private static final Log _log = LogFactoryUtil.getLog(EmployeeJobPosIndexer.class);

}