
package org.opencps.frontend.web.admin.portlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.Portlet;
import javax.portlet.PortletConfig;
import javax.portlet.PortletException;
import javax.portlet.PortletMode;
import javax.portlet.PortletRequest;
import javax.portlet.PortletURL;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.portlet.WindowStateException;

import org.opencps.auth.api.exception.UnauthenticationException;
import org.opencps.auth.api.exception.UnauthorizationException;
import org.opencps.communication.action.impl.NotificationTemplateActions;
import org.opencps.communication.constants.NotificationMGTConstants;
import org.opencps.communication.model.Notificationtemplate;
import org.opencps.datamgt.action.DictcollectionInterface;
import org.opencps.datamgt.action.impl.DictCollectionActions;
import org.opencps.datamgt.constants.DictGroupTerm;
import org.opencps.datamgt.constants.DictItemGroupTerm;
import org.opencps.datamgt.model.DictCollection;
import org.opencps.datamgt.model.DictGroup;
import org.opencps.datamgt.model.DictItem;
import org.opencps.datamgt.service.DictGroupLocalServiceUtil;
import org.opencps.datamgt.service.DictItemLocalServiceUtil;
import org.opencps.dossiermgt.model.ServiceInfo;
import org.opencps.dossiermgt.service.ServiceInfoLocalServiceUtil;
import org.opencps.frontend.web.admin.constants.AdminPortletKeys;
import org.opencps.frontend.web.admin.constants.FrontendWebAdminPortletConstants;
import org.opencps.usermgt.action.impl.JobposActions;
import org.opencps.usermgt.model.Applicant;
import org.opencps.usermgt.model.Employee;
import org.opencps.usermgt.model.EmployeeJobPos;
import org.opencps.usermgt.model.JobPos;
import org.opencps.usermgt.model.WorkingUnit;
import org.opencps.usermgt.service.ApplicantLocalServiceUtil;
import org.opencps.usermgt.service.EmployeeJobPosLocalServiceUtil;
import org.opencps.usermgt.service.EmployeeLocalServiceUtil;
import org.opencps.usermgt.service.JobPosLocalServiceUtil;
import org.opencps.usermgt.service.WorkingUnitLocalServiceUtil;
import org.opencps.usermgt.utils.DateTimeUtils;
import org.osgi.service.component.annotations.Component;

import com.liferay.asset.kernel.exception.DuplicateCategoryException;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.exception.NoSuchUserException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Role;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.portlet.LiferayWindowState;
import com.liferay.portal.kernel.portlet.PortletConfigFactoryUtil;
import com.liferay.portal.kernel.portlet.PortletURLFactoryUtil;
import com.liferay.portal.kernel.search.Document;
import com.liferay.portal.kernel.search.Sort;
import com.liferay.portal.kernel.service.RoleLocalServiceUtil;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceContextFactory;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.theme.PortletDisplay;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.kernel.workflow.WorkflowConstants;
import com.liferay.util.bridges.freemarker.FreeMarkerPortlet;

import backend.auth.api.BackendAuthImpl;
import backend.utils.ObjectConverterUtil;

/**
 * @author huymq
 */
@Component(immediate = true, property = {
	"com.liferay.portlet.display-category=category.opencps.admin",
	"com.liferay.portlet.instanceable=true",
	"javax.portlet.display-name=frontend-web-admin Portlet",
	"com.liferay.portlet.header-portlet-javascript=/js/location.js",
	"com.liferay.portlet.header-portlet-javascript=/js/main.js",
	"com.liferay.portlet.footer-portlet-javascript=/js/dictcollection.js",
	"javax.portlet.name=" + AdminPortletKeys.ADMIN_PORTLET,
	"javax.portlet.init-param.template-path=/",
	"javax.portlet.init-param.view-template=/templates/admin.ftl",
	"javax.portlet.resource-bundle=content.Language",
	"javax.portlet.security-role-ref=power-user,user"
}, service = Portlet.class)
public class AdminPortlet extends FreeMarkerPortlet {

