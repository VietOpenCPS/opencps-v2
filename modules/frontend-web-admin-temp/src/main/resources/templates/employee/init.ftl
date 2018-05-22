<#include "../init.ftl">

<#if (Request)??>

    <#if Request.employee_workingStatus??>
       <#assign employee_workingStatus = Request.employee_workingStatus>
    </#if>
    
    <#if Request.employee??>
       <#assign employee = Request.employee>
    </#if>
    
    <#if Request.employee_accountInfo??>
       <#assign employee_accountInfo = Request.employee_accountInfo>
    </#if>
    
    <#if Request.employee_jobPos??>
       <#assign employee_jobPos = Request.employee_jobPos>
    </#if>
    
    <#assign url = Request.url>
    <#assign api = Request.api>
    <#if Request.params??>
       <#assign params = Request.params>
    </#if>
    <#if Request.constants??>
       <#assign constants = Request.constants>
    </#if>
    <#if Request.employee_fileAttachs??>
       <#assign employee_fileAttachs = Request.employee_fileAttachs>
    </#if>
    
</#if>
