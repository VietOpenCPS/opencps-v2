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
import com.liferay.portal.kernel.util.Validator;

import java.util.Locale;

import javax.portlet.PortletRequest;
import javax.portlet.PortletResponse;

import org.opencps.dossiermgt.constants.ProcessActionTerm;
import org.opencps.dossiermgt.model.ProcessAction;
import org.opencps.dossiermgt.model.ProcessStep;
import org.opencps.dossiermgt.service.ProcessActionLocalServiceUtil;
import org.opencps.dossiermgt.service.ProcessStepLocalServiceUtil;
import org.osgi.service.component.annotations.Component;

@Component(
    immediate = true,
    service = BaseIndexer.class
)
public class ProcessActionIndexer extends BaseIndexer<ProcessAction> {
	public static final String CLASS_NAME = ProcessAction.class.getName();

	@Override
	public String getClassName() {
		return CLASS_NAME;
	}

	@Override
	protected void doDelete(ProcessAction object) throws Exception {
		deleteDocument(object.getCompanyId(), object.getPrimaryKey());
	}

	@Override
	protected Document doGetDocument(ProcessAction object) throws Exception {
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
		document.addNumberSortable(ProcessActionTerm.SERVICE_PROCESS_ID, object.getServiceProcessId());
		document.addNumberSortable(ProcessActionTerm.ASSIGN_USER_ID, object.getAssignUserId());
		document.addNumberSortable(ProcessActionTerm.REQUEST_PAYMENT, object.getRequestPayment());

		// add text fields
		document.addTextSortable(ProcessActionTerm.PRESTEP_CODE, object.getPreStepCode());
		document.addTextSortable(ProcessActionTerm.PRESTEP_NAME, getStepName(object.getGroupId(), object.getPreStepCode(), object.getServiceProcessId()));
		document.addTextSortable(ProcessActionTerm.POSTSTEP_CODE, object.getPostStepCode());
		document.addTextSortable(ProcessActionTerm.POSTSTEP_NAME, getStepName(object.getGroupId(), object.getPostStepCode(), object.getServiceProcessId()));
		document.addTextSortable(ProcessActionTerm.AUTO_EVENT, object.getAutoEvent());
		document.addTextSortable(ProcessActionTerm.PRE_CONDITION, object.getPreCondition());
		document.addTextSortable(ProcessActionTerm.ACTION_CODE, object.getActionCode());
		document.addTextSortable(ProcessActionTerm.ACTION_NAME, object.getActionName());
		document.addTextSortable(ProcessActionTerm.PAYMENT_FEE, object.getPaymentFee());
		document.addTextSortable(ProcessActionTerm.SYNC_ACTION_CODE, object.getSyncActionCode());
		document.addTextSortable(ProcessActionTerm.CREATE_DOSSIER_FILES, object.getCreateDossierFiles());
		document.addTextSortable(ProcessActionTerm.RETURN_DOSSIER_FILES, object.getReturnDossierFiles());
		document.addTextSortable(ProcessActionTerm.MAKE_BRIEF_NOTE, object.getMakeBriefNote());

		document.addNumberSortable(ProcessActionTerm.ALLOW_ASSIGN_USER, object.getAllowAssignUser());
		document.addTextSortable(ProcessActionTerm.ROLLBACKABLE, Boolean.toString(object.getRollbackable()));

		document.addTextSortable(ProcessActionTerm.CREATE_DOSSIER_NO, Boolean.toString(object.getCreateDossierNo()));
		document.addTextSortable(ProcessActionTerm.ESIGNATURE, Boolean.toString(object.getESignature()));
		document.addTextSortable(ProcessActionTerm.SIGNATURE_TYPE, object.getSignatureType());		
		document.addTextSortable(ProcessActionTerm.CONFIG_NOTE, object.getConfigNote());
		document.addTextSortable("dossierTemplateNo", object.getDossierTemplateNo());
		document.addTextSortable(ProcessActionTerm.PAYMENT_FEE, object.getPaymentFee());
		document.addTextSortable(ProcessActionTerm.CREATE_DOSSIERS, object.getCreateDossiers());
		
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
		ProcessAction object = ProcessActionLocalServiceUtil.getProcessAction(classPK);

		doReindex(object);

	}

	@Override
	protected void doReindex(String[] ids) throws Exception {
		long companyId = GetterUtil.getLong(ids[0]);
		reindex(companyId);

	}

	@Override
	protected void doReindex(ProcessAction object) throws Exception {
		Document document = getDocument(object);
		IndexWriterHelperUtil.updateDocument(getSearchEngineId(), object.getCompanyId(), document,
				isCommitImmediately());

	}

	protected void reindex(long companyId) throws PortalException {
		final IndexableActionableDynamicQuery indexableActionableDynamicQuery = ProcessActionLocalServiceUtil
				.getIndexableActionableDynamicQuery();

		indexableActionableDynamicQuery.setCompanyId(companyId);
		indexableActionableDynamicQuery
				.setPerformActionMethod(new ActionableDynamicQuery.PerformActionMethod<ProcessAction>() {

					@Override
					public void performAction(ProcessAction object) {
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
	
	private String getStepName(long groupId, String stepCode, long serviceProcessId) {
		String sName = StringPool.BLANK;
		
		ProcessStep step = ProcessStepLocalServiceUtil.fetchBySC_GID(stepCode, groupId, serviceProcessId);
		
		if (Validator.isNotNull(step)) {
			sName = step.getStepName();
		}
		return sName;
	}

	Log _log = LogFactoryUtil.getLog(ProcessActionIndexer.class);

}
