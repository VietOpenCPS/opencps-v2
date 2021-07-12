package org.opencps.api.controller;


import com.liferay.portal.kernel.model.Company;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.ServiceContext;
import io.swagger.annotations.Api;
import org.apache.cxf.jaxrs.ext.multipart.Multipart;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.File;
import java.util.List;
import java.util.Locale;

@Path("/qlvb")
@Api(value = "/qlvb", tags = "qlvb")
public interface QLVGManagement {
    @GET
    @Path("/{dossierId}/sendDocuments/{userId}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response sendProfile(@PathParam("dossierId") long dossierId, @PathParam("userId") long userId);

    @PUT
    @Path("")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces({
            MediaType.APPLICATION_JSON
    })
    public Response updateProfile(@Context HttpServletRequest request, @Context HttpHeaders header,
                                  @Context Company company, @Context Locale locale, @Context User user,
                                  @Context ServiceContext serviceContext, @Multipart("id") String id,
                                  @Multipart("file") File file,
                                  @Multipart("displayName") String displayName,
                                  @Multipart("fileType") String fileType,
                                  @Multipart("actionCode") String actionCode,
                                  @Multipart("sequence") int sequence);

    @GET
    @Path("/{dossierId}/qlvbcts")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response testReceiveDossierCTS(@PathParam("dossierId") long dossierId);

    @POST
    @Path("/{dossierId}/qlvbcts")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response testSendDossierCTS(@PathParam("dossierId") long dossierId);
}
