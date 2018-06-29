package io.swagger.api;

import io.swagger.model.DossierStatisticResultModel;

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
public interface DossierStatisticApi  {

    @GET
    @Path("/statistics/dossiers/todoTest")
    @Consumes({ "applicantion/json", "applicantion/xml" })
    @Produces({ "applicantion/json", "applicantion/xml" })
    @ApiOperation(value = "Get list all DossierStatistic", tags={ "DossierStatistic" })
    public DossierStatisticResultModel getDossierTodoTest();
}

