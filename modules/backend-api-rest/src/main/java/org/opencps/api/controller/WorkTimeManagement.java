package org.opencps.api.controller;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.BeanParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.DefaultValue;
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

import org.opencps.api.worktime.model.DataSearchModel;
import org.opencps.api.worktime.model.WorkTimeInputModel;

import com.liferay.portal.kernel.model.Company;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.ServiceContext;

@Path("/worktimes")
public interface WorkTimeManagement {

	@GET
	@Consumes({ MediaType.APPLICATION_FORM_URLENCODED })
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public Response getWorktimes(@Context HttpServletRequest request, @Context HttpHeaders header,
			@Context Company company, @Context Locale locale, @Context User user, @Context ServiceContext serviceContext,
			@BeanParam DataSearchModel query);

	@GET
	@Path("/{n}")
	@Consumes({ MediaType.APPLICATION_FORM_URLENCODED })
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public Response read(@Context HttpServletRequest request, @Context HttpHeaders header,
			@Context Company company, @Context Locale locale, @Context User user, @Context ServiceContext serviceContext,
			@DefaultValue("0") @PathParam("n") int n);

	@POST
	@Consumes({ MediaType.APPLICATION_FORM_URLENCODED })
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public Response create(@Context HttpServletRequest request, @Context HttpHeaders header,
			@Context Company company, @Context Locale locale, @Context User user, @Context ServiceContext serviceContext,
			@BeanParam WorkTimeInputModel input);

	@PUT
	@Path("/{n}")
	@Consumes({ MediaType.APPLICATION_FORM_URLENCODED })
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public Response update(@Context HttpServletRequest request, @Context HttpHeaders header,
			@Context Company company, @Context Locale locale, @Context User user, @Context ServiceContext serviceContext,
			@DefaultValue("0") @PathParam("n") int n, @BeanParam WorkTimeInputModel input);

	@DELETE
	@Path("/{n}")
	@Consumes({ MediaType.APPLICATION_FORM_URLENCODED })
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public Response delete(@Context HttpServletRequest request, @Context HttpHeaders header,
			@Context Company company, @Context Locale locale, @Context User user, @Context ServiceContext serviceContext,
			@DefaultValue("0") @PathParam("n") int n);
}
