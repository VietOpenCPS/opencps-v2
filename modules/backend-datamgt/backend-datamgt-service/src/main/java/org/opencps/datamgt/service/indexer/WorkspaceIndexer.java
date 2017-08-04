package org.opencps.datamgt.service.indexer;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;

import javax.portlet.PortletRequest;
import javax.portlet.PortletResponse;

import org.opencps.datamgt.constants.WorkspaceTerm;
import org.opencps.datamgt.model.Workspace;
import org.opencps.datamgt.model.WorkspaceUser;
import org.opencps.datamgt.service.WorkspaceLocalServiceUtil;
import org.opencps.datamgt.service.WorkspaceUserLocalServiceUtil;

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
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;

public class WorkspaceIndexer extends BaseIndexer<Workspace> {

	public static final String CLASS_NAME = Workspace.class.getName();

	@Override
	public String getClassName() {
		return CLASS_NAME;
	}

	@Override
	public void postProcessSearchQuery(BooleanQuery searchQuery, BooleanFilter fullQueryBooleanFilter,
			SearchContext searchContext) throws Exception {

		addSearchTerm(searchQuery, searchContext, WorkspaceTerm.WORKSPACE_ID, false);
		addSearchTerm(searchQuery, searchContext, WorkspaceTerm.GROUP_ID, false);
		addSearchTerm(searchQuery, searchContext, WorkspaceTerm.COMPANY_ID, false);
		addSearchTerm(searchQuery, searchContext, WorkspaceTerm.USER_NAME, false);
		addSearchTerm(searchQuery, searchContext, WorkspaceTerm.CREATE_DATE, false);
		addSearchTerm(searchQuery, searchContext, WorkspaceTerm.MODIFIED_DATE, false);
		addSearchTerm(searchQuery, searchContext, WorkspaceTerm.SEQ_ORDER, false);
		
		addSearchTerm(searchQuery, searchContext, WorkspaceTerm.WORKSPACE_NAME, true);
		addSearchTerm(searchQuery, searchContext, WorkspaceTerm.OWN_USER, false);
		
		LinkedHashMap<String, Object> params = (LinkedHashMap<String, Object>) searchContext.getAttribute("params");

		if (params != null) {
			String expandoAttributes = (String) params.get("expandoAttributes");

			if (Validator.isNotNull(expandoAttributes)) {
				addSearchExpando(searchQuery, searchContext, expandoAttributes);
			}
		}
	}

	@Override
	protected void doDelete(Workspace workspace) throws Exception {
		deleteDocument(workspace.getCompanyId(), workspace.getWorkspaceId());
	}

	@Override
	protected Document doGetDocument(Workspace workspace) throws Exception {
		Document document = getBaseModelDocument(CLASS_NAME, workspace);

		document.addKeywordSortable(Field.COMPANY_ID, String.valueOf(workspace.getCompanyId()));
		document.addDateSortable(Field.MODIFIED_DATE, workspace.getModifiedDate());
		document.addKeywordSortable(Field.USER_ID, String.valueOf(workspace.getUserId()));
		document.addKeywordSortable(Field.USER_NAME, String.valueOf(workspace.getUserName()));

		document.addTextSortable(WorkspaceTerm.WORKSPACE_NAME, workspace.getName());
		
		document.addNumberSortable(WorkspaceTerm.GROUP_ID, workspace.getGroupId());
		document.addNumberSortable(WorkspaceTerm.COMPANY_ID, workspace.getCompanyId());
		document.addNumberSortable(WorkspaceTerm.WORKSPACE_ID, workspace.getWorkspaceId());
		document.addNumberSortable(WorkspaceTerm.SEQ_ORDER, workspace.getSeqOrder());
		
		//add user to workspace.
		List<WorkspaceUser> workspaceUser = WorkspaceUserLocalServiceUtil.findByF_workspaceId(workspace.getWorkspaceId());
		
		String ownUser = StringPool.BLANK;
		
		for (WorkspaceUser mWorkspaceUser : workspaceUser) {
			ownUser += mWorkspaceUser.getAssignUserId() + " ";
		}
		
		document.addTextSortable(WorkspaceTerm.OWN_USER, ownUser);
		
		document.setSortableTextFields(
				new String[] { WorkspaceTerm.SEQ_ORDER});

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
	protected void doReindex(Workspace workspace) throws Exception {
		Document document = getDocument(workspace);

		IndexWriterHelperUtil.updateDocument(getSearchEngineId(), workspace.getCompanyId(), document,
				isCommitImmediately());
	}

	@Override
	protected void doReindex(String className, long classPK) throws Exception {
		Workspace workspace = WorkspaceLocalServiceUtil.fetchWorkspace(classPK);

		doReindex(workspace);
	}

	@Override
	protected void doReindex(String[] ids) throws Exception {
		long companyId = GetterUtil.getLong(ids[0]);

		reindexMWorkspace(companyId);
	}

	protected void reindexMWorkspace(long companyId) throws PortalException {
		final IndexableActionableDynamicQuery indexableActionableDynamicQuery = WorkspaceLocalServiceUtil
				.getIndexableActionableDynamicQuery();

		indexableActionableDynamicQuery.setCompanyId(companyId);
		indexableActionableDynamicQuery
				.setPerformActionMethod(new ActionableDynamicQuery.PerformActionMethod<Workspace>() {

					@Override
					public void performAction(Workspace workspace) {
						try {
							Document document = getDocument(workspace);

							indexableActionableDynamicQuery.addDocuments(document);
						} catch (PortalException pe) {
							if (_log.isWarnEnabled()) {
								_log.warn("Unable to index contact " + workspace.getWorkspaceId(), pe);
							}
						}
					}

				});
		indexableActionableDynamicQuery.setSearchEngineId(getSearchEngineId());

		indexableActionableDynamicQuery.performActions();
	}

	private static final Log _log = LogFactoryUtil.getLog(WorkspaceIndexer.class);

}