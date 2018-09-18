package org.opencps.datamgt.service.indexer;

import java.util.LinkedHashMap;
import java.util.Locale;

import javax.portlet.PortletRequest;
import javax.portlet.PortletResponse;

import org.opencps.datamgt.constants.FileAttachTerm;
import org.opencps.datamgt.model.FileAttach;
import org.opencps.datamgt.service.FileAttachLocalServiceUtil;
import org.osgi.service.component.annotations.Component;

import com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.search.BaseIndexer;
import com.liferay.portal.kernel.search.BooleanQuery;
import com.liferay.portal.kernel.search.Document;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.search.IndexWriterHelperUtil;
import com.liferay.portal.kernel.search.SearchContext;
import com.liferay.portal.kernel.search.Summary;
import com.liferay.portal.kernel.search.filter.BooleanFilter;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.Validator;

@Component(
    immediate = true,
    service = BaseIndexer.class
)
public class FileAttachIndexer extends BaseIndexer<FileAttach> {

	public static final String CLASS_NAME = FileAttach.class.getName();

	@Override
	public String getClassName() {
		return CLASS_NAME;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void postProcessSearchQuery(BooleanQuery searchQuery, BooleanFilter fullQueryBooleanFilter,
			SearchContext searchContext) throws Exception {

		LinkedHashMap<String, Object> params = (LinkedHashMap<String, Object>) searchContext.getAttribute("params");

		if (params != null) {
			String expandoAttributes = (String) params.get("expandoAttributes");

			if (Validator.isNotNull(expandoAttributes)) {
				addSearchExpando(searchQuery, searchContext, expandoAttributes);
			}
		}
	}

	@Override
	protected void doDelete(FileAttach FileAttach) throws Exception {
		deleteDocument(FileAttach.getCompanyId(), FileAttach.getFileAttachId());
	}

	@Override
	protected Document doGetDocument(FileAttach fileAttach) throws Exception {
		Document document = getBaseModelDocument(CLASS_NAME, fileAttach);

		document.addKeywordSortable(Field.COMPANY_ID, String.valueOf(fileAttach.getCompanyId()));
		document.addDateSortable(Field.MODIFIED_DATE, fileAttach.getModifiedDate());
		document.addKeywordSortable(Field.USER_ID, String.valueOf(fileAttach.getUserId()));
		document.addKeywordSortable(Field.USER_NAME, String.valueOf(fileAttach.getUserName()));

		document.addNumberSortable(FileAttachTerm.FILEATTACH_ID, fileAttach.getFileAttachId());
		document.addNumberSortable(FileAttachTerm.GROUP_ID, fileAttach.getGroupId());

		document.addTextSortable(FileAttachTerm.CLASS_NAME, fileAttach.getClassName());

		document.addTextSortable(FileAttachTerm.CLASS_PK, fileAttach.getClassPK());

		document.addTextSortable(FileAttachTerm.FULLNAME, fileAttach.getFullName());
		document.addTextSortable(FileAttachTerm.EMAIL, fileAttach.getEmail());
		document.addNumberSortable(FileAttachTerm.FILE_ENTRY_ID, fileAttach.getFileEntryId());
		document.addTextSortable(FileAttachTerm.SOURCE, fileAttach.getSource());
		document.addTextSortable(FileAttachTerm.SOURCE_URL, fileAttach.getSourceUrl());
		document.addNumberSortable(FileAttachTerm.DOCFILE_ID, fileAttach.getDocFileId());
		document.addTextSortable(FileAttachTerm.FILE_NAME, fileAttach.getFileName());

		document.setSortableTextFields(new String[] { FileAttachTerm.CREATE_DATE });

		return document;
	}

	@Override
	protected Summary doGetSummary(Document document, Locale locale, String snippet, PortletRequest portletRequest,
			PortletResponse portletResponse) {

		Summary summary = createSummary(document);

		summary.setMaxContentLength(QueryUtil.ALL_POS);

		return summary;
	}

	@Override
	protected void doReindex(FileAttach fileAttach) throws Exception {
		Document document = getDocument(fileAttach);

		IndexWriterHelperUtil.updateDocument(getSearchEngineId(), fileAttach.getCompanyId(), document,
				isCommitImmediately());
	}

	@Override
	protected void doReindex(String className, long classPK) throws Exception {
		FileAttach fileAttach = FileAttachLocalServiceUtil.fetchFileAttach(classPK);

		doReindex(fileAttach);
	}

	@Override
	protected void doReindex(String[] ids) throws Exception {
		long companyId = GetterUtil.getLong(ids[0]);

		reindexMFileAttach(companyId);
	}

	protected void reindexMFileAttach(long companyId) throws PortalException {
		final IndexableActionableDynamicQuery indexableActionableDynamicQuery = FileAttachLocalServiceUtil
				.getIndexableActionableDynamicQuery();

		indexableActionableDynamicQuery.setCompanyId(companyId);
		indexableActionableDynamicQuery
				.setPerformActionMethod(new ActionableDynamicQuery.PerformActionMethod<FileAttach>() {

					@Override
					public void performAction(FileAttach fileAttach) {
						try {
							Document document = getDocument(fileAttach);

							indexableActionableDynamicQuery.addDocuments(document);
						} catch (PortalException pe) {
							if (_log.isWarnEnabled()) {
								_log.warn("Unable to index FileAttach " + fileAttach.getFileAttachId(), pe);
							}
						}
					}

				});
		indexableActionableDynamicQuery.setSearchEngineId(getSearchEngineId());

		indexableActionableDynamicQuery.performActions();
	}

	private static final Log _log = LogFactoryUtil.getLog(FileAttachIndexer.class);

}
