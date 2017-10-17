<#if (Request)??>
<#include "init.ftl">
</#if>

<div class="row">
	<div class="col-xs-12 col-sm-3 panel P0">
		<div class="panel-body">
			<div class="row">
				<div class="col-xs-12 col-sm-12">
					<button id="btn_add_service_process" class="k-button btn-primary form-control" title="Thêm quy trình"><i class="glyphicon glyphicon-plus"></i> Thêm quy trình</button>
				</div>
			</div>
			<div class="row MT10">
				<div class="col-xs-12 col-sm-12">
					<div class="input-group">
						<input type="text" class="form-control" id="input_search_service_process" placeholder="Mã quy trình, Tên quy trình" title="Nhập Mã quy trình hoặc Tên quy trình để tìm kiếm">
						<div class="input-group-addon btn-primary" id="btn_search_service_process" title="Tìm kiếm"><i class="fa fa-search" aria-hidden="true"></i></div>
					</div>
				</div>
			</div>
		</div>
		<div class="col-xs-12 col-sm-12">
			<ul id ="service_process_list_view" class="ul-with-border ul-with-border-style-2"></ul>
			<div id="service_process_pager" class="k-pager-wrap"></div>
		</div>

		<script type="text/x-kendo-template" id="service_process_template">
			<li class="clearfix" data-pk="#: id #" style="padding: 10px 0 10px 5px;" role="option" aria-selected="true">
				<div class="P0 col-xs-12 col-sm-12 dossier-template-item" data-pk="#: id #">
					<strong>#: processNo #</strong>
					<a class="btn-group k-delete-button pull-right" href="\\#" title="Xóa">
						<i aria-hidden="true" class="fa fa-trash"></i>
					</a>
				</div>
				<div class="P0 col-xs-12 col-sm-12">
					<div class="edit-buttons">
						<span class="btn-block">#: processName #</span>
					</div>
				</div>
			</li>
		</script>
	</div>

	<div class="col-xs-12 col-sm-9" id="serviceprocess_detail_container">
		<div class="panel panel-body">
			<#include "serviceprocess_detail.ftl">
		</div>
	</div>
</div>

