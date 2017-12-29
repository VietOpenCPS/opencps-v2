package org.opencps.api.controller;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.BeanParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
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

import org.opencps.api.registration.model.RegistrationInputModel;
import org.opencps.api.registrationform.model.RegistrationFormInputModel;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.Company;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.ServiceContext;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiParam;

@Api(value = "/registrations", description = "APIs for Deliverables")
public interface RegistrationManagement {

	@POST
	@Path("/registrations")
	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON, MediaType.APPLICATION_FORM_URLENCODED })
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON, MediaType.APPLICATION_FORM_URLENCODED })
	public Response add(@Context HttpHeaders header, @Context ServiceContext serviceContext,
			@BeanParam RegistrationInputModel input);

	@GET
	@Path("/registrations/{id}")
	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON, MediaType.APPLICATION_FORM_URLENCODED })
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON, MediaType.APPLICATION_FORM_URLENCODED })
	public Response getDetail(@Context HttpServletRequest request, @Context HttpHeaders header,
			@Context Company company, @Context Locale locale, @Context User user,
			@Context ServiceContext serviceContext, @PathParam("id") Long id);

	@PUT
	@Path("/registrations/{id}")
	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON, MediaType.APPLICATION_FORM_URLENCODED })
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON, MediaType.APPLICATION_FORM_URLENCODED })
	public Response update(@Context HttpServletRequest request, @Context HttpHeaders header, @Context Company company,
			@Context Locale locale, @Context User user, @Context ServiceContext serviceContext,
			@BeanParam RegistrationInputModel input, @PathParam("id") long registrationId);

	@DELETE
	@Path("/registrations/{id}")
	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON, MediaType.APPLICATION_FORM_URLENCODED })
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON, MediaType.APPLICATION_FORM_URLENCODED })
	public Response delete(@Context HttpHeaders header,
			@ApiParam(value = "registrationId", required = true) @PathParam("id") long id);

	@GET
	@Path("/registrations/{id}/forms")
	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON, MediaType.APPLICATION_FORM_URLENCODED })
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON, MediaType.APPLICATION_FORM_URLENCODED })
	public Response getFormsbyRegId(@Context HttpHeaders header, @PathParam("id") long id) throws PortalException;

	@POST
	@Path("/registrations/{id}/forms/{formNo}")
	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON, MediaType.APPLICATION_FORM_URLENCODED })
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON, MediaType.APPLICATION_FORM_URLENCODED })
	public Response addRegistrationForm(@Context HttpServletRequest request, @Context HttpHeaders header,
			@Context Company company, @Context Locale locale, @Context User user,
			@Context ServiceContext serviceContext, @BeanParam RegistrationFormInputModel input,
			@ApiParam(value = "registrationId", required = true) @PathParam("id") long registrationId,
			@ApiParam(value = "formNo", required = true) @PathParam("formNo") String formNo);

	@POST
	@Path("/registrations/syncs")
	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON, MediaType.APPLICATION_FORM_URLENCODED })
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON, MediaType.APPLICATION_FORM_URLENCODED })
	public Response registrationSyncs(@Context HttpServletRequest request, @Context HttpHeaders header,
			@Context Company company, @Context Locale locale, @Context User user,
			@Context ServiceContext serviceContext, @BeanParam RegistrationInputModel input,
			@FormParam("submitting") boolean submitting, @FormParam("uuid_") String uuid);
	
	@GET
    @Path("/registrations/{id}/forms/{referenceUid}/preview")
    @Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON, MediaType.APPLICATION_FORM_URLENCODED })
    @Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON, MediaType.APPLICATION_FORM_URLENCODED })
    public Response previewFile(@Context HttpServletRequest request, @Context HttpHeaders header,
            @Context Company company, @Context Locale locale, @Context User user,
            @Context ServiceContext serviceContext,
            @ApiParam(value = "registrationId", required = true) @PathParam("id") long registrationId,
            @ApiParam(value = "referenceUid", required = true) @PathParam("referenceUid") String referenceUid);

}
