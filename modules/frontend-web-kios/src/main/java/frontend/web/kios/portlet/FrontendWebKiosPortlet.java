package frontend.web.kios.portlet;

import javax.portlet.Portlet;
import javax.portlet.PortletException;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;

//
import org.osgi.service.component.annotations.Component;

import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.util.bridges.freemarker.FreeMarkerPortlet;

import frontend.web.kios.constants.FrontendWebKiosPortletKeys;

/**
 * @author
 */
@Component(
	immediate = true,
	property = {
		"com.liferay.portlet.css-class-wrapper=portlet-freemarker",
		"com.liferay.portlet.display-category=category.kios",
		"com.liferay.portlet.instanceable=false",
		"javax.portlet.display-name=frontend-web-kios Portlet",
		"javax.portlet.init-param.template-path=/",
		"javax.portlet.init-param.view-template=/templates/view.ftl",
		"javax.portlet.name=" + FrontendWebKiosPortletKeys.FrontendWebKios,
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=power-user,user"
	},
	service = Portlet.class
)

public class FrontendWebKiosPortlet extends FreeMarkerPortlet {
	private static final Log _log =
			LogFactoryUtil.getLog(FrontendWebKiosPortlet.class);
	public void serveResource(ResourceRequest resourceRequest, ResourceResponse resourceResponse)
			throws PortletException {
		try {

			String resourceID = resourceRequest.getResourceID();

			ThemeDisplay themeDisplay = (ThemeDisplay) resourceRequest.getAttribute(WebKeys.THEME_DISPLAY);

//			HttpServletRequest request = PortalUtil.getHttpServletRequest(resourceRequest);
//			HttpServletRequest requestOrg = PortalUtil.getOriginalServletRequest(request);

			if ("renderURLInit".equals(resourceID)) {

				User user = themeDisplay.getUser();

				JSONObject object = JSONFactoryUtil.createJSONObject();

				object.put("groupId", themeDisplay.getScopeGroupId());

				JSONObject userObject = JSONFactoryUtil.createJSONObject();

				userObject.put("userName", user.getFullName());
				userObject.put("userEmail", user.getEmailAddress());
				userObject.put("userId", user.getUserId());
				userObject.put("defaultUser", user.getDefaultUser());
				object.put("user", userObject);

				writeJSON(resourceRequest, resourceResponse, object);

			} else {

				super.serveResource(resourceRequest, resourceResponse);

			}
		} catch (Exception e) {
			_log.error(e);
			throw new PortletException((Throwable) e);

		}
	}
}
