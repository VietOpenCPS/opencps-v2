<%--
/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */
--%>

<%@ taglib uri="http://liferay.com/tld/theme" prefix="theme" %>
<theme:defineObjects />

<%@ include file="/html/common/themes/init.jsp" %>

<%
	if ((permissionChecker.isOmniadmin() && layout.isPublicLayout() ) || (layout.isPrivateLayout() && layout.getType().equals("control_panel"))) {
%>

	<%@ include file="/html/common/themes/top_meta.jspf" %>
	<%@ include file="/html/common/themes/top_meta-ext.jsp" %>

	<liferay-util:dynamic-include key="/html/common/themes/top_head.jsp#pre" />
	
	<link data-senna-track="temporary" href="<%= themeDisplay.getPathThemeImages() %>/<%= PropsValues.THEME_SHORTCUT_ICON %>" rel="Shortcut Icon" />
	
	<%-- Portal CSS --%>
	
	<link class="lfr-css-file" data-senna-track="temporary" href="<%= HtmlUtil.escapeAttribute(PortalUtil.getStaticResourceURL(request, themeDisplay.getPathThemeCss() + "/clay.css")) %>" id="liferayAUICSS" rel="stylesheet" type="text/css" />
	
	<%
	long cssLastModifiedTime = PortalWebResourcesUtil.getLastModified(PortalWebResourceConstants.RESOURCE_TYPE_CSS);
	%>
	
	<link data-senna-track="temporary" href="<%= HtmlUtil.escapeAttribute(PortalUtil.getStaticResourceURL(request, themeDisplay.getCDNDynamicResourcesHost() + PortalWebResourcesUtil.getContextPath(PortalWebResourceConstants.RESOURCE_TYPE_CSS) + "/main.css", cssLastModifiedTime)) %>" id="liferayPortalCSS" rel="stylesheet" type="text/css" />
	
	<%
	List<Portlet> portlets = null;
	
	if (layoutTypePortlet != null) {
		portlets = layoutTypePortlet.getAllPortlets();
	}
	
	if (layout != null) {
		String ppid = ParamUtil.getString(request, "p_p_id");
	
		if (layout.isTypeEmbedded() || layout.isTypePortlet()) {
			if (themeDisplay.isStateMaximized() || themeDisplay.isStatePopUp()) {
				if (Validator.isNotNull(ppid)) {
					Portlet portlet = PortletLocalServiceUtil.getPortletById(company.getCompanyId(), ppid);
	
					if ((portlet != null) && !portlets.contains(portlet)) {
						portlets.add(portlet);
					}
				}
			}
		}
		else if (layout.isTypeControlPanel() || layout.isTypePanel()) {
			portlets = new ArrayList<Portlet>();
	
			portlets.addAll(layout.getEmbeddedPortlets());
	
			if (Validator.isNotNull(ppid)) {
				Portlet portlet = PortletLocalServiceUtil.getPortletById(company.getCompanyId(), ppid);
	
				if ((portlet != null) && !portlets.contains(portlet)) {
					portlets.add(portlet);
				}
			}
		}
	
		String portletResource = ParamUtil.getString(request, PortalUtil.getPortletNamespace(ppid) + "portletResource");
	
		if (Validator.isNotNull(portletResource)) {
			Portlet portlet = PortletLocalServiceUtil.getPortletById(company.getCompanyId(), portletResource);
	
			if ((portlet != null) && !portlets.contains(portlet)) {
				portlets.add(portlet);
			}
		}
	
		Iterator<Portlet> portletsIterator = portlets.iterator();
	
		LayoutTypeAccessPolicy layoutTypeAccessPolicy = LayoutTypeAccessPolicyTracker.getLayoutTypeAccessPolicy(layout);
	
		while (portletsIterator.hasNext()) {
			Portlet portlet = portletsIterator.next();
	
			try {
				layoutTypeAccessPolicy.checkAccessAllowedToPortlet(request, layout, portlet);
			}
			catch (PrincipalException pe) {
				portletsIterator.remove();
			}
		}
	
		request.setAttribute(WebKeys.LAYOUT_PORTLETS, portlets);
	}
	%>
	
	<%-- Portlet CSS References --%>
	
	<%@ include file="/html/common/themes/top_portlet_resources_css.jspf" %>
	
	<%-- Portal JavaScript References --%>
	
	<%@ include file="/html/common/themes/top_js.jspf" %>
	<%@ include file="/html/common/themes/top_js-ext.jspf" %>
	
	<%-- Portlet JavaScript References --%>
	
	<%@ include file="/html/common/themes/top_portlet_resources_js.jspf" %>
	
	<%-- Raw Text --%>
	
	<%
	List<String> markupHeaders = (List<String>)request.getAttribute(MimeResponse.MARKUP_HEAD_ELEMENT);
	
	if (markupHeaders != null) {
		for (String markupHeader : markupHeaders) {
	%>
	
			<%= markupHeader %>
	
	<%
		}
	}
	
	com.liferay.petra.string.StringBundler pageTopSB = OutputTag.getDataSB(request, WebKeys.PAGE_TOP);
	%>
	
	<c:if test="<%= pageTopSB != null %>">
	
		<%
		pageTopSB.writeTo(out);
		%>
	
	</c:if>
	
	<script type="text/javascript">
		var portlet = portlet || {};
	
		portlet.impl = portlet.impl || {};
	
		portlet.impl.getInitData = function() {
			return <%= RenderStateUtil.generateJSON(request, themeDisplay) %>;
		}
	</script>
	
	<%-- Theme CSS --%>
	
	<link class="lfr-css-file" data-senna-track="temporary" href="<%= HtmlUtil.escapeAttribute(PortalUtil.getStaticResourceURL(request, themeDisplay.getPathThemeCss() + "/main.css")) %>" id="liferayThemeCSS" rel="stylesheet" type="text/css" />
	
	<%-- User Inputted Layout CSS --%>
	
	<c:if test="<%= (layout != null) && Validator.isNotNull(layout.getCssText()) %>">
		<style data-senna-track="temporary" type="text/css">
			<%= _escapeCssBlock(layout.getCssText()) %>
		</style>
	</c:if>
	
	<%-- User Inputted Portlet CSS --%>
	
	<c:if test="<%= portlets != null %>">
		<style data-senna-track="temporary" type="text/css">
	
			<%
			for (Portlet portlet : portlets) {
				PortletPreferences portletSetup = themeDisplay.getStrictLayoutPortletSetup(layout, portlet.getPortletId());
	
				String portletSetupCss = portletSetup.getValue("portletSetupCss", StringPool.BLANK);
			%>
	
				<c:if test="<%= Validator.isNotNull(portletSetupCss) %>">
	
					<%
					try {
					%>
	
						<%@ include file="/html/common/themes/portlet_css.jspf" %>
	
					<%
					}
					catch (Exception e) {
						if (_log.isWarnEnabled()) {
							_log.warn(e.getMessage());
						}
					}
					%>
	
				</c:if>
	
			<%
			}
			%>
	
		</style>
	</c:if>
	
	<liferay-util:dynamic-include key="/html/common/themes/top_head.jsp#post" />


<%
	} else {
%>
	<script data-senna-track="temporary" type="text/javascript">
	// <![CDATA[
		var Liferay = Liferay || {};

		Liferay.ThemeDisplay = {

			<%
			Group scopeGroup = themeDisplay.getScopeGroup();

			Group liveGroup = StagingUtil.getLiveGroup(scopeGroup);
			
			%>

			<c:if test="<%= layout != null %>">
				getLayoutId: function() {
					return '<%= layout.getLayoutId() %>';
				},
	
				<%
				Layout controlPanelLayout = themeDisplay.getControlPanelLayout();
				%>
	
				getLayoutRelativeControlPanelURL: function() {
					return '<%= PortalUtil.getLayoutRelativeURL(new VirtualLayout(controlPanelLayout, scopeGroup), themeDisplay) %>';
				},
	
				getLayoutRelativeURL: function() {
					return '<%= PortalUtil.getLayoutRelativeURL(layout, themeDisplay) %>';
				},
				getLayoutURL: function() {
					return '<%= PortalUtil.getLayoutURL(layout, themeDisplay) %>';
				},
				getParentLayoutId: function() {
					return '<%= layout.getParentLayoutId() %>';
				},
				isControlPanel: function() {
					return <%= layout.isTypeControlPanel() %>;
				},
				isPrivateLayout: function() {
					return '<%= layout.isPrivateLayout() %>';
				},
				isVirtualLayout: function() {
					return <%= layout instanceof VirtualLayout %>;
				},
			</c:if>
			getCompanyId: function() {
				return '<%= themeDisplay.getCompanyId() %>';
			},
			getParentGroupId: function() {
				return '<%= themeDisplay.getSiteGroupId() %>';
			},
			getPathImage: function() {
				return '<%= themeDisplay.getPathImage() %>';
			},
			getPathJavaScript: function() {
				return '<%= themeDisplay.getPathJavaScript() %>';
			},
			getPathMain: function() {
				return '<%= themeDisplay.getPathMain() %>';
			},
			getPathThemeImages: function() {
				return '<%= themeDisplay.getPathThemeImages() %>';
			},
			getPathThemeRoot: function() {
				return '<%= themeDisplay.getPathThemeRoot() %>';
			},
			getPlid: function() {
				return '<%= themeDisplay.getPlid() %>';
			},
			getPortalURL: function() {
				return '<%= themeDisplay.getPortalURL() %>';
			},
			getScopeGroupId: function() {
				return '<%= scopeGroup.getGroupId() %>';
			},
			getScopeGroupIdOrLiveGroupId: function() {
				return '<%= liveGroup.getGroupId() %>';
			},
			getSessionId: function() {
				return '<%= PropsValues.SESSION_ENABLE_URL_WITH_SESSION_ID ? session.getId() : StringPool.BLANK %>';
			},
			getSiteAdminURL: function() {
				return '<%= PortalUtil.getSiteAdminURL(themeDisplay, StringPool.BLANK, null) %>';
			},
			getSiteGroupId: function() {
				return '<%= themeDisplay.getSiteGroupId() %>';
			},
			getURLControlPanel: function() {
				return '<%= themeDisplay.getURLControlPanel() %>';
			},
			getURLHome: function() {
				return '<%= HtmlUtil.escapeJS(themeDisplay.getURLHome()) %>';
			},
			getUserId: function() {
				return '<%= themeDisplay.getUserId() %>';
			},
			getUserName: function() {
				return '<%= themeDisplay.isSignedIn() ? UnicodeFormatter.toString(user.getFullName()) : StringPool.BLANK %>';
			},
			isAddSessionIdToURL: function() {
				return <%= themeDisplay.isAddSessionIdToURL() %>;
			},
			isImpersonated: function() {
				return <%= themeDisplay.isImpersonated() %>;
			},
			isSignedIn: function() {
				return <%= themeDisplay.isSignedIn() %>;
			}
		};

		var themeDisplay = Liferay.ThemeDisplay;

		Liferay.authToken = '<%= AuthTokenUtil.getToken(request) %>';

		<%
		String currentURL = PortalUtil.getCurrentURL(request);
		%>

		Liferay.currentURL = '<%= HtmlUtil.escapeJS(currentURL) %>';
		Liferay.currentURLEncoded = '<%= HtmlUtil.escapeJS(URLCodec.encodeURL(currentURL)) %>';
	// ]]>
</script>
	
<%
	}
%>

<%

if (!themeDisplay.isSignedIn() && layout.isPublicLayout()) {
	
%>
<script type="text/javascript">

	(function() {
		setTimeout(() => {
			var elems = document.querySelectorAll('.portlet .input-container');
			console.log(elems)
			for (var i=0; i<elems.length; i++) {
				elems[i].removeAttribute("disabled");
			}
        }, 100)
	})();

</script>

<%
	}
%>

<%!
private String _escapeCssBlock(String css) {
	return StringUtil.replace(css, new String[] {"<", "expression("}, new String[] {"\\3c", ""});
}

private static Log _log = LogFactoryUtil.getLog("portal_web.docroot.html.common.themes.top_head_jsp");
%>

<script type="text/javascript">
    $.ajaxSetup({
		headers: {"Token": Liferay.authToken},
		global: true
	});
</script>