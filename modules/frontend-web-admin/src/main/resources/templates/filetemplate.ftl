<div class="row panel panel-body">
   <div>
      <div class="row">
         <div class="col-xs-12 col-sm-2">
            <button id="btn-add-file-template" class="k-button btn-primary" title="Thêm biểu mẫu"><i class="glyphicon glyphicon-plus"></i> Thêm biểu mẫu </button>
         </div>
         <div class="col-xs-12 col-sm-10">
            <div class="form-group">
               <div class="input-group">
                  <input type="text" class="form-control" id="inputSearchFileTemplate" placeholder="Số biểu mẫu, Tên biểu mẫu" title="Nhập Sô biểu mẫu hoặc Tên biểu mẫu để tìm kiếm">
                  <div class="input-group-addon" id="buttonSearchFileTemplate" title="Tìm kiếm"><a href="#"><i class="fa fa-search"></i></a></div>
               </div>
            </div>
         </div>
      </div>
      <div class="row">
         <div class="panel-body">
            <ul class="ul-with-border">
               <div id="file_template_list_view"></div>
            </ul>
            <div id="pager" class="k-pager-wrap full-width-pager"></div>
         </div>

         <script type="text/x-kendo-template" id="fileTemplateTemplate">
            <li>
               <div class="row">
                  <div class="col-xs-12 col-sm-10">
                     <div class="row MB5">
                        <div class="col-xs-12 col-sm-2"><b>Số biểu mẫu</b></div>
                        <div class="col-xs-12 col-sm-10">#: fileNo #</div>
                     </div>
                     <div class="row">
                        <div class="col-xs-12 col-sm-2"><b>Tên biểu mẫu</b></div>
                        <div class="col-xs-12 col-sm-10">#: fileName #</div>
                     </div>
                  </div>
                  <div class="col-xs-12 col-sm-2 text-center">
                    <a class="btn-group btn-download-file-template" data-pk="#: id #" href="\\#">
                      <i aria-hidden="true" class="fa fa-cloud-download"></i>
                    </a>
                    <a class="btn-group btn-edit-file-template" data-pk="#: id #" href="\\#">
                      <i aria-hidden="true" class="fa fa-pencil"></i>
                    </a>
                    <a class="btn-group k-delete-button" href="\\#">
                      <i aria-hidden="true" class="fa fa-times"></i>
                    </a>
                  </div>
               </div>
            </li>
         </script>
      </div>

      <!-- filetemplate form modal -->
      <div class="modal fade" id="filetemplate_form">
         <div class="modal-dialog modal-lg">
            <div class="modal-content"></div>
         </div>
      </div>

   </div>
</div>

