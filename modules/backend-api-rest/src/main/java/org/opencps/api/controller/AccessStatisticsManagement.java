package org.opencps.api.controller;

import com.liferay.portal.kernel.model.Company;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.ServiceContext;
import org.opencps.api.accessstatistics.model.AccessStatistics;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Locale;

@Path("/accessStatistics/")
public interface AccessStatisticsManagement
{
	@GET
	@Path("/")
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	Response getAccessStatistics(@Context HttpServletRequest request, @Context HttpHeaders header,@Context
		Company company, @Context Locale locale, @Context User user,@Context ServiceContext serviceContext, @BeanParam
		AccessStatistics accessStatistics);

	@GET
	@Path("/day")
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	Response getAccessStatisticsForDay(@Context HttpServletRequest request, @Context HttpHeaders header,@Context
		Company company, @Context Locale locale, @Context User user,@Context ServiceContext serviceContext, @BeanParam
		AccessStatistics accessStatistics);

	@GET
	@Path("/period")
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	Response getAccessStatisticsForPeriod(@Context HttpServletRequest request, @Context HttpHeaders header,@Context
		Company company, @Context Locale locale, @Context User user,@Context ServiceContext serviceContext,@QueryParam(value = "startDay") String startDay,@QueryParam(value = "endDay") String endDay );

	@GET
	@Path("/month")
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	Response getAccessStatisticsForMonth(@Context HttpServletRequest request, @Context HttpHeaders header,@Context
		Company company, @Context Locale locale, @Context User user,@Context ServiceContext serviceContext, @BeanParam
		AccessStatistics accessStatistics);

	@GET
	@Path("/year")
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	Response getAccessStatisticsForYear(@Context HttpServletRequest request, @Context HttpHeaders header,@Context
		Company company, @Context Locale locale, @Context User user,@Context ServiceContext serviceContext, @BeanParam
		AccessStatistics accessStatistics);

	@GET
	@Path("/allyear")
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	Response getAccessStatisticsForAllYear(@Context HttpServletRequest request, @Context HttpHeaders header,@Context
		Company company, @Context Locale locale, @Context User user,@Context ServiceContext serviceContext, @BeanParam
		AccessStatistics accessStatistics);

	@GET
	@Path("/url/day")
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	Response getAccessStatisticsURLForDay(@Context HttpServletRequest request, @Context HttpHeaders header,@Context
		Company company, @Context Locale locale, @Context User user,@Context ServiceContext serviceContext, @BeanParam
		AccessStatistics accessStatistics);

	@GET
	@Path("/url/period")
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	Response getAccessStatisticsURLForPeriod(@Context HttpServletRequest request, @Context HttpHeaders header,@Context
		Company company, @Context Locale locale, @Context User user,@Context ServiceContext serviceContext,@QueryParam(value = "startDay") String startDay,@QueryParam(value = "endDay") String endDay );

	@GET
	@Path("/url/month")
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	Response getAccessStatisticsURLForMonth(@Context HttpServletRequest request, @Context HttpHeaders header,@Context
		Company company, @Context Locale locale, @Context User user,@Context ServiceContext serviceContext, @BeanParam
		AccessStatistics accessStatistics);

	@GET
	@Path("/url/year")
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	Response getAccessStatisticsURLForYear(@Context HttpServletRequest request, @Context HttpHeaders header,@Context
		Company company, @Context Locale locale, @Context User user,@Context ServiceContext serviceContext, @BeanParam
		AccessStatistics accessStatistics);

	@GET
	@Path("/url/allyear")
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	Response getAccessStatisticsURLForAllYear(@Context HttpServletRequest request, @Context HttpHeaders header,@Context
		Company company, @Context Locale locale, @Context User user,@Context ServiceContext serviceContext, @BeanParam
		AccessStatistics accessStatistics);

	@GET
	@Path("/online")
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	Response getOnline(@Context HttpServletRequest request, @Context HttpHeaders header,@Context
		Company company, @Context Locale locale, @Context User user,@Context ServiceContext serviceContext);



}
