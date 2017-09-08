<#if (Request)??>
	<#include "init.ftl">
</#if>
<div class="row panel panel-body">
   <div>
      <div class="row">
         <div class="col-xs-12 col-sm-3 text-center">
            <button id="btn-add-payment-config" class="k-button btn-primary" title="Thêm cấu hình thanh toán"><i class="glyphicon glyphicon-plus"></i> Thêm cấu hình thanh toán </button>
         </div>
         <div class="col-xs-12 col-sm-9">
            <div class="form-group">
               <div class="input-group">
                  <input type="text" class="form-control" id="input_search_payment_config" placeholder="Mã cấu hình" title="Nhập mã cấu hình để tìm kiếm">
                  <div class="input-group-addon" id="btn_search_payment_config" title="Tìm kiếm"><a href="#"><i class="fa fa-search"></i></a></div>
               </div>
            </div>
         </div>
      </div>
      <div class="row">
         <div class="panel-body">
            <ul class="ul-with-border">
               <div id="payment_config_list_view"></div>
            </ul>
            <div id="payment_config_pager" class="k-pager-wrap full-width-pager"></div>
         </div>

         <script type="text/x-kendo-template" id="payment_config_template">
            <li>
               <div class="row">
                  <div class="col-xs-12 col-sm-10">
                     <div class="row MB5">
                        <div class="col-xs-12 col-sm-3"><b>Mã cấu hình thanh toán</b></div>
                        <div class="col-xs-12 col-sm-9">#: paymentConfigNo #</div>
                     </div>
                     <div class="row">
                        <div class="col-xs-12 col-sm-3"><b>Loại cổng thanh toán</b></div>
                        <div class="col-xs-12 col-sm-9">#: paymentGateType #</div>
                     </div>
                  </div>
                  <div class="col-xs-12 col-sm-2 text-center">
                    <a class="btn-group btn-edit-payment-config" data-pk="#: id #" href="\\#">
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
      <div class="modal fade" id="paymentconfig_form">
         <div class="modal-dialog modal-lg">
            <div class="modal-content"></div>
         </div>
      </div>

   </div>
</div>

