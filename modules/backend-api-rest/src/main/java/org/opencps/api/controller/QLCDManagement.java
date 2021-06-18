package org.opencps.api.controller;

import io.swagger.annotations.Api;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/qldc")
@Api(value = "/qldc", tags = "qldc")
public interface QLCDManagement {
    @POST
    @Path("")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces({MediaType.TEXT_PLAIN, MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response forwardApi(String body);
}
