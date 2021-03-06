package org.fds.opencps.paygate.integration.application;

import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.model.Company;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.xml.QName;

import java.io.File;
import java.net.HttpURLConnection;
import java.util.Collections;
import java.util.Locale;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

import org.fds.opencps.paygate.integration.action.impl.PayGateIntegrationActionImpl;
import org.fds.opencps.paygate.integration.util.PayGateTerm;
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
		long groupId = GetterUtil.getLong(header.getHeaderString(Field.GROUP_ID));

		File file = actionImpl.genneralQRCode(user, groupId, dossierId, serviceContext);
		if (file != null) {

			ResponseBuilder responseBuilder = Response.ok((Object) file);
			String attachmentFilename = String.format(PayGateTerm.ATTACHMENT_FILENAME_FORMAT, file.getName());
			responseBuilder.header(HttpHeaders.CONTENT_DISPOSITION, attachmentFilename);
			responseBuilder.header(HttpHeaders.CONTENT_TYPE, PayGateTerm.PNG_TYPE);

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

		return Response.status(HttpURLConnection.HTTP_OK).entity(result.toJSONString()).build();
	}

	@POST
	@Path("/vtp/receiveresult")
	@Consumes({ MediaType.APPLICATION_FORM_URLENCODED })
	@Produces({ MediaType.APPLICATION_JSON })
	public Response receiveResult(@Context HttpServletRequest request, @Context HttpServletResponse response,
			@Context HttpHeaders header, @Context Company company, @Context Locale locale, @Context User user,
			@Context ServiceContext serviceContext, @FormParam("billcode") String billcode,
			@FormParam("cust_msisdn") String cust_msisdn, @FormParam("error_code") String error_code,
			@FormParam("merchant_code") String merchant_code, @FormParam("order_id") String order_id,
			@FormParam("payment_status") int payment_status, @FormParam("trans_amount") long trans_amount,
			@FormParam("vt_transaction_id") String vt_transaction_id, @FormParam("check_sum") String check_sum) {
		PayGateIntegrationActionImpl actionImpl = new PayGateIntegrationActionImpl();

		JSONObject result = actionImpl.receiveResult(user, serviceContext, billcode, cust_msisdn, error_code,
				merchant_code, order_id, payment_status, trans_amount, vt_transaction_id, check_sum);

		return Response.status(200).entity(result.toJSONString()).build();
	}

	@GET
	@Path("/vtp/search")
	@Consumes({ MediaType.APPLICATION_FORM_URLENCODED })
	@Produces({ MediaType.APPLICATION_JSON })
	public Response searchResult(@Context HttpServletRequest request, @Context HttpServletResponse response,
			@Context HttpHeaders header, @Context Company company, @Context Locale locale, @Context User user,
			@Context ServiceContext serviceContext, @FormParam("order_id") String order_id,
			@FormParam("billcode") String billcode, @FormParam("cust_msisdn") String cust_msisdn,
			@FormParam("trans_amount") long trans_amount) {
		PayGateIntegrationActionImpl actionImpl = new PayGateIntegrationActionImpl();

		JSONObject result = actionImpl.searchResult(user, serviceContext, order_id, billcode, cust_msisdn,
				trans_amount);

		return Response.status(200).entity(result.toJSONString()).build();
	}

	@POST
	@Path("/vtp/mcpaymentconfirm")
	@Consumes({ MediaType.APPLICATION_FORM_URLENCODED })
	@Produces({ MediaType.APPLICATION_JSON })
	public Response mcDoConfirm(@Context HttpServletRequest request, @Context HttpServletResponse response,
			@Context HttpHeaders header, @Context Company company, @Context Locale locale, @Context User user,
			@Context ServiceContext serviceContext, @FormParam("billcode") String billcode,
			@FormParam("merchant_code") String merchant_code, @FormParam("order_id") String order_id,
			@FormParam("check_sum") String check_sum) {
		PayGateIntegrationActionImpl actionImpl = new PayGateIntegrationActionImpl();

		JSONObject result = actionImpl.mcDoConfirm(user, serviceContext, billcode, merchant_code, order_id, check_sum);

		return Response.status(HttpURLConnection.HTTP_OK).entity(result.toJSONString()).build();
	}

	@POST
	@Path("/vtp/mcreceiveresult")
	@Consumes({ MediaType.APPLICATION_FORM_URLENCODED })
	@Produces({ MediaType.APPLICATION_JSON })
	public Response mcReceiveResult(@Context HttpServletRequest request, @Context HttpServletResponse response,
			@Context HttpHeaders header, @Context Company company, @Context Locale locale, @Context User user,
			@Context ServiceContext serviceContext, @FormParam("billcode") String billcode,
			@FormParam("cust_msisdn") String cust_msisdn, @FormParam("error_code") String error_code,
			@FormParam("merchant_code") String merchant_code, @FormParam("order_id") String order_id,
			@FormParam("payment_status") int payment_status, @FormParam("trans_amount") long trans_amount,
			@FormParam("vt_transaction_id") String vt_transaction_id, @FormParam("check_sum") String check_sum) {
		PayGateIntegrationActionImpl actionImpl = new PayGateIntegrationActionImpl();

		JSONObject result = actionImpl.mcReceiveResult(user, serviceContext, billcode, cust_msisdn, error_code,
				merchant_code, order_id, payment_status, trans_amount, vt_transaction_id, check_sum);

		return Response.status(200).entity(result.toJSONString()).build();
	}

	@POST
	@Path("/vtp/mcsearch")
	@Consumes({ MediaType.APPLICATION_FORM_URLENCODED })
	@Produces({ MediaType.APPLICATION_JSON })
	public Response mcSearchResult(@Context HttpServletRequest request, @Context HttpServletResponse response,
			@Context HttpHeaders header, @Context Company company, @Context Locale locale, @Context User user,
			@Context ServiceContext serviceContext, @FormParam("order_id") String order_id,
			@FormParam("billcode") String billcode, @FormParam("cust_msisdn") String cust_msisdn,
			@FormParam("trans_amount") long trans_amount) {
		PayGateIntegrationActionImpl actionImpl = new PayGateIntegrationActionImpl();

		JSONObject result = actionImpl.mcSearchResult(user, serviceContext, order_id, billcode, cust_msisdn,
				trans_amount);

		return Response.status(200).entity(result.toJSONString()).build();
	}

	@POST
	@Path("/vtp/dvcreceiveresult")
	@Consumes({ MediaType.APPLICATION_FORM_URLENCODED })
	@Produces({ MediaType.APPLICATION_JSON })
	public Response dvcReceiveResult(@Context HttpServletRequest request, @Context HttpServletResponse response,
			@Context HttpHeaders header, @Context Company company, @Context Locale locale, @Context User user,
			@Context ServiceContext serviceContext, @FormParam("url") String url, @FormParam("groupId") long groupId,
			@FormParam("actionCode") String actionCode, @FormParam("order_id") String order_id,
			@FormParam("userName") String userName, @FormParam("pwd") String pwd) {
		PayGateIntegrationActionImpl actionImpl = new PayGateIntegrationActionImpl();

		JSONObject result = actionImpl.dvcReceiveResult(user, serviceContext, url, groupId, actionCode, order_id,
				userName, pwd);

		return Response.status(200).entity(result.toJSONString()).build();
	}

	@POST
	@Path("/kpdvcqg/createtransaction")
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_FORM_URLENCODED })
	@Produces({ MediaType.APPLICATION_JSON })
	public Response kpCreateTransaction(@Context HttpServletRequest request, @Context HttpServletResponse response,
			@Context HttpHeaders header, @Context Company company, @Context Locale locale, @Context User user,
			@Context ServiceContext serviceContext,
			@FormParam("dossierId") long dossierId) {

		PayGateIntegrationActionImpl actionImpl = new PayGateIntegrationActionImpl();

		long groupId = GetterUtil.getLong(header.getHeaderString(Field.GROUP_ID));
		String result = actionImpl.kpCreateTransaction(user, groupId, dossierId, serviceContext);
		return Response.status(200).entity(result).build();
	}

	@POST
	@Path("/kpdvcqg/paymentconfirm")
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_FORM_URLENCODED })
	@Produces({ MediaType.APPLICATION_JSON })
	public Response kpCallback(@Context HttpServletRequest request, @Context HttpServletResponse response,
			@Context HttpHeaders header, @Context Company company, @Context Locale locale, @Context User user,
			@Context ServiceContext serviceContext,
			String body) {

		PayGateIntegrationActionImpl actionImpl = new PayGateIntegrationActionImpl();

		JSONObject result = actionImpl.kpCallBack(user, serviceContext, body);

		return Response.status(200).entity(result.toJSONString()).build();
	}

	@POST
	@Path("/kpdvcqg/dptracuuthanhtoanhs")
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_FORM_URLENCODED })
	@Produces({ MediaType.APPLICATION_JSON })
	public Response dptracuuthanhtoanhs(@Context HttpServletRequest request, @Context HttpServletResponse response,
			@Context HttpHeaders header, @Context Company company, @Context Locale locale, @Context User user,
			@Context ServiceContext serviceContext,
			String body) {

		PayGateIntegrationActionImpl actionImpl = new PayGateIntegrationActionImpl();

		JSONObject result = actionImpl.dptracuuthanhtoanhs(user, serviceContext, body);

		return Response.status(200).entity(result.toJSONString()).build();
	}
}