<#include "init.ftl">
<div  class="row">

	<div class="col-sm-12">

		<div class="eq-height">
			
			<div class=" align-middle">
				
				<h5>
					<strong>
						Chức vụ/Phòng ban
					</strong>
				</h5>
				
			</div>
			
			<span data-toggle="modal" class="btn btn-primary image-preview-input MLA"
				href="${(url.employeePortlet.employee_detail_update_jobpos)!}&${portletNamespace}employeeId=${(employee.employeeId)!}" data-target="#modal">
				
				<i class="fa fa-plus-circle"></i> 
				<span class="p-xxs" >Thêm</span> 

			</span>

		</div>

		<div>

			<ul class="mh-head-1" id="employee-detail-jobpos">

				<#if employee_jobPos?has_content>

					<#list employee_jobPos as oJobPos>

						<li class="PT10 PB10 jobpos-item">
								
							<div class="row M0 ">
								
								<div class="col-sm-10 text-ellipsis">
									
									<#if (oJobPos.mainJobPos??) && (oJobPos.mainJobPos == true) >
										<i class="fa fa-suitcase" aria-hidden="true"></i>
									<#else>
										<i class="fa fa-angle-right" aria-hidden="true"></i>
									</#if>
									
									<span class="PL10">
										${(oJobPos.workingUnitName)!} - ${(oJobPos.jobPosTitle)!}
									</span>
								
								</div>

								<div class="col-sm-2 text-right">

									<span data-pk="${(oJobPos.employeeJobPosId)!}" id="employee-detail-jobpos-${(oJobPos.employeeJobPosId)!}" class="employee-detail-delete-jobpos">

										<i aria-hidden="true" class="fa fa-times"></i>

									</span>

								</div>


							</div>	
								
						 </li>

					</#list>

				</#if>

			</ul>

		</div>	
		
	</div>
	
</div>

<script type="text/javascript" charset="utf-8" >

(function($) {

	var birthdate = $('#employee-detail-birthdate');
	//birthdate.text(kendo.toString(kendo.parseDate(birthdate.text().trim(), 'yyyy-MM-dd'), 'dd/MM/yyyy'));

	var employeeDeleteJobPosBaseUrl = "${api.endpoint}/employees/${(employee.employeeId)!}/jobpos";

	$(document).on('click', '.employee-detail-delete-jobpos', function(event){
		
		event.preventDefault();
		event.stopPropagation();
		event.stopImmediatePropagation();

		if (confirm("Bạn muốn xóa chức vụ này ?")) {

			var employeeJobPosId = $(this).attr('data-pk');
		
			$.ajax({
							
				url: employeeDeleteJobPosBaseUrl + "/"+ employeeJobPosId,
				
				type: 'DELETE',
				headers: {
					"groupId": ${groupId}
				},
				success: function(result) {
				
					showMessageToastr("success", 'Yêu cầu của bạn được xử lý thành công!');
					
					var liItem = $("#employee-detail-jobpos-"+employeeJobPosId).closest("li");
					liItem.remove();

				},
				error: function(xhr, textStatus, errorThrown) {
				
					showMessageByAPICode(xhr.status);
			
				}
			
			});

		} else {
			return;
		}

	});

})(jQuery);

</script>