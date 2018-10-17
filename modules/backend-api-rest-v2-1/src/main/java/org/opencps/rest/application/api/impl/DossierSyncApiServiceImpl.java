package org.opencps.rest.application.api.impl;

import org.opencps.rest.application.api.*;
import org.opencps.rest.application.model.DossierSyncResultModel;

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

public class DossierSyncApiServiceImpl implements DossierSyncApi {
    public DossierSyncResultModel getDossierSyncList(String action, String top, Integer start, Integer end) {
        // TODO: Implement...
        
        return null;
    }
    
    public DossierSyncResultModel getSyncByDossierId(Integer info, String id, Integer start, Integer end) {
        // TODO: Implement...
        
        return null;
    }
    
}

