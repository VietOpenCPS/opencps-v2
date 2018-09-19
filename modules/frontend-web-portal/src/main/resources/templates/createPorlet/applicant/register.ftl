<#if (Request)??>
<#include "init.ftl">
</#if>

<div class="row row eq-height-lg" id="register_container">
	<div class="col-xs-12 col-sm-8 align-middle-lg">
		<div class="col-xs-12 bg-gif-register"></div>
	</div>
	<div class="col-xs-12 col-sm-4 box register-wrapper">
   <form id="fm">
    <div class="row">
      <div class="box-title">
        <b>ĐĂNG KÝ TÀI KHOẢN</b>
      </div>
    </div>
    <div class="row MT10 MB10">
      <div class="col-xs-12 col-sm-12 text-center">
       <div class="radio-inline"> <input type="radio" name="applicantIdType" value="citizen" checked> <label>Công dân</label> </div>
       <div class="radio-inline"> <input type="radio" name="applicantIdType" value="business"> <label>Doanh nghiệp</label> </div>
     </div>
   </div>
   <div class="row MT15">
     <div class="col-xs-12 col-sm-12" id="lblApplicantName">Họ và tên</div>
     <div class="col-xs-12 col-sm-12 MT5">
      <input type="text" id="applicantName" name="applicantName" class="form-control" required="required" validationMessage="Trường nhập yêu cầu bắt buộc" placeholder="Tên cá nhân làm thủ tục"/>
    </div>
  </div>
  <div class="row MT15">
    <div class="col-xs-12 col-sm-12" id="lblApplicantIdNo">Số CMND/ Hộ chiếu</div>
    <div class="col-xs-12 col-sm-12 MT5">
      <input type="text" id="applicantIdNo" name="applicantIdNo" class="form-control" required="required" validationMessage="Trường nhập yêu cầu bắt buộc" placeholder="Số CMND hoặc số Hộ chiếu"/>
    </div>
  </div>
  <div class="row MT15">
   <div class="col-xs-12 col-sm-12">Ngày cấp</div>
   <div class="col-xs-12 col-sm-12 MT5">
    <input id="applicantIdDate" name="applicantIdDate" required="required" validationMessage="Trường nhập yêu cầu bắt buộc" placeholder="Ngày/ tháng/ năm"/>
  </div>
</div>
<div class="row MT15">
  <div class="col-xs-12 col-sm-12">Thư điện tử</div>
  <div class="col-xs-12 col-sm-12 MT5">
    <input type="text" id="contactEmail" name="contactEmail" class="form-control" required="required" validationMessage="Trường nhập yêu cầu bắt buộc" placeholder="Địa chỉ thư điện tử" />
  </div>
</div>
<div class="row MT15">
  <div class="col-xs-12 col-sm-12">Số điện thoại</div>
  <div class="col-xs-12 col-sm-12 MT5">
    <input type="text" id="contactTelNo" name="contactTelNo" class="form-control" />
  </div>
</div>
<div class="row MT15">
 <div class="col-xs-12 col-sm-12">Mật khẩu</div>
 <div class="col-xs-12 col-sm-12 MT5">
  <input type="password" id="password" name="password" class="form-control" required="required" validationMessage="Trường nhập yêu cầu bắt buộc" placeholder="Gồm các ký tự 0-9, a-z, ít nhất 6 ký tự"/>
</div>
</div>
<div class="row MT15">
 <div class="col-xs-12 col-sm-12">Nhập lại mật khẩu</div>
 <div class="col-xs-12 col-sm-12 MT5">
  <input type="password" id="repassword" name="repassword" class="form-control" required="required" validationMessage="Trường nhập yêu cầu bắt buộc"/>
</div>
</div>

<div class="row MT15">
 <div class="col-xs-12 col-sm-12">
   <div class="checkbox-inline"> <input type="checkbox" id="agreement" name="agreement"> <label class="text-normal">Tôi đồng ý với điều khoản sử dụng. </label> </div> <span><a href="/dieu-khoan-su-dung" class="text-light-blue">Chi tiết</a></span>
 </div>
</div>
<div class="row MT15 MB15 text-center">
 <button class="btn btn-active" title="Đăng ký" id="btn-register" disabled="true">Đăng ký</button>
</div>
</form>
</div>
</div>

