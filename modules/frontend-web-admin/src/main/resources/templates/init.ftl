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
<#assign sites = (Request.sites)!>
<#assign serviceInfoStatuses = (Request.serviceInfoStatuses)!>
<#assign serviceInfo = (Request.SERVICE_INFO)!>
<#assign portletNamespace = (Request.portletNamespace)!>

<#assign groupId = themeDisplay.getScopeGroupId() />
<#assign userId = themeDisplay.getUserId() />
<#assign currentURL = themeDisplay.getURLCurrent() />
<#assign userName = themeDisplay.getUser().getScreenName() />
<#assign request = themeDisplay.getRequest() />
<#assign portalURL = (themeDisplay.getPortalURL())!>
<#assign url = (Request.url)!>
<#assign api = (Request.api)!>
<#assign constant = (Request.constant)!>
<#assign registrationtemplates = (Request.registrationtemplates)!>


<!--INIT EMPLOYEE-->
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


<#if Request.params??>
<#assign params = Request.params?eval>
</#if>
<#if Request.constants??>
<#assign constants = Request.constants>
</#if>

<!--INIT  WORKING UNIT-->

<#if Request.activityType_dictItem??>
<#assign activityType_dictItem = Request.activityType_dictItem>
</#if>

<#if Request.dictCollection_dictItem??>
<#assign dictCollection_dictItem = Request.dictCollection_dictItem>
</#if>

<#if Request.documentType_dictItem??>
<#assign documentType_dictItem = Request.documentType_dictItem>
</#if>

<#if Request.dictCollection_dictCollection??>
<#assign dictCollection_dictCollection = Request.dictCollection_dictCollection>
</#if>

<#if Request.dictCollection_dictGroup??>
<#assign dictCollection_dictGroup = Request.dictCollection_dictGroup>
</#if>

<#if Request.label??>
<#assign label = Request.label>
</#if>

<#if Request.location??>
<#assign location = Request.location>
</#if>

<#if Request.notificationTemplate??>
<#assign notificationTemplate = Request.notificationTemplate>
</#if>

<#if Request.workspace??>
<#assign workspace = Request.workspace>
</#if>

<#if Request.officeSite??>
<#assign officeSite = Request.officeSite>
</#if>

<#if Request.workingUnit??>
<#assign workingUnit = Request.workingUnit>
</#if>

<#if Request.contact??>
<#assign contact = Request.contact>
</#if>

<#if Request.jobPos??>
<#assign jobPos = Request.jobPos>
</#if>

<#if Request.param??>
<#assign param = Request.param?eval>
</#if>

<#if Request.constants??>
<#assign constant = Request.constants?eval>
</#if>

<#if Request.workspace_jobposes??>
<#assign workspace_jobposes = Request.workspace_jobposes>
</#if>

<#assign isOmniadmin = Request.isOmniadmin>
</#if>
<script type="text/javascript">
	(function($) {
		function setCookie(cname, cvalue, exdays) {
		    var d = new Date();
		    d.setTime(d.getTime() + (exdays * 24 * 60 * 60 * 1000));
		    var expires = "expires="+d.toUTCString();
		    document.cookie = cname + "=" + cvalue + ";" + expires + ";path=/";
		}
		
		function getCookie(cname) {
		    var name = cname + "=";
		    var ca = document.cookie.split(';');
		    for(var i = 0; i < ca.length; i++) {
		        var c = ca[i];
		        while (c.charAt(0) == ' ') {
		            c = c.substring(1);
		        }
		        if (c.indexOf(name) == 0) {
		            return c.substring(name.length, c.length);
		        }
		    }
		    return "";
		}
		
		if (!getCookie('token')) {
			$.ajax({
				url: "${api.server}" + "/login",
				type: "POST",
				dataType: "json",
				headers: {"groupId": ${groupId}},
				data: {
									
				},
				success: function(result) {
					setCookie("token", result.token, 365);	
				}
			});	
		}
		
		$.ajaxSetup({
		    headers:{
		        'Bearer': getCookie('token')
		    },
	        error: function(jqXHR, exception) {
	            if (jqXHR.status === 0) {
	                alert('Not connect.n Verify Network.');
	            } else if (jqXHR.status == 403) {
				} else if (jqXHR.status == 404) {	                
	            } else if (jqXHR.status == 500) {
	            } else if (exception === 'parsererror') {
	            } else if (exception === 'timeout') {
	            } else if (exception === 'abort') {
	            } else {
	            }
	        }		    
		})	
	})(jQuery);
</script>	

