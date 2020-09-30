package org.opencps.dossiermgt.service.indexer;

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

import java.security.MessageDigest;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import javax.portlet.PortletRequest;
import javax.portlet.PortletResponse;

import com.liferay.portal.kernel.util.Validator;
import org.opencps.dossiermgt.action.keypay.util.HashFunction;
import org.opencps.dossiermgt.action.util.SpecialCharacterUtils;
import org.opencps.dossiermgt.constants.ConverterTerm;
import org.opencps.dossiermgt.constants.DossierTerm;
import org.opencps.dossiermgt.constants.PaymentFileTerm;
import org.opencps.dossiermgt.constants.ServiceInfoTerm;
import org.opencps.dossiermgt.model.Dossier;
import org.opencps.dossiermgt.model.PaymentFile;
import org.opencps.dossiermgt.model.ServiceInfo;
import org.opencps.dossiermgt.service.DossierLocalServiceUtil;
import org.opencps.dossiermgt.service.PaymentFileLocalServiceUtil;
import org.opencps.dossiermgt.service.ServiceInfoLocalServiceUtil;
import org.osgi.service.component.annotations.Component;

@Component(
    immediate = true,
    service = BaseIndexer.class
)
public class PaymentFileIndexer extends BaseIndexer<PaymentFile> {
	public static final String CLASS_NAME = PaymentFile.class.getName();

	@Override
	public String getClassName() {
		return CLASS_NAME;
	}

	@Override
	protected void doDelete(PaymentFile object) throws Exception {
		deleteDocument(object.getCompanyId(), object.getPrimaryKey());

	}

