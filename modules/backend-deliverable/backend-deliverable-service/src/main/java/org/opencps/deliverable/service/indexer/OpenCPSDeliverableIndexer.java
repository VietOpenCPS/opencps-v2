package org.opencps.deliverable.service.indexer;

import com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.search.BaseIndexer;
import com.liferay.portal.kernel.search.Document;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.search.IndexWriterHelperUtil;
import com.liferay.portal.kernel.search.Summary;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.Validator;

import java.util.Iterator;
import java.util.Locale;

import javax.portlet.PortletRequest;
import javax.portlet.PortletResponse;

import org.opencps.deliverable.model.OpenCPSDeliverable;
import org.opencps.deliverable.service.OpenCPSDeliverableLocalServiceUtil;
import org.osgi.service.component.annotations.Component;

import backend.deliverable.service.util.ModelKeysDeliverable;

@Component(
    immediate = true,
    service = BaseIndexer.class
)
public class OpenCPSDeliverableIndexer extends BaseIndexer<OpenCPSDeliverable> {
	public static final String CLASS_NAME = OpenCPSDeliverable.class.getName();

	@Override
	public String getClassName() {
		return CLASS_NAME;
	}

	@Override
	protected void doDelete(OpenCPSDeliverable object) throws Exception {
		deleteDocument(object.getCompanyId(), object.getPrimaryKey());
	}

	@Override
	protected Document doGetDocument(OpenCPSDeliverable object) throws Exception {
		Document document = getBaseModelDocument(CLASS_NAME, object);
		
		//System.out.println("OpenCPSDeliverableIndexer.doGetDocument(abc)" + object);
		
		// Indexer of audit fields
		document.addNumberSortable(Field.COMPANY_ID, object.getCompanyId());
		document.addNumberSortable(Field.GROUP_ID, object.getGroupId());
		document.addDateSortable(Field.MODIFIED_DATE, object.getCreateDate());
		document.addDateSortable(Field.CREATE_DATE, object.getModifiedDate());
		document.addNumberSortable(Field.USER_ID, object.getUserId());
		document.addKeywordSortable(Field.ENTRY_CLASS_NAME, CLASS_NAME);
		document.addNumberSortable(Field.ENTRY_CLASS_PK, object.getPrimaryKey());
		document.addNumberSortable(ModelKeysDeliverable.DELIVERABLEID, object.getDeliverableId());

		// Indexer of custom fields
		document.addTextSortable(ModelKeysDeliverable.DELIVERABLECODE, object.getDeliverableCode());
		document.addTextSortable(ModelKeysDeliverable.DELIVERABLENAME, object.getDeliverableName());
		document.addTextSortable(ModelKeysDeliverable.DELIVERABLETYPE, object.getDeliverableType());
		document.addTextSortable(ModelKeysDeliverable.GOVAGENCYCODE, object.getGovAgencyCode());
		document.addTextSortable(ModelKeysDeliverable.GOVAGENCYNAME, object.getGovAgencyName());
		document.addTextSortable(ModelKeysDeliverable.APPLICANTIDNO, object.getApplicantIdNo());
		document.addTextSortable(ModelKeysDeliverable.APPLICANTNAME, object.getApplicantName());
		document.addTextSortable(ModelKeysDeliverable.SUBJECT, object.getSubject());
		
		document.addNumberSortable(ModelKeysDeliverable.ISSUEDATE, Validator.isNotNull(object.getIssueDate()) ? object.getIssueDate().getTime() : null);
		document.addNumberSortable(ModelKeysDeliverable.EXPIREDATE, Validator.isNotNull(object.getExpireDate()) ? object.getExpireDate().getTime(): null);
		document.addNumberSortable(ModelKeysDeliverable.REVALIDATE, Validator.isNotNull(object.getRevalidate()) ? object.getRevalidate().getTime() : null);
		
		document.addDateSortable(ModelKeysDeliverable.ISSUEDATE + "_date", object.getIssueDate());
		document.addDateSortable(ModelKeysDeliverable.EXPIREDATE + "_date", object.getExpireDate());
		document.addDateSortable(ModelKeysDeliverable.REVALIDATE + "_date", object.getRevalidate());
		
		document.addNumberSortable(ModelKeysDeliverable.DELIVERABLESTATE, object.getDeliverableState());
		document.addNumberSortable(ModelKeysDeliverable.FILEENTRYID, object.getFileEntryId());
		document.addNumberSortable(ModelKeysDeliverable.DOCSYNC, object.getDocSync());
		document.addNumberSortable(ModelKeysDeliverable.DOSSIERID, object.getDossierId());
		document.addText(ModelKeysDeliverable.FORMDATA, object.getFormData());
		
		try {

			//System.out.println("OpenCPSDeliverableIndexer.doGetDocument(object.getFormData())" + object.getFormData());
			JSONObject jsonObject = JSONFactoryUtil.createJSONObject(object.getFormData());

			Iterator<String> keys = jsonObject.keys();

			while(keys.hasNext()) {
			    String key = keys.next();
			    document.addTextSortable(key, jsonObject.getString(key));
			}
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
		OpenCPSDeliverable object = OpenCPSDeliverableLocalServiceUtil.fetchOpenCPSDeliverable(classPK);
		doReindex(object);
	}

	@Override
	protected void doReindex(String[] ids) throws Exception {
		long companyId = GetterUtil.getLong(ids[0]);
		reindex(companyId);
	}

	@Override
	protected void doReindex(OpenCPSDeliverable object) throws Exception {
		Document document = getDocument(object);
		IndexWriterHelperUtil.updateDocument(getSearchEngineId(), object.getCompanyId(), document,
				isCommitImmediately());

	}

	protected void reindex(long companyId) throws PortalException {
		final IndexableActionableDynamicQuery indexableActionableDynamicQuery = OpenCPSDeliverableLocalServiceUtil
				.getIndexableActionableDynamicQuery();

		indexableActionableDynamicQuery.setCompanyId(companyId);
		indexableActionableDynamicQuery
				.setPerformActionMethod(new ActionableDynamicQuery.PerformActionMethod<OpenCPSDeliverable>() {

					@Override
					public void performAction(OpenCPSDeliverable object) {
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

	Log _log = LogFactoryUtil.getLog(OpenCPSDeliverableIndexer.class);

}
