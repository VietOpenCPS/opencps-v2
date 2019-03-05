package backend.feedback.service.indexer;

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

import java.util.Calendar;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;

import javax.portlet.PortletRequest;
import javax.portlet.PortletResponse;

import org.opencps.dossiermgt.constants.DossierTerm;
import org.opencps.dossiermgt.model.Dossier;
import org.opencps.dossiermgt.model.ServiceInfo;
import org.opencps.dossiermgt.service.DossierLocalServiceUtil;
import org.opencps.dossiermgt.service.ServiceInfoLocalServiceUtil;
import org.opencps.usermgt.constants.CommonTerm;
import org.opencps.usermgt.model.Employee;
import org.opencps.usermgt.service.EmployeeLocalServiceUtil;

import backend.feedback.constants.VotingResultTerm;
import backend.feedback.constants.VotingTerm;
import backend.feedback.model.Voting;
import backend.feedback.model.VotingResult;
import backend.feedback.service.VotingLocalServiceUtil;
import backend.feedback.service.VotingResultLocalServiceUtil;

public class VotingResultIndexer extends BaseIndexer<VotingResult> {

	public static final String CLASS_NAME = VotingResult.class.getName();

	@Override
	public String getClassName() {
		return CLASS_NAME;
	}

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
	protected void doDelete(VotingResult VotingResult) throws Exception {
		deleteDocument(VotingResult.getCompanyId(), VotingResult.getVotingResultId());
	}

	@Override
	protected Document doGetDocument(VotingResult VotingResult) throws Exception {
		Document document = getBaseModelDocument(CLASS_NAME, VotingResult);

		document.addNumberSortable(VotingResultTerm.VOTING_RESULT_ID, VotingResult.getVotingResultId());
		document.addKeywordSortable(Field.COMPANY_ID, String.valueOf(VotingResult.getCompanyId()));
		document.addDateSortable(Field.CREATE_DATE, VotingResult.getCreateDate());
		document.addDateSortable(Field.MODIFIED_DATE, VotingResult.getModifiedDate());
		document.addKeywordSortable(Field.USER_ID, String.valueOf(VotingResult.getUserId()));
		document.addKeywordSortable(Field.USER_NAME, String.valueOf(VotingResult.getUserName()));
		document.addNumberSortable(VotingResultTerm.GROUP_ID, VotingResult.getGroupId());
		
		document.addNumberSortable(VotingResultTerm.VOTING_ID, VotingResult.getVotingId());
//		document.addNumberSortable(VotingResultTerm.TOUSERID, VotingResult.getToUserId());
		document.addTextSortable(VotingResultTerm.FULLNAME, VotingResult.getFullname());
		document.addTextSortable(VotingResultTerm.EMAIL, VotingResult.getEmail());
		document.addTextSortable(VotingResultTerm.COMMENT, VotingResult.getComment());
		document.addTextSortable(VotingResultTerm.SELECTED, VotingResult.getSelected());
		//Index month, year using search statistic
		int yearVoting = 0;
		int monthVoting = 0;
		if (Validator.isNotNull(VotingResult.getCreateDate())) {
			Calendar cal = Calendar.getInstance();
			cal.setTime(VotingResult.getCreateDate());
			yearVoting = cal.get(Calendar.YEAR);
			monthVoting = cal.get(Calendar.MONTH) + 1;
		}
		document.addNumberSortable(VotingResultTerm.YEAR_VOTING, yearVoting);
		document.addNumberSortable(VotingResultTerm.MONTH_VOTING, monthVoting);
		//_log.info("yearDossier: "+yearDossier);
		//_log.info("monthDossier: "+monthDossier);
		long votingId = VotingResult.getVotingId();
		if (votingId > 0) {
			Voting voting = VotingLocalServiceUtil.fetchVoting(votingId);
			if (voting != null) {
				String className = voting.getClassName();
				document.addTextSortable(VotingTerm.CLASS_NAME, className);
				document.addTextSortable(VotingTerm.CLASS_PK, voting.getClassPK());
				document.addTextSortable(VotingTerm.VOTING_CODE, voting.getVotingCode());
				document.addTextSortable(VotingTerm.VOTING_SUBJECT, voting.getSubject());
				//Process index dossier
				long classPK = GetterUtil.getLong(voting.getClassPK());
				if (classPK > 0) {
					//_log.info("classPK: "+classPK);
					//_log.info("className: "+className);
					if ("employee".equals(className)) {
						Employee employee = EmployeeLocalServiceUtil.fetchEmployee(classPK);
						if (employee != null) {
							List<Dossier> dossierList = DossierLocalServiceUtil.findByGID(employee.getGroupId(), 0, 2);
							if (dossierList != null && dossierList.size() > 0) {
								Dossier dossier = dossierList.get(0);
								if (dossier != null) {
									document.addTextSortable(DossierTerm.GOV_AGENCY_CODE, dossier.getGovAgencyCode());
									document.addTextSortable(DossierTerm.GOV_AGENCY_NAME, dossier.getGovAgencyName());
								}
							}
							
						}
					} else if ("dossier".equals(className)) {
						Dossier dossier = DossierLocalServiceUtil.fetchDossier(classPK);
						if (dossier != null) {
							document.addTextSortable(DossierTerm.GOV_AGENCY_CODE, dossier.getGovAgencyCode());
							document.addTextSortable(DossierTerm.GOV_AGENCY_NAME, dossier.getGovAgencyName());
							//
							ServiceInfo serviceInfo = ServiceInfoLocalServiceUtil.getByCode(dossier.getGroupId(),
									dossier.getServiceCode());
							if (serviceInfo != null) {
								document.addTextSortable(DossierTerm.DOMAIN_CODE, serviceInfo.getDomainCode());
								document.addTextSortable(DossierTerm.DOMAIN_NAME, serviceInfo.getDomainName());
							}
						}
					}
				}
			}
		}
		
		document.setSortableTextFields(new String[] { VotingResultTerm.CREATE_DATE});

		return document;
	}

