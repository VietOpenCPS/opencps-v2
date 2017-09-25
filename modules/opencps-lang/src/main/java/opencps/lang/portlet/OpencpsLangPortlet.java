package opencps.lang.portlet;

import opencps.lang.constants.OpencpsLangPortletKeys;

import java.io.IOException;
import java.io.PrintWriter;

import javax.portlet.GenericPortlet;
import javax.portlet.Portlet;
import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.osgi.service.component.annotations.Component;

/**
 * @author khoavu
 */
@Component(
	immediate = true,
	property = {
		"com.liferay.portlet.display-category=category.sample",
		"com.liferay.portlet.instanceable=true",
		"javax.portlet.display-name=opencps-lang Portlet",
		"javax.portlet.name=" + OpencpsLangPortletKeys.OpencpsLang,
		"javax.portlet.security-role-ref=power-user,user"
	},
	service = Portlet.class
)
public class OpencpsLangPortlet extends GenericPortlet {

	@Override
	protected void doView(
			RenderRequest renderRequest, RenderResponse renderResponse)
		throws IOException, PortletException {

		PrintWriter printWriter = renderResponse.getWriter();

		printWriter.print("opencps-lang Portlet - Hello World!");
	}

}