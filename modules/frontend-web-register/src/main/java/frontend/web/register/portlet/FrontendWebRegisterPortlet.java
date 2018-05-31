package frontend.web.register.portlet;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javax.portlet.Portlet;
import javax.portlet.PortletConfig;
import javax.portlet.PortletException;
import javax.portlet.PortletURL;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.portlet.WindowStateException;

import org.opencps.constants.FrontendWebRegisterPortletKeys;
import org.opencps.dossiermgt.model.Registration;
import org.opencps.dossiermgt.service.RegistrationLocalServiceUtil;
import org.opencps.usermgt.model.Applicant;
import org.opencps.usermgt.service.util.UserMgtUtils;
import org.osgi.service.component.annotations.Component;

import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.LiferayWindowState;
import com.liferay.portal.kernel.portlet.PortletConfigFactoryUtil;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.util.bridges.freemarker.FreeMarkerPortlet;

/**
 * @author hoanganh
 */
@Component(
	immediate = true,
	property = {
		"com.liferay.portlet.css-class-wrapper=portlet-freemarker",
		"com.liferay.portlet.display-category=category.opencps.register",
		"com.liferay.portlet.header-portlet-css=/css/main.css",
		"com.liferay.portlet.instanceable=true",
		"javax.portlet.display-name=frontend-web-register Portlet",
		"javax.portlet.init-param.template-path=/",
		"javax.portlet.init-param.view-template=/templates/registrationsmain.ftl",
		"javax.portlet.name=" + FrontendWebRegisterPortletKeys.FrontendWebRegister,
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=power-user,user"

	},
	service = Portlet.class
)


public class FrontendWebRegisterPortlet extends FreeMarkerPortlet {
	@Override
	public void render(
		RenderRequest renderRequest, RenderResponse renderResponse)
		throws IOException, PortletException {

		ThemeDisplay themeDisplay =
			(ThemeDisplay) renderRequest.getAttribute(WebKeys.THEME_DISPLAY);

		Applicant applicant =
			UserMgtUtils.getApplicant(themeDisplay.getUser().getEmailAddress());
		
		
		
		
		
		
		JSONObject applicantObj = JSONFactoryUtil.createJSONObject();
		
		try {
			applicant.setAddress(applicant.getAddress().trim());
			
			String jsonObj = JSONFactoryUtil.looseSerialize(applicant);
			applicantObj = JSONFactoryUtil.createJSONObject(jsonObj);
			
			SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
			String applicantIdDate = dateFormat.format(applicant.getApplicantIdDate());
			_log.info("applicantIdDate =======================> : "+applicantIdDate);
			_log.info("applicantIdDate_SOURCE =======================> : "+applicant.getApplicantIdDate().toString());
			applicantObj.put("applicantIdDate", applicantIdDate);
			
		}
		catch (Exception e) {
		}

		String registrationTemplateId =
			ParamUtil.getString(renderRequest, "registrationTemplateId");

		// get variable registrationId
		String registrationId = ParamUtil.getString(renderRequest, "registrationId");
		
		try {
			Registration registration =
				RegistrationLocalServiceUtil.getRegistration(Long.parseLong(registrationId));
			String registrationStr = JSONFactoryUtil.looseSerialize(registration);
			JSONObject registrationObj = JSONFactoryUtil.createJSONObject(registrationStr);
			if (registrationObj != null) {
				renderRequest.setAttribute("registration", registrationObj);
			}

		}
		catch (Exception e) {
			// TODO: handle exception
			_log.info(e.getMessage());
		}
		
		

		String registrationFormNo =
			ParamUtil.getString(renderRequest, "registrationFormNo");
		_log.info("registrationFormNo : "+registrationFormNo);


		// set varible
		renderRequest.setAttribute(
			"ajax", generateURLJsonObject(renderResponse));
		renderRequest.setAttribute("api", generateApiJsonObject(themeDisplay));
		renderRequest.setAttribute("applicant", applicantObj);
		renderRequest.setAttribute("registrationTemplateId", registrationTemplateId);
		renderRequest.setAttribute("registrationId", registrationId);
		renderRequest.setAttribute("registrationFormNo", registrationFormNo);
		renderRequest.setAttribute("constants", generateConstantsJsonObject(themeDisplay));

		super.render(renderRequest, renderResponse);

	}
	
	

	private JSONObject generateURLJsonObject(RenderResponse renderResponse)
		throws WindowStateException {

		JSONObject urlObject = JSONFactoryUtil.createJSONObject();

		PortletURL customerRegistrationsDetail = renderResponse.createRenderURL();
		customerRegistrationsDetail.setWindowState(LiferayWindowState.EXCLUSIVE);
		customerRegistrationsDetail.setParameter(
			"mvcPath", "/templates/registrations_detail.ftl");

		urlObject.put("registrations_detail", customerRegistrationsDetail);

		PortletURL customerRegistrationsMenu = renderResponse.createRenderURL();
		customerRegistrationsMenu.setWindowState(LiferayWindowState.EXCLUSIVE);
		customerRegistrationsMenu.setParameter(
			"mvcPath", "/templates/registrations_menu.ftl");

		urlObject.put("registrations_menu", customerRegistrationsMenu);

		return urlObject;
	}

	private JSONObject generateApiJsonObject(ThemeDisplay themeDisplay) {

		JSONObject apiObject = JSONFactoryUtil.createJSONObject();

		apiObject.put("server", themeDisplay.getPortalURL() + "/o/rest/v2");
		apiObject.put(
			"portletNamespace",
			themeDisplay.getPortletDisplay().getNamespace());

		return apiObject;
	}
	
	private JSONObject generateConstantsJsonObject(ThemeDisplay themeDisplay) {

		JSONObject constants = JSONFactoryUtil.createJSONObject();

		constants.put("registrationStates", getRegistrationStates(themeDisplay));

		return constants;
	}
	
	private List<JSONObject> getRegistrationStates(ThemeDisplay themeDisplay) {

		JSONObject regObject = JSONFactoryUtil.createJSONObject();
		List<JSONObject> registrationState = new ArrayList<>();
		
		PortletConfig portletConfig = PortletConfigFactoryUtil.get(
				themeDisplay.getPortletDisplay().getId());
		ResourceBundle resourceBundle =
				portletConfig.getResourceBundle(themeDisplay.getLocale());

		for (int i=0; i<4; i++) {
			regObject = JSONFactoryUtil.createJSONObject();
			regObject.put("value", i);
			regObject.put("text", LanguageUtil.get(resourceBundle, "registration-state"+i));
			registrationState.add(regObject);
		}

		return registrationState;
	}

	private static final Log _log =
		LogFactoryUtil.getLog(FrontendWebRegisterPortlet.class);
}