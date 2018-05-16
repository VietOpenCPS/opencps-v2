package frontend.web.statistics.portlet;

import frontend.web.statistics.constants.FrontendWebStatisticsPortletKeys;

import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.portlet.LiferayWindowState;
import com.liferay.portal.kernel.portlet.PortletURLFactoryUtil;
import com.liferay.portal.kernel.theme.PortletDisplay;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.util.bridges.freemarker.FreeMarkerPortlet;

import java.io.IOException;

import javax.portlet.Portlet;
import javax.portlet.PortletException;
import javax.portlet.PortletMode;
import javax.portlet.PortletRequest;
import javax.portlet.PortletURL;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.osgi.service.component.annotations.Component;

/**
 * @author dtson
 */
@Component(
	immediate = true,
	property = {
		"com.liferay.portlet.css-class-wrapper=portlet-freemarker",
		"com.liferay.portlet.display-category=category.statistics",
		"com.liferay.portlet.header-portlet-css=/css/style.css",
		"com.liferay.portlet.instanceable=true",
		"javax.portlet.display-name=frontend-web-statistics Portlet",
		"javax.portlet.init-param.template-path=/",
		"javax.portlet.init-param.view-template=/templates/statisticsView.ftl",
		"javax.portlet.name=" + FrontendWebStatisticsPortletKeys.FrontendWebStatistics,
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=power-user,user"
	},
	service = Portlet.class
)
public class FrontendWebStatisticsPortlet extends FreeMarkerPortlet {
	
	public void render(RenderRequest renderRequest, RenderResponse renderResponse)
			throws IOException, PortletException {
		ThemeDisplay themeDisplay = (ThemeDisplay) renderRequest.getAttribute(WebKeys.THEME_DISPLAY);
		PortletDisplay portletDisplay = themeDisplay.getPortletDisplay();
		String portletId = portletDisplay.getId();
//		HttpServletRequest httpRequest = PortalUtil.getHttpServletRequest(renderRequest);

		renderRequest.setAttribute("api", generateApiJsonObject(themeDisplay));
		JSONObject urlObject = JSONFactoryUtil.createJSONObject();
		
		String govAgencyCode =
				ParamUtil.getString(renderRequest, "govAgencyCode");
//		// url
//		PortletURL resultmainlistURL = PortletURLFactoryUtil.create(
//			renderRequest, portletId, themeDisplay.getPlid(),
//			PortletRequest.RENDER_PHASE);
//	
//		resultmainlistURL.setPortletMode(PortletMode.VIEW);
//		resultmainlistURL.setWindowState(LiferayWindowState.EXCLUSIVE);
//		resultmainlistURL.setParameter(
//			"mvcPath", "/templates/evaluationMainList.ftl");
//	
//		urlObject.put("evaluationMainList", resultmainlistURL.toString());
		
		renderRequest.setAttribute("ajax", urlObject);
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