	@Override
	protected String doGetSortField(String orderByCol) {
		if (CommonTerm.EMAIL_DASH_ADDRESS.equals(orderByCol)) {
			return CommonTerm.EMAIL_ADDRESS;
		} else if (CommonTerm.FIRST_DASH_NAME.equals(orderByCol)) {
			return CommonTerm.FIRST_NAME;
		} else if (CommonTerm.JOB_DASH_TITLE.equals(orderByCol)) {
			return CommonTerm.JOB_TITLE;
		} else if (CommonTerm.LAST_DASH_NAME.equals(orderByCol)) {
			return CommonTerm.LAST_NAME;
		} else {
			return orderByCol;
		}
	}

	@Override
	protected Summary doGetSummary(Document document, Locale locale, String snippet, PortletRequest portletRequest,
			PortletResponse portletResponse) {

		Summary summary = createSummary(document);

		summary.setMaxContentLength(QueryUtil.ALL_POS);

		return summary;
	}

	@Override
	protected void doReindex(VotingResult VotingResult) throws Exception {
		Document document = getDocument(VotingResult);

		IndexWriterHelperUtil.updateDocument(getSearchEngineId(), VotingResult.getCompanyId(), document,
				isCommitImmediately());
	}

	@Override
	protected void doReindex(String className, long classPK) throws Exception {
		VotingResult VotingResult = VotingResultLocalServiceUtil.fetchVotingResult(classPK);

		doReindex(VotingResult);
	}

	@Override
	protected void doReindex(String[] ids) throws Exception {
		long companyId = GetterUtil.getLong(ids[0]);

		reindexMVotingResult(companyId);
	}

	protected void reindexMVotingResult(long companyId) throws PortalException {
		final IndexableActionableDynamicQuery indexableActionableDynamicQuery = VotingResultLocalServiceUtil
				.getIndexableActionableDynamicQuery();

		indexableActionableDynamicQuery.setCompanyId(companyId);
		indexableActionableDynamicQuery
				.setPerformActionMethod(new ActionableDynamicQuery.PerformActionMethod<VotingResult>() {

					@Override
					public void performAction(VotingResult VotingResult) {
						try {
							Document document = getDocument(VotingResult);

							indexableActionableDynamicQuery.addDocuments(document);
						} catch (PortalException pe) {
							if (_log.isWarnEnabled()) {
								_log.warn("Unable to index VotingResult " + VotingResult.getVotingResultId(), pe);
							}
						}
					}

				});
		indexableActionableDynamicQuery.setSearchEngineId(getSearchEngineId());

		indexableActionableDynamicQuery.performActions();
	}

	private static final Log _log = LogFactoryUtil.getLog(VotingResultIndexer.class);

}
