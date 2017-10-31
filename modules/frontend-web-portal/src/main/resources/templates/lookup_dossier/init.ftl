<#if (Request)??>
<#include "../init.ftl">

<#assign GetterUtil = objectUtil("com.liferay.portal.kernel.util.GetterUtil") />
<#assign PortalUtil = objectUtil("com.liferay.portal.kernel.util.PortalUtil") />

<#assign keyword = GetterUtil.getString(PortalUtil.getOriginalServletRequest(request).getParameter("keyword")) />
</#if>