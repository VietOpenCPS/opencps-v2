package org.opencps.usermgt.service.indexer;

import java.util.Locale;

import javax.portlet.PortletRequest;
import javax.portlet.PortletResponse;

import org.opencps.usermgt.constants.ApplicantTerm;
import org.opencps.usermgt.model.Applicant;
import org.opencps.usermgt.service.ApplicantLocalServiceUtil;
import org.osgi.service.component.annotations.Component;

import com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.search.BaseIndexer;
import com.liferay.portal.kernel.search.Document;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.search.IndexWriterHelperUtil;
import com.liferay.portal.kernel.search.Summary;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.util.GetterUtil;

@Component(
    immediate = true,
    service = BaseIndexer.class
)
public class ApplicantIndexer extends BaseIndexer<Applicant> {

	public static final String CLASS_NAME = Applicant.class.getName();

	@Override
	public String getClassName() {
		return CLASS_NAME;
	}

	@Override
	protected void doDelete(Applicant object) throws Exception {
		deleteDocument(object.getCompanyId(), object.getApplicantId());
	}

	@Override
	protected Document doGetDocument(Applicant object) throws Exception {

		Document document = getBaseModelDocument(CLASS_NAME, object);

		// Indexer of audit fields
		document.addNumberSortable(Field.COMPANY_ID, object.getCompanyId());
		document.addNumberSortable(Field.GROUP_ID, object.getGroupId());
		document.addDateSortable(Field.MODIFIED_DATE, object.getCreateDate());
		document.addDateSortable(Field.CREATE_DATE, object.getModifiedDate());
		document.addNumberSortable(Field.USER_ID, object.getUserId());
		document.addKeywordSortable(Field.USER_NAME, String.valueOf(object.getUserName()));
		document.addKeywordSortable(Field.ENTRY_CLASS_NAME, Applicant.class.getName());
		document.addNumberSortable(Field.ENTRY_CLASS_PK, object.getPrimaryKey());

		document.addKeywordSortable(ApplicantTerm.APPLICANTNAME, object.getApplicantName());
		document.addKeywordSortable(ApplicantTerm.APPLICANTIDNO, object.getApplicantIdNo());
		document.addTextSortable(ApplicantTerm.APPLICANTIDTYPE, object.getApplicantIdType());
		document.addDateSortable(ApplicantTerm.APPLICANTIDDATE, object.getApplicantIdDate());
		document.addTextSortable(ApplicantTerm.ADDRESS, object.getAddress());
		document.addTextSortable(ApplicantTerm.CONTACTNAME, object.getContactName());
		document.addTextSortable(ApplicantTerm.CONTACTTELNO, object.getContactTelNo());
		document.addTextSortable(ApplicantTerm.CONTACTEMAIL, object.getContactEmail());
		document.addNumberSortable(ApplicantTerm.MAPPINGUSERID, object.getMappingUserId());
		document.addTextSortable(ApplicantTerm.CITYCODE, object.getCityCode());
		document.addTextSortable(ApplicantTerm.CITYNAME, object.getCityName());
		document.addTextSortable(ApplicantTerm.DISTRICTCODE, object.getDistrictCode());
		document.addTextSortable(ApplicantTerm.DISTRICTNAME, object.getDistrictName());
		document.addTextSortable(ApplicantTerm.WARDCODE, object.getWardCode());
		document.addTextSortable(ApplicantTerm.WARDNAME, object.getWardName());

		try {
			if (object.getMappingUserId() > 0) {
				User user = UserLocalServiceUtil.getUser(object.getMappingUserId());
				document.addTextSortable(ApplicantTerm.LOCK, Boolean.toString(user.getLockout()));
			}
		} catch (Exception e) {
			_log.debug(e);
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
		Applicant object = ApplicantLocalServiceUtil.getApplicant(classPK);
		doReindex(object);
	}

	@Override
	protected void doReindex(String[] ids) throws Exception {
		long companyId = GetterUtil.getLong(ids[0]);
		reindex(companyId);
	}

	protected void reindex(long companyId) throws PortalException {
		final IndexableActionableDynamicQuery indexableActionableDynamicQuery = ApplicantLocalServiceUtil
				.getIndexableActionableDynamicQuery();

		indexableActionableDynamicQuery.setCompanyId(companyId);
		indexableActionableDynamicQuery
				.setPerformActionMethod(new ActionableDynamicQuery.PerformActionMethod<Applicant>() {

					@Override
					public void performAction(Applicant applicant) {
						try {
							Document document = getDocument(applicant);

							indexableActionableDynamicQuery.addDocuments(document);
						} catch (PortalException pe) {
							if (_log.isWarnEnabled()) {
								_log.warn("Unable to index contact " + applicant.getPrimaryKey(), pe);
							}
						}
					}

				});
		indexableActionableDynamicQuery.setSearchEngineId(getSearchEngineId());

		indexableActionableDynamicQuery.performActions();
	}

	@Override
	protected void doReindex(Applicant object) throws Exception {
		Document document = getDocument(object);
		IndexWriterHelperUtil.updateDocument(getSearchEngineId(), object.getCompanyId(), document,
				isCommitImmediately());
	}

	Log _log = LogFactoryUtil.getLog(ApplicantIndexer.class);
}
