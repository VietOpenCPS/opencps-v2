<#if (Request)??>
	<#include "init.ftl">
</#if>

<div>
  <div class="row">
     <div class="col-xs-12 col-sm-12">
        <button id="btn_add_service_process_step" class="k-button btn-primary" title="Thêm bước xử lý"><i class="glyphicon glyphicon-plus"></i> Thêm bước xử lý </button>
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
	         <b>Tên bước</b>
	       </div>
	       <div class="col-sm-2 text-center">
	         <b>Trạng thái</b>
	       </div>
	       <div class="col-sm-2 text-center">
	         <b>Thời gian xử lý</b>
	       </div>
	       <div class="col-sm-2 text-center">
	         <b>Hành động</b>
	       </div>
	     </li>
	   </ul>
	   <ul id ="service_process_step_listview" class="mimic-table"></ul>
	   <div id="service_process_step_bager" class="k-pager-wrap full-width-pager pull-right PR15 PB15"></div>
	 </div>

	 <script type="text/x-kendo-template" id="service_process_step_template">
		   <li class="clearfix eq-height-lg" data-pk="#: id #" style="padding: 10px 0 10px 5px;" role="option" aria-selected="true">
		     <div class="col-sm-1 text-center center-all">
		       #:itemIndex #
		     </div>
		     <div class="col-sm-5 item-serviceinfo text-hover-blue hover-pointer align-middle-lg" data-pk="#: id #">
		      #: stepName #
		    </div>
		    <div class="col-sm-2 align-middle-lg text-center">
		      #: dossierStatus #
		    </div>
		    <div class="col-sm-2 align-middle-lg text-center">
		      #: durationCount #
		    </div>
		    <div class="col-sm-2 text-center">
					<a class="btn-group btn-edit-service-process-step" data-pk="#: id #" href="\\#" title="Sửa">
							<i aria-hidden="true" class="fa fa-pencil"></i>
					</a>
					<a class="btn-group k-delete-button" href="\\#" title="Xóa">
							<i aria-hidden="true" class="fa fa-times"></i>
					</a>
				</div>
			</li>
		</script>
	</div>

  <script type="text/javascript">
    (function($){
      var serviceProcessStepDataSource = new kendo.data.DataSource({
         transport: {
            read: function(options) {
              $.ajax({
                 url: "${api.server}" + "/serviceprocesses/" + options.data.serviceProcessId + "/steps",
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
                  url: "${api.server}" + "/serviceprocesses/" + options.data.serviceProcessId + "/steps",
                  type: "POST",
                  dataType: "json",
									headers: {"groupId": ${groupId}},
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
                  url: "${api.server}" + "/serviceprocesses/" + options.data.serviceProcessId + "/steps/" + options.data.stepCode,
                  type: "PUT",
                  dataType: "json",
									headers: {"groupId": ${groupId}},
                  data: {
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
                  url: "${api.server}" + "/serviceprocesses/" + options.data.serviceProcessId + "/steps/" + options.data.stepCode,
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
            model : { id: "stepCode" },
         },
         pageSize: 10,
         serverPaging: false,
         serverSorting: false,
         serverFiltering: false
      });

			var localIndex = 0;
      $("#service_process_step_listview").kendoListView({
         dataSource: serviceProcessStepDataSource,
				 template: function(data){
						var _pageSize = serviceProcessStepDataSource.pageSize();
						localIndex++;
						var currentPage = $("#service_process_step_bager").data("kendoPager").page();
						var totalPage =  $("#service_process_step_bager").data("kendoPager").totalPages();
						var index = (currentPage-1)*_pageSize + localIndex;
						data.itemIndex = index;
						return kendo.template($("#service_process_step_template").html())(data);
					},
         selectable: true,
         autoBind: false,
         remove: function(e) {
            if(!confirm("Xác nhận xóa bước: " + e.model.get("stepName") + "?")){
               e.preventDefault();
            }
         }
      });

      $("#service_process_step_bager").kendoPager({
         dataSource: serviceProcessStepDataSource,
         buttonCount: 3,
         pageSizes: [5, 10, 20, 50],
				 info: false,
				 messages: {
		        itemsPerPage: ""
		      }
      });

      $(document).on("click", ".btn-edit-service-process-step", function(event){
         event.preventDefault();

				 $("#serviceprocess_step_container").hide();
				 $("#serviceprocess_detail_formstep_container").show();

					// var index = this.select().index();
					// var dataItem = this.dataSource.view()[index];

					// var viewModel = kendo.observable({
					// 		processNo : dataItem.processNo,
					// 		processName : dataItem.processName,
					// 		description : dataItem.description,
					// 		durationCount : dataItem.durationCount
					// });

					//kendo.bind($("#fm_process_info"), viewModel);

					$("#btn_save_service_process_step").attr("data-pk", $(this).attr("data-pk"));
      });

			$("#btn_cancle_service_process_step").click(function(e){
				 e.preventDefault();
				 $("#serviceprocess_step_container").show();
				 $("#serviceprocess_detail_formstep_container").hide();
			});

      $(document).on("click", "#btn_add_service_process_step", function(event){
         event.preventDefault();
				 $("#serviceprocess_step_container").hide();
				 $("#serviceprocess_detail_formstep_container").show();

         $("#btn_save_service_process_step").attr("data-pk", "");
      });

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
