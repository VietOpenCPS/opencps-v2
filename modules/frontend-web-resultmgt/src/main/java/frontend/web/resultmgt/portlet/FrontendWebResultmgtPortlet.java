package frontend.web.resultmgt.portlet;

import frontend.web.resultmgt.constants.FrontendWebResultmgtPortletKeys;

import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
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

import org.opencps.dossiermgt.model.Dossier;
import org.opencps.dossiermgt.service.DossierLocalServiceUtil;
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
		PortletDisplay portletDisplay = themeDisplay.getPortletDisplay();
		String portletId = portletDisplay.getId();
		// HttpServletRequest httpRequest =
		// PortalUtil.getHttpServletRequest(renderRequest);

		String dossierId = ParamUtil.getString(renderRequest, "dossierId");
		try {
			Dossier dossier = DossierLocalServiceUtil.getDossier(Long.parseLong(dossierId));
			String dossierStr = JSONFactoryUtil.looseSerialize(dossier);
			JSONObject dossierObj = JSONFactoryUtil.createJSONObject(dossierStr);
			if (dossierObj != null) {
				renderRequest.setAttribute("dossier", dossierObj);
			}

		} catch (Exception e) {
			// TODO: handle exception
			_log.info(e.getMessage());
		}
		renderRequest.setAttribute("api", generateApiJsonObject(themeDisplay));
		JSONObject urlObject = JSONFactoryUtil.createJSONObject();

		String govAgencyCode = ParamUtil.getString(renderRequest, "govAgencyCode");
		// url
		PortletURL resultmainlistURL = PortletURLFactoryUtil.create(renderRequest, portletId, themeDisplay.getPlid(),
				PortletRequest.RENDER_PHASE);

		resultmainlistURL.setPortletMode(PortletMode.VIEW);
		resultmainlistURL.setWindowState(LiferayWindowState.EXCLUSIVE);
		resultmainlistURL.setParameter("mvcPath", "/templates/result_mainlist.ftl");

		urlObject.put("result_mainlist", resultmainlistURL.toString());

		PortletURL dossierDetailURL = PortletURLFactoryUtil.create(renderRequest, portletId, themeDisplay.getPlid(),
				PortletRequest.RENDER_PHASE);

		dossierDetailURL.setPortletMode(PortletMode.VIEW);
		dossierDetailURL.setWindowState(LiferayWindowState.EXCLUSIVE);
		dossierDetailURL.setParameter("mvcPath", "/templates/detail_dossier.ftl");

		urlObject.put("detail_dossier", dossierDetailURL.toString());

		PortletURL componentProfilesURL = PortletURLFactoryUtil.create(renderRequest, portletId, themeDisplay.getPlid(),
				PortletRequest.RENDER_PHASE);

		componentProfilesURL.setPortletMode(PortletMode.VIEW);
		componentProfilesURL.setWindowState(LiferayWindowState.EXCLUSIVE);
		componentProfilesURL.setParameter("mvcPath", "/templates/customer_dossier_component_profiles.ftl");

		urlObject.put("customer_dossier_component_profiles", componentProfilesURL.toString());
		String dossierPartNo = ParamUtil.getString(renderRequest, "dossierPartNo");

		renderRequest.setAttribute("ajax", urlObject);
		renderRequest.setAttribute("govAgencyCode", govAgencyCode);
		renderRequest.setAttribute("dossierId", dossierId);
		renderRequest.setAttribute("dossierPartNo", dossierPartNo);

		super.render(renderRequest, renderResponse);
	}

	private JSONObject generateApiJsonObject(ThemeDisplay themeDisplay) {

		JSONObject apiObject = JSONFactoryUtil.createJSONObject();

		apiObject.put("server", themeDisplay.getPortalURL() + "/o/rest/v2");
		apiObject.put("portletNamespace", themeDisplay.getPortletDisplay().getNamespace());

		return apiObject;
	}

	private static final Log _log = LogFactoryUtil.getLog(FrontendWebResultmgtPortlet.class);
}
