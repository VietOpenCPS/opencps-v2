package backend.kyso.api.rest.application;

import java.net.HttpURLConnection;
import java.util.Collections;
import java.util.Locale;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.BeanParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.opencps.api.digitalsignature.model.DigitalSignatureInputModel;
import org.opencps.kyso.action.DigitalSignatureActions;
import org.opencps.kyso.action.impl.DigitalSignatureActionsImpl;
import org.osgi.service.component.annotations.Component;

import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Company;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.StringPool;

import io.swagger.annotations.ApiOperation;

/**
 * @author GIAHUY
 */
@ApplicationPath("/v2/signature")
@Component(immediate = true, service = Application.class)
public class BackendKysoApiRestApplication extends Application {

	private static final Log _log = LogFactoryUtil.getLog(BackendKysoApiRestApplication.class.getName());

	public Set<Object> getSingletons() {
//		Set<Object> singletons = new HashSet<Object>();
//		
//		// add REST endpoints (resources)
//		singletons.add(new DigitalSignatureManagementImpl());
//		return singletons;
		return Collections.<Object>singleton(this);
	}

	@POST
	@Path("/requestsToken")
	@ApiOperation(value = "requestsToken")
	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON, MediaType.APPLICATION_FORM_URLENCODED })
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public Response getByTokens(@Context HttpServletRequest request, @Context HttpHeaders header,
			@Context Company company, @Context Locale locale, @Context User user,
			@Context ServiceContext serviceContext, @BeanParam DigitalSignatureInputModel input) {

			try {

				DigitalSignatureActions action = new DigitalSignatureActionsImpl();

				String emailUser = user.getEmailAddress();
				_log.info("emailUser Id: "+emailUser);
				long fileEntryId = 0;
				
				JSONObject results = action.createHashSignature(emailUser, fileEntryId);

				return Response.status(200).entity(JSONFactoryUtil.looseSerialize(results)).build();

			} catch (Exception e) {
//				ErrorMsg error = new ErrorMsg();

//				if (e instanceof UnauthenticationException) {
//					error.setMessage("Non-Authoritative Information.");
//					error.setCode(HttpURLConnection.HTTP_NOT_AUTHORITATIVE);
//					error.setDescription("Non-Authoritative Information.");
	//
//					return Response.status(HttpURLConnection.HTTP_NOT_AUTHORITATIVE).entity(error).build();
//				} else {
//					if (e instanceof UnauthorizationException) {
//						error.setMessage("Unauthorized.");
//						error.setCode(HttpURLConnection.HTTP_NOT_AUTHORITATIVE);
//						error.setDescription("Unauthorized.");
	//
//						return Response.status(HttpURLConnection.HTTP_UNAUTHORIZED).entity(error).build();
	//
//					} else {

//						error.setMessage("Internal Server Error");
//						error.setCode(HttpURLConnection.HTTP_FORBIDDEN);
//						error.setDescription(e.getMessage());

						return Response.status(HttpURLConnection.HTTP_INTERNAL_ERROR).entity(e).build();

//					}
//				}
			}
	}
//
	@POST
	@Path("/completeSignature")
	@ApiOperation(value = "completeSignature")
	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON, MediaType.APPLICATION_FORM_URLENCODED })
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public Response completeSignature(@Context HttpServletRequest request, @Context HttpHeaders header,
			@Context Company company, @Context Locale locale, @Context User user,
			@Context ServiceContext serviceContext, @PathParam("id") long id,
			@BeanParam DigitalSignatureInputModel input) {
		// TODO Add Deliverable Type
//		BackendAuth auth = new BackendAuthImpl();

//				long groupId = GetterUtil.getLong(header.getHeaderString("groupId"));

		try {
//			if (!auth.isAuth(serviceContext)) {
//				throw new UnauthenticationException();
//			}

//			DigitalSignatureActions action = new DigitalSignatureActionsImpl();

			String sign = input.getSign();
			String signFieldName = input.getSignFieldName();
			String fileName = input.getFileName();
//			JSONObject reCsults = action.completeSignature(user, id, sign, signFieldName, fileName);
//					Deliverable deliverable = action.addDeliverable(groupId, deliverableType, deliverableCode, 
//							govAgencyCode, applicantIdNo, applicantName, subject, issueDate, expireDate,
//							revalidate, deliverableState, serviceContext);
//
//					DeliverableInputModel result = DeliverableUtils.mappingToDeliverablesModel(deliverable);
			JSONObject results = JSONFactoryUtil.createJSONObject();

			return Response.status(200).entity(JSONFactoryUtil.looseSerialize(results)).build();

		} catch (Exception e) {
			return Response.status(HttpURLConnection.HTTP_INTERNAL_ERROR).entity(e).build();
//			ErrorMsg error = new ErrorMsg();

//			if (e instanceof UnauthenticationException) {
//				error.setMessage("Non-Authoritative Information.");
//				error.setCode(HttpURLConnection.HTTP_NOT_AUTHORITATIVE);
//				error.setDescription("Non-Authoritative Information.");
//
//				return Response.status(HttpURLConnection.HTTP_NOT_AUTHORITATIVE).entity(error).build();
////			} else {
////				if (e instanceof UnauthorizationException) {
////					error.setMessage("Unauthorized.");
////					error.setCode(HttpURLConnection.HTTP_NOT_AUTHORITATIVE);
////					error.setDescription("Unauthorized.");
////
////					return Response.status(HttpURLConnection.HTTP_UNAUTHORIZED).entity(error).build();
////
////				} else {
////
////					error.setMessage("Internal Server Error");
////					error.setCode(HttpURLConnection.HTTP_FORBIDDEN);
////					error.setDescription(e.getMessage());
////
////					return Response.status(HttpURLConnection.HTTP_INTERNAL_ERROR).entity(error).build();
////
////				}
////			}
		}
	}

	@GET
	@Produces("text/plain")
	public String working() {
		return "It works!";
	}


}