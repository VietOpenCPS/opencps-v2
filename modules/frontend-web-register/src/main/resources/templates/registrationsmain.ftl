<#if (Request)??>
<#include "init.ftl">
</#if>

<div id="registrationModel" >
	<#include "registrations_route.ftl">
	
	<div class="row" id="registration-detail-wrapper">
		<div class="col-sm-2">
			<#include "registrations_menu.ftl">
		</div>
		<div class="col-sm-10">
			<#include "registrations_detail.ftl">
		</div>
	</div>
	
	<div class="row" id="registration-jasper-wrapper">
		<div class="col-sm-12">
			
		</div>
	</div>
	
</div>

