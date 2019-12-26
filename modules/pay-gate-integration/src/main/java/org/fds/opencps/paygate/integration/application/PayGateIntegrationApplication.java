package org.fds.opencps.paygate.integration.application;

import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.model.Company;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.GetterUtil;

import java.io.File;
import java.net.HttpURLConnection;
import java.util.Collections;
import java.util.Locale;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

import org.fds.opencps.paygate.integration.action.impl.PayGateIntegrationActionImpl;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.jaxrs.whiteboard.JaxrsWhiteboardConstants;

@Component(immediate = true, property = { JaxrsWhiteboardConstants.JAX_RS_APPLICATION_BASE + "=/secure/pgi/",
		JaxrsWhiteboardConstants.JAX_RS_NAME + "=OpenCPS.pgi" }, service = Application.class)
public class PayGateIntegrationApplication extends Application {

	@Override
	public Set<Object> getSingletons() {
		return Collections.<Object>singleton(this);
	}

	@POST
	@Path("/vtp/qrcode")
	@Consumes({ MediaType.APPLICATION_FORM_URLENCODED })
	@Produces({ MediaType.APPLICATION_OCTET_STREAM })
	public Response generalQRCode(@Context HttpServletRequest request, @Context HttpServletResponse response,
			@Context HttpHeaders header, @Context Company company, @Context Locale locale, @Context User user,
			@Context ServiceContext serviceContext, @FormParam("dossierId") long dossierId) {
		PayGateIntegrationActionImpl actionImpl = new PayGateIntegrationActionImpl();
		long groupId = GetterUtil.getLong(header.getHeaderString("groupId"));

		File file = actionImpl.genneralQRCode(user, groupId, dossierId, serviceContext);
		if (file != null) {

			ResponseBuilder responseBuilder = Response.ok((Object) file);

			responseBuilder.header("Content-Disposition", "attachment; filename=\"" + file.getName() + "\"");
			responseBuilder.header("Content-Type", "image/png");

			return responseBuilder.build();
		} else {
			return Response.status(HttpURLConnection.HTTP_NO_CONTENT).build();
		}
	}

	@POST
	@Path("/vtp/paymentconfirm")
	@Consumes({ MediaType.APPLICATION_FORM_URLENCODED })
	@Produces({ MediaType.APPLICATION_JSON })
	public Response doConfirm(@Context HttpServletRequest request, @Context HttpServletResponse response,
			@Context HttpHeaders header, @Context Company company, @Context Locale locale, @Context User user,
			@Context ServiceContext serviceContext, @FormParam("billcode") String billcode,
			@FormParam("merchant_code") String merchant_code, @FormParam("order_id") String order_id,
			@FormParam("check_sum") String check_sum) {
		PayGateIntegrationActionImpl actionImpl = new PayGateIntegrationActionImpl();

		JSONObject result = actionImpl.doConfirm(user, serviceContext, billcode, merchant_code, order_id, check_sum);

		return Response.status(200).entity(result.toJSONString()).build();
	}
	
	@POST
	@Path("/vtp/receiveresult")
	@Consumes({ MediaType.APPLICATION_FORM_URLENCODED })
	@Produces({ MediaType.APPLICATION_JSON })
	public Response receiveResult(@Context HttpServletRequest request, @Context HttpServletResponse response,
			@Context HttpHeaders header, @Context Company company, @Context Locale locale, @Context User user,
			@Context ServiceContext serviceContext, @FormParam("billcode") String billcode,
			@FormParam("merchant_code") String merchant_code, @FormParam("order_id") String order_id,
			@FormParam("check_sum") String check_sum) {
		PayGateIntegrationActionImpl actionImpl = new PayGateIntegrationActionImpl();

		JSONObject result = actionImpl.doConfirm(user, serviceContext, billcode, merchant_code, order_id, check_sum);

		return Response.status(200).entity(result.toJSONString()).build();
	}

}