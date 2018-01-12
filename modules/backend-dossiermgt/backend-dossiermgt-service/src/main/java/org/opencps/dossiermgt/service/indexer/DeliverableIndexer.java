package org.opencps.dossiermgt.service.indexer;

import java.util.Locale;

import javax.portlet.PortletRequest;
import javax.portlet.PortletResponse;

import org.opencps.dossiermgt.constants.DeliverableTerm;
import org.opencps.dossiermgt.model.Deliverable;
import org.opencps.dossiermgt.service.DeliverableLocalServiceUtil;

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

public class DeliverableIndexer extends BaseIndexer<Deliverable> {
	public static final String CLASS_NAME = Deliverable.class.getName();

	@Override
	public String getClassName() {
		return CLASS_NAME;
	}

	@Override
	protected void doDelete(Deliverable object) throws Exception {
		deleteDocument(object.getCompanyId(), object.getPrimaryKey());
	}

	@Override
	protected Document doGetDocument(Deliverable object) throws Exception {
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
		document.addNumber(DeliverableTerm.DELIVERABLE_ID, object.getDeliverableId());
		document.addDateSortable(DeliverableTerm.ISSUE_DATE, object.getIssueDate());
		document.addDateSortable(DeliverableTerm.EXPIRE_DATE, object.getExpireDate());
		document.addDateSortable(DeliverableTerm.REVALIDATE, object.getRevalidate());

		// add number fields
//		document.addNumberSortable(DossierActionTerm.DOSSIER_ID, object.getDossierId());
//		document.addNumberSortable(DossierActionTerm.SERVICE_PROCESS_ID, object.getServiceProcessId());
//		document.addNumberSortable(DossierActionTerm.PREVIOUS_ACTION_ID, object.getPreviousActionId());
//		document.addNumberSortable(DossierActionTerm.ACTION_OVER_DUE, object.getActionOverdue());
//		document.addNumberSortable(DossierActionTerm.NEXT_ACTION_ID, object.getNextActionId());

		// add text fields
		document.addTextSortable(DeliverableTerm.DELIVERABLE_CODE, object.getDeliverableCode());
		document.addTextSortable(DeliverableTerm.DELIVERABLE_NAME, object.getDeliverableName());
		document.addTextSortable(DeliverableTerm.DELIVERABLE_TYPE, object.getDeliverableType());
		document.addTextSortable(DeliverableTerm.GOV_AGENCY_CODE, object.getGovAgencyCode());
		document.addTextSortable(DeliverableTerm.GOV_AGENCY_NAME, object.getGovAgencyName());
		document.addTextSortable(DeliverableTerm.APPLICANT_ID_NO, object.getApplicantIdNo());
		document.addTextSortable(DeliverableTerm.APPLICANT_NAME, object.getApplicantName());
		document.addTextSortable(DeliverableTerm.SUBJECT, object.getSubject());
		document.addTextSortable(DeliverableTerm.FORM_DATA, object.getFormData());
		document.addTextSortable(DeliverableTerm.FORM_SCRIPT, object.getFormScript());
		document.addTextSortable(DeliverableTerm.FORM_REPORT, object.getFormReport());
		document.addTextSortable(DeliverableTerm.DELIVERABLE_STATE, object.getDeliverableState());

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
		Deliverable object = DeliverableLocalServiceUtil.getDetailById(classPK);
		doReindex(object);
	}

	@Override
	protected void doReindex(String[] ids) throws Exception {
		long companyId = GetterUtil.getLong(ids[0]);
		reindex(companyId);
	}

	@Override
	protected void doReindex(Deliverable object) throws Exception {
		Document document = getDocument(object);
		IndexWriterHelperUtil.updateDocument(getSearchEngineId(), object.getCompanyId(), document,
				isCommitImmediately());

	}

	protected void reindex(long companyId) throws PortalException {
		final IndexableActionableDynamicQuery indexableActionableDynamicQuery = DeliverableLocalServiceUtil
				.getIndexableActionableDynamicQuery();

		indexableActionableDynamicQuery.setCompanyId(companyId);
		indexableActionableDynamicQuery
				.setPerformActionMethod(new ActionableDynamicQuery.PerformActionMethod<Deliverable>() {

					@Override
					public void performAction(Deliverable object) {
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

	Log _log = LogFactoryUtil.getLog(DeliverableIndexer.class);

}
