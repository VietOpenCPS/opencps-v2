<#include "../init.ftl">

<@liferay_portlet.actionURL name="/login/login" var="loginURL" >
	<@liferay_portlet.param name="mvcRenderCommandName" value="/login/login" />
</@>

<#assign portletNamespace = renderResponse.getNamespace() />

<#assign isSignedIn = themeDisplay.isSignedIn() />

<#assign logoutURL = (themeDisplay.getURLPortal() + "/c/portal/logout") />

<#assign userName = themeDisplay.getUser().getFullName() />

<#if themeDisplay.getUser().getFemale()>
	<#assign preUserName = "Bà" />
<#else>
	<#assign preUserName = "Ông" />
</#if>