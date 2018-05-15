<#include "init.ftl">

<div class="modal-header form-group">

	<button type="button" class="close" data-dismiss="modal" aria-label="Close">
		<span aria-hidden="true">&times;</span>
	</button>
	
	<h4 class="modal-title">
		
		Thêm chức vụ/ phòng ban
				
	</h4>
	
</div>

<div class="modal-body">
	
	<form id="employee-jobpos-create-form">

		<div class="form-group">
		
			<label for="employee-jobpos-create-jobpos">Chức vụ

				<span class="icon-asterisk text-warning"></span>

			</label>
			<input type="text" id="employee-jobpos-create-jobpos" name="employee-jobpos-create-jobpos" class="form-control"/>
		
		</div>

		<div class="form-group">
		
			<label for="employee-jobpos-create-working-unit">Phòng ban

				<span class="icon-asterisk text-warning"></span>

			</label>
			<input type="text" id="employee-jobpos-create-working-unit" name="employee-jobpos-create-working-unit" class="form-control"/>
		
		</div>

		<div class="form-group">
		
			<div class="font-bold"> 
				
				<div class="form-inline input-checkbox-wrapper"> 
				
					<input class="field" id="employee-jobpos-create-main-jobpos" name="employee-jobpos-create-main-jobpos" type="checkbox">

					<label for="employee-jobpos-create-main-jobpos"> Công việc chính </label> 
					
				</div> 
				
			</div>
		
		</div>


		<div class="eq-height">
				
			<button class="btn btn-sm btn-active" id="employee-jobpos-create-submit-btn" name="employee-jobpos-create-submit-btn" type="button" data-pk="${(employee.employeeId)!}">
				<i class="fa fa-check-circle"></i>
				<span class="lfr-btn-label">Xác nhận</span>
			</button>
		
			<button class="btn btn-default btn-sm MLA" data-dismiss="modal" value="Quay lại">
				<i class="icon-undo"></i>
				<span class="lfr-btn-label">Quay lại</span>
			</button>

		</div>

	</form>

</div>

<script type="text/javascript" charset="utf-8">
	
(function($) {
	
	var getJobPosBaseUrl = "${api.endpoint}/jobpos";

	var getJobPosDataSource = new kendo.data.DataSource({
		
		transport: {
		
			read: function(options) {
				
				$.ajax({
				  
					url: getJobPosBaseUrl,
					dataType: "json",
					type: 'GET',
					headers: {
						"groupId": ${groupId}
					},
					data: {
						sort: "treeIndex",
						order: false

					},
					success: function(result) {
						
						result["data"] = result.total==0 ? []: result["data"];
						options.success(result);
						
					}
				
				});
			}
		},
		schema: {
			data: "data",
			total: "total",
			model: {
				id: "jobPosId",
				fields: {
					jobPosId: {
						editable: false,
						nullable: true
					},
					title: {
						type: "string"
					}
				}
			}
		}
		
	});

	$("#employee-jobpos-create-jobpos").kendoDropDownList({
		
		dataTextField: "title",
		dataValueField: "jobPosId",
		dataSource: getJobPosDataSource
		
	});

	var getWorkingUnitBaseUrl = "${api.endpoint}/workingunits";

	var getWorkingUnitDataSource = new kendo.data.DataSource({
		
		transport: {
		
			read: function(options) {
				
				$.ajax({
				  
					url: getWorkingUnitBaseUrl,
					dataType: "json",
					type: 'GET',
					headers: {
						"groupId": ${groupId}
					},
					data: {
						sort: "treeIndex",
						order: false

					},
					success: function(result) {
						
						result["data"] = result.total==0 ? []: result["data"];
						options.success(result);
						
					}
				
				});
			}
		},
		schema: {
			data: "data",
			total: "total",
			model: {
				id: "workingUnitId",
				fields: {
					workingUnitId: {
						editable: false,
						nullable: true
					},
					name: {
						type: "string"
					}
				}
			}
		}
		
	});

	$("#employee-jobpos-create-working-unit").kendoDropDownList({
		
		dataTextField: "name",
		dataValueField: "workingUnitId",
		dataSource: getWorkingUnitDataSource
		
	});

	$(document).on('click', '#employee-jobpos-create-submit-btn', function(event){
		
		event.preventDefault();
		event.stopPropagation();
		event.stopImmediatePropagation();

		var createEmployeeJobposBaseUrl = "${api.endpoint}/employees/"+ $(this).attr('data-pk') +"/jobpos";
		
		var checkFormValidate = $("#employee-jobpos-create-form").kendoValidator().data("kendoValidator");

		if (!checkFormValidate.validate()) {
			return;
		}

		var workingUnitId = $("#employee-jobpos-create-working-unit").data("kendoDropDownList");
		var jobPosId = $("#employee-jobpos-create-jobpos").data("kendoDropDownList");
		var mainJobPos = $('#employee-jobpos-create-main-jobpos').is(":checked")?"true":"false";

		$.ajax({
			url: createEmployeeJobposBaseUrl,
			headers: {
				"groupId": ${groupId}
			},
			data: {
				
				workingUnitId: workingUnitId.value(),
				jobPosId: jobPosId.value(),
				mainJobPos: mainJobPos

			},
			type: 'POST',
			dataType: 'json',
			contentType: 'application/x-www-form-urlencoded; charset=utf-8',
			success: function(data, textStatus, xhr) {

				var icon = '<i class="fa fa-angle-right" aria-hidden="true"></i>';
				
				var ul = $("#employee-detail-jobpos");

				if (data.mainJobPos == true) {

					icon = '<i class="fa fa-suitcase employee-main-jobpos" title="Chức vụ chính" aria-hidden="true"></i>';

					$('#employee-detail-jobpos li i.fa.fa-suitcase').each(function(i, iconElement) {
						
						$(iconElement).removeClass();
						$(iconElement).addClass('fa fa-angle-right');

					});

				}

				var li = '<li class="PT5 PB5 jobpos-item"><div class="row M0 "><div class="col-sm-10 text-ellipsis">' + icon + '<span class="PL10">'+ data.workingUnitName +' - '+ data.jobPosTitle + '</span></div><div class="col-sm-2 text-right"><span data-pk="'+data.employeeJobPosId+'" id="employee-detail-jobpos-'+data.employeeJobPosId+'" class="employee-detail-delete-jobpos"><i aria-hidden="true" class="fa fa-times"></i></span></div></div></li>';
				ul.append(li);

				$("#modal").trigger({ type: "click" });
				showMessageToastr("success", 'Yêu cầu của bạn được xử lý thành công!');
				
			},
			error: function(xhr, textStatus, errorThrown) {
				
				showMessageByAPICode(xhr.status);
			
			}
		});
		
	});

})(jQuery);

</script>