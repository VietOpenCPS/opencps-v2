
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
					#: preStepCode #
				</div>
				<div class="col-sm-2 align-middle-lg text-center">
					#: postStepCode #
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
								notification.show({
									message: "Xẩy ra lỗi, vui lòng thử lại"
								}, "error");
							}
						});
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

				console.log(serviceProcessAction);

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
					createDossierFiles: serviceProcessAction.createDossierFiles,
					returnDossierFiles: serviceProcessAction.returnDossierFiles,
					syncActionCode: serviceProcessAction.syncActionCode,
					rollbackable: serviceProcessAction.rollbackable
				});

				kendo.bind($("#serviceprocesses_detail_form_action"), viewModel);

				initStepCombobox();

				$("#btn_save_service_process_action").attr("data-pk", $(this).attr("data-pk"));
			});

			$("#btn_cancle_service_process_action").click(function(e){
				e.preventDefault();
				$("#serviceprocess_action_container").show();
				$("#serviceprocess_detail_formaction_container").hide();
			});

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
					rollbackable: ""
				});

				initStepCombobox();

				kendo.bind($("#serviceprocesses_detail_form_action"), viewModel);

				$("#btn_save_service_process_action").attr("data-pk", "");
			});

			$(document).on("click", "#btn_save_service_process_action", function(event){
				event.preventDefault();

				var serviceProcessId = $("#btn_save_service_process").attr("data-pk");
				var actionId = $(this).attr("data-pk");

				if (actionId){
					updateServiceProcessAction(serviceProcessId, actionId);
				} else {
					addServiceProcessAction(serviceProcessId);
				}
			});

			var updateServiceProcessAction = function(serviceProcessId, actionId){

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
						allowAssignUser: $("#allowAssignUser").val(),
						assignUserId: $("#assignUserId").val(),
						requestPayment: $("#requestPayment").val(),
						paymentFee: $("#paymentFee").val(),
						createDossierFiles: getCreateDossierFiles(),
						returnDossierFiles: getReturnDossierFiles(),
						syncActionCode: $("#syncActionCode").val(),
						rollbackable: $("#rollbackable").val()
					},
					success: function(result) {
						notification.show({
							message: "Yêu cầu được thực hiện thành công"
						}, "success");

						var serviceProcessAction = serviceProcessActionDataSource.get(actionId);

						serviceProcessAction.set("actionCode", $("#actionCode").val());
						serviceProcessAction.set("actionName", $("#actionName").val());
						serviceProcessAction.set("preStepCode", $("#preStepCode").val());
						serviceProcessAction.set("postStepCode", $("#postStepCode").val());
						serviceProcessAction.set("autoEvent", $("#autoEvent").val());
						serviceProcessAction.set("preCondition", $("#preCondition").val());
						serviceProcessAction.set("allowAssignUser", $("#allowAssignUser").val());
						serviceProcessAction.set("assignUserId", $("#assignUserId").val());
						serviceProcessAction.set("requestPayment", $("#requestPayment").val());
						serviceProcessAction.set("paymentFee", $("#paymentFee").val());
						serviceProcessAction.set("createDossierFiles", getCreateDossierFiles());
						serviceProcessAction.set("returnDossierFiles", getReturnDossierFiles());
						serviceProcessAction.set("syncActionCode", $("#syncActionCode").val());
						serviceProcessAction.set("rollbackable", $("#rollbackable").val());

						$("#serviceprocess_action_container").show();
						$("#serviceprocess_detail_formaction_container").hide();
					},
					error: function(result) {
						notification.show({
							message: "Xẩy ra lỗi, vui lòng thử lại"
						}, "error");
					}
				});


			}

			var addServiceProcessAction = function(serviceProcessId){
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
						allowAssignUser: $("#allowAssignUser").val(),
						assignUserId: $("#assignUserId").val(),
						requestPayment: $("#requestPayment").val(),
						paymentFee: $("#paymentFee").val(),
						createDossierFiles: "",
						returnDossierFiles: "",
						syncActionCode: $("#syncActionCode").val(),
						rollbackable: $("#rollbackable").val()
					},
					success: function(result) {
						notification.show({
							message: "Yêu cầu được thực hiện thành công"
						}, "success");

						serviceProcessActionDataSource.insert(0, {
							"processActionId": result.processActionId,
							"actionCode": $("#actionCode").val(),
							"actionName": $("#actionName").val(),
							"preStepCode": $("#preStepCode").val(),
							"postStepCode": $("#postStepCode").val(),
							"autoEvent": $("#autoEvent").val(),
							"preCondition": $("#preCondition").val(),
							"allowAssignUser": $("#allowAssignUser").val(),
							"assignUserId": $("#assignUserId").val(),
							"requestPayment": $("#requestPayment").val(),
							"paymentFee": $("#paymentFee").val(),
							"createDossierFiles": getCreateDossierFiles(),
							"returnDossierFiles": getReturnDossierFiles(),
							"syncActionCode": $("#syncActionCode").val(),
							"rollbackable": $("#rollbackable").val(),
						});

						$("#serviceprocess_action_container").show();
						$("#serviceprocess_detail_formaction_container").hide();
					},
					error: function(result) {
						notification.show({
							message: "Xẩy ra lỗi, vui lòng thử lại"
						}, "error");
					}
				});
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

			function initStepCombobox(){
				$("#preStepCode").kendoComboBox({
					dataTextField: "stepName",
					dataValueField: "stepCode",
					filter: "contains",
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
