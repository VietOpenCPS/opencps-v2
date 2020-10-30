package org.opencps.api.controller;

import com.liferay.portal.kernel.model.Company;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.ServiceContext;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.opencps.api.comment.model.CommentSearchModel;
import org.opencps.api.dossier.model.DossierInputModel;
import org.opencps.dossiermgt.rest.model.ViettelPostUpdateOrder;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Path;
import javax.ws.rs.POST;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.FormParam;
import javax.ws.rs.BeanParam;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.HttpURLConnection;
import java.util.Locale;

@Path("dossiers")
@Api(value = "dossiers" ,tags = "dossiers")
public interface VNPostManagement
{
		@POST
		@Path("/testPostBill")
		@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON, MediaType.APPLICATION_FORM_URLENCODED })
		@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
		public Response testPostBill(@Context HttpServletRequest request, @Context HttpHeaders header,@Context Locale locale);

		@POST
		@Path("/testViettlePost")
		@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON, MediaType.APPLICATION_FORM_URLENCODED })
		@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
		public Response testViettlePost(@Context HttpServletRequest request, @Context HttpHeaders header,@Context Locale locale);

		@POST
		@Path("/updateOrder")
		@Consumes({ MediaType.APPLICATION_JSON })
		@Produces({ MediaType.APPLICATION_JSON })
		public Response updateOrder(@Context HttpHeaders header, ViettelPostUpdateOrder updateInfo);

		@GET
		@Path("/vnpost")
		@Consumes({ MediaType.APPLICATION_FORM_URLENCODED })
		@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
		public Response getDossierDetailByBarcode(@Context HttpServletRequest request, @Context HttpHeaders header,
			@Context ServiceContext serviceContext,@ApiParam(value = "Receipt Code") @QueryParam("ma_bien_nhan") String receiptCode);

		@POST
		@Path("/vnpost")
		@Consumes({ MediaType.APPLICATION_FORM_URLENCODED })
		@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
		public Response updateDossierByBarcode(@Context HttpServletRequest request, @Context HttpHeaders header,
			@Context ServiceContext serviceContext,@ApiParam(value = "Receipt Code") @FormParam("receiptCode") String receiptCode,
		@BeanParam DossierInputModel dossierInputModel , @FormParam("ma_bien_nhan") String ma_bien_nhan);


}
