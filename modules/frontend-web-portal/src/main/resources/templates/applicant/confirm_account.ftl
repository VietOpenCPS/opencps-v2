<#if (Request)??>
<#include "init.ftl">
</#if>

<div class="box row MA" style="max-width:550px;">
  <div class="col-xs-12 col-sm-12 text-center">
    <h4><b>XÁC NHẬN TÀI KHOẢN</b></h4>
  </div>
  <div class="col-xs-12 col-sm-12 MT20 text-center">
    <p>Vui lòng nhập mã PIN đã được gửi trong email hoặc số điện thoại</p>
  </div>
  <div class="col-xs-12 col-sm-12 MT20">
    <input id="vertifyPIN" type="text" class="form-control" placeholder="Nhập mã PIN">
  </div>
  <div>
    <div class="col-xs-12 col-sm-6 MT20 MB20">
      <a href="#"><p class="text-link" id="btn-resend-activeCode">Gửi lại mã PIN?</p></a>
    </div>
    <div class="col-xs-12 col-sm-6 MT20 MB20">
      <div class="pull-right">
        <button class="btn btn-active" id="btn-active-account">Đồng ý</button>
      </div>
    </div>
  </div>
</div>
<script type="text/javascript">
  $("#btn-active-account").click(function(){
    $.ajax({
      url : "${api.server}/applicants/${active_user_id}/activate/"+$("#vertifyPIN").val(),
      dataType : "json",
      type : "GET",
      success : function(result){

      },
      error : function(xhr){

      },
      statusCode: {
        200: function(result) {
          console.log('Xác thực thành công');
          console.log(result);
          notification.show({
            title: "Success",
            message: "Xác thực thành công."
          }, "success");

          setTimeout(function(){
            /*<--window.location.href = "${redirectURL}";-->*/

            $("form[name=login_form] #input_login").val(result.email);
            $("form[name=login_form] #input_password").val(result.token);
            $("form[name=login_form] #input_action").val("confirm_account");

            $("form[name=login_form]").submit();
          }, 2000);
        },
        500: function(result) {
          notification.show({
            title: "Error",
            message: "Xẩy ra lỗi, vui lòng thử lại."
          }, "error");
        }
      }
    });
  });

  $("#btn-resend-activeCode").click(function(){
    $.ajax({
      url : "${api.server}/applicants/${active_user_id}/resendActivateCode",
      dataType : "json",
      type : "GET",
      success : function(result){

      },
      error : function(xhr){

      },
      statusCode: {
        200: function(result) {
          console.log('Xác thực thành công');
          console.log(result);
          notification.show({
            title: "Success",
            message: "Yêu cầu thành công"
          }, "success");
        },
        500: function(result) {
          notification.show({
            title: "Error",
            message: "Xẩy ra lỗi, vui lòng thử lại."
          }, "error");
        },
      }
    });
  });
</script>
