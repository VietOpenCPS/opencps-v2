<#include "init.ftl">
<div class="row">

	<div class="col-sm-12">

		<div class="panel-group">

			<div class="panel panel-default">

				<div class="panel-heading">

					<h4 class="panel-title border-bottom border-color-lightblue">

						<a data-toggle="collapse" href="#employee_formTemplate-collapse">

							Hồ sơ nhân sự

						</a>

					</h4>

				</div>

				<div id="employee_formTemplate-collapse" class="panel-collapse collapse in">
					
					<div class="panel-body">
					
						<div class="form-group">
							
							<textarea id="employee-formtemplate-inp" style="display: none;">
								${(employee_formtTemplate.formData)!}
							</textarea>

							<div id="employee-formtemplate-html"></div>

						</div>

						<button id="employee-formtemplate-submit-btn" 
							data-pk="${(employee.employeeId)!}" 
							class="btn btn-primary image-preview-input border-rad-4"> 
							
							<i class="fa fa-check-circle"></i>
							<span class="lfr-btn-label">Cập nhật thông tin</span>

						</button>

					</div>

				</div>

			</div>

		</div>

	</div>	

</div>


<script type="text/javascript">

(function($) {

	var formTemplate = $("#employee-formtemplate-inp").val().trim();
		
	try {

		if (formTemplate!=null && formTemplate!="") {

			var dataForm = JSON.parse(formTemplate);
			$("#employee-formtemplate-html").alpaca(dataForm);

		}

	} catch (e) {
		console.log(e);
		showMessageToastr("error","Mẫu Alpaca không hợp lệ!!!");

	}

	$(document).on('click', '#employee-formtemplate-submit-btn', function(event){
		
		event.preventDefault();
		event.stopPropagation();
		event.stopImmediatePropagation();

		var employeeFormtemplateUpdateBaseUrl = "";

		var metaData = "";

		if ($("#employee-formtemplate-html").alpaca("get")!=null) {

			metaData = $("#employee-formtemplate-html").alpaca("get").getValue();

		}

		metaData = (metaData==null || metaData=="")?"":JSON.stringify(metaData);

		$.ajax({
			url: employeeFormtemplateUpdateBaseUrl,
			headers: {
				"groupId": ${groupId}
			},
			data: {
				
				metaData: metaData
			},
			type: 'PUT',
			dataType: 'json',
			contentType: 'application/x-www-form-urlencoded; charset=utf-8',
			success: function(data, textStatus, xhr) {
			
				showMessageToastr("success", 'Yêu cầu của bạn được xử lý thành công!');
				
			},
			error: function(xhr, textStatus, errorThrown) {
				
				showMessageByAPICode(xhr.status);
			
			}
		});
		
	});

})(jQuery);
</script>