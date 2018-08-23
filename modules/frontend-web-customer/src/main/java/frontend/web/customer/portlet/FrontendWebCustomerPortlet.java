
package frontend.web.customer.portlet;

import java.io.IOException;

import javax.portlet.Portlet;
import javax.portlet.PortletException;
import javax.portlet.PortletURL;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.portlet.WindowStateException;
import javax.servlet.http.HttpServletRequest;

import org.opencps.dossiermgt.model.Dossier;
import org.opencps.dossiermgt.service.DossierLocalServiceUtil;
import org.opencps.usermgt.model.Applicant;
import org.opencps.usermgt.model.Employee;
import org.opencps.usermgt.service.EmployeeLocalServiceUtil;
import org.opencps.usermgt.service.util.UserMgtUtils;
import org.osgi.service.component.annotations.Component;

import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.LiferayWindowState;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.util.bridges.freemarker.FreeMarkerPortlet;

import frontend.web.customer.constants.FrontendWebCustomerPortletKeys;

/**
 * @author huymq
 */

@Component(immediate = true, property = {
	"com.liferay.portlet.css-class-wrapper=portlet-freemarker",
	"com.liferay.portlet.display-category=category.opencps.customer",
	"com.liferay.portlet.header-portlet-css=/css/main.css",
	"com.liferay.portlet.instanceable=true",
	"javax.portlet.display-name=frontend-web-customer Portlet",
	"javax.portlet.init-param.template-path=/",
	"javax.portlet.init-param.view-template=/templates/customermain.ftl",
	"javax.portlet.name=" + FrontendWebCustomerPortletKeys.FrontendWebCustomer,
	"javax.portlet.resource-bundle=content.Language",
	"javax.portlet.security-role-ref=power-user,user"
}, service = Portlet.class)
public class FrontendWebCustomerPortlet extends FreeMarkerPortlet {

	@Override
	public void render(
		RenderRequest renderRequest, RenderResponse renderResponse)
		throws IOException, PortletException {

		ThemeDisplay themeDisplay =
			(ThemeDisplay) renderRequest.getAttribute(WebKeys.THEME_DISPLAY);
		HttpServletRequest httpRequest = PortalUtil.getHttpServletRequest(renderRequest);

		Applicant applicant =
			UserMgtUtils.getApplicant(themeDisplay.getUser().getEmailAddress());
		
		String serviceConfigId = PortalUtil.getOriginalServletRequest(httpRequest).getParameter("serviceConfigId");
		
		String dossierUUid = ParamUtil.getString(renderRequest, "dossierUUid");
		String paymentFileUUid = ParamUtil.getString(renderRequest, "paymentFileUUid");
		JSONObject applicantObj = JSONFactoryUtil.createJSONObject();
		String jsonObj = JSONFactoryUtil.looseSerialize(applicant);
		try {
			applicantObj = JSONFactoryUtil.createJSONObject(jsonObj);
		}
		catch (Exception e) {
		}

		String dossierTemplateId =
			ParamUtil.getString(renderRequest, "dossierTemplateId");

		// get variable dossier
		String dossierId = ParamUtil.getString(renderRequest, "dossierId");
		try {
			Dossier dossier =
				DossierLocalServiceUtil.getDossier(Long.parseLong(dossierId));
			String dossierStr = JSONFactoryUtil.looseSerialize(dossier);
			JSONObject dossierObj =
				JSONFactoryUtil.createJSONObject(dossierStr);
			if (dossierObj != null) {
				renderRequest.setAttribute("dossier", dossierObj);
			}

		}
		catch (Exception e) {
			// TODO: handle exception
			_log.info(e.getMessage());
		}

		String dossierPartNo =
			ParamUtil.getString(renderRequest, "dossierPartNo");
		System.out.println("dossierPartNo:" + dossierPartNo);

		String dossierTemplateNo =
			ParamUtil.getString(renderRequest, "dossierTemplateNo");
		String govAgencyCode =
						ParamUtil.getString(renderRequest, "govAgencyCode");
		

		// apiObject.put("applicant", applicantObj);
		String trans_id = ParamUtil.getString(renderRequest, "trans_id");
		String good_code = ParamUtil.getString(renderRequest, "good_code");

		JSONObject paymentObject = generatePaymentObject(
			dossierUUid, paymentFileUUid, trans_id, good_code);
		// set varible
		renderRequest.setAttribute(
			"ajax", generateURLJsonObject(renderResponse));
		renderRequest.setAttribute("api", generateApiJsonObject(themeDisplay));
		renderRequest.setAttribute("applicant", applicantObj);
		renderRequest.setAttribute("dossierTemplateId", dossierTemplateId);
		renderRequest.setAttribute("dossierId", dossierId);
		renderRequest.setAttribute("dossierPartNo", dossierPartNo);
		renderRequest.setAttribute("dossierTemplateNo", dossierTemplateNo);
		renderRequest.setAttribute("RequestParameters", paymentObject);
		renderRequest.setAttribute("govAgencyCode", govAgencyCode);
		renderRequest.setAttribute("serviceConfigId", serviceConfigId);

		super.render(renderRequest, renderResponse);

	}

