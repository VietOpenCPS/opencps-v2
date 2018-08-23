
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
						<b class="hover-pointer" id="sortPreStepCode" sort="preStepCode" sort-type="asc">Bước thực hiện thao tác </b> <i class="fa fa-sort text-light-gray" aria-hidden="true"></i>
					</div>
					<div class="col-sm-2 text-center">
						<b class="hover-pointer" id="sortPostStepCode" sort="postStepCode" sort-type="asc">Bước sau </b><i class="fa fa-sort text-light-gray" aria-hidden="true"></i>
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
									if(result.data){
										options.success(result);
										serviceProcessActionDataSource.sort({ field: "preStepCode", dir: "asc" });
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

			var sortFieldProcessAction = function(selected){
				var sortItem = $(selected).attr("sort");
				var sortType = $(selected).attr("sort-type");

				if (sortType == "desc") {
					serviceProcessActionDataSource.sort({ field: sortItem, dir: "desc" });
					$(selected).attr("sort-type","asc");
				} else {
					serviceProcessActionDataSource.sort({ field: sortItem, dir: "asc" });
					$(selected).attr("sort-type","desc");
				}
			};

			$("#sortPreStepCode").click(function(){
				sortFieldProcessAction(this);
			});

			$("#sortPostStepCode").click(function(){
				sortFieldProcessAction(this);
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

				console.log("serviceProcessAction==============",serviceProcessAction);

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
					eSignature : serviceProcessAction.eSignature,
					configNote : serviceProcessAction.configNote,
					dossiertemplatesFileFilter : serviceProcessAction.dossierTemplateNo

				});

				kendo.bind($("#serviceprocesses_detail_form_action"), viewModel);

				initStepCombobox();

				$("#createDossierFiles").data("kendoMultiSelect").dataSource.read({
					id : serviceProcessAction.dossierTemplateNo
				});
				
				console.log("serviceProcessAction.createDossierFiles==============",serviceProcessAction.createDossierFiles);

				$("#createDossierFiles").data("kendoMultiSelect").dataSource.fetch(function(){
					if (typeof serviceProcessAction.createDossierFiles === 'number'){
						console.log("type number");
						$("#createDossierFiles").data("kendoMultiSelect").value(serviceProcessAction.createDossierFiles);


					} else if (typeof serviceProcessAction.createDossierFiles === 'string'){
						console.log("type string");
						var createDossierFileArr = serviceProcessAction.createDossierFiles.split(",");
						$("#createDossierFiles").data("kendoMultiSelect").value(createDossierFileArr);

					} else if (serviceProcessAction.createDossierFiles){
						$("#createDossierFiles").data("kendoMultiSelect").value(serviceProcessAction.createDossierFiles);
					}else {
						$("#createDossierFiles").data("kendoMultiSelect").value("");
					}
				});
				
				console.log("serviceProcessAction.returnDossierFiles==============",serviceProcessAction.returnDossierFiles);
				$("#returnDossierFiles").data("kendoMultiSelect").dataSource.read({
					id : serviceProcessAction.dossierTemplateNo
				});

				$("#returnDossierFiles").data("kendoMultiSelect").dataSource.fetch(function(){
					console.log(this.view());
					if (typeof serviceProcessAction.returnDossierFiles === 'number'){

						$("#returnDossierFiles").data("kendoMultiSelect").value(serviceProcessAction.returnDossierFiles);

					}  else if (typeof serviceProcessAction.returnDossierFiles === 'string'){

						var returnDossierFileArr = serviceProcessAction.returnDossierFiles.split(",");

						$("#returnDossierFiles").data("kendoMultiSelect").value(returnDossierFileArr);

					} else if (serviceProcessAction.returnDossierFiles){
						$("#returnDossierFiles").data("kendoMultiSelect").value(serviceProcessAction.returnDossierFiles);
					}else {
						$("#returnDossierFiles").data("kendoMultiSelect").value("");
					}
				});


				

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
					eSignature : "",
					configNote : "",
					dossiertemplatesFileFilter : function(){

						$("#dossiertemplateTmp").val("");

						$('#createDossierFiles').data('kendoMultiSelect').dataSource.read();
						$('#createDossierFiles').data('kendoMultiSelect').value("");

						$('#returnDossierFiles').data('kendoMultiSelect').dataSource.read();
						$('#returnDossierFiles').data('kendoMultiSelect').value("");

						return "";
					}
					/*configNote : '{  "cancelButton": "Quay lại", "requiredNote": false,    "confirmButton": "Đồng ý",    "titleNote": "Nhập ý kiến chỉ đạo",    "displayNote": false}'*/
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
							eSignature : $("#eSignature").prop("checked"),
							configNote : $("textarea#configNote").val(),
							dossierTemplateNo : $("#dossiertemplates_file_filter").val()
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
							eSignature : $("#eSignature").prop("checked"),
							configNote : $("textarea#configNote").val(),
							dossierTemplateNo : $("#dossiertemplates_file_filter").val()
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
								"eSignature" : $("#eSignature").prop("checked"),
								"configNote" : $("textarea#configNote").val()
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
				createDossierFiles = $("#createDossierFiles").data("kendoMultiSelect").value().join();
				return createDossierFiles;
			}

			function getReturnDossierFiles(){
				var returnDossierFiles = "";
				returnDossierFiles = $("#returnDossierFiles").data("kendoMultiSelect").value().join();
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
