
package org.opencps.api.controller.impl;

import com.liferay.document.library.kernel.service.DLAppLocalServiceUtil;
import com.liferay.document.library.kernel.service.DLFileEntryLocalServiceUtil;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.search.Hits;
import com.liferay.portal.kernel.search.SearchContext;
import com.liferay.portal.kernel.search.Sort;
import com.liferay.portal.kernel.search.SortFactoryUtil;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.Validator;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.activation.DataHandler;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

import org.apache.cxf.jaxrs.ext.multipart.Attachment;
import org.opencps.api.comment.model.CommentInputModel;
import org.opencps.api.comment.model.CommentListModel;
import org.opencps.api.comment.model.CommentModel;
import org.opencps.api.comment.model.CommentSearchModel;
import org.opencps.api.comment.model.CommentTopList;
import org.opencps.api.constants.ConstantUtils;
import org.opencps.api.controller.CommentManagement;
import org.opencps.api.controller.util.CommentUtils;
import org.opencps.api.controller.util.MessageUtil;
import org.opencps.api.error.model.ErrorMsg;

import backend.auth.api.exception.BusinessExceptionImpl;
import backend.feedback.constants.CommentTerm;
import backend.feedback.model.Comment;
import backend.feedback.service.CommentLocalServiceUtil;

/**
 * @author trungnt
 */
public class CommentManagementImpl implements CommentManagement {

	@Override
	public Response addComment(HttpServletRequest request, HttpHeaders header, ServiceContext serviceContext,
			CommentInputModel commentInputModel) {

		long groupId = GetterUtil.getLong(header.getHeaderString(Field.GROUP_ID));

		long userId = serviceContext.getUserId();

		try {

			Comment comment = CommentLocalServiceUtil.addComment(userId, groupId, commentInputModel.getClassName(),
					commentInputModel.getClassPK(), commentInputModel.getFullname(), commentInputModel.getEmail(),
					commentInputModel.getParent(), commentInputModel.getContent(), 0, null, StringPool.BLANK,
					StringPool.BLANK, 0, commentInputModel.getPings(), commentInputModel.getOpinion(), serviceContext);

			CommentModel commentModel;

			commentModel = CommentUtils.mappingComment(comment, serviceContext);

			return Response.status(HttpURLConnection.HTTP_OK).entity(commentModel).build();

		} catch (Exception e) {
			return BusinessExceptionImpl.processException(e);
		}
	}

	@Override
	public Response addCommentAttachment(Attachment attachment, HttpServletRequest request, HttpHeaders header,
			ServiceContext serviceContext, String className, String classPK, long parent, String fileName,
			String fileType, long fileSize, String pings, String email, String fullname, Boolean opinion) {

		InputStream inputStream = null;

		try {
			DataHandler dataHandler = attachment.getDataHandler();
			List<String> lstSecureFiles = new ArrayList<>();
			lstSecureFiles.add(MessageUtil.getMessage(ConstantUtils.MEDIA_TYPE_TEXT_X_SH));
			lstSecureFiles.add(MessageUtil.getMessage(ConstantUtils.MEDIA_TYPE_APPLICATION_MAC_BINARY));
			lstSecureFiles.add(MessageUtil.getMessage(ConstantUtils.MEDIA_TYPE_X_MSDOWNLOAD));

			long userId = serviceContext.getUserId();

			long groupId = GetterUtil.getLong(header.getHeaderString(Field.GROUP_ID));

			inputStream = dataHandler.getInputStream();
			if (lstSecureFiles.contains(fileType)) {
				return Response.status(HttpURLConnection.HTTP_BAD_METHOD).entity(StringPool.BLANK).build();
			}
			Comment comment = CommentLocalServiceUtil.addComment(userId, groupId, className, classPK, fullname, email,
					parent, StringPool.BLANK, fileSize, inputStream, fileName, fileType, 0, pings, opinion, serviceContext);

			CommentModel commentModel = new CommentModel();

			commentModel = CommentUtils.mappingComment(comment, serviceContext);

			return Response.status(HttpURLConnection.HTTP_OK).entity(commentModel).build();
		} catch (Exception e) {
			return BusinessExceptionImpl.processException(e);
		} finally {
			try {
				if (inputStream != null)
					inputStream.close();
			} catch (IOException e) {
				_log.error(e);
			}
		}
	}

