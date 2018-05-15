package frontend.web.resultmgt.portlet;

import frontend.web.resultmgt.constants.FrontendWebResultmgtPortletKeys;

import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.util.bridges.freemarker.FreeMarkerPortlet;

import java.io.IOException;

import javax.portlet.Portlet;
import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.servlet.http.HttpServletRequest;

import org.osgi.service.component.annotations.Component;

/**
 * @author dtson
 */
@Component(immediate = true, property = { "com.liferay.portlet.css-class-wrapper=portlet-freemarker",
		"com.liferay.portlet.display-category=category.opencps.resultmgt",
		"com.liferay.portlet.header-portlet-css=/css/style.css", "com.liferay.portlet.instanceable=true",
		"javax.portlet.display-name=frontend-web-resultmgt Portlet", "javax.portlet.init-param.template-path=/",
		"javax.portlet.init-param.view-template=/templates/result_main.ftl",
		"javax.portlet.name=" + FrontendWebResultmgtPortletKeys.FrontendWebResultmgt,
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=power-user,user" }, service = Portlet.class)
public class FrontendWebResultmgtPortlet extends FreeMarkerPortlet {
	@Override
	public void render(RenderRequest renderRequest, RenderResponse renderResponse)
			throws IOException, PortletException {
		ThemeDisplay themeDisplay = (ThemeDisplay) renderRequest.getAttribute(WebKeys.THEME_DISPLAY);
//		HttpServletRequest httpRequest = PortalUtil.getHttpServletRequest(renderRequest);

		renderRequest.setAttribute("api", generateApiJsonObject(themeDisplay));
		
		String govAgencyCode =
				ParamUtil.getString(renderRequest, "govAgencyCode");
		
		renderRequest.setAttribute("govAgencyCode", govAgencyCode);
		super.render(renderRequest, renderResponse);
	}

	private JSONObject generateApiJsonObject(ThemeDisplay themeDisplay) {

		JSONObject apiObject = JSONFactoryUtil.createJSONObject();

		apiObject.put("server", themeDisplay.getPortalURL() + "/o/rest/v2");
		apiObject.put("portletNamespace", themeDisplay.getPortletDisplay().getNamespace());

		return apiObject;
	}
}
