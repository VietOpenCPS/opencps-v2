package org.opencps.api.controller;

import com.liferay.portal.kernel.model.Company;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.ServiceContext;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import io.swagger.annotations.Api;

@Path("/votings")
@Api(value = "/votings", tags = "votings")
public interface VotingManagement {
	@GET
	@Path("/checkpermission")
	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public Response checkVotePermission(@Context HttpServletRequest request, @Context HttpHeaders header,
			@Context Company company, @Context Locale locale, @Context User user,
			@Context ServiceContext serviceContext, @QueryParam("applicantIdNo") String applicantIdNo, @QueryParam("dossierNo") String dossierNo);
	@GET
	@Path("/getStatisticDVCQG")
	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public Response getStatisticDVCQG(@Context HttpServletRequest request, @Context HttpHeaders header,
									  @Context Company company, @Context Locale locale, @Context User user,
									  @Context ServiceContext serviceContext);

	@POST
	@Path("/syncStatisticVoteToDVC")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
	public Response syncStatisticVoteToDVC(@Context HttpServletRequest request, @Context HttpHeaders header,
										   @Context Company company, @Context Locale locale, @Context User user,
										   @Context ServiceContext serviceContext, String body);
}
