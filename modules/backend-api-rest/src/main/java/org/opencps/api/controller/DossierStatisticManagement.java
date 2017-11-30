package org.opencps.api.controller;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import io.swagger.annotations.Api;

@Path("/dossierstatistic")
@Api(value = "/dossierstatistic", tags = "dossierstatistic")
public interface DossierStatisticManagement {

	@GET
	@Path("/statistics/dossiers")
	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON ,MediaType.MULTIPART_FORM_DATA})
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public Response getDossierStatistic();
	
	@GET
	@Path("/statistics/actions/year/{y}")
	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON ,MediaType.MULTIPART_FORM_DATA})
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public Response getYears(@PathParam("y") long year);
	
	@POST
	@Path("/statistics/dossiers/agency/{code}")
	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON ,MediaType.MULTIPART_FORM_DATA})
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public Response postAgency(@PathParam("code") long agencyCd);
}
