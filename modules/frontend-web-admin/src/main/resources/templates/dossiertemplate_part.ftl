<#if (Request)??>
	<#include "init.ftl">
</#if>

<div class="row">
	 <div class="col-xs-12 col-sm-12">
			<button id="btn_add_dossier_template_part" class="k-button btn-primary" title="Thêm thành phần"><i class="glyphicon glyphicon-plus"></i> Thêm thành phần </button>
	 </div>
</div>
<div class="row">
	 <div class="panel-body">
			<ul class="ul-with-border">
				 <div id="dossier_template_part_listview"></div>
			</ul>
			<div id="pager_dossier_template_part" class="k-pager-wrap full-width-pager"></div>
	 </div>

	 <script type="text/x-kendo-template" id="dossier_template_part_template">
	 <li>
			<div class="row">
			 <div class="col-xs-12 col-sm-11">#: itemIndex #. #: id #</div>
			 <div class="col-xs-12 col-sm-1 text-center">
					<a class="btn-group btn-edit-dossier-template-part" data-pk="#: id #" href="\\#" title="Sửa">
							<i aria-hidden="true" class="fa fa-pencil"></i>
					</a>
					<a class="btn-group k-delete-button" href="\\#" title="Xóa">
							<i aria-hidden="true" class="fa fa-trash"></i>
					</a>
				</div>
			</div>
			<div class="row">
			 <div class="col-xs-12 col-sm-12">
				 <p>#: partName # #if (required){#<span style="color: red;">&nbsp;*</span>#}#</p>
				 <p>Kiểu thành phần:
					 #if (partType == "1"){
						 #Một giấy nộp vào#
					 } else if (partType == "2"){
						 #Nhiều giấy tờ nộp vào#
					 } else if (partType == "3"){
						 #Giấy tờ tùy chọn#
					 } else if (partType == "4"){
						 #Hồ sơ riêng#
					 } else if (partType == "5"){
						 #Một giấy kết quả#
					 } else if (partType == "6"){
						 #Nhiều giấy kết quả#
					 }#
				 </p>
				 <p>Mô tả: </p>
			 </div>
			</div>
	 </li>
	 </script>
</div>

<script type="text/javascript">
	(function($){
		var dossierTemplatePartDataSource = new kendo.data.DataSource({
			 transport: {
					read: function(options) {
						 $.ajax({
								url: "${api.server}" + "/dossiertemplates/" + options.data.dossierTemplateId + "/parts",
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
								url: "${api.server}" + "/dossiertemplates/" + $("#btn_save_dossier_template").attr("data-pk") + "/parts/" + options.data.partNo,
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
					model : { id: "partNo" },
			 },
			 pageSize: 10,
			 serverPaging: false,
			 serverSorting: false,
			 serverFiltering: false
		});

		var localIndex = 0;
		$("#dossier_template_part_listview").kendoListView({
			 dataSource: dossierTemplatePartDataSource,
			 selectable: true,
			 autoBind: false,
			 template: function(data){
					var _pageSize = dossierTemplatePartDataSource.pageSize();
					localIndex++;
					var currentPage = $("#pager_dossier_template_part").data("kendoPager").page();
					var totalPage =  $("#pager_dossier_template_part").data("kendoPager").totalPages();
					var index = (currentPage-1)*_pageSize + localIndex;
					data.itemIndex = index;
					return kendo.template($("#dossier_template_part_template").html())(data);
				},
			 remove: function(e) {
					if(!confirm("Xác nhận xóa thành phần hồ sơ: " + e.model.get("partName") + "?")){
						 e.preventDefault();
					}
			 },
			 dataBound: function() {
					localIndex = 0;
		    },
				dataBinding: function() {

		    }
		});

		$("#pager_dossier_template_part").kendoPager({
			 dataSource: dossierTemplatePartDataSource,
			 buttonCount: 3,
			 pageSizes: [5, 10, 20, 50],
		});

		$(document).on("click", ".btn-edit-dossier-template-part", function(event){
			 event.preventDefault();
			 $("#dossiertemplate_part_container").hide();
			 $("#dossiertemplate_part_form_container").show();

			 $("#btn_save_dossier_template_part").attr("data-pk", $(this).attr("data-pk"));

			 var dataItem = dossierTemplatePartDataSource.get($(this).attr("data-pk"));

			var dossierTemplateDatasource = $("#dossier_template_list_view").data("kendoListView");
				var indexDossierTemplateSelected = dossierTemplateDatasource.select().index(),
				    dossierTemplateSelected = dossierTemplateDatasource.dataSource.view()[indexDossierTemplateSelected];
			var formscript, formReport, sampleData;
			$.ajax({
					url: "${api.server}" + "/dossiertemplates/" + dossierTemplateSelected.id + "/parts/" + dataItem.partNo + "/formscript",
					type: "GET",
					dataType: "json",
					headers: {"groupId": ${groupId}},
					async: false,
					success: function(result) {
						console.log("formscript: ");
						console.log(result);
						formscript = result;
					}
			 });
			 $.ajax({
 					url: "${api.server}" + "/dossiertemplates/" + dossierTemplateSelected.id + "/parts/" + dataItem.partNo + "/formReport",
 					type: "GET",
 					dataType: "json",
 					headers: {"groupId": ${groupId}},
 					async: false,
 					success: function(result) {
 						console.log("formReport: ");
 						console.log(result);
 						formReport = result;
 					}
 			 });
			 $.ajax({
 					url: "${api.server}" + "/dossiertemplates/" + dossierTemplateSelected.id + "/parts/" + dataItem.partNo + "/sampleData",
 					type: "GET",
 					dataType: "json",
 					headers: {"groupId": ${groupId}},
 					async: false,
 					success: function(result) {
 						console.log("sampleData: ");
 						console.log(result);
 						sampleData = result;
 					}
 			 });

			 var viewModel = kendo.observable({
					 partNo: dataItem.partNo,
					 partName: dataItem.partName,
					 partTip: dataItem.partTip,
					 partType: dataItem.partType,
					 fileTemplateNo: dataItem.fileTemplateNo,
					 required: dataItem.required,
					 esign: dataItem.esign,
					 formscript: formscript,
					 formReport: formReport,
					 sampleData: sampleData,
			 });

			 kendo.bind($("#dossier_template_part_model"), viewModel);
		});

		$(document).on("click", "#btn_add_dossier_template_part", function(event){
			 event.preventDefault();
			 $("#dossiertemplate_part_container").hide();
			 $("#dossiertemplate_part_form_container").show();

			 var viewModel = kendo.observable({
					 partNo : "",
					 partName : "",
					 partTip : "",
					 partType : "",
					 fileTemplateNo : "",
					 required : "",
					 esign : "",
			 });

			 kendo.bind($("#dossier_template_part_model"), viewModel);

			 $("#btn_save_dossier_template_part").attr("data-pk", "");
		});

	})(jQuery);
</script>
