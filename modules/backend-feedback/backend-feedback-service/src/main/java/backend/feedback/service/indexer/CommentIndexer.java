
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

import backend.feedback.constants.CommentTerm;
import backend.feedback.model.Comment;
import backend.feedback.service.CommentLocalServiceUtil;

public class CommentIndexer extends BaseIndexer<Comment> {

	public static final String CLASS_NAME = Comment.class.getName();

	@Override
	public String getClassName() {

		return CLASS_NAME;
	}

	@Override
	public void postProcessSearchQuery(BooleanQuery searchQuery, BooleanFilter fullQueryBooleanFilter,
			SearchContext searchContext) throws Exception {

		addSearchTerm(searchQuery, searchContext, CommentTerm.COMMENT_ID, false);
		addSearchTerm(searchQuery, searchContext, Field.GROUP_ID, false);
		addSearchTerm(searchQuery, searchContext, CommentTerm.COMPANY_ID, false);
		addSearchTerm(searchQuery, searchContext, Field.USER_ID, false);
		addSearchTerm(searchQuery, searchContext, Field.USER_NAME, false);
		addSearchTerm(searchQuery, searchContext, Field.CREATE_DATE, false);
		addSearchTerm(searchQuery, searchContext, Field.MODIFIED_DATE, false);

		addSearchTerm(searchQuery, searchContext, CommentTerm.CLASS_NAME, true);
		addSearchTerm(searchQuery, searchContext, CommentTerm.CLASS_PK, true);
		addSearchTerm(searchQuery, searchContext, CommentTerm.FULL_NAME, true);
		addSearchTerm(searchQuery, searchContext, CommentTerm.EMAIL, true);
		addSearchTerm(searchQuery, searchContext, CommentTerm.PARENT, false);
		addSearchTerm(searchQuery, searchContext, CommentTerm.CONTENT, true);
		addSearchTerm(searchQuery, searchContext, CommentTerm.FILE_ENTRY_ID, false);
		addSearchTerm(searchQuery, searchContext, CommentTerm.UPVOTE_COUNT, false);
		addSearchTerm(searchQuery, searchContext, CommentTerm.USER_HAS_UPVOTED, true);
		addSearchTerm(searchQuery, searchContext, CommentTerm.PINGS, false);
		// addSearchTerm(searchQuery, searchContext,
		// CommentTerm.CREATED_BY_CURRENT_USER, false);

		LinkedHashMap<String, Object> params = (LinkedHashMap<String, Object>) searchContext.getAttribute("params");

		if (params != null) {
			String expandoAttributes = (String) params.get("expandoAttributes");

			if (Validator.isNotNull(expandoAttributes)) {
				addSearchExpando(searchQuery, searchContext, expandoAttributes);
			}
		}
	}

	@Override
	protected void doDelete(Comment comment) throws Exception {

		deleteDocument(comment.getCompanyId(), comment.getCommentId());
	}

	@Override
	protected Document doGetDocument(Comment comment) throws Exception {

		Document document = getBaseModelDocument(CLASS_NAME, comment);

		document.addNumberSortable(CommentTerm.COMMENT_ID, comment.getCommentId());
		document.addKeywordSortable(Field.COMPANY_ID, String.valueOf(comment.getCompanyId()));
		document.addDateSortable(Field.MODIFIED_DATE, comment.getModifiedDate());
		document.addKeywordSortable(Field.USER_ID, String.valueOf(comment.getUserId()));
		document.addKeywordSortable(Field.USER_NAME, String.valueOf(comment.getUserName()));
		document.addNumberSortable(CommentTerm.GROUP_ID, comment.getGroupId());

		document.addTextSortable(CommentTerm.CLASS_NAME, comment.getClassName());
		document.addTextSortable(CommentTerm.PINGS, comment.getPings());
		document.addTextSortable(CommentTerm.CLASS_PK, comment.getClassPK());
//		document.addTextSortable(CommentTerm.FULL_NAME, comment.getFullName());
		document.addTextSortable(CommentTerm.EMAIL, comment.getEmail());
		document.addNumberSortable(CommentTerm.PARENT, comment.getParent());
		document.addTextSortable(CommentTerm.CONTENT, comment.getContent());
		document.addNumberSortable(CommentTerm.FILE_ENTRY_ID, comment.getFileEntryId());
		document.addNumberSortable(CommentTerm.UPVOTE_COUNT, comment.getUpvoteCount());
//		document.addTextSortable(CommentTerm.USER_HAS_UPVOTED, comment.getUpvotedUsers());

		document.setSortableTextFields(new String[] { CommentTerm.CREATE_DATE });

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
	protected void doReindex(Comment comment) throws Exception {

		Document document = getDocument(comment);

		IndexWriterHelperUtil.updateDocument(getSearchEngineId(), comment.getCompanyId(), document,
				isCommitImmediately());
	}

	@Override
	protected void doReindex(String className, long classPK) throws Exception {

		Comment comment = CommentLocalServiceUtil.fetchComment(classPK);

		doReindex(comment);
	}

	@Override
	protected void doReindex(String[] ids) throws Exception {

		long companyId = GetterUtil.getLong(ids[0]);

		reindexMComment(companyId);
	}

	protected void reindexMComment(long companyId) throws PortalException {

		final IndexableActionableDynamicQuery indexableActionableDynamicQuery = CommentLocalServiceUtil
				.getIndexableActionableDynamicQuery();

		indexableActionableDynamicQuery.setCompanyId(companyId);
		indexableActionableDynamicQuery
				.setPerformActionMethod(new ActionableDynamicQuery.PerformActionMethod<Comment>() {

					@Override
					public void performAction(Comment comment) {

						try {
							Document document = getDocument(comment);

							indexableActionableDynamicQuery.addDocuments(document);
						} catch (PortalException pe) {
							if (_log.isWarnEnabled()) {
								_log.warn("Unable to index Comment " + comment.getCommentId(), pe);
							}
						}
					}

				});
		indexableActionableDynamicQuery.setSearchEngineId(getSearchEngineId());

		indexableActionableDynamicQuery.performActions();
	}

	private static final Log _log = LogFactoryUtil.getLog(CommentIndexer.class);

}
