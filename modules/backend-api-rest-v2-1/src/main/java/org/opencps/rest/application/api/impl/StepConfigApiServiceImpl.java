package org.opencps.rest.application.api.impl;

import org.opencps.rest.application.api.*;
import org.opencps.rest.application.model.StepConfigItem;
import org.opencps.rest.application.model.StepConfigItemResult;

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

public class StepConfigApiServiceImpl implements StepConfigApi {
    public StepConfigItem addStepConfig(StepConfigItem body) {
        // TODO: Implement...
        
        return null;
    }
    
    public void deleteStepConfig(String id) {
        // TODO: Implement...
        
        
    }
    
    public StepConfigItem getStepConfigByCode(String id) {
        // TODO: Implement...
        
        return null;
    }
    
    public StepConfigItemResult getStepConfigByMainStatusAndSubStatus(String mainStatus, String subStatus) {
        // TODO: Implement...
        
        return null;
    }
    
    public StepConfigItemResult getStepConfigsElasticsearch(String q) {
        // TODO: Implement...
        
        return null;
    }
    
    public StepConfigItem updateStepConfig(String id, StepConfigItem body) {
        // TODO: Implement...
        
        return null;
    }
    
}

