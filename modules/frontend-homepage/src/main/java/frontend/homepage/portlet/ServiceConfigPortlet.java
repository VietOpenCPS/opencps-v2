package frontend.homepage.portlet;

import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.util.bridges.freemarker.FreeMarkerPortlet;

import frontend.homepage.constants.FrontendHomepagePortletKeys;

import java.io.IOException;

import javax.portlet.Portlet;
import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.osgi.service.component.annotations.Component;

/**
 * @author admin
 */
@Component(
	immediate = true,
	property = {
		"com.liferay.portlet.display-category=Homepage Service",
		"com.liferay.portlet.instanceable=true",
		"javax.portlet.display-name=Dịch vụ công trực tuyến mức độ 3 và 4",
		"javax.portlet.init-param.template-path=/",
		"javax.portlet.init-param.view-template=/templates/serviceConfig.ftl",
		"javax.portlet.name=" + FrontendHomepagePortletKeys.ServiceConfigPortlet,
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=power-user,user"
	},
	service = Portlet.class
)
public class ServiceConfigPortlet extends FreeMarkerPortlet {
	@Override
	public void render(RenderRequest renderRequest, RenderResponse renderResponse)
			throws IOException, PortletException {
		super.render(renderRequest, renderResponse);
	}
}