<script type="text/javascript">
   (function($) {
      var paymentConfigDataSource = new kendo.data.DataSource({
         transport: {
            read: function(options) {
               $.ajax({
                  url: "${api.server}" + "/paymentconfigs",
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
                  url: "${api.server}" + "/paymentconfigs",
                  type: "POST",
                  dataType: "json",
                  data: {
                    govAgencyCode: $("#govAgencyCode").val(),
                    govAgencyName: $("#govAgencyName").val(),
                    govAgencyTaxNo: $("#govAgencyTaxNo").val(),
                    invoiceTemplateNo: $("#invoiceTemplateNo").val(),
                    invoiceIssueNo: $("#invoiceIssueNo").val(),
                    invoiceLastNo: $("#invoiceLastNo").val(),
                    bankInfo: $("#bankInfo").val(),
                    placeInfo: $("#placeInfo").val(),
                    paymentDomain: $("#paymentDomain").val(),
                    paymentVersion: $("#paymentVersion").val(),
                    paymentMerchantCode: $("#paymentMerchantCode").val(),
                    paymentSecureKey: $("#paymentSecureKey").val(),
                    reportTemplate: $("#reportTemplate").val(),
                    paymentGateType: $("#paymentGateType").val(),
                    returnUrl: $("#returnUrl").val(),
                    paymentConfigNo: $("#paymentConfigNo").val(),
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
                  url: "${api.server}" + "/paymentconfigs/" + options.data.paymentConfigId,
                  type: "PUT",
                  dataType: "json",
                  data: {
                    paymentConfigId: options.data.paymentConfigId,
                    govAgencyCode: $("#govAgencyCode").val(),
                    govAgencyName: $("#govAgencyName").val(),
                    govAgencyTaxNo: $("#govAgencyTaxNo").val(),
                    invoiceTemplateNo: $("#invoiceTemplateNo").val(),
                    invoiceIssueNo: $("#invoiceIssueNo").val(),
                    invoiceLastNo: $("#invoiceLastNo").val(),
                    bankInfo: $("#bankInfo").val(),
                    placeInfo: $("#placeInfo").val(),
                    paymentDomain: $("#paymentDomain").val(),
                    paymentVersion: $("#paymentVersion").val(),
                    paymentMerchantCode: $("#paymentMerchantCode").val(),
                    paymentSecureKey: $("#paymentSecureKey").val(),
                    reportTemplate: $("#reportTemplate").val(),
                    paymentGateType: $("#paymentGateType").val(),
                    returnUrl: $("#returnUrl").val(),
                    paymentConfigNo: $("#paymentConfigNo").val(),
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
                  url: "${api.server}" + "/paymentconfigs/" + options.data.paymentConfigId,
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
            model : { id: "paymentConfigId" },
         },
         pageSize: 10,
         serverPaging: false,
         serverSorting: false,
         serverFiltering: false
      });

      $("#payment_config_list_view").kendoListView({
         dataSource: paymentConfigDataSource,
         template: kendo.template($("#payment_config_template").html()),
         selectable: true,
         remove: function(e) {
            if(!confirm("Xác nhận xóa cấu hình thanh toán: " + e.model.get("paymentConfigNo") + "?")){
               e.preventDefault();
            }
         }
      });

      $("#payment_config_pager").kendoPager({
         dataSource: paymentConfigDataSource,
         buttonCount: 3,
         pageSizes: [5, 10, 20, 50],
      });

      $("#input_search_payment_config").keyup(function(e){
         paymentConfigFilter();
      });

      $("#btn_search_payment_config").click(function(){
         paymentConfigFilter();
      });

      var paymentConfigFilter = function(){
         var inputSearch = $("#input_search_payment_config").val();

         var filters = [];
         var filter = {};

         filters.push({field: "paymentConfigNo", operator: "contains", value: inputSearch});
         filters.push({field: "paymentGateType", operator: "contains", value: inputSearch});

         filter = {
            logic: "or",
            filters: filters
         };

         paymentConfigDataSource.filter(filter);
      };

      $(document).on("click", ".btn-edit-payment-config", function(event){
         event.preventDefault();
         formControl($(this).attr("data-pk"));
      });

      $(document).on("click", "#btn-add-payment-config", function(event){
         event.preventDefault();
         formControl();
      });

      var formControl = function(dataPk){
         var url = "${ajax.paymentconfig_form}";

         if (dataPk){
           url = "${ajax.paymentconfig_form}" + "?paymentConfigId=" + dataPk;
         }

         $("#paymentconfig_form .modal-content").load(
            url,
            function(result){

               $("#paymentconfig_form").modal({show: true});

               $("#btn_cancle_payment_config").click(function(e){
                  e.preventDefault();
                  $("#paymentconfig_form").modal("hide");
               });

               var validator = $("#fm").kendoValidator().data("kendoValidator");

               $("form").submit(function(event) {
                  event.preventDefault();
                  if (validator.validate()) {

                     if (dataPk){
                        updatePaymentConfig(dataPk);
                     } else {
                        addPaymentConfig();
                     }

                     $("#paymentconfig_form").modal("hide");
                  }
               });
            }
         );
      }

      var updatePaymentConfig = function(dataPk){
         var paymentConfig = paymentConfigDataSource.get(dataPk);

         paymentConfig.set("govAgencyCode", $("#govAgencyCode").val());
         paymentConfig.set("govAgencyName", $("#govAgencyName").val());
         paymentConfig.set("govAgencyTaxNo", $("#govAgencyTaxNo").val());
         paymentConfig.set("invoiceTemplateNo", $("#invoiceTemplateNo").val());
         paymentConfig.set("invoiceIssueNo", $("#invoiceIssueNo").val());
         paymentConfig.set("invoiceLastNo", $("#invoiceLastNo").val());
         paymentConfig.set("bankInfo", $("#bankInfo").val());
         paymentConfig.set("placeInfo", $("#placeInfo").val());
         paymentConfig.set("paymentDomain", $("#paymentDomain").val());
         paymentConfig.set("paymentVersion", $("#paymentVersion").val());
         paymentConfig.set("paymentMerchantCode", $("#paymentMerchantCode").val());
         paymentConfig.set("paymentSecureKey", $("#paymentSecureKey").val());
         paymentConfig.set("reportTemplate", $("#reportTemplate").val());
         paymentConfig.set("paymentGateType", $("#paymentGateType").val());
         paymentConfig.set("returnUrl", $("#returnUrl").val());
         paymentConfig.set("paymentConfigNo", $("#paymentConfigNo").val());

         paymentConfigDataSource.sync();
      }

      var addPaymentConfig = function(){
         paymentConfigDataSource.add({
            "govAgencyCode": $("#govAgencyCode").val(),
            "govAgencyName": $("#govAgencyName").val(),
            "govAgencyTaxNo": $("#govAgencyTaxNo").val(),
            "invoiceTemplateNo": $("#invoiceTemplateNo").val(),
            "invoiceIssueNo": $("#invoiceIssueNo").val(),
            "invoiceLastNo": $("#invoiceLastNo").val(),
            "bankInfo": $("#bankInfo").val(),
            "placeInfo": $("#placeInfo").val(),
            "paymentDomain": $("#paymentDomain").val(),
            "paymentVersion": $("#paymentVersion").val(),
            "paymentMerchantCode": $("#paymentMerchantCode").val(),
            "paymentSecureKey": $("#paymentSecureKey").val(),
            "reportTemplate": $("#reportTemplate").val(),
            "paymentGateType": $("#paymentGateType").val(),
            "returnUrl": $("#returnUrl").val(),
            "paymentConfigNo": $("#paymentConfigNo").val(),
         });
         paymentConfigDataSource.sync();
      };

   })(jQuery);
</script>
