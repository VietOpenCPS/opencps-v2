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

import org.opencps.dossiermgt.constants.DossierPartTerm;
import org.opencps.dossiermgt.model.DossierPart;
import org.opencps.dossiermgt.model.DossierTemplate;
import org.opencps.dossiermgt.service.DossierPartLocalServiceUtil;
import org.opencps.dossiermgt.service.DossierTemplateLocalServiceUtil;
import org.osgi.service.component.annotations.Component;

@Component(
    immediate = true,
    service = BaseIndexer.class
)
public class DossierPartIndexer extends BaseIndexer<DossierPart> {
	public static final String CLASS_NAME = DossierPart.class.getName();

	@Override
	public String getClassName() {
		return CLASS_NAME;
	}

	@Override
	protected void doDelete(DossierPart object) throws Exception {
		deleteDocument(object.getCompanyId(), object.getPrimaryKey());

	}

	@Override
	protected Document doGetDocument(DossierPart object) throws Exception {
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
		
		document.addNumberSortable(DossierPartTerm.PART_TYPE, object.getPartType());
		
		DossierTemplate dossierTemplate = DossierTemplateLocalServiceUtil.getByTemplateNo(object.getGroupId(), object.getTemplateNo());
		
		document.addNumberSortable(DossierPartTerm.TEMPLATE_ID, dossierTemplate.getPrimaryKey());
		document.addNumberSortable(DossierPartTerm.DELIVERABLE_ACTION, Validator.isNotNull(object.getDeliverableAction()) ? object.getDeliverableAction() : 0);


		// add text fields
		document.addTextSortable(DossierPartTerm.TEMPLATE_NO, object.getTemplateNo());
		document.addTextSortable(DossierPartTerm.PART_NAME, object.getPartName());
		document.addTextSortable(DossierPartTerm.PART_NO, object.getPartNo());
		document.addTextSortable(DossierPartTerm.PART_TIP, object.getPartTip());

		document.addTextSortable(DossierPartTerm.MULTIPLE, Boolean.toString(object.getMultiple()));
		document.addTextSortable(DossierPartTerm.FORM_SCRIPT, object.getFormScript());
		document.addTextSortable(DossierPartTerm.FORM_REPORT, object.getFormReport());
		document.addTextSortable(DossierPartTerm.SAMPLE_DATA, object.getSampleData());
		document.addTextSortable(DossierPartTerm.REQUIRED, Boolean.toString(object.getRequired()));
		document.addTextSortable(DossierPartTerm.FILE_TEMPLATE_NO, object.getFileTemplateNo());
		document.addTextSortable(DossierPartTerm.ESIGN, Boolean.toString(object.getESign()));
		document.addTextSortable(DossierPartTerm.DELIVERABLE_TYPE, Validator.isNotNull(object.getDeliverableType()) ? object.getDeliverableType() : StringPool.BLANK );
		document.addTextSortable(DossierPartTerm.EFORM, Boolean.toString(object.getEForm()));

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
		DossierPart object = DossierPartLocalServiceUtil.getDossierPart(classPK);
		doReindex(object);

	}

	@Override
	protected void doReindex(String[] ids) throws Exception {
		long companyId = GetterUtil.getLong(ids[0]);
		reindex(companyId);

	}

	@Override
	protected void doReindex(DossierPart object) throws Exception {
		Document document = getDocument(object);
		IndexWriterHelperUtil.updateDocument(getSearchEngineId(), object.getCompanyId(), document,
				isCommitImmediately());
	}

	protected void reindex(long companyId) throws PortalException {
		final IndexableActionableDynamicQuery indexableActionableDynamicQuery = DossierPartLocalServiceUtil
				.getIndexableActionableDynamicQuery();

		indexableActionableDynamicQuery.setCompanyId(companyId);
		indexableActionableDynamicQuery
				.setPerformActionMethod(new ActionableDynamicQuery.PerformActionMethod<DossierPart>() {

					@Override
					public void performAction(DossierPart object) {
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

	Log _log = LogFactoryUtil.getLog(DossierPartIndexer.class);

}
