package backend.kyso.api.rest.application;

import java.net.HttpURLConnection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.BeanParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
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
import org.osgi.service.jaxrs.whiteboard.JaxrsWhiteboardConstants;

import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.Validator;

import io.swagger.annotations.ApiOperation;

/**
 * @author GIAHUY
 */
@Component(
property = { 
	    JaxrsWhiteboardConstants.JAX_RS_APPLICATION_BASE + "=/secure/rest/v2/signature", 
	    JaxrsWhiteboardConstants.JAX_RS_NAME + "=OpenCPS.restv2signature"
},
service = Application.class)
public class BackendKysoApiRestApplication extends Application {

	private static final Log _log = LogFactoryUtil.getLog(BackendKysoApiRestApplication.class.getName());

	public Set<Object> getSingletons() {
		Set<Object> singletons = new HashSet<Object>();
		// add REST endpoints (resources)
//		singletons.add(new DigitalSignatureManagementImpl());
		singletons.add(this);
		return singletons;
//		return Collections.<Object>singleton(this);
	}

	@POST
	@Path("/requestsToken")
	@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_FORM_URLENCODED })
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public Response getByTokens(@Context HttpHeaders header, @BeanParam DigitalSignatureInputModel input) {
		long groupId = GetterUtil.getLong(header.getHeaderString("groupId"));
		
		_log.info("START: =========");
			try {

				DigitalSignatureActions action = new DigitalSignatureActionsImpl();

				String emailUser = input.getEmailUser();
				long fileEntryId = Long.valueOf(input.getFileEntryId());
				String typeSignature = input.getTypeSignature();
				String postStepCode = input.getPostStepCode();
				_log.info("emailUser Id: "+emailUser);
				_log.info("fileEntryId Id: "+fileEntryId);
				_log.info("typeSignature: "+typeSignature);
				
				JSONObject results = action.createHashSignature(emailUser, fileEntryId, typeSignature, postStepCode, groupId);
//				JSONObject results = JSONFactoryUtil.createJSONObject();
				_log.info("results : "+results);

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
	public Response completeSignature(@Context HttpHeaders header,
			@BeanParam DigitalSignatureInputModel input) {

//		long groupId = GetterUtil.getLong(header.getHeaderString("groupId"));

		try {
			
			DigitalSignatureActions action = new DigitalSignatureActionsImpl();

			String sign = input.getSign();
			String signFieldName = input.getSignFieldName();
			String fileName = input.getFileName();
			JSONObject results = JSONFactoryUtil.createJSONObject();
			if (Validator.isNotNull(sign) && Validator.isNotNull(signFieldName) && Validator.isNotNull(fileName)) {
				results = action.completeSignature(sign, signFieldName, fileName);
			}
//			JSONObject results = JSONFactoryUtil.createJSONObject();

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
		return "It works signature!";
	}


}