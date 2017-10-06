package org.opencps.dossiermgt.service.indexer;

import java.util.Locale;

import javax.portlet.PortletRequest;
import javax.portlet.PortletResponse;

import org.opencps.dossiermgt.constants.ServiceInfoTerm;
import org.opencps.dossiermgt.model.ServiceInfo;
import org.opencps.dossiermgt.service.ServiceInfoLocalServiceUtil;


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

public class ServiceInfoIndexer extends BaseIndexer<ServiceInfo> {

	public static final String CLASS_NAME = ServiceInfo.class.getName();

	@Override
	public String getClassName() {
		return CLASS_NAME;
	}

	@Override
	protected void doDelete(ServiceInfo object) throws Exception {
		deleteDocument(object.getCompanyId(), object.getPrimaryKey());
	}

	@Override
	protected Document doGetDocument(ServiceInfo object) throws Exception {
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

		document.addKeywordSortable(ServiceInfoTerm.SERVICE_CODE, object.getServiceCode());

		document.addKeywordSortable(ServiceInfoTerm.SERVICE_NAME, object.getServiceName());
		document.addKeywordSortable(ServiceInfoTerm.PROCESS_TEXT, object.getProcessText());
		document.addKeywordSortable(ServiceInfoTerm.METHOD_TEXT, object.getMethodText());
		document.addKeywordSortable(ServiceInfoTerm.DOSSIER_EXT, object.getDossierText());
		document.addKeywordSortable(ServiceInfoTerm.CONDITION_TEXT, object.getConditionText());
		document.addKeywordSortable(ServiceInfoTerm.DURATION_TEXT, object.getDurationText());
		document.addKeywordSortable(ServiceInfoTerm.APPLICANT_TEXT, object.getApplicantText());
		document.addKeywordSortable(ServiceInfoTerm.RESULT_TEXT, object.getRegularText());
		document.addKeywordSortable(ServiceInfoTerm.REGULAR_TEXT, object.getRegularText());
		document.addKeywordSortable(ServiceInfoTerm.FEE_TEXT, object.getFeeText());
		document.addKeywordSortable(ServiceInfoTerm.ADMINISTRATION_CODE, object.getAdministrationCode());
		document.addKeywordSortable(ServiceInfoTerm.ADMINISTRATION_NAME, object.getAdministrationName());
		document.addKeywordSortable(ServiceInfoTerm.ADMINISTRATION_INDEX, object.getAdministrationIndex());
		document.addKeywordSortable(ServiceInfoTerm.DOMAIN_CODE, object.getDomainCode());
		document.addKeywordSortable(ServiceInfoTerm.DOMAIN_NAME, object.getDomainName());
		document.addKeywordSortable(ServiceInfoTerm.DOMAIN_INDEX, object.getDomainIndex());
		document.addNumberSortable(ServiceInfoTerm.MAX_LEVEL, object.getMaxLevel());
		
		document.addKeywordSortable(ServiceInfoTerm.PUBLIC_, Boolean.toString(object.getPublic_()));

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
		ServiceInfo object = ServiceInfoLocalServiceUtil.getServiceInfo(classPK);
		doReindex(object);
	}

	@Override
	protected void doReindex(String[] ids) throws Exception {
		long companyId = GetterUtil.getLong(ids[0]);
		reindex(companyId);
	}
	
	protected void reindex(long companyId) throws PortalException {
		final IndexableActionableDynamicQuery indexableActionableDynamicQuery = ServiceInfoLocalServiceUtil
				.getIndexableActionableDynamicQuery();

		indexableActionableDynamicQuery.setCompanyId(companyId);
		indexableActionableDynamicQuery
				.setPerformActionMethod(new ActionableDynamicQuery.PerformActionMethod<ServiceInfo>() {

					@Override
					public void performAction(ServiceInfo object) {
						try {
							Document document = getDocument(object);

							indexableActionableDynamicQuery.addDocuments(document);
						} catch (PortalException pe) {
							if (_log.isWarnEnabled()) {
								_log.warn("Unable to index contact " + object.getPrimaryKey(), pe);
							}
						}
					}

				});
		indexableActionableDynamicQuery.setSearchEngineId(getSearchEngineId());

		indexableActionableDynamicQuery.performActions();
	}


	@Override
	protected void doReindex(ServiceInfo object) throws Exception {
		Document document = getDocument(object);
		IndexWriterHelperUtil.updateDocument(getSearchEngineId(), object.getCompanyId(), document,
				isCommitImmediately());
	}

	Log _log = LogFactoryUtil.getLog(ServiceInfoIndexer.class);

}
