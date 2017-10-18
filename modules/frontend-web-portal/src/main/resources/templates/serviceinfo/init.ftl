
<#if (Request)??>
<#include "../init.ftl">
<#assign applicantType = (Request.applicantType)!>

<#assign GetterUtil = objectUtil("com.liferay.portal.kernel.util.GetterUtil") />
<#assign PortalUtil = objectUtil("com.liferay.portal.kernel.util.PortalUtil") />

<#assign domain = GetterUtil.getString(PortalUtil.getOriginalServletRequest(request).getParameter("domain")) /> 
</#if>


