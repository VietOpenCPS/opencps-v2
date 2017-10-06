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
	<#assign portletNamespace = (Request.portletNamespace)!>

	<#assign groupId = themeDisplay.getScopeGroupId() />
	<#assign userId = themeDisplay.getUserId() />
	<#assign currentURL = themeDisplay.getURLCurrent() />
	<#assign request = themeDisplay.getRequest() />
	<#assign portalURL = (themeDisplay.getPortalURL())!>
</#if>

<!-- popup notification -->
<!-- container -->
<span id="notification" style="display:none;"></span>

<!-- templates -->
<script id="errorTemplate" type="text/x-kendo-template">
  <div class="popup-error-notification">
    <p>#= message #</p>
  </div>
</script>

<script id="successTemplate" type="text/x-kendo-template">
  <div class="popup-success-notification">
    <p>#= message #</p>
  </div>
</script>

<!-- script -->
<script type="text/javascript">
	// notification
  var notification;
  $(document).ready(function() {
    notification = $("#notification").kendoNotification({
        position: {
            pinned: true,
            top: 30,
            right: 30
        },
        autoHideAfter: 3500,
        stacking: "down",
        templates: [
          {
            type: "success",
            template: $("#successTemplate").html()
          },
          {
            type: "error",
            template: $("#errorTemplate").html()
          }
        ]
    }).data("kendoNotification");

    // kendo combo box auto open when click
		$("[data-role=combobox]").each(function() {
      var widget = $(this).getKendoComboBox();
      widget.input.on("focus", function() {
        widget.open();
      });
    });
  });
</script>

<style type="text/css">
    /* Over the pointer-events:none, set the cursor to not-allowed.
    On this way you will have a more user friendly cursor. */
    .disabled-tab {
        cursor: not-allowed;
    }
    /* Clicks are not permitted and change the opacity. */
    li.disabled-tab > a[data-toggle="tab"] {
        pointer-events: none;
        filter: alpha(opacity=65);
        -webkit-box-shadow: none;
        box-shadow: none;
        opacity: .65;
    }
</style>
