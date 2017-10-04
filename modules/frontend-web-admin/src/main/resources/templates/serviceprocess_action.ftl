
<#if (Request)??>
	<#include "init.ftl">
</#if>

<div>
  <div class="row MT10">
     <div class="col-xs-12 col-sm-12">
        <button id="btn_add_service_process_action" class="k-button btn-primary" title="Thêm hành động"><i class="glyphicon glyphicon-plus"></i> Thêm hành động </button>
     </div>
  </div>
  <div class="row">
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
                  url: "${api.server}" + "/serviceprocesses/" + options.data.serviceProcessId + "/actions",
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
            create: function(options) {
               $.ajax({
                  url: "${api.server}" + "/serviceprocesses/" + options.data.serviceProcessId + "/actions",
                  type: "POST",
                  dataType: "json",
									headers: {"groupId": ${groupId}},
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
                  url: "${api.server}" + "/serviceprocesses/" + options.data.serviceProcessId + "/actions/" + options.data.processActionId,
                  type: "PUT",
                  dataType: "json",
									headers: {"groupId": ${groupId}},
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
                  url: "${api.server}" + "/serviceprocesses/" + options.data.serviceProcessId + "/actions/" + options.data.processActionId,
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
         selectable: true,
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

					// var index = this.select().index();
					// var dataItem = this.dataSource.view()[index];

					// var viewModel = kendo.observable({
					// 		processNo : dataItem.processNo,
					// 		processName : dataItem.processName,
					// 		description : dataItem.description,
					// 		durationCount : dataItem.durationCount
					// });

					//kendo.bind($("#fm_process_info"), viewModel);

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
      });

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
