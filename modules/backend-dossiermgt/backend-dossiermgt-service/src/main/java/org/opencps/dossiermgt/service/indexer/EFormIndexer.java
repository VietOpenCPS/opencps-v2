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

import org.opencps.auth.utils.APIDateTimeUtils;
import org.opencps.dossiermgt.action.util.SpecialCharacterUtils;
import org.opencps.dossiermgt.constants.EFormTerm;
import org.opencps.dossiermgt.model.EForm;
import org.opencps.dossiermgt.service.EFormLocalServiceUtil;
import org.osgi.service.component.annotations.Component;

@Component(
    immediate = true,
    service = BaseIndexer.class
)
public class EFormIndexer extends BaseIndexer<EForm> {
	public static final String CLASS_NAME = EForm.class.getName();

	@Override
	public String getClassName() {
		return CLASS_NAME;
	}

	@Override
	protected void doDelete(EForm object) throws Exception {
		deleteDocument(object.getCompanyId(), object.getPrimaryKey());
	}

	@Override
	protected Document doGetDocument(EForm object) throws Exception {
		Document document = getBaseModelDocument(CLASS_NAME, object);

		try {
			// Indexer of audit fields
			document.addNumberSortable(Field.COMPANY_ID, object.getCompanyId());
			document.addNumberSortable(Field.GROUP_ID, object.getGroupId());
			document.addDateSortable(Field.CREATE_DATE, object.getCreateDate());
			document.addDateSortable(Field.MODIFIED_DATE, object.getModifiedDate());
			document.addNumberSortable(Field.USER_ID, object.getUserId());
			if (Validator.isNotNull(object.getUserName())) {
				document.addKeywordSortable(Field.USER_NAME, String.valueOf(object.getUserName()));
			}
			document.addKeywordSortable(Field.ENTRY_CLASS_NAME, CLASS_NAME);
			document.addNumberSortable(Field.ENTRY_CLASS_PK, object.getPrimaryKey());

			// add number fields
			document.addNumber(EFormTerm.EFORM_ID, object.getEFormId());
			document.addNumber(EFormTerm.FORM_SCRIPT_FILE_ID, object.getFormScriptFileId());
			document.addNumber(EFormTerm.FORM_REPORT_FILE_ID, object.getFormReportFileId());
//			document.addNumber(EFormTerm.STATE, object.getState());

			// add text fields
			document.addTextSortable(EFormTerm.EFORM_NO, object.getEFormNo());
			if (Validator.isNotNull(object.getEFormNo())) {
				document.addTextSortable(EFormTerm.EFORM_NO_SEARCH,
						SpecialCharacterUtils.splitSpecial(object.getEFormNo()));
			}

			document.addTextSortable(EFormTerm.SERVICE_CODE, object.getServiceCode());
			if (Validator.isNotNull(object.getServiceCode())) {
				document.addTextSortable(EFormTerm.SERVICE_CODE_SEARCH,
						SpecialCharacterUtils.splitSpecial(object.getServiceCode()));
			}
			document.addTextSortable(EFormTerm.FILE_TEMPLATE_NO, object.getFileTemplateNo());
			document.addTextSortable(EFormTerm.EFORM_NAME, object.getEFormName());
			document.addTextSortable(EFormTerm.EFORM_DATA, object.getEFormData());
			document.addTextSortable(EFormTerm.EMAIL, object.getEmail());
			document.addTextSortable(EFormTerm.SECRET, object.getSecret());
//			document.addTextSortable(EFormTerm.GATE_NUMBER, object.getGateNumber());
//
//			if (Validator.isNotNull(object.getCheckinDate())) {
//				document.addTextSortable(EFormTerm.CHECK_IN_DATE, APIDateTimeUtils
//						.convertDateToString(object.getCheckinDate(), APIDateTimeUtils._NORMAL_PARTTERN));
//			} else {
//				document.addTextSortable(EFormTerm.CHECK_IN_DATE, StringPool.BLANK);
//			}
//
//			if (Validator.isNotNull(object.getCheckinDate())) {
//				document.addDateSortable(EFormTerm.CHECK_IN_DATE_LUCENE, object.getCheckinDate());
//			} else {
//				document.addTextSortable(EFormTerm.CHECK_IN_DATE_LUCENE, StringPool.BLANK);
//			}

		} catch (Exception e) {
			_log.error(e);
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
		EForm object = EFormLocalServiceUtil.getEForm(classPK);
		doReindex(object);
	}

	@Override
	protected void doReindex(String[] ids) throws Exception {
		long companyId = GetterUtil.getLong(ids[0]);
		reindex(companyId);
	}

	@Override
	protected void doReindex(EForm object) throws Exception {
		Document document = getDocument(object);
		IndexWriterHelperUtil.updateDocument(getSearchEngineId(), object.getCompanyId(), document,
				isCommitImmediately());

	}

	protected void reindex(long companyId) throws PortalException {
		final IndexableActionableDynamicQuery indexableActionableDynamicQuery = EFormLocalServiceUtil
				.getIndexableActionableDynamicQuery();

		indexableActionableDynamicQuery.setCompanyId(companyId);
		indexableActionableDynamicQuery
				.setPerformActionMethod(new ActionableDynamicQuery.PerformActionMethod<EForm>() {

					@Override
					public void performAction(EForm object) {
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

	Log _log = LogFactoryUtil.getLog(EFormIndexer.class);

}
