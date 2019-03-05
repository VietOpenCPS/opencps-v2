package backend.api.rest.application.v21.impl;

import com.liferay.document.library.kernel.service.DLAppLocalServiceUtil;
import com.liferay.document.library.kernel.service.DLFileEntryLocalServiceUtil;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.Validator;

import java.io.File;
import java.net.HttpURLConnection;
import java.util.Date;
import java.util.List;

import javax.activation.DataHandler;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

import org.apache.cxf.jaxrs.ext.multipart.Attachment;
import org.opencps.dossiermgt.action.DossierDocumentActions;
import org.opencps.dossiermgt.action.impl.DossierDocumentActionsImpl;
import org.opencps.dossiermgt.model.Dossier;
import org.opencps.dossiermgt.model.DossierDocument;
import org.opencps.dossiermgt.service.DossierDocumentLocalServiceUtil;
import org.opencps.dossiermgt.service.DossierLocalServiceUtil;
import org.opencps.rest.application.api.DossierDocumentsApi;
import org.opencps.rest.application.model.DossierDocumentModel;
import org.opencps.rest.application.model.DossierDocumentResultModel;

import backend.api.rest.application.v21.parser.DossierDocumentParser;

public class DossierDocumentApiImpl implements DossierDocumentsApi {

	@Context
	private User user;
	@Context
	private HttpHeaders header;
	@Context
	ServiceContext serviceContext;
	@Context
	HttpServletResponse respones;

	private static Log _log = LogFactoryUtil.getLog(DossierDocumentApiImpl.class);
	@Override
	public void downloadDocByReferenceUid(String id, String referenceUid) {

		// TODO: check user is loged or password for access dossier file
		_log.debug("====START DOWNLOAD DOCUMENT==== ");
		long groupId = GetterUtil.getLong(header.getHeaderString("groupId"));
		Long dossierId = GetterUtil.getLong(id);

		try {

//			if (!auth.isAuth(serviceContext)) {
//				throw new UnauthenticationException();
//			}
			DossierDocument dossierDoc = DossierDocumentLocalServiceUtil.getDocByReferenceUid(groupId, dossierId, referenceUid);
//			// download file with dossierDocumentFileId
			long documentFileId = 0;
			if (dossierDoc != null) {
				documentFileId = dossierDoc.getDocumentFileId();
			}
			
			if (documentFileId > 0) {
				FileEntry fileEntry = DLAppLocalServiceUtil.getFileEntry(documentFileId);

				File file = DLFileEntryLocalServiceUtil.getFile(fileEntry.getFileEntryId(), fileEntry.getVersion(),
						true);

				ResponseBuilder responseBuilder = Response.ok((Object) file);

				responseBuilder.header("Content-Disposition",
						"attachment; filename=\"" + fileEntry.getFileName() + "\"");
				responseBuilder.header("Content-Type", fileEntry.getMimeType());

//				return responseBuilder.build();
				_log.info("====END DOWNLOAD DOCUMENT==== ");
			} else {
//				return Response.status(HttpURLConnection.HTTP_NO_CONTENT).build();
			}

		} catch (Exception e) {
			_log.debug(e);
			_log.info("====DOWNLOAD DOCUMENT ERROR==== ");
			respones.setStatus(HttpURLConnection.HTTP_INTERNAL_ERROR);
		}

//		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public DossierDocumentResultModel getDocumentList(String id, Integer start, Integer end) {
		// TODO: check user is loged or password for access dossier file
		_log.debug("====START GET LIST DOCUMENT==== ");
		Long dossierId = GetterUtil.getLong(id);
		DossierDocumentResultModel results = null;
		

		try {

//			if (!auth.isAuth(serviceContext)) {
//				throw new UnauthenticationException();
//			}
			if (Validator.isNull(end) || end == 0) {
				start = -1;
				end = -1;
			}
			results = new DossierDocumentResultModel();
			DossierDocumentActions actions = new DossierDocumentActionsImpl();
			JSONObject jsonData = actions.getDossierDocumentByDossierId(dossierId, start, end);

			int total = jsonData.getInt("total");
			results.setTotal(total);
			if (jsonData != null && total > 0) {
				results.getData().addAll(
						DossierDocumentParser.mappingDocumentResultModel((List<DossierDocument>) jsonData.get("data")));
			}
			_log.debug("====END GET LIST DOCUMENT==== ");
		} catch (Exception e) {
			_log.debug(e);
			_log.error("====LIST DOCUMENT ERROR==== ");
			respones.setStatus(HttpURLConnection.HTTP_INTERNAL_ERROR);
		}
		return results;
	}

