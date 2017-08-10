package org.opencps.api.controller;

import java.net.HttpURLConnection;
import java.util.List;

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
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.cxf.headers.Header;
import org.apache.cxf.jaxrs.ext.multipart.Attachment;
import org.opencps.exception.model.ExceptionModel;
import org.opencps.serviceinfo.model.FileTempateModel;
import org.opencps.serviceinfo.model.FileTempateResultsModel;
import org.opencps.serviceinfo.model.ServiceInfoDetailModel;
import org.opencps.serviceinfo.model.ServiceInfoInputModel;
import org.opencps.serviceinfo.model.ServiceInfoResultsModel;
import org.opencps.serviceinfo.model.ServiceInfoSearchModel;

import com.liferay.portal.kernel.service.ServiceContext;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Path("/serviceinfos")
@Api(value="/serviceinfos", description="APIs for ServiceInfo")
public interface ServiceInfo {

	@GET
	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	@ApiOperation(value = "Get ServiceInfo", response = ServiceInfoResultsModel.class)
	@ApiResponses (value = {
	        @ApiResponse(code = HttpURLConnection.HTTP_OK, message = "Returns a list of ServiceInfo", response = ServiceInfoResultsModel.class),
	        @ApiResponse(code = HttpURLConnection.HTTP_UNAUTHORIZED, message = "Unauthorized", response = ExceptionModel.class),
	        @ApiResponse(code = HttpURLConnection.HTTP_NOT_FOUND, message = "Not found", response = ExceptionModel.class),
	        @ApiResponse(code = HttpURLConnection.HTTP_FORBIDDEN, message = "Access denied", response = ExceptionModel.class)
	})
	public Response getServiceInfos(@Context HttpServletRequest request, @Context Header header, @Context ServiceContext serviceContext, 
			@ApiParam(value = "query params for search") @BeanParam ServiceInfoSearchModel search);

	@POST
	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	@ApiOperation(value = "Add ServiceInfo", response = ServiceInfoInputModel.class)
	@ApiResponses (value = {
	        @ApiResponse(code = HttpURLConnection.HTTP_OK, message = "Returns a ServiceInfo entity was added", response = ServiceInfoInputModel.class),
	        @ApiResponse(code = HttpURLConnection.HTTP_UNAUTHORIZED, message = "Unauthorized", response = ExceptionModel.class),
	        @ApiResponse(code = HttpURLConnection.HTTP_NOT_FOUND, message = "Not found", response = ExceptionModel.class),
	        @ApiResponse(code = HttpURLConnection.HTTP_FORBIDDEN, message = "Access denied", response = ExceptionModel.class)
	})
	public Response addServiceInfo(@Context HttpServletRequest request, @Context Header header, @Context ServiceContext serviceContext,
			@ApiParam(value = "body input for update") @BeanParam ServiceInfoInputModel input);
	
	
	@GET
	@Path("/{id}")
	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	@ApiOperation(value = "Get detail ServiceInfo by code (or Id)", response = ServiceInfoDetailModel.class)
	@ApiResponses (value = {
	        @ApiResponse(code = HttpURLConnection.HTTP_OK, message = "Returns the detail of ServiceInfo", response = ServiceInfoDetailModel.class),
	        @ApiResponse(code = HttpURLConnection.HTTP_UNAUTHORIZED, message = "Unauthorized", response = ExceptionModel.class),
	        @ApiResponse(code = HttpURLConnection.HTTP_NOT_FOUND, message = "Not found", response = ExceptionModel.class),
	        @ApiResponse(code = HttpURLConnection.HTTP_FORBIDDEN, message = "Access denied", response = ExceptionModel.class)
	})
	public Response getDetailServiceInfo(@Context HttpServletRequest request, @Context Header header, @Context ServiceContext serviceContext, 
			@ApiParam(value = "id (or code) of ServiceInfo that need to be get detail", required = true) @PathParam("id") String id);

	@PUT
	@Path("/{id}")
	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	@ApiOperation(value = "Update ServiceInfo", response = ServiceInfoDetailModel.class)
	@ApiResponses (value = {
	        @ApiResponse(code = HttpURLConnection.HTTP_OK, message = "Returns a ServiceInfo entity was updated", response = ServiceInfoDetailModel.class),
	        @ApiResponse(code = HttpURLConnection.HTTP_UNAUTHORIZED, message = "Unauthorized", response = ExceptionModel.class),
	        @ApiResponse(code = HttpURLConnection.HTTP_NOT_FOUND, message = "Not found", response = ExceptionModel.class),
	        @ApiResponse(code = HttpURLConnection.HTTP_FORBIDDEN, message = "Access denied", response = ExceptionModel.class)
	})
	public Response updateServiceInfo(@Context HttpServletRequest request, @Context Header header, @Context ServiceContext serviceContext,
			@ApiParam(value = "id of ServiceInfo that need to be update") @PathParam("id") long id, 
			@ApiParam(value = "body input for update") @BeanParam ServiceInfoInputModel input);

