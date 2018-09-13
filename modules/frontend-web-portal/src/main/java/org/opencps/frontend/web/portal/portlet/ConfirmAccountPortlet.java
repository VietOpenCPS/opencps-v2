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
 * @date Sep 14, 2017
 *
 */
@Component(immediate = true, property = {
	"com.liferay.portlet.css-class-wrapper=portlet-freemarker",
	"com.liferay.portlet.display-category=category.opencps_v2.portal",
	"com.liferay.portlet.header-portlet-css=/css/main.css",
	"com.liferay.portlet.instanceable=true",
	"javax.portlet.display-name=Confirm Account Portlet",
	"javax.portlet.init-param.template-path=/",
	"javax.portlet.init-param.view-template=/templates/applicant_bvh/confirm_account.ftl",
	"javax.portlet.name=" +
		FrontendWebPortalPortletKeys.CONFIRM_ACCOUNT_PORTLET_NAME,
	"javax.portlet.resource-bundle=content.Language",
	"javax.portlet.security-role-ref=power-user,user"
}, service = Portlet.class)
public class ConfirmAccountPortlet extends FreeMarkerPortlet {

	/*
	 * (non-Javadoc)
	 * @see
	 * com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet#render(javax.
	 * portlet.RenderRequest, javax.portlet.RenderResponse)
	 */
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
		PortletURL registerResultURL = PortletURLFactoryUtil.create(
			renderRequest, portletId, themeDisplay.getPlid(),
			PortletRequest.RENDER_PHASE);

		registerResultURL.setPortletMode(PortletMode.VIEW);
		registerResultURL.setWindowState(LiferayWindowState.EXCLUSIVE);
		registerResultURL.setParameter(
			"mvcPath", "/templates/applicant/register_result.ftl");

		urlObject.put("register_result", registerResultURL.toString());

		// api
		apiObject.put("server", themeDisplay.getPortalURL() + "/o/rest/v2");
		apiObject.put(
			"portletNamespace",
			themeDisplay.getPortletDisplay().getNamespace());

		// set varible
		renderRequest.setAttribute("ajax", urlObject);
		renderRequest.setAttribute("api", apiObject);

		super.render(renderRequest, renderResponse);

	}
}
