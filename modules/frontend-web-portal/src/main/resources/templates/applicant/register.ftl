<#if (Request)??>
	<#include "init.ftl">
</#if>

<div class="row row eq-height-lg">
	<div class="col-xs-12 col-sm-8 align-middle-lg">
		<div class="col-xs-12 bg-gif-register"></div>
	</div>
	<div class="col-xs-12 col-sm-4 box">
  	<form id="fm">
      <div class="row">
        <div class="box-title">
          <b>ĐĂNG KÝ TÀI KHOẢN</b>
        </div>
      </div>
      <div class="row MT10 MB10">
        <div class="col-xs-12 col-sm-12 text-center">
					<div class="radio-inline"> <input type="radio" name="applicantIdType" value="1" checked> <label>Công dân</label> </div>
					<div class="radio-inline"> <input type="radio" name="applicantIdType" value="2"> <label>Doanh nghiệp</label> </div>
        </div>
      </div>
      <div class="row MT15">
  			<div class="col-xs-12 col-sm-12" id="lblApplicantName">Họ và tên</div>
  			<div class="col-xs-12 col-sm-12 MT5">
  				<input type="text" id="applicantName" name="applicantName" class="form-control" required="required" validationMessage="Trường nhập yêu cầu bắt buộc" placeholder="Tên cá nhân làm thủ tục"/>
  			</div>
  		</div>
      <div class="row MT15">
        <div class="col-xs-12 col-sm-12" id="lblApplicantIdNo">Số CMTND/ Hộ chiếu</div>
        <div class="col-xs-12 col-sm-12 MT5">
          <input type="text" id="applicantIdNo" name="applicantIdNo" class="form-control" required="required" validationMessage="Trường nhập yêu cầu bắt buộc" placeholder="Số CMTND hoặc số Hộ chiếu"/>
        </div>
      </div>
      <div class="row MT15">
  			<div class="col-xs-12 col-sm-12">Ngày cấp</div>
  			<div class="col-xs-12 col-sm-12 MT5">
  				<input id="applicantIdDate" name="applicantIdDate" class="form-control" required="required" validationMessage="Trường nhập yêu cầu bắt buộc" placeholder="Ngày/ tháng/ năm"/>
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
          <input type="text" id="telNo" name="telNo" class="form-control" required="required" validationMessage="Trường nhập yêu cầu bắt buộc" placeholder="Nhập số điện thoại" />
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
  				<label><input type="checkbox" id="agreement" name="agreement"/>&nbsp;&nbsp;&nbsp;Tôi đồng ý với điều khoản sử dụng.</label> <a href="#" class="text-light-blue">Chi tiết</a>
  			</div>
  		</div>
      <div class="row MT15 MB15 text-center">
  			<button class="k-button btn-primary" title="Đăng ký" id="btn-register">Đăng ký</button>
  		</div>
  	</form>
  </div>
</div>

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
      format: "dd/MM/yyyy"
    });

    $("#btn-register").click(function(){
      if(validator.validate()){
        if ($("#password").val().length < 6 || $("#repassword").val().length < 6){
           notification.show({ message:" Xác nhận mật khẩu mới không đúng" }, "error");
         } else if ($("#password").val() != $("#repassword").val()){
           notification.show({ message: " Xác nhận mật khẩu mới không đúng"}, "error");
         } else if (!$("#agreement").is(':checked')) {
            notification.show({ message: "Bạn chưa đồng ý với điều khoản sử dụng!!!"}, "error");
         }else{
            register();
         }
      }
    });

    var register = function(){
        var data = $('#fm').serialize();
        console.log(data);
        $.ajax({
          type : 'POST',
          url  : '${api.server}/register',
          data : data,
          success :  function(result){
            notification.show({
              title: "Success",
              message: "Đăng ký thành công."
            }, "success");

          },
          error:function(result){
            notification.show({
              title: "Success",
              message: "Đăng ký không thành công."
            }, "error");
          }
        });
        console.log("success!");
    }

    $('input[type=radio][name=applicantIdType]').change(function() {
        if (this.value == '1') {
          $(this).closest('form').find("input[type=text],input[type=password], textarea").val("");
          $("#lblApplicantName").text("Họ và tên");
          $("#lblApplicantIdNo").text("Số CMTND/ Hộ chiếu");
          $("#applicantName").attr("placeholder","Họ và tên");
          $("#applicantIdNo").attr("placeholder","Số CMTND/ Hộ chiếu");
        }
        else  {
          $(this).closest('form').find("input[type=text],input[type=password], textarea").val("");
          $("#lblApplicantName").text("Tên tổ chức");
          $("#lblApplicantIdNo").text("Mã số thuế");
          $("#applicantName").attr("placeholder","Tên tổ chức");
          $("#applicantIdNo").attr("placeholder","Mã số thuế");
        }
    });

  })(jQuery);


</script>
