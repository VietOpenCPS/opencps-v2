<#if (Request)??>
    <#include "init.ftl">
</#if>

<!-- Call API confirm keypay success -->

<!-- Template thông tin thu phí thành công -->
<div class="col-sm-12" style="background: #ffffff;">
  <h4 class="text-center MT30 MB30">DANH SÁCH CÁC YÊU CẦU THU PHÍ</h4>
  <div class="MB20 P10 PL15" style="background-color: #daffb3;border:1px solid #a9ff4d;">Giao dịch thanh toán đã thành công</div>

  <div id="infoPaying" class="table-responsive">
    <table class="table table-bordered">
      <tbody>
        <tr>
          <th class="col-sm-4">Mã tiếp nhận</th>
          <td class="col-sm-8" data-bind="text: dossierNo"></td>
        </tr>
        <tr>
          <th class="col-sm-4">Tên thủ tục</th>
          <td class="col-sm-8" data-bind="text: serviceName"></td>
        </tr>
        <tr>
          <th class="col-sm-4">Cơ quan thực hiện</th>
          <td class="col-sm-8" data-bind="text: govAgencyName"></td>
        </tr>
        <tr>
          <th class="col-sm-4">Tên phí, lệ phí</th>
          <td class="col-sm-8" data-bind="text: paymentFee"></td>
        </tr>
        <tr>
          <th class="col-sm-4">Tổng tiền</th>
          <td class="col-sm-8">
            <span data-bind="text: paymentAmount"></span>
            <span> VNĐ</span>
          </td>
        </tr>
        <tr>
          <th class="col-sm-4">Mã giao dịch trực tuyến</th>
          <td class="col-sm-8" data-bind="text: trans_id"></td>
        </tr>
        <tr>
          <th class="col-sm-4">Mã tra cứu trên cổng thanh toán</th>
          <td class="col-sm-8" data-bind="text: good_code"></td>
        </tr>
        <tr>
          <th class="col-sm-4">Cổng thanh toán</th>
          <td class="col-sm-8" data-bind="text: paymentPortal"></td>
        </tr>
      </tbody>
    </table>
  </div>
</div>

<script type="text/javascript">
  // /keypay/{dossierUUid}/{paymentFileUUid}
  // var dossierNo,serviceName,govAgencyName,paymentFee,paymentAmount,paymentPortal;
  var trans_id = ${RequestParameters.trans_id};
  var good_code = ${RequestParameters.good_code};
  $.ajax({
    url: "${api.server}/dossiers/keypay/${RequestParameters.dossierUUid}/${RequestParameters.paymentFileUUid}",
    type: "PUT",
    dataType: "json",
    headers: {"groupId": ${groupId}},
    data: {
      
    },
    success: function(result) {
      var viewModelInfoPaying = kendo.observable({
        dossierNo: result.dossierNo,
        serviceName: result.serviceName,
        govAgencyName: result.govAgencyName,
        paymentFee : result.paymentFee,
        paymentAmount: function(e){
          if(result.paymentAmount){
            var moneyCur = (result.paymentAmount/1).toFixed(0).replace('.', ',');
            return moneyCur.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ".");
          }
          return "";
        },
        paymentPortal : result.paymentPortal,
        trans_id : trans_id,
        good_code: good_code
      });
      kendo.bind($("#infoPaying"), viewModelInfoPaying);
    },
    error: function(result){
      
    }

  });
  
  
</script>