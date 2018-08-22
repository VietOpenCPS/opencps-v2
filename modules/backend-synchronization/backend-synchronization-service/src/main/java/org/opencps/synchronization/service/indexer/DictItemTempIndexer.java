package org.opencps.synchronization.service.indexer;

import java.util.Locale;

import javax.portlet.PortletRequest;
import javax.portlet.PortletResponse;

import org.opencps.synchronization.constants.DictItemTempTerm;
import org.opencps.synchronization.model.DictCollectionTemp;
import org.opencps.synchronization.model.DictItemTemp;
import org.opencps.synchronization.service.DictCollectionTempLocalServiceUtil;
import org.opencps.synchronization.service.DictItemTempLocalServiceUtil;
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
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;

@Component(
    immediate = true,
    service = BaseIndexer.class
)
public class DictItemTempIndexer extends BaseIndexer<DictItemTemp> {

	public static final String CLASS_NAME = DictItemTemp.class.getName();

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
	protected void doDelete(DictItemTemp dictItem) throws Exception {
		deleteDocument(dictItem.getCompanyId(), dictItem.getDictItemId());
	}

	@Override
	protected Document doGetDocument(DictItemTemp dictItem) throws Exception {
		Document document = getBaseModelDocument(CLASS_NAME, dictItem);

		document.addKeywordSortable(Field.COMPANY_ID, String.valueOf(dictItem.getCompanyId()));
		document.addDateSortable(Field.MODIFIED_DATE, dictItem.getModifiedDate());
		document.addKeywordSortable(Field.USER_ID, String.valueOf(dictItem.getUserId()));
		document.addKeywordSortable(Field.USER_NAME, String.valueOf(dictItem.getUserName()));

		document.addNumberSortable(DictItemTempTerm.GROUP_ID, dictItem.getGroupId());
		document.addNumberSortable(DictItemTempTerm.DICT_ITEM_ID, dictItem.getDictItemId());
		document.addNumberSortable(DictItemTempTerm.DICT_COLLECTION_ID, dictItem.getDictCollectionId());
		document.addTextSortable(DictItemTempTerm.ITEM_CODE, dictItem.getItemCode());
		document.addTextSortable(DictItemTempTerm.ITEM_NAME, dictItem.getItemName());
		document.addTextSortable(DictItemTempTerm.ITEM_NAME_EN, dictItem.getItemNameEN());
		if (Validator.isNotNull(dictItem.getItemDescription())) {
			document.addTextSortable(DictItemTempTerm.ITEM_DESCRIPTION, dictItem.getItemDescription());			
		}
		document.addNumberSortable(DictItemTempTerm.PARENT_ITEM_ID, dictItem.getParentItemId());
		document.addTextSortable(DictItemTempTerm.SIBLING, dictItem.getSibling());
		document.addTextSortable(DictItemTempTerm.TREE_INDEX, dictItem.getTreeIndex());
		document.addNumberSortable(DictItemTempTerm.LEVEL, dictItem.getLevel());
		if (Validator.isNotNull(dictItem.getMetaData())) {
			document.addTextSortable(DictItemTempTerm.META_DATA, dictItem.getMetaData());			
		}
		document.addNumber(DictItemTempTerm.STATUS, dictItem.getStatus());
		
		long dictCollectionId = dictItem.getDictCollectionId();
		
		DictCollectionTemp dictCollection = DictCollectionTempLocalServiceUtil.fetchDictCollectionTemp(dictCollectionId);
		
		if(Validator.isNotNull(dictCollection)){
			document.addTextSortable(DictItemTempTerm.DICT_COLLECTION_CODE, dictCollection.getCollectionCode());
		}
		
		String parentCode = StringPool.BLANK;
		
		if(dictItem.getParentItemId() > 0){
			
			DictItemTemp parentItem = DictItemTempLocalServiceUtil.fetchDictItemTemp(dictItem.getParentItemId());
			
			if (Validator.isNotNull(parentItem) && Validator.isNotNull(parentItem.getItemCode())) {
				parentCode = parentItem.getItemCode();
			} else {
				_log.info(parentItem.getItemCode());
			}
			
		}  else {
			parentCode = "0";
		}
		
		document.addTextSortable(DictItemTempTerm.PARENT_ITEM_CODE, parentCode);
		
		document.setSortableTextFields(new String[]{DictItemTempTerm.TREE_INDEX});
		
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
	protected void doReindex(DictItemTemp dictItem) throws Exception {
		Document document = getDocument(dictItem);

		IndexWriterHelperUtil.updateDocument(
			getSearchEngineId(), dictItem.getCompanyId(), document,
			isCommitImmediately());
	}

	@Override
	protected void doReindex(String className, long classPK) throws Exception {
		DictItemTemp dictItemTemp = DictItemTempLocalServiceUtil.getDictItemTemp(classPK);

		doReindex(dictItemTemp);
	}

	@Override
	protected void doReindex(String[] ids) throws Exception {
		long companyId = GetterUtil.getLong(ids[0]);

		reindexDictItemTemp(companyId);
	}

	protected void reindexDictItemTemp(long companyId) throws PortalException {
		final IndexableActionableDynamicQuery indexableActionableDynamicQuery =
			DictItemTempLocalServiceUtil.getIndexableActionableDynamicQuery();

		indexableActionableDynamicQuery.setCompanyId(companyId);
		indexableActionableDynamicQuery.setPerformActionMethod(
			new ActionableDynamicQuery.PerformActionMethod<DictItemTemp>() {

				@Override
				public void performAction(DictItemTemp dictItem) {
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
		DictItemTempIndexer.class);

}