	@Override
	protected Document doGetDocument(PaymentFile object) throws Exception {
		Document document = getBaseModelDocument(CLASS_NAME, object);

		// Indexer of audit fields
		document.addNumberSortable(Field.COMPANY_ID, object.getCompanyId());
		document.addNumberSortable(Field.GROUP_ID, object.getGroupId());
		document.addDateSortable(Field.MODIFIED_DATE, object.getModifiedDate());
		document.addDateSortable(Field.CREATE_DATE, object.getCreateDate());
		document.addNumberSortable(Field.USER_ID, object.getUserId());
		document.addKeywordSortable(Field.USER_NAME, String.valueOf(object.getUserName()));
		document.addKeywordSortable(Field.ENTRY_CLASS_NAME, CLASS_NAME);
		document.addNumberSortable(Field.ENTRY_CLASS_PK, object.getPrimaryKey());

		// add number fields

		document.addNumberSortable(PaymentFileTerm.DOSSIER_ID, object.getDossierId());
		document.addNumberSortable(PaymentFileTerm.PAYMENT_AMOUNT, object.getPaymentAmount());
		document.addNumberSortable(PaymentFileTerm.PAYMENT_STATUS, object.getPaymentStatus());
		document.addNumberSortable(PaymentFileTerm.CONFIRM_FILE_ENTRY_ID, object.getConfirmFileEntryId());
//		document.addNumberSortable(PaymentFileTerm.INVOICE_FILE_ENTRY_ID, object.getInvoiceFileEntryId());

		if (object.getApproveDatetime() != null) {
			document.addDateSortable(PaymentFileTerm.APPROVE_DATETIME, object.getApproveDatetime());
		} else {
			document.addTextSortable(PaymentFileTerm.APPROVE_DATETIME, StringPool.BLANK);
		}
		document.addNumberSortable(PaymentFileTerm.APPROVE_DATE_TIMESTAMP,
				Validator.isNotNull(object.getApproveDatetime()) ? object.getApproveDatetime().getTime() : 0);

		document.addDateSortable(PaymentFileTerm.CONFIRM_DATETIME, object.getConfirmDatetime());

		// add text fields
		document.addTextSortable(PaymentFileTerm.REFERENCE_UID, object.getReferenceUid());
//		document.addTextSortable(PaymentFileTerm.GOV_AGENCY_CODE, object.getGovAgencyCode());
//		document.addTextSortable(PaymentFileTerm.GOV_AGENCY_NAME, object.getGovAgencyName());
//		document.addTextSortable(PaymentFileTerm.IS_NEW, Boolean.toString(object.getIsNew()));
		document.addTextSortable(PaymentFileTerm.PAYMENT_FEE, object.getPaymentFee());
		document.addTextSortable(PaymentFileTerm.PAYMENT_NOTE, object.getPaymentNote());
		document.addTextSortable(PaymentFileTerm.EPAYMENT_PROFILE, object.getEpaymentProfile());
		document.addTextSortable(PaymentFileTerm.BANK_INFO, object.getBankInfo());

		if (Validator.isNotNull(object.getPaymentMethod())) {
			document.addTextSortable(PaymentFileTerm.PAYMENT_METHOD, object.getPaymentMethod());
		} else {
			document.addTextSortable(PaymentFileTerm.PAYMENT_METHOD, String.valueOf(0));
		}
		document.addTextSortable(PaymentFileTerm.CONFIRM_PAYLOAD, object.getConfirmPayload());
		document.addTextSortable(PaymentFileTerm.CONFIRM_NOTE, object.getConfirmNote());
		document.addTextSortable(PaymentFileTerm.ACCOUNT_USER_NAME, object.getAccountUserName());
		document.addTextSortable(PaymentFileTerm.GOV_AGENCY_TAX_NO, object.getGovAgencyTaxNo());
		document.addTextSortable(PaymentFileTerm.INVOICE_TEMPLATE_NO, object.getInvoiceTemplateNo());
		document.addTextSortable(PaymentFileTerm.INVOICE_ISSUE_NO, object.getInvoiceIssueNo());
		document.addTextSortable(PaymentFileTerm.INVOICE_NO, object.getInvoiceNo());

		// Add text fields of dossierId
//		try {
		Dossier dossier = DossierLocalServiceUtil.fetchDossier(object.getDossierId());
		if (dossier != null) {
			document.addTextSortable(PaymentFileTerm.APPLICANT_NAME, dossier.getApplicantName());
			document.addTextSortable(PaymentFileTerm.APPLICANT_ID_NO, dossier.getApplicantIdNo());
			document.addTextSortable(PaymentFileTerm.SERVICE_CODE, dossier.getServiceCode());
			document.addTextSortable(PaymentFileTerm.SERVICE_NAME, dossier.getServiceName());
			document.addTextSortable(PaymentFileTerm.DOSSIER_NO, dossier.getDossierNo());
			document.addNumberSortable(PaymentFileTerm.COUNTER, dossier.getCounter());
			document.addTextSortable(DossierTerm.DOSSIER_COUNTER, dossier.getDossierCounter());
			document.addTextSortable(DossierTerm.SERVICE_CODE, dossier.getServiceCode());
			if (Validator.isNotNull(dossier.getServiceCode())) {
				String serviceCodeSearch = SpecialCharacterUtils.splitSpecial(dossier.getServiceCode());
				document.addTextSortable(ServiceInfoTerm.SERVICE_CODE_SEARCH, serviceCodeSearch);
			}
			document.addTextSortable(DossierTerm.SERVICE_NAME, dossier.getServiceName());
			document.addTextSortable(DossierTerm.ADDRESS, dossier.getAddress());
			document.addTextSortable(DossierTerm.META_DATA, dossier.getMetaData());
			String serviceCode = dossier.getServiceCode();
			String domainCode = StringPool.BLANK;
			String domainName = StringPool.BLANK;
			if (Validator.isNotNull(serviceCode)) {
				ServiceInfo service = ServiceInfoLocalServiceUtil.getByCode(object.getGroupId(), serviceCode);
				if (service != null) {
					domainCode = service.getDomainCode();
					domainName = service.getDomainName();
				}
			}
			document.addTextSortable(DossierTerm.DOMAIN_CODE, domainCode);
			document.addTextSortable(DossierTerm.DOMAIN_NAME, domainName);
		} else {
			document.addTextSortable(PaymentFileTerm.APPLICANT_NAME, StringPool.BLANK);
			document.addTextSortable(PaymentFileTerm.APPLICANT_ID_NO, StringPool.BLANK);
			document.addTextSortable(PaymentFileTerm.SERVICE_CODE, StringPool.BLANK);
			document.addTextSortable(PaymentFileTerm.SERVICE_NAME, StringPool.BLANK);
			document.addTextSortable(PaymentFileTerm.DOSSIER_NO, StringPool.BLANK);
			document.addNumberSortable(PaymentFileTerm.COUNTER, 0);
			document.addTextSortable(DossierTerm.DOSSIER_COUNTER, StringPool.BLANK);
			document.addTextSortable(DossierTerm.SERVICE_CODE, StringPool.BLANK);
			document.addTextSortable(DossierTerm.SERVICE_NAME, StringPool.BLANK);
			document.addTextSortable(DossierTerm.META_DATA, StringPool.BLANK);
			document.addTextSortable(DossierTerm.DOMAIN_CODE, StringPool.BLANK);
			document.addTextSortable(DossierTerm.DOMAIN_NAME, StringPool.BLANK);
			document.addTextSortable(DossierTerm.ADDRESS, StringPool.BLANK);
		}

			
			//binhth index dossierId CTN
			// TODO
			/*MessageDigest md5 = null;
			byte[] ba = null;
			try {
				md5 = MessageDigest.getInstance(ConverterTerm.SHA_256);
				ba = md5.digest(dossier.getReferenceUid().getBytes(ConverterTerm.UTF_8));
			} catch (Exception e) {
				_log.error(e);
			} 

			DateFormat df = new SimpleDateFormat(ConverterTerm.YY);
			String formattedDate = df.format(Calendar.getInstance().getTime());
			String dossierIDCTN;
			dossierIDCTN = formattedDate + HashFunction.hexShort(ba);
			document.addTextSortable(DossierTerm.DOSSIER_ID_CTN, dossierIDCTN);*/
			
//		} catch (Exception e) {
//			e.printStackTrace();
//			_log.error(e);
//		}

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
		PaymentFile object = PaymentFileLocalServiceUtil.getPaymentFile(classPK);
		doReindex(object);

	}

