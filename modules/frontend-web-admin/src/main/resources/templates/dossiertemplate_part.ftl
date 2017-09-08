<#if (Request)??>
	<#include "init.ftl">
</#if>
<div class="row panel panel-body">
  <h4>Thành phần hồ sơ</h4>
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
              <div class="col-xs-12 col-sm-11">
                 <div class="row MB5">
                    <div class="col-xs-12 col-sm-3"><b>Số thành phần hồ sơ</b></div>
                    <div class="col-xs-12 col-sm-9">#: partNo #</div>
                 </div>
                 <div class="row MB5">
                    <div class="col-xs-12 col-sm-3"><b>Tên thành phần hồ sơ</b></div>
                    <div class="col-xs-12 col-sm-9">#: partName #</div>
                 </div>
                 <div class="row MB5">
                    <div class="col-xs-12 col-sm-3"><b>Kiểu thành phần hồ sơ</b></div>
                    <div class="col-xs-12 col-sm-9">
                      #
                        if (partType == "1"){
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
                        }
                      #
                    </div>
                 </div>
                 <div class="row MB5">
                    <div class="col-xs-12 col-sm-3"><b>Bắt buộc</b></div>
                    <div class="col-xs-12 col-sm-9">
                      #if (required){#
                        <i class="fa fa-check-square"></i>
                      #} else {#
                        <i class="fa fa-close"></i>
                      #}#
                    </div>
                 </div>
                 <div class="row">
                    <div class="col-xs-12 col-sm-3"><b>Ký số</b></div>
                    <div class="col-xs-12 col-sm-9">
                      #if (eSign){#
                        <i class="fa fa-check-square"></i>
                      #} else {#
                        <i class="fa fa-close"></i>
                      #}#
                    </div>
                 </div>
              </div>
              <div class="col-xs-12 col-sm-1 text-center">
                 <a class="btn-group btn-edit-dossier-template-part" data-pk="#: id #" href="\\#" title="Sửa">
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

  <!-- dossier template part form modal -->
  <div class="modal fade" id="dossier_template_part_form">
     <div class="modal-dialog modal-lg">
        <div class="modal-content"></div>
     </div>
  </div>

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
                url: "${api.server}" + "/dossiertemplates/" + options.data.dossierTemplateId + "/parts",
                type: "POST",
                dataType: "json",
                data: {
                  templateNo: options.data.templateNo,
                  partNo: options.data.partNo,
                  partName: options.data.partName,
                  partTip: options.data.partTip,
                  partType: options.data.partType,
                  multiple: options.data.multiple,
                  formScript: options.data.formScript,
                  formReport: options.data.formReport,
                  sampleData: options.data.sampleData,
                  required: options.data.required,
                  fileTemplateNo: options.data.fileTemplateNo,
                  eSign: options.data.eSign
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
                url: "${api.server}" + "/dossierparts/" + options.data.fileTemplateId,
                type: "PUT",
                dataType: "json",
                data: {
                  dossierPartId: options.data.dossierPartId,
                  templateNo: options.data.templateNo,
                  partNo: options.data.partNo,
                  partName: options.data.partName,
                  partTip: options.data.partTip,
                  partType: options.data.partType,
                  multiple: options.data.multiple,
                  formScript: options.data.formScript,
                  formReport: options.data.formReport,
                  sampleData: options.data.sampleData,
                  required: options.data.required,
                  fileTemplateNo: options.data.fileTemplateNo,
                  eSign: options.data.eSign
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
                url: "${api.server}" + "/dossierparts/" + options.data.fileTemplateId,
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
          model : { id: "dossierPartId" },
       },
       pageSize: 10,
       serverPaging: false,
       serverSorting: false,
       serverFiltering: false
    });

    $("#dossier_template_part_listview").kendoListView({
       dataSource: dossierTemplatePartDataSource,
       template: kendo.template($("#dossier_template_part_template").html()),
       selectable: true,
       remove: function(e) {
          if(!confirm("Xác nhận xóa thành phần hồ sơ: " + e.model.get("partName") + "?")){
             e.preventDefault();
          }
       },
       autoBind: false
    });

    $("#pager_dossier_template_part").kendoPager({
       dataSource: dossierTemplatePartDataSource,
       buttonCount: 3,
       pageSizes: [5, 10, 20, 50],
    });

    $(document).on("click", ".btn-edit-dossier-template-part", function(event){
       event.preventDefault();
       formControl($(this).attr("data-pk"));
    });

    $(document).on("click", "#btn_add_dossier_template_part", function(event){
       event.preventDefault();
       formControl();
    });

    var formControl = function(dataPk){
       var url = "${ajax.dossiertemplate_part_form}";

       if (dataPk){
         url = "${ajax.dossiertemplate_part_form}" + "?dossierTemplateId=" + dataPk;
       }

       $("#dossier_template_form .modal-content").load(
          url,
          function(result){

             $("#dossier_template_form").modal({show: true});

             $("#btn_cancle_dossier_template_part").click(function(e){
                e.preventDefault();
                $("#dossier_template_form").modal("hide");
             });

             var validator = $("#fm").kendoValidator().data("kendoValidator");

             $("form").submit(function(event) {
                event.preventDefault();
                if (validator.validate()) {

                   if (dataPk){
                      updateDossierTemplatePart(dataPk);
                   } else {
                      addDossierTemplatePart();
                   }

                   $("#dossier_template_form").modal("hide");
                }
             });
          }
       );
    }

    var updateDossierTemplatePart = function(dataPk){
       var dossierTemplatePart = dossierTemplatePartDataSource.get(dataPk);

       dossierTemplatePart.set("partNo", $("#partNo").val());
       dossierTemplatePart.set("partName", $("#partName").val());
       dossierTemplatePart.set("partTip", $("#partTip").val());
       dossierTemplatePart.set("partType", $("#partType").val());
       dossierTemplatePart.set("formScript", $("#formScript").val());
       dossierTemplatePart.set("formReport", $("#formReport").val());
       dossierTemplatePart.set("sampleData", $("#sampleData").val());
       dossierTemplatePart.set("required", $("#required").val());
       dossierTemplatePart.set("fileTemplateNo", $("#fileTemplateNo").val());
       dossierTemplatePart.set("eSign", $("#eSign").val());

       dossierTemplatePartDataSource.sync();
    }

    var addDossierTemplatePart = function(){
       dossierTemplatePartDataSource.add({
          "partNo": $("#partNo").val(),
          "partName": $("#partName").val(),
          "partTip": $("#partTip").val(),
          "partType": $("#partType").val(),
          "fileTemplateNo": $("#fileTemplateNo").val(),
          "required": $("#required").val(),
          "eSign": $("#eSign").val(),
          "formScript": $("#formScript").val(),
          "formReport": $("#formReport").val(),
          "sampleData": $("#sampleData").val(),
       });
       dossierTemplatePartDataSource.sync();
    };

  })(jQuery);
</script>
