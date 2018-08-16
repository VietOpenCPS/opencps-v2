<#if (Request)??>
<#include "init.ftl">
</#if>

<#if isSignedIn == false>
<div class="">
	<form action="${loginURL}" method="post" class="eq-height-lg" name="login_form">
		<input name="${portletNamespace}action" id="input_action" type="hidden"/>
		<div class="row">
			<div class="col-sm-6">
				<div class="">
					<input type="text" class="form-control input-sm" name="${portletNamespace}login" id="input_login" placeholder="Tài khoản đăng nhập" title="Tài khoản đăng nhập">
				</div>
			</div>
			<div class="col-sm-6">
				<div class="">
					<input type="password" class="form-control input-sm" name="${portletNamespace}password" id="input_password" placeholder="Mật khẩu" title="Mật khẩu">
				</div>
			</div>
		</div>
		<div class="row">
			<div class="col-sm-6">
				
				<a href="/forgotten-password" class="text-hover-blue">Quên mật khẩu?</a>
				
			</div>
			<div class="col-sm-6">
				<button class="btn btn-active btn-sm" type="button" id="btn-register-applicant" style="float: left;margin-left: -12px;margin-right:2px;">Đăng ký</button>
				<button class="btn btn-active btn-sm">Đăng nhập</button>
			</div>
		</div>
		
	</form>
</div>

<div class="visible-xs visible-sm">
	<a href="#">Đăng nhập</a>
</div>
<#else>
<div class="account-section align-middle">
	<a href="" class="notification-alert">
		<i class="fa fa-bell-o" aria-hidden="true"></i>
		<span>3</span>
	</a>
	<div class="account align-middle">
		<img id="imgAvartarUser" name="imgAvartarUser" src="http://via.placeholder.com/350x150" class="img-rounded">
		<div class="dropdown">
			<button class="btn btn-reset dropdown-toggle" type="button" id="dropdownMenu1" data-toggle="dropdown" aria-haspopup="true" aria-expanded="true">
				<b>${userName}</b>
				<span class="caret"></span>
			</button>
			<ul class="dropdown-menu" aria-labelledby="dropdownMenu1">
				<li><a href="/web${(themeDisplay.getScopeGroup().getFriendlyURL())!}/profile"><i class="fa fa-user"></i> Thông tin tài khoản</a></li>
				<li><a href="/c/portal/logout"><i class="fa fa-arrow-right"></i> Đăng xuất</a></li>
			</ul>
		</div>
	</div>
</div>
</#if>

<script type="text/javascript">
	$("#btn-register-applicant").click(function () {
		window.location.href = "/web/cong-tiep-nhan/register";
	});
	<#if isSignedIn == true>
		var urlPhoto = fileAttachmentUrl({
			method : "GET",
			url : "${api.server}/users/${userId}/photo",
			async : false,
			success: function(options){
				var urlOut = options.url;
				$('#imgAvartarUser').attr('src', urlOut);
			},
			error: function(){}
		});
	</#if>


	function fileAttachmentUrl ( options) {

		var xhttp = new XMLHttpRequest();
		var a,filename;
		var data = {};
		xhttp.onreadystatechange = function() {

			if (xhttp.readyState === 4 && xhttp.status === 200) {
				var disposition = xhttp.getResponseHeader('Content-Disposition');
				if (disposition && disposition.indexOf('attachment') !== -1) {
					var filenameRegex = /filename[^;=\n]*=((['"]).*?\2|[^;\n]*)/;
					var matches = filenameRegex.exec(disposition);
					if (matches != null && matches[1]) filename = matches[1].replace(/['"]/g, '');
				}
				a = document.createElement('a');
				a.href = window.URL.createObjectURL(xhttp.response);
				var url = window.URL.createObjectURL(xhttp.response);
				options.success({url : url, status : xhttp.status});
			} else if (xhttp.readyState === 4 && xhttp.status !== 200) {
				options.error(xhttp.status);
			}
		};
		xhttp.open(options.method, options.url);
		xhttp.setRequestHeader("Content-Type", "application/json");
		xhttp.setRequestHeader("groupId", "${groupId}");
		if (options.hasOwnProperty("headers")){
			Object.keys( options.headers ).map(function(objectKey, index) {
				var value = options.headers[objectKey];
				xhttp.setRequestHeader(objectKey, value);
			});
		}
		if (options.hasOwnProperty("responseType")){
			xhttp.responseType = options.responseType;
		} else {
			xhttp.responseType = 'blob';
		}
		if (options.hasOwnProperty("data")){
			data = options.data;
		}
		xhttp.send(data);

	};
</script>
