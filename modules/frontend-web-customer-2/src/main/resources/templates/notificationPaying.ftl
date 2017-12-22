<#if (Request)??>
    <#include "init.ftl">
</#if>
<!-- Template thông tin thu phí thành công -->
<div class="col-sm-10">
  <h3 class="text-center MT30 MB30">DANH SÁCH CÁC YÊU CẦU THU PHÍ</h3>
  <div class="MB20 P10 PL15" style="background-color: #daffb3;border:1px solid #a9ff4d;">Giao dịch thanh toán đã thành công</div>

  <div id="infoPaying" class="table-responsive">
    <table class="table table-bordered">
      <tbody>
        <tr>
          <th class="col-sm-4">Mã tiếp nhận</th>
          <td class="col-sm-8" data-bind="text:"></td>
        </tr>
        <tr>
          <th class="col-sm-4">Tên thủ tục</th>
          <td class="col-sm-8" data-bind="text:"></td>
        </tr>
        <tr>
          <th class="col-sm-4">Cơ quan thực hiện</th>
          <td class="col-sm-8" data-bind="text:"></td>
        </tr>
        <tr>
          <th class="col-sm-4">Tên phí, lệ phí</th>
          <td class="col-sm-8" data-bind="text:"></td>
        </tr>
        <tr>
          <th class="col-sm-4">Tổng tiền</th>
          <td class="col-sm-8" data-bind="text:"></td>
        </tr>
        <tr>
          <th class="col-sm-4">Mã giao dịch trực tuyến</th>
          <td class="col-sm-8" data-bind="text:"></td>
        </tr>
        <tr>
          <th class="col-sm-4">Mã tra cứu trên cổng thanh toán</th>
          <td class="col-sm-8" data-bind="text:"></td>
        </tr>
        <tr>
          <th class="col-sm-4">Cổng thanh toán</th>
          <td class="col-sm-8" data-bind="text:"></td>
        </tr>
      </tbody>
    </table>
  </div>
</div>

<script type="text/javascript">

  var viewModelInfoPaying = kendo.observable({
    dossierId: result.dossierId,
    applicantName: result.applicantName,
    serviceName: result.serviceName,
    dossierNo: result.dossierNo,
    govAgencyName: result.govAgencyName,
    submitDate: result.submitDate,
    dueDate: result.dueDate,
    dossierStatusText: result.dossierStatusText
  });
  kendo.bind($("#infoPaying"), viewModelInfoPaying);
</script>