<script type="text/javascript">
	(function($){
		var serviceProcessDataSource = new kendo.data.DataSource({
			transport: {
				read: function(options) {
					$.ajax({
						url: "${api.server}" + "/serviceprocesses",
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
						}
					});
				},
				destroy: function(options) {
					$.ajax({
						url: "${api.server}" + "/serviceprocesses/" + options.data.serviceProcessId,
						type: "DELETE",
						dataType: "json",
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
				model : { id: "serviceProcessId" },
			},
			pageSize: 10,
			serverPaging: false,
			serverSorting: false,
			serverFiltering: false
		});

		$("#service_process_list_view").kendoListView({
			dataSource: serviceProcessDataSource,
			template: kendo.template($("#service_process_template").html()),
			selectable: true,
			dataBound: function(e) {
				var listView = e.sender;
				var firstItem = listView.element.children().first();
				listView.select(firstItem);

				//  focus to the first servcie process
				onSelectServiceProcess(firstItem.attr("data-pk"));
			},
			remove: function(e) {
				if(!confirm("Xác nhận quy trình: " + e.model.get("processNo") + "?")){
					e.preventDefault();
				}
			},
			change: function() {
				var index = this.select().index();
				var dataItem = this.dataSource.view()[index];

				var viewModel = kendo.observable({
					processNo : dataItem.processNo,
					processName : dataItem.processName,
					description : dataItem.description,
					durationCount : dataItem.durationCount,
					durationUnit : dataItem.durationUnit,
					generateDossierNo : dataItem.generateDossierNo,
					generateDueDate : dataItem.generateDueDate,
					dossierNoPattern : dataItem.dossierNoPattern,
					dueDatePattern : dataItem.dueDatePattern,
					generatePassword : dataItem.generatePassword,
					directNotification : dataItem.directNotification,
					serverNo : dataItem.serverNo,
				});

				kendo.bind($("#fm_process_info"), viewModel);

				$("#btn_save_service_process").attr("data-pk", dataItem.id);

				// reset state for left tab
				$('.nav-tabs a[href="#tab_process_info"]').tab('show');
				$("ul.nav.nav-tabs li:not(:first)").removeClass("disabled-tab");

				$("#serviceprocess_step_container").show();
				$("#serviceprocess_detail_formstep_container").hide();

				$("#serviceprocess_action_container").show();
				$("#serviceprocess_detail_formaction_container").hide();

				$("#service_process_step_listview").data("kendoListView").dataSource.read({serviceProcessId: dataItem.id});
				$("#service_process_action_listview").data("kendoListView").dataSource.read({serviceProcessId: dataItem.id});

				$.ajax({
					url: "${api.server}" + "/serviceprocesses/" + dataItem.id + "/roles",
					type: "GET",
					dataType: "json",
					headers: {"groupId": ${groupId}},
					success: function(result) {
						var data = result.data;

						var controlForm = $('.service-process-form-controls');
						controlForm.find('.service-process-form-entry:not(:last)').remove();
						if (data && data.length >= 1){

							var role = data[0];
							var entry = $('.service-process-form-entry:first');
							entry.find('input[type=radio]').attr('name', "moderator_1");
							entry.find('select').val(role.roleId);
							entry.find('input[type=radio][value='+role.moderator+']').prop('checked',true);

							for (var i = 1; i < data.length; i++) {
								var role = data[i];

								var currentEntry = $('.service-process-form-entry:last');

								var radioValue = currentEntry.find('input[type=radio]:checked').val();

								var newEntry = $(currentEntry.clone()).appendTo(controlForm);

								var index = controlForm.children().length;
								var name = 'moderator_' + index;
								newEntry.find('input[type=radio]').attr('name', name);

								newEntry.find('select').val(role.roleId);
								newEntry.find('input[type=radio][value=' + role.moderator + ']').prop('checked',true);
								currentEntry.find('input[type=radio][value=' + radioValue + ']').prop('checked',true);

								controlForm.find('.service-process-form-entry:not(:last) .btn-add')
								.removeClass('btn-add').addClass('btn-remove')
								.removeClass('btn-success').addClass('btn-danger')
								.html('<span class="glyphicon glyphicon-minus"></span>');
							}
						} else {
							var viewModel = kendo.observable({
								roleId: "",
								moderator: "",
							});

							kendo.bind($(".service-process-form-entry")[0], viewModel);
						}
					}
				});
			}
		});

		$(document).on("click", ".service-process-item", function(event){
			onSelectServiceProcess($(this).attr("data-pk"));
		});

		var onSelectServiceProcess = function(id){
			$("#service_process_step_listview").getKendoListView().dataSource.read({
				serviceProcessId: id
			});
			$("#service_process_action_listview").getKendoListView().dataSource.read({
				serviceProcessId: id
			});
		}

		$("#service_process_pager").kendoPager({
			dataSource: serviceProcessDataSource,
			buttonCount: 2,
		});

		$("#input_search_service_process").keyup(function(e){
			serviceProcessFilter();
		});

		$("#btn_search_service_process").click(function(){
			serviceProcessFilter();
		});

		var serviceProcessFilter = function(){
			var inputSearch = $("#input_search_service_process").val();

			var filters = [];
			var filter = {};

			filters.push({field: "processNo", operator: "contains", value: inputSearch});
			filters.push({field: "description", operator: "contains", value: inputSearch});

			filter = {
				logic: "or",
				filters: filters
			};

			serviceProcessDataSource.filter(filter);
		};

		$(document).on("click", "#btn_add_service_process", function(event){
			event.preventDefault();

			$("#service_process_list_view li.k-state-selected").removeClass("k-state-selected");
			$('.nav-tabs a[href="#tab_process_info"]').tab('show');
			$(".service-process-detail ul.nav.nav-tabs li:not(:first)").addClass("disabled-tab");

			var viewModel = kendo.observable({
				processNo : "",
				processName : "",
				description : "",
				durationCount : 1,
				durationUnit: 0
			});

			kendo.bind($("#fm_process_info"), viewModel);

			var controlForm = $('.service-process-form-controls');
			controlForm.find('.service-process-form-entry:not(:last)').remove();
			var entry = $('.service-process-form-entry:first');
			entry.find('input[type=radio]').attr('name', "moderator_1");
			entry.find('select').val('');
			entry.find('input[type=radio]:checked').prop('checked', false);

			$("#btn_save_service_process").attr("data-pk", "");
		});

		$(document).on("click", "#btn_save_service_process", function(event){
			event.preventDefault();

			$(".service-process-detail ul.nav.nav-tabs li:not(:first)").removeClass("disabled-tab");

			var dataPk = $(this).attr("data-pk");
			if (dataPk){
				updateServiceProcess(dataPk);
			} else {
				addServiceProcess();
			}
		});

		$(document).on("click", "#btn_cancle_service_process", function(event){
			event.preventDefault();

			$("ul.nav.nav-tabs li").removeClass("disabled-tab");
			$("#service_process_list_view li:first").addClass("k-state-selected");
		});

		var updateServiceProcess = function(dataPk){
			$.ajax({
				url: "${api.server}" + "/serviceprocesses/" + dataPk,
				type: "PUT",
				dataType: "json",
				headers: {"groupId": ${groupId}},
				data: {
					processNo: $("#processNo").val(),
					processName: $("#processName").val(),
					description: $("#description").val(),
					durationCount: $("#durationCount").val(),
					durationUnit: $("input[name=durationUnit]:checked").val(),
					generateDossierNo: $("#generateDossierNo").val(),
					dossierNoPattern: $("#dossierNoPattern").val(),
					generateDueDate: $("#generateDueDate").val(),
					dueDatePattern: $("#dueDatePattern").val(),
					generatePassword: $("#generatePassword").val(),
					directNotification: $("#directNotification").val(),
					serverNo: $("#serverNo").val(),
				},
				success: function(result) {
					notification.show({
						message: "Yêu cầu được thực hiện thành công"
					}, "success");

					var serviceProcess = serviceProcessDataSource.get(dataPk);
					serviceProcess.set("processNo", $("#processNo").val());
					serviceProcess.set("processName", $("#processName").val());
					serviceProcess.set("description", $("#description").val());
					serviceProcess.set("durationCount", $("#durationCount").val());
					serviceProcess.set("durationUnit", $("input[name=durationUnit]:checked").val());
					serviceProcess.set("generateDossierNo", $("#generateDossierNo").val());
					serviceProcess.set("dossierNoPattern", $("#dossierNoPattern").val());
					serviceProcess.set("generateDueDate", $("#generateDueDate").val());
					serviceProcess.set("dueDatePattern", $("#dueDatePattern").val());
					serviceProcess.set("generatePassword", $("#generatePassword").val());
					serviceProcess.set("directNotification", $("#directNotification").val());
					serviceProcess.set("serverNo", $("#serverNo").val());
				},
				error: function(result) {
					notification.show({
						message: "Xẩy ra lỗi, vui lòng thử lại"
					}, "error");
				}
			});

			// edit roles
			$.ajax({
				url: "${api.server}" + "/serviceprocesses/" + dataPk + "/roles",
				type: "GET",
				dataType: "json",
				headers: {"groupId": ${groupId}},
				async: false,
				success: function(result) {
					console.log(result);
					if (result && result.data && result.data.length > 0){
						result.data.forEach(function(role){
							$.ajax({
								url: "${api.server}" + "/serviceprocesses/" + dataPk + "/roles/" + role.roleId,
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

			$(".service-process-form-entry").each(function(){
				if ($(this).find('select[name=roleId]').val()){
					$.ajax({
						url: "${api.server}" + "/serviceprocesses/" + dataPk + "/roles/" + $(this).find('select[name=roleId]').val(),
						type: "PUT",
						dataType: "json",
						headers: {"groupId": ${groupId}},
						data: {
							moderator: $(this).find('input[type=radio]:checked').val(),
						},
						success: function(result) {

						},
						error: function(result) {
							notification.show({
								message: "Xẩy ra lỗi, vui lòng thử lại"
							}, "error");
						}
					});
				}
			});
		}

		var addServiceProcess = function(){
			$.ajax({
				url: "${api.server}" + "/serviceprocesses",
				type: "POST",
				dataType: "json",
				headers: {"groupId": ${groupId}},
				data: {
					processNo: $("#processNo").val(),
					processName: $("#processName").val(),
					description: $("#description").val(),
					durationCount: $("#durationCount").val(),
					durationUnit: $("input[name=durationUnit]:checked").val(),
					generateDossierNo: $("#generateDossierNo").val(),
					dossierNoPattern: $("#dossierNoPattern").val(),
					generateDueDate: $("#generateDueDate").val(),
					dueDatePattern: $("#dueDatePattern").val(),
					generatePassword: $("#generatePassword").val(),
					directNotification: $("#directNotification").val(),
					serverNo: $("#serverNo").val(),
				},
				success: function(result) {
					notification.show({
						message: "Yêu cầu được thực hiện thành công"
					}, "success");

					$(".service-process-form-entry").each(function(){
						if ($(this).find('select[name=roleId]').val()){
							$.ajax({
								url: "${api.server}" + "/serviceprocesses/" + result.serviceProcessId + "/roles",
								type: "POST",
								dataType: "json",
								headers: {"groupId": ${groupId}},
								async: false,
								data: {
									roleId: $(this).find('select[name=roleId]').val(),
									moderator: $(this).find('input[type=radio]:checked').val(),
								},
								error: function(result) {
									notification.show({
										message: "Xẩy ra lỗi, vui lòng thử lại"
									}, "error");
								}
							});
						}
					});

					serviceProcessDataSource.insert(0, {
						"serviceProcessId": result.serviceProcessId,
						"processNo": result.processNo,
						"processName": result.processName,
						"description": result.description,
						"durationCount": result.durationCount,
						"durationUnit": result.durationUnit,
						"generateDossierNo": result.generateDossierNo,
						"dossierNoPattern": result.dossierNoPattern,
						"generateDueDate": result.generateDueDate,
						"dueDatePattern": result.dueDatePattern,
						"generatePassword": result.generatePassword,
						"directNotification": result.directNotification,
						"serverNo": result.serverNo,
					});

					$("#btn_save_service_process").attr("data-pk", result.serviceProcessId);
				},
				error: function(result) {
					notification.show({
						message: "Xẩy ra lỗi, vui lòng thử lại"
					}, "error");
				}
			});
		};

	})(jQuery);
</script>
