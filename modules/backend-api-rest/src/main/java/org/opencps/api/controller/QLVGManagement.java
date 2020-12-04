package org.opencps.api.controller;


import io.swagger.annotations.Api;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/qlvb")
@Api(value = "/qlvb", tags = "qlvb")
public interface QLVGManagement {
    @GET
    @Path("/{dossierId}/sendDocuments")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response sendProfile(@PathParam("dossierId") long dossierId);

}
