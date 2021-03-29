package org.opencps.api.controller;

import com.liferay.portal.kernel.service.ServiceContext;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiParam;
import org.opencps.api.dossier.model.DossierInputModel;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/mofa2")
@Api(value = "/mofa2" ,tags = "mofa2")
public interface Mofa2Management {
    @GET
    @Path("/createMofa2/{dossierId}")
    @Consumes({ MediaType.APPLICATION_FORM_URLENCODED })
    @Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
    public Response createMofa2(@Context HttpServletRequest request, @Context HttpHeaders header,
                                           @Context ServiceContext serviceContext ,
                                           @PathParam("dossierId") long dossierId);

    @GET
    @Path("/KTHochieu")
    @Consumes({ MediaType.APPLICATION_FORM_URLENCODED })
    @Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
    public Response getKTHoChieu(@Context HttpServletRequest request, @Context HttpHeaders header,
                                @Context ServiceContext serviceContext,
                                @FormParam("ktHoChieu") long ktHoChieu,@FormParam("soHC") String soHC, @FormParam("soPhep") String soPhep);
}