	@Override
	public void render(
		RenderRequest renderRequest, RenderResponse renderResponse)
		throws IOException, PortletException {

		ThemeDisplay themeDisplay =
			(ThemeDisplay) renderRequest.getAttribute(WebKeys.THEME_DISPLAY);
		PortletDisplay portletDisplay = themeDisplay.getPortletDisplay();
		String portletId = portletDisplay.getId();

		JSONObject urlObject = JSONFactoryUtil.createJSONObject();
		JSONObject apiObject = JSONFactoryUtil.createJSONObject();

		// url
		PortletURL serviceInfoListURL = PortletURLFactoryUtil.create(
			renderRequest, portletId, themeDisplay.getPlid(),
			PortletRequest.RENDER_PHASE);
		serviceInfoListURL.setPortletMode(PortletMode.VIEW);
		serviceInfoListURL.setWindowState(LiferayWindowState.EXCLUSIVE);
		serviceInfoListURL.setParameter(
			"mvcPath", "/templates/serviceinfo.ftl");
		
		PortletURL serverConfigsURL = PortletURLFactoryUtil.create(
			renderRequest, portletId, themeDisplay.getPlid(),
			PortletRequest.RENDER_PHASE);
		serverConfigsURL.setPortletMode(PortletMode.VIEW);
		serverConfigsURL.setWindowState(LiferayWindowState.EXCLUSIVE);
		serverConfigsURL.setParameter(
			"mvcPath", "/templates/serverconfigs.ftl");

		PortletURL serviceInfoFormURL = PortletURLFactoryUtil.create(
			renderRequest, portletId, themeDisplay.getPlid(),
			PortletRequest.RENDER_PHASE);
		serviceInfoFormURL.setPortletMode(PortletMode.VIEW);
		serviceInfoFormURL.setWindowState(LiferayWindowState.EXCLUSIVE);
		serviceInfoFormURL.setParameter(
			"mvcPath", "/templates/serviceinfo_form.ftl");

		PortletURL serviceFileTemplateURL = PortletURLFactoryUtil.create(
			renderRequest, portletId, themeDisplay.getPlid(),
			PortletRequest.RENDER_PHASE);
		serviceFileTemplateURL.setPortletMode(PortletMode.VIEW);
		serviceFileTemplateURL.setWindowState(LiferayWindowState.EXCLUSIVE);
		serviceFileTemplateURL.setParameter(
			"mvcPath", "/templates/serviceinfo_filetemplate.ftl");

		PortletURL serviceFileTemplateFormURL = PortletURLFactoryUtil.create(
			renderRequest, portletId, themeDisplay.getPlid(),
			PortletRequest.RENDER_PHASE);
		serviceFileTemplateFormURL.setPortletMode(PortletMode.VIEW);
		serviceFileTemplateFormURL.setWindowState(LiferayWindowState.EXCLUSIVE);
		serviceFileTemplateFormURL.setParameter(
			"mvcPath", "/templates/serviceinfo_filetemplate_form.ftl");

		PortletURL dossiertemplateURL = PortletURLFactoryUtil.create(
			renderRequest, portletId, themeDisplay.getPlid(),
			PortletRequest.RENDER_PHASE);
		dossiertemplateURL.setPortletMode(PortletMode.VIEW);
		dossiertemplateURL.setWindowState(LiferayWindowState.EXCLUSIVE);
		dossiertemplateURL.setParameter(
			"mvcPath", "/templates/dossiertemplate.ftl");

		PortletURL dossiertemplatePartURL = PortletURLFactoryUtil.create(
			renderRequest, portletId, themeDisplay.getPlid(),
			PortletRequest.RENDER_PHASE);
		dossiertemplatePartURL.setPortletMode(PortletMode.VIEW);
		dossiertemplatePartURL.setWindowState(LiferayWindowState.EXCLUSIVE);
		dossiertemplatePartURL.setParameter(
			"mvcPath", "/templates/dossiertemplate_path.ftl");

		PortletURL dossiertemplatePartFormURL = PortletURLFactoryUtil.create(
			renderRequest, portletId, themeDisplay.getPlid(),
			PortletRequest.RENDER_PHASE);
		dossiertemplatePartFormURL.setPortletMode(PortletMode.VIEW);
		dossiertemplatePartFormURL.setWindowState(LiferayWindowState.EXCLUSIVE);
		dossiertemplatePartFormURL.setParameter(
			"mvcPath", "/templates/dossiertemplate_part_form.ftl");

		PortletURL serviceInfoDetailURL = PortletURLFactoryUtil.create(
			renderRequest, portletId, themeDisplay.getPlid(),
			PortletRequest.RENDER_PHASE);
		serviceInfoDetailURL.setPortletMode(PortletMode.VIEW);
		serviceInfoDetailURL.setWindowState(LiferayWindowState.EXCLUSIVE);
		serviceInfoDetailURL.setParameter(
			"mvcPath", "/templates/serviceinfo_detail.ftl");

		PortletURL serviceProcesslURL = PortletURLFactoryUtil.create(
			renderRequest, portletId, themeDisplay.getPlid(),
			PortletRequest.RENDER_PHASE);
		serviceProcesslURL.setPortletMode(PortletMode.VIEW);
		serviceProcesslURL.setWindowState(LiferayWindowState.EXCLUSIVE);
		serviceProcesslURL.setParameter(
			"mvcPath", "/templates/serviceprocess.ftl");

		PortletURL serviceConfiglURL = PortletURLFactoryUtil.create(
			renderRequest, portletId, themeDisplay.getPlid(),
			PortletRequest.RENDER_PHASE);
		serviceConfiglURL.setPortletMode(PortletMode.VIEW);
		serviceConfiglURL.setWindowState(LiferayWindowState.EXCLUSIVE);
		serviceConfiglURL.setParameter(
			"mvcPath", "/templates/serviceconfig.ftl");

		PortletURL serviceConfigDetaillURL = PortletURLFactoryUtil.create(
			renderRequest, portletId, themeDisplay.getPlid(),
			PortletRequest.RENDER_PHASE);
		serviceConfigDetaillURL.setPortletMode(PortletMode.VIEW);
		serviceConfigDetaillURL.setWindowState(LiferayWindowState.EXCLUSIVE);
		serviceConfigDetaillURL.setParameter(
			"mvcPath", "/templates/serviceconfig_detail.ftl");

		PortletURL serviceConfigOptionFormURL = PortletURLFactoryUtil.create(
			renderRequest, portletId, themeDisplay.getPlid(),
			PortletRequest.RENDER_PHASE);
		serviceConfigOptionFormURL.setPortletMode(PortletMode.VIEW);
		serviceConfigOptionFormURL.setWindowState(LiferayWindowState.EXCLUSIVE);
		serviceConfigOptionFormURL.setParameter(
			"mvcPath", "/templates/serviceconfig_option_form.ftl");

		PortletURL serviceConfigOptionURL = PortletURLFactoryUtil.create(
			renderRequest, portletId, themeDisplay.getPlid(),
			PortletRequest.RENDER_PHASE);
		serviceConfigOptionURL.setPortletMode(PortletMode.VIEW);
		serviceConfigOptionURL.setWindowState(LiferayWindowState.EXCLUSIVE);
		serviceConfigOptionURL.setParameter(
			"mvcPath", "/templates/serviceconfig_option.ftl");

		PortletURL manageAccountURL = PortletURLFactoryUtil.create(
			renderRequest, portletId, themeDisplay.getPlid(),
			PortletRequest.RENDER_PHASE);
		manageAccountURL.setPortletMode(PortletMode.VIEW);
		manageAccountURL.setWindowState(LiferayWindowState.EXCLUSIVE);
		manageAccountURL.setParameter(
			"mvcPath", "/templates/manage_account.ftl");

		PortletURL paymentConfigtURL = PortletURLFactoryUtil.create(
			renderRequest, portletId, themeDisplay.getPlid(),
			PortletRequest.RENDER_PHASE);
		paymentConfigtURL.setPortletMode(PortletMode.VIEW);
		paymentConfigtURL.setWindowState(LiferayWindowState.EXCLUSIVE);
		paymentConfigtURL.setParameter(
			"mvcPath", "/templates/paymentconfig.ftl");

		PortletURL paymentConfigFormURL = PortletURLFactoryUtil.create(
			renderRequest, portletId, themeDisplay.getPlid(),
			PortletRequest.RENDER_PHASE);
		paymentConfigFormURL.setPortletMode(PortletMode.VIEW);
		paymentConfigFormURL.setWindowState(LiferayWindowState.EXCLUSIVE);
		paymentConfigFormURL.setParameter(
			"mvcPath", "/templates/paymentconfig_form.ftl");

		PortletURL dataMgtURL = PortletURLFactoryUtil.create(
			renderRequest, portletId, themeDisplay.getPlid(),
			PortletRequest.RENDER_PHASE);
		dataMgtURL.setPortletMode(PortletMode.VIEW);
		dataMgtURL.setWindowState(LiferayWindowState.EXCLUSIVE);
		dataMgtURL.setParameter(
			"mvcPath", "/templates/datamgt/dictcollection_index.ftl");
		
		PortletURL dataTempMgtURL = PortletURLFactoryUtil.create(
				renderRequest, portletId, themeDisplay.getPlid(),
				PortletRequest.RENDER_PHASE);
		dataTempMgtURL.setPortletMode(PortletMode.VIEW);
		dataTempMgtURL.setWindowState(LiferayWindowState.EXCLUSIVE);

		PortletURL registrationTemplatesURL = PortletURLFactoryUtil.create(
			renderRequest, portletId, themeDisplay.getPlid(),
			PortletRequest.RENDER_PHASE);
		registrationTemplatesURL.setPortletMode(PortletMode.VIEW);
		registrationTemplatesURL.setWindowState(LiferayWindowState.EXCLUSIVE);
		registrationTemplatesURL.setParameter(
			"mvcPath", "/templates/registrationtemplates.ftl");
		
		PortletURL certNumberURL = PortletURLFactoryUtil.create(
			renderRequest, portletId, themeDisplay.getPlid(),
			PortletRequest.RENDER_PHASE);
		certNumberURL.setPortletMode(PortletMode.VIEW);
		certNumberURL.setWindowState(LiferayWindowState.EXCLUSIVE);
		certNumberURL.setParameter(
			"mvcPath", "/templates/certNumber.ftl");
		
		
		urlObject.put("registrationtemplates", registrationTemplatesURL.toString());
		urlObject.put("serviceinfo_list", serviceInfoListURL.toString());
		urlObject.put("serviceinfo_form", serviceInfoFormURL.toString());
		urlObject.put(
			"serviceinfo_filetemplate", serviceFileTemplateURL.toString());
		urlObject.put(
			"serviceinfo_filetemplate_form",
			serviceFileTemplateFormURL.toString());
		urlObject.put("dossiertemplate", dossiertemplateURL.toString());
		urlObject.put(
			"dossiertemplate_part", dossiertemplatePartURL.toString());
		urlObject.put(
			"dossiertemplate_part_form", dossiertemplatePartFormURL.toString());
		urlObject.put("serviceinfo_detail", serviceInfoDetailURL.toString());
		urlObject.put("serviceprocess", serviceProcesslURL.toString());
		urlObject.put("serviceconfig", serviceConfiglURL.toString());
		urlObject.put(
			"serviceconfig_detail", serviceConfigDetaillURL.toString());
		urlObject.put(
			"serviceconfig_option_form", serviceConfigOptionFormURL.toString());
		urlObject.put(
			"serviceconfig_option", serviceConfigOptionURL.toString());
		urlObject.put("manage_account", manageAccountURL.toString());
		urlObject.put("payment_config", paymentConfigtURL.toString());
		urlObject.put("paymentconfig_form", paymentConfigFormURL.toString());
		urlObject.put("dictcollection_index", dataMgtURL.toString());
		urlObject.put("dictcollectiontemp_index", dataTempMgtURL.toString());
		urlObject.put("serverconfigs", serverConfigsURL.toString());
		urlObject.put("certnumber", certNumberURL.toString());
		
		// set object edit
		long serviceInfoId = ParamUtil.getLong(renderRequest, "serviceInfoId");
		if (serviceInfoId > 0) {
			try {
				ServiceInfo serviceInfo =
					ServiceInfoLocalServiceUtil.getServiceInfo(serviceInfoId);

				renderRequest.setAttribute("SERVICE_INFO", serviceInfo);
			}
			catch (Exception e) {

			}
		}

		Applicant applicant = ApplicantLocalServiceUtil.fetchByMappingID(
			themeDisplay.getUserId());

		// roles
		List<Role> roles =
			RoleLocalServiceUtil.getRoles(themeDisplay.getCompanyId());

		// api
		apiObject.put("server", themeDisplay.getPortalURL() + "/o/rest/v2");
		apiObject.put("endpoint", themeDisplay.getPortalURL() + "/o/rest/v2");
		apiObject.put(
			"portletNamespace",
			themeDisplay.getPortletDisplay().getNamespace());
		apiObject.put("roles", roles);

		// set varible
		renderRequest.setAttribute("ajax", urlObject);
		renderRequest.setAttribute("api", apiObject);
		renderRequest.setAttribute(
			"applicantId", applicant == null ? "" : applicant.getApplicantId());

		// render from mobilink
		renderFromMobilink(renderRequest, renderResponse);

		super.render(renderRequest, renderResponse);

	}