	@DELETE
	@Path("/{id}")
	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	@ApiOperation(value = "Get detail ServiceInfo by code (or Id)")
	@ApiResponses (value = {
	        @ApiResponse(code = HttpURLConnection.HTTP_OK, message = "Returns the detail of ServiceInfo"),
	        @ApiResponse(code = HttpURLConnection.HTTP_UNAUTHORIZED, message = "Unauthorized", response = ExceptionModel.class),
	        @ApiResponse(code = HttpURLConnection.HTTP_NOT_FOUND, message = "Not found", response = ExceptionModel.class),
	        @ApiResponse(code = HttpURLConnection.HTTP_FORBIDDEN, message = "Access denied", response = ExceptionModel.class)
	})
	public Response deleteServiceInfo(@Context HttpServletRequest request, @Context Header header, @Context ServiceContext serviceContext, 
			@ApiParam(value = "id (or code) of ServiceInfo that need to be delete", required = true) @PathParam("id") String id);

	@GET
	@Path("/{id}/filetemplates")
	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	@ApiOperation(value = "Get FileTemplate list of ServiceInfo by its id)", response = FileTempateResultsModel.class)
	@ApiResponses (value = {
	        @ApiResponse(code = HttpURLConnection.HTTP_OK, message = "Returns the list of FileTemplate of ServiceInfo", response = FileTempateResultsModel.class),
	        @ApiResponse(code = HttpURLConnection.HTTP_UNAUTHORIZED, message = "Unauthorized", response = ExceptionModel.class),
	        @ApiResponse(code = HttpURLConnection.HTTP_NOT_FOUND, message = "Not found", response = ExceptionModel.class),
	        @ApiResponse(code = HttpURLConnection.HTTP_FORBIDDEN, message = "Access denied", response = ExceptionModel.class)
	})
	public Response getFileTemplatesOfServiceInfo(@Context HttpServletRequest request, @Context Header header, @Context ServiceContext serviceContext, 
			@ApiParam(value = "id of ServiceInfo that need to be get the list of FileTemplates", required = true) @PathParam("id") String id);
	
	
	@POST
	@Path("/{id}/filetemplates")
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	@ApiOperation(value = "Add FileTempate to ServiceInfo)", response = FileTempateModel.class)
	@ApiResponses (value = {
	        @ApiResponse(code = HttpURLConnection.HTTP_OK, message = "Returns the FileTemplate was updated", response = FileTempateModel.class),
	        @ApiResponse(code = HttpURLConnection.HTTP_UNAUTHORIZED, message = "Unauthorized", response = ExceptionModel.class),
	        @ApiResponse(code = HttpURLConnection.HTTP_NOT_FOUND, message = "Not found", response = ExceptionModel.class),
	        @ApiResponse(code = HttpURLConnection.HTTP_FORBIDDEN, message = "Access denied", response = ExceptionModel.class)
	})
	public Response addFileTemplateToServiceInfo(@Context HttpServletRequest request, @Context Header header, @Context ServiceContext serviceContext,
			@ApiParam(value = "Attachment files", required = true) List<Attachment> attachments,
			@ApiParam(value = "id of ServiceInfo that need to be upload file to", required = true) @PathParam("id") String id,
			@ApiParam(value = "Metadata of FileTemplate", required = true) @BeanParam FileTempateModel fileTemplateModel);


	@GET
	@Path("/{id}/filetemplates/{templateNo}")
	@Produces(MediaType.APPLICATION_OCTET_STREAM)
	@ApiOperation(value = "Download FileTemplate by it templateNo)")
	@ApiResponses (value = {
	        @ApiResponse(code = HttpURLConnection.HTTP_OK, message = "Returns octet content of FileTemplate"),
	        @ApiResponse(code = HttpURLConnection.HTTP_UNAUTHORIZED, message = "Unauthorized", response = ExceptionModel.class),
	        @ApiResponse(code = HttpURLConnection.HTTP_NOT_FOUND, message = "Not found", response = ExceptionModel.class),
	        @ApiResponse(code = HttpURLConnection.HTTP_FORBIDDEN, message = "Access denied", response = ExceptionModel.class)
	})
	public Response downloadFileTemplateOfServiceInfo(@Context HttpServletRequest request, @Context Header header, @Context ServiceContext serviceContext,
			@ApiParam(value = "id of ServiceInfo that need to be get file content", required = true) @PathParam("id") String id,
			@ApiParam(value = "templateNo of FileTemplate that need to be get file content", required = true) @PathParam("templateNo") String templateNo);
	
	@DELETE
	@Path("/{id}/filetemplates/{templateNo}")
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	@ApiOperation(value = "Delete FileTemplate of ServiceInfo)", response = FileTempateModel.class)
	@ApiResponses (value = {
	        @ApiResponse(code = HttpURLConnection.HTTP_OK, message = "Returns FileTemplate was deleted", response = FileTempateModel.class),
	        @ApiResponse(code = HttpURLConnection.HTTP_UNAUTHORIZED, message = "Unauthorized", response = ExceptionModel.class),
	        @ApiResponse(code = HttpURLConnection.HTTP_NOT_FOUND, message = "Not found", response = ExceptionModel.class),
	        @ApiResponse(code = HttpURLConnection.HTTP_FORBIDDEN, message = "Access denied", response = ExceptionModel.class)
	})
	public Response deleteFileTemplateOfServiceInfo(@Context HttpServletRequest request, @Context Header header, @Context ServiceContext serviceContext,
			@ApiParam(value = "id of ServiceInfo that need to be delete", required = true) @PathParam("id") String id,
			@ApiParam(value = "templateNo of FileTemplate that need to be delete", required = true) @PathParam("templateNo") String templateNo);
	

}
