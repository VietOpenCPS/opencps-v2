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
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.util.bridges.freemarker.FreeMarkerPortlet;

/**
 * @author phucnv
 * @date Oct 30, 2017
 */
@Component(immediate = true, property = {
	"com.liferay.portlet.css-class-wrapper=portlet-freemarker",
	"com.liferay.portlet.display-category=category.opencps_v2.portal",
	"com.liferay.portlet.header-portlet-css=/css/main.css",
	"com.liferay.portlet.instanceable=true",
	"javax.portlet.display-name=Lookup Dossier Result Portlet",
	"javax.portlet.init-param.template-path=/",
	"javax.portlet.init-param.view-template=/templates/lookup_dossier/lookupdossier_main.ftl",
	"javax.portlet.name=" + FrontendWebPortalPortletKeys.LOOKUP_DOSSIER_RESULT,
	"javax.portlet.resource-bundle=content.Language",
	"javax.portlet.security-role-ref=power-user,user"
}, service = Portlet.class)
public class LookupDossierResult extends FreeMarkerPortlet {

	@Override
	public void render(
		RenderRequest renderRequest, RenderResponse renderResponse)
		throws IOException, PortletException {

		ThemeDisplay themeDisplay =
			(ThemeDisplay) renderRequest.getAttribute(WebKeys.THEME_DISPLAY);
		PortletDisplay portletDisplay = themeDisplay.getPortletDisplay();
		String portletId = portletDisplay.getId();

		JSONObject urlObject = JSONFactoryUtil.createJSONObject();
		JSONObject apiObject = JSONFactoryUtil.createJSONObject();

		// url
		PortletURL dossierInfoURL = PortletURLFactoryUtil.create(
			renderRequest, portletId, themeDisplay.getPlid(),
			PortletRequest.RENDER_PHASE);

		dossierInfoURL.setPortletMode(PortletMode.VIEW);
		dossierInfoURL.setWindowState(LiferayWindowState.EXCLUSIVE);
		dossierInfoURL.setParameter(
			"mvcPath", "/templates/lookup_dossier/dossierinfo.ftl");

		urlObject.put("dossierinfo", dossierInfoURL.toString());

		// api
		apiObject.put("server", themeDisplay.getPortalURL() + "/o/rest/v2");
		apiObject.put(
			"portletNamespace",
			themeDisplay.getPortletDisplay().getNamespace());
		
		// get variable request
		String keyword = ParamUtil.getString(renderRequest, "keyword");
		
		// set varible
		String secretKey = renderRequest.getParameter("secretKey");
		renderRequest.setAttribute("secretKey", secretKey);
		renderRequest.setAttribute("ajax", urlObject);
		renderRequest.setAttribute("api", apiObject);
		renderRequest.setAttribute("keyword", keyword);

		super.render(renderRequest, renderResponse);

	}
}
