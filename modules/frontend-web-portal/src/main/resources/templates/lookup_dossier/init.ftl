<#if (Request)??>
<#include "../init.ftl">

<#assign GetterUtil = objectUtil("com.liferay.portal.kernel.util.GetterUtil") />
<#assign PortalUtil = objectUtil("com.liferay.portal.kernel.util.PortalUtil") />

<#assign keyword = GetterUtil.getString(PortalUtil.getOriginalServletRequest(request).getParameter("keyword")) />

<@portlet["defineObjects"] />

<#assign groupId = themeDisplay.getScopeGroupId() />
<#assign userId = themeDisplay.getUserId() />
<#assign portletNamespace = themeDisplay.getPortletDisplay().getNamespace() />
<#assign currentURL = themeDisplay.getURLCurrent() />
<#assign request = themeDisplay.getRequest() />

<#assign api = (Request.api)!>
<#assign secretKey = (Request.secretKey)!>
</#if>