	private void renderFromMobilink(
		RenderRequest renderRequest, RenderResponse renderResponse)
		throws IOException, PortletException {

		renderFrontendWebEmployeePortlet(renderRequest, renderResponse);
		renderFrontendWebJobposPortlet(renderRequest, renderResponse);
		renderFrontendWebAdminPortlet(renderRequest, renderResponse);
		renderFrontendWebWorkingUnitPortlet(renderRequest, renderResponse);
		renderFrontendWebNotificationPortlet(renderRequest, renderResponse);

		renderRequest.setAttribute(
			"url", generateURLCommon(renderRequest, renderResponse));

		renderRequest.setAttribute("constants", generalConstantsCommon(renderRequest));

		renderRequest.setAttribute("param", generalParamsCommon(renderRequest));
	}
	
	private void renderFrontendWebNotificationPortlet(
			RenderRequest renderRequest, RenderResponse renderResponse) {
		
		ThemeDisplay themeDisplay =
			(ThemeDisplay) renderRequest.getAttribute(WebKeys.THEME_DISPLAY);

		ServiceContext serviceContext = null;
		
		try {
			serviceContext = ServiceContextFactory.getInstance(renderRequest);
		}
		catch (Exception e) {
			_log.error(e);
			throw new NullPointerException();
		}

		long groupId = themeDisplay.getScopeGroupId();

		long userId = themeDisplay.getUserId();

		String notificationType =
			ParamUtil.getString(renderRequest, "notificationType");

		NotificationTemplateActions notificationTemplateActions =
			new NotificationTemplateActions();

		Notificationtemplate notificationTemplate = null;

		if (Validator.isNotNull(notificationType)) {
			try {

				notificationTemplate = notificationTemplateActions.read(
					userId, groupId, notificationType, serviceContext);
				JSONObject object = ObjectConverterUtil.objectToJSON(
					notificationTemplate.getClass(), notificationTemplate);
				Map<String, String> initTemplates = NotificationMGTConstants.NOTIFICATION_TEMPLATE_INIT;
				
				object.put("typeName", initTemplates.get(notificationTemplate.getNotificationType()));
				renderRequest.setAttribute("notificationTemplate", object);

			}
			catch (Exception e) {
				_log.error(e);
			}
		}
	}

	public void renderFrontendWebWorkingUnitPortlet(
		RenderRequest renderRequest, RenderResponse renderResponse)
		throws IOException, PortletException {

		ThemeDisplay themeDisplay =
			(ThemeDisplay) renderRequest.getAttribute(WebKeys.THEME_DISPLAY);

		ServiceContext serviceContext = null;

		try {
			serviceContext = ServiceContextFactory.getInstance(renderRequest);
		}
		catch (Exception e) {
			_log.error(e);
			throw new NullPointerException();
		}

		BackendAuthImpl backendAuthImpl = new BackendAuthImpl();

		long groupId = themeDisplay.getScopeGroupId();

		long userId = themeDisplay.getUserId();

		boolean isOmniadmin =
			backendAuthImpl.isAdmin(serviceContext, StringPool.BLANK);

		long workingUnitId = ParamUtil.getLong(renderRequest, "workingUnitId");

		WorkingUnit workingUnit = null;

		if (workingUnitId > 0) {
			try {
				workingUnit =
					WorkingUnitLocalServiceUtil.fetchWorkingUnit(workingUnitId);

				JSONObject jsonWorkingUnit = JSONFactoryUtil.createJSONObject();

				jsonWorkingUnit = ObjectConverterUtil.objectToJSON(
					workingUnit.getClass(), workingUnit);

				renderRequest.setAttribute("workingUnit", jsonWorkingUnit);
			}
			catch (Exception e) {
				_log.error(e);
			}
		}

		renderRequest.setAttribute("groupId", groupId);

		renderRequest.setAttribute("userId", userId);

		renderRequest.setAttribute("isOmniadmin", isOmniadmin);

		renderRequest.setAttribute(
			"portletNamespace",
			themeDisplay.getPortletDisplay().getNamespace());

	}

	private void renderFrontendWebAdminPortlet(
		RenderRequest renderRequest, RenderResponse renderResponse)
		throws IOException, PortletException {

		ThemeDisplay themeDisplay =
			(ThemeDisplay) renderRequest.getAttribute(WebKeys.THEME_DISPLAY);

		ServiceContext serviceContext = null;

		try {
			serviceContext = ServiceContextFactory.getInstance(renderRequest);
		}
		catch (Exception e) {
			_log.error(e);
			throw new NullPointerException();
		}

		BackendAuthImpl backendAuthImpl = new BackendAuthImpl();

		String type = ParamUtil.getString(renderRequest, "type");

		long groupId = themeDisplay.getScopeGroupId();

		long userId = themeDisplay.getUserId();

		boolean isOmniadmin =
			backendAuthImpl.isAdmin(serviceContext, StringPool.BLANK);

		if (type.equals(
			FrontendWebAdminPortletConstants.AdminMenuItemType.ACTIVITY.toString()) ||
			type.equals(
				FrontendWebAdminPortletConstants.AdminMenuItemType.DICTCOLLECTION.toString()) ||
			type.equals(
				FrontendWebAdminPortletConstants.AdminMenuItemType.DOCUMENT.toString())) {
			DictCollectionActions collectionActions =
				new DictCollectionActions();
			String collectionCode =
				ParamUtil.getString(renderRequest, "collectionCode");

			String itemCode = ParamUtil.getString(renderRequest, "itemCode");

			String groupCode = ParamUtil.getString(renderRequest, "groupCode");

			DictItem dictItem = null;

			JSONObject dictItemJSON = null;

			DictCollection dictCollection = null;

			DictGroup dictGroup = null;

			try {

				if (Validator.isNotNull(collectionCode)) {

					dictCollection = collectionActions.getDictCollectionDetail(
						collectionCode, groupId);

				}
				if (Validator.isNotNull(itemCode) &&
					Validator.isNotNull(collectionCode)) {

					dictItem = collectionActions.getDictItemByItemCode(
						collectionCode, itemCode, groupId, serviceContext);

					dictItemJSON = ObjectConverterUtil.objectToJSON(
						dictItem.getClass(), dictItem);

					if (Validator.isNotNull(dictItem) &&
						Validator.isNotNull(dictItemJSON)) {

						dictItemJSON.put(
							"groupCode", getListDictGroupByDictItem(
								dictItem, serviceContext));

						if (dictItem.getParentItemId() > 0) {

							dictItem = DictItemLocalServiceUtil.fetchDictItem(
								dictItem.getParentItemId());
							dictItemJSON.put(
								"parentItemCode", dictItem.getItemCode());

						}

					}

				}

				if (Validator.isNotNull(groupCode)) {
					dictGroup =
						DictGroupLocalServiceUtil.fetchByF_DictGroupCode(
							groupCode, groupId);
				}

				if (Validator.isNotNull(dictItem)) {

					dictItem = collectionActions.getDictItemByItemCode(
						collectionCode, dictItem.getItemCode(), groupId,
						serviceContext);

				}

				renderRequest.setAttribute(
					"activityType_dictItem", dictItemJSON);

				renderRequest.setAttribute(
					"documentType_dictItem", dictItemJSON);

				renderRequest.setAttribute(
					"dictCollection_dictItem", dictItemJSON);

				renderRequest.setAttribute(
					"dictCollection_dictGroup", dictGroup);

				renderRequest.setAttribute(
					"dictCollection_dictCollection", dictCollection);

				// JSONFactoryUtil.looseSerialize(dictItem);
			}
			catch (Exception e) {
				_log.error(e);
			}
		}
		else if (type.equals(
			FrontendWebAdminPortletConstants.AdminMenuItemType.NOTIFICATIONTEMPLATE.toString())) {
			// Include portlet
		}

		renderRequest.setAttribute("groupId", groupId);

		renderRequest.setAttribute("userId", userId);

		renderRequest.setAttribute("isOmniadmin", isOmniadmin);

		renderRequest.setAttribute(
			"portletNamespace",
			themeDisplay.getPortletDisplay().getNamespace());

	}

