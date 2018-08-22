package org.opencps.dossiermgt.service.indexer;

import java.util.Locale;

import javax.portlet.PortletRequest;
import javax.portlet.PortletResponse;

import org.opencps.dossiermgt.constants.PaymentConfigTerm;
import org.opencps.dossiermgt.model.PaymentConfig;
import org.opencps.dossiermgt.service.PaymentConfigLocalServiceUtil;
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
public class PaymentConfigIndexer extends BaseIndexer<PaymentConfig>{
	
	public static final String CLASS_NAME = PaymentConfig.class.getName();

	@Override
	public String getClassName() {
		return CLASS_NAME;
	}

	@Override
	protected void doDelete(PaymentConfig object) throws Exception {
		deleteDocument(object.getCompanyId(), object.getPrimaryKey());
	}

	@Override
	protected Document doGetDocument(PaymentConfig object) throws Exception {
		
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

		// add text fields
		document.addTextSortable(PaymentConfigTerm.GOV_AGENCY_CODE, object.getGovAgencyCode());
		document.addTextSortable(PaymentConfigTerm.GOV_AGENCY_NAME, object.getGovAgencyName());
		document.addTextSortable(PaymentConfigTerm.GOV_AGENCY_TAX_NO, object.getGovAgencyTaxNo());
		document.addTextSortable(PaymentConfigTerm.INVOICE_TEMPLATE_NO, object.getInvoiceTemplateNo());
		document.addTextSortable(PaymentConfigTerm.INVOICE_ISSUE_NO, object.getInvoiceIssueNo());
		document.addTextSortable(PaymentConfigTerm.INVOICE_LAST_NO, object.getInvoiceLastNo());
		document.addTextSortable(PaymentConfigTerm.INVOICE_FORM, object.getInvoiceForm());
		document.addTextSortable(PaymentConfigTerm.BANK_INFO, object.getBankInfo());
		document.addTextSortable(PaymentConfigTerm.EPAYMENT_CONFIG, object.getEpaymentConfig());
		
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
		PaymentConfig object = PaymentConfigLocalServiceUtil.getPaymentConfig(classPK);
		doReindex(object);
		
	}

	@Override
	protected void doReindex(String[] ids) throws Exception {
		long companyId = GetterUtil.getLong(ids[0]);
		reindex(companyId);
	}

	@Override
	protected void doReindex(PaymentConfig object) throws Exception {
		Document document = getDocument(object);
		IndexWriterHelperUtil.updateDocument(getSearchEngineId(), object.getCompanyId(), document,
				isCommitImmediately());
		
	}
	
	protected void reindex(long companyId) throws PortalException {
		final IndexableActionableDynamicQuery indexableActionableDynamicQuery = PaymentConfigLocalServiceUtil
				.getIndexableActionableDynamicQuery();

		indexableActionableDynamicQuery.setCompanyId(companyId);
		indexableActionableDynamicQuery
				.setPerformActionMethod(new ActionableDynamicQuery.PerformActionMethod<PaymentConfig>() {

					@Override
					public void performAction(PaymentConfig object) {
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

	Log _log = LogFactoryUtil.getLog(PaymentConfigIndexer.class);

	
}