	private JSONObject generatePaymentObject(
		String dossierUUid, String paymentFileUUid, String trans_id,
		String good_code) {

		JSONObject paymentObject = JSONFactoryUtil.createJSONObject();

		paymentObject.put("dossierUUid", dossierUUid);
		paymentObject.put("paymentFileUUid", paymentFileUUid);
		paymentObject.put(
			"trans_id",
			Validator.isNotNull(trans_id) ? trans_id : StringPool.BLANK);
		paymentObject.put(
			"good_code",
			Validator.isNotNull(good_code) ? good_code : StringPool.BLANK);
		return paymentObject;
	}

	private JSONObject generateURLJsonObject(RenderResponse renderResponse)
		throws WindowStateException {

		JSONObject urlObject = JSONFactoryUtil.createJSONObject();

		PortletURL customerDossierDetail2URL = renderResponse.createRenderURL();
		customerDossierDetail2URL.setWindowState(LiferayWindowState.EXCLUSIVE);
		customerDossierDetail2URL.setParameter(
			"mvcPath", "/templates/customer_dossier_detail_2.ftl");

		urlObject.put("customer_dossier_detail_2", customerDossierDetail2URL);

		PortletURL customerDossierDetail_3 = renderResponse.createRenderURL();
		customerDossierDetail_3.setWindowState(LiferayWindowState.EXCLUSIVE);
		customerDossierDetail_3.setParameter(
			"mvcPath", "/templates/customer_dossier_detail_3.ftl");

		urlObject.put("customer_dossier_detail_3", customerDossierDetail_3);

		PortletURL customerDossierDetail_4 = renderResponse.createRenderURL();
		customerDossierDetail_4.setWindowState(LiferayWindowState.EXCLUSIVE);
		customerDossierDetail_4.setParameter(
			"mvcPath", "/templates/customer_dossier_detail_4.ftl");

		urlObject.put("customer_dossier_detail_4", customerDossierDetail_4);

		PortletURL customerDossierListURL = renderResponse.createRenderURL();
		customerDossierListURL.setWindowState(LiferayWindowState.EXCLUSIVE);
		customerDossierListURL.setParameter(
			"mvcPath", "/templates/customer_dossierlist.ftl");

		urlObject.put("customer_dossierlist", customerDossierListURL);

		PortletURL customerDossierDetailURL = renderResponse.createRenderURL();
		customerDossierDetailURL.setWindowState(LiferayWindowState.EXCLUSIVE);
		customerDossierDetailURL.setParameter(
			"mvcPath", "/templates/customer_dossier_detail.ftl");

		urlObject.put("customer_dossier_detail", customerDossierDetailURL);

		PortletURL customerAdditionalRequirementsURL =
			renderResponse.createRenderURL();
		customerAdditionalRequirementsURL.setWindowState(
			LiferayWindowState.EXCLUSIVE);
		customerAdditionalRequirementsURL.setParameter(
			"mvcPath", "/templates/customer_additional_requirements.ftl");

		urlObject.put(
			"customer_additional_requirements",
			customerAdditionalRequirementsURL);

		PortletURL customerPaymentRequestURL = renderResponse.createRenderURL();
		customerPaymentRequestURL.setWindowState(LiferayWindowState.EXCLUSIVE);
		customerPaymentRequestURL.setParameter(
			"mvcPath", "/templates/customer_payment_request.ftl");

		urlObject.put("customer_payment_request", customerPaymentRequestURL);

		PortletURL customerResultRequestURL = renderResponse.createRenderURL();
		customerResultRequestURL.setWindowState(LiferayWindowState.EXCLUSIVE);
		customerResultRequestURL.setParameter(
			"mvcPath", "/templates/customer_result_request.ftl");

		urlObject.put("customer_result_request", customerResultRequestURL);

		PortletURL customerDossierFollowgovListURL =
			renderResponse.createRenderURL();
		customerDossierFollowgovListURL.setWindowState(
			LiferayWindowState.EXCLUSIVE);
		customerDossierFollowgovListURL.setParameter(
			"mvcPath", "/templates/customer_dossier_followgov_list.ftl");

		urlObject.put(
			"customer_dossier_followgov_list", customerDossierFollowgovListURL);

		PortletURL customerDossierFollownameListURL =
			renderResponse.createRenderURL();
		customerDossierFollownameListURL.setWindowState(
			LiferayWindowState.EXCLUSIVE);
		customerDossierFollownameListURL.setParameter(
			"mvcPath", "/templates/customer_dossier_followname_list.ftl");

		urlObject.put(
			"customer_dossier_followname_list",
			customerDossierFollownameListURL);

		PortletURL customerPrepareFileDetailURL =
			renderResponse.createRenderURL();
		customerPrepareFileDetailURL.setWindowState(
			LiferayWindowState.EXCLUSIVE);
		customerPrepareFileDetailURL.setParameter(
			"mvcPath", "/templates/customer_prepare_file_detail.ftl");

		urlObject.put(
			"customer_prepare_file_detail", customerPrepareFileDetailURL);

		PortletURL notificationURL = renderResponse.createRenderURL();
		notificationURL.setWindowState(LiferayWindowState.EXCLUSIVE);
		notificationURL.setParameter("mvcPath", "/templates/notification.ftl");

		urlObject.put("notification", notificationURL);

		PortletURL serviceconfigURL = renderResponse.createRenderURL();
		serviceconfigURL.setWindowState(LiferayWindowState.EXCLUSIVE);
		serviceconfigURL.setParameter(
			"mvcPath", "/templates/serviceconfig.ftl");

		urlObject.put("serviceconfig", serviceconfigURL);

		PortletURL submitedDossierInfoURL = renderResponse.createRenderURL();
		submitedDossierInfoURL.setWindowState(LiferayWindowState.EXCLUSIVE);
		submitedDossierInfoURL.setParameter(
			"mvcPath", "/templates/submited_dossier_info.ftl");

		urlObject.put("submited_dossier_info", submitedDossierInfoURL);

		PortletURL customerDossierDetailFiletemplateURL =
			renderResponse.createRenderURL();
		customerDossierDetailFiletemplateURL.setWindowState(
			LiferayWindowState.EXCLUSIVE);
		customerDossierDetailFiletemplateURL.setParameter(
			"mvcPath", "/templates/customer_dossier_detail_filetemplate.ftl");

		urlObject.put(
			"customer_dossier_detail_filetemplate",
			customerDossierDetailFiletemplateURL);

		PortletURL serviceconfigAdministrationURL =
			renderResponse.createRenderURL();
		serviceconfigAdministrationURL.setWindowState(
			LiferayWindowState.EXCLUSIVE);
		serviceconfigAdministrationURL.setParameter(
			"mvcPath", "/templates/serviceconfig_administration.ftl");

		urlObject.put(
			"serviceconfig_administration", serviceconfigAdministrationURL);

		PortletURL serviceconfigDomainURL = renderResponse.createRenderURL();
		serviceconfigDomainURL.setWindowState(LiferayWindowState.EXCLUSIVE);
		serviceconfigDomainURL.setParameter(
			"mvcPath", "/templates/serviceconfig_domain.ftl");

		urlObject.put("serviceconfig_domain", serviceconfigDomainURL);

		PortletURL customerUploadfileURL = renderResponse.createRenderURL();
		customerUploadfileURL.setWindowState(LiferayWindowState.EXCLUSIVE);
		customerUploadfileURL.setParameter(
			"mvcPath", "/templates/customer_uploadfile.ftl");

		urlObject.put("customer_uploadfile", customerUploadfileURL);

		PortletURL customerDossierOnlineFormDialogURL =
			renderResponse.createRenderURL();
		customerDossierOnlineFormDialogURL.setWindowState(
			LiferayWindowState.EXCLUSIVE);
		customerDossierOnlineFormDialogURL.setParameter(
			"mvcPath", "/templates/customer_dossier_online_form_dialog.ftl");

		urlObject.put(
			"customer_dossier_online_form_dialog",
			customerDossierOnlineFormDialogURL);

		PortletURL customerDossierInfoURL = renderResponse.createRenderURL();
		customerDossierInfoURL.setWindowState(LiferayWindowState.EXCLUSIVE);
		customerDossierInfoURL.setParameter(
			"mvcPath", "/templates/customer_dossier_info.ftl");

		urlObject.put("customer_dossier_info", customerDossierInfoURL);

		PortletURL customerDossierComponentProfilesURL =
			renderResponse.createRenderURL();
		customerDossierComponentProfilesURL.setWindowState(
			LiferayWindowState.EXCLUSIVE);
		customerDossierComponentProfilesURL.setParameter(
			"mvcPath", "/templates/customer_dossier_component_profiles.ftl");

		urlObject.put(
			"customer_dossier_component_profiles",
			customerDossierComponentProfilesURL);

		PortletURL customerDossierWaitPayingURL =
			renderResponse.createRenderURL();
		customerDossierWaitPayingURL.setWindowState(
			LiferayWindowState.EXCLUSIVE);
		customerDossierWaitPayingURL.setParameter(
			"mvcPath", "/templates/customer_dossier_waitpaying.ftl");

		urlObject.put(
			"customer_dossier_waitpaying", customerDossierWaitPayingURL);

		PortletURL customerNotificationPayingURL =
			renderResponse.createRenderURL();
		customerNotificationPayingURL.setWindowState(
			LiferayWindowState.EXCLUSIVE);
		customerNotificationPayingURL.setParameter(
			"mvcPath", "/templates/notificationPaying.ftl");

		urlObject.put("notificationPaying", customerNotificationPayingURL);

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

	private static final Log _log =
		LogFactoryUtil.getLog(FrontendWebCustomerPortlet.class);

}
