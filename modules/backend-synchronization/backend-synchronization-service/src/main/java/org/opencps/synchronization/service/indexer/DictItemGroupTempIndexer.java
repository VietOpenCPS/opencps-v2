package org.opencps.synchronization.service.indexer;

import java.util.Locale;

import javax.portlet.PortletRequest;
import javax.portlet.PortletResponse;

import org.opencps.synchronization.constants.DictGroupTempTerm;
import org.opencps.synchronization.constants.DictItemGroupTempTerm;
import org.opencps.synchronization.model.DictCollectionTemp;
import org.opencps.synchronization.model.DictGroupTemp;
import org.opencps.synchronization.model.DictItemGroupTemp;
import org.opencps.synchronization.model.DictItemTemp;
import org.opencps.synchronization.service.DictCollectionTempLocalServiceUtil;
import org.opencps.synchronization.service.DictGroupTempLocalServiceUtil;
import org.opencps.synchronization.service.DictItemGroupTempLocalServiceUtil;
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
public class DictItemGroupTempIndexer extends BaseIndexer<DictItemGroupTemp> {

	public static final String CLASS_NAME = DictItemGroupTemp.class.getName();

	@Override
	public String getClassName() {
		return CLASS_NAME;
	}

	@Override
	public void postProcessSearchQuery(BooleanQuery searchQuery, BooleanFilter fullQueryBooleanFilter,
			SearchContext searchContext) throws Exception {

	}

	@Override
	protected void doDelete(DictItemGroupTemp dictItemGroup) throws Exception {
		deleteDocument(dictItemGroup.getCompanyId(), dictItemGroup.getDictItemGroupId());
	}

	@Override
	protected Document doGetDocument(DictItemGroupTemp dictItemGroup) throws Exception {
		Document document = getBaseModelDocument(CLASS_NAME, dictItemGroup);

		document.addKeywordSortable(Field.COMPANY_ID, String.valueOf(dictItemGroup.getCompanyId()));
		document.addDateSortable(Field.MODIFIED_DATE, dictItemGroup.getModifiedDate());
		document.addKeywordSortable(Field.USER_ID, String.valueOf(dictItemGroup.getUserId()));
		document.addKeywordSortable(Field.USER_NAME, String.valueOf(dictItemGroup.getUserName()));

		document.addNumberSortable(DictItemGroupTempTerm.GROUP_ID, dictItemGroup.getGroupId());
		document.addNumberSortable(DictItemGroupTempTerm.DICT_ITEM_GROUP_ID, dictItemGroup.getDictItemGroupId());
		document.addNumberSortable(DictItemGroupTempTerm.DICT_GROUP_ID, dictItemGroup.getDictGroupId());
		document.addNumberSortable(DictItemGroupTempTerm.DICT_ITEM_ID, dictItemGroup.getDictItemId());
		document.addTextSortable(DictItemGroupTempTerm.DICT_GROUP_NAME, dictItemGroup.getDictGroupName());
		
		DictGroupTemp dictGroup = DictGroupTempLocalServiceUtil.fetchDictGroupTemp(dictItemGroup.getDictGroupId());
		
		
		if(Validator.isNotNull(dictGroup)){
			DictCollectionTemp collection = DictCollectionTempLocalServiceUtil.fetchDictCollectionTemp(dictGroup.getDictCollectionId());
			
			String collectionCode = StringPool.BLANK;
			
			if (Validator.isNotNull(collection)) {
				collectionCode = collection.getCollectionCode();
			}
			
			document.addNumberSortable(DictGroupTempTerm.DICT_GROUPID, dictGroup.getDictGroupId());
			document.addNumberSortable(DictGroupTempTerm.DICT_COLLECTIONID, dictGroup.getDictCollectionId());
			document.addTextSortable(DictGroupTempTerm.DICT_COLLECTION_CODE, collectionCode);
			document.addTextSortable(DictGroupTempTerm.GROUP_CODE, dictGroup.getGroupCode());
			
			_log.info(dictGroup.getGroupCode());
			document.addTextSortable(DictGroupTempTerm.GROUP_NAME, dictGroup.getGroupName());
			document.addTextSortable(DictGroupTempTerm.GROUP_NAME_EN, dictGroup.getGroupNameEN());
			document.addTextSortable(DictGroupTempTerm.GROUP_DESCRIPTION, dictGroup.getGroupDescription());
			
		}
		
		DictItemTemp dictItem = DictItemTempLocalServiceUtil.fetchDictItemTemp(dictItemGroup.getDictItemId());
		
		if(Validator.isNotNull(dictItem)){
			
			document.addTextSortable(DictItemGroupTempTerm.ITEM_CODE, dictItem.getItemCode());
			document.addTextSortable(DictItemGroupTempTerm.ITEM_NAME, dictItem.getItemName());
			document.addTextSortable(DictItemGroupTempTerm.ITEM_NAME_EN, dictItem.getItemNameEN());
			document.addTextSortable(DictItemGroupTempTerm.ITEM_DESCRIPTION, dictItem.getItemDescription());
			document.addTextSortable(DictItemGroupTempTerm.SELECTED, Boolean.TRUE.toString());
			
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
	protected void doReindex(DictItemGroupTemp dictItemGroup) throws Exception {
		Document document = getDocument(dictItemGroup);
		IndexWriterHelperUtil.updateDocument(getSearchEngineId(), dictItemGroup.getCompanyId(), document,
				isCommitImmediately());
	}

	@Override
	protected void doReindex(String className, long classPK) throws Exception {
		DictItemGroupTemp dictItemGroup = DictItemGroupTempLocalServiceUtil.getDictItemGroupTemp(classPK);
		doReindex(dictItemGroup);
	}

	@Override
	protected void doReindex(String[] ids) throws Exception {
		long companyId = GetterUtil.getLong(ids[0]);
		reindexDictItemGroupTemp(companyId);
	}

	protected void reindexDictItemGroupTemp(long companyId) throws PortalException {
		final IndexableActionableDynamicQuery indexableActionableDynamicQuery = DictItemGroupTempLocalServiceUtil
				.getIndexableActionableDynamicQuery();

		indexableActionableDynamicQuery.setCompanyId(companyId);
		indexableActionableDynamicQuery
				.setPerformActionMethod(new ActionableDynamicQuery.PerformActionMethod<DictItemGroupTemp>() {

					@Override
					public void performAction(DictItemGroupTemp dictItemGroup) {
						try {
							Document document = getDocument(dictItemGroup);

							indexableActionableDynamicQuery.addDocuments(document);
						} catch (PortalException pe) {
							if (_log.isWarnEnabled()) {
								_log.warn("Unable to index DictItemGroupTempIndexer " + dictItemGroup.getDictItemGroupId(), pe);
							}
						}
					}

				});
		indexableActionableDynamicQuery.setSearchEngineId(getSearchEngineId());

		indexableActionableDynamicQuery.performActions();
	}

	private static final Log _log = LogFactoryUtil.getLog(DictItemGroupTempIndexer.class);

}