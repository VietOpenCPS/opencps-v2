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
     <div class="row MT10 MB10">
      <div class="col-xs-12 col-sm-12 text-center">
        <div class="radio-inline"> <input type="radio" id="rdBusiness" name="applicantIdType" value="business" checked> <label>Doanh nghiệp</label> </div>
        <div class="radio-inline"> <input type="radio" id="rdCitizen" name="applicantIdType" value="citizen"> <label>Công dân</label> </div>
     </div>
   </div>
   <div class="row MT15">
     <div class="col-xs-12 col-sm-12" ><span id="lblApplicantName">Tên tổ chức</span> <span class="red">(*)</span></div>
     <div class="col-xs-12 col-sm-12 MT5">
      <input type="text" id="applicantName" name="applicantName" class="form-control" required="required" validationMessage="Trường nhập yêu cầu bắt buộc" placeholder="Tên tổ chức"/>
    </div>
  </div>
  <div class="row MT15">
    <div class="col-xs-12 col-sm-12" ><span id="lblApplicantIdNo">Mã số thuế</span> <span class="red">(*)</span></div>
    <div class="col-xs-12 col-sm-12 MT5">
      <input type="text" id="applicantIdNo" name="applicantIdNo" class="form-control" required="required" validationMessage="Trường nhập yêu cầu bắt buộc" placeholder="Mã số thuế"/>
    </div>
  </div>
  <div class="row MT15">
   <div class="col-xs-12 col-sm-12">Ngày cấp <span class="red">(*)</span></div>
   <div class="col-xs-12 col-sm-12 MT5">
    <input id="applicantIdDate" name="applicantIdDate" required="required" validationMessage="Trường nhập yêu cầu bắt buộc" placeholder="Ngày/ tháng/ năm"/>
  </div>
</div>
<div class="row MT15">
  <div class="col-xs-12 col-sm-12">Thư điện tử <span class="red">(*)</span></div>
  <div class="col-xs-12 col-sm-12 MT5">
    <input type="text" id="contactEmail" name="contactEmail" class="form-control" required="required" validationMessage="Trường nhập yêu cầu bắt buộc" placeholder="Địa chỉ thư điện tử" />
  </div>
</div>
<div class="row MT15">
  <div class="col-xs-12 col-sm-12">Số điện thoại</div>
  <div class="col-xs-12 col-sm-12 MT5">
    <input type="text" id="contactTelNo" name="contactTelNo" class="form-control" placeholder="Số điện thoại"/>
  </div>
</div>
<div class="row MT15">
 <div class="col-xs-12 col-sm-12">Mật khẩu <span class="red">(*)</span></div>
 <div class="col-xs-12 col-sm-12 MT5">
  <input type="password" id="password" name="password" class="form-control" required="required" validationMessage="Trường nhập yêu cầu bắt buộc" placeholder="Gồm các ký tự 0-9, a-z, ít nhất 6 ký tự"/>
</div>
</div>
<div class="row MT15">
 <div class="col-xs-12 col-sm-12">Nhập lại mật khẩu <span class="red">(*)</span></div>
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
      //var data = $('#fm').serialize();

      var applicantIdType = "";
      if($("#rdCitizen").prop("checked")){
        applicantIdType = "citizen";
      }else {
        applicantIdType = "business";
      }

      var data = {
        applicantIdType : applicantIdType,
        applicantName : $("#applicantName").val(),
        applicantIdNo : $("#applicantIdNo").val(),
        applicantIdDate : $("#applicantIdDate").val(),
        contactEmail : $("#contactEmail").val(),
        contactTelNo : $("#contactTelNo").val(),
        password : $("#password").val()
      }
      
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
  <p align="center"><span style="font-family: 'Times New Roman', serif;"><span style="font-size: medium;"><strong><span style="font-size: large;">CH&Iacute;NH S&Aacute;CH SỬ DỤNG T&Agrave;I KHOẢN</span></strong></span></span></p>
