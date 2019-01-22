package org.opencps.api.controller;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.BeanParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.opencps.api.notification.model.NotificationSearchModel;
import com.liferay.portal.kernel.model.Company;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.ServiceContext;

@Path("/notifications")
public interface NotificationManagement {

	@GET
	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON, MediaType.APPLICATION_FORM_URLENCODED })
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public Response getNotificationList(@Context HttpServletRequest request, @Context HttpHeaders header,
			@Context Company company, @Context Locale locale, @Context User user,
			@Context ServiceContext serviceContext, @BeanParam NotificationSearchModel query, @QueryParam("archived") Boolean archived);

	@GET
	@Path("/count")
	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON, MediaType.APPLICATION_FORM_URLENCODED })
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public Response countTotalNotifications(@Context HttpServletRequest request, @Context HttpHeaders header,
			@Context Company company, @Context Locale locale, @Context User user,
			@Context ServiceContext serviceContext, @BeanParam NotificationSearchModel query, @QueryParam("archived") Boolean archived);

	@POST
	@Path("/{eventId}/mark")
	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON, MediaType.APPLICATION_FORM_URLENCODED })
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public Response markAsRead(@Context HttpServletRequest request, @Context HttpHeaders header,
			@Context Company company, @Context Locale locale, @Context User user,
			@Context ServiceContext serviceContext, @PathParam("eventId") long eventId);
	
//	@GET
//	@Path("/{type}")
//	@Consumes({ MediaType.APPLICATION_FORM_URLENCODED })
//	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
//	public Response read(@Context HttpServletRequest request, @Context HttpHeaders header, @Context Company company,
//			@Context Locale locale, @Context User user, @Context ServiceContext serviceContext,
//			@DefaultValue("0") @PathParam("type") String type);
//
//	@PUT
//	@Path("/{type}")
//	@Consumes({ MediaType.APPLICATION_FORM_URLENCODED })
//	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
//	public Response update(@Context HttpServletRequest request, @Context HttpHeaders header, @Context Company company,
//			@Context Locale locale, @Context User user, @Context ServiceContext serviceContext,
//			@DefaultValue("0") @PathParam("type") String type, @BeanParam NotificationtemplateInputModel input);
//
	@DELETE
	@Path("/{id}")
	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON, MediaType.APPLICATION_FORM_URLENCODED })
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public Response deleteNotification(@Context HttpServletRequest request, @Context HttpHeaders header, @Context Company company,
			@Context Locale locale, @Context User user, @Context ServiceContext serviceContext,
			@PathParam("id") String id);
//
//	@POST
//	@Consumes({ MediaType.APPLICATION_FORM_URLENCODED })
//	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
//	public Response create(@Context HttpServletRequest request, @Context HttpHeaders header, @Context Company company,
//			@Context Locale locale, @Context User user, @Context ServiceContext serviceContext,
//			@BeanParam NotificationtemplateInputModel input);

}
