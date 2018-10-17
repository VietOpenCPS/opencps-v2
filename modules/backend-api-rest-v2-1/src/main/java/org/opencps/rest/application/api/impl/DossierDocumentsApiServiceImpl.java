package org.opencps.rest.application.api.impl;

import org.opencps.rest.application.api.*;
import org.opencps.rest.application.model.DossierDocumentModel;
import org.opencps.rest.application.model.DossierDocumentResultModel;
import java.io.File;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.Map;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import org.apache.cxf.jaxrs.model.wadl.Description;
import org.apache.cxf.jaxrs.model.wadl.DocTarget;

import org.apache.cxf.jaxrs.ext.multipart.*;

import io.swagger.annotations.Api;

public class DossierDocumentsApiServiceImpl implements DossierDocumentsApi {
    public DossierDocumentModel createDossierDoc(String id,  Attachment fileDetail, String referenceUid, String documentType, String documentName, String documentCode) {
        // TODO: Implement...
        
        return null;
    }
    
    public void downloadDocByReferenceUid(String id, String referenceUid) {
        // TODO: Implement...
        
        
    }
    
    public DossierDocumentResultModel getDocumentList(String id, Integer start, Integer end) {
        // TODO: Implement...
        
        return null;
    }
    
}

