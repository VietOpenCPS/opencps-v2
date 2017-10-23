
package org.opencps.frontend.web.admin.portlet;

import java.io.IOException;
import java.util.List;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.Portlet;
import javax.portlet.PortletException;
import javax.portlet.PortletMode;
import javax.portlet.PortletRequest;
import javax.portlet.PortletURL;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.portlet.WindowStateException;

import org.opencps.auth.api.BackendAuthImpl;
import org.opencps.auth.api.exception.UnauthenticationException;
import org.opencps.auth.api.exception.UnauthorizationException;
import org.opencps.datamgt.action.impl.DictCollectionActions;
import org.opencps.datamgt.model.DictCollection;
import org.opencps.datamgt.model.DictGroup;
import org.opencps.datamgt.model.DictItem;
import org.opencps.datamgt.service.DictGroupLocalServiceUtil;
import org.opencps.datamgt.utils.DateTimeUtils;
import org.opencps.dossiermgt.model.ServiceInfo;
import org.opencps.dossiermgt.service.ServiceInfoLocalServiceUtil;
import org.opencps.frontend.web.admin.constants.AdminPortletKeys;
import org.opencps.frontend.web.admin.constants.FrontendWebAdminPortletConstants;
import org.opencps.usermgt.model.Applicant;
import org.opencps.usermgt.model.Employee;
import org.opencps.usermgt.model.WorkingUnit;
import org.opencps.usermgt.service.ApplicantLocalServiceUtil;
import org.opencps.usermgt.service.EmployeeLocalServiceUtil;
import org.opencps.usermgt.service.WorkingUnitLocalServiceUtil;
import org.osgi.service.component.annotations.Component;

import com.liferay.asset.kernel.exception.DuplicateCategoryException;
import com.liferay.portal.kernel.exception.NoSuchUserException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Role;
import com.liferay.portal.kernel.portlet.LiferayWindowState;
import com.liferay.portal.kernel.portlet.PortletURLFactoryUtil;
import com.liferay.portal.kernel.service.RoleLocalServiceUtil;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceContextFactory;
import com.liferay.portal.kernel.theme.PortletDisplay;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.util.bridges.freemarker.FreeMarkerPortlet;

/**
 * @author huymq
 */
