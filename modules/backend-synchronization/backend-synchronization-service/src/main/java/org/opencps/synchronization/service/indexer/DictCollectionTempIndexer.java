package org.opencps.synchronization.service.indexer;

import java.util.Locale;

import javax.portlet.PortletRequest;
import javax.portlet.PortletResponse;

import org.opencps.synchronization.constants.DictCollectionTempTerm;
import org.opencps.synchronization.model.DictCollectionTemp;
import org.opencps.synchronization.service.DictCollectionTempLocalServiceUtil;
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

@Component(
    immediate = true,
    service = BaseIndexer.class
)
public class DictCollectionTempIndexer extends BaseIndexer<DictCollectionTemp> {

	public static final String CLASS_NAME = DictCollectionTemp.class.getName();

	@Override
	public String getClassName() {
		return CLASS_NAME;
	}

	@Override
	public void postProcessSearchQuery(BooleanQuery searchQuery, BooleanFilter fullQueryBooleanFilter,
			SearchContext searchContext) throws Exception {

	}

	@Override
	protected void doDelete(DictCollectionTemp dictCollection) throws Exception {
		deleteDocument(dictCollection.getCompanyId(), dictCollection.getDictCollectionId());
	}

	@Override
	protected Document doGetDocument(DictCollectionTemp dictCollection) throws Exception {
		Document document = getBaseModelDocument(CLASS_NAME, dictCollection);

		document.addKeywordSortable(Field.COMPANY_ID, String.valueOf(dictCollection.getCompanyId()));
		document.addDateSortable(Field.MODIFIED_DATE, dictCollection.getModifiedDate());
		document.addKeywordSortable(Field.USER_ID, String.valueOf(dictCollection.getUserId()));
		document.addKeywordSortable(Field.USER_NAME, String.valueOf(dictCollection.getUserName()));

		document.addNumberSortable(DictCollectionTempTerm.GROUP_ID, dictCollection.getGroupId());
		document.addNumberSortable(DictCollectionTempTerm.DICT_COLLECTION_ID, dictCollection.getDictCollectionId());
		document.addTextSortable(DictCollectionTempTerm.COLLECTION_CODE, dictCollection.getCollectionCode());
		document.addTextSortable(DictCollectionTempTerm.COLLECTION_NAME, dictCollection.getCollectionName());
		document.addTextSortable(DictCollectionTempTerm.COLLECTION_NAME_EN, dictCollection.getCollectionNameEN());
		document.addTextSortable(DictCollectionTempTerm.DESCRIPTION, dictCollection.getDescription());
		document.addTextSortable(DictCollectionTempTerm.DATAFORM, dictCollection.getDataForm());
		document.addNumber(DictCollectionTempTerm.STATUS, dictCollection.getStatus());
		document.addNumber(DictCollectionTempTerm.MUST_SYNC, dictCollection.getMustSync());
		
		return document;
	}

	@Override
	protected String doGetSortField(String orderByCol) {
		return orderByCol;
	}

	@Override
	protected Summary doGetSummary(Document document, Locale locale, String snippet, PortletRequest portletRequest,
			PortletResponse portletResponse) {

		Summary summary = createSummary(document);

		summary.setMaxContentLength(QueryUtil.ALL_POS);

		return summary;
	}

	@Override
	protected void doReindex(DictCollectionTemp dictCollection) throws Exception {
		Document document = getDocument(dictCollection);
		IndexWriterHelperUtil.updateDocument(getSearchEngineId(), dictCollection.getCompanyId(), document,
				isCommitImmediately());
	}

	@Override
	protected void doReindex(String className, long classPK) throws Exception {
		DictCollectionTemp dictCollection = DictCollectionTempLocalServiceUtil.getDictCollectionTemp(classPK);
		doReindex(dictCollection);
	}

	@Override
	protected void doReindex(String[] ids) throws Exception {
		long companyId = GetterUtil.getLong(ids[0]);
		reindexDictCollectionTemp(companyId);
	}

	protected void reindexDictCollectionTemp(long companyId) throws PortalException {
		final IndexableActionableDynamicQuery indexableActionableDynamicQuery = DictCollectionTempLocalServiceUtil
				.getIndexableActionableDynamicQuery();

		indexableActionableDynamicQuery.setCompanyId(companyId);
		indexableActionableDynamicQuery
				.setPerformActionMethod(new ActionableDynamicQuery.PerformActionMethod<DictCollectionTemp>() {

					@Override
					public void performAction(DictCollectionTemp dictCollection) {
						try {
							Document document = getDocument(dictCollection);

							indexableActionableDynamicQuery.addDocuments(document);
						} catch (PortalException pe) {
							if (_log.isWarnEnabled()) {
								_log.warn("Unable to index DictCollectionTempIndexer " + dictCollection.getDictCollectionId(), pe);
							}
						}
					}

				});
		indexableActionableDynamicQuery.setSearchEngineId(getSearchEngineId());

		indexableActionableDynamicQuery.performActions();
	}

	private static final Log _log = LogFactoryUtil.getLog(DictCollectionTempIndexer.class);

}