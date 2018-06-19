package backend.api.rest.application.v21.parser;

import org.opencps.dossiermgt.model.ActionConfig;
import org.opencps.dossiermgt.model.MenuConfig;
import org.opencps.dossiermgt.model.StepConfig;

import com.liferay.portal.kernel.json.JSONFactoryUtil;

import io.swagger.model.ActionConfigItem;
import io.swagger.model.MenuConfigItem;
import io.swagger.model.StepConfigItem;

public class OpenCPSAPIParsing {

	public ActionConfigItem getModel(ActionConfig ett) {
		
		ActionConfigItem object = JSONFactoryUtil.looseDeserialize(JSONFactoryUtil.looseSerialize(ett), ActionConfigItem.class);
		
		return object;
		
	}
	
	public StepConfigItem getModel(StepConfig ett) {
		
		StepConfigItem object = JSONFactoryUtil.looseDeserialize(JSONFactoryUtil.looseSerialize(ett), StepConfigItem.class);
		
		return object;
		
	}
	
	public MenuConfigItem getModel(MenuConfig ett) {
		
		MenuConfigItem object = JSONFactoryUtil.looseDeserialize(JSONFactoryUtil.looseSerialize(ett), MenuConfigItem.class);
		
		return object;
		
	}
}
