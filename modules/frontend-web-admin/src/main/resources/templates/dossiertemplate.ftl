<#if (Request)??>
<#include "init.ftl">
</#if>
<div class="row">
	<div class="col-xs-12 col-sm-3 panel P0">
		<div class="panel-body">
			<div class="row">
				<div class="col-xs-12 col-sm-12">
					<button id="btn_add_dossier_template" class="k-button btn-primary form-control" title="Thêm mẫu hồ sơ"><i class="glyphicon glyphicon-plus"></i> Thêm mẫu hồ sơ </button>
				</div>
			</div>
			<div class="row MT10">
				<div class="col-xs-12 col-sm-12">
					<div class="input-group">
						<input type="text" class="form-control" id="input_search_dossier_template" placeholder="Số mẫu hồ sơ, Tên mẫu hồ sơ" title="Nhập Sô hồ sơ hoặc Tên hồ sơ để tìm kiếm">
						<div class="input-group-addon btn-primary" id="btn_search_dossier_template" title="Tìm kiếm"><i class="fa fa-search" aria-hidden="true"></i></div>
					</div>
				</div>
			</div>
		</div>
		
		<div class="col-xs-12 col-sm-12">
			<ul id ="dossier_template_list_view" class="ul-with-border ul-with-border-style-2"></ul>
			<div id="dossier_template_pager" class="k-pager-wrap"></div>
		</div>

		<script type="text/x-kendo-template" id="dossier_template_template">
			<li class="clearfix dossier_template_item" data-pk="#: id #" style="padding: 10px 0 10px 5px;" role="option" aria-selected="true">
				<div class="P0 col-xs-12 col-sm-12 dossier-template-item" data-pk="#: id #">
					<strong>#: templateNo #</strong>
					<a class="btn-group k-delete-button pull-right" href="\\#" title="Xóa">
						<i aria-hidden="true" class="fa fa-trash"></i>
					</a>
				</div>
				<div class="P0 col-xs-12 col-sm-12">
					<div class="edit-buttons">
						<span class="btn-block">#: templateName #</span>
					</div>
				</div>
			</li>
		</script>
	</div>

	<div class="col-xs-12 col-sm-9">
		<div class="panel panel-body">
			<#include "dossiertemplate_detail.ftl">
		</div>
	</div>
</div>

