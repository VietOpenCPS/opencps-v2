package org.opencps.synchronization.service.indexer;

import java.util.Locale;

import javax.portlet.PortletRequest;
import javax.portlet.PortletResponse;

import org.opencps.synchronization.constants.DictGroupTempTerm;
import org.opencps.synchronization.model.DictCollectionTemp;
import org.opencps.synchronization.model.DictGroupTemp;
import org.opencps.synchronization.service.DictCollectionTempLocalServiceUtil;
import org.opencps.synchronization.service.DictGroupTempLocalServiceUtil;
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
public class DictGroupTempIndexer extends BaseIndexer<DictGroupTemp> {

	public static final String CLASS_NAME = DictGroupTemp.class.getName();

	@Override
	public String getClassName() {
		return CLASS_NAME;
	}

	@Override
	public void postProcessSearchQuery(BooleanQuery searchQuery, BooleanFilter fullQueryBooleanFilter,
			SearchContext searchContext) throws Exception {

	}

	@Override
	protected void doDelete(DictGroupTemp dictGroup) throws Exception {
		deleteDocument(dictGroup.getCompanyId(), dictGroup.getDictGroupId());
	}

	@Override
	protected Document doGetDocument(DictGroupTemp dictGroup) throws Exception {
		Document document = getBaseModelDocument(CLASS_NAME, dictGroup);

		document.addKeywordSortable(Field.COMPANY_ID, String.valueOf(dictGroup.getCompanyId()));
		document.addDateSortable(Field.MODIFIED_DATE, dictGroup.getModifiedDate());
		document.addKeywordSortable(Field.USER_ID, String.valueOf(dictGroup.getUserId()));
		document.addKeywordSortable(Field.USER_NAME, String.valueOf(dictGroup.getUserName()));

		document.addNumberSortable(DictGroupTempTerm.GROUP_ID, dictGroup.getGroupId());
		document.addNumberSortable(DictGroupTempTerm.DICT_GROUPID, dictGroup.getDictGroupId());
		document.addNumberSortable(DictGroupTempTerm.DICT_COLLECTIONID, dictGroup.getDictCollectionId());
		document.addTextSortable(DictGroupTempTerm.GROUP_CODE, dictGroup.getGroupCode());
		document.addTextSortable(DictGroupTempTerm.GROUP_NAME, dictGroup.getGroupName());
		document.addTextSortable(DictGroupTempTerm.GROUP_NAME_EN, dictGroup.getGroupNameEN());
		document.addTextSortable(DictGroupTempTerm.GROUP_DESCRIPTION, dictGroup.getGroupDescription());
		document.addNumber(DictGroupTempTerm.STATUS, dictGroup.getStatus());
		
		DictCollectionTemp dictCollection = DictCollectionTempLocalServiceUtil.fetchDictCollectionTemp(dictGroup.getDictCollectionId());
		
		String dictCollectionCode = Validator.isNotNull(dictCollection)?dictCollection.getCollectionCode():StringPool.BLANK;
		
		document.addTextSortable(DictGroupTempTerm.DICT_COLLECTION_CODE, dictCollectionCode);
		
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
	protected void doReindex(DictGroupTemp dictGroup) throws Exception {
		Document document = getDocument(dictGroup);
		IndexWriterHelperUtil.updateDocument(getSearchEngineId(), dictGroup.getCompanyId(), document,
				isCommitImmediately());
	}

	@Override
	protected void doReindex(String className, long classPK) throws Exception {
		DictGroupTemp dictGroup = DictGroupTempLocalServiceUtil.getDictGroupTemp(classPK);
		doReindex(dictGroup);
	}

	@Override
	protected void doReindex(String[] ids) throws Exception {
		long companyId = GetterUtil.getLong(ids[0]);
		reindexDictGroupTemp(companyId);
	}

	protected void reindexDictGroupTemp(long companyId) throws PortalException {
		final IndexableActionableDynamicQuery indexableActionableDynamicQuery = DictGroupTempLocalServiceUtil
				.getIndexableActionableDynamicQuery();

		indexableActionableDynamicQuery.setCompanyId(companyId);
		indexableActionableDynamicQuery
				.setPerformActionMethod(new ActionableDynamicQuery.PerformActionMethod<DictGroupTemp>() {

					@Override
					public void performAction(DictGroupTemp dictGroup) {
						try {
							Document document = getDocument(dictGroup);

							indexableActionableDynamicQuery.addDocuments(document);
						} catch (PortalException pe) {
							if (_log.isWarnEnabled()) {
								_log.warn("Unable to index DictGroupTempIndexer " + dictGroup.getDictGroupId(), pe);
							}
						}
					}

				});
		indexableActionableDynamicQuery.setSearchEngineId(getSearchEngineId());

		indexableActionableDynamicQuery.performActions();
	}

	private static final Log _log = LogFactoryUtil.getLog(DictGroupTempIndexer.class);

}