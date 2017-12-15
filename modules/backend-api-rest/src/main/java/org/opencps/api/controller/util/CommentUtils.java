
package org.opencps.api.controller.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.ws.rs.core.HttpHeaders;

import org.opencps.api.comment.model.CommentListModel;
import org.opencps.api.comment.model.CommentModel;
import org.opencps.api.comment.model.CommentSearchModel;
import org.opencps.api.comment.model.CommentTopList;
import org.opencps.api.comment.model.CommentTopModel;
import org.opencps.datamgt.constants.CommentTerm;
import org.opencps.datamgt.model.Comment;
import org.opencps.usermgt.utils.DateTimeUtils;

import com.liferay.document.library.kernel.service.DLAppLocalServiceUtil;
import com.liferay.document.library.kernel.util.DLUtil;
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
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;

import backend.auth.api.BackendAuthImpl;
import backend.auth.api.keys.ModelNameKeys;

/**
 * @author trungnt
 */
public class CommentUtils {

	public static CommentModel mappingComment(Comment comment, ServiceContext serviceContext) throws Exception {

		CommentModel commentModel = new CommentModel();

		long userId = comment.getUserId();
		long fileEntryId = comment.getFileEntryId();

		boolean isAdmin = false;
		boolean isNew = false;
		boolean hasUpvoted = false;
		boolean createdByCurrentUser = false;

		String createDate = StringPool.BLANK;
		String modifiedDate = StringPool.BLANK;
		String fullname = StringPool.BLANK;
		String emailAddress = StringPool.BLANK;
		String pictureUrl = StringPool.BLANK;
		String profileURL = StringPool.BLANK;
		String fileName = StringPool.BLANK;
		String fileType = StringPool.BLANK;
		String fileUrl = StringPool.BLANK;
		String userHasUpvoted = comment.getUserHasUpvoted();

		// Convert createDate
		try {
			Date date = comment.getCreateDate();
			if (date != null) {
				// convert to ICT format
				SimpleDateFormat dateFormat = new SimpleDateFormat(DateTimeUtils._TIMESTAMP);
				createDate = dateFormat.format(date);
			}
		} catch (Exception e) {
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
			_log.warn("Can't not get modifiedDate " + e.getMessage());
		}

		// Check user
		if (userId > 0) {
			User user = UserLocalServiceUtil.getUser(userId);
			fullname = user.getFullName();
			emailAddress = user.getEmailAddress();

			BackendAuthImpl authImpl = new BackendAuthImpl();
			isAdmin = authImpl.isAdmin(serviceContext, ModelNameKeys.WORKINGUNIT_MGT_CENTER);

			if (serviceContext.getThemeDisplay() != null) {
				pictureUrl = user.getPortraitURL(serviceContext.getThemeDisplay());
			}

		}

		if (Validator.isNotNull(userHasUpvoted) && StringUtil.contains(userHasUpvoted, emailAddress)) {
			hasUpvoted = true;
		}

		if (fileEntryId > 0) {

			User user = UserLocalServiceUtil.getUser(serviceContext.getUserId());

			PermissionChecker checker = PermissionCheckerFactoryUtil.create(user);

			PermissionThreadLocal.setPermissionChecker(checker);

			FileEntry fileEntry = DLAppLocalServiceUtil.getFileEntry(fileEntryId);

			fileName = fileEntry.getFileName();
			fileType = fileEntry.getMimeType();
			fileUrl = DLUtil.getPreviewURL(fileEntry, fileEntry.getFileVersion(), serviceContext.getThemeDisplay(),
					StringPool.BLANK);

		}

		if (serviceContext.getUserId() == userId && userId > 0) {
			createdByCurrentUser = true;
		}

		// TODO get profileURL

		commentModel.setClassName(comment.getClassName());
		commentModel.setClassPK(comment.getClassPK());
		commentModel.setCommentId(comment.getCommentId());
		commentModel.setContent(comment.getContent() + StringPool.SPACE);
		commentModel.setCreateDate(createDate);
		commentModel.setCreatedByCurrentUser(createdByCurrentUser);
		commentModel.setEmail(emailAddress);
		commentModel.setFileName(fileName);
		commentModel.setFileType(fileType);
		commentModel.setFileUrl(fileUrl);
		commentModel.setFullname(fullname);
		commentModel.setIsAdmin(isAdmin);
		commentModel.setIsNew(isNew);
		commentModel.setModifiedDate(modifiedDate);
		commentModel.setParent(comment.getParent());
		commentModel.setPictureUrl(pictureUrl);
		commentModel.setPings(comment.getPings());
		commentModel.setProfileUrl(profileURL);
		commentModel.setUpvoteCount(comment.getUpvoteCount());
		commentModel.setUserHasUpvoted(hasUpvoted);
		commentModel.setUserId(userId);

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

		CommentModel commentModel = new CommentModel();

		long userId = GetterUtil.getLong(document.get("userId"));
		long fileEntryId = GetterUtil.getLong(document.get("fileEntryId"));

		boolean isAdmin = false;
		boolean isNew = false;
		boolean hasUpvoted = false;
		boolean createdByCurrentUser = false;

		String createDate = StringPool.BLANK;
		String modifiedDate = StringPool.BLANK;
		String fullname = StringPool.BLANK;
		String emailAddress = StringPool.BLANK;
		String pictureUrl = StringPool.BLANK;
		String profileURL = StringPool.BLANK;
		String fileName = StringPool.BLANK;
		String fileType = StringPool.BLANK;
		String fileUrl = StringPool.BLANK;
		String userHasUpvoted = document.get("userHasUpvoted");

		// Convert createDate
		try {
			Date date = document.getDate(Field.CREATE_DATE);
			if (date != null) {
				// convert to ICT format
				SimpleDateFormat dateFormat = new SimpleDateFormat(DateTimeUtils._TIMESTAMP);
				createDate = dateFormat.format(date);
			}
		} catch (Exception e) {
			_log.warn("Can't not get createDate " + e.getMessage());
		}

		// Convert modifiedDate
		try {
			Date date = document.getDate(Field.MODIFIED_DATE);
			if (date != null) {
				// convert to ICT format
				SimpleDateFormat dateFormat = new SimpleDateFormat(DateTimeUtils._TIMESTAMP);
				modifiedDate = dateFormat.format(date);
			}

		} catch (Exception e) {
			_log.warn("Can't not get modifiedDate " + e.getMessage());
		}

		
		// Check user
		if (userId > 0) {
			User user = UserLocalServiceUtil.getUser(userId);
			fullname = user.getFullName();
			//emailAddress = user.getEmailAddress();
			
			BackendAuthImpl authImpl = new BackendAuthImpl();
			isAdmin = authImpl.isAdmin(serviceContext, ModelNameKeys.WORKINGUNIT_MGT_CENTER);

			if (serviceContext.getThemeDisplay() != null) {
				pictureUrl = user.getPortraitURL(serviceContext.getThemeDisplay());
			}

		} 
		
		User userLogin = UserLocalServiceUtil.fetchUser(serviceContext.getUserId());

		if (Validator.isNotNull(userHasUpvoted) && StringUtil.contains(userHasUpvoted, userLogin.getEmailAddress())) {
			hasUpvoted = true;
		}

		if (fileEntryId > 0) {

			FileEntry fileEntry = DLAppLocalServiceUtil.getFileEntry(fileEntryId);

			fileName = fileEntry.getFileName();
			fileType = fileEntry.getMimeType();
			fileUrl = DLUtil.getPreviewURL(fileEntry, fileEntry.getFileVersion(), serviceContext.getThemeDisplay(),
					StringPool.BLANK);

		}

		if (serviceContext.getUserId() == userId && userId > 0) {
			createdByCurrentUser = true;
		}

		if(userLogin.getEmailAddress().contains("default")) {
			if (email != null && email.length > 0) {
				emailAddress = email[0];
				
				// TODO check createdByCurrentUser by email
				if (Validator.isNotNull(emailAddress) && emailAddress.equals(document.get("email"))) {
					createdByCurrentUser = true;
				}
				
			}
		}
		
		// TODO get profileURL

		commentModel.setClassName(document.get(CommentTerm.CLASS_NAME));
		commentModel.setClassPK(document.get(CommentTerm.CLASS_PK));
		commentModel.setCommentId(GetterUtil.getLong(document.get(CommentTerm.COMMENT_ID)));
		commentModel.setContent(document.get(CommentTerm.CONTENT) + StringPool.SPACE);
		commentModel.setCreateDate(createDate);
		commentModel.setCreatedByCurrentUser(createdByCurrentUser);
		commentModel.setEmail(emailAddress);
		commentModel.setFileName(fileName);
		commentModel.setFileType(fileType);
		commentModel.setFileUrl(fileUrl);
		commentModel.setFullname(fullname);
		commentModel.setIsAdmin(isAdmin);
		commentModel.setIsNew(isNew);
		commentModel.setModifiedDate(modifiedDate);
		commentModel.setParent(GetterUtil.getLong(document.get(CommentTerm.PARENT)));
		commentModel.setPictureUrl(pictureUrl);
		commentModel.setPings(document.get(CommentTerm.PINGS));
		commentModel.setProfileUrl(profileURL);
		commentModel.setUpvoteCount(GetterUtil.getInteger(document.get(CommentTerm.UPVOTE_COUNT)));
		commentModel.setUserHasUpvoted(hasUpvoted);
		commentModel.setUserId(userId);

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
				_log.error(e);
				continue;
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
				_log.error(e);
				continue;
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

	public static Log _log = LogFactoryUtil.getLog(CommentUtils.class);
}
