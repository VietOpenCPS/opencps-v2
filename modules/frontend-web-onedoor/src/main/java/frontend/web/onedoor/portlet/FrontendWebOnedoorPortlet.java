package frontend.web.onedoor.portlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.portlet.Portlet;
import javax.portlet.PortletException;
import javax.portlet.PortletURL;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.portlet.WindowStateException;

import org.osgi.service.component.annotations.Component;

import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.portlet.LiferayWindowState;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.util.bridges.freemarker.FreeMarkerPortlet;

import frontend.web.onedoor.constants.FrontendWebOnedoorPortletKeys;

/**
 * @author admin
 */
@Component(immediate = true, property = { "com.liferay.portlet.css-class-wrapper=portlet-freemarker",
		"com.liferay.portlet.display-category=category.onedoor", "com.liferay.portlet.header-portlet-css=/css/styles.css",
		"com.liferay.portlet.instanceable=true", "javax.portlet.display-name=frontend-web-onedoor Portlet",
		"javax.portlet.init-param.template-path=/", "javax.portlet.init-param.view-template=/templates/oneDoorView.ftl",
		"javax.portlet.name=" + FrontendWebOnedoorPortletKeys.FrontendWebOnedoor,
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=power-user,user" }, service = Portlet.class)
public class FrontendWebOnedoorPortlet extends FreeMarkerPortlet {
	@Override
	public void render(RenderRequest renderRequest, RenderResponse renderResponse)
			throws IOException, PortletException {
		
		ThemeDisplay themeDisplay =
			(ThemeDisplay) renderRequest.getAttribute(WebKeys.THEME_DISPLAY);
		
		JSONObject constants = createConstants();
		renderRequest.setAttribute("constants", constants);
		renderRequest.setAttribute("api", generateApiJsonObject(themeDisplay));
		renderRequest.setAttribute(
				"ajax", generateURLJsonObject(renderResponse));
		
		super.render(renderRequest, renderResponse);
	}

	private JSONObject createConstants() {

		JSONObject constantsObj = JSONFactoryUtil.createJSONObject();

		List<JSONObject> dossierStatus = new ArrayList<>();

		JSONObject dossierNew = JSONFactoryUtil.createJSONObject();
		dossierNew.put("text", "Hồ sơ mới tiếp nhận");
		dossierNew.put("value", "new");
		dossierStatus.add(dossierNew);

		JSONObject dossierReceiving = JSONFactoryUtil.createJSONObject();
		dossierReceiving.put("text", "Hồ sơ đang xử lý");
		dossierReceiving.put("value", "processing");
		dossierStatus.add(dossierReceiving);

		JSONObject dossierProcessed = JSONFactoryUtil.createJSONObject();
		dossierProcessed.put("text", "Hồ sơ chờ trả kết quả");
		dossierProcessed.put("value", "release");
		dossierStatus.add(dossierProcessed);

		JSONObject dossierWaiting = JSONFactoryUtil.createJSONObject();
		dossierWaiting.put("text", "Hồ sơ đã trả kết quả");
		dossierWaiting.put("value", "done");
		dossierStatus.add(dossierWaiting);

		constantsObj.put("dossierStatus", dossierStatus);

		return constantsObj;

	}
	
	private JSONObject generateURLJsonObject(RenderResponse renderResponse)
			throws WindowStateException {

			JSONObject urlObject = JSONFactoryUtil.createJSONObject();

			PortletURL addDossier = renderResponse.createRenderURL();
			addDossier.setWindowState(LiferayWindowState.EXCLUSIVE);
			addDossier.setParameter("mvcPath", "/templates/addNewDossier/add_new_dossier.ftl");
			urlObject.put("add_dossier", addDossier);
			
			PortletURL addDossierStep1 = renderResponse.createRenderURL();
			addDossierStep1.setWindowState(LiferayWindowState.EXCLUSIVE);
			addDossierStep1.setParameter("mvcPath", "/templates/addNewDossier/step1_choose_services.ftl");
			urlObject.put("add_dossier_step_1", addDossierStep1);
			
			PortletURL addDossierStep2 = renderResponse.createRenderURL();
			addDossierStep2.setWindowState(LiferayWindowState.EXCLUSIVE);
			addDossierStep2.setParameter("mvcPath", "/templates/addNewDossier/step2_submit_info.ftl");
			urlObject.put("add_dossier_step_2", addDossierStep2);
			
			PortletURL addDossierStep3 = renderResponse.createRenderURL();
			addDossierStep3.setWindowState(LiferayWindowState.EXCLUSIVE);
			addDossierStep3.setParameter("mvcPath", "/templates/addNewDossier/step3_dossier_declaration.ftl");
			urlObject.put("add_dossier_step_3", addDossierStep3);
			
			PortletURL addDossierStep4 = renderResponse.createRenderURL();
			addDossierStep4.setWindowState(LiferayWindowState.EXCLUSIVE);
			addDossierStep4.setParameter("mvcPath", "/templates/addNewDossier/step4_appointment.ftl");
			urlObject.put("add_dossier_step_4", addDossierStep4);
			
			PortletURL newDossiers = renderResponse.createRenderURL();
			newDossiers.setWindowState(LiferayWindowState.EXCLUSIVE);
			newDossiers.setParameter("mvcPath", "/templates/dossierList/newDossiers.ftl");
			urlObject.put("newDossiers", newDossiers);
			
			PortletURL processingDossiers = renderResponse.createRenderURL();
			processingDossiers.setWindowState(LiferayWindowState.EXCLUSIVE);
			processingDossiers.setParameter("mvcPath", "/templates/dossierList/processingDossiers.ftl");
			urlObject.put("processingDossiers", processingDossiers);
			
			PortletURL releaseDossiers = renderResponse.createRenderURL();
			releaseDossiers.setWindowState(LiferayWindowState.EXCLUSIVE);
			releaseDossiers.setParameter("mvcPath", "/templates/dossierList/releaseDossiers.ftl");
			urlObject.put("releaseDossiers", releaseDossiers);
			
			PortletURL doneDossiers = renderResponse.createRenderURL();
			doneDossiers.setWindowState(LiferayWindowState.EXCLUSIVE);
			doneDossiers.setParameter("mvcPath", "/templates/dossierList/doneDossiers.ftl");
			urlObject.put("doneDossiers", doneDossiers);

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
}