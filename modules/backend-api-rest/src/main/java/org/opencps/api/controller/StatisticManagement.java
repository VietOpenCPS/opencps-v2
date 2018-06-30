package org.opencps.api.controller;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.BeanParam;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.opencps.api.statistic.model.StatisticDossierSearchModel;

import com.liferay.portal.kernel.model.Company;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.ServiceContext;

@Path("/statistics")

public interface StatisticManagement {

//	@GET
//	@Path("/dossiers/todo")
//	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
//	public Response getDossierTodo(@Context HttpServletRequest request, @Context HttpHeaders header,
//			@Context Company company, @Context Locale locale, @Context User user,
//			@Context ServiceContext serviceContext, @BeanParam StatisticDossierSearchModel query);

	@GET
	@Path("/dossiers/todo")
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public Response getDossierTodo(@Context HttpServletRequest request, @Context HttpHeaders header,
			@Context Company company, @Context Locale locale, @Context User user,
			@Context ServiceContext serviceContext, @BeanParam StatisticDossierSearchModel query);

	@GET
	@Path("/dossiers/countTodo")
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public Response getDossierCountTodo(@Context HttpServletRequest request, @Context HttpHeaders header,
			@Context Company company, @Context Locale locale, @Context User user,
			@Context ServiceContext serviceContext, @BeanParam StatisticDossierSearchModel query);
}