<style>
  div.checkbox:not(.alpaca-control) label:before, div.checkbox-inline:not(.alpaca-control) label:before, div.radio:not(.alpaca-control) label:before, div.radio-inline:not(.alpaca-control) label:before {
    border: 1px solid #111 !important;
  }
</style>

<script type="text/javascript">
  (function($){
    var validator = $("#fm").kendoValidator().data("kendoValidator");
   /* $("form").submit(function(event) {
       event.preventDefault();
       if (validator.validate()) {
         if (!$("#agreement").is(':checked')){
           alert('Bạn chưa đồng ý với điều khoản sử dụng!!!')
           return;
         }
         // TODO wait api
         console.log("sing up success");
         return true;
       }
     });*/

     $("#applicantIdDate").kendoDatePicker({
      format: "dd/MM/yyyy",
      culture: "vi-VN"
    });

     var datepicker = $("#applicantIdDate").data("kendoDatePicker");

     $("#applicantIdDate").click(function() {
       datepicker.open();
     });

     $("#btn-register").click(function(e){
      e.preventDefault();
      if(validator.validate()){
        if ($("#password").val().length < 6 || $("#repassword").val().length < 6){
         notification.show({ message:"Mật khẩu gồm các ký tự 0-9, a-z, ít nhất 6 ký tự" }, "error");
       } else if ($("#password").val() != $("#repassword").val()){
         notification.show({ message: "Xác nhận mật khẩu mới không đúng"}, "error");
       } else if (!$("#agreement").is(':checked')) {
        notification.show({ message: "Bạn chưa đồng ý với điều khoản sử dụng!!!"}, "error");
      }else{
        register();
      }
    }
  });

     var register = function(){
      var data = $('#fm').serialize();
      $.ajax({
        url: '${api.server}/applicants',
        type: 'POST',
        data: data,
        dataType : "json",
        success: function(result){

        },
        error: function(result){

        },
				statusCode: {
	        200: function(result) {
						notification.show({
	            title: "Success",
	            message: "Đăng ký thành công."
	          }, "success");

	          setTimeout(function(){
							window.location.href = "${portalURL}/confirm-account?active_user_id=" + result.applicantId;
	          }, 2000);
	        },
	        500: function(result) {
						if (JSON.parse(result.responseText).description == 'DuplicateContactEmailException'){
							notification.show({
								title: "Error",
								message: "Email bạn sử dụng đã tồn tại trong hệ thống."
							}, "error");
						} else if (JSON.parse(result.responseText).description == 'DuplicateApplicantIdException'){
							notification.show({
								title: "Error",
								message: "Số CMDN đã tồn tại trong hệ thống."
							}, "error");
						} else if (JSON.parse(result.responseText).description == 'DuplicateContactTelNoException'){
							notification.show({
								title: "Error",
								message: "Số điện thoại đã được sử dụng trong hệ thống."
							}, "error");
						} else {
							notification.show({
								title: "Error",
								message: "Đăng ký không thành công. Vui lòng thử lại."
							}, "error");
						}
	        }
	      }
      });
    }

    $('input[type=radio][name=applicantIdType]').change(function() {
      if (this.value == 'citizen') {
        $(this).closest('form').find("input[type=text],input[type=password], textarea").val("");
        $("#lblApplicantName").text("Họ và tên");
        $("#lblApplicantIdNo").text("Số CMND/ Hộ chiếu");
        $("#applicantName").attr("placeholder","Họ và tên");
        $("#applicantIdNo").attr("placeholder","Số CMND/ Hộ chiếu");
      }
      else  {
        $(this).closest('form').find("input[type=text],input[type=password], textarea").val("");
        $("#lblApplicantName").text("Tên tổ chức");
        $("#lblApplicantIdNo").text("Mã số thuế");
        $("#applicantName").attr("placeholder","Tên tổ chức");
        $("#applicantIdNo").attr("placeholder","Mã số thuế");
      }
    });

    $("#applicantIdDate").focusout(function(){
     setTimeout(function(){
      if ($("#applicantIdDate").hasClass("k-invalid")){
       $("#applicantIdDate").parent().addClass("MB25");
     } else {
       $("#applicantIdDate").parent().removeClass("MB25");
     }
   }, 100);
   });

    $("#agreement").click(function(){
      if($(this).is(':checked')){
        $("#btn-register").prop("disabled",false);
      }else {
        $("#btn-register").prop("disabled",true);
      }
    });

  })(jQuery);


</script>
