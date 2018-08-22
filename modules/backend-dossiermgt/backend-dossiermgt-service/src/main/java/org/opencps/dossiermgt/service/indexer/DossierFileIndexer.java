package org.opencps.dossiermgt.service.indexer;

import java.util.Locale;

import javax.portlet.PortletRequest;
import javax.portlet.PortletResponse;

import org.opencps.dossiermgt.constants.DossierFileTerm;
import org.opencps.dossiermgt.model.DossierFile;
import org.opencps.dossiermgt.service.DossierFileLocalServiceUtil;
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
public class DossierFileIndexer extends BaseIndexer<DossierFile> {
	public static final String CLASS_NAME = DossierFile.class.getName();

	@Override
	public String getClassName() {
		return CLASS_NAME;
	}

	@Override
	protected void doDelete(DossierFile object) throws Exception {
		deleteDocument(object.getCompanyId(), object.getPrimaryKey());

	}

	@Override
	protected Document doGetDocument(DossierFile object) throws Exception {
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
		
		document.addNumberSortable(DossierFileTerm.DOSSIER_ID, object.getDossierId());
		document.addNumberSortable(DossierFileTerm.DOSSIER_FILE_ID, object.getDossierFileId());
		document.addNumberSortable(DossierFileTerm.FILE_ENTRY_ID, object.getFileEntryId());
		document.addNumberSortable(DossierFileTerm.DOSSIER_PART_TYPE, object.getDossierPartType());
		document.addNumberSortable(DossierFileTerm.SIGN_CHECK, object.getSignCheck());

		// add text fields
		document.addTextSortable(DossierFileTerm.REFERENCE_UID, object.getReferenceUid());
		document.addTextSortable(DossierFileTerm.DOSSIER_TEMPLATE_NO, object.getDossierTemplateNo());
		document.addTextSortable(DossierFileTerm.DOSSIER_PART_NO, object.getDossierPartNo());
		document.addTextSortable(DossierFileTerm.FILE_TEMPLATE_NO, object.getFileTemplateNo());
		document.addTextSortable(DossierFileTerm.DISPLAY_NAME, object.getDisplayName());
		document.addTextSortable(DossierFileTerm.FORM_DATA, object.getFormData());
		document.addTextSortable(DossierFileTerm.ORIGINAL, Boolean.toString(object.getOriginal()));
		document.addTextSortable(DossierFileTerm.E_FORM, Boolean.toString(object.getEForm()));
		document.addTextSortable(DossierFileTerm.IS_NEW, Boolean.toString(object.getIsNew()));
		document.addTextSortable(DossierFileTerm.REMOVED, Boolean.toString(object.getRemoved()));
		document.addTextSortable(DossierFileTerm.SIGN_INFO, object.getSignInfo());
		document.addTextSortable(DossierFileTerm.FORM_SCRIPT, object.getFormScript());
		document.addTextSortable(DossierFileTerm.FORM_REPORT, object.getFormReport());

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
		DossierFile object = DossierFileLocalServiceUtil.getDossierFile(classPK);
		doReindex(object);

	}

	@Override
	protected void doReindex(String[] ids) throws Exception {
		long companyId = GetterUtil.getLong(ids[0]);
		reindex(companyId);

	}

	@Override
	protected void doReindex(DossierFile object) throws Exception {
		Document document = getDocument(object);
		IndexWriterHelperUtil.updateDocument(getSearchEngineId(), object.getCompanyId(), document,
				isCommitImmediately());
	}

	protected void reindex(long companyId) throws PortalException {
		final IndexableActionableDynamicQuery indexableActionableDynamicQuery = DossierFileLocalServiceUtil
				.getIndexableActionableDynamicQuery();

		indexableActionableDynamicQuery.setCompanyId(companyId);
		indexableActionableDynamicQuery
				.setPerformActionMethod(new ActionableDynamicQuery.PerformActionMethod<DossierFile>() {

					@Override
					public void performAction(DossierFile object) {
						try {
							Document document = getDocument(object);

							indexableActionableDynamicQuery.addDocuments(document);
						} catch (PortalException pe) {
							if (_log.isWarnEnabled()) {
								_log.warn("Unable to index DossierFile " + object.getPrimaryKey(), pe);
							}
						}
					}

				});
		indexableActionableDynamicQuery.setSearchEngineId(getSearchEngineId());

		indexableActionableDynamicQuery.performActions();
	}

	private static Log _log = LogFactoryUtil.getLog(DossierFileIndexer.class);

}
