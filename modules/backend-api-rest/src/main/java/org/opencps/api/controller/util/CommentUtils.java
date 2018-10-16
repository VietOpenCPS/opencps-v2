
package org.opencps.api.controller.util;

import com.liferay.document.library.kernel.service.DLAppLocalServiceUtil;
import com.liferay.document.library.kernel.util.DLUtil;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.search.Document;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.security.permission.PermissionChecker;
import com.liferay.portal.kernel.security.permission.PermissionCheckerFactoryUtil;
import com.liferay.portal.kernel.security.permission.PermissionThreadLocal;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.ws.rs.core.HttpHeaders;

import org.opencps.api.comment.model.CommentListModel;
import org.opencps.api.comment.model.CommentModel;
import org.opencps.api.comment.model.CommentSearchModel;
import org.opencps.api.comment.model.CommentTopList;
import org.opencps.api.comment.model.CommentTopModel;
import org.opencps.auth.utils.APIDateTimeUtils;
import org.opencps.datamgt.constants.CommentTerm;
import org.opencps.datamgt.model.Comment;
import org.opencps.usermgt.utils.DateTimeUtils;

import backend.auth.api.BackendAuthImpl;
import backend.auth.api.keys.ModelNameKeys;

/**
 * @author trungnt
 */
public class CommentUtils {

	private static Log _log = LogFactoryUtil.getLog(CommentUtils.class);

	public static CommentModel mappingComment(Comment comment, ServiceContext serviceContext) throws Exception {

		long userId = comment.getUserId();
		long fileEntryId = comment.getFileEntryId();
		boolean isNew = false;
		boolean createdByCurrentUser = false;
		String fullname = StringPool.BLANK;
		String emailAddress = StringPool.BLANK;
		String profileURL = StringPool.BLANK;
		String userHasUpvoted = comment.getUserHasUpvoted();
		String createDate = APIDateTimeUtils.convertDateToString(comment.getCreateDate(), DateTimeUtils._TIMESTAMP);
		String modifiedDate = APIDateTimeUtils.convertDateToString(comment.getCreateDate(), DateTimeUtils._TIMESTAMP);

		User userComment = UserLocalServiceUtil.getUser(userId);
		if (userComment != null) {
			emailAddress = userComment.getEmailAddress();
		}

		if (serviceContext.getUserId() == userId && userId > 0) {
			createdByCurrentUser = true;
		}

		// Set permission User
		User user = UserLocalServiceUtil.getUser(serviceContext.getUserId());
		PermissionChecker checker = PermissionCheckerFactoryUtil.create(user);
		PermissionThreadLocal.setPermissionChecker(checker);

		// get profileURL
		CommentModel commentModel = processUpdateComment(createDate, createdByCurrentUser, emailAddress, fileEntryId,
				isNew, fullname, modifiedDate, profileURL, userHasUpvoted, userId, serviceContext);
		//
		commentModel.setClassName(comment.getClassName());
		commentModel.setClassPK(comment.getClassPK());
		commentModel.setCommentId(comment.getCommentId());
		commentModel.setContent(comment.getContent() + StringPool.SPACE);
		commentModel.setParent(comment.getParent());
		commentModel.setPings(comment.getPings());
		commentModel.setUpvoteCount(comment.getUpvoteCount());
		commentModel.setOpinion(comment.getOpinion());

		return commentModel;
	}

	/**
	 * @author trungnt
	 * @param document
	 * @param header
	 * @param serviceContext
	 * @param query
	 * @return
	 * @throws Exception
	 */
	public static CommentModel mappingComment(Document document, HttpHeaders header, ServiceContext serviceContext,
			CommentSearchModel query, String... email) throws Exception {

		long userId = GetterUtil.getLong(document.get(Field.USER_ID));
		long fileEntryId = GetterUtil.getLong(document.get(CommentTerm.FILE_ENTRY_ID));
		boolean isNew = false;
		boolean createdByCurrentUser = false;
		String fullname = StringPool.BLANK;
		String emailAddress = StringPool.BLANK;
		String profileURL = StringPool.BLANK;
		String userHasUpvoted = document.get("userHasUpvoted");

		String createDate = APIDateTimeUtils.convertDateToString(document.getDate(Field.CREATE_DATE),
				DateTimeUtils._TIMESTAMP);
		String modifiedDate = APIDateTimeUtils.convertDateToString(document.getDate(Field.MODIFIED_DATE),
				DateTimeUtils._TIMESTAMP);

		User userLogin = UserLocalServiceUtil.fetchUser(serviceContext.getUserId());
		if (userLogin != null) {
			emailAddress = userLogin.getEmailAddress();
			if(emailAddress.contains("default")) {
				if (email != null && email.length > 0) {
					emailAddress = email[0];
					
					// TODO check createdByCurrentUser by email
					if (Validator.isNotNull(emailAddress) && emailAddress.equals(document.get(CommentTerm.EMAIL))) {
						createdByCurrentUser = true;
					}
					
				}
			}
		}

		// get profileURL
		CommentModel commentModel = processUpdateComment(createDate, createdByCurrentUser, emailAddress, fileEntryId,
				isNew, fullname, modifiedDate, profileURL, userHasUpvoted, userId, serviceContext);

		commentModel.setClassName(document.get(CommentTerm.CLASS_NAME));
		commentModel.setClassPK(document.get(CommentTerm.CLASS_PK));
		commentModel.setCommentId(GetterUtil.getLong(document.get(CommentTerm.COMMENT_ID)));
		commentModel.setContent(document.get(CommentTerm.CONTENT) + StringPool.SPACE);
		commentModel.setParent(GetterUtil.getLong(document.get(CommentTerm.PARENT)));
		commentModel.setPings(document.get(CommentTerm.PINGS));
		commentModel.setUpvoteCount(GetterUtil.getInteger(document.get(CommentTerm.UPVOTE_COUNT)));
		commentModel.setOpinion(
				Validator.isNotNull(document.get(CommentTerm.OPINION))
					? Boolean.parseBoolean(document.get(CommentTerm.OPINION))
					: false);
		commentModel.setCreatedByCurrentUser(createdByCurrentUser);

		return commentModel;
	}

