package org.opencps.rest.application.api;

import org.opencps.rest.application.model.DossierDocumentModel;
import org.opencps.rest.application.model.DossierDocumentResultModel;
import java.io.File;

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
public interface DossierDocumentsApi  {

    @POST
    @Path("/dossiers/{id}/documents")
    @Consumes({ "application/json", "application/xml", "multipart/form-data" })
    @Produces({ "application/json", "application/xml" })
    @ApiOperation(value = "create dossier documents of dossierId", tags={ "DossierDocuments",  })
    public DossierDocumentModel createDossierDoc(@PathParam("id") String id,  @Multipart(value = "file" , required = false) Attachment fileDetail, @Multipart(value = "referenceUid", required = false)  String referenceUid, @Multipart(value = "documentType", required = false)  String documentType, @Multipart(value = "documentName", required = false)  String documentName, @Multipart(value = "documentCode", required = false)  String documentCode);

    @GET
    @Path("/dossiers/{id}/documents/{referenceUid}")
    @Consumes({ "applicantion/json", "applicantion/xml" })
    @Produces({ "applicantion/json", "applicantion/xml" })
    @ApiOperation(value = "Download file document by referenceUid", tags={ "DossierDocuments",  })
    public void downloadDocByReferenceUid(@PathParam("id") String id, @PathParam("referenceUid") String referenceUid);

    @GET
    @Path("/dossiers/{id}/documents")
    @Consumes({ "application/json", "application/xml" })
    @Produces({ "application/json", "application/xml" })
    @ApiOperation(value = "Get list documents of dossierId", tags={ "DossierDocuments" })
    public DossierDocumentResultModel getDocumentList(@PathParam("id") String id, @QueryParam("start") Integer start, @QueryParam("end") Integer end);
}