<script type="text/javascript">
   (function($) {
      var fileTemplateDataSource = new kendo.data.DataSource({
         transport: {
            read: function(options) {
               $.ajax({
                  url: "${api.server}" + "/filetemplates",
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
                    notification.show({
                      message: "Xẩy ra lỗi, vui lòng thử lại"
                    }, "error");
                  }
               });
            },
            create: function(options) {
              var data = new FormData();
              data.append("fileNo", options.data.fileNo);
              data.append("fileName", options.data.fileName);
              data.append("file", $("input#file")[0].files[0]);

              $.ajax({
                url: "${api.server}" + "/filetemplates",
                type: "POST",
                data: data,
                cache: false,
    				    contentType: false,
    				    processData: false,
                success: function(result) {
                  options.success(result);
                  notification.show({
                    message: "Tạo biểu mẫu " + options.data.fileNo + " thành công."
                  }, "success");
                },
                error: function(result) {
                  options.error(result);
                  notification.show({
                    message: "Tạo biểu mẫu " + options.data.fileNo + " thất bại."
                  }, "error");
                }
              });
            },
            update: function(options) {
              console.log(options);
              console.log("update: " + options.data.fileTemplateId);
              var data = new FormData();
              data.append("fileTemplateId", options.data.fileTemplateId);
              data.append("fileNo", options.data.fileNo);
              data.append("fileName", options.data.fileName);
              data.append("file", $("input#file")[0].files[0]);

               $.ajax({
                  url: "${api.server}" + "/filetemplates/" + options.data.fileTemplateId,
                  type: "PUT",
                  data: data,
                  cache: false,
      				    contentType: false,
      				    processData: false,
                  success: function(result) {
                    options.success(result);
                    notification.show({
                      message: "Cập nhật biểu mẫu " + options.data.fileNo + " thành công."
                    }, "success");
                  },
                  error: function(result) {
                    options.error(result);
                    fileTemplateDataSource.cancelChanges();
                    notification.show({
                      message: "Cập nhật biểu mẫu " + options.data.fileNo + " thất bại."
                    }, "error");
                  }
               });
            },
            destroy: function(options) {
               $.ajax({
                  url: "${api.server}" + "/filetemplates/" + options.data.fileTemplateId,
                  type: "DELETE",
                  dataType: "json",
                  success: function(result) {
                    options.success(result);
                    notification.show({
                      message: "Xóa biểu mẫu " + options.data.fileNo + " thành công."
                    }, "success");
                  },
                  error: function(result) {
                    options.error(result);
                    notification.show({
                      message: "Xóa biểu mẫu " + options.data.fileNo + " thất bại."
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
            model : { id: "fileTemplateId" },
         },
         pageSize: 10,
         serverPaging: false,
         serverSorting: false,
         serverFiltering: false
      });

      $("#file_template_list_view").kendoListView({
         dataSource: fileTemplateDataSource,
         template: kendo.template($("#fileTemplateTemplate").html()),
         selectable: true,
         remove: function(e) {
            if(!confirm("Xác nhận xóa biểu mẫu thủ tục: " + e.model.get("fileName") + "?")){
               e.preventDefault();
            }
         }
      });

      $("#pager").kendoPager({
         dataSource: fileTemplateDataSource,
         buttonCount: 3,
         pageSizes: [5, 10, 20, 50],
      });

      $("#inputSearchFileTemplate").keyup(function(e){
         fileTemplateFilter();
      });

      $("#buttonSearchFileTemplate").click(function(){
         fileTemplateFilter();
      });

      var fileTemplateFilter = function(){
         var inputSearch = $("#inputSearchFileTemplate").val();

         var filters = [];
         var filter = {};

         filters.push({field: "fileNo", operator: "contains", value: inputSearch});
         filters.push({field: "fileName", operator: "contains", value: inputSearch});

         filter = {
            logic: "or",
            filters: filters
         };

         fileTemplateDataSource.filter(filter);
      };

      $(document).on("click", ".btn-download-file-template", function(event){
         event.preventDefault();
         downloadFile($(this).attr("data-pk"));
      });

      $(document).on("click", ".btn-edit-file-template", function(event){
         event.preventDefault();
         formControl($(this).attr("data-pk"));
      });

      $(document).on("click", "#btn-add-file-template", function(event){
         event.preventDefault();
         formControl();
      });

      var downloadFile = function(fileTemplateId){
        var url = "${api.server}" + "/filetemplates/" + fileTemplateId + "/download"
      //  window.location.href = url;
        window.open(
          url,
          "_blank"
        );
      }

      var formControl = function(dataPk){
         var url = "${ajax.filetemplate_form}";

         if (dataPk){
           url = "${ajax.filetemplate_form}" + "?fileTemplateId=" + dataPk;
         }

         $("#filetemplate_form .modal-content").load(
            url,
            function(result){

               $("#filetemplate_form").modal({show: true});

               $("#btnCancleFileTemplate").click(function(e){
                  e.preventDefault();
                  $("#filetemplate_form").modal("hide");
               });

               var validator = $("#fm").kendoValidator().data("kendoValidator");

               $("form").submit(function(event) {
                  event.preventDefault();
                  if (validator.validate()) {

                     if (dataPk){
                        updateFiletemplate(dataPk);
                     } else {
                        addFileTemplete();
                     }

                     $("#filetemplate_form").modal("hide");
                  }
               });
            }
         );
      }

      var updateFiletemplate = function(dataPk){
         var fileTemplate = fileTemplateDataSource.get(dataPk);

         fileTemplate.set("fileNo", $("#fileNo").val());
         fileTemplate.set("fileName", $("#fileName").val());

         fileTemplateDataSource.sync();
      }

      var addFileTemplete = function(){
         fileTemplateDataSource.add({
            "fileName": $("#fileName").val(),
            "fileNo": $("#fileNo").val(),
         });
         fileTemplateDataSource.sync();
      };

   })(jQuery);
</script>
