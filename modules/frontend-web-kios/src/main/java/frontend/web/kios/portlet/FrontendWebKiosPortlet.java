package frontend.web.kios.portlet;

import frontend.web.kios.constants.FrontendWebKiosPortletKeys;

import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.portlet.LiferayWindowState;
import com.liferay.portal.kernel.portlet.PortletURLFactoryUtil;
import com.liferay.portal.kernel.theme.PortletDisplay;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.util.bridges.freemarker.FreeMarkerPortlet;
import java.io.IOException;

import javax.portlet.Portlet;
import javax.portlet.PortletException;
import javax.portlet.PortletMode;
import javax.portlet.PortletRequest;
import javax.portlet.PortletURL;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;
import javax.servlet.http.HttpServletRequest;
//
import org.osgi.service.component.annotations.Component;

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

				writeJSON(resourceRequest, resourceResponse, object);

			} else {

				super.serveResource(resourceRequest, resourceResponse);

			}
		} catch (Exception e) {

			throw new PortletException((Throwable) e);

		}
	}
}
