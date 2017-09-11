<#if (Request)??>
	<#include "init.ftl">
</#if>

<#-- <@liferay_portlet.actionURL name="/login/login" var="loginURL" >
	<@liferay_portlet.param name="mvcRenderCommandName" value="/login/login" />
</@> -->

<div class="row MR20">
  <form action="loginURL" method="post">
    <div class="col-xs-12 col-sm-5">
      <input type="text" class="form-control" name="login" placeholder="Tài khoản đăng nhập" title="Tài khoản đăng nhập">
    </div>
    <div class="col-xs-12 col-sm-5">
      <input type="password" class="form-control" name="password" placeholder="Mật khẩu" title="Mật khẩu">
    </div>
    <div class="col-xs-12 col-sm-2">
      <button class="btn btn-active">Đăng nhập</button>
    </div>
  </form>
</div>
<div class="row MT10">
  <div class="col-xs-12 col-sm-5">
  </div>
  <div class="col-xs-12 col-sm-5 P0">
    <a href="#">Quên mật khẩu?</a>
  </div>
</div>
