package frontend.web.dossier.bvh.portlet;

import frontend.web.dossier.bvh.constants.FrontendWebDossierBvhPortletKeys;

import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.theme.PortletDisplay;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.util.bridges.freemarker.FreeMarkerPortlet;

import java.io.IOException;

import javax.portlet.Portlet;
import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.opencps.usermgt.model.Employee;
import org.opencps.usermgt.model.EmployeeJobPos;
import org.opencps.usermgt.model.WorkingUnit;
import org.opencps.usermgt.service.EmployeeJobPosLocalServiceUtil;
import org.opencps.usermgt.service.EmployeeLocalServiceUtil;
import org.opencps.usermgt.service.WorkingUnitLocalServiceUtil;
import org.osgi.service.component.annotations.Component;

/**
 * @author GIAHUY
 */
@Component(immediate = true, property = { "com.liferay.portlet.display-category=category.sample",
		"com.liferay.portlet.header-portlet-css=/css/main.css",
		"com.liferay.portlet.footer-portlet-javascript=/js/main.js", "com.liferay.portlet.instanceable=false",
		"javax.portlet.display-name=frontend-web-dossier-bvh Portlet", "javax.portlet.init-param.template-path=/",
		"javax.portlet.init-param.view-template=/templates/view.ftl",
		"javax.portlet.name=" + FrontendWebDossierBvhPortletKeys.FrontendWebDossierBvh,
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=power-user,user" }, service = Portlet.class)
public class FrontendWebDossierBvhPortlet extends FreeMarkerPortlet {
	public static final Log _log = LogFactoryUtil.getLog(FrontendWebDossierBvhPortlet.class);

	public void render(RenderRequest renderRequest, RenderResponse renderResponse)
			throws IOException, PortletException {
		ThemeDisplay themeDisplay = (ThemeDisplay) renderRequest.getAttribute(WebKeys.THEME_DISPLAY);
		PortletDisplay portletDisplay = themeDisplay.getPortletDisplay();
		String portletId = portletDisplay.getId();
		// HttpServletRequest httpRequest =
		// PortalUtil.getHttpServletRequest(renderRequest);

		renderRequest.setAttribute("api", generateApiJsonObject(themeDisplay));
		JSONObject urlObject = JSONFactoryUtil.createJSONObject();

		try {
			Employee employee = EmployeeLocalServiceUtil.fetchByF_mappingUserId(55301, themeDisplay.getUserId());
			
			EmployeeJobPos employeeJobPos = EmployeeJobPosLocalServiceUtil.fetchEmployeeJobPos(employee.getMainJobPostId());
	
			long workingUnitId = Validator.isNotNull(employeeJobPos)?employeeJobPos.getWorkingUnitId():0;
			
			String workingUnitName = StringPool.BLANK;
			String govAgencyCode = StringPool.BLANK;
			
			if(workingUnitId > 0){
				
				WorkingUnit workingUnit = WorkingUnitLocalServiceUtil.fetchWorkingUnit(workingUnitId);
				
				workingUnitName = Validator.isNotNull(workingUnit)?workingUnit.getName():StringPool.BLANK;
				
				govAgencyCode = Validator.isNotNull(workingUnit)?workingUnit.getGovAgencyCode():StringPool.BLANK;
			}
			
			renderRequest.setAttribute("workingUnitId", workingUnitId);
			renderRequest.setAttribute("workingUnitName", workingUnitName);
			renderRequest.setAttribute("govAgencyCode", govAgencyCode);
			_log.info("govAgencyCode ==============> " + govAgencyCode);
			
			if (employee != null) {
				JSONObject employeeObj = JSONFactoryUtil.createJSONObject();
				String employeeStr = JSONFactoryUtil.looseSerialize(employee);
				employeeObj = JSONFactoryUtil.createJSONObject(employeeStr);
				_log.info("employee===========>" + employeeObj);
				renderRequest.setAttribute("employee", employeeObj);
				renderRequest.setAttribute("userType", "employee");
			}
		} catch (Exception e) {
			_log.info(e.getMessage());
		}
		

		renderRequest.setAttribute("ajax", urlObject);

		super.render(renderRequest, renderResponse);
	}

	private JSONObject generateApiJsonObject(ThemeDisplay themeDisplay) {

		JSONObject apiObject = JSONFactoryUtil.createJSONObject();

		apiObject.put("server", themeDisplay.getPortalURL() + "/o/rest/v2");
		apiObject.put("portletNamespace", themeDisplay.getPortletDisplay().getNamespace());

		return apiObject;
	}
}