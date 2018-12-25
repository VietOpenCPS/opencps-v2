package backend.feedback.service.indexer;

import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.search.BaseIndexer;
import com.liferay.portal.kernel.search.BooleanQuery;
import com.liferay.portal.kernel.search.Document;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.search.IndexWriterHelperUtil;
import com.liferay.portal.kernel.search.SearchContext;
import com.liferay.portal.kernel.search.Summary;
import com.liferay.portal.kernel.search.filter.BooleanFilter;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.Validator;

import java.util.LinkedHashMap;
import java.util.Locale;

import javax.portlet.PortletRequest;
import javax.portlet.PortletResponse;

import org.opencps.usermgt.constants.CommonTerm;

import backend.feedback.constants.VotingTerm;
import backend.feedback.model.Voting;
import backend.feedback.service.VotingLocalServiceUtil;

public class VotingIndexer extends BaseIndexer<Voting> {

	public static final String CLASS_NAME = Voting.class.getName();

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
	protected void doDelete(Voting voting) throws Exception {
		deleteDocument(voting.getCompanyId(), voting.getVotingId());
	}

	@Override
	protected Document doGetDocument(Voting voting) throws Exception {
		Document document = getBaseModelDocument(CLASS_NAME, voting);

		document.addNumberSortable(VotingTerm.VOTING_ID, voting.getVotingId());
		document.addKeywordSortable(Field.COMPANY_ID, String.valueOf(voting.getCompanyId()));
		document.addDateSortable(Field.CREATE_DATE, voting.getCreateDate());
		document.addDateSortable(Field.MODIFIED_DATE, voting.getModifiedDate());
		document.addKeywordSortable(Field.USER_ID, String.valueOf(voting.getUserId()));
		document.addKeywordSortable(Field.USER_NAME, String.valueOf(voting.getUserName()));
		document.addNumberSortable(VotingTerm.GROUP_ID, voting.getGroupId());
		
		User user = UserLocalServiceUtil.fetchUser(voting.getUserId());
		
		document.addTextSortable(VotingTerm.EMAIL, Validator.isNotNull(user)?user.getEmailAddress():StringPool.BLANK);
		document.addTextSortable(VotingTerm.CLASS_NAME, voting.getClassName());
		document.addTextSortable(VotingTerm.CLASS_PK, voting.getClassPK());
		document.addTextSortable(VotingTerm.SUBJECT, voting.getSubject());
		document.addTextSortable(VotingTerm.CHOICES, voting.getChoices());
		document.addTextSortable(VotingTerm.TEMPLATE_NO, voting.getTemplateNo());
		document.addTextSortable(VotingTerm.COMMENTABLE, String.valueOf(voting.getCommentable()));
		document.addTextSortable(VotingTerm.VOTING_CODE, voting.getVotingCode());
		
		document.setSortableTextFields(
				new String[] { Field.CREATE_DATE});

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
	protected void doReindex(Voting voting) throws Exception {
		Document document = getDocument(voting);

		IndexWriterHelperUtil.updateDocument(getSearchEngineId(), voting.getCompanyId(), document,
				isCommitImmediately());
	}

	@Override
	protected void doReindex(String className, long classPK) throws Exception {
		Voting voting = VotingLocalServiceUtil.fetchVoting(classPK);

		doReindex(voting);
	}

	@Override
	protected void doReindex(String[] ids) throws Exception {
		long companyId = GetterUtil.getLong(ids[0]);

		reindexMVoting(companyId);
	}

	protected void reindexMVoting(long companyId) throws PortalException {
		final IndexableActionableDynamicQuery indexableActionableDynamicQuery = VotingLocalServiceUtil
				.getIndexableActionableDynamicQuery();

		indexableActionableDynamicQuery.setCompanyId(companyId);
		indexableActionableDynamicQuery
				.setPerformActionMethod(new ActionableDynamicQuery.PerformActionMethod<Voting>() {

					@Override
					public void performAction(Voting voting) {
						try {
							Document document = getDocument(voting);

							indexableActionableDynamicQuery.addDocuments(document);
						} catch (PortalException pe) {
							if (_log.isWarnEnabled()) {
								_log.warn("Unable to index Voting " + voting.getVotingId(), pe);
							}
						}
					}

				});
		indexableActionableDynamicQuery.setSearchEngineId(getSearchEngineId());

		indexableActionableDynamicQuery.performActions();
	}

	private static final Log _log = LogFactoryUtil.getLog(VotingIndexer.class);

}