@Component(immediate = true, property = {
	"com.liferay.portlet.display-category=category.opencps.admin",
	"com.liferay.portlet.instanceable=true",
	"javax.portlet.display-name=frontend-web-admin Portlet",
	"javax.portlet.name=" + AdminPortletKeys.ADMIN_PORTLET,
	"javax.portlet.init-param.template-path=/",
	"javax.portlet.init-param.view-template=/templates/admin.ftl",
	"javax.portlet.resource-bundle=content.Language",
	"javax.portlet.security-role-ref=power-user,user"
}, service = Portlet.class)
public class AdminPortlet extends FreeMarkerPortlet {

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
		int sibling = ParamUtil.getInteger(actionRequest, "sibling");
		String parentItemCode =
			ParamUtil.getString(actionRequest, "parentItemCode");
		// String groupCode = ParamUtil.getString(actionRequest, "groupCode");

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
			}
			else {
				dictItem = dictCollectionActions.addDictItems(
					userId, groupId, collectionCode, parentItemCode, itemCode,
					itemName, itemNameEN, itemDescription,
					String.valueOf(sibling), 0, metaData, serviceContext);
			}

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
			result.put("dicItemId", dictItem.getDictItemId());

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

		PortletURL employeeURL = PortletURLFactoryUtil.create(
			renderRequest, portletId, themeDisplay.getPlid(),
			PortletRequest.RENDER_PHASE);
		employeeURL.setPortletMode(PortletMode.VIEW);
		employeeURL.setWindowState(LiferayWindowState.EXCLUSIVE);
		employeeURL.setParameter(
			"mvcPath", "/templates/employee/employee_index.ftl");

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
		urlObject.put("employee_index", employeeURL.toString());

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
		apiObject.put(
			"portletNamespace",
			themeDisplay.getPortletDisplay().getNamespace());
		apiObject.put("roles", roles);

		// set varible
		renderRequest.setAttribute("ajax", urlObject);
		renderRequest.setAttribute("api", apiObject);
		renderRequest.setAttribute(
			"applicantId", applicant == null ? "" : applicant.getApplicantId());

		// code from mobilink
		setRenderParams(renderRequest, renderResponse, themeDisplay);

		super.render(renderRequest, renderResponse);

	}

	private void setRenderParams(
		RenderRequest renderRequest, RenderResponse renderResponse,
		ThemeDisplay themeDisplay)
		throws WindowStateException {
		ServiceContext serviceContext = null;

		try {
			serviceContext = ServiceContextFactory.getInstance(renderRequest);
		}
		catch (Exception e) {
			_log.error(e);
			throw new NullPointerException();
		}

		BackendAuthImpl backendAuthImpl = new BackendAuthImpl();

		JSONObject params = JSONFactoryUtil.createJSONObject();

		String type = ParamUtil.getString(renderRequest, "type");

		long groupId = themeDisplay.getScopeGroupId();

		long userId = themeDisplay.getUserId();

		boolean isOmniadmin = true;
		// backendAuthImpl.isAdmin(serviceContext, StringPool.BLANK);

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
				}

				if (Validator.isNotNull(groupCode)) {
					dictGroup =
						DictGroupLocalServiceUtil.fetchByF_DictGroupCode(
							groupCode, groupId);
				}

				params.put("dictCollection_groupCode", groupCode);

				params.put("activityType_itemCode", itemCode);

				params.put("documentType_itemCode", itemCode);

				params.put("dictCollection_collectionCode", collectionCode);

				params.put("dictCollection_itemCode", itemCode);

				renderRequest.setAttribute("activityType_dictItem", dictItem);

				renderRequest.setAttribute("documentType_dictItem", dictItem);

				renderRequest.setAttribute("dictCollection_dictItem", dictItem);

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
			FrontendWebAdminPortletConstants.AdminMenuItemType.LABEL.toString())) {
			long labelId = ParamUtil.getLong(renderRequest, "labelId");

			/* Label label = LabelLocalServiceUtil.fetchLabel(labelId); */

			/*
			 * renderRequest.setAttribute("label", label);
			 * params.put("label_labelId", labelId);
			 */
		}
		else if (type.equals(
			FrontendWebAdminPortletConstants.AdminMenuItemType.LOCATION.toString())) {
			long locationId = ParamUtil.getLong(renderRequest, "locationId");

			/*
			 * Location location =
			 * LocationLocalServiceUtil.fetchLocation(locationId);
			 */

			/*
			 * renderRequest.setAttribute("location", location); double[]
			 * geolocation = new double[] { 0, 0 }; if (location != null &&
			 * Validator.isNotNull(location.getGeolocation())) { geolocation =
			 * StringUtil.split(location.getGeolocation(), 0.0); }
			 */

			params.put("location_locationId", locationId);

			/*
			 * params.put("locationLat", geolocation[0]);
			 * params.put("locationLng", geolocation[1]);
			 */

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

		// renderRequest.setAttribute(
		// "api", themeDisplay.getPortalURL() + "/o/rest/v2");

		renderRequest.setAttribute(
			"url", generateURL(renderRequest, renderResponse));

		renderRequest.setAttribute("constant", generalConstant());
		
		long workingUnitId = ParamUtil.getLong(renderRequest, "workingUnitId");

		WorkingUnit workingUnit = null;

		if (workingUnitId > 0) {
			try {
				workingUnit =
					WorkingUnitLocalServiceUtil.fetchWorkingUnit(workingUnitId);
			}
			catch (Exception e) {
				_log.error(e);
			}
		}
		
		params.put("workingUnit_workingUnitId", workingUnitId);
		renderRequest.setAttribute("workingUnit", workingUnit);
		renderRequest.setAttribute("param", params);
		
		long employeeId = ParamUtil.getLong(renderRequest, "employeeId");
		
		/*JSONObject employee = JSONFactoryUtil.createJSONObject();*/
		
		Employee employeeObj= EmployeeLocalServiceUtil.fetchEmployee(employeeId);

/*		String employee = JSONFactoryUtil.serialize(employeeObj);*/
		
		renderRequest.setAttribute("employee", employeeObj);
	}

	private JSONObject generalConstant() {

		JSONObject constants = JSONFactoryUtil.createJSONObject();

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

		return constants;
	}

	private JSONObject generateURL(
		RenderRequest renderRequest, RenderResponse renderResponse)
		throws WindowStateException {

		JSONObject portletURLs = JSONFactoryUtil.createJSONObject();
		try {

			JSONObject adminDataMgtPortlet = JSONFactoryUtil.createJSONObject();

			PortletURL activityTypeListURL = renderResponse.createRenderURL();
			activityTypeListURL.setParameter(
				"mvcPath", "/templates/datamgt/activity_type_list.ftl");
			activityTypeListURL.setWindowState(LiferayWindowState.EXCLUSIVE);

			adminDataMgtPortlet.put("activity_type_list", activityTypeListURL);

			PortletURL activityTypeDetailURL = renderResponse.createRenderURL();
			activityTypeDetailURL.setParameter(
				"mvcPath", "/templates/datamgt/activity_type_detail.ftl");
			activityTypeDetailURL.setWindowState(LiferayWindowState.EXCLUSIVE);

			adminDataMgtPortlet.put(
				"activity_type_detail", activityTypeDetailURL);

			PortletURL activityTypeCreateURL = renderResponse.createRenderURL();
			activityTypeCreateURL.setParameter(
				"mvcPath", "/templates/datamgt/activity_type_create.ftl");
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
				"mvcPath", "/templates/datamgt/document_type_list.ftl");
			documentTypeListURL.setWindowState(LiferayWindowState.EXCLUSIVE);

			adminDataMgtPortlet.put("document_type_list", documentTypeListURL);

			PortletURL documentTypeDetailURL = renderResponse.createRenderURL();
			documentTypeDetailURL.setParameter(
				"mvcPath", "/templates/datamgt/document_type_detail.ftl");
			documentTypeDetailURL.setWindowState(LiferayWindowState.EXCLUSIVE);

			adminDataMgtPortlet.put(
				"document_type_detail", documentTypeDetailURL);

			PortletURL documentTypeCreateURL = renderResponse.createRenderURL();
			documentTypeCreateURL.setParameter(
				"mvcPath", "/templates/datamgt/document_type_create.ftl");
			documentTypeCreateURL.setWindowState(LiferayWindowState.EXCLUSIVE);

			adminDataMgtPortlet.put(
				"document_type_create", documentTypeCreateURL);

			portletURLs.put("adminDataMgtPortlet", adminDataMgtPortlet);

			// Label url

			JSONObject adminLabelPortlet = JSONFactoryUtil.createJSONObject();

			PortletURL labelListURL = renderResponse.createRenderURL();
			labelListURL.setParameter(
				"mvcPath", "/templates/datamgt/label_list.ftl");
			labelListURL.setWindowState(LiferayWindowState.EXCLUSIVE);

			adminLabelPortlet.put("label_list", labelListURL);

			PortletURL labelDetailURL = renderResponse.createRenderURL();
			labelDetailURL.setParameter(
				"mvcPath", "/templates/datamgt/label_detail.ftl");
			labelDetailURL.setWindowState(LiferayWindowState.EXCLUSIVE);

			adminLabelPortlet.put("label_detail", labelDetailURL);

			PortletURL labelCreateURL = renderResponse.createRenderURL();
			labelCreateURL.setParameter(
				"mvcPath", "/templates/datamgt/label_create.ftl");
			labelCreateURL.setWindowState(LiferayWindowState.EXCLUSIVE);

			adminLabelPortlet.put("label_create", labelCreateURL);

			portletURLs.put("adminLabelPortlet", adminLabelPortlet);

			// Location url

			JSONObject adminLocationPortlet =
				JSONFactoryUtil.createJSONObject();

			PortletURL locationListURL = renderResponse.createRenderURL();
			locationListURL.setParameter(
				"mvcPath", "/templates/datamgt/location_list.ftl");
			locationListURL.setWindowState(LiferayWindowState.EXCLUSIVE);

			adminLocationPortlet.put("location_list", locationListURL);

			PortletURL locationDetailURL = renderResponse.createRenderURL();
			locationDetailURL.setParameter(
				"mvcPath", "/templates/datamgt/location_detail.ftl");
			locationDetailURL.setWindowState(LiferayWindowState.EXCLUSIVE);

			adminLocationPortlet.put("location_detail", locationDetailURL);

			PortletURL locationCreateURL = renderResponse.createRenderURL();
			locationCreateURL.setParameter(
				"mvcPath", "/templates/datamgt/location_create.ftl");
			locationCreateURL.setWindowState(LiferayWindowState.EXCLUSIVE);

			adminLocationPortlet.put("location_create", locationCreateURL);

			portletURLs.put("adminLocationPortlet", adminLocationPortlet);

			// Notification -> include portlet

			// Workingunit

			JSONObject adminWorkingUnitPortlet =
				JSONFactoryUtil.createJSONObject();

			PortletURL workingUnitListURL = renderResponse.createRenderURL();
			workingUnitListURL.setParameter(
				"mvcPath", "/templates/working-unit/working_unit_list.ftl");
			workingUnitListURL.setWindowState(LiferayWindowState.EXCLUSIVE);

			adminWorkingUnitPortlet.put(
				"working_unit_list", workingUnitListURL);

			PortletURL workingUnitDetailURL = renderResponse.createRenderURL();
			workingUnitDetailURL.setParameter(
				"mvcPath", "/templates/working-unit/working_unit_detail.ftl");
			workingUnitDetailURL.setWindowState(LiferayWindowState.EXCLUSIVE);

			adminWorkingUnitPortlet.put(
				"working_unit_detail", workingUnitDetailURL);

			PortletURL workingUnitCreateURL = renderResponse.createRenderURL();
			workingUnitCreateURL.setParameter(
				"mvcPath", "/templates/working-unit/working_unit_create.ftl");
			workingUnitCreateURL.setWindowState(LiferayWindowState.EXCLUSIVE);

			adminWorkingUnitPortlet.put(
				"working_unit_create", workingUnitCreateURL);

			JSONObject employeePortlet = JSONFactoryUtil.createJSONObject();

			PortletURL employeeListURL = renderResponse.createRenderURL();
			employeeListURL.setParameter(
				"mvcPath", "/templates/employee/employee_list.ftl");
			employeeListURL.setWindowState(LiferayWindowState.EXCLUSIVE);

			employeePortlet.put("employee_list", employeeListURL);

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

	private Log _log = LogFactoryUtil.getLog(AdminPortlet.class.getName());
}
