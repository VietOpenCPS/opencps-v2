package frontend.web.onegate.portlet;

import frontend.web.onegate.constants.FrontendWebOnegatePortletKeys;

import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.model.Resource;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.portlet.LiferayPortletMode;
import com.liferay.portal.kernel.portlet.LiferayWindowState;
import com.liferay.portal.kernel.portlet.PortletURLFactoryUtil;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.util.bridges.freemarker.FreeMarkerPortlet;

import java.io.IOException;

import javax.portlet.Portlet;
import javax.portlet.PortletException;
import javax.portlet.PortletRequest;
import javax.portlet.PortletURL;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;
import javax.portlet.ResourceURL;

import org.opencps.usermgt.model.Applicant;
import org.opencps.usermgt.service.ApplicantLocalServiceUtil;
import org.osgi.service.component.annotations.Component;

/**
 * @author hoanganh
 */
@Component(
	immediate = true,
	property = {
		"com.liferay.portlet.css-class-wrapper=portlet-freemarker",
		"com.liferay.portlet.display-category=category.onegate2",
		"com.liferay.portlet.header-portlet-css=/css/main.css",
		"com.liferay.portlet.instanceable=false",
		"javax.portlet.display-name=frontend-web-onegate Portlet",
		"javax.portlet.init-param.template-path=/",
		"javax.portlet.init-param.view-template=/templates/view.ftl",
		"javax.portlet.name=" + FrontendWebOnegatePortletKeys.FrontEndOnegateNpm,
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=power-user,user"
	},
	service = Portlet.class
)
public class FrontendWebOnegatePortlet extends FreeMarkerPortlet {
	@Override
	public void render(RenderRequest renderRequest, RenderResponse renderResponse)
			throws IOException, PortletException {
		// TODO Auto-generated method stub
		ThemeDisplay themeDisplay = (ThemeDisplay) renderRequest.getAttribute(WebKeys.THEME_DISPLAY);
		
		PortletURL resourceUrl = PortletURLFactoryUtil.create(renderRequest, PortalUtil.getPortletId(renderRequest),
				themeDisplay.getPlid(), PortletRequest.RESOURCE_PHASE);
		
		resourceUrl.setWindowState(LiferayWindowState.EXCLUSIVE);
		
		resourceUrl.setParameter("callResourceId", "renderURLInit");
		
		PortletURL renderUrl = PortletURLFactoryUtil.create(renderRequest, themeDisplay.getPortletDisplay().getId(),
				themeDisplay.getPlid(), PortletRequest.RENDER_PHASE);
		renderUrl.setWindowState(LiferayWindowState.NORMAL);
		renderUrl.setPortletMode(LiferayPortletMode.VIEW);
		renderUrl.setParameter("renderId", "renderApi");
		renderRequest.setAttribute("renderUrlByJavaAPI", renderUrl.toString());
		System.out.println("renderUrlByJavaAPI.render()================" + renderUrl.toString());
		
		long groupId = themeDisplay.getScopeGroupId();
		String portletNamespace = themeDisplay.getPortletDisplay().getNamespace();
		String renderId = ParamUtil.getString(renderRequest, "renderId");
		
		if(renderId.equals("renderApi")){
			User user = themeDisplay.getUser();
			Applicant applicant = ApplicantLocalServiceUtil.fetchByEmail(user.getEmailAddress());
			JSONObject api = JSONFactoryUtil.createJSONObject();
			
			api.put("serviceInfoApi", "/o/rest/v2/serviceinfos");
			api.put("serviceConfigApi", "/o/rest/v2/onegate/serviceconfigs/processes");
			api.put("regionApi", "/o/rest/v2/dictcollections");
			api.put("serviceOptionApi", "/o/rest/v2/serviceconfigs");
			api.put("dossierApi", "/o/rest/v2/dossiers");
			api.put("postDossierApi", "/o/rest/v2/onegate");
			api.put("dossierTemplatesApi", "/o/rest/v2/dossiertemplates");
			
			//TODO
			api.put("govAgency", "govAgency");
			api.put("groupId", groupId);
			api.put("user", user);
			api.put("portletNamespace", portletNamespace);
			
			writeJSON(renderRequest, renderResponse, api);
		}
		
		
		ResourceURL url = renderResponse.createResourceURL();
		System.out.println("FrontEndOnegateNpmPortlet.render()" + resourceUrl.toString());
		
		super.render(renderRequest, renderResponse);
	}
	
	@Override
	public void serveResource(ResourceRequest resourceRequest, ResourceResponse resourceResponse)
			throws IOException, PortletException {
		try {

			String resourceID = resourceRequest.getParameter("callResourceId");
			
			ThemeDisplay themeDisplay = (ThemeDisplay) resourceRequest.getAttribute(WebKeys.THEME_DISPLAY);
			

			long groupId = themeDisplay.getScopeGroupId();
			String portletNamespace = themeDisplay.getPortletDisplay().getNamespace();
		
			if (resourceID.equals("renderURLInit")) {

				User user = themeDisplay.getUser();
				Applicant applicant = ApplicantLocalServiceUtil.fetchByEmail(user.getEmailAddress());
				JSONObject api = JSONFactoryUtil.createJSONObject();
				
				api.put("serviceInfoApi", "/o/rest/v2/serviceinfos");
				api.put("serviceConfigApi", "/o/rest/v2/onegate/serviceconfigs/processes");
				api.put("regionApi", "/o/rest/v2/dictcollections");
				api.put("serviceOptionApi", "/o/rest/v2/serviceconfigs");
				api.put("dossierApi", "/o/rest/v2/dossiers");
				api.put("postDossierApi", "/o/rest/v2/onegate");
				api.put("dossierTemplatesApi", "/o/rest/v2/dossiertemplates");
				
				//TODO
				api.put("govAgency", "govAgency");
				api.put("groupId", groupId);
				api.put("user", user);
				api.put("portletNamespace", portletNamespace);
				
				writeJSON(resourceRequest, resourceResponse, api);

			} else {

				super.serveResource(resourceRequest, resourceResponse);

			}
		} catch (Exception e) {

			throw new PortletException((Throwable) e);

		}
	}
	
}