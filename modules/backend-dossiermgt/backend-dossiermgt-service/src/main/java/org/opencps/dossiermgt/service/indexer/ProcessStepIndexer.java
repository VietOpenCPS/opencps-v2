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
import com.liferay.portal.kernel.util.ListUtil;
import com.liferay.portal.kernel.util.Validator;

import java.util.List;
import java.util.Locale;

import javax.portlet.PortletRequest;
import javax.portlet.PortletResponse;

import org.opencps.datamgt.model.DictCollection;
import org.opencps.datamgt.model.DictItem;
import org.opencps.datamgt.service.DictCollectionLocalServiceUtil;
import org.opencps.datamgt.service.DictItemLocalServiceUtil;
import org.opencps.dossiermgt.constants.ProcessStepRoleTerm;
import org.opencps.dossiermgt.constants.ProcessStepTerm;
import org.opencps.dossiermgt.model.ProcessStep;
import org.opencps.dossiermgt.model.ProcessStepRole;
import org.opencps.dossiermgt.service.ProcessStepLocalServiceUtil;
import org.opencps.dossiermgt.service.ProcessStepRoleLocalServiceUtil;
import org.osgi.service.component.annotations.Component;

@Component(
    immediate = true,
    service = BaseIndexer.class
)
public class ProcessStepIndexer extends BaseIndexer<ProcessStep> {
	public static final String CLASS_NAME = ProcessStep.class.getName();

	@Override
	public String getClassName() {
		return CLASS_NAME;
	}

	@Override
	protected void doDelete(ProcessStep object) throws Exception {
		deleteDocument(object.getCompanyId(), object.getPrimaryKey());
	}

	@Override
	protected Document doGetDocument(ProcessStep object) throws Exception {

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
		document.addNumberSortable(ProcessStepTerm.DURATION_COUNT, object.getDurationCount());
		document.addNumberSortable(ProcessStepTerm.SERVICE_PROCESS_ID, object.getServiceProcessId());

		// add text fields
		document.addTextSortable(ProcessStepTerm.STEP_CODE, object.getStepCode());
		document.addTextSortable(ProcessStepTerm.STEP_NAME, object.getStepName());
		document.addTextSortable(ProcessStepTerm.SEQUENCE_NO, object.getSequenceNo());
		document.addTextSortable(ProcessStepTerm.DOSSIER_STATUS, object.getDossierStatus());

		document.addTextSortable(ProcessStepTerm.DOSSIER_STATUS_TEXT,
				getDictItemName(object.getGroupId(), DOSSIER_STATUS, object.getDossierStatus()));
		document.addTextSortable(ProcessStepTerm.DOSSIER_SUB_STATUS, object.getDossierSubStatus());
		document.addTextSortable(ProcessStepTerm.DOSSIER_SUB_STATUS_TEXT,
				getDictItemName(object.getGroupId(), DOSSIER_STATUS, object.getDossierSubStatus()));
		document.addTextSortable(ProcessStepTerm.CUSTOM_PROCESS_URL, object.getCustomProcessUrl());
		document.addTextSortable(ProcessStepTerm.STEP_INSTRUCTION, object.getStepInstruction());
		document.addTextSortable(ProcessStepTerm.BRIEF_NOTE, object.getBriefNote());
		document.addTextSortable(ProcessStepTerm.EDITABLE, Boolean.toString(object.getEditable()));
		document.addTextSortable(ProcessStepTerm.LOCK_STATE, object.getLockState());
		document.addNumber(ProcessStepTerm.CHECK_INPUT, object.getCheckInput());

		// add extra fields (ProcessStepRole)
		List<ProcessStepRole> roles = ProcessStepRoleLocalServiceUtil.findByP_S_ID(object.getPrimaryKey());

		long[] roleArray = ListUtil.toLongArray(roles, ProcessStepRole.ROLE_ID_ACCESSOR);

		document.addNumber(ProcessStepRoleTerm.ROLE_ID, roleArray);

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
		ProcessStep object = ProcessStepLocalServiceUtil.getProcessStep(classPK);

		doReindex(object);

	}

	@Override
	protected void doReindex(String[] ids) throws Exception {
		long companyId = GetterUtil.getLong(ids[0]);
		reindex(companyId);
	}

	@Override
	protected void doReindex(ProcessStep object) throws Exception {
		Document document = getDocument(object);
		IndexWriterHelperUtil.updateDocument(getSearchEngineId(), object.getCompanyId(), document,
				isCommitImmediately());
	}

	protected void reindex(long companyId) throws PortalException {
		final IndexableActionableDynamicQuery indexableActionableDynamicQuery = ProcessStepLocalServiceUtil
				.getIndexableActionableDynamicQuery();

		indexableActionableDynamicQuery.setCompanyId(companyId);
		indexableActionableDynamicQuery
				.setPerformActionMethod(new ActionableDynamicQuery.PerformActionMethod<ProcessStep>() {

					@Override
					public void performAction(ProcessStep object) {
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

	protected String getDictItemName(long groupId, String collectionCode, String itemCode) {

		DictCollection dc = DictCollectionLocalServiceUtil.fetchByF_dictCollectionCode(collectionCode, groupId);

		String itemName = StringPool.BLANK;

		if (Validator.isNotNull(dc)) {
			DictItem it = DictItemLocalServiceUtil.fetchByF_dictItemCode(itemCode, dc.getPrimaryKey(), groupId);
			if (Validator.isNotNull(it)) {
				itemName = it.getItemName();
			} else {
				itemName = StringPool.BLANK;
			}
		}

		return itemName;
	}

	private static String DOSSIER_STATUS = "DOSSIER_STATUS";

	Log _log = LogFactoryUtil.getLog(ProcessStepIndexer.class);

}
