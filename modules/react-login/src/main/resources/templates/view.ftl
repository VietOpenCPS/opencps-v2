<#include "init.ftl">

<@liferay_portlet.actionURL name="/login/login" var="loginURL" >
<@liferay_portlet.param name="mvcRenderCommandName" value="/login/login" />
</@>

<form action="${loginURL}" method="post" name="login_form">
<div id="root" style="float: right;"></div>
</form>
<script type="text/javascript" src="/o/react-login/js/bundle.js?t=21"></script>

<style type="text/css">
	.hidden__temp {
	    opacity: 0;
	}
	.portlet-boundary_npmreactlogin_ .navigation-drawer--right>.navigation-drawer__border {
	    position: absolute;
	    top: 0;
	    height: 100%;
	    width: 100%;
	    background-image: url(http://hanhchinhcong.phutho.gov.vn/o/parent-opencps-vue/images/bg-home.jpg);
	    background-size: cover;
	    -webkit-filter: blur(200px);
	    z-index: -1;
	    filter: blur(200px);
	}
	.portlet-boundary_npmreactlogin_ .navigation-drawer .list {
	    background: transparent;
	}
	.portlet-boundary_npmreactlogin_ .navigation-drawer>.list .list__tile:hover {
	    background: transparent !important;
	}
	.portlet-boundary_npmreactlogin_ .btn-toggle--selected {
	    box-shadow: none !important;
	}
	.portlet-boundary_npmreactlogin_ .theme--light .v-divider {
	    border-color: #eee;
	}
	.portlet-boundary_npmreactlogin_ .theme--light .v-list__tile__content {
	    height: 30px;
	}
	.portlet-boundary_npmreactlogin_ .navigation-drawer>.list .list__tile {
	        height: 42px;
	        padding: 0 16px !important;
	}
	.portlet-boundary_npmreactlogin_ .theme--light .btn-toggle {
	        width: 100%;
	    background: #fff;
	    border-radius: 4px;
	    height: 20px;
	}
	.portlet-boundary_npmreactlogin_ .theme--light .btn-toggle .btn {
	        height: 20px;
	        opacity: 1;
	    font-size: 10px;
	}
	.portlet-boundary_npmreactlogin_ .theme--light .v-list__tile__title {
	        font-size: 24px;
	    padding-left: 10px;
	    color: #383838;
	}
	.portlet-boundary_npmreactlogin_ .theme--light .btn--small {
	    height: 18px !important;
	    width: 18px !important;
	}
	body .portlet-boundary_npmreactlogin_  .v-navigation-drawer--is-mobile:not(.v-navigation-drawer--close), 
	body .portlet-boundary_npmreactlogin_ .v-navigation-drawer--temporary:not(.v-navigation-drawer--close) {
	    -webkit-box-shadow: 0 1px 3px -1px rgba(0,0,0,.2), 1px 1px 5px -5px rgba(0,0,0,.14), 0 6px 30px 5px rgba(0,0,0,.12);
	    box-shadow: 0 1px 3px -1px rgba(0,0,0,.2), 1px 1px 5px -5px rgba(0,0,0,.14), 0 6px 30px 5px rgba(0,0,0,.12);
	}
	.portlet-boundary_npmreactlogin_ .overlay--absolute {
	    opacity: 0;
	}
	
	.login-wrapper {
        float: right;
	}
	.forgetten-password-input {
	    width: 100%;
	    line-height: 31px;
	    font-size: 13px;
	    padding-left: 10px;
	    text-decoration: underline;
	    color: #005792;
	}
	.action-btn-login-input {
	    width: 100%;
   	 	text-align: right;
	}
	.login-wrapper .login-input {
	    display: -webkit-box;
	    display: flex;
	}
	.login-wrapper .login-input .ico {
	    position: relative;
	    margin-left: 10px;
	}
	.login-wrapper .login-input .ico:first-child {
	    margin-left: 0;
	}
	.login-wrapper .login-input .ico:before {
	    content: "";
	    font-family: FontAwesome;
	    position: absolute;
	    color: #005792;
	    width: 30px;
	    height: 100%;
	    display: -webkit-box;
	    display: flex;
	    -webkit-align-items: center;
	    align-items: center;
	    -webkit-justify-content: center;
	    justify-content: center;
	}
	.login-wrapper .login-input .ico-user:before {
	    content: "\f007";
	}
	.login-wrapper .login-input .ico-pass:before {
	    content: "\f023";
	}
	.login-wrapper .login-input .ico:after {
	    content: "";
	    position: absolute;
	    top: 20%;
	    left: 30px;
	    width: 1px;
	    height: 60%;
	    background-color: #005792;
	}
	.login-wrapper .login-input input {
	    height: 26px;
	    width: 200px;
	    border: 1px solid #005792;
	    border-radius: 30px;
	    padding-left: 35px;
	    font-size: 12px;
	    background: #fff;
	}
	.login-wrapper .login-input input:focus {
	    outline: none;
	}
	.login-wrapper .btn-action {
	    text-align: right;
	}
	.login-wrapper button {
	    margin: 5px 0 0 5px;
	    height: 26px;
	    line-height: 26px;
	    padding: 0 12px;
	    color: white;
	    -webkit-box-shadow: none;
	    box-shadow: none;
	    border: 0;
	    border-radius: 30px;
	    background-color: #005792;
	    font-size: 12px;
	    font-weight: bold;
	    will-change: box-shadow;
	    box-shadow: 0 3px 1px -2px rgba(0,0,0,.2), 0 2px 2px 0 rgba(0,0,0,.14), 0 1px 5px 0 rgba(0,0,0,.12);
	}
	.login-wrapper .btn-register {
	    background-color: #fdb44b;
	}
	.login-wrapper button:hover {
	    background-color: #00204a;
	    cursor: pointer;
	}
	.login-wrapper .btn-register:hover {
	    background-color: #0072bc;
	    cursor: pointer;
	}
	.portlet-boundary_npmreactlogin_ .application--wrap {
	    min-height: unset;
	}
	.portlet-boundary_npmreactlogin_ .application.theme--light {
	    background: transparent !important;
	    width: 100%;
	}
	.portlet-boundary_npmreactlogin_ .application--wrap {
	    border-top: none !important;
	}
	.portlet-boundary_npmreactlogin_ .login-wrapper input {
	    background-color: #fff;
	}
	.portlet-boundary_npmreactlogin_ .navigation-drawer{
	    height: 100vh !important;
	    z-index: 11;
	}
	.portlet-boundary_npmreactlogin_ .badge__badge {
	    font-size: 10px;
	    border-radius: 4px;
	    height: 16px;
	    width: auto;
	    padding: 0 4px;
	    right: -6px;
	    top: -2px;
	    min-width: 18px;
	    z-index: 10;
	}
	.portlet-boundary_npmreactlogin_ {
	    min-width: 300px;
	    width: 100%;
	}
	body #banner__content {
	    padding: 0!important;
	    height: 66px !important;
	}
	#banner #banner__content .logo {
	    position: absolute;
	}
	body #banner .navigation-drawer--fixed {
	    position: fixed !important;
	}
	.portlet-boundary_npmreactlogin_ .btn-toggle .btn__content {
	    text-transform: none;
	    background: #929292;
	    color: #fff;
	    font-size: 10px !important;
	}
	.portlet-boundary_npmreactlogin_ .btn-toggle .btn--active .btn__content {
	    background: #fff;
	    color: inherit;
	}
	.portlet-boundary_npmreactlogin_ .btn-toggle .btn--active .btn__content::before {
	    opacity: 0;
	}
	.portlet-boundary_npmreactlogin_ .theme--light.chip,
	.portlet-boundary_npmreactlogin_ .theme--light .chip {
	    background: transparent !important;
	}
	.portlet-boundary_npmreactlogin_ .menu__content {
	    margin-top: 0;
	}
	.portlet-boundary_npmreactlogin_ .menu__content .list {
	    margin-bottom: 0;
	    padding: 0;
	}
	.portlet-boundary_npmreactlogin_ .menu__content .list>div > .list__tile {
	    height: 32px;
	    font-size: 13px;
	    cursor: pointer;
	}
	.portlet-boundary_npmreactlogin_ .menu__content .list>div:hover {
	    background-color: #eee !important;
	}
	.portlet-boundary_npmreactlogin_ .menu__content .list>div {
	    border-bottom: 1px dashed #ddd;
	    display: block;
	    padding: 0;
	    font-size: 13px;
	    height: auto;
	    color: rgba(0,0,0,.87);
	    position: relative;
	}
	.portlet-boundary_npmreactlogin_ .chip .avatar {
	    margin-right: 2px;
	}
	.portlet-boundary_npmreactlogin_ .chip .avatar img {
	    width: 24px;
	    height: 24px;
	    margin-right: 0px !important;
	}
	.portlet-boundary_npmreactlogin_ .list__tile__action {
	    min-width: 25px;
	}
	#banner .application--wrap {
	    min-height: unset !important;
	    border: none !important;
	}
	body #banner .application.theme--light {
		background: none !important;
	}
</style>