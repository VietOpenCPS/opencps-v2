package backend.kyso.api.rest.application;

import com.liferay.document.library.kernel.model.DLFileEntry;
import com.liferay.document.library.kernel.service.DLAppLocalServiceUtil;
import com.liferay.document.library.kernel.service.DLFileEntryLocalServiceUtil;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;
import com.viettel.signature.plugin.SignPdfFile;

import java.io.File;
import java.net.HttpURLConnection;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.BeanParam;
import javax.ws.rs.Consumes;
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
import org.opencps.dossiermgt.model.Dossier;
import org.opencps.dossiermgt.model.DossierFile;
import org.opencps.dossiermgt.service.DossierFileLocalServiceUtil;
import org.opencps.dossiermgt.service.DossierLocalServiceUtil;
import org.opencps.kyso.action.DigitalSignatureActions;
import org.opencps.kyso.action.impl.DigitalSignatureActionsImpl;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.jaxrs.whiteboard.JaxrsWhiteboardConstants;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

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

			return Response.status(HttpURLConnection.HTTP_OK).entity(JSONFactoryUtil.looseSerialize(results)).build();

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

//	@GET
//	@Produces("text/plain")
//	public String working() {
//		return "It works signature!";
//	}

	@POST
	@Path("/{id}/hashFilePDF/{referenceUid}")
	@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_FORM_URLENCODED })
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public Response hashFilePDF(@Context HttpServletRequest request, @Context HttpHeaders header,
			@ApiParam(value = "id of dossier", required = true) @PathParam("id") String id,
			@ApiParam(value = "referenceUid of dossierfile", required = true) @PathParam("referenceUid") String referenceUid,
			@BeanParam DigitalSignatureInputModel input) {
		
		long groupId = GetterUtil.getLong(header.getHeaderString(Field.GROUP_ID));
		DigitalSignatureActions action = new DigitalSignatureActionsImpl();
		
		try {
			
			long dossierId = GetterUtil.getLong(id);
			if (dossierId == 0) {
				Dossier dossier = DossierLocalServiceUtil.getByRef(groupId, id);
				if (dossier != null) {
					dossierId = dossier.getDossierId();
				}
			}
			
			DossierFile dossierFile =
					DossierFileLocalServiceUtil.getDossierFileByReferenceUid(
							dossierId, referenceUid);
			
			if (Validator.isNull(dossierFile) &&
					Validator.isNumber(referenceUid)) {
					dossierFile = DossierFileLocalServiceUtil.fetchDossierFile(
						Long.valueOf(referenceUid));
				}
			
			if (dossierFile.getFileEntryId() > 0) {
				
				FileEntry fileEntry = DLAppLocalServiceUtil.getFileEntry(
						dossierFile.getFileEntryId());
				
				String certChainBase64 = input.getCertChainBase64();
				
				JSONObject results = action.hashFile(fileEntry.getFileEntryId(), certChainBase64, request);
				
				return Response.status(HttpURLConnection.HTTP_OK).entity(JSONFactoryUtil.looseSerialize(results)).build(); 
				
			} else {
				return Response.status(
						HttpURLConnection.HTTP_NO_CONTENT).build();
			} 
		} catch (Exception e) {
			return Response.status(HttpURLConnection.HTTP_INTERNAL_ERROR).entity(e).build();
		}
	}
	
	@POST
	@Path("/insertSignature")
	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON, MediaType.APPLICATION_FORM_URLENCODED })
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public Response insertSignatureFile(@Context HttpServletRequest request, @Context HttpHeaders header, 
			@BeanParam DigitalSignatureInputModel input ) {

		DigitalSignatureActions action = new DigitalSignatureActionsImpl();

		try {
			Long fileEntryId = Long.valueOf(input.getFileEntryId());
			if (fileEntryId > 0) {
				String signatureBase64 = input.getSignatureBase64();
				SignPdfFile signPdfFile = (SignPdfFile) request.getSession().getAttribute("PDFSignature");
				
				JSONObject results = JSONFactoryUtil.createJSONObject();
				if (Validator.isNotNull(signatureBase64) && Validator.isNotNull(input.getFileName()) 
						&& Validator.isNotNull(signPdfFile) ) {
					results = action.insertSignnature(signatureBase64, input.getFileName(), signPdfFile, fileEntryId);			
				}
				
				return Response.status(HttpURLConnection.HTTP_OK).entity(JSONFactoryUtil.looseSerialize(results)).build();
			} else {
				return Response.status(
						HttpURLConnection.HTTP_NO_CONTENT).build();
			}
		} catch (Exception e) {
			return Response.status(HttpURLConnection.HTTP_INTERNAL_ERROR).entity(e).build();
		}
	}

}