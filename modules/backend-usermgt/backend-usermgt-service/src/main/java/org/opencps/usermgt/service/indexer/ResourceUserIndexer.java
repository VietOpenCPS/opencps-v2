package org.opencps.usermgt.service.indexer;

import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.search.BaseIndexer;
import com.liferay.portal.kernel.search.BooleanQuery;
import com.liferay.portal.kernel.search.Document;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.search.IndexWriterHelperUtil;
import com.liferay.portal.kernel.search.SearchContext;
import com.liferay.portal.kernel.search.Summary;
import com.liferay.portal.kernel.search.filter.BooleanFilter;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.Validator;

import java.util.LinkedHashMap;
import java.util.Locale;

import javax.portlet.PortletRequest;
import javax.portlet.PortletResponse;

import org.opencps.usermgt.constants.ResourceUserTerm;
import org.opencps.usermgt.model.Employee;
import org.opencps.usermgt.model.ResourceUser;
import org.opencps.usermgt.service.ResourceUserLocalServiceUtil;
import org.osgi.service.component.annotations.Component;

@Component(
    immediate = true,
    service = BaseIndexer.class
)
public class ResourceUserIndexer extends BaseIndexer<ResourceUser> {

	public static final String CLASS_NAME = ResourceUser.class.getName();

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
	protected void doDelete(ResourceUser resourceUser) throws Exception {
		deleteDocument(resourceUser.getCompanyId(), resourceUser.getResourceUserId());
	}

	@Override
	protected Document doGetDocument(ResourceUser resourceUser) throws Exception {
		Document document = getBaseModelDocument(CLASS_NAME, resourceUser);

		document.addKeywordSortable(Field.COMPANY_ID, String.valueOf(resourceUser.getCompanyId()));
		document.addDateSortable(Field.MODIFIED_DATE, resourceUser.getModifiedDate());
		document.addKeywordSortable(Field.USER_ID, String.valueOf(resourceUser.getUserId()));
		document.addKeywordSortable(Field.USER_NAME, String.valueOf(resourceUser.getUserName()));

		document.addNumberSortable(ResourceUserTerm.GROUP_ID, resourceUser.getGroupId());
		document.addNumberSortable(ResourceUserTerm.RESOURCEUSER_ID, resourceUser.getResourceUserId());
		document.addTextSortable(ResourceUserTerm.CLASS_NAME, resourceUser.getClassName());
		document.addTextSortable(ResourceUserTerm.CLASS_PK, resourceUser.getClassPK());
		document.addNumberSortable(ResourceUserTerm.TO_USERID, resourceUser.getToUserId());
		document.addTextSortable("selected", Boolean.TRUE.toString());
		
		User user = UserLocalServiceUtil.fetchUser(resourceUser.getToUserId());
		
		String userName = StringPool.BLANK;
		String email = StringPool.BLANK;
		
		if(Validator.isNotNull(user)){
			
			userName = user.getFullName();
			email = user.getEmailAddress();
			
		}
		
		document.addTextSortable(ResourceUserTerm.TO_USERNAME, userName);
		document.addTextSortable(ResourceUserTerm.EMAIL, email);
		document.addTextSortable(ResourceUserTerm.USERCLASS, Employee.class.getName());
		
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
	protected void doReindex(ResourceUser resourceUser) throws Exception {
		Document document = getDocument(resourceUser);
		IndexWriterHelperUtil.updateDocument(getSearchEngineId(), resourceUser.getCompanyId(), document,
				isCommitImmediately());
	}

	@Override
	protected void doReindex(String className, long classPK) throws Exception {
		ResourceUser resourceUser = ResourceUserLocalServiceUtil.getResourceUser(classPK);
		doReindex(resourceUser);
	}

	@Override
	protected void doReindex(String[] ids) throws Exception {
		long companyId = GetterUtil.getLong(ids[0]);
		reindexResourceUser(companyId);
	}

	protected void reindexResourceUser(long companyId) throws PortalException {
		final IndexableActionableDynamicQuery indexableActionableDynamicQuery = ResourceUserLocalServiceUtil
				.getIndexableActionableDynamicQuery();

		indexableActionableDynamicQuery.setCompanyId(companyId);
		indexableActionableDynamicQuery
				.setPerformActionMethod(new ActionableDynamicQuery.PerformActionMethod<ResourceUser>() {

					@Override
					public void performAction(ResourceUser resourceUser) {
						try {
							Document document = getDocument(resourceUser);

							indexableActionableDynamicQuery.addDocuments(document);
						} catch (PortalException pe) {
							if (_log.isWarnEnabled()) {
								_log.warn("Unable to index contact " + resourceUser.getResourceUserId(), pe);
							}
						}
					}

				});
		indexableActionableDynamicQuery.setSearchEngineId(getSearchEngineId());

		indexableActionableDynamicQuery.performActions();
	}

	private static final Log _log = LogFactoryUtil.getLog(ResourceUserIndexer.class);

}