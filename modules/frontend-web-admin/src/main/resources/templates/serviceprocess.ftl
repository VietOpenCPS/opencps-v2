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
					<div class="PL0 dossier-template-item" data-pk="#: id #">
						 <strong>#: processNo #</strong>
						 <a class="btn-group k-delete-button pull-right" href="\\#" title="Xóa">
								 <i aria-hidden="true" class="fa fa-trash"></i>
						 </a>
					</div>
					<div class="PL0">
						 <div class="edit-buttons">
							 <span class="btn-block">#: processName #</span>
						 </div>
					</div>
				</li>
      </script>
   	</div>

    <div class="col-xs-12 col-sm-9" id="serviceprocess_detail_container">
      <#include "serviceprocess_detail.ftl">
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
          create: function(options) {
             $.ajax({
                url: "${api.server}" + "/serviceprocesses",
                type: "POST",
                dataType: "json",
								headers: {"groupId": ${groupId}},
                data: {
                  processNo: options.data.processNo,
                  processName: options.data.processName,
                  description: options.data.description,
                  durationCount: options.data.durationCount,
                  durationUnit: options.data.durationUnit,
                  counter: options.data.counter,
                  dossierNoPattern: options.data.dossierNoPattern,
                  dueDatePattern: options.data.dueDatePattern,
                  generateDossierNo: options.data.generateDossierNo,
                  generateDueDate: options.data.generateDueDate
                },
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
          update: function(options) {
             $.ajax({
                url: "${api.server}" + "/serviceprocesses/" + options.data.serviceProcessId,
                type: "PUT",
                dataType: "json",
								headers: {"groupId": ${groupId}},
                data: {
                  processNo: options.data.processNo,
                  processNo: options.data.processName,
                  description: options.data.description,
                  durationCount: options.data.durationCount,
                  durationUnit: options.data.durationUnit,
                  counter: options.data.counter,
                  dossierNoPattern: options.data.dossierNoPattern,
                  dueDatePattern: options.data.dueDatePattern,
                  generateDossierNo: options.data.generateDossierNo,
                  generateDueDate: options.data.generateDueDate
                },
                success: function(result) {
                  options.success(result);
                  notification.show({
                    message: "Yêu cầu được thực hiện thành công"
                  }, "success");
                },
                error: function(result) {
                  options.error(result);
                  fileTemplateDataSource.cancelChanges();
                  notification.show({
                    message: "Xẩy ra lỗi, vui lòng thử lại"
                  }, "error");
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
							durationCount : dataItem.durationCount
					});

					kendo.bind($("#fm_process_info"), viewModel);

					$("#btn_save_service_process").attr("data-pk", dataItem.id);
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
			 $("ul.nav.nav-tabs li:not(:first)").addClass("disabled-tab");

			 var viewModel = kendo.observable({
					 processNo : "",
					 processName : "",
					 description : "",
					 durationCount : ""
			 });

			 kendo.bind($("#fm_process_info"), viewModel);

			 $("#btn_save_service_process").attr("data-pk", "");
    });

    var updateServiceProcess = function(dataPk){
       var serviceProcess = serviceProcessDataSource.get(dataPk);

       serviceProcess.set("processNo", $("#processNo").val());
       serviceProcess.set("processName", $("#processName").val());
       serviceProcess.set("description", $("#description").val());
       serviceProcess.set("durationCount", $("#durationCount").val());
       serviceProcess.set("durationUnit", $("#durationUnit").val());
       serviceProcess.set("counter", $("#counter").val());
       serviceProcess.set("dossierNoPattern", $("#dossierNoPattern").val());
       serviceProcess.set("dueDatePattern", $("#dueDatePattern").val());
       serviceProcess.set("generateDossierNo", $("#generateDossierNo").val());
       serviceProcess.set("generateDueDate", $("#generateDueDate").val());

       serviceProcessDataSource.sync();
    }

    var addServiceProcess = function(){
       serviceProcessDataSource.add({
          "processNo": $("#processNo").val(),
          "processName": $("#processName").val(),
          "description": $("#description").val(),
          "durationCount": $("#durationCount").val(),
          "durationUnit": $("#durationUnit").val(),
          "counter": $("#counter").val(),
          "dossierNoPattern": $("#dossierNoPattern").val(),
          "dueDatePattern": $("#dueDatePattern").val(),
          "generateDossierNo": $("#generateDossierNo").val(),
          "generateDueDate": $("#generateDueDate").val(),
       });
       serviceProcessDataSource.sync();
    };

  })(jQuery);
</script>