	@Override
	protected void doReindex(String[] ids) throws Exception {
		long companyId = GetterUtil.getLong(ids[0]);
		reindex(companyId);

	}

	@Override
	protected void doReindex(PaymentFile object) throws Exception {
		Document document = getDocument(object);
		IndexWriterHelperUtil.updateDocument(getSearchEngineId(), object.getCompanyId(), document,
				isCommitImmediately());
	}

	protected void reindex(long companyId) throws PortalException {
		final IndexableActionableDynamicQuery indexableActionableDynamicQuery = PaymentFileLocalServiceUtil
				.getIndexableActionableDynamicQuery();

		indexableActionableDynamicQuery.setCompanyId(companyId);
		indexableActionableDynamicQuery
				.setPerformActionMethod(new ActionableDynamicQuery.PerformActionMethod<PaymentFile>() {

					@Override
					public void performAction(PaymentFile object) {
						try {
							Document document = getDocument(object);

							indexableActionableDynamicQuery.addDocuments(document);
						} catch (PortalException pe) {
							if (_log.isWarnEnabled()) {
								_log.warn("Unable to index PaymentFile " + object.getPrimaryKey(), pe);
							}
						}
					}

				});
		indexableActionableDynamicQuery.setSearchEngineId(getSearchEngineId());

		indexableActionableDynamicQuery.performActions();
	}

	private static Log _log = LogFactoryUtil.getLog(PaymentFileIndexer.class);

}
