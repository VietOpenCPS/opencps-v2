/**
 * 
 */

package org.opencps.frontend.web.portal.portlet;

import java.io.IOException;

import javax.portlet.Portlet;
import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.opencps.frontend.web.portal.constants.FrontendWebPortalPortletKeys;
import org.opencps.usermgt.model.Applicant;
import org.opencps.usermgt.model.Employee;
import org.opencps.usermgt.service.EmployeeLocalServiceUtil;
import org.opencps.usermgt.service.util.UserMgtUtils;
import org.osgi.service.component.annotations.Component;

import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.util.bridges.freemarker.FreeMarkerPortlet;

/**
 * @author phucnv
 * @date Sep 22, 2017
 */
@Component(immediate = true, property = {
	"com.liferay.portlet.css-class-wrapper=portlet-freemarker",
	"com.liferay.portlet.display-category=category.opencps_v2.portal",
	"com.liferay.portlet.header-portlet-css=/css/main.css",
	"com.liferay.portlet.instanceable=true",
	"javax.portlet.display-name=Profile Portlet",
	"javax.portlet.init-param.template-path=/",
	"javax.portlet.init-param.view-template=/templates/profile/profile_main.ftl",
	"javax.portlet.name=" + FrontendWebPortalPortletKeys.PROFILE_PORTLET_NAME,
	"javax.portlet.resource-bundle=content.Language",
	"javax.portlet.security-role-ref=power-user,user"
}, service = Portlet.class)
public class ProfilePorlet extends FreeMarkerPortlet {

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

		JSONObject urlObject = JSONFactoryUtil.createJSONObject();
		JSONObject apiObject = JSONFactoryUtil.createJSONObject();

		try {
			Applicant applicant = UserMgtUtils.getApplicant(
				themeDisplay.getUser().getEmailAddress());
			
			Employee employee = EmployeeLocalServiceUtil.fetchByF_mappingUserId( themeDisplay.getScopeGroupId(), themeDisplay.getUserId());
			String layoutFriendlyUrl = themeDisplay.getScopeGroup().getFriendlyURL();
			_log.info("layoutFriendlyUrl=======" + layoutFriendlyUrl);
			renderRequest.setAttribute("layoutFriendlyUrl", layoutFriendlyUrl);
			if(employee != null){
				JSONObject employeeObj;
				String employeeStr = JSONFactoryUtil.looseSerialize(employee);
				employeeObj = JSONFactoryUtil.createJSONObject(employeeStr);
				_log.info("employee===========>"+employeeObj);
				renderRequest.setAttribute("employee", employeeObj);
				renderRequest.setAttribute("userType", "employee");
			}else {
				
				renderRequest.setAttribute("userType", "applicant");
				JSONObject applicantObj;
				String jsonObj = JSONFactoryUtil.looseSerialize(applicant);
				applicantObj = JSONFactoryUtil.createJSONObject(jsonObj);
				if(applicantObj != null){
					renderRequest.setAttribute("applicant", applicantObj);
					renderRequest.setAttribute("applicantIdType", applicantObj.get("applicantIdType").toString());
					_log.info("applicantIdType===========>"+applicantObj.get("applicantIdType").toString());
				}
			}
			
			
		}
		catch (Exception e) {
			_log.error(e);
			_log.info(e.getMessage());
		}
		
		long userId = themeDisplay.getUser().getUserId();

		// api
		apiObject.put("server", themeDisplay.getPortalURL() + "/o/rest/v2");
		apiObject.put(
			"portletNamespace",
			themeDisplay.getPortletDisplay().getNamespace());

		// set varible
		renderRequest.setAttribute("ajax", urlObject);
		renderRequest.setAttribute("api", apiObject);
		renderRequest.setAttribute("userId", userId);

		super.render(renderRequest, renderResponse);

	}

	private static final Log _log = LogFactoryUtil.getLog(ProfilePorlet.class);
}
