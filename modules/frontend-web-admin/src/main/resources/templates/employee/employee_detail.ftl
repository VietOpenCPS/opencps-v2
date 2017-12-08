<#include "init.ftl">

<div id="employee-detail-index-wrapper">
	<div class="row clear-both">
		
		<div class="col-sm-5">
			
			<div class="box box-s3">
	
				<div class="employee-detail-info-range">
					
					<#include "employee_detail_info.ftl">
	
				</div>
	
			</div>
	
			<div class="box box-s3 MT20 MBN1">
	
				<div id="employee-detail-working-range" >
						
					<div class="row">
	
						<div class="col-sm-12">
	
							<div class="label-control clearfix" >
	
								<label class="col-sm-5">
	
									Ngày bắt đầu làm việc:
	
								</label>
	
								<div class="col-sm-7">
									
									<a href="javascript:;" id="employee-detail-recruitDate" data-type="combodate" 
	
										data-pk="${(employee.employeeId)!}" 
										data-title="Ngày bắt đầu làm việc:" 
										data-value="${(employee.recruitDate)!}">
										<#if employee.recruitDate??>
											<#assign recruitDate_ = employee.recruitDate?datetime("iso") >
											${recruitDate_?string["dd/MM/yyyy"]}
										<#else>
											Chưa có
										</#if>
									
									</a>
								</div>
									
								<a href="javascript:;" id='employee-detail-recruitDate-icon' data-pk="${(employee.employeeId)!}">
						
									<i class="fa fa-pencil" aria-hidden="true"></i>
	
								</a>
								
							</div>
	
							<div class="label-control clearfix" >
	
								<label class="col-sm-5">
	
									Ngày kết thúc làm việc:
	
								</label>
	
								<div class="col-sm-7">
									
									<a href="javascript:;" id="employee-detail-leaveDate" data-type="combodate" 
	
										data-pk="${(employee.employeeId)!}" 
										data-title="Ngày kết thúc làm việc:" 
										data-value="${(employee.leaveDate)!}">
										<#if employee.leaveDate??>
											<#assign leaveDate_ = employee.leaveDate?datetime("iso") >
											${leaveDate_?string["dd/MM/yyyy"]}
										<#else>
											Chưa có
										</#if>
									
									</a>
								</div>
									
								<a href="javascript:;" id='employee-detail-leaveDate-icon' data-pk="${(employee.employeeId)!}">
						
									<i class="fa fa-pencil" aria-hidden="true"></i>
	
								</a>
								
							</div>
							
						</div>
						
					</div>
					
				</div>
	
			</div>
	
		</div>
	
		<div class="col-sm-7">
			
			<div class="box box-s3 MBN1">
	
				<div id="employee-detail-jobpos-range" >
						
					<#include "employee_detail_jobpos.ftl">
					
				</div>
	
			</div>

			<div class="box box-s3">
				
				<div id="employee-detail-attach-range" >
						
					<#include "employee_detail_attachment.ftl">
					
				</div>
	
			</div>
	
		</div>
	
	</div>
	
</div>

<script>

(function($) {

	$.fn.editable.defaults.mode = 'inline';
	$.fn.editable.defaults.send = "always";
	
	var employeeUpdatBaseUrl = '${api.endpoint}/employees';
	
	$(document).on('click', '#employee-detail-recruitDate-icon', function(e){
		
		e.preventDefault();
		e.stopPropagation();
		e.stopImmediatePropagation();
		
		var pk = $(this).attr('data-pk');
		var d = new Date();
		var n = d.getFullYear();
		
		try {

			$('#employee-detail-recruitDate').editable({
				name: 'recruitDate',
				url: employeeUpdatBaseUrl + '/' + pk,
				template: 'DD/MM/YYYY',
				format: 'YYYY/MM/DD',
				viewformat: 'DD/MM/YYYY',
				emptytext: "__/__/__",
				combodate: {
					minYear: n-35,
					maxYear: n,
				},
				ajaxOptions: {
					method: 'PUT',
					dataType: 'json',
					headers: {
						"groupId": parseInt("${groupId}")
					},
					contentType: "application/x-www-form-urlencoded;charset=utf-8",
				},
				params: function(params) { 
					return {
						recruitDate: params.value.split("/").join("")
					};
				},
				validate: function(value) {
					if (value.length < 1){
						return 'Đây là trường bắt buộc';
					}
				},
				success: function(data) {
					
					showMessageToastr("success", 'Yêu cầu của bạn được xử lý thành công!');
					
				},
				error: function(event, id, obj) {
					showMessageToastr("error", 'Yêu cầu của bạn được xử lý thất bại!');
				}
			}); 

		} catch (e) {
			console.log(e);
		} 
				 
		$('#employee-detail-recruitDate').editable('toggle');
			
	});
	
	$(document).on('click', '#employee-detail-leaveDate-icon', function(e){
		
		e.preventDefault();
		e.stopPropagation();
		e.stopImmediatePropagation();
		
		var pk = $(this).attr('data-pk');
		var d = new Date();
		var n = d.getFullYear();
		
		try {

			$('#employee-detail-leaveDate').editable({
				name: 'leaveDate',
				url: employeeUpdatBaseUrl + '/' + pk,
				template: 'DD/MM/YYYY',
				format: 'YYYY/MM/DD',
				viewformat: 'DD/MM/YYYY',
				emptytext: "__/__/__",
				combodate: {
					minYear: n-35,
					maxYear: n,
				},
				ajaxOptions: {
					method: 'PUT',
					dataType: 'json',
					headers: {
						"groupId": parseInt("${groupId}")
					},
					contentType: "application/x-www-form-urlencoded;charset=utf-8",
				},
				params: function(params) { 
					return {
						leaveDate: params.value.split("/").join("")
					};
				},
				validate: function(value) {
					if (value.length < 1){
						return 'Đây là trường bắt buộc';
					}
				},
				success: function(data) {
					
					showMessageToastr("success", 'Yêu cầu của bạn được xử lý thành công!');
					
				},
				error: function(event, id, obj) {
					showMessageToastr("error", 'Yêu cầu của bạn được xử lý thất bại!');
				}
			}); 

		} catch (e) {
			console.log(e);
		} 
				 
		$('#employee-detail-leaveDate').editable('toggle');
			
	});
	
})(jQuery);

</script>