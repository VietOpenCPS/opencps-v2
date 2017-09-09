<#if (Request)??>
	<#include "init.ftl">
</#if>
<div class="row">
   <div class="col-xs-12 col-sm-3 panel P0">
   		<div class="panel-body">
   			<div class="row">
   			  <div class="col-xs-12 col-sm-12">
            <div class="input-group">
       			  	<input type="text" class="form-control" id="input_search_service_process" placeholder="Mã quy trình, Tên quy trình" title="Nhập Mã quy trình hoặc Tên quy trình để tìm kiếm">
       			  	<div class="input-group-addon btn-primary" id="btn_search_service_process" title="Tìm kiếm"><i class="fa fa-search" aria-hidden="true"></i></div>
       			</div>
   			  </div>
   			</div>
        <div class="row">
          <div class="col-xs-12 col-sm-12">
            <button id="btn_add_service_process" class="k-button btn-primary form-control MT10" title="Thêm quy trình xử lý"><i class="glyphicon glyphicon-plus"></i> Thêm quy trình xử lý </button>
          </div>
        </div>
   		</div>
   		<div>
   			<ul id ="service_process_list_view" class="ul-with-border ul-with-border-style-2"></ul>
   			<div id="service_process_pager" class="k-pager-wrap"></div>
   		</div>

      <script type="text/x-kendo-template" id="service_process_template">
         <li class="clearfix" data-pk="#: id #" style="padding: 10px 0 10px 5px;" role="option" aria-selected="true">
           <div class="col-sm-2 clearfix PL0 PR0">
              <a href="javascript:;">
              <i style="font-size: 25px;padding: 5px;" class="fa fa-book" aria-hidden="true"></i> </a>
           </div>
           <div class="col-sm-9 PL0 service-process-item" data-pk="#: id #">
              <strong class="btn-block">#: processNo #</strong>
              <span class="btn-block">#: description #</span>
           </div>
           <div class="col-sm-1 PL0">
              <div class="edit-buttons">
                <a class="btn-group btn-edit-service-process" data-pk="#: id #" href="\\#" title="Sửa">
                    <i aria-hidden="true" class="fa fa-pencil"></i>
                </a>
                <a class="btn-group k-delete-button" href="\\#" title="Xóa">
                    <i aria-hidden="true" class="fa fa-times"></i>
                </a>
              </div>
           </div>
         </li>
      </script>
   	</div>

    <div class="col-xs-12 col-sm-9" id="serviceprocess_detail_container">
      <#include "serviceprocess_detail.ftl">
    </div>

    <!-- service process form modal -->
    <div class="modal fade" id="service_process_form">
       <div class="modal-dialog modal-lg">
          <div class="modal-content"></div>
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
          create: function(options) {
             $.ajax({
                url: "${api.server}" + "/serviceprocesses",
                type: "POST",
                dataType: "json",
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

    $(document).on("click", ".btn-edit-service-process", function(event){
       event.preventDefault();
       formControl($(this).attr("data-pk"));
    });

    $(document).on("click", "#btn_add_service_process", function(event){
       event.preventDefault();
       formControl();
    });

    var formControl = function(dataPk){
       var url = "${ajax.serviceprocess_form}";

       if (dataPk){
         url = "${ajax.serviceprocess_form}" + "?serviceProcessId=" + dataPk;
       }

       $("#service_process_form .modal-content").load(
          url,
          function(result){

             $("#service_process_form").modal({show: true});

             $("#btn_cancle_service_process").click(function(e){
                e.preventDefault();
                $("#service_process_form").modal("hide");
             });

             var validator = $("#fm").kendoValidator().data("kendoValidator");

             $("form").submit(function(event) {
                event.preventDefault();
                if (validator.validate()) {

                   if (dataPk){
                      updateServiceProcess(dataPk);
                   } else {
                      addServiceProcess();
                   }

                   $("#service_process_form").modal("hide");
                }
             });

             // roles
             if (dataPk){
               var serviceProcessRoleDataSource = new kendo.data.DataSource({
                  transport: {
                     read: function(options) {
                        $.ajax({
                           url: "${api.server}" + "/serviceprocesses/" + "1" + "/roles", // 1 -> dataPk
                           type: "GET",
                           dataType: "json",
                           success: function(result) {
                             options.success(result);
                           }
                        });
                     },
                     create: function(options) {
                        $.ajax({
                           url: "${api.server}" + "/serviceprocesses/" + dataPk + "/roles",
                           type: "POST",
                           dataType: "json",
                           data: {
                             roleId: options.data.roleIdSend,
                             moderator: options.data.moderator,
                             condition: options.data.condition
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
                           url: "${api.server}" + "/serviceprocesses/" + dataPk + "/roles/" + options.data.roleId,
                           type: "PUT",
                           dataType: "json",
                           data: {
                             moderator: options.data.moderator,
                             condition: options.data.condition
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
                     destroy: function(options) {
                        $.ajax({
                           url: "${api.server}" + "/serviceprocesses/" + dataPk + "/roles/" + options.data.roleId,
                           type: "DELETE",
                           dataType: "json",
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
                     model : {
                       id: "roleId",
                       fields: {
                         roleId: { editable: false, nullable: true },
                         roleName: "roleName",
                         condition: "condition",
                         moderator: { type: "boolean" }
                       }
                     }
                  },
                  pageSize: 10,
                  serverPaging: false,
                  serverSorting: false,
                  serverFiltering: false
               });

               var roleListView = $("#service_process_role_list_view").kendoListView({
                 dataSource: serviceProcessRoleDataSource,
                 template: kendo.template($("#service_process_role_template").html()),
                 editTemplate: kendo.template($("#service_process_role_edit_template").html()),
                 selectable: true,
                 dataBound: function(e) {
                    var listView = e.sender;
                    var firstItem = listView.element.children().first();
                    listView.select(firstItem);
                 },
                 edit: function(e) {
                   var model = e.model;
                   var item = $(e.item[0]);
                   var roleName = item.find('[name="roleName"]')[0];

                   var isNew = false;
                   if (!e.model.id){
                     isNew = true;
                   }

                   loadListRole(roleName, isNew);
                 },
                 save: function(e) {
                   var model = e.model;
                   var item = $(e.item[0]);
                   var name = item.find('select option:selected');

                   //model.set("id", name.val());
                   model.set("roleName", name.text());
                   model.set("roleIdSend", name.val());
                 }
               }).data("kendoListView");

               $("#btn_add_role").click(function(e) {
                 e.preventDefault();
                 roleListView.add();
               });

               // load list role
               function loadListRole(target, isNew){
                 target.innerHTML = '';

                 if (isNew){
                   var optionBlank = target.appendChild(document.createElement('option'));
                   optionBlank.value = "";
                   optionBlank.text = "---Chọn---";
                 }

                 var dataPk = $($(target)[0]).attr('data-pk');

                 $.ajax({
                   url: "${api.server}" + "/jobpos",
                   type: "GET",
                   dataType: "json",
                   data: {

                   },
                   success: function(result) {
                     for(d in result.data){
                       var jobpos = result.data[d];
                       if (isNew){
                         var option = target.appendChild(document.createElement('option'));
                         option.value = jobpos.mappingRoleId;
                         option.text = jobpos.title;
                         if (option.value == dataPk){
                           option.selected = true;
                         }
                       } else {
                         if (jobpos.mappingRoleId == dataPk){
                           var option = target.appendChild(document.createElement('option'));
                           option.value = jobpos.mappingRoleId;
                           option.text = jobpos.title;
                           option.selected = true;
                         }
                       }
                     }
                   }
                 });
               }

               $("#service_process_role_pager").kendoPager({
                  dataSource: serviceProcessRoleDataSource,
                  buttonCount: 2,
                  pageSizes: [5, 10, 20, 50],
               });
             } else {
               var tabStrip = $("#tabstrip_service_process_modal").kendoTabStrip().data("kendoTabStrip");
               tabStrip.disable(tabStrip.tabGroup.children().eq(1));
             }
             // end roles
          }
       );
    }

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
