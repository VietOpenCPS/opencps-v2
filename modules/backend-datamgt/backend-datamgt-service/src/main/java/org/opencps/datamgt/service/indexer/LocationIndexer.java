package org.opencps.datamgt.service.indexer;

import java.util.LinkedHashMap;
import java.util.Locale;

import javax.portlet.PortletRequest;
import javax.portlet.PortletResponse;

import org.opencps.datamgt.constants.LocationTerm;
import org.opencps.datamgt.model.Location;
import org.opencps.datamgt.service.LocationLocalServiceUtil;

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

public class LocationIndexer extends BaseIndexer<Location> {

	public static final String CLASS_NAME = Location.class.getName();

	@Override
	public String getClassName() {
		return CLASS_NAME;
	}

	@Override
	public void postProcessSearchQuery(BooleanQuery searchQuery, BooleanFilter fullQueryBooleanFilter,
			SearchContext searchContext) throws Exception {

		addSearchTerm(searchQuery, searchContext, LocationTerm.LOCATION_ID, false);
		addSearchTerm(searchQuery, searchContext, LocationTerm.GROUP_ID, false);
		addSearchTerm(searchQuery, searchContext, LocationTerm.COMPANY_ID, false);
		addSearchTerm(searchQuery, searchContext, LocationTerm.USER_ID, false);
		addSearchTerm(searchQuery, searchContext, LocationTerm.USER_NAME, false);
		addSearchTerm(searchQuery, searchContext, LocationTerm.CREATE_DATE, false);
		addSearchTerm(searchQuery, searchContext, LocationTerm.MODIFIED_DATE, false);

		addSearchTerm(searchQuery, searchContext, LocationTerm.LOCATION, true);

		addSearchTerm(searchQuery, searchContext, LocationTerm.GEOLOCATION, false);

		LinkedHashMap<String, Object> params = (LinkedHashMap<String, Object>) searchContext.getAttribute("params");

		if (params != null) {
			String expandoAttributes = (String) params.get("expandoAttributes");

			if (Validator.isNotNull(expandoAttributes)) {
				addSearchExpando(searchQuery, searchContext, expandoAttributes);
			}
		}
	}

	@Override
	protected void doDelete(Location location) throws Exception {
		deleteDocument(location.getCompanyId(), location.getLocationId());
	}

	@Override
	protected Document doGetDocument(Location location) throws Exception {
		Document document = getBaseModelDocument(CLASS_NAME, location);

		document.addNumberSortable(LocationTerm.GROUP_ID, location.getGroupId());
		document.addKeywordSortable(Field.COMPANY_ID, String.valueOf(location.getCompanyId()));
		document.addDateSortable(Field.MODIFIED_DATE, location.getModifiedDate());
		document.addKeywordSortable(Field.USER_ID, String.valueOf(location.getUserId()));
		document.addKeywordSortable(Field.USER_NAME, String.valueOf(location.getUserName()));

		document.addTextSortable(LocationTerm.LOCATION, location.getLocation());
		document.addTextSortable(LocationTerm.GEOLOCATION, location.getGeolocation());
		
		
		document.addNumberSortable(LocationTerm.LOCATION_ID, location.getLocationId());

		document.setSortableTextFields(
				new String[] { LocationTerm.LOCATION});

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
	protected void doReindex(Location location) throws Exception {
		Document document = getDocument(location);

		IndexWriterHelperUtil.updateDocument(getSearchEngineId(), location.getCompanyId(), document,
				isCommitImmediately());
	}

	@Override
	protected void doReindex(String className, long classPK) throws Exception {
		Location location = LocationLocalServiceUtil.getLocation(classPK);

		doReindex(location);
	}

	@Override
	protected void doReindex(String[] ids) throws Exception {
		long companyId = GetterUtil.getLong(ids[0]);

		reindexMLocation(companyId);
	}

	protected void reindexMLocation(long companyId) throws PortalException {
		final IndexableActionableDynamicQuery indexableActionableDynamicQuery = LocationLocalServiceUtil
				.getIndexableActionableDynamicQuery();

		indexableActionableDynamicQuery.setCompanyId(companyId);
		indexableActionableDynamicQuery
				.setPerformActionMethod(new ActionableDynamicQuery.PerformActionMethod<Location>() {

					@Override
					public void performAction(Location location) {
						try {
							Document document = getDocument(location);

							indexableActionableDynamicQuery.addDocuments(document);
						} catch (PortalException pe) {
							if (_log.isWarnEnabled()) {
								_log.warn("Unable to index contact " + location.getLocationId(), pe);
							}
						}
					}

				});
		indexableActionableDynamicQuery.setSearchEngineId(getSearchEngineId());

		indexableActionableDynamicQuery.performActions();
	}

	private static final Log _log = LogFactoryUtil.getLog(LocationIndexer.class);

}