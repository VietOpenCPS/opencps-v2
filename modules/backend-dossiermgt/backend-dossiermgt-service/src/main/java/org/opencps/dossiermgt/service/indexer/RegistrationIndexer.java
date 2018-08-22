package org.opencps.dossiermgt.service.indexer;

import java.util.Locale;

import javax.portlet.PortletRequest;
import javax.portlet.PortletResponse;

import org.opencps.dossiermgt.constants.RegistrationTerm;
import org.opencps.dossiermgt.model.Registration;
import org.opencps.dossiermgt.service.RegistrationLocalServiceUtil;
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
public class RegistrationIndexer extends BaseIndexer<Registration> {

	public static final String CLASS_NAME = Registration.class.getName();

	@Override
	public String getClassName() {
		return CLASS_NAME;
	}

	@Override
	protected void doDelete(Registration object) throws Exception {
		deleteDocument(object.getCompanyId(), object.getPrimaryKey());
	}

	@Override
	protected Document doGetDocument(Registration object) throws Exception {
		Document document = getBaseModelDocument(CLASS_NAME, object);

		// Indexer of audit fields
		document.addNumberSortable(Field.COMPANY_ID, object.getCompanyId());
		document.addNumberSortable(Field.GROUP_ID, object.getGroupId());
		document.addDateSortable(Field.CREATE_DATE, object.getCreateDate());
		document.addDateSortable(Field.MODIFIED_DATE, object.getModifiedDate());
		document.addNumberSortable(Field.USER_ID, object.getUserId());
		document.addKeywordSortable(Field.ENTRY_CLASS_NAME, CLASS_NAME);
		document.addNumberSortable(Field.ENTRY_CLASS_PK, object.getPrimaryKey());

		document.addNumberSortable(RegistrationTerm.REGISTRATION_ID, object.getRegistrationId());
		document.addKeywordSortable(RegistrationTerm.APPLICATION_NAME, object.getApplicantName());
		document.addKeywordSortable(RegistrationTerm.APPLICATION_ID_TYPE, object.getApplicantIdType());
		document.addKeywordSortable(RegistrationTerm.APPLICATION_ID_NO, object.getApplicantIdNo());
		if (null != object.getApplicantIdDate()) {
			document.addDateSortable(RegistrationTerm.APPLICATION_ID_DATE, object.getApplicantIdDate());
		}
		document.addKeywordSortable(RegistrationTerm.ADDRESS, object.getAddress());
		document.addKeywordSortable(RegistrationTerm.CITY_CODE, object.getCityCode());
		document.addKeywordSortable(RegistrationTerm.CITY_NAME, object.getCityName());
		document.addKeywordSortable(RegistrationTerm.DISTRICT_CODE, object.getDistrictCode());
		document.addKeywordSortable(RegistrationTerm.DISTRICT_NAME, object.getDistrictName());
		document.addKeywordSortable(RegistrationTerm.WARD_CODE, object.getWardCode());
		document.addKeywordSortable(RegistrationTerm.WARD_NAME, object.getWardName());
		document.addKeywordSortable(RegistrationTerm.CONTACT_NAME, object.getContactName());
		document.addKeywordSortable(RegistrationTerm.CONTACT_TEL_NO, object.getContactTelNo());
		document.addKeywordSortable(RegistrationTerm.CONTACT_EMAIL, object.getContactEmail());
		document.addKeywordSortable(RegistrationTerm.GOV_AGENCY_CODE, object.getGovAgencyCode());
		document.addKeywordSortable(RegistrationTerm.GOV_AGENCY_NAME, object.getGovAgencyName());
		document.addKeywordSortable(RegistrationTerm.REGISTRATION_CLASS, object.getRegistrationClass());
		document.addNumberSortable(RegistrationTerm.REGISTRATIONSTATE, object.getRegistrationState());
		document.addKeywordSortable(RegistrationTerm.SUBMITTING, Boolean.toString(object.getSubmitting()));

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
		Registration object = RegistrationLocalServiceUtil.getRegistration(classPK);
		doReindex(object);
	}

	@Override
	protected void doReindex(String[] ids) throws Exception {
		long companyId = GetterUtil.getLong(ids[0]);
		reindex(companyId);
	}

	protected void reindex(long companyId) throws PortalException {
		final IndexableActionableDynamicQuery indexableActionableDynamicQuery = RegistrationLocalServiceUtil
				.getIndexableActionableDynamicQuery();

		indexableActionableDynamicQuery.setCompanyId(companyId);
		indexableActionableDynamicQuery
				.setPerformActionMethod(new ActionableDynamicQuery.PerformActionMethod<Registration>() {

					@Override
					public void performAction(Registration object) {
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

	@Override
	protected void doReindex(Registration object) throws Exception {
		Document document = getDocument(object);
		IndexWriterHelperUtil.updateDocument(getSearchEngineId(), object.getCompanyId(), document,
				isCommitImmediately());
	}

	Log _log = LogFactoryUtil.getLog(RegistrationIndexer.class);

}
