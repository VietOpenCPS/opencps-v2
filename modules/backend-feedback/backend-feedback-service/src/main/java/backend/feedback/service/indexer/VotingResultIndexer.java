package backend.feedback.service.indexer;

import java.util.LinkedHashMap;
import java.util.Locale;

import javax.portlet.PortletRequest;
import javax.portlet.PortletResponse;

import org.opencps.usermgt.constants.CommonTerm;

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

import backend.feedback.constants.VotingResultTerm;
import backend.feedback.model.VotingResult;
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
		
		document.setSortableTextFields(
				new String[] { VotingResultTerm.CREATE_DATE});

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
