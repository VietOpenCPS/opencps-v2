<#if (Request)??>
<#include "init.ftl">
</#if>
<div class="row panel panel-body">
 <div class="col-sm-6">
  <label>Tên cơ quan thực hiện  </label>
  <select class="form-control" id="govAgencyCode" name="govAgencyCode" data-bind="value: govAgencyCode" required="required" validationMessage="Bạn phải chọn cơ quan">
    <option value=""></option>

  </select>
  <span data-for="govAgencyCode" class="k-invalid-msg"></span>
</div>
<div class="col-sm-6">

</div>
<div class="col-sm-6">
 <label>Mã số thuế cơ quan</label>
 <div class="form-group">
  <input type="text" name="govAgencyTaxNo" id="govAgencyTaxNo" class="form-control" placeholder="Số hiệu" data-bind="value:govAgencyTaxNo" required="required" validationMessage="Bạn phải điền số hiệu">
  <span data-for="govAgencyTaxNo" class="k-invalid-msg"></span>
</div>
</div>
<div class="col-sm-6">
  <label>Ký hiệu hóa đơn</label>
  <div class="form-group">
    <input type="text" name="serviceCode" id="serviceCode" class="form-control" placeholder="Số hiệu" data-bind="value:serviceCode" required="required" validationMessage="Bạn phải điền số hiệu">
    <span data-for="serviceCode" class="k-invalid-msg"></span>
  </div>
</div>
<div class="col-sm-6">
  <label>Mẫu số hóa đơn</label>
  <div class="form-group">
    <input type="text" name="invoiceTemplateNo" id="invoiceTemplateNo" class="form-control" placeholder="Số hiệu" data-bind="value:invoiceTemplateNo" required="required" validationMessage="Bạn phải điền số hiệu">
    <span data-for="invoiceTemplateNo" class="k-invalid-msg"></span>
  </div>
</div>
<div class="col-sm-6">
  <label>Số hóa đơn tạo ra cuối cùng</label>
  <div class="form-group">
    <input type="text" name="invoiceLastNo" id="invoiceLastNo" class="form-control" placeholder="Số hiệu" data-bind="value:invoiceLastNo" required="required" validationMessage="Bạn phải điền số hiệu">
    <span data-for="invoiceLastNo" class="k-invalid-msg"></span>
  </div>
</div>
<div class="col-sm-12">
 <div class="form-group">
   <label class="control-label">Mẫu biên lai  
   </label> 
   <textarea class="form-control" rows="3" id="address" name="address" data-bind="text:address" ></textarea>
 </div>
</div>
<div class="col-sm-12">
 <div class="form-group">
  <label class="control-label">Cấu hình thanh toán điện tử 
  </label> 
  <textarea class="form-control" rows="3" id="address" name="address" data-bind="text:address" ></textarea>
</div>
</div>
</div>

<script type="text/javascript">

  $("#govAgencyCode").kendoComboBox({
    dataTextField : "",
    dataValueField : "",
    dataSource : {
      transport : {
        read : {
          url : "",
          dataType : "json",
          type : "GET",
          success : function(result){

          },
          error : function(xhr){

          }
        }
      },
      schema : {
        data : "data",
        total : "total",
        model : {
          id : "id"
        }
      }
    }
  });
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
</script>
