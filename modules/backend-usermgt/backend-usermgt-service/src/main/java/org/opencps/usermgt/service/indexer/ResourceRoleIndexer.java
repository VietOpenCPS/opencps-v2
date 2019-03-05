package org.opencps.usermgt.service.indexer;

import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Role;
import com.liferay.portal.kernel.search.BaseIndexer;
import com.liferay.portal.kernel.search.BooleanQuery;
import com.liferay.portal.kernel.search.Document;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.search.IndexWriterHelperUtil;
import com.liferay.portal.kernel.search.SearchContext;
import com.liferay.portal.kernel.search.Summary;
import com.liferay.portal.kernel.search.filter.BooleanFilter;
import com.liferay.portal.kernel.service.RoleLocalServiceUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.Validator;

import java.util.LinkedHashMap;
import java.util.Locale;

import javax.portlet.PortletRequest;
import javax.portlet.PortletResponse;

import org.opencps.usermgt.constants.ResourceRoleTerm;
import org.opencps.usermgt.model.ResourceRole;
import org.opencps.usermgt.service.ResourceRoleLocalServiceUtil;
import org.osgi.service.component.annotations.Component;

@Component(
    immediate = true,
    service = BaseIndexer.class
)
public class ResourceRoleIndexer extends BaseIndexer<ResourceRole> {

	public static final String CLASS_NAME = ResourceRole.class.getName();

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
	protected void doDelete(ResourceRole resourceRole) throws Exception {
		deleteDocument(resourceRole.getCompanyId(), resourceRole.getResourceRoleId());
	}

	@Override
	protected Document doGetDocument(ResourceRole resourceRole) throws Exception {
		Document document = getBaseModelDocument(CLASS_NAME, resourceRole);

		document.addKeywordSortable(Field.COMPANY_ID, String.valueOf(resourceRole.getCompanyId()));
		document.addDateSortable(Field.MODIFIED_DATE, resourceRole.getModifiedDate());
		document.addKeywordSortable(Field.USER_ID, String.valueOf(resourceRole.getUserId()));
		document.addKeywordSortable(Field.USER_NAME, String.valueOf(resourceRole.getUserName()));

		document.addNumberSortable(ResourceRoleTerm.GROUP_ID, resourceRole.getGroupId());
		document.addNumberSortable(ResourceRoleTerm.RESOURCEROLE_ID, resourceRole.getResourceRoleId());
		document.addTextSortable(ResourceRoleTerm.CLASS_NAME, resourceRole.getClassName());
		document.addTextSortable(ResourceRoleTerm.CLASS_PK, resourceRole.getClassPK());
		document.addNumberSortable(ResourceRoleTerm.ROLE_ID, resourceRole.getRoleId());
		document.addTextSortable("selected", Boolean.TRUE.toString());
		
		Role role = RoleLocalServiceUtil.fetchRole(resourceRole.getRoleId());

		String roleName = StringPool.BLANK;

		if (Validator.isNotNull(role)) {

			roleName = role.getName();

		}

		document.addTextSortable(ResourceRoleTerm.ROLE_NAME, roleName);

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
	protected void doReindex(ResourceRole resourceRole) throws Exception {
		Document document = getDocument(resourceRole);
		IndexWriterHelperUtil.updateDocument(getSearchEngineId(), resourceRole.getCompanyId(), document,
				isCommitImmediately());
	}

	@Override
	protected void doReindex(String className, long classPK) throws Exception {
		ResourceRole ResourceRole = ResourceRoleLocalServiceUtil.getResourceRole(classPK);
		doReindex(ResourceRole);
	}

	@Override
	protected void doReindex(String[] ids) throws Exception {
		long companyId = GetterUtil.getLong(ids[0]);
		reindexResourceRole(companyId);
	}

	protected void reindexResourceRole(long companyId) throws PortalException {
		final IndexableActionableDynamicQuery indexableActionableDynamicQuery = ResourceRoleLocalServiceUtil
				.getIndexableActionableDynamicQuery();

		indexableActionableDynamicQuery.setCompanyId(companyId);
		indexableActionableDynamicQuery
				.setPerformActionMethod(new ActionableDynamicQuery.PerformActionMethod<ResourceRole>() {

					@Override
					public void performAction(ResourceRole resourceRole) {
						try {
							Document document = getDocument(resourceRole);

							indexableActionableDynamicQuery.addDocuments(document);
						} catch (PortalException pe) {
							if (_log.isWarnEnabled()) {
								_log.warn("Unable to index contact " + resourceRole.getResourceRoleId(), pe);
							}
						}
					}

				});
		indexableActionableDynamicQuery.setSearchEngineId(getSearchEngineId());

		indexableActionableDynamicQuery.performActions();
	}

	private static final Log _log = LogFactoryUtil.getLog(ResourceRoleIndexer.class);

}