	@Override
	public DossierDocumentModel createDossierDoc(String id, Attachment upfileDetail, String referenceUid, String documentType,
			String documentName, String documentCode) {

		_log.debug("====START CREATE DOCUMENT==== ");
		long groupId = GetterUtil.getLong(header.getHeaderString("groupId"));
		Long dossierId = GetterUtil.getLong(id);
		DossierDocumentModel result = null;
		ServiceContext context = new ServiceContext();
		
		context.setUserId(user.getUserId());
		
		try {

//			if (!auth.isAuth(serviceContext)) {
//				throw new UnauthenticationException();
//			}
			Dossier dossier = null;
			long dossierActionId = 0;

			if (dossierId != 0) {
				dossier = DossierLocalServiceUtil.fetchDossier(dossierId);
			} else {
				dossier = DossierLocalServiceUtil.getByRef(groupId, id);
			}
			if (dossier != null) {
				if (dossier.getOriginDossierId() != 0) {
					Dossier hsltDossier = DossierLocalServiceUtil.fetchDossier(dossier.getOriginDossierId());
					if (hsltDossier != null) {
						dossierActionId = hsltDossier.getDossierActionId();
						dossierId = hsltDossier.getDossierId();	
					}
					else {
						dossierId = 0l;
					}
				}
				else {
					dossierActionId = dossier.getDossierActionId();
					dossierId = dossier.getDossierId();
				}
			} else {
				dossierId = 0l;
			}

			DossierDocument oldDocument = DossierDocumentLocalServiceUtil.getDocByReferenceUid(groupId, dossierId, referenceUid);
			
			DataHandler dataHandler = upfileDetail.getDataHandler();

			DossierDocumentActions action = new DossierDocumentActionsImpl();
			
			
			DossierDocument dossierDoc = null;
			if (oldDocument == null) {
				dossierDoc = action.addDossierDoc(groupId, dossierId, referenceUid, dossierActionId, documentType,
						documentName, documentCode, dataHandler.getName(), 0, dataHandler.getInputStream(),
						StringPool.BLANK, context);
			} else {
				dossierDoc = DossierDocumentLocalServiceUtil.updateDossierDoc(groupId,
						oldDocument.getDossierDocumentId(), dossierId, referenceUid, dossierActionId, documentType,
						documentName, documentCode, dataHandler.getName(), 0, dataHandler.getInputStream(),
						StringPool.BLANK, context);
			}
			
//			if(Validator.isNotNull(formData)) {
//				dossierFile.setFormData(formData);
//			}
			
//			_log.info("__Start update dossier file at:" + new Date());

//			DossierFileLocalServiceUtil.updateDossierFile(dossierFile);

			if (dossierDoc != null) {
				result = DossierDocumentParser.mappingDocumentTypeModel(dossierDoc);
			}
			_log.debug("====END CREATE DOCUMENT==== ");
		} catch (Exception e) {
			_log.debug(e);
			_log.error("====CREATE DOCUMENT ERROR==== ");
			respones.setStatus(HttpURLConnection.HTTP_INTERNAL_ERROR);
		}
		return result;
	}

//	public static void main(String []args) {
//		 JSONParser parser = new JSONParser();
//		 try {
//			 
////			 InputStream is = 
////		                JsonParser.class.getResourceAsStream("/Users/GIAHUY/Documents/FDS_CODING/OPENCPSV2.1/opencps-v2/modules/backend-api-rest-v2-1/src/main/resources/test.json");
//			 
////			 File f = new File("/Users/GIAHUY/Documents/FDS_CODING/OPENCPSV2.1/opencps-v2/modules/backend-api-rest-v2-1/src/main/resources/test.json");
////			 InputStream is1 = new FileInputStream("/Users/GIAHUY/Documents/FDS_CODING/OPENCPSV2.1/opencps-v2/modules/backend-api-rest-v2-1/src/main/resources/test.json");
////			 String jsonTxt1 = IOUtils.toString( is1 );
////			 
////			 JSONArray json = JSONFactoryUtil.createJSONArray( jsonTxt1 );
////			 System.out.println(json);
//			 
//			 JSONArray a = (JSONArray) parser.parse(new FileReader("/Users/GIAHUY/Documents/FDS_CODING/OPENCPSV2.1/opencps-v2/modules/backend-api-rest-v2-1/src/main/resources/test.json"));
//			 System.out.println(a.toJSONString());
////			  for (Object o : a)
////			  {
////			    JSONObject person = (JSONObject) o;
////
////			    String name = (String) person.get("name");
////			    System.out.println(name);
////
////			    String city = (String) person.get("city");
////			    System.out.println(city);
////
////			    String job = (String) person.get("job");
////			    System.out.println(job);
////
////			    JSONArray cars = (JSONArray) person.get("cars");
////
////			    for (Object c : cars)
////			    {
////			      System.out.println(c+"");
////			    }
////			  }
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		
//	}
}
