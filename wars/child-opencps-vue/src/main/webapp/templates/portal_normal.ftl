<!DOCTYPE html>

<#include init />

<html class="${root_css_class}" dir="<@liferay.language key="lang.dir" />" lang="${w3c_language_id}">

<head>
	<title>Hệ thống Dịch vụ công trực tuyến</title>

	<meta content="initial-scale=1.0, width=device-width" name="viewport" />
	<meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
	<base href="/">
	
	<@liferay_util["include"] page=top_head_include />
	
	<link href='https://fonts.googleapis.com/css?family=Roboto:300,400,500,700|Material+Icons' rel="stylesheet" type="text/css">
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">

<link href="${themeDisplay.getPathThemeRoot()}/css/main.css?t=123123123" rel="stylesheet" type="text/css">

	<script>
        window.__define = window.define;
        window.__require = window.require;
        window.define = undefined;
        window.require = undefined;
    </script>
    
	<script src="${themeDisplay.getPathThemeRoot()}/js/jquery.min.js"></script>
	<script src="${themeDisplay.getPathThemeRoot()}/js/bootstrap.min.js"></script>
	<script src="${themeDisplay.getPathThemeRoot()}/js/handlebars.min.js"></script>
	<script src="${themeDisplay.getPathThemeRoot()}/js/alpaca.min.js"></script>
	<script src="${themeDisplay.getPathThemeRoot()}/js/moment-with-locales.min.js"></script>
	<script src="${themeDisplay.getPathThemeRoot()}/js/bootstrap-datetimepicker.min.js"></script>
	<script src="${themeDisplay.getPathThemeRoot()}/js/moment.min.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/select2/4.0.3/js/select2.min.js"></script>
	<script src="${themeDisplay.getPathThemeRoot()}/js/jquery-comments.js"></script>
	<script src="${themeDisplay.getPathThemeRoot()}/js/jquery.textcomplete.js"></script>
	<script src="${themeDisplay.getPathThemeRoot()}/js/svg-pan-zoom.min.js"></script>
	
	<script>
        window.define = window.__define;
        window.require = window.__require;
        window.__define = undefined;
        window.__require = undefined;
    </script>
</head>

<body class="${css_class} mBody">
<style>
	#theGraph {
	    min-height: 500px !important;
	}
</style>
	<@liferay_util["include"] page=body_top_include />
		
	<#if permissionChecker.isOmniadmin()>

	<#else>
	  	<style>
		    html .has-control-menu .mWrapper {
		        margin-top: 0 !important;
		    }
		    html body.open .mWrapper {
		      padding-left: 0 !important;
		    }
	  	</style>
	</#if>

	<div class="mWrapper">
		<header id="banner">		
			<div class="container align-middle">
				<div class="logo">
					<a href="${site_default_url}" class="align-middle">
						<img src="${themeDisplay.getPathThemeRoot()}/images/logo.png">
						<div class="text-logo">
							HỆ THỐNG MỘT CỬA ĐIỆN TỬ LIÊN THÔNG
							<span>${site_name}</span>
						</div>
					</a>
				</div>
				
			<@liferay_portlet["runtime"] portletName="npmreactlogin"/>

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
			<div class="container">
				<div class="left">
					<div class="site-name"><!--Cổng dịch vụ công trực tuyến--> <span>${site_name_cus}</span> </div>
					<div class="contact">
						<p><i class="fa fa-map-marker"></i> ${address}</p>
						<p><i class="fa fa-phone"></i> ${phone}</p>
						<p><i class="fa fa-envelope"></i> ${email}</p>
					</div>
				</div>
				<div class="right">
					<div class="hotline align-middle">
						<img src="${themeDisplay.getPathThemeRoot()}/images/hotline.png">
						<div>
							<p>Đường dây nóng</p>
							<span>${hotline}</span>
						</div>
					</div>
					<p class="align-middle">
						<img src="${themeDisplay.getPathThemeRoot()}/images/logo-viettel.png">
						Phát triển bởi Tập đoàn Công nghiệp - Viễn thông Quân đội <a href="http://viettel.com.vn/vi" target="_blank"> Viettel </a>
						trên nền <a href="https://github.com/VietOpenCPS/opencps-v2" target="_blank"> OpenCPS </a>
					</p>
				</div>
			</div>
		</footer>
	</div>
	
</body>

<script type="text/javascript">
	    $.ajaxSetup({
			headers: {"Token": Liferay.authToken},
			global: true
		});
</script>

</html>
