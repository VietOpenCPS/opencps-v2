<#if (Request)??>
<#include "init.ftl">
</#if>
<div class="row">
	<div class="col-sm-3">
		<#include "dossier_lookup.ftl">
	</div>
	<div class="col-sm-9">
		<#include "serviceinfo_lookup.ftl">
	</div>
</div>