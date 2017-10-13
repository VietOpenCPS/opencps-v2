<#include "init.ftl">

<div class="row clear-both">
	
	<div class="col-sm-5">
		
		<div class="box-panel">

			<div class="employee-detail-info-range">
				
				<#include "employee_detail_info.ftl">

			</div>

		</div>

		<div class="box-panel MT20">

			<div id="employee-detail-jobpos-range" >
					
				<#include "employee_detail_jobpos.ftl">
				
			</div>

		</div>

		<div class="box-panel">
			
			<div id="employee-detail-attach-range" >
					
				<#include "employee_detail_attachment.ftl">
				
			</div>

		</div>

	</div>

	<div class="col-sm-7">
		
		<div id="employee-detail-formtemplate-range">
			
			<#include "employee_detail_formtemplate.ftl">

		</div>

	</div>

</div>