<script type="text/javascript">
	(function($){

		var dossierTemplateDataSource = new kendo.data.DataSource({
			transport: {
				read: function(options) {
					$.ajax({
						url: "${api.server}" + "/dossiertemplates",
						type: "GET",
						dataType: "json",
						headers: {"groupId": ${groupId}},
						data: {
							keywords: options.data.keywords,
							page: options.data.page,
							pageSize: options.data.pageSize,
							order: true
						},
						success: function(result) {
							options.success(result);
						}
					});
				},
				destroy: function(options) {
					$.ajax({
						url: "${api.server}" + "/dossiertemplates/" + options.data.dossierTemplateId,
						type: "DELETE",
						dataType: "json",
						headers: {"groupId": ${groupId}},
						success: function(result) {
							options.success(result);
							notification.show({
								message: "Yêu cầu được thực hiện thành công"
							}, "success");

							$("#dossier_template_list_view li.k-state-selected").removeClass("k-state-selected");
							$('.nav-tabs a[href="#ttmhs"]').tab('show');
							$("ul.nav.nav-tabs li:not(:first)").addClass("disabled-tab");

							var viewModel = kendo.observable({
								templateNo : "",
								templateName : "",
								description : ""
							});

							kendo.bind($("#ttmhs"), viewModel);

							$("#btn_save_dossier_template").attr("data-pk", "");
						},
						error: function(result) {
							options.error(result);
							if (result.responseJSON.description == "DossierTemplateHasChildrenException"){
								notification.show({
									message: "Xóa không thành công do Mẫu hồ sơ có thành phần hồ sơ."
								}, "error");
							} else {
								notification.show({
									message: "Xẩy ra lỗi, vui lòng thử lại"
								}, "error");
							}
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
				model : { id: "dossierTemplateId" }
			},
			pageSize: 10,
			serverPaging: false,
			serverSorting: false,
			serverFiltering: false
		});

		var firstTimeLoad = true;
		$("#dossier_template_list_view").kendoListView({
			dataSource: dossierTemplateDataSource,
			template: kendo.template($("#dossier_template_template").html()),
			selectable: true,
			dataBound: function(e) {

				if (firstTimeLoad){
					var data = this.dataSource.data();
					var listView = e.sender;
					var firstItem = listView.element.children().first();
					listView.select(firstItem);
					//  the first select dossier template
					onSelectDossiertemplate(data[0].dossierTemplateId);
					$("#btn_save_dossier_template").attr("data-pk", data[0].dossierTemplateId);

					firstTimeLoad = false;
				}
			},
			remove: function(e) {
				if(!confirm("Xác nhận xóa mẫu hồ sơ: " + e.model.get("templateName") + "?")){
					e.preventDefault();
				}
			},
			change: function() {
				var index = this.select().index();
				var dataItem = this.dataSource.view()[index];

				var viewModel = kendo.observable({
					templateNo : dataItem.templateNo,
					templateName : dataItem.templateName,
					description : dataItem.description
				});

				kendo.bind($("#ttmhs"), viewModel);

				$("#btn_save_dossier_template").attr("data-pk", dataItem.id);

				// reset state for left tab
				$('.nav-tabs a[href="#ttmhs"]').tab('show');
				$("ul.nav.nav-tabs li:not(:first)").removeClass("disabled-tab");

				$("#dossiertemplate_part_container").show();
				$("#dossiertemplate_part_form_container").hide();

				try{
					$("#dossier_template_part_listview").getKendoListView().dataSource.page(1);
				}catch(e){
					
				}
				
			}
		});

		$(document).off("click",".dossier_template_item");
		$(document).on("click",".dossier_template_item",function(event){
			event.preventDefault();
			var id = $(this).attr("data-pk");
			console.log("id==========",id);
			onSelectDossiertemplate(id);
		});

		// $(document).on("click", ".dossier-template-item", function(event){
		//   onSelectDossiertemplate($(this).attr("data-pk"));
		// });

		var onSelectDossiertemplate = function(id){
			if(id){
				console.log("load parts 2");
				dossierTemplatePartDataSource.read({
					dossierTemplateId: id
				});
			}
		}

		$("#dossier_template_pager").kendoPager({
			dataSource: dossierTemplateDataSource,
			buttonCount: 2,
			messages: {
				display: "Hiển thị {0}-{1} trong {2} kết quả",
				empty: "Không có kết quả phù hợp!"
			}
		});

		$("#input_search_dossier_template").keyup(function(e){
			dossierTemplateFilter();
		});

		$("#btn_search_dossier_template").click(function(){
			dossierTemplateFilter();
		});

		var dossierTemplateFilter = function(){
			var inputSearch = $("#input_search_dossier_template").val();

			var filters = [];
			var filter = {};

			filters.push({field: "templateNo", operator: "contains", value: inputSearch});
			filters.push({field: "templateName", operator: "contains", value: inputSearch});

			filter = {
				logic: "or",
				filters: filters
			};

			dossierTemplateDataSource.filter(filter);
		};

		$(document).on("click", ".btn-edit-dossier-template", function(event){
			event.preventDefault();
			formControl($(this).attr("data-pk"));
		});

		$(document).on("click", "#btn_add_dossier_template", function(event){
			event.preventDefault();

			$("#dossier_template_list_view li.k-state-selected").removeClass("k-state-selected");
			$('.nav-tabs a[href="#ttmhs"]').tab('show');
			$("ul.nav.nav-tabs li:not(:first)").addClass("disabled-tab");

			var viewModel = kendo.observable({
				templateNo : "",
				templateName : "",
				description : ""
			});

			kendo.bind($("#ttmhs"), viewModel);

			$("#btn_save_dossier_template").attr("data-pk", "");
		});

		$(document).on("click", "#btn_save_dossier_template", function(event){
			event.preventDefault();

			$("ul.nav.nav-tabs li:not(:first)").removeClass("disabled-tab");

			var dataPk = $(this).attr("data-pk");
			if (dataPk){
				updateDossiertemplate(dataPk);
			} else {
				addDossierTemplete();
			}
		});

		$(document).on("click", "#btn_cancle_dossier_template", function(event){
			event.preventDefault();

			$("ul.nav.nav-tabs li:not(:first)").removeClass("disabled-tab");
			$("#dossier_template_list_view li:first").addClass("k-state-selected");

			var dataItem = dossierTemplateDataSource.view()[0];

			var viewModel = kendo.observable({
				templateNo : dataItem.templateNo,
				templateName : dataItem.templateName,
				description : dataItem.description
			});

			kendo.bind($("#ttmhs"), viewModel);

			$("#btn_save_dossier_template").attr("data-pk", dataItem.id);


			onSelectDossiertemplate(dataItem.id);
		});

		var updateDossiertemplate = function(dataPk){
			$.ajax({
				url: "${api.server}" + "/dossiertemplates/" + dataPk,
				type: "PUT",
				dataType: "json",
				headers: {"groupId": ${groupId}},
				data: {
					dossierTemplateId: dataPk,
					templateNo: $("#templateNo").val(),
					templateName: $("#templateName_").val(),
					description: $("#description").val()
				},
				success: function(result) {
					notification.show({
						message: "Yêu cầu được thực hiện thành công"
					}, "success");

					var dossierTemplate = dossierTemplateDataSource.get(dataPk);

					dossierTemplate.set("templateNo", $("#templateNo").val());
					dossierTemplate.set("templateName", $("#templateName_").val());
					dossierTemplate.set("description", $("#description").val());

					dossierTemplateDataSource.pushUpdate(result);

					$("#dossier_template_list_view li[data-pk=" + dataPk + "]").addClass("k-state-selected");
				},
				error: function(result) {
					notification.show({
						message: "Xẩy ra lỗi, vui lòng thử lại"
					}, "error");
				}
			});
		}

		var addDossierTemplete = function(){
			$.ajax({
				url: "${api.server}" + "/dossiertemplates",
				type: "POST",
				dataType: "json",
				headers: {"groupId": ${groupId}},
				data: {
					templateNo: $("#templateNo").val(),
					templateName: $("#templateName_").val(),
					description: $("#description").val()
				},
				success: function(result) {
					notification.show({
						message: "Yêu cầu được thực hiện thành công"
					}, "success");

					dossierTemplateDataSource.insert(0, result);

					$("#dossier_template_list_view div[data-pk=" + result.dossierTemplateId + "]").closest("li").addClass("k-state-selected");
					$("#btn_save_dossier_template").attr("data-pk", result.dossierTemplateId);

					console.log("add success----------------");
					console.log(result.dossierTemplateId);


					console.log(dossierTemplateDataSource.view());
					onSelectDossiertemplate(result.dossierTemplateId);
				},
				error: function(result) {
					if (result.responseJSON.description == "DuplicateTemplateNameException"){
						notification.show({
							message: "Lỗi trùng lặp Mã mẫu hồ sơ."
						}, "error");
					} else {
						notification.show({
							message: "Xẩy ra lỗi, vui lòng thử lại"
						}, "error");
					}
				}
			});
		};

	})(jQuery);
</script>
