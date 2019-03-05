package org.opencps.usermgt.service.indexer;

import java.util.LinkedHashMap;
import java.util.Locale;

import javax.portlet.PortletRequest;
import javax.portlet.PortletResponse;

import org.opencps.usermgt.constants.CommonTerm;
import org.opencps.usermgt.constants.OfficeSiteTerm;
import org.opencps.usermgt.model.OfficeSite;
import org.opencps.usermgt.service.OfficeSiteLocalServiceUtil;
import org.osgi.service.component.annotations.Component;

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

@Component(
    immediate = true,
    service = BaseIndexer.class
)
public class OfficeSiteIndexer extends BaseIndexer<OfficeSite> {

	public static final String CLASS_NAME = OfficeSite.class.getName();

	@Override
	public String getClassName() {
		return CLASS_NAME;
	}

	@Override
	public void postProcessSearchQuery(BooleanQuery searchQuery, BooleanFilter fullQueryBooleanFilter,
			SearchContext searchContext) throws Exception {

		addSearchTerm(searchQuery, searchContext, OfficeSiteTerm.OFFICE_SITE_ID, false);
		addSearchTerm(searchQuery, searchContext, OfficeSiteTerm.GROUP_ID, false);
		addSearchTerm(searchQuery, searchContext, OfficeSiteTerm.COMPANY_ID, false);
		addSearchTerm(searchQuery, searchContext, OfficeSiteTerm.USER_ID, false);
		addSearchTerm(searchQuery, searchContext, OfficeSiteTerm.USER_NAME, false);
		addSearchTerm(searchQuery, searchContext, OfficeSiteTerm.CREATE_DATE, false);
		addSearchTerm(searchQuery, searchContext, OfficeSiteTerm.MODIFIED_DATE, false);

		addSearchTerm(searchQuery, searchContext, OfficeSiteTerm.NAME, true);
		addSearchTerm(searchQuery, searchContext, OfficeSiteTerm.EN_NAME, true);
		addSearchTerm(searchQuery, searchContext, OfficeSiteTerm.GOV_AGENCY_CODE, true);
		addSearchTerm(searchQuery, searchContext, OfficeSiteTerm.ADDRESS, true);
		addSearchTerm(searchQuery, searchContext, OfficeSiteTerm.TEL_NO, true);
		addSearchTerm(searchQuery, searchContext, OfficeSiteTerm.FAX_NO, true);
		addSearchTerm(searchQuery, searchContext, OfficeSiteTerm.EMAIL, true);
		addSearchTerm(searchQuery, searchContext, OfficeSiteTerm.WEBSITE, true);
		addSearchTerm(searchQuery, searchContext, OfficeSiteTerm.LOGO_FILE_ENTRY_ID, true);
		addSearchTerm(searchQuery, searchContext, OfficeSiteTerm.SITE_GROUP_ID, true);
		addSearchTerm(searchQuery, searchContext, OfficeSiteTerm.ADMIN_USER_ID, true);
		addSearchTerm(searchQuery, searchContext, OfficeSiteTerm.PREFERENCES, true);

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
	protected void doDelete(OfficeSite officeSite) throws Exception {
		deleteDocument(officeSite.getCompanyId(), officeSite.getOfficeSiteId());
	}

	@Override
	protected Document doGetDocument(OfficeSite officeSite) throws Exception {
		Document document = getBaseModelDocument(CLASS_NAME, officeSite);

		document.addKeywordSortable(Field.COMPANY_ID, String.valueOf(officeSite.getCompanyId()));
		document.addDateSortable(Field.MODIFIED_DATE, officeSite.getModifiedDate());
		document.addKeywordSortable(Field.USER_ID, String.valueOf(officeSite.getUserId()));
		document.addKeywordSortable(Field.USER_NAME, String.valueOf(officeSite.getUserName()));

		document.addNumberSortable(OfficeSiteTerm.OFFICE_SITE_ID, officeSite.getOfficeSiteId());
		document.addNumberSortable(OfficeSiteTerm.GROUP_ID, officeSite.getGroupId());
		document.addTextSortable(OfficeSiteTerm.NAME, officeSite.getName());
		document.addTextSortable(OfficeSiteTerm.EN_NAME, officeSite.getEnName());
		document.addTextSortable(OfficeSiteTerm.GOV_AGENCY_CODE, officeSite.getGovAgencyCode());
		document.addTextSortable(OfficeSiteTerm.ADDRESS, officeSite.getAddress());
		document.addTextSortable(OfficeSiteTerm.TEL_NO, officeSite.getTelNo());
		document.addTextSortable(OfficeSiteTerm.FAX_NO, officeSite.getFaxNo());
		document.addTextSortable(OfficeSiteTerm.EMAIL, officeSite.getEmail());
		document.addTextSortable(OfficeSiteTerm.WEBSITE, officeSite.getWebsite());
		document.addNumberSortable(OfficeSiteTerm.LOGO_FILE_ENTRY_ID, officeSite.getLogoFileEntryId());
		document.addNumberSortable(OfficeSiteTerm.SITE_GROUP_ID, officeSite.getSiteGroupId());
		document.addNumberSortable(OfficeSiteTerm.ADMIN_USER_ID, officeSite.getAdminUserId());
		document.addTextSortable(OfficeSiteTerm.PREFERENCES, officeSite.getPreferences());
		
		User adminUser = UserLocalServiceUtil.fetchUser(officeSite.getAdminUserId());
		
		if(Validator.isNotNull(adminUser)){
			
			document.addTextSortable("adminUser_screenName", adminUser.getScreenName());
			document.addTextSortable("adminUser_email", adminUser.getEmailAddress());
			
		}

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
	protected void doReindex(OfficeSite officeSite) throws Exception {
		Document document = getDocument(officeSite);
		IndexWriterHelperUtil.updateDocument(getSearchEngineId(), officeSite.getCompanyId(), document,
				isCommitImmediately());
	}

	@Override
	protected void doReindex(String className, long classPK) throws Exception {
		OfficeSite officeSite = OfficeSiteLocalServiceUtil.getOfficeSite(classPK);
		doReindex(officeSite);
	}

	@Override
	protected void doReindex(String[] ids) throws Exception {
		long companyId = GetterUtil.getLong(ids[0]);
		reindexOfficeSite(companyId);
	}

	protected void reindexOfficeSite(long companyId) throws PortalException {
		final IndexableActionableDynamicQuery indexableActionableDynamicQuery = OfficeSiteLocalServiceUtil
				.getIndexableActionableDynamicQuery();

		indexableActionableDynamicQuery.setCompanyId(companyId);
		indexableActionableDynamicQuery
				.setPerformActionMethod(new ActionableDynamicQuery.PerformActionMethod<OfficeSite>() {

					@Override
					public void performAction(OfficeSite officeSite) {
						try {
							Document document = getDocument(officeSite);

							indexableActionableDynamicQuery.addDocuments(document);
						} catch (PortalException pe) {
							if (_log.isWarnEnabled()) {
								_log.warn("Unable to index contact " + officeSite.getOfficeSiteId(), pe);
							}
						}
					}

				});
		indexableActionableDynamicQuery.setSearchEngineId(getSearchEngineId());

		indexableActionableDynamicQuery.performActions();
	}

	private static final Log _log = LogFactoryUtil.getLog(OfficeSiteIndexer.class);

}