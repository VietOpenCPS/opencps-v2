package backend.api.rest.application.v21.parser;

import org.opencps.dossiermgt.model.ActionConfig;

import com.liferay.portal.kernel.json.JSONFactoryUtil;

import io.swagger.model.ActionConfigItem;

public class ActionConfigParsing {

	public ActionConfigItem getModel(ActionConfig ett) {
		
//		Gson gson = new Gson();
		
		ActionConfigItem object = JSONFactoryUtil.looseDeserialize(JSONFactoryUtil.looseSerialize(ett), ActionConfigItem.class);
		
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
