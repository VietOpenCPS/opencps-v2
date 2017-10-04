
package org.opencps.frontend.web.admin.portlet;

import java.io.IOException;
import java.util.Collections;

import javax.portlet.Portlet;
import javax.portlet.PortletException;
import javax.portlet.PortletMode;
import javax.portlet.PortletRequest;
import javax.portlet.PortletURL;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.opencps.dossiermgt.model.ServiceInfo;
import org.opencps.dossiermgt.service.ServiceInfoLocalServiceUtil;
import org.opencps.frontend.web.admin.constants.AdminPortletKeys;
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
 * @author huymq
 */
@Component(immediate = true, property = {
	"com.liferay.portlet.display-category=category.opencps.admin",
	"com.liferay.portlet.instanceable=true",
	"javax.portlet.display-name=frontend-web-admin Portlet",
	"javax.portlet.name=" + AdminPortletKeys.ADMIN_PORTLET,
	"javax.portlet.init-param.template-path=/",
	"javax.portlet.init-param.view-template=/templates/admin.ftl",
	"javax.portlet.resource-bundle=content.Language",
	"javax.portlet.security-role-ref=power-user,user"
}, service = Portlet.class)
public class AdminPortlet extends FreeMarkerPortlet {

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
		PortletURL serviceInfoListURL = PortletURLFactoryUtil.create(
			renderRequest, portletId, themeDisplay.getPlid(),
			PortletRequest.RENDER_PHASE);
		serviceInfoListURL.setPortletMode(PortletMode.VIEW);
		serviceInfoListURL.setWindowState(LiferayWindowState.EXCLUSIVE);
		serviceInfoListURL.setParameter(
			"mvcPath", "/templates/serviceinfo.ftl");

		PortletURL serviceInfoFormURL = PortletURLFactoryUtil.create(
			renderRequest, portletId, themeDisplay.getPlid(),
			PortletRequest.RENDER_PHASE);
		serviceInfoFormURL.setPortletMode(PortletMode.VIEW);
		serviceInfoFormURL.setWindowState(LiferayWindowState.EXCLUSIVE);
		serviceInfoFormURL.setParameter(
			"mvcPath", "/templates/serviceinfo_form.ftl");

		PortletURL serviceFileTemplateURL = PortletURLFactoryUtil.create(
			renderRequest, portletId, themeDisplay.getPlid(),
			PortletRequest.RENDER_PHASE);
		serviceFileTemplateURL.setPortletMode(PortletMode.VIEW);
		serviceFileTemplateURL.setWindowState(LiferayWindowState.EXCLUSIVE);
		serviceFileTemplateURL.setParameter(
			"mvcPath", "/templates/serviceinfo_filetemplate.ftl");

		PortletURL serviceFileTemplateFormURL = PortletURLFactoryUtil.create(
			renderRequest, portletId, themeDisplay.getPlid(),
			PortletRequest.RENDER_PHASE);
		serviceFileTemplateFormURL.setPortletMode(PortletMode.VIEW);
		serviceFileTemplateFormURL.setWindowState(LiferayWindowState.EXCLUSIVE);
		serviceFileTemplateFormURL.setParameter(
			"mvcPath", "/templates/serviceinfo_filetemplate_form.ftl");

		PortletURL dossiertemplateURL = PortletURLFactoryUtil.create(
			renderRequest, portletId, themeDisplay.getPlid(),
			PortletRequest.RENDER_PHASE);
		dossiertemplateURL.setPortletMode(PortletMode.VIEW);
		dossiertemplateURL.setWindowState(LiferayWindowState.EXCLUSIVE);
		dossiertemplateURL.setParameter(
			"mvcPath", "/templates/dossiertemplate.ftl");

		PortletURL dossiertemplatePartURL = PortletURLFactoryUtil.create(
			renderRequest, portletId, themeDisplay.getPlid(),
			PortletRequest.RENDER_PHASE);
		dossiertemplatePartURL.setPortletMode(PortletMode.VIEW);
		dossiertemplatePartURL.setWindowState(LiferayWindowState.EXCLUSIVE);
		dossiertemplatePartURL.setParameter(
			"mvcPath", "/templates/dossiertemplate_path.ftl");

		PortletURL dossiertemplatePartFormURL = PortletURLFactoryUtil.create(
			renderRequest, portletId, themeDisplay.getPlid(),
			PortletRequest.RENDER_PHASE);
		dossiertemplatePartFormURL.setPortletMode(PortletMode.VIEW);
		dossiertemplatePartFormURL.setWindowState(LiferayWindowState.EXCLUSIVE);
		dossiertemplatePartFormURL.setParameter(
			"mvcPath", "/templates/dossiertemplate_part_form.ftl");

		PortletURL serviceInfoDetailURL = PortletURLFactoryUtil.create(
			renderRequest, portletId, themeDisplay.getPlid(),
			PortletRequest.RENDER_PHASE);
		serviceInfoDetailURL.setPortletMode(PortletMode.VIEW);
		serviceInfoDetailURL.setWindowState(LiferayWindowState.EXCLUSIVE);
		serviceInfoDetailURL.setParameter(
			"mvcPath", "/templates/serviceinfo_detail.ftl");

		PortletURL serviceProcesslURL = PortletURLFactoryUtil.create(
			renderRequest, portletId, themeDisplay.getPlid(),
			PortletRequest.RENDER_PHASE);
		serviceProcesslURL.setPortletMode(PortletMode.VIEW);
		serviceProcesslURL.setWindowState(LiferayWindowState.EXCLUSIVE);
		serviceProcesslURL.setParameter(
			"mvcPath", "/templates/serviceprocess.ftl");

		urlObject.put("serviceinfo_list", serviceInfoListURL.toString());
		urlObject.put("serviceinfo_form", serviceInfoFormURL.toString());
		urlObject.put(
			"serviceinfo_filetemplate", serviceFileTemplateURL.toString());
		urlObject.put(
			"serviceinfo_filetemplate_form",
			serviceFileTemplateFormURL.toString());
		urlObject.put("dossiertemplate", dossiertemplateURL.toString());
		urlObject.put(
			"dossiertemplate_part", dossiertemplatePartURL.toString());
		urlObject.put(
			"dossiertemplate_part_form", dossiertemplatePartFormURL.toString());
		urlObject.put("serviceinfo_detail", serviceInfoDetailURL.toString());
		urlObject.put("serviceprocess", serviceProcesslURL.toString());

		// set object edit
		long serviceInfoId = ParamUtil.getLong(renderRequest, "serviceInfoId");
		if (serviceInfoId > 0) {
			try {
				ServiceInfo serviceInfo =
					ServiceInfoLocalServiceUtil.getServiceInfo(serviceInfoId);

				renderRequest.setAttribute("SERVICE_INFO", serviceInfo);
			}
			catch (Exception e) {

			}
		}

		// api
		apiObject.put("server", themeDisplay.getPortalURL() + "/o/rest/v2");
		apiObject.put(
			"portletNamespace",
			themeDisplay.getPortletDisplay().getNamespace());

		// set varible
		renderRequest.setAttribute("ajax", urlObject);
		renderRequest.setAttribute("api", apiObject);
		renderRequest.setAttribute("administrations", Collections.EMPTY_LIST);
		renderRequest.setAttribute("domains", Collections.EMPTY_LIST);
		renderRequest.setAttribute(
			"status", Collections.EMPTY_LIST);
		renderRequest.setAttribute("levels", Collections.EMPTY_LIST);

		super.render(renderRequest, renderResponse);
	}
}
