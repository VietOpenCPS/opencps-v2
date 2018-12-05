package org.opencps.datamgt.service.indexer;

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

import java.util.Locale;

import javax.portlet.PortletRequest;
import javax.portlet.PortletResponse;

import org.opencps.datamgt.constants.DictCollectionTerm;
import org.opencps.datamgt.model.DictCollection;
import org.opencps.datamgt.service.DictCollectionLocalServiceUtil;
import org.osgi.service.component.annotations.Component;

@Component(
    immediate = true,
    service = BaseIndexer.class
)
public class DictCollectionIndexer extends BaseIndexer<DictCollection> {

	public static final String CLASS_NAME = DictCollection.class.getName();

	@Override
	public String getClassName() {
		return CLASS_NAME;
	}

	@Override
	public void postProcessSearchQuery(BooleanQuery searchQuery, BooleanFilter fullQueryBooleanFilter,
			SearchContext searchContext) throws Exception {

	}

	@Override
	protected void doDelete(DictCollection dictCollection) throws Exception {
		deleteDocument(dictCollection.getCompanyId(), dictCollection.getDictCollectionId());
	}

	@Override
	protected Document doGetDocument(DictCollection dictCollection) throws Exception {
		Document document = getBaseModelDocument(CLASS_NAME, dictCollection);

		document.addKeywordSortable(Field.COMPANY_ID, String.valueOf(dictCollection.getCompanyId()));
		document.addDateSortable(Field.MODIFIED_DATE, dictCollection.getModifiedDate());
		document.addKeywordSortable(Field.NAME, String.valueOf(dictCollection.getCollectionCode()));
		document.addKeywordSortable(Field.USER_ID, String.valueOf(dictCollection.getUserId()));
		//document.addKeywordSortable(Field.USER_NAME, String.valueOf(dictCollection.getUserName()));
		//document.addKeywordSortable(Field.USER_NAME, String.valueOf(dictCollection.getCollectionCode()));
		

		document.addNumberSortable(DictCollectionTerm.GROUP_ID, dictCollection.getGroupId());
		document.addNumberSortable(DictCollectionTerm.DICT_COLLECTION_ID, dictCollection.getDictCollectionId());
		document.addTextSortable(DictCollectionTerm.COLLECTION_CODE, dictCollection.getCollectionCode());
		document.addTextSortable(DictCollectionTerm.COLLECTION_NAME, dictCollection.getCollectionName());
		document.addTextSortable(DictCollectionTerm.COLLECTION_NAME_EN, dictCollection.getCollectionNameEN());
		document.addTextSortable(DictCollectionTerm.DESCRIPTION, dictCollection.getDescription());
		document.addTextSortable(DictCollectionTerm.DATAFORM, dictCollection.getDataForm());
		document.addNumberSortable(DictCollectionTerm.STATUS, dictCollection.getStatus());
		
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
	protected void doReindex(DictCollection dictCollection) throws Exception {
		Document document = getDocument(dictCollection);
		IndexWriterHelperUtil.updateDocument(getSearchEngineId(), dictCollection.getCompanyId(), document,
				isCommitImmediately());
	}

	@Override
	protected void doReindex(String className, long classPK) throws Exception {
		DictCollection dictCollection = DictCollectionLocalServiceUtil.getDictCollection(classPK);
		doReindex(dictCollection);
	}

	@Override
	protected void doReindex(String[] ids) throws Exception {
		long companyId = GetterUtil.getLong(ids[0]);
		reindexDictCollection(companyId);
	}

	protected void reindexDictCollection(long companyId) throws PortalException {
		final IndexableActionableDynamicQuery indexableActionableDynamicQuery = DictCollectionLocalServiceUtil
				.getIndexableActionableDynamicQuery();

		indexableActionableDynamicQuery.setCompanyId(companyId);
		indexableActionableDynamicQuery
				.setPerformActionMethod(new ActionableDynamicQuery.PerformActionMethod<DictCollection>() {

					@Override
					public void performAction(DictCollection dictCollection) {
						try {
							Document document = getDocument(dictCollection);

							indexableActionableDynamicQuery.addDocuments(document);
						} catch (PortalException pe) {
							_log.debug(pe);
							//_log.error(e);
							if (_log.isWarnEnabled()) {
								_log.warn("Unable to index DictCollectionIndexer " + dictCollection.getDictCollectionId());
							}
						}
					}

				});
		indexableActionableDynamicQuery.setSearchEngineId(getSearchEngineId());

		indexableActionableDynamicQuery.performActions();
	}

	private static final Log _log = LogFactoryUtil.getLog(DictCollectionIndexer.class);

}