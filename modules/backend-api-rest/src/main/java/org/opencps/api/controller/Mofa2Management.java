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

@Path("dossiers")
@Api(value = "dossiers" ,tags = "dossiers")
public interface Mofa2Management {
    @POST
    @Path("/mofa2")
    @Consumes({ MediaType.APPLICATION_FORM_URLENCODED })
    @Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
    public Response createMofa2(@Context HttpServletRequest request, @Context HttpHeaders header,
                                           @Context ServiceContext serviceContext, @BeanParam DossierInputModel dossierInputModel ,
                                           @FormParam("dossierId") long dossierId, @FormParam("serverCode") String serverCode);
}
