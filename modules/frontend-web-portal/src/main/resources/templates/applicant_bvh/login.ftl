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
		<img src="http://via.placeholder.com/350x150" class="img-rounded">
		<div class="dropdown">
			<button class="btn btn-reset dropdown-toggle" type="button" id="dropdownMenu1" data-toggle="dropdown" aria-haspopup="true" aria-expanded="true">
				<b>${userName}</b>
				<span class="caret"></span>
			</button>
			<ul class="dropdown-menu" aria-labelledby="dropdownMenu1">
				<li><a href="/profile"><i class="fa fa-user"></i> Thông tin tài khoản</a></li>
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
</script>
