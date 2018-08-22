package org.opencps.dossiermgt.service.indexer;

import java.util.Locale;

import javax.portlet.PortletRequest;
import javax.portlet.PortletResponse;

import org.opencps.dossiermgt.constants.DossierStatisticTerm;
import org.opencps.dossiermgt.model.DossierStatistic;
import org.opencps.dossiermgt.service.DossierStatisticLocalServiceUtil;
import org.osgi.service.component.annotations.Component;

import com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.search.BaseIndexer;
import com.liferay.portal.kernel.search.Document;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.search.IndexWriterHelperUtil;
import com.liferay.portal.kernel.search.Summary;
import com.liferay.portal.kernel.util.GetterUtil;

@Component(
    immediate = true,
    service = BaseIndexer.class
)
public class DossierStatisticIndexer extends BaseIndexer<DossierStatistic> {
	public static final String CLASS_NAME = DossierStatistic.class.getName();

	@Override
	public String getClassName() {
		return CLASS_NAME;
	}

	@Override
	protected void doDelete(DossierStatistic object) throws Exception {
		deleteDocument(object.getCompanyId(), object.getPrimaryKey());

	}

	@Override
	protected Document doGetDocument(DossierStatistic object) throws Exception {
		Document document = getBaseModelDocument(CLASS_NAME, object);

		// Indexer of audit fields
		document.addNumberSortable(Field.COMPANY_ID, object.getCompanyId());
		document.addNumberSortable(Field.GROUP_ID, object.getGroupId());
		document.addDateSortable(Field.MODIFIED_DATE, object.getCreateDate());
		document.addDateSortable(Field.CREATE_DATE, object.getModifiedDate());
		document.addNumberSortable(Field.USER_ID, object.getUserId());
		document.addKeywordSortable(Field.USER_NAME, String.valueOf(object.getUserName()));
		document.addKeywordSortable(Field.ENTRY_CLASS_NAME, CLASS_NAME);
		document.addNumberSortable(Field.ENTRY_CLASS_PK, object.getPrimaryKey());

		// add number fields
		document.addNumberSortable(DossierStatisticTerm.DOSSIER_STATISTIC_ID, object.getDossierStatisticId());
		document.addNumberSortable(DossierStatisticTerm.MONTH, object.getMonth());
		document.addNumberSortable(DossierStatisticTerm.YEAR, object.getYear());
		document.addNumberSortable(DossierStatisticTerm.REMAINING_COUNT, object.getRemainingCount());
		document.addNumberSortable(DossierStatisticTerm.RECEIVED_COUNT, object.getReceivedCount());
		document.addNumberSortable(DossierStatisticTerm.ONLINE_COUNT, object.getOnlineCount());
		document.addNumberSortable(DossierStatisticTerm.UNDUE_COUNT, object.getUndueCount());
		document.addNumberSortable(DossierStatisticTerm.OVERDUE_COUNT, object.getOverdueCount());
		document.addNumberSortable(DossierStatisticTerm.ONTIME_COUNT, object.getOntimeCount());
		document.addNumberSortable(DossierStatisticTerm.OVERTIME_COUNT, object.getOvertimeCount());
		// add text fields
		document.addTextSortable(DossierStatisticTerm.GOV_AGENCY_CODE, object.getGovAgencyCode());
		document.addTextSortable(DossierStatisticTerm.GOV_AGENCY_NAME, object.getGovAgencyName());
		document.addTextSortable(DossierStatisticTerm.DOMAIN_CODE, object.getDomainCode());
		document.addTextSortable(DossierStatisticTerm.DOMAIN_NAME, object.getDomainName());
		
		
		document.addKeywordSortable(DossierStatisticTerm.REPORTING, object.getReporting());
		return document;
	}

	@Override
	protected Summary doGetSummary(Document document, Locale locale, String snippet, PortletRequest portletRequest,
			PortletResponse portletResponse) throws Exception {
		Summary summary = createSummary(document);

		summary.setMaxContentLength(QueryUtil.ALL_POS);

		return summary;
	}

	@Override
	protected void doReindex(String className, long classPK) throws Exception {
		DossierStatistic object = DossierStatisticLocalServiceUtil.getDossierStatistic(classPK);
		doReindex(object);

	}

	@Override
	protected void doReindex(String[] ids) throws Exception {
		long companyId = GetterUtil.getLong(ids[0]);
		reindex(companyId);

	}

	@Override
	protected void doReindex(DossierStatistic object) throws Exception {
		Document document = getDocument(object);
		IndexWriterHelperUtil.updateDocument(getSearchEngineId(), object.getCompanyId(), document,
				isCommitImmediately());
	}

	protected void reindex(long companyId) throws PortalException {
		final IndexableActionableDynamicQuery indexableActionableDynamicQuery = DossierStatisticLocalServiceUtil
				.getIndexableActionableDynamicQuery();

		indexableActionableDynamicQuery.setCompanyId(companyId);
		indexableActionableDynamicQuery
				.setPerformActionMethod(new ActionableDynamicQuery.PerformActionMethod<DossierStatistic>() {

					@Override
					public void performAction(DossierStatistic object) {
						try {
							Document document = getDocument(object);

							indexableActionableDynamicQuery.addDocuments(document);
						} catch (PortalException pe) {
							if (_log.isWarnEnabled()) {
								_log.warn("Unable to index DossierStatistic " + object.getPrimaryKey(), pe);
							}
						}
					}

				});
		indexableActionableDynamicQuery.setSearchEngineId(getSearchEngineId());

		indexableActionableDynamicQuery.performActions();
	}

	private static Log _log = LogFactoryUtil.getLog(DossierStatisticIndexer.class);

}
