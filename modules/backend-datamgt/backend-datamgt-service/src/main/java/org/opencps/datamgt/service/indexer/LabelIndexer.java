package org.opencps.datamgt.service.indexer;

import java.util.LinkedHashMap;
import java.util.Locale;

import javax.portlet.PortletRequest;
import javax.portlet.PortletResponse;

import org.opencps.datamgt.constants.LabelTerm;
import org.opencps.datamgt.model.Label;
import org.opencps.datamgt.service.LabelLocalServiceUtil;

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

public class LabelIndexer extends BaseIndexer<Label> {

	public static final String CLASS_NAME = Label.class.getName();

	@Override
	public String getClassName() {
		return CLASS_NAME;
	}

	@Override
	public void postProcessSearchQuery(BooleanQuery searchQuery, BooleanFilter fullQueryBooleanFilter,
			SearchContext searchContext) throws Exception {

		addSearchTerm(searchQuery, searchContext, LabelTerm.LABEL_ID, false);
		addSearchTerm(searchQuery, searchContext, LabelTerm.GROUP_ID, false);
		addSearchTerm(searchQuery, searchContext, LabelTerm.COMPANY_ID, false);
		addSearchTerm(searchQuery, searchContext, LabelTerm.USER_ID, false);
		addSearchTerm(searchQuery, searchContext, LabelTerm.USER_NAME, false);
		addSearchTerm(searchQuery, searchContext, LabelTerm.CREATE_DATE, false);
		addSearchTerm(searchQuery, searchContext, LabelTerm.MODIFIED_DATE, false);

		addSearchTerm(searchQuery, searchContext, LabelTerm.LABEL_NAME, true);

		addSearchTerm(searchQuery, searchContext, LabelTerm.LABEL_COLOR, true);
		addSearchTerm(searchQuery, searchContext, LabelTerm.SCOPE, false);
		

		LinkedHashMap<String, Object> params = (LinkedHashMap<String, Object>) searchContext.getAttribute("params");

		if (params != null) {
			String expandoAttributes = (String) params.get("expandoAttributes");

			if (Validator.isNotNull(expandoAttributes)) {
				addSearchExpando(searchQuery, searchContext, expandoAttributes);
			}
		}
	}

	@Override
	protected void doDelete(Label label) throws Exception {
		deleteDocument(label.getCompanyId(), label.getLabelId());
	}

	@Override
	protected Document doGetDocument(Label label) throws Exception {
		Document document = getBaseModelDocument(CLASS_NAME, label);

		document.addKeywordSortable(Field.COMPANY_ID, String.valueOf(label.getCompanyId()));
		document.addDateSortable(Field.MODIFIED_DATE, label.getModifiedDate());
		document.addKeywordSortable(Field.USER_ID, String.valueOf(label.getUserId()));
		document.addKeywordSortable(Field.USER_NAME, String.valueOf(label.getUserName()));

		document.addTextSortable(LabelTerm.LABEL_NAME, label.getName());
		document.addTextSortable(LabelTerm.LABEL_COLOR, label.getColor());
		
		document.addNumberSortable(LabelTerm.GROUP_ID, label.getGroupId());
		document.addNumberSortable(LabelTerm.LABEL_ID, label.getLabelId());
		document.addNumberSortable(LabelTerm.SCOPE, label.getScope());
		
		document.setSortableTextFields(
				new String[] { LabelTerm.LABEL_NAME,LabelTerm.LABEL_COLOR});

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
	protected void doReindex(Label label) throws Exception {
		Document document = getDocument(label);

		IndexWriterHelperUtil.updateDocument(getSearchEngineId(), label.getCompanyId(), document,
				isCommitImmediately());
	}

	@Override
	protected void doReindex(String className, long classPK) throws Exception {
		Label MLabel = LabelLocalServiceUtil.fetchLabel(classPK);

		doReindex(MLabel);
	}

	@Override
	protected void doReindex(String[] ids) throws Exception {
		long companyId = GetterUtil.getLong(ids[0]);

		reindexMLabel(companyId);
	}

	protected void reindexMLabel(long companyId) throws PortalException {
		final IndexableActionableDynamicQuery indexableActionableDynamicQuery = LabelLocalServiceUtil
				.getIndexableActionableDynamicQuery();

		indexableActionableDynamicQuery.setCompanyId(companyId);
		indexableActionableDynamicQuery
				.setPerformActionMethod(new ActionableDynamicQuery.PerformActionMethod<Label>() {

					@Override
					public void performAction(Label label) {
						try {
							Document document = getDocument(label);

							indexableActionableDynamicQuery.addDocuments(document);
						} catch (PortalException pe) {
							if (_log.isWarnEnabled()) {
								_log.warn("Unable to index label " + label.getLabelId(), pe);
							}
						}
					}

				});
		indexableActionableDynamicQuery.setSearchEngineId(getSearchEngineId());

		indexableActionableDynamicQuery.performActions();
	}

	private static final Log _log = LogFactoryUtil.getLog(LabelIndexer.class);

}