	/**
	 * @author trungnt
	 * @param ls
	 * @param serviceContext
	 * @param header
	 * @param query
	 * @return
	 */
	public static CommentListModel mappingCommentList(List<Document> ls, ServiceContext serviceContext,
			HttpHeaders header, CommentSearchModel query, String... email) {

		CommentListModel commentListModel = new CommentListModel();

		for (Document doc : ls) {
			try {
				commentListModel.getCommentModel().add(mappingComment(doc, header, serviceContext, query, email));
			} catch (Exception e) {
				//_log.error(e);
				_log.debug(e);
			}
		}

		return commentListModel;
	}

	public static CommentTopList mappingCommentTopList(List<Comment> ls, ServiceContext serviceContext) {

		CommentTopList commentListModel = new CommentTopList();

		commentListModel.setTotal(ls.size());

		for (Comment comment : ls) {
			try {
				commentListModel.getCommentTopModel().add(mappingCommentTopModel(comment, serviceContext));
			} catch (Exception e) {
				//_log.error(e);
				_log.debug(e);
			}
		}

		return commentListModel;
	}

	public static CommentTopModel mappingCommentTopModel(Comment comment, ServiceContext serviceContext)
			throws Exception {

		CommentTopModel commentModel = new CommentTopModel();

		long userId = comment.getUserId();
		String createDate = StringPool.BLANK;
		String modifiedDate = StringPool.BLANK;
		String fullname = StringPool.BLANK;
		String emailAddress = StringPool.BLANK;
		// Convert createDate
		try {
			Date date = comment.getCreateDate();
			if (date != null) {
				// convert to ICT format
				SimpleDateFormat dateFormat = new SimpleDateFormat(DateTimeUtils._TIMESTAMP);
				createDate = dateFormat.format(date);
			}
		} catch (Exception e) {
			//_log.error(e);
			_log.debug(e);
			_log.warn("Can't not get createDate " + e.getMessage());
		}

		// Convert modifiedDate
		try {
			Date date = comment.getModifiedDate();
			if (date != null) {
				// convert to ICT format
				SimpleDateFormat dateFormat = new SimpleDateFormat(DateTimeUtils._TIMESTAMP);
				modifiedDate = dateFormat.format(date);
			}

		} catch (Exception e) {
			//_log.error(e);
			_log.debug(e);
			_log.warn("Can't not get modifiedDate " + e.getMessage());
		}

		commentModel.setCreateDate(createDate);
		commentModel.setModifiedDate(modifiedDate);
		commentModel.setClassName(comment.getClassName());
		commentModel.setClassPK(comment.getClassPK());
		commentModel.setCommentId(comment.getCommentId());
		commentModel.setContent(comment.getContent() + StringPool.SPACE);
		commentModel.setEmail(emailAddress);
		commentModel.setFullname(fullname);
		commentModel.setUserId(userId);

		return commentModel;
	}

	private static CommentModel processUpdateComment(String createDate, boolean createdByCurrentUser,
			String emailAddress, long fileEntryId, boolean isNew, String fullname, String modifiedDate,
			String profileURL, String userHasUpvoted, long userId, ServiceContext serviceContext)
			throws PortalException {

		CommentModel commentModel = new CommentModel();

		// Check user
		if (userId > 0) {
			User user = UserLocalServiceUtil.getUser(userId);
			fullname = user.getFullName();

			BackendAuthImpl authImpl = new BackendAuthImpl();
			boolean isAdmin = authImpl.isAdmin(serviceContext, ModelNameKeys.WORKINGUNIT_MGT_CENTER);
			commentModel.setIsAdmin(isAdmin);

			if (serviceContext.getThemeDisplay() != null) {
				String pictureUrl = user.getPortraitURL(serviceContext.getThemeDisplay());
				commentModel.setPictureUrl(pictureUrl);
			}
		}

		if (fileEntryId > 0) {
			FileEntry fileEntry = DLAppLocalServiceUtil.getFileEntry(fileEntryId);
			if (fileEntry != null) {
				commentModel.setFileName(fileEntry.getFileName());
				commentModel.setFileType(fileEntry.getMimeType());
				String fileUrl = DLUtil.getPreviewURL(fileEntry, fileEntry.getFileVersion(), serviceContext.getThemeDisplay(),
						StringPool.BLANK);
				commentModel.setFileUrl(fileUrl);
			}
		}

		if (Validator.isNotNull(userHasUpvoted) && StringUtil.contains(userHasUpvoted, emailAddress)) {
			commentModel.setUserHasUpvoted(true);
		} else {
			commentModel.setUserHasUpvoted(false);
		}

		commentModel.setCreatedByCurrentUser(createdByCurrentUser);
		commentModel.setCreateDate(createDate);
		commentModel.setEmail(emailAddress);
		commentModel.setFullname(fullname);
		commentModel.setIsNew(isNew);
		commentModel.setModifiedDate(modifiedDate);
		commentModel.setProfileUrl(profileURL);
		commentModel.setUserId(userId);
		//
		return commentModel;
	}

}
