<#if (Request)??>
	<#assign aui = PortletJspTagLibs["/META-INF/liferay-aui.tld"] />
	<#assign liferay_portlet = PortletJspTagLibs["/META-INF/liferay-portlet-ext.tld"] />
	<#assign liferay_security = PortletJspTagLibs["/META-INF/liferay-security.tld"] />
	<#assign liferay_theme = PortletJspTagLibs["/META-INF/liferay-theme.tld"] />
	<#assign liferay_ui = PortletJspTagLibs["/META-INF/liferay-ui.tld"] />
	<#assign liferay_util = PortletJspTagLibs["/META-INF/liferay-util.tld"] />
	<#assign portlet = PortletJspTagLibs["/META-INF/liferay-portlet.tld"] />
	
	<@liferay_theme["defineObjects"] />
	
	<@portlet["defineObjects"] />

	<#assign portletNamespace = themeDisplay.getPortletDisplay().getNamespace() />

	<#assign groupId = themeDisplay.getScopeGroupId() />
	<#assign userId = themeDisplay.getUserId() />
	<#assign user = themeDisplay.getUser() />
	<#assign currentURL = themeDisplay.getURLCurrent() />
	<#assign request = themeDisplay.getRequest() />
	<#assign portalURL = (themeDisplay.getPortalURL())!>

	<#assign dossierId = (Request.dossierId)!>
	<#assign dossier = (Request.dossier)!>
	<#assign dossierPartNo = (Request.dossierPartNo)!>
	<#assign stateWindow = (Request.stateWindow)!>
	<#assign employee = (Request.employee)!>
	
	<#assign RequestParameters = (Request.RequestParameters)!>
	
</#if>


<#include "layout/navigation.ftl">

<#include "layout/main_layout.ftl">

<#include "details/thong_tin_doanh_nghiep_table_design.ftl">

<#include "details/tra_cuu_hoso_table_design.ftl">

<#include "details/danh_sach_hoso_table_design.ftl">

<#include "details/document_in_list_design.ftl">

<#include "details/document_out_list_design.ftl">

<#include "details/history_processing_table_design.ftl">

<#include "details/popup_dossier_file.ftl">