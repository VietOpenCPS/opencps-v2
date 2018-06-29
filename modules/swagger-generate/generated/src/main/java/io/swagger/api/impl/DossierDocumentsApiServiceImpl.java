package io.swagger.api.impl;

import io.swagger.api.*;
import io.swagger.model.DossierDocumentModel;
import io.swagger.model.DossierDocumentResultModel;
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
    public DossierDocumentModel createDossierDoc(String id,  Attachment upfileDetail, String documentType, String documentName, String documentCode) {
        // TODO: Implement...
        
        return null;
    }
    
    public void downloadDocByTypeCode(String id, String typeCode) {
        // TODO: Implement...
        
        
    }
    
    public DossierDocumentResultModel getDocumentList(String id, Integer start, Integer end) {
        // TODO: Implement...
        
        return null;
    }
    
}