	private String getListDictGroupByDictItem(
		DictItem dictItem, ServiceContext serviceContext) {

		List<String> result = new ArrayList<String>();
		LinkedHashMap<String, Object> params =
			new LinkedHashMap<String, Object>();
		DictcollectionInterface dictItemDataUtil = new DictCollectionActions();
		params.put("groupId", String.valueOf(dictItem.getGroupId()));
		params.put(
			DictItemGroupTerm.DICT_ITEM_ID,
			String.valueOf(dictItem.getDictItemId()));

		JSONObject jsonData = dictItemDataUtil.getDictItemsGroup(
			dictItem.getUserId(), dictItem.getCompanyId(),
			dictItem.getGroupId(), params, new Sort[] {}, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, serviceContext);

		try {

			// TODO template commented
			// @SuppressWarnings("unchecked")
			List<Document> listResults = (List<Document>) jsonData.get("data");

			for (Document document : listResults) {

				result.add(document.get(DictGroupTerm.GROUP_CODE).toString());

			}

		}
		catch (Exception e) {

			_log.error(e);
		}
		
		_log.info("Dict_GROUP =====================>"+String.join(",", result));
		return String.join(",", result);

	}

	private void renderFrontendWebJobposPortlet(
		RenderRequest renderRequest, RenderResponse renderResponse)
		throws IOException, PortletException {

		ThemeDisplay themeDisplay =
			(ThemeDisplay) renderRequest.getAttribute(WebKeys.THEME_DISPLAY);

		ServiceContext serviceContext = null;

		try {
			serviceContext = ServiceContextFactory.getInstance(renderRequest);
		}
		catch (Exception e) {
			_log.error(e);
			throw new NullPointerException();
		}

		BackendAuthImpl backendAuthImpl = new BackendAuthImpl();

		long groupId = themeDisplay.getScopeGroupId();

		long userId = themeDisplay.getUserId();

		boolean isOmniadmin =
			backendAuthImpl.isAdmin(serviceContext, StringPool.BLANK);

		long jobPosId = ParamUtil.getLong(renderRequest, "jobPosId");

		JobPos jobPos = null;

		if (jobPosId > 0) {
			try {
				jobPos = JobPosLocalServiceUtil.fetchJobPos(jobPosId);
			}
			catch (Exception e) {
				_log.error(e);
			}
		}

		renderRequest.setAttribute("jobPos", jobPos);

		renderRequest.setAttribute("groupId", groupId);

		renderRequest.setAttribute("userId", userId);

		renderRequest.setAttribute("isOmniadmin", isOmniadmin);

		renderRequest.setAttribute(
			"portletNamespace",
			themeDisplay.getPortletDisplay().getNamespace());

	}

	private void renderFrontendWebEmployeePortlet(
		RenderRequest renderRequest, RenderResponse renderResponse)
		throws IOException, PortletException {

		ThemeDisplay themeDisplay =
			(ThemeDisplay) renderRequest.getAttribute(WebKeys.THEME_DISPLAY);

		// EmployeeActions employeeActions = new EmployeeActions();

		ServiceContext serviceContext = null;

		try {
			serviceContext = ServiceContextFactory.getInstance(renderRequest);
		}
		catch (Exception e) {
			_log.error(e);
			throw new NullPointerException();
		}

		BackendAuthImpl backendAuthImpl = new BackendAuthImpl();

		long groupId = themeDisplay.getScopeGroupId();

		long userId = themeDisplay.getUserId();

		boolean isOmniadmin =
			backendAuthImpl.isAdmin(serviceContext, StringPool.BLANK);

		long employeeId = ParamUtil.getLong(renderRequest, "employeeId");

		Employee employee = null;

		JSONObject result = JSONFactoryUtil.createJSONObject();

		if (employeeId > 0) {
			try {
				employee = EmployeeLocalServiceUtil.fetchEmployee(employeeId);

				result = ObjectConverterUtil.objectToJSON(
					employee.getClass(), employee);

				long mappingUserId = employee.getMappingUserId();

				if (mappingUserId > 0) {
					User user = UserLocalServiceUtil.getUser(mappingUserId);
					JSONObject userInfo = JSONFactoryUtil.createJSONObject();

					userInfo.put("email", user.getEmailAddress());
					userInfo.put("screenName", user.getScreenName());
					userInfo.put(
						"lock",
						user.getStatus() == WorkflowConstants.STATUS_APPROVED
							? false : true);

					renderRequest.setAttribute(
						"employee_accountInfo", userInfo);

				}

				renderRequest.setAttribute(
					"employee_fileAttachs",
					getFileAttachment(employeeId, groupId));

			}
			catch (Exception e) {
				_log.error(e);
			}
		}

		if (employee != null) {
			try {
				List<JSONObject> results = new ArrayList<JSONObject>();
				List<EmployeeJobPos> employeeJobPos =
					EmployeeJobPosLocalServiceUtil.findByF_EmployeeId(
						employeeId);
				for (EmployeeJobPos empjobpos : employeeJobPos) {
					long jobPosId = empjobpos.getJobPostId();
					long workingUnitId = empjobpos.getWorkingUnitId();
					boolean mainJobPos =
						empjobpos.getEmployeeJobPosId() == employee.getMainJobPostId()
							? true : false;

					JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

					jsonObject = ObjectConverterUtil.objectToJSON(
						empjobpos.getClass(), empjobpos);

					jsonObject.put("mainJobPos", mainJobPos);

					jsonObject.put(
						"employeeJobPosId", empjobpos.getEmployeeJobPosId());

					try {
						JobPos jobPos =
							JobPosLocalServiceUtil.getJobPos(jobPosId);
						WorkingUnit workingUnit =
							WorkingUnitLocalServiceUtil.getWorkingUnit(
								workingUnitId);

						if (jobPos != null) {
							jsonObject.put("leader", jobPos.getLeader());
							jsonObject.put("jobPosTitle", jobPos.getTitle());
							jsonObject.put("jobPosId", jobPos.getJobPosId());
						}

						if (workingUnit != null) {
							jsonObject.put(
								"workingUnitName", workingUnit.getName());
							jsonObject.put(
								"workingUnitId",
								workingUnit.getWorkingUnitId());

						}

					}
					catch (Exception e) {
						continue;
					}

					results.add(jsonObject);

				}

				renderRequest.setAttribute("employee_jobPos", results);
			}
			catch (Exception e) {
				_log.error(e);
			}
		}

		PortletConfig portletConfig = PortletConfigFactoryUtil.get(
			themeDisplay.getPortletDisplay().getId());

		ResourceBundle resourceBundle =
			portletConfig.getResourceBundle(themeDisplay.getLocale());

		// ResourceBundle resourceBundle =
		// ResourceBundle.getBundle("content.Language", UTF8Control.INSTANCE);

		List<JSONObject> workingStatus = new ArrayList<JSONObject>();

		JSONObject working = JSONFactoryUtil.createJSONObject();
		working.put("status", 1);
		working.put(
			"value", LanguageUtil.get(resourceBundle, "working-status"));
		JSONObject notworking = JSONFactoryUtil.createJSONObject();
		notworking.put("status", 0);
		notworking.put(
			"value", LanguageUtil.get(resourceBundle, "notworking-status"));

		workingStatus.add(working);
		workingStatus.add(notworking);

		renderRequest.setAttribute("employee_workingStatus", workingStatus);

		renderRequest.setAttribute("employee", result);

		renderRequest.setAttribute("groupId", groupId);

		renderRequest.setAttribute("userId", userId);

		renderRequest.setAttribute("isOmniadmin", isOmniadmin);

		renderRequest.setAttribute(
			"portletNamespace",
			themeDisplay.getPortletDisplay().getNamespace());

	}

