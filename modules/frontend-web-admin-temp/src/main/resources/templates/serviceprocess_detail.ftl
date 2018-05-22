<#if (Request)??>
<#include "init.ftl">
</#if>

<div class="row nav-tabs-wrapper service-process-detail">
	<ul class="nav nav-tabs">
		<li class="active">
			<a data-toggle="tab" href="#tab_process_info">Thông tin quy trình</a>
		</li>
		<li>
			<a data-toggle="tab" href="#tab_step">Bước</a>
		</li>
		<li>
			<a data-toggle="tab" href="#tab_action">Thao tác</a>
		</li>
	</ul>
	<div class="tab-content">
		<div id="tab_process_info" class="tab-pane fade in active">
			<#include "serviceprocess_form.ftl">
		</div>
		<div id="tab_step" class="tab-pane fade">
			<div id="serviceprocess_step_container">
				<#include "serviceprocess_step.ftl">
			</div>
			<div id="serviceprocess_detail_formstep_container" style="display: none;">
				<#include "serviceprocess_detail_formstep.ftl">
			</div>
		</div>
		<div id="tab_action" class="tab-pane fade in">
			<div id="serviceprocess_action_container">
				<#include "serviceprocess_action.ftl">
			</div>
			<div id="serviceprocess_detail_formaction_container" style="display: none;">
				<#include "serviceprocess_detail_formaction.ftl">
			</div>
		</div>
	</div>
</div>
