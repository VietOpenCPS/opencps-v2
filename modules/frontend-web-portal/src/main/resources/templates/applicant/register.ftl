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
     <input type="hidden" name="applicantIdType" value="business" >
    <#-- <div class="row MT10 MB10">
      <div class="col-xs-12 col-sm-12 text-center">
       <div class="radio-inline"> <input type="radio" name="applicantIdType" value="citizen" checked> <label>Công dân</label> </div>
       <div class="radio-inline"> <input type="radio" name="applicantIdType" value="business"> <label>Doanh nghiệp</label> </div>
     </div>
   </div> -->
   <div class="row MT15">
     <div class="col-xs-12 col-sm-12" id="lblApplicantName">Tên tổ chức</div>
     <div class="col-xs-12 col-sm-12 MT5">
      <input type="text" id="applicantName" name="applicantName" class="form-control" required="required" validationMessage="Trường nhập yêu cầu bắt buộc" placeholder="Tên tổ chức"/>
    </div>
  </div>
  <div class="row MT15">
    <div class="col-xs-12 col-sm-12" id="lblApplicantIdNo">Mã số thuế</div>
    <div class="col-xs-12 col-sm-12 MT5">
      <input type="text" id="applicantIdNo" name="applicantIdNo" class="form-control" required="required" validationMessage="Trường nhập yêu cầu bắt buộc" placeholder="Mã số thuế"/>
    </div>
  </div>
  <div class="row MT15">
   <div class="col-xs-12 col-sm-12">Ngày cấp giấy đăng ký kinh doanh (lần gần nhất)</div>
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
    <input type="text" id="contactTelNo" name="contactTelNo" class="form-control" required="required" validationMessage="Trường nhập yêu cầu bắt buộc" placeholder="Số điện thoại"/>
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
   <div class="checkbox-inline"> <input type="checkbox" id="agreement" name="agreement"> <label class="text-normal">Tôi đồng ý với điều khoản sử dụng. </label> </div> <span><a href="javascript:;" id="viewRules" class="text-light-blue">Chi tiết</a></span>
 </div>
</div>
<div class="row MT15 MB15 text-center">
  <div class="captcha-chat">
    <div class="captcha-container media">
      <div class="media-body">             
      </div>
      <div id="captcha">
        <div class="controls">
          <input class="user-text btn-common form-control" placeholder="Nhập mã xác nhận" type="text" />
          <!-- <button class="validate btn-common">
            this image should be converted into inline svg
            <img src="img/enter_icon.png" alt="submit icon">
          </button> -->
          <button class="refresh btn-common">
            <!-- this image should be converted into inline svg -->
            <img src="img/refresh_icon.png" alt="refresh icon">
          </button>
        </div>
      </div>
      <p class="wrong info">Sai mã xác nhận</p>
    </div>
  </div>
</div>
<div class="row MT15 MB15 text-center">
 <button class="btn btn-active" title="Đăng ký" id="btn-register" disabled="true">Đăng ký</button>
</div>
</form>
</div>
</div>

<style>
  p.wrong {
    display: none;
  }

  p.wrong.shake {
    display: block;
  }

  p.wrong.shake {
    animation: shake .4s cubic-bezier(.36, .07, .19, .97) both;
    transform: translate3d(0, 0, 0);
    backface-visibility: hidden;
    perspective: 1000px;
  }

  @keyframes shake {
    10%,
    90% {
      transform: translate3d(-1px, 0, 0);
    }
    20%,
    80% {
      transform: translate3d(1px, 0, 0);
    }
    30%,
    50%,
    70% {
      transform: translate3d(-2px, 0, 0);
    }
    40%,
    60% {
      transform: translate3d(2px, 0, 0);
    }
  }

  .controls img {
    height: 20px;
  }
</style>

<script type="text/javascript">
  var captcha;
  document.addEventListener("DOMContentLoaded", function() {
    document.body.scrollTop;

    var timeout;

    captcha = new $.Captcha({
      onFailure: function() {

        $(".captcha-chat .wrong").show({
          duration: 30,
          done: function() {
            var that = this;
            clearTimeout(timeout);
            $(this).removeClass("shake");
            $(this).css("animation");
            $(this).addClass("shake");
            var time = parseFloat($(this).css("animation-duration")) * 1000;
            timeout = setTimeout(function() {
              $(that).removeClass("shake");
            }, time);
          }
        });

      },
      onSuccess: function() {
        // alert("CORRECT!!!");
      }
    });
    captcha.generate();
  });
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
      if (!captcha.validate()) {
        return
      }
      var data = $('#fm').serialize();
      $.ajax({
        url: '${api.server}/applicants',
        type: 'POST',
        data: data,
        dataType : "json",
        headers: {
          "groupId": ${groupId}
        },
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
           window.location.href = "${portalURL}/web${(themeDisplay.getScopeGroup().getFriendlyURL())!}/confirm-account?active_user_id=" + result.applicantId;
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
  // ++++++++++++++++++ fix 27/12 congtrinh0209>
    // $('input[type=radio][name=applicantIdType]').change(function() {
    //   if (this.value == 'citizen') {
    //     $(this).closest('form').find("input[type=text],input[type=password], textarea").val("");
    //     $("#lblApplicantName").text("Họ và tên");
    //     $("#lblApplicantIdNo").text("Số CMND/ Hộ chiếu");
    //     $("#applicantName").attr("placeholder","Họ và tên");
    //     $("#applicantIdNo").attr("placeholder","Số CMND/ Hộ chiếu");
    //   }
    //   else  {
    //     $(this).closest('form').find("input[type=text],input[type=password], textarea").val("");
    //     $("#lblApplicantName").text("Tên tổ chức");
    //     $("#lblApplicantIdNo").text("Mã số thuế");
    //     $("#applicantName").attr("placeholder","Tên tổ chức");
    //     $("#applicantIdNo").attr("placeholder","Mã số thuế");
    //   }
    // });
  // +++++++++++++++++++++++++++
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

    $("#viewRules").click(function(e){
      e.preventDefault();

    });

  })(jQuery);


