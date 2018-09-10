package org.opencps.dossiermgt.service.indexer;

import java.util.Locale;

import javax.portlet.PortletRequest;
import javax.portlet.PortletResponse;

import org.opencps.dossiermgt.constants.DossierActionTerm;
import org.opencps.dossiermgt.model.DossierAction;
import org.opencps.dossiermgt.service.DossierActionLocalServiceUtil;
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
public class DossierActionIndexer extends BaseIndexer<DossierAction> {
	public static final String CLASS_NAME = DossierAction.class.getName();

	@Override
	public String getClassName() {
		return CLASS_NAME;
	}

	@Override
	protected void doDelete(DossierAction object) throws Exception {
		deleteDocument(object.getCompanyId(), object.getPrimaryKey());
	}

	@Override
	protected Document doGetDocument(DossierAction object) throws Exception {
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
		document.addDateSortable(DossierActionTerm.DUE_DATE, object.getDueDate());

		// add number fields
		document.addNumberSortable(DossierActionTerm.DOSSIER_ID, object.getDossierId());
		document.addNumberSortable(DossierActionTerm.SERVICE_PROCESS_ID, object.getServiceProcessId());
		document.addNumberSortable(DossierActionTerm.PREVIOUS_ACTION_ID, object.getPreviousActionId());
		document.addNumberSortable(DossierActionTerm.ACTION_OVER_DUE, object.getActionOverdue());
		document.addNumberSortable(DossierActionTerm.NEXT_ACTION_ID, object.getNextActionId());

		// add text fields
		document.addTextSortable(DossierActionTerm.ACTION_CODE, object.getActionCode());
		document.addTextSortable(DossierActionTerm.ACTION_NAME, object.getActionName());
		document.addTextSortable(DossierActionTerm.ACTION_USER, object.getActionUser());
		document.addTextSortable(DossierActionTerm.ACTION_NOTE, object.getActionNote());
		document.addTextSortable(DossierActionTerm.SYNC_ACTION_CODE, object.getSyncActionCode());
		document.addTextSortable(DossierActionTerm.PENDING, Boolean.toString(object.getPending()));
		document.addTextSortable(DossierActionTerm.ROLLBACK_ABLE, Boolean.toString(object.getRollbackable()));
		document.addTextSortable(DossierActionTerm.STEP_CODE, object.getStepCode());
		document.addTextSortable(DossierActionTerm.STEP_NAME, object.getStepName());
		document.addTextSortable(DossierActionTerm.STEP_INSTRUCTION, object.getStepInstruction());
		document.addTextSortable(DossierActionTerm.PAYLOAD, object.getPayload());

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
		DossierAction object = DossierActionLocalServiceUtil.getDossierAction(classPK);
		doReindex(object);
	}

	@Override
	protected void doReindex(String[] ids) throws Exception {
		long companyId = GetterUtil.getLong(ids[0]);
		reindex(companyId);
	}

	@Override
	protected void doReindex(DossierAction object) throws Exception {
		Document document = getDocument(object);
		IndexWriterHelperUtil.updateDocument(getSearchEngineId(), object.getCompanyId(), document,
				isCommitImmediately());

	}

	protected void reindex(long companyId) throws PortalException {
		final IndexableActionableDynamicQuery indexableActionableDynamicQuery = DossierActionLocalServiceUtil
				.getIndexableActionableDynamicQuery();

		indexableActionableDynamicQuery.setCompanyId(companyId);
		indexableActionableDynamicQuery
				.setPerformActionMethod(new ActionableDynamicQuery.PerformActionMethod<DossierAction>() {

					@Override
					public void performAction(DossierAction object) {
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

	Log _log = LogFactoryUtil.getLog(DossierActionIndexer.class);

}
