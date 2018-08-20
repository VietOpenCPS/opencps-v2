package frontend.web.customer.v21.portlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import javax.portlet.Portlet;
import javax.portlet.PortletException;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;
import javax.servlet.http.HttpServletRequest;

import org.osgi.service.component.annotations.Component;

import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.util.bridges.freemarker.FreeMarkerPortlet;

import frontend.web.customer.v21.constants.FrontendWebCustomerPortletKeys;

/**
 * @author dangkhanhtrung
 */
@Component(immediate = true, property = { "com.liferay.portlet.css-class-wrapper=portlet-freemarker",
		"com.liferay.portlet.display-category=category.opencps.customer", "com.liferay.portlet.instanceable=false",
		"javax.portlet.display-name=frontend-web-customer-v2-1 Portlet", "javax.portlet.init-param.template-path=/",
		"javax.portlet.init-param.view-template=/templates/view.ftl",
		"javax.portlet.name=" + FrontendWebCustomerPortletKeys.FrontendWebCustomer,
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=power-user,user" }, service = Portlet.class)
public class FrontendWebCustomerPortlet extends FreeMarkerPortlet {

	public void serveResource(ResourceRequest resourceRequest, ResourceResponse resourceResponse)
			throws PortletException {
		try {

			String resourceID = resourceRequest.getResourceID();

			ThemeDisplay themeDisplay = (ThemeDisplay) resourceRequest.getAttribute(WebKeys.THEME_DISPLAY);

			HttpServletRequest request = PortalUtil.getHttpServletRequest(resourceRequest);
			HttpServletRequest requestOrg = PortalUtil.getOriginalServletRequest(request);

			if (resourceID.equals("renderURLInit")) {

				User user = themeDisplay.getUser();

				JSONObject object = JSONFactoryUtil.createJSONObject();

				object.put("groupId", themeDisplay.getScopeGroupId());

				JSONObject userObject = JSONFactoryUtil.createJSONObject();

				userObject.put("userName", user.getFullName());
				userObject.put("userEmail", user.getEmailAddress());
				userObject.put("userId", user.getUserId());
				userObject.put("defaultUser", user.getDefaultUser());
				object.put("user", userObject);

				object.put("menuConfigToDo", "/o/rest/v2_1/menuconfigs/todo");

				object.put("counterMenuStep", "/o/rest/v2/statistics/dossiers/todo");
				object.put("getListThuTucHanhChinh", "/o/rest/v2/onegate/serviceconfigs/processes");
				
				object.put("serviceInfoApi", "/o/rest/v2/serviceinfos");
				object.put("serviceConfigApi", "/o/rest/v2/onegate/serviceconfigs/processes");
				object.put("regionApi", "/o/rest/v2/dictcollections");
				object.put("postDossierApi", "/o/rest/v2/dossiers");
				object.put("dossierTemplatesApi", "/o/rest/v2/dossiertemplates");
				object.put("applicantApi", "/o/rest/v2/applicants");
				object.put("dossierlogsApi", "/o/rest/v2/dossierlogs");
				object.put("dossierApi", "/o/rest/v2/dossiers");
				object.put("serviceProcessesApi", "/o/rest/v2/serviceprocesses");
				object.put("serviceConfigApi", "/o/rest/v2/serviceconfigs");
				object.put("serviceConfigByGovApi", "/o/rest/v2/serviceconfigs/govagencies");
				object.put("commentApi", "/o/rest/v2/comments");
<<<<<<< HEAD
				object.put("documentApi", "/o/rest/v2/dossiers");
=======
				object.put("documentApi", "/o/rest/v2_1/dossiers");
>>>>>>> fds-opencps/release-candidate-2.1
				object.put("stepConfigApi", "/o/rest/v2_1/stepconfigs");
				
				object.put("getNextAction", "/o/rest/v2/dossiers");
				object.put("getServiceConfigs", "/o/rest/v2/serviceconfigs");

				String token = pullToken(themeDisplay);

				requestOrg.getSession().setAttribute("CSRF_TOKEN_FOR_SESSION_NAME", token);

				object.put("cps_auth", token);

				writeJSON(resourceRequest, resourceResponse, object);

			} else {

				super.serveResource(resourceRequest, resourceResponse);

			}
		} catch (Exception e) {

			throw new PortletException((Throwable) e);

		}
	}

	public static String pullToken(ThemeDisplay themeDisplay) {

		String result = StringPool.BLANK;

		Process process = null;

		List<String> list = new ArrayList<String>();
		list.add("curl");
		list.add("-s");
		list.add("-X");
		list.add("GET");
		list.add(themeDisplay.getPortalURL() + "/o/rest/v2/onegate/token");

		ProcessBuilder pb = new ProcessBuilder(list);

		String data = StringPool.BLANK;

		try {

			pb.redirectErrorStream();
			process = pb.start();
			BufferedReader input = new BufferedReader(new InputStreamReader(process.getInputStream()));

			String line = null;

			while ((line = input.readLine()) != null) {
				data += line;
			}
			process.destroy();

		} catch (IOException e) {
			process = null;
			process.destroy();
		} finally {
			process.destroy();
		}

		result = data;

		return result;
	}
}