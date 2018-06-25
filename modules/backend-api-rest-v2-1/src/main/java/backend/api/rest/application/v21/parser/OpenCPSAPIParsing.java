package backend.api.rest.application.v21.parser;

import java.util.ArrayList;
import java.util.List;

import org.opencps.dossiermgt.model.ActionConfig;
import org.opencps.dossiermgt.model.MenuConfig;
import org.opencps.dossiermgt.model.StepConfig;

import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;

import io.swagger.model.ActionConfigItem;
import io.swagger.model.MenuConfigItem;
import io.swagger.model.MenuConfigItemResult;
import io.swagger.model.MenuConfigStepsItem;
import io.swagger.model.StepConfigItem;

public class OpenCPSAPIParsing {
	
//	private OpenCPSAPIParsing() {
//		
//	}
	
//	OpenCPSAPIParsing.getLocatorResponseToDealershipResponseConverter(locale).convert(ActionConfig);
//
//	public static Converter<ActionConfig, ActionConfigItem> getLocatorResponseToDealershipResponseConverter(
//			   String locale) {
//			  return (source) -> {
//			   DealershipResponse dealershipResponse = new DealershipResponse();
//
//			   if (source.getLocatorResult().get(0) != null) {
//
//			    Vendor firstVendor = source.getLocatorResult().get(0).getVendor();
//
//			    Contact contact = getContactMain(firstVendor.getContact());
//
//			    Phone phone = getPhoneMain(contact.getPhone());
//
//			    Address address = getAddress(contact);
//
//			    List<DealerCode> dealerCodes = getDealerCodeList(source.getLocatorResult().get(0), locale);
//
//			    dealershipResponse.setBacCode(String.valueOf(firstVendor.getId()));
//			    dealershipResponse.setDealerCity(address.getCity());
//			    dealershipResponse.setDealerCode(firstVendor.getVendorCode());
//			    dealershipResponse.setDealerPhone(phone.getNumber());
//			    dealershipResponse.setDealerState(address.getProvince());
//			    dealershipResponse.setDealerStreet1(getAddressStreet(address).getValue());
//			    dealershipResponse.setDealerType(firstVendor.getType().value());
//			    dealershipResponse.setDealerZip(address.getPostalCode());
//			    dealershipResponse.setName(firstVendor.getPrimaryName());
//			    dealershipResponse.setDealerCodes(dealerCodes);
//			   }
//			   return dealershipResponse;
//			  };
//	}
	public ActionConfigItem getModel(ActionConfig ett) {

		ActionConfigItem object = JSONFactoryUtil.looseDeserialize(JSONFactoryUtil.looseSerialize(ett),
				ActionConfigItem.class);

		return object;

	}

	public StepConfigItem getModel(StepConfig ett) {

		StepConfigItem object = JSONFactoryUtil.looseDeserialize(JSONFactoryUtil.looseSerialize(ett),
				StepConfigItem.class);

		return object;

	}

	public MenuConfigItem getModel(MenuConfig ett) {

		MenuConfigItem object = JSONFactoryUtil.looseDeserialize(JSONFactoryUtil.looseSerialize(ett),
				MenuConfigItem.class);

		return object;

	}

	public MenuConfigItemResult getModel(JSONObject ett) {

		MenuConfigItemResult object = new MenuConfigItemResult();

		JSONArray resultData = ett.getJSONObject("hits").getJSONArray("hits");

		List<MenuConfigItem> data = new ArrayList<>();
		
		JSONArray arrayStep = JSONFactoryUtil.createJSONArray();
		
		for (int i = 0; i < resultData.length(); i++) {

			JSONObject current = resultData.getJSONObject(i).getJSONObject("_source");
			
			if (current.getString("entryClassName").equalsIgnoreCase(MenuConfig.class.getName()) 
					&& current.getInt("menuType") != 0) {
				MenuConfigItem objectItem = JSONFactoryUtil.looseDeserialize(current.toJSONString(), MenuConfigItem.class);
				data.add(objectItem);
			} else if (current.getString("entryClassName").equalsIgnoreCase(StepConfig.class.getName())) {
				arrayStep.put(current);
			}
			
		}
		
		List<MenuConfigStepsItem> menuConfigStepsItems = new ArrayList<>();
		MenuConfigStepsItem menuConfigStepsItem = new MenuConfigStepsItem();
		
		for (MenuConfigItem menuConfigItem : data) {
			
			menuConfigStepsItems = menuConfigItem.getSteps();
			
			for (int i = 0; i < arrayStep.length(); i++) {

				JSONObject current = arrayStep.getJSONObject(i);
				if (menuConfigItem.getMenuGroup().trim().equals(current.getString("menuGroup"))
						&& current.getInt("stepType") == 1) {
					
					menuConfigStepsItem = new MenuConfigStepsItem();

					menuConfigStepsItem.setStepCode(current.getString("stepCode"));
					menuConfigStepsItem.setStepName(current.getString("stepName"));
					menuConfigStepsItem.setMenuStepName(current.getString("menuStepName"));
					menuConfigStepsItem.setButtonConfig(current.getString("buttonConfig"));
					
					menuConfigStepsItems.add(menuConfigStepsItem);
					
				}
				
			}
			menuConfigItem.setSteps(menuConfigStepsItems);
		}
		
		object.setData(data);
		object.setTotal(Long.valueOf(data.size()));
		return object;

	}
}
