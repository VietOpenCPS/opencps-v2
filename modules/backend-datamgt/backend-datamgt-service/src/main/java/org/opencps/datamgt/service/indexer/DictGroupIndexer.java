package org.opencps.datamgt.service.indexer;

import com.liferay.petra.string.StringPool;
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

import java.util.Locale;

import javax.portlet.PortletRequest;
import javax.portlet.PortletResponse;

import org.opencps.datamgt.constants.DictGroupTerm;
import org.opencps.datamgt.model.DictCollection;
import org.opencps.datamgt.model.DictGroup;
import org.opencps.datamgt.service.DictCollectionLocalServiceUtil;
import org.opencps.datamgt.service.DictGroupLocalServiceUtil;
import org.osgi.service.component.annotations.Component;

@Component(
    immediate = true,
    service = BaseIndexer.class
)
public class DictGroupIndexer extends BaseIndexer<DictGroup> {

	public static final String CLASS_NAME = DictGroup.class.getName();

	@Override
	public String getClassName() {
		return CLASS_NAME;
	}

	@Override
	public void postProcessSearchQuery(BooleanQuery searchQuery, BooleanFilter fullQueryBooleanFilter,
			SearchContext searchContext) throws Exception {

	}

	@Override
	protected void doDelete(DictGroup dictGroup) throws Exception {
		deleteDocument(dictGroup.getCompanyId(), dictGroup.getDictGroupId());
	}

	@Override
	protected Document doGetDocument(DictGroup dictGroup) throws Exception {
		Document document = getBaseModelDocument(CLASS_NAME, dictGroup);

		document.addKeywordSortable(Field.COMPANY_ID, String.valueOf(dictGroup.getCompanyId()));
		document.addDateSortable(Field.MODIFIED_DATE, dictGroup.getModifiedDate());
		document.addKeywordSortable(Field.USER_ID, String.valueOf(dictGroup.getUserId()));
		document.addKeywordSortable(Field.USER_NAME, String.valueOf(dictGroup.getUserName()));

		document.addNumberSortable(DictGroupTerm.GROUP_ID, dictGroup.getGroupId());
		document.addNumberSortable(DictGroupTerm.DICT_GROUPID, dictGroup.getDictGroupId());
		document.addNumberSortable(DictGroupTerm.DICT_COLLECTIONID, dictGroup.getDictCollectionId());
		document.addTextSortable(DictGroupTerm.GROUP_CODE, dictGroup.getGroupCode());
		document.addTextSortable(DictGroupTerm.GROUP_NAME, dictGroup.getGroupName());
		document.addTextSortable(DictGroupTerm.GROUP_NAME_EN, dictGroup.getGroupNameEN());
		document.addTextSortable(DictGroupTerm.GROUP_DESCRIPTION, dictGroup.getGroupDescription());
		
		DictCollection dictCollection = DictCollectionLocalServiceUtil.fetchDictCollection(dictGroup.getDictCollectionId());
		
		String dictCollectionCode = Validator.isNotNull(dictCollection)?dictCollection.getCollectionCode():StringPool.BLANK;
		
		document.addTextSortable(DictGroupTerm.DICT_COLLECTION_CODE, dictCollectionCode);
		
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
	protected void doReindex(DictGroup dictGroup) throws Exception {
		Document document = getDocument(dictGroup);
		IndexWriterHelperUtil.updateDocument(getSearchEngineId(), dictGroup.getCompanyId(), document,
				isCommitImmediately());
	}

	@Override
	protected void doReindex(String className, long classPK) throws Exception {
		DictGroup dictGroup = DictGroupLocalServiceUtil.getDictGroup(classPK);
		doReindex(dictGroup);
	}

	@Override
	protected void doReindex(String[] ids) throws Exception {
		long companyId = GetterUtil.getLong(ids[0]);
		reindexDictGroup(companyId);
	}

	protected void reindexDictGroup(long companyId) throws PortalException {
		final IndexableActionableDynamicQuery indexableActionableDynamicQuery = DictGroupLocalServiceUtil
				.getIndexableActionableDynamicQuery();

		indexableActionableDynamicQuery.setCompanyId(companyId);
		indexableActionableDynamicQuery
				.setPerformActionMethod(new ActionableDynamicQuery.PerformActionMethod<DictGroup>() {

					@Override
					public void performAction(DictGroup dictGroup) {
						try {
							Document document = getDocument(dictGroup);

							indexableActionableDynamicQuery.addDocuments(document);
						} catch (PortalException pe) {
							_log.debug(pe);
							//_log.error(e);
							if (_log.isWarnEnabled()) {
								_log.warn("Unable to index DictGroupIndexer " + dictGroup.getDictGroupId());
							}
						}
					}

				});
		indexableActionableDynamicQuery.setSearchEngineId(getSearchEngineId());

		indexableActionableDynamicQuery.performActions();
	}

	private static final Log _log = LogFactoryUtil.getLog(DictGroupIndexer.class);

}