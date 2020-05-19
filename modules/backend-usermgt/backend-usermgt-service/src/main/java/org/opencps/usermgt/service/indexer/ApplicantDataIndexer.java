package org.opencps.usermgt.service.indexer;

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

import java.util.Locale;

import javax.portlet.PortletRequest;
import javax.portlet.PortletResponse;

import org.opencps.usermgt.constants.ApplicantDataTerm;
import org.opencps.usermgt.model.ApplicantData;
import org.opencps.usermgt.service.ApplicantDataLocalServiceUtil;
import org.osgi.service.component.annotations.Component;

@Component(
	immediate = true,
	service = BaseIndexer.class
)
public class ApplicantDataIndexer extends BaseIndexer<ApplicantData> {
	public static final String CLASS_NAME = ApplicantData.class.getName();

	@Override
	public String getClassName() {
		return CLASS_NAME;
	}

	@Override
	protected void doDelete(ApplicantData object) throws Exception {
		deleteDocument(object.getCompanyId(), object.getApplicantDataId());
	}

	@Override
	protected Document doGetDocument(ApplicantData object) throws Exception {

		Document document = getBaseModelDocument(CLASS_NAME, object);

		// Indexer of audit fields
		document.addNumberSortable(Field.COMPANY_ID, object.getCompanyId());
		document.addNumberSortable(Field.GROUP_ID, object.getGroupId());
		document.addDateSortable(ApplicantDataTerm.MODIFIED_DATE, object.getModifiedDate());
		document.addDateSortable(ApplicantDataTerm.CREATE_DATE, object.getCreateDate());
		document.addNumberSortable(Field.USER_ID, object.getUserId());
		document.addKeywordSortable(Field.USER_NAME, String.valueOf(object.getUserName()));
		document.addKeywordSortable(Field.ENTRY_CLASS_NAME, ApplicantData.class.getName());
		document.addNumberSortable(Field.ENTRY_CLASS_PK, object.getPrimaryKey());

		document.addTextSortable(ApplicantDataTerm.FILE_TEMPLATE_NO, object.getFileTemplateNo());
		document.addTextSortable(ApplicantDataTerm.FILE_NO, object.getFileNo());
		document.addTextSortable(ApplicantDataTerm.FILE_NAME, object.getFileName());
		document.addNumber(ApplicantDataTerm.FILE_ENTRY_ID, object.getFileEntryId());
		document.addTextSortable(ApplicantDataTerm.META_DATA, object.getMetadata());
		document.addNumber(ApplicantDataTerm.STATUS, object.getStatus());
		document.addTextSortable(ApplicantDataTerm.APPLICANT_ID_NO, object.getApplicantIdNo());
		document.addNumber(ApplicantDataTerm.APPLICANT_DATA_TYPE, object.getApplicantDataType());

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
		ApplicantData object = ApplicantDataLocalServiceUtil.fetchApplicantData(classPK);
		doReindex(object);
	}

	@Override
	protected void doReindex(String[] ids) throws Exception {
		long companyId = GetterUtil.getLong(ids[0]);
		reindex(companyId);
	}

	protected void reindex(long companyId) throws PortalException {
		final IndexableActionableDynamicQuery indexableActionableDynamicQuery = ApplicantDataLocalServiceUtil
				.getIndexableActionableDynamicQuery();

		indexableActionableDynamicQuery.setCompanyId(companyId);
		indexableActionableDynamicQuery
				.setPerformActionMethod(new ActionableDynamicQuery.PerformActionMethod<ApplicantData>() {

					@Override
					public void performAction(ApplicantData applicantData) {
						try {
							Document document = getDocument(applicantData);

							indexableActionableDynamicQuery.addDocuments(document);
						} catch (PortalException pe) {
							if (_log.isWarnEnabled()) {
								_log.warn("Unable to index contact " + applicantData.getPrimaryKey(), pe);
							}
						}
					}

				});
		indexableActionableDynamicQuery.setSearchEngineId(getSearchEngineId());

		indexableActionableDynamicQuery.performActions();
	}

	@Override
	protected void doReindex(ApplicantData object) throws Exception {
		Document document = getDocument(object);
		IndexWriterHelperUtil.updateDocument(getSearchEngineId(), object.getCompanyId(), document,
				isCommitImmediately());
	}

	Log _log = LogFactoryUtil.getLog(ApplicantDataIndexer.class);
}
