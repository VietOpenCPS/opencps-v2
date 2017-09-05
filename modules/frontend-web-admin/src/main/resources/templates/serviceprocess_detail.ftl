<div id="service_process_detail_tabstrip" class="panel">
  <div id="service_process_tabstrip">
    <ul class="ul-with-border ul-with-border-style-2">
      <li class="clearfix k-state-active" >
        <div class="col-sm-2 clearfix ">
          <a href="javascript:;" >
          <i class="fa fa-book" aria-hidden="true"></i>
          </a>
        </div>
        <div class="col-sm-9">Bước xử lý</div>
      </li>
      <li class="clearfix" >
        <div class="col-sm-2 clearfix ">
          <a href="javascript:;" >
          <i class="fa fa-tags" aria-hidden="true"></i>
          </a>
        </div>
        <div class="col-sm-9">Hành động</div>
      </li>
    </ul>
    <div>
      <div class="row">
         <div class="col-xs-12 col-sm-12">
            <button id="btn_add_service_process_step" class="k-button btn-primary" title="Thêm bước xử lý"><i class="glyphicon glyphicon-plus"></i> Thêm bước xử lý </button>
         </div>
      </div>
      <div class="row">
         <div class="panel-body">
            <ul class="ul-with-border">
               <div id="service_process_step_listview"></div>
            </ul>
            <div id="service_process_step_bager" class="k-pager-wrap full-width-pager"></div>
         </div>

         <script type="text/x-kendo-template" id="service_process_step_template">
            <li>
               <div class="row">
                  <div class="col-xs-12 col-sm-1 text-center">
                    #: sequenceNo #
                  </div>
                  <div class="col-xs-12 col-sm-10">
                     <div class="row MB5">
                        <div class="col-xs-12 col-sm-3"><b>Tên bước</b></div>
                        <div class="col-xs-12 col-sm-9">#: stepName #</div>
                     </div>
                     <div class="row MB5">
                        <div class="col-xs-12 col-sm-3"><b>Trạng thái bước</b></div>
                        <div class="col-xs-12 col-sm-9">#: dossierStatus #</div>
                     </div>
                     <div class="row MB5">
                        <div class="col-xs-12 col-sm-3"><b>Ngày xử lý</b></div>
                        <div class="col-xs-12 col-sm-9">#: durationCount #</div>
                     </div>
                  </div>
                  <div class="col-xs-12 col-sm-1 text-center">
                     <a class="btn-group btn-edit-service-process-step" data-pk="#: id #" href="\\#" title="Sửa">
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

      <!-- service process step form modal -->
      <div class="modal fade" id="service_process_step_form">
         <div class="modal-dialog modal-lg">
            <div class="modal-content"></div>
         </div>
      </div>


      <script type="text/javascript">
        (function($){
          var serviceProcessStepDataSource = new kendo.data.DataSource({
             transport: {
                read: function(options) {
                   $.ajax({
                      url: "${api.server}" + "/serviceprocesses/" + options.data.serviceProcessId + "/processsteps",
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
                      url: "${api.server}" + "/serviceprocesses/" + options.data.serviceProcessId + "/processsteps",
                      type: "POST",
                      dataType: "json",
                      data: {
                        stepCode: options.data.stepCode,
                        stepName: options.data.stepName,
                        sequenceNo: options.data.sequenceNo,
                        durationUnit: options.data.durationUnit,
                        dossierSubStatus: options.data.dossierSubStatus,
                        durationCount: options.data.durationCount,
                        customProcessUrl: options.data.customProcessUrl,
                        stepallowance: options.data.stepallowance
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
                      url: "${api.server}" + "/serviceprocesses/" + options.data.processStepId,
                      type: "PUT",
                      dataType: "json",
                      data: {
                        processStepId: options.data.processStepId,
                        stepCode: options.data.stepCode,
                        stepName: options.data.stepName,
                        sequenceNo: options.data.sequenceNo,
                        durationUnit: options.data.durationUnit,
                        dossierSubStatus: options.data.dossierSubStatus,
                        durationCount: options.data.durationCount,
                        customProcessUrl: options.data.customProcessUrl,
                        stepallowance: options.data.stepallowance
                      },
                      success: function(result) {
                        notification.show({
                          message: "Yêu cầu được thực hiện thành công"
                        }, "success");
                      },
                      error: function(result) {
                        fileTemplateDataSource.cancelChanges();
                        notification.show({
                          message: "Xẩy ra lỗi, vui lòng thử lại"
                        }, "error");
                      }
                   });
                },
                destroy: function(options) {
                   $.ajax({
                      url: "${api.server}" + "/serviceprocesses/" + options.data.processStepId,
                      dataType: "json",
                      type: "DELETE",
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
                model : { id: "processStepId" },
             },
             pageSize: 10,
             serverPaging: false,
             serverSorting: false,
             serverFiltering: false
          });

          $("#service_process_step_listview").kendoListView({
             dataSource: serviceProcessStepDataSource,
             template: kendo.template($("#service_process_step_template").html()),
             selectable: true,
             remove: function(e) {
                if(!confirm("Xác nhận xóa bước: " + e.model.get("stepName") + "?")){
                   e.preventDefault();
                }
             },
          });

          $("#service_process_step_bager").kendoPager({
             dataSource: serviceProcessStepDataSource,
             buttonCount: 3,
             pageSizes: [5, 10, 20, 50],
          });

          $(document).on("click", ".btn-edit-service-process-step", function(event){
             event.preventDefault();
             formControl($(this).attr("data-pk"));
          });

          $(document).on("click", "#btn_add_service_process_step", function(event){
             event.preventDefault();
             formControl();
          });

          var formControl = function(dataPk){
             var url = "${ajax.serviceprocess_detail_formstep}";

             $("#service_process_step_form .modal-content").load(
                url,
                function(result){

                   $("#service_process_step_form").modal({show: true});

                   $("#btn_cancle_service_process_step").click(function(e){
                      e.preventDefault();
                      $("#service_process_step_form").modal("hide");
                   });

                   var validator = $("#fm").kendoValidator().data("kendoValidator");

                   $("form").submit(function(event) {
                      event.preventDefault();
                      if (validator.validate()) {

                         if (dataPk){
                            updateServiceProcessStep(dataPk);
                         } else {
                            addServiceProcessStep();
                         }

                         $("#service_process_step_form").modal("hide");
                      }
                   });
                }
             );
          }

          var updateServiceProcessStep = function(dataPk){
             var serviceProcessStep = serviceProcessStepDataSource.get(dataPk);

             serviceProcessStep.set("stepCode", $("#stepCode").val());
             serviceProcessStep.set("stepName", $("#stepName").val());
             serviceProcessStep.set("sequenceNo", $("#sequenceNo").val());
             serviceProcessStep.set("durationUnit", $("#durationUnit").val());
             serviceProcessStep.set("dossierSubStatus", $("#dossierSubStatus").val());
             serviceProcessStep.set("durationCount", $("#durationCount").val());
             serviceProcessStep.set("customProcessUrl", $("#customProcessUrl").val());
             serviceProcessStep.set("stepallowance", $("#stepallowance").val());

             serviceProcessStepDataSource.sync();
          }

          var addServiceProcessStep = function(){
             serviceProcessStepDataSource.add({
                "stepCode": $("#stepCode").val(),
                "stepName": $("#stepName").val(),
                "sequenceNo": $("#sequenceNo").val(),
                "durationUnit": $("#durationUnit").val(),
                "dossierSubStatus": $("#dossierSubStatus").val(),
                "durationCount": $("#durationCount").val(),
                "customProcessUrl": $("#customProcessUrl").val(),
                "stepallowance": $("#stepallowance").val(),
             });
             serviceProcessStepDataSource.sync();
          };

        })(jQuery);
      </script>
    </div>
    <div>
      <div class="row">
         <div class="col-xs-12 col-sm-12">
            <button id="btn_add_service_process_action" class="k-button btn-primary" title="Thêm hành động"><i class="glyphicon glyphicon-plus"></i> Thêm hành động </button>
         </div>
      </div>
      <div class="row">
         <div class="panel-body">
            <ul class="ul-with-border">
               <div id="service_process_action_listview"></div>
            </ul>
            <div id="service_process_action_bager" class="k-pager-wrap full-width-pager"></div>
         </div>

         <script type="text/x-kendo-template" id="service_process_action_template">
            <li>
               <div class="row">
                  <div class="col-xs-12 col-sm-11">
                     <div class="row MB5">
                        <div class="col-xs-12 col-sm-2"><b>Tên thao tác</b></div>
                        <div class="col-xs-12 col-sm-10">#: actionName #</div>
                     </div>
                     <div class="row MB5">
                        <div class="col-xs-12 col-sm-2"><b>Bước bắt đầu</b></div>
                        <div class="col-xs-12 col-sm-10">#: preProcessStepId #</div>
                     </div>
                     <div class="row MB5">
                        <div class="col-xs-12 col-sm-2"><b>Bước kết thúc</b></div>
                        <div class="col-xs-12 col-sm-10">#: postProcessStepId #</div>
                     </div>
                  </div>
                  <div class="col-xs-12 col-sm-1 text-center">
                     <a class="btn-group btn-edit-service-process-action" data-pk="#: id #" href="\\#" title="Sửa">
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
                      url: "${api.server}" + "/serviceprocesses/" + options.data.serviceProcessId + "/processactions",
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
                      url: "${api.server}" + "/serviceprocesses/" + options.data.serviceProcessId + "/processactions",
                      type: "POST",
                      dataType: "json",
                      data: {
                        actionCode: options.data.actionCode,
                        actionName: options.data.actionName,
                        preProcessStepId: options.data.preProcessStepId,
                        postProcessStepId: options.data.postProcessStepId,
                        preCondition: options.data.preCondition,
                        autoEvent: options.data.autoEvent,
                        allowAssignUser: options.data.allowAssignUser,
                        assignUserId: options.data.assignUserId,
                        requestPayment: options.data.requestPayment,
                        paymentFee: options.data.paymentFee,
                        createDossierFiles: options.data.createDossierFiles,
                        returnDossierFiles: options.data.returnDossierFiles,
                        syncActionCode: options.data.syncActionCode,
                        rollback: options.data.rollback
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
                      url: "${api.server}" + "/serviceprocesses/" + options.data.processActionId,
                      type: "PUT",
                      dataType: "json",
                      data: {
                        processActionId: options.data.processActionId,
                        actionCode: options.data.actionCode,
                        actionName: options.data.actionName,
                        preProcessStepId: options.data.preProcessStepId,
                        postProcessStepId: options.data.postProcessStepId,
                        preCondition: options.data.preCondition,
                        autoEvent: options.data.autoEvent,
                        allowAssignUser: options.data.allowAssignUser,
                        assignUserId: options.data.assignUserId,
                        requestPayment: options.data.requestPayment,
                        paymentFee: options.data.paymentFee,
                        createDossierFiles: options.data.createDossierFiles,
                        returnDossierFiles: options.data.returnDossierFiles,
                        syncActionCode: options.data.syncActionCode,
                        rollback: options.data.rollback
                      },
                      success: function(result) {
                        notification.show({
                          message: "Yêu cầu được thực hiện thành công"
                        }, "success");
                      },
                      error: function(result) {
                        fileTemplateDataSource.cancelChanges();
                        notification.show({
                          message: "Xẩy ra lỗi, vui lòng thử lại"
                        }, "error");
                      }
                   });
                },
                destroy: function(options) {
                   $.ajax({
                      url: "${api.server}" + "/serviceprocesses/" + options.data.processActionId,
                      dataType: "json",
                      type: "DELETE",
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

          $("#service_process_action_listview").kendoListView({
             dataSource: serviceProcessActionDataSource,
             template: kendo.template($("#service_process_action_template").html()),
             selectable: true,
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
          });

          $(document).on("click", ".btn-edit-service-process-action", function(event){
             event.preventDefault();
             formControl($(this).attr("data-pk"));
          });

          $(document).on("click", "#btn_add_service_process_action", function(event){
             event.preventDefault();
             formControl();
          });

          var formControl = function(dataPk){
             var url = "${ajax.serviceprocess_detail_formaction}";

             $("#service_process_action_form .modal-content").load(
                url,
                function(result){

                   $("#service_process_action_form").modal({show: true});

                   $("#btn_cancle_service_process_action").click(function(e){
                      e.preventDefault();
                      $("#service_process_action_form").modal("hide");
                   });

                   var validator = $("#fm").kendoValidator().data("kendoValidator");

                   $("form").submit(function(event) {
                      event.preventDefault();
                      if (validator.validate()) {

                         if (dataPk){
                            updateServiceProcessAction(dataPk);
                         } else {
                            addServiceProcessAction();
                         }

                         $("#service_process_action_form").modal("hide");
                      }
                   });
                }
             );
          }

          var updateServiceProcessAction = function(dataPk){
             var serviceProcessAction = serviceProcessActionDataSource.get(dataPk);

             serviceProcessAction.set("actionCode", $("#actionCode").val());
             serviceProcessAction.set("actionName", $("#actionName").val());
             serviceProcessAction.set("preProcessStepId", $("#preProcessStepId").val());
             serviceProcessAction.set("postProcessStepId", $("#postProcessStepId").val());
             serviceProcessAction.set("preCondition", $("#preCondition").val());
             serviceProcessAction.set("autoEvent", $("#autoEvent").val());
             serviceProcessAction.set("allowAssignUser", $("#allowAssignUser").val());
             serviceProcessAction.set("assignUserId", $("#assignUserId").val());
             serviceProcessAction.set("requestPayment", $("#requestPayment").val());
             serviceProcessAction.set("paymentFee", $("#paymentFee").val());
             serviceProcessAction.set("createDossierFiles", $("#createDossierFiles").val());
             serviceProcessAction.set("returnDossierFiles", $("#returnDossierFiles").val());
             serviceProcessAction.set("syncActionCode", $("#syncActionCode").val());
             serviceProcessAction.set("rollback", $("#rollback").val());

             serviceProcessActionDataSource.sync();
          }

          var addServiceProcessStep = function(){
             serviceProcessActionDataSource.add({
                "actionCode": $("#actionCode").val(),
                "actionName": $("#actionName").val(),
                "preProcessStepId": $("#preProcessStepId").val(),
                "postProcessStepId": $("#postProcessStepId").val(),
                "preCondition": $("#preCondition").val(),
                "autoEvent": $("#autoEvent").val(),
                "allowAssignUser": $("#allowAssignUser").val(),
                "assignUserId": $("#assignUserId").val(),
                "requestPayment": $("#requestPayment").val(),
                "paymentFee": $("#paymentFee").val(),
                "createDossierFiles": $("#createDossierFiles").val(),
                "returnDossierFiles": $("#returnDossierFiles").val(),
                "syncActionCode": $("#syncActionCode").val(),
                "rollback": $("#rollback").val(),
             });
             serviceProcessActionDataSource.sync();
          };

        })(jQuery);
      </script>
    </div>
  </div>
</div>

<script type="text/javascript">
  $(function() {
    var ts = $("#service_process_tabstrip").kendoTabStrip({
      animation: { open: { effects: "fadeIn"} }
    }).data('kendoTabStrip');
  });
</script>
