package org.opencps.api.controller;

import com.liferay.portal.kernel.model.Company;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.ServiceContext;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/dvcqgsso")
public interface DVCQGSSOManagement {

	@GET
	@Path("/checkauth")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.TEXT_HTML, MediaType.APPLICATION_JSON })
	public Response checkAuth(@Context HttpServletRequest request, @Context HttpServletResponse response, @Context HttpHeaders header,
			@Context Company company, @Context Locale locale, @Context User user,
			@Context ServiceContext serviceContext, @FormParam("vnconnect") int vnconnect,
			@FormParam("curenturl") String currentURL);
	
	@GET
	@Path("/authurl")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.TEXT_HTML, MediaType.APPLICATION_JSON })
	public Response getAuthURL(@Context HttpServletRequest request, @Context HttpServletResponse response, @Context HttpHeaders header,
			@Context Company company, @Context Locale locale, @Context User user,
			@Context ServiceContext serviceContext, @FormParam("state") String state, @FormParam("redirectURL") String redirectURL);

	@GET
	@Path("/userinfo")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_JSON })
	public Response getUserInfo(@Context HttpServletRequest request, @Context HttpServletResponse response, @Context HttpHeaders header,
			@Context Company company, @Context Locale locale, @Context User user,
			@Context ServiceContext serviceContext, @QueryParam("authToken") String authToken, @QueryParam("state") String state);
	
	@POST
	@Path("/auth")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_JSON })
	public Response doAuth(@Context HttpServletRequest request, @Context HttpServletResponse response, @Context HttpHeaders header,
			@Context Company company, @Context Locale locale, @Context User user,
			@Context ServiceContext serviceContext, String userinfo);
}
