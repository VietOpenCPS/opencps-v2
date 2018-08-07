<#if (Request)??>
<#include "init.ftl">
</#if>

<#if isSignedIn == false>
<div class="hidden-xs hidden-sm">
	<form action="${loginURL}" method="post" class="eq-height-lg" name="login_form">
		<input name="${portletNamespace}action" id="input_action" type="hidden"/>
		<div class="">
			<input type="text" class="form-control input-sm" name="${portletNamespace}login" id="input_login" placeholder="Tài khoản đăng nhập" title="Tài khoản đăng nhập">
		</div>
		<div class="">
			<input type="password" class="form-control input-sm" name="${portletNamespace}password" id="input_password" placeholder="Mật khẩu" title="Mật khẩu">
		</div>
		<div class="">
			<button class="btn btn-active btn-sm">Đăng nhập</button>
		</div>
	</form>
	<div class="row MT5">
		<div class="col-xs-12 col-sm-5">
		</div>
		<div class="col-xs-12 col-sm-5 P0">
			<a href="/forgotten-password" class="text-hover-blue">Quên mật khẩu?</a>
		</div>
	</div>
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
				<li><a href="/web${(themeDisplay.getScopeGroup().getFriendlyURL())!}/profile"><i class="fa fa-user"></i> Thông tin tài khoản</a></li>
				<li><a href="/c/portal/logout"><i class="fa fa-arrow-right"></i> Đăng xuất</a></li>
			</ul>
		</div>
	</div>
</div>
</#if>