	@Override
	public Response getCommentAttachment(HttpServletRequest request, HttpHeaders header, ServiceContext serviceContext,
			long commentId) {

		try {

			Comment comment = CommentLocalServiceUtil.fetchComment(commentId);

			long fileEntryId = comment.getFileEntryId();

			FileEntry fileEntry;

			File file = null;

			fileEntry = DLAppLocalServiceUtil.getFileEntry(fileEntryId);

			file = DLFileEntryLocalServiceUtil.getFile(fileEntry.getFileEntryId(), fileEntry.getVersion(), true);
			
			if(file != null && fileEntry != null){
				String fileName = fileEntry.getFileName();

				ResponseBuilder responseBuilder = Response.ok((Object) file);
				String returnFileName = String.format(MessageUtil.getMessage(ConstantUtils.ATTACHMENT_FILENAME), fileName);
				
				responseBuilder.header(ConstantUtils.CONTENT_DISPOSITION, returnFileName)
						.header(HttpHeaders.CONTENT_TYPE, fileEntry.getMimeType());

				return responseBuilder.build();
			}else{
				ErrorMsg error = new ErrorMsg();
				error.setMessage(MessageUtil.getMessage(ConstantUtils.API_MESSAGE_FILENOTFOUND));
				error.setCode(HttpURLConnection.HTTP_NOT_FOUND);
				error.setDescription(MessageUtil.getMessage(ConstantUtils.API_MESSAGE_FILENOTFOUND));
				return Response.status(HttpURLConnection.HTTP_NOT_FOUND).entity(error).build();
			}

		} catch (Exception e) {
			return BusinessExceptionImpl.processException(e);
		}

	}

	@Override
	public Response getCommentList(HttpServletRequest request, HttpHeaders header, ServiceContext serviceContext,
			String className, long classPK, CommentSearchModel query) {

		// _Results results = new _Results();

		SearchContext searchContext = new SearchContext();

		searchContext.setCompanyId(serviceContext.getCompanyId());

		Map<String, Object> params = new HashMap<String, Object>();

		Hits hits = null;

		try {

			long groupId = GetterUtil.getLong(header.getHeaderString(Field.GROUP_ID));

			if (query.getEnd() == 0) {

				query.setStart(QueryUtil.ALL_POS);

				query.setEnd(QueryUtil.ALL_POS);
			}

			params.put(ConstantUtils.API_GROUPID_KEY, String.valueOf(groupId));
			params.put(ConstantUtils.API_KEYWORDS_KEY, query.getKeywords());
			params.put(ConstantUtils.API_CLASSNAME_KEY, className);
			params.put(ConstantUtils.API_CLASSPK_KEY, String.valueOf(classPK));
			if (Validator.isNotNull(query.getOpinion())) {
				params.put(CommentTerm.OPINION, query.getOpinion());				
			}
			
			String commentSort = String.format(MessageUtil.getMessage(ConstantUtils.QUERY_SORT), query.getSort());
			
			Sort[] sorts = new Sort[] {
					SortFactoryUtil.create(commentSort, Sort.STRING_TYPE, query.isOrder()) };

			hits = CommentLocalServiceUtil.luceneSearchEngine(params, sorts, query.getStart(), query.getEnd(),
					searchContext);

			// results = (_Results) CommentUtils.mappingCommentList(
			// hits.toList(), serviceContext, header, query);

			CommentListModel results = CommentUtils.mappingCommentList(hits.toList(), serviceContext, header, query);

			return Response.status(HttpURLConnection.HTTP_OK).entity(results).build();
		} catch (Exception e) {
			return BusinessExceptionImpl.processException(e);
		}
	}

	@Override
	public Response removeComment(HttpServletRequest request, HttpHeaders header, ServiceContext serviceContext,
			long commentId) {

		SearchContext searchContext = new SearchContext();

		searchContext.setCompanyId(serviceContext.getCompanyId());

		try {

			CommentLocalServiceUtil.deleteComment(commentId);

			JSONObject result = JSONFactoryUtil.createJSONObject();

			result.put(ConstantUtils.API_JSON_MESSAGE, MessageUtil.getMessage(ConstantUtils.API_MESSAGE_COMMENT_REMOVESUCCESS));

			return Response.status(HttpURLConnection.HTTP_OK).entity(result.toString()).build();
		} catch (Exception e) {
			return BusinessExceptionImpl.processException(e);
		}
	}

