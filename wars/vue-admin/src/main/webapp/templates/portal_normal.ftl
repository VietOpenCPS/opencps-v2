<!DOCTYPE html>

<#include init />

<html class="${root_css_class}" dir="<@liferay.language key="lang.dir" />" lang="${w3c_language_id}">

<head>
	<title>Hệ thống Dịch vụ công trực tuyến</title>

	<meta content="initial-scale=1.0, width=device-width" name="viewport" />
	<meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
	<base href="/">
	<link href="/o/vue-admin/images/favicon.ico" rel="Shortcut Icon">
	
	<@liferay_util["include"] page=top_head_include />
	
	<link href="https://fonts.googleapis.com/css?family=Roboto:300,400,500,700,900|Material+Icons&amp;subset=vietnamese" rel="stylesheet">
	
	<link href="/o/vue-admin/css/roboto-fontface.css" rel="stylesheet" type="text/css">
	<script src="/o/vue-admin/js/jquery.min.js"></script>
    <script src="/o/vue-admin/js/jquery.jexcel.js"></script>
	
</head>

<body>
	
	<@liferay_util["include"] page=content_include />
	
</body>

<script type="text/javascript">
	    $.ajaxSetup({
			headers: {
				"Token": Liferay.authToken,
				"groupId": themeDisplay.getScopeGroupId()
			},
			global: true
		});
</script>

</html>
