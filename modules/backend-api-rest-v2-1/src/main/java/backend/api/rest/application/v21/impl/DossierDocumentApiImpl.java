package backend.api.rest.application.v21.impl;

import java.net.HttpURLConnection;
import java.util.Date;
import java.util.List;

import javax.activation.DataHandler;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;

import org.apache.cxf.jaxrs.ext.multipart.Attachment;
import org.opencps.auth.api.BackendAuth;
import org.opencps.auth.api.BackendAuthImpl;
import org.opencps.dossiermgt.action.DossierDocumentActions;
import org.opencps.dossiermgt.action.impl.DossierDocumentActionsImpl;
import org.opencps.dossiermgt.model.Dossier;
import org.opencps.dossiermgt.model.DossierDocument;
import org.opencps.dossiermgt.service.DossierLocalServiceUtil;

import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.StringPool;

import backend.api.rest.application.v21.parser.DossierDocumentParser;
import io.swagger.api.DossierDocumentsApi;
import io.swagger.model.DossierDocumentModel;
import io.swagger.model.DossierDocumentResultModel;

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
	public void downloadDocByTypeCode(String id, String typeCode) {

		// TODO: check user is loged or password for access dossier file
//		BackendAuth auth = new BackendAuthImpl();
		Long dossierId = GetterUtil.getLong(id);

		try {
//
////			if (!auth.isAuth(serviceContext)) {
////				throw new UnauthenticationException();
////			}
//
//			DossierDocument dossierDoc = DossierDocumentLocalServiceUtil.getDocByTypeCode(dossierId, typeCode);
//			
//			// TODO download file with dossierFileID
//			if (Validator.isNull(dossierFile) && Validator.isNumber(referenceUid)) {
//				dossierFile = DossierFileLocalServiceUtil.fetchDossierFile(Long.valueOf(referenceUid));
//			}
//
//			if (dossierFile.getFileEntryId() > 0) {
//				FileEntry fileEntry = DLAppLocalServiceUtil.getFileEntry(dossierFile.getFileEntryId());
//
//				File file = DLFileEntryLocalServiceUtil.getFile(fileEntry.getFileEntryId(), fileEntry.getVersion(),
//						true);
//
//				ResponseBuilder responseBuilder = Response.ok((Object) file);
//
//				responseBuilder.header("Content-Disposition",
//						"attachment; filename=\"" + fileEntry.getFileName() + "\"");
//				responseBuilder.header("Content-Type", fileEntry.getMimeType());
//
//				return responseBuilder.build();
//			} else {
//				return Response.status(HttpURLConnection.HTTP_NO_CONTENT).build();
//			}

		} catch (Exception e) {
			_log.info(e);
			respones.setStatus(HttpURLConnection.HTTP_INTERNAL_ERROR);
		}

//		return null;
	}

	@Override
	public DossierDocumentResultModel getDocumentList(String id, Integer start, Integer end) {
		// TODO: check user is loged or password for access dossier file
//		BackendAuth auth = new BackendAuthImpl();
		Long dossierId = GetterUtil.getLong(id);
		DossierDocumentResultModel results = null;
		

		try {

//			if (!auth.isAuth(serviceContext)) {
//				throw new UnauthenticationException();
//			}
			if (end == 0) {
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
		} catch (Exception e) {
			_log.info(e);
			respones.setStatus(HttpURLConnection.HTTP_INTERNAL_ERROR);
		}
		return results;
	}

	@Override
	public DossierDocumentModel createDossierDoc(String id, Attachment upfileDetail, String documentType,
			String documentName, String documentCode) {
		BackendAuth auth = new BackendAuthImpl();

		long groupId = GetterUtil.getLong(header.getHeaderString("groupId"));
		Long dossierId = GetterUtil.getLong(id);
		DossierDocumentModel result = null;

		try {

//			if (!auth.isAuth(serviceContext)) {
//				throw new UnauthenticationException();
//			}

			Dossier dossier = null;
			long dossierActionId = 0;

			if (dossierId != 0) {
				dossier = DossierLocalServiceUtil.fetchDossier(dossierId);
				if (dossier != null) {
					dossierActionId = dossier.getDossierActionId();
				}
			}

			DataHandler dataHandler = upfileDetail.getDataHandler();

			DossierDocumentActions action = new DossierDocumentActionsImpl();
			
			
			_log.info("__Start add file at:" + new Date());

			DossierDocument dossierDoc = action.addDossierDoc(groupId, dossierId, dossierActionId, documentType,
					documentName, documentCode, dataHandler.getName(), 0, dataHandler.getInputStream(),
					StringPool.BLANK, serviceContext);
			
			_log.info("__End add file at:" + new Date());
			
//			if(Validator.isNotNull(formData)) {
//				dossierFile.setFormData(formData);
//			}
			
//			_log.info("__Start update dossier file at:" + new Date());

//			DossierFileLocalServiceUtil.updateDossierFile(dossierFile);

			_log.info("__End update dossier file at:" + new Date());
			if (dossierDoc != null) {
				result = DossierDocumentParser.mappingDocumentTypeModel(dossierDoc);
			}
		} catch (Exception e) {
			_log.info(e);
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
