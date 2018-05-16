package frontend.web.survey.portlet;

import frontend.web.survey.constants.FrontendWebSurveyPortletKeys;

import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.util.bridges.freemarker.FreeMarkerPortlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.portlet.Portlet;
import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.osgi.service.component.annotations.Component;

/**
 * @author dtson
 */
@Component(immediate = true, property = { "com.liferay.portlet.css-class-wrapper=portlet-freemarker",
		"com.liferay.portlet.display-category=category.survey", "com.liferay.portlet.header-portlet-css=/css/main.css",
		"com.liferay.portlet.instanceable=true", "javax.portlet.display-name=frontend-web-survey Portlet",
		"javax.portlet.init-param.template-path=/", "javax.portlet.init-param.view-template=/templates/surveyView.ftl",
		"javax.portlet.name=" + FrontendWebSurveyPortletKeys.FrontendWebSurvey,
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=power-user,user" }, service = Portlet.class)
public class FrontendWebSurveyPortlet extends FreeMarkerPortlet {
	@Override
	public void render(RenderRequest renderRequest, RenderResponse renderResponse)
			throws IOException, PortletException {

		JSONObject constantsObj = createConstants();

		renderRequest.setAttribute("constants", constantsObj);

		super.render(renderRequest, renderResponse);
	}

	private JSONObject createConstants() {

		JSONObject constantsObj = JSONFactoryUtil.createJSONObject();
		constantsObj.put("className", "Employee");
		constantsObj.put("collection_Academic", "ACADEMIC");
		constantsObj.put("alpaca_templateNo", "EMPLOYEE");

		List<JSONObject> dossierStatus = new ArrayList<>();

		JSONObject dossierNew = JSONFactoryUtil.createJSONObject();
		dossierNew.put("text", "Hồ sơ mới");
		dossierNew.put("value", "new");
		dossierStatus.add(dossierNew);

		JSONObject dossierReceiving = JSONFactoryUtil.createJSONObject();
		dossierReceiving.put("text", "Hồ sơ chờ tiếp nhận");
		dossierReceiving.put("value", "receiving");
		dossierStatus.add(dossierReceiving);

		JSONObject dossierProcessed = JSONFactoryUtil.createJSONObject();
		dossierProcessed.put("text", "Hồ sơ đã tiếp nhận");
		dossierProcessed.put("value", "processing");
		dossierStatus.add(dossierProcessed);

		JSONObject dossierWaiting = JSONFactoryUtil.createJSONObject();
		dossierWaiting.put("text", "Hồ sơ chờ bổ sung");
		dossierWaiting.put("value", "waiting");
		dossierStatus.add(dossierWaiting);

		JSONObject dossierPaying = JSONFactoryUtil.createJSONObject();
		dossierPaying.put("text", "Hồ sơ chờ thanh toán");
		dossierPaying.put("value", "paying");
		dossierStatus.add(dossierPaying);

		JSONObject dossierDone = JSONFactoryUtil.createJSONObject();
		dossierDone.put("text", "Hồ sơ đã kết thúc");
		dossierDone.put("value", "done");
		dossierStatus.add(dossierDone);

		JSONObject dossierCancelling = JSONFactoryUtil.createJSONObject();
		dossierCancelling.put("text", "Hồ sơ yêu cầu hủy");
		dossierCancelling.put("value", "cancelling");
		dossierStatus.add(dossierCancelling);

		JSONObject dossierCancelled = JSONFactoryUtil.createJSONObject();
		dossierCancelled.put("text", "Hồ sơ xác nhận hủy");
		dossierCancelled.put("value", "cancelled");
		dossierStatus.add(dossierCancelled);

		JSONObject dossierExpired = JSONFactoryUtil.createJSONObject();
		dossierExpired.put("text", "Hồ sơ đến hạn XN hiệu lực");
		dossierExpired.put("value", "expired");
		dossierStatus.add(dossierExpired);

		JSONObject dossierAll = JSONFactoryUtil.createJSONObject();
		dossierAll.put("text", "Tất cả hồ sơ");
		dossierAll.put("value", "all");
		dossierStatus.add(dossierAll);

		constantsObj.put("city", dossierStatus);

		return constantsObj;

	}
}