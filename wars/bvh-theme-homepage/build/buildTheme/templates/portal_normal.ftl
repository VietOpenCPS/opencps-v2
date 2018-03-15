<!DOCTYPE html>

<#include init />

<html class="${root_css_class}" dir="<@liferay.language key="lang.dir" />" lang="${w3c_language_id}">

<head>
	<title>Hệ thống Dịch vụ công trực tuyến - Cục Đăng kiểm Việt Nam</title>

	<meta content="initial-scale=1.0, width=device-width" name="viewport" />
	<meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
	
	<link href="https://fonts.googleapis.com/css?family=Roboto:300,400,500,700,900&amp;subset=vietnamese" rel="stylesheet">

	<@liferay_util["include"] page=top_head_include />
	
	<!-- Mainly scripts -->
	<script>
        window.__define = window.define;
        window.__require = window.require;
        window.define = undefined;
        window.require = undefined;
    </script>
	 
	<script src="${themeDisplay.getPathThemeRoot()}/js/jquery.min.js"></script>
	<script src="${themeDisplay.getPathThemeRoot()}/js/bootstrap.min.js"></script>
	<script src="${themeDisplay.getPathThemeRoot()}/js/custom.js?t=101"></script>
	
	<script>
        window.define = window.__define;
        window.require = window.__require;
        window.__define = undefined;
        window.__require = undefined;
    </script>
</head>

<body class="${css_class} OpenCPS mBody page-theme">

<@liferay_ui["quick-access"] contentId="#main-content" />
<@liferay_util["include"] page=body_top_include />

<#if permissionChecker.isOmniadmin()>
  	<@liferay.control_menu />
<#else>
  	<style>
	    html .has-control-menu #wrapper {
	        margin-top: 0 !important;
	    }
	    html body.open #wrapper {
	      padding-left: 0 !important;
	    }
  	</style>
</#if>

<div class="mWrapper">
	<header id="banner">		
		<div class="container align-middle">
			<div class="logo align-middle">
				<a href="${site_default_url}">
					<img src="${themeDisplay.getPathThemeRoot()}/images/logo.png">
					<div class="text-logo">
						Cổng dịch vụ công trực tuyến
						<span>Bộ Văn hóa, Thể thao và Du lịch</span>
					</div>
				</a>
			</div>
			<@liferay_portlet["runtime"]
              defaultPreferences="${freeMarkerPortletPreferences}"
              portletProviderAction=portletProviderAction.VIEW
              instanceId="FrontendWebPortal_LoginPortlet_1"
              portletName="FrontendWebPortal_LoginPortlet"/>
		</div>
	</header>
	
	<#if has_navigation && is_setup_complete>
		<#include "${full_templates_path}/navigation.ftl" />
	</#if>

	<section id="main-content">
		<div class="container-fluid">
			<#if selectable>
				<@liferay_util["include"] page=content_include />
			<#else>
				${portletDisplay.recycle()}

				${portletDisplay.setTitle(the_title)}

				<@liferay_theme["wrap-portlet"] page="portlet.ftl">
					<@liferay_util["include"] page=content_include />
				</@>
			</#if>
		</div>
	</section>

	<footer id="footer">
		<div class="container align-middle-lg">
			<div>
				<div class="site-name">
					Cổng dịch vụ công trực tuyến
					<span>Bộ Văn hóa, Thể thao và Du lịch</span>
				</div>
				<div>Chịu trách nhiệm chính: Nguyễn Thanh Liêm, Giám đốc Trung tâm Công nghệ thông tin</div>
			</div>
			<div class="contact">
				<p>Địa chỉ: <b>Số 20, ngõ 2, Hoa Lư, Q.Hai Bà Trưng, TP.Hà Nội</b></p>
				<p>Điện thoại: <b>0243.9745845 - 0243.9745846</b></p>
				<p>Email: <b>ttcntt@cntt.gov.vn</b></p>
			</div>
		</div>
		
		<div class="text-center last">
			Ghi rõ nguồn "Cổng Dịch vụ công trực tuyến của Bộ VHTTDL", dichvucong.bvhttdl.gov.vn khi phát hành lại thông tin từ website này
		</div>
	</footer>
</div>

<@liferay_util["include"] page=body_bottom_include />

<@liferay_util["include"] page=bottom_include />
</body>

</html>
