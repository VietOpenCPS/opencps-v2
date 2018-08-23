<#if (Request)??>
<#include "init.ftl">
</#if>

<div>
	<div class="row">
		<div class="col-xs-12 col-sm-12">
			<button id="btn_add_service_process_step" class="k-button btn-primary" title="Thêm bước xử lý"><i class="glyphicon glyphicon-plus"></i> Thêm bước xử lý </button>
		</div>
	</div>
	<div class="row MT10">
		<div>
			<!-- list view header -->
			<ul class="mimic-table">
				<li class="clearfix">
					<div class="col-sm-1 text-center">
						<b>STT</b>
					</div>
					<div class="col-sm-5 text-center">
						<b>Tên bước</b>
					</div>
					<div class="col-sm-2 text-center">
						<b>Trạng thái</b>
					</div>
					<div class="col-sm-2 text-center">
						<b>Thời gian xử lý</b>
					</div>
					<div class="col-sm-2 text-center">
						<b>Hành động</b>
					</div>
				</li>
			</ul>
			<ul id ="service_process_step_listview" class="mimic-table"></ul>
			<div id="service_process_step_bager" class="k-pager-wrap full-width-pager pull-right PR15 PB15"></div>
		</div>

		<script type="text/x-kendo-template" id="service_process_step_template">
			<li class="clearfix eq-height-lg" data-pk="#: id #" style="padding: 10px 0 10px 5px;" role="option" aria-selected="true">
				<div class="col-sm-1 text-center center-all">
					#:itemIndex #
				</div>
				<div class="col-sm-5 item-serviceinfo text-hover-blue hover-pointer align-middle-lg" data-pk="#: id #">
					#: stepName #
				</div>
				<div class="col-sm-2 align-middle-lg text-center">
					#if (dossierStatus == "releasing"){
						#Chờ lấy kết quả#
					} 
					else if (dossierStatus == "submitting"){
						#Đã gửi#
					}
					else if (dossierStatus == "paid"){
						#Đã báo nộp phí#
					}
					else if (dossierStatus == "collecting"){
						#Chờ chuyển phát#
					}
					else if (dossierStatus == "crosshandover"){
						#Hồ sơ chờ bàn giao liên thông cơ quan#
					}
					else if (dossierStatus == "ended"){
						#Hồ sơ kết thúc quy trình xử lý#
					}
					else if (dossierStatus == "outstanding"){
						#Hồ sơ thanh toán sau#
					}
					else if (dossierStatus == "presubmitting"){
						#Hồ sơ mới gửi chờ thanh toán#
					}
					else if (dossierStatus == "new"){
						#Tạo mới#
					}
					else if (dossierStatus == "receiving"){
						#Chờ tiếp nhận#
					}
					else if (dossierStatus == "waiting"){
						#Chờ bổ sung#
					}
					else if (dossierStatus == "paying"){
						#Chờ thanh toán#
					}
					else if (dossierStatus == "processing"){
						#Đang giải quyết#
					}
					else if (dossierStatus == "done"){
						#Hoàn thành#
					}
					else if (dossierStatus == "done"){
						#Hệ thống đang xử lý#
					}
					else if (dossierStatus == "error"){
						#Lỗi xử lý#
					}
					else if (dossierStatus == "denied"){
						#Đã từ chối#
					}
					else if (dossierStatus == "cancelled"){
						#Đã hủy hồ sơ#
					}
					else if (dossierStatus == "handover"){
						#Chuyển liên thông#
					}#
				</div>
				<div class="col-sm-2 align-middle-lg text-center">
					#: durationCount #
				</div>
				<div class="col-sm-2 text-center">
					<a class="btn-group btn-edit-service-process-step" data-pk="#: id #" href="\\#" title="Sửa">
						<i aria-hidden="true" class="fa fa-pencil"></i>
					</a>
					<a class="btn-group k-delete-button" href="\\#" title="Xóa">
						<i aria-hidden="true" class="fa fa-times"></i>
					</a>
				</div>
			</li>
		</script>
	</div>

	<script type="text/javascript">
		(function($){
			var serviceProcessStepDataSource = new kendo.data.DataSource({
				transport: {
					read: function(options) {
						console.log("options");
						console.log(options);
						
						$.ajax({
							url: "${api.server}" + "/serviceprocesses/" + options.data.serviceProcessId + "/steps",
							type: "GET",
							dataType: "json",
							headers: {"groupId": ${groupId}},
							data: {
								keywords: options.data.keywords
							},
							success: function(result) {
								if(result.data){
									options.success(result);
								}else {
									options.success({
										"data" : [],
										"total" : 0
									});
								}
							},
							error: function(result) {
								options.error(result);
							}
						});

					},
					destroy: function(options) {
						if (options && options.data && options.data.stepCode){
							$.ajax({
								url: "${api.server}" + "/serviceprocesses/" + $("#btn_save_service_process").attr("data-pk") + "/steps/" + options.data.stepCode,
								dataType: "json",
								type: "DELETE",
								headers: {"groupId": ${groupId}},
								success: function(result) {
									options.success(result);
									notification.show({
										message: "Yêu cầu được thực hiện thành công"
									}, "success");
								},
								error: function(result) {
									options.error(result);
									notification.show({
										message: "Xẩy ra lỗi, vui lòng thử lại"
									}, "error");
								}
							});
						}
					},
					parameterMap: function(options, operation) {
						if (operation !== "read" && options.models) {
							return {
								models: kendo.stringify(options.models)
							};
						}
					},
				},
				error: function(e){
					this.cancelChanges();
				},
				schema: {
					total: "total",
					data: "data",
					model : { id: "processStepId" },
				},
				pageSize: 10,
				serverPaging: false,
				serverSorting: false,
				serverFiltering: false
			});

			var localIndex = 0;
			$("#service_process_step_listview").kendoListView({
				dataSource: serviceProcessStepDataSource,
				template: function(data){
					var _pageSize = serviceProcessStepDataSource.pageSize();
					localIndex++;
					var currentPage = $("#service_process_step_bager").data("kendoPager").page();
					var totalPage =  $("#service_process_step_bager").data("kendoPager").totalPages();
					var index = (currentPage-1)*_pageSize + localIndex;
					data.itemIndex = index;
					return kendo.template($("#service_process_step_template").html())(data);
				},
				dataBound: function() {
					localIndex = 0;
				},
				autoBind: false,
				remove: function(e) {
					if(!confirm("Xác nhận xóa bước: " + e.model.get("stepName") + "?")){
						e.preventDefault();
					}
				}
			});

			$("#service_process_step_bager").kendoPager({
				dataSource: serviceProcessStepDataSource,
				buttonCount: 3,
				pageSizes: [5, 10, 20, 50],
				info: false,
				messages: {
					itemsPerPage: ""
				}
			});

			$(document).on("click", ".btn-edit-service-process-step", function(event){
				event.preventDefault();

				$("#serviceprocess_step_container").hide();
				$("#serviceprocess_detail_formstep_container").show();

				var processStepId = $(this).attr("data-pk");
				var serviceProcessId = $("#btn_save_service_process").attr("data-pk");

				var processStep = serviceProcessStepDataSource.get(processStepId);

				var viewModel = kendo.observable({
					stepCode: processStep.stepCode,
					stepName: processStep.stepName,
					sequenceNo: processStep.sequenceNo,
					dossierStatus: processStep.dossierStatus,
					dossierSubStatus: function(e){

						//console.log(processStep.dossierSubStatus);
						//console.log($("#dossierSubStatus").val());
						/*if($("#dossierSubStatus").val() && processStep.dossierSubStatus !== $("#dossierSubStatus").val()){
							return $("#dossierSubStatus").val();
						}else {
							$("#dossierSubStatus").data("kendoComboBox").dataSource.read({
								parent : processStep.dossierStatus
							});
						}*/
						$("#dossierSubStatus").data("kendoComboBox").dataSource.read({
							parent : processStep.dossierStatus
						});
						return processStep.dossierSubStatus;
					},
					editable: processStep.editable,
					durationCount: processStep.durationCount,
					customProcessUrl: processStep.customProcessUrl,
					briefNote: processStep.briefNote,
					stepInstruction: processStep.stepInstruction,
					lockState : processStep.lockState
				});

				kendo.bind($("#process_detail_form"), viewModel);

				$.ajax({
					url: "${api.server}" + "/serviceprocesses/" + serviceProcessId + "/steps/" + processStep.stepCode + "/roles",
					type: "GET",
					dataType: "json",
					headers: {"groupId": ${groupId}},
					success: function(result) {
						var data = result.data;

						var controlForm = $('.service-process-form-step-controls');
						controlForm.find('.service-process-form-step-entry:not(:last)').remove();

						if (data && data.length >= 1){

							var role = data[0];
							var entry = $('.service-process-form-step-entry:first');
							entry.find('input[type=radio]').attr('name', "moderator_1");
							entry.find('select').val(role.roleId);
							entry.find('input[name=condition]').val(role.condition);
							entry.find('input[type=radio][value='+role.moderator+']').prop('checked',true);

							for (var i = 1; i < data.length; i++) {
								var role = data[i];

								var currentEntry = $('.service-process-form-step-entry:last');

								var radioValue = currentEntry.find('input[type=radio]:checked').val();

								var newEntry = $(currentEntry.clone()).appendTo(controlForm);

								var index = controlForm.children().length;
								var name = 'moderator_' + index;
								newEntry.find('input[type=radio]').attr('name', name);

								newEntry.find('select').val(role.roleId);
								newEntry.find('input[name=condition]').val(role.condition);
								newEntry.find('input[type=radio][value=' + role.moderator + ']').prop('checked',true);
								currentEntry.find('input[type=radio][value=' + radioValue + ']').prop('checked',true);

								controlForm.find('.service-process-form-step-entry:not(:last) .btn-add')
								.removeClass('btn-add').addClass('btn-remove')
								.removeClass('btn-success').addClass('btn-danger')
								.html('<span class="glyphicon glyphicon-minus"></span>');
							}
						} else {
							var viewModel = kendo.observable({
								roleId: "",
								moderator: "",
								condition: ""
							});

							kendo.bind($(".service-process-form-entry")[0], viewModel);
						}
					}
				});

				$("#btn_save_service_process_step").attr("data-pk", $(this).attr("data-pk"));
			});

			$("#btn_cancle_service_process_step").click(function(e){
				e.preventDefault();
				$("#serviceprocess_step_container").show();
				$("#serviceprocess_detail_formstep_container").hide();
			});

			$(document).on("click", "#btn_add_service_process_step", function(event){
				event.preventDefault();
				$("#serviceprocess_step_container").hide();
				$("#serviceprocess_detail_formstep_container").show();

				var viewModel = kendo.observable({
					stepCode: "",
					stepName: "",
					sequenceNo: "",
					dossierStatus: "",
					dossierSubStatus: "",
					editable: "",
					durationCount: "",
					customProcessUrl: "",
					briefNote: "",
					stepInstruction: "",
					lockState : ""
				});

				kendo.bind($("#process_detail_form"), viewModel);

				$(".service-process-form-step-entry:not(:last)").remove();

				$("#btn_save_service_process_step").attr("data-pk", "");
			});

			$(document).on("click", "#btn_save_service_process_step", function(event){
				event.preventDefault();

				var lockState = $("#lockState").val();

				if(lockState){
					if(!lockState.startsWith("LOCK") && !lockState.startsWith("UPDATE")){
						notification.show({
							message: "Lock State phải bắt đầu bằng LOCK hoặc UPDATE"
						}, "error");
						return ;
					}
				}

				$(".service-process-detail ul.nav.nav-tabs li:not(:first)").removeClass("disabled-tab");

				var serviceProcessId = $("#btn_save_service_process").attr("data-pk");
				var processStepId = $(this).attr("data-pk");

				if (processStepId){
					updateServiceProcessStep(serviceProcessId, processStepId);
				} else {
					addServiceProcessStep(serviceProcessId);
				}
			});

			var updateServiceProcessStep = function(serviceProcessId, processStepId){

				var stepCode = serviceProcessStepDataSource.get(processStepId).stepCode;

				$.ajax({
					url: "${api.server}" + "/serviceprocesses/" + serviceProcessId + "/steps/" + stepCode,
					type: "PUT",
					dataType: "json",
					headers: {"groupId": ${groupId}},
					async: false,
					data: {
						stepCode: $("#stepCode").val(),
						stepName: $("#stepName").val(),
						sequenceNo: $("#sequenceNo").val(),
						dossierStatus: $("#dossierStatus").val(),
						dossierSubStatus: $("#dossierSubStatus").val(),
						editable: $("#editable").prop("checked"),
						durationCount: $("#durationCount_").val(),
						customProcessUrl: $("#customProcessUrl").val(),
						briefNote: $("#briefNote").val(),
						stepInstruction: $("#stepInstruction").val(),
						lockState : $("#lockState").val(),
					},
					success: function(result) {
						notification.show({
							message: "Yêu cầu được thực hiện thành công"
						}, "success");

						// var serviceProcessStep = serviceProcessStepDataSource.get(processStepId);

						// serviceProcessStep.set("stepCode", $("#stepCode").val());
						// serviceProcessStep.set("stepName", $("#stepName").val());
						// serviceProcessStep.set("sequenceNo", $("#sequenceNo").val());
						// serviceProcessStep.set("dossierStatus", $("#dossierStatus").val());
						// serviceProcessStep.set("dossierSubStatus", $("#dossierSubStatus").val());
						// serviceProcessStep.set("editable", $("#editable").prop("checked"));
						// serviceProcessStep.set("durationCount", $("#durationCount").val());
						// serviceProcessStep.set("customProcessUrl", $("#customProcessUrl").val());
						// serviceProcessStep.set("briefNote", $("#briefNote").val());
						// serviceProcessStep.set("stepInstruction", $("#stepInstruction").val());

						serviceProcessStepDataSource.read({serviceProcessId: $("#btn_save_service_process").attr("data-pk")});

						$("#serviceprocess_step_container").show();
						$("#serviceprocess_detail_formstep_container").hide();
					},
					error: function(result) {
						notification.show({
							message: "Xẩy ra lỗi, vui lòng thử lại"
						}, "error");
					}
				});

				// edit roles
				$.ajax({
					url: "${api.server}" + "/serviceprocesses/" + serviceProcessId + "/steps/" + stepCode + "/roles",
					type: "GET",
					dataType: "json",
					headers: {"groupId": ${groupId}},
					async: false,
					success: function(result) {
						if (result && result.data && result.data.length > 0){
							result.data.forEach(function(role){
								$.ajax({
									url: "${api.server}" + "/serviceprocesses/" + serviceProcessId + "/steps/" + stepCode + "/roles/" + role.roleId,
									type: "DELETE",
									dataType: "json",
									async: false,
									headers: {"groupId": ${groupId}},
									success: function(result) {

									},
									error: function(result) {
										notification.show({
											message: "Xẩy ra lỗi, vui lòng thử lại"
										}, "error");
									}
								});
							});
						}
					},
					error: function(result) {
						notification.show({
							message: "Xẩy ra lỗi, vui lòng thử lại"
						}, "error");
					}
				});

				$(".service-process-form-step-entry").each(function(){
					if ($(this).find('select[name=roleId]').val()){
						$.ajax({
							url: "${api.server}" + "/serviceprocesses/" + serviceProcessId + "/steps/" + stepCode + "/roles/" + $(this).find('select[name=roleId]').val(),
							type: "PUT",
							dataType: "json",
							headers: {"groupId": ${groupId}},
							async: false,
							data: {
								moderator: $(this).find('input[type=radio]:checked').val(),
								condition: $(this).find('input[name=condition]').val(),
							},
							error: function(result) {
								notification.show({
									message: "Xẩy ra lỗi, vui lòng thử lại"
								}, "error");
								console.log(result.responseJSON.description);
							}
						});
					}
				});
			}

			var addServiceProcessStep = function(serviceProcessId){

				$.ajax({
					url: "${api.server}" + "/serviceprocesses/" + serviceProcessId + "/steps",
					type: "POST",
					dataType: "json",
					headers: {"groupId": ${groupId}},
					data: {
						stepCode: $("#stepCode").val(),
						stepName: $("#stepName").val(),
						sequenceNo: $("#sequenceNo").val(),
						dossierStatus: $("#dossierStatus").val(),
						dossierSubStatus: $("#dossierSubStatus").val(),
						editable: $("#editable").prop("checked"),
						durationCount: $("#durationCount_").val(),
						customProcessUrl: $("#customProcessUrl").val(),
						briefNote: $("#briefNote").val(),
						stepInstruction: $("#stepInstruction").val(),
						lockState : $("#lockState").val(),
					},
					success: function(result) {
						notification.show({
							message: "Yêu cầu được thực hiện thành công"
						}, "success");

						serviceProcessStepDataSource.insert(0, {
							"processStepId": result.processStepId,
							"stepCode": $("#stepCode").val(),
							"stepName": $("#stepName").val(),
							"sequenceNo": $("#sequenceNo").val(),
							"dossierStatus": $("#dossierStatus").val(),
							"dossierSubStatus": $("#dossierSubStatus").val(),
							"editable": $("#editable").prop("checked"),
							"durationCount": $("#durationCount_").val(),
							"customProcessUrl": $("#customProcessUrl").val(),
							"briefNote": $("#briefNote").val(),
							"stepInstruction": $("#stepInstruction").val(),
						});

						// add roles
						$(".service-process-form-step-entry").each(function(){
							if ($(this).find('select[name=roleId]').val()){
								$.ajax({
									url: "${api.server}" + "/serviceprocesses/" + serviceProcessId + "/steps/" + result.stepCode + "/roles",
									type: "POST",
									dataType: "json",
									headers: {"groupId": ${groupId}},
									async: false,
									data: {
										roleId: $(this).find('select[name=roleId]').val(),
										moderator: $(this).find('input[type=radio]:checked').val(),
										condition: $(this).find('input[name=condition]').val(),
									},
									error: function(result) {
										notification.show({
											message: "Xẩy ra lỗi, vui lòng thử lại"
										}, "error");
										console.log(result.responseJSON.description);
									}
								});
							}
						});

						$("#serviceprocess_detail_formstep_container").hide();
						$("#serviceprocess_step_container").show();
					},
					error: function(result) {
						if (result.responseJSON.description == "DuplicateStepNoException"){
							notification.show({
								message: "Lỗi trùng mã bước"
							}, "error");
						} else {
							notification.show({
								message: "Xẩy ra lỗi, vui lòng thử lại"
							}, "error");
							console.log(result.responseJSON.description);
						}
					}
				});
			};
		})(jQuery);
	</script>
</div>
