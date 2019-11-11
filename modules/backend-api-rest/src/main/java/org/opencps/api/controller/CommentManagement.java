
package org.opencps.api.controller;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.BeanParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.cxf.jaxrs.ext.multipart.Attachment;
import org.apache.cxf.jaxrs.ext.multipart.Multipart;
import org.opencps.api.comment.model.CommentInputModel;
import org.opencps.api.comment.model.CommentSearchModel;

import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.ServiceContext;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiParam;

/**
 * @author trungnt
 */
@Path("/comments")
@Api(value = "/comments", tags = "comments")
public interface CommentManagement {

	@GET
	@Path("/{className}/{classPK}")
	@Consumes({ MediaType.APPLICATION_FORM_URLENCODED })
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public Response getCommentList(@Context HttpServletRequest request, @Context HttpHeaders header,
			@Context ServiceContext serviceContext,
			@ApiParam(value = "className for getting comment list") @PathParam("className") String className,
			@ApiParam(value = "classPK for getting comment list") @PathParam("classPK") long classPK,
			@ApiParam(value = "seach model") @BeanParam CommentSearchModel searchModel);

	@GET
	@Path("/{id}/download")
	@Produces(MediaType.APPLICATION_OCTET_STREAM)
	public Response getCommentAttachment(@Context HttpServletRequest request, @Context HttpHeaders header,
			@Context ServiceContext serviceContext, @ApiParam(value = "id of comment") @PathParam("id") long commentId);

	@POST
	@Consumes({ MediaType.APPLICATION_FORM_URLENCODED })
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public Response addComment(@Context HttpServletRequest request, @Context HttpHeaders header,
			@Context ServiceContext serviceContext,
			@ApiParam(value = "json object store CommentInputModel") @BeanParam CommentInputModel commentInputModel);

	@POST
	@Path("/uploads")
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public Response addCommentAttachment(@Multipart("file") Attachment attachment,
			@Context HttpServletRequest request, @Context HttpHeaders header, @Context ServiceContext serviceContext,
			@Multipart("className") String className, @Multipart("classPK") String classPK,
			@Multipart("parent") long parent, @Multipart("fileName") String fileName,
			@Multipart("fileType") String fileType, @Multipart("fileSize") long fileSize,
			@Multipart("pings") String pings, @Multipart("email") String email, @Multipart("fullname") String fullname,
			@Multipart("opinion") Boolean opinion);

	@PUT
	@Path("/{id}")
	@Consumes({ MediaType.APPLICATION_FORM_URLENCODED })
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public Response updateComment(@Context HttpServletRequest request, @Context HttpHeaders header,
			@Context ServiceContext serviceContext, @Context User user,
			@ApiParam(value = "id of comment for delete user from") @PathParam("id") long commentId,
			@ApiParam(value = "json object store CommentInputModel") @BeanParam CommentInputModel commentInputModel);

	@PUT
	@Path("/{id}/upvotes")
	@Consumes({ MediaType.APPLICATION_FORM_URLENCODED })
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public Response updateUpvoteCount(@Context HttpServletRequest request, @Context HttpHeaders header,
			@Context ServiceContext serviceContext, @Context User user,
			@ApiParam(value = "id of comment for delete user from") @PathParam("id") long commentId,
			@ApiParam(value = "json object store CommentInputModel") @BeanParam CommentInputModel commentInputModel);

	@DELETE
	@Path("/{id}")
	@Consumes({ MediaType.APPLICATION_FORM_URLENCODED })
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public Response removeComment(@Context HttpServletRequest request, @Context HttpHeaders header,
			@Context ServiceContext serviceContext,
			@ApiParam(value = "id of comment for delete user from") @PathParam("id") long commentId);

	@DELETE
	@Path("/{id}/upvotes")
	@Consumes({ MediaType.APPLICATION_FORM_URLENCODED })
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public Response removeUpvoteCount(@Context HttpServletRequest request, @Context HttpHeaders header,
			@Context ServiceContext serviceContext, @Context User user,
			@ApiParam(value = "id of comment for delete user from") @PathParam("id") long commentId,
			@ApiParam(value = "json object store CommentInputModel") @BeanParam CommentInputModel commentInputModel);

	@GET
	@Path("/top")
	@Consumes({ MediaType.APPLICATION_FORM_URLENCODED })
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public Response getCommentTop(@Context HttpServletRequest request, @Context HttpHeaders header,
			@Context ServiceContext serviceContext,
			@ApiParam(value = "seach model") @BeanParam CommentSearchModel searchModel);
	
}
