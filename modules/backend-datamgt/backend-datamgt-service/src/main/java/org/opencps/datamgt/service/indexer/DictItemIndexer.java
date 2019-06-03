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

import org.opencps.datamgt.constants.DictItemTerm;
import org.opencps.datamgt.model.DictCollection;
import org.opencps.datamgt.model.DictItem;
import org.opencps.datamgt.service.DictCollectionLocalServiceUtil;
import org.opencps.datamgt.service.DictItemLocalServiceUtil;
import org.osgi.service.component.annotations.Component;

@Component(
    immediate = true,
    service = BaseIndexer.class
)
public class DictItemIndexer extends BaseIndexer<DictItem> {

	public static final String CLASS_NAME = DictItem.class.getName();

	@Override
	public String getClassName() {
		return CLASS_NAME;
	}

	@Override
	public void postProcessSearchQuery(
			BooleanQuery searchQuery, BooleanFilter fullQueryBooleanFilter,
			SearchContext searchContext)
		throws Exception {

	}

	@Override
	protected void doDelete(DictItem dictItem) throws Exception {
		deleteDocument(dictItem.getCompanyId(), dictItem.getDictItemId());
	}

	@Override
	protected Document doGetDocument(DictItem dictItem) throws Exception {
		Document document = getBaseModelDocument(CLASS_NAME, dictItem);

		document.addKeywordSortable(Field.COMPANY_ID, String.valueOf(dictItem.getCompanyId()));
		document.addDateSortable(Field.MODIFIED_DATE, dictItem.getModifiedDate());
		document.addKeywordSortable(Field.USER_ID, String.valueOf(dictItem.getUserId()));
		document.addKeywordSortable(Field.USER_NAME, String.valueOf(dictItem.getUserName()));

		document.addNumberSortable(DictItemTerm.GROUP_ID, dictItem.getGroupId());
		document.addNumberSortable(DictItemTerm.DICT_ITEM_ID, dictItem.getDictItemId());
		document.addNumberSortable(DictItemTerm.DICT_COLLECTION_ID, dictItem.getDictCollectionId());
		document.addTextSortable(DictItemTerm.ITEM_CODE, dictItem.getItemCode());
		document.addTextSortable(DictItemTerm.ITEM_NAME, dictItem.getItemName());
		document.addTextSortable(DictItemTerm.ITEM_NAME_EN, dictItem.getItemNameEN());
		if (Validator.isNotNull(dictItem.getItemDescription())) {
			document.addTextSortable(DictItemTerm.ITEM_DESCRIPTION, dictItem.getItemDescription());			
		}
		document.addNumberSortable(DictItemTerm.PARENT_ITEM_ID, dictItem.getParentItemId());
		document.addTextSortable(DictItemTerm.SIBLING, dictItem.getSibling());
		document.addNumberSortable(DictItemTerm.SIBLING_SEARCH, GetterUtil.getInteger(dictItem.getSibling()));
		document.addTextSortable(DictItemTerm.TREE_INDEX, dictItem.getTreeIndex());
		document.addNumberSortable(DictItemTerm.LEVEL, dictItem.getLevel());
		if (Validator.isNotNull(dictItem.getMetaData())) {
			document.addTextSortable(DictItemTerm.META_DATA, dictItem.getMetaData());			
		}

		long dictCollectionId = dictItem.getDictCollectionId();
		
		DictCollection dictCollection = DictCollectionLocalServiceUtil.fetchDictCollection(dictCollectionId);
		
		if(Validator.isNotNull(dictCollection)){
			document.addTextSortable(DictItemTerm.DICT_COLLECTION_CODE, dictCollection.getCollectionCode());
		}
		String parentCode = StringPool.BLANK;
		
		if(dictItem.getParentItemId() > 0){
			
			DictItem parentItem = DictItemLocalServiceUtil.fetchDictItem(dictItem.getParentItemId());
			
			if (Validator.isNotNull(parentItem) && Validator.isNotNull(parentItem.getItemCode())) {
				parentCode = parentItem.getItemCode();
			}
			//else {
			//	_log.info(parentItem.getItemCode());
			//}
			
		}  else {
			parentCode = "0";
		}
		
		document.addTextSortable(DictItemTerm.PARENT_ITEM_CODE, parentCode);
		
		document.setSortableTextFields(new String[]{DictItemTerm.TREE_INDEX});
		
		return document;
	}

	@Override
	protected Summary doGetSummary(
		Document document, Locale locale, String snippet,
		PortletRequest portletRequest, PortletResponse portletResponse) {

		Summary summary = createSummary(document);

		summary.setMaxContentLength(QueryUtil.ALL_POS);
		
		return summary;
	}

	@Override
	protected void doReindex(DictItem dictItem) throws Exception {
		Document document = getDocument(dictItem);

		IndexWriterHelperUtil.updateDocument(
			getSearchEngineId(), dictItem.getCompanyId(), document,
			isCommitImmediately());
	}

	@Override
	protected void doReindex(String className, long classPK) throws Exception {
		DictItem DictItem = DictItemLocalServiceUtil.getDictItem(classPK);

		doReindex(DictItem);
	}

	@Override
	protected void doReindex(String[] ids) throws Exception {
		long companyId = GetterUtil.getLong(ids[0]);

		reindexDictItem(companyId);
	}

	protected void reindexDictItem(long companyId) throws PortalException {
		final IndexableActionableDynamicQuery indexableActionableDynamicQuery =
			DictItemLocalServiceUtil.getIndexableActionableDynamicQuery();

		indexableActionableDynamicQuery.setCompanyId(companyId);
		indexableActionableDynamicQuery.setPerformActionMethod(
			new ActionableDynamicQuery.PerformActionMethod<DictItem>() {

				@Override
				public void performAction(DictItem dictItem) {
					try {
						Document document = getDocument(dictItem);

						indexableActionableDynamicQuery.addDocuments(document);
					}
					catch (PortalException pe) {
						if (_log.isWarnEnabled()) {
							_log.warn(
								"Unable to index contact " +
										dictItem.getDictItemId(),
								pe);
						}
					}
				}

			});
		indexableActionableDynamicQuery.setSearchEngineId(getSearchEngineId());

		indexableActionableDynamicQuery.performActions();
	}

	private static final Log _log = LogFactoryUtil.getLog(
		DictItemIndexer.class);

}