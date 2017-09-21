<#include "../init.ftl">

<@liferay_portlet.actionURL name="/login/login" var="loginURL" >
	<@liferay_portlet.param name="mvcRenderCommandName" value="/login/login" />
</@>

<#assign isSignedIn = themeDisplay.isSignedIn() />

<#assign logoutURL = (themeDisplay.getURLPortal() + "/c/portal/logout") />

<#assign userName = themeDisplay.getUser().getFullName() />

<#if themeDisplay.getUser().getFemale()>
	<#assign preUserName = "Bà" />
<#else>
	<#assign preUserName = "Ông" />
</#if>

<#assign GetterUtil = objectUtil("com.liferay.portal.kernel.util.GetterUtil") />
<#assign PortalUtil = objectUtil("com.liferay.portal.kernel.util.PortalUtil") />

<#assign active_user_id = GetterUtil.getString(PortalUtil.getOriginalServletRequest(request).getParameter("active_user_id")) />