	@Override
	public Response removeUpvoteCount(HttpServletRequest request, HttpHeaders header, ServiceContext serviceContext, User user,
			long commentId, CommentInputModel commentInputModel) {

		try {

			String email = commentInputModel.getEmail();
			// Truong hop co userId thi lay email va fullname tu user

			if (!ConstantUtils.API_DEFAULT_EMAIL.equals(user.getEmailAddress())) {
				email = user.getEmailAddress();
			}

			Comment comment = CommentLocalServiceUtil.updateComment(commentId, commentInputModel.getClassName(),
					commentInputModel.getClassPK(), email, -1, serviceContext);

			CommentModel commentModel;

			commentModel = CommentUtils.mappingComment(comment, serviceContext);

			return Response.status(HttpURLConnection.HTTP_OK).entity(commentModel).build();

		} catch (Exception e) {
			return BusinessExceptionImpl.processException(e);
		}
	}

	@Override
	public Response updateComment(HttpServletRequest request, HttpHeaders header, ServiceContext serviceContext, User user,
			long commentId, CommentInputModel commentInputModel) {

		try {
			String email = commentInputModel.getEmail();
			String fullname = commentInputModel.getFullname();
			// Truong hop co userId thi lay email va fullname tu user

			if (!ConstantUtils.API_DEFAULT_EMAIL.equals(user.getEmailAddress())) {
				email = user.getEmailAddress();
			}

			Comment comment = CommentLocalServiceUtil.updateComment(serviceContext.getUserId(), commentId,
					commentInputModel.getClassName(), commentInputModel.getClassPK(), fullname, email,
					commentInputModel.getParent(), commentInputModel.getContent(), commentInputModel.getPings(),
					serviceContext);

			CommentModel commentModel;

			commentModel = CommentUtils.mappingComment(comment, serviceContext);

			return Response.status(HttpURLConnection.HTTP_OK).entity(commentModel).build();

		} catch (Exception e) {
			return BusinessExceptionImpl.processException(e);
		}
	}

	@Override
	public Response updateUpvoteCount(HttpServletRequest request, HttpHeaders header, ServiceContext serviceContext, User user,
			long commentId, CommentInputModel commentInputModel) {

		try {

			String email = commentInputModel.getEmail();

			if (!ConstantUtils.API_DEFAULT_EMAIL.equals(user.getEmailAddress())) {
				email = user.getEmailAddress();
			}

			Comment comment = CommentLocalServiceUtil.updateComment(commentId, commentInputModel.getClassName(),
					commentInputModel.getClassPK(), email, 0, serviceContext);

			CommentModel commentModel;

			commentModel = CommentUtils.mappingComment(comment, serviceContext);

			return Response.status(HttpURLConnection.HTTP_OK).entity(commentModel).build();

		} catch (Exception e) {
			return BusinessExceptionImpl.processException(e);
		}
	}

	private static final Log _log = LogFactoryUtil.getLog(CommentManagementImpl.class);

	@Override
	public Response getCommentTop(HttpServletRequest request, HttpHeaders header, ServiceContext serviceContext,
			CommentSearchModel query) {
		// _Results results = new _Results();

		SearchContext searchContext = new SearchContext();

		searchContext.setCompanyId(serviceContext.getCompanyId());

		try {

			long groupId = GetterUtil.getLong(header.getHeaderString(Field.GROUP_ID));

			if (query.getEnd() == 0) {

				query.setStart(QueryUtil.ALL_POS);

				query.setEnd(QueryUtil.ALL_POS);
			}

			List<Comment> listComments = new ArrayList<>(CommentLocalServiceUtil.findByF_groupId(groupId, query.getStart(), query.getEnd()));
			
			
			Collections.reverse(listComments);
			
			CommentTopList results = CommentUtils.mappingCommentTopList(listComments, serviceContext);

			return Response.status(HttpURLConnection.HTTP_OK).entity(results).build();
			
		} catch (Exception e) {
			return BusinessExceptionImpl.processException(e);
		}
	}

}