<p align="justify"><span style="font-family: 'Times New Roman', serif;"><span style="font-size: medium;"><span style="font-size: large;">&nbsp; &nbsp; &nbsp;Cổng dịch vụ c&ocirc;ng trực tuyến tỉnh Ph&uacute; Thọ tr&acirc;n trọng c&ocirc;ng bố dưới đ&acirc;y ch&iacute;nh s&aacute;ch đảm bảo quyền ri&ecirc;ng tư hợp ph&aacute;p của người sử dụng Cổng dịch vụ c&ocirc;ng trực tuyến tỉnh Ph&uacute; Thọ tại địa chỉ&nbsp;</span><span style="color: #0070c0;"><span style="font-size: large;"><a href="http://hanhchinhcong.phutho.gov.vn/">http://hanhchinhcong.phutho.gov.vn</a> </span></span></span></span></p>
<p align="justify"><span style="font-family: 'Times New Roman', serif;"><span style="font-size: medium;"><span style="font-size: large;">&nbsp; &nbsp; Để đảm bảo an to&agrave;n, bảo mật th&ocirc;ng tin đề nghị c&ocirc;ng d&acirc;n, c&aacute;c tổ chức, doanh nghiệp trong tỉnh đọc kỹ c&aacute;c quy định trong bản ch&iacute;nh s&aacute;ch n&agrave;y. </span></span></span></p>
<p align="justify"><span style="font-family: 'Times New Roman', serif;"><span style="font-size: medium;"><strong><span style="font-size: large;">Tự động thu thập c&aacute;c th&ocirc;ng tin kh&ocirc;ng thuộc c&aacute; nh&acirc;n</span></strong></span></span></p>
<p align="justify"><span style="font-family: 'Times New Roman', serif;"><span style="font-size: medium;"><span style="font-size: large;">Cổng dịch vụ c&ocirc;ng trực tuyến tỉnh Ph&uacute; Thọ cam kết bảo vệ quyền ri&ecirc;ng tư của những người viếng thăm Cổng dịch vụ c&ocirc;ng trực tuyến tỉnh Ph&uacute; Thọ.</span></span></span></p>
<p align="justify"><span style="font-family: 'Times New Roman', serif;"><span style="font-size: medium;"><span style="font-size: large;">Xin lưu &yacute; rằng Cổng dịch vụ c&ocirc;ng trực tuyến tỉnh Ph&uacute; Thọ c&oacute; thể cung cấp c&aacute;c số liệu thống k&ecirc; tổng hợp về c&aacute;c kh&aacute;ch viếng thăm, h&igrave;nh thức kết nối v&agrave;o Cổng dịch vụ c&ocirc;ng trực tuyến tỉnh Ph&uacute; Thọ v&agrave; c&aacute;c th&ocirc;ng tin li&ecirc;n quan đến Cổng dịch vụ c&ocirc;ng trực tuyến tỉnh Ph&uacute; Thọ cho những b&ecirc;n thứ ba c&oacute; uy t&iacute;n, nhưng những thống k&ecirc; n&agrave;y sẽ kh&ocirc;ng chứa c&aacute;c th&ocirc;ng tin nhận diện c&aacute; nh&acirc;n.</span></span></span></p>
<p align="justify"><span style="font-family: 'Times New Roman', serif;"><span style="font-size: medium;"><strong><span style="font-size: large;">Thu thập th&ocirc;ng tin c&aacute; nh&acirc;n với sự đồng &yacute; của người d&ugrave;ng</span></strong></span></span></p>
<p align="justify"><span style="font-family: 'Times New Roman', serif;"><span style="font-size: medium;"><span style="font-size: large;">Để đ&aacute;p ứng y&ecirc;u cầu của qu&yacute; vị, hoặc quản l&yacute; những chương tr&igrave;nh tương t&aacute;c với người sử dụng như g&oacute;p &yacute;, Cổng dịch vụ c&ocirc;ng trực tuyến tỉnh Ph&uacute; Thọ c&oacute; thể cần phải hỏi những th&ocirc;ng tin c&aacute; nh&acirc;n của qu&yacute; vị như t&ecirc;n, địa chỉ, v&agrave; địa chỉ email.</span></span></span></p>
<p align="justify"><span style="font-family: 'Times New Roman', serif;"><span style="font-size: medium;"><span style="font-size: large;">Cổng dịch vụ c&ocirc;ng trực tuyến tỉnh Ph&uacute; Thọ c&oacute; thể sử dụng những th&ocirc;ng tin n&agrave;y để đ&aacute;p ứng c&aacute;c y&ecirc;u cầu của qu&yacute; vị, hoặc li&ecirc;n lạc với qu&yacute; vị bằng email để gửi th&ocirc;ng tin về hoạt động v&agrave; c&aacute;c sự kiện của ch&uacute;ng t&ocirc;i.</span></span></span></p>
<p align="justify"><span style="font-family: 'Times New Roman', serif;"><span style="font-size: medium;"><span style="font-size: large;">Ngo&agrave;i những th&ocirc;ng tin c&aacute; nh&acirc;n m&agrave; qu&yacute; vị c&oacute; thể cung cấp cho Cổng dịch vụ c&ocirc;ng trực tuyến tỉnh Ph&uacute; Thọ, Cổng dịch vụ c&ocirc;ng trực tuyến tỉnh Ph&uacute; Thọ c&oacute; thể sử dụng c&ocirc;ng nghệ cho ph&eacute;p thu thập một số th&ocirc;ng tin kỹ thuật của qu&yacute; vị như địa chỉ giao thức Internet, hệ điều h&agrave;nh tr&ecirc;n m&aacute;y t&iacute;nh, kiểu tr&igrave;nh duyệt, c&aacute;c kiểu lưu th&ocirc;ng mạng v&agrave; địa chỉ của bất kỳ trang web li&ecirc;n quan n&agrave;o.</span></span></span></p>
<p align="justify"><span style="font-family: 'Times New Roman', serif;"><span style="font-size: medium;"><strong><span style="font-size: large;">Bảo mật</span></strong></span></span></p>
<p align="justify"><span style="font-family: 'Times New Roman', serif;"><span style="font-size: medium;"><span style="font-size: large;">Xin lưu &yacute; rằng lu&ocirc;n c&oacute; những rủi ro gắn với việc cung cấp dữ liệu c&aacute; nh&acirc;n một c&aacute;ch trực tiếp, qua điện thoại hoặc qua Internet v&agrave; kh&ocirc;ng c&oacute; hệ thống c&ocirc;ng nghệ n&agrave;o tuyệt đối an to&agrave;n để chống lại sự quấy nhiễu hoặc hacker.</span></span></span></p>
<p align="justify"><span style="font-family: 'Times New Roman', serif;"><span style="font-size: medium;"><span style="font-size: large;">Tuy nhi&ecirc;n, Cổng dịch vụ c&ocirc;ng trực tuyến tỉnh Ph&uacute; Thọ lu&ocirc;n cố gắng sử dụng c&aacute;c biện ph&aacute;p th&iacute;ch hợp để ngăn cản v&agrave; giảm thiểu những rủi ro của việc truy cập tr&aacute;i ph&eacute;p, sử dụng kh&ocirc;ng đ&uacute;ng đắn v&agrave; kh&ocirc;ng ch&iacute;nh x&aacute;c c&aacute;c th&ocirc;ng tin c&aacute; nh&acirc;n của qu&yacute; vị.</span></span></span></p>
<p align="justify"><span style="font-family: 'Times New Roman', serif;"><span style="font-size: medium;"><strong><span style="font-size: large;">T&iacute;nh ch&iacute;nh x&aacute;c của th&ocirc;ng tin thu thập</span></strong></span></span></p>
<p align="justify"><span style="font-family: 'Times New Roman', serif;"><span style="font-size: medium;"><span style="font-size: large;">Cổng dịch vụ c&ocirc;ng trực tuyến tỉnh Ph&uacute; Thọ c&oacute; thể bổ sung, sửa chữa hoặc x&oacute;a bất kỳ dữ liệu c&aacute; nh&acirc;n n&agrave;o kh&ocirc;ng ho&agrave;n chỉnh, kh&ocirc;ng ch&iacute;nh x&aacute;c hoặc lạc hậu m&agrave; Cổng dịch vụ c&ocirc;ng trực tuyến tỉnh Ph&uacute; Thọ c&oacute; được li&ecirc;n quan đến hoạt động của Cổng dịch vụ c&ocirc;ng trực tuyến tỉnh Ph&uacute; Thọ. </span></span></span></p>
<p align="justify"><span style="font-family: 'Times New Roman', serif;"><span style="font-size: medium;"><strong><span style="font-size: large;">Nhận diện kh&aacute;ch viếng thăm</span></strong></span></span></p>
<p align="justify"><span style="font-family: 'Times New Roman', serif;"><span style="font-size: medium;"><span style="font-size: large;">Đ&ocirc;i khi th&ocirc;ng tin c&oacute; thể được đưa v&agrave;o m&aacute;y t&iacute;nh của qu&yacute; vị để cho ph&eacute;p ch&uacute;ng t&ocirc;i nhận diện qu&yacute; vị. Th&ocirc;ng tin n&agrave;y thường được gọi l&agrave; c&aacute;c &ldquo;cookies&rdquo;. Qua việc cho biết c&aacute;ch thức v&agrave; thời gian m&agrave; c&aacute;c kh&aacute;ch h&agrave;ng viếng thăm Cổng dịch vụ c&ocirc;ng trực tuyến tỉnh Ph&uacute; Thọ n&agrave;y, th&ocirc;ng tin n&agrave;y c&oacute; thể gi&uacute;p Cổng dịch vụ c&ocirc;ng trực tuyến tỉnh Ph&uacute; Thọ tiếp tục cải thiện Cổng dịch vụ c&ocirc;ng trực tuyến tỉnh Ph&uacute; Thọ.</span></span></span></p>
<p align="justify"><span style="font-family: 'Times New Roman', serif;"><span style="font-size: medium;"><span style="font-size: large;">Ch&uacute;ng t&ocirc;i sẽ chỉ sử dụng cookies để xem th&ocirc;ng tin tr&ecirc;n đĩa cứng của qu&yacute; vị m&agrave; những th&ocirc;ng tin n&agrave;y được một cookies từ Cổng dịch vụ c&ocirc;ng trực tuyến tỉnh Ph&uacute; Thọ n&agrave;y đặt v&agrave;o đ&oacute;. Việc sử dụng cookies l&agrave; một ti&ecirc;u chuẩn được c&ocirc;ng nhận rộng r&atilde;i v&agrave; nhiều Cổng th&ocirc;ng tin điện tử, website kh&aacute;c cũng sử dụng ch&uacute;ng. C&aacute;c cookies được lưu tr&ecirc;n m&aacute;y t&iacute;nh của qu&yacute; vị chứ kh&ocirc;ng phải tr&ecirc;n Cổng dịch vụ c&ocirc;ng trực tuyến tỉnh Ph&uacute; Thọ.</span></span></span></p>
<p align="justify"><span style="font-family: 'Times New Roman', serif;"><span style="font-size: medium;"><span style="font-size: large;">Nếu qu&yacute; vị kh&ocirc;ng muốn nhận c&aacute;c cookies, hoặc muốn được th&ocirc;ng b&aacute;o khi ch&uacute;ng được sử dụng, th&igrave; qu&yacute; vị cần thiết lập chế độ cho tr&igrave;nh duyệt của qu&yacute; vị l&agrave;m điều n&agrave;y, nếu tr&igrave;nh duyệt của qu&yacute; vị cho ph&eacute;p.</span></span></span></p>
<p align="justify"><span style="font-family: 'Times New Roman', serif;"><span style="font-size: medium;"><strong><span style="font-size: large;">Thay đổi ch&iacute;nh s&aacute;ch ri&ecirc;ng tư</span></strong></span></span></p>
<p align="justify"><span style="font-family: 'Times New Roman', serif;"><span style="font-size: medium;"><span style="font-size: large;">Cổng dịch vụ c&ocirc;ng trực tuyến tỉnh Ph&uacute; Thọ c&oacute; thể thay đổi ch&iacute;nh s&aacute;ch về quyền ri&ecirc;ng tư n&agrave;y, hoặc thay đổi, sửa đổi hoặc r&uacute;t lại sự truy cập đến Cổng dịch vụ c&ocirc;ng trực tuyến tỉnh Ph&uacute; Thọ, hoặc nội dung của những trang n&agrave;y v&agrave;o bất kỳ l&uacute;c n&agrave;o, c&oacute; hoặc kh&ocirc;ng c&oacute; th&ocirc;ng b&aacute;o trước.</span></span></span></p>
<p>&nbsp;</p>
<p align="right"><span style="font-family: 'Times New Roman', serif;"><span style="font-size: medium;"><em><span style="font-size: large;"><strong>Trung t&acirc;m Phục vụ H&agrave;nh ch&iacute;nh c&ocirc;ng</strong></span></em><em><span style="font-size: large;"> trực thuộc UBND tỉnh Ph&uacute; Thọ</span></em></span></span></p>

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




