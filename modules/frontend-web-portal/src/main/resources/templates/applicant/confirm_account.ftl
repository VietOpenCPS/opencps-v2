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
      <a href="#"><p class="text-link">Gửi lại mã PIN?</p></a>
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

      }
    });
  });
</script>
