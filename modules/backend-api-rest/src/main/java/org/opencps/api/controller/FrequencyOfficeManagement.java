package org.opencps.api.controller;

import io.swagger.annotations.Api;
import org.opencps.dossiermgt.input.model.DtoFrequencyOffice;
import org.opencps.dossiermgt.input.model.FakeModel;
import org.opencps.dossiermgt.input.model.ProfileInModel;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/hshc")
@Api(value = "/hshc", tags = "hshc")
public interface FrequencyOfficeManagement {
    @POST
    @Path("/sendProfile")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response sendProfile();

    @GET
    @Path("/dossiers")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response getListDossierFake();

    @GET
    @Path("/dossier")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response getDetailDossierFake();

    @POST
    @Path("/synDossierFake")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response synDossierFake(Object profile);


}