	private JSONObject generalParamsCommon(RenderRequest renderRequest) {

		JSONObject params = JSONFactoryUtil.createJSONObject();

		// FrontendWebEmployeePortlet
		long employeeId = ParamUtil.getLong(renderRequest, "employeeId");
		params.put("employeeId", employeeId);

		// FrontendWebJobposPortlet
		long jobPosId = ParamUtil.getLong(renderRequest, "jobPosId");
		params.put("jobPos_jobPosId", jobPosId);

		// FrontendWebAdminPortlet
		ServiceContext serviceContext = null;

		try {
			serviceContext = ServiceContextFactory.getInstance(renderRequest);
		}
		catch (Exception e) {
			_log.error(e);
			throw new NullPointerException();
		}

		long groupId = serviceContext.getScopeGroupId();

		DictCollectionActions collectionActions = new DictCollectionActions();

		String collectionCode =
			ParamUtil.getString(renderRequest, "collectionCode");

		String itemCode = ParamUtil.getString(renderRequest, "itemCode");

		String groupCode = ParamUtil.getString(renderRequest, "groupCode");

		DictItem dictItem = null;

		JSONObject dictItemJSON = null;

		try {

			if (Validator.isNotNull(itemCode) &&
				Validator.isNotNull(collectionCode)) {

				dictItem = collectionActions.getDictItemByItemCode(
					collectionCode, itemCode, groupId, serviceContext);

				dictItemJSON = ObjectConverterUtil.objectToJSON(
					dictItem.getClass(), dictItem);

				if (Validator.isNotNull(dictItem) &&
					Validator.isNotNull(dictItemJSON)) {
					dictItemJSON.put(
						"groupCode",
						getListDictGroupByDictItem(dictItem, serviceContext));
					if (dictItem.getParentItemId() > 0) {
						dictItem = DictItemLocalServiceUtil.fetchDictItem(
							dictItem.getParentItemId());
						dictItemJSON.put(
							"parentItemCode", dictItem.getItemCode());
					}
				}
			}

			if (Validator.isNotNull(dictItem)) {
				dictItem = collectionActions.getDictItemByItemCode(
					collectionCode, dictItem.getItemCode(), groupId,
					serviceContext);
			}
		}
		catch (Exception e) {

		}

		params.put("dictCollection_groupCode", groupCode);
		params.put("activityType_itemCode", itemCode);
		params.put("documentType_itemCode", itemCode);
		params.put("dictCollection_collectionCode", collectionCode);
		params.put("dictCollection_itemCode", itemCode);

		// FrontendWebWorkingUnitPortlet
		long workingUnitId = ParamUtil.getLong(renderRequest, "workingUnitId");

		params.put("workingUnit_workingUnitId", workingUnitId);
		params.put("workingUnit_className", WorkingUnit.class.getName());

		return params;
	}

	private JSONObject generalConstantsCommon(RenderRequest renderRequest) {

		JSONObject constants = JSONFactoryUtil.createJSONObject();

		// FrontendWebEmployeePortlet
		constants.put("className", Employee.class.getName());
		constants.put("collection_Academic", "ACADEMIC");
		constants.put("alpaca_templateNo", "EMPLOYEE");

		// FrontendWebJobposPortlet
		constants.put(
			"type_jobPos",
			FrontendWebAdminPortletConstants.AdminMenuItemType.JOBPOS.toString());

		// FrontendWebAdminPortlet
		constants.put(
			"type_activityType",
			FrontendWebAdminPortletConstants.AdminMenuItemType.ACTIVITY.toString());
		constants.put(
			"type_documentType",
			FrontendWebAdminPortletConstants.AdminMenuItemType.DOCUMENT.toString());
		constants.put(
			"type_dictCollection",
			FrontendWebAdminPortletConstants.AdminMenuItemType.DICTCOLLECTION.toString());
		constants.put(
			"type_workingUnit",
			FrontendWebAdminPortletConstants.AdminMenuItemType.WORKINGUNIT.toString());
		constants.put(
			"type_workspace",
			FrontendWebAdminPortletConstants.AdminMenuItemType.WORKSPACE.toString());
		constants.put(
			"type_jobPos",
			FrontendWebAdminPortletConstants.AdminMenuItemType.JOBPOS.toString());
		constants.put(
			"type_contact",
			FrontendWebAdminPortletConstants.AdminMenuItemType.CONTACT.toString());
		constants.put(
			"type_officeSite",
			FrontendWebAdminPortletConstants.AdminMenuItemType.OFFICESITE.toString());
		constants.put(
			"type_location",
			FrontendWebAdminPortletConstants.AdminMenuItemType.LOCATION.toString());
		constants.put(
			"type_label",
			FrontendWebAdminPortletConstants.AdminMenuItemType.LABEL.toString());
		constants.put(
			"type_notificationTemplate",
			FrontendWebAdminPortletConstants.AdminMenuItemType.NOTIFICATIONTEMPLATE.toString());
		constants.put(
			"label_activityClassName",
			FrontendWebAdminPortletConstants._ACTIVITY_CLASSNAME);
		constants.put(
			"label_documentClassName",
			FrontendWebAdminPortletConstants._DOCUMENT_CLASSNAME);
		constants.put(
			"officeSite_officeSiteClassName",
			FrontendWebAdminPortletConstants._OFFICESITE_CLASSNAME);

		// FrontendWebWorkingUnitPortlet
		constants.put(
			"type_workingUnit",
			FrontendWebAdminPortletConstants.AdminMenuItemType.WORKINGUNIT.toString());

		constants.put(
			"workingUnit_workingUnitClassName", WorkingUnit.class.getName());
		
		constants.put(
				"notification_interval", generateNotiIntervals(renderRequest));

		return constants;
	}
	
	private List<JSONObject> generateNotiIntervals(RenderRequest renderRequest) {
		
		ThemeDisplay themeDisplay =
			(ThemeDisplay) renderRequest.getAttribute(WebKeys.THEME_DISPLAY);
		PortletConfig portletConfig = PortletConfigFactoryUtil.get(
			themeDisplay.getPortletDisplay().getId());

		ResourceBundle resourceBundle =
			portletConfig.getResourceBundle(themeDisplay.getLocale());
		
		List<JSONObject> intervals = new ArrayList<JSONObject>();
		JSONObject interval1 = JSONFactoryUtil.createJSONObject();
		interval1.put("value", "minutely");
		interval1.put("text", LanguageUtil.get(resourceBundle, "minutely"));
		intervals.add(interval1);

		JSONObject interval2 = JSONFactoryUtil.createJSONObject();
		interval2.put("value", "5-mins");
		interval2.put("text", LanguageUtil.get(resourceBundle, "5-mins"));
		intervals.add(interval2);

		JSONObject interval3 = JSONFactoryUtil.createJSONObject();
		interval3.put("value", "15-mins");
		interval3.put("text", LanguageUtil.get(resourceBundle, "15-mins"));
		intervals.add(interval3);

		JSONObject interval4 = JSONFactoryUtil.createJSONObject();
		interval4.put("value", "30-mins");
		interval4.put("text", LanguageUtil.get(resourceBundle, "30-mins"));
		intervals.add(interval4);

		JSONObject interval5 = JSONFactoryUtil.createJSONObject();
		interval5.put("value", "hourly");
		interval5.put("text", LanguageUtil.get(resourceBundle, "hourly"));
		intervals.add(interval5);

		JSONObject interval6 = JSONFactoryUtil.createJSONObject();
		interval6.put("value", "daily");
		interval6.put("text", LanguageUtil.get(resourceBundle, "daily"));
		intervals.add(interval6);
		
		return intervals;
		
	}