</script>
<#-- Phần nội dung điều khoản sử dụng -->
<div id="rules" style="background-color: #ffffff; color: black" class="MT20">
   <div style="padding: 15px;"><ol> <li style="text-align:justify;background-image:initial;background-position:initial;background-size:initial;background-repeat:initial;background-attachment:initial;background-origin:initial;background-clip:initial;"><span style="color:black;"><span style="font-family:times new roman,serif;"><span style="font-size:12.0pt;">Tổ chức, cá nhân có nhu cầu sử dụng dịch vụ công trực tuyến có quyền đề nghị cấp, sửa đổi, bổ sung thông tin tài&nbsp;khoản, tạm dừng, hủy tài khoản tại Cổng thông tin điện tử&nbsp;cung cấp dịch vụ&nbsp;công trực tuyến của Bộ Văn hóa, Thể thao và Du lịch. </span></span></span></li> <li style="text-align:justify;background-image:initial;background-position:initial;background-size:initial;background-repeat:initial;background-attachment:initial;background-origin:initial;background-clip:initial;"><span style="color:black;"><span style="font-family:times new roman,serif;"><span style="font-size:12.0pt;">Các Tổng cục, Cục, Vụ thực hiện TTHC trên môi trường mạng thông qua phần mềm Quản lý hồ sơ Một cửa có trách nhiệm gửi yêu cầu cấp phát, sửa đổi, bổ sung, tạm dừng, hủy tài khoản của Lãnh đạo, cán bộ thực hiện dịch vụ công trực tuyến đến Trung tâm Công nghệ thông tin.</span></span></span></li> <li style="text-align:justify;background-image:initial;background-position:initial;background-size:initial;background-repeat:initial;background-attachment:initial;background-origin:initial;background-clip:initial;"><span style="color:black;"><span style="font-family:times new roman,serif;"><span style="font-size:12.0pt;">Tổ chức, cá nhân đề nghị cấp, sửa đổi, bổ sung thông tin, tạm dừng, hủy tài khoản hoàn toàn chịu trách nhiệm về tính chính xác của thông tin cung cấp, thực hiện thay đổi mật khẩu ngay khi được cấp phát và chịu trách nhiệm quản lý thông tin tài khoản.</span></span></span></li> <li style="text-align:justify;background-image:initial;background-position:initial;background-size:initial;background-repeat:initial;background-attachment:initial;background-origin:initial;background-clip:initial;"><span style="color:black;"><span style="font-family:times new roman,serif;"><span style="font-size:12.0pt;">Trung tâm Công nghệ thông tin phối hợp với các cơ quan, đơn vị liên quan có trách nhiệm cấp, sửa đổi, bổ sung thông tin, tạm dừng, hủy tài khoản sử&nbsp;dụng hệ thống dịch vụ công trực tuyến. Trường hợp không đáp ứng yêu cầu phải nêu rõ lý do.</span></span></span></li> <li style="text-align:justify;background-image:initial;background-position:initial;background-size:initial;background-repeat:initial;background-attachment:initial;background-origin:initial;background-clip:initial;"><span style="color:black;"><span style="font-family:times new roman,serif;"><span style="font-size:12.0pt;">Trường hợp tổ chức, cá nhân đăng nhập vào hệ thống dịch vụ công trực tuyến quá 05 (năm) lần mà không thành công thì&nbsp;hệ thống dịch vụ công trực tuyến sẽ&nbsp;tự động tạm dừng tài khoản. Tổ chức, cá nhân liên hệ với Trung tâm Công nghệ thông tin để kích hoạt lại thông tin tài khoản.</span></span></span></li> <li style="text-align:justify;background-image:initial;background-position:initial;background-size:initial;background-repeat:initial;background-attachment:initial;background-origin:initial;background-clip:initial;"><span style="color:black;"><span style="font-family:times new roman,serif;"><span style="font-size:12.0pt;">Trường hợp phát hiện tổ&nbsp;chức, cá nhân trong quá trình thực hiện dịch vụ công trực tuyến có hành vi gây ảnh hưởng tới sự ổn định của hệ thống dịch vụ công trực tuyến, các Tổng cục, Cục, Vụ và cơ quan, đơn vị có liên quan thông báo cho Trung tâm Công nghệ thông tin tạm dừng tài&nbsp;khoản&nbsp;của tổ chức, cá nhân đó và nêu rõ lý do.</span></span></span></li> </ol></div>

</div>

<div class="responsive-message"></div>

<script>
  $(document).ready(function() {
    var viewRules = $("#rules");
    $("#viewRules").click(function() {
        viewRules.data("kendoWindow").open();
        $("div.k-widget.k-window").css({"background-color":"#14bef0","color":"#ffffff"})
    });
    viewRules.kendoWindow({
        title: "ĐIỀU KHOẢN SỬ DỤNG :",
        width: "50%",
        height: "500px",
        visible: false,
        actions: [
          "Maximize",
          "Close"
        ],
        modal: true,
        position: {
          top: "50px"
        },
        scrollable: true
    }).data("kendoWindow").center().close();
  });
</script>




