package org.opencps.usermgt.service.indexer;

import java.util.LinkedHashMap;
import java.util.Locale;

import javax.portlet.PortletRequest;
import javax.portlet.PortletResponse;

import org.opencps.usermgt.constants.CommonTerm;
import org.opencps.usermgt.constants.WorkingUnitTerm;
import org.opencps.usermgt.model.WorkingUnit;
import org.opencps.usermgt.service.WorkingUnitLocalServiceUtil;
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
public class WorkingUnitIndexer extends BaseIndexer<WorkingUnit> {

	public static final String CLASS_NAME = WorkingUnit.class.getName();

	@Override
	public String getClassName() {
		return CLASS_NAME;
	}

	@Override
	public void postProcessSearchQuery(BooleanQuery searchQuery, BooleanFilter fullQueryBooleanFilter,
			SearchContext searchContext) throws Exception {

		addSearchTerm(searchQuery, searchContext, WorkingUnitTerm.WORKINGUNIT_ID, false);
		addSearchTerm(searchQuery, searchContext, WorkingUnitTerm.GROUP_ID, false);
		addSearchTerm(searchQuery, searchContext, WorkingUnitTerm.COMPANY_ID, false);
		addSearchTerm(searchQuery, searchContext, WorkingUnitTerm.USER_ID, false);
		addSearchTerm(searchQuery, searchContext, WorkingUnitTerm.USER_NAME, false);
		addSearchTerm(searchQuery, searchContext, WorkingUnitTerm.CREATE_DATE, false);
		addSearchTerm(searchQuery, searchContext, WorkingUnitTerm.MODIFIED_DATE, false);
		
		addSearchTerm(searchQuery, searchContext, WorkingUnitTerm.NAME, true);
		addSearchTerm(searchQuery, searchContext, WorkingUnitTerm.ENNAME, true);
		addSearchTerm(searchQuery, searchContext, WorkingUnitTerm.GOV_AGENCY_CODE, true);
		addSearchTerm(searchQuery, searchContext, WorkingUnitTerm.PARENT_WORKING_UNIT_ID, true);
		addSearchTerm(searchQuery, searchContext, WorkingUnitTerm.SIBLING, true);
		addSearchTerm(searchQuery, searchContext, WorkingUnitTerm.TREEINDEX, true);
		addSearchTerm(searchQuery, searchContext, WorkingUnitTerm.ADDRESS, true);
		addSearchTerm(searchQuery, searchContext, WorkingUnitTerm.TEL_NO, true);
		addSearchTerm(searchQuery, searchContext, WorkingUnitTerm.FAX_NO, true);
		addSearchTerm(searchQuery, searchContext, WorkingUnitTerm.EMAIL, true);
		addSearchTerm(searchQuery, searchContext, WorkingUnitTerm.WEBSITE, true);
		
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
	protected void doDelete(WorkingUnit workingUnit) throws Exception {
		deleteDocument(workingUnit.getCompanyId(), workingUnit.getWorkingUnitId());
	}

	@Override
	protected Document doGetDocument(WorkingUnit workingUnit) throws Exception {
		Document document = getBaseModelDocument(CLASS_NAME, workingUnit);

		document.addKeywordSortable(Field.COMPANY_ID, String.valueOf(workingUnit.getCompanyId()));
		document.addDateSortable(Field.MODIFIED_DATE, workingUnit.getModifiedDate());
		document.addKeywordSortable(Field.USER_ID, String.valueOf(workingUnit.getUserId()));
		document.addKeywordSortable(Field.USER_NAME, String.valueOf(workingUnit.getUserName()));

		document.addNumberSortable(WorkingUnitTerm.GROUP_ID, workingUnit.getGroupId());
		document.addNumberSortable(WorkingUnitTerm.WORKINGUNIT_ID, workingUnit.getWorkingUnitId());
		document.addTextSortable(WorkingUnitTerm.NAME, workingUnit.getName());
		document.addTextSortable(WorkingUnitTerm.ENNAME, workingUnit.getEnName());
		document.addTextSortable(WorkingUnitTerm.GOV_AGENCY_CODE, workingUnit.getGovAgencyCode());
		document.addNumberSortable(WorkingUnitTerm.PARENT_WORKING_UNIT_ID, workingUnit.getParentWorkingUnitId());
		document.addTextSortable(WorkingUnitTerm.SIBLING, workingUnit.getSibling());
		document.addTextSortable(WorkingUnitTerm.TREEINDEX, workingUnit.getTreeIndex());
		document.addTextSortable(WorkingUnitTerm.ADDRESS, workingUnit.getAddress());
		document.addTextSortable(WorkingUnitTerm.TEL_NO, workingUnit.getTelNo());
		document.addTextSortable(WorkingUnitTerm.FAX_NO, workingUnit.getFaxNo());
		document.addTextSortable(WorkingUnitTerm.EMAIL, workingUnit.getEmail());
		document.addTextSortable(WorkingUnitTerm.WEBSITE, workingUnit.getWebsite());
		document.addNumberSortable(WorkingUnitTerm.LOGO_FILE_ENTRY_ID, workingUnit.getLogoFileEntryId());
		
		return document;
	}

	@Override
	protected String doGetSortField(String orderByCol) {
		if (CommonTerm.EMAIL_DASH_ADDRESS.equals(orderByCol)) {
			return CommonTerm.EMAIL_ADDRESS;
		} else if (CommonTerm.FIRST_DASH_NAME.equals(orderByCol)) {
			return CommonTerm.FIRST_NAME;
		} else if (CommonTerm.JOB_DASH_TITLE.equals(orderByCol)) {
			return CommonTerm.JOB_TITLE;
		} else if (CommonTerm.LAST_DASH_NAME.equals(orderByCol)) {
			return CommonTerm.LAST_NAME;
		} else {
			return orderByCol;
		}
	}

	@Override
	protected Summary doGetSummary(Document document, Locale locale, String snippet, PortletRequest portletRequest,
			PortletResponse portletResponse) {

		Summary summary = createSummary(document);

		summary.setMaxContentLength(QueryUtil.ALL_POS);

		return summary;
	}

	@Override
	protected void doReindex(WorkingUnit workingUnit) throws Exception {
		Document document = getDocument(workingUnit);
		IndexWriterHelperUtil.updateDocument(getSearchEngineId(), workingUnit.getCompanyId(), document,
				isCommitImmediately());
	}

	@Override
	protected void doReindex(String className, long classPK) throws Exception {
		WorkingUnit workingUnit = WorkingUnitLocalServiceUtil.getWorkingUnit(classPK);
		doReindex(workingUnit);
	}

	@Override
	protected void doReindex(String[] ids) throws Exception {
		long companyId = GetterUtil.getLong(ids[0]);
		reindexWorkingUnit(companyId);
	}

	protected void reindexWorkingUnit(long companyId) throws PortalException {
		final IndexableActionableDynamicQuery indexableActionableDynamicQuery = WorkingUnitLocalServiceUtil
				.getIndexableActionableDynamicQuery();

		indexableActionableDynamicQuery.setCompanyId(companyId);
		indexableActionableDynamicQuery
				.setPerformActionMethod(new ActionableDynamicQuery.PerformActionMethod<WorkingUnit>() {

					@Override
					public void performAction(WorkingUnit workingUnit) {
						try {
							Document document = getDocument(workingUnit);

							indexableActionableDynamicQuery.addDocuments(document);
						} catch (PortalException pe) {
							if (_log.isWarnEnabled()) {
								_log.warn("Unable to index contact " + workingUnit.getWorkingUnitId(), pe);
							}
						}
					}

				});
		indexableActionableDynamicQuery.setSearchEngineId(getSearchEngineId());

		indexableActionableDynamicQuery.performActions();
	}

	private static final Log _log = LogFactoryUtil.getLog(WorkingUnitIndexer.class);

}