	private JSONObject generateURLCommon(
		RenderRequest renderRequest, RenderResponse renderResponse)
		throws WindowStateException {

		JSONObject portletURLs = JSONFactoryUtil.createJSONObject();

		try {

			///////////////////// FrontendWebEmployeePortlet
			JSONObject employeePortlet = JSONFactoryUtil.createJSONObject();

			PortletURL employeeIndexURL = renderResponse.createRenderURL();
			employeeIndexURL.setParameter("mvcPath", "/templates/employee/employee_index.ftl");
			employeeIndexURL.setWindowState(LiferayWindowState.EXCLUSIVE);

			employeePortlet.put("employee_index", employeeIndexURL);

			PortletURL employeeCreateURL = renderResponse.createRenderURL();
			employeeCreateURL.setParameter(
				"mvcPath", "/templates/employee/employee_create.ftl");
			employeeCreateURL.setWindowState(LiferayWindowState.EXCLUSIVE);

			employeePortlet.put("employee_create", employeeCreateURL);

			PortletURL employeeDetailURL = renderResponse.createRenderURL();
			employeeDetailURL.setParameter(
				"mvcPath", "/templates/employee/employee_detail.ftl");
			employeeDetailURL.setWindowState(LiferayWindowState.EXCLUSIVE);

			employeePortlet.put("employee_detail", employeeDetailURL);

			PortletURL employeeDetaiUpdateJobposlURL =
				renderResponse.createRenderURL();
			employeeDetaiUpdateJobposlURL.setParameter(
				"mvcPath",
				"/templates/employee/employee_detail_update_jobpos.ftl");
			employeeDetaiUpdateJobposlURL.setWindowState(
				LiferayWindowState.EXCLUSIVE);

			employeePortlet.put(
				"employee_detail_update_jobpos", employeeDetaiUpdateJobposlURL);

			PortletURL employeeBirthdatelURL = renderResponse.createRenderURL();
			employeeBirthdatelURL.setParameter(
				"mvcPath", "/templates/employee/employee_birthdate.ftl");
			employeeBirthdatelURL.setWindowState(LiferayWindowState.EXCLUSIVE);

			employeePortlet.put("employee_birthdate", employeeBirthdatelURL);

			portletURLs.put("employeePortlet", employeePortlet);

			///////////////////// FrontendWebJobposPortlet
			JSONObject adminJobPosPortlet = JSONFactoryUtil.createJSONObject();

			PortletURL jobposListURL = renderResponse.createRenderURL();
			jobposListURL.setParameter(
				"mvcPath", "/templates/jobpos/jobpos_list.ftl");
			jobposListURL.setWindowState(LiferayWindowState.EXCLUSIVE);

			adminJobPosPortlet.put("jobpos_list", jobposListURL);

			PortletURL jobposDetailURL = renderResponse.createRenderURL();
			jobposDetailURL.setParameter(
				"mvcPath", "/templates/jobpos/jobpos_detail.ftl");
			jobposDetailURL.setWindowState(LiferayWindowState.EXCLUSIVE);

			adminJobPosPortlet.put("jobpos_detail", jobposDetailURL);

			PortletURL jobposCreateURL = renderResponse.createRenderURL();
			jobposCreateURL.setParameter(
				"mvcPath", "/templates/jobpos/jobpos_create.ftl");
			jobposCreateURL.setWindowState(LiferayWindowState.EXCLUSIVE);

			adminJobPosPortlet.put("jobpos_create", jobposCreateURL);

			PortletURL jobposSaveURL = renderResponse.createActionURL();

			jobposSaveURL.setParameter(ActionRequest.ACTION_NAME, "saveJobPos");
			jobposSaveURL.setWindowState(LiferayWindowState.EXCLUSIVE);
			adminJobPosPortlet.put("jobpos_edit_action", jobposSaveURL);

			portletURLs.put("adminJobPosPortlet", adminJobPosPortlet);

			///////////////////// FrontendWebAdminPortlet
			JSONObject adminDataMgtPortlet = JSONFactoryUtil.createJSONObject();

			PortletURL activityTypeListURL = renderResponse.createRenderURL();
			activityTypeListURL.setParameter(
				"mvcPath", "/templates/html/activity_type_list.ftl");
			activityTypeListURL.setWindowState(LiferayWindowState.EXCLUSIVE);

			adminDataMgtPortlet.put("activity_type_list", activityTypeListURL);

			PortletURL activityTypeDetailURL = renderResponse.createRenderURL();
			activityTypeDetailURL.setParameter(
				"mvcPath", "/templates/html/activity_type_detail.ftl");
			activityTypeDetailURL.setWindowState(LiferayWindowState.EXCLUSIVE);

			adminDataMgtPortlet.put(
				"activity_type_detail", activityTypeDetailURL);

			PortletURL activityTypeCreateURL = renderResponse.createRenderURL();
			activityTypeCreateURL.setParameter(
				"mvcPath", "/templates/html/activity_type_create.ftl");
			activityTypeCreateURL.setWindowState(LiferayWindowState.EXCLUSIVE);

			adminDataMgtPortlet.put(
				"activity_type_create", activityTypeCreateURL);

			PortletURL dictCollectionListURL = renderResponse.createRenderURL();
			dictCollectionListURL.setParameter(
				"mvcPath", "/templates/datamgt/dictcollection_list.ftl");
			dictCollectionListURL.setWindowState(LiferayWindowState.EXCLUSIVE);

			adminDataMgtPortlet.put(
				"dictcollection_list", dictCollectionListURL);

			PortletURL dictCollectionDetailURL =
				renderResponse.createRenderURL();
			dictCollectionDetailURL.setParameter(
				"mvcPath", "/templates/datamgt/dictcollection_detail.ftl");
			dictCollectionDetailURL.setWindowState(
				LiferayWindowState.EXCLUSIVE);

			adminDataMgtPortlet.put(
				"dictcollection_detail", dictCollectionDetailURL);

			PortletURL dictCollectionInfoURL = renderResponse.createRenderURL();
			dictCollectionInfoURL.setParameter(
				"mvcPath", "/templates/datamgt/dictcollection_detail_info.ftl");
			dictCollectionInfoURL.setWindowState(LiferayWindowState.EXCLUSIVE);

			adminDataMgtPortlet.put(
				"dictcollection_detail_info", dictCollectionInfoURL);

			PortletURL dictCollectionDetailDictItemURL =
				renderResponse.createRenderURL();
			dictCollectionDetailDictItemURL.setParameter(
				"mvcPath",
				"/templates/datamgt/dictcollection_detail_dictitem.ftl");
			dictCollectionDetailDictItemURL.setWindowState(
				LiferayWindowState.EXCLUSIVE);

			adminDataMgtPortlet.put(
				"dictcollection_detail_dictitem",
				dictCollectionDetailDictItemURL);
			
			PortletURL dictCollectionDetailDictItem_2_URL =
							renderResponse.createRenderURL();
				dictCollectionDetailDictItem_2_URL.setParameter(
							"mvcPath",
							"/templates/datamgt/dictcollection_detail_dictitem_2.ftl");
				dictCollectionDetailDictItem_2_URL.setWindowState(
							LiferayWindowState.EXCLUSIVE);

			adminDataMgtPortlet.put(
				"dictcollection_detail_dictitem_2",
				dictCollectionDetailDictItem_2_URL);

			PortletURL dictCollectionDetailFormTemplateURL =
				renderResponse.createRenderURL();
			dictCollectionDetailFormTemplateURL.setParameter(
				"mvcPath",
				"/templates/datamgt/dictcollection_detail_formtemplate.ftl");
			dictCollectionDetailFormTemplateURL.setWindowState(
				LiferayWindowState.EXCLUSIVE);

			adminDataMgtPortlet.put(
				"dictcollection_detail_formtemplate",
				dictCollectionDetailFormTemplateURL);

			PortletURL dictCollectionCreateDictCollectionURL =
				renderResponse.createRenderURL();
			dictCollectionCreateDictCollectionURL.setParameter(
				"mvcPath",
				"/templates/datamgt/dictcollection_create_dictcollection.ftl");
			dictCollectionCreateDictCollectionURL.setWindowState(
				LiferayWindowState.EXCLUSIVE);

			adminDataMgtPortlet.put(
				"dictcollection_create_dictcollection",
				dictCollectionCreateDictCollectionURL);

			PortletURL dictCollectionCreateDictItemURL =
					renderResponse.createRenderURL();
				dictCollectionCreateDictItemURL.setParameter(
					"mvcPath",
					"/templates/datamgt/dictcollection_create_dictitem.ftl");
				dictCollectionCreateDictItemURL.setWindowState(
					LiferayWindowState.EXCLUSIVE);

				adminDataMgtPortlet.put(
					"dictcollection_create_dictitem",
					dictCollectionCreateDictItemURL);

				PortletURL dictCollectionCreateInfoURL =
					renderResponse.createRenderURL();
				dictCollectionCreateInfoURL.setParameter(
					"mvcPath",
					"/templates/datamgt/dictcollection_create_dictgroup.ftl");
				dictCollectionCreateInfoURL.setWindowState(
					LiferayWindowState.EXCLUSIVE);

				adminDataMgtPortlet.put(
					"dictcollection_create_dictgroup", dictCollectionCreateInfoURL);

				PortletURL saveDictItemURL = renderResponse.createActionURL();

				saveDictItemURL.setParameter(
					ActionRequest.ACTION_NAME, "saveDictItem");
				saveDictItemURL.setWindowState(LiferayWindowState.EXCLUSIVE);

				adminDataMgtPortlet.put(
					"dictcollection_dictitem_edit_action", saveDictItemURL);

				PortletURL documentTypeListURL = renderResponse.createRenderURL();
				documentTypeListURL.setParameter(
					"mvcPath", "/templates/html/document_type_list.ftl");
				documentTypeListURL.setWindowState(LiferayWindowState.EXCLUSIVE);

				adminDataMgtPortlet.put("document_type_list", documentTypeListURL);

				PortletURL documentTypeDetailURL = renderResponse.createRenderURL();
				documentTypeDetailURL.setParameter(
					"mvcPath", "/templates/html/document_type_detail.ftl");
				documentTypeDetailURL.setWindowState(LiferayWindowState.EXCLUSIVE);

				adminDataMgtPortlet.put(
					"document_type_detail", documentTypeDetailURL);

				PortletURL documentTypeCreateURL = renderResponse.createRenderURL();
				documentTypeCreateURL.setParameter(
					"mvcPath", "/templates/html/document_type_create.ftl");
				documentTypeCreateURL.setWindowState(LiferayWindowState.EXCLUSIVE);

				adminDataMgtPortlet.put(
					"document_type_create", documentTypeCreateURL);

				portletURLs.put("adminDataMgtPortlet", adminDataMgtPortlet);			
			
			// Label url

			JSONObject adminLabelPortlet = JSONFactoryUtil.createJSONObject();

			PortletURL labelListURL = renderResponse.createRenderURL();
			labelListURL.setParameter(
				"mvcPath", "/templates/html/label_list.ftl");
			labelListURL.setWindowState(LiferayWindowState.EXCLUSIVE);

			adminLabelPortlet.put("label_list", labelListURL);

			PortletURL labelDetailURL = renderResponse.createRenderURL();
			labelDetailURL.setParameter(
				"mvcPath", "/templates/html/label_detail.ftl");
			labelDetailURL.setWindowState(LiferayWindowState.EXCLUSIVE);

			adminLabelPortlet.put("label_detail", labelDetailURL);

			PortletURL labelCreateURL = renderResponse.createRenderURL();
			labelCreateURL.setParameter(
				"mvcPath", "/templates/html/label_create.ftl");
			labelCreateURL.setWindowState(LiferayWindowState.EXCLUSIVE);

			adminLabelPortlet.put("label_create", labelCreateURL);

			portletURLs.put("adminLabelPortlet", adminLabelPortlet);

			// Location url

			JSONObject adminLocationPortlet =
				JSONFactoryUtil.createJSONObject();

			PortletURL locationListURL = renderResponse.createRenderURL();
			locationListURL.setParameter(
				"mvcPath", "/templates/html/location_list.ftl");
			locationListURL.setWindowState(LiferayWindowState.EXCLUSIVE);

			adminLocationPortlet.put("location_list", locationListURL);

			PortletURL locationDetailURL = renderResponse.createRenderURL();
			locationDetailURL.setParameter(
				"mvcPath", "/templates/html/location_detail.ftl");
			locationDetailURL.setWindowState(LiferayWindowState.EXCLUSIVE);

			adminLocationPortlet.put("location_detail", locationDetailURL);

			PortletURL locationCreateURL = renderResponse.createRenderURL();
			locationCreateURL.setParameter(
				"mvcPath", "/templates/html/location_create.ftl");
			locationCreateURL.setWindowState(LiferayWindowState.EXCLUSIVE);

			adminLocationPortlet.put("location_create", locationCreateURL);

			portletURLs.put("adminLocationPortlet", adminLocationPortlet);

			// Notification -> include portlet
			
			JSONObject adminNotificationPortlet =
					JSONFactoryUtil.createJSONObject();

			PortletURL notificationListURL = renderResponse.createRenderURL();
			notificationListURL.setParameter(
				"mvcPath", "/templates/notification/notification_template_list.ftl");
			notificationListURL.setWindowState(LiferayWindowState.EXCLUSIVE);

			adminNotificationPortlet.put(
				"notification_template_list", notificationListURL);

			PortletURL notificationDetailURL = renderResponse.createRenderURL();
			notificationDetailURL.setParameter(
				"mvcPath", "/templates/notification/notification_template_detail.ftl");
			notificationDetailURL.setWindowState(LiferayWindowState.EXCLUSIVE);

			adminNotificationPortlet.put(
				"notification_template_detail", notificationDetailURL);

			portletURLs.put(
				"adminNotificationPortlet", adminNotificationPortlet);

			// Workingunit

			JSONObject adminWorkingUnitPortlet =
				JSONFactoryUtil.createJSONObject();

			PortletURL workingUnitListURL = renderResponse.createRenderURL();
			workingUnitListURL.setParameter(
				"mvcPath", "/templates/workingunit/working_unit_list.ftl");
			workingUnitListURL.setWindowState(LiferayWindowState.EXCLUSIVE);

			adminWorkingUnitPortlet.put(
				"working_unit_list", workingUnitListURL);

			PortletURL workingUnitDetailURL = renderResponse.createRenderURL();
			workingUnitDetailURL.setParameter(
				"mvcPath", "/templates/workingunit/working_unit_detail.ftl");
			workingUnitDetailURL.setWindowState(LiferayWindowState.EXCLUSIVE);

			adminWorkingUnitPortlet.put(
				"working_unit_detail", workingUnitDetailURL);

			PortletURL workingUnitCreateURL = renderResponse.createRenderURL();
			workingUnitCreateURL.setParameter(
				"mvcPath", "/templates/workingunit/working_unit_create.ftl");
			workingUnitCreateURL.setWindowState(LiferayWindowState.EXCLUSIVE);

			adminWorkingUnitPortlet.put(
				"working_unit_create", workingUnitCreateURL);

			portletURLs.put("adminWorkingUnitPortlet", adminWorkingUnitPortlet);

			// Workspace -> include portlet

			// Office Site -> include portlet

			// Contact -> include portlet

			// Jobpos -> include portlet

		}
		catch (Exception e) {
			_log.error(e);
		}

		return portletURLs;
	}

