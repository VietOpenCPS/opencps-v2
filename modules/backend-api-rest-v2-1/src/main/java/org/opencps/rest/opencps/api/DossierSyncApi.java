package org.opencps.rest.opencps.api;

import org.opencps.rest.opencps.model.DossierSyncResultModel;

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

import java.util.Locale; 
import javax.ws.rs.core.Context; 
import javax.ws.rs.core.HttpHeaders; 
import com.liferay.portal.kernel.model.Company; 
import com.liferay.portal.kernel.model.User; 
import com.liferay.portal.kernel.service.ServiceContext; 
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
    public DossierSyncResultModel getSyncByDossierId(@QueryParam("info") @NotNull Integer info, @PathParam("id") String id, @QueryParam("start") Integer start, @QueryParam("end") Integer end);
}

