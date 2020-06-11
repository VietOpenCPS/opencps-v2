package org.opencps.api.controller;

import com.liferay.portal.kernel.service.ServiceContext;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiParam;
import org.opencps.api.comment.model.CommentSearchModel;
import org.opencps.api.dossier.model.DossierInputModel;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("dossiers")
@Api(value = "dossiers" ,tags = "dossiers")
public interface VNPostManagement
{


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
		public Response updateDossierDetailByBarcode(@Context HttpServletRequest request, @Context HttpHeaders header,
			@Context ServiceContext serviceContext,@ApiParam(value = "Receipt Code") @FormParam("receiptCode") String receiptCode,
		@BeanParam DossierInputModel dossierInputModel , @FormParam("ma_bien_nhan") String ma_bien_nhan);


}