	// TODO template commented
	private List<JSONObject> getFileAttachment(long employeeId, long groupId) {

		// FileAttachActions attachActions = new FileAttachActions();
		// List<FileAttach> fileAttachs = attachActions.getFileAttachs(
		// groupId, Employee.class.getName(), String.valueOf(employeeId), 0);
		List<JSONObject> attachs = new ArrayList<JSONObject>();

		// for (FileAttach fileAttach : fileAttachs) {
		//
		// long fileEntryId = fileAttach.getFileEntryId();
		// JSONObject fileAttachJSON = JSONFactoryUtil.createJSONObject();
		// try {
		//
		// FileEntry fileEntry =
		// DLAppLocalServiceUtil.getFileEntry(fileEntryId);
		//
		// fileAttachJSON.put(
		// "fileAttachId", fileAttach.getFileAttachId());
		// fileAttachJSON.put(
		// "createdDate", DateTimeUtils.convertDateToString(
		// fileAttach.getCreateDate(), DateTimeUtils._TIMESTAMP));
		// fileAttachJSON.put(
		// "modifiedDate",
		// DateTimeUtils.convertDateToString(
		// fileAttach.getModifiedDate(),
		// DateTimeUtils._TIMESTAMP));
		// fileAttachJSON.put("fullName", fileAttach.getFullName());
		// fileAttachJSON.put("email", fileAttach.getEmail());
		// fileAttachJSON.put("fileName", fileEntry.getFileName());
		// fileAttachJSON.put("fileType", fileEntry.getMimeType());
		// fileAttachJSON.put("fileSize", fileEntry.getSize());
		// fileAttachJSON.put("version", fileEntry.getVersion());
		// fileAttachJSON.put("source", fileAttach.getSource());
		// fileAttachJSON.put("sourceUrl", fileAttach.getSourceUrl());
		// fileAttachJSON.put("docFileId", fileAttach.getDocFileId());
		//
		// attachs.add(fileAttachJSON);
		//
		// }
		// catch (Exception e) {
		// _log.info(
		// "Can't not get FileEntry with fileEntryId = " +
		// fileEntryId);
		// continue;
		// }
		//
		// }

		return attachs;

	}

