package io.swagger.api;

import io.swagger.model.DossierSyncResultModel;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.Map;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.MediaType;
import org.apache.cxf.jaxrs.ext.multipart.*;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.jaxrs.PATCH;
import javax.validation.constraints.*;

@Path("/")
@Api(value = "/", description = "")
public interface DossierSyncApi  {

    @GET
    @Path("/dossiersyncs")
    @Consumes({ "applicantion/json", "applicantion/xml" })
    @Produces({ "applicantion/json", "applicantion/xml" })
    @ApiOperation(value = "Get list all dossierSync", tags={ "DossierSync",  })
    public DossierSyncResultModel getDossierSyncList(@QueryParam("action") String action, @QueryParam("top") String top, @QueryParam("start") Integer start, @QueryParam("end") Integer end);

    @GET
    @Path("/dossiers/{id}/syncs")
    @Consumes({ "applicantion/json", "applicantion/xml" })
    @Produces({ "applicantion/json", "applicantion/xml" })
    @ApiOperation(value = "Read info exchange on dossier", tags={ "DossierSync" })
    public DossierSyncResultModel getSyncByDossierId(@QueryParam("model") @NotNull Integer model, @PathParam("id") String id, @QueryParam("start") Integer start, @QueryParam("end") Integer end);
}

