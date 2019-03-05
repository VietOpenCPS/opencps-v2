package backend.api.rest.application.v21.parser;

import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

import java.util.ArrayList;
import java.util.List;

import org.opencps.dossiermgt.model.DossierSync;
import org.opencps.rest.application.model.DossierSyncModel;

public class DossierSyncParser {

	//private static Log _log = LogFactoryUtil.getLog(DocumentTypeParser.class);
	public static List<DossierSyncModel> mappingDossierSyncResultModel(List<DossierSync> docList) {
//		Gson gson = new Gson();
		if (docList != null && docList.size() > 0) {
			List<DossierSyncModel> docModelList = new ArrayList<DossierSyncModel>();
			for (DossierSync doc : docList) {
				String strDoc = JSONFactoryUtil.looseSerialize(doc);
				//_log.info("strDoc: "+strDoc);
				DossierSyncModel model = JSONFactoryUtil.looseDeserialize(strDoc, DossierSyncModel.class);

				docModelList.add(model);
			}
			return docModelList;
		} else{
			return null;
		}

	}

	public static DossierSyncModel mappingDossierSyncModel(DossierSync doc) {
		
//		Gson gson = new Gson();
		String strDoc = JSONFactoryUtil.looseSerialize(doc);
		//_log.info("strDoc: "+strDoc);
		DossierSyncModel object = JSONFactoryUtil.looseDeserialize(strDoc, DossierSyncModel.class);
		
//		ActionConfigItem object = gson.fromJson(gson.toJson(ett), ActionConfigItem.class);
		
//		object.setActionCode(ett.getActionCode());
//		object.setActionName(ett.getActionName());
//		object.setExtraForm(ett.getExtraForm());
//		object.setFormScript(ett.getFormScript());
//		object.setSampleData(ett.getSampleData());
//		object.setInsideProcess(ett.getInsideProcess());
//		object.setSyncType(ett.getSyncType());
//		object.setPending(ett.getPending());
//		object.setNotificationType(ett.getNotificationType());
//		object.setCreateDocument(ett.getCreateDocument());
//		object.setDocumentName(ett.getDocumentName());
//		object.setDocumentScript(ett.getDocumentScript());
//		object.setDocumentCode(ett.getDocumentCode());
//		object.setSendDocument(ett.getSendDocument());
		
		return object;
		
	}
}
