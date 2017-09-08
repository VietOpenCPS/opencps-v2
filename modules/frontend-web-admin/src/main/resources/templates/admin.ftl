<#if (Request)??>
	<#include "init.ftl">
</#if>

<div id="admin_management_tabstrip" class="row">
  <div id="admin_tabstrip">
    <ul class="ul-with-border ul-with-border-style-2">
      <li class="clearfix k-state-active" >
        <div class="col-sm-2 clearfix ">
          <a href="javascript:;" >
          <i class="fa fa-gg" aria-hidden="true"></i>
          </a>
        </div>
        <div class="col-sm-9 PL10 P0">Thủ tục hành chính</div>
      </li>
      <li class="clearfix k-state-active" >
        <div class="col-sm-2 clearfix ">
          <a href="javascript:;" >
          <i class="fa fa-gg" aria-hidden="true"></i>
          </a>
        </div>
        <div class="col-sm-9 PL10 P0">Mẫu hồ sơ</div>
      </li>
      <li class="clearfix k-state-active" >
        <div class="col-sm-2 clearfix ">
          <a href="javascript:;" >
          <i class="fa fa-gg" aria-hidden="true"></i>
          </a>
        </div>
        <div class="col-sm-9 PL10 P0">Dịch vụ công</div>
      </li>
      <li class="clearfix k-state-active" >
        <div class="col-sm-2 clearfix ">
          <a href="javascript:;" >
          <i class="fa fa-gg" aria-hidden="true"></i>
          </a>
        </div>
        <div class="col-sm-9 PL10 P0">Quy trình</div>
      </li>
      <li class="clearfix k-state-active" >
        <div class="col-sm-2 clearfix ">
          <a href="javascript:;" >
          <i class="fa fa-gg" aria-hidden="true"></i>
          </a>
        </div>
        <div class="col-sm-9 PL10 P0">Cấu hình thanh toán</div>
      </li>
    </ul>
  </div>
</div>

<script type="text/javascript">
  $(function() {
    var ts = $("#admin_tabstrip").kendoTabStrip({
      animation: { open: { effects: "fadeIn"} },
      tabPosition: "left",
      contentUrls: [
        '${ajax.serviceinfo_list}',
        'dossiertemplate.ftl',
        'serviceconfig.ftl',
        'serviceprocess.ftl',
        'paymentconfig.ftl'
      ]
    }).data('kendoTabStrip');
  });
</script>

<!-- popup notification -->
<!-- container -->
<span id="notification" style="display:none;"></span>

<!-- templates -->
<script id="successTemplate" type="text/x-kendo-template">
  <div class="popup-error-notification">
    <p>#= message #</p>
  </div>
</script>

<script id="errorTemplate" type="text/x-kendo-template">
  <div class="popup-success-notification">
    <p>#= message #</p>
  </div>
</script>

<!-- script -->
<script type="text/javascript">
  var notification;
  $(document).ready(function() {
    notification = $("#notification").kendoNotification({
        position: {
            pinned: true,
            top: 30,
            right: 30
        },
        autoHideAfter: 1000,
        stacking: "down",
        templates: [
          {
            type: "success",
            template: $("#successTemplate").html()
          },
          {
            type: "error",
            template: $("#errorTemplate").html()
          }
        ]
    }).data("kendoNotification");
  });
</script>
