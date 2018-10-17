package org.opencps.rest.application.api.impl;

import org.opencps.rest.application.api.*;
import org.opencps.rest.application.model.DocumentTypeInputModel;
import org.opencps.rest.application.model.DocumentTypeModel;
import org.opencps.rest.application.model.DocumentTypeResultModel;

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

public class DocumentTypesApiServiceImpl implements DocumentTypesApi {
    public DocumentTypeModel createDocumentType(DocumentTypeInputModel input) {
        // TODO: Implement...
        
        return null;
    }
    
    public DocumentTypeResultModel getAllDocumentTypes(String keyword, Integer start, Integer end) {
        // TODO: Implement...
        
        return null;
    }
    
    public DocumentTypeModel getDocById(String id) {
        // TODO: Implement...
        
        return null;
    }
    
    public DocumentTypeModel removeDocById(String id) {
        // TODO: Implement...
        
        return null;
    }
    
    public DocumentTypeModel updateDocById(String id, DocumentTypeInputModel input) {
        // TODO: Implement...
        
        return null;
    }
    
}

