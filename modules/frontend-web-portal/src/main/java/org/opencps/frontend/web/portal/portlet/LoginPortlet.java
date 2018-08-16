/**
 * 
 */
package org.opencps.frontend.web.portal.portlet;

import java.io.IOException;

import javax.portlet.Portlet;
import javax.portlet.PortletException;
import javax.portlet.PortletMode;
import javax.portlet.PortletRequest;
import javax.portlet.PortletURL;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.opencps.frontend.web.portal.constants.FrontendWebPortalPortletKeys;
import org.osgi.service.component.annotations.Component;

import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.portlet.LiferayWindowState;
import com.liferay.portal.kernel.portlet.PortletURLFactoryUtil;
import com.liferay.portal.kernel.theme.PortletDisplay;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.util.bridges.freemarker.FreeMarkerPortlet;

/**
 * @author phucnv
 * @date Sep 11, 2017
 *
 */
@Component(immediate = true, property = {
	"com.liferay.portlet.css-class-wrapper=portlet-freemarker",
	"com.liferay.portlet.display-category=category.opencps_v2.portal",
	"com.liferay.portlet.header-portlet-css=/css/main.css",
	"com.liferay.portlet.instanceable=false",
	"javax.portlet.display-name=Login Portlet",
	"javax.portlet.init-param.template-path=/",
	"javax.portlet.init-param.view-template=/templates/applicant/login.ftl",
	"javax.portlet.name=" + FrontendWebPortalPortletKeys.LOGIN_PORTLET_NAME,
	"javax.portlet.resource-bundle=content.Language",
	"javax.portlet.security-role-ref=power-user,user"
}, service = Portlet.class)
public class LoginPortlet extends FreeMarkerPortlet {

	@Override
	public void render(
		RenderRequest renderRequest, RenderResponse renderResponse)
		throws IOException, PortletException {
		JSONObject urlObject = JSONFactoryUtil.createJSONObject();
		JSONObject apiObject = JSONFactoryUtil.createJSONObject();
		ThemeDisplay themeDisplay =
			(ThemeDisplay) renderRequest.getAttribute(WebKeys.THEME_DISPLAY);
		
		renderRequest.setAttribute("layoutfriendurl", themeDisplay.getScopeGroup().getFriendlyURL());
		long userId = themeDisplay.getUser().getUserId();

		// api
		apiObject.put("server", themeDisplay.getPortalURL() + "/o/rest/v2");
		apiObject.put(
			"portletNamespace",
			themeDisplay.getPortletDisplay().getNamespace());
		// set varible
		renderRequest.setAttribute("ajax", urlObject);
		renderRequest.setAttribute("api", apiObject);
		renderRequest.setAttribute("userId", userId);
		super.render(renderRequest, renderResponse);

	}
}
