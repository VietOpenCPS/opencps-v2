package org.opencps.dossiermgt.service.indexer;

import java.util.Locale;

import javax.portlet.PortletRequest;
import javax.portlet.PortletResponse;

import org.opencps.dossiermgt.constants.ServiceConfigTerm;
import org.opencps.dossiermgt.constants.ServiceInfoMappingTerm;
import org.opencps.dossiermgt.model.ServiceConfig;
import org.opencps.dossiermgt.model.ServiceInfo;
import org.opencps.dossiermgt.model.ServiceInfoMapping;
import org.opencps.dossiermgt.service.ServiceConfigLocalServiceUtil;
import org.opencps.dossiermgt.service.ServiceInfoLocalServiceUtil;
import org.opencps.dossiermgt.service.ServiceInfoMappingLocalServiceUtil;
import org.osgi.service.component.annotations.Component;

import com.liferay.petra.string.StringPool;
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
import com.liferay.portal.kernel.util.Validator;

@Component(
    immediate = true,
    service = BaseIndexer.class
)
public class ServiceConfigIndexer extends BaseIndexer<ServiceConfig> {

	public static final String CLASS_NAME = ServiceConfig.class.getName();

	@Override
	public String getClassName() {
		return CLASS_NAME;
	}

	@Override
	protected void doDelete(ServiceConfig object) throws Exception {
		deleteDocument(object.getCompanyId(), object.getPrimaryKey());
	}

	@Override
	protected Document doGetDocument(ServiceConfig object) throws Exception {
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
		document.addNumberSortable(ServiceConfigTerm.SERVICE_LEVEL, object.getServiceLevel());
		document.addNumberSortable(ServiceConfigTerm.SERVICEINFO_ID, object.getServiceInfoId());
		document.addNumberSortable(ServiceConfigTerm.SERVICECONFIG_ID, object.getServiceConfigId());

		// add text fields
		document.addTextSortable(ServiceConfigTerm.GOVAGENCY_CODE, object.getGovAgencyCode());
		document.addTextSortable(ServiceConfigTerm.GOVAGENCY_NAME, object.getGovAgencyName());
		document.addTextSortable(ServiceConfigTerm.SERVICE_INSTRUCTION, object.getServiceInstruction());
		document.addTextSortable(ServiceConfigTerm.SERVICE_URL, object.getServiceUrl());
		document.addTextSortable(ServiceConfigTerm.FOR_CITIZEN, Boolean.toString(object.getForCitizen()));
		document.addTextSortable(ServiceConfigTerm.FOR_BUSINESS, Boolean.toString(object.getForBusiness()));
		document.addTextSortable(ServiceConfigTerm.POSTAL_SERVICE, Boolean.toString(object.getPostService()));
		document.addTextSortable(ServiceConfigTerm._POST_SERVICE_, Boolean.toString(object.getPostService()));
		document.addTextSortable(ServiceConfigTerm.REGISTRATION, Boolean.toString(object.getRegistration()));
		

		// add extend fields

		ServiceInfo serviceInfo = null;

		try {
			serviceInfo = ServiceInfoLocalServiceUtil.getServiceInfo(object.getServiceInfoId());
		} catch (Exception e) {
			_log.info(e);
		}

		if (serviceInfo != null && Validator.isNotNull(serviceInfo)) {
			document.addTextSortable(ServiceConfigTerm.SERVICE_CODE, serviceInfo.getServiceCode());
			document.addTextSortable(ServiceConfigTerm.SERVICE_NAME, serviceInfo.getServiceName());
			document.addTextSortable(ServiceConfigTerm.DOMAIN_CODE, serviceInfo.getDomainCode());
			document.addTextSortable(ServiceConfigTerm.DOMAIN_NAME, serviceInfo.getDomainName());
			ServiceInfoMapping serviceInfoMapping = ServiceInfoMappingLocalServiceUtil.fetchDVCQGServiceCode(serviceInfo.getGroupId(), serviceInfo.getServiceCode());
			document.addTextSortable(ServiceInfoMappingTerm.SERVICE_CODE_DVCQG, Validator.isNull(serviceInfoMapping) ? StringPool.BLANK : serviceInfoMapping.getServiceCodeDVCQG());
		}
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
		ServiceConfig object = ServiceConfigLocalServiceUtil.getServiceConfig(classPK);
		doReindex(object);
	}

	@Override
	protected void doReindex(String[] ids) throws Exception {
		long companyId = GetterUtil.getLong(ids[0]);
		reindex(companyId);
	}

	protected void reindex(long companyId) throws PortalException {
		final IndexableActionableDynamicQuery indexableActionableDynamicQuery = ServiceConfigLocalServiceUtil
				.getIndexableActionableDynamicQuery();

		indexableActionableDynamicQuery.setCompanyId(companyId);
		indexableActionableDynamicQuery
				.setPerformActionMethod(new ActionableDynamicQuery.PerformActionMethod<ServiceConfig>() {

					@Override
					public void performAction(ServiceConfig object) {
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
	protected void doReindex(ServiceConfig object) throws Exception {
		Document document = getDocument(object);
		IndexWriterHelperUtil.updateDocument(getSearchEngineId(), object.getCompanyId(), document,
				isCommitImmediately());
	}

	Log _log = LogFactoryUtil.getLog(ServiceConfigIndexer.class);

}
