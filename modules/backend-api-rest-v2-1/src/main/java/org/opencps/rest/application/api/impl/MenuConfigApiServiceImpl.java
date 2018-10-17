package org.opencps.rest.application.api.impl;

import org.opencps.rest.application.api.*;
import org.opencps.rest.application.model.MenuConfigCountItemResults;
import org.opencps.rest.application.model.MenuConfigItem;
import org.opencps.rest.application.model.MenuConfigItemResult;

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

public class MenuConfigApiServiceImpl implements MenuConfigApi {
    public MenuConfigItem addMenuConfig(MenuConfigItem body) {
        // TODO: Implement...
        
        return null;
    }
    
    public void deleteMenuConfig(String id) {
        // TODO: Implement...
        
        
    }
    
    public MenuConfigItem getMenuConfigByCode(String id) {
        // TODO: Implement...
        
        return null;
    }
    
    public MenuConfigCountItemResults getMenuConfigsCount(String q) {
        // TODO: Implement...
        
        return null;
    }
    
    public MenuConfigItemResult getMenuConfigsElasticsearch(String q) {
        // TODO: Implement...
        
        return null;
    }
    
    public MenuConfigItemResult getMenuConfigsTodo(String q) {
        // TODO: Implement...
        
        return null;
    }
    
    public MenuConfigItem updateMenuConfig(String id, MenuConfigItem body) {
        // TODO: Implement...
        
        return null;
    }
    
}

