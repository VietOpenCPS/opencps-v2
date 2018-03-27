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

	<#assign ajax = (Request.ajax)!>
	<#assign api = (Request.api)!>
	<#assign serviceInfoStatuses = (Request.serviceInfoStatuses)!>
	<#assign serviceInfo = (Request.SERVICE_INFO)!>
	<#assign portletNamespace = themeDisplay.getPortletDisplay().getNamespace() />

	<#assign groupId = themeDisplay.getScopeGroupId() />
	<#assign userId = themeDisplay.getUserId() />
	<#assign currentURL = themeDisplay.getURLCurrent() />
	<#assign request = themeDisplay.getRequest() />
	<#assign portalURL = (themeDisplay.getPortalURL())!>

	<#assign applicant = (Request.applicant)!>
	<#assign dossierTemplateId = (Request.dossierTemplateId)!>
	<#assign dossierId = (Request.dossierId)!>
	<#assign dossier = (Request.dossier)!>
	<#assign dossierPartNo = (Request.dossierPartNo)!>
	<#assign dossierTemplateNo = (Request.dossierTemplateNo)!>
	<#assign RequestParameters = (Request.RequestParameters)!>
	<#assign govAgencyCode = (Request.govAgencyCode)!>
	<#assign serviceConfigId = (Request.serviceConfigId)!>
	
	
</#if>
