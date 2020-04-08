package org.opencps.api.controller;

import com.liferay.portal.kernel.model.Company;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.ServiceContext;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/mail")
public interface MailTestManagement {
	@POST
	@Path("/sendmail")
	@Consumes({ MediaType.APPLICATION_FORM_URLENCODED })
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public Response sendMail(@Context HttpServletRequest request, @Context HttpHeaders header,
			@Context Company company, @Context Locale locale, @Context User user, @Context ServiceContext serviceContext,
			@FormParam("subject") String subject, 
			@FormParam("to") String to, 
			@FormParam("body") String body, 
			@FormParam("from") String from,
			@FormParam("mailSecret") String mailSecret,
			@FormParam("sslPort") String sslPort,
			@FormParam("smtpPort") String smtpPort);

	@POST
	@Path("/javamail")
	@Consumes({ MediaType.APPLICATION_FORM_URLENCODED })
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public Response javaMail(@Context HttpServletRequest request, @Context HttpHeaders header,
			@Context Company company, @Context Locale locale, @Context User user, @Context ServiceContext serviceContext,
			@FormParam("subject") String subject, 
			@FormParam("to") String to, 
			@FormParam("body") String body, 
			@FormParam("from") String from,
			@FormParam("mailSecret") String mailSecret,
			@FormParam("sslPort") String sslPort,
			@FormParam("smtpPort") String smtpPort,
			@FormParam("host") String host);
}
