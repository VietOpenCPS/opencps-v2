package org.mobilink.backend.datamgt.service.indexer;

import java.util.LinkedHashMap;
import java.util.Locale;

import javax.portlet.PortletRequest;
import javax.portlet.PortletResponse;

import org.mobilink.backend.datamgt.constants.WorkspaceUserTerm;
import org.mobilink.backend.datamgt.model.WorkspaceUser;
import org.mobilink.backend.datamgt.service.WorkspaceUserLocalServiceUtil;

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

public class WorkspaceUserIndexer extends BaseIndexer<WorkspaceUser> {

	public static final String CLASS_NAME = WorkspaceUser.class.getName();

	@Override
	public String getClassName() {
		return CLASS_NAME;
	}

	@Override
	public void postProcessSearchQuery(BooleanQuery searchQuery, BooleanFilter fullQueryBooleanFilter,
			SearchContext searchContext) throws Exception {

		addSearchTerm(searchQuery, searchContext, WorkspaceUserTerm.WORKSPACE_USER_ID, false);
		addSearchTerm(searchQuery, searchContext, WorkspaceUserTerm.GROUP_ID, false);
		addSearchTerm(searchQuery, searchContext, WorkspaceUserTerm.COMPANY_ID, false);
		addSearchTerm(searchQuery, searchContext, WorkspaceUserTerm.USER_NAME, false);
		addSearchTerm(searchQuery, searchContext, WorkspaceUserTerm.CREATE_DATE, false);
		addSearchTerm(searchQuery, searchContext, WorkspaceUserTerm.MODIFIED_DATE, false);		
		addSearchTerm(searchQuery, searchContext, WorkspaceUserTerm.WORKSPACE_ID, false);	
		addSearchTerm(searchQuery, searchContext, WorkspaceUserTerm.ASSIGN_USER_ID, false);	
		addSearchTerm(searchQuery, searchContext, WorkspaceUserTerm.USER_ID, false);

		LinkedHashMap<String, Object> params = (LinkedHashMap<String, Object>) searchContext.getAttribute("params");

		if (params != null) {
			String expandoAttributes = (String) params.get("expandoAttributes");

			if (Validator.isNotNull(expandoAttributes)) {
				addSearchExpando(searchQuery, searchContext, expandoAttributes);
			}
		}
	}

	@Override
	protected void doDelete(WorkspaceUser workspaceuser) throws Exception {
		deleteDocument(workspaceuser.getCompanyId(), workspaceuser.getWorkspaceUserId());
	}
//	public void doDelete1(long companyId, long workspaceId) throws Exception {
//		deleteDocument(companyId, workspaceId);
//	}

	@Override
	protected Document doGetDocument(WorkspaceUser workspaceuser) throws Exception {
		Document document = getBaseModelDocument(CLASS_NAME, workspaceuser);

		document.addKeywordSortable(Field.COMPANY_ID, String.valueOf(workspaceuser.getCompanyId()));
		document.addDateSortable(Field.MODIFIED_DATE, workspaceuser.getModifiedDate());
		document.addKeywordSortable(Field.USER_ID, String.valueOf(workspaceuser.getUserId()));
		document.addKeywordSortable(Field.USER_NAME, String.valueOf(workspaceuser.getUserName()));

		document.addNumberSortable(WorkspaceUserTerm.WORKSPACE_USER_ID, workspaceuser.getWorkspaceUserId());
		
		document.addNumberSortable(WorkspaceUserTerm.GROUP_ID, workspaceuser.getGroupId());
		document.addNumberSortable(WorkspaceUserTerm.WORKSPACE_ID, workspaceuser.getWorkspaceId());
		document.addNumberSortable(WorkspaceUserTerm.ASSIGN_USER_ID, workspaceuser.getAssignUserId());


		document.setSortableTextFields(
				new String[] { WorkspaceUserTerm.USER_NAME});

		return document;
	}

	@Override
	protected String doGetSortField(String orderByCol) {
		if (orderByCol.equals("email-address")) {
			return "emailAddress";
		} else if (orderByCol.equals("first-name")) {
			return "firstName";
		} else if (orderByCol.equals("job-title")) {
			return "jobTitle";
		} else if (orderByCol.equals("last-name")) {
			return "lastName";
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
	protected void doReindex(WorkspaceUser workspace) throws Exception {
		Document document = getDocument(workspace);

		IndexWriterHelperUtil.updateDocument(getSearchEngineId(), workspace.getCompanyId(), document,
				isCommitImmediately());
	}

	@Override
	protected void doReindex(String className, long classPK) throws Exception {
		WorkspaceUser workspace = WorkspaceUserLocalServiceUtil.fetchWorkspaceUser(classPK);

		doReindex(workspace);
	}

	@Override
	protected void doReindex(String[] ids) throws Exception {
		long companyId = GetterUtil.getLong(ids[0]);

		reindexWorkspaceUser(companyId);
	}

	protected void reindexWorkspaceUser(long companyId) throws PortalException {
		final IndexableActionableDynamicQuery indexableActionableDynamicQuery = WorkspaceUserLocalServiceUtil
				.getIndexableActionableDynamicQuery();

		indexableActionableDynamicQuery.setCompanyId(companyId);
		indexableActionableDynamicQuery
				.setPerformActionMethod(new ActionableDynamicQuery.PerformActionMethod<WorkspaceUser>() {

					@Override
					public void performAction(WorkspaceUser workspaceuser) {
						try {
							Document document = getDocument(workspaceuser);

							indexableActionableDynamicQuery.addDocuments(document);
						} catch (PortalException pe) {
							if (_log.isWarnEnabled()) {
								_log.warn("Unable to index workspaceuser " + workspaceuser.getWorkspaceUserId(), pe);
							}
						}
					}

				});
		indexableActionableDynamicQuery.setSearchEngineId(getSearchEngineId());

		indexableActionableDynamicQuery.performActions();
	}

	private static final Log _log = LogFactoryUtil.getLog(WorkspaceUserIndexer.class);

}