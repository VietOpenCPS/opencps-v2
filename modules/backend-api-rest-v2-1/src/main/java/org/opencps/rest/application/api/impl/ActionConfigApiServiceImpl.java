package org.opencps.rest.application.api.impl;

import org.opencps.rest.application.api.*;
import org.opencps.rest.application.model.ActionConfigItem;
import org.opencps.rest.application.model.ActionConfigItemResult;

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

public class ActionConfigApiServiceImpl implements ActionConfigApi {
    public ActionConfigItem addActionConfig(ActionConfigItem body) {
        // TODO: Implement...
        
        return null;
    }
    
    public void deleteActionConfig(String id) {
        // TODO: Implement...
        
        
    }
    
    public ActionConfigItem getActionConfigByCode(String id) {
        // TODO: Implement...
        
        return null;
    }
    
    public ActionConfigItemResult getActionConfigsElasticsearch(String q) {
        // TODO: Implement...
        
        return null;
    }
    
    public ActionConfigItem updateActionConfig(String id, ActionConfigItem body) {
        // TODO: Implement...
        
        return null;
    }
    
}

