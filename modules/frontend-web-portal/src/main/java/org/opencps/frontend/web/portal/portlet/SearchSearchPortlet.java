/**
 * 
 */
package org.opencps.frontend.web.portal.portlet;

import javax.portlet.Portlet;

import org.opencps.frontend.web.portal.constants.FrontendWebPortalPortletKeys;
import org.osgi.service.component.annotations.Component;

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
	"javax.portlet.display-name=Service Search Portlet",
	"javax.portlet.init-param.template-path=/",
	"javax.portlet.init-param.view-template=/templates/createPorlet/search_serviceinfo.ftl",
	"javax.portlet.name=" +
		FrontendWebPortalPortletKeys.SERVICE_SEARCH_PORTLET_NAME,
	"javax.portlet.resource-bundle=content.Language",
	"javax.portlet.security-role-ref=power-user,user"
}, service = Portlet.class)
public class SearchSearchPortlet extends FreeMarkerPortlet {

	
}
