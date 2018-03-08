package frontend.web.dossier.bvh.portlet;

import frontend.web.dossier.bvh.constants.FrontendWebDossierBvhPortletKeys;

import com.liferay.util.bridges.freemarker.FreeMarkerPortlet;

import javax.portlet.Portlet;

import org.osgi.service.component.annotations.Component;

/**
 * @author GIAHUY
 */
@Component(
	immediate = true,
	property = {
		"com.liferay.portlet.display-category=category.sample",
		"com.liferay.portlet.header-portlet-css=/css/main.css",
		"com.liferay.portlet.footer-portlet-javascript=/js/main.js",
		"com.liferay.portlet.instanceable=false",
		"javax.portlet.display-name=frontend-web-dossier-bvh Portlet",
		"javax.portlet.init-param.template-path=/",
		"javax.portlet.init-param.view-template=/templates/view.ftl",
		"javax.portlet.name=" + FrontendWebDossierBvhPortletKeys.FrontendWebDossierBvh,
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=power-user,user"
	},
	service = Portlet.class
)
public class FrontendWebDossierBvhPortlet extends FreeMarkerPortlet {
	
}