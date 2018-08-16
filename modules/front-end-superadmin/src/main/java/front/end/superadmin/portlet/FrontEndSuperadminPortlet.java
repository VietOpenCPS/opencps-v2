package front.end.superadmin.portlet;

import java.util.List;

import javax.portlet.Portlet;
import javax.portlet.PortletException;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;
import javax.portlet.ResourceURL;
import javax.servlet.http.HttpServletRequest;

import org.opencps.dossiermgt.model.ActionConfig;
import org.opencps.dossiermgt.model.MenuConfig;
import org.opencps.dossiermgt.model.StepConfig;
import org.opencps.dossiermgt.service.ActionConfigLocalServiceUtil;
import org.opencps.dossiermgt.service.MenuConfigLocalServiceUtil;
import org.opencps.dossiermgt.service.StepConfigLocalServiceUtil;
import org.osgi.service.component.annotations.Component;

import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.util.bridges.freemarker.FreeMarkerPortlet;

import front.end.superadmin.constants.FrontEndSuperadminPortletKeys;

/**
 * @author binhth
 */
@Component(immediate = true, property = { "com.liferay.portlet.css-class-wrapper=portlet-freemarker",
		"com.liferay.portlet.display-category=category.superadmin", "com.liferay.portlet.instanceable=false",
		"javax.portlet.display-name=front-end-superadmin Portlet", "javax.portlet.init-param.template-path=/",
		"javax.portlet.init-param.view-template=/templates/view.ftl",
		"javax.portlet.name=" + FrontEndSuperadminPortletKeys.FrontEndSuperadmin,
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=power-user,user" }, service = Portlet.class)
public class FrontEndSuperadminPortlet extends FreeMarkerPortlet {

	public void serveResource(ResourceRequest resourceRequest, ResourceResponse resourceResponse)
			throws PortletException {
		try {

			String resourceID = resourceRequest.getResourceID();

			ThemeDisplay themeDisplay = (ThemeDisplay) resourceRequest.getAttribute(WebKeys.THEME_DISPLAY);

			HttpServletRequest request = PortalUtil.getHttpServletRequest(resourceRequest);
			HttpServletRequest requestOrg = PortalUtil.getOriginalServletRequest(request);
			
			if (resourceID.equals("renderURLInit")) {

				User user = themeDisplay.getUser();

				JSONObject object = JSONFactoryUtil.createJSONObject();

				object.put("groupId", themeDisplay.getScopeGroupId());
				
				JSONObject userObject = JSONFactoryUtil.createJSONObject();

				userObject.put("userName", user.getFullName());
				userObject.put("userEmail", user.getEmailAddress());
				userObject.put("userId", user.getUserId());
				userObject.put("defaultUser", user.getDefaultUser());
				object.put("user", userObject);


				ResourceURL myReourceURL = resourceResponse.createResourceURL();
				myReourceURL.setResourceID("getActionConfigs");
				
				object.put("getActionConfigs", myReourceURL);
				
				myReourceURL = resourceResponse.createResourceURL();
				myReourceURL.setResourceID("getMenuConfigs");
				
				object.put("getMenuConfigs", myReourceURL);
				
				myReourceURL = resourceResponse.createResourceURL();
				myReourceURL.setResourceID("getStepConfigs");
				
				object.put("getStepConfigs", myReourceURL);
				
				object.put("restActionConfigs", "/o/rest/v2_1/actionconfigs");
				object.put("restMenuConfigs", "/o/rest/v2_1/menuconfigs");
				object.put("restStepConfigs", "/o/rest/v2_1/stepconfigs");
				
				
				writeJSON(resourceRequest, resourceResponse, object);

			} else if (resourceID.equals("getActionConfigs")) {
				
				List<ActionConfig> actionConfigs = ActionConfigLocalServiceUtil.getByGroupId(themeDisplay.getScopeGroupId());
				JSONObject object = JSONFactoryUtil.createJSONObject();
				
				object.put("total", actionConfigs.size());
				object.put("data", JSONFactoryUtil.createJSONArray(JSONFactoryUtil.looseSerialize(actionConfigs)));
				
				writeJSON(resourceRequest, resourceResponse, object);
				
			} else if (resourceID.equals("getMenuConfigs")) {
				
				List<MenuConfig> actionConfigs = MenuConfigLocalServiceUtil.getByGroupId(themeDisplay.getScopeGroupId());
				JSONObject object = JSONFactoryUtil.createJSONObject();
				
				object.put("total", actionConfigs.size());
				object.put("data", JSONFactoryUtil.createJSONArray(JSONFactoryUtil.looseSerialize(actionConfigs)));
				
				writeJSON(resourceRequest, resourceResponse, object);
				
			} else if (resourceID.equals("getStepConfigs")) {
				
				List<StepConfig> actionConfigs = StepConfigLocalServiceUtil.getStepByGroupId(themeDisplay.getScopeGroupId());
				JSONObject object = JSONFactoryUtil.createJSONObject();
				
				object.put("total", actionConfigs.size());
				object.put("data", JSONFactoryUtil.createJSONArray(JSONFactoryUtil.looseSerialize(actionConfigs)));
				
				writeJSON(resourceRequest, resourceResponse, object);
				
			} else {

				super.serveResource(resourceRequest, resourceResponse);

			}
		} catch (Exception e) {

			throw new PortletException((Throwable) e);

		}
	}
}