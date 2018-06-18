package io.swagger.api.impl;

import io.swagger.api.*;
import io.swagger.model.ActionConfigItem;

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
    
    public void deleteActionConfig(String actionCode) {
        // TODO: Implement...
        
        
    }
    
    public ActionConfigItem getActionConfigByCode(String actionCode) {
        // TODO: Implement...
        
        return null;
    }
    
    public List<ActionConfigItem> getActionConfigs(String q) {
        // TODO: Implement...
        
        return null;
    }
    
    public ActionConfigItem updateActionConfig(String actionCode, ActionConfigItem body) {
        // TODO: Implement...
        
        return null;
    }
    
}

