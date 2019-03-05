package backend.api.rest.application.v21.parser;

import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

import java.util.ArrayList;
import java.util.List;

import org.opencps.dossiermgt.model.DocumentType;
import org.opencps.rest.application.model.DocumentTypeModel;

public class DocumentTypeParser {

	//private static Log _log = LogFactoryUtil.getLog(DocumentTypeParser.class);
	public static List<DocumentTypeModel> mappingDocumentResultModel(List<DocumentType> docList) {
//		Gson gson = new Gson();
		if (docList != null && docList.size() > 0) {
			List<DocumentTypeModel> docModelList = new ArrayList<DocumentTypeModel>();
			for (DocumentType doc : docList) {
				String strDoc = JSONFactoryUtil.looseSerialize(doc);
				//_log.info("strDoc: "+strDoc);
				DocumentTypeModel model = JSONFactoryUtil.looseDeserialize(strDoc, DocumentTypeModel.class);

				docModelList.add(model);
			}
			return docModelList;
		} else{
			return null;
		}

	}

	public static DocumentTypeModel mappingDocumentTypeModel(DocumentType doc) {
		
//		Gson gson = new Gson();
		String strDoc = JSONFactoryUtil.looseSerialize(doc);
		//_log.info("strDoc: "+strDoc);
		DocumentTypeModel object = JSONFactoryUtil.looseDeserialize(strDoc, DocumentTypeModel.class);
		
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
