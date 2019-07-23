package org.opencps.rest.opencps.api;

import org.opencps.rest.opencps.model.DocumentTypeInputModel;
import org.opencps.rest.opencps.model.DocumentTypeModel;
import org.opencps.rest.opencps.model.DocumentTypeResultModel;

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
public interface DocumentTypesApi  {

    @POST
    @Path("/documenttypes")
    @Consumes({ "application/json", "application/xml", "application/x-www-form-urlencoded" })
    @Produces({ "application/json", "application/xml", "application/x-www-form-urlencoded" })
    @ApiOperation(value = "Create DocumentType", tags={ "DocumentTypes",  })
    public DocumentTypeModel createDocumentType(DocumentTypeInputModel input);

    @GET
    @Path("/documenttypes")
    @Consumes({ "applicantion/json", "applicantion/xml" })
    @Produces({ "application/json", "application/xml" })
    @ApiOperation(value = "Get all list DocumentTypes", tags={ "DocumentTypes",  })
    public DocumentTypeResultModel getAllDocumentTypes(@QueryParam("keyword") String keyword, @QueryParam("start") Integer start, @QueryParam("end") Integer end);

    @GET
    @Path("/documenttypes/{id}")
    @Consumes({ "applicantion/json", "applicantion/xml" })
    @Produces({ "applicantion/json", "applicantion/xml" })
    @ApiOperation(value = "Get DocumentType by id or code", tags={ "DocumentTypes",  })
    public DocumentTypeModel getDocById(@PathParam("id") String id);

    @DELETE
    @Path("/documenttypes/{id}")
    @Consumes({ "applicantion/json", "applicantion/xml" })
    @Produces({ "applicantion/json", "applicantion/xml" })
    @ApiOperation(value = "Delete DocumentType by id", tags={ "DocumentTypes",  })
    public DocumentTypeModel removeDocById(@PathParam("id") String id);

    @PUT
    @Path("/documenttypes/{id}")
    @Consumes({ "applicantion/json", "applicantion/xml", "application/x-www-form-urlencoded" })
    @Produces({ "applicantion/json", "applicantion/xml", "application/x-www-form-urlencoded" })
    @ApiOperation(value = "Update DocumentType by id", tags={ "DocumentTypes" })
    public DocumentTypeModel updateDocById(@PathParam("id") String id, DocumentTypeInputModel input);
}

