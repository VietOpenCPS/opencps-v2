<#if (Request)??>
	<#include "init.ftl">
</#if>
<div class="modal-header">
	<a href="#" data-dismiss="modal" class="class pull-right">
		<span class="glyphicon glyphicon-remove"></span>
	</a>
	<h3 class="modal-title">Thêm cấu hình thanh toán</h3>
</div>
<div class="modal-body">
	<form id="fm">
    <ul id="panelbar">
      <li class="k-state-active">
        <span class="k-link k-state-selected">Cấu hình chung</span>
        <div class="row">
          <div class="row MT5 MB5">
            <div class="col-xs-12 col-sm-3">Loại cổng thanh toán</div>
            <div class="col-xs-12 col-sm-9">
              <input id="paymentGateType" name="paymentGateType" class="k-textbox form-control" value="${paymentconfigs.paymentGateType}" required="required" validationMessage="Trường nhập yêu cầu bắt buộc"/>
            </div>
          </div>
          <div class="row MT5 MB5">
            <div class="col-xs-12 col-sm-3">Mã cấu hình thanh toán</div>
            <div class="col-xs-12 col-sm-9">
              <input id="paymentConfigNo" name="paymentConfigNo" class="k-textbox form-control" value="${paymentconfigs.paymentConfigNo}" required="required" validationMessage="Trường nhập yêu cầu bắt buộc"/>
            </div>
          </div>
          <div class="row MT5 MB5">
            <div class="col-xs-12 col-sm-3">Tài khoản ngân hàng</div>
            <div class="col-xs-12 col-sm-9">
              <input id="bankInfo" name="bankInfo" class="k-textbox form-control" value="${paymentconfigs.bankInfo}" required="required" validationMessage="Trường nhập yêu cầu bắt buộc"/>
            </div>
          </div>
          <div class="row MT5 MB5">
            <div class="col-xs-12 col-sm-3">Địa chỉ thanh toán</div>
            <div class="col-xs-12 col-sm-9">
              <input id="placeInfo" name="placeInfo" class="k-textbox form-control" value="${paymentconfigs.placeInfo}" required="required" validationMessage="Trường nhập yêu cầu bắt buộc"/>
            </div>
          </div>
        </div>
      </li>
      <li>
        <span>Cấu hình thanh toán trực tuyến</span>
        <div class="row">
          <div class="row MT5 MB5">
            <div class="col-xs-12 col-sm-3">Địa chỉ miền</div>
            <div class="col-xs-12 col-sm-9">
              <input id="paymentDomain" name="paymentDomain" class="k-textbox form-control" value="${paymentconfigs.paymentDomain}" required="required" validationMessage="Trường nhập yêu cầu bắt buộc"/>
            </div>
          </div>
          <div class="row MT5 MB5">
            <div class="col-xs-12 col-sm-3">Mã đại lý</div>
            <div class="col-xs-12 col-sm-9">
              <input id="paymentMerchantCode" name="paymentMerchantCode" class="k-textbox form-control" value="${paymentconfigs.paymentMerchantCode}" required="required" validationMessage="Trường nhập yêu cầu bắt buộc"/>
            </div>
          </div>
          <div class="row MT5 MB5">
            <div class="col-xs-12 col-sm-3">Mã bảo mật</div>
            <div class="col-xs-12 col-sm-9">
              <input id="paymentSecureKey" name="paymentSecureKey" class="k-textbox form-control" value="${paymentconfigs.paymentSecureKey}" required="required" validationMessage="Trường nhập yêu cầu bắt buộc"/>
            </div>
          </div>
        </div>
      </li>
      <li>
        <span>Cấu hình thông tin hóa đơn</span>
        <div class="row">
          <div class="row MT5 MB5">
            <div class="col-xs-12 col-sm-3">Mã số thuế</div>
            <div class="col-xs-12 col-sm-9">
              <input id="govAgencyTaxNo" name="govAgencyTaxNo" class="k-textbox form-control" value="${paymentconfigs.govAgencyTaxNo}" required="required" validationMessage="Trường nhập yêu cầu bắt buộc"/>
            </div>
          </div>
          <div class="row MT5 MB5">
            <div class="col-xs-12 col-sm-3">Mẫu số</div>
            <div class="col-xs-12 col-sm-9">
              <input id="invoiceTemplateNo" name="invoiceTemplateNo" class="k-textbox form-control" value="${paymentconfigs.invoiceTemplateNo}" required="required" validationMessage="Trường nhập yêu cầu bắt buộc"/>
            </div>
          </div>
          <div class="row MT5 MB5">
            <div class="col-xs-12 col-sm-3">Kí hiệu</div>
            <div class="col-xs-12 col-sm-9">
              <input id="invoiceIssueNo" name="invoiceIssueNo" class="k-textbox form-control" value="${paymentconfigs.invoiceIssueNo}" required="required" validationMessage="Trường nhập yêu cầu bắt buộc"/>
            </div>
          </div>
          <div class="row MT5 MB5">
            <div class="col-xs-12 col-sm-3">Số thứ tự</div>
            <div class="col-xs-12 col-sm-9">
              <input id="invoiceLastNo" name="invoiceLastNo" class="k-textbox form-control" value="${paymentconfigs.invoiceLastNo}" required="required" validationMessage="Trường nhập yêu cầu bắt buộc"/>
            </div>
          </div>
          <div class="row MT5 MB5">
            <div class="col-xs-12 col-sm-3">Địa chỉ miền trả về</div>
            <div class="col-xs-12 col-sm-9">
              <input id="returnUrl" name="returnUrl" class="k-textbox form-control" value="${paymentconfigs.returnUrl}" required="required" validationMessage="Trường nhập yêu cầu bắt buộc"/>
            </div>
          </div>
          <div class="row MT5 MB5">
            <div class="col-xs-12 col-sm-3">Mẫu in biên lai</div>
            <div class="col-xs-12 col-sm-9">
              <textarea id="reportTemplate" name="reportTemplate" class="k-textbox form-control" required="required" validationMessage="Trường nhập yêu cầu bắt buộc">${paymentconfigs.reportTemplate}</textarea>
            </div>
          </div>
        </div>
      </li>
    </ul>

    <div class="row MT10 text-center">
			<button id="btn_save_payment_config" class="k-button btn-primary" title="Ghi lại">Ghi lại</button>
			<button id="btn_cancle_payment_config" class="k-button btn-default" title="Hủy bỏ">Hủy bỏ</button>
		</div>
	</form>
</div>

<script type="text/javascript">
  $(document).ready(function() {
      $("#panelbar").kendoPanelBar({});
  });
</script>
