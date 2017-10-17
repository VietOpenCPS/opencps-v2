<#if (Request)??>
<#include "init.ftl">
</#if>

<div class="row clear-both">
	
	<div class="col-sm-5">
		
		<div class="box-s3">

			<div class="employee-detail-info-range">
				
				<#include "employee_detail_info.ftl">

			</div>

		</div>

		<div class="box-s3 MT20">

			<div id="employee-detail-jobpos-range" >

				<#include "employee_detail_jobpos.ftl">
				
			</div>

		</div>

		<div class="box-s3 MTN1">
			
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

	<div class="col-sm-12 text-center MT10">
		<button class="btn" id="btn-back-employee">Quay lại</button>
	</div>
</div>
<script type="text/javascript">
	$("#btn-back-employee").click(function(){
		$("#employee_list").show();
		$("#employee_detail").hide();
	});
</script>