	public void saveJobPos(
		ActionRequest actionRequest, ActionResponse actionResponse)
		throws IOException {

		ThemeDisplay themeDisplay =
			(ThemeDisplay) actionRequest.getAttribute(WebKeys.THEME_DISPLAY);

		JobposActions jobposActions = new JobposActions();

		JSONObject result = JSONFactoryUtil.createJSONObject();

		long groupId = themeDisplay.getScopeGroupId();

		long userId = themeDisplay.getUserId();

		long jobPosId = ParamUtil.getLong(actionRequest, "jobPosId");

		String title = ParamUtil.getString(actionRequest, "title");
		String description = ParamUtil.getString(actionRequest, "description");
		String permissions = ParamUtil.getString(actionRequest, "permissions");
		String works = ParamUtil.getString(actionRequest, "works");

		int leader = ParamUtil.getInteger(actionRequest, "leader");

		try {
			ServiceContext serviceContext =
				ServiceContextFactory.getInstance(actionRequest);
			JobPos jobPos = null;
			if (jobPosId > 0) {
				jobPos = jobposActions.update(
					userId, groupId, jobPosId, title, description, leader,
					serviceContext);

			}
			else {
				jobPos = jobposActions.create(
					userId, groupId, title, description, leader,
					serviceContext);
			}

			// if (Validator.isNotNull(permissions)) {
			jobposActions.createPermissionsPatch(
				userId, serviceContext.getCompanyId(), groupId,
				jobPos.getJobPosId(), permissions, serviceContext);
			// }

			// TODO template commented
			// if (Validator.isNotNull(works)) {
			// jobposActions.createJobposWorksPatch(
			// userId, serviceContext.getCompanyId(), groupId,
			// jobPos.getJobPosId(), works, serviceContext);
			// }

			result.put("jobPosId", jobPos.getJobPosId());
			result.put(
				"createDate", DateTimeUtils.convertDateToString(
					jobPos.getCreateDate(), DateTimeUtils._TIMESTAMP));
			result.put(
				"modifiedDate", DateTimeUtils.convertDateToString(
					jobPos.getModifiedDate(), DateTimeUtils._TIMESTAMP));
			result.put(
				"title", Validator.isNotNull(jobPos.getTitle())
					? jobPos.getTitle() : StringPool.BLANK);
			result.put(
				"description", Validator.isNotNull(jobPos.getDescription())
					? jobPos.getDescription() : StringPool.BLANK);
			result.put("leader", jobPos.getLeader());
			result.put("mappingRoleId", jobPos.getMappingRoleId());

		}
		catch (Exception e) {
			_log.error(e);
			if (e instanceof UnauthenticationException) {
				result.put("statusCode", 401);
			}

			else if (e instanceof UnauthorizationException) {
				result.put("statusCode", 403);
			}

			else if (e instanceof NoSuchUserException) {
				result.put("statusCode", 409);
			}
			result.put("msg", "error");
		}
		finally {
			writeJSON(actionRequest, actionResponse, result);
		}
		
		

	}

	public void saveDictItem(
			ActionRequest actionRequest, ActionResponse actionResponse)
			throws IOException {

			ThemeDisplay themeDisplay =
				(ThemeDisplay) actionRequest.getAttribute(WebKeys.THEME_DISPLAY);

			long groupId = themeDisplay.getScopeGroupId();

			long userId = themeDisplay.getUserId();

			String itemCode = ParamUtil.getString(actionRequest, "itemCode");
			String itemName = ParamUtil.getString(actionRequest, "itemName");
			String itemNameEN = ParamUtil.getString(actionRequest, "itemNameEN");
			String itemCodeOld = ParamUtil.getString(actionRequest, "itemCodeOld");
			String collectionCode =
				ParamUtil.getString(actionRequest, "collectionCode");
			String itemDescription =
				ParamUtil.getString(actionRequest, "itemDescription");
			int sibling = ParamUtil.getInteger(actionRequest, "sibling", 0);
			String parentItemCode =
				ParamUtil.getString(actionRequest, "parentItemCode");
			String groupCodes = ParamUtil.getString(actionRequest, "groupCode");

			String metaData = ParamUtil.getString(actionRequest, "metaData");

			DictCollectionActions dictCollectionActions =
				new DictCollectionActions();

			JSONObject result = JSONFactoryUtil.createJSONObject();

			try {
				ServiceContext serviceContext =
					ServiceContextFactory.getInstance(actionRequest);

				DictItem dictItem = null;

				if (Validator.isNotNull(itemCodeOld)) {

					dictItem = dictCollectionActions.updateDictItemByItemCode(
						userId, groupId, serviceContext, collectionCode,
						itemCodeOld, itemCode, itemName, itemNameEN,
						itemDescription, String.valueOf(sibling), parentItemCode);

					dictItem = dictCollectionActions.updateMetaDataByItemCode(
						userId, groupId, serviceContext, collectionCode,
						itemCode, metaData);
					
				}
				else {
					dictItem = dictCollectionActions.addDictItems(
						userId, groupId, collectionCode, parentItemCode, itemCode,
						itemName, itemNameEN, itemDescription,
						String.valueOf(sibling), 0, metaData, serviceContext);
				}

			groupCodes = dictCollectionActions.updateDictItemGroup(userId, groupId, dictItem.getDictItemId(),
					groupCodes, collectionCode, serviceContext);

				result.put(
					"createDate", DateTimeUtils.convertDateToString(
						dictItem.getCreateDate(), DateTimeUtils._TIMESTAMP));
				result.put(
					"modifiedDate", DateTimeUtils.convertDateToString(
						dictItem.getModifiedDate(), DateTimeUtils._TIMESTAMP));
				result.put("itemCode", dictItem.getItemCode());
				result.put(
					"itemName", Validator.isNotNull(dictItem.getItemName())
						? dictItem.getItemName() : StringPool.BLANK);
				result.put(
					"itemNameEN", Validator.isNotNull(dictItem.getItemNameEN())
						? dictItem.getItemNameEN() : StringPool.BLANK);
				result.put("itemDescription", dictItem.getItemDescription());
				result.put("parentItem", dictItem.getParentItemId());
				result.put("level", dictItem.getLevel());
				result.put("sibling", dictItem.getSibling());
				result.put("treeIndex", dictItem.getTreeIndex());
				result.put("dictItemId", dictItem.getDictItemId());
				result.put("groupCode", groupCodes);

			}
			catch (Exception e) {
				_log.error(e);
				if (e instanceof UnauthenticationException) {

					result.put("statusCode", 401);

				}

				if (e instanceof UnauthorizationException) {

					result.put("statusCode", 403);

				}

				if (e instanceof NoSuchUserException) {

					result.put("statusCode", 401);

				}

				if (e instanceof DuplicateCategoryException) {

					result.put("statusCode", 409);

				}
				result.put("msg", "error");
			}
			finally {
				writeJSON(actionRequest, actionResponse, result);
			}

		}

	private Log _log = LogFactoryUtil.getLog(AdminPortlet.class.getName());
}
