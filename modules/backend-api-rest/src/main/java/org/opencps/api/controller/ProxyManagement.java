package org.opencps.api.controller;

import com.liferay.portal.kernel.model.Company;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.ServiceContext;

import java.net.HttpURLConnection;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.cxf.jaxrs.ext.multipart.Attachment;
import org.apache.cxf.jaxrs.ext.multipart.Multipart;
import org.opencps.api.faq.model.QuestionDetailModel;
import org.opencps.exception.model.ExceptionModel;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Path("/proxy")
@Api(value = "/proxy", tags = "proxy")
public interface ProxyManagement {
	@POST
	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON, MediaType.APPLICATION_FORM_URLENCODED })
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON, MediaType.APPLICATION_FORM_URLENCODED })
	@ApiOperation(value = "Proxy server", response = QuestionDetailModel.class)
	@ApiResponses(value = {
			@ApiResponse(code = HttpURLConnection.HTTP_OK, message = "Returns a question was created", response = QuestionDetailModel.class),
			@ApiResponse(code = HttpURLConnection.HTTP_FORBIDDEN, message = "Access denied", response = ExceptionModel.class),
			@ApiResponse(code = HttpURLConnection.HTTP_INTERNAL_ERROR, message = "Internal error", response = ExceptionModel.class) })
	public Response proxy(@Context HttpServletRequest request, @Context HttpHeaders header, @Context Company company,
			@Context Locale locale, @Context User user, @Context ServiceContext serviceContext,
			@FormParam("url") String url, @FormParam("method") String method, @FormParam("data") String data,
			@FormParam("serverCode") String serverCode);
	
	@POST
	@Path("/multipart")
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	@Produces({
		MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON
	})
	@ApiOperation(value = "Proxy server", response = QuestionDetailModel.class)
	@ApiResponses(value = {
			@ApiResponse(code = HttpURLConnection.HTTP_OK, message = "Returns a question was created", response = QuestionDetailModel.class),
			@ApiResponse(code = HttpURLConnection.HTTP_FORBIDDEN, message = "Access denied", response = ExceptionModel.class),
			@ApiResponse(code = HttpURLConnection.HTTP_INTERNAL_ERROR, message = "Internal error", response = ExceptionModel.class) })
	public Response proxyMultipart(@Context HttpServletRequest request, @Context HttpHeaders header, @Context Company company,
			@Context Locale locale, @Context User user, @Context ServiceContext serviceContext,
			@Multipart(value="file", required=false) Attachment file,
			@Multipart("url") String url, 
			@Multipart("method") String method, 
			@Multipart("data") String data,
			@Multipart(value="serverCode", required=false) String serverCode);
}
