<!DOCTYPE html>

<#include init />

<html class="${root_css_class}" dir="<@liferay.language key="lang.dir" />" lang="${w3c_language_id}">

<head>
	<title>Hệ thống Dịch vụ công trực tuyến</title>

	<meta content="initial-scale=1.0, width=device-width" name="viewport" />
	<meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
	<base href="/">
	<link href="https://fonts.googleapis.com/css?family=Roboto:300,400,500,700,900|Material+Icons&amp;subset=vietnamese" rel="stylesheet">
	<link type="text/css" href="${themeDisplay.getPathThemeRoot()}/css/base-style.css?t=3453492221199533232" rel="stylesheet">
	<link type="text/css" href="${themeDisplay.getPathThemeRoot()}/css/app_custom.css?t=9992112222911" rel="stylesheet">
	
	<script type="text/javascript" src="http://code.jquery.com/jquery-1.11.1.min.js"></script>
	<script type="text/javascript" src="http://cdnjs.cloudflare.com/ajax/libs/handlebars.js/4.0.5/handlebars.js"></script>
	<script type="text/javascript" src="http://code.cloudcms.com/alpaca/1.5.24/bootstrap/alpaca.min.js"></script>
	
</head>

<body class="${css_class} mBody">
	<div class="mWrapper">
		<header id="banner">		
			<div class="container align-middle" style="padding: 0;">
				<div class="logo">
					<a href="${site_default_url}" class="align-middle">
						<img src="${themeDisplay.getPathThemeRoot()}/images/logo.png">
						<div class="text-logo">
							Hệ thống Một cửa điện tử liên thông
							<span>${site_name}</span>
						</div>
					</a>
				</div>
			<@liferay_portlet["runtime"]
              defaultPreferences="${freeMarkerPortletPreferences}"
              portletProviderAction=portletProviderAction.VIEW
              instanceId="FrontendWebPortal_LoginPortlet_1"
              portletName="FrontendWebPortal_LoginPortlet"/>
			</div>
		
			<#if has_navigation && is_setup_complete>
				<#include "${full_templates_path}/navigation.ftl" />
			</#if>
		</header>

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
			<div>
				<div class="site-name">Cổng dịch vụ công trực tuyến <span>Ủy ban nhân dân Tỉnh Phú Thọ</span> </div>
			</div>
			<div class="contact">
				<p>Địa chỉ: <b>Đường Nguyễn Tất Thành, phường Tân Dân, thành phố Việt Trì, tỉnh Phú Thọ</b></p>
				<p>Điện thoại: <b>0210.3811486</b> | Fax: <b>0210.3811485</b></p>
				<p>Email: <b>sotttt@phutho.gov.vn</b></p>
			</div>
		</footer>
	</div>
</body>

</html>
