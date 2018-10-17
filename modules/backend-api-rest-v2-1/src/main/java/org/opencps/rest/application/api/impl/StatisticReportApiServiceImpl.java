package org.opencps.rest.application.api.impl;

import org.opencps.rest.application.api.*;

import com.liferay.portal.kernel.model.Company;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.ServiceContext;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import javax.ws.rs.*;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import org.apache.cxf.jaxrs.model.wadl.Description;
import org.apache.cxf.jaxrs.model.wadl.DocTarget;

import org.apache.cxf.jaxrs.ext.multipart.*;

import io.swagger.annotations.Api;

public class StatisticReportApiServiceImpl implements StatisticReportApi {
    public Object statisticReportPrint(String code, String body) {
        // TODO: Implement...
        
        return null;
    }

	@Override
	public Object statisticReportPrint(User user, Company company, Locale locale, HttpHeaders httpHeaders,
			ServiceContext serviceContext, String code, String body) {
		// TODO Auto-generated method stub
		return null;
	}
    
}

