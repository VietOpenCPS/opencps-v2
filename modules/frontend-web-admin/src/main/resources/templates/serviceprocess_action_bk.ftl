
<#if (Request)??>
<#include "init.ftl">
</#if>

<div>
	<div class="row">
		<div class="col-xs-12 col-sm-12">
			<button id="btn_add_service_process_action" class="k-button btn-primary" title="Thêm hành động"><i class="glyphicon glyphicon-plus"></i> Thêm thao tác </button>
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
						<b>Tên thao tác</b>
					</div>
					<div class="col-sm-2 text-center">
						<b>Bước thực hiện thao tác</b>
					</div>
					<div class="col-sm-2 text-center">
						<b>Bước sau</b>
					</div>
					<div class="col-sm-2 text-center">
						<b>Hành động</b>
					</div>
				</li>
			</ul>
			<ul id ="service_process_action_listview" class="mimic-table"></ul>
			<div id="service_process_action_bager" class="k-pager-wrap full-width-pager pull-right PR15 PB15"></div>
		</div>

		<script type="text/x-kendo-template" id="service_process_action_template">
			<li class="clearfix eq-height-lg" data-pk="#: id #" style="padding: 10px 0 10px 5px;" role="option" aria-selected="true">
				<div class="col-sm-1 text-center center-all">
					#: itemIndex #
				</div>
				<div class="col-sm-5 item-serviceinfo text-hover-blue hover-pointer align-middle-lg" data-pk="#: id #">
					#: actionName #
				</div>
				<div class="col-sm-2 align-middle-lg text-center">
					#: preStepName #
				</div>
				<div class="col-sm-2 align-middle-lg text-center">
					#: postStepName #
				</div>
				<div class="col-sm-2 text-center">
					<a class="btn-group btn-edit-service-process-action" data-pk="#: id #" href="\\#" title="Sửa">
						<i aria-hidden="true" class="fa fa-pencil"></i>
					</a>
					<a class="btn-group k-delete-button" href="\\#" title="Xóa">
						<i aria-hidden="true" class="fa fa-times"></i>
					</a>
				</div>
			</li>
		</script>
	</div>

	<!-- service process step form modal -->
	<div class="modal fade" id="service_process_action_form">
		<div class="modal-dialog modal-lg">
			<div class="modal-content"></div>
		</div>
	</div>

	<script type="text/javascript">
		(function($){
			var serviceProcessActionDataSource = new kendo.data.DataSource({
				transport: {
					read: function(options) {
						if ($("#btn_save_service_process").attr("data-pk")){
							$.ajax({
								url: "${api.server}" + "/serviceprocesses/" + $("#btn_save_service_process").attr("data-pk") + "/actions",
								type: "GET",
								dataType: "json",
								headers: {"groupId": ${groupId}},
								data: {
									keywords: options.data.keywords,
									page: options.data.page,
									pageSize: options.data.pageSize
								},
								success: function(result) {
									options.success(result);
								},
								error: function(result) {
									options.error(result);
								}
							});
						}
					},
					destroy: function(options) {
						$.ajax({
							url: "${api.server}" + "/serviceprocesses/" + $("#btn_save_service_process").attr("data-pk") + "/actions/" + options.data.processActionId,
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
					model : { id: "processActionId" },
				},
				pageSize: 10,
				serverPaging: false,
				serverSorting: false,
				serverFiltering: false
			});

			var localIndex = 0;
			$("#service_process_action_listview").kendoListView({
				dataSource: serviceProcessActionDataSource,
				template: function(data){
					var _pageSize = serviceProcessActionDataSource.pageSize();
					localIndex++;
					var currentPage = $("#service_process_action_bager").data("kendoPager").page();
					var totalPage =  $("#service_process_action_bager").data("kendoPager").totalPages();
					var index = (currentPage-1)*_pageSize + localIndex;
					data.itemIndex = index;
					return kendo.template($("#service_process_action_template").html())(data);
				},
				dataBound: function() {
					localIndex = 0;
				},
				autoBind: false,
				remove: function(e) {
					if(!confirm("Xác nhận xóa hành động: " + e.model.get("actionName") + "?")){
						e.preventDefault();
					}
				},
			});

			$("#service_process_action_bager").kendoPager({
				dataSource: serviceProcessActionDataSource,
				buttonCount: 3,
				pageSizes: [5, 10, 20, 50],
				info: false,
				messages: {
					itemsPerPage: ""
				}
			});

			$(document).on("click", ".btn-edit-service-process-action", function(event){
				event.preventDefault();

				$("#serviceprocess_action_container").hide();
				$("#serviceprocess_detail_formaction_container").show();

				var serviceProcessAction = serviceProcessActionDataSource.get(($(this).attr("data-pk")));

				var viewModel = kendo.observable({
					actionCode: serviceProcessAction.actionCode,
					actionName: serviceProcessAction.actionName,
					preStepCode: serviceProcessAction.preStepCode,
					postStepCode: serviceProcessAction.postStepCode,
					autoEvent: serviceProcessAction.autoEvent,
					preCondition: serviceProcessAction.preCondition,
					allowAssignUser: serviceProcessAction.allowAssignUser,
					assignUserId: serviceProcessAction.assignUserId,
					requestPayment: serviceProcessAction.requestPayment,
					paymentFee: serviceProcessAction.paymentFee,
					syncActionCode: serviceProcessAction.syncActionCode,
					rollbackable: serviceProcessAction.rollbackable,
					dossiertemplatesFileFilter: "",
					createDossierNo : serviceProcessAction.createDossierNo,
					eSignature : serviceProcessAction.eSignature

				});

				kendo.bind($("#serviceprocesses_detail_form_action"), viewModel);

				initStepCombobox();

				var controlForm = $('.service-process-create-dossier-file-form-action-controls');

				if (typeof serviceProcessAction.createDossierFiles === 'number'){
					console.log("type number");
					var currentEntry = $('.service-process-create-dossier-file-form-action-entry:last');
					currentEntry.find('select').val(serviceProcessAction.createDossierFiles);

				} else if (typeof serviceProcessAction.createDossierFiles === 'string'){
					console.log("type string");
					var createDossierFileArr = serviceProcessAction.createDossierFiles.split(",");

					var currentEntry = $('.service-process-create-dossier-file-form-action-entry:last');
					currentEntry.find('select').val(createDossierFileArr[0]);

					for (var i = 1; i < createDossierFileArr.length; i++){
						if (createDossierFileArr[i]){
							var fileTemplateNo = createDossierFileArr[i];
							if (fileTemplateNo){
								var currentEntry = $('.service-process-create-dossier-file-form-action-entry:last');
								var newEntry = $(currentEntry.clone()).appendTo(controlForm);
								newEntry.find('select').val(fileTemplateNo);
								controlForm.find('.service-process-create-dossier-file-form-action-entry:not(:last) .btn-add-action-create-dossier-file')
								.removeClass('btn-add-action-create-dossier-file').addClass('btn-remove')
								.removeClass('btn-success').addClass('btn-danger')
								.html('<span class="glyphicon glyphicon-minus"></span>');
							}
						}
					}
				} else if (serviceProcessAction.createDossierFiles){
					console.log("type array");
					var currentEntry = $('.service-process-create-dossier-file-form-action-entry:last');
					currentEntry.find('select').val(serviceProcessAction.createDossierFiles[0]);

					for (var i = 1; i < serviceProcessAction.createDossierFiles.length; i++){
						var fileTemplateNo = serviceProcessAction.createDossierFiles[i];
						if (fileTemplateNo){
							console.log(fileTemplateNo);
							var currentEntry = $('.service-process-create-dossier-file-form-action-entry:last');
							var newEntry = $(currentEntry.clone()).appendTo(controlForm);
							newEntry.find('select').val(fileTemplateNo);
							controlForm.find('.service-process-create-dossier-file-form-action-entry:not(:last) .btn-add-action-create-dossier-file')
							.removeClass('btn-add-action-create-dossier-file').addClass('btn-remove')
							.removeClass('btn-success').addClass('btn-danger')
							.html('<span class="glyphicon glyphicon-minus"></span>');
						}
					}
				}

				controlForm = $('.service-process-return-dossier-file-form-action-controls');

				if (typeof serviceProcessAction.returnDossierFiles === 'number'){
					var currentEntry = $('.service-process-return-dossier-file-form-action-entry:last');
					currentEntry.find('select').val(serviceProcessAction.returnDossierFiles);
				}  else if (typeof serviceProcessAction.returnDossierFiles === 'string'){

					var returnDossierFileArr = serviceProcessAction.returnDossierFiles.split(",");

					var currentEntry = $('.service-process-return-dossier-file-form-action-entry:last');
					currentEntry.find('select').val(returnDossierFileArr[0]);

					for (var i = 1; i < returnDossierFileArr.length; i++){
						if (returnDossierFileArr[i]){
							var fileTemplateNo = returnDossierFileArr[i];
							if (fileTemplateNo){
								var currentEntry = $('.service-process-return-dossier-file-form-action-entry:last');
								var newEntry = $(currentEntry.clone()).appendTo(controlForm);
								newEntry.find('select').val(fileTemplateNo);
								controlForm.find('.service-process-return-dossier-file-form-action-entry:not(:last) .btn-add-action-return-dossier-file')
								.removeClass('btn-add-action-return-dossier-file').addClass('btn-remove')
								.removeClass('btn-success').addClass('btn-danger')
								.html('<span class="glyphicon glyphicon-minus"></span>');
							}
						}
					}
				} else if (serviceProcessAction.returnDossierFiles){
					var currentEntry = $('.service-process-return-dossier-file-form-action-entry:last');
					currentEntry.find('select').val(serviceProcessAction.returnDossierFiles[0]);
					for (var i = 1; i < serviceProcessAction.returnDossierFiles.length; i++){
						var fileTemplateNo = serviceProcessAction.returnDossierFiles[i];
						if (fileTemplateNo){
							var currentEntry = $('.service-process-return-dossier-file-form-action-entry:last');

							var newEntry = $(currentEntry.clone()).appendTo(controlForm);

							newEntry.find('select').val(fileTemplateNo);

							controlForm.find('.service-process-return-dossier-file-form-action-entry:not(:last) .btn-add-action-return-dossier-file')
							.removeClass('btn-add-action-return-dossier-file').addClass('btn-remove')
							.removeClass('btn-success').addClass('btn-danger')
							.html('<span class="glyphicon glyphicon-minus"></span>');
						}
					}
				}

				$("#btn_save_service_process_action").attr("data-pk", $(this).attr("data-pk"));
			});

			$("#btn_cancle_service_process_action").click(function(e){
				e.preventDefault();
				$("#serviceprocess_action_container").show();
				$("#serviceprocess_detail_formaction_container").hide();
			});

			$(document).off("click", "#btn_add_service_process_action");
			$(document).on("click", "#btn_add_service_process_action", function(event){
				event.preventDefault();
				$("#serviceprocess_action_container").hide();
				$("#serviceprocess_detail_formaction_container").show();

				var viewModel = kendo.observable({
					actionCode: "",
					actionName: "",
					preStepCode: "",
					postStepCode: "",
					autoEvent: "",
					preCondition: "",
					allowAssignUser: "",
					assignUserId: "",
					requestPayment: "",
					paymentFee: "",
					createDossierFiles: "",
					returnDossierFiles: "",
					syncActionCode: "",
					rollbackable: "",
					dossiertemplatesFileFilter: "",
					createDossierNo : "",
					eSignature : ""
				});

				initStepCombobox();

				kendo.bind($("#serviceprocesses_detail_form_action"), viewModel);

				$("#btn_save_service_process_action").attr("data-pk", "");
			});

			$(document).off("click", "#btn_save_service_process_action");
			$(document).on("click", "#btn_save_service_process_action", function(event){
				event.preventDefault();

				var serviceProcessId = $("#btn_save_service_process").attr("data-pk");
				var actionId = $(this).attr("data-pk");

				if (actionId){
					updateServiceProcessAction(serviceProcessId, actionId);
				} else {
					addServiceProcessAction(serviceProcessId);
				}

				/*$("#serviceprocess_action_container").show();
				$("#serviceprocess_detail_formaction_container").hide();*/
			});

			var updateServiceProcessAction = function(serviceProcessId, actionId){
				if (validateProcessAction()){
					$.ajax({
						url: "${api.server}" + "/serviceprocesses/" + serviceProcessId + "/actions/" + actionId,
						type: "PUT",
						dataType: "json",
						headers: {"groupId": ${groupId}},
						data: {
							actionCode: $("#actionCode").val(),
							actionName: $("#actionName").val(),
							preStepCode: $("#preStepCode").val(),
							postStepCode: $("#postStepCode").val(),
							autoEvent: $("#autoEvent").val(),
							preCondition: $("#preCondition").val(),
							allowAssignUser: $("#allowAssignUser").prop("checked"),
							assignUserId: $("#assignUserId").val(),
							requestPayment: $("#requestPayment").prop("checked"),
							paymentFee: $("#paymentFee").val(),
							createDossierFiles: getCreateDossierFiles(),
							returnDossierFiles: getReturnDossierFiles(),
							syncActionCode: $("#syncActionCode").val(),
							rollbackable: $("#rollbackable").prop("checked"),
							createDossierNo : $("#createDossierNo").prop("checked"),
							eSignature : $("#eSignature").prop("checked")
						},
						success: function(result) {
							notification.show({
								message: "Yêu cầu được thực hiện thành công"
							}, "success");
							$("#serviceprocess_action_container").show();
							$("#serviceprocess_detail_formaction_container").hide();
							// var serviceProcessAction = serviceProcessActionDataSource.get(actionId);

							// serviceProcessAction.set("actionCode", $("#actionCode").val());
							// serviceProcessAction.set("actionName", $("#actionName").val());
							// serviceProcessAction.set("preStepCode", $("#preStepCode").val());
							// serviceProcessAction.set("postStepCode", $("#postStepCode").val());
							// serviceProcessAction.set("autoEvent", $("#autoEvent").val());
							// serviceProcessAction.set("preCondition", $("#preCondition").val());
							// serviceProcessAction.set("allowAssignUser", $("#allowAssignUser").prop("checked"));
							// serviceProcessAction.set("assignUserId", $("#assignUserId").val());
							// serviceProcessAction.set("requestPayment", $("#requestPayment").prop("checked"));
							// serviceProcessAction.set("paymentFee", $("#paymentFee").val());
							// serviceProcessAction.set("createDossierFiles", getCreateDossierFiles());
							// serviceProcessAction.set("returnDossierFiles", getReturnDossierFiles());
							// serviceProcessAction.set("syncActionCode", $("#syncActionCode").val());
							// serviceProcessAction.set("rollbackable", $("#rollbackable").prop("checked"));

							serviceProcessActionDataSource.read();

							
						},
						error: function(result) {
							if (result.responseJSON.description){
								notification.show({
									message: result.responseJSON.description
								}, "error");
							} else {
								notification.show({
									message: "Xẩy ra lỗi, vui lòng thử lại"
								}, "error");
							}

							$("#serviceprocess_action_container").show();
							$("#serviceprocess_detail_formaction_container").hide();
						}
					});
				}
			}

			var addServiceProcessAction = function(serviceProcessId){
				if (validateProcessAction()){
					$.ajax({
						url: "${api.server}" + "/serviceprocesses/" + serviceProcessId + "/actions",
						type: "POST",
						dataType: "json",
						headers: {"groupId": ${groupId}},
						data: {
							actionCode: $("#actionCode").val(),
							actionName: $("#actionName").val(),
							preStepCode: $("#preStepCode").val(),
							postStepCode: $("#postStepCode").val(),
							autoEvent: $("#autoEvent").val(),
							preCondition: $("#preCondition").val(),
							allowAssignUser: $("#allowAssignUser").prop("checked"),
							assignUserId: $("#assignUserId").val(),
							requestPayment: $("#requestPayment").prop("checked"),
							paymentFee: $("#paymentFee").val(),
							createDossierFiles: getCreateDossierFiles(),
							returnDossierFiles: getReturnDossierFiles(),
							syncActionCode: $("#syncActionCode").val(),
							rollbackable: $("#rollbackable").prop("checked"),
							createDossierNo : $("#createDossierNo").prop("checked"),
							eSignature : $("#eSignature").prop("checked")
						},
						success: function(result) {
							notification.show({
								message: "Yêu cầu được thực hiện thành công"
							}, "success");

							$("#serviceprocess_action_container").show();
							$("#serviceprocess_detail_formaction_container").hide();

							serviceProcessActionDataSource.insert(0, {
								"processActionId": result.processActionId,
								"actionCode": $("#actionCode").val(),
								"actionName": $("#actionName").val(),
								"preStepCode": $("#preStepCode").val(),
								"preStepName" : $("#preStepCode").data("kendoComboBox").text(),
								"postStepCode": $("#postStepCode").val(),
								"postStepName" : $("#postStepCode").data("kendoComboBox").text(),
								"autoEvent": $("#autoEvent").val(),
								"preCondition": $("#preCondition").val(),
								"allowAssignUser": $("#allowAssignUser").prop("checked"),
								"assignUserId": $("#assignUserId").val(),
								"requestPayment": $("#requestPayment").prop("checked"),
								"paymentFee": $("#paymentFee").val(),
								"createDossierFiles": getCreateDossierFiles(),
								"returnDossierFiles": getReturnDossierFiles(),
								"syncActionCode": $("#syncActionCode").val(),
								"rollbackable": $("#rollbackable").prop("checked"),
								"createDossierNo" : $("#createDossierNo").prop("checked"),
								"eSignature" : $("#eSignature").prop("checked")
							});

							
						},
						error: function(result) {
							if (result.responseJSON.description){
								notification.show({
									message: result.responseJSON.description
								}, "error");
							} else {
								notification.show({
									message: "Xẩy ra lỗi, vui lòng thử lại"
								}, "error");
							}

							$("#serviceprocess_action_container").show();
							$("#serviceprocess_detail_formaction_container").hide();
						}
					});
				}
			};

			function getCreateDossierFiles(){
				var createDossierFiles = "";
				$(".service-process-create-dossier-file-form-action-entry").each(function(){
					createDossierFiles += $(this).find("select").val() + ",";
				});
				return createDossierFiles;
			}

			function getReturnDossierFiles(){
				var returnDossierFiles = "";
				$(".service-process-return-dossier-file-form-action-entry").each(function(){
					returnDossierFiles += $(this).find("select").val() + ",";
				});
				return returnDossierFiles;
			}

			function validateProcessAction(){
				if (!$("#actionCode").val()){
					notification.show({
						message: "Mời nhập mã thao tác"
					}, "error");
					return false;
				}
				if (!$("#actionName").val()){
					notification.show({
						message: "Mời nhập tên thao tác"
					}, "error");
					return false;
				}
				/*if ($("#allowAssignUser").prop("checked") && !$("#assignUserId").val()){
					notification.show({
						message: "Mời chọn người được phân công"
					}, "error");
					return false;
				}*/
				if ($("#requestPayment").prop("checked") && !$("#paymentFee").val()){
					notification.show({
						message: "Mời nhập phí"
					}, "error");
					return false;
				}
				return true;
			}

			function initStepCombobox(){
				$(".service-process-create-dossier-file-form-action-entry:not(:last)").remove();
				$(".service-process-return-dossier-file-form-action-entry:not(:last)").remove();

				$(".service-process-create-dossier-file-form-action-entry:last").find("select").val("");
				$(".service-process-return-dossier-file-form-action-entry:last").find("select").val("");

				$("#preStepCode").kendoComboBox({
					dataTextField: "stepName",
					dataValueField: "stepCode",
					filter: "contains",
					async: false,
					dataSource : {
						transport : {
							read : {
								url : "${api.server}/serviceprocesses/" + $("#btn_save_service_process").attr("data-pk") + "/steps",
								dataType : "json",
								type : "GET",
								headers: {"groupId": ${groupId}},
								success : function(result){

								},
								error : function(xhr){

								}
							}
						},
						schema: {
							data : "data",
							total : "total"
						}
					},
					noDataTemplate: 'Không có dữ liệu'
				});

				$("#postStepCode").kendoComboBox({
					dataTextField: "stepName",
					dataValueField: "stepCode",
					filter: "contains",
					async: false,
					dataSource : {
						transport : {
							read : {
								url : "${api.server}/serviceprocesses/" + $("#btn_save_service_process").attr("data-pk") + "/steps",
								dataType : "json",
								type : "GET",
								headers: {"groupId": ${groupId}},
								success : function(result){

								},
								error : function(xhr){

								}
							}
						},
						schema: {
							data : "data",
							total : "total"
						}
					},
					noDataTemplate: 'Không có dữ liệu'
				});
			}
		})(jQuery);
	</script>
</div>
