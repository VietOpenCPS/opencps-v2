package org.opencps.api.controller;

import com.liferay.portal.kernel.model.Company;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.ServiceContext;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/nps")
public interface DVCQGIManagement {
	
	@GET
	@Path("/serviceinfodvcqg")
	@Consumes({ MediaType.APPLICATION_FORM_URLENCODED })
	@Produces({ MediaType.APPLICATION_JSON })
	public Response getServiceInfoDVCQG(@Context HttpServletRequest request, @Context HttpServletResponse response,
			@Context HttpHeaders header, @Context Company company, @Context Locale locale, @Context User user,
			@Context ServiceContext serviceContext, @FormParam("serviceCode") String serviceCode,
			@FormParam("serviceName") String serviceName);
	
	@GET
	@Path("/serviceinfodvcqg/{code}")
	@Consumes({ MediaType.APPLICATION_FORM_URLENCODED })
	@Produces({ MediaType.APPLICATION_JSON })
	public Response getServiceInfoDVCQGDetail(@Context HttpServletRequest request, @Context HttpServletResponse response,
			@Context HttpHeaders header, @Context Company company, @Context Locale locale, @Context User user,
			@Context ServiceContext serviceContext, @PathParam("code") String code);

	@POST
	@Path("/syncdossier")
	@Consumes({ MediaType.APPLICATION_FORM_URLENCODED })
	@Produces({ MediaType.APPLICATION_JSON })
	public Response doSyncDossier(@Context HttpServletRequest request, @Context HttpServletResponse response,
			@Context HttpHeaders header, @Context Company company, @Context Locale locale, @Context User user,
			@Context ServiceContext serviceContext, @FormParam("strDossierId") String strDossierId,
			@FormParam("isUpdating") String isUpdating);

	@POST
	@Path("/syncdossierstatus")
	@Consumes({ MediaType.APPLICATION_FORM_URLENCODED })
	@Produces({ MediaType.APPLICATION_JSON })
	public Response doSyncDossierStatus(@Context HttpServletRequest request, @Context HttpServletResponse response,
			@Context HttpHeaders header, @Context Company company, @Context Locale locale, @Context User user,
			@Context ServiceContext serviceContext, @FormParam("strDossierId") String strDossierId);

	@POST
	@Path("/accesstoken")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
	public Response doConfirm(@Context HttpServletRequest request, @Context HttpServletResponse response,
			@Context HttpHeaders header, @Context Company company, @Context Locale locale, @Context User user,
			@Context ServiceContext serviceContext);

	@POST
	@Path("/getsharingdictcollection")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
	public Response getSharingDictCollection(@Context HttpServletRequest request, @Context HttpServletResponse response,
			@Context HttpHeaders header, @Context Company company, @Context Locale locale, @Context User user,
			@Context ServiceContext serviceContext, String body);

	@POST
	@Path("/getsharingdata")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
	public Response getSharingData(@Context HttpServletRequest request, @Context HttpServletResponse response,
			@Context HttpHeaders header, @Context Company company, @Context Locale locale, @Context User user,
			@Context ServiceContext serviceContext, String body);

	@POST
	@Path("/mappingserviceinfo")
	@Consumes({ MediaType.APPLICATION_FORM_URLENCODED })
	@Produces({ MediaType.APPLICATION_JSON })
	public Response doMappingServiceInfo(@Context HttpServletRequest request, @Context HttpServletResponse response,
			@Context HttpHeaders header, @Context Company company, @Context Locale locale, @Context User user,
			@Context ServiceContext serviceContext, @FormParam("serviceCode") String serviceCode,
			@FormParam("serviceCodeDVCQG") String serviceCodeDVCQG,
			@FormParam("serviceNameDVCQG") String serviceNameDVCQG);

	@POST
	@Path("/syncserviceinfo")
	@Consumes({ MediaType.APPLICATION_FORM_URLENCODED })
	@Produces({ MediaType.APPLICATION_JSON })
	public Response doSyncServiceInfo(@Context HttpServletRequest request, @Context HttpServletResponse response,
			@Context HttpHeaders header, @Context Company company, @Context Locale locale, @Context User user,
			@Context ServiceContext serviceContext, @FormParam("serviceCodes") String serviceCodes, @FormParam("type") String type);

	@DELETE
	@Path("/removemappingserviceinfo/{id}")
	@Consumes({ MediaType.APPLICATION_FORM_URLENCODED })
	@Produces({ MediaType.APPLICATION_JSON })
	public Response doRemoveMappingServiceInfo(@Context HttpServletRequest request,
			@Context HttpServletResponse response, @Context HttpHeaders header, @Context Company company,
			@Context Locale locale, @Context User user, @Context ServiceContext serviceContext,
			@PathParam("id") long id);

	@POST
	@Path("/getsharingqa")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
	public Response getSharingQA(@Context HttpServletRequest request, @Context HttpServletResponse response,
			@Context HttpHeaders header, @Context Company company, @Context Locale locale, @Context User user,
			@Context ServiceContext serviceContext, String body);

	@POST
	@Path("/syncsharingqa")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
	public Response doSyncSharingQA(@Context HttpServletRequest request, @Context HttpServletResponse response,
			@Context HttpHeaders header, @Context Company company, @Context Locale locale, @Context User user,
			@Context ServiceContext serviceContext, String body);

	@POST
	@Path("/syncservicedomain")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
	public Response doSyncServiceDomain(@Context HttpServletRequest request, @Context HttpServletResponse response,
			@Context HttpHeaders header, @Context Company company, @Context Locale locale, @Context User user,
			@Context ServiceContext serviceContext, String body);

	@POST
	@Path("/syncgovernmentagency")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
	public Response doSyncGovernmentAgency(@Context HttpServletRequest request, @Context HttpServletResponse response,
			@Context HttpHeaders header, @Context Company company, @Context Locale locale, @Context User user,
			@Context ServiceContext serviceContext, String body);

	@POST
	@Path("/syncserviceadministration")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
	public Response doSyncServiceAdministration(@Context HttpServletRequest request,
			@Context HttpServletResponse response, @Context HttpHeaders header, @Context Company company,
			@Context Locale locale, @Context User user, @Context ServiceContext serviceContext, String body);
	
	
	@POST
	@Path("/createdossierfromdvcqg")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
	public Response doCreateDossierFromDVCQG(@Context HttpServletRequest request,
			@Context HttpServletResponse response, @Context HttpHeaders header, @Context Company company,
			@Context Locale locale, @Context User user, @Context ServiceContext serviceContext, String body);

}
