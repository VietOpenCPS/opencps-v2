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
import org.opencps.datamgt.constants.DictItemGroupTerm;
import org.opencps.datamgt.model.DictCollection;
import org.opencps.datamgt.model.DictGroup;
import org.opencps.datamgt.model.DictItem;
import org.opencps.datamgt.model.DictItemGroup;
import org.opencps.datamgt.service.DictCollectionLocalServiceUtil;
import org.opencps.datamgt.service.DictGroupLocalServiceUtil;
import org.opencps.datamgt.service.DictItemGroupLocalServiceUtil;
import org.opencps.datamgt.service.DictItemLocalServiceUtil;
import org.osgi.service.component.annotations.Component;

@Component(
    immediate = true,
    service = BaseIndexer.class
)
public class DictItemGroupIndexer extends BaseIndexer<DictItemGroup> {

	public static final String CLASS_NAME = DictItemGroup.class.getName();

	@Override
	public String getClassName() {
		return CLASS_NAME;
	}

	@Override
	public void postProcessSearchQuery(BooleanQuery searchQuery, BooleanFilter fullQueryBooleanFilter,
			SearchContext searchContext) throws Exception {

	}

	@Override
	protected void doDelete(DictItemGroup dictItemGroup) throws Exception {
		deleteDocument(dictItemGroup.getCompanyId(), dictItemGroup.getDictItemGroupId());
	}

	@Override
	protected Document doGetDocument(DictItemGroup dictItemGroup) throws Exception {
		Document document = getBaseModelDocument(CLASS_NAME, dictItemGroup);

		document.addKeywordSortable(Field.COMPANY_ID, String.valueOf(dictItemGroup.getCompanyId()));
		document.addDateSortable(Field.MODIFIED_DATE, dictItemGroup.getModifiedDate());
		document.addKeywordSortable(Field.USER_ID, String.valueOf(dictItemGroup.getUserId()));
		document.addKeywordSortable(Field.USER_NAME, String.valueOf(dictItemGroup.getUserName()));

		document.addNumberSortable(DictItemGroupTerm.GROUP_ID, dictItemGroup.getGroupId());
		document.addNumberSortable(DictItemGroupTerm.DICT_ITEM_GROUP_ID, dictItemGroup.getDictItemGroupId());
		document.addNumberSortable(DictItemGroupTerm.DICT_GROUP_ID, dictItemGroup.getDictGroupId());
		document.addNumberSortable(DictItemGroupTerm.DICT_ITEM_ID, dictItemGroup.getDictItemId());
		document.addTextSortable(DictItemGroupTerm.DICT_GROUP_NAME, dictItemGroup.getDictGroupName());
		
		DictGroup dictGroup = DictGroupLocalServiceUtil.fetchDictGroup(dictItemGroup.getDictGroupId());
		
		
		if(Validator.isNotNull(dictGroup)){
			DictCollection collection = DictCollectionLocalServiceUtil.fetchDictCollection(dictGroup.getDictCollectionId());
			
			String collectionCode = StringPool.BLANK;
			
			if (Validator.isNotNull(collection)) {
				collectionCode = collection.getCollectionCode();
			}
			
			document.addNumberSortable(DictGroupTerm.DICT_GROUPID, dictGroup.getDictGroupId());
			document.addNumberSortable(DictGroupTerm.DICT_COLLECTIONID, dictGroup.getDictCollectionId());
			document.addTextSortable(DictGroupTerm.DICT_COLLECTION_CODE, collectionCode);
			document.addTextSortable(DictGroupTerm.GROUP_CODE, dictGroup.getGroupCode());
			
			//_log.info(dictGroup.getGroupCode());
			document.addTextSortable(DictGroupTerm.GROUP_NAME, dictGroup.getGroupName());
			document.addTextSortable(DictGroupTerm.GROUP_NAME_EN, dictGroup.getGroupNameEN());
			document.addTextSortable(DictGroupTerm.GROUP_DESCRIPTION, dictGroup.getGroupDescription());
			
		}
		
		DictItem dictItem = DictItemLocalServiceUtil.fetchDictItem(dictItemGroup.getDictItemId());
		
		if(Validator.isNotNull(dictItem)){
			
			document.addTextSortable(DictItemGroupTerm.ITEM_CODE, dictItem.getItemCode());
			document.addTextSortable(DictItemGroupTerm.ITEM_NAME, dictItem.getItemName());
			document.addTextSortable(DictItemGroupTerm.ITEM_NAME_EN, dictItem.getItemNameEN());
			document.addTextSortable(DictItemGroupTerm.ITEM_DESCRIPTION, dictItem.getItemDescription());
			document.addTextSortable(DictItemGroupTerm.SELECTED, Boolean.TRUE.toString());
			
		}
		

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
	protected void doReindex(DictItemGroup dictItemGroup) throws Exception {
		Document document = getDocument(dictItemGroup);
		IndexWriterHelperUtil.updateDocument(getSearchEngineId(), dictItemGroup.getCompanyId(), document,
				isCommitImmediately());
	}

	@Override
	protected void doReindex(String className, long classPK) throws Exception {
		DictItemGroup dictItemGroup = DictItemGroupLocalServiceUtil.getDictItemGroup(classPK);
		doReindex(dictItemGroup);
	}

	@Override
	protected void doReindex(String[] ids) throws Exception {
		long companyId = GetterUtil.getLong(ids[0]);
		reindexDictItemGroup(companyId);
	}

	protected void reindexDictItemGroup(long companyId) throws PortalException {
		final IndexableActionableDynamicQuery indexableActionableDynamicQuery = DictItemGroupLocalServiceUtil
				.getIndexableActionableDynamicQuery();

		indexableActionableDynamicQuery.setCompanyId(companyId);
		indexableActionableDynamicQuery
				.setPerformActionMethod(new ActionableDynamicQuery.PerformActionMethod<DictItemGroup>() {

					@Override
					public void performAction(DictItemGroup dictItemGroup) {
						try {
							Document document = getDocument(dictItemGroup);

							indexableActionableDynamicQuery.addDocuments(document);
						} catch (PortalException pe) {
							_log.debug(pe);
							//_log.error(e);
							if (_log.isWarnEnabled()) {
								_log.warn("Unable to index DictItemGroupIndexer " + dictItemGroup.getDictItemGroupId());
							}
						}
					}

				});
		indexableActionableDynamicQuery.setSearchEngineId(getSearchEngineId());

		indexableActionableDynamicQuery.performActions();
	}

	private static final Log _log = LogFactoryUtil.getLog(DictItemGroupIndexer.class);

}