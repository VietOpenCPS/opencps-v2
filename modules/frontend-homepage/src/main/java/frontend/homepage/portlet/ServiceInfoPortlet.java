package frontend.homepage.portlet;

import java.io.IOException;

import javax.portlet.Portlet;
import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.osgi.service.component.annotations.Component;

import com.liferay.util.bridges.freemarker.FreeMarkerPortlet;

import frontend.homepage.constants.FrontendHomepagePortletKeys;

/**
 * @author admin
 */
@Component(
	immediate = true,
	property = {
		"com.liferay.portlet.display-category=Homepage Service",
		"com.liferay.portlet.instanceable=true",
		"javax.portlet.display-name=Dịch vụ công trực tuyến mức độ 1 và 2",
		"com.liferay.portlet.header-portlet-css=/css/serviceInfo.css",
		"javax.portlet.init-param.template-path=/",
		"javax.portlet.init-param.view-template=/templates/serviceInfo.ftl",
		"javax.portlet.name=" + FrontendHomepagePortletKeys.ServiceInfoPortlet,
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=power-user,user"
	},
	service = Portlet.class
)
public class ServiceInfoPortlet extends FreeMarkerPortlet {
	@Override
	public void render(RenderRequest renderRequest, RenderResponse renderResponse)
			throws IOException, PortletException {
		renderRequest.setAttribute("api", "http://103.21.148.29/o/rest/v2");
		super.render(renderRequest, renderResponse);
	}
}